package q2;

import android.util.Log;
import h.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f21326a = new a();

    @Override // q2.d
    public final void a(String str, int i10, String str2, Throwable th) {
        String strA = b.f21325a.a(0, null, str);
        try {
            if (th == null) {
                int iC = f0.c(i10);
                if (iC == 0) {
                    Log.v(str2, strA);
                    return;
                }
                if (iC == 1) {
                    Log.d(str2, strA);
                    return;
                }
                if (iC == 2) {
                    Log.i(str2, strA);
                    return;
                }
                if (iC == 3) {
                    Log.w(str2, strA);
                    return;
                } else if (iC == 4) {
                    Log.e(str2, strA);
                    return;
                } else {
                    if (iC != 5) {
                        return;
                    }
                    Log.wtf(str2, strA);
                    return;
                }
            }
            int iC2 = f0.c(i10);
            if (iC2 == 0) {
                Log.v(str2, strA, th);
                return;
            }
            if (iC2 == 1) {
                Log.d(str2, strA, th);
                return;
            }
            if (iC2 == 2) {
                Log.i(str2, strA, th);
                return;
            }
            if (iC2 == 3) {
                Log.w(str2, strA, th);
            } else if (iC2 == 4) {
                Log.e(str2, strA, th);
            } else {
                if (iC2 != 5) {
                    return;
                }
                Log.wtf(str2, strA, th);
            }
        } catch (Exception unused) {
            this.f21326a.a(str, i10, str2, th);
        }
    }
}
