package androidx.compose.foundation.text2;

import a0.c;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text2.input.ImeActionHandler;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.TextFieldValue;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aè\u0001\u0010(\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u001728\b\u0002\u0010!\u001a2\u0012\u0004\u0012\u00020\u001a\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0019¢\u0006\u0002\b 2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001aÔ\u0001\u0010(\u001a\u00020\u00032\u0006\u0010*\u001a\u00020)2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u001728\b\u0002\u0010!\u001a2\u0012\u0004\u0012\u00020\u001a\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0019¢\u0006\u0002\b 2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u0017\u0010.\u001a\u00020-2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b.\u0010/\u001a\"\u00102\u001a\u00020\u00032\u0011\u00101\u001a\r\u0012\u0004\u0012\u00020\u00030\u001b¢\u0006\u0002\b0H\u0003¢\u0006\u0004\b2\u00103\"\u0014\u00105\u001a\u0002048\u0002X\u0082T¢\u0006\u0006\n\u0004\b5\u00106\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00069²\u0006\u000e\u00108\u001a\u0002078\n@\nX\u008a\u008e\u0002"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/text2/input/ImeActionHandler;", "onSubmit", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "Landroidx/compose/foundation/text2/input/TextObfuscationMode;", "textObfuscationMode", "Landroidx/compose/ui/text/input/KeyboardType;", "keyboardType", "", "enabled", "Landroidx/compose/foundation/text2/input/InputTransformation;", "inputTransformation", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Brush;", "cursorBrush", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function0;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/y;", ContentDisposition.Parameters.Name, "getResult", "Lx6/n;", "onTextLayout", "Landroidx/compose/foundation/text2/TextFieldDecorator;", "decorator", "Landroidx/compose/foundation/ScrollState;", "scrollState", "BasicSecureTextField-TLP4tmw", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text2/input/ImeActionHandler;IIIZLandroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/p;Landroidx/compose/foundation/text2/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "BasicSecureTextField", "Landroidx/compose/foundation/text2/input/TextFieldState;", "state", "BasicSecureTextField-mMrxcSU", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text2/input/ImeActionHandler;IIIZLandroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lr7/p;Landroidx/compose/foundation/text2/TextFieldDecorator;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/foundation/text/KeyboardActions;", "KeyboardActions", "(Landroidx/compose/foundation/text2/input/ImeActionHandler;)Landroidx/compose/foundation/text/KeyboardActions;", "Landroidx/compose/runtime/Composable;", "content", "DisableCutCopy", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "", "LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS", "J", "Landroidx/compose/ui/text/input/TextFieldValue;", "valueWithSelection", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicSecureTextFieldKt {
    private static final long LAST_TYPED_CHARACTER_REVEAL_DURATION_MILLIS = 1500;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$DisableCutCopy$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$DisableCutCopy$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "invoke-ZmokQxo", "(Landroid/view/KeyEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00641 extends r implements l<KeyEvent, Boolean> {
            public static final C00641 INSTANCE = new C00641();

            public C00641() {
                super(1);
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return m1050invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m1050invokeZmokQxo(android.view.KeyEvent keyEvent) {
                KeyCommand keyCommandMo827mapZmokQxo = KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo827mapZmokQxo(keyEvent);
                return Boolean.valueOf(keyCommandMo827mapZmokQxo == KeyCommand.COPY || keyCommandMo827mapZmokQxo == KeyCommand.CUT);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
            this.$content = pVar;
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
                ComposerKt.traceEventStart(-1741121166, i10, -1, "androidx.compose.foundation.text2.DisableCutCopy.<anonymous> (BasicSecureTextField.kt:512)");
            }
            Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(Modifier.INSTANCE, C00641.INSTANCE);
            p<Composer, Integer, t0> pVar = this.$content;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnPreviewKeyEvent);
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
            p pVarU = c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (c.B(pVar, composer, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$DisableCutCopy$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicSecureTextFieldKt.DisableCutCopy(this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04011 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04011(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5368getDoneeUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5368getDoneeUduSuo());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04022 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04022(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5369getGoeUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5369getGoeUduSuo());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5370getNexteUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5370getNexteUduSuo());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5372getPreviouseUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5372getPreviouseUduSuo());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5373getSearcheUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5373getSearcheUduSuo());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.BasicSecureTextFieldKt$KeyboardActions$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/text/KeyboardActionScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass6 extends r implements l<KeyboardActionScope, t0> {
        final /* synthetic */ ImeActionHandler $onSubmit;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(ImeActionHandler imeActionHandler) {
            super(1);
            this.$onSubmit = imeActionHandler;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((KeyboardActionScope) obj);
            return t0.f22605a;
        }

        public final void invoke(KeyboardActionScope keyboardActionScope) {
            ImeActionHandler imeActionHandler = this.$onSubmit;
            ImeAction.Companion companion = ImeAction.INSTANCE;
            if (imeActionHandler.mo1049onImeActionKlQnJC8(companion.m5374getSendeUduSuo())) {
                return;
            }
            keyboardActionScope.mo828defaultKeyboardActionKlQnJC8(companion.m5374getSendeUduSuo());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:239:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012d  */
    /* JADX INFO: renamed from: BasicSecureTextField-TLP4tmw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1047BasicSecureTextFieldTLP4tmw(java.lang.String r37, r7.l<? super java.lang.String, x6.t0> r38, androidx.compose.ui.Modifier r39, androidx.compose.foundation.text2.input.ImeActionHandler r40, int r41, int r42, int r43, boolean r44, androidx.compose.foundation.text2.input.InputTransformation r45, androidx.compose.ui.text.TextStyle r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, androidx.compose.ui.graphics.Brush r48, r7.p<? super androidx.compose.ui.unit.Density, ? super r7.a<androidx.compose.ui.text.TextLayoutResult>, x6.t0> r49, androidx.compose.foundation.text2.TextFieldDecorator r50, androidx.compose.foundation.ScrollState r51, androidx.compose.runtime.Composer r52, int r53, int r54, int r55) {
        /*
            Method dump skipped, instruction units count: 1063
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.BasicSecureTextFieldKt.m1047BasicSecureTextFieldTLP4tmw(java.lang.String, r7.l, androidx.compose.ui.Modifier, androidx.compose.foundation.text2.input.ImeActionHandler, int, int, int, boolean, androidx.compose.foundation.text2.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.p, androidx.compose.foundation.text2.TextFieldDecorator, androidx.compose.foundation.ScrollState, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:242:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012b  */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [androidx.compose.foundation.text2.TextFieldDecorator] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r31v5 */
    /* JADX WARN: Type inference failed for: r31v6, types: [androidx.compose.foundation.text2.TextFieldDecorator] */
    /* JADX WARN: Type inference failed for: r31v7 */
    /* JADX INFO: renamed from: BasicSecureTextField-mMrxcSU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1048BasicSecureTextFieldmMrxcSU(androidx.compose.foundation.text2.input.TextFieldState r34, androidx.compose.ui.Modifier r35, androidx.compose.foundation.text2.input.ImeActionHandler r36, int r37, int r38, int r39, boolean r40, androidx.compose.foundation.text2.input.InputTransformation r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.interaction.MutableInteractionSource r43, androidx.compose.ui.graphics.Brush r44, r7.p<? super androidx.compose.ui.unit.Density, ? super r7.a<androidx.compose.ui.text.TextLayoutResult>, x6.t0> r45, androidx.compose.foundation.text2.TextFieldDecorator r46, androidx.compose.foundation.ScrollState r47, androidx.compose.runtime.Composer r48, int r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 955
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.BasicSecureTextFieldKt.m1048BasicSecureTextFieldmMrxcSU(androidx.compose.foundation.text2.input.TextFieldState, androidx.compose.ui.Modifier, androidx.compose.foundation.text2.input.ImeActionHandler, int, int, int, boolean, androidx.compose.foundation.text2.input.InputTransformation, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, r7.p, androidx.compose.foundation.text2.TextFieldDecorator, androidx.compose.foundation.ScrollState, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue BasicSecureTextField_TLP4tmw$lambda$2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisableCutCopy(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(930154034);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 11) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(930154034, i11, -1, "androidx.compose.foundation.text2.DisableCutCopy (BasicSecureTextField.kt:490)");
            }
            final TextToolbar textToolbar = (TextToolbar) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalTextToolbar());
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            boolean zChanged = composerStartRestartGroup.changed(textToolbar);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TextToolbar() { // from class: androidx.compose.foundation.text2.BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1
                    private final /* synthetic */ TextToolbar $$delegate_0;

                    {
                        this.$$delegate_0 = this.$currentToolbar;
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public TextToolbarStatus getStatus() {
                        return this.$$delegate_0.getStatus();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void hide() {
                        this.$$delegate_0.hide();
                    }

                    @Override // androidx.compose.ui.platform.TextToolbar
                    public void showMenu(Rect rect, a<t0> onCopyRequested, a<t0> onPasteRequested, a<t0> onCutRequested, a<t0> onSelectAllRequested) {
                        this.$currentToolbar.showMenu(rect, null, onPasteRequested, null, onSelectAllRequested);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalTextToolbar().provides((BasicSecureTextFieldKt$DisableCutCopy$copyDisabledToolbar$1$1) objRememberedValue), ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1741121166, true, new AnonymousClass1(pVar)), composerStartRestartGroup, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KeyboardActions KeyboardActions(ImeActionHandler imeActionHandler) {
        return new KeyboardActions(new C04011(imeActionHandler), new C04022(imeActionHandler), new AnonymousClass3(imeActionHandler), new AnonymousClass4(imeActionHandler), new AnonymousClass5(imeActionHandler), new AnonymousClass6(imeActionHandler));
    }
}
