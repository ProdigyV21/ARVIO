package com.arflix.tv.ui.screens.home;

import com.arflix.tv.ui.screens.home.HomeViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$loadCustomCatalogsIncrementally$1", f = "HomeViewModel.kt", l = {2983, 2985}, m = "invokeSuspend$publishMergedThrottled", v = 2)
public final class HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$1 extends f7.c {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    public HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$1(d7.d<? super HomeViewModel$loadCustomCatalogsIncrementally$1$publishMergedThrottled$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HomeViewModel.C13621.invokeSuspend$publishMergedThrottled(null, null, null, null, null, null, false, this);
    }
}
