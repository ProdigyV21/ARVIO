package io.ktor.client.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeKt;
import io.ktor.util.PathKt;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"LocalFileContent", "Lio/ktor/client/content/LocalFileContent;", "baseDir", "Ljava/io/File;", "relativePath", "", "contentType", "Lio/ktor/http/ContentType;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFileContentKt {
    public static final LocalFileContent LocalFileContent(File file, String str, ContentType contentType) {
        return new LocalFileContent(PathKt.combineSafe(file, str), contentType);
    }

    public static /* synthetic */ LocalFileContent LocalFileContent$default(File file, String str, ContentType contentType, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            contentType = FileContentTypeKt.defaultForFilePath(ContentType.INSTANCE, str);
        }
        return LocalFileContent(file, str, contentType);
    }
}
