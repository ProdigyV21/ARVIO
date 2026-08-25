package g8;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final v f15548l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final v f15549m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final v f15550n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final v f15551o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final v f15552p;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15553i;

    static {
        int i10 = 1;
        f15548l = new v(i10, 0);
        f15549m = new v(i10, 1);
        f15550n = new v(i10, 2);
        f15551o = new v(i10, 3);
        f15552p = new v(i10, 4);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(int i10, int i11) {
        super(i10);
        this.f15553i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f15553i) {
            case 0:
                return 0;
            case 1:
                return ((h0) obj).c();
            case 2:
                return Boolean.valueOf(((k) obj) instanceof a);
            case 3:
                return Boolean.valueOf(!(((k) obj) instanceof j));
            default:
                return new ga.p(((a) ((k) obj)).getTypeParameters(), 3);
        }
    }
}
