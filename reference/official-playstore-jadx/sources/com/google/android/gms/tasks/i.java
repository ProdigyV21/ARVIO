package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes4.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f13855a = new t();

    public final void a(Exception exc) {
        this.f13855a.k(exc);
    }

    public final void b(Object obj) {
        this.f13855a.j(obj);
    }

    public final boolean c(Exception exc) {
        t tVar = this.f13855a;
        tVar.getClass();
        com.google.android.gms.common.internal.t.j(exc, "Exception must not be null");
        synchronized (tVar.f13880a) {
            try {
                if (tVar.f13882c) {
                    return false;
                }
                tVar.f13882c = true;
                tVar.f13885f = exc;
                tVar.f13881b.b(tVar);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean d(Object obj) {
        t tVar = this.f13855a;
        synchronized (tVar.f13880a) {
            try {
                if (tVar.f13882c) {
                    return false;
                }
                tVar.f13882c = true;
                tVar.f13884e = obj;
                tVar.f13881b.b(tVar);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
