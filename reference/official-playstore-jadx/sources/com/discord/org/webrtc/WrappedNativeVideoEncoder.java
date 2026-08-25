package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoEncoder;

/* JADX INFO: loaded from: classes4.dex */
public abstract class WrappedNativeVideoEncoder implements VideoEncoder {
    @Override // com.discord.org.webrtc.VideoEncoder
    public abstract long createNative(long j10);

    @Override // com.discord.org.webrtc.VideoEncoder
    public final VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final /* synthetic */ VideoEncoder.EncoderInfo getEncoderInfo() {
        return d0.b(this);
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final /* synthetic */ VideoEncoder.ResolutionBitrateLimits[] getResolutionBitrateLimits() {
        return d0.c(this);
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final VideoEncoder.ScalingSettings getScalingSettings() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public abstract boolean isHardwareEncoder();

    @Override // com.discord.org.webrtc.VideoEncoder
    public final VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i10) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @Override // com.discord.org.webrtc.VideoEncoder
    public final /* synthetic */ VideoCodecStatus setRates(VideoEncoder.RateControlParameters rateControlParameters) {
        return d0.e(this, rateControlParameters);
    }
}
