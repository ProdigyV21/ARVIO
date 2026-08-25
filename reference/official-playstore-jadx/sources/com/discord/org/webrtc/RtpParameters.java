package com.discord.org.webrtc;

import com.discord.org.webrtc.MediaStreamTrack;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RtpParameters {
    public final List<Codec> codecs;
    public DegradationPreference degradationPreference;
    public final List<Encoding> encodings;
    private final List<HeaderExtension> headerExtensions;
    private final Rtcp rtcp;
    public final String transactionId;

    public static class Codec {
        public Integer clockRate;
        MediaStreamTrack.MediaType kind;
        public String name;
        public Integer numChannels;
        public Map<String, String> parameters;
        public int payloadType;

        public Codec(int i10, String str, MediaStreamTrack.MediaType mediaType, Integer num, Integer num2, Map<String, String> map) {
            this.payloadType = i10;
            this.name = str;
            this.kind = mediaType;
            this.clockRate = num;
            this.numChannels = num2;
            this.parameters = map;
        }

        public Integer getClockRate() {
            return this.clockRate;
        }

        public MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        public String getName() {
            return this.name;
        }

        public Integer getNumChannels() {
            return this.numChannels;
        }

        public Map getParameters() {
            return this.parameters;
        }

        public int getPayloadType() {
            return this.payloadType;
        }
    }

    public enum DegradationPreference {
        DISABLED,
        MAINTAIN_FRAMERATE,
        MAINTAIN_RESOLUTION,
        BALANCED;

        public static DegradationPreference fromNativeIndex(int i10) {
            return values()[i10];
        }
    }

    public static class HeaderExtension {
        private final boolean encrypted;
        private final int id;
        private final String uri;

        public HeaderExtension(String str, int i10, boolean z) {
            this.uri = str;
            this.id = i10;
            this.encrypted = z;
        }

        public boolean getEncrypted() {
            return this.encrypted;
        }

        public int getId() {
            return this.id;
        }

        public String getUri() {
            return this.uri;
        }
    }

    public static class Rtcp {
        private final String cname;
        private final boolean reducedSize;

        public Rtcp(String str, boolean z) {
            this.cname = str;
            this.reducedSize = z;
        }

        public String getCname() {
            return this.cname;
        }

        public boolean getReducedSize() {
            return this.reducedSize;
        }
    }

    public RtpParameters(String str, DegradationPreference degradationPreference, Rtcp rtcp, List<HeaderExtension> list, List<Encoding> list2, List<Codec> list3) {
        this.transactionId = str;
        this.degradationPreference = degradationPreference;
        this.rtcp = rtcp;
        this.headerExtensions = list;
        this.encodings = list2;
        this.codecs = list3;
    }

    public List<Codec> getCodecs() {
        return this.codecs;
    }

    public DegradationPreference getDegradationPreference() {
        return this.degradationPreference;
    }

    public List<Encoding> getEncodings() {
        return this.encodings;
    }

    public List<HeaderExtension> getHeaderExtensions() {
        return this.headerExtensions;
    }

    public Rtcp getRtcp() {
        return this.rtcp;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public static class Encoding {
        public boolean active;
        public boolean adaptiveAudioPacketTime;
        public double bitratePriority;
        public Integer maxBitrateBps;
        public Integer maxFramerate;
        public Integer minBitrateBps;
        public int networkPriority;
        public Integer numTemporalLayers;
        public String rid;
        public Double scaleResolutionDownBy;
        public Long ssrc;

        public Encoding(String str, boolean z, Double d4) {
            this.bitratePriority = 1.0d;
            this.networkPriority = 1;
            this.rid = str;
            this.active = z;
            this.scaleResolutionDownBy = d4;
        }

        public boolean getActive() {
            return this.active;
        }

        public boolean getAdaptivePTime() {
            return this.adaptiveAudioPacketTime;
        }

        public double getBitratePriority() {
            return this.bitratePriority;
        }

        public Integer getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        public Integer getMaxFramerate() {
            return this.maxFramerate;
        }

        public Integer getMinBitrateBps() {
            return this.minBitrateBps;
        }

        public int getNetworkPriority() {
            return this.networkPriority;
        }

        public Integer getNumTemporalLayers() {
            return this.numTemporalLayers;
        }

        public String getRid() {
            return this.rid;
        }

        public Double getScaleResolutionDownBy() {
            return this.scaleResolutionDownBy;
        }

        public Long getSsrc() {
            return this.ssrc;
        }

        public Encoding(String str, boolean z, double d4, int i10, Integer num, Integer num2, Integer num3, Integer num4, Double d10, Long l10, boolean z5) {
            this.rid = str;
            this.active = z;
            this.bitratePriority = d4;
            this.networkPriority = i10;
            this.maxBitrateBps = num;
            this.minBitrateBps = num2;
            this.maxFramerate = num3;
            this.numTemporalLayers = num4;
            this.scaleResolutionDownBy = d10;
            this.ssrc = l10;
            this.adaptiveAudioPacketTime = z5;
        }
    }
}
