package com.arflix.tv.data.repository;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7635i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HomeServerRepository f7636l;

    public /* synthetic */ e(HomeServerRepository homeServerRepository, int i10) {
        this.f7635i = i10;
        this.f7636l = homeServerRepository;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7635i) {
            case 0:
                return HomeServerRepository.parsePlexResourcesXml$lambda$0(this.f7636l, (kotlin.text.i) obj);
            case 1:
                return HomeServerRepository.sourceCacheKey$lambda$0(this.f7636l, (HomeServerConnection) obj);
            default:
                return HomeServerRepository.parsePlexResourcesXml$lambda$0$1(this.f7636l, (kotlin.text.i) obj);
        }
    }
}
