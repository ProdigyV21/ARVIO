package t9;

import kotlin.reflect.jvm.internal.impl.protobuf.l;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends r9.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f21999m;

    static {
        l lVar = new l();
        b9.b.a(lVar);
        f21999m = new a(lVar, b9.b.f7261c, b9.b.f7260b, b9.b.f7262d, b9.b.f7263e, b9.b.f7264f, b9.b.f7265g, b9.b.f7267i, b9.b.f7266h, b9.b.f7268j, b9.b.k, b9.b.f7269l);
    }

    public static String a(f9.c cVar) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(cVar.b().replace('.', '/'));
        sb2.append('/');
        sb2.append((cVar.d() ? "default-package" : cVar.f().d()).concat(".kotlin_builtins"));
        return sb2.toString();
    }
}
