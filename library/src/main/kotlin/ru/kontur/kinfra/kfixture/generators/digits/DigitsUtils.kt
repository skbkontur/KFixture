package ru.kontur.kinfra.kfixture.generators.digits

internal object DigitsUtils {

    fun createStr(integer: Int, fraction: Int): String {
        val sb = StringBuilder()

        for (i in 0 until integer) {
            sb.append("1")
        }

        if (fraction != 0)
            sb.append(".")

        for (i in 0 until fraction) {
            sb.append("1")
        }

        return sb.toString()
    }
}