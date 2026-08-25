package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/model/CatalogSourceType;", "", "<init>", "(Ljava/lang/String;I)V", "PREINSTALLED", "TRAKT", "MDBLIST", "ADDON", "HOME_SERVER", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CatalogSourceType {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ CatalogSourceType[] $VALUES;
    public static final CatalogSourceType PREINSTALLED = new CatalogSourceType("PREINSTALLED", 0);
    public static final CatalogSourceType TRAKT = new CatalogSourceType("TRAKT", 1);
    public static final CatalogSourceType MDBLIST = new CatalogSourceType("MDBLIST", 2);
    public static final CatalogSourceType ADDON = new CatalogSourceType("ADDON", 3);
    public static final CatalogSourceType HOME_SERVER = new CatalogSourceType("HOME_SERVER", 4);

    private static final /* synthetic */ CatalogSourceType[] $values() {
        return new CatalogSourceType[]{PREINSTALLED, TRAKT, MDBLIST, ADDON, HOME_SERVER};
    }

    static {
        CatalogSourceType[] catalogSourceTypeArr$values = $values();
        $VALUES = catalogSourceTypeArr$values;
        $ENTRIES = new b(catalogSourceTypeArr$values);
    }

    private CatalogSourceType(String str, int i10) {
    }

    public static a<CatalogSourceType> getEntries() {
        return $ENTRIES;
    }

    public static CatalogSourceType valueOf(String str) {
        return (CatalogSourceType) Enum.valueOf(CatalogSourceType.class, str);
    }

    public static CatalogSourceType[] values() {
        return (CatalogSourceType[]) $VALUES.clone();
    }
}
