package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends OutputStream {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final byte[] f19766p = new byte[0];

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19769m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f19771o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f19767i = 128;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f19768l = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public byte[] f19770n = new byte[128];

    public final void i(int i10) {
        this.f19768l.add(new b0(this.f19770n));
        int length = this.f19769m + this.f19770n.length;
        this.f19769m = length;
        this.f19770n = new byte[Math.max(this.f19767i, Math.max(i10, length >>> 1))];
        this.f19771o = 0;
    }

    public final void j() {
        int i10 = this.f19771o;
        byte[] bArr = this.f19770n;
        int length = bArr.length;
        ArrayList arrayList = this.f19768l;
        if (i10 >= length) {
            arrayList.add(new b0(this.f19770n));
            this.f19770n = f19766p;
        } else if (i10 > 0) {
            byte[] bArr2 = new byte[i10];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i10));
            arrayList.add(new b0(bArr2));
        }
        this.f19769m += this.f19771o;
        this.f19771o = 0;
    }

    public final synchronized g k() {
        ArrayList arrayList;
        j();
        arrayList = this.f19768l;
        if (!(arrayList != null)) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add((g) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList.isEmpty() ? g.f19772i : g.a(arrayList.iterator(), arrayList.size());
    }

    public final String toString() {
        int i10;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        synchronized (this) {
            i10 = this.f19769m + this.f19771o;
        }
        return String.format("<ByteString.Output@%s size=%d>", hexString, Integer.valueOf(i10));
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int i10) {
        try {
            if (this.f19771o == this.f19770n.length) {
                i(1);
            }
            byte[] bArr = this.f19770n;
            int i11 = this.f19771o;
            this.f19771o = i11 + 1;
            bArr[i11] = (byte) i10;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i10, int i11) {
        try {
            byte[] bArr2 = this.f19770n;
            int length = bArr2.length;
            int i12 = this.f19771o;
            if (i11 <= length - i12) {
                System.arraycopy(bArr, i10, bArr2, i12, i11);
                this.f19771o += i11;
            } else {
                int length2 = bArr2.length - i12;
                System.arraycopy(bArr, i10, bArr2, i12, length2);
                int i13 = i11 - length2;
                i(i13);
                System.arraycopy(bArr, i10 + length2, this.f19770n, 0, i13);
                this.f19771o = i13;
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
