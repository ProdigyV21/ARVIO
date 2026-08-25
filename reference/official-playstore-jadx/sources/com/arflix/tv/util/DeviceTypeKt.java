package com.arflix.tv.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import c1.e;
import com.arflix.tv.ui.screens.player.d0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\t\u001a\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u000b\u0010\f\"\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011\"\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018\"\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018\"\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018\"\u0014\u0010\u001f\u001a\u00020\u00058\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001f\u0010 \"\u0014\u0010!\u001a\u00020\u00058\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010 \"\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u00148\u0006¢\u0006\f\n\u0004\b\"\u0010\u0016\u001a\u0004\b#\u0010\u0018¨\u0006$"}, d2 = {"Landroid/content/Context;", "context", "", "deviceHasTouchScreen", "(Landroid/content/Context;)Z", "", "value", "Lx6/t0;", "setDeviceModeOverrideCache", "(Landroid/content/Context;Ljava/lang/String;)V", "Lcom/arflix/tv/util/DeviceType;", "detectDeviceType", "(Landroid/content/Context;)Lcom/arflix/tv/util/DeviceType;", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalDeviceType", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalDeviceType", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalHasTouchScreen", "getLocalHasTouchScreen", "Lc1/e;", "DEVICE_MODE_OVERRIDE_KEY", "Lc1/e;", "getDEVICE_MODE_OVERRIDE_KEY", "()Lc1/e;", "SKIP_PROFILE_SELECTION_KEY", "getSKIP_PROFILE_SELECTION_KEY", "OLED_BLACK_BACKGROUND_KEY", "getOLED_BLACK_BACKGROUND_KEY", "ACCENT_COLOR_KEY", "getACCENT_COLOR_KEY", "DEVICE_MODE_PREFS", "Ljava/lang/String;", "DEVICE_MODE_PREF_KEY", "QUALITY_FILTERS_KEY", "getQUALITY_FILTERS_KEY", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DeviceTypeKt {
    private static final String DEVICE_MODE_PREFS = "arvio_device_mode_cache";
    private static final ProvidableCompositionLocal<DeviceType> LocalDeviceType = CompositionLocalKt.compositionLocalOf$default(null, new d0(20), 1, null);
    private static final ProvidableCompositionLocal<Boolean> LocalHasTouchScreen = CompositionLocalKt.compositionLocalOf$default(null, new d0(21), 1, null);
    private static final String DEVICE_MODE_PREF_KEY = "device_mode_override";
    private static final e DEVICE_MODE_OVERRIDE_KEY = new e(DEVICE_MODE_PREF_KEY);
    private static final e SKIP_PROFILE_SELECTION_KEY = new e("skip_profile_selection");
    private static final e OLED_BLACK_BACKGROUND_KEY = new e("oled_black_background");
    private static final e ACCENT_COLOR_KEY = new e("accent_color");
    private static final e QUALITY_FILTERS_KEY = new e("quality_filters");

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LocalHasTouchScreen$lambda$0() {
        return true;
    }

    public static final DeviceType detectDeviceType(Context context) {
        String string = null;
        try {
            string = context.getApplicationContext().getSharedPreferences(DEVICE_MODE_PREFS, 0).getString(DEVICE_MODE_PREF_KEY, null);
        } catch (Exception unused) {
        }
        if (string != null) {
            int iHashCode = string.hashCode();
            if (iHashCode != -881377690) {
                if (iHashCode != 3714) {
                    if (iHashCode == 106642798 && string.equals("phone")) {
                        return DeviceType.PHONE;
                    }
                } else if (string.equals("tv")) {
                    return DeviceType.TV;
                }
            } else if (string.equals("tablet")) {
                return DeviceType.TABLET;
            }
        }
        PackageManager packageManager = context.getPackageManager();
        return (packageManager.hasSystemFeature("android.software.leanback") || packageManager.hasSystemFeature("android.hardware.type.television")) ? DeviceType.TV : !packageManager.hasSystemFeature("android.hardware.touchscreen") ? DeviceType.TV : context.getResources().getConfiguration().smallestScreenWidthDp >= 600 ? DeviceType.TABLET : DeviceType.PHONE;
    }

    public static final boolean deviceHasTouchScreen(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen");
    }

    public static final e getACCENT_COLOR_KEY() {
        return ACCENT_COLOR_KEY;
    }

    public static final e getDEVICE_MODE_OVERRIDE_KEY() {
        return DEVICE_MODE_OVERRIDE_KEY;
    }

    public static final ProvidableCompositionLocal<DeviceType> getLocalDeviceType() {
        return LocalDeviceType;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalHasTouchScreen() {
        return LocalHasTouchScreen;
    }

    public static final e getOLED_BLACK_BACKGROUND_KEY() {
        return OLED_BLACK_BACKGROUND_KEY;
    }

    public static final e getQUALITY_FILTERS_KEY() {
        return QUALITY_FILTERS_KEY;
    }

    public static final e getSKIP_PROFILE_SELECTION_KEY() {
        return SKIP_PROFILE_SELECTION_KEY;
    }

    public static final void setDeviceModeOverrideCache(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(DEVICE_MODE_PREFS, 0).edit();
        if (str == null) {
            editorEdit.remove(DEVICE_MODE_PREF_KEY);
        } else {
            editorEdit.putString(DEVICE_MODE_PREF_KEY, str);
        }
        editorEdit.apply();
    }
}
