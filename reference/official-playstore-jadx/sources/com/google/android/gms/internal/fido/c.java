package com.google.android.gms.internal.fido;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13759i;

    public /* synthetic */ c(Object obj) {
        this.f13759i = obj;
    }

    public Object a() {
        if (b.f13752l == null) {
            b.f13752l = new a();
        }
        synchronized (b.f13751i) {
        }
        throw new IllegalStateException("Must call PhenotypeContext.setContext() first");
    }
}
