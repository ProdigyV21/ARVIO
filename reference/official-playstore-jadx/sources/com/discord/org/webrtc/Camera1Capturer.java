package com.discord.org.webrtc;

import android.content.Context;
import com.discord.org.webrtc.CameraSession;
import com.discord.org.webrtc.CameraVideoCapturer;

/* JADX INFO: loaded from: classes4.dex */
public class Camera1Capturer extends CameraCapturer {
    private final boolean captureToTexture;

    public Camera1Capturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler, boolean z) {
        super(str, cameraEventsHandler, new Camera1Enumerator(z));
        this.captureToTexture = z;
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ void changeCaptureFormat(int i10, int i11, int i12) {
        super.changeCaptureFormat(i10, i11, i12);
    }

    @Override // com.discord.org.webrtc.CameraCapturer
    public void createCameraSession(CameraSession.CreateSessionCallback createSessionCallback, CameraSession.Events events, Context context, SurfaceTextureHelper surfaceTextureHelper, String str, int i10, int i11, int i12) {
        Camera1Session.create(createSessionCallback, events, this.captureToTexture, context, surfaceTextureHelper, str, i10, i11, i12);
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver) {
        super.initialize(surfaceTextureHelper, context, capturerObserver);
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ boolean isScreencast() {
        return super.isScreencast();
    }

    @Override // com.discord.org.webrtc.CameraCapturer
    public /* bridge */ /* synthetic */ void printStackTrace() {
        super.printStackTrace();
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ void startCapture(int i10, int i11, int i12) {
        super.startCapture(i10, i11, i12);
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.VideoCapturer
    public /* bridge */ /* synthetic */ void stopCapture() {
        super.stopCapture();
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void switchCamera(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        super.switchCamera(cameraSwitchHandler);
    }

    @Override // com.discord.org.webrtc.CameraCapturer, com.discord.org.webrtc.CameraVideoCapturer
    public /* bridge */ /* synthetic */ void switchCamera(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler, String str) {
        super.switchCamera(cameraSwitchHandler, str);
    }
}
