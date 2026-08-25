package com.squareup.moshi;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ArrayList f14498d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f14499a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal f14500b = new ThreadLocal();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f14501c = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList(5);
        f14498d = arrayList;
        arrayList.add(e0.f14422a);
        arrayList.add(h.f14439c);
        arrayList.add(b.f14400e);
        arrayList.add(b.f14399d);
        arrayList.add(a0.f14398a);
        arrayList.add(g.f14435d);
    }

    public z(w wVar) {
        ArrayList arrayList = wVar.f14489a;
        int size = arrayList.size();
        ArrayList arrayList2 = f14498d;
        ArrayList arrayList3 = new ArrayList(arrayList2.size() + size);
        arrayList3.addAll(arrayList);
        arrayList3.addAll(arrayList2);
        this.f14499a = Collections.unmodifiableList(arrayList3);
    }

    public final l a(Type type, Set set, String str) {
        l lVar;
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type typeA = j6.e.a(type);
        if (typeA instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) typeA;
            if (wildcardType.getLowerBounds().length == 0) {
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds.length != 1) {
                    throw new IllegalArgumentException();
                }
                typeA = upperBounds[0];
            }
        }
        Object objAsList = set.isEmpty() ? typeA : Arrays.asList(typeA, set);
        synchronized (this.f14501c) {
            try {
                l lVar2 = (l) this.f14501c.get(objAsList);
                if (lVar2 != null) {
                    return lVar2;
                }
                y yVar = (y) this.f14500b.get();
                if (yVar == null) {
                    yVar = new y(this);
                    this.f14500b.set(yVar);
                }
                ArrayDeque arrayDeque = yVar.f14495b;
                ArrayList arrayList = yVar.f14494a;
                int size = arrayList.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size) {
                        x xVar = new x(typeA, str, objAsList);
                        arrayList.add(xVar);
                        arrayDeque.add(xVar);
                        lVar = null;
                        break;
                    }
                    x xVar2 = (x) arrayList.get(i10);
                    if (xVar2.f14492c.equals(objAsList)) {
                        arrayDeque.add(xVar2);
                        l lVar3 = xVar2.f14493d;
                        lVar = xVar2;
                        if (lVar3 != null) {
                            lVar = lVar3;
                        }
                    } else {
                        i10++;
                    }
                }
                try {
                    if (lVar != null) {
                        return lVar;
                    }
                    try {
                        int size2 = this.f14499a.size();
                        for (int i11 = 0; i11 < size2; i11++) {
                            l lVarA = ((k) this.f14499a.get(i11)).a(typeA, set, this);
                            if (lVarA != null) {
                                ((x) yVar.f14495b.getLast()).f14493d = lVarA;
                                yVar.b(true);
                                return lVarA;
                            }
                        }
                        throw new IllegalArgumentException("No JsonAdapter for " + j6.e.i(typeA, set));
                    } catch (IllegalArgumentException e5) {
                        throw yVar.a(e5);
                    }
                } finally {
                    yVar.b(false);
                }
            } finally {
            }
        }
    }
}
