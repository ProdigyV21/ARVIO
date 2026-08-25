package com.google.common.collect;

import j$.util.Objects;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class m1 extends o4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o4 f14057i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f14058l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o4 f14059m;

    public m1(i1 i1Var) {
        t3 t3Var = i1Var.f14025o;
        w1 w1VarC = t3Var.f14047i;
        if (w1VarC == null) {
            w1VarC = t3Var.c();
            t3Var.f14047i = w1VarC;
        }
        this.f14057i = w1VarC.iterator();
        this.f14058l = null;
        this.f14059m = c2.f13957n;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f14059m.hasNext() || this.f14057i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f14059m.hasNext()) {
            Map.Entry entry = (Map.Entry) this.f14057i.next();
            this.f14058l = entry.getKey();
            this.f14059m = ((c1) entry.getValue()).iterator();
        }
        Object obj = this.f14058l;
        Objects.requireNonNull(obj);
        return new d1(obj, this.f14059m.next());
    }
}
