package io.github.javautm.core.web

import io.github.javautm.core.parser.UtmParser
import io.github.javautm.core.persistence.UtmPersistenceHandler
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

/**
 * Spring MVC interceptor that parses UTM parameters from every incoming
 * request and stores them in [UtmHolder] for the duration of the request.
 *
 * It also calls the [UtmPersistenceHandler.handle] method if implemented.
 */
class UtmInterceptor(
    private val parser: UtmParser,
    private val handlers: List<UtmPersistenceHandler>
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val parameters = parser.parse(request)

        handlers.forEach {
            it.handle(parameters)
        }

        UtmHolder.set(parameters)

        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        UtmHolder.clear()
    }

}