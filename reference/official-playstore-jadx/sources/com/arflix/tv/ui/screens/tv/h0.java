package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.ui.screens.tv.TvViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11238i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TvViewModel f11239l;

    public /* synthetic */ h0(TvViewModel tvViewModel, int i10) {
        this.f11238i = i10;
        this.f11239l = tvViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11238i) {
            case 0:
                return Boolean.valueOf(TvViewModel.C16051.invokeSuspend$lambda$5(this.f11239l, (String) obj));
            case 1:
                return TvScreenKt.TvScreen$lambda$118$2$0$3$0(this.f11239l, (String) obj);
            case 2:
                return TvScreenKt.TvScreen$lambda$118$2$0$4$0(this.f11239l, (String) obj);
            case 3:
                return TvScreenKt.TvScreen$lambda$118$2$0$5$0(this.f11239l, (String) obj);
            case 4:
                return TvScreenKt.TvScreen$lambda$118$2$0$6$0(this.f11239l, (String) obj);
            case 5:
                return TvScreenKt.TvScreen$lambda$118$2$0$7$0(this.f11239l, (String) obj);
            case 6:
                return TvViewModel.refresh$lambda$0$0(this.f11239l, (Throwable) obj);
            case 7:
                return TvViewModel.warmXtreamVodCache$lambda$0$0(this.f11239l, (Throwable) obj);
            default:
                return Boolean.valueOf(TvViewModel.claimEpgNetworkRefresh$lambda$0$1(this.f11239l, (String) obj));
        }
    }
}
