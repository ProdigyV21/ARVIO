package o3;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public final class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static h f20749b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20750a;

    public h(Context context) {
        this.f20750a = context.getApplicationContext();
    }

    public static h a(Context context) {
        t.i(context);
        synchronized (h.class) {
            try {
                if (f20749b == null) {
                    q.a(context);
                    f20749b = new h(context);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f20749b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f6, code lost:
    
        r5 = r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean b(android.content.pm.PackageInfo r12, boolean r13) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: o3.h.b(android.content.pm.PackageInfo, boolean):boolean");
    }

    public static n c(PackageInfo packageInfo, n... nVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null) {
            if (signatureArr.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                return null;
            }
            o oVar = new o(packageInfo.signatures[0].toByteArray());
            for (int i10 = 0; i10 < nVarArr.length; i10++) {
                if (nVarArr[i10].equals(oVar)) {
                    return nVarArr[i10];
                }
            }
        }
        return null;
    }
}
