package io.github.javautm.core.web

import io.github.javautm.core.model.UtmParameters

/**
 * Holds the [UtmParameters] for the current request thread.
 *
 * The interceptor populates this holder at the start of each request and
 * clears it after the request completes. Application code can read from it at
 * any point during request processing
 */
object UtmHolder {

    private val HOLDER: ThreadLocal<UtmParameters> = ThreadLocal()

    /**
     * @return the UTM parameters bound to the current thread or an empty
     * [UtmParameters] if none are present
     */
    @JvmStatic
    fun get(): UtmParameters {
        return HOLDER.get() ?: UtmParameters.empty()
    }

    internal fun set(params: UtmParameters?) {
        HOLDER.set(params)
    }

    internal fun clear() {
        HOLDER.remove()
    }

}