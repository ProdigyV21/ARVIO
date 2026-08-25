package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class a2 extends o4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13930i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f13931l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13932m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Iterator f13933n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f13934o;

    public a2() {
        this.f13930i = 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Iterator
    public final boolean hasNext() {
        Object next;
        ac.b.s(this.f13930i != 4);
        int iC = h.f0.c(this.f13930i);
        if (iC == 0) {
            return true;
        }
        if (iC != 2) {
            this.f13930i = 4;
            switch (this.f13932m) {
                case 0:
                    do {
                        Iterator it = this.f13933n;
                        if (!it.hasNext()) {
                            this.f13930i = 3;
                            next = null;
                        } else {
                            next = it.next();
                        }
                        break;
                    } while (!((com.google.common.base.r) this.f13934o).apply(next));
                    break;
                default:
                    do {
                        Iterator it2 = this.f13933n;
                        if (!it2.hasNext()) {
                            this.f13930i = 3;
                            next = null;
                        } else {
                            next = it2.next();
                        }
                        break;
                    } while (!((a4) this.f13934o).f13938l.contains(next));
                    break;
            }
            this.f13931l = next;
            if (this.f13930i != 3) {
                this.f13930i = 1;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f13930i = 2;
        Object obj = this.f13931l;
        this.f13931l = null;
        return obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a2(Iterator it, com.google.common.base.r rVar) {
        this();
        this.f13932m = 0;
        this.f13933n = it;
        this.f13934o = rVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a2(a4 a4Var) {
        this();
        this.f13932m = 1;
        this.f13934o = a4Var;
        this.f13933n = a4Var.f13937i.iterator();
    }
}
