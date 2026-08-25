package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoDecoder {
    public static final String TAG = "VideoDecoderDiscord";

    public interface Callback {
        void onDecodedFrame(VideoFrame videoFrame, Integer num, Integer num2);
    }

    public static class DecodeInfo {
        public final boolean isMissingFrames;
        public final long renderTimeMs;

        public DecodeInfo(boolean z, long j10) {
            this.isMissingFrames = z;
            this.renderTimeMs = j10;
        }
    }

    public static class Settings {
        public final int height;
        public final int numberOfCores;
        public final int width;

        public Settings(int i10, int i11, int i12) {
            this.numberOfCores = i10;
            this.width = i11;
            this.height = i12;
        }
    }

    long createNative(long j10);

    VideoCodecStatus decode(EncodedImage encodedImage, DecodeInfo decodeInfo);

    String getImplementationName();

    VideoCodecStatus initDecode(Settings settings, Callback callback);

    VideoCodecStatus release();

    void setStreamId(String str);
}
