package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.core.provider.e f2587b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a1.a f2588c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2589d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler f2590e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ThreadPoolExecutor f2591f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ThreadPoolExecutor f2592g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m2.f0 f2593h;

    public a0(Context context, androidx.core.provider.e eVar) {
        p0.g.b(context, "Context cannot be null");
        this.f2586a = context.getApplicationContext();
        this.f2587b = eVar;
        this.f2588c = b0.f2594d;
    }

    @Override // androidx.emoji2.text.l
    public final void a(m2.f0 f0Var) {
        synchronized (this.f2589d) {
            this.f2593h = f0Var;
        }
        c();
    }

    public final void b() {
        synchronized (this.f2589d) {
            try {
                this.f2593h = null;
                Handler handler = this.f2590e;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.f2590e = null;
                ThreadPoolExecutor threadPoolExecutor = this.f2592g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f2591f = null;
                this.f2592g = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c() {
        synchronized (this.f2589d) {
            try {
                if (this.f2593h == null) {
                    return;
                }
                if (this.f2591f == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.f2592g = threadPoolExecutor;
                    this.f2591f = threadPoolExecutor;
                }
                this.f2591f.execute(new androidx.activity.n(this, 7));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final androidx.core.provider.k d() throws Throwable {
        try {
            a1.a aVar = this.f2588c;
            Context context = this.f2586a;
            androidx.core.provider.e eVar = this.f2587b;
            aVar.getClass();
            androidx.core.provider.j jVarA = androidx.core.provider.d.a(context, eVar);
            int i10 = jVarA.f2159b;
            if (i10 != 0) {
                throw new RuntimeException(androidx.compose.foundation.c.o(i10, "fetchFonts failed (", ")"));
            }
            androidx.core.provider.k[] kVarArr = (androidx.core.provider.k[]) jVarA.f2160c;
            if (kVarArr == null || kVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return kVarArr[0];
        } catch (PackageManager.NameNotFoundException e5) {
            throw new RuntimeException("provider not found", e5);
        }
    }
}
