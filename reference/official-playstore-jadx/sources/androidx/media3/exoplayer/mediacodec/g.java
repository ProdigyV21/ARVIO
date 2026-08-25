package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class g {
    static {
        MediaCodecAdapter.Factory factory = MediaCodecAdapter.Factory.DEFAULT;
    }

    public static MediaCodecAdapter.Factory a(Context context) {
        return new DefaultMediaCodecAdapterFactory(context);
    }
}
