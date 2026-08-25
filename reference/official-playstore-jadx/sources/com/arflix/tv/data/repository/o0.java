package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbTvSeason;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7681i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Integer f7682l;

    public /* synthetic */ o0(int i10, Integer num) {
        this.f7681i = i10;
        this.f7682l = num;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zEstimateWatchedEpisodesBeforeCurrent$lambda$0;
        switch (this.f7681i) {
            case 0:
                zEstimateWatchedEpisodesBeforeCurrent$lambda$0 = TraktRepositoryKt.estimateWatchedEpisodesBeforeCurrent$lambda$0(this.f7682l, (TmdbTvSeason) obj);
                break;
            default:
                zEstimateWatchedEpisodesBeforeCurrent$lambda$0 = TraktRepositoryKt.estimateAiredEpisodeCount$lambda$0(this.f7682l, (TmdbTvSeason) obj);
                break;
        }
        return Boolean.valueOf(zEstimateWatchedEpisodesBeforeCurrent$lambda$0);
    }
}
