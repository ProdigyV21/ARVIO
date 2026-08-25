package androidx.tv.material3;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: loaded from: classes3.dex */
public final class s extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final s f6356l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final s f6357m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final s f6358n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final s f6359o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final s f6360p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final s f6361q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final s f6362r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final s f6363s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final s f6364t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final s f6365u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final s f6366v;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6367i;

    static {
        int i10 = 1;
        f6356l = new s(i10, 0);
        f6357m = new s(i10, 1);
        f6358n = new s(i10, 2);
        f6359o = new s(i10, 3);
        f6360p = new s(i10, 4);
        f6361q = new s(i10, 5);
        f6362r = new s(i10, 6);
        f6363s = new s(i10, 7);
        f6364t = new s(i10, 8);
        f6365u = new s(i10, 9);
        f6366v = new s(i10, 10);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s(int i10, int i11) {
        super(i10);
        this.f6367i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f6367i) {
            case 0:
                ((GraphicsLayerScope) obj).setAlpha(0.6f);
                break;
            case 1:
                ((GraphicsLayerScope) obj).setAlpha(0.8f);
                break;
            case 2:
                ((Boolean) obj).booleanValue();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                ((GraphicsLayerScope) obj).setAlpha(0.8f);
                break;
            case 7:
                break;
            case 8:
                ((IntSize) obj).getPackedValue();
                break;
            case 9:
                break;
            default:
                break;
        }
        return x6.t0.f22605a;
    }
}
