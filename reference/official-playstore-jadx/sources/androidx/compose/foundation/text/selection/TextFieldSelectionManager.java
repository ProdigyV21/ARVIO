package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursorKt;
import androidx.compose.foundation.text.TextFieldState;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import qb.d;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000e\u001a\u00020\bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u000f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u001b\u001a\u00020\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001e\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u000f\u0010 \u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u001f\u0010\u0015J\u000f\u0010\"\u001a\u00020\u0010H\u0000¢\u0006\u0004\b!\u0010\u0015J\u000f\u0010$\u001a\u00020\u0010H\u0000¢\u0006\u0004\b#\u0010\u0015J\u001d\u0010'\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u0006H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u001d\u0010,\u001a\u00020\u00172\u0006\u0010)\u001a\u00020(H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u000f\u0010.\u001a\u00020\u0010H\u0000¢\u0006\u0004\b-\u0010\u0015J\u000f\u00100\u001a\u00020\u0010H\u0000¢\u0006\u0004\b/\u0010\u0015J\u0018\u00103\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0017ø\u0001\u0000¢\u0006\u0004\b1\u00102J\u000f\u00106\u001a\u00020\u0006H\u0000¢\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0006H\u0002¢\u0006\u0004\b8\u0010\u0012J\u000f\u0010:\u001a\u000209H\u0002¢\u0006\u0004\b:\u0010;JB\u0010F\u001a\u00020C2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u0017\u0010I\u001a\u00020\u00102\u0006\u0010H\u001a\u00020GH\u0002¢\u0006\u0004\bI\u0010JJ\"\u0010P\u001a\u00020<2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020CH\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010OR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010Q\u001a\u0004\bR\u0010SR\"\u0010U\u001a\u00020T8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR.\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00100[8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR$\u0010c\u001a\u0004\u0018\u00010b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR+\u0010=\u001a\u00020<2\u0006\u0010i\u001a\u00020<8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bj\u0010k\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\"\u0010q\u001a\u00020p8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bq\u0010r\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR$\u0010x\u001a\u0004\u0018\u00010w8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R)\u0010\u007f\u001a\u0004\u0018\u00010~8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R,\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0085\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0086\u0001\u0010\u0087\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R,\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u008d\u0001\u0010\u008e\u0001\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R/\u0010\u0096\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0093\u0001\u0010k\u001a\u0005\b\u0094\u0001\u00105\"\u0005\b\u0095\u0001\u0010\u0012R\u001f\u0010\u0097\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001c\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0099\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u009b\u0001R\u001f\u0010\u009c\u0001\u001a\u00020\u00178\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u0098\u0001R7\u0010£\u0001\u001a\u0005\u0018\u00010\u009d\u00012\t\u0010i\u001a\u0005\u0018\u00010\u009d\u00018F@BX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\b\u009e\u0001\u0010k\u001a\u0006\b\u009f\u0001\u0010 \u0001\"\u0006\b¡\u0001\u0010¢\u0001R:\u0010¨\u0001\u001a\u0004\u0018\u00010\u00172\b\u0010i\u001a\u0004\u0018\u00010\u00178F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0016\n\u0005\b¤\u0001\u0010k\u001a\u0006\b¥\u0001\u0010¦\u0001\"\u0005\b§\u0001\u0010\u001aR\u001a\u0010©\u0001\u001a\u00030\u0099\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0001\u0010ª\u0001R\u0019\u0010«\u0001\u001a\u00020<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R\u001c\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b®\u0001\u0010¯\u0001R\u001e\u0010°\u0001\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\u000f\n\u0006\b°\u0001\u0010±\u0001\u001a\u0005\b²\u0001\u0010\rR \u0010´\u0001\u001a\u00030³\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b´\u0001\u0010µ\u0001\u001a\u0006\b¶\u0001\u0010·\u0001R \u0010»\u0001\u001a\u0004\u0018\u00010K8@X\u0080\u0004¢\u0006\u000f\u0012\u0005\bº\u0001\u0010\u0015\u001a\u0006\b¸\u0001\u0010¹\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006¼\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "", "Landroidx/compose/foundation/text/UndoManager;", "undoManager", "<init>", "(Landroidx/compose/foundation/text/UndoManager;)V", "", "isStartHandle", "Landroidx/compose/foundation/text/TextDragObserver;", "handleDragObserver$foundation_release", "(Z)Landroidx/compose/foundation/text/TextDragObserver;", "handleDragObserver", "cursorDragObserver$foundation_release", "()Landroidx/compose/foundation/text/TextDragObserver;", "cursorDragObserver", "showFloatingToolbar", "Lx6/t0;", "enterSelectionMode$foundation_release", "(Z)V", "enterSelectionMode", "exitSelectionMode$foundation_release", "()V", "exitSelectionMode", "Landroidx/compose/ui/geometry/Offset;", "position", "deselect-_kEHs6E$foundation_release", "(Landroidx/compose/ui/geometry/Offset;)V", "deselect", "cancelSelection", "copy$foundation_release", "copy", "paste$foundation_release", "paste", "cut$foundation_release", "cut", "selectAll$foundation_release", "selectAll", "getHandlePosition-tuRUvjQ$foundation_release", "(Z)J", "getHandlePosition", "Landroidx/compose/ui/unit/Density;", "density", "getCursorPosition-tuRUvjQ$foundation_release", "(Landroidx/compose/ui/unit/Density;)J", "getCursorPosition", "showSelectionToolbar$foundation_release", "showSelectionToolbar", "hideSelectionToolbar$foundation_release", "hideSelectionToolbar", "contextMenuOpenAdjustment-k-4lQ0M", "(J)V", "contextMenuOpenAdjustment", "isTextChanged$foundation_release", "()Z", "isTextChanged", "show", "updateFloatingToolbar", "Landroidx/compose/ui/geometry/Rect;", "getContentRect", "()Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "currentPosition", "isStartOfSelection", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustment", "isTouchBasedSelection", "Landroidx/compose/ui/text/TextRange;", "updateSelection-8UEBfa8", "(Landroidx/compose/ui/text/input/TextFieldValue;JZZLandroidx/compose/foundation/text/selection/SelectionAdjustment;Z)J", "updateSelection", "Landroidx/compose/foundation/text/HandleState;", "handleState", "setHandleState", "(Landroidx/compose/foundation/text/HandleState;)V", "Landroidx/compose/ui/text/AnnotatedString;", "annotatedString", "selection", "createTextFieldValue-FDrldGo", "(Landroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/input/TextFieldValue;", "createTextFieldValue", "Landroidx/compose/foundation/text/UndoManager;", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping$foundation_release", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping$foundation_release", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "Lkotlin/Function1;", "onValueChange", "Lr7/l;", "getOnValueChange$foundation_release", "()Lr7/l;", "setOnValueChange$foundation_release", "(Lr7/l;)V", "Landroidx/compose/foundation/text/TextFieldState;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "getState$foundation_release", "()Landroidx/compose/foundation/text/TextFieldState;", "setState$foundation_release", "(Landroidx/compose/foundation/text/TextFieldState;)V", "<set-?>", "value$delegate", "Landroidx/compose/runtime/MutableState;", "getValue$foundation_release", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "getVisualTransformation$foundation_release", "()Landroidx/compose/ui/text/input/VisualTransformation;", "setVisualTransformation$foundation_release", "(Landroidx/compose/ui/text/input/VisualTransformation;)V", "Landroidx/compose/ui/platform/ClipboardManager;", "clipboardManager", "Landroidx/compose/ui/platform/ClipboardManager;", "getClipboardManager$foundation_release", "()Landroidx/compose/ui/platform/ClipboardManager;", "setClipboardManager$foundation_release", "(Landroidx/compose/ui/platform/ClipboardManager;)V", "Landroidx/compose/ui/platform/TextToolbar;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "editable$delegate", "getEditable", "setEditable", "editable", "dragBeginPosition", "J", "", "dragBeginOffsetInText", "Ljava/lang/Integer;", "dragTotalDistance", "Landroidx/compose/foundation/text/Handle;", "draggingHandle$delegate", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle", "currentDragPosition$delegate", "getCurrentDragPosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setCurrentDragPosition-_kEHs6E", "currentDragPosition", "previousRawDragOffset", "I", "oldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "touchSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getTouchSelectionObserver$foundation_release", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver$foundation_release", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getTransformedText$foundation_release", "()Landroidx/compose/ui/text/AnnotatedString;", "getTransformedText$foundation_release$annotations", "transformedText", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldSelectionManager {
    public static final int $stable = 8;
    private ClipboardManager clipboardManager;

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;
    private Integer dragBeginOffsetInText;
    private long dragBeginPosition;
    private long dragTotalDistance;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* JADX INFO: renamed from: editable$delegate, reason: from kotlin metadata */
    private final MutableState editable;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;
    private final MouseSelectionObserver mouseSelectionObserver;
    private OffsetMapping offsetMapping;
    private TextFieldValue oldValue;
    private l<? super TextFieldValue, t0> onValueChange;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;
    private TextFieldState state;
    private TextToolbar textToolbar;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;

    /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;
    private VisualTransformation visualTransformation;

    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldSelectionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void copy$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.copy$foundation_release(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m1028createTextFieldValueFDrldGo(AnnotatedString annotatedString, long selection) {
        return new TextFieldValue(annotatedString, selection, (TextRange) null, 4, (h) null);
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release$default, reason: not valid java name */
    public static /* synthetic */ void m1029deselect_kEHs6E$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m1033deselect_kEHs6E$foundation_release(offset);
    }

    public static /* synthetic */ void enterSelectionMode$foundation_release$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.enterSelectionMode$foundation_release(z);
    }

    private final Rect getContentRect() {
        float fM3237getYimpl;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResult value;
        Rect cursorRect;
        LayoutCoordinates layoutCoordinates2;
        TextLayoutResult value2;
        Rect cursorRect2;
        LayoutCoordinates layoutCoordinates3;
        LayoutCoordinates layoutCoordinates4;
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            if (textFieldState.getIsLayoutResultStale()) {
                textFieldState = null;
            }
            if (textFieldState != null) {
                int iOriginalToTransformed = this.offsetMapping.originalToTransformed(TextRange.m5192getStartimpl(getValue$foundation_release().getSelection()));
                int iOriginalToTransformed2 = this.offsetMapping.originalToTransformed(TextRange.m5187getEndimpl(getValue$foundation_release().getSelection()));
                TextFieldState textFieldState2 = this.state;
                long jM3252getZeroF1C5BW0 = (textFieldState2 == null || (layoutCoordinates4 = textFieldState2.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m3252getZeroF1C5BW0() : layoutCoordinates4.mo4640localToRootMKHz9U(m1036getHandlePositiontuRUvjQ$foundation_release(true));
                TextFieldState textFieldState3 = this.state;
                long jM3252getZeroF1C5BW02 = (textFieldState3 == null || (layoutCoordinates3 = textFieldState3.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m3252getZeroF1C5BW0() : layoutCoordinates3.mo4640localToRootMKHz9U(m1036getHandlePositiontuRUvjQ$foundation_release(false));
                TextFieldState textFieldState4 = this.state;
                float fM3237getYimpl2 = 0.0f;
                if (textFieldState4 == null || (layoutCoordinates2 = textFieldState4.getLayoutCoordinates()) == null) {
                    fM3237getYimpl = 0.0f;
                } else {
                    TextLayoutResultProxy layoutResult = textFieldState.getLayoutResult();
                    fM3237getYimpl = Offset.m3237getYimpl(layoutCoordinates2.mo4640localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult == null || (value2 = layoutResult.getValue()) == null || (cursorRect2 = value2.getCursorRect(iOriginalToTransformed)) == null) ? 0.0f : cursorRect2.getTop())));
                }
                TextFieldState textFieldState5 = this.state;
                if (textFieldState5 != null && (layoutCoordinates = textFieldState5.getLayoutCoordinates()) != null) {
                    TextLayoutResultProxy layoutResult2 = textFieldState.getLayoutResult();
                    fM3237getYimpl2 = Offset.m3237getYimpl(layoutCoordinates.mo4640localToRootMKHz9U(OffsetKt.Offset(0.0f, (layoutResult2 == null || (value = layoutResult2.getValue()) == null || (cursorRect = value.getCursorRect(iOriginalToTransformed2)) == null) ? 0.0f : cursorRect.getTop())));
                }
                return new Rect(Math.min(Offset.m3236getXimpl(jM3252getZeroF1C5BW0), Offset.m3236getXimpl(jM3252getZeroF1C5BW02)), Math.min(fM3237getYimpl, fM3237getYimpl2), Math.max(Offset.m3236getXimpl(jM3252getZeroF1C5BW0), Offset.m3236getXimpl(jM3252getZeroF1C5BW02)), (textFieldState.getTextDelegate().getDensity().getDensity() * Dp.m5678constructorimpl(25)) + Math.max(Offset.m3237getYimpl(jM3252getZeroF1C5BW0), Offset.m3237getYimpl(jM3252getZeroF1C5BW02)));
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public static /* synthetic */ void getTransformedText$foundation_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m1030setCurrentDragPosition_kEHs6E(Offset offset) {
        this.currentDragPosition.setValue(offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        this.draggingHandle.setValue(handle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHandleState(HandleState handleState) {
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            if (textFieldState.getHandleState() == handleState) {
                textFieldState = null;
            }
            if (textFieldState != null) {
                textFieldState.setHandleState(handleState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingToolbar(boolean show) {
        TextFieldState textFieldState = this.state;
        if (textFieldState != null) {
            textFieldState.setShowFloatingToolbar(show);
        }
        if (show) {
            showSelectionToolbar$foundation_release();
        } else {
            hideSelectionToolbar$foundation_release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSelection-8UEBfa8, reason: not valid java name */
    public final long m1031updateSelection8UEBfa8(TextFieldValue value, long currentPosition, boolean isStartOfSelection, boolean isStartHandle, SelectionAdjustment adjustment, boolean isTouchBasedSelection) {
        TextLayoutResultProxy layoutResult;
        int i10;
        HapticFeedback hapticFeedback;
        TextFieldState textFieldState = this.state;
        if (textFieldState == null || (layoutResult = textFieldState.getLayoutResult()) == null) {
            return TextRange.INSTANCE.m5197getZerod9O1mEE();
        }
        long jTextRange = TextRangeKt.TextRange(this.offsetMapping.originalToTransformed(TextRange.m5192getStartimpl(value.getSelection())), this.offsetMapping.originalToTransformed(TextRange.m5187getEndimpl(value.getSelection())));
        int iM909getOffsetForPosition3MmeM6k = layoutResult.m909getOffsetForPosition3MmeM6k(currentPosition, false);
        int iM5192getStartimpl = (isStartHandle || isStartOfSelection) ? iM909getOffsetForPosition3MmeM6k : TextRange.m5192getStartimpl(jTextRange);
        int iM5187getEndimpl = (!isStartHandle || isStartOfSelection) ? iM909getOffsetForPosition3MmeM6k : TextRange.m5187getEndimpl(jTextRange);
        SelectionLayout selectionLayout = this.previousSelectionLayout;
        if (isStartOfSelection || selectionLayout == null || (i10 = this.previousRawDragOffset) == -1) {
            i10 = -1;
        }
        SelectionLayout selectionLayoutM977getTextFieldSelectionLayoutRcvTLA = SelectionLayoutKt.m977getTextFieldSelectionLayoutRcvTLA(layoutResult.getValue(), iM5192getStartimpl, iM5187getEndimpl, i10, jTextRange, isStartOfSelection, isStartHandle);
        if (!selectionLayoutM977getTextFieldSelectionLayoutRcvTLA.shouldRecomputeSelection(selectionLayout)) {
            return value.getSelection();
        }
        this.previousSelectionLayout = selectionLayoutM977getTextFieldSelectionLayoutRcvTLA;
        this.previousRawDragOffset = iM909getOffsetForPosition3MmeM6k;
        Selection selectionAdjust = adjustment.adjust(selectionLayoutM977getTextFieldSelectionLayoutRcvTLA);
        long jTextRange2 = TextRangeKt.TextRange(this.offsetMapping.transformedToOriginal(selectionAdjust.getStart().getOffset()), this.offsetMapping.transformedToOriginal(selectionAdjust.getEnd().getOffset()));
        if (TextRange.m5185equalsimpl0(jTextRange2, value.getSelection())) {
            return value.getSelection();
        }
        boolean z = TextRange.m5191getReversedimpl(jTextRange2) != TextRange.m5191getReversedimpl(value.getSelection()) && TextRange.m5185equalsimpl0(TextRangeKt.TextRange(TextRange.m5187getEndimpl(jTextRange2), TextRange.m5192getStartimpl(jTextRange2)), value.getSelection());
        boolean z5 = TextRange.m5186getCollapsedimpl(jTextRange2) && TextRange.m5186getCollapsedimpl(value.getSelection());
        if (isTouchBasedSelection && value.getText().length() > 0 && !z && !z5 && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
        }
        TextFieldValue textFieldValueM1028createTextFieldValueFDrldGo = m1028createTextFieldValueFDrldGo(value.getAnnotatedString(), jTextRange2);
        this.onValueChange.invoke(textFieldValueM1028createTextFieldValueFDrldGo);
        setHandleState(TextRange.m5186getCollapsedimpl(textFieldValueM1028createTextFieldValueFDrldGo.getSelection()) ? HandleState.Cursor : HandleState.Selection);
        TextFieldState textFieldState2 = this.state;
        if (textFieldState2 != null) {
            textFieldState2.setInTouchMode(isTouchBasedSelection);
        }
        TextFieldState textFieldState3 = this.state;
        if (textFieldState3 != null) {
            textFieldState3.setShowSelectionHandleStart(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, true));
        }
        TextFieldState textFieldState4 = this.state;
        if (textFieldState4 == null) {
            return jTextRange2;
        }
        textFieldState4.setShowSelectionHandleEnd(TextFieldSelectionManagerKt.isSelectionHandleInVisibleBound(this, false));
        return jTextRange2;
    }

    /* JADX INFO: renamed from: contextMenuOpenAdjustment-k-4lQ0M, reason: not valid java name */
    public final void m1032contextMenuOpenAdjustmentk4lQ0M(long position) {
        TextLayoutResultProxy layoutResult;
        TextFieldState textFieldState = this.state;
        if (textFieldState == null || (layoutResult = textFieldState.getLayoutResult()) == null) {
            return;
        }
        if (TextRange.m5183containsimpl(getValue$foundation_release().getSelection(), TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, position, false, 2, null))) {
            return;
        }
        this.previousRawDragOffset = -1;
        m1031updateSelection8UEBfa8(getValue$foundation_release(), position, true, false, SelectionAdjustment.INSTANCE.getWord(), false);
    }

    public final void copy$foundation_release(boolean cancelSelection) {
        if (TextRange.m5186getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        if (cancelSelection) {
            int iM5189getMaximpl = TextRange.m5189getMaximpl(getValue$foundation_release().getSelection());
            this.onValueChange.invoke(m1028createTextFieldValueFDrldGo(getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(iM5189getMaximpl, iM5189getMaximpl)));
            setHandleState(HandleState.None);
        }
    }

    public final TextDragObserver cursorDragObserver$foundation_release() {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo875onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo876onDragk4lQ0M(long delta) {
                TextLayoutResultProxy layoutResult;
                HapticFeedback hapticFeedBack;
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m3241plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                TextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                textFieldSelectionManager2.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(Offset.m3241plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                int iTransformedToOriginal = textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false, 2, null));
                long jTextRange = TextRangeKt.TextRange(iTransformedToOriginal, iTransformedToOriginal);
                if (TextRange.m5185equalsimpl0(jTextRange, textFieldSelectionManager2.getValue$foundation_release().getSelection())) {
                    return;
                }
                TextFieldState state2 = textFieldSelectionManager2.getState();
                if ((state2 == null || state2.isInTouchMode()) && (hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack()) != null) {
                    hapticFeedBack.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
                }
                textFieldSelectionManager2.getOnValueChange$foundation_release().invoke(textFieldSelectionManager2.m1028createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation_release().getAnnotatedString(), jTextRange));
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo877onStartk4lQ0M(long startPoint) {
                TextLayoutResultProxy layoutResult;
                long jM974getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(this.this$0.m1036getHandlePositiontuRUvjQ$foundation_release(true));
                TextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m912translateInnerToDecorationCoordinatesMKHz9U$foundation_release(jM974getAdjustedCoordinatesk4lQ0M);
                this.this$0.dragBeginPosition = jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                this.this$0.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m3252getZeroF1C5BW0();
                this.this$0.setDraggingHandle(Handle.Cursor);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1030setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1030setCurrentDragPosition_kEHs6E(null);
            }
        };
    }

    public final void cut$foundation_release() {
        if (TextRange.m5186getCollapsedimpl(getValue$foundation_release().getSelection())) {
            return;
        }
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager != null) {
            clipboardManager.setText(TextFieldValueKt.getSelectedText(getValue$foundation_release()));
        }
        AnnotatedString annotatedStringPlus = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int iM5190getMinimpl = TextRange.m5190getMinimpl(getValue$foundation_release().getSelection());
        this.onValueChange.invoke(m1028createTextFieldValueFDrldGo(annotatedStringPlus, TextRangeKt.TextRange(iM5190getMinimpl, iM5190getMinimpl)));
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation_release, reason: not valid java name */
    public final void m1033deselect_kEHs6E$foundation_release(Offset position) {
        if (!TextRange.m5186getCollapsedimpl(getValue$foundation_release().getSelection())) {
            TextFieldState textFieldState = this.state;
            TextLayoutResultProxy layoutResult = textFieldState != null ? textFieldState.getLayoutResult() : null;
            this.onValueChange.invoke(TextFieldValue.m5424copy3r_uNRQ$default(getValue$foundation_release(), (AnnotatedString) null, TextRangeKt.TextRange((position == null || layoutResult == null) ? TextRange.m5189getMaximpl(getValue$foundation_release().getSelection()) : this.offsetMapping.transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, position.getPackedValue(), false, 2, null))), (TextRange) null, 5, (Object) null));
        }
        setHandleState((position == null || getValue$foundation_release().getText().length() <= 0) ? HandleState.None : HandleState.Cursor);
        updateFloatingToolbar(false);
    }

    public final void enterSelectionMode$foundation_release(boolean showFloatingToolbar) {
        FocusRequester focusRequester;
        TextFieldState textFieldState = this.state;
        if (textFieldState != null && !textFieldState.getHasFocus() && (focusRequester = this.focusRequester) != null) {
            focusRequester.requestFocus();
        }
        this.oldValue = getValue$foundation_release();
        updateFloatingToolbar(showFloatingToolbar);
        setHandleState(HandleState.Selection);
    }

    public final void exitSelectionMode$foundation_release() {
        updateFloatingToolbar(false);
        setHandleState(HandleState.None);
    }

    /* JADX INFO: renamed from: getClipboardManager$foundation_release, reason: from getter */
    public final ClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m1034getCurrentDragPosition_m7T9E() {
        return (Offset) this.currentDragPosition.getValue();
    }

    /* JADX INFO: renamed from: getCursorPosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1035getCursorPositiontuRUvjQ$foundation_release(Density density) {
        int iOriginalToTransformed = this.offsetMapping.originalToTransformed(TextRange.m5192getStartimpl(getValue$foundation_release().getSelection()));
        TextFieldState textFieldState = this.state;
        TextLayoutResult value = (textFieldState != null ? textFieldState.getLayoutResult() : null).getValue();
        Rect cursorRect = value.getCursorRect(d.n(iOriginalToTransformed, 0, value.getLayoutInput().getText().length()));
        return OffsetKt.Offset((density.mo285toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness()) / 2) + cursorRect.getLeft(), cursorRect.getBottom());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Handle getDraggingHandle() {
        return (Handle) this.draggingHandle.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getEditable() {
        return ((Boolean) this.editable.getValue()).booleanValue();
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ$foundation_release, reason: not valid java name */
    public final long m1036getHandlePositiontuRUvjQ$foundation_release(boolean isStartHandle) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        TextFieldState textFieldState = this.state;
        if (textFieldState == null || (layoutResult = textFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        AnnotatedString transformedText$foundation_release = getTransformedText$foundation_release();
        if (transformedText$foundation_release == null) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        if (!p.a(transformedText$foundation_release.getText(), value.getLayoutInput().getText().getText())) {
            return Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
        }
        long selection = getValue$foundation_release().getSelection();
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(value, this.offsetMapping.originalToTransformed(isStartHandle ? TextRange.m5192getStartimpl(selection) : TextRange.m5187getEndimpl(selection)), isStartHandle, TextRange.m5191getReversedimpl(getValue$foundation_release().getSelection()));
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    /* JADX INFO: renamed from: getMouseSelectionObserver$foundation_release, reason: from getter */
    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    /* JADX INFO: renamed from: getOffsetMapping$foundation_release, reason: from getter */
    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final l<TextFieldValue, t0> getOnValueChange$foundation_release() {
        return this.onValueChange;
    }

    /* JADX INFO: renamed from: getState$foundation_release, reason: from getter */
    public final TextFieldState getState() {
        return this.state;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    /* JADX INFO: renamed from: getTouchSelectionObserver$foundation_release, reason: from getter */
    public final TextDragObserver getTouchSelectionObserver() {
        return this.touchSelectionObserver;
    }

    public final AnnotatedString getTransformedText$foundation_release() {
        TextDelegate textDelegate;
        TextFieldState textFieldState = this.state;
        if (textFieldState == null || (textDelegate = textFieldState.getTextDelegate()) == null) {
            return null;
        }
        return textDelegate.getText();
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TextFieldValue getValue$foundation_release() {
        return (TextFieldValue) this.value.getValue();
    }

    /* JADX INFO: renamed from: getVisualTransformation$foundation_release, reason: from getter */
    public final VisualTransformation getVisualTransformation() {
        return this.visualTransformation;
    }

    public final TextDragObserver handleDragObserver$foundation_release(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo875onDownk4lQ0M(long point) {
                TextLayoutResultProxy layoutResult;
                this.this$0.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                long jM974getAdjustedCoordinatesk4lQ0M = SelectionHandlesKt.m974getAdjustedCoordinatesk4lQ0M(this.this$0.m1036getHandlePositiontuRUvjQ$foundation_release(isStartHandle));
                TextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release = layoutResult.m912translateInnerToDecorationCoordinatesMKHz9U$foundation_release(jM974getAdjustedCoordinatesk4lQ0M);
                this.this$0.dragBeginPosition = jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release;
                this.this$0.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(jM912translateInnerToDecorationCoordinatesMKHz9U$foundation_release));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m3252getZeroF1C5BW0();
                this.this$0.previousRawDragOffset = -1;
                TextFieldState state2 = this.this$0.getState();
                if (state2 != null) {
                    state2.setInTouchMode(true);
                }
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo876onDragk4lQ0M(long delta) {
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m3241plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                textFieldSelectionManager2.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(Offset.m3241plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, this.this$0.dragTotalDistance)));
                TextFieldSelectionManager textFieldSelectionManager3 = this.this$0;
                textFieldSelectionManager3.m1031updateSelection8UEBfa8(textFieldSelectionManager3.getValue$foundation_release(), this.this$0.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false, isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo877onStartk4lQ0M(long startPoint) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1030setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1030setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }
        };
    }

    public final void hideSelectionToolbar$foundation_release() {
        TextToolbar textToolbar;
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    public final boolean isTextChanged$foundation_release() {
        return !p.a(this.oldValue.getText(), getValue$foundation_release().getText());
    }

    public final void paste$foundation_release() {
        AnnotatedString text;
        ClipboardManager clipboardManager = this.clipboardManager;
        if (clipboardManager == null || (text = clipboardManager.getText()) == null) {
            return;
        }
        AnnotatedString annotatedStringPlus = TextFieldValueKt.getTextBeforeSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()).plus(text).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation_release(), getValue$foundation_release().getText().length()));
        int length = text.length() + TextRange.m5190getMinimpl(getValue$foundation_release().getSelection());
        this.onValueChange.invoke(m1028createTextFieldValueFDrldGo(annotatedStringPlus, TextRangeKt.TextRange(length, length)));
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
    }

    public final void selectAll$foundation_release() {
        TextFieldValue textFieldValueM1028createTextFieldValueFDrldGo = m1028createTextFieldValueFDrldGo(getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(0, getValue$foundation_release().getText().length()));
        this.onValueChange.invoke(textFieldValueM1028createTextFieldValueFDrldGo);
        this.oldValue = TextFieldValue.m5424copy3r_uNRQ$default(this.oldValue, (AnnotatedString) null, textFieldValueM1028createTextFieldValueFDrldGo.getSelection(), (TextRange) null, 5, (Object) null);
        enterSelectionMode$foundation_release(true);
    }

    public final void setClipboardManager$foundation_release(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    public final void setEditable(boolean z) {
        this.editable.setValue(Boolean.valueOf(z));
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final void setOffsetMapping$foundation_release(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final void setOnValueChange$foundation_release(l<? super TextFieldValue, t0> lVar) {
        this.onValueChange = lVar;
    }

    public final void setState$foundation_release(TextFieldState textFieldState) {
        this.state = textFieldState;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final void setValue$foundation_release(TextFieldValue textFieldValue) {
        this.value.setValue(textFieldValue);
    }

    public final void setVisualTransformation$foundation_release(VisualTransformation visualTransformation) {
        this.visualTransformation = visualTransformation;
    }

    public final void showSelectionToolbar$foundation_release() {
        ClipboardManager clipboardManager;
        TextFieldState textFieldState = this.state;
        if (textFieldState == null || textFieldState.isInTouchMode()) {
            boolean z = this.visualTransformation instanceof PasswordVisualTransformation;
            TextFieldSelectionManager$showSelectionToolbar$copy$1 textFieldSelectionManager$showSelectionToolbar$copy$1 = (TextRange.m5186getCollapsedimpl(getValue$foundation_release().getSelection()) || z) ? null : new TextFieldSelectionManager$showSelectionToolbar$copy$1(this);
            TextFieldSelectionManager$showSelectionToolbar$cut$1 textFieldSelectionManager$showSelectionToolbar$cut$1 = (TextRange.m5186getCollapsedimpl(getValue$foundation_release().getSelection()) || !getEditable() || z) ? null : new TextFieldSelectionManager$showSelectionToolbar$cut$1(this);
            TextFieldSelectionManager$showSelectionToolbar$paste$1 textFieldSelectionManager$showSelectionToolbar$paste$1 = (getEditable() && (clipboardManager = this.clipboardManager) != null && clipboardManager.hasText()) ? new TextFieldSelectionManager$showSelectionToolbar$paste$1(this) : null;
            TextFieldSelectionManager$showSelectionToolbar$selectAll$1 textFieldSelectionManager$showSelectionToolbar$selectAll$1 = TextRange.m5188getLengthimpl(getValue$foundation_release().getSelection()) != getValue$foundation_release().getText().length() ? new TextFieldSelectionManager$showSelectionToolbar$selectAll$1(this) : null;
            TextToolbar textToolbar = this.textToolbar;
            if (textToolbar != null) {
                textToolbar.showMenu(getContentRect(), textFieldSelectionManager$showSelectionToolbar$copy$1, textFieldSelectionManager$showSelectionToolbar$paste$1, textFieldSelectionManager$showSelectionToolbar$cut$1, textFieldSelectionManager$showSelectionToolbar$selectAll$1);
            }
        }
    }

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        this.offsetMapping = ValidatingOffsetMappingKt.getValidatingEmptyOffsetMappingIdentity();
        this.onValueChange = TextFieldSelectionManager$onValueChange$1.INSTANCE;
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (h) null), null, 2, null);
        this.visualTransformation = VisualTransformation.INSTANCE.getNone();
        this.editable = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
        Offset.Companion companion = Offset.INSTANCE;
        this.dragBeginPosition = companion.m3252getZeroF1C5BW0();
        this.dragTotalDistance = companion.m3252getZeroF1C5BW0();
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) null, 7, (h) null);
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo875onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo876onDragk4lQ0M(long delta) {
                TextLayoutResultProxy layoutResult;
                long jM1031updateSelection8UEBfa8;
                if (this.this$0.getValue$foundation_release().getText().length() == 0) {
                    return;
                }
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.dragTotalDistance = Offset.m3241plusMKHz9U(textFieldSelectionManager.dragTotalDistance, delta);
                TextFieldState state = this.this$0.getState();
                if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                    textFieldSelectionManager2.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(Offset.m3241plusMKHz9U(textFieldSelectionManager2.dragBeginPosition, textFieldSelectionManager2.dragTotalDistance)));
                    if (textFieldSelectionManager2.dragBeginOffsetInText != null || layoutResult.m910isPositionOnTextk4lQ0M(textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue())) {
                        Integer num = textFieldSelectionManager2.dragBeginOffsetInText;
                        int iIntValue = num != null ? num.intValue() : layoutResult.m909getOffsetForPosition3MmeM6k(textFieldSelectionManager2.dragBeginPosition, false);
                        int iM909getOffsetForPosition3MmeM6k = layoutResult.m909getOffsetForPosition3MmeM6k(textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false);
                        if (textFieldSelectionManager2.dragBeginOffsetInText == null && iIntValue == iM909getOffsetForPosition3MmeM6k) {
                            return;
                        } else {
                            jM1031updateSelection8UEBfa8 = textFieldSelectionManager2.m1031updateSelection8UEBfa8(textFieldSelectionManager2.getValue$foundation_release(), textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true);
                        }
                    } else {
                        jM1031updateSelection8UEBfa8 = textFieldSelectionManager2.m1031updateSelection8UEBfa8(textFieldSelectionManager2.getValue$foundation_release(), textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false, false, textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, textFieldSelectionManager2.dragBeginPosition, false, 2, null)) == textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, textFieldSelectionManager2.m1034getCurrentDragPosition_m7T9E().getPackedValue(), false, 2, null)) ? SelectionAdjustment.INSTANCE.getNone() : SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true);
                    }
                    TextRange.m5180boximpl(jM1031updateSelection8UEBfa8);
                }
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-k-4lQ0M */
            public void mo877onStartk4lQ0M(long startPoint) {
                long j10;
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                if (this.this$0.getDraggingHandle() != null) {
                    return;
                }
                this.this$0.setDraggingHandle(Handle.SelectionEnd);
                this.this$0.previousRawDragOffset = -1;
                this.this$0.hideSelectionToolbar$foundation_release();
                TextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult2 = state.getLayoutResult()) == null || !layoutResult2.m910isPositionOnTextk4lQ0M(startPoint)) {
                    j10 = startPoint;
                    TextFieldState state2 = this.this$0.getState();
                    if (state2 != null && (layoutResult = state2.getLayoutResult()) != null) {
                        TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                        int iTransformedToOriginal = textFieldSelectionManager.getOffsetMapping().transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(layoutResult, j10, false, 2, null));
                        TextFieldValue textFieldValueM1028createTextFieldValueFDrldGo = textFieldSelectionManager.m1028createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation_release().getAnnotatedString(), TextRangeKt.TextRange(iTransformedToOriginal, iTransformedToOriginal));
                        textFieldSelectionManager.enterSelectionMode$foundation_release(false);
                        textFieldSelectionManager.setHandleState(HandleState.Cursor);
                        HapticFeedback hapticFeedBack = textFieldSelectionManager.getHapticFeedBack();
                        if (hapticFeedBack != null) {
                            hapticFeedBack.mo4064performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m4073getTextHandleMove5zf0vsI());
                        }
                        textFieldSelectionManager.getOnValueChange$foundation_release().invoke(textFieldValueM1028createTextFieldValueFDrldGo);
                    }
                } else {
                    if (this.this$0.getValue$foundation_release().getText().length() == 0) {
                        return;
                    }
                    this.this$0.enterSelectionMode$foundation_release(false);
                    TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                    j10 = startPoint;
                    this.this$0.dragBeginOffsetInText = Integer.valueOf(TextRange.m5192getStartimpl(textFieldSelectionManager2.m1031updateSelection8UEBfa8(TextFieldValue.m5424copy3r_uNRQ$default(textFieldSelectionManager2.getValue$foundation_release(), (AnnotatedString) null, TextRange.INSTANCE.m5197getZerod9O1mEE(), (TextRange) null, 5, (Object) null), startPoint, true, false, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true)));
                }
                this.this$0.dragBeginPosition = j10;
                TextFieldSelectionManager textFieldSelectionManager3 = this.this$0;
                textFieldSelectionManager3.m1030setCurrentDragPosition_kEHs6E(Offset.m3225boximpl(textFieldSelectionManager3.dragBeginPosition));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m3252getZeroF1C5BW0();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m1030setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
                this.this$0.dragBeginOffsetInText = null;
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo948onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                TextFieldState state;
                if (this.this$0.getValue$foundation_release().getText().length() == 0 || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.m1031updateSelection8UEBfa8(textFieldSelectionManager.getValue$foundation_release(), dragPosition, false, false, adjustment, false);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo949onExtendk4lQ0M(long downPosition) {
                TextFieldState state = this.this$0.getState();
                if (state == null || state.getLayoutResult() == null) {
                    return false;
                }
                this.this$0.previousRawDragOffset = -1;
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.m1031updateSelection8UEBfa8(textFieldSelectionManager.getValue$foundation_release(), downPosition, false, false, SelectionAdjustment.INSTANCE.getNone(), false);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo950onExtendDragk4lQ0M(long dragPosition) {
                TextFieldState state;
                if (this.this$0.getValue$foundation_release().getText().length() == 0 || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                    return false;
                }
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.m1031updateSelection8UEBfa8(textFieldSelectionManager.getValue$foundation_release(), dragPosition, false, false, SelectionAdjustment.INSTANCE.getNone(), false);
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public boolean mo951onStart3MmeM6k(long downPosition, SelectionAdjustment adjustment) {
                TextFieldState state;
                if (this.this$0.getValue$foundation_release().getText().length() == 0 || (state = this.this$0.getState()) == null || state.getLayoutResult() == null) {
                    return false;
                }
                FocusRequester focusRequester = this.this$0.getFocusRequester();
                if (focusRequester != null) {
                    focusRequester.requestFocus();
                }
                this.this$0.dragBeginPosition = downPosition;
                this.this$0.previousRawDragOffset = -1;
                TextFieldSelectionManager.enterSelectionMode$foundation_release$default(this.this$0, false, 1, null);
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                textFieldSelectionManager.m1031updateSelection8UEBfa8(textFieldSelectionManager.getValue$foundation_release(), this.this$0.dragBeginPosition, true, false, adjustment, false);
                return true;
            }
        };
    }

    public /* synthetic */ TextFieldSelectionManager(UndoManager undoManager, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : undoManager);
    }
}
