package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.decoder.DecoderOutputBuffer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ImageOutputBuffer extends DecoderOutputBuffer {
    public Bitmap bitmap;

    @Override // androidx.media3.decoder.DecoderOutputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        this.bitmap = null;
        super.clear();
    }
}
