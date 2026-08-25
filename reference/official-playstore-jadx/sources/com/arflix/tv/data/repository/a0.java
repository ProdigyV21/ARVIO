package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7619i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7620l;

    public /* synthetic */ a0(Object obj, int i10) {
        this.f7619i = i10;
        this.f7620l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7619i) {
            case 0:
                return LauncherContinueWatchingRepository.AnonymousClass2.invokeSuspend$lambda$0((LauncherContinueWatchingRepository) this.f7620l);
            case 1:
                return AuthRepository.supabase_delegate$lambda$0((AuthRepository) this.f7620l);
            case 2:
                return CatalogRepository.bundledPreinstalledCatalogIds_delegate$lambda$0((CatalogRepository) this.f7620l);
            default:
                return HttpLocalScraperRuntime.noRedirectClient_delegate$lambda$0((HttpLocalScraperRuntime) this.f7620l);
        }
    }
}
