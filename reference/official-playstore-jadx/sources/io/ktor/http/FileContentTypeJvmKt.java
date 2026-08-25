package io.ktor.http;

import io.ktor.http.ContentType;
import io.ktor.util.NioPathKt;
import java.io.File;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"defaultForFile", "Lio/ktor/http/ContentType;", "Lio/ktor/http/ContentType$Companion;", "file", "Ljava/io/File;", "Ljava/nio/file/Path;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FileContentTypeJvmKt {
    public static final ContentType defaultForFile(ContentType.Companion companion, File file) {
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.INSTANCE, o.E0('.', file.getName(), "")));
    }

    public static final ContentType defaultForFile(ContentType.Companion companion, Path path) {
        return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.INSTANCE, NioPathKt.getExtension(path)));
    }
}
