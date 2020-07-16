package ru.kontur.kinfra.kfixture.scanner

import org.junit.jupiter.api.extension.ExtensionContext
import ru.kontur.kinfra.kfixture.processor.scanner.GeneratorAnnotationScanner

class CachedScanner {

    fun getScanner(paths: List<String>, extensionContext: ExtensionContext): GeneratorAnnotationScanner {
        val stored = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
            .get(DEFAULT_REFLECTION_KEY, ReflectionsWrapper::class.java)

        if (stored != null) {
            return if (stored.pathes.containsAll(paths)) {
                stored.cachedScanner
            } else {
                val wrapper = ReflectionsWrapper(
                    paths + listOf(
                        LIBRARY_PATH,
                        JAVAX_PATH
                    )
                )
                extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(DEFAULT_REFLECTION_KEY, wrapper)
                wrapper.cachedScanner
            }
        } else {
            return if (extensionContext.parent.isPresent) {
                getScanner(paths, extensionContext.parent.get())
            } else {
                val wrapper = ReflectionsWrapper(
                    paths + listOf(
                        LIBRARY_PATH,
                        JAVAX_PATH
                    )
                )
                extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(DEFAULT_REFLECTION_KEY, wrapper)
                wrapper.cachedScanner
            }
        }
    }

    private companion object {
        const val DEFAULT_REFLECTION_KEY = "REFLECTIONS_REFERENCES"
        const val LIBRARY_PATH = "ru.kontur.kinfra.kfixture"
        const val JAVAX_PATH = "javax.validation.constraints"
    }
}