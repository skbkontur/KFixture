[![Build Status](https://travis-ci.org/skbkontur/KFixture.svg?branch=master)](https://travis-ci.org/skbkontur/KFixture)

### What is about?

Library give a parameter resolver,
which can be used to generate valid data for your class to pass validation of your api.

* Generate data for all javax validation annotations.
* Also generate data ignore javax annotations
* Provide an interface to define custom rules for validation.
* Valid param go through chain of validation.
* Suite of test utils for generation parameters and so on.

#### Use in your project

##### maven
```xml
<dependency>
    <groupId>ru.kontur.kinfra.kfixture</groupId>
    <artifactId>kfixture</artifactId>
    <version>0.5.0</version>
</dependency>
```

##### gradle
```groovy
implementation "ru.kontur.kinfra.kfixture:kfixture:0.5.0"
```

#### Fixture generates random data
```kotlin
data class DataGenerate(
    val param: String
)

fun `should just generate random data`(@Fixture fixture: DataGenerate) {}
```

#### JavaxFixture generates valid random data
```kotlin
data class JavaxData(
    @field:Pattern("\\d{2}")
    val param: String
)

fun `should just generate valid data`(@JavaxFixture fixture: JavaxData) {}
```

#### If kfixture can't generate your class, you can use ParamConstructor
```kotlin
class DurationConstructor: ParamConstructor<Duration> {
    override fun call(): Duration {
        return Duration.ofSeconds(10)
    }
}
```

#### For custom annotation you have to provide your generation router
```kotlin
class EmailRouter<T> : ValidRouter<T, Email> where T : Any {
    private val emailGenerator = EmailGenerator()

    override fun process(param: T, annotation: Email, clazz: KClass<*>, type: KType): Any? {
        return when (param) {
            is String -> emailGenerator.process(param, annotation, clazz, type)
            else -> throw AnnotationCantBeAppliedException(annotation, clazz)
        }
    }
}
```

#### If you need just construct object in validation and do not process annotation generation, you can use ValidationConstructor
```kotlin
class DurationConstructor: ValidationConstructor<Duration> {
    override fun call(): Duration {
        return Duration.ofSeconds(10)
    }
}
```