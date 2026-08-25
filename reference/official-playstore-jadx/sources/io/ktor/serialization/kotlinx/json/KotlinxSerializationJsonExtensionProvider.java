package io.ktor.serialization.kotlinx.json;

import db.b;
import io.ktor.serialization.kotlinx.KotlinxSerializationExtension;
import io.ktor.serialization.kotlinx.KotlinxSerializationExtensionProvider;
import kotlin.Metadata;
import ya.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/serialization/kotlinx/json/KotlinxSerializationJsonExtensionProvider;", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtensionProvider;", "<init>", "()V", "Lya/o;", "format", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "extension", "(Lya/o;)Lio/ktor/serialization/kotlinx/KotlinxSerializationExtension;", "ktor-serialization-kotlinx-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinxSerializationJsonExtensionProvider implements KotlinxSerializationExtensionProvider {
    @Override // io.ktor.serialization.kotlinx.KotlinxSerializationExtensionProvider
    public KotlinxSerializationExtension extension(o format) {
        if (format instanceof b) {
            return new KotlinxSerializationJsonExtensions((b) format);
        }
        return null;
    }
}
