package ru.kontur.test.kfixture

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.kontur.test.kfixture.annotations.Fixture
import ru.kontur.test.kfixture.resolver.FixtureParameterResolver
import java.net.URI

@ExtendWith(FixtureParameterResolver::class)
class URITest {

    @Test
    fun `should generate uri without null link`(@Fixture link: URI) {
        assertNotNull(link.path)
    }
}