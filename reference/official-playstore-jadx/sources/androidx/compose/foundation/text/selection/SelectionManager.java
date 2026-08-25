package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text2.input.internal.TextLayoutStateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.platform.n;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import com.google.common.util.concurrent.r0;
import d7.d;
import f7.e;
import f7.h;
import f7.j;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import k2.c;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.t;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\"\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\u00020\fH\u0000¢\u0006\u0004\b\r\u0010\u000eJ;\u0010\u0018\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00120\u00150\u00142\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u001c\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001d\u0010\u001bJ\u0011\u0010\"\u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0004\b \u0010!J\u000f\u0010&\u001a\u00020#H\u0000¢\u0006\u0004\b$\u0010%J\r\u0010'\u001a\u00020#¢\u0006\u0004\b'\u0010%J\u0015\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020\u0019¢\u0006\u0004\b*\u0010+J4\u00103\u001a\u00020\u00192\b\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010.\u001a\u00020,2\u0006\u0010(\u001a\u00020\u00192\u0006\u00100\u001a\u00020/H\u0000ø\u0001\u0000¢\u0006\u0004\b1\u00102J2\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020,2\u0006\u0010(\u001a\u00020\u00192\u0006\u00100\u001a\u00020/H\u0000ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u00020\u0019H\u0001¢\u0006\u0004\b8\u0010\u001bJ\u0018\u0010<\u001a\u00020#2\u0006\u00104\u001a\u00020,ø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u000f\u0010=\u001a\u00020#H\u0002¢\u0006\u0004\b=\u0010%J\u000f\u0010>\u001a\u00020#H\u0002¢\u0006\u0004\b>\u0010%J\u000f\u0010?\u001a\u00020#H\u0002¢\u0006\u0004\b?\u0010%J\u0011\u0010A\u001a\u0004\u0018\u00010@H\u0002¢\u0006\u0004\bA\u0010BJ(\u0010F\u001a\u00020#*\u00020C2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020#0DH\u0082@¢\u0006\u0004\bF\u0010GJ!\u0010K\u001a\u00020H*\u00020H2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020#0IH\u0002¢\u0006\u0004\bK\u0010LJ\"\u0010Q\u001a\u00020,2\u0006\u0010M\u001a\u00020\f2\u0006\u0010N\u001a\u00020,H\u0002ø\u0001\u0000¢\u0006\u0004\bO\u0010PJ*\u0010T\u001a\u00020#2\u0006\u00104\u001a\u00020,2\u0006\u0010(\u001a\u00020\u00192\u0006\u00100\u001a\u00020/H\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ*\u0010X\u001a\u00020U2\u0006\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020,2\u0006\u0010(\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\bV\u0010WJ\u001f\u0010[\u001a\u00020#2\u0006\u0010Y\u001a\u00020U2\u0006\u0010Z\u001a\u00020\u0012H\u0002¢\u0006\u0004\b[\u0010\\R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010]R\u001c\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120^8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b_\u0010`R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00190^8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010`RF\u0010c\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020#0D2\u0014\u0010b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020#0D8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR$\u0010j\u001a\u0004\u0018\u00010i8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bj\u0010k\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR$\u0010q\u001a\u0004\u0018\u00010p8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bq\u0010r\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR$\u0010x\u001a\u0004\u0018\u00010w8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R'\u0010\u007f\u001a\u00020~8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R1\u0010\u008a\u0001\u001a\u00020\u00192\u0007\u0010\u0085\u0001\u001a\u00020\u00198F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0005\b\u0086\u0001\u0010`\u001a\u0005\b\u0087\u0001\u0010\u001b\"\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001f\u0010.\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\n\u0005\b.\u0010\u008b\u0001R5\u0010\u008d\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010\f8\u0006@FX\u0086\u000e¢\u0006\u0017\n\u0006\b\u008d\u0001\u0010\u008e\u0001\u001a\u0005\b\u008f\u0001\u0010\u000e\"\u0006\b\u0090\u0001\u0010\u0091\u0001R7\u0010\u0096\u0001\u001a\u00020,2\u0007\u0010\u0085\u0001\u001a\u00020,8@@BX\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0016\n\u0005\b\u0092\u0001\u0010`\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001\"\u0005\b\u0095\u0001\u0010;R7\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010\u0085\u0001\u001a\u00020,8@@BX\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0016\n\u0005\b\u0097\u0001\u0010`\u001a\u0006\b\u0098\u0001\u0010\u0094\u0001\"\u0005\b\u0099\u0001\u0010;R<\u0010 \u0001\u001a\u0004\u0018\u00010,2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010,8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0005\b\u009b\u0001\u0010`\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R<\u0010¤\u0001\u001a\u0004\u0018\u00010,2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010,8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0005\b¡\u0001\u0010`\u001a\u0006\b¢\u0001\u0010\u009d\u0001\"\u0006\b£\u0001\u0010\u009f\u0001R8\u0010«\u0001\u001a\u0005\u0018\u00010¥\u00012\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010¥\u00018F@BX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b¦\u0001\u0010`\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001R<\u0010¯\u0001\u001a\u0004\u0018\u00010,2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010,8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0017\n\u0005\b¬\u0001\u0010`\u001a\u0006\b\u00ad\u0001\u0010\u009d\u0001\"\u0006\b®\u0001\u0010\u009f\u0001R2\u0010°\u0001\u001a\u0004\u0018\u00010U8\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\b°\u0001\u0010±\u0001\u0012\u0005\b¶\u0001\u0010%\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001R1\u0010·\u0001\u001a\u00020\u00192\u0007\u0010\u008c\u0001\u001a\u00020\u00198\u0000@@X\u0080\u000e¢\u0006\u0017\n\u0006\b·\u0001\u0010¸\u0001\u001a\u0005\b¹\u0001\u0010\u001b\"\u0006\bº\u0001\u0010\u0089\u0001R.\u0010¿\u0001\u001a\u0004\u0018\u00010\u00122\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0006\b½\u0001\u0010¾\u0001R)\u0010À\u0001\u001a\u00020\u00192\u0007\u0010\u008c\u0001\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\u000f\u001a\u0005\bÀ\u0001\u0010\u001b\"\u0006\bÁ\u0001\u0010\u0089\u0001R\u0014\u0010Ä\u0001\u001a\u00020H8F¢\u0006\b\u001a\u0006\bÂ\u0001\u0010Ã\u0001R\u0016\u0010Æ\u0001\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010\u001b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Ç\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionManager;", "", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "selectionRegistrar", "<init>", "(Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;)V", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", LinkHeader.Parameters.Anchor, "Landroidx/compose/foundation/text/selection/Selectable;", "getAnchorSelectable$foundation_release", "(Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;)Landroidx/compose/foundation/text/selection/Selectable;", "getAnchorSelectable", "Landroidx/compose/ui/layout/LayoutCoordinates;", "requireContainerCoordinates$foundation_release", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "requireContainerCoordinates", "", "selectableId", "Landroidx/compose/foundation/text/selection/Selection;", "previousSelection", "Lx6/x;", "", "selectAll$foundation_release", "(JLandroidx/compose/foundation/text/selection/Selection;)Lx6/x;", "selectAll", "", "isTriviallyCollapsedSelection$foundation_release", "()Z", "isTriviallyCollapsedSelection", "isNonEmptySelection$foundation_release", "isNonEmptySelection", "Landroidx/compose/ui/text/AnnotatedString;", "getSelectedText$foundation_release", "()Landroidx/compose/ui/text/AnnotatedString;", "getSelectedText", "Lx6/t0;", "copy$foundation_release", "()V", "copy", "onRelease", "isStartHandle", "Landroidx/compose/foundation/text/TextDragObserver;", "handleDragObserver", "(Z)Landroidx/compose/foundation/text/TextDragObserver;", "Landroidx/compose/ui/geometry/Offset;", "newPosition", "previousPosition", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustment", "updateSelection-qNKwrvQ$foundation_release", "(Landroidx/compose/ui/geometry/Offset;JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "updateSelection", "position", "previousHandlePosition", "updateSelection-jyLRC_s$foundation_release", "(JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "shouldPerformHaptics$foundation_release", "shouldPerformHaptics", "contextMenuOpenAdjustment-k-4lQ0M", "(J)V", "contextMenuOpenAdjustment", "updateHandleOffsets", "toolbarCopy", "updateSelectionToolbar", "Landroidx/compose/ui/geometry/Rect;", "getContentRect", "()Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lkotlin/Function1;", "onTap", "detectNonConsumingTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "block", "onClearSelectionRequested", "(Landroidx/compose/ui/Modifier;Lr7/a;)Landroidx/compose/ui/Modifier;", "layoutCoordinates", "offset", "convertToContainerCoordinates-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "convertToContainerCoordinates", "startSelection-9KIMszo", "(JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "startSelection", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "getSelectionLayout-Wko1d7g", "(JJZ)Landroidx/compose/foundation/text/selection/SelectionLayout;", "getSelectionLayout", "selectionLayout", "newSelection", "selectionChanged", "(Landroidx/compose/foundation/text/selection/SelectionLayout;Landroidx/compose/foundation/text/selection/Selection;)V", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "Landroidx/compose/runtime/MutableState;", "_selection", "Landroidx/compose/runtime/MutableState;", "_isInTouchMode", "newOnSelectionChange", "onSelectionChange", "Lr7/l;", "getOnSelectionChange", "()Lr7/l;", "setOnSelectionChange", "(Lr7/l;)V", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "Landroidx/compose/ui/platform/ClipboardManager;", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "Landroidx/compose/ui/platform/TextToolbar;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "<set-?>", "hasFocus$delegate", "getHasFocus", "setHasFocus", "(Z)V", "hasFocus", "Landroidx/compose/ui/geometry/Offset;", "value", "containerLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getContainerLayoutCoordinates", "setContainerLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "dragBeginPosition$delegate", "getDragBeginPosition-F1C5BW0$foundation_release", "()J", "setDragBeginPosition-k-4lQ0M", "dragBeginPosition", "dragTotalDistance$delegate", "getDragTotalDistance-F1C5BW0$foundation_release", "setDragTotalDistance-k-4lQ0M", "dragTotalDistance", "startHandlePosition$delegate", "getStartHandlePosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setStartHandlePosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "startHandlePosition", "endHandlePosition$delegate", "getEndHandlePosition-_m7T9-E", "setEndHandlePosition-_kEHs6E", "endHandlePosition", "Landroidx/compose/foundation/text/Handle;", "draggingHandle$delegate", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle", "currentDragPosition$delegate", "getCurrentDragPosition-_m7T9-E", "setCurrentDragPosition-_kEHs6E", "currentDragPosition", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "getPreviousSelectionLayout$foundation_release", "()Landroidx/compose/foundation/text/selection/SelectionLayout;", "setPreviousSelectionLayout$foundation_release", "(Landroidx/compose/foundation/text/selection/SelectionLayout;)V", "getPreviousSelectionLayout$foundation_release$annotations", "showToolbar", "Z", "getShowToolbar$foundation_release", "setShowToolbar$foundation_release", "getSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "setSelection", "(Landroidx/compose/foundation/text/selection/Selection;)V", "selection", "isInTouchMode", "setInTouchMode", "getModifier", "()Landroidx/compose/ui/Modifier;", "modifier", "getShouldShowMagnifier", "shouldShowMagnifier", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectionManager {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;
    private LayoutCoordinates containerLayoutCoordinates;

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;

    /* JADX INFO: renamed from: dragBeginPosition$delegate, reason: from kotlin metadata */
    private final MutableState dragBeginPosition;

    /* JADX INFO: renamed from: dragTotalDistance$delegate, reason: from kotlin metadata */
    private final MutableState dragTotalDistance;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* JADX INFO: renamed from: endHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState endHandlePosition;
    private HapticFeedback hapticFeedBack;
    private Offset previousPosition;
    private SelectionLayout previousSelectionLayout;
    private final SelectionRegistrarImpl selectionRegistrar;
    private boolean showToolbar;

    /* JADX INFO: renamed from: startHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState startHandlePosition;
    private TextToolbar textToolbar;
    private final MutableState<Selection> _selection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private final MutableState<Boolean> _isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
    private l<? super Selection, t0> onSelectionChange = new SelectionManager$onSelectionChange$1(this);
    private FocusRequester focusRequester = new FocusRequester();

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "selectableId", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Long, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            if (SelectionManager.this.selectionRegistrar.getSubselections().containsKey(Long.valueOf(j10))) {
                SelectionManager.this.updateHandleOffsets();
                SelectionManager.this.updateSelectionToolbar();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000 \n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"", "isInTouchMode", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutCoordinates", "Landroidx/compose/ui/geometry/Offset;", "rawPosition", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "selectionMode", "Lx6/t0;", "invoke-Rg1IO4c", "(ZLandroidx/compose/ui/layout/LayoutCoordinates;JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements r7.r<Boolean, LayoutCoordinates, Offset, SelectionAdjustment, t0> {
        public AnonymousClass2() {
            super(4);
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            m1006invokeRg1IO4c(((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, ((Offset) obj3).getPackedValue(), (SelectionAdjustment) obj4);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke-Rg1IO4c, reason: not valid java name */
        public final void m1006invokeRg1IO4c(boolean z, LayoutCoordinates layoutCoordinates, long j10, SelectionAdjustment selectionAdjustment) {
            long jMo4638getSizeYbymL2g = layoutCoordinates.mo4638getSizeYbymL2g();
            Rect rect = new Rect(0.0f, 0.0f, IntSize.m5844getWidthimpl(jMo4638getSizeYbymL2g), IntSize.m5843getHeightimpl(jMo4638getSizeYbymL2g));
            if (!SelectionManagerKt.m1014containsInclusiveUv8p0NA(rect, j10)) {
                j10 = TextLayoutStateKt.m1130coerceIn3MmeM6k(j10, rect);
            }
            long jM990convertToContainerCoordinatesR5De75A = SelectionManager.this.m990convertToContainerCoordinatesR5De75A(layoutCoordinates, j10);
            if (OffsetKt.m3255isSpecifiedk4lQ0M(jM990convertToContainerCoordinatesR5De75A)) {
                SelectionManager.this.setInTouchMode(z);
                SelectionManager.this.m997startSelection9KIMszo(jM990convertToContainerCoordinatesR5De75A, false, selectionAdjustment);
                SelectionManager.this.getFocusRequester().requestFocus();
                SelectionManager.this.setShowToolbar$foundation_release(false);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "isInTouchMode", "", "selectableId", "Lx6/t0;", "invoke", "(ZJ)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements p<Boolean, Long, t0> {
        public AnonymousClass3() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke(((Boolean) obj).booleanValue(), ((Number) obj2).longValue());
            return t0.f22605a;
        }

        public final void invoke(boolean z, long j10) {
            SelectionManager selectionManager = SelectionManager.this;
            x xVarSelectAll$foundation_release = selectionManager.selectAll$foundation_release(j10, selectionManager.getSelection());
            Selection selection = (Selection) xVarSelectAll$foundation_release.f22608i;
            Map<Long, Selection> map = (Map) xVarSelectAll$foundation_release.f22609l;
            if (!kotlin.jvm.internal.p.a(selection, SelectionManager.this.getSelection())) {
                SelectionManager.this.selectionRegistrar.setSubselections(map);
                SelectionManager.this.getOnSelectionChange().invoke(selection);
            }
            SelectionManager.this.setInTouchMode(z);
            SelectionManager.this.getFocusRequester().requestFocus();
            SelectionManager.this.setShowToolbar$foundation_release(false);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f"}, d2 = {"<anonymous>", "", "isInTouchMode", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "newPosition", "Landroidx/compose/ui/geometry/Offset;", "previousPosition", "isStartHandle", "selectionMode", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "invoke-pGV3PM0", "(ZLandroidx/compose/ui/layout/LayoutCoordinates;JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements t<Boolean, LayoutCoordinates, Offset, Offset, Boolean, SelectionAdjustment, Boolean> {
        public AnonymousClass4() {
            super(6);
        }

        @Override // r7.t
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
            return m1007invokepGV3PM0(((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, ((Offset) obj3).getPackedValue(), ((Offset) obj4).getPackedValue(), ((Boolean) obj5).booleanValue(), (SelectionAdjustment) obj6);
        }

        /* JADX INFO: renamed from: invoke-pGV3PM0, reason: not valid java name */
        public final Boolean m1007invokepGV3PM0(boolean z, LayoutCoordinates layoutCoordinates, long j10, long j11, boolean z5, SelectionAdjustment selectionAdjustment) {
            long jM990convertToContainerCoordinatesR5De75A = SelectionManager.this.m990convertToContainerCoordinatesR5De75A(layoutCoordinates, j10);
            long jM990convertToContainerCoordinatesR5De75A2 = SelectionManager.this.m990convertToContainerCoordinatesR5De75A(layoutCoordinates, j11);
            SelectionManager.this.setInTouchMode(z);
            return Boolean.valueOf(SelectionManager.this.m1005updateSelectionqNKwrvQ$foundation_release(Offset.m3225boximpl(jM990convertToContainerCoordinatesR5De75A), jM990convertToContainerCoordinatesR5De75A2, z5, selectionAdjustment));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements r7.a<t0> {
        public AnonymousClass5() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1008invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1008invoke() {
            SelectionManager.this.setShowToolbar$foundation_release(true);
            SelectionManager.this.setDraggingHandle(null);
            SelectionManager.this.m992setCurrentDragPosition_kEHs6E(null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "selectableKey", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass6 extends r implements l<Long, t0> {
        public AnonymousClass6() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            if (SelectionManager.this.selectionRegistrar.getSubselections().containsKey(Long.valueOf(j10))) {
                SelectionManager.this.onRelease();
                SelectionManager.this.setSelection(null);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "selectableId", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass7 extends r implements l<Long, t0> {
        public AnonymousClass7() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            Selection.AnchorInfo end;
            Selection.AnchorInfo start;
            Selection selection = SelectionManager.this.getSelection();
            if (selection != null && (start = selection.getStart()) != null && j10 == start.getSelectableId()) {
                SelectionManager.this.m996setStartHandlePosition_kEHs6E(null);
            }
            Selection selection2 = SelectionManager.this.getSelection();
            if (selection2 != null && (end = selection2.getEnd()) != null && j10 == end.getSelectableId()) {
                SelectionManager.this.m995setEndHandlePosition_kEHs6E(null);
            }
            if (SelectionManager.this.selectionRegistrar.getSubselections().containsKey(Long.valueOf(j10))) {
                SelectionManager.this.updateSelectionToolbar();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$detectNonConsumingTap$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.selection.SelectionManager$detectNonConsumingTap$2", f = "SelectionManager.kt", l = {664}, m = "invokeSuspend")
    public static final class C03972 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
        final /* synthetic */ l<Offset, t0> $onTap;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03972(l<? super Offset, t0> lVar, d<? super C03972> dVar) {
            super(2, dVar);
            this.$onTap = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03972 c03972 = new C03972(this.$onTap, dVar);
            c03972.L$0 = obj;
            return c03972;
        }

        @Override // r7.p
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
            return ((C03972) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.label = 1;
                obj = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            PointerInputChange pointerInputChange = (PointerInputChange) obj;
            if (pointerInputChange != null) {
                this.$onTap.invoke(Offset.m3225boximpl(pointerInputChange.getPosition()));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1", f = "SelectionManager.kt", l = {671}, m = "invokeSuspend")
    public static final class C03991 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ r7.a<t0> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00601 extends r implements l<Offset, t0> {
            final /* synthetic */ r7.a<t0> $block;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00601(r7.a<t0> aVar) {
                super(1);
                this.$block = aVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m1011invokek4lQ0M(((Offset) obj).getPackedValue());
                return t0.f22605a;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m1011invokek4lQ0M(long j10) {
                this.$block.invoke();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03991(r7.a<t0> aVar, d<? super C03991> dVar) {
            super(2, dVar);
            this.$block = aVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03991 c03991 = SelectionManager.this.new C03991(this.$block, dVar);
            c03991.L$0 = obj;
            return c03991;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C03991) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                SelectionManager selectionManager = SelectionManager.this;
                C00601 c00601 = new C00601(this.$block);
                this.label = 1;
                Object objDetectNonConsumingTap = selectionManager.detectNonConsumingTap(pointerInputScope, c00601, this);
                e7.a aVar = e7.a.f15033i;
                if (objDetectNonConsumingTap == aVar) {
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
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$updateSelectionToolbar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class C04001 extends m implements r7.a<t0> {
        public C04001(Object obj) {
            super(0, 0, SelectionManager.class, obj, "toolbarCopy", "toolbarCopy()V");
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1012invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1012invoke() {
            ((SelectionManager) this.receiver).toolbarCopy();
        }
    }

    public SelectionManager(SelectionRegistrarImpl selectionRegistrarImpl) {
        this.selectionRegistrar = selectionRegistrarImpl;
        Offset.Companion companion = Offset.INSTANCE;
        this.dragBeginPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(companion.m3252getZeroF1C5BW0()), null, 2, null);
        this.dragTotalDistance = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(companion.m3252getZeroF1C5BW0()), null, 2, null);
        this.startHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.endHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        selectionRegistrarImpl.setOnPositionChangeCallback$foundation_release(new AnonymousClass1());
        selectionRegistrarImpl.setOnSelectionUpdateStartCallback$foundation_release(new AnonymousClass2());
        selectionRegistrarImpl.setOnSelectionUpdateSelectAll$foundation_release(new AnonymousClass3());
        selectionRegistrarImpl.setOnSelectionUpdateCallback$foundation_release(new AnonymousClass4());
        selectionRegistrarImpl.setOnSelectionUpdateEndCallback$foundation_release(new AnonymousClass5());
        selectionRegistrarImpl.setOnSelectableChangeCallback$foundation_release(new AnonymousClass6());
        selectionRegistrarImpl.setAfterSelectableUnsubscribe$foundation_release(new AnonymousClass7());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: convertToContainerCoordinates-R5De75A, reason: not valid java name */
    public final long m990convertToContainerCoordinatesR5De75A(LayoutCoordinates layoutCoordinates, long offset) {
        LayoutCoordinates layoutCoordinates2 = this.containerLayoutCoordinates;
        return (layoutCoordinates2 == null || !layoutCoordinates2.isAttached()) ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : requireContainerCoordinates$foundation_release().mo4639localPositionOfR5De75A(layoutCoordinates, offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object detectNonConsumingTap(PointerInputScope pointerInputScope, l<? super Offset, t0> lVar, d<? super t0> dVar) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C03972(lVar, null), dVar);
        return objAwaitEachGesture == e7.a.f15033i ? objAwaitEachGesture : t0.f22605a;
    }

    private final Rect getContentRect() {
        LayoutCoordinates layoutCoordinates;
        if (getSelection() == null || (layoutCoordinates = this.containerLayoutCoordinates) == null || !layoutCoordinates.isAttached()) {
            return null;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        ArrayList arrayList = new ArrayList(listSort.size());
        int size = listSort.size();
        for (int i10 = 0; i10 < size; i10++) {
            Selectable selectable = listSort.get(i10);
            Selection selection = this.selectionRegistrar.getSubselections().get(Long.valueOf(selectable.getSelectableId()));
            x xVar = selection != null ? new x(selectable, selection) : null;
            if (xVar != null) {
                arrayList.add(xVar);
            }
        }
        List listFirstAndLast = SelectionManagerKt.firstAndLast(arrayList);
        if (listFirstAndLast.isEmpty()) {
            return null;
        }
        Rect selectedRegionRect = SelectionManagerKt.getSelectedRegionRect(listFirstAndLast, layoutCoordinates);
        if (kotlin.jvm.internal.p.a(selectedRegionRect, SelectionManagerKt.invertedInfiniteRect)) {
            return null;
        }
        Rect rectIntersect = SelectionManagerKt.visibleBounds(layoutCoordinates).intersect(selectedRegionRect);
        if (rectIntersect.getWidth() < 0.0f || rectIntersect.getHeight() < 0.0f) {
            return null;
        }
        return Rect.copy$default(rectIntersect.m3273translatek4lQ0M(LayoutCoordinatesKt.positionInRoot(layoutCoordinates)), 0.0f, 0.0f, 0.0f, (SelectionHandlesKt.getHandleHeight() * 4) + rectIntersect.getBottom(), 7, null);
    }

    public static /* synthetic */ void getPreviousSelectionLayout$foundation_release$annotations() {
    }

    /* JADX INFO: renamed from: getSelectionLayout-Wko1d7g, reason: not valid java name */
    private final SelectionLayout m991getSelectionLayoutWko1d7g(long position, long previousHandlePosition, boolean isStartHandle) {
        LayoutCoordinates layoutCoordinatesRequireContainerCoordinates$foundation_release = requireContainerCoordinates$foundation_release();
        List<Selectable> listSort = this.selectionRegistrar.sort(layoutCoordinatesRequireContainerCoordinates$foundation_release);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = listSort.size();
        for (int i10 = 0; i10 < size; i10++) {
            linkedHashMap.put(Long.valueOf(listSort.get(i10).getSelectableId()), Integer.valueOf(i10));
        }
        SelectionLayoutBuilder selectionLayoutBuilder = new SelectionLayoutBuilder(position, previousHandlePosition, layoutCoordinatesRequireContainerCoordinates$foundation_release, isStartHandle, OffsetKt.m3257isUnspecifiedk4lQ0M(previousHandlePosition) ? null : getSelection(), new Comparator() { // from class: androidx.compose.foundation.text.selection.SelectionManager$getSelectionLayout-Wko1d7g$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e((Comparable) linkedHashMap.get(Long.valueOf(((Number) t2).longValue())), (Comparable) linkedHashMap.get(Long.valueOf(((Number) t10).longValue())));
            }
        }, null);
        int size2 = listSort.size();
        for (int i11 = 0; i11 < size2; i11++) {
            listSort.get(i11).appendSelectableInfoToBuilder(selectionLayoutBuilder);
        }
        return selectionLayoutBuilder.build();
    }

    private final boolean getShouldShowMagnifier() {
        return (getDraggingHandle() == null || !isInTouchMode() || isTriviallyCollapsedSelection$foundation_release()) ? false : true;
    }

    private final Modifier onClearSelectionRequested(Modifier modifier, r7.a<t0> aVar) {
        if (!getHasFocus()) {
            return modifier;
        }
        return SuspendingPointerInputFilterKt.pointerInput(modifier, t0.f22605a, new C03991(aVar, null));
    }

    private final void selectionChanged(SelectionLayout selectionLayout, Selection newSelection) {
        HapticFeedback hapticFeedback;
        if (shouldPerformHaptics$foundation_release() && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
        }
        this.selectionRegistrar.setSubselections(selectionLayout.createSubSelections(newSelection));
        this.onSelectionChange.invoke(newSelection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m992setCurrentDragPosition_kEHs6E(Offset offset) {
        this.currentDragPosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
    public final void m993setDragBeginPositionk4lQ0M(long j10) {
        this.dragBeginPosition.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragTotalDistance-k-4lQ0M, reason: not valid java name */
    public final void m994setDragTotalDistancek4lQ0M(long j10) {
        this.dragTotalDistance.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setEndHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m995setEndHandlePosition_kEHs6E(Offset offset) {
        this.endHandlePosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setStartHandlePosition-_kEHs6E, reason: not valid java name */
    public final void m996setStartHandlePosition_kEHs6E(Offset offset) {
        this.startHandlePosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startSelection-9KIMszo, reason: not valid java name */
    public final void m997startSelection9KIMszo(long position, boolean isStartHandle, SelectionAdjustment adjustment) {
        this.previousSelectionLayout = null;
        m1004updateSelectionjyLRC_s$foundation_release(position, Offset.INSTANCE.m3251getUnspecifiedF1C5BW0(), isStartHandle, adjustment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toolbarCopy() {
        copy$foundation_release();
        onRelease();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateHandleOffsets() {
        /*
            r11 = this;
            androidx.compose.foundation.text.selection.Selection r0 = r11.getSelection()
            androidx.compose.ui.layout.LayoutCoordinates r1 = r11.containerLayoutCoordinates
            r2 = 0
            if (r0 == 0) goto L14
            androidx.compose.foundation.text.selection.Selection$AnchorInfo r3 = r0.getStart()
            if (r3 == 0) goto L14
            androidx.compose.foundation.text.selection.Selectable r3 = r11.getAnchorSelectable$foundation_release(r3)
            goto L15
        L14:
            r3 = r2
        L15:
            if (r0 == 0) goto L22
            androidx.compose.foundation.text.selection.Selection$AnchorInfo r4 = r0.getEnd()
            if (r4 == 0) goto L22
            androidx.compose.foundation.text.selection.Selectable r4 = r11.getAnchorSelectable$foundation_release(r4)
            goto L23
        L22:
            r4 = r2
        L23:
            if (r3 == 0) goto L2a
            androidx.compose.ui.layout.LayoutCoordinates r5 = r3.getLayoutCoordinates()
            goto L2b
        L2a:
            r5 = r2
        L2b:
            if (r4 == 0) goto L32
            androidx.compose.ui.layout.LayoutCoordinates r6 = r4.getLayoutCoordinates()
            goto L33
        L32:
            r6 = r2
        L33:
            if (r0 == 0) goto La0
            if (r1 == 0) goto La0
            boolean r7 = r1.isAttached()
            if (r7 == 0) goto La0
            if (r5 != 0) goto L42
            if (r6 != 0) goto L42
            goto La0
        L42:
            androidx.compose.ui.geometry.Rect r7 = androidx.compose.foundation.text.selection.SelectionManagerKt.visibleBounds(r1)
            if (r5 == 0) goto L6f
            r8 = 1
            long r8 = r3.mo960getHandlePositiondBAh8RU(r0, r8)
            boolean r3 = androidx.compose.ui.geometry.OffsetKt.m3257isUnspecifiedk4lQ0M(r8)
            if (r3 == 0) goto L54
            goto L6f
        L54:
            long r8 = r1.mo4639localPositionOfR5De75A(r5, r8)
            androidx.compose.ui.geometry.Offset r3 = androidx.compose.ui.geometry.Offset.m3225boximpl(r8)
            long r8 = r3.getPackedValue()
            androidx.compose.foundation.text.Handle r5 = r11.getDraggingHandle()
            androidx.compose.foundation.text.Handle r10 = androidx.compose.foundation.text.Handle.SelectionStart
            if (r5 == r10) goto L70
            boolean r5 = androidx.compose.foundation.text.selection.SelectionManagerKt.m1014containsInclusiveUv8p0NA(r7, r8)
            if (r5 == 0) goto L6f
            goto L70
        L6f:
            r3 = r2
        L70:
            r11.m996setStartHandlePosition_kEHs6E(r3)
            if (r6 == 0) goto L9c
            r3 = 0
            long r3 = r4.mo960getHandlePositiondBAh8RU(r0, r3)
            boolean r0 = androidx.compose.ui.geometry.OffsetKt.m3257isUnspecifiedk4lQ0M(r3)
            if (r0 == 0) goto L81
            goto L9c
        L81:
            long r0 = r1.mo4639localPositionOfR5De75A(r6, r3)
            androidx.compose.ui.geometry.Offset r0 = androidx.compose.ui.geometry.Offset.m3225boximpl(r0)
            long r3 = r0.getPackedValue()
            androidx.compose.foundation.text.Handle r1 = r11.getDraggingHandle()
            androidx.compose.foundation.text.Handle r5 = androidx.compose.foundation.text.Handle.SelectionEnd
            if (r1 == r5) goto L9b
            boolean r1 = androidx.compose.foundation.text.selection.SelectionManagerKt.m1014containsInclusiveUv8p0NA(r7, r3)
            if (r1 == 0) goto L9c
        L9b:
            r2 = r0
        L9c:
            r11.m995setEndHandlePosition_kEHs6E(r2)
            return
        La0:
            r11.m996setStartHandlePosition_kEHs6E(r2)
            r11.m995setEndHandlePosition_kEHs6E(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionManager.updateHandleOffsets():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSelectionToolbar() {
        TextToolbar textToolbar;
        if (getHasFocus() && (textToolbar = this.textToolbar) != null) {
            if (!this.showToolbar || !isInTouchMode() || !isNonEmptySelection$foundation_release()) {
                if (textToolbar.getStatus() == TextToolbarStatus.Shown) {
                    textToolbar.hide();
                }
            } else {
                Rect contentRect = getContentRect();
                if (contentRect == null) {
                    return;
                }
                n.a(textToolbar, contentRect, new C04001(this), null, null, null, 28, null);
            }
        }
    }

    /* JADX INFO: renamed from: contextMenuOpenAdjustment-k-4lQ0M, reason: not valid java name */
    public final void m998contextMenuOpenAdjustmentk4lQ0M(long position) {
        Selection selection = getSelection();
        if (selection != null ? TextRange.m5186getCollapsedimpl(selection.m966toTextRanged9O1mEE()) : true) {
            m997startSelection9KIMszo(position, true, SelectionAdjustment.INSTANCE.getWord());
        }
    }

    public final void copy$foundation_release() {
        ClipboardManager clipboardManager;
        AnnotatedString selectedText$foundation_release = getSelectedText$foundation_release();
        if (selectedText$foundation_release != null) {
            if (selectedText$foundation_release.length() <= 0) {
                selectedText$foundation_release = null;
            }
            if (selectedText$foundation_release == null || (clipboardManager = this.clipboardManager) == null) {
                return;
            }
            clipboardManager.setText(selectedText$foundation_release);
        }
    }

    public final Selectable getAnchorSelectable$foundation_release(Selection.AnchorInfo anchor) {
        return this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf(anchor.getSelectableId()));
    }

    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final LayoutCoordinates getContainerLayoutCoordinates() {
        return this.containerLayoutCoordinates;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m999getCurrentDragPosition_m7T9E() {
        return (Offset) this.currentDragPosition.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getDragBeginPosition-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m1000getDragBeginPositionF1C5BW0$foundation_release() {
        return ((Offset) this.dragBeginPosition.getValue()).getPackedValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getDragTotalDistance-F1C5BW0$foundation_release, reason: not valid java name */
    public final long m1001getDragTotalDistanceF1C5BW0$foundation_release() {
        return ((Offset) this.dragTotalDistance.getValue()).getPackedValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getEndHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1002getEndHandlePosition_m7T9E() {
        return (Offset) this.endHandlePosition.getValue();
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasFocus() {
        return ((Boolean) this.hasFocus.getValue()).booleanValue();
    }

    public final Modifier getModifier() {
        Modifier modifierSelectionMagnifier = Modifier.INSTANCE;
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(SelectionGesturesKt.updateSelectionTouchMode(FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(OnGloballyPositionedModifierKt.onGloballyPositioned(onClearSelectionRequested(modifierSelectionMagnifier, new SelectionManager$modifier$1(this)), new SelectionManager$modifier$2(this)), this.focusRequester), new SelectionManager$modifier$3(this)), false, null, 3, null), new SelectionManager$modifier$4(this)), new SelectionManager$modifier$5(this));
        if (getShouldShowMagnifier()) {
            modifierSelectionMagnifier = SelectionManager_androidKt.selectionMagnifier(modifierSelectionMagnifier, this);
        }
        return modifierOnKeyEvent.then(modifierSelectionMagnifier);
    }

    public final l<Selection, t0> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    /* JADX INFO: renamed from: getPreviousSelectionLayout$foundation_release, reason: from getter */
    public final SelectionLayout getPreviousSelectionLayout() {
        return this.previousSelectionLayout;
    }

    public final AnnotatedString getSelectedText$foundation_release() {
        if (getSelection() == null || this.selectionRegistrar.getSubselections().isEmpty()) {
            return null;
        }
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = listSort.size();
        for (int i10 = 0; i10 < size; i10++) {
            Selectable selectable = listSort.get(i10);
            Selection selection = this.selectionRegistrar.getSubselections().get(Long.valueOf(selectable.getSelectableId()));
            if (selection != null) {
                AnnotatedString text = selectable.getText();
                builder.append(selection.getHandlesCrossed() ? text.subSequence(selection.getEnd().getOffset(), selection.getStart().getOffset()) : text.subSequence(selection.getStart().getOffset(), selection.getEnd().getOffset()));
            }
        }
        return builder.toAnnotatedString();
    }

    public final Selection getSelection() {
        return this._selection.getValue();
    }

    /* JADX INFO: renamed from: getShowToolbar$foundation_release, reason: from getter */
    public final boolean getShowToolbar() {
        return this.showToolbar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getStartHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m1003getStartHandlePosition_m7T9E() {
        return (Offset) this.startHandlePosition.getValue();
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final TextDragObserver handleDragObserver(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.SelectionManager.handleDragObserver.1
            private final void done() {
                this.setShowToolbar$foundation_release(true);
                this.setDraggingHandle(null);
                this.m992setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo875onDownk4lQ0M(long point) {
                LayoutCoordinates layoutCoordinates;
                Offset offsetM1003getStartHandlePosition_m7T9E = isStartHandle ? this.m1003getStartHandlePosition_m7T9E() : this.m1002getEndHandlePosition_m7T9E();
                if (offsetM1003getStartHandlePosition_m7T9E != null) {
                    offsetM1003getStartHandlePosition_m7T9E.getPackedValue();
                    Selection selection = this.getSelection();
                    if (selection == null) {
                        return;
                    }
                    Selectable anchorSelectable$foundation_release = this.getAnchorSelectable$foundation_release(isStartHandle ? selection.getStart() : selection.getEnd());
                    if (anchorSelectable$foundation_release == null || (layoutCoordinates = anchorSelectable$foundation_release.getLayoutCoordinates()) == null) {
                        return;
                    }
                    long jMo960getHandlePositiondBAh8RU = anchorSelectable$foundation_release.mo960getHandlePositiondBAh8RU(selection, isStartHandle);
                    if (OffsetKt.m3257isUnspecifiedk4lQ0M(jMo960getHandlePositiondBAh8RU)) {
                        return;
                    }
                    long jM974getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(jMo960getHandlePositiondBAh8RU);
                    SelectionManager selectionManager = this;
                    selectionManager.m992setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(selectionManager.requireContainerCoordinates$foundation_release().mo4639localPositionOfR5De75A(layoutCoordinates, jM974getAdjustedCoordinatesk4lQ0M)));
                    this.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                    this.setShowToolbar$foundation_release(false);
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo876onDragk4lQ0M(long delta) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                SelectionManager selectionManager = this;
                selectionManager.m994setDragTotalDistancek4lQ0M(Offset.m3241plusMKHz9U(selectionManager.m1001getDragTotalDistanceF1C5BW0$foundation_release(), delta));
                long jM3241plusMKHz9U = Offset.m3241plusMKHz9U(this.m1000getDragBeginPositionF1C5BW0$foundation_release(), this.m1001getDragTotalDistanceF1C5BW0$foundation_release());
                if (this.m1005updateSelectionqNKwrvQ$foundation_release(Offset.m3225boximpl(jM3241plusMKHz9U), this.m1000getDragBeginPositionF1C5BW0$foundation_release(), isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate())) {
                    this.m993setDragBeginPositionk4lQ0M(jM3241plusMKHz9U);
                    this.m994setDragTotalDistancek4lQ0M(Offset.INSTANCE.m3252getZeroF1C5BW0());
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo877onStartk4lQ0M(long startPoint) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                Selection selection = this.getSelection();
                Selectable selectable = this.selectionRegistrar.getSelectableMap$foundation_release().get(Long.valueOf((isStartHandle ? selection.getStart() : selection.getEnd()).getSelectableId()));
                if (selectable == null) {
                    throw new IllegalStateException("SelectionRegistrar should contain the current selection's selectableIds");
                }
                Selectable selectable2 = selectable;
                LayoutCoordinates layoutCoordinates = selectable2.getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    throw new IllegalStateException("Current selectable should have layout coordinates.");
                }
                long jMo960getHandlePositiondBAh8RU = selectable2.mo960getHandlePositiondBAh8RU(selection, isStartHandle);
                if (OffsetKt.m3257isUnspecifiedk4lQ0M(jMo960getHandlePositiondBAh8RU)) {
                    return;
                }
                long jM974getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(jMo960getHandlePositiondBAh8RU);
                SelectionManager selectionManager = this;
                selectionManager.m993setDragBeginPositionk4lQ0M(selectionManager.requireContainerCoordinates$foundation_release().mo4639localPositionOfR5De75A(layoutCoordinates, jM974getAdjustedCoordinatesk4lQ0M));
                this.m994setDragTotalDistancek4lQ0M(Offset.INSTANCE.m3252getZeroF1C5BW0());
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                done();
            }
        };
    }

    public final boolean isInTouchMode() {
        return this._isInTouchMode.getValue().booleanValue();
    }

    public final boolean isNonEmptySelection$foundation_release() {
        Selection selection = getSelection();
        if (selection == null || kotlin.jvm.internal.p.a(selection.getStart(), selection.getEnd())) {
            return false;
        }
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            return true;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = listSort.size();
        for (int i10 = 0; i10 < size; i10++) {
            Selection selection2 = this.selectionRegistrar.getSubselections().get(Long.valueOf(listSort.get(i10).getSelectableId()));
            if (selection2 != null && selection2.getStart().getOffset() != selection2.getEnd().getOffset()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isTriviallyCollapsedSelection$foundation_release() {
        Selection selection = getSelection();
        if (selection == null) {
            return true;
        }
        return kotlin.jvm.internal.p.a(selection.getStart(), selection.getEnd());
    }

    public final void onRelease() {
        HapticFeedback hapticFeedback;
        this.selectionRegistrar.setSubselections(a0.f19683i);
        setShowToolbar$foundation_release(false);
        if (getSelection() != null) {
            this.onSelectionChange.invoke(null);
            if (!isInTouchMode() || (hapticFeedback = this.hapticFeedBack) == null) {
                return;
            }
            hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
        }
    }

    public final LayoutCoordinates requireContainerCoordinates$foundation_release() {
        LayoutCoordinates layoutCoordinates = this.containerLayoutCoordinates;
        if (layoutCoordinates == null) {
            throw new IllegalArgumentException("null coordinates");
        }
        if (layoutCoordinates.isAttached()) {
            return layoutCoordinates;
        }
        throw new IllegalArgumentException("unattached coordinates");
    }

    public final x selectAll$foundation_release(long selectableId, Selection previousSelection) {
        HapticFeedback hapticFeedback;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation_release());
        int size = listSort.size();
        Selection selectionMerge = null;
        for (int i10 = 0; i10 < size; i10++) {
            Selectable selectable = listSort.get(i10);
            Selection selectAllSelection = selectable.getSelectableId() == selectableId ? selectable.getSelectAllSelection() : null;
            if (selectAllSelection != null) {
                linkedHashMap.put(Long.valueOf(selectable.getSelectableId()), selectAllSelection);
            }
            selectionMerge = SelectionManagerKt.merge(selectionMerge, selectAllSelection);
        }
        if (isInTouchMode() && !kotlin.jvm.internal.p.a(selectionMerge, previousSelection) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
        }
        return new x(selectionMerge, linkedHashMap);
    }

    public final void setClipboardManager(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final void setContainerLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        this.containerLayoutCoordinates = layoutCoordinates;
        if (!getHasFocus() || getSelection() == null) {
            return;
        }
        Offset offsetM3225boximpl = layoutCoordinates != null ? Offset.m3225boximpl(LayoutCoordinatesKt.positionInWindow(layoutCoordinates)) : null;
        if (kotlin.jvm.internal.p.a(this.previousPosition, offsetM3225boximpl)) {
            return;
        }
        this.previousPosition = offsetM3225boximpl;
        updateHandleOffsets();
        updateSelectionToolbar();
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final void setHasFocus(boolean z) {
        this.hasFocus.setValue(Boolean.valueOf(z));
    }

    public final void setInTouchMode(boolean z) {
        if (this._isInTouchMode.getValue().booleanValue() != z) {
            this._isInTouchMode.setValue(Boolean.valueOf(z));
            updateSelectionToolbar();
        }
    }

    public final void setOnSelectionChange(l<? super Selection, t0> lVar) {
        this.onSelectionChange = new SelectionManager$onSelectionChange$2(this, lVar);
    }

    public final void setPreviousSelectionLayout$foundation_release(SelectionLayout selectionLayout) {
        this.previousSelectionLayout = selectionLayout;
    }

    public final void setSelection(Selection selection) {
        this._selection.setValue(selection);
        if (selection != null) {
            updateHandleOffsets();
        }
    }

    public final void setShowToolbar$foundation_release(boolean z) {
        this.showToolbar = z;
        updateSelectionToolbar();
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final boolean shouldPerformHaptics$foundation_release() {
        if (isInTouchMode()) {
            List<Selectable> selectables$foundation_release = this.selectionRegistrar.getSelectables$foundation_release();
            int size = selectables$foundation_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (selectables$foundation_release.get(i10).getText().length() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: updateSelection-jyLRC_s$foundation_release, reason: not valid java name */
    public final boolean m1004updateSelectionjyLRC_s$foundation_release(long position, long previousHandlePosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
        m992setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(position));
        SelectionLayout selectionLayoutM991getSelectionLayoutWko1d7g = m991getSelectionLayoutWko1d7g(position, previousHandlePosition, isStartHandle);
        if (!selectionLayoutM991getSelectionLayoutWko1d7g.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return false;
        }
        Selection selectionAdjust = adjustment.adjust(selectionLayoutM991getSelectionLayoutWko1d7g);
        if (!kotlin.jvm.internal.p.a(selectionAdjust, getSelection())) {
            selectionChanged(selectionLayoutM991getSelectionLayoutWko1d7g, selectionAdjust);
        }
        this.previousSelectionLayout = selectionLayoutM991getSelectionLayoutWko1d7g;
        return true;
    }

    /* JADX INFO: renamed from: updateSelection-qNKwrvQ$foundation_release, reason: not valid java name */
    public final boolean m1005updateSelectionqNKwrvQ$foundation_release(Offset newPosition, long previousPosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        if (newPosition == null) {
            return false;
        }
        return m1004updateSelectionjyLRC_s$foundation_release(newPosition.getPackedValue(), previousPosition, isStartHandle, adjustment);
    }
}
