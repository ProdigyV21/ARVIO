package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Cookie;
import io.ktor.http.CookieEncoding;
import io.ktor.http.CookieKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0086\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0081\u0001\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001cH\u0007¢\u0006\u0004\b\u000f\u0010\u001eJ\u0081\u0001\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u001f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001c¢\u0006\u0004\b\u000f\u0010 J/\u0010!\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$¨\u0006%"}, d2 = {"Lio/ktor/server/response/ResponseCookies;", "", "Lio/ktor/server/response/ApplicationResponse;", "response", "", "secureTransport", "<init>", "(Lio/ktor/server/response/ApplicationResponse;Z)V", "", ContentDisposition.Parameters.Name, "Lio/ktor/http/Cookie;", "get", "(Ljava/lang/String;)Lio/ktor/http/Cookie;", "item", "Lx6/t0;", "append", "(Lio/ktor/http/Cookie;)V", "value", "Lio/ktor/http/CookieEncoding;", "encoding", "", "maxAge", "Lio/ktor/util/date/GMTDate;", "expires", "domain", "path", "secure", "httpOnly", "", "extensions", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;ILio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;)V", "", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;JLio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;)V", "appendExpired", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lio/ktor/server/response/ApplicationResponse;", "Z", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResponseCookies {
    private final ApplicationResponse response;
    private final boolean secureTransport;

    public ResponseCookies(ApplicationResponse applicationResponse, boolean z) {
        this.response = applicationResponse;
        this.secureTransport = z;
    }

    public static /* synthetic */ void appendExpired$default(ResponseCookies responseCookies, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        responseCookies.appendExpired(str, str2, str3);
    }

    public final void append(Cookie item) {
        if (item.getSecure() && !this.secureTransport) {
            throw new IllegalArgumentException("You should set secure cookie only via secure transport (HTTPS)");
        }
        ResponseHeaders.append$default(this.response.getHeaders(), "Set-Cookie", CookieKt.renderSetCookieHeader(item), false, 4, null);
    }

    @e
    public final void appendExpired(String name, String domain, String path) {
        append$default(this, name, "", (CookieEncoding) null, 0L, GMTDate.INSTANCE.getSTART(), domain, path, false, false, (Map) null, 908, (Object) null);
    }

    public final Cookie get(String name) {
        Object next;
        List<String> listValues = this.response.getHeaders().values("Set-Cookie");
        ArrayList arrayList = new ArrayList(s.U(listValues, 10));
        Iterator<T> it = listValues.iterator();
        while (it.hasNext()) {
            arrayList.add(CookieKt.parseServerSetCookieHeader((String) it.next()));
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            if (p.a(((Cookie) next).getName(), name)) {
                break;
            }
        }
        return (Cookie) next;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void append$default(ResponseCookies responseCookies, String str, String str2, CookieEncoding cookieEncoding, long j10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map map, int i10, Object obj) {
        responseCookies.append(str, str2, (i10 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding, (i10 & 8) != 0 ? 0L : j10, (i10 & 16) != 0 ? null : gMTDate, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4, (i10 & 128) != 0 ? false : z, (i10 & 256) != 0 ? false : z5, (Map<String, String>) ((i10 & 512) != 0 ? a0.f19683i : map));
    }

    @e
    public final void append(String name, String value, CookieEncoding encoding, int maxAge, GMTDate expires, String domain, String path, boolean secure, boolean httpOnly, Map<String, String> extensions) {
        append(name, value, encoding, maxAge, expires, domain, path, secure, httpOnly, extensions);
    }

    public final void append(String name, String value, CookieEncoding encoding, long maxAge, GMTDate expires, String domain, String path, boolean secure, boolean httpOnly, Map<String, String> extensions) {
        long j10 = maxAge;
        if (j10 > 2147483647L) {
            j10 = 2147483647L;
        }
        append(new Cookie(name, value, encoding, (int) j10, expires, domain, path, secure, httpOnly, extensions));
    }
}
