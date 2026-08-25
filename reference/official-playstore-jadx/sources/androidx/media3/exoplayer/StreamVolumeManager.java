package androidx.media3.exoplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Looper;
import androidx.media3.common.audio.AudioManagerCompat;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.StreamVolumeManager;

/* JADX INFO: loaded from: classes3.dex */
final class StreamVolumeManager {
    private static final String TAG = "StreamVolumeManager";
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private final Context applicationContext;
    private AudioManager audioManager;
    private final Listener listener;
    private VolumeChangeReceiver receiver;
    private final BackgroundThreadStateHandler<StreamVolumeState> stateHandler;
    private int volumeBeforeMute;

    public interface Listener {
        void onStreamTypeChanged(int i10);

        void onStreamVolumeChanged(int i10, boolean z);
    }

    public static final class StreamVolumeState {
        public final int maxVolume;
        public final int minVolume;
        public final boolean muted;
        public final int streamType;
        public final int volume;

        public StreamVolumeState(int i10, int i11, boolean z, int i12, int i13) {
            this.streamType = i10;
            this.volume = i11;
            this.muted = z;
            this.minVolume = i12;
            this.maxVolume = i13;
        }
    }

    public final class VolumeChangeReceiver extends BroadcastReceiver {
        private VolumeChangeReceiver() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0() {
            if (StreamVolumeManager.this.receiver == null) {
                return;
            }
            StreamVolumeManager.this.stateHandler.setStateInBackground(StreamVolumeManager.this.generateState(((StreamVolumeState) StreamVolumeManager.this.stateHandler.get()).streamType));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StreamVolumeManager.this.stateHandler.runInBackground(new a(this, 3));
        }
    }

    public StreamVolumeManager(Context context, Listener listener, int i10, Looper looper, Looper looper2, Clock clock) {
        this.applicationContext = context.getApplicationContext();
        this.listener = listener;
        BackgroundThreadStateHandler<StreamVolumeState> backgroundThreadStateHandler = new BackgroundThreadStateHandler<>(new StreamVolumeState(i10, 0, false, 0, 0), looper, looper2, clock, new p(this, 10));
        this.stateHandler = backgroundThreadStateHandler;
        backgroundThreadStateHandler.runInBackground(new e0(this, i10, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamVolumeState generateState(int i10) {
        this.audioManager.getClass();
        return new StreamVolumeState(i10, AudioManagerCompat.getStreamVolume(this.audioManager, i10), AudioManagerCompat.isStreamMute(this.audioManager, i10), AudioManagerCompat.getStreamMinVolume(this.audioManager, i10), AudioManagerCompat.getStreamMaxVolume(this.audioManager, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$decreaseVolume$7(StreamVolumeState streamVolumeState) {
        int i10 = streamVolumeState.streamType;
        int i11 = streamVolumeState.volume;
        int i12 = streamVolumeState.minVolume;
        return new StreamVolumeState(i10, i11 > i12 ? i11 - 1 : i12, i11 <= 1, i12, streamVolumeState.maxVolume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamVolumeState lambda$decreaseVolume$8(int i10, StreamVolumeState streamVolumeState) {
        if (streamVolumeState.volume <= streamVolumeState.minVolume) {
            return streamVolumeState;
        }
        AudioManager audioManager = this.audioManager;
        audioManager.getClass();
        audioManager.adjustStreamVolume(streamVolumeState.streamType, -1, i10);
        return generateState(streamVolumeState.streamType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$increaseVolume$5(StreamVolumeState streamVolumeState) {
        int i10 = streamVolumeState.streamType;
        int i11 = streamVolumeState.volume;
        int i12 = streamVolumeState.maxVolume;
        return new StreamVolumeState(i10, i11 < i12 ? i11 + 1 : i12, false, streamVolumeState.minVolume, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamVolumeState lambda$increaseVolume$6(int i10, StreamVolumeState streamVolumeState) {
        if (streamVolumeState.volume >= streamVolumeState.maxVolume) {
            return streamVolumeState;
        }
        AudioManager audioManager = this.audioManager;
        audioManager.getClass();
        audioManager.adjustStreamVolume(streamVolumeState.streamType, 1, i10);
        return generateState(streamVolumeState.streamType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$new$0(int i10) {
        AudioManager audioManager = (AudioManager) this.applicationContext.getSystemService("audio");
        audioManager.getClass();
        this.audioManager = audioManager;
        VolumeChangeReceiver volumeChangeReceiver = new VolumeChangeReceiver();
        try {
            this.applicationContext.registerReceiver(volumeChangeReceiver, new IntentFilter(VOLUME_CHANGED_ACTION));
            this.receiver = volumeChangeReceiver;
        } catch (RuntimeException e5) {
            Log.w(TAG, "Error registering stream volume receiver", e5);
        }
        this.stateHandler.setStateInBackground(generateState(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$release$11(StreamVolumeState streamVolumeState) {
        return streamVolumeState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ StreamVolumeState lambda$release$12(StreamVolumeState streamVolumeState) {
        VolumeChangeReceiver volumeChangeReceiver = this.receiver;
        if (volumeChangeReceiver != null) {
            try {
                this.applicationContext.unregisterReceiver(volumeChangeReceiver);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Error unregistering stream volume receiver", e5);
            }
            this.receiver = null;
        }
        return streamVolumeState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamVolumeState lambda$setMuted$10(boolean z, int i10, StreamVolumeState streamVolumeState) {
        if (streamVolumeState.muted == z) {
            return streamVolumeState;
        }
        this.audioManager.getClass();
        this.audioManager.adjustStreamVolume(streamVolumeState.streamType, z ? -100 : 100, i10);
        return generateState(streamVolumeState.streamType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ StreamVolumeState lambda$setMuted$9(boolean z, StreamVolumeState streamVolumeState) {
        return new StreamVolumeState(streamVolumeState.streamType, streamVolumeState.muted == z ? streamVolumeState.volume : z ? 0 : this.volumeBeforeMute, z, streamVolumeState.minVolume, streamVolumeState.maxVolume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$setStreamType$1(int i10, StreamVolumeState streamVolumeState) {
        return new StreamVolumeState(i10, streamVolumeState.volume, streamVolumeState.muted, streamVolumeState.minVolume, streamVolumeState.maxVolume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ StreamVolumeState lambda$setStreamType$2(int i10, StreamVolumeState streamVolumeState) {
        return streamVolumeState.streamType == i10 ? streamVolumeState : generateState(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StreamVolumeState lambda$setVolume$3(int i10, StreamVolumeState streamVolumeState) {
        int i11 = streamVolumeState.streamType;
        int i12 = streamVolumeState.minVolume;
        return new StreamVolumeState(i11, (i10 < i12 || i10 > streamVolumeState.maxVolume) ? streamVolumeState.volume : i10, i10 == 0, i12, streamVolumeState.maxVolume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamVolumeState lambda$setVolume$4(int i10, int i11, StreamVolumeState streamVolumeState) {
        if (i10 == streamVolumeState.volume || i10 < streamVolumeState.minVolume || i10 > streamVolumeState.maxVolume) {
            return streamVolumeState;
        }
        AudioManager audioManager = this.audioManager;
        audioManager.getClass();
        audioManager.setStreamVolume(streamVolumeState.streamType, i10, i11);
        return generateState(streamVolumeState.streamType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStreamVolumeStateChanged(StreamVolumeState streamVolumeState, StreamVolumeState streamVolumeState2) {
        boolean z = streamVolumeState.muted;
        if (!z && streamVolumeState2.muted) {
            this.volumeBeforeMute = streamVolumeState.volume;
        }
        int i10 = streamVolumeState.volume;
        int i11 = streamVolumeState2.volume;
        if (i10 != i11 || z != streamVolumeState2.muted) {
            this.listener.onStreamVolumeChanged(i11, streamVolumeState2.muted);
        }
        int i12 = streamVolumeState.streamType;
        int i13 = streamVolumeState2.streamType;
        if (i12 == i13 && streamVolumeState.minVolume == streamVolumeState2.minVolume && streamVolumeState.maxVolume == streamVolumeState2.maxVolume) {
            return;
        }
        this.listener.onStreamTypeChanged(i13);
    }

    public void decreaseVolume(int i10) {
        this.stateHandler.updateStateAsync(new v0(2), new w0(this, i10, 1));
    }

    public int getMaxVolume() {
        return this.stateHandler.get().maxVolume;
    }

    public int getMinVolume() {
        return this.stateHandler.get().minVolume;
    }

    public int getVolume() {
        return this.stateHandler.get().volume;
    }

    public void increaseVolume(int i10) {
        this.stateHandler.updateStateAsync(new v0(1), new w0(this, i10, 0));
    }

    public boolean isMuted() {
        return this.stateHandler.get().muted;
    }

    public void release() {
        this.stateHandler.updateStateAsync(new v0(0), new h(this, 2));
    }

    public void setMuted(final boolean z, final int i10) {
        this.stateHandler.updateStateAsync(new com.google.common.base.m() { // from class: androidx.media3.exoplayer.y0
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                return this.f3888a.lambda$setMuted$9(z, (StreamVolumeManager.StreamVolumeState) obj);
            }
        }, new com.google.common.base.m() { // from class: androidx.media3.exoplayer.z0
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                return this.f3892a.lambda$setMuted$10(z, i10, (StreamVolumeManager.StreamVolumeState) obj);
            }
        });
    }

    public void setStreamType(int i10) {
        this.stateHandler.updateStateAsync(new v(i10, 4), new w0(this, i10, 2));
    }

    public void setVolume(final int i10, final int i11) {
        this.stateHandler.updateStateAsync(new v(i10, 3), new com.google.common.base.m() { // from class: androidx.media3.exoplayer.x0
            @Override // com.google.common.base.m
            public final Object apply(Object obj) {
                return this.f3883a.lambda$setVolume$4(i10, i11, (StreamVolumeManager.StreamVolumeState) obj);
            }
        });
    }
}
