package androidx.leanback.widget;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class r1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f3114d;

    public r1(byte[] bArr) {
        this.f3111a = 4;
        int length = bArr.length;
        this.f3114d = bArr;
        this.f3112b = 0;
        this.f3113c = length;
    }

    public static void b(int i10, int i11, int i12) {
        if (i11 == i12) {
            return;
        }
        StringBuilder sbS = a0.c.s(i10, "Field ", ": expected ");
        androidx.compose.material3.d.A(sbS, i11 != 0 ? i11 != 1 ? i11 != 2 ? i11 != 5 ? "unknown" : "fixed32" : "length-delimited" : "fixed64" : "varint", " (wire type ", i11, ") but got ");
        sbS.append(i12 != 0 ? i12 != 1 ? i12 != 2 ? i12 != 5 ? "unknown" : "fixed32" : "length-delimited" : "fixed64" : "varint");
        sbS.append(" (wire type ");
        sbS.append(i12);
        sbS.append(")");
        throw new IOException(sbS.toString());
    }

    public void a() {
        int i10 = this.f3112b;
        if (i10 == 2) {
            if (this.f3113c <= 0) {
                throw new IllegalArgumentException();
            }
            t.r rVar = (t.r) this.f3114d;
            if (rVar == null || rVar.maxSize() != this.f3113c) {
                this.f3114d = new t.r(this.f3113c);
                return;
            }
            return;
        }
        if (i10 != 3 && i10 != 1) {
            this.f3114d = null;
            return;
        }
        t.r rVar2 = (t.r) this.f3114d;
        if (rVar2 == null || rVar2.maxSize() != Integer.MAX_VALUE) {
            this.f3114d = new t.r(Integer.MAX_VALUE);
        }
    }

    public byte c(int i10, int i11) {
        return ((byte[][]) this.f3114d)[i11][i10];
    }

    public io.sentry.android.core.internal.threaddump.a d() {
        int i10 = this.f3113c;
        if (i10 < 0 || i10 >= this.f3112b) {
            return null;
        }
        ArrayList arrayList = (ArrayList) this.f3114d;
        this.f3113c = i10 + 1;
        return (io.sentry.android.core.internal.threaddump.a) arrayList.get(i10);
    }

    public boolean e() {
        return j() != 0;
    }

    public byte[] f() {
        int iJ = (int) j();
        if (iJ < 0) {
            throw new IOException(a0.c.i(iJ, "Negative length: "));
        }
        int i10 = this.f3113c;
        int i11 = this.f3112b;
        if (i10 - i11 < iJ) {
            throw new EOFException("Not enough bytes for length-delimited field");
        }
        byte[] bArr = new byte[iJ];
        System.arraycopy((byte[]) this.f3114d, i11, bArr, 0, iJ);
        this.f3112b += iJ;
        return bArr;
    }

    public r1 g() {
        return new r1(f());
    }

    public String h() {
        return new String(f(), StandardCharsets.UTF_8);
    }

    public int i() {
        if (this.f3112b < this.f3113c) {
            return (int) j();
        }
        return 0;
    }

    public long j() {
        long j10 = 0;
        for (int i10 = 0; i10 < 64; i10 += 7) {
            int i11 = this.f3112b;
            if (i11 >= this.f3113c) {
                throw new EOFException("Truncated varint");
            }
            byte[] bArr = (byte[]) this.f3114d;
            this.f3112b = i11 + 1;
            byte b10 = bArr[i11];
            j10 |= ((long) (b10 & 127)) << i10;
            if ((b10 & 128) == 0) {
                return j10;
            }
        }
        throw new IOException("Malformed varint");
    }

    public void k(int i10, int i11, int i12) {
        ((byte[][]) this.f3114d)[i11][i10] = (byte) i12;
    }

    public void l(int i10) {
        int i11 = this.f3113c;
        if (i10 == 0) {
            j();
            return;
        }
        if (i10 == 1) {
            int i12 = this.f3112b;
            if (i11 - i12 < 8) {
                throw new EOFException("Not enough bytes to skip fixed64");
            }
            this.f3112b = i12 + 8;
            return;
        }
        if (i10 == 2) {
            int iJ = (int) j();
            int i13 = this.f3112b;
            if (i11 - i13 < iJ) {
                throw new EOFException("Not enough bytes to skip length-delimited");
            }
            this.f3112b = i13 + iJ;
            return;
        }
        if (i10 != 5) {
            throw new IOException(a0.c.i(i10, "Unknown wire type: "));
        }
        int i14 = this.f3112b;
        if (i11 - i14 < 4) {
            throw new EOFException("Not enough bytes to skip fixed32");
        }
        this.f3112b = i14 + 4;
    }

    public String toString() {
        switch (this.f3111a) {
            case 1:
                int i10 = this.f3112b;
                int i11 = this.f3113c;
                StringBuilder sb2 = new StringBuilder((i10 * 2 * i11) + 2);
                for (int i12 = 0; i12 < i11; i12++) {
                    byte[] bArr = ((byte[][]) this.f3114d)[i12];
                    for (int i13 = 0; i13 < i10; i13++) {
                        byte b10 = bArr[i13];
                        if (b10 == 0) {
                            sb2.append(" 0");
                        } else if (b10 != 1) {
                            sb2.append("  ");
                        } else {
                            sb2.append(" 1");
                        }
                    }
                    sb2.append('\n');
                }
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public r1(int i10, int i11, int i12) {
        this.f3111a = i12;
        switch (i12) {
            case 3:
                this.f3114d = null;
                this.f3112b = i10;
                int i13 = i11 & 7;
                this.f3113c = i13 == 0 ? 8 : i13;
                break;
            default:
                this.f3114d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i11, i10);
                this.f3112b = i10;
                this.f3113c = i11;
                break;
        }
    }

    public r1(ArrayList arrayList) {
        this.f3111a = 2;
        this.f3114d = arrayList;
        this.f3112b = arrayList.size();
    }

    public r1(int i10) {
        this.f3111a = i10;
        switch (i10) {
            case 3:
                this.f3114d = new r1[256];
                this.f3112b = 0;
                this.f3113c = 0;
                break;
        }
    }
}
