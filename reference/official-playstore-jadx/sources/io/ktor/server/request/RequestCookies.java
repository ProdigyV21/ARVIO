package io.ktor.server.request;

import io.ktor.http.ContentDisposition;
import io.ktor.http.CookieEncoding;
import io.ktor.http.CookieKt;
import io.ktor.util.collections.ConcurrentMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.r;
import r7.a;
import x6.i0;
import x6.s;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\fH\u0014¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R,\u0010\u0014\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0004\u0012\u00020\u00060\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R'\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u000e¨\u0006\u001a"}, d2 = {"Lio/ktor/server/request/RequestCookies;", "", "Lio/ktor/server/request/ApplicationRequest;", "request", "<init>", "(Lio/ktor/server/request/ApplicationRequest;)V", "", ContentDisposition.Parameters.Name, "Lio/ktor/http/CookieEncoding;", "encoding", "get", "(Ljava/lang/String;Lio/ktor/http/CookieEncoding;)Ljava/lang/String;", "", "fetchCookies", "()Ljava/util/Map;", "Lio/ktor/server/request/ApplicationRequest;", "getRequest", "()Lio/ktor/server/request/ApplicationRequest;", "Lio/ktor/util/collections/ConcurrentMap;", "Lx6/x;", "map", "Lio/ktor/util/collections/ConcurrentMap;", "rawCookies$delegate", "Lx6/s;", "getRawCookies", "rawCookies", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class RequestCookies {
    private final ConcurrentMap<x, String> map = new ConcurrentMap<>(0, 1, null);

    /* JADX INFO: renamed from: rawCookies$delegate, reason: from kotlin metadata */
    private final s rawCookies = new i0(new RequestCookies$rawCookies$2(this));
    private final ApplicationRequest request;

    /* JADX INFO: renamed from: io.ktor.server.request.RequestCookies$get$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        final /* synthetic */ CookieEncoding $encoding;
        final /* synthetic */ String $rawValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, CookieEncoding cookieEncoding) {
            super(0);
            this.$rawValue = str;
            this.$encoding = cookieEncoding;
        }

        @Override // r7.a
        public final String invoke() {
            return CookieKt.decodeCookieValue(this.$rawValue, this.$encoding);
        }
    }

    public RequestCookies(ApplicationRequest applicationRequest) {
        this.request = applicationRequest;
    }

    public static /* synthetic */ String get$default(RequestCookies requestCookies, String str, CookieEncoding cookieEncoding, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
        }
        if ((i10 & 2) != 0) {
            cookieEncoding = CookieEncoding.URI_ENCODING;
        }
        return requestCookies.get(str, cookieEncoding);
    }

    public Map<String, String> fetchCookies() {
        List<String> all = this.request.getHeaders().getAll("Cookie");
        if (all == null) {
            return a0.f19683i;
        }
        HashMap map = new HashMap(all.size());
        Iterator<String> it = all.iterator();
        while (it.hasNext()) {
            map.putAll(CookieKt.parseClientCookiesHeader$default(it.next(), false, 2, null));
        }
        return map;
    }

    public final String get(String name, CookieEncoding encoding) {
        String str = getRawCookies().get(name);
        if (str == null) {
            return null;
        }
        return this.map.computeIfAbsent(new x(encoding, name), new AnonymousClass1(str, encoding));
    }

    public final Map<String, String> getRawCookies() {
        return (Map) this.rawCookies.getValue();
    }

    public final ApplicationRequest getRequest() {
        return this.request;
    }
}
