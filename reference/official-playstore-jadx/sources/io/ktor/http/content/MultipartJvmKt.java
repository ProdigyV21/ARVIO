package io.ktor.http.content;

import io.ktor.http.content.PartData;
import java.io.InputStream;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001b\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/ktor/http/content/PartData$FileItem;", "Lkotlin/Function0;", "Ljava/io/InputStream;", "getStreamProvider", "(Lio/ktor/http/content/PartData$FileItem;)Lr7/a;", "streamProvider", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultipartJvmKt {
    public static final a<InputStream> getStreamProvider(PartData.FileItem fileItem) {
        return new MultipartJvmKt$streamProvider$1(fileItem);
    }
}
