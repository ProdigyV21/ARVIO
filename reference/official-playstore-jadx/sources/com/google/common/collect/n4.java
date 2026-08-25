package com.google.common.collect;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public final class n4 implements l3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TreeMap f14074a = new TreeMap();

    @Override // com.google.common.collect.l3
    public final Map a() {
        return new v2(this, this.f14074a.values());
    }

    public final Map.Entry b(Long l10) {
        Map.Entry entryFloorEntry = this.f14074a.floorEntry(new n0(l10, 2));
        if (entryFloorEntry == null) {
            return null;
        }
        j3 j3Var = ((m4) entryFloorEntry.getValue()).f14060i;
        j3Var.getClass();
        if (!j3Var.f14035i.j(l10) || j3Var.f14036l.j(l10)) {
            return null;
        }
        return (Map.Entry) entryFloorEntry.getValue();
    }

    public final void c(j3 j3Var, Object obj) {
        o0 o0Var = j3Var.f14035i;
        o0 o0Var2 = j3Var.f14036l;
        boolean zEquals = o0Var.equals(o0Var2);
        o0 o0Var3 = j3Var.f14035i;
        if (zEquals) {
            return;
        }
        obj.getClass();
        boolean zEquals2 = o0Var3.equals(o0Var2);
        TreeMap treeMap = this.f14074a;
        if (!zEquals2) {
            Map.Entry entryLowerEntry = treeMap.lowerEntry(o0Var3);
            if (entryLowerEntry != null) {
                j3 j3Var2 = ((m4) entryLowerEntry.getValue()).f14060i;
                o0 o0Var4 = j3Var2.f14036l;
                o0 o0Var5 = j3Var2.f14036l;
                if (o0Var4.compareTo(o0Var3) > 0) {
                    if (o0Var5.compareTo(o0Var2) > 0) {
                        d(o0Var2, o0Var5, ((m4) entryLowerEntry.getValue()).f14061l);
                    }
                    d(j3Var2.f14035i, o0Var3, ((m4) entryLowerEntry.getValue()).f14061l);
                }
            }
            Map.Entry entryLowerEntry2 = treeMap.lowerEntry(o0Var2);
            if (entryLowerEntry2 != null) {
                m4 m4Var = (m4) entryLowerEntry2.getValue();
                if (m4Var.f14060i.f14036l.compareTo(o0Var2) > 0) {
                    d(o0Var2, m4Var.f14060i.f14036l, ((m4) entryLowerEntry2.getValue()).f14061l);
                }
            }
            treeMap.subMap(o0Var3, o0Var2).clear();
        }
        treeMap.put(o0Var3, new m4(j3Var, obj));
    }

    public final void d(o0 o0Var, o0 o0Var2, Object obj) {
        this.f14074a.put(o0Var, new m4(new j3(o0Var, o0Var2), obj));
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof l3)) {
            return false;
        }
        return ((AbstractMap) a()).equals(((l3) obj).a());
    }

    public final int hashCode() {
        return ((AbstractMap) a()).hashCode();
    }

    public final String toString() {
        return this.f14074a.values().toString();
    }
}
