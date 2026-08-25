package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/arflix/tv/data/model/CollectionGroupKind;", "", "<init>", "(Ljava/lang/String;I)V", "FEATURED", "SERVICE", "GENRE", "DECADE", "FRANCHISE", "NETWORK", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionGroupKind {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ CollectionGroupKind[] $VALUES;
    public static final CollectionGroupKind FEATURED = new CollectionGroupKind("FEATURED", 0);
    public static final CollectionGroupKind SERVICE = new CollectionGroupKind("SERVICE", 1);
    public static final CollectionGroupKind GENRE = new CollectionGroupKind("GENRE", 2);
    public static final CollectionGroupKind DECADE = new CollectionGroupKind("DECADE", 3);
    public static final CollectionGroupKind FRANCHISE = new CollectionGroupKind("FRANCHISE", 4);
    public static final CollectionGroupKind NETWORK = new CollectionGroupKind("NETWORK", 5);

    private static final /* synthetic */ CollectionGroupKind[] $values() {
        return new CollectionGroupKind[]{FEATURED, SERVICE, GENRE, DECADE, FRANCHISE, NETWORK};
    }

    static {
        CollectionGroupKind[] collectionGroupKindArr$values = $values();
        $VALUES = collectionGroupKindArr$values;
        $ENTRIES = new b(collectionGroupKindArr$values);
    }

    private CollectionGroupKind(String str, int i10) {
    }

    public static a<CollectionGroupKind> getEntries() {
        return $ENTRIES;
    }

    public static CollectionGroupKind valueOf(String str) {
        return (CollectionGroupKind) Enum.valueOf(CollectionGroupKind.class, str);
    }

    public static CollectionGroupKind[] values() {
        return (CollectionGroupKind[]) $VALUES.clone();
    }
}
