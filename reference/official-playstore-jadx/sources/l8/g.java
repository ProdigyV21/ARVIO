package l8;

import java.util.ArrayList;
import m8.s;
import s9.o;
import w8.l;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements o, v8.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final g f20066b = new g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f20067c = new g();

    @Override // s9.o
    public void a(g8.c cVar) {
        throw new IllegalStateException("Cannot infer visibility for " + cVar);
    }

    @Override // s9.o
    public void b(g8.f fVar, ArrayList arrayList) {
        throw new IllegalStateException("Incomplete hierarchy for class " + fVar.getName() + ", unresolved classes " + arrayList);
    }

    @Override // v8.b
    public i c(l lVar) {
        return new i((s) lVar);
    }
}
