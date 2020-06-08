package ru.kontur.kinfra.kfixture.scanner

import org.junit.jupiter.api.extension.ExtensionContext
import org.reflections.Reflections

class CachedReflections {

    fun getReflections(paths: List<String>, extensionContext: ExtensionContext): Reflections {
        val stored = extensionContext.getStore(ExtensionContext.Namespace.GLOBAL)
            .get(DEFAULT_REFLECTION_KEY, ReflectionsWrapper::class.java)

        if (stored != null) {
            return if (stored.pathes.containsAll(paths)) {
                stored.reflections
            } else {
                val wrapper = ReflectionsWrapper(
                    paths + listOf(
                        LIBRARY_PATH,
                        JAVAX_PATH
                    )
                )
                extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(DEFAULT_REFLECTION_KEY, wrapper)
                wrapper.reflections
            }
        } else {
            return if (extensionContext.parent.isPresent) {
                getReflections(paths, extensionContext.parent.get())
            } else {
                val wrapper = ReflectionsWrapper(
                    paths + listOf(
                        LIBRARY_PATH,
                        JAVAX_PATH
                    )
                )
                extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put(DEFAULT_REFLECTION_KEY, wrapper)
                wrapper.reflections
            }
        }
    }

    private companion object {
        const val DEFAULT_REFLECTION_KEY = "REFLECTIONS_REFERENCES"
        const val LIBRARY_PATH = "ru.kontur.kinfra.kfixture"
        const val JAVAX_PATH = "javax.validation.constraints"
    }
}