package io.sentry.cache.tape;

import androidx.media3.common.C;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements Closeable, Iterable {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final byte[] f17176u = new byte[4096];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public RandomAccessFile f17177i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final File f17178l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f17179m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17180n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public h f17181o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public h f17182p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final byte[] f17183q = new byte[32];

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17184r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f17185s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f17186t;

    public j(File file, RandomAccessFile randomAccessFile, int i10) throws IOException {
        this.f17178l = file;
        this.f17177i = randomAccessFile;
        this.f17185s = i10;
        G();
    }

    public static int K0(byte[] bArr, int i10) {
        return ((bArr[i10] & 255) << 24) + ((bArr[i10 + 1] & 255) << 16) + ((bArr[i10 + 2] & 255) << 8) + (bArr[i10 + 3] & 255);
    }

    public static long M0(int i10, byte[] bArr) {
        return ((((long) bArr[i10]) & 255) << 56) + ((((long) bArr[i10 + 1]) & 255) << 48) + ((((long) bArr[i10 + 2]) & 255) << 40) + ((((long) bArr[i10 + 3]) & 255) << 32) + ((((long) bArr[i10 + 4]) & 255) << 24) + ((((long) bArr[i10 + 5]) & 255) << 16) + ((((long) bArr[i10 + 6]) & 255) << 8) + (((long) bArr[i10 + 7]) & 255);
    }

    public static void T0(byte[] bArr, int i10, int i11) {
        bArr[i10] = (byte) (i11 >> 24);
        bArr[i10 + 1] = (byte) (i11 >> 16);
        bArr[i10 + 2] = (byte) (i11 >> 8);
        bArr[i10 + 3] = (byte) i11;
    }

    public static void U0(byte[] bArr, int i10, long j10) {
        bArr[i10] = (byte) (j10 >> 56);
        bArr[i10 + 1] = (byte) (j10 >> 48);
        bArr[i10 + 2] = (byte) (j10 >> 40);
        bArr[i10 + 3] = (byte) (j10 >> 32);
        bArr[i10 + 4] = (byte) (j10 >> 24);
        bArr[i10 + 5] = (byte) (j10 >> 16);
        bArr[i10 + 6] = (byte) (j10 >> 8);
        bArr[i10 + 7] = (byte) j10;
    }

    public static RandomAccessFile k(File file) throws IOException {
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rwd");
            try {
                randomAccessFile.setLength(4096L);
                randomAccessFile.seek(0L);
                randomAccessFile.writeInt(C.RATE_UNSET_INT);
                randomAccessFile.writeLong(4096L);
                randomAccessFile.close();
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } catch (Throwable th) {
                randomAccessFile.close();
                throw th;
            }
        }
        return new RandomAccessFile(file, "rwd");
    }

    public final void G() throws IOException {
        this.f17177i.seek(0L);
        RandomAccessFile randomAccessFile = this.f17177i;
        byte[] bArr = this.f17183q;
        randomAccessFile.readFully(bArr);
        this.f17179m = M0(4, bArr);
        this.f17180n = K0(bArr, 12);
        long jM0 = M0(16, bArr);
        long jM02 = M0(24, bArr);
        if (this.f17179m > this.f17177i.length()) {
            throw new IOException("File is truncated. Expected length: " + this.f17179m + ", Actual length: " + this.f17177i.length());
        }
        if (this.f17179m <= 32) {
            throw new IOException(a0.c.j(this.f17179m, ") is invalid.", new StringBuilder("File is corrupt; length stored in header (")));
        }
        this.f17181o = y(jM0);
        this.f17182p = y(jM02);
    }

    public final void N0(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Cannot remove negative (", ") number of elements."));
        }
        if (i10 == 0) {
            return;
        }
        int i11 = this.f17180n;
        if (i10 == i11) {
            clear();
            return;
        }
        if (i11 == 0) {
            throw new NoSuchElementException();
        }
        if (i10 > i11) {
            throw new IllegalArgumentException(androidx.compose.material3.d.j(this.f17180n, ").", a0.c.s(i10, "Cannot remove more elements (", ") than present in queue (")));
        }
        h hVar = this.f17181o;
        long j10 = hVar.f17170a;
        int iK0 = hVar.f17171b;
        long jR0 = j10;
        long j11 = 0;
        for (int i12 = 0; i12 < i10; i12++) {
            j11 += (long) (iK0 + 4);
            jR0 = R0(jR0 + 4 + ((long) iK0));
            byte[] bArr = this.f17183q;
            if (!P0(bArr, 4, jR0)) {
                return;
            }
            iK0 = K0(bArr, 0);
        }
        S0(this.f17180n - i10, this.f17179m, jR0, this.f17182p.f17170a);
        this.f17180n -= i10;
        this.f17184r++;
        this.f17181o = new h(jR0, iK0);
        while (j11 > 0) {
            int iMin = (int) Math.min(j11, 4096);
            Q0(f17176u, iMin, j10);
            long j12 = iMin;
            j11 -= j12;
            j10 += j12;
        }
    }

    public final void O0() {
        this.f17177i.close();
        File file = this.f17178l;
        file.delete();
        this.f17177i = k(file);
        G();
    }

    public final boolean P0(byte[] bArr, int i10, long j10) throws IOException {
        try {
            long jR0 = R0(j10);
            long j11 = ((long) i10) + jR0;
            long j12 = this.f17179m;
            if (j11 <= j12) {
                this.f17177i.seek(jR0);
                this.f17177i.readFully(bArr, 0, i10);
                return true;
            }
            int i11 = (int) (j12 - jR0);
            this.f17177i.seek(jR0);
            this.f17177i.readFully(bArr, 0, i11);
            this.f17177i.seek(32L);
            this.f17177i.readFully(bArr, i11, i10 - i11);
            return true;
        } catch (EOFException unused) {
            O0();
            return false;
        } catch (IOException e5) {
            throw e5;
        } catch (Throwable unused2) {
            O0();
            return false;
        }
    }

    public final void Q0(byte[] bArr, int i10, long j10) throws IOException {
        long jR0 = R0(j10);
        long j11 = ((long) i10) + jR0;
        long j12 = this.f17179m;
        if (j11 <= j12) {
            this.f17177i.seek(jR0);
            this.f17177i.write(bArr, 0, i10);
            return;
        }
        int i11 = (int) (j12 - jR0);
        this.f17177i.seek(jR0);
        this.f17177i.write(bArr, 0, i11);
        this.f17177i.seek(32L);
        this.f17177i.write(bArr, i11, i10 - i11);
    }

    public final long R0(long j10) {
        long j11 = this.f17179m;
        return j10 < j11 ? j10 : (j10 + 32) - j11;
    }

    public final void S0(int i10, long j10, long j11, long j12) throws IOException {
        this.f17177i.seek(0L);
        byte[] bArr = this.f17183q;
        T0(bArr, 0, C.RATE_UNSET_INT);
        U0(bArr, 4, j10);
        T0(bArr, 12, i10);
        U0(bArr, 16, j11);
        U0(bArr, 24, j12);
        this.f17177i.write(bArr, 0, 32);
    }

    public final void clear() throws IOException {
        if (this.f17186t) {
            throw new IllegalStateException("closed");
        }
        S0(0, 4096L, 0L, 0L);
        this.f17177i.seek(32L);
        this.f17177i.write(f17176u, 0, 4064);
        this.f17180n = 0;
        h hVar = h.f17169c;
        this.f17181o = hVar;
        this.f17182p = hVar;
        if (this.f17179m > 4096) {
            this.f17177i.setLength(4096L);
            this.f17177i.getChannel().force(true);
        }
        this.f17179m = 4096L;
        this.f17184r++;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f17186t = true;
        this.f17177i.close();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new i(this);
    }

    public final String toString() {
        return "QueueFile{file=" + this.f17178l + ", zero=true, length=" + this.f17179m + ", size=" + this.f17180n + ", first=" + this.f17181o + ", last=" + this.f17182p + '}';
    }

    public final h y(long j10) {
        if (j10 != 0) {
            byte[] bArr = this.f17183q;
            if (P0(bArr, 4, j10)) {
                return new h(j10, K0(bArr, 0));
            }
        }
        return h.f17169c;
    }
}
