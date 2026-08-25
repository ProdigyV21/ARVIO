package io.ktor.http.auth;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.CookieUtilsKt;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.parsing.ParseException;
import io.ktor.util.InternalAPI;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.text.i;
import kotlin.text.m;
import kotlin.text.o;
import qb.d;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a-\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0002¢\u0006\u0004\b\u0003\u0010\f\u001a7\u0010\u000f\u001a\u0004\u0018\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a3\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a3\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0014\u001a\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a#\u0010\u001c\u001a\u00020\b*\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001b\u0010\u001e\u001a\u00020\b*\u00020\u00002\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001e\u0010\u0017\u001a\u0013\u0010 \u001a\u00020\u001f*\u00020\u001aH\u0002¢\u0006\u0004\b \u0010!\u001a\u0013\u0010\"\u001a\u00020\u001f*\u00020\u001aH\u0002¢\u0006\u0004\b\"\u0010!\"\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%\"\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010%\"\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)\"\u0014\u0010*\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010)¨\u0006+"}, d2 = {"", "headerValue", "Lio/ktor/http/auth/HttpAuthHeader;", "parseAuthorizationHeader", "(Ljava/lang/String;)Lio/ktor/http/auth/HttpAuthHeader;", "", "parseAuthorizationHeaders", "(Ljava/lang/String;)Ljava/util/List;", "", "startIndex", "", "headers", "(Ljava/lang/String;ILjava/util/List;)I", "header", "index", "nextChallengeIndex", "(Ljava/util/List;Lio/ktor/http/auth/HttpAuthHeader;ILjava/lang/String;)Ljava/lang/Integer;", "", "parameters", "matchParameters", "(Ljava/lang/String;ILjava/util/Map;)I", "matchParameter", "matchToken68", "(Ljava/lang/String;I)I", "unescaped", "(Ljava/lang/String;)Ljava/lang/String;", "", TtmlNode.RUBY_DELIMITER, "skipDelimiter", "(Ljava/lang/String;IC)I", "skipSpaces", "", "isToken68", "(C)Z", "isToken", "", "TOKEN_EXTRA", "Ljava/util/Set;", "TOKEN68_EXTRA", "Lkotlin/text/m;", "token68Pattern", "Lkotlin/text/m;", "escapeRegex", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpAuthHeaderKt {
    private static final Set<Character> TOKEN_EXTRA = r.p0(new Character[]{'!', '#', '$', '%', '&', '\'', Character.valueOf(GMTDateParser.ANY), '+', '-', '.', '^', '_', '`', '|', '~'});
    private static final Set<Character> TOKEN68_EXTRA = r.p0(new Character[]{'-', '.', '_', '~', '+', '/'});
    private static final m token68Pattern = new m("[a-zA-Z0-9\\-._~+/]+=*");
    private static final m escapeRegex = new m("\\\\.");

    /* JADX INFO: renamed from: io.ktor.http.auth.HttpAuthHeaderKt$unescaped$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlin/text/i;", "it", "", "invoke", "(Lkotlin/text/i;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<i, CharSequence> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final CharSequence invoke(i iVar) {
            return o.J0(1, iVar.getValue());
        }
    }

    private static final boolean isToken(char c10) {
        if ('a' > c10 || c10 >= '{') {
            return ('A' <= c10 && c10 < '[') || CookieUtilsKt.isDigit(c10) || TOKEN_EXTRA.contains(Character.valueOf(c10));
        }
        return true;
    }

    private static final boolean isToken68(char c10) {
        if ('a' > c10 || c10 >= '{') {
            return ('A' <= c10 && c10 < '[') || CookieUtilsKt.isDigit(c10) || TOKEN68_EXTRA.contains(Character.valueOf(c10));
        }
        return true;
    }

    private static final int matchParameter(String str, int i10, Map<String, String> map) {
        int i11;
        int iSkipSpaces = skipSpaces(str, i10);
        int i12 = iSkipSpaces;
        while (i12 < str.length() && isToken(str.charAt(i12))) {
            i12++;
        }
        String strB0 = o.B0(str, d.X(iSkipSpaces, i12));
        int iSkipSpaces2 = skipSpaces(str, i12);
        if (iSkipSpaces2 == str.length() || str.charAt(iSkipSpaces2) != '=') {
            return i10;
        }
        int iSkipSpaces3 = skipSpaces(str, iSkipSpaces2 + 1);
        boolean z = false;
        if (str.charAt(iSkipSpaces3) == '\"') {
            iSkipSpaces3++;
            i11 = iSkipSpaces3;
            boolean z5 = false;
            while (i11 < str.length() && (str.charAt(i11) != '\"' || z5)) {
                z5 = !z5 && str.charAt(i11) == '\\';
                i11++;
            }
            if (i11 == str.length()) {
                throw new ParseException("Expected closing quote'\"' in parameter", null, 2, null);
            }
            z = true;
        } else {
            i11 = iSkipSpaces3;
            while (i11 < str.length() && str.charAt(i11) != ' ' && str.charAt(i11) != ',') {
                i11++;
            }
        }
        String strB02 = o.B0(str, d.X(iSkipSpaces3, i11));
        if (z) {
            strB02 = unescaped(strB02);
        }
        map.put(strB0, strB02);
        return z ? i11 + 1 : i11;
    }

    private static final int matchParameters(String str, int i10, Map<String, String> map) {
        while (i10 > 0 && i10 < str.length()) {
            int iMatchParameter = matchParameter(str, i10, map);
            if (iMatchParameter == i10) {
                break;
            }
            i10 = skipDelimiter(str, iMatchParameter, ',');
        }
        return i10;
    }

    private static final int matchToken68(String str, int i10) {
        int iSkipSpaces = skipSpaces(str, i10);
        while (iSkipSpaces < str.length() && isToken68(str.charAt(iSkipSpaces))) {
            iSkipSpaces++;
        }
        while (iSkipSpaces < str.length() && str.charAt(iSkipSpaces) == '=') {
            iSkipSpaces++;
        }
        return skipSpaces(str, iSkipSpaces);
    }

    private static final Integer nextChallengeIndex(List<HttpAuthHeader> list, HttpAuthHeader httpAuthHeader, int i10, String str) {
        if (i10 != str.length() && str.charAt(i10) != ',') {
            return null;
        }
        list.add(httpAuthHeader);
        if (i10 == str.length()) {
            return -1;
        }
        if (str.charAt(i10) == ',') {
            return Integer.valueOf(i10 + 1);
        }
        throw new IllegalStateException("");
    }

    public static final HttpAuthHeader parseAuthorizationHeader(String str) {
        int iSkipSpaces = skipSpaces(str, 0);
        int i10 = iSkipSpaces;
        while (i10 < str.length() && isToken(str.charAt(i10))) {
            i10++;
        }
        String strB0 = o.B0(str, d.X(iSkipSpaces, i10));
        int iSkipSpaces2 = skipSpaces(str, i10);
        if (o.h0(strB0)) {
            return null;
        }
        if (str.length() == iSkipSpaces2) {
            return new HttpAuthHeader.Parameterized(strB0, z.f19728i, (HeaderValueEncoding) null, 4, (h) null);
        }
        int iMatchToken68 = matchToken68(str, iSkipSpaces2);
        String string = o.L0(o.B0(str, d.X(iSkipSpaces2, iMatchToken68))).toString();
        if (string.length() > 0 && iMatchToken68 == str.length()) {
            return new HttpAuthHeader.Single(strB0, string);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (matchParameters(str, iSkipSpaces2, linkedHashMap) != -1) {
            throw new ParseException("Function parseAuthorizationHeader can parse only one header", null, 2, null);
        }
        return new HttpAuthHeader.Parameterized(strB0, linkedHashMap, (HeaderValueEncoding) null, 4, (h) null);
    }

    @InternalAPI
    public static final List<HttpAuthHeader> parseAuthorizationHeaders(String str) {
        ArrayList arrayList = new ArrayList();
        int authorizationHeader = 0;
        while (authorizationHeader != -1) {
            authorizationHeader = parseAuthorizationHeader(str, authorizationHeader, arrayList);
        }
        return arrayList;
    }

    private static final int skipDelimiter(String str, int i10, char c10) {
        int iSkipSpaces = skipSpaces(str, i10);
        if (iSkipSpaces == str.length()) {
            return -1;
        }
        if (str.charAt(iSkipSpaces) == c10) {
            return skipSpaces(str, iSkipSpaces + 1);
        }
        throw new ParseException("Expected delimiter " + c10 + " at position " + iSkipSpaces, null, 2, null);
    }

    private static final int skipSpaces(String str, int i10) {
        while (i10 < str.length() && str.charAt(i10) == ' ') {
            i10++;
        }
        return i10;
    }

    private static final String unescaped(String str) {
        return escapeRegex.g(str, AnonymousClass1.INSTANCE);
    }

    private static final int parseAuthorizationHeader(String str, int i10, List<HttpAuthHeader> list) {
        Integer numNextChallengeIndex;
        int iSkipSpaces = skipSpaces(str, i10);
        int i11 = iSkipSpaces;
        while (i11 < str.length() && isToken(str.charAt(i11))) {
            i11++;
        }
        String strB0 = o.B0(str, d.X(iSkipSpaces, i11));
        if (!o.h0(strB0)) {
            int iSkipSpaces2 = skipSpaces(str, i11);
            Integer numNextChallengeIndex2 = nextChallengeIndex(list, new HttpAuthHeader.Parameterized(strB0, z.f19728i, (HeaderValueEncoding) null, 4, (h) null), iSkipSpaces2, str);
            if (numNextChallengeIndex2 != null) {
                return numNextChallengeIndex2.intValue();
            }
            int iMatchToken68 = matchToken68(str, iSkipSpaces2);
            String string = o.L0(o.B0(str, d.X(iSkipSpaces2, iMatchToken68))).toString();
            if (string.length() > 0 && (numNextChallengeIndex = nextChallengeIndex(list, new HttpAuthHeader.Single(strB0, string), iMatchToken68, str)) != null) {
                return numNextChallengeIndex.intValue();
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int iMatchParameters = matchParameters(str, iSkipSpaces2, linkedHashMap);
            list.add(new HttpAuthHeader.Parameterized(strB0, linkedHashMap, (HeaderValueEncoding) null, 4, (h) null));
            return iMatchParameters;
        }
        throw new ParseException("Invalid authScheme value: it should be token, can't be blank", null, 2, null);
    }
}
