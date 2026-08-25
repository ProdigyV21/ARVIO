package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$6$1$1", f = "SearchOverlay.kt", l = {183}, m = "invokeSuspend", v = 2)
public final class SearchOverlayKt$SearchOverlay$6$1$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    public SearchOverlayKt$SearchOverlay$6$1$1(d7.d<? super SearchOverlayKt$SearchOverlay$6$1$1> dVar) {
        super(2, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(Offset offset) {
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SearchOverlayKt$SearchOverlay$6$1$1 searchOverlayKt$SearchOverlay$6$1$1 = new SearchOverlayKt$SearchOverlay$6$1$1(dVar);
        searchOverlayKt$SearchOverlay$6$1$1.L$0 = obj;
        return searchOverlayKt$SearchOverlay$6$1$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((SearchOverlayKt$SearchOverlay$6$1$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            n2 n2Var = new n2(5);
            this.L$0 = null;
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, n2Var, this, 7, null);
            e7.a aVar = e7.a.f15033i;
            if (objDetectTapGestures$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }
}
