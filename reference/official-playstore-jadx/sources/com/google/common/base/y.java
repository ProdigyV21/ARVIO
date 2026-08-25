package com.google.common.base;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class y implements Iterator {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f13916l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f13917m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f13918n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f13920p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f13921q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ a0 f13922r;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f13915i = 2;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f13919o = 0;

    public y(a0 a0Var, b0 b0Var, CharSequence charSequence, int i10) {
        this.f13921q = i10;
        this.f13922r = a0Var;
        this.f13918n = b0Var.f13893a;
        this.f13920p = b0Var.f13895c;
        this.f13917m = charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r3 = r3 + 1;
     */
    @Override // java.util.Iterator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean hasNext() {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.y.hasNext():boolean");
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f13915i = 2;
        String str = this.f13916l;
        this.f13916l = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
