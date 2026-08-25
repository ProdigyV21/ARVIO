package androidx.media3.datasource;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import androidx.fragment.app.a2;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes3.dex */
public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private boolean opened;
    private Uri uri;

    public static final class Factory implements DataSource.Factory {
        private TransferListener listener;

        public Factory setListener(TransferListener transferListener) {
            this.listener = transferListener;
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public FileDataSource createDataSource() {
            FileDataSource fileDataSource = new FileDataSource();
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                fileDataSource.addTransferListener(transferListener);
            }
            return fileDataSource;
        }
    }

    public static class FileDataSourceException extends DataSourceException {
        @Deprecated
        public FileDataSourceException(Exception exc) {
            super(exc, 2000);
        }

        @Deprecated
        public FileDataSourceException(String str, IOException iOException) {
            super(str, iOException, 2000);
        }

        public FileDataSourceException(Throwable th, int i10) {
            super(th, i10);
        }

        public FileDataSourceException(String str, Throwable th, int i10) {
            super(str, th, i10);
        }
    }

    public FileDataSource() {
        super(false);
    }

    private static RandomAccessFile openLocalFile(Uri uri) throws FileDataSourceException {
        try {
            String path = uri.getPath();
            path.getClass();
            return new RandomAccessFile(path, "r");
        } catch (FileNotFoundException e5) {
            if (TextUtils.isEmpty(uri.getQuery()) && TextUtils.isEmpty(uri.getFragment())) {
                throw new FileDataSourceException(e5, ((e5.getCause() instanceof ErrnoException) && ((ErrnoException) e5.getCause()).errno == OsConstants.EACCES) ? 2006 : 2005);
            }
            String path2 = uri.getPath();
            String query = uri.getQuery();
            String fragment = uri.getFragment();
            StringBuilder sbR = a2.r("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=", path2, ",query=", query, ",fragment=");
            sbR.append(fragment);
            throw new FileDataSourceException(sbR.toString(), e5, 1004);
        } catch (SecurityException e6) {
            throw new FileDataSourceException(e6, 2006);
        } catch (RuntimeException e10) {
            throw new FileDataSourceException(e10, 2000);
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws FileDataSourceException {
        this.uri = null;
        try {
            try {
                RandomAccessFile randomAccessFile = this.file;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e5) {
                throw new FileDataSourceException(e5, 2000);
            }
        } finally {
            this.file = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        }
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws FileDataSourceException {
        Uri uri = dataSpec.uri;
        this.uri = uri;
        transferInitializing(dataSpec);
        RandomAccessFile randomAccessFileOpenLocalFile = openLocalFile(uri);
        this.file = randomAccessFileOpenLocalFile;
        try {
            randomAccessFileOpenLocalFile.seek(dataSpec.position);
            long length = dataSpec.length;
            if (length == -1) {
                length = this.file.length() - dataSpec.position;
            }
            this.bytesRemaining = length;
            if (length < 0) {
                throw new FileDataSourceException(null, null, 2008);
            }
            this.opened = true;
            transferStarted(dataSpec);
            return this.bytesRemaining;
        } catch (IOException e5) {
            throw new FileDataSourceException(e5, 2000);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i10, int i11) throws FileDataSourceException {
        if (i11 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            int i12 = ((RandomAccessFile) Util.castNonNull(this.file)).read(bArr, i10, (int) Math.min(this.bytesRemaining, i11));
            if (i12 > 0) {
                this.bytesRemaining -= (long) i12;
                bytesTransferred(i12);
            }
            return i12;
        } catch (IOException e5) {
            throw new FileDataSourceException(e5, 2000);
        }
    }
}
