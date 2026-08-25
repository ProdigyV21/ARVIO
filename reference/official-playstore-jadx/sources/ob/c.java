package ob;

import java.io.EOFException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xb.j f20884a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f20886c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20890g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20891h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20885b = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f20887d = 4096;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a[] f20888e = new a[8];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20889f = 7;

    public c(xb.j jVar) {
        this.f20884a = jVar;
    }

    public final void a(int i10) {
        int i11;
        if (i10 > 0) {
            int length = this.f20888e.length - 1;
            int i12 = 0;
            while (true) {
                i11 = this.f20889f;
                if (length < i11 || i10 <= 0) {
                    break;
                }
                int i13 = this.f20888e[length].f20873c;
                i10 -= i13;
                this.f20891h -= i13;
                this.f20890g--;
                i12++;
                length--;
            }
            a[] aVarArr = this.f20888e;
            int i14 = i11 + 1;
            System.arraycopy(aVarArr, i14, aVarArr, i14 + i12, this.f20890g);
            a[] aVarArr2 = this.f20888e;
            int i15 = this.f20889f + 1;
            Arrays.fill(aVarArr2, i15, i15 + i12, (Object) null);
            this.f20889f += i12;
        }
    }

    public final void b(a aVar) {
        int i10 = aVar.f20873c;
        int i11 = this.f20887d;
        if (i10 > i11) {
            a[] aVarArr = this.f20888e;
            Arrays.fill(aVarArr, 0, aVarArr.length, (Object) null);
            this.f20889f = this.f20888e.length - 1;
            this.f20890g = 0;
            this.f20891h = 0;
            return;
        }
        a((this.f20891h + i10) - i11);
        int i12 = this.f20890g + 1;
        a[] aVarArr2 = this.f20888e;
        if (i12 > aVarArr2.length) {
            a[] aVarArr3 = new a[aVarArr2.length * 2];
            System.arraycopy(aVarArr2, 0, aVarArr3, aVarArr2.length, aVarArr2.length);
            this.f20889f = this.f20888e.length - 1;
            this.f20888e = aVarArr3;
        }
        int i13 = this.f20889f;
        this.f20889f = i13 - 1;
        this.f20888e[i13] = aVar;
        this.f20890g++;
        this.f20891h += i10;
    }

    public final void c(xb.m mVar) throws EOFException {
        int[] iArr = z.f20984a;
        int iF = mVar.f();
        long j10 = 0;
        long j11 = 0;
        for (int i10 = 0; i10 < iF; i10++) {
            byte bM = mVar.m(i10);
            byte[] bArr = ib.c.f16268a;
            j11 += (long) z.f20985b[bM & 255];
        }
        int i11 = (int) ((j11 + ((long) 7)) >> 3);
        int iF2 = mVar.f();
        xb.j jVar = this.f20884a;
        if (i11 >= iF2) {
            e(mVar.f(), 127, 0);
            mVar.u(jVar, mVar.f());
            return;
        }
        xb.j jVar2 = new xb.j();
        int[] iArr2 = z.f20984a;
        int iF3 = mVar.f();
        int i12 = 0;
        for (int i13 = 0; i13 < iF3; i13++) {
            byte bM2 = mVar.m(i13);
            byte[] bArr2 = ib.c.f16268a;
            int i14 = bM2 & 255;
            int i15 = z.f20984a[i14];
            byte b10 = z.f20985b[i14];
            j10 = (j10 << b10) | ((long) i15);
            i12 += b10;
            while (i12 >= 8) {
                i12 -= 8;
                jVar2.M0((int) (j10 >> i12));
            }
        }
        if (i12 > 0) {
            jVar2.M0((int) ((j10 << (8 - i12)) | (255 >>> i12)));
        }
        xb.m mVarC0 = jVar2.c0(jVar2.f22759l);
        e(mVarC0.f(), 127, 128);
        mVarC0.u(jVar, mVarC0.f());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(java.util.ArrayList r14) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ob.c.d(java.util.ArrayList):void");
    }

    public final void e(int i10, int i11, int i12) {
        xb.j jVar = this.f20884a;
        if (i10 < i11) {
            jVar.M0(i10 | i12);
            return;
        }
        jVar.M0(i12 | i11);
        int i13 = i10 - i11;
        while (i13 >= 128) {
            jVar.M0(128 | (i13 & 127));
            i13 >>>= 7;
        }
        jVar.M0(i13);
    }
}
