package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.d1;

/* JADX INFO: loaded from: classes3.dex */
public interface BitmapLoader {
    d1<Bitmap> decodeBitmap(byte[] bArr);

    d1<Bitmap> loadBitmap(Uri uri);

    d1<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata);

    boolean supportsMimeType(String str);
}
