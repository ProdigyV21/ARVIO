package d8;

import j8.d0;
import v9.b0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14701i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f14702l;

    public /* synthetic */ i(Object obj, int i10) {
        this.f14701i = i10;
        this.f14702l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f14701i) {
            case 0:
                f9.f fVar = (f9.f) obj;
                d0 d0VarK = ((k) this.f14702l).k();
                f9.c cVar = q.f14766l;
                p9.n nVarL = d0VarK.B(cVar).l();
                if (nVarL == null) {
                    k.a(11);
                    throw null;
                }
                g8.h hVarE = ((p9.j) nVarL).e(fVar, o8.e.f20793i);
                if (hVarE == null) {
                    throw new AssertionError("Built-in class " + cVar.c(fVar) + " is not found");
                }
                if (hVarE instanceof g8.f) {
                    return (g8.f) hVarE;
                }
                throw new AssertionError("Must be a class descriptor " + fVar + ", but was " + hVarE);
            case 1:
                j8.a aVar = (j8.a) this.f14702l;
                j8.b bVar = aVar.f19241l;
                ((w9.e) obj).getClass();
                return (b0) aVar.f19241l.f19249l.invoke();
            default:
                g8.c cVar2 = (g8.c) obj;
                if (cVar2 == null) {
                    throw new IllegalArgumentException("Argument for @NotNull parameter 'descriptor' of kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1$1.invoke must not be null");
                }
                ((q8.a) this.f14702l).f21329c.a(cVar2);
                return t0.f22605a;
        }
    }
}
