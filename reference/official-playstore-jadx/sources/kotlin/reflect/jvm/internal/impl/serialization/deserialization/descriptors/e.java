package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19853i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g f19854l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(g gVar, int i10) {
        super(0);
        this.f19853i = i10;
        this.f19854l = gVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f19853i) {
            case 0:
                p9.f fVar = p9.f.f21212m;
                p9.n.f21236a.getClass();
                return this.f19854l.i(fVar, p9.k.f21229l);
            default:
                g gVar = this.f19854l;
                w9.e eVar = gVar.f19856g;
                k kVar = gVar.f19859j;
                eVar.getClass();
                return ((v9.g) kVar.g()).d();
        }
    }
}
