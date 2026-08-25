package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.ProfileRepository;
import io.github.jan.supabase.gotrue.providers.builtin.IDToken;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7714i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f7715l;

    public /* synthetic */ x(String str, int i10) {
        this.f7714i = i10;
        this.f7715l = str;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7714i) {
            case 0:
                return Boolean.valueOf(IptvRepository.C08422.invokeSuspend$lambda$0(this.f7715l, (String) obj));
            case 1:
                return Boolean.valueOf(ProfileRepository.C09512.invokeSuspend$lambda$0(this.f7715l, (Profile) obj));
            case 2:
                return AuthRepository.handleGoogleSignInResult_gIAlu_s$lambda$0(this.f7715l, (IDToken.Config) obj);
            case 3:
                return Boolean.valueOf(CatalogRepository.removeCatalogPack_gIAlu_s$lambda$0(this.f7715l, (CatalogConfig) obj));
            case 4:
                return Boolean.valueOf(CatalogRepository.removeCustomCatalog_gIAlu_s$lambda$1(this.f7715l, (CatalogConfig) obj));
            case 5:
                return IptvUrlNormalizerKt.decodeLegacyHttpUrl$lambda$1(this.f7715l, ((Integer) obj).intValue());
            case 6:
                return Boolean.valueOf(StreamRepository.installHttpLocalScraperCandidate$lambda$0(this.f7715l, (Addon) obj));
            default:
                return StreamRepository.pickBestM3uUrl$lambda$3(this.f7715l, (String) obj);
        }
    }
}
