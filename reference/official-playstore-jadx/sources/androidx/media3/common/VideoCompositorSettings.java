package androidx.media3.common;

import android.util.Pair;
import androidx.media3.common.util.Size;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoCompositorSettings {
    public static final VideoCompositorSettings DEFAULT = new VideoCompositorSettings() { // from class: androidx.media3.common.VideoCompositorSettings.1
        @Override // androidx.media3.common.VideoCompositorSettings
        public Size getOutputSize(List<Size> list) {
            return list.get(0);
        }

        @Override // androidx.media3.common.VideoCompositorSettings
        public OverlaySettings getOverlaySettings(int i10, long j10) {
            return new OverlaySettings() { // from class: androidx.media3.common.VideoCompositorSettings.1.1
                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ float getAlphaScale() {
                    return h.a(this);
                }

                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ Pair getBackgroundFrameAnchor() {
                    return h.b(this);
                }

                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ float getHdrLuminanceMultiplier() {
                    return h.c(this);
                }

                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ Pair getOverlayFrameAnchor() {
                    return h.d(this);
                }

                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ float getRotationDegrees() {
                    return h.e(this);
                }

                @Override // androidx.media3.common.OverlaySettings
                public final /* synthetic */ Pair getScale() {
                    return h.f(this);
                }
            };
        }
    };

    Size getOutputSize(List<Size> list);

    OverlaySettings getOverlaySettings(int i10, long j10);
}
