package com.google.android.datatransport.runtime;

import android.content.Context;
import androidx.appcompat.app.i1;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class y implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f12660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Provider f12661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e3.d f12662d;

    public /* synthetic */ y(Provider provider, Provider provider2, e3.d dVar, int i10) {
        this.f12659a = i10;
        this.f12660b = provider;
        this.f12661c = provider2;
        this.f12662d = dVar;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        switch (this.f12659a) {
            case 0:
                return new w(new io.sentry.util.l(8), new io.sentry.util.l(7), (j3.c) ((j3.b) this.f12660b).get(), (k3.f) ((k3.g) this.f12661c).get(), (k3.h) ((k3.i) this.f12662d).get());
            default:
                return new i1((Context) this.f12660b.get(), (com.google.android.datatransport.runtime.scheduling.persistence.d) this.f12661c.get(), (k3.a) ((p) this.f12662d).get(), 21);
        }
    }
}
