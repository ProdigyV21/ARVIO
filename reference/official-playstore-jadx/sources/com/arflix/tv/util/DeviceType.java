package com.arflix.tv.util;

import g7.b;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\n"}, d2 = {"Lcom/arflix/tv/util/DeviceType;", "", "<init>", "(Ljava/lang/String;I)V", "TV", "TABLET", "PHONE", "isTouchDevice", "", "isMobile", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DeviceType {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ DeviceType[] $VALUES;
    public static final DeviceType TV = new DeviceType("TV", 0);
    public static final DeviceType TABLET = new DeviceType("TABLET", 1);
    public static final DeviceType PHONE = new DeviceType("PHONE", 2);

    private static final /* synthetic */ DeviceType[] $values() {
        return new DeviceType[]{TV, TABLET, PHONE};
    }

    static {
        DeviceType[] deviceTypeArr$values = $values();
        $VALUES = deviceTypeArr$values;
        $ENTRIES = new b(deviceTypeArr$values);
    }

    private DeviceType(String str, int i10) {
    }

    public static g7.a<DeviceType> getEntries() {
        return $ENTRIES;
    }

    public static DeviceType valueOf(String str) {
        return (DeviceType) Enum.valueOf(DeviceType.class, str);
    }

    public static DeviceType[] values() {
        return (DeviceType[]) $VALUES.clone();
    }

    public final boolean isMobile() {
        return isTouchDevice();
    }

    public final boolean isTouchDevice() {
        return this == PHONE || this == TABLET;
    }
}
