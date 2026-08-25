package io.ktor.http.content;

import io.ktor.http.DateUtilsKt;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0013\u001a\u00020\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010 \u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010$\u001a\u00020\u00102\b\u0010#\u001a\u0004\u0018\u00010\"HÖ\u0003¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010\u001aR\u0014\u0010(\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010&¨\u0006)"}, d2 = {"Lio/ktor/http/content/LastModifiedVersion;", "Lio/ktor/http/content/Version;", "Lio/ktor/util/date/GMTDate;", "lastModified", "<init>", "(Lio/ktor/util/date/GMTDate;)V", "", "", "parseDates", "(Ljava/util/List;)Ljava/util/List;", "Lio/ktor/http/Headers;", "requestHeaders", "Lio/ktor/http/content/VersionCheckResult;", "check", "(Lio/ktor/http/Headers;)Lio/ktor/http/content/VersionCheckResult;", "dates", "", "ifModifiedSince", "(Ljava/util/List;)Z", "ifUnmodifiedSince", "Lio/ktor/http/HeadersBuilder;", "builder", "Lx6/t0;", "appendHeadersTo", "(Lio/ktor/http/HeadersBuilder;)V", "component1", "()Lio/ktor/util/date/GMTDate;", "copy", "(Lio/ktor/util/date/GMTDate;)Lio/ktor/http/content/LastModifiedVersion;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lio/ktor/util/date/GMTDate;", "getLastModified", "truncatedModificationDate", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class LastModifiedVersion implements Version {
    private final GMTDate lastModified;
    private final GMTDate truncatedModificationDate;

    public LastModifiedVersion(GMTDate gMTDate) {
        this.lastModified = gMTDate;
        this.truncatedModificationDate = DateKt.truncateToSeconds(gMTDate);
    }

    public static /* synthetic */ LastModifiedVersion copy$default(LastModifiedVersion lastModifiedVersion, GMTDate gMTDate, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            gMTDate = lastModifiedVersion.lastModified;
        }
        return lastModifiedVersion.copy(gMTDate);
    }

    private final List<GMTDate> parseDates(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            GMTDate gMTDateFromHttpToGmtDate = null;
            if (!it.hasNext()) {
                break;
            }
            try {
                gMTDateFromHttpToGmtDate = DateUtilsKt.fromHttpToGmtDate((String) it.next());
            } catch (Throwable unused) {
            }
            if (gMTDateFromHttpToGmtDate != null) {
                arrayList2.add(gMTDateFromHttpToGmtDate);
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    @Override // io.ktor.http.content.Version
    public void appendHeadersTo(HeadersBuilder builder) {
        builder.set(HttpHeaders.INSTANCE.getLastModified(), DateUtilsKt.toHttpDate(this.lastModified));
    }

    @Override // io.ktor.http.content.Version
    public VersionCheckResult check(Headers requestHeaders) {
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        List<String> all = requestHeaders.getAll(httpHeaders.getIfModifiedSince());
        List<GMTDate> dates = all != null ? parseDates(all) : null;
        if (dates != null && !ifModifiedSince(dates)) {
            return VersionCheckResult.NOT_MODIFIED;
        }
        List<String> all2 = requestHeaders.getAll(httpHeaders.getIfUnmodifiedSince());
        List<GMTDate> dates2 = all2 != null ? parseDates(all2) : null;
        return (dates2 == null || ifUnmodifiedSince(dates2)) ? VersionCheckResult.OK : VersionCheckResult.PRECONDITION_FAILED;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    public final LastModifiedVersion copy(GMTDate lastModified) {
        return new LastModifiedVersion(lastModified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LastModifiedVersion) && p.a(this.lastModified, ((LastModifiedVersion) other).lastModified);
    }

    public final GMTDate getLastModified() {
        return this.lastModified;
    }

    public int hashCode() {
        return this.lastModified.hashCode();
    }

    public final boolean ifModifiedSince(List<GMTDate> dates) {
        if (dates.isEmpty()) {
            return false;
        }
        Iterator<T> it = dates.iterator();
        while (it.hasNext()) {
            if (this.truncatedModificationDate.compareTo((GMTDate) it.next()) > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean ifUnmodifiedSince(List<GMTDate> dates) {
        if (dates.isEmpty()) {
            return true;
        }
        Iterator<T> it = dates.iterator();
        while (it.hasNext()) {
            if (this.truncatedModificationDate.compareTo((GMTDate) it.next()) > 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "LastModifiedVersion(lastModified=" + this.lastModified + ')';
    }
}
