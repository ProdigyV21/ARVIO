package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class d2 implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d2 f13965i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ d2[] f13966l;

    static {
        d2 d2Var = new d2("INSTANCE", 0);
        f13965i = d2Var;
        f13966l = new d2[]{d2Var};
    }

    public static d2 valueOf(String str) {
        return (d2) Enum.valueOf(d2.class, str);
    }

    public static d2[] values() {
        return (d2[]) f13966l.clone();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        ac.b.t(false, "no calls to next() since the last call to remove()");
    }
}
