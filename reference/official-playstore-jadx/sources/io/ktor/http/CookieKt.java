package io.ktor.http;

import androidx.compose.material3.d;
import androidx.core.view.z0;
import com.google.common.util.concurrent.r0;
import ga.t;
import io.ktor.http.ContentDisposition;
import io.ktor.util.Base64Kt;
import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.a0;
import kotlin.collections.i0;
import kotlin.collections.r;
import kotlin.jvm.internal.p;
import kotlin.text.f;
import kotlin.text.i;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;
import t7.a;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a+\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\f\u001a\u008b\u0001\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\u0016\b\u0002\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001d\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b \u0010\u001e\u001a\u0013\u0010!\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0013\u0010$\u001a\u00020\u0005*\u00020#H\u0002¢\u0006\u0004\b$\u0010%\u001a*\u0010'\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010&2\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\b¢\u0006\u0004\b'\u0010(\u001a\"\u0010)\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010&H\u0082\b¢\u0006\u0004\b)\u0010*\u001a \u0010+\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0005H\u0082\b¢\u0006\u0004\b+\u0010,\u001a\"\u0010-\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0082\b¢\u0006\u0004\b-\u0010.\u001a\u0013\u0010/\u001a\u00020\u0012*\u00020\u0000H\u0002¢\u0006\u0004\b/\u00100\"\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u0000018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103\"\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106\"\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020#018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00103¨\u00068"}, d2 = {"", "cookiesHeader", "Lio/ktor/http/Cookie;", "parseServerSetCookieHeader", "(Ljava/lang/String;)Lio/ktor/http/Cookie;", "", "skipEscaped", "", "parseClientCookiesHeader", "(Ljava/lang/String;Z)Ljava/util/Map;", "cookie", "renderSetCookieHeader", "(Lio/ktor/http/Cookie;)Ljava/lang/String;", "renderCookieHeader", ContentDisposition.Parameters.Name, "value", "Lio/ktor/http/CookieEncoding;", "encoding", "", "maxAge", "Lio/ktor/util/date/GMTDate;", "expires", "domain", "path", "secure", "httpOnly", "extensions", "includeEncoding", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;ILio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;Z)Ljava/lang/String;", "encodeCookieValue", "(Ljava/lang/String;Lio/ktor/http/CookieEncoding;)Ljava/lang/String;", "encodedValue", "decodeCookieValue", "assertCookieName", "(Ljava/lang/String;)Ljava/lang/String;", "", "shouldEscapeInCookies", "(C)Z", "", "cookiePart", "(Ljava/lang/String;Ljava/lang/Object;Lio/ktor/http/CookieEncoding;)Ljava/lang/String;", "cookiePartUnencoded", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;", "cookiePartFlag", "(Ljava/lang/String;Z)Ljava/lang/String;", "cookiePartExt", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "toIntClamping", "(Ljava/lang/String;)I", "", "loweredPartNames", "Ljava/util/Set;", "Lkotlin/text/m;", "clientCookieHeaderPattern", "Lkotlin/text/m;", "cookieCharsShouldBeEscaped", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CookieKt {
    private static final Set<String> loweredPartNames = r.p0(new String[]{io.ktor.client.utils.CacheControl.MAX_AGE, "expires", "domain", "path", "secure", "httponly", "$x-enc"});
    private static final m clientCookieHeaderPattern = new m("(^|;)\\s*([^;=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?");
    private static final Set<Character> cookieCharsShouldBeEscaped = r.p0(new Character[]{';', ',', '\"'});

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CookieEncoding.values().length];
            try {
                iArr[CookieEncoding.RAW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CookieEncoding.DQUOTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CookieEncoding.BASE64_ENCODING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CookieEncoding.URI_ENCODING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieKt$parseClientCookiesHeader$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/text/i;", "it", "Lx6/x;", "", "invoke", "(Lkotlin/text/i;)Lx6/x;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<i, x> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final x invoke(i iVar) {
            f fVarA = iVar.c().a(2);
            String str = fVarA != null ? fVarA.f19943a : "";
            f fVarA2 = iVar.c().a(4);
            return new x(str, fVarA2 != null ? fVarA2.f19943a : "");
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieKt$parseClientCookiesHeader$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lx6/x;", "", "it", "", "invoke", "(Lx6/x;)Ljava/lang/Boolean;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements l<x, Boolean> {
        final /* synthetic */ boolean $skipEscaped;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(boolean z) {
            super(1);
            this.$skipEscaped = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x0011  */
        @Override // r7.l
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Boolean invoke(x6.x r3) {
            /*
                r2 = this;
                boolean r0 = r2.$skipEscaped
                if (r0 == 0) goto L11
                java.lang.Object r3 = r3.f22608i
                java.lang.String r3 = (java.lang.String) r3
                java.lang.String r0 = "$"
                r1 = 0
                boolean r3 = kotlin.text.u.P(r3, r0, r1)
                if (r3 != 0) goto L12
            L11:
                r1 = 1
            L12:
                java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CookieKt.AnonymousClass2.invoke(x6.x):java.lang.Boolean");
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.CookieKt$parseClientCookiesHeader$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u00002\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lx6/x;", "", "cookie", "invoke", "(Lx6/x;)Lx6/x;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends kotlin.jvm.internal.r implements l<x, x> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        @Override // r7.l
        public final x invoke(x xVar) {
            String str = (String) xVar.f22609l;
            if (!u.P(str, "\"", false) || !u.K(str, "\"", false)) {
                return xVar;
            }
            return new x(xVar.f22608i, o.t0(str));
        }
    }

    private static final String assertCookieName(String str) {
        for (int i10 = 0; i10 < str.length(); i10++) {
            if (shouldEscapeInCookies(str.charAt(i10))) {
                throw new IllegalArgumentException(d.C("Cookie name is not valid: ", str));
            }
        }
        return str;
    }

    private static final String cookiePart(String str, Object obj, CookieEncoding cookieEncoding) {
        if (obj == null) {
            return "";
        }
        return str + '=' + encodeCookieValue(obj.toString(), cookieEncoding);
    }

    private static final String cookiePartExt(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return str + '=' + encodeCookieValue(str2.toString(), CookieEncoding.RAW);
    }

    private static final String cookiePartFlag(String str, boolean z) {
        return z ? str : "";
    }

    private static final String cookiePartUnencoded(String str, Object obj) {
        if (obj == null) {
            return "";
        }
        return str + '=' + obj;
    }

    public static final String decodeCookieValue(String str, CookieEncoding cookieEncoding) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[cookieEncoding.ordinal()];
        if (i10 == 1 || i10 == 2) {
            return (u.P(o.P0(str).toString(), "\"", false) && u.K(o.N0(str).toString(), "\"", false)) ? o.t0(o.L0(str).toString()) : str;
        }
        if (i10 == 3) {
            return Base64Kt.decodeBase64String(str);
        }
        if (i10 == 4) {
            return CodecsKt.decodeURLQueryComponent$default(str, 0, 0, true, null, 11, null);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final String encodeCookieValue(String str, CookieEncoding cookieEncoding) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[cookieEncoding.ordinal()];
        int i11 = 0;
        if (i10 == 1) {
            while (i11 < str.length()) {
                if (shouldEscapeInCookies(str.charAt(i11))) {
                    throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in RAW format.  Consider URL_ENCODING mode");
                }
                i11++;
            }
            return str;
        }
        if (i10 != 2) {
            if (i10 == 3) {
                return Base64Kt.encodeBase64(str);
            }
            if (i10 == 4) {
                return CodecsKt.encodeURLParameter(str, true);
            }
            throw new NoWhenBranchMatchedException();
        }
        if (o.U(str, '\"')) {
            throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in DQUOTES format. Consider URL_ENCODING mode");
        }
        while (i11 < str.length()) {
            if (shouldEscapeInCookies(str.charAt(i11))) {
                return d.i('\"', "\"", str);
            }
            i11++;
        }
        return str;
    }

    public static final Map<String, String> parseClientCookiesHeader(String str, boolean z) {
        t tVar = new t(ga.r.I(new t(m.c(str, clientCookieHeaderPattern), AnonymousClass1.INSTANCE), new AnonymousClass2(z)), AnonymousClass3.INSTANCE);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = tVar.iterator();
        while (true) {
            z0 z0Var = (z0) it;
            if (!z0Var.hasNext()) {
                break;
            }
            x xVar = (x) z0Var.next();
            linkedHashMap.put(xVar.f22608i, xVar.f22609l);
        }
        int size = linkedHashMap.size();
        if (size == 0) {
            return a0.f19683i;
        }
        if (size != 1) {
            return linkedHashMap;
        }
        Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    public static /* synthetic */ Map parseClientCookiesHeader$default(String str, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return parseClientCookiesHeader(str, z);
    }

    public static final Cookie parseServerSetCookieHeader(String str) {
        CookieEncoding cookieEncodingValueOf;
        Map<String, String> clientCookiesHeader = parseClientCookiesHeader(str, false);
        Iterator<T> it = clientCookiesHeader.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!u.P((String) entry.getKey(), "$", false)) {
                String str2 = clientCookiesHeader.get("$x-enc");
                if (str2 == null || (cookieEncodingValueOf = CookieEncoding.valueOf(str2)) == null) {
                    cookieEncodingValueOf = CookieEncoding.RAW;
                }
                CookieEncoding cookieEncoding = cookieEncodingValueOf;
                LinkedHashMap linkedHashMap = new LinkedHashMap(i0.q0(clientCookiesHeader.size()));
                Iterator<T> it2 = clientCookiesHeader.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it2.next();
                    linkedHashMap.put(TextKt.toLowerCasePreservingASCIIRules((String) entry2.getKey()), entry2.getValue());
                }
                String str3 = (String) entry.getKey();
                String strDecodeCookieValue = decodeCookieValue((String) entry.getValue(), cookieEncoding);
                String str4 = (String) linkedHashMap.get(io.ktor.client.utils.CacheControl.MAX_AGE);
                int intClamping = str4 != null ? toIntClamping(str4) : 0;
                String str5 = (String) linkedHashMap.get("expires");
                GMTDate gMTDateFromCookieToGmtDate = str5 != null ? DateUtilsKt.fromCookieToGmtDate(str5) : null;
                String str6 = (String) linkedHashMap.get("domain");
                String str7 = (String) linkedHashMap.get("path");
                boolean zContainsKey = linkedHashMap.containsKey("secure");
                boolean zContainsKey2 = linkedHashMap.containsKey("httponly");
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Map.Entry<String, String> entry3 : clientCookiesHeader.entrySet()) {
                    String key = entry3.getKey();
                    if (!loweredPartNames.contains(TextKt.toLowerCasePreservingASCIIRules(key)) && !p.a(key, entry.getKey())) {
                        linkedHashMap2.put(entry3.getKey(), entry3.getValue());
                    }
                }
                return new Cookie(str3, strDecodeCookieValue, cookieEncoding, intClamping, gMTDateFromCookieToGmtDate, str6, str7, zContainsKey, zContainsKey2, linkedHashMap2);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final String renderCookieHeader(Cookie cookie) {
        return cookie.getName() + '=' + encodeCookieValue(cookie.getValue(), cookie.getEncoding());
    }

    public static final String renderSetCookieHeader(Cookie cookie) {
        return renderSetCookieHeader$default(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAgeInt(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false, 1024, null);
    }

    public static /* synthetic */ String renderSetCookieHeader$default(String str, String str2, CookieEncoding cookieEncoding, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map map, boolean z10, int i11, Object obj) {
        return renderSetCookieHeader(str, str2, (i11 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding, (i11 & 8) != 0 ? 0 : i10, (i11 & 16) != 0 ? null : gMTDate, (i11 & 32) != 0 ? null : str3, (i11 & 64) == 0 ? str4 : null, (i11 & 128) != 0 ? false : z, (i11 & 256) == 0 ? z5 : false, (i11 & 512) != 0 ? a0.f19683i : map, (i11 & 1024) != 0 ? true : z10);
    }

    private static final boolean shouldEscapeInCookies(char c10) {
        return r0.u(c10) || p.c(c10, 32) < 0 || cookieCharsShouldBeEscaped.contains(Character.valueOf(c10));
    }

    private static final int toIntClamping(String str) {
        return (int) qb.d.o(Long.parseLong(str), 0L, 2147483647L);
    }

    public static final String renderSetCookieHeader(String str, String str2, CookieEncoding cookieEncoding, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map<String, String> map, boolean z10) {
        String str5;
        String str6;
        String str7 = assertCookieName(str) + '=' + encodeCookieValue(str2.toString(), cookieEncoding);
        Integer numValueOf = i10 > 0 ? Integer.valueOf(i10) : null;
        String str8 = "";
        String str9 = numValueOf != null ? "Max-Age=" + numValueOf : "";
        String httpDate = gMTDate != null ? DateUtilsKt.toHttpDate(gMTDate) : null;
        String str10 = httpDate == null ? "" : "Expires=" + ((Object) httpDate);
        CookieEncoding cookieEncoding2 = CookieEncoding.RAW;
        String str11 = str3 == null ? "" : "Domain=" + encodeCookieValue(str3.toString(), cookieEncoding2);
        String str12 = str4 == null ? "" : "Path=" + encodeCookieValue(str4.toString(), cookieEncoding2);
        if (!z) {
            str5 = "";
        } else {
            str5 = "Secure";
        }
        if (!z5) {
            str6 = "";
        } else {
            str6 = "HttpOnly";
        }
        List listE = a.E(str7, str9, str10, str11, str12, str5, str6);
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strAssertCookieName = assertCookieName(entry.getKey());
            String value = entry.getValue();
            if (value != null) {
                strAssertCookieName = strAssertCookieName + '=' + encodeCookieValue(value.toString(), CookieEncoding.RAW);
            }
            arrayList.add(strAssertCookieName);
        }
        ArrayList arrayListI0 = kotlin.collections.x.I0(listE, arrayList);
        if (z10) {
            String strName = cookieEncoding.name();
            str8 = strName == null ? "$x-enc" : "$x-enc=" + encodeCookieValue(strName.toString(), CookieEncoding.RAW);
        }
        ArrayList arrayListH0 = kotlin.collections.x.H0(str8, arrayListI0);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayListH0) {
            if (((String) obj).length() > 0) {
                arrayList2.add(obj);
            }
        }
        return kotlin.collections.x.u0(arrayList2, "; ", null, null, null, 62);
    }
}
