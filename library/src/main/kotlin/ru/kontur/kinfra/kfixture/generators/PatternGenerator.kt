package ru.kontur.kinfra.kfixture.generators

import com.mifmif.common.regex.Generex
import ru.kontur.kinfra.kfixture.api.ValidParamGenerator
import javax.validation.constraints.Pattern

/**
 * @author Konstantin Volivach
 */
class PatternGenerator : ValidParamGenerator<String, Pattern> {
    override fun process(param: String, annotation: Pattern): String {
        val pattern = annotation.regexp
        val generex = Generex(pattern)
        return generex.getMatchedString(0)
    }
}