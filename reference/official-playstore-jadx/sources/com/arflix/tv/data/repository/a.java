package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonCatalog;
import io.github.jan.supabase.gotrue.providers.builtin.Email;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7616i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7617l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7618m;

    public /* synthetic */ a(Object obj, Object obj2, int i10) {
        this.f7616i = i10;
        this.f7617l = obj;
        this.f7618m = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7616i) {
            case 0:
                return AuthRepository.signIn_0E7RQCE$lambda$2((String) this.f7617l, (String) this.f7618m, (Email.Config) obj);
            case 1:
                return ((CatalogRepository) this.f7617l).buildAddonCatalogConfig((Addon) this.f7618m, (AddonCatalog) obj);
            case 2:
                return Boolean.valueOf(IptvProviderOrderKt.changedPlaylistSourceIds$lambda$3((LinkedHashMap) this.f7617l, (LinkedHashMap) this.f7618m, (String) obj));
            default:
                return IptvRepository.fetchAndParseM3uOnce$lambda$0$1((Long) this.f7617l, (r7.l) this.f7618m, ((Long) obj).longValue());
        }
    }
}
