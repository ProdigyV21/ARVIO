package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes3.dex */
public final class y3 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f6570i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f6571l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f6572m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f6573n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f6574o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ SwitchColors f6575p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6576q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6577r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6578s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y3(boolean z, r7.l lVar, Modifier modifier, r7.p pVar, boolean z5, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
        super(2);
        this.f6570i = z;
        this.f6571l = lVar;
        this.f6572m = modifier;
        this.f6573n = pVar;
        this.f6574o = z5;
        this.f6575p = switchColors;
        this.f6576q = mutableInteractionSource;
        this.f6577r = i10;
        this.f6578s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        SwitchKt.Switch(this.f6570i, this.f6571l, this.f6572m, this.f6573n, this.f6574o, this.f6575p, this.f6576q, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(this.f6577r | 1), this.f6578s);
        return x6.t0.f22605a;
    }
}
