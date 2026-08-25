package io.ktor.serialization.kotlinx.json;

import db.b;
import io.ktor.http.ContentType;
import io.ktor.serialization.Configuration;
import io.ktor.serialization.kotlinx.KotlinxSerializationConverterKt;
import kotlin.Metadata;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a%\u0010\u0002\u001a\u00020\u0005*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0002\u0010\u0006\"\u0017\u0010\u0007\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/serialization/Configuration;", "Ldb/b;", "json", "Lio/ktor/http/ContentType;", "contentType", "Lx6/t0;", "(Lio/ktor/serialization/Configuration;Ldb/b;Lio/ktor/http/ContentType;)V", "DefaultJson", "Ldb/b;", "getDefaultJson", "()Ldb/b;", "ktor-serialization-kotlinx-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JsonSupportKt {
    private static final b DefaultJson = b0.a(JsonSupportKt$DefaultJson$1.INSTANCE);

    public static final b getDefaultJson() {
        return DefaultJson;
    }

    public static final void json(Configuration configuration, b bVar, ContentType contentType) {
        KotlinxSerializationConverterKt.serialization(configuration, contentType, bVar);
    }

    public static /* synthetic */ void json$default(Configuration configuration, b bVar, ContentType contentType, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            bVar = DefaultJson;
        }
        if ((i10 & 2) != 0) {
            contentType = ContentType.Application.INSTANCE.getJson();
        }
        json(configuration, bVar, contentType);
    }
}
