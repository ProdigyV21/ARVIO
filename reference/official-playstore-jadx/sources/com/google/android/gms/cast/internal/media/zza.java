package com.google.android.gms.cast.internal.media;

import android.text.TextUtils;
import androidx.compose.material3.d;
import com.google.android.gms.cast.internal.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import r3.b;

/* JADX INFO: loaded from: classes4.dex */
public final class zza {
    private static final Logger zza = new Logger("MetadataUtils");
    private static final String[] zzb;
    private static final String zzc;

    static {
        String[] strArr = {"Z", "+hh", "+hhmm", "+hh:mm"};
        zzb = strArr;
        zzc = "yyyyMMdd'T'HHmmss".concat(String.valueOf(strArr[0]));
    }

    public static void zza(List list, JSONArray jSONArray) {
        try {
            list.clear();
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                try {
                    list.add(new b(jSONArray.getJSONObject(i10)));
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public static JSONArray zzb(List list) {
        list.getClass();
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            bVar.getClass();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", bVar.f21388l.toString());
                jSONObject.put("width", bVar.f21389m);
                jSONObject.put("height", bVar.f21390n);
            } catch (JSONException unused) {
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public static String zzc(Calendar calendar) {
        if (calendar == null) {
            zza.d("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = zzc;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String str2 = simpleDateFormat.format(calendar.getTime());
        return str2.endsWith("+0000") ? str2.replace("+0000", zzb[0]) : str2;
    }

    public static Calendar zzd(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            zza.d("Input string is empty or null", new Object[0]);
            return null;
        }
        String strZze = zze(str);
        if (TextUtils.isEmpty(strZze)) {
            zza.d("Invalid date format", new Object[0]);
            return null;
        }
        String strZzf = zzf(str);
        if (TextUtils.isEmpty(strZzf)) {
            str2 = "yyyyMMdd";
        } else {
            strZze = d.q(new StringBuilder(String.valueOf(strZze).length() + 1 + String.valueOf(strZzf).length()), strZze, "T", strZzf);
            str2 = strZzf.length() == 6 ? "yyyyMMdd'T'HHmmss" : zzc;
        }
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat(str2).parse(strZze));
            return calendar;
        } catch (ParseException e5) {
            zza.e(e5, "Error parsing string", new Object[0]);
            return null;
        }
    }

    private static String zze(String str) {
        if (TextUtils.isEmpty(str)) {
            zza.d("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, 8);
        } catch (IndexOutOfBoundsException e5) {
            zza.e(e5, "Error extracting the date", new Object[0]);
            return null;
        }
    }

    private static String zzf(String str) {
        if (TextUtils.isEmpty(str)) {
            zza.d("string is empty or null", new Object[0]);
            return null;
        }
        int iIndexOf = str.indexOf(84);
        int i10 = iIndexOf + 1;
        if (iIndexOf != 8) {
            zza.d("T delimeter is not found", new Object[0]);
            return null;
        }
        try {
            String strSubstring = str.substring(i10);
            if (strSubstring.length() == 6) {
                return strSubstring;
            }
            char cCharAt = strSubstring.charAt(6);
            if (cCharAt != '+' && cCharAt != '-') {
                if (cCharAt == 'Z' && strSubstring.length() == zzb[0].length() + 6) {
                    return String.valueOf(strSubstring.substring(0, strSubstring.length() - 1)).concat("+0000");
                }
                return null;
            }
            int length = strSubstring.length();
            String[] strArr = zzb;
            if (length == strArr[1].length() + 6 || length == strArr[2].length() + 6 || length == strArr[3].length() + 6) {
                return strSubstring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
            }
            return null;
        } catch (IndexOutOfBoundsException e5) {
            zza.e(e5, "Error extracting the time substring: %s", new Object[0]);
            return null;
        }
    }
}
