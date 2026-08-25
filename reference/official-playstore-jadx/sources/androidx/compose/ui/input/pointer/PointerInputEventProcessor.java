package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "", "Landroidx/compose/ui/node/LayoutNode;", "root", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "positionCalculator", "", "isInBounds", "Landroidx/compose/ui/input/pointer/ProcessResult;", "process-BIzXfog", "(Landroidx/compose/ui/input/pointer/PointerInputEvent;Landroidx/compose/ui/input/pointer/PositionCalculator;Z)I", "process", "Lx6/t0;", "processCancel", "()V", "Landroidx/compose/ui/node/LayoutNode;", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/input/pointer/HitPathTracker;", "hitPathTracker", "Landroidx/compose/ui/input/pointer/HitPathTracker;", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "pointerInputChangeEventProducer", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "Landroidx/compose/ui/node/HitTestResult;", "hitResult", "Landroidx/compose/ui/node/HitTestResult;", "isProcessing", "Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerInputEventProcessor {
    public static final int $stable = 8;
    private final HitPathTracker hitPathTracker;
    private boolean isProcessing;
    private final LayoutNode root;
    private final PointerInputChangeEventProducer pointerInputChangeEventProducer = new PointerInputChangeEventProducer();
    private final HitTestResult hitResult = new HitTestResult();

    public PointerInputEventProcessor(LayoutNode layoutNode) {
        this.root = layoutNode;
        this.hitPathTracker = new HitPathTracker(layoutNode.getCoordinates());
    }

    /* JADX INFO: renamed from: process-BIzXfog$default, reason: not valid java name */
    public static /* synthetic */ int m4539processBIzXfog$default(PointerInputEventProcessor pointerInputEventProcessor, PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        return pointerInputEventProcessor.m4540processBIzXfog(pointerInputEvent, positionCalculator, z);
    }

    public final LayoutNode getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: process-BIzXfog, reason: not valid java name */
    public final int m4540processBIzXfog(PointerInputEvent pointerEvent, PositionCalculator positionCalculator, boolean isInBounds) {
        boolean z;
        if (this.isProcessing) {
            return PointerInputEventProcessorKt.ProcessResult(false, false);
        }
        boolean z5 = true;
        try {
            this.isProcessing = true;
            InternalPointerEvent internalPointerEventProduce = this.pointerInputChangeEventProducer.produce(pointerEvent, positionCalculator);
            int iH = internalPointerEventProduce.getChanges().h();
            for (int i10 = 0; i10 < iH; i10++) {
                PointerInputChange pointerInputChange = (PointerInputChange) internalPointerEventProduce.getChanges().i(i10);
                if (!pointerInputChange.getPressed() && !pointerInputChange.getPreviousPressed()) {
                }
                z = false;
                break;
            }
            z = true;
            int iH2 = internalPointerEventProduce.getChanges().h();
            for (int i11 = 0; i11 < iH2; i11++) {
                PointerInputChange pointerInputChange2 = (PointerInputChange) internalPointerEventProduce.getChanges().i(i11);
                if (z || PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange2)) {
                    LayoutNode.m4748hitTestM_7yMNQ$ui_release$default(this.root, pointerInputChange2.getPosition(), this.hitResult, PointerType.m4577equalsimpl0(pointerInputChange2.getType(), PointerType.INSTANCE.m4584getTouchT8wyACA()), false, 8, null);
                    if (!this.hitResult.isEmpty()) {
                        this.hitPathTracker.m4446addHitPathKNwqfcY(pointerInputChange2.getId(), this.hitResult);
                        this.hitResult.clear();
                    }
                }
            }
            this.hitPathTracker.removeDetachedPointerInputFilters();
            boolean zDispatchChanges = this.hitPathTracker.dispatchChanges(internalPointerEventProduce, isInBounds);
            if (internalPointerEventProduce.getSuppressMovementConsumption()) {
                z5 = false;
            } else {
                int iH3 = internalPointerEventProduce.getChanges().h();
                for (int i12 = 0; i12 < iH3; i12++) {
                    PointerInputChange pointerInputChange3 = (PointerInputChange) internalPointerEventProduce.getChanges().i(i12);
                    if (PointerEventKt.positionChangedIgnoreConsumed(pointerInputChange3) && pointerInputChange3.isConsumed()) {
                        break;
                    }
                }
                z5 = false;
            }
            int iProcessResult = PointerInputEventProcessorKt.ProcessResult(zDispatchChanges, z5);
            this.isProcessing = false;
            return iProcessResult;
        } catch (Throwable th) {
            this.isProcessing = false;
            throw th;
        }
    }

    public final void processCancel() {
        if (this.isProcessing) {
            return;
        }
        this.pointerInputChangeEventProducer.clear();
        this.hitPathTracker.processCancel();
    }
}
