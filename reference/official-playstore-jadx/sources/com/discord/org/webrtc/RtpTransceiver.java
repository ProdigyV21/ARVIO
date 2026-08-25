package com.discord.org.webrtc;

import com.discord.org.webrtc.MediaStreamTrack;
import com.discord.org.webrtc.RtpCapabilities;
import com.discord.org.webrtc.RtpParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RtpTransceiver {
    private RtpReceiver cachedReceiver;
    private RtpSender cachedSender;
    private long nativeRtpTransceiver;

    public enum RtpTransceiverDirection {
        SEND_RECV(0),
        SEND_ONLY(1),
        RECV_ONLY(2),
        INACTIVE(3),
        STOPPED(4);

        private final int nativeIndex;

        RtpTransceiverDirection(int i10) {
            this.nativeIndex = i10;
        }

        public static RtpTransceiverDirection fromNativeIndex(int i10) {
            for (RtpTransceiverDirection rtpTransceiverDirection : values()) {
                if (rtpTransceiverDirection.getNativeIndex() == i10) {
                    return rtpTransceiverDirection;
                }
            }
            throw new IllegalArgumentException(a0.c.i(i10, "Uknown native RtpTransceiverDirection type"));
        }

        public int getNativeIndex() {
            return this.nativeIndex;
        }
    }

    public static final class RtpTransceiverInit {
        private final RtpTransceiverDirection direction;
        private final List<RtpParameters.Encoding> sendEncodings;
        private final List<String> streamIds;

        public RtpTransceiverInit() {
            this(RtpTransceiverDirection.SEND_RECV);
        }

        public int getDirectionNativeIndex() {
            return this.direction.getNativeIndex();
        }

        public List<RtpParameters.Encoding> getSendEncodings() {
            return new ArrayList(this.sendEncodings);
        }

        public List<String> getStreamIds() {
            return new ArrayList(this.streamIds);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public RtpTransceiverInit(RtpTransceiverDirection rtpTransceiverDirection) {
            List list = Collections.EMPTY_LIST;
            this(rtpTransceiverDirection, list, list);
        }

        public RtpTransceiverInit(RtpTransceiverDirection rtpTransceiverDirection, List<String> list) {
            this(rtpTransceiverDirection, list, Collections.EMPTY_LIST);
        }

        public RtpTransceiverInit(RtpTransceiverDirection rtpTransceiverDirection, List<String> list, List<RtpParameters.Encoding> list2) {
            this.direction = rtpTransceiverDirection;
            this.streamIds = new ArrayList(list);
            this.sendEncodings = new ArrayList(list2);
        }
    }

    public RtpTransceiver(long j10) {
        this.nativeRtpTransceiver = j10;
        this.cachedSender = nativeGetSender(j10);
        this.cachedReceiver = nativeGetReceiver(j10);
    }

    private void checkRtpTransceiverExists() {
        if (this.nativeRtpTransceiver == 0) {
            throw new IllegalStateException("RtpTransceiver has been disposed.");
        }
    }

    private static native RtpTransceiverDirection nativeCurrentDirection(long j10);

    private static native RtpTransceiverDirection nativeDirection(long j10);

    private static native MediaStreamTrack.MediaType nativeGetMediaType(long j10);

    private static native String nativeGetMid(long j10);

    private static native RtpReceiver nativeGetReceiver(long j10);

    private static native RtpSender nativeGetSender(long j10);

    private static native RtcError nativeSetCodecPreferences(long j10, List<RtpCapabilities.CodecCapability> list);

    private static native boolean nativeSetDirection(long j10, RtpTransceiverDirection rtpTransceiverDirection);

    private static native void nativeStopInternal(long j10);

    private static native void nativeStopStandard(long j10);

    private static native boolean nativeStopped(long j10);

    public void dispose() {
        checkRtpTransceiverExists();
        this.cachedSender.dispose();
        this.cachedReceiver.dispose();
        JniCommon.nativeReleaseRef(this.nativeRtpTransceiver);
        this.nativeRtpTransceiver = 0L;
    }

    public RtpTransceiverDirection getCurrentDirection() {
        checkRtpTransceiverExists();
        return nativeCurrentDirection(this.nativeRtpTransceiver);
    }

    public RtpTransceiverDirection getDirection() {
        checkRtpTransceiverExists();
        return nativeDirection(this.nativeRtpTransceiver);
    }

    public MediaStreamTrack.MediaType getMediaType() {
        checkRtpTransceiverExists();
        return nativeGetMediaType(this.nativeRtpTransceiver);
    }

    public String getMid() {
        checkRtpTransceiverExists();
        return nativeGetMid(this.nativeRtpTransceiver);
    }

    public RtpReceiver getReceiver() {
        return this.cachedReceiver;
    }

    public RtpSender getSender() {
        return this.cachedSender;
    }

    public boolean isStopped() {
        checkRtpTransceiverExists();
        return nativeStopped(this.nativeRtpTransceiver);
    }

    public RtcError setCodecPreferences(List<RtpCapabilities.CodecCapability> list) {
        checkRtpTransceiverExists();
        return nativeSetCodecPreferences(this.nativeRtpTransceiver, list);
    }

    public boolean setDirection(RtpTransceiverDirection rtpTransceiverDirection) {
        checkRtpTransceiverExists();
        return nativeSetDirection(this.nativeRtpTransceiver, rtpTransceiverDirection);
    }

    public void stop() {
        checkRtpTransceiverExists();
        nativeStopInternal(this.nativeRtpTransceiver);
    }

    public void stopInternal() {
        checkRtpTransceiverExists();
        nativeStopInternal(this.nativeRtpTransceiver);
    }

    public void stopStandard() {
        checkRtpTransceiverExists();
        nativeStopStandard(this.nativeRtpTransceiver);
    }
}
