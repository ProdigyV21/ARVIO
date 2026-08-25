package io.ktor.serialization.kotlinx;

import io.ktor.http.ContentType;
import io.ktor.serialization.Configuration;
import kotlin.Metadata;
import ya.a;
import ya.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/serialization/Configuration;", "Lio/ktor/http/ContentType;", "contentType", "Lya/a;", "format", "Lx6/t0;", "serialization", "(Lio/ktor/serialization/Configuration;Lio/ktor/http/ContentType;Lya/a;)V", "Lya/x;", "(Lio/ktor/serialization/Configuration;Lio/ktor/http/ContentType;Lya/x;)V", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationConverterKt {
    public static final void serialization(Configuration configuration, ContentType contentType, a aVar) {
        Configuration.DefaultImpls.register$default(configuration, contentType, new KotlinxSerializationConverter(aVar), null, 4, null);
    }

    public static final void serialization(Configuration configuration, ContentType contentType, x xVar) {
        Configuration.DefaultImpls.register$default(configuration, contentType, new KotlinxSerializationConverter(xVar), null, 4, null);
    }
}
