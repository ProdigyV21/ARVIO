package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final k0 f6147l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k0 f6148m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6149i;

    static {
        int i10 = 2;
        f6147l = new k0(i10, 0);
        f6148m = new k0(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k0(int i10, int i11) {
        super(i10);
        this.f6149i = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6149i) {
            case 0:
                return Integer.valueOf(((l0) obj2).f6185b.getIntValue());
            default:
                return ((p1) obj2).a();
        }
    }
}
