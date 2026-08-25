package com.discord.org.webrtc;

import com.discord.org.webrtc.VideoProcessor;

/* JADX INFO: loaded from: classes4.dex */
public class VideoSource extends MediaSource {
    private final CapturerObserver capturerObserver;
    private boolean isCapturerRunning;
    private final NativeAndroidVideoTrackSource nativeAndroidVideoTrackSource;
    private VideoProcessor videoProcessor;
    private final Object videoProcessorLock;

    public static class AspectRatio {
        public static final AspectRatio UNDEFINED = new AspectRatio(0, 0);
        public final int height;
        public final int width;

        public AspectRatio(int i10, int i11) {
            this.width = i10;
            this.height = i11;
        }
    }

    public VideoSource(long j10) {
        super(j10);
        this.videoProcessorLock = new Object();
        this.capturerObserver = new CapturerObserver() { // from class: com.discord.org.webrtc.VideoSource.1
            @Override // com.discord.org.webrtc.CapturerObserver
            public void onCapturerStarted(boolean z) {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(z);
                synchronized (VideoSource.this.videoProcessorLock) {
                    try {
                        VideoSource.this.isCapturerRunning = z;
                        if (VideoSource.this.videoProcessor != null) {
                            VideoSource.this.videoProcessor.onCapturerStarted(z);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            @Override // com.discord.org.webrtc.CapturerObserver
            public void onCapturerStopped() {
                VideoSource.this.nativeAndroidVideoTrackSource.setState(false);
                synchronized (VideoSource.this.videoProcessorLock) {
                    try {
                        VideoSource.this.isCapturerRunning = false;
                        if (VideoSource.this.videoProcessor != null) {
                            VideoSource.this.videoProcessor.onCapturerStopped();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            @Override // com.discord.org.webrtc.CapturerObserver
            public void onFrameCaptured(VideoFrame videoFrame) {
                VideoProcessor.FrameAdaptationParameters frameAdaptationParametersAdaptFrame = VideoSource.this.nativeAndroidVideoTrackSource.adaptFrame(videoFrame);
                synchronized (VideoSource.this.videoProcessorLock) {
                    try {
                        if (VideoSource.this.videoProcessor != null) {
                            VideoSource.this.videoProcessor.onFrameCaptured(videoFrame, frameAdaptationParametersAdaptFrame);
                            return;
                        }
                        VideoFrame videoFrameB = j0.b(videoFrame, frameAdaptationParametersAdaptFrame);
                        if (videoFrameB != null) {
                            VideoSource.this.nativeAndroidVideoTrackSource.onFrameCaptured(videoFrameB);
                            videoFrameB.release();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.nativeAndroidVideoTrackSource = new NativeAndroidVideoTrackSource(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoProcessor$0(VideoFrame videoFrame) {
        this.nativeAndroidVideoTrackSource.onFrameCaptured(videoFrame);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoProcessor$1(VideoFrame videoFrame) {
        runWithReference(new y(this, videoFrame, 5));
    }

    public void adaptOutputFormat(int i10, int i11, int i12) {
        int iMax = Math.max(i10, i11);
        int iMin = Math.min(i10, i11);
        adaptOutputFormat(iMax, iMin, iMin, iMax, i12);
    }

    @Override // com.discord.org.webrtc.MediaSource
    public void dispose() {
        setVideoProcessor(null);
        super.dispose();
    }

    public CapturerObserver getCapturerObserver() {
        return this.capturerObserver;
    }

    public long getNativeVideoTrackSource() {
        return getNativeMediaSource();
    }

    public void setIsScreencast(boolean z) {
        this.nativeAndroidVideoTrackSource.setIsScreencast(z);
    }

    public void setVideoProcessor(VideoProcessor videoProcessor) {
        synchronized (this.videoProcessorLock) {
            try {
                VideoProcessor videoProcessor2 = this.videoProcessor;
                if (videoProcessor2 != null) {
                    videoProcessor2.setSink(null);
                    if (this.isCapturerRunning) {
                        this.videoProcessor.onCapturerStopped();
                    }
                }
                this.videoProcessor = videoProcessor;
                if (videoProcessor != null) {
                    videoProcessor.setSink(new b(this, 2));
                    if (this.isCapturerRunning) {
                        videoProcessor.onCapturerStarted(true);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void adaptOutputFormat(int i10, int i11, int i12, int i13, int i14) {
        adaptOutputFormat(new AspectRatio(i10, i11), Integer.valueOf(i10 * i11), new AspectRatio(i12, i13), Integer.valueOf(i12 * i13), Integer.valueOf(i14));
    }

    public void adaptOutputFormat(AspectRatio aspectRatio, Integer num, AspectRatio aspectRatio2, Integer num2, Integer num3) {
        this.nativeAndroidVideoTrackSource.adaptOutputFormat(aspectRatio, num, aspectRatio2, num2, num3);
    }
}
