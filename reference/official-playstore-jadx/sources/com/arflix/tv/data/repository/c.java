package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.CatalogConfig;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7628i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Map f7629l;

    public /* synthetic */ c(int i10, Map map) {
        this.f7628i = i10;
        this.f7629l = map;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7628i) {
            case 0:
                return Boolean.valueOf(CatalogRepository.syncHomeServerCatalogs$lambda$8(this.f7629l, (CatalogConfig) obj));
            case 1:
                return MediaRepository.parseMdblistJson$lambda$1$0(this.f7629l, (String) obj);
            default:
                return MediaRepository.parseMdblistJson$lambda$1$1(this.f7629l, (String) obj);
        }
    }
}
