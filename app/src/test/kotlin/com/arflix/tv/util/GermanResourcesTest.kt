package com.arflix.tv.util

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.w3c.dom.Element

class GermanResourcesTest {
    private val resources = File("src/main/res").takeIf { it.isDirectory } ?: File("app/src/main/res")
    private val placeholders = Regex("""%(\d+\$)?[-#+ 0,(]*\d*(?:\.\d+)?([tT][A-Za-z]|[A-Za-z%])""")

    @Test fun germanTranslationsPreserveFormatArguments() {
        val base = strings("values")
        strings("values-de").forEach { (name, translated) ->
            assertTrue("Unknown German resource: $name", name in base)
            val expected = arguments(base.getValue(name))
            val actual = arguments(translated)
            assertEquals("Format arguments: $name", expected, actual)
        }
    }

    // Argument index and conversion must match; width, padding and precision are free, so a
    // translation may format "%2\$d" as "%2\$02d" (e.g. "1:05 h").
    private fun arguments(text: String): List<String> =
        placeholders.findAll(text).map { it.groupValues[1] + it.groupValues[2] }.sorted().toList()

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
