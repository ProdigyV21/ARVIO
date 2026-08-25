package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.BasicText_androidKt;
import androidx.compose.foundation.text.selection.MultiWidgetSelectionDelegate;
import androidx.compose.foundation.text.selection.Selectable;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.c;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u000eJ\u0015\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0007\u0010\u001dR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/foundation/text/modifiers/SelectionController;", "Landroidx/compose/runtime/RememberObserver;", "", "selectableId", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "selectionRegistrar", "Landroidx/compose/ui/graphics/Color;", "backgroundSelectionColor", "Landroidx/compose/foundation/text/modifiers/StaticTextSelectionParams;", "params", "<init>", "(JLandroidx/compose/foundation/text/selection/SelectionRegistrar;JLandroidx/compose/foundation/text/modifiers/StaticTextSelectionParams;Lkotlin/jvm/internal/h;)V", "Lx6/t0;", "onRemembered", "()V", "onForgotten", "onAbandoned", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "updateTextLayout", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "updateGlobalPosition", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawScope", "draw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "J", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "Landroidx/compose/foundation/text/modifiers/StaticTextSelectionParams;", "Landroidx/compose/foundation/text/selection/Selectable;", "selectable", "Landroidx/compose/foundation/text/selection/Selectable;", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectionController implements RememberObserver {
    public static final int $stable = 8;
    private final long backgroundSelectionColor;
    private final Modifier modifier;
    private StaticTextSelectionParams params;
    private Selectable selectable;
    private final long selectableId;
    private final SelectionRegistrar selectionRegistrar;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.SelectionController$onRemembered$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<LayoutCoordinates> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final LayoutCoordinates invoke() {
            return SelectionController.this.params.getLayoutCoordinates();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.modifiers.SelectionController$onRemembered$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/text/TextLayoutResult;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<TextLayoutResult> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final TextLayoutResult invoke() {
            return SelectionController.this.params.getTextLayoutResult();
        }
    }

    public /* synthetic */ SelectionController(long j10, SelectionRegistrar selectionRegistrar, long j11, StaticTextSelectionParams staticTextSelectionParams, h hVar) {
        this(j10, selectionRegistrar, j11, staticTextSelectionParams);
    }

    public final void draw(DrawScope drawScope) {
        Selection selection = this.selectionRegistrar.getSubselections().get(Long.valueOf(this.selectableId));
        if (selection == null) {
            return;
        }
        int offset = !selection.getHandlesCrossed() ? selection.getStart().getOffset() : selection.getEnd().getOffset();
        int offset2 = !selection.getHandlesCrossed() ? selection.getEnd().getOffset() : selection.getStart().getOffset();
        if (offset == offset2) {
            return;
        }
        Selectable selectable = this.selectable;
        int lastVisibleOffset = selectable != null ? selectable.getLastVisibleOffset() : 0;
        if (offset > lastVisibleOffset) {
            offset = lastVisibleOffset;
        }
        if (offset2 > lastVisibleOffset) {
            offset2 = lastVisibleOffset;
        }
        Path pathForRange = this.params.getPathForRange(offset, offset2);
        if (pathForRange == null) {
            return;
        }
        if (!this.params.getShouldClip()) {
            c.G(drawScope, pathForRange, this.backgroundSelectionColor, 0.0f, null, null, 0, 60, null);
            return;
        }
        float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        int iM3461getIntersectrtfAjoo = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(0.0f, 0.0f, fM3305getWidthimpl, fM3302getHeightimpl, iM3461getIntersectrtfAjoo);
        c.G(drawScope, pathForRange, this.backgroundSelectionColor, 0.0f, null, null, 0, 60, null);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        Selectable selectable = this.selectable;
        if (selectable != null) {
            this.selectionRegistrar.unsubscribe(selectable);
            this.selectable = null;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        Selectable selectable = this.selectable;
        if (selectable != null) {
            this.selectionRegistrar.unsubscribe(selectable);
            this.selectable = null;
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        this.selectable = this.selectionRegistrar.subscribe(new MultiWidgetSelectionDelegate(this.selectableId, new AnonymousClass1(), new AnonymousClass2()));
    }

    public final void updateGlobalPosition(LayoutCoordinates coordinates) {
        this.params = StaticTextSelectionParams.copy$default(this.params, coordinates, null, 2, null);
        this.selectionRegistrar.notifyPositionChange(this.selectableId);
    }

    public final void updateTextLayout(TextLayoutResult textLayoutResult) {
        this.params = StaticTextSelectionParams.copy$default(this.params, null, textLayoutResult, 1, null);
    }

    private SelectionController(long j10, SelectionRegistrar selectionRegistrar, long j11, StaticTextSelectionParams staticTextSelectionParams) {
        this.selectableId = j10;
        this.selectionRegistrar = selectionRegistrar;
        this.backgroundSelectionColor = j11;
        this.params = staticTextSelectionParams;
        this.modifier = BasicText_androidKt.textPointerHoverIcon(SelectionControllerKt.makeSelectionModifier(selectionRegistrar, j10, new SelectionController$modifier$1(this)), selectionRegistrar);
    }

    public /* synthetic */ SelectionController(long j10, SelectionRegistrar selectionRegistrar, long j11, StaticTextSelectionParams staticTextSelectionParams, int i10, h hVar) {
        this(j10, selectionRegistrar, j11, (i10 & 8) != 0 ? StaticTextSelectionParams.INSTANCE.getEmpty() : staticTextSelectionParams, null);
    }
}
