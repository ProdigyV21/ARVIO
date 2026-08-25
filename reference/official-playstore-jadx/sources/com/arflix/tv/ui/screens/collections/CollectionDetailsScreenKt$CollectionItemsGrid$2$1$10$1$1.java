package com.arflix.tv.ui.screens.collections;

import androidx.compose.ui.focus.FocusRequester;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1", f = "CollectionDetailsScreen.kt", l = {976}, m = "invokeSuspend", v = 2)
public final class CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ FocusRequester $itemFocusRequester;
    final /* synthetic */ r7.a<t0> $onClearPendingFocus;
    final /* synthetic */ int $pendingFocusIndex;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1(int i10, int i11, r7.a<t0> aVar, FocusRequester focusRequester, d7.d<? super CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1> dVar) {
        super(2, dVar);
        this.$pendingFocusIndex = i10;
        this.$index = i11;
        this.$onClearPendingFocus = aVar;
        this.$itemFocusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1 collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1 = new CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1(this.$pendingFocusIndex, this.$index, this.$onClearPendingFocus, this.$itemFocusRequester, dVar);
        collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1.L$0 = obj;
        return collectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(1:(1:5)(2:6|7))(4:8|(2:10|(1:12))|16|17)|13|19|14|15|16|17) */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r3.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            k2.c.G(r4)
            goto L2f
        Lf:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L17:
            k2.c.G(r4)
            int r4 = r3.$pendingFocusIndex
            int r1 = r3.$index
            if (r4 != r1) goto L39
            r3.L$0 = r0
            r3.label = r2
            r0 = 50
            java.lang.Object r4 = ka.s0.a(r0, r3)
            e7.a r0 = e7.a.f15033i
            if (r4 != r0) goto L2f
            return r0
        L2f:
            androidx.compose.ui.focus.FocusRequester r4 = r3.$itemFocusRequester
            r4.requestFocus()     // Catch: java.lang.Throwable -> L34
        L34:
            r7.a<x6.t0> r4 = r3.$onClearPendingFocus
            r4.invoke()
        L39:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CollectionDetailsScreenKt$CollectionItemsGrid$2$1$10$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
