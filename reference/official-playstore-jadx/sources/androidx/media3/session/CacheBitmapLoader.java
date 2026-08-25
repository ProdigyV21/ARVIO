package androidx.media3.session;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class CacheBitmapLoader implements androidx.media3.common.util.BitmapLoader {
    private final androidx.media3.common.util.BitmapLoader bitmapLoader;
    private BitmapLoadRequest lastBitmapLoadRequest;

    public static class BitmapLoadRequest {
        private final byte[] data;
        private final com.google.common.util.concurrent.d1<Bitmap> future;
        private final Uri uri;

        /* JADX INFO: Access modifiers changed from: private */
        public com.google.common.util.concurrent.d1<Bitmap> getFuture() {
            com.google.common.util.concurrent.d1<Bitmap> d1Var = this.future;
            d1Var.getClass();
            return d1Var;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean matches(byte[] bArr) {
            byte[] bArr2 = this.data;
            return bArr2 != null && Arrays.equals(bArr2, bArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean matches(Uri uri) {
            Uri uri2 = this.uri;
            return uri2 != null && uri2.equals(uri);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean matches(MediaMetadata mediaMetadata) {
            Uri uri = this.uri;
            if (uri != null && uri.equals(mediaMetadata.artworkUri)) {
                return true;
            }
            byte[] bArr = this.data;
            return bArr != null && Arrays.equals(bArr, mediaMetadata.artworkData);
        }

        private BitmapLoadRequest(byte[] bArr, com.google.common.util.concurrent.d1<Bitmap> d1Var) {
            this.data = bArr;
            this.uri = null;
            this.future = d1Var;
        }

        private BitmapLoadRequest(Uri uri, com.google.common.util.concurrent.d1<Bitmap> d1Var) {
            this.data = null;
            this.uri = uri;
            this.future = d1Var;
        }

        private BitmapLoadRequest(MediaMetadata mediaMetadata, com.google.common.util.concurrent.d1<Bitmap> d1Var) {
            this.data = mediaMetadata.artworkData;
            this.uri = mediaMetadata.artworkUri;
            this.future = d1Var;
        }
    }

    public CacheBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
        this.bitmapLoader = bitmapLoader;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> decodeBitmap(byte[] bArr) {
        BitmapLoadRequest bitmapLoadRequest = this.lastBitmapLoadRequest;
        if (bitmapLoadRequest != null && bitmapLoadRequest.matches(bArr)) {
            return this.lastBitmapLoadRequest.getFuture();
        }
        com.google.common.util.concurrent.d1<Bitmap> d1VarDecodeBitmap = this.bitmapLoader.decodeBitmap(bArr);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(bArr, d1VarDecodeBitmap);
        return d1VarDecodeBitmap;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> loadBitmap(Uri uri) {
        BitmapLoadRequest bitmapLoadRequest = this.lastBitmapLoadRequest;
        if (bitmapLoadRequest != null && bitmapLoadRequest.matches(uri)) {
            return this.lastBitmapLoadRequest.getFuture();
        }
        com.google.common.util.concurrent.d1<Bitmap> d1VarLoadBitmap = this.bitmapLoader.loadBitmap(uri);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(uri, d1VarLoadBitmap);
        return d1VarLoadBitmap;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public com.google.common.util.concurrent.d1<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        BitmapLoadRequest bitmapLoadRequest = this.lastBitmapLoadRequest;
        if (bitmapLoadRequest != null && bitmapLoadRequest.matches(mediaMetadata)) {
            return this.lastBitmapLoadRequest.getFuture();
        }
        com.google.common.util.concurrent.d1<Bitmap> d1VarLoadBitmapFromMetadata = this.bitmapLoader.loadBitmapFromMetadata(mediaMetadata);
        if (d1VarLoadBitmapFromMetadata == null) {
            return null;
        }
        this.lastBitmapLoadRequest = new BitmapLoadRequest(mediaMetadata, d1VarLoadBitmapFromMetadata);
        return d1VarLoadBitmapFromMetadata;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public boolean supportsMimeType(String str) {
        return this.bitmapLoader.supportsMimeType(str);
    }
}
