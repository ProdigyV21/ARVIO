package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import java.util.List;
import kotlin.Metadata;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0013\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R5\u0010\b\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR5\u0010\f\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000bR5\u0010\u000e\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000bR5\u0010\u0010\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000bR5\u0010\u0012\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0013\u0010\u000bR5\u0010\u0014\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0015\u0010\u000bR5\u0010\u0016\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\t\u001a\u0004\b\u0017\u0010\u000bR5\u0010\u0018\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0019\u0010\u000b¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/layout/IntrinsicMeasureBlocks;", "", "<init>", "()V", "Lkotlin/Function3;", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "HorizontalMinWidth", "Lr7/q;", "getHorizontalMinWidth", "()Lr7/q;", "VerticalMinWidth", "getVerticalMinWidth", "HorizontalMinHeight", "getHorizontalMinHeight", "VerticalMinHeight", "getVerticalMinHeight", "HorizontalMaxWidth", "getHorizontalMaxWidth", "VerticalMaxWidth", "getVerticalMaxWidth", "HorizontalMaxHeight", "getHorizontalMaxHeight", "VerticalMaxHeight", "getVerticalMaxHeight", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class IntrinsicMeasureBlocks {
    public static final IntrinsicMeasureBlocks INSTANCE = new IntrinsicMeasureBlocks();
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> HorizontalMinWidth = IntrinsicMeasureBlocks$HorizontalMinWidth$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> VerticalMinWidth = IntrinsicMeasureBlocks$VerticalMinWidth$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> HorizontalMinHeight = IntrinsicMeasureBlocks$HorizontalMinHeight$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> VerticalMinHeight = IntrinsicMeasureBlocks$VerticalMinHeight$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> HorizontalMaxWidth = IntrinsicMeasureBlocks$HorizontalMaxWidth$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> VerticalMaxWidth = IntrinsicMeasureBlocks$VerticalMaxWidth$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> HorizontalMaxHeight = IntrinsicMeasureBlocks$HorizontalMaxHeight$1.INSTANCE;
    private static final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> VerticalMaxHeight = IntrinsicMeasureBlocks$VerticalMaxHeight$1.INSTANCE;

    private IntrinsicMeasureBlocks() {
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getHorizontalMaxHeight() {
        return HorizontalMaxHeight;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getHorizontalMaxWidth() {
        return HorizontalMaxWidth;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getHorizontalMinHeight() {
        return HorizontalMinHeight;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getHorizontalMinWidth() {
        return HorizontalMinWidth;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getVerticalMaxHeight() {
        return VerticalMaxHeight;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getVerticalMaxWidth() {
        return VerticalMaxWidth;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getVerticalMinHeight() {
        return VerticalMinHeight;
    }

    public final q<List<? extends IntrinsicMeasurable>, Integer, Integer, Integer> getVerticalMinWidth() {
        return VerticalMinWidth;
    }
}
