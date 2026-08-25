package k3;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.media3.common.m;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f f19484i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ com.google.android.datatransport.runtime.i f19485l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19486m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Runnable f19487n;

    public /* synthetic */ e(f fVar, com.google.android.datatransport.runtime.i iVar, int i10, Runnable runnable) {
        this.f19484i = fVar;
        this.f19485l = iVar;
        this.f19486m = i10;
        this.f19487n = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.datatransport.runtime.i iVar = this.f19485l;
        int i10 = this.f19486m;
        Runnable runnable = this.f19487n;
        f fVar = this.f19484i;
        l3.a aVar = fVar.f19493f;
        try {
            try {
                com.google.android.datatransport.runtime.scheduling.persistence.d dVar = fVar.f19490c;
                Objects.requireNonNull(dVar);
                aVar.j(new io.sentry.cache.a(dVar, 7));
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) fVar.f19488a.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    aVar.j(new m(i10, 2, fVar, iVar));
                } else {
                    fVar.a(iVar, i10);
                }
                runnable.run();
            } catch (SynchronizationException unused) {
                fVar.f19491d.a(iVar, i10 + 1);
                runnable.run();
            }
        } catch (Throwable th) {
            runnable.run();
            throw th;
        }
    }
}
