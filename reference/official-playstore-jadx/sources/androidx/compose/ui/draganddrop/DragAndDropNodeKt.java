package androidx.compose.ui.draganddrop;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.IntSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a8\u0010\u0001\u001a\u00020\u00002!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0001\u0010\f\u001a\u001b\u0010\u000e\u001a\u00020\r*\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001e\u0010\u0014\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "DragAndDropModifierNode", "()Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "Lx6/y;", ContentDisposition.Parameters.Name, "event", "", "shouldStartDragAndDrop", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "target", "(Lr7/l;Landroidx/compose/ui/draganddrop/DragAndDropTarget;)Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;", "Lx6/t0;", "dispatchEntered", "(Landroidx/compose/ui/draganddrop/DragAndDropTarget;Landroidx/compose/ui/draganddrop/DragAndDropEvent;)V", "Landroidx/compose/ui/geometry/Offset;", "position", "contains-Uv8p0NA", "(Landroidx/compose/ui/draganddrop/DragAndDropModifierNode;J)Z", "contains", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DragAndDropNodeKt {

    /* JADX INFO: renamed from: androidx.compose.ui.draganddrop.DragAndDropNodeKt$DragAndDropModifierNode$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "it", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<DragAndDropEvent, DragAndDropTarget> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final DragAndDropTarget invoke(DragAndDropEvent dragAndDropEvent) {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.draganddrop.DragAndDropNodeKt$DragAndDropModifierNode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/draganddrop/DragAndDropTarget;", "startEvent", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<DragAndDropEvent, DragAndDropTarget> {
        final /* synthetic */ l<DragAndDropEvent, Boolean> $shouldStartDragAndDrop;
        final /* synthetic */ DragAndDropTarget $target;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super DragAndDropEvent, Boolean> lVar, DragAndDropTarget dragAndDropTarget) {
            super(1);
            this.$shouldStartDragAndDrop = lVar;
            this.$target = dragAndDropTarget;
        }

        @Override // r7.l
        public final DragAndDropTarget invoke(DragAndDropEvent dragAndDropEvent) {
            if (((Boolean) this.$shouldStartDragAndDrop.invoke(dragAndDropEvent)).booleanValue()) {
                return this.$target;
            }
            return null;
        }
    }

    public static final DragAndDropModifierNode DragAndDropModifierNode() {
        return new DragAndDropNode(AnonymousClass1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: contains-Uv8p0NA, reason: not valid java name */
    public static final boolean m3124containsUv8p0NA(DragAndDropModifierNode dragAndDropModifierNode, long j10) {
        if (!dragAndDropModifierNode.getNode().getIsAttached()) {
            return false;
        }
        LayoutCoordinates coordinates = DelegatableNodeKt.requireLayoutNode(dragAndDropModifierNode).getCoordinates();
        if (!coordinates.isAttached()) {
            return false;
        }
        long jMo4638getSizeYbymL2g = coordinates.mo4638getSizeYbymL2g();
        int iM5844getWidthimpl = IntSize.m5844getWidthimpl(jMo4638getSizeYbymL2g);
        int iM5843getHeightimpl = IntSize.m5843getHeightimpl(jMo4638getSizeYbymL2g);
        long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(coordinates);
        float fM3226component1impl = Offset.m3226component1impl(jPositionInRoot);
        float fM3227component2impl = Offset.m3227component2impl(jPositionInRoot);
        float f10 = iM5844getWidthimpl + fM3226component1impl;
        float f11 = iM5843getHeightimpl + fM3227component2impl;
        float fM3236getXimpl = Offset.m3236getXimpl(j10);
        if (fM3226component1impl <= fM3236getXimpl && fM3236getXimpl <= f10) {
            float fM3237getYimpl = Offset.m3237getYimpl(j10);
            if (fM3227component2impl <= fM3237getYimpl && fM3237getYimpl <= f11) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchEntered(DragAndDropTarget dragAndDropTarget, DragAndDropEvent dragAndDropEvent) {
        dragAndDropTarget.onEntered(dragAndDropEvent);
        dragAndDropTarget.onMoved(dragAndDropEvent);
    }

    public static final DragAndDropModifierNode DragAndDropModifierNode(l<? super DragAndDropEvent, Boolean> lVar, DragAndDropTarget dragAndDropTarget) {
        return new DragAndDropNode(new AnonymousClass2(lVar, dragAndDropTarget));
    }
}
