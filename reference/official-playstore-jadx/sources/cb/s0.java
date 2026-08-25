package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class s0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7555i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ya.h f7556l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ya.h f7557m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s0(ya.h hVar, ya.h hVar2, int i10) {
        super(1);
        this.f7555i = i10;
        this.f7556l = hVar;
        this.f7557m = hVar2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7555i) {
            case 0:
                ab.a aVar = (ab.a) obj;
                aVar.a("key", this.f7556l.getDescriptor(), (12 & 8) == 0);
                aVar.a("value", this.f7557m.getDescriptor(), (12 & 8) == 0);
                break;
            default:
                ab.a aVar2 = (ab.a) obj;
                aVar2.a("first", this.f7556l.getDescriptor(), (12 & 8) == 0);
                aVar2.a("second", this.f7557m.getDescriptor(), (12 & 8) == 0);
                break;
        }
        return x6.t0.f22605a;
    }
}
