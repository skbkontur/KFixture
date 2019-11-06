package ru.kontur.spring.test.generator.generators

import com.mifmif.common.regex.Generex
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ValidatorFor
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ValidatorFor(value = Pattern::class)
class PatternGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val patternAnnotation = annotation as Pattern
        val generex = Generex(patternAnnotation.regexp)
        return generex.getMatchedString(0)
    }
}