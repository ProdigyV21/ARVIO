package com.google.firebase.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f14256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f14257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f14258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f14260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Set f14261g;

    public b(String str, Set set, Set set2, int i10, int i11, f fVar, Set set3) {
        this.f14255a = str;
        this.f14256b = Collections.unmodifiableSet(set);
        this.f14257c = Collections.unmodifiableSet(set2);
        this.f14258d = i10;
        this.f14259e = i11;
        this.f14260f = fVar;
        this.f14261g = Collections.unmodifiableSet(set3);
    }

    public static a a(s sVar) {
        return new a(sVar, new s[0]);
    }

    public static a b(Class cls) {
        return new a(cls, new Class[0]);
    }

    public static b c(Object obj, Class cls, Class... clsArr) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(s.a(cls));
        for (Class cls2 : clsArr) {
            androidx.work.impl.t.f(cls2, "Null interface");
            hashSet.add(s.a(cls2));
        }
        return new b(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 0, new a3.b(obj, 16), hashSet3);
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.f14256b.toArray()) + ">{" + this.f14258d + ", type=" + this.f14259e + ", deps=" + Arrays.toString(this.f14257c.toArray()) + "}";
    }
}
