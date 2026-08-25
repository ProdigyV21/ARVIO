package com.google.common.collect;

import j$.util.Objects;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class d0 extends AbstractSet {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13961i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f0 f13962l;

    public /* synthetic */ d0(f0 f0Var, int i10) {
        this.f13961i = i10;
        this.f13962l = f0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f13961i) {
            case 0:
                this.f13962l.clear();
                break;
            default:
                this.f13962l.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        switch (this.f13961i) {
            case 0:
                f0 f0Var = this.f13962l;
                Map mapE = f0Var.e();
                if (mapE != null) {
                    return mapE.entrySet().contains(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    int iH = f0Var.h(entry.getKey());
                    if (iH != -1 && a.a.L(f0Var.o()[iH], entry.getValue())) {
                        return true;
                    }
                }
                return false;
            default:
                return this.f13962l.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f13961i) {
            case 0:
                f0 f0Var = this.f13962l;
                Map mapE = f0Var.e();
                return mapE != null ? mapE.entrySet().iterator() : new c0(f0Var, 1);
            default:
                f0 f0Var2 = this.f13962l;
                Map mapE2 = f0Var2.e();
                return mapE2 != null ? mapE2.keySet().iterator() : new c0(f0Var2, 0);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        switch (this.f13961i) {
            case 0:
                f0 f0Var = this.f13962l;
                Map mapE = f0Var.e();
                if (mapE != null) {
                    return mapE.entrySet().remove(obj);
                }
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    if (!f0Var.j()) {
                        int iG = f0Var.g();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        Object obj2 = f0Var.f13988i;
                        Objects.requireNonNull(obj2);
                        int iQ = a0.q(key, value, iG, obj2, f0Var.l(), f0Var.m(), f0Var.o());
                        if (iQ != -1) {
                            f0Var.i(iQ, iG);
                            f0Var.f13993p--;
                            f0Var.f13992o += 32;
                            return true;
                        }
                    }
                }
                return false;
            default:
                f0 f0Var2 = this.f13962l;
                Map mapE2 = f0Var2.e();
                return mapE2 != null ? mapE2.keySet().remove(obj) : f0Var2.k(obj) != f0.f13987t;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.f13961i) {
        }
        return this.f13962l.size();
    }
}
