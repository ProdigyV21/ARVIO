package bc;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends FilterOutputStream {
    public static final byte[] z = "\u001b[0m".getBytes();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a8.e f7341i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f7342l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7343m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7344n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList f7345o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7346p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Charset f7347q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final b f7348r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final a8.e f7349s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final a f7350t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a f7351u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f7352v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f7353w;
    public final int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7354y;

    public c(f fVar, b bVar, int i10, a8.e eVar, int i11, int i12, Charset charset, zb.g gVar, zb.g gVar2, boolean z5) {
        super(fVar);
        this.f7342l = new byte[100];
        this.f7343m = 0;
        this.f7345o = new ArrayList();
        this.f7346p = 0;
        this.f7348r = bVar;
        this.f7349s = eVar;
        this.f7353w = i11;
        this.x = i12;
        this.f7350t = gVar;
        this.f7351u = gVar2;
        this.f7352v = z5;
        this.f7347q = charset;
        l(i10);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i10;
        if (this.f7352v && (i10 = this.f7353w) != 5 && i10 != 2) {
            l(2);
            write(z);
            flush();
        }
        a aVar = this.f7351u;
        if (aVar != null) {
            aVar.run();
        }
        super.close();
    }

    public final void i(int i10) throws IOException {
        try {
            a8.e eVar = this.f7341i;
            k(eVar != null && eVar.f0(i10, this.f7345o));
        } catch (RuntimeException e5) {
            k(true);
            throw e5;
        }
    }

    public final void j() throws IOException {
        try {
            a8.e eVar = this.f7341i;
            k(eVar != null && eVar.h0(this.f7345o));
        } catch (RuntimeException e5) {
            k(true);
            throw e5;
        }
    }

    public final void k(boolean z5) throws IOException {
        if (!z5) {
            ((FilterOutputStream) this).out.write(this.f7342l, 0, this.f7343m);
        }
        this.f7343m = 0;
        this.f7344n = 0;
        this.f7345o.clear();
        this.f7346p = 0;
    }

    public final void l(int i10) {
        a8.e eVar;
        if (i10 == 1) {
            eVar = new a8.e(((FilterOutputStream) this).out, 7);
        } else if (i10 == 3 || (eVar = this.f7349s) == null) {
            eVar = new e(((FilterOutputStream) this).out, this.x);
        }
        this.f7341i = eVar;
        this.f7354y = i10;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i10) throws IOException {
        int i11 = this.f7346p;
        Charset charset = this.f7347q;
        byte[] bArr = this.f7342l;
        ArrayList arrayList = this.f7345o;
        switch (i11) {
            case 0:
                if (i10 != 27) {
                    ((FilterOutputStream) this).out.write(i10);
                } else {
                    int i12 = this.f7343m;
                    this.f7343m = i12 + 1;
                    bArr[i12] = (byte) i10;
                    this.f7346p = 1;
                }
                break;
            case 1:
                int i13 = this.f7343m;
                this.f7343m = i13 + 1;
                bArr[i13] = (byte) i10;
                if (i10 == 91) {
                    this.f7346p = 2;
                } else if (i10 == 93) {
                    this.f7346p = 5;
                } else if (i10 == 40) {
                    arrayList.add(0);
                    this.f7346p = 9;
                } else if (i10 != 41) {
                    k(false);
                } else {
                    arrayList.add(1);
                    this.f7346p = 9;
                }
                break;
            case 2:
                int i14 = this.f7343m;
                this.f7343m = i14 + 1;
                bArr[i14] = (byte) i10;
                if (34 == i10) {
                    this.f7344n = i14;
                    this.f7346p = 3;
                } else if (48 <= i10 && i10 <= 57) {
                    this.f7344n = i14;
                    this.f7346p = 4;
                } else if (59 == i10) {
                    arrayList.add(null);
                } else if (63 == i10) {
                    arrayList.add('?');
                } else if (61 != i10) {
                    i(i10);
                } else {
                    arrayList.add('=');
                }
                break;
            case 3:
                int i15 = this.f7343m;
                this.f7343m = i15 + 1;
                bArr[i15] = (byte) i10;
                if (34 != i10) {
                    int i16 = this.f7344n;
                    arrayList.add(new String(bArr, i16, i15 - i16, charset));
                    if (i10 != 59) {
                        i(i10);
                    } else {
                        this.f7346p = 2;
                    }
                }
                break;
            case 4:
                int i17 = this.f7343m;
                this.f7343m = i17 + 1;
                bArr[i17] = (byte) i10;
                if (48 > i10 || i10 > 57) {
                    int i18 = this.f7344n;
                    arrayList.add(Integer.valueOf(new String(bArr, i18, i17 - i18)));
                    if (i10 != 59) {
                        i(i10);
                    } else {
                        this.f7346p = 2;
                    }
                }
                break;
            case 5:
                int i19 = this.f7343m;
                this.f7343m = i19 + 1;
                bArr[i19] = (byte) i10;
                if (48 <= i10 && i10 <= 57) {
                    this.f7344n = i19;
                    this.f7346p = 6;
                } else {
                    k(false);
                }
                break;
            case 6:
                int i20 = this.f7343m;
                this.f7343m = i20 + 1;
                bArr[i20] = (byte) i10;
                if (59 == i10) {
                    int i21 = this.f7344n;
                    arrayList.add(Integer.valueOf(new String(bArr, i21, i20 - i21)));
                    this.f7344n = this.f7343m;
                    this.f7346p = 7;
                } else if (48 > i10 || i10 > 57) {
                    k(false);
                }
                break;
            case 7:
                int i22 = this.f7343m;
                this.f7343m = i22 + 1;
                bArr[i22] = (byte) i10;
                if (7 == i10) {
                    int i23 = this.f7344n;
                    arrayList.add(new String(bArr, i23, i22 - i23, charset));
                    j();
                } else if (27 == i10) {
                    this.f7346p = 8;
                }
                break;
            case 8:
                int i24 = this.f7343m;
                this.f7343m = i24 + 1;
                bArr[i24] = (byte) i10;
                if (92 != i10) {
                    this.f7346p = 7;
                } else {
                    int i25 = this.f7344n;
                    arrayList.add(new String(bArr, i25, (i24 - 1) - i25, charset));
                    j();
                }
                break;
            case 9:
                arrayList.add(Character.valueOf((char) i10));
                try {
                    a8.e eVar = this.f7341i;
                    k(eVar != null && eVar.R(arrayList));
                } catch (RuntimeException e5) {
                    k(true);
                    throw e5;
                }
                break;
        }
        if (this.f7343m >= bArr.length) {
            k(false);
        }
    }
}
