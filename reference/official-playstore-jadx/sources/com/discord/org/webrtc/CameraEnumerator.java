package com.discord.org.webrtc;

import com.discord.org.webrtc.CameraEnumerationAndroid;
import com.discord.org.webrtc.CameraVideoCapturer;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface CameraEnumerator {
    CameraVideoCapturer createCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler);

    String[] getDeviceNames();

    List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String str);

    boolean isBackFacing(String str);

    boolean isFrontFacing(String str);
}
