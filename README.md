# Java UTM

Java UTM is a lightweight Java/Kotlin/Spring library for parsing, intercepting and persisting UTM (Urchin Tracking
Module) parameters.

It helps ensure consistent campaign tagging across applications and integrations while providing a clean, type-safe API.

## Features

* Parse existing URLs and extract UTM parameters from:
    * Raw String
    * `HttpServletRequest`
* Immutable and type-safe API
* Framework-agnostic core module
* Optional Spring Boot integration
* Java 17+

## Installation

### Maven

```xml

<dependency>
    <groupId>io.github.javautm</groupId>
    <artifactId>javautm-core</artifactId>
    <version>1.0.0</version>
</dependency>

        <!-- Or optional Spring Boot starter -->

<dependency>
<groupId>io.github.javautm</groupId>
<artifactId>spring-boot-starter-javautm</artifactId>
<version>1.0.0</version>
</dependency>
```

### Gradle

```kotlin
implementation("io.github.javautm:javautm-core:1.0.0")

// Or optional Spring Boot starter

implementation("io.github.javautm:spring-boot-starter-javautm:1.0.0")
```

## Quick Start with Spring Boot

### 1. Add spring boot starter

```kotlin
dependencies {
    implementation("io.github.javautm:spring-boot-starter-javautm:1.0.0")
}
```

### 2. Implement persistence handler

```java
import io.github.javautm.core.persistence.UtmPersistenceHandler;
import io.github.javautm.core.model.UtmParameters;

@Component // Register as a component so the interceptor can use it
public class UtmPersistenceHandlerImpl implements UtmPersistenceHandler {

    @Override
    public handle(UtmParameters parameters) {
        // Persistence logic...
    }

}
```

## Supported Parameters

| Parameter    | Description          |
|--------------|----------------------|
| utm_source   | Traffic source       |
| utm_medium   | Marketing medium     |
| utm_campaign | Campaign name        |
| utm_term     | Paid keyword         |
| utm_content  | Ad/content variation |

---

## Modules

### javautm-core

Core library containing:

* UTM parameters model
* Parser
* Interceptor
* Holder
* Persistence handler

### spring-boot-starter-javautm

* Enabling/disabling by property `javautm.enabled`
* `UtmParser` bean
* Interceptor automatically registered

## Compatibility

| Version | Java |
|---------|------|
| 1.x     | 17+  |

## Documentation

[Full documentation](https://javautm.github.io/javautm)

## Contributing

Contributions, bug reports, and feature requests are welcome.

1. Fork the repository
2. Create a feature branch
3. Submit a pull request
