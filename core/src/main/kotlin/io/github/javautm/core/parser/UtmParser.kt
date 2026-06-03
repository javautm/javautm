package io.github.javautm.core.parser

import io.github.javautm.core.model.UtmParameterNames
import io.github.javautm.core.model.UtmParameters
import jakarta.servlet.http.HttpServletRequest
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * Parses [UtmParameters] from a [HttpServletRequest] or a raw URL string.
 */
class UtmParser {

    /**
     * Parses UTM parameters from a [HttpServletRequest]
     *
     * @param request the incoming HTTP request
     * @return a (possibly empty) [UtmParameters] instance
     */
    fun parse(request: HttpServletRequest): UtmParameters {
        return UtmParameters.builder()
            .source(request.getParameter(UtmParameterNames.SOURCE))
            .medium(request.getParameter(UtmParameterNames.MEDIUM))
            .campaign(request.getParameter(UtmParameterNames.CAMPAIGN))
            .term(request.getParameter(UtmParameterNames.TERM))
            .content(request.getParameter(UtmParameterNames.CONTENT))
            .build()
    }

    /**
     * Parses UTM parameters from a full URL string (including query string)
     *
     * @param url the URL to parse
     * @return a (possibly empty) [UtmParameters] instance
     */
    fun parse(url: String?): UtmParameters {
        if (url.isNullOrBlank()) return UtmParameters.empty()

        val queryStart = url.indexOf('?')
        if (queryStart == -1 || queryStart == url.length - 1) return UtmParameters.empty()

        val params = parseQueryString(url.substring(queryStart + 1))

        return UtmParameters.builder()
            .source(params[UtmParameterNames.SOURCE])
            .medium(params[UtmParameterNames.MEDIUM])
            .campaign(params[UtmParameterNames.CAMPAIGN])
            .term(params[UtmParameterNames.TERM])
            .content(params[UtmParameterNames.CONTENT])
            .build()
    }

    private fun parseQueryString(queryString: String): Map<String, String> {
        return queryString
            .split('&')
            .asSequence()
            .filter { it.isNotBlank() }
            .fold(LinkedHashMap()) { params, pair ->
                val eqIndex = pair.indexOf('=')

                if (eqIndex == -1) {
                    params.putIfAbsent(decode(pair), "")
                } else {
                    val key = decode(pair.substring(0, eqIndex))
                    val value = decode(pair.substring(eqIndex + 1))
                    params.putIfAbsent(key, value)
                }

                params
            }
    }

    companion object {

        @JvmStatic
        private fun decode(value: String): String {
            return URLDecoder.decode(value, StandardCharsets.UTF_8)
        }

    }

}