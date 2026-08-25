package com.arflix.tv.ui.screens.search;

import android.os.SystemClock;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.ui.focus.FocusRequester;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchScreenKt$SearchScreen$12$1", f = "SearchScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SearchScreenKt$SearchScreen$12$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ FocusRequester $searchFocusRequester;
    final /* synthetic */ MutableLongState $suppressSelectUntilMs$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchScreenKt$SearchScreen$12$1(boolean z, FocusRequester focusRequester, MutableLongState mutableLongState, d7.d<? super SearchScreenKt$SearchScreen$12$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$searchFocusRequester = focusRequester;
        this.$suppressSelectUntilMs$delegate = mutableLongState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SearchScreenKt$SearchScreen$12$1 searchScreenKt$SearchScreen$12$1 = new SearchScreenKt$SearchScreen$12$1(this.$isTouchDevice, this.$searchFocusRequester, this.$suppressSelectUntilMs$delegate, dVar);
        searchScreenKt$SearchScreen$12$1.L$0 = obj;
        return searchScreenKt$SearchScreen$12$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (!this.$isTouchDevice) {
            try {
                this.$searchFocusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        this.$suppressSelectUntilMs$delegate.setLongValue(SystemClock.elapsedRealtime() + 150);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchScreenKt$SearchScreen$12$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
