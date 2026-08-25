package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.content.CachingOptions;
import io.ktor.server.util.DateUtilsJvmKt;
import j$.time.ZonedDateTime;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/http/CacheControl;", "cacheControl", "j$/time/ZonedDateTime", "expires", "Lio/ktor/http/content/CachingOptions;", "CachingOptions", "(Lio/ktor/http/CacheControl;Lj$/time/ZonedDateTime;)Lio/ktor/http/content/CachingOptions;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CachingOptionsJvmKt {
    public static final CachingOptions CachingOptions(CacheControl cacheControl, ZonedDateTime zonedDateTime) {
        return new CachingOptions(cacheControl, DateUtilsJvmKt.toGMTDate(zonedDateTime));
    }

    public static /* synthetic */ CachingOptions CachingOptions$default(CacheControl cacheControl, ZonedDateTime zonedDateTime, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            cacheControl = null;
        }
        return CachingOptions(cacheControl, zonedDateTime);
    }
}
