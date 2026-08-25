package androidx.compose.foundation.text2.input.internal;

import android.view.KeyEvent;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text2.input.internal.selection.TextToolbarState;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNodeKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.node.f;
import androidx.compose.ui.node.h;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.PlatformTextInputModifierNode;
import androidx.compose.ui.platform.PlatformTextInputModifierNodeKt;
import androidx.compose.ui.platform.PlatformTextInputSessionScope;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.e;
import f7.j;
import java.util.List;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.k0;
import ka.m0;
import ka.v1;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ï\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001u\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\nBQ\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0013¢\u0006\u0004\b\u001b\u0010\u001cJW\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0013¢\u0006\u0004\b\u001e\u0010\u001cJ\u0013\u0010 \u001a\u00020\u001d*\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u001dH\u0016¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u001dH\u0016¢\u0006\u0004\b(\u0010'J\u0017\u0010+\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J*\u00105\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020-2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u000201H\u0016ø\u0001\u0000¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u00020\u001dH\u0016¢\u0006\u0004\b6\u0010'J\u001a\u0010;\u001a\u00020\u00132\u0006\u00108\u001a\u000207H\u0016ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001a\u0010=\u001a\u00020\u00132\u0006\u00108\u001a\u000207H\u0016ø\u0001\u0000¢\u0006\u0004\b<\u0010:J\u000f\u0010>\u001a\u00020\u001dH\u0016¢\u0006\u0004\b>\u0010'J\u000f\u0010?\u001a\u00020\u001dH\u0002¢\u0006\u0004\b?\u0010'J\u000f\u0010@\u001a\u00020\u001dH\u0002¢\u0006\u0004\b@\u0010'J\u000f\u0010A\u001a\u00020\u001dH\u0002¢\u0006\u0004\bA\u0010'J\u000f\u0010C\u001a\u00020BH\u0002¢\u0006\u0004\bC\u0010DR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\"\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010T\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\"\u0010\u0015\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010Y\u001a\u0004\b^\u0010[\"\u0004\b_\u0010]R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010`\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\"\u0010\u001a\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010Y\u001a\u0004\be\u0010[\"\u0004\bf\u0010]R\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR$\u0010\u0017\u001a\u00020\u00162\u0006\u0010j\u001a\u00020\u00168\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0017\u0010k\u001a\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bn\u0010YR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bp\u0010qR\u0014\u0010s\u001a\u00020r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010v\u001a\u00020u8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010wR \u0010z\u001a\u000e\u0012\u0004\u0012\u00020y\u0012\u0004\u0012\u00020\u001d0x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bz\u0010{R\u0018\u0010}\u001a\u0004\u0018\u00010|8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u0015\u0010\u0080\u0001\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010[R\u0016\u0010\u0081\u0001\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010[\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0082\u0001"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/platform/PlatformTextInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "textLayoutState", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "textFieldSelectionState", "Landroidx/compose/foundation/text2/input/InputTransformation;", "filter", "", "enabled", "readOnly", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "singleLine", "<init>", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text2/input/internal/TextLayoutState;Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text2/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Z)V", "Lx6/t0;", "updateNode", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "Landroidx/compose/ui/focus/FocusState;", "focusState", "onFocusEvent", "(Landroidx/compose/ui/focus/FocusState;)V", "onAttach", "()V", "onDetach", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "onGloballyPositioned", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "Landroidx/compose/ui/input/key/KeyEvent;", "event", "onPreKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onPreKeyEvent", "onKeyEvent-ZmokQxo", "onKeyEvent", "onObservedReadsChanged", "startInputSession", "disposeInputSession", "startOrDisposeInputSessionOnWindowFocusChange", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "requireKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "getTextFieldState", "()Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "setTextFieldState", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;)V", "Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "getTextLayoutState", "()Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "setTextLayoutState", "(Landroidx/compose/foundation/text2/input/internal/TextLayoutState;)V", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "getTextFieldSelectionState", "()Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "setTextFieldSelectionState", "(Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;)V", "Landroidx/compose/foundation/text2/input/InputTransformation;", "getFilter", "()Landroidx/compose/foundation/text2/input/InputTransformation;", "setFilter", "(Landroidx/compose/foundation/text2/input/InputTransformation;)V", "Z", "getEnabled", "()Z", "setEnabled", "(Z)V", "getReadOnly", "setReadOnly", "Landroidx/compose/foundation/text/KeyboardActions;", "getKeyboardActions", "()Landroidx/compose/foundation/text/KeyboardActions;", "setKeyboardActions", "(Landroidx/compose/foundation/text/KeyboardActions;)V", "getSingleLine", "setSingleLine", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "<set-?>", "Landroidx/compose/foundation/text/KeyboardOptions;", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "isElementFocused", "Landroidx/compose/ui/platform/WindowInfo;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "Landroidx/compose/foundation/text2/input/internal/TextFieldKeyEventHandler;", "textFieldKeyEventHandler", "Landroidx/compose/foundation/text2/input/internal/TextFieldKeyEventHandler;", "androidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1", "keyboardActionScope", "Landroidx/compose/foundation/text2/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1;", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed", "Lr7/l;", "Lka/v1;", "inputSessionJob", "Lka/v1;", "getShouldMergeDescendantSemantics", "shouldMergeDescendantSemantics", "isFocused", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDecoratorModifierNode extends DelegatingNode implements PlatformTextInputModifierNode, SemanticsModifierNode, FocusRequesterModifierNode, FocusEventModifierNode, GlobalPositionAwareModifierNode, PointerInputModifierNode, KeyInputModifierNode, CompositionLocalConsumerModifierNode, ObserverModifierNode {
    public static final int $stable = 8;
    private boolean enabled;
    private InputTransformation filter;
    private v1 inputSessionJob;
    private boolean isElementFocused;
    private final TextFieldDecoratorModifierNode$keyboardActionScope$1 keyboardActionScope;
    private KeyboardActions keyboardActions;
    private KeyboardOptions keyboardOptions;
    private final l<ImeAction, t0> onImeActionPerformed;
    private final SuspendingPointerInputModifierNode pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new TextFieldDecoratorModifierNode$pointerInputNode$1(this, null)));
    private boolean readOnly;
    private boolean singleLine;
    private final TextFieldKeyEventHandler textFieldKeyEventHandler;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private WindowInfo windowInfo;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "Landroidx/compose/ui/text/TextLayoutResult;", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<TextLayoutResult>, Boolean> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(List<TextLayoutResult> list) {
            TextLayoutResult layoutResult = TextFieldDecoratorModifierNode.this.getTextLayoutState().getLayoutResult();
            return Boolean.valueOf(layoutResult != null ? list.add(layoutResult) : false);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$10, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements r7.a<Boolean> {
        public AnonymousClass10() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().paste();
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "newText", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<AnnotatedString, Boolean> {
        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            if (TextFieldDecoratorModifierNode.this.getReadOnly() || !TextFieldDecoratorModifierNode.this.getEnabled()) {
                return Boolean.FALSE;
            }
            TextFieldDecoratorModifierNode.this.getTextFieldState().replaceAll(annotatedString);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", TtmlNode.START, "", TtmlNode.END, "relativeToOriginal", "invoke", "(IIZ)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements q<Integer, Integer, Boolean, Boolean> {
        public AnonymousClass3() {
            super(3);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), ((Boolean) obj3).booleanValue());
        }

        public final Boolean invoke(int i10, int i11, boolean z) {
            TextFieldCharSequence untransformedText = z ? TextFieldDecoratorModifierNode.this.getTextFieldState().getUntransformedText() : TextFieldDecoratorModifierNode.this.getTextFieldState().getText();
            long selectionInChars = untransformedText.getSelectionInChars();
            if (!TextFieldDecoratorModifierNode.this.getEnabled() || Math.min(i10, i11) < 0 || Math.max(i10, i11) > untransformedText.length()) {
                return Boolean.FALSE;
            }
            if (i10 == TextRange.m5192getStartimpl(selectionInChars) && i11 == TextRange.m5187getEndimpl(selectionInChars)) {
                return Boolean.TRUE;
            }
            long jTextRange = TextRangeKt.TextRange(i10, i11);
            if (z || i10 == i11) {
                TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.None);
            } else {
                TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
            }
            if (z) {
                TextFieldDecoratorModifierNode.this.getTextFieldState().m1141selectUntransformedCharsIn5zctL8(jTextRange);
            } else {
                TextFieldDecoratorModifierNode.this.getTextFieldState().m1140selectCharsIn5zctL8(jTextRange);
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "newText", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<AnnotatedString, Boolean> {
        public AnonymousClass4() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            if (TextFieldDecoratorModifierNode.this.getReadOnly() || !TextFieldDecoratorModifierNode.this.getEnabled()) {
                return Boolean.FALSE;
            }
            TransformedTextFieldState.replaceSelectedText$default(TextFieldDecoratorModifierNode.this.getTextFieldState(), annotatedString, true, null, 4, null);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements r7.a<Boolean> {
        public AnonymousClass5() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldDecoratorModifierNode.this.onImeActionPerformed.invoke(ImeAction.m5352boximpl(TextFieldDecoratorModifierNode.this.getKeyboardOptions().getImeAction()));
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements r7.a<Boolean> {
        public AnonymousClass6() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            if (!TextFieldDecoratorModifierNode.this.isFocused()) {
                FocusRequesterModifierNodeKt.requestFocus(TextFieldDecoratorModifierNode.this);
            } else if (!TextFieldDecoratorModifierNode.this.getReadOnly()) {
                TextFieldDecoratorModifierNode.this.requireKeyboardController().show();
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass7 extends r implements r7.a<Boolean> {
        public AnonymousClass7() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            if (!TextFieldDecoratorModifierNode.this.isFocused()) {
                FocusRequesterModifierNodeKt.requestFocus(TextFieldDecoratorModifierNode.this);
            }
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$8, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements r7.a<Boolean> {
        public AnonymousClass8() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldSelectionState.copy$default(TextFieldDecoratorModifierNode.this.getTextFieldSelectionState(), false, 1, null);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$applySemantics$9, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements r7.a<Boolean> {
        public AnonymousClass9() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().cut();
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$onObservedReadsChanged$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04181 extends r implements r7.a<t0> {
        public C04181() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1111invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1111invoke() {
            TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
            textFieldDecoratorModifierNode.windowInfo = (WindowInfo) CompositionLocalConsumerModifierNodeKt.currentValueOf(textFieldDecoratorModifierNode, CompositionLocalsKt.getLocalWindowInfo());
            TextFieldDecoratorModifierNode.this.startOrDisposeInputSessionOnWindowFocusChange();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1", f = "TextFieldDecoratorModifier.kt", l = {479}, m = "invokeSuspend")
    public static final class C04191 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1", f = "TextFieldDecoratorModifier.kt", l = {485}, m = "invokeSuspend")
        public static final class C00681 extends j implements p<PlatformTextInputSessionScope, d<?>, Object> {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ TextFieldDecoratorModifierNode this$0;

            /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text2.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$1", f = "TextFieldDecoratorModifier.kt", l = {482}, m = "invokeSuspend")
            public static final class C00691 extends j implements p<k0, d<? super t0>, Object> {
                int label;
                final /* synthetic */ TextFieldDecoratorModifierNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00691(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, d<? super C00691> dVar) {
                    super(2, dVar);
                    this.this$0 = textFieldDecoratorModifierNode;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C00691(this.this$0, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        TextFieldSelectionState textFieldSelectionState = this.this$0.getTextFieldSelectionState();
                        this.label = 1;
                        Object objObserveChanges = textFieldSelectionState.observeChanges(this);
                        e7.a aVar = e7.a.f15033i;
                        if (objObserveChanges == aVar) {
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
                    return ((C00691) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00681(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, d<? super C00681> dVar) {
                super(2, dVar);
                this.this$0 = textFieldDecoratorModifierNode;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00681 c00681 = new C00681(this.this$0, dVar);
                c00681.L$0 = obj;
                return c00681;
            }

            @Override // r7.p
            public final Object invoke(PlatformTextInputSessionScope platformTextInputSessionScope, d<?> dVar) {
                return ((C00681) create(platformTextInputSessionScope, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PlatformTextInputSessionScope platformTextInputSessionScope = (PlatformTextInputSessionScope) this.L$0;
                    m0.p(platformTextInputSessionScope, null, 4, new C00691(this.this$0, null), 1);
                    TransformedTextFieldState textFieldState = this.this$0.getTextFieldState();
                    ImeOptions imeOptions$foundation_release = this.this$0.getKeyboardOptions().toImeOptions$foundation_release(this.this$0.getSingleLine());
                    l lVar = this.this$0.onImeActionPerformed;
                    this.label = 1;
                    Object objPlatformSpecificTextInputSession = AndroidTextInputSession_androidKt.platformSpecificTextInputSession(platformTextInputSessionScope, textFieldState, imeOptions$foundation_release, lVar, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objPlatformSpecificTextInputSession == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                throw new KotlinNothingValueException();
            }
        }

        public C04191(d<? super C04191> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return TextFieldDecoratorModifierNode.this.new C04191(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = TextFieldDecoratorModifierNode.this;
                C00681 c00681 = new C00681(textFieldDecoratorModifierNode, null);
                this.label = 1;
                Object objEstablishTextInputSession = PlatformTextInputModifierNodeKt.establishTextInputSession(textFieldDecoratorModifierNode, c00681, this);
                e7.a aVar = e7.a.f15033i;
                if (objEstablishTextInputSession == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C04191) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public TextFieldDecoratorModifierNode(TransformedTextFieldState transformedTextFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation inputTransformation, boolean z, boolean z5, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z10) {
        this.textFieldState = transformedTextFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = inputTransformation;
        this.enabled = z;
        this.readOnly = z5;
        this.keyboardActions = keyboardActions;
        this.singleLine = z10;
        InputTransformation inputTransformation2 = this.filter;
        this.keyboardOptions = TextFieldDecoratorModifierKt.withDefaultsFrom(keyboardOptions, inputTransformation2 != null ? inputTransformation2.getKeyboardOptions() : null);
        this.textFieldKeyEventHandler = TextFieldKeyEventHandler_androidKt.createTextFieldKeyEventHandler();
        this.keyboardActionScope = new TextFieldDecoratorModifierNode$keyboardActionScope$1(this);
        this.onImeActionPerformed = new TextFieldDecoratorModifierNode$onImeActionPerformed$1(this);
    }

    private final void disposeInputSession() {
        v1 v1Var = this.inputSessionJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.inputSessionJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFocused() {
        WindowInfo windowInfo;
        return this.isElementFocused && (windowInfo = this.windowInfo) != null && windowInfo.isWindowFocused();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SoftwareKeyboardController requireKeyboardController() {
        SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalSoftwareKeyboardController());
        if (softwareKeyboardController != null) {
            return softwareKeyboardController;
        }
        throw new IllegalStateException("No software keyboard controller");
    }

    private final void startInputSession() {
        this.inputSessionJob = m0.p(getCoroutineScope(), null, 0, new C04191(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startOrDisposeInputSessionOnWindowFocusChange() {
        WindowInfo windowInfo = this.windowInfo;
        if (windowInfo == null) {
            return;
        }
        if (windowInfo != null && windowInfo.isWindowFocused() && this.isElementFocused) {
            startInputSession();
        } else {
            disposeInputSession();
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        TextFieldCharSequence untransformedText = this.textFieldState.getUntransformedText();
        long selectionInChars = untransformedText.getSelectionInChars();
        SemanticsPropertiesKt.setEditableText(semanticsPropertyReceiver, new AnnotatedString(untransformedText.toString(), null, null, 6, null));
        SemanticsPropertiesKt.m5040setTextSelectionRangeFDrldGo(semanticsPropertyReceiver, selectionInChars);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, new AnonymousClass1(), 1, null);
        if (!this.enabled) {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        SemanticsPropertiesKt.setText$default(semanticsPropertyReceiver, null, new AnonymousClass2(), 1, null);
        SemanticsPropertiesKt.setSelection$default(semanticsPropertyReceiver, null, new AnonymousClass3(), 1, null);
        SemanticsPropertiesKt.insertTextAtCursor$default(semanticsPropertyReceiver, null, new AnonymousClass4(), 1, null);
        SemanticsPropertiesKt.m5036onImeAction9UiTYpY$default(semanticsPropertyReceiver, this.keyboardOptions.getImeAction(), null, new AnonymousClass5(), 2, null);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new AnonymousClass6(), 1, null);
        SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, null, new AnonymousClass7(), 1, null);
        if (!TextRange.m5186getCollapsedimpl(selectionInChars)) {
            SemanticsPropertiesKt.copyText$default(semanticsPropertyReceiver, null, new AnonymousClass8(), 1, null);
            if (this.enabled && !this.readOnly) {
                SemanticsPropertiesKt.cutText$default(semanticsPropertyReceiver, null, new AnonymousClass9(), 1, null);
            }
        }
        if (!this.enabled || this.readOnly) {
            return;
        }
        SemanticsPropertiesKt.pasteText$default(semanticsPropertyReceiver, null, new AnonymousClass10(), 1, null);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final InputTransformation getFilter() {
        return this.filter;
    }

    public final KeyboardActions getKeyboardActions() {
        return this.keyboardActions;
    }

    public final KeyboardOptions getKeyboardOptions() {
        return this.keyboardOptions;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: getShouldClearDescendantSemantics */
    public final /* synthetic */ boolean getIsClearingSemantics() {
        return h.a(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    public final boolean getSingleLine() {
        return this.singleLine;
    }

    public final TextFieldSelectionState getTextFieldSelectionState() {
        return this.textFieldSelectionState;
    }

    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    public final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return f.a(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        onObservedReadsChanged();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        this.pointerInputNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onDensityChange() {
        f.b(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        disposeInputSession();
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        if (this.isElementFocused == focusState.isFocused()) {
            return;
        }
        this.isElementFocused = focusState.isFocused();
        this.textFieldSelectionState.setFocused(isFocused());
        if (!focusState.isFocused()) {
            disposeInputSession();
            this.textFieldState.collapseSelectionToMax();
        } else {
            if (!this.enabled || this.readOnly) {
                return;
            }
            startInputSession();
        }
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setDecoratorNodeCoordinates(coordinates);
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
    public boolean mo147onKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.m1114onKeyEvent6ptp14s(event, this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.enabled && !this.readOnly, this.singleLine, new TextFieldDecoratorModifierNode$onKeyEvent$1(this));
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new C04181());
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.pointerInputNode.mo148onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
    public boolean mo149onPreKeyEventZmokQxo(KeyEvent event) {
        return this.textFieldKeyEventHandler.mo1081onPreKeyEventMyFupTE(event, this.textFieldState, this.textFieldSelectionState, (FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager()), requireKeyboardController());
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onViewConfigurationChange() {
        f.c(this);
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final void setFilter(InputTransformation inputTransformation) {
        this.filter = inputTransformation;
    }

    public final void setKeyboardActions(KeyboardActions keyboardActions) {
        this.keyboardActions = keyboardActions;
    }

    public final void setReadOnly(boolean z) {
        this.readOnly = z;
    }

    public final void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    public final void setTextFieldSelectionState(TextFieldSelectionState textFieldSelectionState) {
        this.textFieldSelectionState = textFieldSelectionState;
    }

    public final void setTextFieldState(TransformedTextFieldState transformedTextFieldState) {
        this.textFieldState = transformedTextFieldState;
    }

    public final void setTextLayoutState(TextLayoutState textLayoutState) {
        this.textLayoutState = textLayoutState;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return f.d(this);
    }

    public final void updateNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine) {
        boolean z = this.enabled;
        boolean z5 = false;
        boolean z10 = z && !this.readOnly;
        if (enabled && !readOnly) {
            z5 = true;
        }
        TransformedTextFieldState transformedTextFieldState = this.textFieldState;
        KeyboardOptions keyboardOptions2 = this.keyboardOptions;
        TextFieldSelectionState textFieldSelectionState2 = this.textFieldSelectionState;
        InputTransformation inputTransformation = this.filter;
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = filter;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.keyboardOptions = TextFieldDecoratorModifierKt.withDefaultsFrom(keyboardOptions, filter != null ? filter.getKeyboardOptions() : null);
        this.keyboardActions = keyboardActions;
        this.singleLine = singleLine;
        if (z5 != z10 || !kotlin.jvm.internal.p.a(textFieldState, transformedTextFieldState) || !kotlin.jvm.internal.p.a(keyboardOptions, keyboardOptions2) || !kotlin.jvm.internal.p.a(filter, inputTransformation)) {
            if (z5 && isFocused()) {
                startInputSession();
            } else if (!z5) {
                disposeInputSession();
            }
        }
        if (z != enabled) {
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (kotlin.jvm.internal.p.a(textFieldSelectionState, textFieldSelectionState2)) {
            return;
        }
        this.pointerInputNode.resetPointerInputHandler();
    }
}
