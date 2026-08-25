package coil.network;

import android.content.Time;
import android.content.Utils;
import gb.a0;
import gb.j0;
import gb.k0;
import gb.p0;
import gb.z;
import io.ktor.http.ContentDisposition;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.u;
import mb.c;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0002\u000e\u000fB\u001d\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcoil/network/CacheStrategy;", "", "Lgb/k0;", "networkRequest", "Lcoil/network/CacheResponse;", "cacheResponse", "<init>", "(Lgb/k0;Lcoil/network/CacheResponse;)V", "Lgb/k0;", "getNetworkRequest", "()Lgb/k0;", "Lcoil/network/CacheResponse;", "getCacheResponse", "()Lcoil/network/CacheResponse;", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CacheStrategy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CacheResponse cacheResponse;
    private final k0 networkRequest;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0010¢\u0006\u0004\b\u000e\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcoil/network/CacheStrategy$Companion;", "", "<init>", "()V", "", ContentDisposition.Parameters.Name, "", "isEndToEnd", "(Ljava/lang/String;)Z", "isContentSpecificHeader", "Lgb/k0;", "request", "Lgb/p0;", "response", "isCacheable", "(Lgb/k0;Lgb/p0;)Z", "Lcoil/network/CacheResponse;", "(Lgb/k0;Lcoil/network/CacheResponse;)Z", "Lgb/a0;", "cachedHeaders", "networkHeaders", "combineHeaders", "(Lgb/a0;Lgb/a0;)Lgb/a0;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final boolean isContentSpecificHeader(String name) {
            return "Content-Length".equalsIgnoreCase(name) || HttpConnection.CONTENT_ENCODING.equalsIgnoreCase(name) || HttpConnection.CONTENT_TYPE.equalsIgnoreCase(name);
        }

        private final boolean isEndToEnd(String name) {
            return ("Connection".equalsIgnoreCase(name) || "Keep-Alive".equalsIgnoreCase(name) || "Proxy-Authenticate".equalsIgnoreCase(name) || "Proxy-Authorization".equalsIgnoreCase(name) || "TE".equalsIgnoreCase(name) || "Trailers".equalsIgnoreCase(name) || "Transfer-Encoding".equalsIgnoreCase(name) || "Upgrade".equalsIgnoreCase(name)) ? false : true;
        }

        public final a0 combineHeaders(a0 cachedHeaders, a0 networkHeaders) {
            z zVar = new z();
            int size = cachedHeaders.size();
            for (int i10 = 0; i10 < size; i10++) {
                String strB = cachedHeaders.b(i10);
                String strE = cachedHeaders.e(i10);
                if ((!"Warning".equalsIgnoreCase(strB) || !u.P(strE, "1", false)) && (isContentSpecificHeader(strB) || !isEndToEnd(strB) || networkHeaders.a(strB) == null)) {
                    zVar.d(strB, strE);
                }
            }
            int size2 = networkHeaders.size();
            for (int i11 = 0; i11 < size2; i11++) {
                String strB2 = networkHeaders.b(i11);
                if (!isContentSpecificHeader(strB2) && isEndToEnd(strB2)) {
                    zVar.d(strB2, networkHeaders.e(i11));
                }
            }
            return zVar.e();
        }

        public final boolean isCacheable(k0 request, p0 response) {
            return (request.a().f15689b || response.i().f15689b || p.a(response.f15785p.a("Vary"), "*")) ? false : true;
        }

        private Companion() {
        }

        public final boolean isCacheable(k0 request, CacheResponse response) {
            return (request.a().f15689b || response.getCacheControl().f15689b || p.a(response.getResponseHeaders().a("Vary"), "*")) ? false : true;
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u0016R\u0016\u0010\u001d\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u0019R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcoil/network/CacheStrategy$Factory;", "", "Lgb/k0;", "request", "Lcoil/network/CacheResponse;", "cacheResponse", "<init>", "(Lgb/k0;Lcoil/network/CacheResponse;)V", "", "computeFreshnessLifetime", "()J", "cacheResponseAge", "", "hasConditions", "(Lgb/k0;)Z", "Lcoil/network/CacheStrategy;", "compute", "()Lcoil/network/CacheStrategy;", "Lgb/k0;", "Lcoil/network/CacheResponse;", "Ljava/util/Date;", "servedDate", "Ljava/util/Date;", "", "servedDateString", "Ljava/lang/String;", "lastModified", "lastModifiedString", "expires", "sentRequestMillis", "J", "receivedResponseMillis", "etag", "", "ageSeconds", "I", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory {
        private int ageSeconds;
        private final CacheResponse cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private long receivedResponseMillis;
        private final k0 request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(k0 k0Var, CacheResponse cacheResponse) {
            this.request = k0Var;
            this.cacheResponse = cacheResponse;
            this.ageSeconds = -1;
            if (cacheResponse != null) {
                this.sentRequestMillis = cacheResponse.getSentRequestAtMillis();
                this.receivedResponseMillis = cacheResponse.getReceivedResponseAtMillis();
                a0 responseHeaders = cacheResponse.getResponseHeaders();
                int size = responseHeaders.size();
                for (int i10 = 0; i10 < size; i10++) {
                    String strB = responseHeaders.b(i10);
                    if (u.L(strB, "Date", true)) {
                        String strA = responseHeaders.a("Date");
                        this.servedDate = strA != null ? c.a(strA) : null;
                        this.servedDateString = responseHeaders.e(i10);
                    } else if (u.L(strB, "Expires", true)) {
                        String strA2 = responseHeaders.a("Expires");
                        this.expires = strA2 != null ? c.a(strA2) : null;
                    } else if (u.L(strB, "Last-Modified", true)) {
                        String strA3 = responseHeaders.a("Last-Modified");
                        this.lastModified = strA3 != null ? c.a(strA3) : null;
                        this.lastModifiedString = responseHeaders.e(i10);
                    } else if (u.L(strB, "ETag", true)) {
                        this.etag = responseHeaders.e(i10);
                    } else if (u.L(strB, "Age", true)) {
                        this.ageSeconds = Utils.toNonNegativeInt(responseHeaders.e(i10), -1);
                    }
                }
            }
        }

        private final long cacheResponseAge() {
            Date date = this.servedDate;
            long jMax = date != null ? Math.max(0L, this.receivedResponseMillis - date.getTime()) : 0L;
            int i10 = this.ageSeconds;
            if (i10 != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i10));
            }
            return jMax + (this.receivedResponseMillis - this.sentRequestMillis) + (Time.INSTANCE.currentMillis() - this.receivedResponseMillis);
        }

        private final long computeFreshnessLifetime() {
            String string;
            int i10 = this.cacheResponse.getCacheControl().f15690c;
            if (i10 != -1) {
                return TimeUnit.SECONDS.toMillis(i10);
            }
            Date date = this.expires;
            if (date != null) {
                Date date2 = this.servedDate;
                long time = date.getTime() - (date2 != null ? date2.getTime() : this.receivedResponseMillis);
                if (time > 0) {
                    return time;
                }
            } else if (this.lastModified != null) {
                List list = this.request.f15730a.f15623g;
                if (list == null) {
                    string = null;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    gb.u.i(sb2, list);
                    string = sb2.toString();
                }
                if (string == null) {
                    Date date3 = this.servedDate;
                    long time2 = (date3 != null ? date3.getTime() : this.sentRequestMillis) - this.lastModified.getTime();
                    if (time2 > 0) {
                        return time2 / ((long) 10);
                    }
                }
            }
            return 0L;
        }

        private final boolean hasConditions(k0 request) {
            return (request.f15732c.a("If-Modified-Since") == null && request.f15732c.a("If-None-Match") == null) ? false : true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final CacheStrategy compute() {
            String str;
            int i10;
            CacheResponse cacheResponse = this.cacheResponse;
            CacheResponse cacheResponse2 = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            Object[] objArr4 = 0;
            Object[] objArr5 = 0;
            Object[] objArr6 = 0;
            Object[] objArr7 = 0;
            Object[] objArr8 = 0;
            Object[] objArr9 = 0;
            Object[] objArr10 = 0;
            Object[] objArr11 = 0;
            Object[] objArr12 = 0;
            if (cacheResponse == null) {
                return new CacheStrategy(this.request, cacheResponse2, objArr12 == true ? 1 : 0);
            }
            if (this.request.f15730a.f15626j && !cacheResponse.getIsTls()) {
                return new CacheStrategy(this.request, objArr11 == true ? 1 : 0, objArr10 == true ? 1 : 0);
            }
            gb.h cacheControl = this.cacheResponse.getCacheControl();
            if (!CacheStrategy.INSTANCE.isCacheable(this.request, this.cacheResponse)) {
                return new CacheStrategy(this.request, objArr9 == true ? 1 : 0, objArr8 == true ? 1 : 0);
            }
            gb.h hVarA = this.request.a();
            if (hVarA.f15688a || hasConditions(this.request)) {
                return new CacheStrategy(this.request, objArr2 == true ? 1 : 0, objArr == true ? 1 : 0);
            }
            long jCacheResponseAge = cacheResponseAge();
            long jComputeFreshnessLifetime = computeFreshnessLifetime();
            int i11 = hVarA.f15690c;
            if (i11 != -1) {
                jComputeFreshnessLifetime = Math.min(jComputeFreshnessLifetime, TimeUnit.SECONDS.toMillis(i11));
            }
            int i12 = hVarA.f15696i;
            long millis = 0;
            long millis2 = i12 != -1 ? TimeUnit.SECONDS.toMillis(i12) : 0L;
            if (!cacheControl.f15694g && (i10 = hVarA.f15695h) != -1) {
                millis = TimeUnit.SECONDS.toMillis(i10);
            }
            if (!cacheControl.f15688a && jCacheResponseAge + millis2 < jComputeFreshnessLifetime + millis) {
                return new CacheStrategy(objArr7 == true ? 1 : 0, this.cacheResponse, objArr6 == true ? 1 : 0);
            }
            String str2 = this.etag;
            if (str2 != null) {
                str = "If-None-Match";
            } else {
                str = "If-Modified-Since";
                if (this.lastModified != null) {
                    str2 = this.lastModifiedString;
                } else {
                    if (this.servedDate == null) {
                        return new CacheStrategy(this.request, objArr4 == true ? 1 : 0, objArr3 == true ? 1 : 0);
                    }
                    str2 = this.servedDateString;
                }
            }
            j0 j0VarB = this.request.b();
            j0VarB.f15724c.a(str, str2);
            return new CacheStrategy(j0VarB.b(), this.cacheResponse, objArr5 == true ? 1 : 0);
        }
    }

    public /* synthetic */ CacheStrategy(k0 k0Var, CacheResponse cacheResponse, h hVar) {
        this(k0Var, cacheResponse);
    }

    public final CacheResponse getCacheResponse() {
        return this.cacheResponse;
    }

    public final k0 getNetworkRequest() {
        return this.networkRequest;
    }

    private CacheStrategy(k0 k0Var, CacheResponse cacheResponse) {
        this.networkRequest = k0Var;
        this.cacheResponse = cacheResponse;
    }
}
