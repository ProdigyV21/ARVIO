package xb;

import java.io.Closeable;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends p {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f22809n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Closeable f22810o;

    public /* synthetic */ z(Closeable closeable, int i10) {
        this.f22809n = i10;
        this.f22810o = closeable;
    }

    private final synchronized int G(long j10, byte[] bArr, int i10, int i11) {
        ((FileChannel) this.f22810o).position(j10);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i10, i11);
        int i12 = 0;
        while (true) {
            if (i12 >= i11) {
                break;
            }
            int i13 = ((FileChannel) this.f22810o).read(byteBufferWrap);
            if (i13 != -1) {
                i12 += i13;
            } else if (i12 == 0) {
                return -1;
            }
        }
        return i12;
    }

    private final synchronized long O() {
        return ((RandomAccessFile) this.f22810o).length();
    }

    private final synchronized long R() {
        return ((FileChannel) this.f22810o).size();
    }

    private final synchronized void o() {
        ((RandomAccessFile) this.f22810o).close();
    }

    private final synchronized void x() {
        ((FileChannel) this.f22810o).close();
    }

    private final synchronized int y(long j10, byte[] bArr, int i10, int i11) {
        ((RandomAccessFile) this.f22810o).seek(j10);
        int i12 = 0;
        while (true) {
            if (i12 >= i11) {
                break;
            }
            int i13 = ((RandomAccessFile) this.f22810o).read(bArr, i10, i11 - i12);
            if (i13 != -1) {
                i12 += i13;
            } else if (i12 == 0) {
                return -1;
            }
        }
        return i12;
    }

    @Override // xb.p
    public final synchronized void i() {
        switch (this.f22809n) {
            case 0:
                o();
                break;
            default:
                x();
                break;
        }
    }

    @Override // xb.p
    public final synchronized int j(long j10, byte[] bArr, int i10, int i11) {
        switch (this.f22809n) {
            case 0:
                return y(j10, bArr, i10, i11);
            default:
                return G(j10, bArr, i10, i11);
        }
    }

    @Override // xb.p
    public final synchronized long k() {
        switch (this.f22809n) {
            case 0:
                return O();
            default:
                return R();
        }
    }
}
