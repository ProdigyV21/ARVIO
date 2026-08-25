package m2;

import android.os.Build;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f20173a = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new d(false));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ExecutorService f20174b = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new d(true));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u f20175c = new u();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i0 f20176d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u f20177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final androidx.work.impl.c f20178f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f20179g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f20180h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f20181i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f20182j;

    public c(androidx.core.provider.j jVar) {
        i0 h0Var = (i1.b) jVar.f2160c;
        if (h0Var == null) {
            String str = i0.f20206a;
            h0Var = new h0();
        }
        this.f20176d = h0Var;
        this.f20177e = u.f20217a;
        this.f20178f = new androidx.work.impl.c(0);
        this.f20179g = jVar.f2159b;
        this.f20180h = Integer.MAX_VALUE;
        this.f20182j = Build.VERSION.SDK_INT == 23 ? 10 : 20;
        this.f20181i = 8;
    }
}
