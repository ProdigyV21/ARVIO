package com.arflix.tv.data.model;

import g7.a;
import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/arflix/tv/data/model/AddonInstallSource;", "", "<init>", "(Ljava/lang/String;I)V", "DIRECT_URL", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AddonInstallSource {
    private static final /* synthetic */ a $ENTRIES;
    private static final /* synthetic */ AddonInstallSource[] $VALUES;
    public static final AddonInstallSource DIRECT_URL = new AddonInstallSource("DIRECT_URL", 0);

    private static final /* synthetic */ AddonInstallSource[] $values() {
        return new AddonInstallSource[]{DIRECT_URL};
    }

    static {
        AddonInstallSource[] addonInstallSourceArr$values = $values();
        $VALUES = addonInstallSourceArr$values;
        $ENTRIES = new b(addonInstallSourceArr$values);
    }

    private AddonInstallSource(String str, int i10) {
    }

    public static a<AddonInstallSource> getEntries() {
        return $ENTRIES;
    }

    public static AddonInstallSource valueOf(String str) {
        return (AddonInstallSource) Enum.valueOf(AddonInstallSource.class, str);
    }

    public static AddonInstallSource[] values() {
        return (AddonInstallSource[]) $VALUES.clone();
    }
}
