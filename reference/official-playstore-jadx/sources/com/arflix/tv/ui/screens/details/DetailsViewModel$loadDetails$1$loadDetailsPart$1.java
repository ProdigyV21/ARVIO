package com.arflix.tv.ui.screens.details;

import com.arflix.tv.ui.screens.details.DetailsViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1", f = "DetailsViewModel.kt", l = {382}, m = "invokeSuspend$loadDetailsPart", v = 2)
public final class DetailsViewModel$loadDetails$1$loadDetailsPart$1<T> extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public DetailsViewModel$loadDetails$1$loadDetailsPart$1(d7.d<? super DetailsViewModel$loadDetails$1$loadDetailsPart$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DetailsViewModel.C13181.invokeSuspend$loadDetailsPart(null, null, null, this);
    }
}
