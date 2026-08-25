package com.google.android.gms.tasks;

import com.google.common.util.concurrent.w0;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class n implements q, e, d, b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13867i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Executor f13868l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f13869m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t f13870n;

    public /* synthetic */ n(Executor executor, a aVar, t tVar, int i10) {
        this.f13867i = i10;
        this.f13868l = executor;
        this.f13869m = aVar;
        this.f13870n = tVar;
    }

    @Override // com.google.android.gms.tasks.q
    public final void a(h hVar) {
        switch (this.f13867i) {
            case 0:
                this.f13868l.execute(new w0(16, this, false, hVar));
                break;
            default:
                this.f13868l.execute(new w0(17, this, false, hVar));
                break;
        }
    }

    @Override // com.google.android.gms.tasks.b
    public void b() {
        this.f13870n.l();
    }

    @Override // com.google.android.gms.tasks.d
    public void onFailure(Exception exc) {
        this.f13870n.k(exc);
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        this.f13870n.j(obj);
    }
}
