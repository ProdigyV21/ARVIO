package q8;

import d8.p;
import kotlin.collections.h0;
import p8.b0;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.f f21338a = f9.f.j("message");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.f f21339b = f9.f.j("allowedTargets");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f9.f f21340c = f9.f.j("value");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f21341d = h0.t0(new x(p.f14751t, b0.f21090c), new x(p.f14754w, b0.f21091d), new x(p.x, b0.f21093f));

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.util.Map] */
    public static r8.i a(f9.c cVar, w8.d dVar, androidx.core.provider.e eVar) {
        w8.a aVarJ;
        w8.a aVarJ2;
        if (cVar.equals(p.f14744m) && (aVarJ2 = dVar.j(b0.f21092e)) != null) {
            return new g(aVarJ2, eVar);
        }
        f9.c cVar2 = (f9.c) f21341d.get(cVar);
        if (cVar2 == null || (aVarJ = dVar.j(cVar2)) == null) {
            return null;
        }
        return b(eVar, aVarJ, false);
    }

    public static r8.i b(androidx.core.provider.e eVar, w8.a aVar, boolean z) {
        f9.b bVarH = aVar.h();
        if (bVarH.equals(f9.b.j(b0.f21090c))) {
            return new l(aVar, eVar);
        }
        if (bVarH.equals(f9.b.j(b0.f21091d))) {
            return new k(aVar, eVar);
        }
        if (bVarH.equals(f9.b.j(b0.f21093f))) {
            return new b(eVar, aVar, p.x);
        }
        if (bVarH.equals(f9.b.j(b0.f21092e))) {
            return null;
        }
        return new t8.f(eVar, aVar, z);
    }
}
