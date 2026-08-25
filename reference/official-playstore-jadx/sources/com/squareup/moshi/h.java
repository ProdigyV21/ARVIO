package com.squareup.moshi;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f14439c = new a(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f14440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f14441b;

    public h(l lVar, int i10) {
        this.f14441b = i10;
        this.f14440a = lVar;
    }

    @Override // com.squareup.moshi.l
    public Object a(p pVar) throws EOFException {
        Collection arrayList;
        switch (this.f14441b) {
            case 0:
                arrayList = new ArrayList();
                break;
            default:
                arrayList = new LinkedHashSet();
                break;
        }
        pVar.beginArray();
        while (pVar.hasNext()) {
            arrayList.add(this.f14440a.a(pVar));
        }
        pVar.endArray();
        return arrayList;
    }

    @Override // com.squareup.moshi.l
    public void e(q qVar, Object obj) {
        qVar.l();
        Iterator it = ((Collection) obj).iterator();
        while (it.hasNext()) {
            this.f14440a.e(qVar, it.next());
        }
        qVar.x(1, 2, ']');
    }

    public final String toString() {
        return this.f14440a + ".collection()";
    }
}
