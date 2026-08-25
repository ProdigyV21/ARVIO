package com.google.firebase.components;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class p implements m5.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Set f14288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Set f14289b;

    public final synchronized void a() {
        try {
            Iterator it = this.f14288a.iterator();
            while (it.hasNext()) {
                this.f14289b.add(((m5.b) it.next()).get());
            }
            this.f14288a = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // m5.b
    public final Object get() {
        if (this.f14289b == null) {
            synchronized (this) {
                try {
                    if (this.f14289b == null) {
                        this.f14289b = Collections.newSetFromMap(new ConcurrentHashMap());
                        a();
                    }
                } finally {
                }
            }
        }
        return Collections.unmodifiableSet(this.f14289b);
    }
}
