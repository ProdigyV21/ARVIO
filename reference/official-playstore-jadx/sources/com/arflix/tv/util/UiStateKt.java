package com.arflix.tv.util;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"T", "Lcom/arflix/tv/util/Result;", "Lkotlin/Function0;", "Lx6/t0;", "retryAction", "Lcom/arflix/tv/util/UiState;", "toUiState", "(Lcom/arflix/tv/util/Result;Lr7/a;)Lcom/arflix/tv/util/UiState;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class UiStateKt {
    public static final <T> UiState<T> toUiState(Result<? extends T> result, r7.a<t0> aVar) {
        return UiState.INSTANCE.fromResult(result, aVar);
    }

    public static /* synthetic */ UiState toUiState$default(Result result, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = null;
        }
        return toUiState(result, aVar);
    }
}
