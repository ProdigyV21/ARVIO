package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.MediaType;
import io.github.jan.supabase.gotrue.AuthConfig;
import j$.time.LocalDateTime;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7633i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7634l;

    public /* synthetic */ d0(Object obj, int i10) {
        this.f7633i = i10;
        this.f7634l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7633i) {
            case 0:
                return Boolean.valueOf(StreamRepository$addCustomAddon$2.invokeSuspend$lambda$0((Addon) this.f7634l, (Addon) obj));
            case 1:
                return AuthRepository.supabase_delegate$lambda$0$0$0((AuthRepository) this.f7634l, (AuthConfig) obj);
            case 2:
                return Boolean.valueOf(CatalogRepository.syncAddonCatalogs$lambda$6((LinkedHashMap) this.f7634l, (CatalogConfig) obj));
            case 3:
                return CatalogRepository.syncAddonCatalogs$lambda$2((CatalogRepository) this.f7634l, (Addon) obj);
            case 4:
                return IptvRepository.replaceDatePatternPlaceholders$lambda$0((LocalDateTime) this.f7634l, (kotlin.text.i) obj);
            case 5:
                return ((SportsRepository) this.f7634l).encodeStatusPart((String) obj);
            default:
                return Boolean.valueOf(TraktRepository.searchTmdbWatchlistMatch$lambda$0((MediaType) this.f7634l, (TmdbMediaItem) obj));
        }
    }
}
