package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 extends g {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f19776r;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f19777l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g f19778m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g f19779n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f19780o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f19781p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19782q = 0;

    static {
        ArrayList arrayList = new ArrayList();
        int i10 = 1;
        int i11 = 1;
        while (i10 > 0) {
            arrayList.add(Integer.valueOf(i10));
            int i12 = i11 + i10;
            i11 = i10;
            i10 = i12;
        }
        arrayList.add(Integer.MAX_VALUE);
        f19776r = new int[arrayList.size()];
        int i13 = 0;
        while (true) {
            int[] iArr = f19776r;
            if (i13 >= iArr.length) {
                return;
            }
            iArr[i13] = ((Integer) arrayList.get(i13)).intValue();
            i13++;
        }
    }

    public h0(g gVar, g gVar2) {
        this.f19778m = gVar;
        this.f19779n = gVar2;
        int size = gVar.size();
        this.f19780o = size;
        this.f19777l = gVar2.size() + size;
        this.f19781p = Math.max(gVar.e(), gVar2.e()) + 1;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final void d(int i10, int i11, int i12, byte[] bArr) {
        int i13 = i10 + i12;
        g gVar = this.f19778m;
        int i14 = this.f19780o;
        if (i13 <= i14) {
            gVar.d(i10, i11, i12, bArr);
            return;
        }
        g gVar2 = this.f19779n;
        if (i10 >= i14) {
            gVar2.d(i10 - i14, i11, i12, bArr);
            return;
        }
        int i15 = i14 - i10;
        gVar.d(i10, i11, i15, bArr);
        gVar2.d(0, i11 + i15, i12 - i15, bArr);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int e() {
        return this.f19781p;
    }

    public final boolean equals(Object obj) {
        int iN;
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            int size = gVar.size();
            int i10 = this.f19777l;
            if (i10 == size) {
                if (i10 == 0) {
                    return true;
                }
                if (this.f19782q == 0 || (iN = gVar.n()) == 0 || this.f19782q == iN) {
                    io.sentry.cache.tape.d dVar = new io.sentry.cache.tape.d(this);
                    b0 b0VarA = dVar.a();
                    io.sentry.cache.tape.d dVar2 = new io.sentry.cache.tape.d(gVar);
                    b0 b0VarA2 = dVar2.a();
                    int i11 = 0;
                    int i12 = 0;
                    int i13 = 0;
                    while (true) {
                        int length = b0VarA.f19764l.length - i11;
                        int length2 = b0VarA2.f19764l.length - i12;
                        int iMin = Math.min(length, length2);
                        if (!(i11 == 0 ? b0VarA.s(b0VarA2, i12, iMin) : b0VarA2.s(b0VarA, i11, iMin))) {
                            break;
                        }
                        i13 += iMin;
                        if (i13 >= i10) {
                            if (i13 == i10) {
                                return true;
                            }
                            throw new IllegalStateException();
                        }
                        if (iMin == length) {
                            b0VarA = dVar.a();
                            i11 = 0;
                        } else {
                            i11 += iMin;
                        }
                        if (iMin == length2) {
                            b0VarA2 = dVar2.a();
                            i12 = 0;
                        } else {
                            i12 += iMin;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final boolean f() {
        return this.f19777l >= f19776r[this.f19781p];
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final boolean g() {
        int iM = this.f19778m.m(0, 0, this.f19780o);
        g gVar = this.f19779n;
        return gVar.m(iM, 0, gVar.size()) == 0;
    }

    public final int hashCode() {
        int iK = this.f19782q;
        if (iK == 0) {
            int i10 = this.f19777l;
            iK = k(i10, 0, i10);
            if (iK == 0) {
                iK = 1;
            }
            this.f19782q = iK;
        }
        return iK;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new g0(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int k(int i10, int i11, int i12) {
        int i13 = i11 + i12;
        g gVar = this.f19778m;
        int i14 = this.f19780o;
        if (i13 <= i14) {
            return gVar.k(i10, i11, i12);
        }
        g gVar2 = this.f19779n;
        if (i11 >= i14) {
            return gVar2.k(i10, i11 - i14, i12);
        }
        int i15 = i14 - i11;
        return gVar2.k(gVar.k(i10, i11, i15), 0, i12 - i15);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int m(int i10, int i11, int i12) {
        int i13 = i11 + i12;
        g gVar = this.f19778m;
        int i14 = this.f19780o;
        if (i13 <= i14) {
            return gVar.m(i10, i11, i12);
        }
        g gVar2 = this.f19779n;
        if (i11 >= i14) {
            return gVar2.m(i10, i11 - i14, i12);
        }
        int i15 = i14 - i11;
        return gVar2.m(gVar.m(i10, i11, i15), 0, i12 - i15);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int n() {
        return this.f19782q;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final String p() {
        byte[] bArr;
        int i10 = this.f19777l;
        if (i10 == 0) {
            bArr = x.f19848a;
        } else {
            byte[] bArr2 = new byte[i10];
            d(0, 0, i10, bArr2);
            bArr = bArr2;
        }
        return new String(bArr, "UTF-8");
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final void r(OutputStream outputStream, int i10, int i11) {
        int i12 = i10 + i11;
        g gVar = this.f19778m;
        int i13 = this.f19780o;
        if (i12 <= i13) {
            gVar.r(outputStream, i10, i11);
            return;
        }
        g gVar2 = this.f19779n;
        if (i10 >= i13) {
            gVar2.r(outputStream, i10 - i13, i11);
            return;
        }
        int i14 = i13 - i10;
        gVar.r(outputStream, i10, i14);
        gVar2.r(outputStream, 0, i11 - i14);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int size() {
        return this.f19777l;
    }
}
