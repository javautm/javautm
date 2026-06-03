package io.github.javautm.spring

import io.github.javautm.core.parser.UtmParser
import io.github.javautm.core.web.UtmInterceptor
import io.github.javautm.spring.autoconfigure.JavautmAutoConfiguration
import io.github.javautm.spring.properties.JavautmProperties
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.boot.webmvc.autoconfigure.WebMvcAutoConfiguration
import kotlin.test.assertTrue

class JavautmAutoConfigurationTests {

    private val contextRunner: WebApplicationContextRunner = WebApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(
                WebMvcAutoConfiguration::class.java,
                JavautmAutoConfiguration::class.java
            )
        )

    @Test
    fun `should register beans by default`() {
        contextRunner.run {
            assertThat(it).hasSingleBean(JavautmProperties::class.java)
            assertThat(it).hasSingleBean(UtmInterceptor::class.java)
        }
    }

    @Test
    fun `properties should be bound with defaults`() {
        contextRunner.run {
            val props = it.getBean(JavautmProperties::class.java)
            assertNotNull(props)
            assertTrue(props.enabled)
        }
    }

    @Test
    fun `autoconfiguration should be disabled when property is false`() {
        contextRunner
            .withPropertyValues("javautm.enabled=false")
            .run {
                assertThat(it).doesNotHaveBean(UtmParser::class.java)
                assertThat(it).doesNotHaveBean(UtmInterceptor::class.java)
            }
    }

}