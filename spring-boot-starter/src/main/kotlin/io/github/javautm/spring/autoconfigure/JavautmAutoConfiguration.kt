package io.github.javautm.spring.autoconfigure

import io.github.javautm.core.parser.UtmParser
import io.github.javautm.core.persistence.UtmPersistenceHandler
import io.github.javautm.core.web.UtmInterceptor
import io.github.javautm.spring.properties.JavautmProperties
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Spring Boot autoconfiguration for Java sUTM.
 *
 * All beans are [ConditionalOnMissingBean] so it's possible to override any
 * component by declaring your own bean of the same type.
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "javautm", name = ["enabled"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(JavautmProperties::class)
class JavautmAutoConfiguration(val properties: JavautmProperties) {

    @Bean
    @ConditionalOnMissingBean
    fun utmParser() = UtmParser()

    @Bean
    @ConditionalOnMissingBean
    fun utmInterceptor(utmParser: UtmParser, handlers: List<UtmPersistenceHandler>) =
        UtmInterceptor(utmParser, handlers)

    @Bean
    @ConditionalOnMissingBean(name = ["javautmWebMvcConfigurer"])
    fun javautmWebMvcConfigurer(utmInterceptor: UtmInterceptor): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addInterceptors(registry: InterceptorRegistry) {
                registry.addInterceptor(utmInterceptor)
            }
        }
    }

}