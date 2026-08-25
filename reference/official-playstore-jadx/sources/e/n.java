package e;

import androidx.activity.compose.ReportDrawnKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class n extends r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14962i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(int i10) {
        super(2);
        this.f14962i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        ReportDrawnKt.ReportDrawn((Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f14962i | 1));
        return t0.f22605a;
    }
}
