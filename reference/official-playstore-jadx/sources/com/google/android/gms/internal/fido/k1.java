package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class k1 extends q1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q f13790i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f13791l;

    public k1(b0 b0Var) throws zzhf {
        b0Var.getClass();
        this.f13790i = b0Var;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            q qVar = this.f13790i;
            if (i10 >= qVar.size()) {
                break;
            }
            int iD = ((q1) qVar.get(i10)).d();
            if (i11 < iD) {
                i11 = iD;
            }
            i10++;
        }
        int i12 = i11 + 1;
        this.f13791l = i12;
        if (i12 > 8) {
            throw new zzhf("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int a() {
        return q1.i((byte) -128);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        q1 q1Var = (q1) obj;
        int iA = q1Var.a();
        int i10 = q1.i((byte) -128);
        if (i10 != iA) {
            return i10 - q1Var.a();
        }
        q qVar = ((k1) q1Var).f13790i;
        q qVar2 = this.f13790i;
        if (qVar2.size() != qVar.size()) {
            return qVar2.size() - qVar.size();
        }
        for (int i11 = 0; i11 < qVar2.size(); i11++) {
            int iCompareTo = ((q1) qVar2.get(i11)).compareTo((q1) qVar.get(i11));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.fido.q1
    public final int d() {
        return this.f13791l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && k1.class == obj.getClass()) {
            return this.f13790i.equals(((k1) obj).f13790i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(q1.i((byte) -128)), this.f13790i});
    }

    public final String toString() {
        q qVar = this.f13790i;
        if (qVar.isEmpty()) {
            return "[]";
        }
        ArrayList arrayList = new ArrayList();
        int size = qVar.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(((q1) qVar.get(i10)).toString().replace("\n", "\n  "));
        }
        StringBuilder sb2 = new StringBuilder("[\n  ");
        Iterator it = arrayList.iterator();
        try {
            if (it.hasNext()) {
                sb2.append(a1.a.t(it.next()));
                while (it.hasNext()) {
                    sb2.append((CharSequence) ",\n  ");
                    sb2.append(a1.a.t(it.next()));
                }
            }
            sb2.append("\n]");
            return sb2.toString();
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }
}
