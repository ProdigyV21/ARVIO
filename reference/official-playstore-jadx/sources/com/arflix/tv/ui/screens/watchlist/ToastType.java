package com.arflix.tv.ui.screens.watchlist;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/ToastType;", "", "<init>", "(Ljava/lang/String;I)V", "SUCCESS", MediaError.ERROR_TYPE_ERROR, "INFO", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ToastType {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ ToastType[] $VALUES;
    public static final ToastType SUCCESS = new ToastType("SUCCESS", 0);
    public static final ToastType ERROR = new ToastType(MediaError.ERROR_TYPE_ERROR, 1);
    public static final ToastType INFO = new ToastType("INFO", 2);

    private static final /* synthetic */ ToastType[] $values() {
        return new ToastType[]{SUCCESS, ERROR, INFO};
    }

    static {
        ToastType[] toastTypeArr$values = $values();
        $VALUES = toastTypeArr$values;
        $ENTRIES = new g7.b(toastTypeArr$values);
    }

    private ToastType(String str, int i10) {
    }

    public static g7.a<ToastType> getEntries() {
        return $ENTRIES;
    }

    public static ToastType valueOf(String str) {
        return (ToastType) Enum.valueOf(ToastType.class, str);
    }

    public static ToastType[] values() {
        return (ToastType[]) $VALUES.clone();
    }
}
