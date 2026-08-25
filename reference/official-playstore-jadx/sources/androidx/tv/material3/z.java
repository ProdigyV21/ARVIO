package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class z extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6579i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f6580l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(int i10, l0 l0Var) {
        super(0);
        this.f6579i = i10;
        this.f6580l = l0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f6579i) {
            case 0:
                return Integer.valueOf(this.f6580l.f6184a.getIntValue());
            default:
                return Float.valueOf(this.f6580l.f6185b.getIntValue());
        }
    }
}
