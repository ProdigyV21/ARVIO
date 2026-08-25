package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f14040a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14041b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j1 f14042c;

    public k1(int i10) {
        this.f14040a = new Object[i10 * 2];
    }

    public final t3 a(boolean z) {
        j1 j1Var;
        j1 j1Var2;
        if (z && (j1Var2 = this.f14042c) != null) {
            throw j1Var2.a();
        }
        t3 t3VarJ = t3.j(this.f14041b, this.f14040a, this);
        if (!z || (j1Var = this.f14042c) == null) {
            return t3VarJ;
        }
        throw j1Var.a();
    }

    public l1 b() {
        return a(false);
    }

    public k1 c(Object obj, Object obj2) {
        int i10 = (this.f14041b + 1) * 2;
        Object[] objArr = this.f14040a;
        if (i10 > objArr.length) {
            this.f14040a = Arrays.copyOf(objArr, b1.b(objArr.length, i10));
        }
        a0.b(obj, obj2);
        Object[] objArr2 = this.f14040a;
        int i11 = this.f14041b;
        int i12 = i11 * 2;
        objArr2[i12] = obj;
        objArr2[i12 + 1] = obj2;
        this.f14041b = i11 + 1;
        return this;
    }

    public void d(Map.Entry entry) {
        c(entry.getKey(), entry.getValue());
    }

    public k1 e(Iterable iterable) {
        if (iterable instanceof Collection) {
            int size = (((Collection) iterable).size() + this.f14041b) * 2;
            Object[] objArr = this.f14040a;
            if (size > objArr.length) {
                this.f14040a = Arrays.copyOf(objArr, b1.b(objArr.length, size));
            }
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            d((Map.Entry) it.next());
        }
        return this;
    }
}
