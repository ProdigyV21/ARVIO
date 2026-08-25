package m2;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public final class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f20214b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile t f20215c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20216a;

    public t(int i10) {
        this.f20216a = i10;
    }

    public static t d() {
        t tVar;
        synchronized (f20214b) {
            try {
                if (f20215c == null) {
                    f20215c = new t(3);
                }
                tVar = f20215c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return tVar;
    }

    public static String f(String str) {
        int length = str.length();
        StringBuilder sb2 = new StringBuilder(23);
        sb2.append("WM-");
        if (length >= 20) {
            sb2.append(str.substring(0, 20));
        } else {
            sb2.append(str);
        }
        return sb2.toString();
    }

    public final void a(String str, String str2) {
        if (this.f20216a <= 3) {
            Log.d(str, str2);
        }
    }

    public final void b(String str, String str2) {
        if (this.f20216a <= 6) {
            Log.e(str, str2);
        }
    }

    public final void c(String str, String str2, Throwable th) {
        if (this.f20216a <= 6) {
            Log.e(str, str2, th);
        }
    }

    public final void e(String str, String str2) {
        if (this.f20216a <= 4) {
            Log.i(str, str2);
        }
    }

    public final void g(String str, String str2) {
        if (this.f20216a <= 5) {
            Log.w(str, str2);
        }
    }
}
