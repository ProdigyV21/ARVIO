package androidx.navigation;

import android.os.Bundle;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class q0 extends w0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4756l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q0(boolean z, int i10) {
        super(z);
        this.f4756l = i10;
    }

    @Override // androidx.navigation.w0
    public final Object a(Bundle bundle, String str) {
        switch (this.f4756l) {
            case 0:
                return (boolean[]) bundle.get(str);
            case 1:
                return (Boolean) bundle.get(str);
            case 2:
                return (float[]) bundle.get(str);
            case 3:
                return (Float) bundle.get(str);
            case 4:
                return (int[]) bundle.get(str);
            case 5:
                return (Integer) bundle.get(str);
            case 6:
                return (long[]) bundle.get(str);
            case 7:
                return (Long) bundle.get(str);
            case 8:
                return (String[]) bundle.get(str);
            default:
                return (String) bundle.get(str);
        }
    }

    @Override // androidx.navigation.w0
    public final String b() {
        switch (this.f4756l) {
            case 0:
                return "boolean[]";
            case 1:
                return "boolean";
            case 2:
                return "float[]";
            case 3:
                return "float";
            case 4:
                return "integer[]";
            case 5:
                return "integer";
            case 6:
                return "long[]";
            case 7:
                return "long";
            case 8:
                return "string[]";
            default:
                return "string";
        }
    }

    @Override // androidx.navigation.w0
    public final Object c(String str) {
        int i10;
        long j10;
        boolean z = true;
        switch (this.f4756l) {
            case 0:
                return new boolean[]{((Boolean) w0.f4797h.c(str)).booleanValue()};
            case 1:
                if (!str.equals("true")) {
                    if (!str.equals("false")) {
                        throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
                    }
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                return new float[]{((Number) w0.f4795f.c(str)).floatValue()};
            case 3:
                return Float.valueOf(Float.parseFloat(str));
            case 4:
                return new int[]{((Number) w0.f4791b.c(str)).intValue()};
            case 5:
                if (kotlin.text.u.P(str, "0x", false)) {
                    String strSubstring = str.substring(2);
                    com.google.common.util.concurrent.r0.c(16);
                    i10 = Integer.parseInt(strSubstring, 16);
                } else {
                    i10 = Integer.parseInt(str);
                }
                return Integer.valueOf(i10);
            case 6:
                return new long[]{((Number) w0.f4793d.c(str)).longValue()};
            case 7:
                String strSubstring2 = kotlin.text.u.K(str, "L", false) ? str.substring(0, str.length() - 1) : str;
                if (kotlin.text.u.P(str, "0x", false)) {
                    String strSubstring3 = strSubstring2.substring(2);
                    com.google.common.util.concurrent.r0.c(16);
                    j10 = Long.parseLong(strSubstring3, 16);
                } else {
                    j10 = Long.parseLong(strSubstring2);
                }
                return Long.valueOf(j10);
            case 8:
                return new String[]{str};
            default:
                if (str.equals("null")) {
                    return null;
                }
                return str;
        }
    }

    @Override // androidx.navigation.w0
    public Object d(String str, Object obj) {
        switch (this.f4756l) {
            case 0:
                boolean[] zArr = (boolean[]) obj;
                q0 q0Var = w0.f4797h;
                if (zArr == null) {
                    return new boolean[]{((Boolean) q0Var.c(str)).booleanValue()};
                }
                boolean[] zArr2 = {((Boolean) q0Var.c(str)).booleanValue()};
                int length = zArr.length;
                boolean[] zArrCopyOf = Arrays.copyOf(zArr, length + 1);
                System.arraycopy(zArr2, 0, zArrCopyOf, length, 1);
                return zArrCopyOf;
            case 1:
            case 3:
            case 5:
            case 7:
            default:
                return super.d(str, obj);
            case 2:
                float[] fArr = (float[]) obj;
                q0 q0Var2 = w0.f4795f;
                if (fArr == null) {
                    return new float[]{((Number) q0Var2.c(str)).floatValue()};
                }
                float[] fArr2 = {((Number) q0Var2.c(str)).floatValue()};
                int length2 = fArr.length;
                float[] fArrCopyOf = Arrays.copyOf(fArr, length2 + 1);
                System.arraycopy(fArr2, 0, fArrCopyOf, length2, 1);
                return fArrCopyOf;
            case 4:
                int[] iArr = (int[]) obj;
                q0 q0Var3 = w0.f4791b;
                if (iArr == null) {
                    return new int[]{((Number) q0Var3.c(str)).intValue()};
                }
                int[] iArr2 = {((Number) q0Var3.c(str)).intValue()};
                int length3 = iArr.length;
                int[] iArrCopyOf = Arrays.copyOf(iArr, length3 + 1);
                System.arraycopy(iArr2, 0, iArrCopyOf, length3, 1);
                return iArrCopyOf;
            case 6:
                long[] jArr = (long[]) obj;
                q0 q0Var4 = w0.f4793d;
                if (jArr == null) {
                    return new long[]{((Number) q0Var4.c(str)).longValue()};
                }
                long[] jArr2 = {((Number) q0Var4.c(str)).longValue()};
                int length4 = jArr.length;
                long[] jArrCopyOf = Arrays.copyOf(jArr, length4 + 1);
                System.arraycopy(jArr2, 0, jArrCopyOf, length4, 1);
                return jArrCopyOf;
            case 8:
                String[] strArr = (String[]) obj;
                if (strArr == null) {
                    return new String[]{str};
                }
                int length5 = strArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(strArr, length5 + 1);
                System.arraycopy(new String[]{str}, 0, objArrCopyOf, length5, 1);
                return (String[]) objArrCopyOf;
        }
    }

    @Override // androidx.navigation.w0
    public final void e(Bundle bundle, String str, Object obj) {
        switch (this.f4756l) {
            case 0:
                bundle.putBooleanArray(str, (boolean[]) obj);
                break;
            case 1:
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                break;
            case 2:
                bundle.putFloatArray(str, (float[]) obj);
                break;
            case 3:
                bundle.putFloat(str, ((Number) obj).floatValue());
                break;
            case 4:
                bundle.putIntArray(str, (int[]) obj);
                break;
            case 5:
                bundle.putInt(str, ((Number) obj).intValue());
                break;
            case 6:
                bundle.putLongArray(str, (long[]) obj);
                break;
            case 7:
                bundle.putLong(str, ((Number) obj).longValue());
                break;
            case 8:
                bundle.putStringArray(str, (String[]) obj);
                break;
            default:
                bundle.putString(str, (String) obj);
                break;
        }
    }
}
