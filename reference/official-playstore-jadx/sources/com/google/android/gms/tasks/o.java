package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes4.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ p f13871i;

    public o(p pVar) {
        this.f13871i = pVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        p pVar = this.f13871i;
        synchronized (pVar.f13874m) {
            try {
                b bVar = (b) pVar.f13875n;
                if (bVar != null) {
                    bVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
