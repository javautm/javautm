package io.github.javautm.core

import io.github.javautm.core.parser.UtmParser
import io.github.javautm.core.persistence.UtmPersistenceHandler
import io.github.javautm.core.web.UtmHolder
import io.github.javautm.core.web.UtmInterceptor
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UtmInterceptorTests {

    private val handler = mockk<UtmPersistenceHandler>(relaxed = true)
    private val interceptor = UtmInterceptor(UtmParser(), listOf(handler))

    @AfterEach
    fun tearDown() {
        UtmHolder.clear()
    }

    @Test
    fun `should populate holder on pre handle`() {
        val request = MockHttpServletRequest()
        request.addParameter("utm_source", "google")
        request.addParameter("utm_medium", "cpc")

        val proceed = interceptor.preHandle(request, MockHttpServletResponse(), Any())

        assertTrue(proceed)
        assertTrue(UtmHolder.get().isPresent())
        assertEquals("google", UtmHolder.get().source)
        assertEquals("cpc", UtmHolder.get().medium)
    }

    @Test
    fun `should clear holder after completion`() {
        val request = MockHttpServletRequest()
        request.addParameter("utm_source", "google")

        interceptor.preHandle(request, MockHttpServletResponse(), Any())
        assertTrue(UtmHolder.get().isPresent())

        interceptor.afterCompletion(request, MockHttpServletResponse(), Any(), null)
        assertFalse(UtmHolder.get().isPresent())
    }

    @Test
    fun `holder should be empty when no utm params`() {
        interceptor.preHandle(MockHttpServletRequest(), MockHttpServletResponse(), Any())

        assertFalse(UtmHolder.get().isPresent())
        assertTrue(UtmHolder.get().isEmpty())
    }

    @Test
    fun `should always continue no matter if exception`() {
        val proceed = interceptor.preHandle(
            MockHttpServletRequest(), MockHttpServletResponse(), Any()
        )

        assertTrue(proceed)
    }

    @Test
    fun `should call handle when a persistence handler is present`() {
        interceptor.preHandle(
            MockHttpServletRequest(),
            MockHttpServletResponse(),
            Any()
        )

        verify {
            handler.handle(any())
        }
    }

}