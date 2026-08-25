package xa;

import ab.g;
import bb.e;
import cb.h1;
import wa.b;
import wa.c;
import xc.d;
import ya.h;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f22712a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h1 f22713b = d.F("Instant");

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        b bVar = c.Companion;
        String strV = dVar.v();
        bVar.getClass();
        return b.a(strV);
    }

    @Override // ya.s, ya.c
    public final g getDescriptor() {
        return f22713b;
    }

    @Override // ya.s
    public final void serialize(e eVar, Object obj) {
        eVar.E(((c) obj).f22539i.toString());
    }
}
