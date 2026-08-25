package com.arflix.tv.ui.screens.tv;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel", f = "TvViewModel.kt", l = {1955, 1959, 1960}, m = "resolvePlayableStreamUrl$app_playRelease", v = 2)
public final class TvViewModel$resolvePlayableStreamUrl$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$resolvePlayableStreamUrl$1(TvViewModel tvViewModel, d7.d<? super TvViewModel$resolvePlayableStreamUrl$1> dVar) {
        super(dVar);
        this.this$0 = tvViewModel;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.resolvePlayableStreamUrl$app_playRelease(null, null, false, 0, this);
    }
}
