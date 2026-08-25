package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes3.dex */
public final class d0 extends f7.j implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f5893i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f5894l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f5895m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d0(MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester, d7.d dVar) {
        super(1, dVar);
        this.f5893i = focusRequester;
        this.f5894l = mutableState;
        this.f5895m = mutableState2;
    }

    @Override // f7.a
    public final d7.d create(d7.d dVar) {
        return new d0(this.f5894l, this.f5895m, this.f5893i, dVar);
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        d0 d0Var = (d0) create((d7.d) obj);
        x6.t0 t0Var = x6.t0.f22605a;
        d0Var.invokeSuspend(t0Var);
        return t0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        FocusState focusState;
        k2.c.G(obj);
        if (!((Boolean) this.f5894l.getValue()).booleanValue() && (focusState = (FocusState) this.f5895m.getValue()) != null && focusState.isFocused()) {
            this.f5893i.requestFocus();
        }
        return x6.t0.f22605a;
    }
}
