package io.ktor.serialization.kotlinx;

import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"providers", "", "Lio/ktor/serialization/kotlinx/KotlinxSerializationExtensionProvider;", "getProviders", "()Ljava/util/List;", "ktor-serialization-kotlinx"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExtensionsJvmKt {
    private static final List<KotlinxSerializationExtensionProvider> providers = x.c1(ServiceLoader.load(KotlinxSerializationExtensionProvider.class, KotlinxSerializationExtensionProvider.class.getClassLoader()));

    public static final List<KotlinxSerializationExtensionProvider> getProviders() {
        return providers;
    }
}
