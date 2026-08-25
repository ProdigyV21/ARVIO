package com.arflix.tv.data.model;

import com.arflix.tv.data.repository.HomeServerRepository;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"effectivePackId", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "getEffectivePackId", "(Lcom/arflix/tv/data/model/CatalogConfig;)Ljava/lang/String;", "effectivePackName", "getEffectivePackName", "isBulkDeletablePack", "", "(Lcom/arflix/tv/data/model/CatalogConfig;)Z", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CatalogModelsKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CatalogSourceType.values().length];
            try {
                iArr[CatalogSourceType.PREINSTALLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CatalogSourceType.ADDON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CatalogSourceType.TRAKT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CatalogSourceType.MDBLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CatalogSourceType.HOME_SERVER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String getEffectivePackId(CatalogConfig catalogConfig) {
        String packId = catalogConfig.getPackId();
        if (packId != null) {
            return packId;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[catalogConfig.getSourceType().ordinal()];
        if (i10 == 1) {
            return "system";
        }
        if (i10 == 2) {
            return "addon";
        }
        if (i10 == 3) {
            return "trakt";
        }
        if (i10 == 4) {
            return "mdblist";
        }
        if (i10 == 5) {
            return HomeServerRepository.ADDON_ID;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final String getEffectivePackName(CatalogConfig catalogConfig) {
        String packName = catalogConfig.getPackName();
        if (packName != null) {
            return packName;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[catalogConfig.getSourceType().ordinal()];
        if (i10 == 1) {
            return "System Catalogs";
        }
        if (i10 == 2) {
            return "Addon Catalogs";
        }
        if (i10 == 3) {
            return "Trakt Catalogs";
        }
        if (i10 == 4) {
            return "MDBlist Catalogs";
        }
        if (i10 == 5) {
            return "Home Server Catalogs";
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final boolean isBulkDeletablePack(CatalogConfig catalogConfig) {
        return (catalogConfig.getPackId() == null || p.a(catalogConfig.getPackId(), "system") || p.a(catalogConfig.getPackId(), "addon") || p.a(catalogConfig.getPackId(), "trakt") || p.a(catalogConfig.getPackId(), "mdblist") || p.a(catalogConfig.getPackId(), HomeServerRepository.ADDON_ID) || p.a(catalogConfig.getPackId(), "individual")) ? false : true;
    }
}
