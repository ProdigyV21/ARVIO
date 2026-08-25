package io.ktor.http;

import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.ParserDslKt;
import io.ktor.http.parsing.PrimitivesKt;
import io.ktor.http.parsing.regex.RegexParserGeneratorKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"IP_PARSER", "Lio/ktor/http/parsing/Parser;", "IPv4address", "Lio/ktor/http/parsing/Grammar;", "IPv6address", "hostIsIp", "", "host", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IpParserKt {
    private static final Parser IP_PARSER;
    private static final Grammar IPv4address;
    private static final Grammar IPv6address;

    static {
        Grammar grammarThen = ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(PrimitivesKt.getDigits(), "."), PrimitivesKt.getDigits()), "."), PrimitivesKt.getDigits()), "."), PrimitivesKt.getDigits());
        IPv4address = grammarThen;
        Grammar grammarThen2 = ParserDslKt.then(ParserDslKt.then("[", ParserDslKt.atLeastOne(ParserDslKt.or(PrimitivesKt.getHex(), ":"))), "]");
        IPv6address = grammarThen2;
        IP_PARSER = RegexParserGeneratorKt.buildRegexParser(ParserDslKt.or(grammarThen, grammarThen2));
    }

    public static final boolean hostIsIp(String str) {
        return IP_PARSER.match(str);
    }
}
