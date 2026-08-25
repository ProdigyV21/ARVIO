package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.player.SubtitleSyncMatcher;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7646i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f7647l;

    public /* synthetic */ i(long j10, int i10) {
        this.f7646i = i10;
        this.f7647l = j10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7646i) {
            case 0:
                return Boolean.valueOf(IptvEpgIndex.buildNowNext$lambda$4(this.f7647l, (IptvProgram) obj));
            case 1:
                return Boolean.valueOf(IptvRepository.hasEnoughFutureGuide$lambda$1(this.f7647l, (IptvProgram) obj));
            case 2:
                return IptvRepository.replaceDurationScalePlaceholders$lambda$0(this.f7647l, (kotlin.text.i) obj);
            default:
                return SubtitleSyncMatcher.shiftTimestamps$lambda$0(this.f7647l, (kotlin.text.i) obj);
        }
    }
}
