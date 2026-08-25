package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.state.ToggleableState;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6341i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ToggleableState f6342l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f6343m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ CheckboxColors f6344n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f6345o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(boolean z, ToggleableState toggleableState, Modifier modifier, CheckboxColors checkboxColors, int i10) {
        super(2);
        this.f6341i = z;
        this.f6342l = toggleableState;
        this.f6343m = modifier;
        this.f6344n = checkboxColors;
        this.f6345o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        CheckboxKt.CheckboxImpl(this.f6341i, this.f6342l, this.f6343m, this.f6344n, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6345o | 1));
        return x6.t0.f22605a;
    }
}
