package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.JavaxFixture
import ru.kontur.test.kfixture.api.FixtureGeneratorMeta
import ru.kontur.test.kfixture.api.ValidationParamResolver
import ru.kontur.test.kfixture.api.ResolverFor
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver
import ru.kontur.test.kfixture.utils.generateString
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.KType

@ResolverFor(value = ClassLevelValidatorTest.SomeValidation::class)
class CustomResolver : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        return ClassLevelValidatorTest.Data(
            param1 = generateString(10),
            param2 = null
        )
    }
}

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(pathes = ["ru.kontur.spring.test.generator"])
class ClassLevelValidatorTest {
    @SomeValidation
    data class Data(
        val param1: String?,
        val param2: String?
    )

    @Constraint(validatedBy = [SomeValidation.Validator::class])
    annotation class SomeValidation(
        val message: String = "Incorrect request",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
    ) {
        class Validator : ConstraintValidator<SomeValidation, Data> {
            override fun isValid(value: Data?, context: ConstraintValidatorContext?): Boolean {
                if (value == null) {
                    return true
                }
                if (value.param1 != null && value.param2 != null || value.param1 == null && value.param2 == null) {
                    return false
                }
                return true
            }
        }
    }

    @Test
    fun test(@JavaxFixture data: Data) {
        val result = data.param1 == null && data.param2 != null || data.param1 != null && data.param2 == null
        assertTrue(result)
    }
}