package com.google.android.gms.tasks;

import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class m implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13859i = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13860l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t f13861m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f13862n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f13863o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f13864p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Exception f13865q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f13866r;

    public m(int i10, t tVar) {
        this.f13860l = i10;
        this.f13861m = tVar;
    }

    public final void a() {
        int i10 = this.f13862n + this.f13863o + this.f13864p;
        int i11 = this.f13860l;
        if (i10 == i11) {
            Exception exc = this.f13865q;
            t tVar = this.f13861m;
            if (exc == null) {
                if (this.f13866r) {
                    tVar.l();
                    return;
                } else {
                    tVar.j(null);
                    return;
                }
            }
            int i12 = this.f13863o;
            int length = String.valueOf(i12).length();
            StringBuilder sb2 = new StringBuilder(String.valueOf(i11).length() + length + 8 + 24);
            sb2.append(i12);
            sb2.append(" out of ");
            sb2.append(i11);
            sb2.append(" underlying tasks failed");
            tVar.k(new ExecutionException(sb2.toString(), this.f13865q));
        }
    }

    @Override // com.google.android.gms.tasks.b
    public final void b() {
        synchronized (this.f13859i) {
            this.f13864p++;
            this.f13866r = true;
            a();
        }
    }

    @Override // com.google.android.gms.tasks.d
    public final void onFailure(Exception exc) {
        synchronized (this.f13859i) {
            this.f13863o++;
            this.f13865q = exc;
            a();
        }
    }

    @Override // com.google.android.gms.tasks.e
    public final void onSuccess(Object obj) {
        synchronized (this.f13859i) {
            this.f13862n++;
            a();
        }
    }
}
