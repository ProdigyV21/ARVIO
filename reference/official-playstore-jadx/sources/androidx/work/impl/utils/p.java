package androidx.work.impl.utils;

import android.content.Context;
import android.os.Build;
import com.google.common.util.concurrent.w0;

/* JADX INFO: loaded from: classes3.dex */
public final class p implements Runnable {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f7055q = m2.t.f("WorkForegroundRunnable");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.work.impl.utils.futures.k f7056i = new androidx.work.impl.utils.futures.k();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f7057l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final androidx.work.impl.model.p f7058m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final m2.s f7059n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final r f7060o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.b f7061p;

    public p(Context context, androidx.work.impl.model.p pVar, m2.s sVar, r rVar, androidx.work.impl.utils.taskexecutor.d dVar) {
        this.f7057l = context;
        this.f7058m = pVar;
        this.f7059n = sVar;
        this.f7060o = rVar;
        this.f7061p = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.f7058m.f6937q || Build.VERSION.SDK_INT >= 31) {
            this.f7056i.h(null);
            return;
        }
        androidx.work.impl.utils.futures.k kVar = new androidx.work.impl.utils.futures.k();
        androidx.work.impl.utils.taskexecutor.b bVar = this.f7061p;
        bVar.a().execute(new androidx.activity.s(this, kVar, 14));
        kVar.addListener(new w0(10, this, false, kVar), bVar.a());
    }
}
