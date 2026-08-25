package androidx.media3.exoplayer;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import j$.util.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes3.dex */
public final class ExoPlaybackException extends PlaybackException {
    public static final int TYPE_REMOTE = 3;
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    final boolean isRecoverable;
    public final MediaSource.MediaPeriodId mediaPeriodId;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final String rendererName;
    public final int type;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private ExoPlaybackException(int i10, Throwable th, int i11) {
        this(i10, th, null, i11, null, -1, null, 4, null, false);
    }

    public static ExoPlaybackException createForRemote(String str) {
        return new ExoPlaybackException(3, null, str, 1001, null, -1, null, 4, null, false);
    }

    @Deprecated
    public static ExoPlaybackException createForRenderer(Throwable th, String str, int i10, Format format, int i11, boolean z, int i12) {
        return createForRenderer(th, str, i10, format, i11, null, z, i12);
    }

    public static ExoPlaybackException createForSource(IOException iOException, int i10) {
        return new ExoPlaybackException(0, iOException, i10);
    }

    @Deprecated
    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return createForUnexpected(runtimeException, 1000);
    }

    private static String deriveMessage(int i10, String str, String str2, int i11, Format format, int i12) {
        String str3;
        if (i10 == 0) {
            str3 = "Source error";
        } else if (i10 != 1) {
            str3 = i10 != 3 ? "Unexpected runtime error" : "Remote error";
        } else {
            str3 = str2 + " error, index=" + i11 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(i12);
        }
        return !TextUtils.isEmpty(str) ? androidx.compose.foundation.c.t(str3, ": ", str) : str3;
    }

    public ExoPlaybackException copyWithMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId, this.timestampMs, this.isRecoverable);
    }

    @Override // androidx.media3.common.PlaybackException
    public boolean errorInfoEquals(PlaybackException playbackException) {
        if (!super.errorInfoEquals(playbackException)) {
            return false;
        }
        ExoPlaybackException exoPlaybackException = (ExoPlaybackException) Util.castNonNull(playbackException);
        return this.type == exoPlaybackException.type && Objects.equals(this.rendererName, exoPlaybackException.rendererName) && this.rendererIndex == exoPlaybackException.rendererIndex && Objects.equals(this.rendererFormat, exoPlaybackException.rendererFormat) && this.rendererFormatSupport == exoPlaybackException.rendererFormatSupport && Objects.equals(this.mediaPeriodId, exoPlaybackException.mediaPeriodId) && this.isRecoverable == exoPlaybackException.isRecoverable;
    }

    public Exception getRendererException() {
        ac.b.s(this.type == 1);
        Throwable cause = getCause();
        cause.getClass();
        return (Exception) cause;
    }

    public IOException getSourceException() {
        ac.b.s(this.type == 0);
        Throwable cause = getCause();
        cause.getClass();
        return (IOException) cause;
    }

    public RuntimeException getUnexpectedException() {
        ac.b.s(this.type == 2);
        Throwable cause = getCause();
        cause.getClass();
        return (RuntimeException) cause;
    }

    private ExoPlaybackException(int i10, Throwable th, String str, int i11, String str2, int i12, Format format, int i13, MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        this(deriveMessage(i10, str, str2, i12, format, i13), th, i11, i10, str2, i12, format, i13, mediaPeriodId, SystemClock.elapsedRealtime(), z);
    }

    public static ExoPlaybackException createForRenderer(Throwable th, String str, int i10, Format format, int i11, MediaSource.MediaPeriodId mediaPeriodId, boolean z, int i12) {
        if (format == null) {
            i11 = 4;
        }
        return new ExoPlaybackException(1, th, null, i12, str, i10, format, i11, mediaPeriodId, z);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int i10) {
        return new ExoPlaybackException(2, runtimeException, i10);
    }

    private ExoPlaybackException(String str, Throwable th, int i10, int i11, String str2, int i12, Format format, int i13, MediaSource.MediaPeriodId mediaPeriodId, long j10, boolean z) {
        super(str, th, i10, Bundle.EMPTY, j10);
        ac.b.j(!z || i11 == 1);
        ac.b.j(th != null || i11 == 3);
        this.type = i11;
        this.rendererName = str2;
        this.rendererIndex = i12;
        this.rendererFormat = format;
        this.rendererFormatSupport = i13;
        this.mediaPeriodId = mediaPeriodId;
        this.isRecoverable = z;
    }
}
