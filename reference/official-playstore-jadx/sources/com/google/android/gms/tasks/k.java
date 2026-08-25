package com.google.android.gms.tasks;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes4.dex */
public final class k implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CountDownLatch f13858i = new CountDownLatch(1);

    @Override // com.google.android.gms.tasks.b
    public final void b() {
        this.f13858i.countDown();
    }

    @Override // com.google.android.gms.tasks.d
    public final void onFailure(Exception exc) {
        this.f13858i.countDown();
    }

    @Override // com.google.android.gms.tasks.e
    public final void onSuccess(Object obj) {
        this.f13858i.countDown();
    }
}
