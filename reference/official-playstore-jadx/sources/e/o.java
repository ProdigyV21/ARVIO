package e;

import androidx.activity.compose.ReportDrawnKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class o extends r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14963i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f14964l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f14965m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(int i10, int i11, r7.l lVar) {
        super(2);
        this.f14963i = i11;
        this.f14964l = lVar;
        this.f14965m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f14963i;
        Composer composer = (Composer) obj;
        ((Number) obj2).intValue();
        switch (i10) {
            case 0:
                ReportDrawnKt.ReportDrawnAfter(this.f14964l, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14965m | 1));
                break;
            default:
                ReportDrawnKt.ReportDrawnAfter(this.f14964l, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14965m | 1));
                break;
        }
        return t0.f22605a;
    }
}
