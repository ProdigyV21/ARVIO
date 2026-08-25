package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class o1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f13801i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final v f13802l;

    public o1(v vVar) throws zzhf {
        vVar.getClass();
        this.f13802l = vVar;
        h0 h0VarD = vVar.entrySet().d();
        int i10 = 0;
        while (h0VarD.hasNext()) {
            Map.Entry entry = (Map.Entry) h0VarD.next();
            int iD = ((q1) entry.getKey()).d();
            i10 = i10 < iD ? iD : i10;
            int iD2 = ((q1) entry.getValue()).d();
            if (i10 < iD2) {
                i10 = iD2;
            }
        }
        int i11 = i10 + 1;
        this.f13801i = i11;
        if (i11 > 8) {
            throw new zzhf("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i((byte) -96);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int iCompareTo;
        q1 q1Var = (q1) obj;
        int iA = q1Var.a();
        int i10 = q1.i((byte) -96);
        if (i10 != iA) {
            return i10 - q1Var.a();
        }
        v vVar = ((o1) q1Var).f13802l;
        v vVar2 = this.f13802l;
        if (vVar2.f13828n.size() != vVar.f13828n.size()) {
            return vVar2.f13828n.size() - vVar.f13828n.size();
        }
        h0 h0VarD = vVar2.entrySet().d();
        h0 h0VarD2 = vVar.entrySet().d();
        do {
            if (!h0VarD.hasNext() && !h0VarD2.hasNext()) {
                return 0;
            }
            Map.Entry entry = (Map.Entry) h0VarD.next();
            Map.Entry entry2 = (Map.Entry) h0VarD2.next();
            int iCompareTo2 = ((q1) entry.getKey()).compareTo((q1) entry2.getKey());
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
            iCompareTo = ((q1) entry.getValue()).compareTo((q1) entry2.getValue());
        } while (iCompareTo == 0);
        return iCompareTo;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int d() {
        return this.f13801i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && o1.class == obj.getClass()) {
            return this.f13802l.equals(((o1) obj).f13802l);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(q1.i((byte) -96)), this.f13802l});
    }

    public final String toString() {
        v vVar = this.f13802l;
        if (vVar.isEmpty()) {
            return "{}";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        h0 h0VarD = vVar.entrySet().d();
        while (h0VarD.hasNext()) {
            Map.Entry entry = (Map.Entry) h0VarD.next();
            linkedHashMap.put(((q1) entry.getKey()).toString().replace("\n", "\n  "), ((q1) entry.getValue()).toString().replace("\n", "\n  "));
        }
        a1.a aVar = new a1.a();
        StringBuilder sb2 = new StringBuilder("{\n  ");
        try {
            b.g(sb2, linkedHashMap.entrySet().iterator(), aVar);
            sb2.append("\n}");
            return sb2.toString();
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }
}
