package com.caverock.androidsvg;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList f12377a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12378b = 0;

    public final void a() {
        this.f12378b += 1000;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        Iterator it = this.f12377a.iterator();
        while (it.hasNext()) {
            sb2.append((o) it.next());
            sb2.append(' ');
        }
        sb2.append('[');
        return a0.c.o(sb2, this.f12378b, ']');
    }
}
