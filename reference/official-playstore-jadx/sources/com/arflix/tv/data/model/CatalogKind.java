package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/model/CatalogKind;", "", "<init>", "(Ljava/lang/String;I)V", "STANDARD", "COLLECTION", "COLLECTION_RAIL", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CatalogKind {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ CatalogKind[] $VALUES;
    public static final CatalogKind STANDARD = new CatalogKind("STANDARD", 0);
    public static final CatalogKind COLLECTION = new CatalogKind("COLLECTION", 1);
    public static final CatalogKind COLLECTION_RAIL = new CatalogKind("COLLECTION_RAIL", 2);

    private static final /* synthetic */ CatalogKind[] $values() {
        return new CatalogKind[]{STANDARD, COLLECTION, COLLECTION_RAIL};
    }

    static {
        CatalogKind[] catalogKindArr$values = $values();
        $VALUES = catalogKindArr$values;
        $ENTRIES = new b(catalogKindArr$values);
    }

    private CatalogKind(String str, int i10) {
    }

    public static a<CatalogKind> getEntries() {
        return $ENTRIES;
    }

    public static CatalogKind valueOf(String str) {
        return (CatalogKind) Enum.valueOf(CatalogKind.class, str);
    }

    public static CatalogKind[] values() {
        return (CatalogKind[]) $VALUES.clone();
    }
}
