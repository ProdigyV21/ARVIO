package com.google.common.base;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 implements c0, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient Object f13898i = new Object();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final c0 f13899l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile transient boolean f13900m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient Object f13901n;

    public d0(c0 c0Var) {
        this.f13899l = c0Var;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        if (!this.f13900m) {
            synchronized (this.f13898i) {
                try {
                    if (!this.f13900m) {
                        Object obj = this.f13899l.get();
                        this.f13901n = obj;
                        this.f13900m = true;
                        return obj;
                    }
                } finally {
                }
            }
        }
        return this.f13901n;
    }

    public final String toString() {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Suppliers.memoize(");
        if (this.f13900m) {
            obj = "<supplier that returned " + this.f13901n + ">";
        } else {
            obj = this.f13899l;
        }
        sb2.append(obj);
        sb2.append(")");
        return sb2.toString();
    }
}
