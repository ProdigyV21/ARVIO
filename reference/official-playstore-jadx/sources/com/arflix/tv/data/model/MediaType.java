package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/model/MediaType;", "", "<init>", "(Ljava/lang/String;I)V", "MOVIE", "TV", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MediaType {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ MediaType[] $VALUES;
    public static final MediaType MOVIE = new MediaType("MOVIE", 0);
    public static final MediaType TV = new MediaType("TV", 1);

    private static final /* synthetic */ MediaType[] $values() {
        return new MediaType[]{MOVIE, TV};
    }

    static {
        MediaType[] mediaTypeArr$values = $values();
        $VALUES = mediaTypeArr$values;
        $ENTRIES = new b(mediaTypeArr$values);
    }

    private MediaType(String str, int i10) {
    }

    public static a<MediaType> getEntries() {
        return $ENTRIES;
    }

    public static MediaType valueOf(String str) {
        return (MediaType) Enum.valueOf(MediaType.class, str);
    }

    public static MediaType[] values() {
        return (MediaType[]) $VALUES.clone();
    }
}
