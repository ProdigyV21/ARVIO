package com.arflix.tv.ui.screens.player;

import androidx.media3.common.text.Cue;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\n\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002¨\u0006\r"}, d2 = {"requiresVideoFrameSubtitleViewport", "", "", "Landroidx/media3/common/text/Cue;", "preserveAuthoredTextPositioning", "hasNonDefaultAuthoredPosition", "defaultFractionForAnchor", "", LinkHeader.Parameters.Anchor, "", "(I)Ljava/lang/Float;", "approximatelyEquals", "other", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FullViewportSubtitlePlayerViewKt {
    private static final boolean approximatelyEquals(float f10, float f11) {
        return Math.abs(f10 - f11) < 1.0E-4f;
    }

    private static final Float defaultFractionForAnchor(int i10) {
        if (i10 == 0) {
            return Float.valueOf(0.05f);
        }
        if (i10 == 1) {
            return Float.valueOf(0.5f);
        }
        if (i10 != 2) {
            return null;
        }
        return Float.valueOf(0.95f);
    }

    private static final boolean hasNonDefaultAuthoredPosition(Cue cue) {
        if (cue.line == -3.4028235E38f) {
            float f10 = cue.position;
            return (f10 == -3.4028235E38f || (cue.positionAnchor == 1 && approximatelyEquals(f10, 0.5f))) ? false : true;
        }
        if (cue.lineType != 0) {
            return true;
        }
        Float fDefaultFractionForAnchor = defaultFractionForAnchor(cue.lineAnchor);
        Float fDefaultFractionForAnchor2 = defaultFractionForAnchor(cue.positionAnchor);
        if (fDefaultFractionForAnchor != null && approximatelyEquals(cue.line, fDefaultFractionForAnchor.floatValue())) {
            float f11 = cue.position;
            if (f11 == -3.4028235E38f || (fDefaultFractionForAnchor2 != null && approximatelyEquals(f11, fDefaultFractionForAnchor2.floatValue()))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean requiresVideoFrameSubtitleViewport(List<Cue> list, boolean z) {
        if (list.isEmpty()) {
            return false;
        }
        for (Cue cue : list) {
            if (cue.bitmap != null || cue.verticalType != Integer.MIN_VALUE) {
                return true;
            }
            if (z && hasNonDefaultAuthoredPosition(cue)) {
                return true;
            }
        }
        return false;
    }
}
