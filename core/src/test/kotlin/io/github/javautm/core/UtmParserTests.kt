package io.github.javautm.core

import io.github.javautm.core.model.UtmParameterNames
import io.github.javautm.core.model.UtmParameters
import io.github.javautm.core.parser.UtmParser
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UtmParserTests {

    private val parser = UtmParser()

    // -------------------------------------------------------------------------
    // HttpServletRequest parsing
    // -------------------------------------------------------------------------

    @Test
    fun `should parse all params from request`() {
        val request = MockHttpServletRequest()
        request.addParameter(UtmParameterNames.SOURCE, "source")
        request.addParameter(UtmParameterNames.MEDIUM, "medium")
        request.addParameter(UtmParameterNames.CAMPAIGN, "campaign")
        request.addParameter(UtmParameterNames.TERM, "term")
        request.addParameter(UtmParameterNames.CONTENT, "content")

        val utm = parser.parse(request)

        assertEquals("source", utm.source)
        assertEquals("medium", utm.medium)
        assertEquals("campaign", utm.campaign)
        assertEquals("term", utm.term)
        assertEquals("content", utm.content)
    }

    @Test
    fun `should return empty from request without query params`() {
        val request = MockHttpServletRequest()

        assertTrue(parser.parse(request).isEmpty())
    }

    // ------------------------------------------------------------------
    // URL string parsing
    // ------------------------------------------------------------------

    @Test
    fun `should parse all params from url`() {
        val url = "https://example.com/something" +
                "?utm_source=source" +
                "&utm_medium=medium" +
                "&utm_campaign=campaign" +
                "&utm_term=term" +
                "&utm_content=content"

        val utm = parser.parse(url)

        assertEquals("source", utm.source)
        assertEquals("medium", utm.medium)
        assertEquals("campaign", utm.campaign)
        assertEquals("term", utm.term)
        assertEquals("content", utm.content)
    }

    @Test
    fun `should parse encoded values`() {
        val utm = parser.parse("https://example.com/?utm_source=google&utm_campaign=spring%20sale")

        assertEquals("google", utm.source)
        assertEquals("spring sale", utm.campaign)
    }

    @Test
    fun `should return empty from url without query params`() {
        assertEquals(UtmParameters.empty(), parser.parse("https://example.com/something"))
    }

    @Test
    fun `should return empty for null url`() {
        assertEquals(UtmParameters.empty(), parser.parse(null))
    }

    @Test
    fun `should ignore non utm query params`() {
        val utm = parser.parse("https://example.com/?ref=homepage&utm_source=google")

        assertEquals("google", utm.source)
        assertNull(utm.medium)
        assertNull(utm.campaign)
        assertNull(utm.term)
        assertNull(utm.content)
    }

}