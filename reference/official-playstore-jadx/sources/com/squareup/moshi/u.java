package com.squareup.moshi;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class u implements Map.Entry {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public u f14471i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public u f14472l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public u f14473m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u f14474n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public u f14475o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f14476p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f14477q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f14478r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f14479s;

    public u() {
        this.f14476p = null;
        this.f14477q = -1;
        this.f14475o = this;
        this.f14474n = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f14476p;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.f14478r;
                if (obj3 == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (obj3.equals(entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f14476p;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f14478r;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f14476p;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f14478r;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.f14478r;
        this.f14478r = obj;
        return obj2;
    }

    public final String toString() {
        return this.f14476p + "=" + this.f14478r;
    }

    public u(u uVar, Object obj, int i10, u uVar2, u uVar3) {
        this.f14471i = uVar;
        this.f14476p = obj;
        this.f14477q = i10;
        this.f14479s = 1;
        this.f14474n = uVar2;
        this.f14475o = uVar3;
        uVar3.f14474n = this;
        uVar2.f14475o = this;
    }
}
