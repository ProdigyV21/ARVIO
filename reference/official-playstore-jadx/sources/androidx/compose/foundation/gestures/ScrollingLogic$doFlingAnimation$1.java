package androidx.compose.foundation.gestures;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@f7.e(c = "androidx.compose.foundation.gestures.ScrollingLogic", f = "Scrollable.kt", l = {831}, m = "doFlingAnimation-QWom1Mo")
public final class ScrollingLogic$doFlingAnimation$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$doFlingAnimation$1(ScrollingLogic scrollingLogic, d7.d<? super ScrollingLogic$doFlingAnimation$1> dVar) {
        super(dVar);
        this.this$0 = scrollingLogic;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m374doFlingAnimationQWom1Mo(0L, this);
    }
}
