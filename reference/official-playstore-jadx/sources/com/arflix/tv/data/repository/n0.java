package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktWatchedShow;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.repository.TraktRepository;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7678i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Set f7679l;

    public /* synthetic */ n0(Set set, int i10) {
        this.f7678i = i10;
        this.f7679l = set;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zInvokeSuspend$lambda$2;
        switch (this.f7678i) {
            case 0:
                zInvokeSuspend$lambda$2 = TraktRepository.C10782.invokeSuspend$lambda$2(this.f7679l, (TraktWatchedShow) obj);
                break;
            default:
                zInvokeSuspend$lambda$2 = CatalogRepository.syncAddonCatalogs$lambda$3(this.f7679l, (CatalogConfig) obj);
                break;
        }
        return Boolean.valueOf(zInvokeSuspend$lambda$2);
    }
}
