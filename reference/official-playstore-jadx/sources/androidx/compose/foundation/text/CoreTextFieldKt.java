package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.relocation.BringIntoViewRequesterKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.exoplayer.RendererCapabilities;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.util.List;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.r;
import na.k;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.j0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\u001að\u0001\u0010\"\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00112\b\b\u0002\u0010\u001b\u001a\u00020\u001123\b\u0002\u0010!\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u001dH\u0001¢\u0006\u0004\b\"\u0010#\u001a2\u0010'\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010%\u001a\u00020$2\u0011\u0010&\u001a\r\u0012\u0004\u0012\u00020\u00030\u001c¢\u0006\u0002\b\u001dH\u0003¢\u0006\u0004\b'\u0010(\u001a#\u0010+\u001a\u00020\u0005*\u00020\u00052\u0006\u0010*\u001a\u00020)2\u0006\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\b+\u0010,\u001a'\u00100\u001a\u00020\u00032\u0006\u0010*\u001a\u00020)2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020\u0011H\u0002¢\u0006\u0004\b0\u00101\u001a7\u00106\u001a\u00020\u00032\u0006\u00103\u001a\u0002022\u0006\u0010*\u001a\u00020)2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00105\u001a\u000204H\u0002¢\u0006\u0004\b6\u00107\u001a\u0017\u00108\u001a\u00020\u00032\u0006\u0010*\u001a\u00020)H\u0002¢\u0006\u0004\b8\u00109\u001a4\u0010>\u001a\u00020\u0003*\u00020:2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u000b2\u0006\u00105\u001a\u000204H\u0080@¢\u0006\u0004\b>\u0010?\u001a\u001f\u0010A\u001a\u00020\u00032\u0006\u0010%\u001a\u00020$2\u0006\u0010@\u001a\u00020\u0011H\u0003¢\u0006\u0004\bA\u0010B\u001a\u0017\u0010C\u001a\u00020\u00032\u0006\u0010%\u001a\u00020$H\u0001¢\u0006\u0004\bC\u0010D\u001a'\u0010E\u001a\u00020\u00032\u0006\u0010*\u001a\u00020)2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00105\u001a\u000204H\u0002¢\u0006\u0004\bE\u0010F\u001a\u0017\u0010I\u001a\u00020\u00112\u0006\u0010H\u001a\u00020GH\u0000¢\u0006\u0004\bI\u0010J\"\u0014\u0010K\u001a\u00020\u00118\u0000X\u0080T¢\u0006\u0006\n\u0004\bK\u0010L¨\u0006N²\u0006\f\u0010M\u001a\u00020\u00118\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Landroidx/compose/ui/text/TextLayoutResult;", "onTextLayout", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "", "softWrap", "", "maxLines", "minLines", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "enabled", "readOnly", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lx6/y;", ContentDisposition.Parameters.Name, "innerTextField", "decorationBox", "CoreTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/input/VisualTransformation;Lr7/l;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;ZIILandroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/foundation/text/KeyboardActions;ZZLr7/q;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "manager", "content", "CoreTextFieldRootBox", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/text/TextFieldState;", "state", "previewKeyEventToDeselectOnBack", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "allowKeyboard", "tapToFocus", "(Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/ui/focus/FocusRequester;Z)V", "Landroidx/compose/ui/text/input/TextInputService;", "textInputService", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "startInputSession", "(Landroidx/compose/ui/text/input/TextInputService;Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/ui/text/input/OffsetMapping;)V", "endInputSession", "(Landroidx/compose/foundation/text/TextFieldState;)V", "Landroidx/compose/foundation/relocation/BringIntoViewRequester;", "Landroidx/compose/foundation/text/TextDelegate;", "textDelegate", "textLayoutResult", "bringSelectionEndIntoView", "(Landroidx/compose/foundation/relocation/BringIntoViewRequester;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/input/OffsetMapping;Ld7/d;)Ljava/lang/Object;", "show", "SelectionToolbarAndHandles", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;ZLandroidx/compose/runtime/Composer;I)V", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "notifyFocusedRect", "(Landroidx/compose/foundation/text/TextFieldState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/OffsetMapping;)V", "Landroidx/compose/ui/platform/WindowInfo;", "windowInfo", "isWindowFocusedBehindFlag", "(Landroidx/compose/ui/platform/WindowInfo;)Z", "USE_WINDOW_FOCUS_ENABLED", "Z", "writeable", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoreTextFieldKt {
    public static final boolean USE_WINDOW_FOCUS_ENABLED = false;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<TextLayoutResult, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(TextLayoutResult textLayoutResult) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((TextLayoutResult) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2", f = "CoreTextField.kt", l = {348}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextInputService $textInputService;
        final /* synthetic */ State<Boolean> $writeable$delegate;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements a<Boolean> {
            final /* synthetic */ State<Boolean> $writeable$delegate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(State<Boolean> state) {
                super(0);
                this.$writeable$delegate = state;
            }

            @Override // r7.a
            public final Boolean invoke() {
                return Boolean.valueOf(CoreTextFieldKt.CoreTextField$lambda$8(this.$writeable$delegate));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TextFieldState textFieldState, State<Boolean> state, TextInputService textInputService, TextFieldSelectionManager textFieldSelectionManager, ImeOptions imeOptions, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$state = textFieldState;
            this.$writeable$delegate = state;
            this.$textInputService = textInputService;
            this.$manager = textFieldSelectionManager;
            this.$imeOptions = imeOptions;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$state, this.$writeable$delegate, this.$textInputService, this.$manager, this.$imeOptions, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new AnonymousClass1(this.$writeable$delegate));
                    final TextFieldState textFieldState = this.$state;
                    final TextInputService textInputService = this.$textInputService;
                    final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                    final ImeOptions imeOptions = this.$imeOptions;
                    k kVar = new k() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.2.2
                        @Override // na.k
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                            return emit(((Boolean) obj2).booleanValue(), (d<? super t0>) dVar);
                        }

                        public final Object emit(boolean z, d<? super t0> dVar) {
                            if (z && textFieldState.getHasFocus()) {
                                CoreTextFieldKt.startInputSession(textInputService, textFieldState, textFieldSelectionManager.getValue$foundation_release(), imeOptions, textFieldSelectionManager.getOffsetMapping());
                            } else {
                                CoreTextFieldKt.endInputSession(textFieldState);
                            }
                            return t0.f22605a;
                        }
                    };
                    this.label = 1;
                    Object objCollect = jVarSnapshotFlow.collect(kVar, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objCollect == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                CoreTextFieldKt.endInputSession(this.$state);
                return t0.f22605a;
            } catch (Throwable th) {
                CoreTextFieldKt.endInputSession(this.$state);
                throw th;
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(TextFieldSelectionManager textFieldSelectionManager) {
            super(1);
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            final TextFieldSelectionManager textFieldSelectionManager = this.$manager;
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$3$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextInputService $textInputService;
        final /* synthetic */ TextFieldValue $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(TextInputService textInputService, TextFieldState textFieldState, TextFieldValue textFieldValue, ImeOptions imeOptions) {
            super(1);
            this.$textInputService = textInputService;
            this.$state = textFieldState;
            this.$value = textFieldValue;
            this.$imeOptions = imeOptions;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            if (this.$textInputService != null && this.$state.getHasFocus()) {
                TextFieldState textFieldState = this.$state;
                textFieldState.setInputSession(TextFieldDelegate.INSTANCE.restartInput$foundation_release(this.$textInputService, this.$value, textFieldState.getProcessor(), this.$imeOptions, this.$state.getOnValueChange(), this.$state.getOnImeActionPerformed()));
            }
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$4$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
        final /* synthetic */ Modifier $cursorModifier;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ Density $density;
        final /* synthetic */ Modifier $drawModifier;
        final /* synthetic */ Modifier $magnifierModifier;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ OffsetMapping $offsetMapping;
        final /* synthetic */ Modifier $onPositionedModifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
        final /* synthetic */ boolean $showHandleAndMagnifier;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ BringIntoViewRequester $bringIntoViewRequester;
            final /* synthetic */ Modifier $cursorModifier;
            final /* synthetic */ Density $density;
            final /* synthetic */ Modifier $drawModifier;
            final /* synthetic */ Modifier $magnifierModifier;
            final /* synthetic */ TextFieldSelectionManager $manager;
            final /* synthetic */ int $maxLines;
            final /* synthetic */ int $minLines;
            final /* synthetic */ OffsetMapping $offsetMapping;
            final /* synthetic */ Modifier $onPositionedModifier;
            final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
            final /* synthetic */ boolean $readOnly;
            final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;
            final /* synthetic */ boolean $showHandleAndMagnifier;
            final /* synthetic */ TextFieldState $state;
            final /* synthetic */ TextStyle $textStyle;
            final /* synthetic */ TextFieldValue $value;
            final /* synthetic */ VisualTransformation $visualTransformation;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$5$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00411 extends r implements p<Composer, Integer, t0> {
                final /* synthetic */ Density $density;
                final /* synthetic */ TextFieldSelectionManager $manager;
                final /* synthetic */ int $maxLines;
                final /* synthetic */ OffsetMapping $offsetMapping;
                final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
                final /* synthetic */ boolean $readOnly;
                final /* synthetic */ boolean $showHandleAndMagnifier;
                final /* synthetic */ TextFieldState $state;
                final /* synthetic */ TextFieldValue $value;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00411(TextFieldSelectionManager textFieldSelectionManager, TextFieldState textFieldState, boolean z, boolean z5, l<? super TextLayoutResult, t0> lVar, TextFieldValue textFieldValue, OffsetMapping offsetMapping, Density density, int i10) {
                    super(2);
                    this.$manager = textFieldSelectionManager;
                    this.$state = textFieldState;
                    this.$showHandleAndMagnifier = z;
                    this.$readOnly = z5;
                    this.$onTextLayout = lVar;
                    this.$value = textFieldValue;
                    this.$offsetMapping = offsetMapping;
                    this.$density = density;
                    this.$maxLines = i10;
                }

                @Override // r7.p
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return t0.f22605a;
                }

                public final void invoke(Composer composer, int i10) {
                    if ((i10 & 11) == 2 && composer.getSkipping()) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-363167407, i10, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous>.<anonymous> (CoreTextField.kt:668)");
                    }
                    final TextFieldState textFieldState = this.$state;
                    final l<TextLayoutResult, t0> lVar = this.$onTextLayout;
                    final TextFieldValue textFieldValue = this.$value;
                    final OffsetMapping offsetMapping = this.$offsetMapping;
                    final Density density = this.$density;
                    final int i11 = this.$maxLines;
                    MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField.5.1.1.2
                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                            return g.a(this, intrinsicMeasureScope, list, i12);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i12) {
                            textFieldState.getTextDelegate().layoutIntrinsics(intrinsicMeasureScope.getLayoutDirection());
                            return textFieldState.getTextDelegate().getMaxIntrinsicWidth();
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        /* JADX INFO: renamed from: measure-3p2s80s */
                        public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                            Snapshot.Companion companion = Snapshot.INSTANCE;
                            TextFieldState textFieldState2 = textFieldState;
                            Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
                            try {
                                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                                try {
                                    TextLayoutResultProxy layoutResult = textFieldState2.getLayoutResult();
                                    TextLayoutResult value = layoutResult != null ? layoutResult.getValue() : null;
                                    snapshotCreateNonObservableSnapshot.dispose();
                                    j0 j0VarM882layout_EkL_Y$foundation_release = TextFieldDelegate.INSTANCE.m882layout_EkL_Y$foundation_release(textFieldState.getTextDelegate(), j10, measureScope.getLayoutDirection(), value);
                                    int iIntValue = ((Number) j0VarM882layout_EkL_Y$foundation_release.f22587i).intValue();
                                    int iIntValue2 = ((Number) j0VarM882layout_EkL_Y$foundation_release.f22588l).intValue();
                                    TextLayoutResult textLayoutResult = (TextLayoutResult) j0VarM882layout_EkL_Y$foundation_release.f22589m;
                                    if (!kotlin.jvm.internal.p.a(value, textLayoutResult)) {
                                        textFieldState.setLayoutResult(new TextLayoutResultProxy(textLayoutResult));
                                        lVar.invoke(textLayoutResult);
                                        CoreTextFieldKt.notifyFocusedRect(textFieldState, textFieldValue, offsetMapping);
                                    }
                                    textFieldState.m903setMinHeightForSingleLineField0680j_4(density.mo282toDpu2uoSUM(i11 == 1 ? TextDelegateKt.ceilToIntPx(textLayoutResult.getLineBottom(0)) : 0));
                                    return measureScope.layout(iIntValue, iIntValue2, h0.t0(new x(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(t7.a.M(textLayoutResult.getFirstBaseline()))), new x(AlignmentLineKt.getLastBaseline(), Integer.valueOf(t7.a.M(textLayoutResult.getLastBaseline())))), CoreTextFieldKt$CoreTextField$5$1$1$2$measure$2.INSTANCE);
                                } finally {
                                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                                }
                            } catch (Throwable th) {
                                snapshotCreateNonObservableSnapshot.dispose();
                                throw th;
                            }
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                            return g.c(this, intrinsicMeasureScope, list, i12);
                        }

                        @Override // androidx.compose.ui.layout.MeasurePolicy
                        public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                            return g.d(this, intrinsicMeasureScope, list, i12);
                        }
                    };
                    composer.startReplaceableGroup(-1323940314);
                    Modifier.Companion companion = Modifier.INSTANCE;
                    boolean z = false;
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                    a<ComposeUiNode> constructor = companion2.getConstructor();
                    q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
                    if (composer.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                        composer.createNode(constructor);
                    } else {
                        composer.useNode();
                    }
                    Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
                    Updater.m2998setimpl(composerM2991constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
                    Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                    p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                    if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
                    }
                    qVarModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                    composer.startReplaceableGroup(2058660585);
                    composer.endReplaceableGroup();
                    composer.endNode();
                    composer.endReplaceableGroup();
                    TextFieldSelectionManager textFieldSelectionManager = this.$manager;
                    if (this.$state.getHandleState() != HandleState.None && this.$state.getLayoutCoordinates() != null && this.$state.getLayoutCoordinates().isAttached() && this.$showHandleAndMagnifier) {
                        z = true;
                    }
                    CoreTextFieldKt.SelectionToolbarAndHandles(textFieldSelectionManager, z, composer, 8);
                    if (this.$state.getHandleState() == HandleState.Cursor && !this.$readOnly && this.$showHandleAndMagnifier) {
                        CoreTextFieldKt.TextFieldCursorHandle(this.$manager, composer, 8);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(TextFieldState textFieldState, TextStyle textStyle, int i10, int i11, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z5, l<? super TextLayoutResult, t0> lVar, OffsetMapping offsetMapping, Density density) {
                super(2);
                this.$state = textFieldState;
                this.$textStyle = textStyle;
                this.$minLines = i10;
                this.$maxLines = i11;
                this.$scrollerPosition = textFieldScrollerPosition;
                this.$value = textFieldValue;
                this.$visualTransformation = visualTransformation;
                this.$cursorModifier = modifier;
                this.$drawModifier = modifier2;
                this.$onPositionedModifier = modifier3;
                this.$magnifierModifier = modifier4;
                this.$bringIntoViewRequester = bringIntoViewRequester;
                this.$manager = textFieldSelectionManager;
                this.$showHandleAndMagnifier = z;
                this.$readOnly = z5;
                this.$onTextLayout = lVar;
                this.$offsetMapping = offsetMapping;
                this.$density = density;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                if ((i10 & 11) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2032502107, i10, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous>.<anonymous> (CoreTextField.kt:645)");
                }
                SimpleLayoutKt.SimpleLayout(BringIntoViewRequesterKt.bringIntoViewRequester(TextFieldSizeKt.textFieldMinSize(TextFieldScrollKt.textFieldScroll(HeightInLinesModifierKt.heightInLines(SizeKt.m566heightInVpY3zN4$default(Modifier.INSTANCE, this.$state.m902getMinHeightForSingleLineFieldD9Ej5fM(), 0.0f, 2, null), this.$textStyle, this.$minLines, this.$maxLines), this.$scrollerPosition, this.$value, this.$visualTransformation, new CoreTextFieldKt$CoreTextField$5$1$coreTextFieldModifier$1(this.$state)).then(this.$cursorModifier).then(this.$drawModifier), this.$textStyle).then(this.$onPositionedModifier).then(this.$magnifierModifier), this.$bringIntoViewRequester), ComposableLambdaKt.composableLambda(composer, -363167407, true, new C00411(this.$manager, this.$state, this.$showHandleAndMagnifier, this.$readOnly, this.$onTextLayout, this.$value, this.$offsetMapping, this.$density, this.$maxLines)), composer, 48, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, TextFieldState textFieldState, TextStyle textStyle, int i10, int i11, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Modifier modifier, Modifier modifier2, Modifier modifier3, Modifier modifier4, BringIntoViewRequester bringIntoViewRequester, TextFieldSelectionManager textFieldSelectionManager, boolean z, boolean z5, l<? super TextLayoutResult, t0> lVar, OffsetMapping offsetMapping, Density density) {
            super(2);
            this.$decorationBox = qVar;
            this.$state = textFieldState;
            this.$textStyle = textStyle;
            this.$minLines = i10;
            this.$maxLines = i11;
            this.$scrollerPosition = textFieldScrollerPosition;
            this.$value = textFieldValue;
            this.$visualTransformation = visualTransformation;
            this.$cursorModifier = modifier;
            this.$drawModifier = modifier2;
            this.$onPositionedModifier = modifier3;
            this.$magnifierModifier = modifier4;
            this.$bringIntoViewRequester = bringIntoViewRequester;
            this.$manager = textFieldSelectionManager;
            this.$showHandleAndMagnifier = z;
            this.$readOnly = z5;
            this.$onTextLayout = lVar;
            this.$offsetMapping = offsetMapping;
            this.$density = density;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-374338080, i10, -1, "androidx.compose.foundation.text.CoreTextField.<anonymous> (CoreTextField.kt:642)");
            }
            this.$decorationBox.invoke(ComposableLambdaKt.composableLambda(composer, 2032502107, true, new AnonymousClass1(this.$state, this.$textStyle, this.$minLines, this.$maxLines, this.$scrollerPosition, this.$value, this.$visualTransformation, this.$cursorModifier, this.$drawModifier, this.$onPositionedModifier, this.$magnifierModifier, this.$bringIntoViewRequester, this.$manager, this.$showHandleAndMagnifier, this.$readOnly, this.$onTextLayout, this.$offsetMapping, this.$density)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ q<p<? super Composer, ? super Integer, t0>, Composer, Integer, t0> $decorationBox;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextLayoutResult, t0> $onTextLayout;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ boolean $softWrap;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, Modifier modifier, TextStyle textStyle, VisualTransformation visualTransformation, l<? super TextLayoutResult, t0> lVar2, MutableInteractionSource mutableInteractionSource, Brush brush, boolean z, int i10, int i11, ImeOptions imeOptions, KeyboardActions keyboardActions, boolean z5, boolean z10, q<? super p<? super Composer, ? super Integer, t0>, ? super Composer, ? super Integer, t0> qVar, int i12, int i13, int i14) {
            super(2);
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$textStyle = textStyle;
            this.$visualTransformation = visualTransformation;
            this.$onTextLayout = lVar2;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$softWrap = z;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$imeOptions = imeOptions;
            this.$keyboardActions = keyboardActions;
            this.$enabled = z5;
            this.$readOnly = z10;
            this.$decorationBox = qVar;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$default = i14;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CoreTextFieldKt.CoreTextField(this.$value, this.$onValueChange, this.$modifier, this.$textStyle, this.$visualTransformation, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$softWrap, this.$maxLines, this.$minLines, this.$imeOptions, this.$keyboardActions, this.$enabled, this.$readOnly, this.$decorationBox, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextFieldRootBox$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03782 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C03782(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$manager = textFieldSelectionManager;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CoreTextFieldKt.CoreTextFieldRootBox(this.$modifier, this.$manager, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$SelectionToolbarAndHandles$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03792 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ boolean $show;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03792(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i10) {
            super(2);
            this.$manager = textFieldSelectionManager;
            this.$show = z;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CoreTextFieldKt.SelectionToolbarAndHandles(this.$manager, this.$show, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1", f = "CoreTextField.kt", l = {1134}, m = "invokeSuspend")
    public static final class C03801 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ TextDragObserver $observer;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1", f = "CoreTextField.kt", l = {}, m = "invokeSuspend")
        public static final class C00421 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ TextFieldSelectionManager $manager;
            final /* synthetic */ TextDragObserver $observer;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1$1", f = "CoreTextField.kt", l = {1138}, m = "invokeSuspend")
            public static final class C00431 extends j implements p<k0, d<? super t0>, Object> {
                final /* synthetic */ PointerInputScope $$this$pointerInput;
                final /* synthetic */ TextDragObserver $observer;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00431(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, d<? super C00431> dVar) {
                    super(2, dVar);
                    this.$$this$pointerInput = pointerInputScope;
                    this.$observer = textDragObserver;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C00431(this.$$this$pointerInput, this.$observer, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        PointerInputScope pointerInputScope = this.$$this$pointerInput;
                        TextDragObserver textDragObserver = this.$observer;
                        this.label = 1;
                        Object objDetectDownAndDragGesturesWithObserver = LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver(pointerInputScope, textDragObserver, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objDetectDownAndDragGesturesWithObserver == aVar) {
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
                    return ((C00431) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1$2", f = "CoreTextField.kt", l = {1141}, m = "invokeSuspend")
            public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
                final /* synthetic */ PointerInputScope $$this$pointerInput;
                final /* synthetic */ TextFieldSelectionManager $manager;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$1$1$2$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                public static final class C00441 extends r implements l<Offset, t0> {
                    final /* synthetic */ TextFieldSelectionManager $manager;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00441(TextFieldSelectionManager textFieldSelectionManager) {
                        super(1);
                        this.$manager = textFieldSelectionManager;
                    }

                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        m823invokek4lQ0M(((Offset) obj).getPackedValue());
                        return t0.f22605a;
                    }

                    /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                    public final void m823invokek4lQ0M(long j10) {
                        this.$manager.showSelectionToolbar$foundation_release();
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(PointerInputScope pointerInputScope, TextFieldSelectionManager textFieldSelectionManager, d<? super AnonymousClass2> dVar) {
                    super(2, dVar);
                    this.$$this$pointerInput = pointerInputScope;
                    this.$manager = textFieldSelectionManager;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new AnonymousClass2(this.$$this$pointerInput, this.$manager, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        PointerInputScope pointerInputScope = this.$$this$pointerInput;
                        C00441 c00441 = new C00441(this.$manager);
                        this.label = 1;
                        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, c00441, this, 7, null);
                        e7.a aVar = e7.a.f15033i;
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
                    return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00421(PointerInputScope pointerInputScope, TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, d<? super C00421> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$observer = textDragObserver;
                this.$manager = textFieldSelectionManager;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00421 c00421 = new C00421(this.$$this$pointerInput, this.$observer, this.$manager, dVar);
                c00421.L$0 = obj;
                return c00421;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                k0 k0Var = (k0) this.L$0;
                m0.p(k0Var, null, 4, new C00431(this.$$this$pointerInput, this.$observer, null), 1);
                m0.p(k0Var, null, 4, new AnonymousClass2(this.$$this$pointerInput, this.$manager, null), 1);
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00421) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03801(TextDragObserver textDragObserver, TextFieldSelectionManager textFieldSelectionManager, d<? super C03801> dVar) {
            super(2, dVar);
            this.$observer = textDragObserver;
            this.$manager = textFieldSelectionManager;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03801 c03801 = new C03801(this.$observer, this.$manager, dVar);
            c03801.L$0 = obj;
            return c03801;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C03801) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                C00421 c00421 = new C00421((PointerInputScope) this.L$0, this.$observer, this.$manager, null);
                this.label = 1;
                Object objC = l0.c(c00421, this);
                e7.a aVar = e7.a.f15033i;
                if (objC == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$TextFieldCursorHandle$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03813 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03813(TextFieldSelectionManager textFieldSelectionManager, int i10) {
            super(2);
            this.$manager = textFieldSelectionManager;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            CoreTextFieldKt.TextFieldCursorHandle(this.$manager, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$previewKeyEventToDeselectOnBack$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "invoke-ZmokQxo", "(Landroid/view/KeyEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03821 extends r implements l<KeyEvent, Boolean> {
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ TextFieldState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03821(TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager) {
            super(1);
            this.$state = textFieldState;
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m824invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
        }

        /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
        public final Boolean m824invokeZmokQxo(android.view.KeyEvent keyEvent) {
            boolean z;
            if (this.$state.getHandleState() == HandleState.Selection && KeyEventHelpers_androidKt.m826cancelsTextSelectionZmokQxo(keyEvent)) {
                z = true;
                TextFieldSelectionManager.m1029deselect_kEHs6E$foundation_release$default(this.$manager, null, 1, null);
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x06e1  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0713  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0777  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x077d  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x07c7  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x07ec  */
    /* JADX WARN: Removed duplicated region for block: B:326:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CoreTextField(androidx.compose.ui.text.input.TextFieldValue r43, r7.l<? super androidx.compose.ui.text.input.TextFieldValue, x6.t0> r44, androidx.compose.ui.Modifier r45, androidx.compose.ui.text.TextStyle r46, androidx.compose.ui.text.input.VisualTransformation r47, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, boolean r51, int r52, int r53, androidx.compose.ui.text.input.ImeOptions r54, androidx.compose.foundation.text.KeyboardActions r55, boolean r56, boolean r57, r7.q<? super r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r58, androidx.compose.runtime.Composer r59, int r60, int r61, int r62) {
        /*
            Method dump skipped, instruction units count: 2052
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CoreTextFieldKt.CoreTextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.input.VisualTransformation, r7.l, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, boolean, int, int, androidx.compose.ui.text.input.ImeOptions, androidx.compose.foundation.text.KeyboardActions, boolean, boolean, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean CoreTextField$lambda$8(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void CoreTextFieldRootBox(Modifier modifier, TextFieldSelectionManager textFieldSelectionManager, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-20551815);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-20551815, i10, -1, "androidx.compose.foundation.text.CoreTextFieldRootBox (CoreTextField.kt:746)");
        }
        composerStartRestartGroup.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, true, composerStartRestartGroup, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
        if (composerStartRestartGroup.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(constructor);
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        composerStartRestartGroup.startReplaceableGroup(-1985516685);
        pVar.invoke(composerStartRestartGroup, Integer.valueOf(((((i10 >> 3) & 112) | 8) >> 3) & 14));
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C03782(modifier, textFieldSelectionManager, pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SelectionToolbarAndHandles(TextFieldSelectionManager textFieldSelectionManager, boolean z, Composer composer, int i10) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        Composer composerStartRestartGroup = composer.startRestartGroup(626339208);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(626339208, i10, -1, "androidx.compose.foundation.text.SelectionToolbarAndHandles (CoreTextField.kt:1082)");
        }
        if (z) {
            TextFieldState state = textFieldSelectionManager.getState();
            TextLayoutResult textLayoutResult = null;
            if (state != null && (layoutResult = state.getLayoutResult()) != null && (value = layoutResult.getValue()) != null) {
                TextFieldState state2 = textFieldSelectionManager.getState();
                if (!(state2 != null ? state2.getIsLayoutResultStale() : true)) {
                    textLayoutResult = value;
                }
            }
            if (textLayoutResult != null) {
                if (!TextRange.m5186getCollapsedimpl(textFieldSelectionManager.getValue$foundation_release().getSelection())) {
                    int iOriginalToTransformed = textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m5192getStartimpl(textFieldSelectionManager.getValue$foundation_release().getSelection()));
                    int iOriginalToTransformed2 = textFieldSelectionManager.getOffsetMapping().originalToTransformed(TextRange.m5187getEndimpl(textFieldSelectionManager.getValue$foundation_release().getSelection()));
                    ResolvedTextDirection bidiRunDirection = textLayoutResult.getBidiRunDirection(iOriginalToTransformed);
                    ResolvedTextDirection bidiRunDirection2 = textLayoutResult.getBidiRunDirection(Math.max(iOriginalToTransformed2 - 1, 0));
                    composerStartRestartGroup.startReplaceableGroup(-498386756);
                    TextFieldState state3 = textFieldSelectionManager.getState();
                    if (state3 != null && state3.getShowSelectionHandleStart()) {
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(true, bidiRunDirection, textFieldSelectionManager, composerStartRestartGroup, 518);
                    }
                    composerStartRestartGroup.endReplaceableGroup();
                    TextFieldState state4 = textFieldSelectionManager.getState();
                    if (state4 != null && state4.getShowSelectionHandleEnd()) {
                        TextFieldSelectionManagerKt.TextFieldSelectionHandle(false, bidiRunDirection2, textFieldSelectionManager, composerStartRestartGroup, 518);
                    }
                }
                TextFieldState state5 = textFieldSelectionManager.getState();
                if (state5 != null) {
                    if (textFieldSelectionManager.isTextChanged$foundation_release()) {
                        state5.setShowFloatingToolbar(false);
                    }
                    if (state5.getHasFocus()) {
                        if (state5.getShowFloatingToolbar()) {
                            textFieldSelectionManager.showSelectionToolbar$foundation_release();
                        } else {
                            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
                        }
                    }
                }
            }
        } else {
            textFieldSelectionManager.hideSelectionToolbar$foundation_release();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C03792(textFieldSelectionManager, z, i10));
        }
    }

    public static final void TextFieldCursorHandle(TextFieldSelectionManager textFieldSelectionManager, Composer composer, int i10) {
        AnnotatedString transformedText$foundation_release;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1436003720);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1436003720, i10, -1, "androidx.compose.foundation.text.TextFieldCursorHandle (CoreTextField.kt:1125)");
        }
        TextFieldState state = textFieldSelectionManager.getState();
        if (state != null && state.getShowCursorHandle() && (transformedText$foundation_release = textFieldSelectionManager.getTransformedText$foundation_release()) != null && transformedText$foundation_release.length() > 0) {
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            boolean zChanged = composerStartRestartGroup.changed(textFieldSelectionManager);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = textFieldSelectionManager.cursorDragObserver$foundation_release();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            TextDragObserver textDragObserver = (TextDragObserver) objRememberedValue;
            long jM1035getCursorPositiontuRUvjQ$foundation_release = textFieldSelectionManager.m1035getCursorPositiontuRUvjQ$foundation_release((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity()));
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textDragObserver, new C03801(textDragObserver, textFieldSelectionManager, null));
            composerStartRestartGroup.startReplaceableGroup(294220498);
            boolean zChanged2 = composerStartRestartGroup.changed(jM1035getCursorPositiontuRUvjQ$foundation_release);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new CoreTextFieldKt$TextFieldCursorHandle$2$1(jM1035getCursorPositiontuRUvjQ$foundation_release);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AndroidCursorHandle_androidKt.m801CursorHandleULxng0E(jM1035getCursorPositiontuRUvjQ$foundation_release, SemanticsModifierKt.semantics$default(modifierPointerInput, false, (l) objRememberedValue2, 1, null), null, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C03813(textFieldSelectionManager, i10));
        }
    }

    public static final Object bringSelectionEndIntoView(BringIntoViewRequester bringIntoViewRequester, TextFieldValue textFieldValue, TextDelegate textDelegate, TextLayoutResult textLayoutResult, OffsetMapping offsetMapping, d<? super t0> dVar) {
        int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m5189getMaximpl(textFieldValue.getSelection()));
        Object objBringIntoView = bringIntoViewRequester.bringIntoView(iOriginalToTransformed < textLayoutResult.getLayoutInput().getText().length() ? textLayoutResult.getBoundingBox(iOriginalToTransformed) : iOriginalToTransformed != 0 ? textLayoutResult.getBoundingBox(iOriginalToTransformed - 1) : new Rect(0.0f, 0.0f, 1.0f, IntSize.m5843getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null))), dVar);
        return objBringIntoView == e7.a.f15033i ? objBringIntoView : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void endInputSession(TextFieldState textFieldState) {
        TextInputSession inputSession = textFieldState.getInputSession();
        if (inputSession != null) {
            TextFieldDelegate.INSTANCE.onBlur$foundation_release(inputSession, textFieldState.getProcessor(), textFieldState.getOnValueChange());
        }
        textFieldState.setInputSession(null);
    }

    public static final boolean isWindowFocusedBehindFlag(WindowInfo windowInfo) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyFocusedRect(TextFieldState textFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                TextLayoutResultProxy layoutResult = textFieldState.getLayoutResult();
                if (layoutResult == null) {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                    return;
                }
                TextInputSession inputSession = textFieldState.getInputSession();
                if (inputSession == null) {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                    return;
                }
                LayoutCoordinates layoutCoordinates = textFieldState.getLayoutCoordinates();
                if (layoutCoordinates == null) {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                } else {
                    TextFieldDelegate.INSTANCE.notifyFocusedRect$foundation_release(textFieldValue, textFieldState.getTextDelegate(), layoutResult.getValue(), layoutCoordinates, inputSession, textFieldState.getHasFocus(), offsetMapping);
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    snapshotCreateNonObservableSnapshot.dispose();
                }
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                throw th;
            }
        } catch (Throwable th2) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th2;
        }
    }

    private static final Modifier previewKeyEventToDeselectOnBack(Modifier modifier, TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager) {
        return KeyInputModifierKt.onPreviewKeyEvent(modifier, new C03821(textFieldState, textFieldSelectionManager));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startInputSession(TextInputService textInputService, TextFieldState textFieldState, TextFieldValue textFieldValue, ImeOptions imeOptions, OffsetMapping offsetMapping) {
        textFieldState.setInputSession(TextFieldDelegate.INSTANCE.onFocus$foundation_release(textInputService, textFieldValue, textFieldState.getProcessor(), imeOptions, textFieldState.getOnValueChange(), textFieldState.getOnImeActionPerformed()));
        notifyFocusedRect(textFieldState, textFieldValue, offsetMapping);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tapToFocus(TextFieldState textFieldState, FocusRequester focusRequester, boolean z) {
        SoftwareKeyboardController keyboardController;
        if (!textFieldState.getHasFocus()) {
            focusRequester.requestFocus();
        } else {
            if (!z || (keyboardController = textFieldState.getKeyboardController()) == null) {
                return;
            }
            keyboardController.show();
        }
    }
}
