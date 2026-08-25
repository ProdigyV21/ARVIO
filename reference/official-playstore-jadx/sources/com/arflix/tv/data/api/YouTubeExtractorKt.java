package com.arflix.tv.data.api;

import com.google.android.gms.cast.CredentialsData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.z;
import kotlin.text.t;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a&\u0010\u000e\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\n*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\u001a*\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n0\f*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\u001a\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\u001a#\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0014\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"TAG", "", "EXTRACTOR_TIMEOUT_MS", "", "URL_CACHE_TTL_MS", "WATCH_CONFIG_TTL_MS", "FALLBACK_INNERTUBE_KEY", "DEFAULT_USER_AGENT", "PREFERRED_SEPARATE_CLIENT", "DEFAULT_HEADERS", "", "CLIENTS", "", "Lcom/arflix/tv/data/api/YouTubeClient;", "mapValue", "key", "listMapValue", "stringValue", "numberValue", "", "(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Double;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class YouTubeExtractorKt {
    private static final long EXTRACTOR_TIMEOUT_MS = 30000;
    private static final String FALLBACK_INNERTUBE_KEY = "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8";
    private static final String TAG = "InAppYouTubeExtractor";
    private static final long URL_CACHE_TTL_MS = 300000;
    private static final long WATCH_CONFIG_TTL_MS = 86400000;
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Linux; Android 12; Android TV) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36";
    private static final Map<String, String> DEFAULT_HEADERS = h0.t0(new x("accept-language", "en-US,en;q=0.9"), new x("user-agent", DEFAULT_USER_AGENT));
    private static final String PREFERRED_SEPARATE_CLIENT = "android_vr";
    private static final List<YouTubeClient> CLIENTS = t7.a.E(new YouTubeClient(PREFERRED_SEPARATE_CLIENT, "28", "1.56.21", "com.google.android.apps.youtube.vr.oculus/1.56.21 (Linux; U; Android 12; en_US; Quest 3; Build/SQ3A.220605.009.A1) gzip", h0.t0(new x("clientName", "ANDROID_VR"), new x("clientVersion", "1.56.21"), new x("deviceMake", "Oculus"), new x("deviceModel", "Quest 3"), new x("osName", "Android"), new x("osVersion", "12"), new x("platform", "MOBILE"), new x("androidSdkVersion", 32), new x("hl", "en"), new x("gl", "US")), 0), new YouTubeClient(CredentialsData.CREDENTIALS_TYPE_ANDROID, "3", "20.10.35", "com.google.android.youtube/20.10.35 (Linux; U; Android 14; en_US) gzip", h0.t0(new x("clientName", "ANDROID"), new x("clientVersion", "20.10.35"), new x("osName", "Android"), new x("osVersion", "14"), new x("platform", "MOBILE"), new x("androidSdkVersion", 34), new x("hl", "en"), new x("gl", "US")), 1), new YouTubeClient(CredentialsData.CREDENTIALS_TYPE_IOS, "5", "20.10.1", "com.google.ios.youtube/20.10.1 (iPhone16,2; U; CPU iOS 17_4 like Mac OS X)", h0.t0(new x("clientName", "IOS"), new x("clientVersion", "20.10.1"), new x("deviceModel", "iPhone16,2"), new x("osName", "iPhone"), new x("osVersion", "17.4.0.21E219"), new x("platform", "MOBILE"), new x("hl", "en"), new x("gl", "US")), 2));

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Map<?, ?>> listMapValue(Map<?, ?> map, String str) {
        Object obj = map.get(str);
        List list = obj instanceof List ? (List) obj : null;
        if (list == null) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            Map map2 = obj2 instanceof Map ? (Map) obj2 : null;
            if (map2 != null) {
                arrayList.add(map2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<?, ?> mapValue(Map<?, ?> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Double numberValue(Map<?, ?> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            return t.G((String) obj);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String stringValue(Map<?, ?> map, String str) {
        Object obj = map.get(str);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }
}
