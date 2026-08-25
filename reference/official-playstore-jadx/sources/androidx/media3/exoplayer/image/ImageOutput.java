package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public interface ImageOutput {
    public static final ImageOutput NO_OP = new ImageOutput() { // from class: androidx.media3.exoplayer.image.ImageOutput.1
        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onDisabled() {
        }

        @Override // androidx.media3.exoplayer.image.ImageOutput
        public void onImageAvailable(long j10, Bitmap bitmap) {
        }
    };

    void onDisabled();

    void onImageAvailable(long j10, Bitmap bitmap);
}
