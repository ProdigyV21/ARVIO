package com.arflix.tv.ui.screens.crash;

import androidx.compose.ui.focus.FocusRequester;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.ui.screens.crash.CrashReportActivityKt$CrashReportScreen$1$1", f = "CrashReportActivity.kt", l = {}, m = "invokeSuspend", v = 2)
public final class CrashReportActivityKt$CrashReportScreen$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $isTv;
    final /* synthetic */ FocusRequester $restartFocusRequester;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CrashReportActivityKt$CrashReportScreen$1$1(boolean z, FocusRequester focusRequester, d7.d<? super CrashReportActivityKt$CrashReportScreen$1$1> dVar) {
        super(2, dVar);
        this.$isTv = z;
        this.$restartFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        CrashReportActivityKt$CrashReportScreen$1$1 crashReportActivityKt$CrashReportScreen$1$1 = new CrashReportActivityKt$CrashReportScreen$1$1(this.$isTv, this.$restartFocusRequester, dVar);
        crashReportActivityKt$CrashReportScreen$1$1.L$0 = obj;
        return crashReportActivityKt$CrashReportScreen$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$isTv) {
            try {
                this.$restartFocusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CrashReportActivityKt$CrashReportScreen$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
