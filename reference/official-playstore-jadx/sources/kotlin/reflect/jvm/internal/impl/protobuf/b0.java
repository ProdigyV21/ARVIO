package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public class b0 extends g {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f19764l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19765m = 0;

    public b0(byte[] bArr) {
        this.f19764l = bArr;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public void d(int i10, int i11, int i12, byte[] bArr) {
        System.arraycopy(this.f19764l, i10, bArr, i11, i12);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int e() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g) || size() != ((g) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof b0) {
            return s((b0) obj, 0, size());
        }
        if (obj instanceof h0) {
            return obj.equals(this);
        }
        String strValueOf = String.valueOf(obj.getClass());
        throw new IllegalArgumentException(a0.c.p(new StringBuilder(strValueOf.length() + 49), "Has a new type of ByteString been created? Found ", strValueOf));
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final boolean f() {
        return true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final boolean g() {
        byte[] bArr = this.f19764l;
        return l0.c(0, bArr.length, bArr) == 0;
    }

    public final int hashCode() {
        int iK = this.f19765m;
        if (iK == 0) {
            int size = size();
            iK = k(size, 0, size);
            if (iK == 0) {
                iK = 1;
            }
            this.f19765m = iK;
        }
        return iK;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new a0(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int k(int i10, int i11, int i12) {
        for (int i13 = i11; i13 < i11 + i12; i13++) {
            i10 = (i10 * 31) + this.f19764l[i13];
        }
        return i10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
    
        if (r0[r9] > (-65)) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
    
        r9 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0049, code lost:
    
        if (r0[r9] > (-65)) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0092, code lost:
    
        if (r0[r8] > (-65)) goto L59;
     */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m(int r8, int r9, int r10) {
        /*
            r7 = this;
            int r10 = r10 + r9
            byte[] r0 = r7.f19764l
            if (r8 == 0) goto L95
            if (r9 < r10) goto L8
            return r8
        L8:
            byte r1 = (byte) r8
            r2 = -32
            r3 = -1
            r4 = -65
            if (r1 >= r2) goto L1f
            r8 = -62
            if (r1 < r8) goto L94
            int r8 = r9 + 1
            r9 = r0[r9]
            if (r9 <= r4) goto L1c
            goto L94
        L1c:
            r9 = r8
            goto L95
        L1f:
            r5 = -16
            if (r1 >= r5) goto L4c
            int r8 = r8 >> 8
            int r8 = ~r8
            byte r8 = (byte) r8
            if (r8 != 0) goto L37
            int r8 = r9 + 1
            r9 = r0[r9]
            if (r8 < r10) goto L34
            int r8 = kotlin.reflect.jvm.internal.impl.protobuf.l0.a(r1, r9)
            return r8
        L34:
            r6 = r9
            r9 = r8
            r8 = r6
        L37:
            if (r8 > r4) goto L94
            r5 = -96
            if (r1 != r2) goto L3f
            if (r8 < r5) goto L94
        L3f:
            r2 = -19
            if (r1 != r2) goto L45
            if (r8 >= r5) goto L94
        L45:
            int r8 = r9 + 1
            r9 = r0[r9]
            if (r9 <= r4) goto L1c
            goto L94
        L4c:
            int r2 = r8 >> 8
            int r2 = ~r2
            byte r2 = (byte) r2
            if (r2 != 0) goto L5f
            int r8 = r9 + 1
            r2 = r0[r9]
            if (r8 < r10) goto L5d
            int r8 = kotlin.reflect.jvm.internal.impl.protobuf.l0.a(r1, r2)
            return r8
        L5d:
            r9 = 0
            goto L65
        L5f:
            int r8 = r8 >> 16
            byte r8 = (byte) r8
            r6 = r9
            r9 = r8
            r8 = r6
        L65:
            if (r9 != 0) goto L81
            int r9 = r8 + 1
            r8 = r0[r8]
            if (r9 < r10) goto L7e
            r9 = -12
            if (r1 > r9) goto L7d
            if (r2 > r4) goto L7d
            if (r8 <= r4) goto L76
            goto L7d
        L76:
            int r9 = r2 << 8
            r9 = r9 ^ r1
            int r8 = r8 << 16
            r8 = r8 ^ r9
            return r8
        L7d:
            return r3
        L7e:
            r6 = r9
            r9 = r8
            r8 = r6
        L81:
            if (r2 > r4) goto L94
            int r1 = r1 << 28
            int r2 = r2 + 112
            int r2 = r2 + r1
            int r1 = r2 >> 30
            if (r1 != 0) goto L94
            if (r9 > r4) goto L94
            int r9 = r8 + 1
            r8 = r0[r8]
            if (r8 <= r4) goto L95
        L94:
            return r3
        L95:
            int r8 = kotlin.reflect.jvm.internal.impl.protobuf.l0.c(r9, r10, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.b0.m(int, int, int):int");
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final int n() {
        return this.f19765m;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final String p() {
        byte[] bArr = this.f19764l;
        return new String(bArr, 0, bArr.length, "UTF-8");
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public final void r(OutputStream outputStream, int i10, int i11) throws IOException {
        outputStream.write(this.f19764l, i10, i11);
    }

    public final boolean s(b0 b0Var, int i10, int i11) {
        byte[] bArr = b0Var.f19764l;
        int length = bArr.length;
        byte[] bArr2 = this.f19764l;
        if (i11 > length) {
            int length2 = bArr2.length;
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Length too large: ");
            sb2.append(i11);
            sb2.append(length2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (i10 + i11 <= bArr.length) {
            int i12 = 0;
            while (i12 < i11) {
                if (bArr2[i12] != bArr[i10]) {
                    return false;
                }
                i12++;
                i10++;
            }
            return true;
        }
        int length3 = b0Var.f19764l.length;
        StringBuilder sb3 = new StringBuilder(59);
        sb3.append("Ran off end of other: ");
        sb3.append(i10);
        sb3.append(", ");
        sb3.append(i11);
        throw new IllegalArgumentException(androidx.compose.foundation.c.p(length3, ", ", sb3));
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.g
    public int size() {
        return this.f19764l.length;
    }
}
