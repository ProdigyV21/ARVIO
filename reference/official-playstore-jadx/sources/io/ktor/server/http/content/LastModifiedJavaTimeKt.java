package io.ktor.server.http.content;

import io.ktor.http.content.LastModifiedVersion;
import io.ktor.server.util.DateUtilsJvmKt;
import io.ktor.util.date.DateJvmKt;
import j$.time.ZonedDateTime;
import java.nio.file.attribute.FileTime;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0007¢\u0006\u0004\b\u0003\u0010\b¨\u0006\t"}, d2 = {"j$/time/ZonedDateTime", "lastModified", "Lio/ktor/http/content/LastModifiedVersion;", "LastModifiedVersion", "(Lj$/time/ZonedDateTime;)Lio/ktor/http/content/LastModifiedVersion;", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/attribute/FileTime;)Lio/ktor/http/content/LastModifiedVersion;", "", "(J)Lio/ktor/http/content/LastModifiedVersion;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LastModifiedJavaTimeKt {
    public static final LastModifiedVersion LastModifiedVersion(ZonedDateTime zonedDateTime) {
        return new LastModifiedVersion(DateUtilsJvmKt.toGMTDate(zonedDateTime));
    }

    public static final LastModifiedVersion LastModifiedVersion(FileTime fileTime) {
        return new LastModifiedVersion(DateJvmKt.GMTDate(Long.valueOf(fileTime.toMillis())));
    }

    public static final LastModifiedVersion LastModifiedVersion(long j10) {
        return new LastModifiedVersion(DateJvmKt.GMTDate(Long.valueOf(j10)));
    }
}
