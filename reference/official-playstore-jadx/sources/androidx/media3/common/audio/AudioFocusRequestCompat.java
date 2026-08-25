package androidx.media3.common.audio;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioFocusRequestCompat {
    private final AudioAttributes audioAttributes;
    private final Handler focusChangeHandler;
    private final int focusGain;
    private final Object frameworkAudioFocusRequest;
    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private final boolean pauseOnDuck;

    public static final class Builder {
        private AudioAttributes audioAttributes;
        private Handler focusChangeHandler;
        private int focusGain;
        private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        private boolean pauseOnDuck;

        private static boolean isValidFocusGain(int i10) {
            return i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4;
        }

        public AudioFocusRequestCompat build() {
            AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = this.onAudioFocusChangeListener;
            if (onAudioFocusChangeListener == null) {
                throw new IllegalStateException("Can't build an AudioFocusRequestCompat instance without a listener");
            }
            int i10 = this.focusGain;
            Handler handler = this.focusChangeHandler;
            handler.getClass();
            return new AudioFocusRequestCompat(i10, onAudioFocusChangeListener, handler, this.audioAttributes, this.pauseOnDuck);
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes) {
            audioAttributes.getClass();
            this.audioAttributes = audioAttributes;
            return this;
        }

        public Builder setFocusGain(int i10) {
            ac.b.j(isValidFocusGain(i10));
            this.focusGain = i10;
            return this;
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener) {
            return setOnAudioFocusChangeListener(onAudioFocusChangeListener, new Handler(Looper.getMainLooper()));
        }

        public Builder setWillPauseWhenDucked(boolean z) {
            this.pauseOnDuck = z;
            return this;
        }

        public Builder(int i10) {
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.focusGain = i10;
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler) {
            onAudioFocusChangeListener.getClass();
            handler.getClass();
            this.onAudioFocusChangeListener = onAudioFocusChangeListener;
            this.focusChangeHandler = handler;
            return this;
        }

        private Builder(AudioFocusRequestCompat audioFocusRequestCompat) {
            this.focusGain = audioFocusRequestCompat.getFocusGain();
            this.onAudioFocusChangeListener = audioFocusRequestCompat.getOnAudioFocusChangeListener();
            this.focusChangeHandler = audioFocusRequestCompat.getFocusChangeHandler();
            this.audioAttributes = audioFocusRequestCompat.getAudioAttributes();
            this.pauseOnDuck = audioFocusRequestCompat.willPauseWhenDucked();
        }
    }

    public static class OnAudioFocusChangeListenerHandlerCompat implements AudioManager.OnAudioFocusChangeListener {
        private final Handler handler;
        private final AudioManager.OnAudioFocusChangeListener listener;

        public OnAudioFocusChangeListenerHandlerCompat(AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler) {
            this.listener = onAudioFocusChangeListener;
            this.handler = Util.createHandler(handler.getLooper(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onAudioFocusChange$0(int i10) {
            this.listener.onAudioFocusChange(i10);
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(final int i10) {
            Util.postOrRun(this.handler, new Runnable() { // from class: androidx.media3.common.audio.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3366i.lambda$onAudioFocusChange$0(i10);
                }
            });
        }
    }

    public AudioFocusRequestCompat(int i10, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler, AudioAttributes audioAttributes, boolean z) {
        this.focusGain = i10;
        this.focusChangeHandler = handler;
        this.audioAttributes = audioAttributes;
        this.pauseOnDuck = z;
        int i11 = Build.VERSION.SDK_INT;
        if (i11 < 26) {
            this.onAudioFocusChangeListener = new OnAudioFocusChangeListenerHandlerCompat(onAudioFocusChangeListener, handler);
        } else {
            this.onAudioFocusChangeListener = onAudioFocusChangeListener;
        }
        if (i11 >= 26) {
            this.frameworkAudioFocusRequest = new AudioFocusRequest.Builder(i10).setAudioAttributes(audioAttributes.getPlatformAudioAttributes()).setWillPauseWhenDucked(z).setOnAudioFocusChangeListener(onAudioFocusChangeListener, handler).build();
        } else {
            this.frameworkAudioFocusRequest = null;
        }
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioFocusRequestCompat)) {
            return false;
        }
        AudioFocusRequestCompat audioFocusRequestCompat = (AudioFocusRequestCompat) obj;
        return this.focusGain == audioFocusRequestCompat.focusGain && this.pauseOnDuck == audioFocusRequestCompat.pauseOnDuck && Objects.equals(this.onAudioFocusChangeListener, audioFocusRequestCompat.onAudioFocusChangeListener) && Objects.equals(this.focusChangeHandler, audioFocusRequestCompat.focusChangeHandler) && Objects.equals(this.audioAttributes, audioFocusRequestCompat.audioAttributes);
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public AudioFocusRequest getAudioFocusRequest() {
        Object obj = this.frameworkAudioFocusRequest;
        obj.getClass();
        return (AudioFocusRequest) obj;
    }

    public Handler getFocusChangeHandler() {
        return this.focusChangeHandler;
    }

    public int getFocusGain() {
        return this.focusGain;
    }

    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener() {
        return this.onAudioFocusChangeListener;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.focusGain), this.onAudioFocusChangeListener, this.focusChangeHandler, this.audioAttributes, Boolean.valueOf(this.pauseOnDuck));
    }

    public boolean willPauseWhenDucked() {
        return this.pauseOnDuck;
    }
}
