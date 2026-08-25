package com.google.firebase.components;

/* JADX INFO: loaded from: classes4.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f14294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f14295b;

    public s(Class cls, Class cls2) {
        this.f14294a = cls;
        this.f14295b = cls2;
    }

    public static s a(Class cls) {
        return new s(r.class, cls);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s.class != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        if (this.f14295b.equals(sVar.f14295b)) {
            return this.f14294a.equals(sVar.f14294a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f14294a.hashCode() + (this.f14295b.hashCode() * 31);
    }

    public final String toString() {
        Class cls = this.f14295b;
        Class cls2 = this.f14294a;
        if (cls2 == r.class) {
            return cls.getName();
        }
        return "@" + cls2.getName() + " " + cls.getName();
    }
}
