package androidx.loader.content;

import android.os.Looper;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Runnable {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ThreadPoolExecutor f3328s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static j f3329t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static volatile ThreadPoolExecutor f3330u;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g f3331i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final h f3332l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile int f3333m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicBoolean f3334n = new AtomicBoolean();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AtomicBoolean f3335o = new AtomicBoolean();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CountDownLatch f3336p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3337q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ b f3338r;

    static {
        f fVar = new f(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), fVar);
        f3328s = threadPoolExecutor;
        f3330u = threadPoolExecutor;
    }

    public a(b bVar) {
        this.f3338r = bVar;
        g gVar = new g(this, 0);
        this.f3331i = gVar;
        this.f3332l = new h(this, gVar);
        this.f3336p = new CountDownLatch(1);
    }

    public final void a(Object obj) {
        j jVar;
        synchronized (a.class) {
            try {
                if (f3329t == null) {
                    f3329t = new j(Looper.getMainLooper(), 0, false);
                }
                jVar = f3329t;
            } catch (Throwable th) {
                throw th;
            }
        }
        jVar.obtainMessage(1, new i(this, obj)).sendToTarget();
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f3337q = false;
        this.f3338r.executePendingTask();
    }
}
