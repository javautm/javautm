package io.github.javautm.core.persistence

import io.github.javautm.core.model.UtmParameters

/**
 * Contract for components that persist or process parsed UTM parameters.
 *
 * Implementations can store the parameters in a database, cookies,
 * a message broker, analytics platforms, or any other destination.
 *
 * All registered handlers are invoked for every request after the
 * UTM parameters have been parsed.
 */
interface UtmPersistenceHandler {

    /**
     * Handles the parsed UTM parameters.
     *
     * @param parameters the parsed UTM parameters for the current request
     */
    fun handle(parameters: UtmParameters)

}