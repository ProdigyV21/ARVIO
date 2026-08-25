package com.google.firebase.components;

/* JADX INFO: loaded from: classes4.dex */
public final class o implements m5.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f14285c = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f14286a = f14285c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile m5.b f14287b;

    public o(m5.b bVar) {
        this.f14287b = bVar;
    }

    @Override // m5.b
    public final Object get() {
        Object obj;
        Object obj2 = this.f14286a;
        Object obj3 = f14285c;
        if (obj2 != obj3) {
            return obj2;
        }
        synchronized (this) {
            try {
                obj = this.f14286a;
                if (obj == obj3) {
                    obj = this.f14287b.get();
                    this.f14286a = obj;
                    this.f14287b = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }
}
