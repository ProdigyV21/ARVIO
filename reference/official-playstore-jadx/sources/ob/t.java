package ob;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import xb.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class t implements Closeable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Logger f20950n = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.l f20951i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r f20952l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f20953m;

    public t(i0 i0Var) {
        this.f20951i = i0Var;
        r rVar = new r(i0Var);
        this.f20952l = rVar;
        this.f20953m = new b(rVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f20951i.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x01d5, code lost:
    
        throw new java.io.IOException(a0.c.i(r11, "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: "));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(boolean r19, j8.h r20) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 850
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ob.t.i(boolean, j8.h):boolean");
    }

    public final List j(int i10, int i11, int i12, int i13) throws IOException {
        r rVar = this.f20952l;
        rVar.f20948o = i10;
        rVar.f20945l = i10;
        rVar.f20949p = i11;
        rVar.f20946m = i12;
        rVar.f20947n = i13;
        b bVar = this.f20953m;
        i0 i0Var = bVar.f20877c;
        ArrayList arrayList = bVar.f20876b;
        while (!i0Var.i()) {
            byte b10 = i0Var.readByte();
            byte[] bArr = ib.c.f16268a;
            int i14 = b10 & 255;
            if (i14 == 128) {
                throw new IOException("index == 0");
            }
            if ((b10 & 128) == 128) {
                int iE = bVar.e(i14, 127);
                int i15 = iE - 1;
                if (i15 >= 0) {
                    a[] aVarArr = d.f20892a;
                    if (i15 <= aVarArr.length - 1) {
                        arrayList.add(aVarArr[i15]);
                    }
                }
                int length = bVar.f20879e + 1 + (i15 - d.f20892a.length);
                if (length >= 0) {
                    a[] aVarArr2 = bVar.f20878d;
                    if (length < aVarArr2.length) {
                        arrayList.add(aVarArr2[length]);
                    }
                }
                throw new IOException(a0.c.i(iE, "Header index too large "));
            }
            if (i14 == 64) {
                a[] aVarArr3 = d.f20892a;
                xb.m mVarD = bVar.d();
                d.a(mVarD);
                bVar.c(new a(mVarD, bVar.d()));
            } else if ((b10 & 64) == 64) {
                bVar.c(new a(bVar.b(bVar.e(i14, 63) - 1), bVar.d()));
            } else if ((b10 & 32) == 32) {
                int iE2 = bVar.e(i14, 31);
                bVar.f20875a = iE2;
                if (iE2 < 0 || iE2 > 4096) {
                    throw new IOException("Invalid dynamic table size update " + bVar.f20875a);
                }
                int i16 = bVar.f20881g;
                if (iE2 < i16) {
                    if (iE2 == 0) {
                        kotlin.collections.r.W(bVar.f20878d, null);
                        bVar.f20879e = bVar.f20878d.length - 1;
                        bVar.f20880f = 0;
                        bVar.f20881g = 0;
                    } else {
                        bVar.a(i16 - iE2);
                    }
                }
            } else if (i14 == 16 || i14 == 0) {
                a[] aVarArr4 = d.f20892a;
                xb.m mVarD2 = bVar.d();
                d.a(mVarD2);
                arrayList.add(new a(mVarD2, bVar.d()));
            } else {
                arrayList.add(new a(bVar.b(bVar.e(i14, 15) - 1), bVar.d()));
            }
        }
        List listC1 = kotlin.collections.x.c1(arrayList);
        arrayList.clear();
        return listC1;
    }

    public final void k(j8.h hVar, int i10) {
        xb.l lVar = this.f20951i;
        lVar.readInt();
        lVar.readByte();
        byte[] bArr = ib.c.f16268a;
    }
}
