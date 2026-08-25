package androidx.media3.datasource;

import android.text.TextUtils;
import androidx.media3.common.util.Log;
import com.google.common.collect.t3;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class HttpUtil {
    private static final String TAG = "HttpUtil";
    private static final Pattern CONTENT_RANGE_WITH_START_AND_END = Pattern.compile("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)");
    private static final Pattern CONTENT_RANGE_WITH_SIZE = Pattern.compile("bytes (?:(?:\\d+-\\d+)|\\*)/(\\d+)");

    private HttpUtil() {
    }

    public static String buildRangeRequestHeader(long j10, long j11) {
        if (j10 == 0 && j11 == -1) {
            return null;
        }
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "bytes=", "-");
        if (j11 != -1) {
            sbR.append((j10 + j11) - 1);
        }
        return sbR.toString();
    }

    public static long getContentLength(String str, String str2) {
        long j10;
        if (TextUtils.isEmpty(str)) {
            j10 = -1;
        } else {
            try {
                j10 = Long.parseLong(str);
            } catch (NumberFormatException unused) {
                Log.e(TAG, "Unexpected Content-Length [" + str + "]");
                j10 = -1;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return j10;
        }
        Matcher matcher = CONTENT_RANGE_WITH_START_AND_END.matcher(str2);
        if (!matcher.matches()) {
            return j10;
        }
        try {
            String strGroup = matcher.group(2);
            strGroup.getClass();
            long j11 = Long.parseLong(strGroup);
            String strGroup2 = matcher.group(1);
            strGroup2.getClass();
            long j12 = (j11 - Long.parseLong(strGroup2)) + 1;
            if (j10 < 0) {
                return j12;
            }
            if (j10 == j12) {
                return j10;
            }
            Log.w(TAG, "Inconsistent headers [" + str + "] [" + str2 + "]");
            return Math.max(j10, j12);
        } catch (NumberFormatException unused2) {
            Log.e(TAG, "Unexpected Content-Range [" + str2 + "]");
            return j10;
        }
    }

    public static String getCookieHeader(String str, Map<String, List<String>> map, CookieHandler cookieHandler) {
        Map<String, List<String>> map2;
        List<String> list;
        if (cookieHandler == null) {
            return "";
        }
        try {
            map2 = cookieHandler.get(new URI(str), map);
        } catch (Exception e5) {
            Log.w(TAG, "Failed to read cookies from CookieHandler", e5);
            map2 = t3.f14114q;
        }
        StringBuilder sb2 = new StringBuilder();
        if (map2.containsKey("Cookie") && (list = map2.get("Cookie")) != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                sb2.append(it.next());
                sb2.append("; ");
            }
        }
        String string = sb2.toString();
        int length = string.length();
        while (length > 0) {
            int iCodePointBefore = Character.codePointBefore(string, length);
            if (!Character.isWhitespace(iCodePointBefore)) {
                break;
            }
            length -= Character.charCount(iCodePointBefore);
        }
        return string.substring(0, length);
    }

    public static long getDocumentSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1L;
        }
        Matcher matcher = CONTENT_RANGE_WITH_SIZE.matcher(str);
        if (!matcher.matches()) {
            return -1L;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        return Long.parseLong(strGroup);
    }

    public static void storeCookiesFromHeaders(String str, Map<String, List<String>> map, CookieHandler cookieHandler) {
        if (cookieHandler == null) {
            return;
        }
        try {
            cookieHandler.put(new URI(str), map);
        } catch (Exception e5) {
            Log.w(TAG, "Failed to store cookies in CookieHandler", e5);
        }
    }
}
