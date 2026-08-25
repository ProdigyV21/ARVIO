package o;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ac.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile a f20715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final androidx.credentials.a f20716f = new androidx.credentials.a(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f20717d = new b();

    public static a Z() {
        if (f20715e != null) {
            return f20715e;
        }
        synchronized (a.class) {
            try {
                if (f20715e == null) {
                    f20715e = new a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f20715e;
    }

    public final void a0(Runnable runnable) {
        b bVar = this.f20717d;
        if (bVar.f20720f == null) {
            synchronized (bVar.f20718d) {
                try {
                    if (bVar.f20720f == null) {
                        bVar.f20720f = b.Z(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        bVar.f20720f.post(runnable);
    }
}
