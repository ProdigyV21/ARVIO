package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.player.SubtitleSyncMatcher;
import com.arflix.tv.ui.screens.tv.TvViewModel;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class u implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7707i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f7708l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f7709m;

    public /* synthetic */ u(long j10, long j11, int i10) {
        this.f7707i = i10;
        this.f7708l = j10;
        this.f7709m = j11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zHasEnoughCatchupHistory$lambda$0;
        switch (this.f7707i) {
            case 0:
                zHasEnoughCatchupHistory$lambda$0 = IptvRepository.hasEnoughCatchupHistory$lambda$0(this.f7708l, this.f7709m, (IptvProgram) obj);
                break;
            case 1:
                zHasEnoughCatchupHistory$lambda$0 = SubtitleSyncMatcher.score$lambda$0(this.f7708l, this.f7709m, (SubtitleSyncMatcher.TimedCue) obj);
                break;
            default:
                zHasEnoughCatchupHistory$lambda$0 = TvViewModel.hasRecentCatchupHistory$lambda$0(this.f7708l, this.f7709m, (IptvProgram) obj);
                break;
        }
        return Boolean.valueOf(zHasEnoughCatchupHistory$lambda$0);
    }
}
