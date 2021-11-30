package ru.kontur.kinfra.kfixture

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.kinfra.kfixture.api.JavaxFixture
import ru.kontur.kinfra.kfixture.api.FixtureGeneratorMeta
import ru.kontur.kinfra.kfixture.api.ScannerSettings
import ru.kontur.kinfra.kfixture.resolver.FixtureParameterResolver
import ru.kontur.kinfra.kfixture.routers.ValidRouter
import ru.kontur.kinfra.kfixture.misc.generateString
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CustomResolver : ValidRouter<ClassLevelValidatorTest.Data, ClassLevelValidatorTest.SomeValidation> {
    override fun process(
        param: ClassLevelValidatorTest.Data,
        annotation: ClassLevelValidatorTest.SomeValidation,
        clazz: KClass<*>,
        type: KType
    ): Any? {
        return ClassLevelValidatorTest.Data(
            param1 = generateString(10),
            param2 = null
        )
    }
}

@ExtendWith(FixtureParameterResolver::class)
@FixtureGeneratorMeta(ScannerSettings( paths = ["ru.kontur.kinfra.kfixture"]))
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