package com.arflix.tv.util

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.w3c.dom.Element

class PortugueseResourcesTest {
    private val resources = File("src/main/res").takeIf { it.isDirectory } ?: File("app/src/main/res")
    private val placeholders = Regex("""%(?:\d+\$)?[-#+ 0,(]*\d*(?:\.\d+)?(?:[tT][A-Za-z]|[A-Za-z%])""")

    @Test fun portugueseTranslationsPreserveFormatArguments() {
        val base = strings("values")
        for (locale in listOf("values-pt", "values-pt-rBR")) {
            strings(locale).forEach { (name, translated) ->
                assertTrue("Unknown resource: $locale/$name", name in base)
                val expected = placeholders.findAll(base.getValue(name)).map { it.value }.sorted().toList()
                val actual = placeholders.findAll(translated).map { it.value }.sorted().toList()
                assertEquals("Format arguments: $locale/$name", expected, actual)
            }
        }
    }

    private fun strings(locale: String): Map<String, String> {
        val factory = DocumentBuilderFactory.newInstance().apply {
            setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
        }
        val nodes = factory.newDocumentBuilder().parse(File(resources, "$locale/strings.xml"))
            .getElementsByTagName("string")
        val entries = (0 until nodes.length).map {
            val node = nodes.item(it) as Element
            node.getAttribute("name") to node.textContent
        }
        assertEquals("Duplicate resource in $locale", entries.size, entries.map { it.first }.distinct().size)
        return entries.toMap()
    }
}
