package androidx.compose.foundation;

import d7.d;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect", f = "AndroidOverscroll.android.kt", l = {223, 248}, m = "applyToFling-BMRW4eQ")
public final class AndroidEdgeEffectOverscrollEffect$applyToFling$1 extends f7.c {
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AndroidEdgeEffectOverscrollEffect this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidEdgeEffectOverscrollEffect$applyToFling$1(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, d<? super AndroidEdgeEffectOverscrollEffect$applyToFling$1> dVar) {
        super(dVar);
        this.this$0 = androidEdgeEffectOverscrollEffect;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo159applyToFlingBMRW4eQ(0L, null, this);
    }
}
