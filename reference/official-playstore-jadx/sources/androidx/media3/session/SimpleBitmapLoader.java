package androidx.media3.session;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class SimpleBitmapLoader implements androidx.media3.common.util.BitmapLoader {
    private static final com.google.common.base.c0<com.google.common.util.concurrent.g1> DEFAULT_EXECUTOR_SERVICE = com.google.common.util.concurrent.p0.v(new i2(1));
    private static final String FILE_URI_EXCEPTION_MESSAGE = "Could not read image from file";
    private final com.google.common.util.concurrent.g1 executorService;

    /* JADX WARN: Illegal instructions before constructor call */
    public SimpleBitmapLoader() {
        com.google.common.util.concurrent.g1 g1Var = (com.google.common.util.concurrent.g1) DEFAULT_EXECUTOR_SERVICE.get();
        g1Var.getClass();
        this(g1Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap decode(byte[] bArr) {
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        ac.b.k(bitmapDecodeByteArray != null, "Could not decode image data");
        return bitmapDecodeByteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.g1 lambda$static$0() {
        return a.a.U(Executors.newSingleThreadExecutor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap load(Uri uri) throws IOException {
        if ("file".equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path == null) {
                throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
            }
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(path);
            if (bitmapDecodeFile != null) {
                return bitmapDecodeFile;
            }
            throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
        }
        URLConnection uRLConnectionOpenConnection = new URL(uri.toString()).openConnection();
        if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
            throw new UnsupportedOperationException("Unsupported scheme: " + uri.getScheme());
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException(a0.c.i(responseCode, "Invalid response status code: "));
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            Bitmap bitmapDecode = decode(com.google.common.io.i.b(inputStream));
            inputStream.close();
            return bitmapDecode;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> decodeBitmap(byte[] bArr) {
        return this.executorService.submit((Callable) new k3(bArr, 1));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> loadBitmap(Uri uri) {
        return this.executorService.submit((Callable) new k3(uri, 0));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public final /* synthetic */ com.google.common.util.concurrent.d1 loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        return androidx.media3.common.util.b.a(this, mediaMetadata);
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    public SimpleBitmapLoader(ExecutorService executorService) {
        this.executorService = a.a.U(executorService);
    }
}
