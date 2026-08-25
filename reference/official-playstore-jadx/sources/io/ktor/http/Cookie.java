package io.ktor.http;

import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u000eHÆ\u0003J\t\u0010,\u001a\u00020\u000eHÆ\u0003J\u0081\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011HÆ\u0001J\u0013\u0010.\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\bHÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00062"}, d2 = {"Lio/ktor/http/Cookie;", "", ContentDisposition.Parameters.Name, "", "value", "encoding", "Lio/ktor/http/CookieEncoding;", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", "path", "secure", "", "httpOnly", "extensions", "", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/CookieEncoding;ILio/ktor/util/date/GMTDate;Ljava/lang/String;Ljava/lang/String;ZZLjava/util/Map;)V", "getDomain", "()Ljava/lang/String;", "getEncoding", "()Lio/ktor/http/CookieEncoding;", "getExpires", "()Lio/ktor/util/date/GMTDate;", "getExtensions", "()Ljava/util/Map;", "getHttpOnly", "()Z", "getMaxAgeInt", "()I", "getName", "getPath", "getSecure", "getValue", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class Cookie {
    private final String domain;
    private final CookieEncoding encoding;
    private final GMTDate expires;
    private final Map<String, String> extensions;
    private final boolean httpOnly;
    private final int maxAge;
    private final String name;
    private final String path;
    private final boolean secure;
    private final String value;

    public Cookie(String str, String str2, CookieEncoding cookieEncoding, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map<String, String> map) {
        this.name = str;
        this.value = str2;
        this.encoding = cookieEncoding;
        this.maxAge = i10;
        this.expires = gMTDate;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z5;
        this.extensions = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Cookie copy$default(Cookie cookie, String str, String str2, CookieEncoding cookieEncoding, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map map, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = cookie.name;
        }
        if ((i11 & 2) != 0) {
            str2 = cookie.value;
        }
        if ((i11 & 4) != 0) {
            cookieEncoding = cookie.encoding;
        }
        if ((i11 & 8) != 0) {
            i10 = cookie.maxAge;
        }
        if ((i11 & 16) != 0) {
            gMTDate = cookie.expires;
        }
        if ((i11 & 32) != 0) {
            str3 = cookie.domain;
        }
        if ((i11 & 64) != 0) {
            str4 = cookie.path;
        }
        if ((i11 & 128) != 0) {
            z = cookie.secure;
        }
        if ((i11 & 256) != 0) {
            z5 = cookie.httpOnly;
        }
        if ((i11 & 512) != 0) {
            map = cookie.extensions;
        }
        boolean z10 = z5;
        Map map2 = map;
        String str5 = str4;
        boolean z11 = z;
        GMTDate gMTDate2 = gMTDate;
        String str6 = str3;
        return cookie.copy(str, str2, cookieEncoding, i10, gMTDate2, str6, str5, z11, z10, map2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Map<String, String> component10() {
        return this.extensions;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CookieEncoding getEncoding() {
        return this.encoding;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getMaxAge() {
        return this.maxAge;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final GMTDate getExpires() {
        return this.expires;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getDomain() {
        return this.domain;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getSecure() {
        return this.secure;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getHttpOnly() {
        return this.httpOnly;
    }

    public final Cookie copy(String name, String value, CookieEncoding encoding, int maxAge, GMTDate expires, String domain, String path, boolean secure, boolean httpOnly, Map<String, String> extensions) {
        return new Cookie(name, value, encoding, maxAge, expires, domain, path, secure, httpOnly, extensions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) other;
        return p.a(this.name, cookie.name) && p.a(this.value, cookie.value) && this.encoding == cookie.encoding && this.maxAge == cookie.maxAge && p.a(this.expires, cookie.expires) && p.a(this.domain, cookie.domain) && p.a(this.path, cookie.path) && this.secure == cookie.secure && this.httpOnly == cookie.httpOnly && p.a(this.extensions, cookie.extensions);
    }

    public final String getDomain() {
        return this.domain;
    }

    public final CookieEncoding getEncoding() {
        return this.encoding;
    }

    public final GMTDate getExpires() {
        return this.expires;
    }

    public final Map<String, String> getExtensions() {
        return this.extensions;
    }

    public final boolean getHttpOnly() {
        return this.httpOnly;
    }

    public final int getMaxAgeInt() {
        return this.maxAge;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public final boolean getSecure() {
        return this.secure;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [int] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public int hashCode() {
        int iHashCode = (((this.encoding.hashCode() + c.c(this.name.hashCode() * 31, 31, this.value)) * 31) + this.maxAge) * 31;
        GMTDate gMTDate = this.expires;
        int iHashCode2 = (iHashCode + (gMTDate == null ? 0 : gMTDate.hashCode())) * 31;
        String str = this.domain;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.path;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.secure;
        ?? r02 = z;
        if (z) {
            r02 = 1;
        }
        int i10 = (iHashCode4 + r02) * 31;
        boolean z5 = this.httpOnly;
        return this.extensions.hashCode() + ((i10 + (z5 ? 1 : z5)) * 31);
    }

    public String toString() {
        return "Cookie(name=" + this.name + ", value=" + this.value + ", encoding=" + this.encoding + ", maxAge=" + this.maxAge + ", expires=" + this.expires + ", domain=" + this.domain + ", path=" + this.path + ", secure=" + this.secure + ", httpOnly=" + this.httpOnly + ", extensions=" + this.extensions + ')';
    }

    public /* synthetic */ Cookie(String str, String str2, CookieEncoding cookieEncoding, int i10, GMTDate gMTDate, String str3, String str4, boolean z, boolean z5, Map map, int i11, h hVar) {
        this(str, str2, (i11 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding, (i11 & 8) != 0 ? 0 : i10, (i11 & 16) != 0 ? null : gMTDate, (i11 & 32) != 0 ? null : str3, (i11 & 64) != 0 ? null : str4, (i11 & 128) != 0 ? false : z, (i11 & 256) != 0 ? false : z5, (i11 & 512) != 0 ? a0.f19683i : map);
    }
}
