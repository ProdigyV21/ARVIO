package com.google.android.gms.tasks;

import com.google.common.util.concurrent.w0;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class p implements q, e, d, b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13872i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Executor f13873l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f13874m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f13875n;

    public p(Executor executor, b bVar) {
        this.f13872i = 0;
        this.f13874m = new Object();
        this.f13873l = executor;
        this.f13875n = bVar;
    }

    private final void c(h hVar) {
        synchronized (this.f13874m) {
        }
        this.f13873l.execute(new w0(18, this, false, hVar));
    }

    private final void d(h hVar) {
        if (hVar.h() || ((t) hVar).f13883d) {
            return;
        }
        synchronized (this.f13874m) {
            try {
                if (((d) this.f13875n) == null) {
                    return;
                }
                this.f13873l.execute(new w0(19, this, false, hVar));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void e(h hVar) {
        if (hVar.h()) {
            synchronized (this.f13874m) {
                try {
                    if (((e) this.f13875n) == null) {
                        return;
                    }
                    this.f13873l.execute(new w0(20, this, false, hVar));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // com.google.android.gms.tasks.q
    public final void a(h hVar) {
        switch (this.f13872i) {
            case 0:
                if (((t) hVar).f13883d) {
                    synchronized (this.f13874m) {
                        try {
                            if (((b) this.f13875n) != null) {
                                this.f13873l.execute(new o(this));
                            }
                        } finally {
                        }
                    }
                    return;
                }
                return;
            case 1:
                c(hVar);
                return;
            case 2:
                d(hVar);
                return;
            case 3:
                e(hVar);
                return;
            default:
                this.f13873l.execute(new w0(21, this, false, hVar));
                return;
        }
    }

    @Override // com.google.android.gms.tasks.b
    public void b() {
        ((t) this.f13875n).l();
    }

    @Override // com.google.android.gms.tasks.d
    public void onFailure(Exception exc) {
        ((t) this.f13875n).k(exc);
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        ((t) this.f13875n).j(obj);
    }

    public p(Executor executor, c cVar) {
        this.f13872i = 1;
        this.f13874m = new Object();
        this.f13873l = executor;
        this.f13875n = cVar;
    }

    public p(Executor executor, d dVar) {
        this.f13872i = 2;
        this.f13874m = new Object();
        this.f13873l = executor;
        this.f13875n = dVar;
    }

    public p(Executor executor, e eVar) {
        this.f13872i = 3;
        this.f13874m = new Object();
        this.f13873l = executor;
        this.f13875n = eVar;
    }

    public p(Executor executor, g gVar, t tVar) {
        this.f13872i = 4;
        this.f13873l = executor;
        this.f13874m = gVar;
        this.f13875n = tVar;
    }
}
