package xb;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends InputStream {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22748i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f22749l;

    public /* synthetic */ h(l lVar, int i10) {
        this.f22748i = i10;
        this.f22749l = lVar;
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        long jMin;
        switch (this.f22748i) {
            case 0:
                jMin = Math.min(((j) this.f22749l).f22759l, Integer.MAX_VALUE);
                break;
            default:
                i0 i0Var = (i0) this.f22749l;
                if (i0Var.f22757m) {
                    throw new IOException("closed");
                }
                jMin = Math.min(i0Var.f22756l.f22759l, Integer.MAX_VALUE);
                break;
        }
        return (int) jMin;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.f22748i) {
            case 0:
                break;
            default:
                ((i0) this.f22749l).close();
                break;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        switch (this.f22748i) {
            case 0:
                j jVar = (j) this.f22749l;
                if (jVar.f22759l > 0) {
                    return jVar.readByte() & 255;
                }
                return -1;
            default:
                i0 i0Var = (i0) this.f22749l;
                j jVar2 = i0Var.f22756l;
                if (i0Var.f22757m) {
                    throw new IOException("closed");
                }
                if (jVar2.f22759l == 0 && i0Var.f22755i.read(jVar2, 8192L) == -1) {
                    return -1;
                }
                return jVar2.readByte() & 255;
        }
    }

    public final String toString() {
        switch (this.f22748i) {
            case 0:
                return ((j) this.f22749l) + ".inputStream()";
            default:
                return ((i0) this.f22749l) + ".inputStream()";
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i10, int i11) throws IOException {
        switch (this.f22748i) {
            case 0:
                return ((j) this.f22749l).read(bArr, i10, i11);
            default:
                i0 i0Var = (i0) this.f22749l;
                j jVar = i0Var.f22756l;
                if (!i0Var.f22757m) {
                    b.c(bArr.length, i10, i11);
                    if (jVar.f22759l == 0 && i0Var.f22755i.read(jVar, 8192L) == -1) {
                        return -1;
                    }
                    return jVar.read(bArr, i10, i11);
                }
                throw new IOException("closed");
        }
    }

    private final void i() {
    }
}
