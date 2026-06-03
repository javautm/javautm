package io.github.javautm.spring.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue

/**
 * Configuration properties for Java UTM.
 */
@ConfigurationProperties(prefix = "javautm")
data class JavautmProperties(

    @DefaultValue("true")
    val enabled: Boolean = true

)
