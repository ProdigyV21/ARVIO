package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.repository.IptvLoadProgress;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.arflix.tv.ui.screens.tv.TvViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11249i;

    public /* synthetic */ j0(int i10) {
        this.f11249i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11249i) {
            case 0:
                return ((IptvChannel) obj).getId();
            case 1:
                return Boolean.valueOf(TvViewModel.C16152.AnonymousClass1.invokeSuspend$lambda$0((String) obj));
            case 2:
                return Boolean.valueOf(TvViewModel.C16152.AnonymousClass1.invokeSuspend$lambda$1((String) obj));
            case 3:
                return TvViewModel$startCompleteEpgBackfill$2$snapshot$1.invokeSuspend$lambda$0((IptvLoadProgress) obj);
            case 4:
                return ((IptvChannel) obj).getId();
            case 5:
                return TvScreenKt.CategoryRail$lambda$0$0(((Integer) obj).intValue());
            case 6:
                return TvScreenKt.CategoryRail$lambda$1$0(((Integer) obj).intValue());
            case 7:
                return TvScreenKt.CategoryRail$lambda$3$0((String) obj);
            case 8:
                return TvScreenKt.CategoryRail$lambda$4$0((String) obj);
            case 9:
                return TvScreenKt.CategoryRail$lambda$5$0((String) obj);
            case 10:
                return TvScreenKt.CategoryRail$lambda$6$0((String) obj);
            case 11:
                return TvScreenKt.CategoryRail$lambda$7$0((String) obj);
            case 12:
                return TvScreenKt.GuidePanel$lambda$0$0(((Integer) obj).intValue());
            case 13:
                return TvViewModel.capLargeListGuideSnapshot$lambda$0((String) obj);
            case 14:
                return Boolean.valueOf(TvViewModel.capLargeListGuideSnapshot$lambda$1((String) obj));
            case 15:
                return Boolean.valueOf(TvViewModel.capLargeListGuideSnapshot$lambda$3((String) obj));
            case 16:
                return Boolean.valueOf(TvViewModel.markEpgLoading$lambda$0((String) obj));
            case 17:
                return Boolean.valueOf(TvViewModel.clearEpgLoading$lambda$0((String) obj));
            case 18:
                return TvViewModel.currentVisiblePlaylistGroups$lambda$2((x6.j0) obj);
            case 19:
                return TvViewModel.currentVisiblePlaylistGroups$lambda$5((String) obj);
            case 20:
                return Boolean.valueOf(TvViewModel.claimEpgNetworkRefresh$lambda$0$0((String) obj));
            case 21:
                return Boolean.valueOf(TvViewModel.deferCompleteEpgBackfill$lambda$0((String) obj));
            case 22:
                return Boolean.valueOf(TvViewModel.finishEpgAttempt$lambda$0((String) obj));
            default:
                return TvViewModelKt.syncSignature$lambda$0((IptvPlaylistEntry) obj);
        }
    }
}
