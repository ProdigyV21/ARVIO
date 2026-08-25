package androidx.media3.exoplayer.audio;

import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.CodecParameters;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioRendererEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler handler, AudioRendererEventListener audioRendererEventListener) {
            if (audioRendererEventListener != null) {
                handler.getClass();
            } else {
                handler = null;
            }
            this.handler = handler;
            this.listener = audioRendererEventListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioCodecError$9(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecError(exc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioCodecParametersChanged$13(CodecParameters codecParameters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecParametersChanged(codecParameters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioSessionIdChanged$12(int i10) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSessionIdChanged(i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioSinkError$8(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSinkError(exc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioTrackInitialized$10(AudioSink.AudioTrackConfig audioTrackConfig) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioTrackInitialized(audioTrackConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$audioTrackReleased$11(AudioSink.AudioTrackConfig audioTrackConfig) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioTrackReleased(audioTrackConfig);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderInitialized$1(String str, long j10, long j11) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderInitialized(str, j10, j11);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderReleased$5(String str) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderReleased(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$disabled$6(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDisabled(decoderCounters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$enabled$0(DecoderCounters decoderCounters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioEnabled(decoderCounters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$inputFormatChanged$2(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$positionAdvancing$3(long j10) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioPositionAdvancing(j10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$skipSilenceEnabledChanged$7(boolean z) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onSkipSilenceEnabledChanged(z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$underrun$4(int i10, long j10, long j11) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioUnderrun(i10, j10, j11);
        }

        public void audioCodecError(Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new i(this, exc, 0));
            }
        }

        public void audioCodecParametersChanged(CodecParameters codecParameters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new p(this, codecParameters, 2));
            }
        }

        public void audioSessionIdChanged(int i10) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new e(this, i10, 0));
            }
        }

        public void audioSinkError(Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new i(this, exc, 1));
            }
        }

        public void audioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new h(this, audioTrackConfig, 0));
            }
        }

        public void audioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new h(this, audioTrackConfig, 1));
            }
        }

        public void decoderInitialized(String str, long j10, long j11) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new k(this, str, j10, j11, 0));
            }
        }

        public void decoderReleased(String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new p(this, str, 3));
            }
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new d(this, decoderCounters, 1));
            }
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new d(this, decoderCounters, 0));
            }
        }

        public void inputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new androidx.emoji2.text.q(this, format, decoderReuseEvaluation, 3));
            }
        }

        public void positionAdvancing(long j10) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new f(this, j10, 0));
            }
        }

        public void skipSilenceEnabledChanged(boolean z) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new j(0, this, z));
            }
        }

        public void underrun(final int i10, final long j10, final long j11) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: androidx.media3.exoplayer.audio.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3552i.lambda$underrun$4(i10, j10, j11);
                    }
                });
            }
        }
    }

    void onAudioCodecError(Exception exc);

    void onAudioCodecParametersChanged(CodecParameters codecParameters);

    void onAudioDecoderInitialized(String str, long j10, long j11);

    void onAudioDecoderReleased(String str);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void onAudioPositionAdvancing(long j10);

    void onAudioSessionIdChanged(int i10);

    void onAudioSinkError(Exception exc);

    void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig);

    void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig);

    void onAudioUnderrun(int i10, long j10, long j11);

    void onSkipSilenceEnabledChanged(boolean z);
}
