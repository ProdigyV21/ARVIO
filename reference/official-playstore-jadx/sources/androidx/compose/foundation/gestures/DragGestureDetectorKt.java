package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aY\u0010\u000e\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a3\u0010\u0014\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a!\u0010\u0017\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0082\u0001\u0010\u001e\u001a\u00020\n*\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u000f2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001a26\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\n0\u0003H\u0086@¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0082\u0001\u0010 \u001a\u00020\n*\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u000f2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001a26\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\n0\u0003H\u0086@¢\u0006\u0004\b \u0010\u001f\u001aY\u0010#\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\"\u0010\r\u001aa\u0010(\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010%\u001a\u00020$26\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a3\u0010*\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b)\u0010\u0013\u001a!\u0010,\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b+\u0010\u0016\u001a\u0082\u0001\u0010.\u001a\u00020\n*\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u000f2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001a26\u0010-\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\n0\u0003H\u0086@¢\u0006\u0004\b.\u0010\u001f\u001aY\u00100\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000126\u0010\u000b\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b/\u0010\r\u001aa\u00103\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010%\u001a\u00020$26\u00101\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003H\u0080@ø\u0001\u0000¢\u0006\u0004\b2\u0010'\u001a3\u00105\u001a\u00020\u0011*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0013\u001a!\u00107\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\b6\u0010\u0016\u001a\u0082\u0001\u00109\u001a\u00020\n*\u00020\u00182\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u000f2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001a2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u001a26\u00108\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\n0\u0003H\u0086@¢\u0006\u0004\b9\u0010\u001f\u001a]\u0010\u0014\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\u000f2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u000f2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u000fH\u0080Hø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a5\u0010?\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u000fH\u0082Hø\u0001\u0000¢\u0006\u0004\b>\u0010\u0013\u001aK\u0010D\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010%\u001a\u00020$2\u0006\u0010A\u001a\u00020@2\u0018\u00101\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n0\u0003H\u0080Hø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a\u0013\u0010F\u001a\u00020@*\u00020EH\u0000¢\u0006\u0004\bF\u0010G\u001a!\u0010I\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0004\bH\u0010\u0016\u001a\u001e\u0010M\u001a\u00020\u0011*\u00020J2\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010L\u001a\u001e\u0010Q\u001a\u00020!*\u00020N2\u0006\u0010%\u001a\u00020$H\u0000ø\u0001\u0000¢\u0006\u0004\bO\u0010P\"\u001a\u0010R\u001a\u00020@8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u001a\u0010V\u001a\u00020@8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bV\u0010S\u001a\u0004\bW\u0010U\"\u001a\u0010X\u001a\u00020@8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bX\u0010S\u001a\u0004\bY\u0010U\"\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\\"\u0014\u0010]\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010\\\"\u0014\u0010^\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010\\\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006_"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/input/pointer/PointerId;", "pointerId", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Lx6/y;", ContentDisposition.Parameters.Name, "change", "Landroidx/compose/ui/geometry/Offset;", "overSlop", "Lx6/t0;", "onTouchSlopReached", "awaitTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLr7/p;Ld7/d;)Ljava/lang/Object;", "awaitTouchSlopOrCancellation", "Lkotlin/Function1;", "onDrag", "", "drag-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLr7/l;Ld7/d;)Ljava/lang/Object;", "drag", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLd7/d;)Ljava/lang/Object;", "awaitDragOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "Lkotlin/Function0;", "onDragEnd", "onDragCancel", "dragAmount", "detectDragGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/l;Lr7/a;Lr7/a;Lr7/p;Ld7/d;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "", "awaitVerticalTouchSlopOrCancellation-jO51t88", "awaitVerticalTouchSlopOrCancellation", "Landroidx/compose/ui/input/pointer/PointerType;", "pointerType", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILr7/p;Ld7/d;)Ljava/lang/Object;", "awaitVerticalPointerSlopOrCancellation", "verticalDrag-jO51t88", "verticalDrag", "awaitVerticalDragOrCancellation-rnUCldI", "awaitVerticalDragOrCancellation", "onVerticalDrag", "detectVerticalDragGestures", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "awaitHorizontalTouchSlopOrCancellation", "onPointerSlopReached", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "awaitHorizontalPointerSlopOrCancellation", "horizontalDrag-jO51t88", "horizontalDrag", "awaitHorizontalDragOrCancellation-rnUCldI", "awaitHorizontalDragOrCancellation", "onHorizontalDrag", "detectHorizontalDragGestures", "hasDragged", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLr7/l;Lr7/l;Lr7/l;Ld7/d;)Ljava/lang/Object;", "awaitDragOrUp-jO51t88", "awaitDragOrUp", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "pointerDirectionConfig", "awaitPointerSlopOrCancellation-pn7EDYM", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/PointerDirectionConfig;Lr7/p;Ld7/d;)Ljava/lang/Object;", "awaitPointerSlopOrCancellation", "Landroidx/compose/foundation/gestures/Orientation;", "toPointerDirectionConfig", "(Landroidx/compose/foundation/gestures/Orientation;)Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "awaitLongPressOrCancellation-rnUCldI", "awaitLongPressOrCancellation", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "isPointerUp", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "pointerSlop", "HorizontalPointerDirectionConfig", "Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "getHorizontalPointerDirectionConfig", "()Landroidx/compose/foundation/gestures/PointerDirectionConfig;", "VerticalPointerDirectionConfig", "getVerticalPointerDirectionConfig", "BidirectionalPointerDirectionConfig", "getBidirectionalPointerDirectionConfig", "Landroidx/compose/ui/unit/Dp;", "mouseSlop", "F", "defaultTouchSlop", "mouseToTouchSlopRatio", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DragGestureDetectorKt {
    private static final float defaultTouchSlop;
    private static final float mouseSlop;
    private static final float mouseToTouchSlopRatio;
    private static final PointerDirectionConfig HorizontalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$HorizontalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M */
        public float mo326calculateDeltaChangek4lQ0M(long offset) {
            return Math.abs(Offset.m3236getXimpl(offset));
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk */
        public long mo327calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return OffsetKt.Offset(Offset.m3236getXimpl(totalPositionChange) - (Math.signum(Offset.m3236getXimpl(totalPositionChange)) * touchSlop), Offset.m3237getYimpl(totalPositionChange));
        }
    };
    private static final PointerDirectionConfig VerticalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$VerticalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M */
        public float mo326calculateDeltaChangek4lQ0M(long offset) {
            return Math.abs(Offset.m3237getYimpl(offset));
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk */
        public long mo327calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return OffsetKt.Offset(Offset.m3236getXimpl(totalPositionChange), Offset.m3237getYimpl(totalPositionChange) - (Math.signum(Offset.m3237getYimpl(totalPositionChange)) * touchSlop));
        }
    };
    private static final PointerDirectionConfig BidirectionalPointerDirectionConfig = new PointerDirectionConfig() { // from class: androidx.compose.foundation.gestures.DragGestureDetectorKt$BidirectionalPointerDirectionConfig$1
        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculateDeltaChange-k-4lQ0M, reason: not valid java name */
        public float mo326calculateDeltaChangek4lQ0M(long offset) {
            return Offset.m3234getDistanceimpl(offset);
        }

        @Override // androidx.compose.foundation.gestures.PointerDirectionConfig
        /* JADX INFO: renamed from: calculatePostSlopOffset-8S9VItk, reason: not valid java name */
        public long mo327calculatePostSlopOffset8S9VItk(long totalPositionChange, float touchSlop) {
            return Offset.m3240minusMKHz9U(totalPositionChange, Offset.m3243timestuRUvjQ(Offset.m3231divtuRUvjQ(totalPositionChange, mo326calculateDeltaChangek4lQ0M(totalPositionChange)), touchSlop));
        }
    };

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Offset, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m328invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m328invokek4lQ0M(long j10) {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements r7.a<t0> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m329invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m329invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.a<t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m330invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m330invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5", f = "DragGestureDetector.kt", l = {175, 884, 926, PsExtractor.AUDIO_STREAM}, m = "invokeSuspend")
    public static final class AnonymousClass5 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ p<PointerInputChange, Offset, t0> $onDrag;
        final /* synthetic */ r7.a<t0> $onDragCancel;
        final /* synthetic */ r7.a<t0> $onDragEnd;
        final /* synthetic */ l<Offset, t0> $onDragStart;
        float F$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$5$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements l<PointerInputChange, t0> {
            final /* synthetic */ p<PointerInputChange, Offset, t0> $onDrag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(p<? super PointerInputChange, ? super Offset, t0> pVar) {
                super(1);
                this.$onDrag = pVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PointerInputChange) obj);
                return t0.f22605a;
            }

            public final void invoke(PointerInputChange pointerInputChange) {
                this.$onDrag.invoke(pointerInputChange, Offset.m3225boximpl(PointerEventKt.positionChange(pointerInputChange)));
                pointerInputChange.consume();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(l<? super Offset, t0> lVar, p<? super PointerInputChange, ? super Offset, t0> pVar, r7.a<t0> aVar, r7.a<t0> aVar2, d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
            this.$onDragStart = lVar;
            this.$onDrag = pVar;
            this.$onDragCancel = aVar;
            this.$onDragEnd = aVar2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, dVar);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x00a6, code lost:
        
            if (r1 == r11) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0108, code lost:
        
            if (r15 == r11) goto L16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x023e, code lost:
        
            return r11;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Path cross not found for [B:38:0x0155, B:34:0x0146], limit reached: 81 */
        /* JADX WARN: Removed duplicated region for block: B:20:0x00d0  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01cf  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x01f3  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0203  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0247  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x024d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00d0 -> B:65:0x01f1). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0146 -> B:65:0x01f1). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x017e -> B:22:0x00f0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x01c2 -> B:57:0x01c9). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x01ee -> B:65:0x01f1). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r24) {
            /*
                Method dump skipped, instruction units count: 613
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03032 extends r implements l<Offset, t0> {
        public static final C03032 INSTANCE = new C03032();

        public C03032() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m331invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m331invokek4lQ0M(long j10) {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03043 extends r implements r7.a<t0> {
        public static final C03043 INSTANCE = new C03043();

        public C03043() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m332invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m332invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03054 extends r implements r7.a<t0> {
        public static final C03054 INSTANCE = new C03054();

        public C03054() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m333invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m333invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", l = {234, 235, PsExtractor.VIDEO_STREAM_MASK}, m = "invokeSuspend")
    public static final class C03065 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ p<PointerInputChange, Offset, t0> $onDrag;
        final /* synthetic */ r7.a<t0> $onDragCancel;
        final /* synthetic */ r7.a<t0> $onDragEnd;
        final /* synthetic */ l<Offset, t0> $onDragStart;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<PointerInputChange, t0> {
            final /* synthetic */ p<PointerInputChange, Offset, t0> $onDrag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(p<? super PointerInputChange, ? super Offset, t0> pVar) {
                super(1);
                this.$onDrag = pVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PointerInputChange) obj);
                return t0.f22605a;
            }

            public final void invoke(PointerInputChange pointerInputChange) {
                this.$onDrag.invoke(pointerInputChange, Offset.m3225boximpl(PointerEventKt.positionChange(pointerInputChange)));
                pointerInputChange.consume();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03065(l<? super Offset, t0> lVar, r7.a<t0> aVar, r7.a<t0> aVar2, p<? super PointerInputChange, ? super Offset, t0> pVar, d7.d<? super C03065> dVar) {
            super(2, dVar);
            this.$onDragStart = lVar;
            this.$onDragEnd = aVar;
            this.$onDragCancel = aVar2;
            this.$onDrag = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03065 c03065 = new C03065(this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$onDrag, dVar);
            c03065.L$0 = obj;
            return c03065;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((C03065) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0086, code lost:
        
            if (r12 == r4) goto L34;
         */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0062 A[Catch: CancellationException -> 0x00b0, PHI: r0 r8 r12
          0x0062: PHI (r0v12 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
          (r0v5 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
          (r0v13 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
         binds: [B:16:0x002d, B:28:0x005f] A[DONT_GENERATE, DONT_INLINE]
          0x0062: PHI (r8v6 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5) = 
          (r8v2 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5)
          (r8v7 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5)
         binds: [B:16:0x002d, B:28:0x005f] A[DONT_GENERATE, DONT_INLINE]
          0x0062: PHI (r12v12 java.lang.Object) = (r12v0 java.lang.Object), (r12v18 java.lang.Object) binds: [B:16:0x002d, B:28:0x005f] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {CancellationException -> 0x00b0, blocks: (B:35:0x0089, B:37:0x0091, B:39:0x00a0, B:41:0x00ac, B:44:0x00b3, B:45:0x00b6, B:46:0x00bc, B:30:0x0062, B:32:0x0066, B:27:0x0051, B:23:0x0049), top: B:53:0x0049 }] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0066 A[Catch: CancellationException -> 0x00b0, TryCatch #0 {CancellationException -> 0x00b0, blocks: (B:35:0x0089, B:37:0x0091, B:39:0x00a0, B:41:0x00ac, B:44:0x00b3, B:45:0x00b6, B:46:0x00bc, B:30:0x0062, B:32:0x0066, B:27:0x0051, B:23:0x0049), top: B:53:0x0049 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C03065.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03072 extends r implements l<Offset, t0> {
        public static final C03072 INSTANCE = new C03072();

        public C03072() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m334invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m334invokek4lQ0M(long j10) {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03083 extends r implements r7.a<t0> {
        public static final C03083 INSTANCE = new C03083();

        public C03083() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m335invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m335invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03094 extends r implements r7.a<t0> {
        public static final C03094 INSTANCE = new C03094();

        public C03094() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m336invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m336invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5", f = "DragGestureDetector.kt", l = {538, 540, 551}, m = "invokeSuspend")
    public static final class C03105 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ r7.a<t0> $onDragCancel;
        final /* synthetic */ r7.a<t0> $onDragEnd;
        final /* synthetic */ l<Offset, t0> $onDragStart;
        final /* synthetic */ p<PointerInputChange, Float, t0> $onHorizontalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<PointerInputChange, t0> {
            final /* synthetic */ p<PointerInputChange, Float, t0> $onHorizontalDrag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(p<? super PointerInputChange, ? super Float, t0> pVar) {
                super(1);
                this.$onHorizontalDrag = pVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PointerInputChange) obj);
                return t0.f22605a;
            }

            public final void invoke(PointerInputChange pointerInputChange) {
                this.$onHorizontalDrag.invoke(pointerInputChange, Float.valueOf(Offset.m3236getXimpl(PointerEventKt.positionChange(pointerInputChange))));
                pointerInputChange.consume();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03105(l<? super Offset, t0> lVar, p<? super PointerInputChange, ? super Float, t0> pVar, r7.a<t0> aVar, r7.a<t0> aVar2, d7.d<? super C03105> dVar) {
            super(2, dVar);
            this.$onDragStart = lVar;
            this.$onHorizontalDrag = pVar;
            this.$onDragEnd = aVar;
            this.$onDragCancel = aVar2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03105 c03105 = new C03105(this.$onDragStart, this.$onHorizontalDrag, this.$onDragEnd, this.$onDragCancel, dVar);
            c03105.L$0 = obj;
            return c03105;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((C03105) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x00a4, code lost:
        
            if (r13 == r4) goto L24;
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                int r0 = r12.label
                r1 = 3
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L34
                if (r0 == r3) goto L2a
                if (r0 == r2) goto L1d
                if (r0 != r1) goto L15
                k2.c.G(r13)
                r8 = r12
                goto La7
            L15:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L1d:
                java.lang.Object r0 = r12.L$1
                kotlin.jvm.internal.h0 r0 = (kotlin.jvm.internal.h0) r0
                java.lang.Object r2 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                k2.c.G(r13)
                r8 = r12
                goto L71
            L2a:
                java.lang.Object r0 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r13)
                r8 = r12
                r6 = r0
                goto L4d
            L34:
                k2.c.G(r13)
                java.lang.Object r13 = r12.L$0
                r5 = r13
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                r12.L$0 = r5
                r12.label = r3
                r6 = 0
                r7 = 0
                r9 = 2
                r10 = 0
                r8 = r12
                java.lang.Object r13 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r5, r6, r7, r8, r9, r10)
                if (r13 != r4) goto L4c
                goto La6
            L4c:
                r6 = r5
            L4d:
                androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
                kotlin.jvm.internal.h0 r0 = new kotlin.jvm.internal.h0
                r0.<init>()
                r11 = r8
                long r7 = r13.getId()
                int r9 = r13.getType()
                androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1 r10 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$drag$1
                r10.<init>(r0)
                r11.L$0 = r6
                r11.L$1 = r0
                r11.label = r2
                java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m310awaitHorizontalPointerSlopOrCancellationgDDlDlE(r6, r7, r9, r10, r11)
                r8 = r11
                if (r13 != r4) goto L70
                goto La6
            L70:
                r2 = r6
            L71:
                androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
                if (r13 == 0) goto Lba
                r7.l<androidx.compose.ui.geometry.Offset, x6.t0> r3 = r8.$onDragStart
                long r5 = r13.getPosition()
                androidx.compose.ui.geometry.Offset r5 = androidx.compose.ui.geometry.Offset.m3225boximpl(r5)
                r3.invoke(r5)
                r7.p<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float, x6.t0> r3 = r8.$onHorizontalDrag
                float r0 = r0.f19742i
                java.lang.Float r5 = new java.lang.Float
                r5.<init>(r0)
                r3.invoke(r13, r5)
                long r5 = r13.getId()
                androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$1 r13 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5$1
                r7.p<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float, x6.t0> r0 = r8.$onHorizontalDrag
                r13.<init>(r0)
                r0 = 0
                r8.L$0 = r0
                r8.L$1 = r0
                r8.label = r1
                java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m322horizontalDragjO51t88(r2, r5, r13, r12)
                if (r13 != r4) goto La7
            La6:
                return r4
            La7:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lb5
                r7.a<x6.t0> r13 = r8.$onDragEnd
                r13.invoke()
                goto Lba
            Lb5:
                r7.a<x6.t0> r13 = r8.$onDragCancel
                r13.invoke()
            Lba:
                x6.t0 r13 = x6.t0.f22605a
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C03105.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03112 extends r implements l<Offset, t0> {
        public static final C03112 INSTANCE = new C03112();

        public C03112() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m337invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m337invokek4lQ0M(long j10) {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03123 extends r implements r7.a<t0> {
        public static final C03123 INSTANCE = new C03123();

        public C03123() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m338invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m338invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03134 extends r implements r7.a<t0> {
        public static final C03134 INSTANCE = new C03134();

        public C03134() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m339invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m339invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5", f = "DragGestureDetector.kt", l = {390, 392, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN}, m = "invokeSuspend")
    public static final class C03145 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
        final /* synthetic */ r7.a<t0> $onDragCancel;
        final /* synthetic */ r7.a<t0> $onDragEnd;
        final /* synthetic */ l<Offset, t0> $onDragStart;
        final /* synthetic */ p<PointerInputChange, Float, t0> $onVerticalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<PointerInputChange, t0> {
            final /* synthetic */ p<PointerInputChange, Float, t0> $onVerticalDrag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(p<? super PointerInputChange, ? super Float, t0> pVar) {
                super(1);
                this.$onVerticalDrag = pVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PointerInputChange) obj);
                return t0.f22605a;
            }

            public final void invoke(PointerInputChange pointerInputChange) {
                this.$onVerticalDrag.invoke(pointerInputChange, Float.valueOf(Offset.m3237getYimpl(PointerEventKt.positionChange(pointerInputChange))));
                pointerInputChange.consume();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03145(l<? super Offset, t0> lVar, p<? super PointerInputChange, ? super Float, t0> pVar, r7.a<t0> aVar, r7.a<t0> aVar2, d7.d<? super C03145> dVar) {
            super(2, dVar);
            this.$onDragStart = lVar;
            this.$onVerticalDrag = pVar;
            this.$onDragEnd = aVar;
            this.$onDragCancel = aVar2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C03145 c03145 = new C03145(this.$onDragStart, this.$onVerticalDrag, this.$onDragEnd, this.$onDragCancel, dVar);
            c03145.L$0 = obj;
            return c03145;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
            return ((C03145) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x00a4, code lost:
        
            if (r13 == r4) goto L24;
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                int r0 = r12.label
                r1 = 3
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L34
                if (r0 == r3) goto L2a
                if (r0 == r2) goto L1d
                if (r0 != r1) goto L15
                k2.c.G(r13)
                r8 = r12
                goto La7
            L15:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L1d:
                java.lang.Object r0 = r12.L$1
                kotlin.jvm.internal.h0 r0 = (kotlin.jvm.internal.h0) r0
                java.lang.Object r2 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                k2.c.G(r13)
                r8 = r12
                goto L71
            L2a:
                java.lang.Object r0 = r12.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r13)
                r8 = r12
                r6 = r0
                goto L4d
            L34:
                k2.c.G(r13)
                java.lang.Object r13 = r12.L$0
                r5 = r13
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                r12.L$0 = r5
                r12.label = r3
                r6 = 0
                r7 = 0
                r9 = 2
                r10 = 0
                r8 = r12
                java.lang.Object r13 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r5, r6, r7, r8, r9, r10)
                if (r13 != r4) goto L4c
                goto La6
            L4c:
                r6 = r5
            L4d:
                androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
                kotlin.jvm.internal.h0 r0 = new kotlin.jvm.internal.h0
                r0.<init>()
                r11 = r8
                long r7 = r13.getId()
                int r9 = r13.getType()
                androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$drag$1 r10 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$drag$1
                r10.<init>(r0)
                r11.L$0 = r6
                r11.L$1 = r0
                r11.label = r2
                java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m317awaitVerticalPointerSlopOrCancellationgDDlDlE(r6, r7, r9, r10, r11)
                r8 = r11
                if (r13 != r4) goto L70
                goto La6
            L70:
                r2 = r6
            L71:
                androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
                if (r13 == 0) goto Lba
                r7.l<androidx.compose.ui.geometry.Offset, x6.t0> r3 = r8.$onDragStart
                long r5 = r13.getPosition()
                androidx.compose.ui.geometry.Offset r5 = androidx.compose.ui.geometry.Offset.m3225boximpl(r5)
                r3.invoke(r5)
                r7.p<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float, x6.t0> r3 = r8.$onVerticalDrag
                float r0 = r0.f19742i
                java.lang.Float r5 = new java.lang.Float
                r5.<init>(r0)
                r3.invoke(r13, r5)
                long r5 = r13.getId()
                androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$1 r13 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5$1
                r7.p<androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Float, x6.t0> r0 = r8.$onVerticalDrag
                r13.<init>(r0)
                r0 = 0
                r8.L$0 = r0
                r8.L$1 = r0
                r8.label = r1
                java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.m325verticalDragjO51t88(r2, r5, r13, r12)
                if (r13 != r4) goto La7
            La6:
                return r4
            La7:
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto Lb5
                r7.a<x6.t0> r13 = r8.$onDragEnd
                r13.invoke()
                goto Lba
            Lb5:
                r7.a<x6.t0> r13 = r8.$onDragCancel
                r13.invoke()
            Lba:
                x6.t0 r13 = x6.t0.f22605a
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.C03145.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl((float) 0.125d);
        mouseSlop = fM5678constructorimpl;
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(18);
        defaultTouchSlop = fM5678constructorimpl2;
        mouseToTouchSlopRatio = fM5678constructorimpl / fM5678constructorimpl2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00be, code lost:
    
        if (androidx.compose.ui.input.pointer.PointerEventKt.positionChangedIgnoreConsumed(r6) != false) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005d -> B:22:0x0060). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m307awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, long r13, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r15) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m307awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m308awaitDragOrUpjO51t88(AwaitPointerEventScope awaitPointerEventScope, long j10, l<? super PointerInputChange, Boolean> lVar, d7.d<? super PointerInputChange> dVar) {
        PointerInputChange pointerInputChange;
        PointerInputChange pointerInputChange2;
        while (true) {
            PointerInputChange pointerInputChange3 = null;
            PointerEvent pointerEvent = (PointerEvent) androidx.compose.ui.input.pointer.a.t(awaitPointerEventScope, null, dVar, 1, null);
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int size = changes.size();
            int i10 = 0;
            int i11 = 0;
            while (true) {
                if (i11 >= size) {
                    pointerInputChange = null;
                    break;
                }
                pointerInputChange = changes.get(i11);
                if (Boolean.valueOf(PointerId.m4500equalsimpl0(pointerInputChange.getId(), j10)).booleanValue()) {
                    break;
                }
                i11++;
            }
            pointerInputChange2 = pointerInputChange;
            if (pointerInputChange2 == null) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange2)) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                while (true) {
                    if (i10 >= size2) {
                        break;
                    }
                    PointerInputChange pointerInputChange4 = changes2.get(i10);
                    if (Boolean.valueOf(pointerInputChange4.getPressed()).booleanValue()) {
                        pointerInputChange3 = pointerInputChange4;
                        break;
                    }
                    i10++;
                }
                PointerInputChange pointerInputChange5 = pointerInputChange3;
                if (pointerInputChange5 == null) {
                    break;
                }
                j10 = pointerInputChange5.getId();
            } else if (((Boolean) lVar.invoke(pointerInputChange2)).booleanValue()) {
                break;
            }
        }
        return pointerInputChange2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c8, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3236getXimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r6)) == 0.0f ? 1 : 0) == 0) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005d -> B:22:0x0060). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m309awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, long r13, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r15) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m309awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x013b -> B:51:0x0149). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0180 -> B:58:0x0185). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x01ac -> B:62:0x018f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m310awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, x6.t0> r26, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m310awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0191 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0142 -> B:50:0x0152). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0189 -> B:57:0x018b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01b4 -> B:61:0x0194). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m311awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, x6.t0> r25, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m311awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m312awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, long r9, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r11) {
        /*
            boolean r0 = r11 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            if (r0 == 0) goto L13
            r0 = r11
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.internal.k0 r8 = (kotlin.jvm.internal.k0) r8
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
            k2.c.G(r11)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L99
            goto L97
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            k2.c.G(r11)
            androidx.compose.ui.input.pointer.PointerEvent r11 = r8.getCurrentEvent()
            boolean r11 = m323isPointerUpDmW0f2w(r11, r9)
            if (r11 == 0) goto L44
            return r3
        L44:
            androidx.compose.ui.input.pointer.PointerEvent r11 = r8.getCurrentEvent()
            java.util.List r11 = r11.getChanges()
            int r1 = r11.size()
            r4 = 0
        L51:
            if (r4 >= r1) goto L68
            java.lang.Object r5 = r11.get(r4)
            r6 = r5
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            long r6 = r6.getId()
            boolean r6 = androidx.compose.ui.input.pointer.PointerId.m4500equalsimpl0(r6, r9)
            if (r6 == 0) goto L65
            goto L69
        L65:
            int r4 = r4 + 1
            goto L51
        L68:
            r5 = r3
        L69:
            r9 = r5
            androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
            if (r9 != 0) goto L6f
            return r3
        L6f:
            kotlin.jvm.internal.k0 r10 = new kotlin.jvm.internal.k0
            r10.<init>()
            kotlin.jvm.internal.k0 r11 = new kotlin.jvm.internal.k0
            r11.<init>()
            r11.f19746i = r9
            androidx.compose.ui.platform.ViewConfiguration r1 = r8.getViewConfiguration()
            long r4 = r1.getLongPressTimeoutMillis()
            androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2 r1 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitLongPressOrCancellation$2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            r1.<init>(r11, r10, r3)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            r0.L$0 = r9     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            r0.L$1 = r10     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            r0.label = r2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            java.lang.Object r8 = r8.withTimeout(r4, r1, r0)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L98
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto L97
            return r9
        L97:
            return r3
        L98:
            r8 = r10
        L99:
            java.lang.Object r8 = r8.f19746i
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 != 0) goto La0
            goto La1
        La0:
            r9 = r8
        La1:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m312awaitLongPressOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x018d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x013b -> B:50:0x0149). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0180 -> B:57:0x0187). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01a9 -> B:61:0x0191). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-pn7EDYM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m313awaitPointerSlopOrCancellationpn7EDYM(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, androidx.compose.foundation.gestures.PointerDirectionConfig r26, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, x6.t0> r27, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r28) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m313awaitPointerSlopOrCancellationpn7EDYM(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, androidx.compose.foundation.gestures.PointerDirectionConfig, r7.p, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-pn7EDYM$$forInline, reason: not valid java name */
    private static final Object m314awaitPointerSlopOrCancellationpn7EDYM$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j10, int i10, PointerDirectionConfig pointerDirectionConfig, p<? super PointerInputChange, ? super Offset, t0> pVar, d7.d<? super PointerInputChange> dVar) {
        PointerInputChange pointerInputChange;
        long jM3252getZeroF1C5BW0;
        PointerInputChange pointerInputChange2;
        long jM4516getIdJ3iCeTQ = j10;
        if (m323isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), jM4516getIdJ3iCeTQ)) {
            return null;
        }
        float fM324pointerSlopE8SPZFQ = m324pointerSlopE8SPZFQ(awaitPointerEventScope.getViewConfiguration(), i10);
        long jM3252getZeroF1C5BW02 = Offset.INSTANCE.m3252getZeroF1C5BW0();
        while (true) {
            PointerEvent pointerEvent = (PointerEvent) androidx.compose.ui.input.pointer.a.t(awaitPointerEventScope, null, dVar, 1, null);
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int size = changes.size();
            int i11 = 0;
            while (true) {
                if (i11 >= size) {
                    pointerInputChange = null;
                    break;
                }
                pointerInputChange = changes.get(i11);
                int i12 = i11;
                if (Boolean.valueOf(PointerId.m4500equalsimpl0(pointerInputChange.getId(), jM4516getIdJ3iCeTQ)).booleanValue()) {
                    break;
                }
                i11 = i12 + 1;
            }
            PointerInputChange pointerInputChange3 = pointerInputChange;
            if (pointerInputChange3 == null || pointerInputChange3.isConsumed()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange3)) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size2) {
                        pointerInputChange2 = null;
                        break;
                    }
                    pointerInputChange2 = changes2.get(i13);
                    if (Boolean.valueOf(pointerInputChange2.getPressed()).booleanValue()) {
                        break;
                    }
                    i13++;
                }
                PointerInputChange pointerInputChange4 = pointerInputChange2;
                if (pointerInputChange4 == null) {
                    return null;
                }
                jM4516getIdJ3iCeTQ = pointerInputChange4.getId();
            } else {
                jM3252getZeroF1C5BW02 = Offset.m3241plusMKHz9U(jM3252getZeroF1C5BW02, Offset.m3240minusMKHz9U(pointerInputChange3.getPosition(), pointerInputChange3.getPreviousPosition()));
                if (pointerDirectionConfig.mo326calculateDeltaChangek4lQ0M(jM3252getZeroF1C5BW02) < fM324pointerSlopE8SPZFQ) {
                    awaitPointerEventScope.awaitPointerEvent(PointerEventPass.Final, dVar);
                    if (pointerInputChange3.isConsumed()) {
                        return null;
                    }
                } else {
                    pVar.invoke(pointerInputChange3, Offset.m3225boximpl(pointerDirectionConfig.mo327calculatePostSlopOffset8S9VItk(jM3252getZeroF1C5BW02, fM324pointerSlopE8SPZFQ)));
                    if (pointerInputChange3.isConsumed()) {
                        return pointerInputChange3;
                    }
                    jM3252getZeroF1C5BW0 = Offset.INSTANCE.m3252getZeroF1C5BW0();
                    jM3252getZeroF1C5BW02 = jM3252getZeroF1C5BW0;
                }
            }
            jM3252getZeroF1C5BW0 = jM3252getZeroF1C5BW02;
            jM3252getZeroF1C5BW02 = jM3252getZeroF1C5BW0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0191 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0142 -> B:50:0x0152). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0189 -> B:57:0x018b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01af -> B:61:0x0194). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m315awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, x6.t0> r25, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m315awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c8, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3237getYimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r6)) == 0.0f ? 1 : 0) == 0) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005d -> B:22:0x0060). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m316awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r12, long r13, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r15) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m316awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x013b -> B:51:0x0149). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0180 -> B:58:0x0185). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x01ac -> B:62:0x018f). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m317awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, int r25, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, x6.t0> r26, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r27) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m317awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0191 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0142 -> B:50:0x0152). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x0189 -> B:57:0x018b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x01b4 -> B:61:0x0194). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m318awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r22, long r23, r7.p<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, x6.t0> r25, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m318awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.p, d7.d):java.lang.Object");
    }

    public static final Object detectDragGestures(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, r7.a<t0> aVar, r7.a<t0> aVar2, p<? super PointerInputChange, ? super Offset, t0> pVar, d7.d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass5(lVar, pVar, aVar2, aVar, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, l lVar, r7.a aVar, r7.a aVar2, p pVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        if ((i10 & 2) != 0) {
            aVar = AnonymousClass3.INSTANCE;
        }
        if ((i10 & 4) != 0) {
            aVar2 = AnonymousClass4.INSTANCE;
        }
        r7.a aVar3 = aVar2;
        return detectDragGestures(pointerInputScope, lVar, aVar, aVar3, pVar, dVar);
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, r7.a<t0> aVar, r7.a<t0> aVar2, p<? super PointerInputChange, ? super Offset, t0> pVar, d7.d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03065(lVar, aVar, aVar2, pVar, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectDragGesturesAfterLongPress$default(PointerInputScope pointerInputScope, l lVar, r7.a aVar, r7.a aVar2, p pVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = C03032.INSTANCE;
        }
        if ((i10 & 2) != 0) {
            aVar = C03043.INSTANCE;
        }
        if ((i10 & 4) != 0) {
            aVar2 = C03054.INSTANCE;
        }
        r7.a aVar3 = aVar2;
        return detectDragGesturesAfterLongPress(pointerInputScope, lVar, aVar, aVar3, pVar, dVar);
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, r7.a<t0> aVar, r7.a<t0> aVar2, p<? super PointerInputChange, ? super Float, t0> pVar, d7.d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03105(lVar, pVar, aVar, aVar2, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectHorizontalDragGestures$default(PointerInputScope pointerInputScope, l lVar, r7.a aVar, r7.a aVar2, p pVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = C03072.INSTANCE;
        }
        if ((i10 & 2) != 0) {
            aVar = C03083.INSTANCE;
        }
        if ((i10 & 4) != 0) {
            aVar2 = C03094.INSTANCE;
        }
        r7.a aVar3 = aVar2;
        return detectHorizontalDragGestures(pointerInputScope, lVar, aVar, aVar3, pVar, dVar);
    }

    public static final Object detectVerticalDragGestures(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, r7.a<t0> aVar, r7.a<t0> aVar2, p<? super PointerInputChange, ? super Float, t0> pVar, d7.d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03145(lVar, pVar, aVar, aVar2, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    public static /* synthetic */ Object detectVerticalDragGestures$default(PointerInputScope pointerInputScope, l lVar, r7.a aVar, r7.a aVar2, p pVar, d7.d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = C03112.INSTANCE;
        }
        if ((i10 & 2) != 0) {
            aVar = C03123.INSTANCE;
        }
        if ((i10 & 4) != 0) {
            aVar2 = C03134.INSTANCE;
        }
        r7.a aVar3 = aVar2;
        return detectVerticalDragGestures(pointerInputScope, lVar, aVar, aVar3, pVar, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0100, code lost:
    
        if (r0 != false) goto L48;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:35:0x00cc, B:46:0x00f6], limit reached: 59 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008b -> B:23:0x0091). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: drag-VnAYq1g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m319dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, long r19, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, x6.t0> r21, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r22, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r23, d7.d<? super androidx.compose.ui.input.pointer.PointerInputChange> r24) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m319dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.l, r7.l, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m320dragVnAYq1g$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j10, l<? super PointerInputChange, t0> lVar, l<? super PointerInputChange, Boolean> lVar2, l<? super PointerInputChange, Boolean> lVar3, d7.d<? super PointerInputChange> dVar) {
        PointerInputChange pointerInputChange;
        PointerInputChange pointerInputChange2;
        if (m323isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j10)) {
            return null;
        }
        while (true) {
            PointerEvent pointerEvent = (PointerEvent) androidx.compose.ui.input.pointer.a.t(awaitPointerEventScope, null, dVar, 1, null);
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int size = changes.size();
            int i10 = 0;
            int i11 = 0;
            while (true) {
                if (i11 >= size) {
                    pointerInputChange = null;
                    break;
                }
                pointerInputChange = changes.get(i11);
                if (Boolean.valueOf(PointerId.m4500equalsimpl0(pointerInputChange.getId(), j10)).booleanValue()) {
                    break;
                }
                i11++;
            }
            PointerInputChange pointerInputChange3 = pointerInputChange;
            if (pointerInputChange3 == null) {
                pointerInputChange3 = null;
            } else if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange3)) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                while (true) {
                    if (i10 >= size2) {
                        pointerInputChange2 = null;
                        break;
                    }
                    pointerInputChange2 = changes2.get(i10);
                    if (Boolean.valueOf(pointerInputChange2.getPressed()).booleanValue()) {
                        break;
                    }
                    i10++;
                }
                PointerInputChange pointerInputChange4 = pointerInputChange2;
                if (pointerInputChange4 != null) {
                    j10 = pointerInputChange4.getId();
                }
            } else if (!((Boolean) lVar2.invoke(pointerInputChange3)).booleanValue()) {
                continue;
            }
            if (pointerInputChange3 == null || ((Boolean) lVar3.invoke(pointerInputChange3)).booleanValue()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange3)) {
                return pointerInputChange3;
            }
            lVar.invoke(pointerInputChange3);
            j10 = pointerInputChange3.getId();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0046 -> B:18:0x0049). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: drag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m321dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r4, long r5, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, x6.t0> r7, d7.d<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r4 = r0.L$1
            r7.l r4 = (r7.l) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
            k2.c.G(r8)
            r7 = r4
            r4 = r5
            goto L49
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            k2.c.G(r8)
        L3a:
            r0.L$0 = r4
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r8 = m307awaitDragOrCancellationrnUCldI(r4, r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r8 != r5) goto L49
            return r5
        L49:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 != 0) goto L50
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L50:
            boolean r5 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r8)
            if (r5 == 0) goto L59
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L59:
            r7.invoke(r8)
            long r5 = r8.getId()
            goto L3a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m321dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.l, d7.d):java.lang.Object");
    }

    public static final PointerDirectionConfig getBidirectionalPointerDirectionConfig() {
        return BidirectionalPointerDirectionConfig;
    }

    public static final PointerDirectionConfig getHorizontalPointerDirectionConfig() {
        return HorizontalPointerDirectionConfig;
    }

    public static final PointerDirectionConfig getVerticalPointerDirectionConfig() {
        return VerticalPointerDirectionConfig;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3236getXimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r12)) == 0.0f) == false) goto L51;
     */
    /* JADX WARN: Path cross not found for [B:46:0x00d3, B:35:0x00aa], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x009d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0075 -> B:23:0x0078). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m322horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r16, long r17, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, x6.t0> r19, d7.d<? super java.lang.Boolean> r20) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m322horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m323isPointerUpDmW0f2w(PointerEvent pointerEvent, long j10) {
        PointerInputChange pointerInputChange;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                pointerInputChange = null;
                break;
            }
            pointerInputChange = changes.get(i10);
            if (PointerId.m4500equalsimpl0(pointerInputChange.getId(), j10)) {
                break;
            }
            i10++;
        }
        PointerInputChange pointerInputChange2 = pointerInputChange;
        if (pointerInputChange2 != null && pointerInputChange2.getPressed()) {
            z = true;
        }
        return true ^ z;
    }

    /* JADX INFO: renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m324pointerSlopE8SPZFQ(ViewConfiguration viewConfiguration, int i10) {
        return PointerType.m4577equalsimpl0(i10, PointerType.INSTANCE.m4582getMouseT8wyACA()) ? viewConfiguration.getTouchSlop() * mouseToTouchSlopRatio : viewConfiguration.getTouchSlop();
    }

    public static final PointerDirectionConfig toPointerDirectionConfig(Orientation orientation) {
        return orientation == Orientation.Vertical ? VerticalPointerDirectionConfig : HorizontalPointerDirectionConfig;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
    
        if ((androidx.compose.ui.geometry.Offset.m3237getYimpl(androidx.compose.ui.input.pointer.PointerEventKt.positionChangeIgnoreConsumed(r12)) == 0.0f) == false) goto L51;
     */
    /* JADX WARN: Path cross not found for [B:46:0x00d3, B:35:0x00aa], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x009d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0075 -> B:23:0x0078). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: verticalDrag-jO51t88, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m325verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r16, long r17, r7.l<? super androidx.compose.ui.input.pointer.PointerInputChange, x6.t0> r19, d7.d<? super java.lang.Boolean> r20) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m325verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, r7.l, d7.d):java.lang.Object");
    }
}
