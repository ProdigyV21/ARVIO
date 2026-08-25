package androidx.compose.foundation.text2.input.internal.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.internal.TextLayoutState;
import androidx.compose.foundation.text2.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text2.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.unit.Density;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.h;
import f7.j;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.r;
import na.k;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rJ=\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0018\u001a\u00020\u0014*\u00020\u0017H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J0\u0010\u001d\u001a\u00020\u0014*\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aH\u0086@¢\u0006\u0004\b\u001d\u0010\u001eJ\u001c\u0010 \u001a\u00020\u0014*\u00020\u00172\u0006\u0010\u001f\u001a\u00020\bH\u0086@¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\"\u0010#J\u0015\u0010&\u001a\u00020\u00142\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\u0014¢\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\u0014¢\u0006\u0004\b*\u0010)J\u0017\u0010,\u001a\u00020\u00142\b\b\u0002\u0010+\u001a\u00020\b¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\u0014¢\u0006\u0004\b.\u0010)J\r\u0010/\u001a\u00020\u0014¢\u0006\u0004\b/\u0010)J\u0014\u00100\u001a\u00020\u0014*\u00020\u0017H\u0082@¢\u0006\u0004\b0\u0010\u0019J0\u00101\u001a\u00020\u0014*\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082@¢\u0006\u0004\b1\u0010\u001eJ\u0014\u00102\u001a\u00020\u0014*\u00020\u0017H\u0082@¢\u0006\u0004\b2\u0010\u0019J\"\u00103\u001a\u00020\u0014*\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082@¢\u0006\u0004\b3\u00104J\u001c\u00105\u001a\u00020\u0014*\u00020\u00172\u0006\u0010\u001f\u001a\u00020\bH\u0082@¢\u0006\u0004\b5\u0010!J\u0010\u00106\u001a\u00020\u0014H\u0082@¢\u0006\u0004\b6\u0010#J\u0010\u00107\u001a\u00020\u0014H\u0082@¢\u0006\u0004\b7\u0010#J\u000f\u00109\u001a\u000208H\u0002¢\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020;2\u0006\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0004\b<\u0010=J\u001d\u0010A\u001a\u00020>2\u0006\u0010\u001f\u001a\u00020\bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b?\u0010@J\"\u0010G\u001a\u00020\u00142\u0006\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020>H\u0002ø\u0001\u0001¢\u0006\u0004\bE\u0010FJ\u000f\u0010H\u001a\u00020\u0014H\u0002¢\u0006\u0004\bH\u0010)J\u000f\u0010I\u001a\u00020\u0014H\u0002¢\u0006\u0004\bI\u0010)J\u0017\u0010K\u001a\u00020\u00142\u0006\u0010J\u001a\u000208H\u0002¢\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u00020\u0014H\u0002¢\u0006\u0004\bM\u0010)JG\u0010Y\u001a\u00020V2\u0006\u0010O\u001a\u00020N2\u0006\u0010Q\u001a\u00020P2\u0006\u0010R\u001a\u00020P2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010T\u001a\u00020S2\b\b\u0002\u0010U\u001a\u00020\bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bW\u0010XJ<\u0010_\u001a\u00020V2\u0006\u0010Z\u001a\u00020P2\u0006\u0010[\u001a\u00020P2\b\u0010\\\u001a\u0004\u0018\u00010V2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010T\u001a\u00020SH\u0002ø\u0001\u0001¢\u0006\u0004\b]\u0010^R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010`R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010aR\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010bR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010cR\u0016\u0010\n\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010cR\"\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010c\u001a\u0004\b\u000b\u0010d\"\u0004\be\u0010-R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010fR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010gR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010hR+\u0010l\u001a\u00020\b2\u0006\u0010i\u001a\u00020\b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bj\u0010k\u001a\u0004\bl\u0010d\"\u0004\bm\u0010-R1\u0010s\u001a\u00020>2\u0006\u0010i\u001a\u00020>8B@BX\u0082\u008e\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0012\n\u0004\bn\u0010k\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR1\u0010w\u001a\u00020>2\u0006\u0010i\u001a\u00020>8B@BX\u0082\u008e\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0012\n\u0004\bt\u0010k\u001a\u0004\bu\u0010p\"\u0004\bv\u0010rR/\u0010}\u001a\u0004\u0018\u00010B2\b\u0010i\u001a\u0004\u0018\u00010B8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bx\u0010k\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R-\u0010\u0081\u0001\u001a\u00020\b2\u0006\u0010i\u001a\u00020\b8B@BX\u0082\u008e\u0002¢\u0006\u0013\n\u0004\b~\u0010k\u001a\u0004\b\u007f\u0010d\"\u0005\b\u0080\u0001\u0010-R/\u0010%\u001a\u00020$2\u0006\u0010i\u001a\u00020$8B@BX\u0082\u008e\u0002¢\u0006\u0016\n\u0005\b\u0082\u0001\u0010k\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"\u0005\b\u0085\u0001\u0010'R\u001c\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0019\u0010\u0089\u0001\u001a\u00020P8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R \u0010\u008f\u0001\u001a\u00020;8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001f\u0010\u0092\u0001\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0006\b\u0090\u0001\u0010\u008c\u0001\u001a\u0005\b\u0091\u0001\u0010dR\u001f\u0010\u0095\u0001\u001a\u0002088FX\u0086\u0084\u0002¢\u0006\u000f\n\u0006\b\u0093\u0001\u0010\u008c\u0001\u001a\u0005\b\u0094\u0001\u0010:R \u0010\u0098\u0001\u001a\u00020;8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0096\u0001\u0010\u008c\u0001\u001a\u0006\b\u0097\u0001\u0010\u008e\u0001R \u0010\u009b\u0001\u001a\u00020;8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0099\u0001\u0010\u008c\u0001\u001a\u0006\b\u009a\u0001\u0010\u008e\u0001R\u0019\u0010\u009d\u0001\u001a\u00020>8Fø\u0001\u0001ø\u0001\u0000¢\u0006\u0007\u001a\u0005\b\u009c\u0001\u0010pR\u001c\u0010\u009f\u0001\u001a\u00020>8BX\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0007\u001a\u0005\b\u009e\u0001\u0010pR\u001a\u0010£\u0001\u001a\u0005\u0018\u00010 \u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b¡\u0001\u0010¢\u0001R\u0016\u0010¥\u0001\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¤\u0001\u0010d\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006¦\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/ui/unit/Density;", "density", "", "enabled", "readOnly", "isFocused", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZ)V", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "hapticFeedBack", "Landroidx/compose/ui/platform/ClipboardManager;", "clipboardManager", "Landroidx/compose/ui/platform/TextToolbar;", "textToolbar", "Lx6/t0;", "update", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;Landroidx/compose/ui/platform/ClipboardManager;Landroidx/compose/ui/platform/TextToolbar;Landroidx/compose/ui/unit/Density;ZZ)V", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "cursorHandleGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function0;", "requestFocus", "showKeyboard", "textFieldGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/a;Lr7/a;Ld7/d;)Ljava/lang/Object;", "isStartHandle", "selectionHandleGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLd7/d;)Ljava/lang/Object;", "observeChanges", "(Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;", "textToolbarState", "updateTextToolbarState", "(Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;)V", "dispose", "()V", "cut", "cancelSelection", "copy", "(Z)V", "paste", "deselect", "detectTouchMode", "detectTextFieldTapGestures", "detectCursorHandleDragGestures", "detectTextFieldLongPressAndAfterDrag", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/a;Ld7/d;)Ljava/lang/Object;", "detectSelectionHandleDragGestures", "observeTextChanges", "observeTextToolbarVisibility", "Landroidx/compose/ui/geometry/Rect;", "getContentRect", "()Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "getSelectionHandleState", "(Z)Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "Landroidx/compose/ui/geometry/Offset;", "getHandlePosition-tuRUvjQ", "(Z)J", "getHandlePosition", "Landroidx/compose/foundation/text/Handle;", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "updateHandleDragging", "markStartContentVisibleOffset", "clearHandleDragging", "contentRect", "showTextToolbar", "(Landroidx/compose/ui/geometry/Rect;)V", "hideTextToolbar", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "textFieldCharSequence", "", "startOffset", "endOffset", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustment", "allowPreviousSelectionCollapsed", "Landroidx/compose/ui/text/TextRange;", "updateSelection-QNhciaU", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "updateSelection", "rawStartOffset", "rawEndOffset", "previousSelection", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "getTextFieldSelection", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "Landroidx/compose/ui/unit/Density;", "Z", "()Z", "setFocused", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "Landroidx/compose/ui/platform/TextToolbar;", "Landroidx/compose/ui/platform/ClipboardManager;", "<set-?>", "isInTouchMode$delegate", "Landroidx/compose/runtime/MutableState;", "isInTouchMode", "setInTouchMode", "startContentVisibleOffset$delegate", "getStartContentVisibleOffset-F1C5BW0", "()J", "setStartContentVisibleOffset-k-4lQ0M", "(J)V", "startContentVisibleOffset", "rawHandleDragPosition$delegate", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "rawHandleDragPosition", "draggingHandle$delegate", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle", "showCursorHandle$delegate", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle", "textToolbarState$delegate", "getTextToolbarState", "()Landroidx/compose/foundation/text2/input/internal/selection/TextToolbarState;", "setTextToolbarState", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousRawDragOffset", "I", "cursorHandle$delegate", "Landroidx/compose/runtime/State;", "getCursorHandle", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldHandleState;", "cursorHandle", "cursorHandleInBounds$delegate", "getCursorHandleInBounds", "cursorHandleInBounds", "cursorRect$delegate", "getCursorRect", "cursorRect", "startSelectionHandle$delegate", "getStartSelectionHandle", "startSelectionHandle", "endSelectionHandle$delegate", "getEndSelectionHandle", "endSelectionHandle", "getHandleDragPosition-F1C5BW0", "handleDragPosition", "getCurrentContentVisibleOffset-F1C5BW0", "currentContentVisibleOffset", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "textLayoutCoordinates", "getEditable", "editable", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;

    /* JADX INFO: renamed from: cursorHandle$delegate, reason: from kotlin metadata */
    private final State cursorHandle;

    /* JADX INFO: renamed from: cursorHandleInBounds$delegate, reason: from kotlin metadata */
    private final State cursorHandleInBounds;

    /* JADX INFO: renamed from: cursorRect$delegate, reason: from kotlin metadata */
    private final State cursorRect;
    private Density density;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;
    private boolean enabled;

    /* JADX INFO: renamed from: endSelectionHandle$delegate, reason: from kotlin metadata */
    private final State endSelectionHandle;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;

    /* JADX INFO: renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition;
    private boolean readOnly;

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle;

    /* JADX INFO: renamed from: startContentVisibleOffset$delegate, reason: from kotlin metadata */
    private final MutableState startContentVisibleOffset;

    /* JADX INFO: renamed from: startSelectionHandle$delegate, reason: from kotlin metadata */
    private final State startSelectionHandle;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbar textToolbar;

    /* JADX INFO: renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lka/v1;", "<anonymous>", "(Lka/k0;)Lka/v1;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2", f = "TextFieldSelectionState.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super v1>, Object> {
        final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1", f = "TextFieldSelectionState.kt", l = {318}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$this_cursorHandleGestures, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    this.label = 1;
                    Object objDetectTouchMode = textFieldSelectionState.detectTouchMode(pointerInputScope, this);
                    a aVar = a.f15033i;
                    if (objDetectTouchMode == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2", f = "TextFieldSelectionState.kt", l = {MediaError.DetailedErrorCode.DASH_NETWORK}, m = "invokeSuspend")
        public static final class C00742 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00742(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, d<? super C00742> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00742(this.this$0, this.$this_cursorHandleGestures, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    this.label = 1;
                    Object objDetectCursorHandleDragGestures = textFieldSelectionState.detectCursorHandleDragGestures(pointerInputScope, this);
                    a aVar = a.f15033i;
                    if (objDetectCursorHandleDragGestures == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00742) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3", f = "TextFieldSelectionState.kt", l = {324}, m = "invokeSuspend")
        public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass1 extends r implements l<Offset, t0> {
                final /* synthetic */ TextFieldSelectionState this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(TextFieldSelectionState textFieldSelectionState) {
                    super(1);
                    this.this$0 = textFieldSelectionState;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m1174invokek4lQ0M(((Offset) obj).getPackedValue());
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m1174invokek4lQ0M(long j10) {
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    TextToolbarState textToolbarState = textFieldSelectionState.getTextToolbarState();
                    TextToolbarState textToolbarState2 = TextToolbarState.Cursor;
                    if (textToolbarState == textToolbarState2) {
                        textToolbarState2 = TextToolbarState.None;
                    }
                    textFieldSelectionState.setTextToolbarState(textToolbarState2);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.$this_cursorHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass3(this.$this_cursorHandleGestures, this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0);
                    this.label = 1;
                    Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, anonymousClass1, this, 7, null);
                    a aVar = a.f15033i;
                    if (objDetectTapGestures$default == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PointerInputScope pointerInputScope, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_cursorHandleGestures = pointerInputScope;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = TextFieldSelectionState.this.new AnonymousClass2(this.$this_cursorHandleGestures, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            m0.p(k0Var, null, 4, new AnonymousClass1(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1);
            m0.p(k0Var, null, 4, new C00742(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1);
            return m0.p(k0Var, null, 4, new AnonymousClass3(this.$this_cursorHandleGestures, TextFieldSelectionState.this, null), 1);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super v1> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", l = {498}, m = "detectCursorHandleDragGestures")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectCursorHandleDragGestures(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04212 extends r implements l<Offset, t0> {
        final /* synthetic */ j0 $cursorDragDelta;
        final /* synthetic */ j0 $cursorDragStart;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04212(j0 j0Var, TextFieldSelectionState textFieldSelectionState, j0 j0Var2) {
            super(1);
            this.$cursorDragStart = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$cursorDragDelta = j0Var2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1175invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m1175invokek4lQ0M(long j10) {
            this.$cursorDragStart.f19745i = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(this.this$0.getCursorRect().m3263getBottomCenterF1C5BW0());
            this.$cursorDragDelta.f19745i = Offset.INSTANCE.m3252getZeroF1C5BW0();
            this.this$0.setInTouchMode(true);
            this.this$0.markStartContentVisibleOffset();
            this.this$0.m1170updateHandleDraggingUv8p0NA(Handle.Cursor, this.$cursorDragStart.f19745i);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements r7.a<t0> {
        final /* synthetic */ j0 $cursorDragDelta;
        final /* synthetic */ j0 $cursorDragStart;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(j0 j0Var, j0 j0Var2, TextFieldSelectionState textFieldSelectionState) {
            super(0);
            this.$cursorDragStart = j0Var;
            this.$cursorDragDelta = j0Var2;
            this.this$0 = textFieldSelectionState;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1176invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1176invoke() {
            TextFieldSelectionState.detectCursorHandleDragGestures$onDragStop(this.$cursorDragStart, this.$cursorDragDelta, this.this$0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.a<t0> {
        final /* synthetic */ j0 $cursorDragDelta;
        final /* synthetic */ j0 $cursorDragStart;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(j0 j0Var, j0 j0Var2, TextFieldSelectionState textFieldSelectionState) {
            super(0);
            this.$cursorDragStart = j0Var;
            this.$cursorDragDelta = j0Var2;
            this.this$0 = textFieldSelectionState;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1177invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1177invoke() {
            TextFieldSelectionState.detectCursorHandleDragGestures$onDragStop(this.$cursorDragStart, this.$cursorDragDelta, this.this$0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "change", "Landroidx/compose/ui/geometry/Offset;", "dragAmount", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ j0 $cursorDragDelta;
        final /* synthetic */ j0 $cursorDragStart;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(j0 j0Var, TextFieldSelectionState textFieldSelectionState, j0 j0Var2) {
            super(2);
            this.$cursorDragDelta = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$cursorDragStart = j0Var2;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m1178invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m1178invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            j0 j0Var = this.$cursorDragDelta;
            j0Var.f19745i = Offset.m3241plusMKHz9U(j0Var.f19745i, j10);
            this.this$0.m1170updateHandleDraggingUv8p0NA(Handle.Cursor, Offset.m3241plusMKHz9U(this.$cursorDragStart.f19745i, this.$cursorDragDelta.f19745i));
            TextLayoutResult layoutResult = this.this$0.textLayoutState.getLayoutResult();
            if (layoutResult == null) {
                return;
            }
            long jTextRange = TextRangeKt.TextRange(layoutResult.m5164getOffsetForPositionk4lQ0M(this.this$0.m1173getHandleDragPositionF1C5BW0()));
            if (TextRange.m5185equalsimpl0(jTextRange, this.this$0.textFieldState.getText().getSelectionInChars())) {
                return;
            }
            pointerInputChange.consume();
            HapticFeedback hapticFeedback = this.this$0.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
            }
            this.this$0.textFieldState.m1140selectCharsIn5zctL8(jTextRange);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", l = {740}, m = "detectSelectionHandleDragGestures")
    public static final class C04221 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C04221(d<? super C04221> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectSelectionHandleDragGestures(null, false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04232 extends r implements l<Offset, t0> {
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ Handle $handle;
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04232(j0 j0Var, TextFieldSelectionState textFieldSelectionState, boolean z, Handle handle, j0 j0Var2) {
            super(1);
            this.$dragBeginPosition = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$isStartHandle = z;
            this.$handle = handle;
            this.$dragTotalDistance = j0Var2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1179invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m1179invokek4lQ0M(long j10) {
            this.$dragBeginPosition.f19745i = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(this.this$0.m1164getHandlePositiontuRUvjQ(this.$isStartHandle));
            this.this$0.m1170updateHandleDraggingUv8p0NA(this.$handle, this.$dragBeginPosition.f19745i);
            this.$dragTotalDistance.f19745i = Offset.INSTANCE.m3252getZeroF1C5BW0();
            this.this$0.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04243 extends r implements r7.a<t0> {
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04243(j0 j0Var, TextFieldSelectionState textFieldSelectionState, j0 j0Var2) {
            super(0);
            this.$dragBeginPosition = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$dragTotalDistance = j0Var2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1180invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1180invoke() {
            TextFieldSelectionState.detectSelectionHandleDragGestures$onDragStop$2(this.$dragBeginPosition, this.this$0, this.$dragTotalDistance);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04254 extends r implements r7.a<t0> {
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04254(j0 j0Var, TextFieldSelectionState textFieldSelectionState, j0 j0Var2) {
            super(0);
            this.$dragBeginPosition = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$dragTotalDistance = j0Var2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1181invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1181invoke() {
            TextFieldSelectionState.detectSelectionHandleDragGestures$onDragStop$2(this.$dragBeginPosition, this.this$0, this.$dragTotalDistance);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "<anonymous parameter 0>", "Landroidx/compose/ui/geometry/Offset;", "delta", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04265 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ Handle $handle;
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04265(j0 j0Var, TextFieldSelectionState textFieldSelectionState, Handle handle, j0 j0Var2, boolean z) {
            super(2);
            this.$dragTotalDistance = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$handle = handle;
            this.$dragBeginPosition = j0Var2;
            this.$isStartHandle = z;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m1182invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m1182invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            j0 j0Var = this.$dragTotalDistance;
            j0Var.f19745i = Offset.m3241plusMKHz9U(j0Var.f19745i, j10);
            TextLayoutResult layoutResult = this.this$0.textLayoutState.getLayoutResult();
            if (layoutResult == null) {
                return;
            }
            this.this$0.m1170updateHandleDraggingUv8p0NA(this.$handle, Offset.m3241plusMKHz9U(this.$dragBeginPosition.f19745i, this.$dragTotalDistance.f19745i));
            int iM5164getOffsetForPositionk4lQ0M = this.$isStartHandle ? layoutResult.m5164getOffsetForPositionk4lQ0M(this.this$0.m1173getHandleDragPositionF1C5BW0()) : TextRange.m5192getStartimpl(this.this$0.textFieldState.getText().getSelectionInChars());
            int iM5187getEndimpl = this.$isStartHandle ? TextRange.m5187getEndimpl(this.this$0.textFieldState.getText().getSelectionInChars()) : layoutResult.m5164getOffsetForPositionk4lQ0M(this.this$0.m1173getHandleDragPositionF1C5BW0());
            long selectionInChars = this.this$0.textFieldState.getText().getSelectionInChars();
            TextFieldSelectionState textFieldSelectionState = this.this$0;
            long jM1172updateSelectionQNhciaU$default = TextFieldSelectionState.m1172updateSelectionQNhciaU$default(textFieldSelectionState, textFieldSelectionState.textFieldState.getText(), iM5164getOffsetForPositionk4lQ0M, iM5187getEndimpl, this.$isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, 32, null);
            if (TextRange.m5186getCollapsedimpl(selectionInChars) || !TextRange.m5186getCollapsedimpl(jM1172updateSelectionQNhciaU$default)) {
                this.this$0.textFieldState.m1140selectCharsIn5zctL8(jM1172updateSelectionQNhciaU$default);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements r7.a<String> {
        final /* synthetic */ Handle $handle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(Handle handle) {
            super(0);
            this.$handle = handle;
        }

        @Override // r7.a
        public final String invoke() {
            return "Selection Handle drag cancelled for draggingHandle: " + TextFieldSelectionState.this.getDraggingHandle() + " definedOn: " + this.$handle;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "dragStartOffset", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04272 extends r implements l<Offset, t0> {
        final /* synthetic */ kotlin.jvm.internal.k0 $actingHandle;
        final /* synthetic */ i0 $dragBeginOffsetInText;
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ r7.a<t0> $requestFocus;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements r7.a<String> {
            final /* synthetic */ long $dragStartOffset;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(long j10) {
                super(0);
                this.$dragStartOffset = j10;
            }

            @Override // r7.a
            public final String invoke() {
                return "onDragStart after longPress " + ((Object) Offset.m3244toStringimpl(this.$dragStartOffset));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04272(r7.a<t0> aVar, TextFieldSelectionState textFieldSelectionState, kotlin.jvm.internal.k0 k0Var, j0 j0Var, j0 j0Var2, i0 i0Var) {
            super(1);
            this.$requestFocus = aVar;
            this.this$0 = textFieldSelectionState;
            this.$actingHandle = k0Var;
            this.$dragBeginPosition = j0Var;
            this.$dragTotalDistance = j0Var2;
            this.$dragBeginOffsetInText = i0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m1183invokek4lQ0M(((Offset) obj).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
        public final void m1183invokek4lQ0M(long j10) {
            TextFieldSelectionStateKt.logDebug(new AnonymousClass1(j10));
            this.$requestFocus.invoke();
            this.this$0.m1170updateHandleDraggingUv8p0NA((Handle) this.$actingHandle.f19746i, j10);
            this.$dragBeginPosition.f19745i = j10;
            this.$dragTotalDistance.f19745i = Offset.INSTANCE.m3252getZeroF1C5BW0();
            this.this$0.previousRawDragOffset = -1;
            if (!this.this$0.textLayoutState.m1127isPositionOnTextk4lQ0M(j10)) {
                int iM1124getOffsetForPosition3MmeM6k$default = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(this.this$0.textLayoutState, j10, false, 2, null);
                HapticFeedback hapticFeedback = this.this$0.hapticFeedBack;
                if (hapticFeedback != null) {
                    hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
                }
                this.this$0.textFieldState.placeCursorBeforeCharAt(iM1124getOffsetForPosition3MmeM6k$default);
                this.this$0.setShowCursorHandle(true);
                this.this$0.updateTextToolbarState(TextToolbarState.Cursor);
                return;
            }
            if (this.this$0.textFieldState.getText().length() == 0) {
                return;
            }
            int iM1124getOffsetForPosition3MmeM6k$default2 = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(this.this$0.textLayoutState, j10, false, 2, null);
            TextFieldSelectionState textFieldSelectionState = this.this$0;
            long jM1172updateSelectionQNhciaU$default = TextFieldSelectionState.m1172updateSelectionQNhciaU$default(textFieldSelectionState, TextFieldCharSequenceKt.m1067TextFieldCharSequence3r_uNRQ$default(textFieldSelectionState.textFieldState.getText(), TextRange.INSTANCE.m5197getZerod9O1mEE(), null, 4, null), iM1124getOffsetForPosition3MmeM6k$default2, iM1124getOffsetForPosition3MmeM6k$default2, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, 32, null);
            this.this$0.textFieldState.m1140selectCharsIn5zctL8(jM1172updateSelectionQNhciaU$default);
            this.this$0.updateTextToolbarState(TextToolbarState.Selection);
            this.$dragBeginOffsetInText.f19744i = TextRange.m5192getStartimpl(jM1172updateSelectionQNhciaU$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04283 extends r implements r7.a<t0> {
        final /* synthetic */ i0 $dragBeginOffsetInText;
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04283(j0 j0Var, TextFieldSelectionState textFieldSelectionState, i0 i0Var, j0 j0Var2) {
            super(0);
            this.$dragBeginPosition = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$dragBeginOffsetInText = i0Var;
            this.$dragTotalDistance = j0Var2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1184invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1184invoke() {
            TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag$onDragStop$1(this.$dragBeginPosition, this.this$0, this.$dragBeginOffsetInText, this.$dragTotalDistance);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04294 extends r implements r7.a<t0> {
        final /* synthetic */ i0 $dragBeginOffsetInText;
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04294(j0 j0Var, TextFieldSelectionState textFieldSelectionState, i0 i0Var, j0 j0Var2) {
            super(0);
            this.$dragBeginPosition = j0Var;
            this.this$0 = textFieldSelectionState;
            this.$dragBeginOffsetInText = i0Var;
            this.$dragTotalDistance = j0Var2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1185invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1185invoke() {
            TextFieldSelectionState.detectTextFieldLongPressAndAfterDrag$onDragStop$1(this.$dragBeginPosition, this.this$0, this.$dragBeginOffsetInText, this.$dragTotalDistance);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "<anonymous parameter 0>", "Landroidx/compose/ui/geometry/Offset;", "dragAmount", "Lx6/t0;", "invoke-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04305 extends r implements p<PointerInputChange, Offset, t0> {
        final /* synthetic */ kotlin.jvm.internal.k0 $actingHandle;
        final /* synthetic */ i0 $dragBeginOffsetInText;
        final /* synthetic */ j0 $dragBeginPosition;
        final /* synthetic */ j0 $dragTotalDistance;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldLongPressAndAfterDrag$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements r7.a<String> {
            final /* synthetic */ long $currentDragPosition;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(long j10) {
                super(0);
                this.$currentDragPosition = j10;
            }

            @Override // r7.a
            public final String invoke() {
                return "onDrag after longPress " + ((Object) Offset.m3244toStringimpl(this.$currentDragPosition));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04305(j0 j0Var, j0 j0Var2, i0 i0Var, kotlin.jvm.internal.k0 k0Var) {
            super(2);
            this.$dragTotalDistance = j0Var;
            this.$dragBeginPosition = j0Var2;
            this.$dragBeginOffsetInText = i0Var;
            this.$actingHandle = k0Var;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            m1186invokeUv8p0NA((PointerInputChange) obj, ((Offset) obj2).getPackedValue());
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Uv8p0NA, reason: not valid java name */
        public final void m1186invokeUv8p0NA(PointerInputChange pointerInputChange, long j10) {
            int iIntValue;
            int iM1126getOffsetForPosition3MmeM6k;
            SelectionAdjustment word;
            Handle handle;
            if (TextFieldSelectionState.this.textFieldState.getText().length() == 0) {
                return;
            }
            j0 j0Var = this.$dragTotalDistance;
            j0Var.f19745i = Offset.m3241plusMKHz9U(j0Var.f19745i, j10);
            long jM3241plusMKHz9U = Offset.m3241plusMKHz9U(this.$dragBeginPosition.f19745i, this.$dragTotalDistance.f19745i);
            TextFieldSelectionStateKt.logDebug(new AnonymousClass1(jM3241plusMKHz9U));
            if (this.$dragBeginOffsetInText.f19744i >= 0 || TextFieldSelectionState.this.textLayoutState.m1127isPositionOnTextk4lQ0M(jM3241plusMKHz9U)) {
                Integer numValueOf = Integer.valueOf(this.$dragBeginOffsetInText.f19744i);
                if (numValueOf.intValue() < 0) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : TextFieldSelectionState.this.textLayoutState.m1126getOffsetForPosition3MmeM6k(this.$dragBeginPosition.f19745i, false);
                iM1126getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.textLayoutState.m1126getOffsetForPosition3MmeM6k(jM3241plusMKHz9U, false);
                if (this.$dragBeginOffsetInText.f19744i < 0 && iIntValue == iM1126getOffsetForPosition3MmeM6k) {
                    return;
                } else {
                    word = SelectionAdjustment.INSTANCE.getWord();
                }
            } else {
                iIntValue = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, this.$dragBeginPosition.f19745i, false, 2, null);
                iM1126getOffsetForPosition3MmeM6k = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, jM3241plusMKHz9U, false, 2, null);
                word = iIntValue == iM1126getOffsetForPosition3MmeM6k ? SelectionAdjustment.INSTANCE.getNone() : SelectionAdjustment.INSTANCE.getWord();
            }
            int i10 = iIntValue;
            int i11 = iM1126getOffsetForPosition3MmeM6k;
            SelectionAdjustment selectionAdjustment = word;
            long selectionInChars = TextFieldSelectionState.this.textFieldState.getText().getSelectionInChars();
            TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
            long jM1171updateSelectionQNhciaU = textFieldSelectionState.m1171updateSelectionQNhciaU(textFieldSelectionState.textFieldState.getText(), i10, i11, false, selectionAdjustment, false);
            if (TextRange.m5191getReversedimpl(jM1171updateSelectionQNhciaU)) {
                jM1171updateSelectionQNhciaU = TextFieldSelectionStateKt.m1193reverse5zctL8(jM1171updateSelectionQNhciaU);
            }
            if (this.$dragBeginOffsetInText.f19744i == -1 && !TextRange.m5186getCollapsedimpl(jM1171updateSelectionQNhciaU)) {
                this.$dragBeginOffsetInText.f19744i = TextRange.m5192getStartimpl(jM1171updateSelectionQNhciaU);
            }
            if (!TextRange.m5185equalsimpl0(jM1171updateSelectionQNhciaU, selectionInChars)) {
                kotlin.jvm.internal.k0 k0Var = this.$actingHandle;
                if (TextRange.m5192getStartimpl(jM1171updateSelectionQNhciaU) != TextRange.m5192getStartimpl(selectionInChars) && TextRange.m5187getEndimpl(jM1171updateSelectionQNhciaU) == TextRange.m5187getEndimpl(selectionInChars)) {
                    handle = Handle.SelectionStart;
                } else if (TextRange.m5192getStartimpl(jM1171updateSelectionQNhciaU) != TextRange.m5192getStartimpl(selectionInChars) || TextRange.m5187getEndimpl(jM1171updateSelectionQNhciaU) == TextRange.m5187getEndimpl(selectionInChars)) {
                    handle = ((float) (TextRange.m5187getEndimpl(jM1171updateSelectionQNhciaU) + TextRange.m5192getStartimpl(jM1171updateSelectionQNhciaU))) / 2.0f > ((float) (TextRange.m5187getEndimpl(selectionInChars) + TextRange.m5192getStartimpl(selectionInChars))) / 2.0f ? Handle.SelectionEnd : Handle.SelectionStart;
                } else {
                    handle = Handle.SelectionEnd;
                }
                k0Var.f19746i = handle;
            }
            if (TextRange.m5186getCollapsedimpl(selectionInChars) || !TextRange.m5186getCollapsedimpl(jM1171updateSelectionQNhciaU)) {
                TextFieldSelectionState.this.textFieldState.m1140selectCharsIn5zctL8(jM1171updateSelectionQNhciaU);
            }
            TextFieldSelectionState.this.m1170updateHandleDraggingUv8p0NA((Handle) this.$actingHandle.f19746i, jM3241plusMKHz9U);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTouchMode$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTouchMode$2", f = "TextFieldSelectionState.kt", l = {425}, m = "invokeSuspend")
    public static final class C04332 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C04332(d<? super C04332> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04332 c04332 = TextFieldSelectionState.this.new C04332(dVar);
            c04332.L$0 = obj;
            return c04332;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
            return ((C04332) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x002d A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002b -> B:12:0x002e). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                int r0 = r3.label
                r1 = 1
                if (r0 == 0) goto L17
                if (r0 != r1) goto Lf
                java.lang.Object r0 = r3.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                k2.c.G(r4)
                goto L2e
            Lf:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            L17:
                k2.c.G(r4)
                java.lang.Object r4 = r3.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                r0 = r4
            L1f:
                androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                r3.L$0 = r0
                r3.label = r1
                java.lang.Object r4 = r0.awaitPointerEvent(r4, r3)
                e7.a r2 = e7.a.f15033i
                if (r4 != r2) goto L2e
                return r2
            L2e:
                androidx.compose.ui.input.pointer.PointerEvent r4 = (androidx.compose.ui.input.pointer.PointerEvent) r4
                androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r2 = androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.this
                boolean r4 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r4)
                r4 = r4 ^ r1
                androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.access$setInTouchMode(r2, r4)
                goto L1f
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C04332.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", l = {393}, m = "observeChanges")
    public static final class C04341 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C04341(d<? super C04341> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.observeChanges(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lka/v1;", "<anonymous>", "(Lka/k0;)Lka/v1;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2", f = "TextFieldSelectionState.kt", l = {}, m = "invokeSuspend")
    public static final class C04352 extends j implements p<k0, d<? super v1>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$1", f = "TextFieldSelectionState.kt", l = {394}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    this.label = 1;
                    Object objObserveTextChanges = textFieldSelectionState.observeTextChanges(this);
                    a aVar = a.f15033i;
                    if (objObserveTextChanges == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2$2", f = "TextFieldSelectionState.kt", l = {395}, m = "invokeSuspend")
        public static final class C00752 extends j implements p<k0, d<? super t0>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00752(TextFieldSelectionState textFieldSelectionState, d<? super C00752> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00752(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    this.label = 1;
                    Object objObserveTextToolbarVisibility = textFieldSelectionState.observeTextToolbarVisibility(this);
                    a aVar = a.f15033i;
                    if (objObserveTextToolbarVisibility == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00752) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public C04352(d<? super C04352> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04352 c04352 = TextFieldSelectionState.this.new C04352(dVar);
            c04352.L$0 = obj;
            return c04352;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            m0.p(k0Var, null, 0, new AnonymousClass1(TextFieldSelectionState.this, null), 3);
            return m0.p(k0Var, null, 0, new C00752(TextFieldSelectionState.this, null), 3);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super v1> dVar) {
            return ((C04352) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04362 extends r implements r7.a<TextFieldCharSequence> {
        public C04362() {
            super(0);
        }

        @Override // r7.a
        public final TextFieldCharSequence invoke() {
            return TextFieldSelectionState.this.textFieldState.getText();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class C04373 extends m implements p<TextFieldCharSequence, CharSequence, Boolean> {
        public static final C04373 INSTANCE = new C04373();

        public C04373() {
            super(2, TextFieldCharSequence.class, "contentEquals", "contentEquals(Ljava/lang/CharSequence;)Z", 0);
        }

        @Override // r7.p
        public final Boolean invoke(TextFieldCharSequence textFieldCharSequence, CharSequence charSequence) {
            return Boolean.valueOf(textFieldCharSequence.contentEquals(charSequence));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextToolbarVisibility$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Rect;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04392 extends r implements r7.a<Rect> {
        public C04392() {
            super(0);
        }

        @Override // r7.a
        public final Rect invoke() {
            Rect rectIntersect;
            boolean zM5186getCollapsedimpl = TextRange.m5186getCollapsedimpl(TextFieldSelectionState.this.textFieldState.getText().getSelectionInChars());
            if (((!zM5186getCollapsedimpl || TextFieldSelectionState.this.getTextToolbarState() != TextToolbarState.Cursor) && (zM5186getCollapsedimpl || TextFieldSelectionState.this.getTextToolbarState() != TextToolbarState.Selection)) || TextFieldSelectionState.this.getDraggingHandle() != null || !TextFieldSelectionState.this.isInTouchMode()) {
                return Rect.INSTANCE.getZero();
            }
            LayoutCoordinates textLayoutCoordinates = TextFieldSelectionState.this.getTextLayoutCoordinates();
            Rect rectVisibleBounds = textLayoutCoordinates != null ? SelectionManagerKt.visibleBounds(textLayoutCoordinates) : null;
            if (rectVisibleBounds == null) {
                return Rect.INSTANCE.getZero();
            }
            LayoutCoordinates textLayoutCoordinates2 = TextFieldSelectionState.this.getTextLayoutCoordinates();
            Rect rectM3276Recttz77jQw = RectKt.m3276Recttz77jQw((textLayoutCoordinates2 != null ? Offset.m3225boximpl(textLayoutCoordinates2.mo4640localToRootMKHz9U(rectVisibleBounds.m3271getTopLeftF1C5BW0())) : null).getPackedValue(), rectVisibleBounds.m3269getSizeNHjbRc());
            Rect contentRect = TextFieldSelectionState.this.getContentRect();
            Rect rect = rectM3276Recttz77jQw.overlaps(contentRect) ? contentRect : null;
            return (rect == null || (rectIntersect = rect.intersect(rectM3276Recttz77jQw)) == null) ? Rect.INSTANCE.getZero() : rectIntersect;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lka/v1;", "<anonymous>", "(Lka/k0;)Lka/v1;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2", f = "TextFieldSelectionState.kt", l = {}, m = "invokeSuspend")
    public static final class C04412 extends j implements p<k0, d<? super v1>, Object> {
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1", f = "TextFieldSelectionState.kt", l = {361}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$this_selectionHandleGestures, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    this.label = 1;
                    Object objDetectTouchMode = textFieldSelectionState.detectTouchMode(pointerInputScope, this);
                    a aVar = a.f15033i;
                    if (objDetectTouchMode == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2", f = "TextFieldSelectionState.kt", l = {364}, m = "invokeSuspend")
        public static final class C00762 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00772 extends r implements r7.a<t0> {
                final /* synthetic */ TextFieldSelectionState this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00772(TextFieldSelectionState textFieldSelectionState) {
                    super(0);
                    this.this$0 = textFieldSelectionState;
                }

                @Override // r7.a
                public /* bridge */ /* synthetic */ Object invoke() {
                    m1187invoke();
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m1187invoke() {
                    this.this$0.clearHandleDragging();
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00762(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, boolean z, d<? super C00762> dVar) {
                super(2, dVar);
                this.$this_selectionHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
                this.$isStartHandle = z;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00762(this.$this_selectionHandleGestures, this.this$0, this.$isStartHandle, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    final TextFieldSelectionState textFieldSelectionState = this.this$0;
                    final boolean z = this.$isStartHandle;
                    TapOnPosition tapOnPosition = new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.1
                        @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
                        /* JADX INFO: renamed from: onEvent-k-4lQ0M */
                        public final void mo1146onEventk4lQ0M(long j10) {
                            textFieldSelectionState.markStartContentVisibleOffset();
                            TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                            boolean z5 = z;
                            textFieldSelectionState2.m1170updateHandleDraggingUv8p0NA(z5 ? Handle.SelectionStart : Handle.SelectionEnd, SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(textFieldSelectionState2.m1164getHandlePositiontuRUvjQ(z5)));
                        }
                    };
                    C00772 c00772 = new C00772(this.this$0);
                    this.label = 1;
                    Object objDetectPressDownGesture = PressDownGestureKt.detectPressDownGesture(pointerInputScope, tapOnPosition, c00772, this);
                    a aVar = a.f15033i;
                    if (objDetectPressDownGesture == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00762) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$3", f = "TextFieldSelectionState.kt", l = {382}, m = "invokeSuspend")
        public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, boolean z, d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
                this.$isStartHandle = z;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass3(this.this$0, this.$this_selectionHandleGestures, this.$isStartHandle, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                    boolean z = this.$isStartHandle;
                    this.label = 1;
                    Object objDetectSelectionHandleDragGestures = textFieldSelectionState.detectSelectionHandleDragGestures(pointerInputScope, z, this);
                    a aVar = a.f15033i;
                    if (objDetectSelectionHandleDragGestures == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04412(PointerInputScope pointerInputScope, boolean z, d<? super C04412> dVar) {
            super(2, dVar);
            this.$this_selectionHandleGestures = pointerInputScope;
            this.$isStartHandle = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04412 c04412 = TextFieldSelectionState.this.new C04412(this.$this_selectionHandleGestures, this.$isStartHandle, dVar);
            c04412.L$0 = obj;
            return c04412;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            m0.p(k0Var, null, 4, new AnonymousClass1(TextFieldSelectionState.this, this.$this_selectionHandleGestures, null), 1);
            m0.p(k0Var, null, 4, new C00762(this.$this_selectionHandleGestures, TextFieldSelectionState.this, this.$isStartHandle, null), 1);
            return m0.p(k0Var, null, 4, new AnonymousClass3(TextFieldSelectionState.this, this.$this_selectionHandleGestures, this.$isStartHandle, null), 1);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super v1> dVar) {
            return ((C04412) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lka/v1;", "<anonymous>", "(Lka/k0;)Lka/v1;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2", f = "TextFieldSelectionState.kt", l = {}, m = "invokeSuspend")
    public static final class C04422 extends j implements p<k0, d<? super v1>, Object> {
        final /* synthetic */ r7.a<t0> $requestFocus;
        final /* synthetic */ r7.a<t0> $showKeyboard;
        final /* synthetic */ PointerInputScope $this_textFieldGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$1", f = "TextFieldSelectionState.kt", l = {344}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.this$0, this.$this_textFieldGestures, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_textFieldGestures;
                    this.label = 1;
                    Object objDetectTouchMode = textFieldSelectionState.detectTouchMode(pointerInputScope, this);
                    a aVar = a.f15033i;
                    if (objDetectTouchMode == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$2", f = "TextFieldSelectionState.kt", l = {347}, m = "invokeSuspend")
        public static final class C00782 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ r7.a<t0> $requestFocus;
            final /* synthetic */ r7.a<t0> $showKeyboard;
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00782(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, r7.a<t0> aVar, r7.a<t0> aVar2, d<? super C00782> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
                this.$requestFocus = aVar;
                this.$showKeyboard = aVar2;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00782(this.this$0, this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_textFieldGestures;
                    r7.a<t0> aVar = this.$requestFocus;
                    r7.a<t0> aVar2 = this.$showKeyboard;
                    this.label = 1;
                    Object objDetectTextFieldTapGestures = textFieldSelectionState.detectTextFieldTapGestures(pointerInputScope, aVar, aVar2, this);
                    a aVar3 = a.f15033i;
                    if (objDetectTextFieldTapGestures == aVar3) {
                        return aVar3;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00782) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$textFieldGestures$2$3", f = "TextFieldSelectionState.kt", l = {350}, m = "invokeSuspend")
        public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ r7.a<t0> $requestFocus;
            final /* synthetic */ PointerInputScope $this_textFieldGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, r7.a<t0> aVar, d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = textFieldSelectionState;
                this.$this_textFieldGestures = pointerInputScope;
                this.$requestFocus = aVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass3(this.this$0, this.$this_textFieldGestures, this.$requestFocus, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    TextFieldSelectionState textFieldSelectionState = this.this$0;
                    PointerInputScope pointerInputScope = this.$this_textFieldGestures;
                    r7.a<t0> aVar = this.$requestFocus;
                    this.label = 1;
                    Object objDetectTextFieldLongPressAndAfterDrag = textFieldSelectionState.detectTextFieldLongPressAndAfterDrag(pointerInputScope, aVar, this);
                    a aVar2 = a.f15033i;
                    if (objDetectTextFieldLongPressAndAfterDrag == aVar2) {
                        return aVar2;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04422(PointerInputScope pointerInputScope, r7.a<t0> aVar, r7.a<t0> aVar2, d<? super C04422> dVar) {
            super(2, dVar);
            this.$this_textFieldGestures = pointerInputScope;
            this.$requestFocus = aVar;
            this.$showKeyboard = aVar2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04422 c04422 = TextFieldSelectionState.this.new C04422(this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, dVar);
            c04422.L$0 = obj;
            return c04422;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            k0 k0Var = (k0) this.L$0;
            m0.p(k0Var, null, 4, new AnonymousClass1(TextFieldSelectionState.this, this.$this_textFieldGestures, null), 1);
            m0.p(k0Var, null, 4, new C00782(TextFieldSelectionState.this, this.$this_textFieldGestures, this.$requestFocus, this.$showKeyboard, null), 1);
            return m0.p(k0Var, null, 4, new AnonymousClass3(TextFieldSelectionState.this, this.$this_textFieldGestures, this.$requestFocus, null), 1);
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super v1> dVar) {
            return ((C04422) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, Density density, boolean z, boolean z5, boolean z10) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.enabled = z;
        this.readOnly = z5;
        this.isFocused = z10;
        Offset.Companion companion = Offset.INSTANCE;
        this.startContentVisibleOffset = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(companion.m3251getUnspecifiedF1C5BW0()), null, 2, null);
        this.rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(companion.m3251getUnspecifiedF1C5BW0()), null, 2, null);
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);
        this.previousRawDragOffset = -1;
        this.cursorHandle = SnapshotStateKt.derivedStateOf(new TextFieldSelectionState$cursorHandle$2(this));
        this.cursorHandleInBounds = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new TextFieldSelectionState$cursorHandleInBounds$2(this));
        this.cursorRect = SnapshotStateKt.derivedStateOf(new TextFieldSelectionState$cursorRect$2(this));
        this.startSelectionHandle = SnapshotStateKt.derivedStateOf(new TextFieldSelectionState$startSelectionHandle$2(this));
        this.endSelectionHandle = SnapshotStateKt.derivedStateOf(new TextFieldSelectionState$endSelectionHandle$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearHandleDragging() {
        setDraggingHandle(null);
        Offset.Companion companion = Offset.INSTANCE;
        m1168setRawHandleDragPositionk4lQ0M(companion.m3251getUnspecifiedF1C5BW0());
        m1169setStartContentVisibleOffsetk4lQ0M(companion.m3251getUnspecifiedF1C5BW0());
    }

    public static /* synthetic */ void copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        textFieldSelectionState.copy(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1
            r0.<init>(r10)
            goto L12
        L1a:
            java.lang.Object r10 = r6.result
            int r0 = r6.label
            r1 = 1
            if (r0 == 0) goto L40
            if (r0 != r1) goto L38
            java.lang.Object r9 = r6.L$2
            kotlin.jvm.internal.j0 r9 = (kotlin.jvm.internal.j0) r9
            java.lang.Object r0 = r6.L$1
            r1 = r0
            kotlin.jvm.internal.j0 r1 = (kotlin.jvm.internal.j0) r1
            java.lang.Object r0 = r6.L$0
            r2 = r0
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r2 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState) r2
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L35
            goto L84
        L35:
            r0 = move-exception
            r10 = r0
            goto L90
        L38:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L40:
            k2.c.G(r10)
            kotlin.jvm.internal.j0 r10 = new kotlin.jvm.internal.j0
            r10.<init>()
            androidx.compose.ui.geometry.Offset$Companion r0 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r0.m3251getUnspecifiedF1C5BW0()
            r10.f19745i = r2
            kotlin.jvm.internal.j0 r7 = new kotlin.jvm.internal.j0
            r7.<init>()
            long r2 = r0.m3251getUnspecifiedF1C5BW0()
            r7.f19745i = r2
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2 r2 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$2     // Catch: java.lang.Throwable -> L8a
            r2.<init>(r10, r8, r7)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3 r3 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$3     // Catch: java.lang.Throwable -> L8a
            r3.<init>(r10, r7, r8)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4 r4 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$4     // Catch: java.lang.Throwable -> L8a
            r4.<init>(r10, r7, r8)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5 r5 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$5     // Catch: java.lang.Throwable -> L8a
            r5.<init>(r7, r8, r10)     // Catch: java.lang.Throwable -> L8a
            r6.L$0 = r8     // Catch: java.lang.Throwable -> L8a
            r6.L$1 = r10     // Catch: java.lang.Throwable -> L8a
            r6.L$2 = r7     // Catch: java.lang.Throwable -> L8a
            r6.label = r1     // Catch: java.lang.Throwable -> L8a
            r1 = r9
            java.lang.Object r9 = androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L8a
            e7.a r0 = e7.a.f15033i
            if (r9 != r0) goto L81
            return r0
        L81:
            r2 = r8
            r1 = r10
            r9 = r7
        L84:
            detectCursorHandleDragGestures$onDragStop(r1, r9, r2)
            x6.t0 r9 = x6.t0.f22605a
            return r9
        L8a:
            r0 = move-exception
            r9 = r0
            r2 = r8
            r1 = r10
            r10 = r9
            r9 = r7
        L90:
            detectCursorHandleDragGestures$onDragStop(r1, r9, r2)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectCursorHandleDragGestures$onDragStop(j0 j0Var, j0 j0Var2, TextFieldSelectionState textFieldSelectionState) {
        if (OffsetKt.m3255isSpecifiedk4lQ0M(j0Var.f19745i)) {
            Offset.Companion companion = Offset.INSTANCE;
            j0Var.f19745i = companion.m3251getUnspecifiedF1C5BW0();
            j0Var2.f19745i = companion.m3251getUnspecifiedF1C5BW0();
            textFieldSelectionState.clearHandleDragging();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r14, boolean r15, d7.d<? super x6.t0> r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, boolean, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectSelectionHandleDragGestures$onDragStop$2(j0 j0Var, TextFieldSelectionState textFieldSelectionState, j0 j0Var2) {
        if (OffsetKt.m3255isSpecifiedk4lQ0M(j0Var.f19745i)) {
            textFieldSelectionState.clearHandleDragging();
            Offset.Companion companion = Offset.INSTANCE;
            j0Var.f19745i = companion.m3251getUnspecifiedF1C5BW0();
            j0Var2.f19745i = companion.m3252getZeroF1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectTextFieldLongPressAndAfterDrag(PointerInputScope pointerInputScope, r7.a<t0> aVar, d<? super t0> dVar) {
        i0 i0Var = new i0();
        i0Var.f19744i = -1;
        j0 j0Var = new j0();
        Offset.Companion companion = Offset.INSTANCE;
        j0Var.f19745i = companion.m3251getUnspecifiedF1C5BW0();
        j0 j0Var2 = new j0();
        j0Var2.f19745i = companion.m3252getZeroF1C5BW0();
        kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
        k0Var.f19746i = Handle.SelectionEnd;
        Object objDetectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new C04272(aVar, this, k0Var, j0Var, j0Var2, i0Var), new C04283(j0Var, this, i0Var, j0Var2), new C04294(j0Var, this, i0Var, j0Var2), new C04305(j0Var2, j0Var, i0Var, k0Var), dVar);
        return objDetectDragGesturesAfterLongPress == a.f15033i ? objDetectDragGesturesAfterLongPress : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detectTextFieldLongPressAndAfterDrag$onDragStop$1(j0 j0Var, TextFieldSelectionState textFieldSelectionState, i0 i0Var, j0 j0Var2) {
        if (OffsetKt.m3255isSpecifiedk4lQ0M(j0Var.f19745i)) {
            textFieldSelectionState.clearHandleDragging();
            i0Var.f19744i = -1;
            Offset.Companion companion = Offset.INSTANCE;
            j0Var.f19745i = companion.m3251getUnspecifiedF1C5BW0();
            j0Var2.f19745i = companion.m3252getZeroF1C5BW0();
            textFieldSelectionState.previousRawDragOffset = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectTextFieldTapGestures(PointerInputScope pointerInputScope, final r7.a<t0> aVar, final r7.a<t0> aVar2, d<? super t0> dVar) {
        Object objDetectTapAndDoubleTap = TapAndDoubleTapGestureKt.detectTapAndDoubleTap(pointerInputScope, new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.2

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$2$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass1 extends r implements r7.a<String> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                public AnonymousClass1() {
                    super(0);
                }

                @Override // r7.a
                public final String invoke() {
                    return "onTapTextField";
                }
            }

            @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
            public final void mo1146onEventk4lQ0M(long j10) {
                TextFieldSelectionStateKt.logDebug(AnonymousClass1.INSTANCE);
                aVar.invoke();
                if (this.getEditable() && this.getIsFocused()) {
                    aVar2.invoke();
                    if (this.textFieldState.getText().length() > 0) {
                        this.setShowCursorHandle(true);
                    }
                    this.updateTextToolbarState(TextToolbarState.None);
                    int iM1124getOffsetForPosition3MmeM6k$default = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(this.textLayoutState, j10, false, 2, null);
                    if (iM1124getOffsetForPosition3MmeM6k$default >= 0) {
                        this.textFieldState.placeCursorBeforeCharAt(iM1124getOffsetForPosition3MmeM6k$default);
                    }
                }
            }
        }, new TapOnPosition() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.detectTextFieldTapGestures.3

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$detectTextFieldTapGestures$3$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass1 extends r implements r7.a<String> {
                public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                public AnonymousClass1() {
                    super(0);
                }

                @Override // r7.a
                public final String invoke() {
                    return "onDoubleTapTextField";
                }
            }

            @Override // androidx.compose.foundation.text2.input.internal.selection.TapOnPosition
            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
            public final void mo1146onEventk4lQ0M(long j10) {
                TextFieldSelectionStateKt.logDebug(AnonymousClass1.INSTANCE);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                int iM1124getOffsetForPosition3MmeM6k$default = TextLayoutState.m1124getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.textLayoutState, j10, false, 2, null);
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                TextFieldSelectionState.this.textFieldState.m1140selectCharsIn5zctL8(TextFieldSelectionState.m1172updateSelectionQNhciaU$default(textFieldSelectionState, TextFieldCharSequenceKt.m1067TextFieldCharSequence3r_uNRQ$default(textFieldSelectionState.textFieldState.getText(), TextRange.INSTANCE.m5197getZerod9O1mEE(), null, 4, null), iM1124getOffsetForPosition3MmeM6k$default, iM1124getOffsetForPosition3MmeM6k$default, false, SelectionAdjustment.INSTANCE.getWord(), false, 32, null));
            }
        }, dVar);
        return objDetectTapAndDoubleTap == a.f15033i ? objDetectTapAndDoubleTap : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectTouchMode(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C04332(null), dVar);
        return objAwaitPointerEventScope == a.f15033i ? objAwaitPointerEventScope : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        float fM3237getYimpl;
        Rect cursorRect;
        Rect cursorRect2;
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5186getCollapsedimpl(text.getSelectionInChars())) {
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            return RectKt.m3276Recttz77jQw(textLayoutCoordinates != null ? textLayoutCoordinates.mo4640localToRootMKHz9U(getCursorRect().m3271getTopLeftF1C5BW0()) : Offset.INSTANCE.m3252getZeroF1C5BW0(), getCursorRect().m3269getSizeNHjbRc());
        }
        LayoutCoordinates textLayoutCoordinates2 = getTextLayoutCoordinates();
        long jMo4640localToRootMKHz9U = textLayoutCoordinates2 != null ? textLayoutCoordinates2.mo4640localToRootMKHz9U(m1164getHandlePositiontuRUvjQ(true)) : Offset.INSTANCE.m3252getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates3 = getTextLayoutCoordinates();
        long jMo4640localToRootMKHz9U2 = textLayoutCoordinates3 != null ? textLayoutCoordinates3.mo4640localToRootMKHz9U(m1164getHandlePositiontuRUvjQ(false)) : Offset.INSTANCE.m3252getZeroF1C5BW0();
        LayoutCoordinates textLayoutCoordinates4 = getTextLayoutCoordinates();
        float fM3237getYimpl2 = 0.0f;
        if (textLayoutCoordinates4 != null) {
            TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
            fM3237getYimpl = Offset.m3237getYimpl(textLayoutCoordinates4.mo4640localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (cursorRect2 = layoutResult.getCursorRect(TextRange.m5192getStartimpl(text.getSelectionInChars()))) == null) ? 0.0f : cursorRect2.getTop())));
        } else {
            fM3237getYimpl = 0.0f;
        }
        LayoutCoordinates textLayoutCoordinates5 = getTextLayoutCoordinates();
        if (textLayoutCoordinates5 != null) {
            TextLayoutResult layoutResult2 = this.textLayoutState.getLayoutResult();
            fM3237getYimpl2 = Offset.m3237getYimpl(textLayoutCoordinates5.mo4640localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (cursorRect = layoutResult2.getCursorRect(TextRange.m5187getEndimpl(text.getSelectionInChars()))) == null) ? 0.0f : cursorRect.getTop())));
        }
        return new Rect(Math.min(Offset.m3236getXimpl(jMo4640localToRootMKHz9U), Offset.m3236getXimpl(jMo4640localToRootMKHz9U2)), Math.min(fM3237getYimpl, fM3237getYimpl2), Math.max(Offset.m3236getXimpl(jMo4640localToRootMKHz9U), Offset.m3236getXimpl(jMo4640localToRootMKHz9U2)), Math.max(Offset.m3237getYimpl(jMo4640localToRootMKHz9U), Offset.m3237getYimpl(jMo4640localToRootMKHz9U2)));
    }

    /* JADX INFO: renamed from: getCurrentContentVisibleOffset-F1C5BW0, reason: not valid java name */
    private final long m1163getCurrentContentVisibleOffsetF1C5BW0() {
        Rect rectVisibleBounds;
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return (textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : rectVisibleBounds.m3271getTopLeftF1C5BW0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getCursorHandleInBounds() {
        return ((Boolean) this.cursorHandleInBounds.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getEditable() {
        return this.enabled && !this.readOnly;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1164getHandlePositiontuRUvjQ(boolean isStartHandle) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m3252getZeroF1C5BW0();
        }
        long selectionInChars = this.textFieldState.getText().getSelectionInChars();
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, isStartHandle ? TextRange.m5192getStartimpl(selectionInChars) : TextRange.m5187getEndimpl(selectionInChars), isStartHandle, TextRange.m5191getReversedimpl(selectionInChars));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1165getRawHandleDragPositionF1C5BW0() {
        return ((Offset) this.rawHandleDragPosition.getValue()).getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState getSelectionHandleState(boolean r14) {
        /*
            r13 = this;
            if (r14 == 0) goto L5
            androidx.compose.foundation.text.Handle r0 = androidx.compose.foundation.text.Handle.SelectionStart
            goto L7
        L5:
            androidx.compose.foundation.text.Handle r0 = androidx.compose.foundation.text.Handle.SelectionEnd
        L7:
            androidx.compose.foundation.text2.input.internal.TextLayoutState r1 = r13.textLayoutState
            androidx.compose.ui.text.TextLayoutResult r1 = r1.getLayoutResult()
            if (r1 != 0) goto L16
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState$Companion r14 = androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState r14 = r14.getHidden()
            return r14
        L16:
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState r2 = r13.textFieldState
            androidx.compose.foundation.text2.input.TextFieldCharSequence r2 = r2.getText()
            long r2 = r2.getSelectionInChars()
            boolean r4 = androidx.compose.ui.text.TextRange.m5186getCollapsedimpl(r2)
            if (r4 == 0) goto L2d
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState$Companion r14 = androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState r14 = r14.getHidden()
            return r14
        L2d:
            long r4 = r13.m1164getHandlePositiontuRUvjQ(r14)
            androidx.compose.foundation.text.Handle r6 = r13.getDraggingHandle()
            r7 = 1
            r8 = 0
            if (r6 == r0) goto L50
            androidx.compose.ui.layout.LayoutCoordinates r0 = r13.getTextLayoutCoordinates()
            if (r0 == 0) goto L4a
            androidx.compose.ui.geometry.Rect r0 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r0)
            if (r0 == 0) goto L4a
            boolean r0 = androidx.compose.foundation.text.selection.SelectionManagerKt.m1014containsInclusiveUv8p0NA(r0, r4)
            goto L4b
        L4a:
            r0 = r8
        L4b:
            if (r0 == 0) goto L4e
            goto L50
        L4e:
            r0 = r8
            goto L51
        L50:
            r0 = r7
        L51:
            if (r0 != 0) goto L5a
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState$Companion r14 = androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState r14 = r14.getHidden()
            return r14
        L5a:
            if (r14 == 0) goto L61
            int r14 = androidx.compose.ui.text.TextRange.m5192getStartimpl(r2)
            goto L6a
        L61:
            int r14 = androidx.compose.ui.text.TextRange.m5187getEndimpl(r2)
            int r14 = r14 - r7
            int r14 = java.lang.Math.max(r14, r8)
        L6a:
            androidx.compose.ui.text.style.ResolvedTextDirection r10 = r1.getBidiRunDirection(r14)
            boolean r11 = androidx.compose.ui.text.TextRange.m5191getReversedimpl(r2)
            androidx.compose.ui.layout.LayoutCoordinates r14 = r13.getTextLayoutCoordinates()
            if (r14 == 0) goto L82
            androidx.compose.ui.geometry.Rect r14 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r14)
            if (r14 == 0) goto L82
            long r4 = androidx.compose.foundation.text2.input.internal.TextLayoutStateKt.m1130coerceIn3MmeM6k(r4, r14)
        L82:
            r8 = r4
            androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState r6 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState
            r7 = 1
            r12 = 0
            r6.<init>(r7, r8, r10, r11, r12)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.getSelectionHandleState(boolean):androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getShowCursorHandle() {
        return ((Boolean) this.showCursorHandle.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getStartContentVisibleOffset-F1C5BW0, reason: not valid java name */
    private final long m1166getStartContentVisibleOffsetF1C5BW0() {
        return ((Offset) this.startContentVisibleOffset.getValue()).getPackedValue();
    }

    /* JADX INFO: renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1167getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m5197getZerod9O1mEE();
        }
        if (previousSelection == null && kotlin.jvm.internal.p.a(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayoutM977getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m977getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m5197getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayoutM977getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long jM966toTextRanged9O1mEE = adjustment.adjust(selectionLayoutM977getTextFieldSelectionLayoutRcvTLA).m966toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayoutM977getTextFieldSelectionLayoutRcvTLA;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return jM966toTextRanged9O1mEE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates textLayoutNodeCoordinates = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates == null || !textLayoutNodeCoordinates.isAttached()) {
            return null;
        }
        return textLayoutNodeCoordinates;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final TextToolbarState getTextToolbarState() {
        return (TextToolbarState) this.textToolbarState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        Rect rectVisibleBounds;
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        m1169setStartContentVisibleOffsetk4lQ0M((textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : rectVisibleBounds.m3271getTopLeftF1C5BW0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeTextChanges(d7.d<? super x6.t0> r5) {
        /*
            r4 = this;
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$2 r0 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$2
            r0.<init>()
            na.j r0 = androidx.compose.runtime.SnapshotStateKt.snapshotFlow(r0)
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$3 r1 = androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C04373.INSTANCE
            r2 = 2
            kotlin.jvm.internal.q0.e(r2, r1)
            boolean r2 = r0 instanceof na.h
            if (r2 == 0) goto L1b
            r2 = r0
            na.h r2 = (na.h) r2
            r7.p r3 = r2.f20575l
            if (r3 != r1) goto L1b
            goto L20
        L1b:
            na.h r2 = new na.h
            r2.<init>(r0, r1)
        L20:
            r0 = 1
            na.v r0 = na.y0.i(r2, r0)
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$4 r1 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeTextChanges$4
            r1.<init>()
            java.lang.Object r5 = r0.collect(r1, r5)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L33
            return r5
        L33:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextChanges(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(d<? super t0> dVar) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new C04392()).collect(new k() { // from class: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.3
            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d dVar2) {
                return emit((Rect) obj, (d<? super t0>) dVar2);
            }

            public final Object emit(Rect rect, d<? super t0> dVar2) {
                if (kotlin.jvm.internal.p.a(rect, Rect.INSTANCE.getZero())) {
                    TextFieldSelectionState.this.hideTextToolbar();
                } else {
                    TextFieldSelectionState.this.showTextToolbar(rect);
                }
                return t0.f22605a;
            }
        }, dVar);
        return objCollect == a.f15033i ? objCollect : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setInTouchMode(boolean z) {
        this.isInTouchMode.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1168setRawHandleDragPositionk4lQ0M(long j10) {
        this.rawHandleDragPosition.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowCursorHandle(boolean z) {
        this.showCursorHandle.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: setStartContentVisibleOffset-k-4lQ0M, reason: not valid java name */
    private final void m1169setStartContentVisibleOffsetk4lQ0M(long j10) {
        this.startContentVisibleOffset.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        this.textToolbarState.setValue(textToolbarState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTextToolbar(Rect contentRect) {
        ClipboardManager clipboardManager;
        long selectionInChars = this.textFieldState.getText().getSelectionInChars();
        TextFieldSelectionState$showTextToolbar$paste$1 textFieldSelectionState$showTextToolbar$paste$1 = (getEditable() && (clipboardManager = this.clipboardManager) != null && clipboardManager.hasText()) ? new TextFieldSelectionState$showTextToolbar$paste$1(this) : null;
        TextFieldSelectionState$showTextToolbar$copy$1 textFieldSelectionState$showTextToolbar$copy$1 = !TextRange.m5186getCollapsedimpl(selectionInChars) ? new TextFieldSelectionState$showTextToolbar$copy$1(this) : null;
        TextFieldSelectionState$showTextToolbar$cut$1 textFieldSelectionState$showTextToolbar$cut$1 = (TextRange.m5186getCollapsedimpl(selectionInChars) || !getEditable()) ? null : new TextFieldSelectionState$showTextToolbar$cut$1(this);
        TextFieldSelectionState$showTextToolbar$selectAll$1 textFieldSelectionState$showTextToolbar$selectAll$1 = TextRange.m5188getLengthimpl(selectionInChars) != this.textFieldState.getText().length() ? new TextFieldSelectionState$showTextToolbar$selectAll$1(this) : null;
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar != null) {
            textToolbar.showMenu(contentRect, textFieldSelectionState$showTextToolbar$copy$1, textFieldSelectionState$showTextToolbar$paste$1, textFieldSelectionState$showTextToolbar$cut$1, textFieldSelectionState$showTextToolbar$selectAll$1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1170updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1168setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSelection-QNhciaU, reason: not valid java name */
    public final long m1171updateSelectionQNhciaU(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed) {
        HapticFeedback hapticFeedback;
        TextRange textRangeM5180boximpl = TextRange.m5180boximpl(textFieldCharSequence.getSelectionInChars());
        long packedValue = textRangeM5180boximpl.getPackedValue();
        if (!allowPreviousSelectionCollapsed && TextRange.m5186getCollapsedimpl(packedValue)) {
            textRangeM5180boximpl = null;
        }
        long jM1167getTextFieldSelectionqeG_v_k = m1167getTextFieldSelectionqeG_v_k(startOffset, endOffset, textRangeM5180boximpl, isStartHandle, adjustment);
        if (!TextRange.m5185equalsimpl0(jM1167getTextFieldSelectionqeG_v_k, textFieldCharSequence.getSelectionInChars())) {
            boolean z = TextRange.m5191getReversedimpl(jM1167getTextFieldSelectionqeG_v_k) != TextRange.m5191getReversedimpl(textFieldCharSequence.getSelectionInChars()) && TextRange.m5185equalsimpl0(TextRangeKt.TextRange(TextRange.m5187getEndimpl(jM1167getTextFieldSelectionqeG_v_k), TextRange.m5192getStartimpl(jM1167getTextFieldSelectionqeG_v_k)), textFieldCharSequence.getSelectionInChars());
            if (isInTouchMode() && !z && (hapticFeedback = this.hapticFeedBack) != null) {
                hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
            }
        }
        return jM1167getTextFieldSelectionqeG_v_k;
    }

    /* JADX INFO: renamed from: updateSelection-QNhciaU$default, reason: not valid java name */
    public static /* synthetic */ long m1172updateSelectionQNhciaU$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i10, int i11, boolean z, SelectionAdjustment selectionAdjustment, boolean z5, int i12, Object obj) {
        if ((i12 & 32) != 0) {
            z5 = false;
        }
        return textFieldSelectionState.m1171updateSelectionQNhciaU(textFieldCharSequence, i10, i11, z, selectionAdjustment, z5);
    }

    public final void copy(boolean cancelSelection) {
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5186getCollapsedimpl(text.getSelectionInChars())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
    }

    public final Object cursorHandleGestures(PointerInputScope pointerInputScope, d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(pointerInputScope, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public final void cut() {
        TextFieldCharSequence text = this.textFieldState.getText();
        if (TextRange.m5186getCollapsedimpl(text.getSelectionInChars())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(new AnnotatedString(TextFieldCharSequenceKt.getSelectedText(text).toString(), null, null, 6, null));
        }
        this.textFieldState.deleteSelectedText();
    }

    public final void deselect() {
        if (!TextRange.m5186getCollapsedimpl(this.textFieldState.getText().getSelectionInChars())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    public final void dispose() {
        hideTextToolbar();
        this.textToolbar = null;
        this.clipboardManager = null;
        this.hapticFeedBack = null;
    }

    public final TextFieldHandleState getCursorHandle() {
        return (TextFieldHandleState) this.cursorHandle.getValue();
    }

    public final Rect getCursorRect() {
        return (Rect) this.cursorRect.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    public final TextFieldHandleState getEndSelectionHandle() {
        return (TextFieldHandleState) this.endSelectionHandle.getValue();
    }

    /* JADX INFO: renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1173getHandleDragPositionF1C5BW0() {
        return OffsetKt.m3257isUnspecifiedk4lQ0M(m1165getRawHandleDragPositionF1C5BW0()) ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : OffsetKt.m3257isUnspecifiedk4lQ0M(m1166getStartContentVisibleOffsetF1C5BW0()) ? TextLayoutStateKt.m1131fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1165getRawHandleDragPositionF1C5BW0()) : Offset.m3240minusMKHz9U(Offset.m3241plusMKHz9U(m1165getRawHandleDragPositionF1C5BW0(), m1163getCurrentContentVisibleOffsetF1C5BW0()), m1166getStartContentVisibleOffsetF1C5BW0());
    }

    public final TextFieldHandleState getStartSelectionHandle() {
        return (TextFieldHandleState) this.startSelectionHandle.getValue();
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInTouchMode() {
        return ((Boolean) this.isInTouchMode.getValue()).booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object observeChanges(d7.d<? super x6.t0> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C04341
            if (r0 == 0) goto L13
            r0 = r5
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.C04341) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1 r0 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L34
            if (r1 != r3) goto L2c
            java.lang.Object r0 = r0.L$0
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState r0 = (androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState) r0
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L2a
            goto L4b
        L2a:
            r5 = move-exception
            goto L5e
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L34:
            k2.c.G(r5)
            androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2 r5 = new androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState$observeChanges$2     // Catch: java.lang.Throwable -> L5c
            r1 = 0
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5c
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L5c
            r0.label = r3     // Catch: java.lang.Throwable -> L5c
            java.lang.Object r5 = ka.l0.c(r5, r0)     // Catch: java.lang.Throwable -> L5c
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L4a
            return r0
        L4a:
            r0 = r4
        L4b:
            r0.setShowCursorHandle(r2)
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r5 = r0.getTextToolbarState()
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r1 = androidx.compose.foundation.text2.input.internal.selection.TextToolbarState.None
            if (r5 == r1) goto L59
            r0.hideTextToolbar()
        L59:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L5c:
            r5 = move-exception
            r0 = r4
        L5e:
            r0.setShowCursorHandle(r2)
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r1 = r0.getTextToolbarState()
            androidx.compose.foundation.text2.input.internal.selection.TextToolbarState r2 = androidx.compose.foundation.text2.input.internal.selection.TextToolbarState.None
            if (r1 == r2) goto L6c
            r0.hideTextToolbar()
        L6c:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState.observeChanges(d7.d):java.lang.Object");
    }

    public final void paste() {
        AnnotatedString text;
        String text2;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null || (text2 = text.getText()) == null) {
            return;
        }
        TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, text2, false, TextFieldEditUndoBehavior.NeverMerge, 2, null);
    }

    public final Object selectionHandleGestures(PointerInputScope pointerInputScope, boolean z, d<? super t0> dVar) {
        Object objC = l0.c(new C04412(pointerInputScope, z, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    public final Object textFieldGestures(PointerInputScope pointerInputScope, r7.a<t0> aVar, r7.a<t0> aVar2, d<? super t0> dVar) {
        Object objC = l0.c(new C04422(pointerInputScope, aVar, aVar2, null), dVar);
        return objC == a.f15033i ? objC : t0.f22605a;
    }

    public final void update(HapticFeedback hapticFeedBack, ClipboardManager clipboardManager, TextToolbar textToolbar, Density density, boolean enabled, boolean readOnly) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboardManager = clipboardManager;
        this.textToolbar = textToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }
}
