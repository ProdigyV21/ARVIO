package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f13880a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f13881b = new r();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f13882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f13883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f13884e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Exception f13885f;

    @Override // com.google.android.gms.tasks.h
    public final t a(c cVar) {
        this.f13881b.a(new p(j.f13856a, cVar));
        n();
        return this;
    }

    @Override // com.google.android.gms.tasks.h
    public final t b(Executor executor, d dVar) {
        this.f13881b.a(new p(executor, dVar));
        n();
        return this;
    }

    @Override // com.google.android.gms.tasks.h
    public final t c(Executor executor, e eVar) {
        this.f13881b.a(new p(executor, eVar));
        n();
        return this;
    }

    @Override // com.google.android.gms.tasks.h
    public final t d(Executor executor, a aVar) {
        t tVar = new t();
        this.f13881b.a(new n(executor, aVar, tVar, 0));
        n();
        return tVar;
    }

    @Override // com.google.android.gms.tasks.h
    public final Exception e() {
        Exception exc;
        synchronized (this.f13880a) {
            exc = this.f13885f;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.h
    public final Object f() {
        Object obj;
        synchronized (this.f13880a) {
            try {
                com.google.android.gms.common.internal.t.k("Task is not yet complete", this.f13882c);
                if (this.f13883d) {
                    throw new CancellationException("Task is already canceled.");
                }
                Exception exc = this.f13885f;
                if (exc != null) {
                    throw new RuntimeExecutionException(exc);
                }
                obj = this.f13884e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    @Override // com.google.android.gms.tasks.h
    public final boolean g() {
        boolean z;
        synchronized (this.f13880a) {
            z = this.f13882c;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.h
    public final boolean h() {
        boolean z;
        synchronized (this.f13880a) {
            try {
                z = false;
                if (this.f13882c && !this.f13883d && this.f13885f == null) {
                    z = true;
                }
            } finally {
            }
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.h
    public final t i(Executor executor, g gVar) {
        t tVar = new t();
        this.f13881b.a(new p(executor, gVar, tVar));
        n();
        return tVar;
    }

    public final void j(Object obj) {
        synchronized (this.f13880a) {
            m();
            this.f13882c = true;
            this.f13884e = obj;
        }
        this.f13881b.b(this);
    }

    public final void k(Exception exc) {
        com.google.android.gms.common.internal.t.j(exc, "Exception must not be null");
        synchronized (this.f13880a) {
            m();
            this.f13882c = true;
            this.f13885f = exc;
        }
        this.f13881b.b(this);
    }

    public final void l() {
        synchronized (this.f13880a) {
            try {
                if (this.f13882c) {
                    return;
                }
                this.f13882c = true;
                this.f13883d = true;
                this.f13881b.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void m() {
        if (this.f13882c) {
            int i10 = DuplicateTaskCompletionException.f13854i;
            if (!g()) {
                throw new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
            }
            Exception excE = e();
        }
    }

    public final void n() {
        synchronized (this.f13880a) {
            try {
                if (this.f13882c) {
                    this.f13881b.b(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
