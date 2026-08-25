package androidx.recyclerview.widget;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements f0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final j0 f4897d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.core.provider.j f4894a = new androidx.core.provider.j(30, 7);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4895b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f4896c = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4899f = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w0 f4898e = new w0(this, 2);

    public c(j0 j0Var) {
        this.f4897d = j0Var;
    }

    public final boolean a(int i10) {
        ArrayList arrayList = this.f4896c;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            b bVar = (b) arrayList.get(i11);
            int i12 = bVar.f4879a;
            if (i12 != 8) {
                if (i12 == 1) {
                    int i13 = bVar.f4880b;
                    int i14 = bVar.f4882d + i13;
                    while (i13 < i14) {
                        if (f(i13, i11 + 1) == i10) {
                            return true;
                        }
                        i13++;
                    }
                } else {
                    continue;
                }
            } else {
                if (f(bVar.f4882d, i11 + 1) == i10) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void b() {
        ArrayList arrayList = this.f4896c;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            this.f4897d.a((b) arrayList.get(i10));
        }
        k(arrayList);
        this.f4899f = 0;
    }

    public final void c() {
        b();
        ArrayList arrayList = this.f4895b;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            b bVar = (b) arrayList.get(i10);
            int i11 = bVar.f4879a;
            j0 j0Var = this.f4897d;
            if (i11 == 1) {
                j0Var.a(bVar);
                j0Var.d(bVar.f4880b, bVar.f4882d);
            } else if (i11 == 2) {
                j0Var.a(bVar);
                j0Var.f(bVar.f4880b, bVar.f4882d);
            } else if (i11 == 4) {
                j0Var.a(bVar);
                j0Var.c(bVar.f4880b, bVar.f4882d, bVar.f4881c);
            } else if (i11 == 8) {
                j0Var.a(bVar);
                j0Var.e(bVar.f4880b, bVar.f4882d);
            }
        }
        k(arrayList);
        this.f4899f = 0;
    }

    public final void d(b bVar) {
        int i10;
        androidx.core.provider.j jVar;
        int i11 = bVar.f4879a;
        if (i11 == 1 || i11 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iL = l(bVar.f4880b, i11);
        int i12 = bVar.f4880b;
        int i13 = bVar.f4879a;
        if (i13 == 2) {
            i10 = 0;
        } else {
            if (i13 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i10 = 1;
        }
        int i14 = 1;
        int i15 = 1;
        while (true) {
            int i16 = bVar.f4882d;
            jVar = this.f4894a;
            if (i14 >= i16) {
                break;
            }
            int iL2 = l((i10 * i14) + bVar.f4880b, bVar.f4879a);
            int i17 = bVar.f4879a;
            if (i17 == 2 ? iL2 != iL : !(i17 == 4 && iL2 == iL + 1)) {
                b bVarH = h(i17, iL, i15, bVar.f4881c);
                e(bVarH, i12);
                bVarH.f4881c = null;
                jVar.f(bVarH);
                if (bVar.f4879a == 4) {
                    i12 += i15;
                }
                i15 = 1;
                iL = iL2;
            } else {
                i15++;
            }
            i14++;
        }
        Object obj = bVar.f4881c;
        bVar.f4881c = null;
        jVar.f(bVar);
        if (i15 > 0) {
            b bVarH2 = h(bVar.f4879a, iL, i15, obj);
            e(bVarH2, i12);
            bVarH2.f4881c = null;
            jVar.f(bVarH2);
        }
    }

    public final void e(b bVar, int i10) {
        j0 j0Var = this.f4897d;
        j0Var.a(bVar);
        int i11 = bVar.f4879a;
        if (i11 == 2) {
            j0Var.f(i10, bVar.f4882d);
        } else {
            if (i11 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            j0Var.c(i10, bVar.f4882d, bVar.f4881c);
        }
    }

    public final int f(int i10, int i11) {
        ArrayList arrayList = this.f4896c;
        int size = arrayList.size();
        while (i11 < size) {
            b bVar = (b) arrayList.get(i11);
            int i12 = bVar.f4879a;
            if (i12 == 8) {
                int i13 = bVar.f4880b;
                if (i13 == i10) {
                    i10 = bVar.f4882d;
                } else {
                    if (i13 < i10) {
                        i10--;
                    }
                    if (bVar.f4882d <= i10) {
                        i10++;
                    }
                }
            } else {
                int i14 = bVar.f4880b;
                if (i14 > i10) {
                    continue;
                } else if (i12 == 2) {
                    int i15 = bVar.f4882d;
                    if (i10 < i14 + i15) {
                        return -1;
                    }
                    i10 -= i15;
                } else if (i12 == 1) {
                    i10 += bVar.f4882d;
                }
            }
            i11++;
        }
        return i10;
    }

    public final boolean g() {
        return this.f4895b.size() > 0;
    }

    public final b h(int i10, int i11, int i12, Object obj) {
        b bVar = (b) this.f4894a.a();
        if (bVar != null) {
            bVar.f4879a = i10;
            bVar.f4880b = i11;
            bVar.f4882d = i12;
            bVar.f4881c = obj;
            return bVar;
        }
        b bVar2 = new b();
        bVar2.f4879a = i10;
        bVar2.f4880b = i11;
        bVar2.f4882d = i12;
        bVar2.f4881c = obj;
        return bVar2;
    }

    public final void i(b bVar) {
        this.f4896c.add(bVar);
        int i10 = bVar.f4879a;
        j0 j0Var = this.f4897d;
        if (i10 == 1) {
            j0Var.d(bVar.f4880b, bVar.f4882d);
            return;
        }
        if (i10 == 2) {
            int i11 = bVar.f4880b;
            int i12 = bVar.f4882d;
            RecyclerView recyclerView = j0Var.f4952a;
            recyclerView.N(i11, i12, false);
            recyclerView.f4852t0 = true;
            return;
        }
        if (i10 == 4) {
            j0Var.c(bVar.f4880b, bVar.f4882d, bVar.f4881c);
        } else if (i10 == 8) {
            j0Var.e(bVar.f4880b, bVar.f4882d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:186:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0123 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0116 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0007 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 692
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.c.j():void");
    }

    public final void k(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            b bVar = (b) arrayList.get(i10);
            bVar.f4881c = null;
            this.f4894a.f(bVar);
        }
        arrayList.clear();
    }

    public final int l(int i10, int i11) {
        int i12;
        int i13;
        ArrayList arrayList = this.f4896c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            b bVar = (b) arrayList.get(size);
            int i14 = bVar.f4879a;
            if (i14 == 8) {
                int i15 = bVar.f4880b;
                int i16 = bVar.f4882d;
                if (i15 < i16) {
                    i13 = i15;
                    i12 = i16;
                } else {
                    i12 = i15;
                    i13 = i16;
                }
                if (i10 < i13 || i10 > i12) {
                    if (i10 < i15) {
                        if (i11 == 1) {
                            bVar.f4880b = i15 + 1;
                            bVar.f4882d = i16 + 1;
                        } else if (i11 == 2) {
                            bVar.f4880b = i15 - 1;
                            bVar.f4882d = i16 - 1;
                        }
                    }
                } else if (i13 == i15) {
                    if (i11 == 1) {
                        bVar.f4882d = i16 + 1;
                    } else if (i11 == 2) {
                        bVar.f4882d = i16 - 1;
                    }
                    i10++;
                } else {
                    if (i11 == 1) {
                        bVar.f4880b = i15 + 1;
                    } else if (i11 == 2) {
                        bVar.f4880b = i15 - 1;
                    }
                    i10--;
                }
            } else {
                int i17 = bVar.f4880b;
                if (i17 <= i10) {
                    if (i14 == 1) {
                        i10 -= bVar.f4882d;
                    } else if (i14 == 2) {
                        i10 += bVar.f4882d;
                    }
                } else if (i11 == 1) {
                    bVar.f4880b = i17 + 1;
                } else if (i11 == 2) {
                    bVar.f4880b = i17 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            b bVar2 = (b) arrayList.get(size2);
            int i18 = bVar2.f4879a;
            androidx.core.provider.j jVar = this.f4894a;
            if (i18 == 8) {
                int i19 = bVar2.f4882d;
                if (i19 == bVar2.f4880b || i19 < 0) {
                    arrayList.remove(size2);
                    bVar2.f4881c = null;
                    jVar.f(bVar2);
                }
            } else if (bVar2.f4882d <= 0) {
                arrayList.remove(size2);
                bVar2.f4881c = null;
                jVar.f(bVar2);
            }
        }
        return i10;
    }
}
