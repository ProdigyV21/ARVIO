package com.google.android.gms.internal.cast;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class kb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f13391a;

    static {
        char[] cArr = new char[80];
        f13391a = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static void a(StringBuilder sb2, int i10, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                a(sb2, i10, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                a(sb2, i10, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb2.append('\n');
        c(sb2, i10);
        if (!str.isEmpty()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Character.toLowerCase(str.charAt(0)));
            for (int i11 = 1; i11 < str.length(); i11++) {
                char cCharAt = str.charAt(i11);
                if (Character.isUpperCase(cCharAt)) {
                    sb3.append("_");
                }
                sb3.append(Character.toLowerCase(cCharAt));
            }
            str = sb3.toString();
        }
        sb2.append(str);
        if (obj instanceof String) {
            sb2.append(": \"");
            ha haVar = ha.f13297m;
            sb2.append(u0.k(new ha(((String) obj).getBytes(ya.f13698a))));
            sb2.append('\"');
            return;
        }
        if (obj instanceof ha) {
            sb2.append(": \"");
            sb2.append(u0.k((ha) obj));
            sb2.append('\"');
            return;
        }
        if (obj instanceof pa) {
            sb2.append(" {");
            b((pa) obj, sb2, i10 + 2);
            sb2.append("\n");
            c(sb2, i10);
            sb2.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb2.append(": ");
            sb2.append(obj);
            return;
        }
        int i12 = i10 + 2;
        sb2.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        a(sb2, i12, "key", entry.getKey());
        a(sb2, i12, "value", entry.getValue());
        sb2.append("\n");
        c(sb2, i10);
        sb2.append("}");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01fa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(com.google.android.gms.internal.cast.pa r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instruction units count: 512
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.kb.b(com.google.android.gms.internal.cast.pa, java.lang.StringBuilder, int):void");
    }

    public static void c(StringBuilder sb2, int i10) {
        while (i10 > 0) {
            int i11 = 80;
            if (i10 <= 80) {
                i11 = i10;
            }
            sb2.append(f13391a, 0, i11);
            i10 -= i11;
        }
    }
}
