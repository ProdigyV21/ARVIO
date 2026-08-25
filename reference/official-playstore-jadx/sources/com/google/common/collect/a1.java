package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a1 extends b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f13927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f13928b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f13929c;

    public a1(int i10) {
        a0.c(i10, "initialCapacity");
        this.f13927a = new Object[i10];
        this.f13928b = 0;
    }

    public final void c(Object obj) {
        obj.getClass();
        e(1);
        Object[] objArr = this.f13927a;
        int i10 = this.f13928b;
        this.f13928b = i10 + 1;
        objArr[i10] = obj;
    }

    public final void d(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            e(collection.size());
            if (collection instanceof c1) {
                this.f13928b = ((c1) collection).b(this.f13928b, this.f13927a);
                return;
            }
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    public final void e(int i10) {
        Object[] objArr = this.f13927a;
        int iB = b1.b(objArr.length, this.f13928b + i10);
        if (iB > objArr.length || this.f13929c) {
            this.f13927a = Arrays.copyOf(this.f13927a, iB);
            this.f13929c = false;
        }
    }
}
