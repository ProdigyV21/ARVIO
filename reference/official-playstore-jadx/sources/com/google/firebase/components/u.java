package com.google.firebase.components;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class u implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f14298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f14299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f14300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set f14301d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f14302e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set f14303f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f14304g;

    public u(b bVar, c cVar) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        Set<m> set = bVar.f14257c;
        Set set2 = bVar.f14261g;
        for (m mVar : set) {
            int i10 = mVar.f14283c;
            int i11 = mVar.f14282b;
            boolean z = i10 == 0;
            s sVar = mVar.f14281a;
            if (z) {
                if (i11 == 2) {
                    hashSet4.add(sVar);
                } else {
                    hashSet.add(sVar);
                }
            } else if (i10 == 2) {
                hashSet3.add(sVar);
            } else if (i11 == 2) {
                hashSet5.add(sVar);
            } else {
                hashSet2.add(sVar);
            }
        }
        if (!set2.isEmpty()) {
            hashSet.add(s.a(k5.b.class));
        }
        this.f14298a = Collections.unmodifiableSet(hashSet);
        this.f14299b = Collections.unmodifiableSet(hashSet2);
        this.f14300c = Collections.unmodifiableSet(hashSet3);
        this.f14301d = Collections.unmodifiableSet(hashSet4);
        this.f14302e = Collections.unmodifiableSet(hashSet5);
        this.f14303f = set2;
        this.f14304g = cVar;
    }

    @Override // com.google.firebase.components.c
    public final Object a(Class cls) {
        if (this.f14298a.contains(s.a(cls))) {
            Object objA = this.f14304g.a(cls);
            return !cls.equals(k5.b.class) ? objA : new t(this.f14303f, (k5.b) objA);
        }
        throw new DependencyException("Attempting to request an undeclared dependency " + cls + ".");
    }

    @Override // com.google.firebase.components.c
    public final m5.b b(s sVar) {
        if (this.f14299b.contains(sVar)) {
            return this.f14304g.b(sVar);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Provider<" + sVar + ">.");
    }

    @Override // com.google.firebase.components.c
    public final Set c(s sVar) {
        if (this.f14301d.contains(sVar)) {
            return this.f14304g.c(sVar);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Set<" + sVar + ">.");
    }

    @Override // com.google.firebase.components.c
    public final Object d(s sVar) {
        if (this.f14298a.contains(sVar)) {
            return this.f14304g.d(sVar);
        }
        throw new DependencyException("Attempting to request an undeclared dependency " + sVar + ".");
    }

    @Override // com.google.firebase.components.c
    public final m5.b e(Class cls) {
        return b(s.a(cls));
    }

    @Override // com.google.firebase.components.c
    public final m5.a f(s sVar) {
        if (this.f14300c.contains(sVar)) {
            return this.f14304g.f(sVar);
        }
        throw new DependencyException("Attempting to request an undeclared dependency Deferred<" + sVar + ">.");
    }

    @Override // com.google.firebase.components.c
    public final m5.a g(Class cls) {
        return f(s.a(cls));
    }

    public final Set h(Class cls) {
        return c(s.a(cls));
    }
}
