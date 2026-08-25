package com.google.firebase.components;

import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f14248a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f14249b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet f14250c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f14251d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f14252e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f14253f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f14254g;

    public a(Class cls, Class[] clsArr) {
        HashSet hashSet = new HashSet();
        this.f14249b = hashSet;
        this.f14250c = new HashSet();
        this.f14251d = 0;
        this.f14252e = 0;
        this.f14254g = new HashSet();
        hashSet.add(s.a(cls));
        for (Class cls2 : clsArr) {
            androidx.work.impl.t.f(cls2, "Null interface");
            this.f14249b.add(s.a(cls2));
        }
    }

    public final void a(m mVar) {
        if (this.f14249b.contains(mVar.f14281a)) {
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }
        this.f14250c.add(mVar);
    }

    public final b b() {
        if (this.f14253f != null) {
            return new b(this.f14248a, new HashSet(this.f14249b), new HashSet(this.f14250c), this.f14251d, this.f14252e, this.f14253f, this.f14254g);
        }
        throw new IllegalStateException("Missing required property: factory.");
    }

    public a(s sVar, s[] sVarArr) {
        HashSet hashSet = new HashSet();
        this.f14249b = hashSet;
        this.f14250c = new HashSet();
        this.f14251d = 0;
        this.f14252e = 0;
        this.f14254g = new HashSet();
        hashSet.add(sVar);
        for (s sVar2 : sVarArr) {
            androidx.work.impl.t.f(sVar2, "Null interface");
        }
        Collections.addAll(this.f14249b, sVarArr);
    }
}
