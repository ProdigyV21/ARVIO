package com.discord.org.webrtc;

import android.graphics.ImageFormat;
import androidx.compose.material3.MenuKt;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CameraEnumerationAndroid {
    static final ArrayList<Size> COMMON_RESOLUTIONS = new ArrayList<>(Arrays.asList(new Size(160, MenuKt.InTransitionDuration), new Size(PsExtractor.VIDEO_STREAM_MASK, 160), new Size(320, PsExtractor.VIDEO_STREAM_MASK), new Size(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, PsExtractor.VIDEO_STREAM_MASK), new Size(480, 320), new Size(640, 360), new Size(640, 480), new Size(768, 480), new Size(854, 480), new Size(800, MediaError.DetailedErrorCode.TEXT_UNKNOWN), new Size(960, 540), new Size(960, 640), new Size(1024, 576), new Size(1024, MediaError.DetailedErrorCode.TEXT_UNKNOWN), new Size(1280, 720), new Size(1280, 1024), new Size(1920, 1080), new Size(1920, 1440), new Size(2560, 1440), new Size(3840, 2160)));
    private static final String TAG = "CameraEnumerationAndroid";

    public static class CaptureFormat {
        public final FramerateRange framerate;
        public final int height;
        public final int imageFormat = 17;
        public final int width;

        public static class FramerateRange {
            public int max;
            public int min;

            public FramerateRange(int i10, int i11) {
                this.min = i10;
                this.max = i11;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FramerateRange)) {
                    return false;
                }
                FramerateRange framerateRange = (FramerateRange) obj;
                return this.min == framerateRange.min && this.max == framerateRange.max;
            }

            public int hashCode() {
                return (this.min * 65537) + 1 + this.max;
            }

            public String toString() {
                return "[" + (this.min / 1000.0f) + ":" + (this.max / 1000.0f) + "]";
            }
        }

        public CaptureFormat(int i10, int i11, int i12, int i13) {
            this.width = i10;
            this.height = i11;
            this.framerate = new FramerateRange(i12, i13);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CaptureFormat)) {
                return false;
            }
            CaptureFormat captureFormat = (CaptureFormat) obj;
            return this.width == captureFormat.width && this.height == captureFormat.height && this.framerate.equals(captureFormat.framerate);
        }

        public int frameSize() {
            return frameSize(this.width, this.height, 17);
        }

        public int hashCode() {
            return this.framerate.hashCode() + (((this.width * 65497) + this.height) * 251) + 1;
        }

        public String toString() {
            return this.width + "x" + this.height + "@" + this.framerate;
        }

        public static int frameSize(int i10, int i11, int i12) {
            if (i12 != 17) {
                throw new UnsupportedOperationException("Don't know how to calculate the frame size of non-NV21 image formats.");
            }
            return (ImageFormat.getBitsPerPixel(i12) * (i10 * i11)) / 8;
        }

        public CaptureFormat(int i10, int i11, FramerateRange framerateRange) {
            this.width = i10;
            this.height = i11;
            this.framerate = framerateRange;
        }
    }

    public static abstract class ClosestComparator<T> implements Comparator<T> {
        public /* synthetic */ ClosestComparator(int i10) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(T t2, T t10) {
            return diff(t2) - diff(t10);
        }

        public abstract int diff(T t2);

        private ClosestComparator() {
        }
    }

    public static CaptureFormat.FramerateRange getClosestSupportedFramerateRange(List<CaptureFormat.FramerateRange> list, final int i10) {
        return (CaptureFormat.FramerateRange) Collections.min(list, new ClosestComparator<CaptureFormat.FramerateRange>() { // from class: com.discord.org.webrtc.CameraEnumerationAndroid.1
            private static final int MAX_FPS_DIFF_THRESHOLD = 5000;
            private static final int MAX_FPS_HIGH_DIFF_WEIGHT = 3;
            private static final int MAX_FPS_LOW_DIFF_WEIGHT = 1;
            private static final int MIN_FPS_HIGH_VALUE_WEIGHT = 4;
            private static final int MIN_FPS_LOW_VALUE_WEIGHT = 1;
            private static final int MIN_FPS_THRESHOLD = 8000;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            private int progressivePenalty(int i11, int i12, int i13, int i14) {
                if (i11 < i12) {
                    return i11 * i13;
                }
                return ((i11 - i12) * i14) + (i13 * i12);
            }

            @Override // com.discord.org.webrtc.CameraEnumerationAndroid.ClosestComparator
            public int diff(CaptureFormat.FramerateRange framerateRange) {
                return progressivePenalty(framerateRange.min, 8000, 1, 4) + progressivePenalty(Math.abs((i10 * 1000) - framerateRange.max), 5000, 1, 3);
            }
        });
    }

    public static Size getClosestSupportedSize(List<Size> list, final int i10, final int i11) {
        return (Size) Collections.min(list, new ClosestComparator<Size>() { // from class: com.discord.org.webrtc.CameraEnumerationAndroid.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // com.discord.org.webrtc.CameraEnumerationAndroid.ClosestComparator
            public int diff(Size size) {
                return Math.abs(i11 - size.height) + Math.abs(i10 - size.width);
            }
        });
    }

    public static void reportCameraResolution(Histogram histogram, Size size) {
        histogram.addSample(COMMON_RESOLUTIONS.indexOf(size) + 1);
    }
}
