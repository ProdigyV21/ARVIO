package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/model/AddonType;", "", "<init>", "(Ljava/lang/String;I)V", "OFFICIAL", "COMMUNITY", "SUBTITLE", "METADATA", "CUSTOM", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AddonType {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ AddonType[] $VALUES;
    public static final AddonType OFFICIAL = new AddonType("OFFICIAL", 0);
    public static final AddonType COMMUNITY = new AddonType("COMMUNITY", 1);
    public static final AddonType SUBTITLE = new AddonType("SUBTITLE", 2);
    public static final AddonType METADATA = new AddonType("METADATA", 3);
    public static final AddonType CUSTOM = new AddonType("CUSTOM", 4);

    private static final /* synthetic */ AddonType[] $values() {
        return new AddonType[]{OFFICIAL, COMMUNITY, SUBTITLE, METADATA, CUSTOM};
    }

    static {
        AddonType[] addonTypeArr$values = $values();
        $VALUES = addonTypeArr$values;
        $ENTRIES = new b(addonTypeArr$values);
    }

    private AddonType(String str, int i10) {
    }

    public static a<AddonType> getEntries() {
        return $ENTRIES;
    }

    public static AddonType valueOf(String str) {
        return (AddonType) Enum.valueOf(AddonType.class, str);
    }

    public static AddonType[] values() {
        return (AddonType[]) $VALUES.clone();
    }
}
