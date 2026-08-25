package com.google.android.gms.internal.auth;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f13150a;

    static {
        char[] cArr = new char[80];
        f13150a = cArr;
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
        b(sb2, i10);
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
            sb2.append(s1.d(new x1(((String) obj).getBytes(i2.f13047a))));
            sb2.append('\"');
            return;
        }
        if (obj instanceof x1) {
            sb2.append(": \"");
            sb2.append(s1.d((x1) obj));
            sb2.append('\"');
            return;
        }
        if (obj instanceof d2) {
            sb2.append(" {");
            c((d2) obj, sb2, i10 + 2);
            sb2.append("\n");
            b(sb2, i10);
            sb2.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb2.append(": ");
            sb2.append(obj);
            return;
        }
        sb2.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i12 = i10 + 2;
        a(sb2, i12, "key", entry.getKey());
        a(sb2, i12, "value", entry.getValue());
        sb2.append("\n");
        b(sb2, i10);
        sb2.append("}");
    }

    public static void b(StringBuilder sb2, int i10) {
        while (i10 > 0) {
            int i11 = 80;
            if (i10 <= 80) {
                i11 = i10;
            }
            sb2.append(f13150a, 0, i11);
            i10 -= i11;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(com.google.android.gms.internal.auth.d2 r19, java.lang.StringBuilder r20, int r21) {
        /*
            Method dump skipped, instruction units count: 547
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.y2.c(com.google.android.gms.internal.auth.d2, java.lang.StringBuilder, int):void");
    }
}
