package com.discord.org.webrtc;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
class MediaCodecWrapperFactoryImpl implements MediaCodecWrapperFactory {

    public static class MediaCodecWrapperImpl implements MediaCodecWrapper {
        private final MediaCodec mediaCodec;

        public MediaCodecWrapperImpl(MediaCodec mediaCodec) {
            this.mediaCodec = mediaCodec;
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void configure(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i10) {
            this.mediaCodec.configure(mediaFormat, surface, mediaCrypto, i10);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public Surface createInputSurface() {
            return this.mediaCodec.createInputSurface();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public int dequeueInputBuffer(long j10) {
            return this.mediaCodec.dequeueInputBuffer(j10);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public int dequeueOutputBuffer(MediaCodec.BufferInfo bufferInfo, long j10) {
            return this.mediaCodec.dequeueOutputBuffer(bufferInfo, j10);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void flush() {
            this.mediaCodec.flush();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public MediaCodecInfo getCodecInfo() {
            return this.mediaCodec.getCodecInfo();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public ByteBuffer getInputBuffer(int i10) {
            return this.mediaCodec.getInputBuffer(i10);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public MediaFormat getInputFormat() {
            return this.mediaCodec.getInputFormat();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public ByteBuffer getOutputBuffer(int i10) {
            return this.mediaCodec.getOutputBuffer(i10);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public MediaFormat getOutputFormat() {
            return this.mediaCodec.getOutputFormat();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void queueInputBuffer(int i10, int i11, int i12, long j10, int i13) {
            this.mediaCodec.queueInputBuffer(i10, i11, i12, j10, i13);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void release() {
            this.mediaCodec.release();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void releaseOutputBuffer(int i10, boolean z) {
            this.mediaCodec.releaseOutputBuffer(i10, z);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void setOutputSurface(Surface surface) {
            this.mediaCodec.setOutputSurface(surface);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void setParameters(Bundle bundle) {
            this.mediaCodec.setParameters(bundle);
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void start() {
            this.mediaCodec.start();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public void stop() {
            this.mediaCodec.stop();
        }

        @Override // com.discord.org.webrtc.MediaCodecWrapper
        public MediaFormat getOutputFormat(int i10) {
            return this.mediaCodec.getOutputFormat(i10);
        }
    }

    @Override // com.discord.org.webrtc.MediaCodecWrapperFactory
    public MediaCodecWrapper createByCodecName(String str) throws IOException {
        return new MediaCodecWrapperImpl(MediaCodec.createByCodecName(str));
    }
}
