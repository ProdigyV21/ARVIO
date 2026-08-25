package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g implements Iterable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b0 f19772i = new b0(new byte[0]);

    public static g a(Iterator it, int i10) {
        if (i10 == 1) {
            return (g) it.next();
        }
        int i11 = i10 >>> 1;
        return a(it, i11).b(a(it, i10 - i11));
    }

    public static f h() {
        return new f();
    }

    public final g b(g gVar) {
        int size = size();
        int size2 = gVar.size();
        if (((long) size) + ((long) size2) >= 2147483647L) {
            StringBuilder sb2 = new StringBuilder(53);
            sb2.append("ByteString would be too long: ");
            sb2.append(size);
            sb2.append("+");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        int[] iArr = h0.f19776r;
        h0 h0Var = this instanceof h0 ? (h0) this : null;
        if (gVar.size() == 0) {
            return this;
        }
        if (size() == 0) {
            return gVar;
        }
        int size3 = gVar.size() + size();
        if (size3 < 128) {
            int size4 = size();
            int size5 = gVar.size();
            byte[] bArr = new byte[size4 + size5];
            c(0, 0, size4, bArr);
            gVar.c(0, size4, size5, bArr);
            return new b0(bArr);
        }
        if (h0Var != null) {
            g gVar2 = h0Var.f19779n;
            if (gVar.size() + gVar2.size() < 128) {
                int size6 = gVar2.size();
                int size7 = gVar.size();
                byte[] bArr2 = new byte[size6 + size7];
                gVar2.c(0, 0, size6, bArr2);
                gVar.c(0, size6, size7, bArr2);
                return new h0(h0Var.f19778m, new b0(bArr2));
            }
        }
        if (h0Var != null) {
            g gVar3 = h0Var.f19779n;
            g gVar4 = h0Var.f19778m;
            if (gVar4.e() > gVar3.e() && h0Var.f19781p > gVar.e()) {
                return new h0(gVar4, new h0(gVar3, gVar));
            }
        }
        if (size3 >= h0.f19776r[Math.max(e(), gVar.e()) + 1]) {
            return new h0(this, gVar);
        }
        kb.d dVar = new kb.d(1);
        dVar.i(this);
        dVar.i(gVar);
        Stack stack = (Stack) dVar.f19673l;
        g h0Var2 = (g) stack.pop();
        while (!stack.isEmpty()) {
            h0Var2 = new h0((g) stack.pop(), h0Var2);
        }
        return h0Var2;
    }

    public final void c(int i10, int i11, int i12, byte[] bArr) {
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Source offset < 0: ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        if (i11 < 0) {
            StringBuilder sb3 = new StringBuilder(30);
            sb3.append("Target offset < 0: ");
            sb3.append(i11);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
        if (i12 < 0) {
            StringBuilder sb4 = new StringBuilder(23);
            sb4.append("Length < 0: ");
            sb4.append(i12);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
        int i13 = i10 + i12;
        if (i13 > size()) {
            StringBuilder sb5 = new StringBuilder(34);
            sb5.append("Source end offset < 0: ");
            sb5.append(i13);
            throw new IndexOutOfBoundsException(sb5.toString());
        }
        int i14 = i11 + i12;
        if (i14 <= bArr.length) {
            if (i12 > 0) {
                d(i10, i11, i12, bArr);
            }
        } else {
            StringBuilder sb6 = new StringBuilder(34);
            sb6.append("Target end offset < 0: ");
            sb6.append(i14);
            throw new IndexOutOfBoundsException(sb6.toString());
        }
    }

    public abstract void d(int i10, int i11, int i12, byte[] bArr);

    public abstract int e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract int k(int i10, int i11, int i12);

    public abstract int m(int i10, int i11, int i12);

    public abstract int n();

    public abstract String p();

    public final String q() {
        try {
            return p();
        } catch (UnsupportedEncodingException e5) {
            throw new RuntimeException("UTF-8 not supported?", e5);
        }
    }

    public abstract void r(OutputStream outputStream, int i10, int i11);

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }
}
