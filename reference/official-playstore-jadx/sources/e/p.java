package e;

import androidx.activity.compose.ReportDrawnKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class p extends r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14966i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f14967l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f14968m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(int i10, int i11, r7.a aVar) {
        super(2);
        this.f14966i = i11;
        this.f14967l = aVar;
        this.f14968m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f14966i;
        Composer composer = (Composer) obj;
        ((Number) obj2).intValue();
        switch (i10) {
            case 0:
                ReportDrawnKt.ReportDrawnWhen(this.f14967l, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14968m | 1));
                break;
            default:
                ReportDrawnKt.ReportDrawnWhen(this.f14967l, composer, RecomposeScopeImplKt.updateChangedFlags(this.f14968m | 1));
                break;
        }
        return t0.f22605a;
    }
}
