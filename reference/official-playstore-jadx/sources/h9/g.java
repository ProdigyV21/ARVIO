package h9;

import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final g f15902l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g f15903m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final g f15904n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final g f15905o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15906i;

    static {
        int i10 = 1;
        f15902l = new g(i10, 0);
        f15903m = new g(i10, 1);
        f15904n = new g(i10, 2);
        f15905o = new g(i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(int i10, int i11) {
        super(i10);
        this.f15906i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f15906i) {
            case 0:
                return "";
            case 1:
                return (w) obj;
            case 2:
                return "...";
            default:
                return (w) obj;
        }
    }
}
