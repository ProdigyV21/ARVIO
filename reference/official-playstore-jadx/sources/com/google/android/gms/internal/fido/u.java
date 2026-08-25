package com.google.android.gms.internal.fido;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class u extends s {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f13824n;

    public u(v vVar) {
        this.f13824n = vVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f13824n.get(entry.getKey());
            if (obj2 != null && obj2.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fido.l
    public final h0 d() {
        return h().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.s, java.util.Collection, java.util.Set
    public final int hashCode() {
        return b.b(this.f13824n.entrySet());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return h().listIterator(0);
    }

    @Override // com.google.android.gms.internal.fido.s
    public final q k() {
        return new t(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13824n.f13828n.size();
    }
}
