package f8;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements ea.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f15310i = new e();

    public static g8.f a(f9.c cVar, d8.k kVar) {
        String str = d.f15297a;
        f9.b bVar = (f9.b) d.f15304h.get(cVar.i());
        if (bVar != null) {
            return kVar.i(bVar.b());
        }
        return null;
    }

    @Override // ea.b
    public Iterable r(Object obj) {
        kotlin.reflect.m[] mVarArr = n.f15334h;
        return ((g8.c) obj).a().k();
    }
}
