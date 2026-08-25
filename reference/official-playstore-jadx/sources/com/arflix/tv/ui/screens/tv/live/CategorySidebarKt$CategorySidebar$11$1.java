package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$11$1", f = "CategorySidebar.kt", l = {218}, m = "invokeSuspend", v = 2)
public final class CategorySidebarKt$CategorySidebar$11$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $categoriesLoaded;
    final /* synthetic */ FocusRequester $firstCategoryFocusRequester;
    final /* synthetic */ int $focusSearchSignal;
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ FocusRequester $selectedCategoryFocusRequester;
    final /* synthetic */ MutableState<Boolean> $userChoseSearch$delegate;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CategorySidebarKt$CategorySidebar$11$1(boolean z, boolean z5, int i10, MutableState<Boolean> mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, d7.d<? super CategorySidebarKt$CategorySidebar$11$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$categoriesLoaded = z5;
        this.$focusSearchSignal = i10;
        this.$userChoseSearch$delegate = mutableState;
        this.$selectedCategoryFocusRequester = focusRequester;
        this.$firstCategoryFocusRequester = focusRequester2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        CategorySidebarKt$CategorySidebar$11$1 categorySidebarKt$CategorySidebar$11$1 = new CategorySidebarKt$CategorySidebar$11$1(this.$isTouchDevice, this.$categoriesLoaded, this.$focusSearchSignal, this.$userChoseSearch$delegate, this.$selectedCategoryFocusRequester, this.$firstCategoryFocusRequester, dVar);
        categorySidebarKt$CategorySidebar$11$1.L$0 = obj;
        return categorySidebarKt$CategorySidebar$11$1;
    }

    /* JADX WARN: Path cross not found for [B:42:0x0060, B:34:0x0072], limit reached: 44 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0090 -> B:40:0x0093). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r10.label
            r2 = 0
            r3 = 1
            x6.t0 r4 = x6.t0.f22605a
            if (r1 == 0) goto L27
            if (r1 != r3) goto L1f
            int r1 = r10.I$1
            int r5 = r10.I$0
            java.lang.Object r6 = r10.L$2
            androidx.compose.ui.focus.FocusRequester r6 = (androidx.compose.ui.focus.FocusRequester) r6
            java.lang.Object r7 = r10.L$1
            androidx.compose.ui.focus.FocusRequester r7 = (androidx.compose.ui.focus.FocusRequester) r7
            k2.c.G(r11)
            goto L93
        L1f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L27:
            k2.c.G(r11)
            boolean r11 = r10.$isTouchDevice
            if (r11 != 0) goto L95
            boolean r11 = r10.$categoriesLoaded
            if (r11 == 0) goto L95
            androidx.compose.runtime.MutableState<java.lang.Boolean> r11 = r10.$userChoseSearch$delegate
            boolean r11 = com.arflix.tv.ui.screens.tv.live.CategorySidebarKt.access$CategorySidebar$lambda$28(r11)
            if (r11 == 0) goto L3b
            goto L95
        L3b:
            com.arflix.tv.ui.screens.tv.live.LiveTvStartup r11 = com.arflix.tv.ui.screens.tv.live.LiveTvStartup.INSTANCE
            int r1 = r10.$focusSearchSignal
            boolean r11 = r11.shouldFocusSearch(r1)
            if (r11 == 0) goto L46
            return r4
        L46:
            androidx.compose.ui.focus.FocusRequester r11 = r10.$selectedCategoryFocusRequester
            androidx.compose.ui.focus.FocusRequester r1 = r10.$firstCategoryFocusRequester
            r5 = 25
            r7 = r11
            r6 = r1
            r1 = r2
        L4f:
            if (r1 >= r5) goto L95
            r7.requestFocus()     // Catch: java.lang.Throwable -> L56
            r8 = r4
            goto L5c
        L56:
            r11 = move-exception
            x6.c0 r8 = new x6.c0
            r8.<init>(r11)
        L5c:
            boolean r11 = r8 instanceof x6.c0
            if (r11 == 0) goto L72
            r6.requestFocus()     // Catch: java.lang.Throwable -> L65
            r8 = r4
            goto L6b
        L65:
            r11 = move-exception
            x6.c0 r8 = new x6.c0
            r8.<init>(r11)
        L6b:
            boolean r11 = r8 instanceof x6.c0
            if (r11 != 0) goto L70
            goto L72
        L70:
            r11 = r2
            goto L73
        L72:
            r11 = r3
        L73:
            if (r11 == 0) goto L76
            return r4
        L76:
            r10.L$0 = r0
            r10.L$1 = r7
            r10.L$2 = r6
            r10.I$0 = r5
            r10.I$1 = r1
            r10.I$2 = r1
            r10.I$3 = r2
            r10.I$4 = r11
            r10.label = r3
            r8 = 80
            java.lang.Object r11 = ka.s0.a(r8, r10)
            e7.a r8 = e7.a.f15033i
            if (r11 != r8) goto L93
            return r8
        L93:
            int r1 = r1 + r3
            goto L4f
        L95:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.CategorySidebarKt$CategorySidebar$11$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((CategorySidebarKt$CategorySidebar$11$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
