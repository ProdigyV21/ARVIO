package com.arflix.tv.ui.screens.plugin;

import android.os.Build;
import android.view.Window;
import androidx.core.view.r3;
import androidx.core.view.s3;
import androidx.core.view.t3;
import androidx.core.view.x0;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginScreenKt$HideDialogSystemBars$1$1", f = "PluginScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PluginScreenKt$HideDialogSystemBars$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $isTv;
    final /* synthetic */ Window $window;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginScreenKt$HideDialogSystemBars$1$1(Window window, boolean z, d7.d<? super PluginScreenKt$HideDialogSystemBars$1$1> dVar) {
        super(2, dVar);
        this.$window = window;
        this.$isTv = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new PluginScreenKt$HideDialogSystemBars$1$1(this.$window, this.$isTv, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        Window window = this.$window;
        if (window != null && this.$isTv) {
            x0 x0Var = new x0(window.getDecorView());
            int i10 = Build.VERSION.SDK_INT;
            k2.c t3Var = i10 >= 30 ? new t3(window, x0Var) : i10 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var);
            t3Var.u();
            t3Var.D();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((PluginScreenKt$HideDialogSystemBars$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
