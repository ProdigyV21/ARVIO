package com.discord.org.webrtc;

import android.hardware.Camera;
import android.os.SystemClock;
import androidx.fragment.app.a2;
import com.discord.org.webrtc.CameraEnumerationAndroid;
import com.discord.org.webrtc.CameraVideoCapturer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Camera1Enumerator implements CameraEnumerator {
    private static final String TAG = "Camera1Enumerator";
    private static List<List<CameraEnumerationAndroid.CaptureFormat>> cachedSupportedFormats;
    private final boolean captureToTexture;

    public Camera1Enumerator() {
        this(true);
    }

    public static List<CameraEnumerationAndroid.CaptureFormat.FramerateRange> convertFramerates(List<int[]> list) {
        ArrayList arrayList = new ArrayList();
        for (int[] iArr : list) {
            arrayList.add(new CameraEnumerationAndroid.CaptureFormat.FramerateRange(iArr[0], iArr[1]));
        }
        return arrayList;
    }

    public static List<Size> convertSizes(List<Camera.Size> list) {
        ArrayList arrayList = new ArrayList();
        for (Camera.Size size : list) {
            arrayList.add(new Size(size.width, size.height));
        }
        return arrayList;
    }

    private static List<CameraEnumerationAndroid.CaptureFormat> enumerateFormats(int i10) {
        int i11;
        Logging.d(TAG, "Get supported formats for camera index " + i10 + ".");
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Camera cameraOpen = null;
        try {
            try {
                Logging.d(TAG, "Opening camera with index " + i10);
                cameraOpen = Camera.open(i10);
                Camera.Parameters parameters = cameraOpen.getParameters();
                cameraOpen.release();
                ArrayList arrayList = new ArrayList();
                try {
                    List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
                    int i12 = 0;
                    if (supportedPreviewFpsRange != null) {
                        int[] iArr = supportedPreviewFpsRange.get(supportedPreviewFpsRange.size() - 1);
                        i12 = iArr[0];
                        i11 = iArr[1];
                    } else {
                        i11 = 0;
                    }
                    for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
                        arrayList.add(new CameraEnumerationAndroid.CaptureFormat(size.width, size.height, i12, i11));
                    }
                } catch (Exception e5) {
                    Logging.e(TAG, "getSupportedFormats() failed on camera index " + i10, e5);
                }
                Logging.d(TAG, "Get supported formats for camera index " + i10 + " done. Time spent: " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms.");
                return arrayList;
            } catch (RuntimeException e6) {
                Logging.e(TAG, "Open camera failed on camera index " + i10, e6);
                ArrayList arrayList2 = new ArrayList();
                if (cameraOpen != null) {
                    cameraOpen.release();
                }
                return arrayList2;
            }
        } catch (Throwable th) {
            if (cameraOpen != null) {
                cameraOpen.release();
            }
            throw th;
        }
    }

    public static int getCameraIndex(String str) {
        Logging.d(TAG, "getCameraIndex: " + str);
        for (int i10 = 0; i10 < Camera.getNumberOfCameras(); i10++) {
            if (str.equals(getDeviceName(i10))) {
                return i10;
            }
        }
        throw new IllegalArgumentException(androidx.compose.material3.d.C("No such camera: ", str));
    }

    private static Camera.CameraInfo getCameraInfo(int i10) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i10, cameraInfo);
            return cameraInfo;
        } catch (Exception e5) {
            Logging.e(TAG, "getCameraInfo failed on index " + i10, e5);
            return null;
        }
    }

    public static String getDeviceName(int i10) {
        Camera.CameraInfo cameraInfo = getCameraInfo(i10);
        if (cameraInfo == null) {
            return null;
        }
        String str = cameraInfo.facing == 1 ? "front" : "back";
        int i11 = cameraInfo.orientation;
        StringBuilder sbO = a2.o("Camera ", i10, ", Facing ", str, ", Orientation ");
        sbO.append(i11);
        return sbO.toString();
    }

    @Override // com.discord.org.webrtc.CameraEnumerator
    public CameraVideoCapturer createCapturer(String str, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        return new Camera1Capturer(str, cameraEventsHandler, this.captureToTexture);
    }

    @Override // com.discord.org.webrtc.CameraEnumerator
    public String[] getDeviceNames() {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < Camera.getNumberOfCameras(); i10++) {
            String deviceName = getDeviceName(i10);
            if (deviceName != null) {
                arrayList.add(deviceName);
                Logging.d(TAG, "Index: " + i10 + ". " + deviceName);
            } else {
                Logging.e(TAG, "Index: " + i10 + ". Failed to query camera name.");
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // com.discord.org.webrtc.CameraEnumerator
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(String str) {
        return getSupportedFormats(getCameraIndex(str));
    }

    @Override // com.discord.org.webrtc.CameraEnumerator
    public boolean isBackFacing(String str) {
        Camera.CameraInfo cameraInfo = getCameraInfo(getCameraIndex(str));
        return cameraInfo != null && cameraInfo.facing == 0;
    }

    @Override // com.discord.org.webrtc.CameraEnumerator
    public boolean isFrontFacing(String str) {
        Camera.CameraInfo cameraInfo = getCameraInfo(getCameraIndex(str));
        return cameraInfo != null && cameraInfo.facing == 1;
    }

    public Camera1Enumerator(boolean z) {
        this.captureToTexture = z;
    }

    public static synchronized List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats(int i10) {
        try {
            if (cachedSupportedFormats == null) {
                cachedSupportedFormats = new ArrayList();
                for (int i11 = 0; i11 < Camera.getNumberOfCameras(); i11++) {
                    cachedSupportedFormats.add(enumerateFormats(i11));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return cachedSupportedFormats.get(i10);
    }
}
