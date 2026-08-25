package io.ktor.client.plugins.cookies;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import d7.d;
import e7.a;
import io.ktor.http.Cookie;
import io.ktor.http.IpParserKt;
import io.ktor.http.URLProtocolKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.TextKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a'\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\u000b\u001a\u00020\n*\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\r\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/client/plugins/cookies/CookiesStorage;", "", "urlString", "Lio/ktor/http/Cookie;", "cookie", "Lx6/t0;", "addCookie", "(Lio/ktor/client/plugins/cookies/CookiesStorage;Ljava/lang/String;Lio/ktor/http/Cookie;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/Url;", "requestUrl", "", "matches", "(Lio/ktor/http/Cookie;Lio/ktor/http/Url;)Z", "fillDefaults", "(Lio/ktor/http/Cookie;Lio/ktor/http/Url;)Lio/ktor/http/Cookie;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CookiesStorageKt {
    public static final Object addCookie(CookiesStorage cookiesStorage, String str, Cookie cookie, d<? super t0> dVar) {
        Object objAddCookie = cookiesStorage.addCookie(URLUtilsKt.Url(str), cookie, dVar);
        return objAddCookie == a.f15033i ? objAddCookie : t0.f22605a;
    }

    public static final Cookie fillDefaults(Cookie cookie, Url url) {
        String path = cookie.getPath();
        if (path == null || !u.P(path, DomExceptionUtils.SEPARATOR, false)) {
            cookie = Cookie.copy$default(cookie, null, null, null, 0, null, null, url.getEncodedPath(), false, false, null, 959, null);
        }
        Cookie cookie2 = cookie;
        String domain = cookie2.getDomain();
        return (domain == null || o.h0(domain)) ? Cookie.copy$default(cookie2, null, null, null, 0, null, url.getHost(), null, false, false, null, 991, null) : cookie2;
    }

    public static final boolean matches(Cookie cookie, Url url) {
        String lowerCasePreservingASCIIRules;
        String strQ0;
        String domain = cookie.getDomain();
        if (domain == null || (lowerCasePreservingASCIIRules = TextKt.toLowerCasePreservingASCIIRules(domain)) == null || (strQ0 = o.Q0(lowerCasePreservingASCIIRules, '.')) == null) {
            throw new IllegalStateException("Domain field should have the default value");
        }
        cookie.getPath();
        String path = cookie.getPath();
        if (path == null) {
            throw new IllegalStateException("Path field should have the default value");
        }
        if (!o.X(path, '/')) {
            path = cookie.getPath() + '/';
        }
        String lowerCasePreservingASCIIRules2 = TextKt.toLowerCasePreservingASCIIRules(url.getHost());
        String encodedPath = url.getEncodedPath();
        if (!o.X(encodedPath, '/')) {
            encodedPath = encodedPath + '/';
        }
        return (p.a(lowerCasePreservingASCIIRules2, strQ0) || (!IpParserKt.hostIsIp(lowerCasePreservingASCIIRules2) && u.K(lowerCasePreservingASCIIRules2, ".".concat(strQ0), false))) && (p.a(path, DomExceptionUtils.SEPARATOR) || p.a(encodedPath, path) || u.P(encodedPath, path, false)) && (!cookie.getSecure() || URLProtocolKt.isSecure(url.getProtocol()));
    }
}
