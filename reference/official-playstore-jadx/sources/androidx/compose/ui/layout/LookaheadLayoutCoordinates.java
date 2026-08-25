package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\tJ\u001a\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\tJ\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\"\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020 H\u0096\u0002¢\u0006\u0004\b#\u0010$R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010*\u001a\u00020\u00068BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010.\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u00101\u001a\u00020/8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b0\u0010)R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020 028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0016\u00108\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u0016\u0010:\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u00107R\u0014\u0010;\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006="}, d2 = {"Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "<init>", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "Landroidx/compose/ui/geometry/Offset;", "relativeToWindow", "windowToLocal-MK-Hz9U", "(J)J", "windowToLocal", "relativeToLocal", "localToWindow-MK-Hz9U", "localToWindow", "localToRoot-MK-Hz9U", "localToRoot", "sourceCoordinates", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "localPositionOf", "", "clipBounds", "Landroidx/compose/ui/geometry/Rect;", "localBoundingBoxOf", "(Landroidx/compose/ui/layout/LayoutCoordinates;Z)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "Lx6/t0;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformFrom", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLine", "", "get", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "Landroidx/compose/ui/node/LookaheadDelegate;", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "getLookaheadOffset-F1C5BW0", "()J", "lookaheadOffset", "Landroidx/compose/ui/node/NodeCoordinator;", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "coordinator", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", ContentDisposition.Parameters.Size, "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "providedAlignmentLines", "getParentLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "getParentCoordinates", "parentCoordinates", "isAttached", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LookaheadLayoutCoordinates implements LayoutCoordinates {
    public static final int $stable = 0;
    private final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinates(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    /* JADX INFO: renamed from: getLookaheadOffset-F1C5BW0, reason: not valid java name */
    private final long m4648getLookaheadOffsetF1C5BW0() {
        LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        LayoutCoordinates coordinates = rootLookaheadDelegate.getCoordinates();
        Offset.Companion companion = Offset.INSTANCE;
        return Offset.m3240minusMKHz9U(mo4639localPositionOfR5De75A(coordinates, companion.m3252getZeroF1C5BW0()), getCoordinator().mo4639localPositionOfR5De75A(rootLookaheadDelegate.getCoordinator(), companion.m3252getZeroF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public int get(AlignmentLine alignmentLine) {
        return this.lookaheadDelegate.get(alignmentLine);
    }

    public final NodeCoordinator getCoordinator() {
        return this.lookaheadDelegate.getCoordinator();
    }

    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            throw new IllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator wrappedBy = getCoordinator().getWrappedBy();
        if (wrappedBy == null || (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            throw new IllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator wrappedBy = getCoordinator().getLayoutNode().getOuterCoordinator$ui_release().getWrappedBy();
        if (wrappedBy == null || (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        return getCoordinator().getProvidedAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public long mo4638getSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        return IntSizeKt.IntSize(lookaheadDelegate.getWidth(), lookaheadDelegate.getHeight());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getCoordinator().isAttached();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        return getCoordinator().localBoundingBoxOf(sourceCoordinates, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo4639localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        if (!(sourceCoordinates instanceof LookaheadLayoutCoordinates)) {
            LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
            return Offset.m3241plusMKHz9U(mo4639localPositionOfR5De75A(rootLookaheadDelegate.getLookaheadLayoutCoordinates(), relativeToSource), rootLookaheadDelegate.getCoordinator().getCoordinates().mo4639localPositionOfR5De75A(sourceCoordinates, Offset.INSTANCE.m3252getZeroF1C5BW0()));
        }
        LookaheadDelegate lookaheadDelegate = ((LookaheadLayoutCoordinates) sourceCoordinates).lookaheadDelegate;
        lookaheadDelegate.getCoordinator().onCoordinatesUsed$ui_release();
        LookaheadDelegate lookaheadDelegate2 = getCoordinator().findCommonAncestor$ui_release(lookaheadDelegate.getCoordinator()).getLookaheadDelegate();
        if (lookaheadDelegate2 != null) {
            long jM4789positionInBjo55l4$ui_release = lookaheadDelegate.m4789positionInBjo55l4$ui_release(lookaheadDelegate2);
            long jIntOffset = IntOffsetKt.IntOffset(t7.a.M(Offset.m3236getXimpl(relativeToSource)), t7.a.M(Offset.m3237getYimpl(relativeToSource)));
            long jC = a0.c.c(IntOffset.m5803getYimpl(jM4789positionInBjo55l4$ui_release), IntOffset.m5802getXimpl(jIntOffset) + IntOffset.m5802getXimpl(jM4789positionInBjo55l4$ui_release), jIntOffset);
            long jM4789positionInBjo55l4$ui_release2 = this.lookaheadDelegate.m4789positionInBjo55l4$ui_release(lookaheadDelegate2);
            long jIntOffset2 = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jC) - IntOffset.m5802getXimpl(jM4789positionInBjo55l4$ui_release2), IntOffset.m5803getYimpl(jC) - IntOffset.m5803getYimpl(jM4789positionInBjo55l4$ui_release2));
            return OffsetKt.Offset(IntOffset.m5802getXimpl(jIntOffset2), IntOffset.m5803getYimpl(jIntOffset2));
        }
        LookaheadDelegate rootLookaheadDelegate2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
        long jM4789positionInBjo55l4$ui_release3 = lookaheadDelegate.m4789positionInBjo55l4$ui_release(rootLookaheadDelegate2);
        long position = rootLookaheadDelegate2.getPosition();
        long jC2 = a0.c.c(IntOffset.m5803getYimpl(jM4789positionInBjo55l4$ui_release3), IntOffset.m5802getXimpl(position) + IntOffset.m5802getXimpl(jM4789positionInBjo55l4$ui_release3), position);
        long jIntOffset3 = IntOffsetKt.IntOffset(t7.a.M(Offset.m3236getXimpl(relativeToSource)), t7.a.M(Offset.m3237getYimpl(relativeToSource)));
        long jC3 = a0.c.c(IntOffset.m5803getYimpl(jC2), IntOffset.m5802getXimpl(jIntOffset3) + IntOffset.m5802getXimpl(jC2), jIntOffset3);
        LookaheadDelegate lookaheadDelegate3 = this.lookaheadDelegate;
        long jM4789positionInBjo55l4$ui_release4 = lookaheadDelegate3.m4789positionInBjo55l4$ui_release(LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate3));
        long position2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate3).getPosition();
        long jC4 = a0.c.c(IntOffset.m5803getYimpl(jM4789positionInBjo55l4$ui_release4), IntOffset.m5802getXimpl(position2) + IntOffset.m5802getXimpl(jM4789positionInBjo55l4$ui_release4), position2);
        long jIntOffset4 = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jC3) - IntOffset.m5802getXimpl(jC4), IntOffset.m5803getYimpl(jC3) - IntOffset.m5803getYimpl(jC4));
        return LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate).getCoordinator().getWrappedBy().mo4639localPositionOfR5De75A(rootLookaheadDelegate2.getCoordinator().getWrappedBy(), OffsetKt.Offset(IntOffset.m5802getXimpl(jIntOffset4), IntOffset.m5803getYimpl(jIntOffset4)));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo4640localToRootMKHz9U(long relativeToLocal) {
        return getCoordinator().mo4640localToRootMKHz9U(Offset.m3241plusMKHz9U(relativeToLocal, m4648getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo4641localToWindowMKHz9U(long relativeToLocal) {
        return getCoordinator().mo4641localToWindowMKHz9U(Offset.m3241plusMKHz9U(relativeToLocal, m4648getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo4642transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        getCoordinator().mo4642transformFromEL8BTi8(sourceCoordinates, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo4643windowToLocalMKHz9U(long relativeToWindow) {
        return Offset.m3241plusMKHz9U(getCoordinator().mo4643windowToLocalMKHz9U(relativeToWindow), m4648getLookaheadOffsetF1C5BW0());
    }
}
