package androidx.datastore.preferences.protobuf;

import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class m implements Iterable, Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final m f2470m = new m(f1.f2422b);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k f2471n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2472i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte[] f2473l;

    static {
        f2471n = e.a() ? new j(1) : new j(0);
    }

    public m(byte[] bArr) {
        bArr.getClass();
        this.f2473l = bArr;
    }

    public static m a(int i10, int i11, byte[] bArr) {
        int i12 = i10 + i11;
        int length = bArr.length;
        if (((i12 - i10) | i10 | i12 | (length - i12)) >= 0) {
            return new m(f2471n.a(i10, i11, bArr));
        }
        if (i10 < 0) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.o(i10, "Beginning index: ", " < 0"));
        }
        if (i12 < i10) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i10, i12, "Beginning index larger than ending index: ", ", "));
        }
        throw new IndexOutOfBoundsException(androidx.compose.foundation.c.n(i12, length, "End index: ", " >= "));
    }

    public int b() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m) || size() != ((m) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof m)) {
            return obj.equals(this);
        }
        m mVar = (m) obj;
        int i10 = this.f2472i;
        int i11 = mVar.f2472i;
        if (i10 != 0 && i11 != 0 && i10 != i11) {
            return false;
        }
        int size = size();
        if (size > mVar.size()) {
            throw new IllegalArgumentException("Length too large: " + size + size());
        }
        if (size > mVar.size()) {
            StringBuilder sbS = a0.c.s(size, "Ran off end of other: 0, ", ", ");
            sbS.append(mVar.size());
            throw new IllegalArgumentException(sbS.toString());
        }
        byte[] bArr = mVar.f2473l;
        int iB = b() + size;
        int iB2 = b();
        int iB3 = mVar.b();
        while (iB2 < iB) {
            if (this.f2473l[iB2] != bArr[iB3]) {
                return false;
            }
            iB2++;
            iB3++;
        }
        return true;
    }

    public final int hashCode() {
        int i10 = this.f2472i;
        if (i10 != 0) {
            return i10;
        }
        int size = size();
        int iB = b();
        int i11 = size;
        for (int i12 = iB; i12 < iB + size; i12++) {
            i11 = (i11 * 31) + this.f2473l[i12];
        }
        if (i11 == 0) {
            i11 = 1;
        }
        this.f2472i = i11;
        return i11;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new i(this);
    }

    public int size() {
        return this.f2473l.length;
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }
}
