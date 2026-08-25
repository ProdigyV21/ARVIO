package com.google.common.collect;

/* JADX INFO: loaded from: classes4.dex */
public final class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f14030b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f14031c;

    public j1(Object obj, Object obj2, Object obj3) {
        this.f14029a = obj;
        this.f14030b = obj2;
        this.f14031c = obj3;
    }

    public final IllegalArgumentException a() {
        StringBuilder sb2 = new StringBuilder("Multiple entries with same key: ");
        Object obj = this.f14029a;
        sb2.append(obj);
        sb2.append("=");
        sb2.append(this.f14030b);
        sb2.append(" and ");
        sb2.append(obj);
        sb2.append("=");
        sb2.append(this.f14031c);
        return new IllegalArgumentException(sb2.toString());
    }
}
