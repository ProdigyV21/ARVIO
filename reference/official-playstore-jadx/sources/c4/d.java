package c4;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f7402a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f7403b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f7404c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Boolean f7405d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Boolean f7406e;

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 11);
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 11);
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean d(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (f7403b == null) {
            f7403b = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        if (f7403b.booleanValue() && Build.VERSION.SDK_INT < 24) {
            return true;
        }
        if (f7404c == null) {
            f7404c = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
        }
        if (f7404c.booleanValue()) {
            return !c() || Build.VERSION.SDK_INT >= 30;
        }
        return false;
    }

    public static void e(StringBuilder sb2, HashMap map) {
        sb2.append("{");
        boolean z = true;
        for (String str : map.keySet()) {
            if (!z) {
                sb2.append(",");
            }
            String str2 = (String) map.get(str);
            sb2.append("\"");
            sb2.append(str);
            sb2.append("\":");
            if (str2 == null) {
                sb2.append("null");
            } else {
                sb2.append("\"");
                sb2.append(str2);
                sb2.append("\"");
            }
            z = false;
        }
        sb2.append("}");
    }
}
