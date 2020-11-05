package ru.kontur.kinfra.kfixture.exceptions

import java.lang.Exception

class NotFilledEnumClass(
    className: String
): Exception("Class $className not filled. At least once should have 1 constant in your enum")