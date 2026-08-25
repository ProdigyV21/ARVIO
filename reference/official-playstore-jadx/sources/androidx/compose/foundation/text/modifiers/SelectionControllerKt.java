package androidx.compose.foundation.text.modifiers;

import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.foundation.text.selection.SelectionRegistrar;
import androidx.compose.foundation.text.selection.SelectionRegistrarKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0002¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "", "selectableId", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutCoordinates", "Landroidx/compose/ui/Modifier;", "makeSelectionModifier", "(Landroidx/compose/foundation/text/selection/SelectionRegistrar;JLr7/a;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionControllerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier makeSelectionModifier(final SelectionRegistrar selectionRegistrar, final long j10, final a<? extends LayoutCoordinates> aVar) {
        TextDragObserver textDragObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$longPressDragObserver$1
            private long dragTotalDistance;
            private long lastPosition;

            /* JADX WARN: Multi-variable type inference failed */
            {
                Offset.Companion companion = Offset.INSTANCE;
                this.lastPosition = companion.m3252getZeroF1C5BW0();
                this.dragTotalDistance = companion.m3252getZeroF1C5BW0();
            }

            public final long getDragTotalDistance() {
                return this.dragTotalDistance;
            }

            public final long getLastPosition() {
                return this.lastPosition;
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j10)) {
                    selectionRegistrar.notifySelectionUpdateEnd();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo875onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo876onDragk4lQ0M(long delta) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                    long j11 = j10;
                    if (layoutCoordinates.isAttached() && SelectionRegistrarKt.hasSelection(selectionRegistrar2, j11)) {
                        long jM3241plusMKHz9U = Offset.m3241plusMKHz9U(this.dragTotalDistance, delta);
                        this.dragTotalDistance = jM3241plusMKHz9U;
                        long jM3241plusMKHz9U2 = Offset.m3241plusMKHz9U(this.lastPosition, jM3241plusMKHz9U);
                        if (selectionRegistrar2.mo1023notifySelectionUpdatenjBpvok(layoutCoordinates, jM3241plusMKHz9U2, this.lastPosition, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true)) {
                            this.lastPosition = jM3241plusMKHz9U2;
                            this.dragTotalDistance = Offset.INSTANCE.m3252getZeroF1C5BW0();
                        }
                    }
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo877onStartk4lQ0M(long startPoint) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates != null) {
                    SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                    if (!layoutCoordinates.isAttached()) {
                        return;
                    }
                    selectionRegistrar2.mo1024notifySelectionUpdateStartubNVwUQ(layoutCoordinates, startPoint, SelectionAdjustment.INSTANCE.getWord(), true);
                    this.lastPosition = startPoint;
                }
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j10)) {
                    this.dragTotalDistance = Offset.INSTANCE.m3252getZeroF1C5BW0();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                if (SelectionRegistrarKt.hasSelection(selectionRegistrar, j10)) {
                    selectionRegistrar.notifySelectionUpdateEnd();
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            public final void setDragTotalDistance(long j11) {
                this.dragTotalDistance = j11;
            }

            public final void setLastPosition(long j11) {
                this.lastPosition = j11;
            }
        };
        return SelectionGesturesKt.selectionGestureInput(Modifier.INSTANCE, new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.modifiers.SelectionControllerKt$makeSelectionModifier$mouseSelectionObserver$1
            private long lastPosition = Offset.INSTANCE.m3252getZeroF1C5BW0();

            public final long getLastPosition() {
                return this.lastPosition;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
            public boolean mo948onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates == null) {
                    return true;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j11 = j10;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, j11)) {
                    return false;
                }
                if (!selectionRegistrar2.mo1023notifySelectionUpdatenjBpvok(layoutCoordinates, dragPosition, this.lastPosition, false, adjustment, false)) {
                    return true;
                }
                this.lastPosition = dragPosition;
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
                selectionRegistrar.notifySelectionUpdateEnd();
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
            public boolean mo949onExtendk4lQ0M(long downPosition) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j11 = j10;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                if (selectionRegistrar2.mo1023notifySelectionUpdatenjBpvok(layoutCoordinates, downPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false)) {
                    this.lastPosition = downPosition;
                }
                return SelectionRegistrarKt.hasSelection(selectionRegistrar2, j11);
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
            public boolean mo950onExtendDragk4lQ0M(long dragPosition) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates == null) {
                    return true;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j11 = j10;
                if (!layoutCoordinates.isAttached() || !SelectionRegistrarKt.hasSelection(selectionRegistrar2, j11)) {
                    return false;
                }
                if (!selectionRegistrar2.mo1023notifySelectionUpdatenjBpvok(layoutCoordinates, dragPosition, this.lastPosition, false, SelectionAdjustment.INSTANCE.getNone(), false)) {
                    return true;
                }
                this.lastPosition = dragPosition;
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k, reason: not valid java name */
            public boolean mo951onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) aVar.invoke();
                if (layoutCoordinates == null) {
                    return false;
                }
                SelectionRegistrar selectionRegistrar2 = selectionRegistrar;
                long j11 = j10;
                if (!layoutCoordinates.isAttached()) {
                    return false;
                }
                selectionRegistrar2.mo1024notifySelectionUpdateStartubNVwUQ(layoutCoordinates, downPosition, adjustment, false);
                this.lastPosition = downPosition;
                return SelectionRegistrarKt.hasSelection(selectionRegistrar2, j11);
            }

            public final void setLastPosition(long j11) {
                this.lastPosition = j11;
            }
        }, textDragObserver);
    }
}
