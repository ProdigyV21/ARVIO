package io.ktor.http.content;

import io.ktor.http.CacheControl;
import io.ktor.util.date.GMTDate;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/ktor/http/content/CachingOptions;", "", "cacheControl", "Lio/ktor/http/CacheControl;", "expires", "Lio/ktor/util/date/GMTDate;", "(Lio/ktor/http/CacheControl;Lio/ktor/util/date/GMTDate;)V", "getCacheControl", "()Lio/ktor/http/CacheControl;", "getExpires", "()Lio/ktor/util/date/GMTDate;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class CachingOptions {
    private final CacheControl cacheControl;
    private final GMTDate expires;

    /* JADX WARN: Multi-variable type inference failed */
    public CachingOptions() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ CachingOptions copy$default(CachingOptions cachingOptions, CacheControl cacheControl, GMTDate gMTDate, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            cacheControl = cachingOptions.cacheControl;
        }
        if ((i10 & 2) != 0) {
            gMTDate = cachingOptions.expires;
        }
        return cachingOptions.copy(cacheControl, gMTDate);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CacheControl getCacheControl() {
        return this.cacheControl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final GMTDate getExpires() {
        return this.expires;
    }

    public final CachingOptions copy(CacheControl cacheControl, GMTDate expires) {
        return new CachingOptions(cacheControl, expires);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CachingOptions)) {
            return false;
        }
        CachingOptions cachingOptions = (CachingOptions) other;
        return p.a(this.cacheControl, cachingOptions.cacheControl) && p.a(this.expires, cachingOptions.expires);
    }

    public final CacheControl getCacheControl() {
        return this.cacheControl;
    }

    public final GMTDate getExpires() {
        return this.expires;
    }

    public int hashCode() {
        CacheControl cacheControl = this.cacheControl;
        int iHashCode = (cacheControl == null ? 0 : cacheControl.hashCode()) * 31;
        GMTDate gMTDate = this.expires;
        return iHashCode + (gMTDate != null ? gMTDate.hashCode() : 0);
    }

    public String toString() {
        return "CachingOptions(cacheControl=" + this.cacheControl + ", expires=" + this.expires + ')';
    }

    public CachingOptions(CacheControl cacheControl, GMTDate gMTDate) {
        this.cacheControl = cacheControl;
        this.expires = gMTDate;
    }

    public /* synthetic */ CachingOptions(CacheControl cacheControl, GMTDate gMTDate, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : cacheControl, (i10 & 2) != 0 ? null : gMTDate);
    }
}
