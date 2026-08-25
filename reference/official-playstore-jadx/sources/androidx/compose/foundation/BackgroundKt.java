package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007H\u0007\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"background", "Landroidx/compose/ui/Modifier;", "brush", "Landroidx/compose/ui/graphics/Brush;", "shape", "Landroidx/compose/ui/graphics/Shape;", "alpha", "", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "background-bw27NRU", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackgroundKt {
    public static final Modifier background(Modifier modifier, Brush brush, Shape shape, float f10) {
        return modifier.then(new BackgroundElement(0L, brush, f10, shape, InspectableValueKt.isDebugInspectorInfoEnabled() ? new BackgroundKt$background$$inlined$debugInspectorInfo$1(f10, brush, shape) : InspectableValueKt.getNoInspectorInfo(), 1, null));
    }

    public static /* synthetic */ Modifier background$default(Modifier modifier, Brush brush, Shape shape, float f10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        if ((i10 & 4) != 0) {
            f10 = 1.0f;
        }
        return background(modifier, brush, shape, f10);
    }

    /* JADX INFO: renamed from: background-bw27NRU, reason: not valid java name */
    public static final Modifier m178backgroundbw27NRU(Modifier modifier, long j10, Shape shape) {
        return modifier.then(new BackgroundElement(j10, null, 1.0f, shape, InspectableValueKt.isDebugInspectorInfoEnabled() ? new BackgroundKt$backgroundbw27NRU$$inlined$debugInspectorInfo$1(j10, shape) : InspectableValueKt.getNoInspectorInfo(), 2, null));
    }

    /* JADX INFO: renamed from: background-bw27NRU$default, reason: not valid java name */
    public static /* synthetic */ Modifier m179backgroundbw27NRU$default(Modifier modifier, long j10, Shape shape, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        return m178backgroundbw27NRU(modifier, j10, shape);
    }
}
