package androidx.tv.material3;

import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public final class i1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i1 f6091l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final i1 f6092m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i1 f6093n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final i1 f6094o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final i1 f6095p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final i1 f6096q;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6097i;

    static {
        int i10 = 0;
        f6091l = new i1(i10, 0);
        f6092m = new i1(i10, 1);
        f6093n = new i1(i10, 2);
        f6094o = new i1(i10, 3);
        f6095p = new i1(i10, 4);
        f6096q = new i1(i10, 5);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i1(int i10, int i11) {
        super(i10);
        this.f6097i = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f6097i) {
            case 0:
                ProvidableCompositionLocal providableCompositionLocal = ColorSchemeKt.f5609a;
                long j10 = j2.c.f19073t;
                return new h1(j10, j2.c.f19064j, j2.c.f19074u, j2.c.k, j2.c.f19059e, j2.c.f19076w, j2.c.f19065l, j2.c.x, j2.c.f19066m, j2.c.A, j2.c.f19069p, j2.c.B, j2.c.f19070q, j2.c.f19055a, j2.c.f19061g, j2.c.f19077y, j2.c.f19067n, j2.c.z, j2.c.f19068o, j10, j2.c.f19060f, j2.c.f19058d, j2.c.f19056b, j2.c.f19062h, j2.c.f19057c, j2.c.f19063i, j2.c.f19071r, j2.c.f19072s, j2.c.f19075v);
            case 1:
                return new d3();
            case 2:
                return Dp.m5676boximpl(Dp.m5678constructorimpl(0));
            case 3:
                return x6.t0.f22605a;
            case 4:
                return j2.p.f19198a;
            default:
                return new p4();
        }
    }
}
