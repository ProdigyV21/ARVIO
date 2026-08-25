package ob;

import io.ktor.network.sockets.DatagramKt;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f20882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f20883b = new int[10];

    public final int a() {
        return (this.f20882a & 128) != 0 ? this.f20883b[7] : DatagramKt.MAX_DATAGRAM_SIZE;
    }

    public final void b(int i10, int i11) {
        if (i10 >= 0) {
            int[] iArr = this.f20883b;
            if (i10 >= iArr.length) {
                return;
            }
            this.f20882a = (1 << i10) | this.f20882a;
            iArr[i10] = i11;
        }
    }
}
