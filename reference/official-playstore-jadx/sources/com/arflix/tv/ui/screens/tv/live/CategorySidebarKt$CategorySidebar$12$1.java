package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$12$1", f = "CategorySidebar.kt", l = {227}, m = "invokeSuspend", v = 2)
public final class CategorySidebarKt$CategorySidebar$12$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ int $focusSearchSignal;
    final /* synthetic */ FocusRequester $searchFocusRequester;
    final /* synthetic */ MutableState<Boolean> $userChoseSearch$delegate;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$12$1(int i10, MutableState<Boolean> mutableState, FocusRequester focusRequester, d7.d<? super CategorySidebarKt$CategorySidebar$12$1> dVar) {
        super(2, dVar);
        this.$focusSearchSignal = i10;
        this.$userChoseSearch$delegate = mutableState;
        this.$searchFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        CategorySidebarKt$CategorySidebar$12$1 categorySidebarKt$CategorySidebar$12$1 = new CategorySidebarKt$CategorySidebar$12$1(this.$focusSearchSignal, this.$userChoseSearch$delegate, this.$searchFocusRequester, dVar);
        categorySidebarKt$CategorySidebar$12$1.L$0 = obj;
        return categorySidebarKt$CategorySidebar$12$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0053 -> B:16:0x0056). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r8.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 != r3) goto L18
            int r1 = r8.I$1
            int r4 = r8.I$0
            java.lang.Object r5 = r8.L$1
            androidx.compose.ui.focus.FocusRequester r5 = (androidx.compose.ui.focus.FocusRequester) r5
            k2.c.G(r9)
            goto L56
        L18:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L20:
            k2.c.G(r9)
            com.arflix.tv.ui.screens.tv.live.LiveTvStartup r9 = com.arflix.tv.ui.screens.tv.live.LiveTvStartup.INSTANCE
            int r1 = r8.$focusSearchSignal
            boolean r9 = r9.shouldFocusSearch(r1)
            if (r9 == 0) goto L58
            androidx.compose.runtime.MutableState<java.lang.Boolean> r9 = r8.$userChoseSearch$delegate
            com.arflix.tv.ui.screens.tv.live.CategorySidebarKt.access$CategorySidebar$lambda$29(r9, r3)
            androidx.compose.ui.focus.FocusRequester r9 = r8.$searchFocusRequester
            r1 = 3
            r5 = r9
            r4 = r1
            r1 = r2
        L38:
            if (r1 >= r4) goto L58
            r5.requestFocus()     // Catch: java.lang.Throwable -> L3d
        L3d:
            r8.L$0 = r0
            r8.L$1 = r5
            r8.I$0 = r4
            r8.I$1 = r1
            r8.I$2 = r1
            r8.I$3 = r2
            r8.label = r3
            r6 = 50
            java.lang.Object r9 = ka.s0.a(r6, r8)
            e7.a r6 = e7.a.f15033i
            if (r9 != r6) goto L56
            return r6
        L56:
            int r1 = r1 + r3
            goto L38
        L58:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$12$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((CategorySidebarKt$CategorySidebar$12$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
