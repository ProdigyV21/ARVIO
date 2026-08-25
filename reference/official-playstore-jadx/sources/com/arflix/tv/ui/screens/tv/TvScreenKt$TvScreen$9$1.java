package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.google.android.gms.cast.MediaError;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$9$1", f = "TvScreen.kt", l = {MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$9$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ long $focusRecoveryDelayMs;
    final /* synthetic */ FocusRequester $rootFocusRequester;
    final /* synthetic */ MutableState<Boolean> $rootHasFocus$delegate;
    final /* synthetic */ MutableState<Boolean> $showGroupContextMenu$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$9$1(long j10, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, FocusRequester focusRequester, d7.d<? super TvScreenKt$TvScreen$9$1> dVar) {
        super(2, dVar);
        this.$focusRecoveryDelayMs = j10;
        this.$rootHasFocus$delegate = mutableState;
        this.$showGroupContextMenu$delegate = mutableState2;
        this.$rootFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TvScreenKt$TvScreen$9$1 tvScreenKt$TvScreen$9$1 = new TvScreenKt$TvScreen$9$1(this.$focusRecoveryDelayMs, this.$rootHasFocus$delegate, this.$showGroupContextMenu$delegate, this.$rootFocusRequester, dVar);
        tvScreenKt$TvScreen$9$1.L$0 = obj;
        return tvScreenKt$TvScreen$9$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!TvScreenKt.TvScreen$lambda$54(this.$rootHasFocus$delegate) && !TvScreenKt.TvScreen$lambda$26(this.$showGroupContextMenu$delegate)) {
                long j10 = this.$focusRecoveryDelayMs;
                this.L$0 = k0Var;
                this.label = 1;
                Object objA = s0.a(j10, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!TvScreenKt.TvScreen$lambda$54(this.$rootHasFocus$delegate) && !TvScreenKt.TvScreen$lambda$26(this.$showGroupContextMenu$delegate)) {
            try {
                this.$rootFocusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$9$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
