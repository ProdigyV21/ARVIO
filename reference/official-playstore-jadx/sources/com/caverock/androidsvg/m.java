package com.caverock.androidsvg;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList f12373a = null;

    public final void a(l lVar) {
        if (this.f12373a == null) {
            this.f12373a = new ArrayList();
        }
        for (int i10 = 0; i10 < this.f12373a.size(); i10++) {
            if (((l) this.f12373a.get(i10)).f12361a.f12378b > lVar.f12361a.f12378b) {
                this.f12373a.add(i10, lVar);
                return;
            }
        }
        this.f12373a.add(lVar);
    }

    public final void b(m mVar) {
        if (mVar.f12373a == null) {
            return;
        }
        if (this.f12373a == null) {
            this.f12373a = new ArrayList(mVar.f12373a.size());
        }
        Iterator it = mVar.f12373a.iterator();
        while (it.hasNext()) {
            a((l) it.next());
        }
    }

    public final String toString() {
        if (this.f12373a == null) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        Iterator it = this.f12373a.iterator();
        while (it.hasNext()) {
            sb2.append(((l) it.next()).toString());
            sb2.append('\n');
        }
        return sb2.toString();
    }
}
