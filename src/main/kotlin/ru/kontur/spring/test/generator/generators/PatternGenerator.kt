package ru.kontur.spring.test.generator.generators

import com.mifmif.common.regex.Generex
import ru.kontur.spring.test.generator.api.ValidationParamResolver
import ru.kontur.spring.test.generator.api.ResolverFor
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * @author Konstantin Volivach
 */
@ResolverFor(value = Pattern::class)
class PatternGenerator : ValidationParamResolver {
    override fun <T> process(generatedParam: T?, clazz: KClass<*>, type: KType, annotation: Annotation): Any? {
        val patternAnnotation = annotation as Pattern
        val generex = Generex(patternAnnotation.regexp)
        return generex.getMatchedString(0)
    }
}