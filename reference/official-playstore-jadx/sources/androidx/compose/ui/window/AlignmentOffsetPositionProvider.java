package androidx.compose.ui.window;

import a0.c;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J2\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/window/AlignmentOffsetPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/unit/IntOffset;", "offset", "<init>", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/unit/IntRect;", "anchorBounds", "Landroidx/compose/ui/unit/IntSize;", "windowSize", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "calculatePosition", "Landroidx/compose/ui/Alignment;", "getAlignment", "()Landroidx/compose/ui/Alignment;", "J", "getOffset-nOcc-ac", "()J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AlignmentOffsetPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final Alignment alignment;
    private final long offset;

    public /* synthetic */ AlignmentOffsetPositionProvider(Alignment alignment, long j10, h hVar) {
        this(alignment, j10);
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo959calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        Alignment alignment = this.alignment;
        IntSize.Companion companion = IntSize.INSTANCE;
        long jMo3109alignKFBX0sM = alignment.mo3109alignKFBX0sM(companion.m5849getZeroYbymL2g(), anchorBounds.m5828getSizeYbymL2g(), layoutDirection);
        long jMo3109alignKFBX0sM2 = this.alignment.mo3109alignKFBX0sM(companion.m5849getZeroYbymL2g(), popupContentSize, layoutDirection);
        long jIntOffset = IntOffsetKt.IntOffset(-IntOffset.m5802getXimpl(jMo3109alignKFBX0sM2), -IntOffset.m5803getYimpl(jMo3109alignKFBX0sM2));
        long jIntOffset2 = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(this.offset) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1), IntOffset.m5803getYimpl(this.offset));
        long jM5830getTopLeftnOccac = anchorBounds.m5830getTopLeftnOccac();
        long jC = c.c(IntOffset.m5803getYimpl(jM5830getTopLeftnOccac), IntOffset.m5802getXimpl(jMo3109alignKFBX0sM) + IntOffset.m5802getXimpl(jM5830getTopLeftnOccac), jMo3109alignKFBX0sM);
        long jC2 = c.c(IntOffset.m5803getYimpl(jC), IntOffset.m5802getXimpl(jIntOffset) + IntOffset.m5802getXimpl(jC), jIntOffset);
        return c.c(IntOffset.m5803getYimpl(jC2), IntOffset.m5802getXimpl(jIntOffset2) + IntOffset.m5802getXimpl(jC2), jIntOffset2);
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getOffset() {
        return this.offset;
    }

    private AlignmentOffsetPositionProvider(Alignment alignment, long j10) {
        this.alignment = alignment;
        this.offset = j10;
    }
}
