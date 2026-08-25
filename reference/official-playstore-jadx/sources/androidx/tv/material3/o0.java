package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class o0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6255i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6256l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6257m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(int i10, Object obj, boolean z) {
        super(0);
        this.f6255i = i10;
        this.f6257m = obj;
        this.f6256l = z;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [kotlin.jvm.internal.m, r7.a] */
    @Override // r7.a
    public final Object invoke() {
        switch (this.f6255i) {
            case 0:
                ((r7.l) this.f6257m).invoke(Boolean.valueOf(!this.f6256l));
                break;
            default:
                e.e eVar = (e.e) this.f6257m;
                eVar.f931a = this.f6256l;
                ?? r02 = eVar.f933c;
                if (r02 != 0) {
                    r02.invoke();
                }
                break;
        }
        return x6.t0.f22605a;
    }
}
