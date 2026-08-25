package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultRendererCapabilitiesList implements RendererCapabilitiesList {
    private final Renderer[] renderers;

    public static final class Factory implements RendererCapabilitiesList.Factory {
        private final RenderersFactory renderersFactory;

        public Factory(Context context) {
            this.renderersFactory = new DefaultRenderersFactory(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$createRendererCapabilitiesList$0(CueGroup cueGroup) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$createRendererCapabilitiesList$1(Metadata metadata) {
        }

        @Override // androidx.media3.exoplayer.RendererCapabilitiesList.Factory
        public DefaultRendererCapabilitiesList createRendererCapabilitiesList() {
            return new DefaultRendererCapabilitiesList(this.renderersFactory.createRenderers(Util.createHandlerForCurrentOrMainLooper(), new VideoRendererEventListener() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList.Factory.1
                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onDroppedFrames(int i10, long j10) {
                    androidx.media3.exoplayer.video.j.a(this, i10, j10);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onRenderedFirstFrame(Object obj, long j10) {
                    androidx.media3.exoplayer.video.j.b(this, obj, j10);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoCodecError(Exception exc) {
                    androidx.media3.exoplayer.video.j.c(this, exc);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoCodecParametersChanged(CodecParameters codecParameters) {
                    androidx.media3.exoplayer.video.j.d(this, codecParameters);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoDecoderInitialized(String str, long j10, long j11) {
                    androidx.media3.exoplayer.video.j.e(this, str, j10, j11);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoDecoderReleased(String str) {
                    androidx.media3.exoplayer.video.j.f(this, str);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoDisabled(DecoderCounters decoderCounters) {
                    androidx.media3.exoplayer.video.j.g(this, decoderCounters);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoEnabled(DecoderCounters decoderCounters) {
                    androidx.media3.exoplayer.video.j.h(this, decoderCounters);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoFrameProcessingOffset(long j10, int i10) {
                    androidx.media3.exoplayer.video.j.i(this, j10, i10);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                    androidx.media3.exoplayer.video.j.j(this, format, decoderReuseEvaluation);
                }

                @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
                public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                    androidx.media3.exoplayer.video.j.k(this, videoSize);
                }
            }, new AudioRendererEventListener() { // from class: androidx.media3.exoplayer.DefaultRendererCapabilitiesList.Factory.2
                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioCodecError(Exception exc) {
                    androidx.media3.exoplayer.audio.c.a(this, exc);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioCodecParametersChanged(CodecParameters codecParameters) {
                    androidx.media3.exoplayer.audio.c.b(this, codecParameters);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioDecoderInitialized(String str, long j10, long j11) {
                    androidx.media3.exoplayer.audio.c.c(this, str, j10, j11);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioDecoderReleased(String str) {
                    androidx.media3.exoplayer.audio.c.d(this, str);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioDisabled(DecoderCounters decoderCounters) {
                    androidx.media3.exoplayer.audio.c.e(this, decoderCounters);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioEnabled(DecoderCounters decoderCounters) {
                    androidx.media3.exoplayer.audio.c.f(this, decoderCounters);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
                    androidx.media3.exoplayer.audio.c.g(this, format, decoderReuseEvaluation);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioPositionAdvancing(long j10) {
                    androidx.media3.exoplayer.audio.c.h(this, j10);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
                    androidx.media3.exoplayer.audio.c.i(this, i10);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioSinkError(Exception exc) {
                    androidx.media3.exoplayer.audio.c.j(this, exc);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
                    androidx.media3.exoplayer.audio.c.k(this, audioTrackConfig);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
                    androidx.media3.exoplayer.audio.c.l(this, audioTrackConfig);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onAudioUnderrun(int i10, long j10, long j11) {
                    androidx.media3.exoplayer.audio.c.m(this, i10, j10, j11);
                }

                @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
                public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                    androidx.media3.exoplayer.audio.c.n(this, z);
                }
            }, new w(), new w()));
        }

        public Factory(RenderersFactory renderersFactory) {
            this.renderersFactory = renderersFactory;
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public RendererCapabilities[] getRendererCapabilities() {
        RendererCapabilities[] rendererCapabilitiesArr = new RendererCapabilities[this.renderers.length];
        int i10 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i10 >= rendererArr.length) {
                return rendererCapabilitiesArr;
            }
            rendererCapabilitiesArr[i10] = rendererArr[i10].getCapabilities();
            i10++;
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public void release() {
        for (Renderer renderer : this.renderers) {
            renderer.release();
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilitiesList
    public int size() {
        return this.renderers.length;
    }

    private DefaultRendererCapabilitiesList(Renderer[] rendererArr) {
        this.renderers = (Renderer[]) Arrays.copyOf(rendererArr, rendererArr.length);
        for (int i10 = 0; i10 < rendererArr.length; i10++) {
            this.renderers[i10].init(i10, PlayerId.UNSET, Clock.DEFAULT);
        }
    }
}
