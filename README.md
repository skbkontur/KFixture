[![Build Status](https://travis-ci.org/skbkontur/KFixture.svg?branch=master)](https://travis-ci.org/skbkontur/KFixture)

### What is about?

Library give a parameter resolver,
which can be used to generate valid data for your class to pass validation of your api.

Version 1.0

* Generate data for all javax validation annotations.
* Also generate data ignore javax annotations
* Provide an interface to define custom rules for validation.
* Valid param go through chain of validation.
* Suite of test utils for generation parameters and so on.

#### How it works:

You just inheritance from annotation and define your own behavior
```kotlin
class NotEmptyGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        TODO("your implementation")
    }
}
```

#### Fixture annotations

* Fixture - generate random instance of your class, ignore all javax annotations
* JavaxFixture - generate valid random instance of your class, use all annotations

### Use in your test

```kotlin
data class TestClass (
    @field:NotEmpty
    val param1:String
)

@ExtendWith(GenerateParameterResolver::class)
class YourTest {

    @Test
    fun test(@JavaxFixture param: TestClass) {
    
    }
}
```






