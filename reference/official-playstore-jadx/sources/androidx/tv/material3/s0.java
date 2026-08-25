package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.state.ToggleableState;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ToggleableState f6368i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6369l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f6370m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6371n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ CheckboxColors f6372o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6373p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6374q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6375r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(ToggleableState toggleableState, r7.a aVar, Modifier modifier, boolean z, CheckboxColors checkboxColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
        super(2);
        this.f6368i = toggleableState;
        this.f6369l = aVar;
        this.f6370m = modifier;
        this.f6371n = z;
        this.f6372o = checkboxColors;
        this.f6373p = mutableInteractionSource;
        this.f6374q = i10;
        this.f6375r = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        CheckboxKt.TriStateCheckbox(this.f6368i, this.f6369l, this.f6370m, this.f6371n, this.f6372o, this.f6373p, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6374q | 1), this.f6375r);
        return x6.t0.f22605a;
    }
}
