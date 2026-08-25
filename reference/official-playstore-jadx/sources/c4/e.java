package c4;

import android.text.TextUtils;
import com.google.android.gms.common.internal.t;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f7407a;

    static {
        Pattern.compile("\\\\.");
        f7407a = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static boolean a(Object obj, Object obj2) {
        int i10;
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj != null && obj2 != null) {
            try {
                if ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) {
                    JSONObject jSONObject = (JSONObject) obj;
                    JSONObject jSONObject2 = (JSONObject) obj2;
                    if (jSONObject.length() == jSONObject2.length()) {
                        Iterator<String> itKeys = jSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            if (jSONObject2.has(next)) {
                                t.i(next);
                                if (a(jSONObject.get(next), jSONObject2.get(next))) {
                                }
                            }
                        }
                        return true;
                    }
                } else {
                    if (!(obj instanceof JSONArray) || !(obj2 instanceof JSONArray)) {
                        return obj.equals(obj2);
                    }
                    JSONArray jSONArray = (JSONArray) obj;
                    JSONArray jSONArray2 = (JSONArray) obj2;
                    if (jSONArray.length() == jSONArray2.length()) {
                        for (0; i10 < jSONArray.length(); i10 + 1) {
                            i10 = a(jSONArray.get(i10), jSONArray2.get(i10)) ? i10 + 1 : 0;
                        }
                        return true;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = f7407a.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            char cCharAt = matcher.group().charAt(0);
            if (cCharAt == '\f') {
                matcher.appendReplacement(stringBuffer, "\\\\f");
            } else if (cCharAt == '\r') {
                matcher.appendReplacement(stringBuffer, "\\\\r");
            } else if (cCharAt == '\"') {
                matcher.appendReplacement(stringBuffer, "\\\\\\\"");
            } else if (cCharAt == '/') {
                matcher.appendReplacement(stringBuffer, "\\\\/");
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        matcher.appendReplacement(stringBuffer, "\\\\b");
                        break;
                    case '\t':
                        matcher.appendReplacement(stringBuffer, "\\\\t");
                        break;
                    case '\n':
                        matcher.appendReplacement(stringBuffer, "\\\\n");
                        break;
                }
            } else {
                matcher.appendReplacement(stringBuffer, "\\\\\\\\");
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
