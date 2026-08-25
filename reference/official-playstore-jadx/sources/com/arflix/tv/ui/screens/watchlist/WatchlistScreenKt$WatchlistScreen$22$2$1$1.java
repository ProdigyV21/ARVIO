package com.arflix.tv.ui.screens.watchlist;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class WatchlistScreenKt$WatchlistScreen$22$2$1$1 extends kotlin.jvm.internal.m implements r7.a<t0> {
    public WatchlistScreenKt$WatchlistScreen$22$2$1$1(Object obj) {
        super(0, 0, WatchlistViewModel.class, obj, "dismissToast", "dismissToast()V");
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m6472invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m6472invoke() {
        ((WatchlistViewModel) this.receiver).dismissToast();
    }
}
