package com.discord.org.webrtc;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    private long nativeRtpSender;
    private boolean ownsTrack = true;

    public RtpSender(long j10) {
        this.nativeRtpSender = j10;
        this.cachedTrack = MediaStreamTrack.createMediaStreamTrack(nativeGetTrack(j10));
        if (!nativeGetMediaType(j10).equalsIgnoreCase("audio")) {
            this.dtmfSender = null;
        } else {
            long jNativeGetDtmfSender = nativeGetDtmfSender(j10);
            this.dtmfSender = jNativeGetDtmfSender != 0 ? new DtmfSender(jNativeGetDtmfSender) : null;
        }
    }

    private void checkRtpSenderExists() {
        if (this.nativeRtpSender == 0) {
            throw new IllegalStateException("RtpSender has been disposed.");
        }
    }

    private static native long nativeGetDtmfSender(long j10);

    private static native String nativeGetId(long j10);

    private static native String nativeGetMediaType(long j10);

    private static native RtpParameters nativeGetParameters(long j10);

    private static native List<String> nativeGetStreams(long j10);

    private static native long nativeGetTrack(long j10);

    private static native void nativeSetFrameEncryptor(long j10, long j11);

    private static native boolean nativeSetParameters(long j10, RtpParameters rtpParameters);

    private static native void nativeSetStreams(long j10, List<String> list);

    private static native boolean nativeSetTrack(long j10, long j11);

    public void dispose() {
        checkRtpSenderExists();
        DtmfSender dtmfSender = this.dtmfSender;
        if (dtmfSender != null) {
            dtmfSender.dispose();
        }
        MediaStreamTrack mediaStreamTrack = this.cachedTrack;
        if (mediaStreamTrack != null && this.ownsTrack) {
            mediaStreamTrack.dispose();
        }
        JniCommon.nativeReleaseRef(this.nativeRtpSender);
        this.nativeRtpSender = 0L;
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public long getNativeRtpSender() {
        checkRtpSenderExists();
        return this.nativeRtpSender;
    }

    public RtpParameters getParameters() {
        checkRtpSenderExists();
        return nativeGetParameters(this.nativeRtpSender);
    }

    public List<String> getStreams() {
        checkRtpSenderExists();
        return nativeGetStreams(this.nativeRtpSender);
    }

    public String id() {
        checkRtpSenderExists();
        return nativeGetId(this.nativeRtpSender);
    }

    public void setFrameEncryptor(FrameEncryptor frameEncryptor) {
        checkRtpSenderExists();
        nativeSetFrameEncryptor(this.nativeRtpSender, frameEncryptor.getNativeFrameEncryptor());
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        checkRtpSenderExists();
        return nativeSetParameters(this.nativeRtpSender, rtpParameters);
    }

    public void setStreams(List<String> list) {
        checkRtpSenderExists();
        nativeSetStreams(this.nativeRtpSender, list);
    }

    public boolean setTrack(MediaStreamTrack mediaStreamTrack, boolean z) {
        checkRtpSenderExists();
        if (!nativeSetTrack(this.nativeRtpSender, mediaStreamTrack == null ? 0L : mediaStreamTrack.getNativeMediaStreamTrack())) {
            return false;
        }
        MediaStreamTrack mediaStreamTrack2 = this.cachedTrack;
        if (mediaStreamTrack2 != null && this.ownsTrack) {
            mediaStreamTrack2.dispose();
        }
        this.cachedTrack = mediaStreamTrack;
        this.ownsTrack = z;
        return true;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
