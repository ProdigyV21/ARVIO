package androidx.tv.material3;

import android.view.KeyEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes3.dex */
public final class r3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ka.k0 f6346i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f6347l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f6348m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6349n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ PressInteraction.Press f6350o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableState f6351p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r3(ka.k0 k0Var, r7.a aVar, r7.a aVar2, MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, MutableState mutableState) {
        super(1);
        this.f6346i = k0Var;
        this.f6347l = aVar;
        this.f6348m = aVar2;
        this.f6349n = mutableInteractionSource;
        this.f6350o = press;
        this.f6351p = mutableState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.l
    public final Object invoke(Object obj) {
        r7.a aVar;
        KeyEvent keyEventM4390unboximpl = ((androidx.compose.ui.input.key.KeyEvent) obj).m4390unboximpl();
        if (!kotlin.collections.r.I(SurfaceKt.f5725b, keyEventM4390unboximpl.getKeyCode())) {
            return Boolean.FALSE;
        }
        int action = keyEventM4390unboximpl.getAction();
        ka.k0 k0Var = this.f6346i;
        PressInteraction.Press press = this.f6350o;
        MutableInteractionSource mutableInteractionSource = this.f6349n;
        MutableState mutableState = this.f6351p;
        if (action == 0) {
            int repeatCount = keyEventM4390unboximpl.getRepeatCount();
            if (repeatCount == 0) {
                ka.m0.p(k0Var, null, 0, new o3(mutableInteractionSource, press, null), 3);
            } else if (repeatCount == 1 && (aVar = this.f6347l) != null) {
                mutableState.setValue(Boolean.TRUE);
                ka.m0.p(k0Var, null, 0, new p3(mutableInteractionSource, press, null), 3);
                aVar.invoke();
            }
        } else if (action == 1) {
            if (((Boolean) mutableState.getValue()).booleanValue()) {
                mutableState.setValue(Boolean.FALSE);
            } else {
                ka.m0.p(k0Var, null, 0, new q3(mutableInteractionSource, press, null), 3);
                r7.a aVar2 = this.f6348m;
                if (aVar2 != null) {
                    aVar2.invoke();
                }
            }
        }
        return Boolean.TRUE;
    }
}
