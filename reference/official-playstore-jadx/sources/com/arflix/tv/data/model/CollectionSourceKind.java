package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/model/CollectionSourceKind;", "", "<init>", "(Ljava/lang/String;I)V", "ADDON_CATALOG", "TMDB_GENRE", "TMDB_PERSON", "TMDB_COLLECTION", "TMDB_KEYWORD", "TMDB_WATCH_PROVIDER", "CURATED_IDS", "MDBLIST_PUBLIC", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionSourceKind {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ CollectionSourceKind[] $VALUES;
    public static final CollectionSourceKind ADDON_CATALOG = new CollectionSourceKind("ADDON_CATALOG", 0);
    public static final CollectionSourceKind TMDB_GENRE = new CollectionSourceKind("TMDB_GENRE", 1);
    public static final CollectionSourceKind TMDB_PERSON = new CollectionSourceKind("TMDB_PERSON", 2);
    public static final CollectionSourceKind TMDB_COLLECTION = new CollectionSourceKind("TMDB_COLLECTION", 3);
    public static final CollectionSourceKind TMDB_KEYWORD = new CollectionSourceKind("TMDB_KEYWORD", 4);
    public static final CollectionSourceKind TMDB_WATCH_PROVIDER = new CollectionSourceKind("TMDB_WATCH_PROVIDER", 5);
    public static final CollectionSourceKind CURATED_IDS = new CollectionSourceKind("CURATED_IDS", 6);
    public static final CollectionSourceKind MDBLIST_PUBLIC = new CollectionSourceKind("MDBLIST_PUBLIC", 7);

    private static final /* synthetic */ CollectionSourceKind[] $values() {
        return new CollectionSourceKind[]{ADDON_CATALOG, TMDB_GENRE, TMDB_PERSON, TMDB_COLLECTION, TMDB_KEYWORD, TMDB_WATCH_PROVIDER, CURATED_IDS, MDBLIST_PUBLIC};
    }

    static {
        CollectionSourceKind[] collectionSourceKindArr$values = $values();
        $VALUES = collectionSourceKindArr$values;
        $ENTRIES = new b(collectionSourceKindArr$values);
    }

    private CollectionSourceKind(String str, int i10) {
    }

    public static a<CollectionSourceKind> getEntries() {
        return $ENTRIES;
    }

    public static CollectionSourceKind valueOf(String str) {
        return (CollectionSourceKind) Enum.valueOf(CollectionSourceKind.class, str);
    }

    public static CollectionSourceKind[] values() {
        return (CollectionSourceKind[]) $VALUES.clone();
    }
}
