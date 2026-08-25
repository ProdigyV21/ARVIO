package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes3.dex */
public final class n3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f6234i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f6235l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6236m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ PressInteraction.Press f6237n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n3(ka.k0 k0Var, State state, MutableInteractionSource mutableInteractionSource, PressInteraction.Press press) {
        super(1);
        this.f6234i = k0Var;
        this.f6235l = state;
        this.f6236m = mutableInteractionSource;
        this.f6237n = press;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        if (!((FocusState) obj).isFocused() && ((Boolean) this.f6235l.getValue()).booleanValue()) {
            ka.m0.p(this.f6234i, null, 0, new m3(this.f6236m, this.f6237n, null), 3);
        }
        return x6.t0.f22605a;
    }
}
