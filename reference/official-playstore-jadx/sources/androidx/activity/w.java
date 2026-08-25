package androidx.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f1019a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f1020b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1022d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1024f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1021c = new Object();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f1025g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final n f1026h = new n(this, 2);

    public w(Executor executor, r rVar) {
        this.f1019a = executor;
        this.f1020b = rVar;
    }

    public final void a() {
        synchronized (this.f1021c) {
            if (!this.f1024f) {
                this.f1022d++;
            }
        }
    }

    public final void b() {
        synchronized (this.f1021c) {
            try {
                this.f1024f = true;
                Iterator it = this.f1025g.iterator();
                while (it.hasNext()) {
                    ((r7.a) it.next()).invoke();
                }
                this.f1025g.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean c() {
        boolean z;
        synchronized (this.f1021c) {
            z = this.f1024f;
        }
        return z;
    }

    public final void d() {
        int i10;
        synchronized (this.f1021c) {
            if (!this.f1024f && (i10 = this.f1022d) > 0) {
                int i11 = i10 - 1;
                this.f1022d = i11;
                if (!this.f1023e && i11 == 0) {
                    this.f1023e = true;
                    this.f1019a.execute(this.f1026h);
                }
            }
        }
    }
}
