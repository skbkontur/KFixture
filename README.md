### What is about?

Library give a parameter resolver,
which can be used to generate valid data for your class to pass validation of your api.

Version 1.0

* Generate data for all javax validation annotations.
* Provide an interface to define custom rules for validation.
* Valid param go through chain of validation.

#### How it works:

```kotlin
class NotEmptyRule: SomeAnnotation {
    fun correctParam(param: String): String {
        if (param.isEmpty()) {
            return generateSomeWortds()
        }
        return param
    }
}
```

### Use in your test

```kotlin
data class TestClass (
    @field:NotEmpty
    val param1:String
)

@ExtendWith(SpringTestDataFrameworkParameterResolver::class)
class YourTest {

    @Test
    fun test(@TestData param: TestClass) {
    
    }
}
```






