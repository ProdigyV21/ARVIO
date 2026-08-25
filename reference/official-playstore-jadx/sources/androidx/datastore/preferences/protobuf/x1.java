package androidx.datastore.preferences.protobuf;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class x1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s2 f2548a = new s2();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t2 f2549b = new t2();

    public static void a(byte b10, byte b11, byte b12, byte b13, char[] cArr, int i10) throws InvalidProtocolBufferException {
        if (!h(b11)) {
            if ((((b11 + 112) + (b10 << 28)) >> 30) == 0 && !h(b12) && !h(b13)) {
                int i11 = ((b10 & 7) << 18) | ((b11 & 63) << 12) | ((b12 & 63) << 6) | (b13 & 63);
                cArr[i10] = (char) ((i11 >>> 10) + 55232);
                cArr[i10 + 1] = (char) ((i11 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
                return;
            }
        }
        throw InvalidProtocolBufferException.a();
    }

    public static void b(byte b10, byte b11, char[] cArr, int i10) throws InvalidProtocolBufferException {
        if (b10 < -62 || h(b11)) {
            throw InvalidProtocolBufferException.a();
        }
        cArr[i10] = (char) (((b10 & 31) << 6) | (b11 & 63));
    }

    public static void c(byte b10, byte b11, byte b12, char[] cArr, int i10) throws InvalidProtocolBufferException {
        if (h(b11) || ((b10 == -32 && b11 < -96) || ((b10 == -19 && b11 >= -96) || h(b12)))) {
            throw InvalidProtocolBufferException.a();
        }
        cArr[i10] = (char) (((b10 & 15) << 12) | ((b11 & 63) << 6) | (b12 & 63));
    }

    public static final String d(String str) {
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < str.length(); i10++) {
            char cCharAt = str.charAt(i10);
            if (Character.isUpperCase(cCharAt)) {
                sb2.append("_");
            }
            sb2.append(Character.toLowerCase(cCharAt));
        }
        return sb2.toString();
    }

    public static String g(m mVar) {
        StringBuilder sb2 = new StringBuilder(mVar.size());
        for (int i10 = 0; i10 < mVar.size(); i10++) {
            byte b10 = mVar.f2473l[i10];
            if (b10 == 34) {
                sb2.append("\\\"");
            } else if (b10 == 39) {
                sb2.append("\\'");
            } else if (b10 != 92) {
                switch (b10) {
                    case 7:
                        sb2.append("\\a");
                        break;
                    case 8:
                        sb2.append("\\b");
                        break;
                    case 9:
                        sb2.append("\\t");
                        break;
                    case 10:
                        sb2.append("\\n");
                        break;
                    case 11:
                        sb2.append("\\v");
                        break;
                    case 12:
                        sb2.append("\\f");
                        break;
                    case 13:
                        sb2.append("\\r");
                        break;
                    default:
                        if (b10 < 32 || b10 > 126) {
                            sb2.append('\\');
                            sb2.append((char) (((b10 >>> 6) & 3) + 48));
                            sb2.append((char) (((b10 >>> 3) & 7) + 48));
                            sb2.append((char) ((b10 & 7) + 48));
                        } else {
                            sb2.append((char) b10);
                        }
                        break;
                }
            } else {
                sb2.append("\\\\");
            }
        }
        return sb2.toString();
    }

    public static boolean h(byte b10) {
        return b10 > -65;
    }

    public static final void j(StringBuilder sb2, int i10, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                j(sb2, i10, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                j(sb2, i10, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb2.append('\n');
        int i11 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            sb2.append(' ');
        }
        sb2.append(str);
        if (obj instanceof String) {
            sb2.append(": \"");
            m mVar = m.f2470m;
            sb2.append(g(new m(((String) obj).getBytes(f1.f2421a))));
            sb2.append('\"');
            return;
        }
        if (obj instanceof m) {
            sb2.append(": \"");
            sb2.append(g((m) obj));
            sb2.append('\"');
            return;
        }
        if (obj instanceof r0) {
            sb2.append(" {");
            k((r0) obj, sb2, i10 + 2);
            sb2.append("\n");
            while (i11 < i10) {
                sb2.append(' ');
                i11++;
            }
            sb2.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb2.append(": ");
            sb2.append(obj.toString());
            return;
        }
        sb2.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i13 = i10 + 2;
        j(sb2, i13, "key", entry.getKey());
        j(sb2, i13, "value", entry.getValue());
        sb2.append("\n");
        while (i11 < i10) {
            sb2.append(' ');
            i11++;
        }
        sb2.append("}");
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void k(androidx.datastore.preferences.protobuf.r0 r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instruction units count: 575
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.x1.k(androidx.datastore.preferences.protobuf.r0, java.lang.StringBuilder, int):void");
    }

    public abstract String e(int i10, int i11, byte[] bArr);

    public abstract int f(String str, byte[] bArr, int i10, int i11);

    public abstract int i(int i10, int i11, byte[] bArr);

    public abstract void l(int i10, int i11, byte[] bArr);
}
