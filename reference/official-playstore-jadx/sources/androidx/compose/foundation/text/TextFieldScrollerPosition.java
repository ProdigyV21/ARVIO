package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.ListSaverKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b \b\u0001\u0018\u0000 62\u00020\u0001:\u00016B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ-\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR+\u0010#\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010'\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00048F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u001e\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u0016\u0010(\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R(\u0010*\u001a\u00020\u00178\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R+\u0010\t\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00028F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00067"}, d2 = {"Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "", "Landroidx/compose/foundation/gestures/Orientation;", "initialOrientation", "", "initial", "<init>", "(Landroidx/compose/foundation/gestures/Orientation;F)V", "()V", "orientation", "Landroidx/compose/ui/geometry/Rect;", "cursorRect", "", "containerSize", "textFieldSize", "Lx6/t0;", "update", "(Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/geometry/Rect;II)V", "cursorStart", "cursorEnd", "coerceOffset$foundation_release", "(FFI)V", "coerceOffset", "Landroidx/compose/ui/text/TextRange;", "selection", "getOffsetToFollow-5zc-tL8", "(J)I", "getOffsetToFollow", "<set-?>", "offset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getOffset", "()F", "setOffset", "(F)V", "offset", "maximum$delegate", "getMaximum", "setMaximum", "maximum", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "previousSelection", "J", "getPreviousSelection-d9O1mEE", "()J", "setPreviousSelection-5zc-tL8", "(J)V", "orientation$delegate", "Landroidx/compose/runtime/MutableState;", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldScrollerPosition {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<TextFieldScrollerPosition, Object> Saver = ListSaverKt.listSaver(TextFieldScrollerPosition$Companion$Saver$1.INSTANCE, TextFieldScrollerPosition$Companion$Saver$2.INSTANCE);

    /* JADX INFO: renamed from: maximum$delegate, reason: from kotlin metadata */
    private final MutableFloatState maximum;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;

    /* JADX INFO: renamed from: orientation$delegate, reason: from kotlin metadata */
    private final MutableState orientation;
    private Rect previousCursorRect;
    private long previousSelection;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/TextFieldScrollerPosition$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Saver<TextFieldScrollerPosition, Object> getSaver() {
            return TextFieldScrollerPosition.Saver;
        }

        private Companion() {
        }
    }

    public TextFieldScrollerPosition(Orientation orientation, float f10) {
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(f10);
        this.maximum = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.previousCursorRect = Rect.INSTANCE.getZero();
        this.previousSelection = TextRange.INSTANCE.m5197getZerod9O1mEE();
        this.orientation = SnapshotStateKt.mutableStateOf(orientation, SnapshotStateKt.structuralEqualityPolicy());
    }

    private final void setMaximum(float f10) {
        this.maximum.setFloatValue(f10);
    }

    public final void coerceOffset$foundation_release(float cursorStart, float cursorEnd, int containerSize) {
        float offset = getOffset();
        float f10 = containerSize;
        float f11 = offset + f10;
        setOffset(getOffset() + ((cursorEnd <= f11 && (cursorStart >= offset || cursorEnd - cursorStart <= f10)) ? (cursorStart >= offset || cursorEnd - cursorStart > f10) ? 0.0f : cursorStart - offset : cursorEnd - f11));
    }

    public final float getMaximum() {
        return this.maximum.getFloatValue();
    }

    public final float getOffset() {
        return this.offset.getFloatValue();
    }

    /* JADX INFO: renamed from: getOffsetToFollow-5zc-tL8, reason: not valid java name */
    public final int m896getOffsetToFollow5zctL8(long selection) {
        return TextRange.m5192getStartimpl(selection) != TextRange.m5192getStartimpl(this.previousSelection) ? TextRange.m5192getStartimpl(selection) : TextRange.m5187getEndimpl(selection) != TextRange.m5187getEndimpl(this.previousSelection) ? TextRange.m5187getEndimpl(selection) : TextRange.m5190getMinimpl(selection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Orientation getOrientation() {
        return (Orientation) this.orientation.getValue();
    }

    /* JADX INFO: renamed from: getPreviousSelection-d9O1mEE, reason: not valid java name and from getter */
    public final long getPreviousSelection() {
        return this.previousSelection;
    }

    public final void setOffset(float f10) {
        this.offset.setFloatValue(f10);
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation.setValue(orientation);
    }

    /* JADX INFO: renamed from: setPreviousSelection-5zc-tL8, reason: not valid java name */
    public final void m898setPreviousSelection5zctL8(long j10) {
        this.previousSelection = j10;
    }

    public final void update(Orientation orientation, Rect cursorRect, int containerSize, int textFieldSize) {
        float f10 = textFieldSize - containerSize;
        setMaximum(f10);
        if (cursorRect.getLeft() != this.previousCursorRect.getLeft() || cursorRect.getTop() != this.previousCursorRect.getTop()) {
            boolean z = orientation == Orientation.Vertical;
            coerceOffset$foundation_release(z ? cursorRect.getTop() : cursorRect.getLeft(), z ? cursorRect.getBottom() : cursorRect.getRight(), containerSize);
            this.previousCursorRect = cursorRect;
        }
        setOffset(d.m(getOffset(), 0.0f, f10));
    }

    public /* synthetic */ TextFieldScrollerPosition(Orientation orientation, float f10, int i10, h hVar) {
        this(orientation, (i10 & 2) != 0 ? 0.0f : f10);
    }

    public TextFieldScrollerPosition() {
        this(Orientation.Vertical, 0.0f, 2, null);
    }
}
