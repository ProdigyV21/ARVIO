package com.arflix.tv.util

/**
 * Same result as `replace(regex, replacement)` for a literal [replacement], without
 * `java.util.regex.Matcher.appendReplacement`.
 *
 * On Android 14 every appendReplacement call runs an app-compat change check
 * (`AppCompatCallbacks.isChangeEnabled` -> `ChangeReporter.reportChange`). Profiled
 * on a TV, that check took a third of the time of guide-key normalisation while
 * the XMLTV guide was parsed during scrolling. The match loop is the one
 * `Matcher.replaceAll` uses, so empty matches behave the same.
 *
 * [replacement] is inserted as is: `$` and `\` have no special meaning.
 */
fun CharSequence.replaceLiteral(regex: Regex, replacement: String): String {
    val matcher = regex.toPattern().matcher(this)
    if (!matcher.find()) return toString()
    val out = StringBuilder(length + 16)
    var last = 0
    do {
        out.append(this, last, matcher.start()).append(replacement)
        last = matcher.end()
    } while (matcher.find())
    out.append(this, last, length)
    return out.toString()
}
