package androidx.media3.common;

import androidx.compose.foundation.c;

/* JADX INFO: loaded from: classes3.dex */
public final class VideoFrameProcessingException extends Exception {
    public final long presentationTimeUs;

    /* JADX WARN: Illegal instructions before constructor call */
    public VideoFrameProcessingException(String str, long j10) {
        StringBuilder sbS = androidx.compose.material3.d.s(str);
        sbS.append(getPresentationTimeUsString(j10));
        super(sbS.toString());
        this.presentationTimeUs = j10;
    }

    public static VideoFrameProcessingException from(Exception exc) {
        return from(exc, C.TIME_UNSET);
    }

    private static String getPresentationTimeUsString(long j10) {
        return j10 == C.TIME_UNSET ? " @UNSET" : c.q(j10, " @");
    }

    public static VideoFrameProcessingException from(Exception exc, long j10) {
        return exc instanceof VideoFrameProcessingException ? (VideoFrameProcessingException) exc : new VideoFrameProcessingException(exc, j10);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public VideoFrameProcessingException(String str, Throwable th, long j10) {
        StringBuilder sbS = androidx.compose.material3.d.s(str);
        sbS.append(getPresentationTimeUsString(j10));
        super(sbS.toString(), th);
        this.presentationTimeUs = j10;
    }

    public VideoFrameProcessingException(String str) {
        this(str, C.TIME_UNSET);
    }

    public VideoFrameProcessingException(String str, Throwable th) {
        this(str, th, C.TIME_UNSET);
    }

    public VideoFrameProcessingException(Throwable th) {
        this(th, C.TIME_UNSET);
    }

    public VideoFrameProcessingException(Throwable th, long j10) {
        super(getPresentationTimeUsString(j10), th);
        this.presentationTimeUs = j10;
    }
}
