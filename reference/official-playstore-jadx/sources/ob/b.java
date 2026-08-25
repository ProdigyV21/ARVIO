package ob;

import androidx.leanback.widget.r1;
import java.io.IOException;
import java.util.ArrayList;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i0 f20877c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f20880f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f20881g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20875a = 4096;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f20876b = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a[] f20878d = new a[8];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f20879e = 7;

    public b(r rVar) {
        this.f20877c = new i0(rVar);
    }

    public final int a(int i10) {
        int i11;
        int i12 = 0;
        if (i10 > 0) {
            int length = this.f20878d.length;
            while (true) {
                length--;
                i11 = this.f20879e;
                if (length < i11 || i10 <= 0) {
                    break;
                }
                int i13 = this.f20878d[length].f20873c;
                i10 -= i13;
                this.f20881g -= i13;
                this.f20880f--;
                i12++;
            }
            a[] aVarArr = this.f20878d;
            System.arraycopy(aVarArr, i11 + 1, aVarArr, i11 + 1 + i12, this.f20880f);
            this.f20879e += i12;
        }
        return i12;
    }

    public final xb.m b(int i10) throws IOException {
        if (i10 >= 0) {
            a[] aVarArr = d.f20892a;
            if (i10 <= aVarArr.length - 1) {
                return aVarArr[i10].f20871a;
            }
        }
        int length = this.f20879e + 1 + (i10 - d.f20892a.length);
        if (length >= 0) {
            a[] aVarArr2 = this.f20878d;
            if (length < aVarArr2.length) {
                return aVarArr2[length].f20871a;
            }
        }
        throw new IOException("Header index too large " + (i10 + 1));
    }

    public final void c(a aVar) {
        this.f20876b.add(aVar);
        int i10 = aVar.f20873c;
        int i11 = this.f20875a;
        if (i10 > i11) {
            kotlin.collections.r.W(this.f20878d, null);
            this.f20879e = this.f20878d.length - 1;
            this.f20880f = 0;
            this.f20881g = 0;
            return;
        }
        a((this.f20881g + i10) - i11);
        int i12 = this.f20880f + 1;
        a[] aVarArr = this.f20878d;
        if (i12 > aVarArr.length) {
            a[] aVarArr2 = new a[aVarArr.length * 2];
            System.arraycopy(aVarArr, 0, aVarArr2, aVarArr.length, aVarArr.length);
            this.f20879e = this.f20878d.length - 1;
            this.f20878d = aVarArr2;
        }
        int i13 = this.f20879e;
        this.f20879e = i13 - 1;
        this.f20878d[i13] = aVar;
        this.f20880f++;
        this.f20881g += i10;
    }

    public final xb.m d() {
        i0 i0Var = this.f20877c;
        byte b10 = i0Var.readByte();
        byte[] bArr = ib.c.f16268a;
        int i10 = b10 & 255;
        int i11 = 0;
        boolean z = (b10 & 128) == 128;
        long jE = e(i10, 127);
        if (!z) {
            return i0Var.c0(jE);
        }
        xb.j jVar = new xb.j();
        r1 r1Var = z.f20986c;
        r1 r1Var2 = r1Var;
        int i12 = 0;
        for (long j10 = 0; j10 < jE; j10++) {
            byte b11 = i0Var.readByte();
            byte[] bArr2 = ib.c.f16268a;
            i11 = (i11 << 8) | (b11 & 255);
            i12 += 8;
            while (i12 >= 8) {
                r1Var2 = ((r1[]) r1Var2.f3114d)[(i11 >>> (i12 - 8)) & 255];
                if (((r1[]) r1Var2.f3114d) == null) {
                    jVar.M0(r1Var2.f3112b);
                    i12 -= r1Var2.f3113c;
                    r1Var2 = r1Var;
                } else {
                    i12 -= 8;
                }
            }
        }
        while (i12 > 0) {
            r1 r1Var3 = ((r1[]) r1Var2.f3114d)[(i11 << (8 - i12)) & 255];
            int i13 = r1Var3.f3113c;
            if (((r1[]) r1Var3.f3114d) != null || i13 > i12) {
                break;
            }
            jVar.M0(r1Var3.f3112b);
            i12 -= i13;
            r1Var2 = r1Var;
        }
        return jVar.c0(jVar.f22759l);
    }

    public final int e(int i10, int i11) {
        int i12 = i10 & i11;
        if (i12 < i11) {
            return i12;
        }
        int i13 = 0;
        while (true) {
            byte b10 = this.f20877c.readByte();
            byte[] bArr = ib.c.f16268a;
            int i14 = b10 & 255;
            if ((b10 & 128) == 0) {
                return i11 + (i14 << i13);
            }
            i11 += (b10 & 127) << i13;
            i13 += 7;
        }
    }
}
