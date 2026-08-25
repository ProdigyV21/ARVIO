package com.arflix.tv.ui.screens.player;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0010J_\u0010#\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` H\u0014¢\u0006\u0004\b#\u0010$J_\u0010'\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000e2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` H\u0014¢\u0006\u0004\b'\u0010(J'\u0010+\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0016H\u0014¢\u0006\u0004\b+\u0010,JG\u00101\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/2\u0006\u0010\u0013\u001a\u00020\n2\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` H\u0014¢\u0006\u0004\b1\u00102R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00103R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00104R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R(\u0010<\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010:8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020A0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010C¨\u0006D"}, d2 = {"Lcom/arflix/tv/ui/screens/player/AiSubtitleRenderersFactory;", "Landroidx/media3/exoplayer/DefaultRenderersFactory;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "translationManager", "Lka/k0;", "scope", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;Lka/k0;)V", "", "maxCount", "", "Lx6/x;", "", "extractBufferedReferenceIntervals", "(I)Ljava/util/List;", "", "extractBufferedCueTexts", "extensionRendererMode", "Landroidx/media3/exoplayer/mediacodec/MediaCodecSelector;", "mediaCodecSelector", "", "enableDecoderFallback", "Landroidx/media3/exoplayer/audio/AudioSink;", "audioSink", "Landroid/os/Handler;", "eventHandler", "Landroidx/media3/exoplayer/audio/AudioRendererEventListener;", "eventListener", "Ljava/util/ArrayList;", "Landroidx/media3/exoplayer/Renderer;", "Lkotlin/collections/ArrayList;", "out", "Lx6/t0;", "buildAudioRenderers", "(Landroid/content/Context;ILandroidx/media3/exoplayer/mediacodec/MediaCodecSelector;ZLandroidx/media3/exoplayer/audio/AudioSink;Landroid/os/Handler;Landroidx/media3/exoplayer/audio/AudioRendererEventListener;Ljava/util/ArrayList;)V", "Landroidx/media3/exoplayer/video/VideoRendererEventListener;", "allowedVideoJoiningTimeMs", "buildVideoRenderers", "(Landroid/content/Context;ILandroidx/media3/exoplayer/mediacodec/MediaCodecSelector;ZLandroid/os/Handler;Landroidx/media3/exoplayer/video/VideoRendererEventListener;JLjava/util/ArrayList;)V", "enableFloatOutput", "enableAudioTrackPlaybackParams", "buildAudioSink", "(Landroid/content/Context;ZZ)Landroidx/media3/exoplayer/audio/AudioSink;", "Landroidx/media3/exoplayer/text/TextOutput;", "output", "Landroid/os/Looper;", "outputLooper", "buildTextRenderers", "(Landroid/content/Context;Landroidx/media3/exoplayer/text/TextOutput;Landroid/os/Looper;ILjava/util/ArrayList;)V", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "Lka/k0;", "Ljava/util/concurrent/atomic/AtomicLong;", "syncOffsetUs", "Ljava/util/concurrent/atomic/AtomicLong;", "getSyncOffsetUs", "()Ljava/util/concurrent/atomic/AtomicLong;", "Lcom/arflix/tv/ui/screens/player/AudioCaptureProcessor;", "value", "audioCaptureProcessor", "Lcom/arflix/tv/ui/screens/player/AudioCaptureProcessor;", "getAudioCaptureProcessor", "()Lcom/arflix/tv/ui/screens/player/AudioCaptureProcessor;", "", "Lcom/arflix/tv/ui/screens/player/SubtitleOffsetRenderer;", "offsetRenderers", "Ljava/util/List;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AiSubtitleRenderersFactory extends DefaultRenderersFactory {
    public static final int $stable = 8;
    private AudioCaptureProcessor audioCaptureProcessor;
    private final List<SubtitleOffsetRenderer> offsetRenderers;
    private final ka.k0 scope;
    private final AtomicLong syncOffsetUs;
    private final SubtitleTranslationManager translationManager;

    public AiSubtitleRenderersFactory(Context context, SubtitleTranslationManager subtitleTranslationManager, ka.k0 k0Var) {
        super(context);
        this.translationManager = subtitleTranslationManager;
        this.scope = k0Var;
        this.syncOffsetUs = new AtomicLong(0L);
        this.offsetRenderers = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 buildTextRenderers$lambda$0(AiSubtitleRenderersFactory aiSubtitleRenderersFactory) {
        Iterator<T> it = aiSubtitleRenderersFactory.offsetRenderers.iterator();
        while (it.hasNext()) {
            ((SubtitleOffsetRenderer) it.next()).triggerPreTranslation();
        }
        return x6.t0.f22605a;
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    public void buildAudioRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, AudioSink audioSink, Handler eventHandler, AudioRendererEventListener eventListener, ArrayList<Renderer> out) {
        super.buildAudioRenderers(context, 1, mediaCodecSelector, enableDecoderFallback, audioSink, eventHandler, eventListener, out);
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    public AudioSink buildAudioSink(Context context, boolean enableFloatOutput, boolean enableAudioTrackPlaybackParams) {
        AudioCaptureProcessor audioCaptureProcessor = new AudioCaptureProcessor();
        this.audioCaptureProcessor = audioCaptureProcessor;
        return new DefaultAudioSink.Builder(context).setEnableFloatOutput(enableFloatOutput).setEnableAudioTrackPlaybackParams(enableAudioTrackPlaybackParams).setAudioProcessors(new AudioCaptureProcessor[]{audioCaptureProcessor}).build();
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    public void buildTextRenderers(Context context, TextOutput output, Looper outputLooper, int extensionRendererMode, ArrayList<Renderer> out) {
        TranslatingTextOutput translatingTextOutput = new TranslatingTextOutput(output, this.translationManager, outputLooper, this.scope);
        super.buildTextRenderers(context, translatingTextOutput, outputLooper, extensionRendererMode, out);
        this.offsetRenderers.clear();
        int size = out.size();
        for (int size2 = out.size(); size2 < size; size2++) {
            SubtitleOffsetRenderer subtitleOffsetRenderer = new SubtitleOffsetRenderer(out.get(size2), this.translationManager, this.scope, this.syncOffsetUs);
            this.offsetRenderers.add(subtitleOffsetRenderer);
            out.set(size2, subtitleOffsetRenderer);
        }
        translatingTextOutput.setOnFirstCueOnPlaybackThread(new n1(this, 1));
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    public void buildVideoRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, Handler eventHandler, VideoRendererEventListener eventListener, long allowedVideoJoiningTimeMs, ArrayList<Renderer> out) {
        super.buildVideoRenderers(context, 1, mediaCodecSelector, enableDecoderFallback, eventHandler, eventListener, allowedVideoJoiningTimeMs, out);
    }

    public final List<String> extractBufferedCueTexts(int maxCount) {
        Iterator<SubtitleOffsetRenderer> it = this.offsetRenderers.iterator();
        while (it.hasNext()) {
            List<String> listExtractAllCueTexts = it.next().extractAllCueTexts();
            if (!listExtractAllCueTexts.isEmpty()) {
                return kotlin.collections.x.X0(listExtractAllCueTexts, maxCount);
            }
        }
        return kotlin.collections.z.f19728i;
    }

    public final List<x6.x> extractBufferedReferenceIntervals(int maxCount) {
        Iterator<SubtitleOffsetRenderer> it = this.offsetRenderers.iterator();
        while (it.hasNext()) {
            List<x6.x> listExtractBufferedIntervals = it.next().extractBufferedIntervals(maxCount);
            if (!listExtractBufferedIntervals.isEmpty()) {
                return listExtractBufferedIntervals;
            }
        }
        return kotlin.collections.z.f19728i;
    }

    public final AudioCaptureProcessor getAudioCaptureProcessor() {
        return this.audioCaptureProcessor;
    }

    public final AtomicLong getSyncOffsetUs() {
        return this.syncOffsetUs;
    }
}
