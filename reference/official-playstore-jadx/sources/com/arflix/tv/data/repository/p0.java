package com.arflix.tv.data.repository;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7689i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7690l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7691m;

    public /* synthetic */ p0(int i10, Object obj, int i11) {
        this.f7689i = i11;
        this.f7690l = i10;
        this.f7691m = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7689i) {
            case 0:
                return Boolean.valueOf(WatchlistRepository.addToWatchlist$lambda$0(this.f7690l, (LocalWatchlistItem) this.f7691m, (LocalWatchlistItem) obj));
            case 1:
                return Boolean.valueOf(WatchlistRepository.removeFromWatchlist$lambda$0(this.f7690l, (String) this.f7691m, (LocalWatchlistItem) obj));
            default:
                return TelegramSettingsScreenKt.CodeContent$lambda$3$2$0(this.f7690l, (MutableState) this.f7691m, (String) obj);
        }
    }
}
