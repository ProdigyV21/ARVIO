package com.squareup.moshi;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class t extends AbstractSet {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14469i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ v f14470l;

    public /* synthetic */ t(v vVar, int i10) {
        this.f14469i = i10;
        this.f14470l = vVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        switch (this.f14469i) {
            case 0:
                this.f14470l.clear();
                break;
            default:
                this.f14470l.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        u uVarB;
        Object obj2;
        Object value;
        switch (this.f14469i) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                v vVar = this.f14470l;
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                u uVar = null;
                if (key != null) {
                    try {
                        uVarB = vVar.b(key, false);
                    } catch (ClassCastException unused) {
                        uVarB = null;
                    }
                    break;
                } else {
                    uVarB = null;
                }
                if (uVarB != null && ((obj2 = uVarB.f14478r) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                    uVar = uVarB;
                }
                return uVar != null;
            default:
                return this.f14470l.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f14469i) {
            case 0:
                return new s(this.f14470l, 0);
            default:
                return new s(this.f14470l, 1);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        u uVarB;
        Object obj2;
        Object value;
        switch (this.f14469i) {
            case 0:
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    v vVar = this.f14470l;
                    u uVar = null;
                    if (key != null) {
                        try {
                            uVarB = vVar.b(key, false);
                        } catch (ClassCastException unused) {
                            uVarB = null;
                        }
                    } else {
                        uVarB = null;
                    }
                    if (uVarB != null && ((obj2 = uVarB.f14478r) == (value = entry.getValue()) || (obj2 != null && obj2.equals(value)))) {
                        uVar = uVarB;
                    }
                    if (uVar != null) {
                        vVar.e(uVar, true);
                        break;
                    }
                    break;
                }
                break;
            default:
                v vVar2 = this.f14470l;
                u uVarB2 = null;
                if (obj != null) {
                    try {
                        uVarB2 = vVar2.b(obj, false);
                        break;
                    } catch (ClassCastException unused2) {
                    }
                }
                if (uVarB2 != null) {
                    vVar2.e(uVarB2, true);
                }
                if (uVarB2 != null) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.f14469i) {
        }
        return this.f14470l.f14484n;
    }
}
