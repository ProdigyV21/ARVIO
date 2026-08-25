package androidx.compose.foundation.text2;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.AndroidCursorHandle_androidKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldLineLimits;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldHandleState;
import androidx.compose.foundation.text2.input.internal.selection.TextFieldSelectionState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ClipboardManager;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Density;
import androidx.media3.exoplayer.RendererCapabilities;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aï\u0001\u0010'\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u001228\b\u0002\u0010\u001c\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010&\u001a\u00020%H\u0007¢\u0006\u0004\b'\u0010(\u001aÛ\u0001\u0010'\u001a\u00020\u00032\u0006\u0010*\u001a\u00020)2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u001228\b\u0002\u0010\u001c\u001a2\u0012\u0004\u0012\u00020\u0015\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0014¢\u0006\u0002\b\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\b\b\u0002\u0010&\u001a\u00020%H\u0007¢\u0006\u0004\b'\u0010+\u001a\u0017\u0010.\u001a\u00020\u00032\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b.\u0010/\u001a\u0017\u00100\u001a\u00020\u00032\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b0\u0010/\"\u001a\u00101\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b1\u00102\u0012\u0004\b3\u00104¨\u00067²\u0006\u000e\u00106\u001a\u0002058\n@\nX\u008a\u008e\u0002"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "readOnly", "Landroidx/compose/foundation/text2/input/InputTransformation;", "inputTransformation", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "Landroidx/compose/foundation/text2/input/TextFieldLineLimits;", "lineLimits", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/y;", ContentDisposition.Parameters.Name, "getResult", "Lx6/n;", "onTextLayout", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "codepointTransformation", "Landroidx/compose/foundation/text2/TextFieldDecorator;", "decorator", "Landroidx/compose/foundation/ScrollState;", "scrollState", "BasicTextField2", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/foundation/text2/input/TextFieldLineLimits;Lr7/p;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text2/input/CodepointTransformation;Landroidx/compose/foundation/text2/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/foundation/text2/input/TextFieldLineLimits;Lr7/p;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/text2/input/CodepointTransformation;Landroidx/compose/foundation/text2/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;", "selectionState", "TextFieldCursorHandle", "(Landroidx/compose/foundation/text2/input/internal/selection/TextFieldSelectionState;Landroidx/compose/runtime/Composer;I)V", "TextFieldSelectionHandles", "DefaultTextFieldDecorator", "Landroidx/compose/foundation/text2/TextFieldDecorator;", "getDefaultTextFieldDecorator$annotations", "()V", "Landroidx/compose/ui/text/input/TextFieldValue;", "valueWithSelection", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTextField2Kt {
    private static final TextFieldDecorator DefaultTextFieldDecorator = BasicTextField2Kt$DefaultTextFieldDecorator$1.INSTANCE;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$BasicTextField2$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ CodepointTransformation $codepointTransformation;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ TextFieldDecorator $decorator;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InputTransformation $inputTransformation;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Density, a<TextLayoutResult>, t0> $onTextLayout;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ ScrollState $scrollState;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ String $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, l<? super String, t0> lVar, Modifier modifier, boolean z, boolean z5, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, TextFieldLineLimits textFieldLineLimits, p<? super Density, ? super a<TextLayoutResult>, t0> pVar, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, int i10, int i11, int i12) {
            super(2);
            this.$value = str;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$inputTransformation = inputTransformation;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$lineLimits = textFieldLineLimits;
            this.$onTextLayout = pVar;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$codepointTransformation = codepointTransformation;
            this.$decorator = textFieldDecorator;
            this.$scrollState = scrollState;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextField2Kt.BasicTextField2(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$inputTransformation, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$lineLimits, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$codepointTransformation, this.$decorator, this.$scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$BasicTextField2$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements a<t0> {
        final /* synthetic */ ClipboardManager $currentClipboardManager;
        final /* synthetic */ HapticFeedback $currentHapticFeedback;
        final /* synthetic */ TextToolbar $currentTextToolbar;
        final /* synthetic */ Density $density;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextFieldSelectionState $textFieldSelectionState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(TextFieldSelectionState textFieldSelectionState, HapticFeedback hapticFeedback, ClipboardManager clipboardManager, TextToolbar textToolbar, Density density, boolean z, boolean z5) {
            super(0);
            this.$textFieldSelectionState = textFieldSelectionState;
            this.$currentHapticFeedback = hapticFeedback;
            this.$currentClipboardManager = clipboardManager;
            this.$currentTextToolbar = textToolbar;
            this.$density = density;
            this.$enabled = z;
            this.$readOnly = z5;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m1051invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m1051invoke() {
            this.$textFieldSelectionState.update(this.$currentHapticFeedback, this.$currentClipboardManager, this.$currentTextToolbar, this.$density, this.$enabled, this.$readOnly);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$BasicTextField2$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ TextFieldSelectionState $textFieldSelectionState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(TextFieldSelectionState textFieldSelectionState) {
            super(1);
            this.$textFieldSelectionState = textFieldSelectionState;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            final TextFieldSelectionState textFieldSelectionState = this.$textFieldSelectionState;
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.text2.BasicTextField2Kt$BasicTextField2$4$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    textFieldSelectionState.dispose();
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$BasicTextField2$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ CodepointTransformation $codepointTransformation;
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ TextFieldDecorator $decorator;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InputTransformation $inputTransformation;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Density, a<TextLayoutResult>, t0> $onTextLayout;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ ScrollState $scrollState;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextStyle $textStyle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z5, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, TextFieldLineLimits textFieldLineLimits, p<? super Density, ? super a<TextLayoutResult>, t0> pVar, MutableInteractionSource mutableInteractionSource, Brush brush, CodepointTransformation codepointTransformation, TextFieldDecorator textFieldDecorator, ScrollState scrollState, int i10, int i11, int i12) {
            super(2);
            this.$state = textFieldState;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$inputTransformation = inputTransformation;
            this.$textStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$lineLimits = textFieldLineLimits;
            this.$onTextLayout = pVar;
            this.$interactionSource = mutableInteractionSource;
            this.$cursorBrush = brush;
            this.$codepointTransformation = codepointTransformation;
            this.$decorator = textFieldDecorator;
            this.$scrollState = scrollState;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextField2Kt.BasicTextField2(this.$state, this.$modifier, this.$enabled, this.$readOnly, this.$inputTransformation, this.$textStyle, this.$keyboardOptions, this.$keyboardActions, this.$lineLimits, this.$onTextLayout, this.$interactionSource, this.$cursorBrush, this.$codepointTransformation, this.$decorator, this.$scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldCursorHandle$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldCursorHandle$2", f = "BasicTextField2.kt", l = {503}, m = "invokeSuspend")
    public static final class C04032 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ TextFieldSelectionState $selectionState;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04032(TextFieldSelectionState textFieldSelectionState, d<? super C04032> dVar) {
            super(2, dVar);
            this.$selectionState = textFieldSelectionState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04032 c04032 = new C04032(this.$selectionState, dVar);
            c04032.L$0 = obj;
            return c04032;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C04032) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                TextFieldSelectionState textFieldSelectionState = this.$selectionState;
                this.label = 1;
                Object objCursorHandleGestures = textFieldSelectionState.cursorHandleGestures(pointerInputScope, this);
                e7.a aVar = e7.a.f15033i;
                if (objCursorHandleGestures == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldCursorHandle$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04043 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TextFieldSelectionState $selectionState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04043(TextFieldSelectionState textFieldSelectionState, int i10) {
            super(2);
            this.$selectionState = textFieldSelectionState;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextField2Kt.TextFieldCursorHandle(this.$selectionState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldSelectionHandles$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldSelectionHandles$2", f = "BasicTextField2.kt", l = {522}, m = "invokeSuspend")
    public static final class C04052 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ TextFieldSelectionState $selectionState;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04052(TextFieldSelectionState textFieldSelectionState, d<? super C04052> dVar) {
            super(2, dVar);
            this.$selectionState = textFieldSelectionState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04052 c04052 = new C04052(this.$selectionState, dVar);
            c04052.L$0 = obj;
            return c04052;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C04052) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                TextFieldSelectionState textFieldSelectionState = this.$selectionState;
                this.label = 1;
                Object objSelectionHandleGestures = textFieldSelectionState.selectionHandleGestures(pointerInputScope, true, this);
                e7.a aVar = e7.a.f15033i;
                if (objSelectionHandleGestures == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldSelectionHandles$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldSelectionHandles$4", f = "BasicTextField2.kt", l = {535}, m = "invokeSuspend")
    public static final class C04074 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ TextFieldSelectionState $selectionState;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04074(TextFieldSelectionState textFieldSelectionState, d<? super C04074> dVar) {
            super(2, dVar);
            this.$selectionState = textFieldSelectionState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C04074 c04074 = new C04074(this.$selectionState, dVar);
            c04074.L$0 = obj;
            return c04074;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C04074) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                TextFieldSelectionState textFieldSelectionState = this.$selectionState;
                this.label = 1;
                Object objSelectionHandleGestures = textFieldSelectionState.selectionHandleGestures(pointerInputScope, false, this);
                e7.a aVar = e7.a.f15033i;
                if (objSelectionHandleGestures == aVar) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicTextField2Kt$TextFieldSelectionHandles$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TextFieldSelectionState $selectionState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(TextFieldSelectionState textFieldSelectionState, int i10) {
            super(2);
            this.$selectionState = textFieldSelectionState;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTextField2Kt.TextFieldSelectionHandles(this.$selectionState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:250:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField2(java.lang.String r38, r7.l<? super java.lang.String, x6.t0> r39, androidx.compose.ui.Modifier r40, boolean r41, boolean r42, androidx.compose.foundation.text2.input.InputTransformation r43, androidx.compose.ui.text.TextStyle r44, androidx.compose.foundation.text.KeyboardOptions r45, androidx.compose.foundation.text.KeyboardActions r46, androidx.compose.foundation.text2.input.TextFieldLineLimits r47, r7.p<? super androidx.compose.ui.unit.Density, ? super r7.a<androidx.compose.ui.text.TextLayoutResult>, x6.t0> r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, androidx.compose.foundation.text2.input.CodepointTransformation r51, androidx.compose.foundation.text2.TextFieldDecorator r52, androidx.compose.foundation.ScrollState r53, androidx.compose.runtime.Composer r54, int r55, int r56, int r57) {
        /*
            Method dump skipped, instruction units count: 1109
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.BasicTextField2Kt.BasicTextField2(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text2.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.foundation.text2.input.TextFieldLineLimits, r7.p, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text2.input.CodepointTransformation, androidx.compose.foundation.text2.TextFieldDecorator, androidx.compose.foundation.ScrollState, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue BasicTextField2$lambda$2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    public static final void TextFieldCursorHandle(TextFieldSelectionState textFieldSelectionState, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(773754631);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(773754631, i10, -1, "androidx.compose.foundation.text2.TextFieldCursorHandle (BasicTextField2.kt:487)");
        }
        TextFieldHandleState cursorHandle = textFieldSelectionState.getCursorHandle();
        if (cursorHandle.getVisible()) {
            long jM1150getPositionF1C5BW0 = cursorHandle.m1150getPositionF1C5BW0();
            Modifier.Companion companion = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(1290415310);
            boolean zChanged = composerStartRestartGroup.changed(cursorHandle);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new BasicTextField2Kt$TextFieldCursorHandle$1$1(cursorHandle);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AndroidCursorHandle_androidKt.m801CursorHandleULxng0E(jM1150getPositionF1C5BW0, SuspendingPointerInputFilterKt.pointerInput(SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), textFieldSelectionState, new C04032(textFieldSelectionState, null)), null, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04043(textFieldSelectionState, i10));
        }
    }

    public static final void TextFieldSelectionHandles(final TextFieldSelectionState textFieldSelectionState, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-1194626330);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1194626330, i10, -1, "androidx.compose.foundation.text2.TextFieldSelectionHandles (BasicTextField2.kt:512)");
        }
        TextFieldHandleState startSelectionHandle = textFieldSelectionState.getStartSelectionHandle();
        composerStartRestartGroup.startReplaceableGroup(-1453543870);
        if (startSelectionHandle.getVisible()) {
            AndroidSelectionHandles_androidKt.SelectionHandle(new OffsetProvider() { // from class: androidx.compose.foundation.text2.BasicTextField2Kt.TextFieldSelectionHandles.1
                @Override // androidx.compose.foundation.text.selection.OffsetProvider
                /* JADX INFO: renamed from: provide-F1C5BW0 */
                public final long mo802provideF1C5BW0() {
                    return textFieldSelectionState.getStartSelectionHandle().m1150getPositionF1C5BW0();
                }
            }, true, startSelectionHandle.getDirection(), startSelectionHandle.getHandlesCrossed(), SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textFieldSelectionState, new C04052(textFieldSelectionState, null)), composerStartRestartGroup, 48);
        }
        composerStartRestartGroup.endReplaceableGroup();
        TextFieldHandleState endSelectionHandle = textFieldSelectionState.getEndSelectionHandle();
        if (endSelectionHandle.getVisible()) {
            AndroidSelectionHandles_androidKt.SelectionHandle(new OffsetProvider() { // from class: androidx.compose.foundation.text2.BasicTextField2Kt.TextFieldSelectionHandles.3
                @Override // androidx.compose.foundation.text.selection.OffsetProvider
                /* JADX INFO: renamed from: provide-F1C5BW0 */
                public final long mo802provideF1C5BW0() {
                    return textFieldSelectionState.getEndSelectionHandle().m1150getPositionF1C5BW0();
                }
            }, false, endSelectionHandle.getDirection(), endSelectionHandle.getHandlesCrossed(), SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, textFieldSelectionState, new C04074(textFieldSelectionState, null)), composerStartRestartGroup, 48);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass5(textFieldSelectionState, i10));
        }
    }

    private static /* synthetic */ void getDefaultTextFieldDecorator$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0510  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:301:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012a  */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45, types: [androidx.compose.foundation.text2.TextFieldDecorator] */
    /* JADX WARN: Type inference failed for: r0v71 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6, types: [androidx.compose.foundation.text2.TextFieldDecorator] */
    /* JADX WARN: Type inference failed for: r14v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField2(androidx.compose.foundation.text2.input.TextFieldState r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.foundation.text2.input.InputTransformation r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, androidx.compose.foundation.text2.input.TextFieldLineLimits r45, r7.p<? super androidx.compose.ui.unit.Density, ? super r7.a<androidx.compose.ui.text.TextLayoutResult>, x6.t0> r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, androidx.compose.ui.graphics.Brush r48, androidx.compose.foundation.text2.input.CodepointTransformation r49, androidx.compose.foundation.text2.TextFieldDecorator r50, androidx.compose.foundation.ScrollState r51, androidx.compose.runtime.Composer r52, int r53, int r54, int r55) {
        /*
            Method dump skipped, instruction units count: 1478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.BasicTextField2Kt.BasicTextField2(androidx.compose.foundation.text2.input.TextFieldState, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.foundation.text2.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.foundation.text2.input.TextFieldLineLimits, r7.p, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.text2.input.CodepointTransformation, androidx.compose.foundation.text2.TextFieldDecorator, androidx.compose.foundation.ScrollState, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
