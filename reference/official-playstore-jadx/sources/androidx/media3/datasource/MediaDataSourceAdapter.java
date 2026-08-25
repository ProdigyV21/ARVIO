package androidx.media3.datasource;

import android.media.MediaDataSource;
import android.net.Uri;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class MediaDataSourceAdapter extends BaseDataSource {
    private long bytesRemaining;
    private final MediaDataSource mediaDataSource;
    private boolean opened;
    private long position;
    private Uri uri;

    public MediaDataSourceAdapter(MediaDataSource mediaDataSource, boolean z) {
        super(z);
        this.mediaDataSource = mediaDataSource;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.uri = null;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        this.position = dataSpec.position;
        transferInitializing(dataSpec);
        if (this.mediaDataSource.getSize() != -1 && this.position > this.mediaDataSource.getSize()) {
            throw new DataSourceException(2008);
        }
        if (this.mediaDataSource.getSize() == -1) {
            this.bytesRemaining = -1L;
        } else {
            this.bytesRemaining = this.mediaDataSource.getSize() - this.position;
        }
        long jMin = dataSpec.length;
        if (jMin != -1) {
            long j10 = this.bytesRemaining;
            if (j10 != -1) {
                jMin = Math.min(j10, jMin);
            }
            this.bytesRemaining = jMin;
        }
        this.opened = true;
        transferStarted(dataSpec);
        long j11 = dataSpec.length;
        return j11 != -1 ? j11 : this.bytesRemaining;
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i10, int i11) throws DataSourceException {
        if (i11 == 0) {
            return 0;
        }
        long j10 = this.bytesRemaining;
        if (j10 == 0) {
            return -1;
        }
        if (j10 != -1) {
            i11 = (int) Math.min(j10, i11);
        }
        try {
            int at = this.mediaDataSource.readAt(this.position, bArr, i10, i11);
            if (at == -1) {
                return -1;
            }
            long j11 = at;
            this.position += j11;
            long j12 = this.bytesRemaining;
            if (j12 != -1) {
                this.bytesRemaining = j12 - j11;
            }
            bytesTransferred(at);
            return at;
        } catch (IOException e5) {
            throw new DataSourceException(e5, 2000);
        }
    }
}
