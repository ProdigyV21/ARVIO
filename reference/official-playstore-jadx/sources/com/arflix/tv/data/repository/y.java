package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class y implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7716i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ LauncherContinueWatchingRepository f7717l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7718m;

    public /* synthetic */ y(LauncherContinueWatchingRepository launcherContinueWatchingRepository, Object obj, int i10) {
        this.f7716i = i10;
        this.f7717l = launcherContinueWatchingRepository;
        this.f7718m = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7716i) {
            case 0:
                return LauncherContinueWatchingRepository.deleteWatchNextPrograms$lambda$0(this.f7717l, (String[]) this.f7718m);
            case 1:
                return LauncherContinueWatchingRepository.findExistingChannelId$lambda$0(this.f7717l, (String[]) this.f7718m);
            default:
                return LauncherContinueWatchingRepository.C08672.invokeSuspend$lambda$0(this.f7717l, (List) this.f7718m);
        }
    }
}
