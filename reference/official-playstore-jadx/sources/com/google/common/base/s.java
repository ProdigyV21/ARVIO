package com.google.common.base;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class s implements r, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f13912i;

    public s(List list) {
        this.f13912i = list;
    }

    @Override // com.google.common.base.r
    public final boolean apply(Object obj) {
        int i10 = 0;
        while (true) {
            List list = this.f13912i;
            if (i10 >= list.size()) {
                return true;
            }
            if (!((r) list.get(i10)).apply(obj)) {
                return false;
            }
            i10++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof s) {
            return this.f13912i.equals(((s) obj).f13912i);
        }
        return false;
    }

    public final int hashCode() {
        return this.f13912i.hashCode() + 306654252;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Predicates.and(");
        boolean z = true;
        for (Object obj : this.f13912i) {
            if (!z) {
                sb2.append(',');
            }
            sb2.append(obj);
            z = false;
        }
        sb2.append(')');
        return sb2.toString();
    }
}
