package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.PlayerViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9788i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9789l;

    public /* synthetic */ w1(String str, int i10) {
        this.f9788i = i10;
        this.f9789l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9788i) {
            case 0:
                return PlayerViewModel.AnonymousClass4.invokeSuspend$childFailed$lambda$11(this.f9789l, (Throwable) obj);
            default:
                return Boolean.valueOf(PlayerViewModel.C14371.invokeSuspend$lambda$0$0(this.f9789l, (PlayerViewModel.CachedSubMatch) obj));
        }
    }
}
