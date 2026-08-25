package ba;

/* JADX INFO: loaded from: classes5.dex */
public abstract class x implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.jvm.internal.r f7334a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7335b;

    /* JADX WARN: Multi-variable type inference failed */
    public x(String str, r7.l lVar) {
        this.f7334a = (kotlin.jvm.internal.r) lVar;
        this.f7335b = "must return ".concat(str);
    }

    @Override // ba.e
    public final String a() {
        return this.f7335b;
    }

    @Override // ba.e
    public final String b(r8.g gVar) {
        return t7.a.B(this, gVar);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // ba.e
    public final boolean c(r8.g gVar) {
        return kotlin.jvm.internal.p.a(gVar.f19379q, this.f7334a.invoke(m9.d.e(gVar)));
    }
}
