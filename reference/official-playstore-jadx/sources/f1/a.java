package f1;

import android.media.MediaDataSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends MediaDataSource {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f15205i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h f15206l;

    public a(h hVar) {
        this.f15206l = hVar;
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return -1L;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j10, byte[] bArr, int i10, int i11) {
        if (i11 == 0) {
            return 0;
        }
        if (j10 < 0) {
            return -1;
        }
        try {
            long j11 = this.f15205i;
            h hVar = this.f15206l;
            if (j11 != j10) {
                if (j11 >= 0 && j10 >= j11 + ((long) hVar.f15209i.available())) {
                    return -1;
                }
                hVar.j(j10);
                this.f15205i = j10;
            }
            if (i11 > hVar.f15209i.available()) {
                i11 = hVar.f15209i.available();
            }
            int i12 = hVar.read(bArr, i10, i11);
            if (i12 >= 0) {
                this.f15205i += (long) i12;
                return i12;
            }
        } catch (IOException unused) {
        }
        this.f15205i = -1L;
        return -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
