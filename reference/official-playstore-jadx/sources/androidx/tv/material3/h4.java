package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.DpRect;

/* JADX INFO: loaded from: classes3.dex */
public final class h4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6066i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TabRowDefaults f6067l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ DpRect f6068m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6069n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Modifier f6070o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f6071p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ long f6072q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6073r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6074s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h4(TabRowDefaults tabRowDefaults, DpRect dpRect, boolean z, Modifier modifier, long j10, long j11, int i10, int i11, int i12) {
        super(2);
        this.f6066i = i12;
        this.f6067l = tabRowDefaults;
        this.f6068m = dpRect;
        this.f6069n = z;
        this.f6070o = modifier;
        this.f6071p = j10;
        this.f6072q = j11;
        this.f6073r = i10;
        this.f6074s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6066i) {
            case 0:
                ((Number) obj2).intValue();
                this.f6067l.m6016PillIndicatorjA1GFJw(this.f6068m, this.f6069n, this.f6070o, this.f6071p, this.f6072q, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6073r | 1), this.f6074s);
                break;
            default:
                ((Number) obj2).intValue();
                this.f6067l.m6017UnderlinedIndicatorjA1GFJw(this.f6068m, this.f6069n, this.f6070o, this.f6071p, this.f6072q, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6073r | 1), this.f6074s);
                break;
        }
        return x6.t0.f22605a;
    }
}
