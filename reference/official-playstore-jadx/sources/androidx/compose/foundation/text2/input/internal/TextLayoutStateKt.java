package androidx.compose.foundation.text2.input.internal;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u000b\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"coerceIn", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "coerceIn-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)J", "fromDecorationToTextLayout", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "offset", "fromDecorationToTextLayout-Uv8p0NA", "(Landroidx/compose/foundation/text2/input/internal/TextLayoutState;J)J", "fromTextLayoutToCore", "fromTextLayoutToCore-Uv8p0NA", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutStateKt {
    /* JADX INFO: renamed from: coerceIn-3MmeM6k, reason: not valid java name */
    public static final long m1130coerceIn3MmeM6k(long j10, Rect rect) {
        return OffsetKt.Offset(Offset.m3236getXimpl(j10) < rect.getLeft() ? rect.getLeft() : Offset.m3236getXimpl(j10) > rect.getRight() ? rect.getRight() : Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10) < rect.getTop() ? rect.getTop() : Offset.m3237getYimpl(j10) > rect.getBottom() ? rect.getBottom() : Offset.m3237getYimpl(j10));
    }

    /* JADX INFO: renamed from: fromDecorationToTextLayout-Uv8p0NA, reason: not valid java name */
    public static final long m1131fromDecorationToTextLayoutUv8p0NA(TextLayoutState textLayoutState, long j10) {
        Offset offsetM3225boximpl;
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            LayoutCoordinates decoratorNodeCoordinates = textLayoutState.getDecoratorNodeCoordinates();
            if (decoratorNodeCoordinates != null) {
                offsetM3225boximpl = Offset.m3225boximpl((textLayoutNodeCoordinates.isAttached() && decoratorNodeCoordinates.isAttached()) ? textLayoutNodeCoordinates.mo4639localPositionOfR5De75A(decoratorNodeCoordinates, j10) : j10);
            } else {
                offsetM3225boximpl = null;
            }
            if (offsetM3225boximpl != null) {
                return offsetM3225boximpl.getPackedValue();
            }
        }
        return j10;
    }

    /* JADX INFO: renamed from: fromTextLayoutToCore-Uv8p0NA, reason: not valid java name */
    public static final long m1132fromTextLayoutToCoreUv8p0NA(TextLayoutState textLayoutState, long j10) {
        LayoutCoordinates textLayoutNodeCoordinates = textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            Offset offsetM3225boximpl = null;
            if (!textLayoutNodeCoordinates.isAttached()) {
                textLayoutNodeCoordinates = null;
            }
            if (textLayoutNodeCoordinates != null) {
                LayoutCoordinates coreNodeCoordinates = textLayoutState.getCoreNodeCoordinates();
                if (coreNodeCoordinates != null) {
                    if (!coreNodeCoordinates.isAttached()) {
                        coreNodeCoordinates = null;
                    }
                    if (coreNodeCoordinates != null) {
                        offsetM3225boximpl = Offset.m3225boximpl(coreNodeCoordinates.mo4639localPositionOfR5De75A(textLayoutNodeCoordinates, j10));
                    }
                }
                if (offsetM3225boximpl != null) {
                    return offsetM3225boximpl.getPackedValue();
                }
            }
        }
        return j10;
    }
}
