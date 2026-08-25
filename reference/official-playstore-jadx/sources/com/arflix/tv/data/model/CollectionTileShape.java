package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/model/CollectionTileShape;", "", "<init>", "(Ljava/lang/String;I)V", "LANDSCAPE", "POSTER", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionTileShape {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ CollectionTileShape[] $VALUES;
    public static final CollectionTileShape LANDSCAPE = new CollectionTileShape("LANDSCAPE", 0);
    public static final CollectionTileShape POSTER = new CollectionTileShape("POSTER", 1);

    private static final /* synthetic */ CollectionTileShape[] $values() {
        return new CollectionTileShape[]{LANDSCAPE, POSTER};
    }

    static {
        CollectionTileShape[] collectionTileShapeArr$values = $values();
        $VALUES = collectionTileShapeArr$values;
        $ENTRIES = new b(collectionTileShapeArr$values);
    }

    private CollectionTileShape(String str, int i10) {
    }

    public static a<CollectionTileShape> getEntries() {
        return $ENTRIES;
    }

    public static CollectionTileShape valueOf(String str) {
        return (CollectionTileShape) Enum.valueOf(CollectionTileShape.class, str);
    }

    public static CollectionTileShape[] values() {
        return (CollectionTileShape[]) $VALUES.clone();
    }
}
