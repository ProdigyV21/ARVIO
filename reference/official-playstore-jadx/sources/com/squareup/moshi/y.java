package com.squareup.moshi;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f14494a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque f14495b = new ArrayDeque();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f14496c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ z f14497d;

    public y(z zVar) {
        this.f14497d = zVar;
    }

    public final IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        if (!this.f14496c) {
            this.f14496c = true;
            ArrayDeque arrayDeque = this.f14495b;
            if (arrayDeque.size() != 1 || ((x) arrayDeque.getFirst()).f14491b != null) {
                StringBuilder sb2 = new StringBuilder(illegalArgumentException.getMessage());
                Iterator itDescendingIterator = arrayDeque.descendingIterator();
                while (itDescendingIterator.hasNext()) {
                    x xVar = (x) itDescendingIterator.next();
                    sb2.append("\nfor ");
                    Type type = xVar.f14490a;
                    String str = xVar.f14491b;
                    sb2.append(type);
                    if (str != null) {
                        sb2.append(' ');
                        sb2.append(str);
                    }
                }
                return new IllegalArgumentException(sb2.toString(), illegalArgumentException);
            }
        }
        return illegalArgumentException;
    }

    public final void b(boolean z) {
        this.f14495b.removeLast();
        if (this.f14495b.isEmpty()) {
            this.f14497d.f14500b.remove();
            if (z) {
                synchronized (this.f14497d.f14501c) {
                    try {
                        int size = this.f14494a.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            x xVar = (x) this.f14494a.get(i10);
                            l lVar = (l) this.f14497d.f14501c.put(xVar.f14492c, xVar.f14493d);
                            if (lVar != null) {
                                xVar.f14493d = lVar;
                                this.f14497d.f14501c.put(xVar.f14492c, lVar);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
    }
}
