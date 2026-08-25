package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19799b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Serializable f19800c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f19801d;

    public j(int i10, int i11, String str, String str2) {
        this.f19798a = i10;
        this.f19800c = str;
        this.f19799b = i11;
        this.f19801d = str2;
    }

    public static int a(int i10, int i11) {
        return c(i11) + h(i10);
    }

    public static int b(int i10, int i11) {
        return c(i11) + h(i10);
    }

    public static int c(int i10) {
        if (i10 >= 0) {
            return f(i10);
        }
        return 10;
    }

    public static int d(int i10, c0 c0Var) {
        return e(c0Var) + h(i10);
    }

    public static int e(c0 c0Var) {
        int iA = c0Var.a();
        return f(iA) + iA;
    }

    public static int f(int i10) {
        if ((i10 & (-128)) == 0) {
            return 1;
        }
        if ((i10 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i10) == 0) {
            return 3;
        }
        return (i10 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int g(long j10) {
        if (((-128) & j10) == 0) {
            return 1;
        }
        if (((-16384) & j10) == 0) {
            return 2;
        }
        if (((-2097152) & j10) == 0) {
            return 3;
        }
        if (((-268435456) & j10) == 0) {
            return 4;
        }
        if (((-34359738368L) & j10) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j10) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j10) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j10) == 0) {
            return 8;
        }
        return (j10 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int h(int i10) {
        return f(i10 << 3);
    }

    public static j j(OutputStream outputStream, int i10) {
        return new j(outputStream, new byte[i10]);
    }

    public void i() throws IOException {
        if (((OutputStream) this.f19801d) != null) {
            k();
        }
    }

    public void k() throws IOException {
        OutputStream outputStream = (OutputStream) this.f19801d;
        if (outputStream == null) {
            throw new IOException() { // from class: kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream$OutOfSpaceException
            };
        }
        outputStream.write((byte[]) this.f19800c, 0, this.f19799b);
        this.f19799b = 0;
    }

    public void l(int i10, int i11) {
        x(i10, 0);
        n(i11);
    }

    public void m(int i10, int i11) {
        x(i10, 0);
        n(i11);
    }

    public void n(int i10) {
        if (i10 >= 0) {
            v(i10);
        } else {
            w(i10);
        }
    }

    public void o(int i10, c0 c0Var) {
        x(i10, 2);
        p(c0Var);
    }

    public void p(c0 c0Var) {
        v(c0Var.a());
        c0Var.e(this);
    }

    public void q(int i10) throws IOException {
        byte b10 = (byte) i10;
        if (this.f19799b == this.f19798a) {
            k();
        }
        byte[] bArr = (byte[]) this.f19800c;
        int i11 = this.f19799b;
        this.f19799b = i11 + 1;
        bArr[i11] = b10;
    }

    public void r(g gVar) {
        int size = gVar.size();
        byte[] bArr = (byte[]) this.f19800c;
        int i10 = this.f19799b;
        int i11 = this.f19798a;
        int i12 = i11 - i10;
        if (i12 >= size) {
            gVar.c(0, i10, size, bArr);
            this.f19799b += size;
            return;
        }
        gVar.c(0, i10, i12, bArr);
        int i13 = size - i12;
        this.f19799b = i11;
        k();
        if (i13 <= i11) {
            gVar.c(i12, 0, i13, bArr);
            this.f19799b = i13;
            return;
        }
        OutputStream outputStream = (OutputStream) this.f19801d;
        if (i12 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Source offset < 0: ");
            sb2.append(i12);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        if (i13 < 0) {
            StringBuilder sb3 = new StringBuilder(23);
            sb3.append("Length < 0: ");
            sb3.append(i13);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
        int i14 = i12 + i13;
        if (i14 <= gVar.size()) {
            if (i13 > 0) {
                gVar.r(outputStream, i12, i13);
            }
        } else {
            StringBuilder sb4 = new StringBuilder(39);
            sb4.append("Source end offset exceeded: ");
            sb4.append(i14);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
    }

    public void s(byte[] bArr) throws IOException {
        int length = bArr.length;
        byte[] bArr2 = (byte[]) this.f19800c;
        int i10 = this.f19799b;
        int i11 = this.f19798a;
        int i12 = i11 - i10;
        if (i12 >= length) {
            System.arraycopy(bArr, 0, bArr2, i10, length);
            this.f19799b += length;
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i10, i12);
        int i13 = length - i12;
        this.f19799b = i11;
        k();
        if (i13 > i11) {
            ((OutputStream) this.f19801d).write(bArr, i12, i13);
        } else {
            System.arraycopy(bArr, i12, bArr2, 0, i13);
            this.f19799b = i13;
        }
    }

    public void t(int i10) {
        q(i10 & 255);
        q((i10 >> 8) & 255);
        q((i10 >> 16) & 255);
        q((i10 >> 24) & 255);
    }

    public void u(long j10) {
        q(((int) j10) & 255);
        q(((int) (j10 >> 8)) & 255);
        q(((int) (j10 >> 16)) & 255);
        q(((int) (j10 >> 24)) & 255);
        q(((int) (j10 >> 32)) & 255);
        q(((int) (j10 >> 40)) & 255);
        q(((int) (j10 >> 48)) & 255);
        q(((int) (j10 >> 56)) & 255);
    }

    public void v(int i10) {
        while ((i10 & (-128)) != 0) {
            q((i10 & 127) | 128);
            i10 >>>= 7;
        }
        q(i10);
    }

    public void w(long j10) {
        while (((-128) & j10) != 0) {
            q((((int) j10) & 127) | 128);
            j10 >>>= 7;
        }
        q((int) j10);
    }

    public void x(int i10, int i11) {
        v((i10 << 3) | i11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j(OutputStream outputStream, byte[] bArr) {
        this.f19801d = outputStream;
        this.f19800c = bArr;
        this.f19799b = 0;
        this.f19798a = bArr.length;
    }
}
