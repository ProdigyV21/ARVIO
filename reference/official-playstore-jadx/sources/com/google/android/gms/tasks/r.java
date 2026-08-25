package com.google.android.gms.tasks;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes4.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f13876a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayDeque f13877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f13878c;

    public final void a(q qVar) {
        synchronized (this.f13876a) {
            try {
                if (this.f13877b == null) {
                    this.f13877b = new ArrayDeque();
                }
                this.f13877b.add(qVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(h hVar) {
        q qVar;
        synchronized (this.f13876a) {
            if (this.f13877b != null && !this.f13878c) {
                this.f13878c = true;
                while (true) {
                    synchronized (this.f13876a) {
                        try {
                            qVar = (q) this.f13877b.poll();
                            if (qVar == null) {
                                this.f13878c = false;
                                return;
                            }
                        } finally {
                        }
                    }
                    qVar.a(hVar);
                }
            }
        }
    }
}
