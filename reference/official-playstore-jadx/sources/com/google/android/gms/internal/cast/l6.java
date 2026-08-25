package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Looper;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.internal.Logger;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class l6 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Logger f13416i = new Logger("FeatureUsageAnalytics");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f13417j = "22.2.0";
    public static l6 k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4 f13418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SharedPreferences f13419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f13420c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f13425h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashSet f13423f = new HashSet();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f13424g = new HashSet();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final androidx.loader.content.j f13422e = new androidx.loader.content.j(Looper.getMainLooper(), 2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g1 f13421d = new g1(this, 2);

    public l6(SharedPreferences sharedPreferences, z4 z4Var, String str) {
        this.f13419b = sharedPreferences;
        this.f13418a = z4Var;
        this.f13420c = str;
    }

    public static void a(m5 m5Var) {
        l6 l6Var;
        if (!z4.k || (l6Var = k) == null) {
            return;
        }
        SharedPreferences sharedPreferences = l6Var.f13419b;
        String string = Integer.toString(m5Var.f13472i);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String strC = androidx.compose.material3.d.C("feature_usage_timestamp_reported_feature_", string);
        if (!sharedPreferences.contains(strC)) {
            strC = androidx.compose.material3.d.C("feature_usage_timestamp_detected_feature_", string);
        }
        editorEdit.putLong(strC, System.currentTimeMillis()).apply();
        l6Var.f13423f.add(m5Var);
        l6Var.f13422e.post(l6Var.f13421d);
    }

    public static m5 c(String str) {
        m5 m5Var = m5.DEVELOPER_FEATURE_FLAG_UNKNOWN;
        switch (Integer.parseInt(str)) {
            case 0:
                return m5Var;
            case 1:
                return m5.CAF_CAST_BUTTON;
            case 2:
                return m5.CAF_EXPANDED_CONTROLLER;
            case 3:
                return m5.CAF_MINI_CONTROLLER;
            case 4:
                return m5.CAF_CONTAINER_CONTROLLER;
            case 5:
                return m5.CAST_CONTEXT;
            case 6:
                return m5.IMAGE_CACHE;
            case 7:
                return m5.IMAGE_PICKER;
            case 8:
                return m5.AD_BREAK_PARSER;
            case 9:
                return m5.UI_STYLE;
            case 10:
                return m5.HARDWARE_VOLUME_BUTTON;
            case 11:
                return m5.NON_CAST_DEVICE_PROVIDER;
            case 12:
                return m5.PAUSE_CONTROLLER;
            case 13:
                return m5.SEEK_CONTROLLER;
            case 14:
                return m5.STREAM_VOLUME;
            case 15:
                return m5.UI_MEDIA_CONTROLLER;
            case 16:
                return m5.PLAYBACK_RATE_CONTROLLER;
            case 17:
                return m5.PRECACHE;
            case 18:
                return m5.INSTRUCTIONS_VIEW;
            case 19:
                return m5.OPTION_SUSPEND_SESSIONS_WHEN_BACKGROUNDED;
            case 20:
                return m5.OPTION_STOP_RECEIVER_APPLICATION_WHEN_ENDING_SESSION;
            case 21:
                return m5.OPTION_DISABLE_DISCOVERY_AUTOSTART;
            case 22:
                return m5.OPTION_DISABLE_ANALYTICS_LOGGING;
            case 23:
                return m5.OPTION_PHYSICAL_VOLUME_BUTTONS_WILL_CONTROL_DEVICE_VOLUME;
            case 24:
                return m5.CAF_EXPANDED_CONTROLLER_HIDE_STREAM_POSITION_CONTROLS_FOR_LIVE_CONTENT;
            case 25:
                return m5.CAF_EXPANDED_CONTROLLER_WITH_LIVE_CONTENT;
            case 26:
                return m5.REMOTE_MEDIA_CLIENT_LOAD_MEDIA_WITH_OPTIONS;
            case 27:
                return m5.REMOTE_MEDIA_CLIENT_QUEUE_LOAD_ITEMS_WITH_OPTIONS;
            case 28:
                return m5.REMOTE_MEDIA_CLIENT_LOAD_MEDIA_WITH_LOAD_REQUEST_DATA;
            case 29:
                return m5.LAUNCH_OPTION_ANDROID_RECEIVER_COMPATIBLE;
            case 30:
                return m5.CAST_CONTEXT_SET_LAUNCH_CREDENTIALS_DATA;
            case 31:
                return m5.START_DISCOVERY_AFTER_FIRST_TAP_ON_CAST_BUTTON;
            case 32:
                return m5.CAST_UNAVAILABLE_BUTTON_VISIBLE;
            case 33:
                return m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG;
            case 34:
                return m5.CAST_CUSTOM_MEDIA_ROUTER_DIALOG;
            case 35:
                return m5.CAST_OUTPUT_SWITCHER_ENABLED;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                return m5.CAST_TRANSFER_TO_LOCAL_ENABLED;
            case 37:
                return m5.CAST_BUTTON_IS_TRIGGERED_DEFAULT_CAST_DIALOG_FALSE;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                return m5.CAST_BUTTON_DELEGATE;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                return m5.CAST_BUTTON_DELEGATE_PRESENT_LNA_PERMISSION_CUSTOM_DIALOG;
            case 40:
                return m5.CAST_BUTTON_DELEGATE_PRESENT_CAST_STATE_CUSTOM_DIALOG;
            case 41:
                return m5.CAST_TRANSFER_TO_LOCAL_USED;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                return m5.MEDIA_REQUEST_ITEM_MAP_HLS_SEGMENT_FORMAT_TO_STRING;
            case 43:
                return m5.MEDIA_REQUEST_ITEM_MAP_HLS_SEGMENT_FORMAT_STRING_TO_ENUM;
            case 44:
                return m5.HLS_SEGMENT_MAP_HLS_SEGMENT_FORMAT_TO_STRING;
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                return m5.HLS_SEGMENT_MAP_HLS_SEGMENT_FORMAT_STRING_TO_ENUM;
            case 46:
                return m5.HLS_VIDEO_SEGMENT_MAP_HLS_VIDEO_SEGMENT_FORMAT_TO_STRING;
            case 47:
                return m5.HLS_VIDEO_SEGMENT_MAP_HLS_VIDEO_SEGMENT_FORMAT_STRING_TO_ENUM;
            case 48:
                return m5.CAST_SLIDER_SET_AD_BLOCK_POSITIONS;
            case 49:
                return m5.CAF_NOTIFICATION_SERVICE;
            case 50:
                return m5.HARDWARE_VOLUME_BUTTON_PRESS;
            case 51:
                return m5.CAST_SDK_DEFAULT_DEVICE_DIALOG;
            case 52:
                return m5.CAST_SDK_CUSTOM_DEVICE_DIALOG;
            case 53:
                return m5.PERSISTENT_CAST_BUTTON_DISCOVERY_DISABLED_WITH_CONFLICT_TYPES;
            case 54:
                return m5.CAST_DEVICE_DIALOG_FACTORY_INSTANTIATED;
            case 55:
                return m5.CAF_MEDIA_NOTIFICATION_PROXY;
            case 56:
                return m5.REMOTE_CONNECTION_MANAGER_ACQUIRED;
            case 57:
                return m5.REMOTE_CONNECTION_CALLBACK_SET;
            default:
                return null;
        }
    }

    public final void b(HashSet hashSet) {
        if (hashSet.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.f13419b.edit();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) it.next());
        }
        editorEdit.apply();
    }
}
