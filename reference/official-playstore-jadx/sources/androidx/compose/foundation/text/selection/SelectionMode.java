package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ*\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\f*\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014j\u0002\b\u0015j\u0002\b\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionMode;", "", "(Ljava/lang/String;I)V", "compare", "", "position", "Landroidx/compose/ui/geometry/Offset;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "compare-3MmeM6k$foundation_release", "(JLandroidx/compose/ui/geometry/Rect;)I", "isSelected", "", TtmlNode.START, TtmlNode.END, "isSelected-2x9bVx0$foundation_release", "(Landroidx/compose/ui/geometry/Rect;JJ)Z", "containsInclusive", "offset", "containsInclusive-Uv8p0NA", "(Landroidx/compose/ui/geometry/Rect;J)Z", "Vertical", "Horizontal", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum SelectionMode {
    Vertical { // from class: androidx.compose.foundation.text.selection.SelectionMode.Vertical
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* JADX INFO: renamed from: compare-3MmeM6k$foundation_release */
        public int mo1021compare3MmeM6k$foundation_release(long position, Rect bounds) {
            if (SelectionManagerKt.m1014containsInclusiveUv8p0NA(bounds, position)) {
                return 0;
            }
            if (Offset.m3237getYimpl(position) < bounds.getTop()) {
                return -1;
            }
            return (Offset.m3236getXimpl(position) >= bounds.getLeft() || Offset.m3237getYimpl(position) >= bounds.getBottom()) ? 1 : -1;
        }
    },
    Horizontal { // from class: androidx.compose.foundation.text.selection.SelectionMode.Horizontal
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* JADX INFO: renamed from: compare-3MmeM6k$foundation_release */
        public int mo1021compare3MmeM6k$foundation_release(long position, Rect bounds) {
            if (SelectionManagerKt.m1014containsInclusiveUv8p0NA(bounds, position)) {
                return 0;
            }
            if (Offset.m3236getXimpl(position) < bounds.getLeft()) {
                return -1;
            }
            return (Offset.m3237getYimpl(position) >= bounds.getTop() || Offset.m3236getXimpl(position) >= bounds.getRight()) ? 1 : -1;
        }
    };

    /* synthetic */ SelectionMode(h hVar) {
        this();
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    private final boolean m1020containsInclusiveUv8p0NA(Rect rect, long j10) {
        float left = rect.getLeft();
        float right = rect.getRight();
        float fM3236getXimpl = Offset.m3236getXimpl(j10);
        if (left > fM3236getXimpl || fM3236getXimpl > right) {
            return false;
        }
        float top = rect.getTop();
        float bottom = rect.getBottom();
        float fM3237getYimpl = Offset.m3237getYimpl(j10);
        return top <= fM3237getYimpl && fM3237getYimpl <= bottom;
    }

    /* JADX INFO: renamed from: compare-3MmeM6k$foundation_release, reason: not valid java name */
    public abstract int mo1021compare3MmeM6k$foundation_release(long position, Rect bounds);

    /* JADX INFO: renamed from: isSelected-2x9bVx0$foundation_release, reason: not valid java name */
    public final boolean m1022isSelected2x9bVx0$foundation_release(Rect bounds, long start, long end) {
        if (m1020containsInclusiveUv8p0NA(bounds, start) || m1020containsInclusiveUv8p0NA(bounds, end)) {
            return true;
        }
        return (mo1021compare3MmeM6k$foundation_release(start, bounds) > 0) ^ (mo1021compare3MmeM6k$foundation_release(end, bounds) > 0);
    }
}
