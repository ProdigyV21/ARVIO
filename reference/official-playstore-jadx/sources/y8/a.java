package y8;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f22910l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f22911m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22912i;

    static {
        int i10 = 2;
        f22910l = new a(i10, 0);
        f22911m = new a(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i10, int i11) {
        super(i10);
        this.f22912i = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f22912i) {
            case 0:
                return ((h) obj).f22938c.get((a0) obj2);
            default:
                return ((h) obj).f22937b.get((a0) obj2);
        }
    }
}
