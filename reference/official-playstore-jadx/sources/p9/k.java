package p9;

import g8.p0;
import g8.u0;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final k f21229l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k f21230m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k f21231n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final k f21232o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21233i;

    static {
        int i10 = 1;
        f21229l = new k(i10, 0);
        f21230m = new k(i10, 1);
        f21231n = new k(i10, 2);
        f21232o = new k(i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i10, int i11) {
        super(i10);
        this.f21233i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f21233i) {
            case 0:
                return Boolean.TRUE;
            case 1:
                return (g8.a) obj;
            case 2:
                return (u0) obj;
            default:
                return (p0) obj;
        }
    }
}
