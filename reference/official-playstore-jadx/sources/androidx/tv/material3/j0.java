package androidx.tv.material3;

import android.view.KeyEvent;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;

/* JADX INFO: loaded from: classes3.dex */
public final class j0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.a f6122i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f6123l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6124m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6125n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ FocusManager f6126o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f6127p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(r7.a aVar, l0 l0Var, int i10, boolean z, FocusManager focusManager, FocusRequester focusRequester) {
        super(1);
        this.f6122i = aVar;
        this.f6123l = l0Var;
        this.f6124m = i10;
        this.f6125n = z;
        this.f6126o = focusManager;
        this.f6127p = focusRequester;
    }

    public static final boolean a(KeyEvent keyEvent, r7.a aVar, l0 l0Var, int i10, boolean z, FocusManager focusManager, FocusRequester focusRequester, int i11) {
        FocusState focusState;
        if (keyEvent.getRepeatCount() <= 0) {
            FocusState focusState2 = (FocusState) aVar.invoke();
            if (focusState2 != null && focusState2.isFocused()) {
                FocusDirection.Companion companion = FocusDirection.INSTANCE;
                if (!(FocusDirection.m3153equalsimpl0(i11, companion.m3162getLeftdhqQ8s()) && z && l0Var.f6185b.getIntValue() != 0) && ((!FocusDirection.m3153equalsimpl0(i11, companion.m3162getLeftdhqQ8s()) || z || l0Var.f6185b.getIntValue() == i10 - 1) && (!(FocusDirection.m3153equalsimpl0(i11, companion.m3165getRightdhqQ8s()) && z && l0Var.f6185b.getIntValue() != i10 - 1) && (!FocusDirection.m3153equalsimpl0(i11, companion.m3165getRightdhqQ8s()) || z || l0Var.f6185b.getIntValue() == 0)))) {
                    return false;
                }
                b(l0Var, i10, focusRequester, i11, z);
                return true;
            }
            if (!focusManager.mo3168moveFocus3ESFkO8(i11) && (focusState = (FocusState) aVar.invoke()) != null && focusState.getHasFocus()) {
                b(l0Var, i10, focusRequester, i11, z);
            }
        }
        return true;
    }

    public static final void b(l0 l0Var, int i10, FocusRequester focusRequester, int i11, boolean z) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i11, companion.m3162getLeftdhqQ8s())) {
            if (z) {
                l0Var.b(i10);
                focusRequester.requestFocus();
                return;
            } else {
                l0Var.a(i10);
                focusRequester.requestFocus();
                return;
            }
        }
        if (FocusDirection.m3153equalsimpl0(i11, companion.m3165getRightdhqQ8s())) {
            if (z) {
                l0Var.a(i10);
                focusRequester.requestFocus();
            } else {
                l0Var.b(i10);
                focusRequester.requestFocus();
            }
        }
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        KeyEvent keyEventM4390unboximpl = ((androidx.compose.ui.input.key.KeyEvent) obj).m4390unboximpl();
        boolean zA = false;
        if (!KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEventM4390unboximpl), KeyEventType.INSTANCE.m4399getKeyUpCS__XNY())) {
            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEventM4390unboximpl);
            Key.Companion companion = Key.INSTANCE;
            if (!Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion.m4108getBackEK5gGoQ())) {
                if (Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEventM4390unboximpl), companion.m4169getDirectionLeftEK5gGoQ())) {
                    zA = a(keyEventM4390unboximpl, this.f6122i, this.f6123l, this.f6124m, this.f6125n, this.f6126o, this.f6127p, FocusDirection.INSTANCE.m3162getLeftdhqQ8s());
                } else if (Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEventM4390unboximpl), companion.m4170getDirectionRightEK5gGoQ())) {
                    zA = a(keyEventM4390unboximpl, this.f6122i, this.f6123l, this.f6124m, this.f6125n, this.f6126o, this.f6127p, FocusDirection.INSTANCE.m3165getRightdhqQ8s());
                }
            }
        }
        return Boolean.valueOf(zA);
    }
}
