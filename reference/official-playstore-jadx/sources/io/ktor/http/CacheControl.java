package io.ktor.http;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lio/ktor/http/CacheControl;", "", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "getVisibility", "()Lio/ktor/http/CacheControl$Visibility;", "MaxAge", "NoCache", "NoStore", "Visibility", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CacheControl {
    private final Visibility visibility;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0019"}, d2 = {"Lio/ktor/http/CacheControl$MaxAge;", "Lio/ktor/http/CacheControl;", "maxAgeSeconds", "", "proxyMaxAgeSeconds", "mustRevalidate", "", "proxyRevalidate", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(ILjava/lang/Integer;ZZLio/ktor/http/CacheControl$Visibility;)V", "getMaxAgeSeconds", "()I", "getMustRevalidate", "()Z", "getProxyMaxAgeSeconds", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProxyRevalidate", "equals", "other", "", "hashCode", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MaxAge extends CacheControl {
        private final int maxAgeSeconds;
        private final boolean mustRevalidate;
        private final Integer proxyMaxAgeSeconds;
        private final boolean proxyRevalidate;

        public /* synthetic */ MaxAge(int i10, Integer num, boolean z, boolean z5, Visibility visibility, int i11, h hVar) {
            this(i10, (i11 & 2) != 0 ? null : num, (i11 & 4) != 0 ? false : z, (i11 & 8) != 0 ? false : z5, (i11 & 16) != 0 ? null : visibility);
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof MaxAge)) {
                return false;
            }
            MaxAge maxAge = (MaxAge) other;
            return maxAge.maxAgeSeconds == this.maxAgeSeconds && p.a(maxAge.proxyMaxAgeSeconds, this.proxyMaxAgeSeconds) && maxAge.mustRevalidate == this.mustRevalidate && maxAge.proxyRevalidate == this.proxyRevalidate && maxAge.getVisibility() == getVisibility();
        }

        public final int getMaxAgeSeconds() {
            return this.maxAgeSeconds;
        }

        public final boolean getMustRevalidate() {
            return this.mustRevalidate;
        }

        public final Integer getProxyMaxAgeSeconds() {
            return this.proxyMaxAgeSeconds;
        }

        public final boolean getProxyRevalidate() {
            return this.proxyRevalidate;
        }

        public int hashCode() {
            int i10 = this.maxAgeSeconds * 31;
            Integer num = this.proxyMaxAgeSeconds;
            int iIntValue = (((((i10 + (num != null ? num.intValue() : 0)) * 31) + (this.mustRevalidate ? 1231 : 1237)) * 31) + (this.proxyRevalidate ? 1231 : 1237)) * 31;
            Visibility visibility = getVisibility();
            return iIntValue + (visibility != null ? visibility.hashCode() : 0);
        }

        public String toString() {
            ArrayList arrayList = new ArrayList(5);
            arrayList.add("max-age=" + this.maxAgeSeconds);
            if (this.proxyMaxAgeSeconds != null) {
                arrayList.add("s-maxage=" + this.proxyMaxAgeSeconds);
            }
            if (this.mustRevalidate) {
                arrayList.add(io.ktor.client.utils.CacheControl.MUST_REVALIDATE);
            }
            if (this.proxyRevalidate) {
                arrayList.add(io.ktor.client.utils.CacheControl.PROXY_REVALIDATE);
            }
            if (getVisibility() != null) {
                arrayList.add(getVisibility().getHeaderValue());
            }
            return x.u0(arrayList, ", ", null, null, null, 62);
        }

        public MaxAge(int i10, Integer num, boolean z, boolean z5, Visibility visibility) {
            super(visibility);
            this.maxAgeSeconds = i10;
            this.proxyMaxAgeSeconds = num;
            this.mustRevalidate = z;
            this.proxyRevalidate = z5;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lio/ktor/http/CacheControl$NoCache;", "Lio/ktor/http/CacheControl;", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NoCache extends CacheControl {
        public NoCache(Visibility visibility) {
            super(visibility);
        }

        public boolean equals(Object other) {
            return (other instanceof NoCache) && getVisibility() == ((NoCache) other).getVisibility();
        }

        public int hashCode() {
            Visibility visibility = getVisibility();
            if (visibility != null) {
                return visibility.hashCode();
            }
            return 0;
        }

        public String toString() {
            if (getVisibility() == null) {
                return io.ktor.client.utils.CacheControl.NO_CACHE;
            }
            return "no-cache, " + getVisibility().getHeaderValue();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lio/ktor/http/CacheControl$NoStore;", "Lio/ktor/http/CacheControl;", "visibility", "Lio/ktor/http/CacheControl$Visibility;", "(Lio/ktor/http/CacheControl$Visibility;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NoStore extends CacheControl {
        public NoStore(Visibility visibility) {
            super(visibility);
        }

        public boolean equals(Object other) {
            return (other instanceof NoStore) && ((NoStore) other).getVisibility() == getVisibility();
        }

        public int hashCode() {
            Visibility visibility = getVisibility();
            if (visibility != null) {
                return visibility.hashCode();
            }
            return 0;
        }

        public String toString() {
            if (getVisibility() == null) {
                return io.ktor.client.utils.CacheControl.NO_STORE;
            }
            return "no-store, " + getVisibility().getHeaderValue();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lio/ktor/http/CacheControl$Visibility;", "", "headerValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getHeaderValue$ktor_http", "()Ljava/lang/String;", "Public", "Private", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum Visibility {
        Public(io.ktor.client.utils.CacheControl.PUBLIC),
        Private(io.ktor.client.utils.CacheControl.PRIVATE);

        private final String headerValue;

        Visibility(String str) {
            this.headerValue = str;
        }

        /* JADX INFO: renamed from: getHeaderValue$ktor_http, reason: from getter */
        public final String getHeaderValue() {
            return this.headerValue;
        }
    }

    public CacheControl(Visibility visibility) {
        this.visibility = visibility;
    }

    public final Visibility getVisibility() {
        return this.visibility;
    }
}
