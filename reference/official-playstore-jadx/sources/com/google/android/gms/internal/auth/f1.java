package com.google.android.gms.internal.auth;

import android.os.Build;
import android.os.StrictMode;
import android.os.UserManager;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class f1 implements x0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final t.e f13028i = new t.e(0);

    public static void a() {
        UserManager userManager = r0.f13106a;
        if (Build.VERSION.SDK_INT >= 24) {
            throw null;
        }
        synchronized (f1.class) {
            try {
                if (f13028i.get(null) != null) {
                    throw new ClassCastException();
                }
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    throw null;
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public static synchronized void b() {
        t.e eVar = f13028i;
        Iterator it = ((t.d) eVar.values()).iterator();
        if (it.hasNext()) {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            throw null;
        }
        eVar.clear();
    }
}
