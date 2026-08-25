package com.arflix.tv.ui.components;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/components/SkeletonCardType;", "", "<init>", "(Ljava/lang/String;I)V", "POSTER", "MEDIA", "EPISODE", "CAST", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SkeletonCardType {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SkeletonCardType[] $VALUES;
    public static final SkeletonCardType POSTER = new SkeletonCardType("POSTER", 0);
    public static final SkeletonCardType MEDIA = new SkeletonCardType("MEDIA", 1);
    public static final SkeletonCardType EPISODE = new SkeletonCardType("EPISODE", 2);
    public static final SkeletonCardType CAST = new SkeletonCardType("CAST", 3);

    private static final /* synthetic */ SkeletonCardType[] $values() {
        return new SkeletonCardType[]{POSTER, MEDIA, EPISODE, CAST};
    }

    static {
        SkeletonCardType[] skeletonCardTypeArr$values = $values();
        $VALUES = skeletonCardTypeArr$values;
        $ENTRIES = new g7.b(skeletonCardTypeArr$values);
    }

    private SkeletonCardType(String str, int i10) {
    }

    public static g7.a<SkeletonCardType> getEntries() {
        return $ENTRIES;
    }

    public static SkeletonCardType valueOf(String str) {
        return (SkeletonCardType) Enum.valueOf(SkeletonCardType.class, str);
    }

    public static SkeletonCardType[] values() {
        return (SkeletonCardType[]) $VALUES.clone();
    }
}
