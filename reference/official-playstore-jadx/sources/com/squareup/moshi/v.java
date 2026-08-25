package com.squareup.moshi;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class v extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a8.h f14480s = new a8.h(5);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public t f14487q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public t f14488r;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f14484n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f14485o = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Comparator f14481i = f14480s;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final u f14483m = new u();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public u[] f14482l = new u[16];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14486p = 12;

    public final u b(Object obj, boolean z) {
        int iCompareTo;
        u uVar;
        boolean z5;
        u uVar2;
        u uVar3;
        u uVar4;
        u uVar5;
        u uVar6;
        u[] uVarArr = this.f14482l;
        int iHashCode = obj.hashCode();
        int i10 = iHashCode ^ ((iHashCode >>> 20) ^ (iHashCode >>> 12));
        int i11 = ((i10 >>> 7) ^ i10) ^ (i10 >>> 4);
        boolean z10 = true;
        int length = i11 & (uVarArr.length - 1);
        u uVar7 = uVarArr[length];
        a8.h hVar = f14480s;
        u uVar8 = null;
        Comparator comparator = this.f14481i;
        if (uVar7 != null) {
            Comparable comparable = comparator == hVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = uVar7.f14476p;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return uVar7;
                }
                u uVar9 = iCompareTo < 0 ? uVar7.f14472l : uVar7.f14473m;
                if (uVar9 == null) {
                    break;
                }
                uVar7 = uVar9;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        u uVar10 = this.f14483m;
        if (uVar7 != null) {
            u uVar11 = uVar7;
            uVar = new u(uVar11, obj, i11, uVar10, uVar10.f14475o);
            if (iCompareTo < 0) {
                uVar11.f14472l = uVar;
            } else {
                uVar11.f14473m = uVar;
            }
            c(uVar11, true);
        } else {
            if (comparator == hVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            uVar = new u(uVar7, obj, i11, uVar10, uVar10.f14475o);
            uVarArr[length] = uVar;
        }
        int i12 = this.f14484n;
        this.f14484n = i12 + 1;
        if (i12 > this.f14486p) {
            u[] uVarArr2 = this.f14482l;
            int length2 = uVarArr2.length;
            int i13 = length2 * 2;
            u[] uVarArr3 = new u[i13];
            androidx.recyclerview.widget.s sVar = new androidx.recyclerview.widget.s();
            androidx.recyclerview.widget.s sVar2 = new androidx.recyclerview.widget.s();
            int i14 = 0;
            while (i14 < length2) {
                u uVar12 = uVarArr2[i14];
                if (uVar12 == null) {
                    z5 = z10;
                    uVar3 = uVar8;
                } else {
                    u uVar13 = uVar8;
                    for (u uVar14 = uVar12; uVar14 != null; uVar14 = uVar14.f14472l) {
                        uVar14.f14471i = uVar13;
                        uVar13 = uVar14;
                    }
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        if (uVar13 != null) {
                            z5 = z10;
                            u uVar15 = uVar13.f14471i;
                            uVar13.f14471i = uVar8;
                            u uVar16 = uVar13.f14473m;
                            while (true) {
                                u uVar17 = uVar16;
                                uVar2 = uVar15;
                                uVar15 = uVar17;
                                if (uVar15 == null) {
                                    break;
                                }
                                uVar15.f14471i = uVar2;
                                uVar16 = uVar15.f14472l;
                            }
                        } else {
                            u uVar18 = uVar13;
                            uVar13 = uVar8;
                            uVar2 = uVar18;
                            z5 = z10;
                        }
                        if (uVar13 == null) {
                            break;
                        }
                        if ((uVar13.f14477q & length2) == 0) {
                            i15++;
                        } else {
                            i16++;
                        }
                        uVar13 = uVar2;
                        z10 = z5;
                        uVar8 = null;
                    }
                    sVar.f5040a = ((Integer.highestOneBit(i15) * 2) - 1) - i15;
                    sVar.f5042c = 0;
                    sVar.f5041b = 0;
                    uVar3 = null;
                    sVar.f5043d = null;
                    sVar2.f5040a = ((Integer.highestOneBit(i16) * 2) - 1) - i16;
                    sVar2.f5042c = 0;
                    sVar2.f5041b = 0;
                    sVar2.f5043d = null;
                    u uVar19 = null;
                    while (uVar12 != null) {
                        uVar12.f14471i = uVar19;
                        u uVar20 = uVar12;
                        uVar12 = uVar12.f14472l;
                        uVar19 = uVar20;
                    }
                    while (true) {
                        if (uVar19 != null) {
                            u uVar21 = uVar19.f14471i;
                            uVar19.f14471i = null;
                            u uVar22 = uVar19.f14473m;
                            while (true) {
                                u uVar23 = uVar22;
                                uVar4 = uVar21;
                                uVar21 = uVar23;
                                if (uVar21 == null) {
                                    break;
                                }
                                uVar21.f14471i = uVar4;
                                uVar22 = uVar21.f14472l;
                            }
                        } else {
                            uVar4 = uVar19;
                            uVar19 = null;
                        }
                        if (uVar19 == null) {
                            break;
                        }
                        if ((uVar19.f14477q & length2) == 0) {
                            sVar.a(uVar19);
                        } else {
                            sVar2.a(uVar19);
                        }
                        uVar19 = uVar4;
                    }
                    if (i15 > 0) {
                        uVar5 = (u) sVar.f5043d;
                        if (uVar5.f14471i != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        uVar5 = null;
                    }
                    uVarArr3[i14] = uVar5;
                    int i17 = i14 + length2;
                    if (i16 > 0) {
                        uVar6 = (u) sVar2.f5043d;
                        if (uVar6.f14471i != null) {
                            throw new IllegalStateException();
                        }
                    } else {
                        uVar6 = null;
                    }
                    uVarArr3[i17] = uVar6;
                }
                i14++;
                uVar8 = uVar3;
                z10 = z5;
            }
            this.f14482l = uVarArr3;
            this.f14486p = (i13 / 4) + (i13 / 2);
        }
        this.f14485o++;
        return uVar;
    }

    public final void c(u uVar, boolean z) {
        while (uVar != null) {
            u uVar2 = uVar.f14472l;
            u uVar3 = uVar.f14473m;
            int i10 = uVar2 != null ? uVar2.f14479s : 0;
            int i11 = uVar3 != null ? uVar3.f14479s : 0;
            int i12 = i10 - i11;
            if (i12 == -2) {
                u uVar4 = uVar3.f14472l;
                u uVar5 = uVar3.f14473m;
                int i13 = (uVar4 != null ? uVar4.f14479s : 0) - (uVar5 != null ? uVar5.f14479s : 0);
                if (i13 != -1 && (i13 != 0 || z)) {
                    i(uVar3);
                }
                h(uVar);
                if (z) {
                    return;
                }
            } else if (i12 == 2) {
                u uVar6 = uVar2.f14472l;
                u uVar7 = uVar2.f14473m;
                int i14 = (uVar6 != null ? uVar6.f14479s : 0) - (uVar7 != null ? uVar7.f14479s : 0);
                if (i14 != 1 && (i14 != 0 || z)) {
                    h(uVar2);
                }
                i(uVar);
                if (z) {
                    return;
                }
            } else if (i12 == 0) {
                uVar.f14479s = i10 + 1;
                if (z) {
                    return;
                }
            } else {
                uVar.f14479s = Math.max(i10, i11) + 1;
                if (!z) {
                    return;
                }
            }
            uVar = uVar.f14471i;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Arrays.fill(this.f14482l, (Object) null);
        this.f14484n = 0;
        this.f14485o++;
        u uVar = this.f14483m;
        u uVar2 = uVar.f14474n;
        while (uVar2 != uVar) {
            u uVar3 = uVar2.f14474n;
            uVar2.f14475o = null;
            uVar2.f14474n = null;
            uVar2 = uVar3;
        }
        uVar.f14475o = uVar;
        uVar.f14474n = uVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        u uVarB = null;
        if (obj != null) {
            try {
                uVarB = b(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return uVarB != null;
    }

    public final void e(u uVar, boolean z) {
        u uVar2;
        u uVar3;
        int i10;
        if (z) {
            u uVar4 = uVar.f14475o;
            uVar4.f14474n = uVar.f14474n;
            uVar.f14474n.f14475o = uVar4;
            uVar.f14475o = null;
            uVar.f14474n = null;
        }
        u uVar5 = uVar.f14472l;
        u uVar6 = uVar.f14473m;
        u uVar7 = uVar.f14471i;
        int i11 = 0;
        if (uVar5 == null || uVar6 == null) {
            if (uVar5 != null) {
                g(uVar, uVar5);
                uVar.f14472l = null;
            } else if (uVar6 != null) {
                g(uVar, uVar6);
                uVar.f14473m = null;
            } else {
                g(uVar, null);
            }
            c(uVar7, false);
            this.f14484n--;
            this.f14485o++;
            return;
        }
        if (uVar5.f14479s > uVar6.f14479s) {
            u uVar8 = uVar5.f14473m;
            while (true) {
                u uVar9 = uVar8;
                uVar3 = uVar5;
                uVar5 = uVar9;
                if (uVar5 == null) {
                    break;
                } else {
                    uVar8 = uVar5.f14473m;
                }
            }
        } else {
            u uVar10 = uVar6.f14472l;
            while (true) {
                uVar2 = uVar6;
                uVar6 = uVar10;
                if (uVar6 == null) {
                    break;
                } else {
                    uVar10 = uVar6.f14472l;
                }
            }
            uVar3 = uVar2;
        }
        e(uVar3, false);
        u uVar11 = uVar.f14472l;
        if (uVar11 != null) {
            i10 = uVar11.f14479s;
            uVar3.f14472l = uVar11;
            uVar11.f14471i = uVar3;
            uVar.f14472l = null;
        } else {
            i10 = 0;
        }
        u uVar12 = uVar.f14473m;
        if (uVar12 != null) {
            i11 = uVar12.f14479s;
            uVar3.f14473m = uVar12;
            uVar12.f14471i = uVar3;
            uVar.f14473m = null;
        }
        uVar3.f14479s = Math.max(i10, i11) + 1;
        g(uVar, uVar3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        t tVar = this.f14487q;
        if (tVar != null) {
            return tVar;
        }
        t tVar2 = new t(this, 0);
        this.f14487q = tVar2;
        return tVar2;
    }

    public final void g(u uVar, u uVar2) {
        u uVar3 = uVar.f14471i;
        uVar.f14471i = null;
        if (uVar2 != null) {
            uVar2.f14471i = uVar3;
        }
        if (uVar3 == null) {
            int i10 = uVar.f14477q;
            this.f14482l[i10 & (r0.length - 1)] = uVar2;
        } else if (uVar3.f14472l == uVar) {
            uVar3.f14472l = uVar2;
        } else {
            uVar3.f14473m = uVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        u uVarB;
        if (obj != null) {
            try {
                uVarB = b(obj, false);
            } catch (ClassCastException unused) {
                uVarB = null;
            }
        } else {
            uVarB = null;
        }
        if (uVarB != null) {
            return uVarB.f14478r;
        }
        return null;
    }

    public final void h(u uVar) {
        u uVar2 = uVar.f14472l;
        u uVar3 = uVar.f14473m;
        u uVar4 = uVar3.f14472l;
        u uVar5 = uVar3.f14473m;
        uVar.f14473m = uVar4;
        if (uVar4 != null) {
            uVar4.f14471i = uVar;
        }
        g(uVar, uVar3);
        uVar3.f14472l = uVar;
        uVar.f14471i = uVar3;
        int iMax = Math.max(uVar2 != null ? uVar2.f14479s : 0, uVar4 != null ? uVar4.f14479s : 0) + 1;
        uVar.f14479s = iMax;
        uVar3.f14479s = Math.max(iMax, uVar5 != null ? uVar5.f14479s : 0) + 1;
    }

    public final void i(u uVar) {
        u uVar2 = uVar.f14472l;
        u uVar3 = uVar.f14473m;
        u uVar4 = uVar2.f14472l;
        u uVar5 = uVar2.f14473m;
        uVar.f14472l = uVar5;
        if (uVar5 != null) {
            uVar5.f14471i = uVar;
        }
        g(uVar, uVar2);
        uVar2.f14473m = uVar;
        uVar.f14471i = uVar2;
        int iMax = Math.max(uVar3 != null ? uVar3.f14479s : 0, uVar5 != null ? uVar5.f14479s : 0) + 1;
        uVar.f14479s = iMax;
        uVar2.f14479s = Math.max(iMax, uVar4 != null ? uVar4.f14479s : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        t tVar = this.f14488r;
        if (tVar != null) {
            return tVar;
        }
        t tVar2 = new t(this, 1);
        this.f14488r = tVar2;
        return tVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        u uVarB = b(obj, true);
        Object obj3 = uVarB.f14478r;
        uVarB.f14478r = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        u uVarB;
        if (obj != null) {
            try {
                uVarB = b(obj, false);
            } catch (ClassCastException unused) {
                uVarB = null;
            }
        } else {
            uVarB = null;
        }
        if (uVarB != null) {
            e(uVarB, true);
        }
        if (uVarB != null) {
            return uVarB.f14478r;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f14484n;
    }
}
