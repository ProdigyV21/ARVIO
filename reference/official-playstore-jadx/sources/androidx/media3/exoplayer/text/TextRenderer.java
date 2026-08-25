package androidx.media3.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.t0;
import androidx.media3.extractor.text.CueDecoder;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import androidx.work.impl.t;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: loaded from: classes3.dex */
public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    private static final int MSG_UPDATE_OUTPUT = 1;
    private static final int REPLACEMENT_STATE_NONE = 0;
    private static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "TextRenderer";
    private final CueDecoder cueDecoder;
    private final DecoderInputBuffer cueDecoderInputBuffer;
    private CuesResolver cuesResolver;
    private int decoderReplacementState;
    private long finalStreamEndPositionUs;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private long lastRendererPositionUs;
    private boolean legacyDecodingEnabled;
    private SubtitleOutputBuffer nextSubtitle;
    private int nextSubtitleEventIndex;
    private SubtitleInputBuffer nextSubtitleInputBuffer;
    private final TextOutput output;
    private final Handler outputHandler;
    private boolean outputStreamEnded;
    private Format streamFormat;
    private SubtitleOutputBuffer subtitle;
    private SubtitleDecoder subtitleDecoder;
    private final SubtitleDecoderFactory subtitleDecoderFactory;
    private boolean waitingForKeyFrame;

    public TextRenderer(TextOutput textOutput, Looper looper) {
        this(textOutput, looper, SubtitleDecoderFactory.DEFAULT);
    }

    @RequiresNonNull({"streamFormat"})
    private void assertLegacyDecodingEnabledIfRequired() {
        boolean z = this.legacyDecodingEnabled || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_CEA608) || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_MP4CEA608) || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_CEA708);
        String str = this.streamFormat.sampleMimeType;
        if (!z) {
            throw new IllegalStateException(t.y("Legacy decoding is disabled, can't handle %s samples (expected %s).", str, MimeTypes.APPLICATION_MEDIA3_CUES));
        }
    }

    private void clearOutput() {
        updateOutput(new CueGroup(o3.f14078o, getPresentationTimeUs(this.lastRendererPositionUs)));
    }

    @RequiresNonNull({MediaTrack.ROLE_SUBTITLE})
    @SideEffectFree
    private long getCurrentEventTimeUs(long j10) {
        int nextEventTimeIndex = this.subtitle.getNextEventTimeIndex(j10);
        if (nextEventTimeIndex == 0 || this.subtitle.getEventTimeCount() == 0) {
            return this.subtitle.timeUs;
        }
        if (nextEventTimeIndex != -1) {
            return this.subtitle.getEventTime(nextEventTimeIndex - 1);
        }
        return this.subtitle.getEventTime(r2.getEventTimeCount() - 1);
    }

    private long getNextEventTime() {
        if (this.nextSubtitleEventIndex == -1) {
            return Long.MAX_VALUE;
        }
        this.subtitle.getClass();
        if (this.nextSubtitleEventIndex >= this.subtitle.getEventTimeCount()) {
            return Long.MAX_VALUE;
        }
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long j10) {
        ac.b.s(j10 != C.TIME_UNSET);
        return j10 - getStreamOffsetUs();
    }

    private void handleDecoderError(SubtitleDecoderException subtitleDecoderException) {
        Log.e(TAG, "Subtitle decoding failed. streamFormat=" + this.streamFormat, subtitleDecoderException);
        clearOutput();
        replaceSubtitleDecoder();
    }

    private static boolean hasEventsAfter(Subtitle subtitle, long j10) {
        return subtitle != null && subtitle.getEventTimeCount() > 0 && subtitle.getEventTime(subtitle.getEventTimeCount() - 1) > j10;
    }

    private void initSubtitleDecoder() {
        this.waitingForKeyFrame = true;
        SubtitleDecoderFactory subtitleDecoderFactory = this.subtitleDecoderFactory;
        Format format = this.streamFormat;
        format.getClass();
        SubtitleDecoder subtitleDecoderCreateDecoder = subtitleDecoderFactory.createDecoder(format);
        this.subtitleDecoder = subtitleDecoderCreateDecoder;
        subtitleDecoderCreateDecoder.setOutputStartTimeUs(getLastResetPositionUs());
    }

    private void invokeUpdateOutputInternal(CueGroup cueGroup) {
        this.output.onCues(cueGroup.cues);
        this.output.onCues(cueGroup);
    }

    @SideEffectFree
    private static boolean isCuesWithTiming(Format format) {
        return Objects.equals(format.sampleMimeType, MimeTypes.APPLICATION_MEDIA3_CUES);
    }

    @RequiresNonNull({"this.cuesResolver"})
    private boolean readAndDecodeCuesWithTiming(long j10) {
        if (this.inputStreamEnded || readSource(this.formatHolder, this.cueDecoderInputBuffer, 0) != -4) {
            return false;
        }
        if (this.cueDecoderInputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            return false;
        }
        this.cueDecoderInputBuffer.flip();
        ByteBuffer byteBuffer = this.cueDecoderInputBuffer.data;
        byteBuffer.getClass();
        CuesWithTiming cuesWithTimingDecode = this.cueDecoder.decode(this.cueDecoderInputBuffer.timeUs, byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
        this.cueDecoderInputBuffer.clear();
        return this.cuesResolver.addCues(cuesWithTimingDecode, j10);
    }

    private void releaseSubtitleBuffers() {
        this.nextSubtitleInputBuffer = null;
        this.nextSubtitleEventIndex = -1;
        SubtitleOutputBuffer subtitleOutputBuffer = this.subtitle;
        if (subtitleOutputBuffer != null) {
            subtitleOutputBuffer.release();
            this.subtitle = null;
        }
        SubtitleOutputBuffer subtitleOutputBuffer2 = this.nextSubtitle;
        if (subtitleOutputBuffer2 != null) {
            subtitleOutputBuffer2.release();
            this.nextSubtitle = null;
        }
    }

    private void releaseSubtitleDecoder() {
        releaseSubtitleBuffers();
        SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
        subtitleDecoder.getClass();
        subtitleDecoder.release();
        this.subtitleDecoder = null;
        this.decoderReplacementState = 0;
    }

    @RequiresNonNull({"this.cuesResolver"})
    private void renderFromCuesWithTiming(long j10) {
        boolean andDecodeCuesWithTiming = readAndDecodeCuesWithTiming(j10);
        long nextCueChangeTimeUs = this.cuesResolver.getNextCueChangeTimeUs(this.lastRendererPositionUs);
        if (nextCueChangeTimeUs == Long.MIN_VALUE && this.inputStreamEnded && !andDecodeCuesWithTiming) {
            this.outputStreamEnded = true;
        }
        if (nextCueChangeTimeUs != Long.MIN_VALUE && nextCueChangeTimeUs <= j10) {
            andDecodeCuesWithTiming = true;
        }
        if (andDecodeCuesWithTiming) {
            h1 cuesAtTimeUs = this.cuesResolver.getCuesAtTimeUs(j10);
            long previousCueChangeTimeUs = this.cuesResolver.getPreviousCueChangeTimeUs(j10);
            updateOutput(new CueGroup(cuesAtTimeUs, getPresentationTimeUs(previousCueChangeTimeUs)));
            this.cuesResolver.discardCuesBeforeTimeUs(previousCueChangeTimeUs);
        }
        this.lastRendererPositionUs = j10;
    }

    private void renderFromSubtitles(long j10) throws DecoderException {
        boolean z;
        this.lastRendererPositionUs = j10;
        if (this.nextSubtitle == null) {
            SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
            subtitleDecoder.getClass();
            subtitleDecoder.setPositionUs(j10);
            try {
                SubtitleDecoder subtitleDecoder2 = this.subtitleDecoder;
                subtitleDecoder2.getClass();
                this.nextSubtitle = subtitleDecoder2.dequeueOutputBuffer();
            } catch (SubtitleDecoderException e5) {
                handleDecoderError(e5);
                return;
            }
        }
        if (getState() != 2) {
            return;
        }
        if (this.subtitle != null) {
            long nextEventTime = getNextEventTime();
            z = false;
            while (nextEventTime <= j10) {
                this.nextSubtitleEventIndex++;
                nextEventTime = getNextEventTime();
                z = true;
            }
        } else {
            z = false;
        }
        SubtitleOutputBuffer subtitleOutputBuffer = this.nextSubtitle;
        if (subtitleOutputBuffer != null) {
            if (subtitleOutputBuffer.isEndOfStream()) {
                if (!z && getNextEventTime() == Long.MAX_VALUE) {
                    if (this.decoderReplacementState == 2) {
                        replaceSubtitleDecoder();
                    } else {
                        releaseSubtitleBuffers();
                        this.outputStreamEnded = true;
                    }
                }
            } else if (subtitleOutputBuffer.timeUs <= j10) {
                SubtitleOutputBuffer subtitleOutputBuffer2 = this.subtitle;
                if (subtitleOutputBuffer2 != null) {
                    subtitleOutputBuffer2.release();
                }
                this.nextSubtitleEventIndex = subtitleOutputBuffer.getNextEventTimeIndex(j10);
                this.subtitle = subtitleOutputBuffer;
                this.nextSubtitle = null;
                z = true;
            }
        }
        if (z) {
            this.subtitle.getClass();
            updateOutput(new CueGroup(this.subtitle.getCues(j10), getPresentationTimeUs(getCurrentEventTimeUs(j10))));
        }
        if (this.decoderReplacementState == 2) {
            return;
        }
        while (!this.inputStreamEnded) {
            try {
                SubtitleInputBuffer subtitleInputBufferDequeueInputBuffer = this.nextSubtitleInputBuffer;
                if (subtitleInputBufferDequeueInputBuffer == null) {
                    SubtitleDecoder subtitleDecoder3 = this.subtitleDecoder;
                    subtitleDecoder3.getClass();
                    subtitleInputBufferDequeueInputBuffer = subtitleDecoder3.dequeueInputBuffer();
                    if (subtitleInputBufferDequeueInputBuffer == null) {
                        return;
                    } else {
                        this.nextSubtitleInputBuffer = subtitleInputBufferDequeueInputBuffer;
                    }
                }
                if (this.decoderReplacementState == 1) {
                    subtitleInputBufferDequeueInputBuffer.setFlags(4);
                    SubtitleDecoder subtitleDecoder4 = this.subtitleDecoder;
                    subtitleDecoder4.getClass();
                    subtitleDecoder4.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                    this.nextSubtitleInputBuffer = null;
                    this.decoderReplacementState = 2;
                    return;
                }
                int source = readSource(this.formatHolder, subtitleInputBufferDequeueInputBuffer, 0);
                if (source == -4) {
                    if (subtitleInputBufferDequeueInputBuffer.isEndOfStream()) {
                        this.inputStreamEnded = true;
                        this.waitingForKeyFrame = false;
                    } else {
                        Format format = this.formatHolder.format;
                        if (format == null) {
                            return;
                        }
                        subtitleInputBufferDequeueInputBuffer.subsampleOffsetUs = format.subsampleOffsetUs;
                        subtitleInputBufferDequeueInputBuffer.flip();
                        this.waitingForKeyFrame &= !subtitleInputBufferDequeueInputBuffer.isKeyFrame();
                    }
                    if (!this.waitingForKeyFrame) {
                        SubtitleDecoder subtitleDecoder5 = this.subtitleDecoder;
                        subtitleDecoder5.getClass();
                        subtitleDecoder5.queueInputBuffer(subtitleInputBufferDequeueInputBuffer);
                        this.nextSubtitleInputBuffer = null;
                    }
                } else if (source == -3) {
                    return;
                }
            } catch (SubtitleDecoderException e6) {
                handleDecoderError(e6);
                return;
            }
        }
    }

    private void replaceSubtitleDecoder() {
        releaseSubtitleDecoder();
        initSubtitleDecoder();
    }

    private void updateOutput(CueGroup cueGroup) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(1, cueGroup).sendToTarget();
        } else {
            invokeUpdateOutputInternal(cueGroup);
        }
    }

    @Deprecated
    public void experimentalSetLegacyDecodingEnabled(boolean z) {
        this.legacyDecodingEnabled = z;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public String getName() {
        return TAG;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            throw new IllegalStateException();
        }
        invokeUpdateOutputInternal((CueGroup) message.obj);
        return true;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        Format format = this.streamFormat;
        if (format == null) {
            return true;
        }
        format.getClass();
        if (!isCuesWithTiming(format)) {
            return !this.outputStreamEnded && (!this.inputStreamEnded || hasEventsAfter(this.subtitle, this.lastRendererPositionUs) || hasEventsAfter(this.nextSubtitle, this.lastRendererPositionUs) || this.nextSubtitleInputBuffer == null);
        }
        CuesResolver cuesResolver = this.cuesResolver;
        cuesResolver.getClass();
        if (cuesResolver.getNextCueChangeTimeUs(this.lastRendererPositionUs) != Long.MIN_VALUE) {
            return true;
        }
        try {
            maybeThrowStreamError();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        clearOutput();
        this.lastRendererPositionUs = C.TIME_UNSET;
        if (this.subtitleDecoder != null) {
            releaseSubtitleDecoder();
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j10, boolean z, boolean z5) {
        this.lastRendererPositionUs = j10;
        CuesResolver cuesResolver = this.cuesResolver;
        if (cuesResolver != null) {
            cuesResolver.clear();
        }
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        Format format = this.streamFormat;
        if (format == null || isCuesWithTiming(format)) {
            return;
        }
        if (this.decoderReplacementState != 0) {
            replaceSubtitleDecoder();
            return;
        }
        releaseSubtitleBuffers();
        SubtitleDecoder subtitleDecoder = this.subtitleDecoder;
        subtitleDecoder.getClass();
        subtitleDecoder.flush();
        subtitleDecoder.setOutputStartTimeUs(getLastResetPositionUs());
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j10, long j11, MediaSource.MediaPeriodId mediaPeriodId) {
        Format format = formatArr[0];
        this.streamFormat = format;
        if (isCuesWithTiming(format)) {
            this.cuesResolver = this.streamFormat.cueReplacementBehavior == 1 ? new MergingCuesResolver() : new ReplacingCuesResolver();
            return;
        }
        assertLegacyDecodingEnabledIfRequired();
        if (this.subtitleDecoder != null) {
            this.decoderReplacementState = 1;
        } else {
            initSubtitleDecoder();
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j10, long j11) throws DecoderException {
        if (isCurrentStreamFinal()) {
            long j12 = this.finalStreamEndPositionUs;
            if (j12 != C.TIME_UNSET && j10 >= j12) {
                releaseSubtitleBuffers();
                this.outputStreamEnded = true;
            }
        }
        if (this.outputStreamEnded) {
            return;
        }
        Format format = this.streamFormat;
        format.getClass();
        if (isCuesWithTiming(format)) {
            this.cuesResolver.getClass();
            renderFromCuesWithTiming(j10);
        } else {
            assertLegacyDecodingEnabledIfRequired();
            renderFromSubtitles(j10);
        }
    }

    public void setFinalStreamEndPositionUs(long j10) {
        ac.b.s(isCurrentStreamFinal());
        this.finalStreamEndPositionUs = j10;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        if (isCuesWithTiming(format) || this.subtitleDecoderFactory.supportsFormat(format)) {
            return t0.c(format.cryptoType == 0 ? 4 : 2);
        }
        return MimeTypes.isText(format.sampleMimeType) ? t0.c(1) : t0.c(0);
    }

    public TextRenderer(TextOutput textOutput, Looper looper, SubtitleDecoderFactory subtitleDecoderFactory) {
        super(3);
        textOutput.getClass();
        this.output = textOutput;
        this.outputHandler = looper == null ? null : Util.createHandler(looper, this);
        this.subtitleDecoderFactory = subtitleDecoderFactory;
        this.cueDecoder = new CueDecoder();
        this.cueDecoderInputBuffer = new DecoderInputBuffer(1);
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        this.lastRendererPositionUs = C.TIME_UNSET;
        this.legacyDecodingEnabled = false;
    }
}
