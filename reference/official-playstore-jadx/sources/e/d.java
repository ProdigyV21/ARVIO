package e;

import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.PredictiveBackHandlerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class d extends r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14941i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f14942l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f14943m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f14944n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ x6.o f14945o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(boolean z, x6.o oVar, int i10, int i11, int i12) {
        super(2);
        this.f14941i = i12;
        this.f14942l = z;
        this.f14945o = oVar;
        this.f14943m = i10;
        this.f14944n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f14941i;
        Composer composer = (Composer) obj;
        ((Number) obj2).intValue();
        switch (i10) {
            case 0:
                BackHandlerKt.BackHandler(this.f14942l, (r7.a) this.f14945o, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14943m | 1), this.f14944n);
                break;
            default:
                PredictiveBackHandlerKt.PredictiveBackHandler(this.f14942l, (r7.p) this.f14945o, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14943m | 1), this.f14944n);
                break;
        }
        return t0.f22605a;
    }
}
