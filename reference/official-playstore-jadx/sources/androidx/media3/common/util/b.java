package androidx.media3.common.util;

import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.d1;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    public static d1 a(BitmapLoader bitmapLoader, MediaMetadata mediaMetadata) {
        byte[] bArr = mediaMetadata.artworkData;
        if (bArr != null) {
            return bitmapLoader.decodeBitmap(bArr);
        }
        Uri uri = mediaMetadata.artworkUri;
        if (uri != null) {
            return bitmapLoader.loadBitmap(uri);
        }
        return null;
    }
}
