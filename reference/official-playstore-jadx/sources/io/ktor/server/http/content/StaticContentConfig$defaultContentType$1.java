package io.ktor.server.http.content;

import g8.b;
import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import io.ktor.http.FileContentTypeKt;
import java.io.File;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: Add missing generic type declarations: [Resource] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "Resource", "", "it", "invoke", "(Ljava/lang/Object;)Lio/ktor/http/ContentType;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class StaticContentConfig$defaultContentType$1<Resource> extends r implements l<Resource, ContentType> {
    public static final StaticContentConfig$defaultContentType$1 INSTANCE = new StaticContentConfig$defaultContentType$1();

    public StaticContentConfig$defaultContentType$1() {
        super(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.l
    public final ContentType invoke(Resource resource) {
        if (resource instanceof File) {
            return FileContentTypeJvmKt.defaultForFile(ContentType.INSTANCE, (File) resource);
        }
        if (resource instanceof URL) {
            return FileContentTypeKt.defaultForFilePath(ContentType.INSTANCE, ((URL) resource).getPath());
        }
        StringBuilder sb2 = new StringBuilder("Argument can be only of type File or URL, but was ");
        throw new IllegalArgumentException(b.h(l0.f19747a, resource.getClass(), sb2));
    }
}
