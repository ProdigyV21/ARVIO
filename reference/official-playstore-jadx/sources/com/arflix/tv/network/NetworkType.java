package com.arflix.tv.network;

import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/network/NetworkType;", "", "<init>", "(Ljava/lang/String;I)V", "WIFI", "ETHERNET", "CELLULAR", "OTHER", "NONE", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NetworkType {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ NetworkType[] $VALUES;
    public static final NetworkType WIFI = new NetworkType("WIFI", 0);
    public static final NetworkType ETHERNET = new NetworkType("ETHERNET", 1);
    public static final NetworkType CELLULAR = new NetworkType("CELLULAR", 2);
    public static final NetworkType OTHER = new NetworkType("OTHER", 3);
    public static final NetworkType NONE = new NetworkType("NONE", 4);

    private static final /* synthetic */ NetworkType[] $values() {
        return new NetworkType[]{WIFI, ETHERNET, CELLULAR, OTHER, NONE};
    }

    static {
        NetworkType[] networkTypeArr$values = $values();
        $VALUES = networkTypeArr$values;
        $ENTRIES = new g7.b(networkTypeArr$values);
    }

    private NetworkType(String str, int i10) {
    }

    public static g7.a<NetworkType> getEntries() {
        return $ENTRIES;
    }

    public static NetworkType valueOf(String str) {
        return (NetworkType) Enum.valueOf(NetworkType.class, str);
    }

    public static NetworkType[] values() {
        return (NetworkType[]) $VALUES.clone();
    }
}
