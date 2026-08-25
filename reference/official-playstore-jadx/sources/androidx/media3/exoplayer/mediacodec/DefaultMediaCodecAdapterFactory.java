package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter;
import com.google.common.base.c0;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultMediaCodecAdapterFactory implements MediaCodecAdapter.Factory {
    private static final int MODE_DEFAULT = 0;
    private static final int MODE_DISABLED = 2;
    private static final int MODE_ENABLED = 1;
    private static final String TAG = "DMCodecAdapterFactory";
    private boolean asyncCryptoFlagEnabled;
    private int asynchronousMode;
    private final c0<HandlerThread> callbackThreadSupplier;
    private final Context context;
    private final c0<HandlerThread> queueingThreadSupplier;

    @Deprecated
    public DefaultMediaCodecAdapterFactory() {
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = true;
        this.context = null;
        this.callbackThreadSupplier = null;
        this.queueingThreadSupplier = null;
    }

    private boolean shouldUseAsynchronousAdapterInDefaultMode() {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 31) {
            return true;
        }
        Context context = this.context;
        return context != null && i10 >= 28 && context.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen");
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
    public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration configuration) throws IOException {
        c0<HandlerThread> c0Var;
        int i10 = this.asynchronousMode;
        if (i10 != 1 && (i10 != 0 || !shouldUseAsynchronousAdapterInDefaultMode())) {
            return new SynchronousMediaCodecAdapter.Factory().createAdapter(configuration);
        }
        int trackType = MimeTypes.getTrackType(configuration.format.sampleMimeType);
        Log.i(TAG, "Creating an asynchronous MediaCodec adapter for track type " + Util.getTrackTypeString(trackType));
        c0<HandlerThread> c0Var2 = this.callbackThreadSupplier;
        AsynchronousMediaCodecAdapter.Factory factory = (c0Var2 == null || (c0Var = this.queueingThreadSupplier) == null) ? new AsynchronousMediaCodecAdapter.Factory(trackType) : new AsynchronousMediaCodecAdapter.Factory(c0Var2, c0Var);
        factory.experimentalSetAsyncCryptoFlagEnabled(this.asyncCryptoFlagEnabled);
        return factory.createAdapter(configuration);
    }

    public DefaultMediaCodecAdapterFactory experimentalSetAsyncCryptoFlagEnabled(boolean z) {
        this.asyncCryptoFlagEnabled = z;
        return this;
    }

    public DefaultMediaCodecAdapterFactory forceDisableAsynchronous() {
        this.asynchronousMode = 2;
        return this;
    }

    public DefaultMediaCodecAdapterFactory forceEnableAsynchronous() {
        this.asynchronousMode = 1;
        return this;
    }

    public DefaultMediaCodecAdapterFactory(Context context) {
        this(context, null, null);
    }

    public DefaultMediaCodecAdapterFactory(Context context, c0<HandlerThread> c0Var, c0<HandlerThread> c0Var2) {
        this.context = context;
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = true;
        this.callbackThreadSupplier = c0Var;
        this.queueingThreadSupplier = c0Var2;
    }
}
