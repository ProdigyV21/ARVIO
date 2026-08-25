package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7637i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ StreamRepository f7638l;

    public /* synthetic */ e0(StreamRepository streamRepository, int i10) {
        this.f7637i = i10;
        this.f7638l = streamRepository;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7637i) {
            case 0:
                return Boolean.valueOf(this.f7638l.canPrewarmWithoutSideEffects((StreamSource) obj));
            case 1:
                return StreamRepository$resolveHubCloudChain$2$1$1.invokeSuspend$lambda$3(this.f7638l, (kotlin.text.i) obj);
            default:
                return StreamRepository.unwrapEmbeddedLinkParam$lambda$1(this.f7638l, (String) obj);
        }
    }
}
