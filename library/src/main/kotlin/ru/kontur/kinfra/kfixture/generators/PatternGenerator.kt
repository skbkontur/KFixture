package ru.kontur.kinfra.kfixture.generators

import com.mifmif.common.regex.Generex
import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
class PatternGenerator : ValidParamGenerator<String, Pattern> {
    override fun process(
        param: String,
        annotation: Pattern,
        clazz: KClass<*>,
        type: KType
    ): String? {
        val pattern = annotation.regexp
        val generex = Generex(pattern)
        return generex.getMatchedString(0)
    }
}