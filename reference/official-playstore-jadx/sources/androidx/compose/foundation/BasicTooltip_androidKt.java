package androidx.compose.foundation;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.repository.g;
import d7.d;
import d7.k;
import f7.e;
import f7.h;
import f7.j;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\u001ac\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a<\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001aB\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0017\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a3\u0010\u001b\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/window/PopupPositionProvider;", "positionProvider", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "tooltip", "Landroidx/compose/foundation/BasicTooltipState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "", "focusable", "enableUserInput", "content", "BasicTooltipBox", "(Landroidx/compose/ui/window/PopupPositionProvider;Lr7/p;Landroidx/compose/foundation/BasicTooltipState;Landroidx/compose/ui/Modifier;ZZLr7/p;Landroidx/compose/runtime/Composer;II)V", "WrappedAnchor", "(ZLandroidx/compose/foundation/BasicTooltipState;Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Lka/k0;", "scope", "TooltipPopup", "(Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/foundation/BasicTooltipState;Lka/k0;ZLr7/p;Landroidx/compose/runtime/Composer;I)V", "enabled", "handleGestures", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/BasicTooltipState;)Landroidx/compose/ui/Modifier;", "", "label", "anchorSemantics", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;ZLandroidx/compose/foundation/BasicTooltipState;Lka/k0;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTooltip_androidKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$BasicTooltipBox$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $enableUserInput;
        final /* synthetic */ boolean $focusable;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ PopupPositionProvider $positionProvider;
        final /* synthetic */ BasicTooltipState $state;
        final /* synthetic */ p<Composer, Integer, t0> $tooltip;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(PopupPositionProvider popupPositionProvider, p<? super Composer, ? super Integer, t0> pVar, BasicTooltipState basicTooltipState, Modifier modifier, boolean z, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, int i10, int i11) {
            super(2);
            this.$positionProvider = popupPositionProvider;
            this.$tooltip = pVar;
            this.$state = basicTooltipState;
            this.$modifier = modifier;
            this.$focusable = z;
            this.$enableUserInput = z5;
            this.$content = pVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTooltip_androidKt.BasicTooltipBox(this.$positionProvider, this.$tooltip, this.$state, this.$modifier, this.$focusable, this.$enableUserInput, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$TooltipPopup$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        final /* synthetic */ k0 $scope;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$TooltipPopup$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$TooltipPopup$1$1", f = "BasicTooltip.android.kt", l = {}, m = "invokeSuspend")
        public static final class C00021 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ BasicTooltipState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00021(BasicTooltipState basicTooltipState, d<? super C00021> dVar) {
                super(2, dVar);
                this.$state = basicTooltipState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00021(this.$state, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                this.$state.dismiss();
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00021) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BasicTooltipState basicTooltipState, k0 k0Var) {
            super(0);
            this.$state = basicTooltipState;
            this.$scope = k0Var;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m187invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m187invoke() {
            if (this.$state.isVisible()) {
                m0.p(this.$scope, null, 0, new C00021(this.$state, null), 3);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$TooltipPopup$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ String $tooltipDescription;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
            this.$tooltipDescription = str;
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
                ComposerKt.traceEventStart(-1147839433, i10, -1, "androidx.compose.foundation.TooltipPopup.<anonymous> (BasicTooltip.android.kt:135)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(-1626832148);
            boolean zChanged = composer.changed(this.$tooltipDescription);
            String str = this.$tooltipDescription;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new BasicTooltip_androidKt$TooltipPopup$2$1$1(str);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null);
            p<Composer, Integer, t0> pVar = this.$content;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierSemantics$default);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (a0.c.B(pVar, composer, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$TooltipPopup$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02853 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $focusable;
        final /* synthetic */ PopupPositionProvider $positionProvider;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02853(PopupPositionProvider popupPositionProvider, BasicTooltipState basicTooltipState, k0 k0Var, boolean z, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$positionProvider = popupPositionProvider;
            this.$state = basicTooltipState;
            this.$scope = k0Var;
            this.$focusable = z;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTooltip_androidKt.TooltipPopup(this.$positionProvider, this.$state, this.$scope, this.$focusable, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$WrappedAnchor$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02862 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $enableUserInput;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02862(boolean z, BasicTooltipState basicTooltipState, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$enableUserInput = z;
            this.$state = basicTooltipState;
            this.$modifier = modifier;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            BasicTooltip_androidKt.WrappedAnchor(this.$enableUserInput, this.$state, this.$modifier, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$anchorSemantics$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02871 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ String $label;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$anchorSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00031 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ BasicTooltipState $state;

            /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$anchorSemantics$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$anchorSemantics$1$1$1", f = "BasicTooltip.android.kt", l = {216}, m = "invokeSuspend")
            public static final class C00041 extends j implements p<k0, d<? super t0>, Object> {
                final /* synthetic */ BasicTooltipState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00041(BasicTooltipState basicTooltipState, d<? super C00041> dVar) {
                    super(2, dVar);
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C00041(this.$state, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        BasicTooltipState basicTooltipState = this.$state;
                        this.label = 1;
                        Object objA = b.a(basicTooltipState, null, this, 1, null);
                        e7.a aVar = e7.a.f15033i;
                        if (objA == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                    return ((C00041) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00031(k0 k0Var, BasicTooltipState basicTooltipState) {
                super(0);
                this.$scope = k0Var;
                this.$state = basicTooltipState;
            }

            @Override // r7.a
            public final Boolean invoke() {
                m0.p(this.$scope, null, 0, new C00041(this.$state, null), 3);
                return Boolean.TRUE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02871(String str, k0 k0Var, BasicTooltipState basicTooltipState) {
            super(1);
            this.$label = str;
            this.$scope = k0Var;
            this.$state = basicTooltipState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, this.$label, new C00031(this.$scope, this.$state));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1", f = "BasicTooltip.android.kt", l = {152}, m = "invokeSuspend")
    public static final class C02881 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ BasicTooltipState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1", f = "BasicTooltip.android.kt", l = {153}, m = "invokeSuspend")
        public static final class C00051 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ BasicTooltipState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1", f = "BasicTooltip.android.kt", l = {158, 164, TsExtractor.TS_STREAM_TYPE_AC4}, m = "invokeSuspend")
            public static final class C00061 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ BasicTooltipState $state;
                long J$0;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$1", f = "BasicTooltip.android.kt", l = {165}, m = "invokeSuspend")
                public static final class C00071 extends h implements p<AwaitPointerEventScope, d<? super PointerInputChange>, Object> {
                    final /* synthetic */ PointerEventPass $pass;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00071(PointerEventPass pointerEventPass, d<? super C00071> dVar) {
                        super(2, dVar);
                        this.$pass = pointerEventPass;
                    }

                    @Override // f7.a
                    public final d<t0> create(Object obj, d<?> dVar) {
                        C00071 c00071 = new C00071(this.$pass, dVar);
                        c00071.L$0 = obj;
                        return c00071;
                    }

                    @Override // r7.p
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super PointerInputChange> dVar) {
                        return ((C00071) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 != 0) {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                            return obj;
                        }
                        k2.c.G(obj);
                        AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        PointerEventPass pointerEventPass = this.$pass;
                        this.label = 1;
                        Object objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, this);
                        e7.a aVar = e7.a.f15033i;
                        return objWaitForUpOrCancellation == aVar ? aVar : objWaitForUpOrCancellation;
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$2", f = "BasicTooltip.android.kt", l = {169}, m = "invokeSuspend")
                public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
                    final /* synthetic */ BasicTooltipState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass2(BasicTooltipState basicTooltipState, d<? super AnonymousClass2> dVar) {
                        super(2, dVar);
                        this.$state = basicTooltipState;
                    }

                    @Override // f7.a
                    public final d<t0> create(Object obj, d<?> dVar) {
                        return new AnonymousClass2(this.$state, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 == 0) {
                            k2.c.G(obj);
                            BasicTooltipState basicTooltipState = this.$state;
                            MutatePriority mutatePriority = MutatePriority.UserInput;
                            this.label = 1;
                            Object objShow = basicTooltipState.show(mutatePriority, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objShow == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                        }
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                        return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00061(k0 k0Var, BasicTooltipState basicTooltipState, d<? super C00061> dVar) {
                    super(2, dVar);
                    this.$$this$coroutineScope = k0Var;
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    C00061 c00061 = new C00061(this.$$this$coroutineScope, this.$state, dVar);
                    c00061.L$0 = obj;
                    return c00061;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                    return ((C00061) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Code restructure failed: missing block: B:24:0x008e, code lost:
                
                    if (r4.withTimeout(r11, r1, r13) == r10) goto L29;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
                
                    if (r0 == r10) goto L29;
                 */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                    /*
                        r13 = this;
                        int r0 = r13.label
                        r6 = 0
                        r7 = 3
                        r8 = 2
                        r1 = 1
                        r9 = 0
                        e7.a r10 = e7.a.f15033i
                        if (r0 == 0) goto L3d
                        if (r0 == r1) goto L2c
                        if (r0 == r8) goto L1f
                        if (r0 != r7) goto L17
                        k2.c.G(r14)
                        r0 = r14
                        goto Lab
                    L17:
                        java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                        java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                        r0.<init>(r1)
                        throw r0
                    L1f:
                        java.lang.Object r0 = r13.L$1
                        androidx.compose.ui.input.pointer.PointerEventPass r0 = (androidx.compose.ui.input.pointer.PointerEventPass) r0
                        java.lang.Object r1 = r13.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        k2.c.G(r14)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L92
                        goto Lc3
                    L2c:
                        long r0 = r13.J$0
                        java.lang.Object r2 = r13.L$1
                        androidx.compose.ui.input.pointer.PointerEventPass r2 = (androidx.compose.ui.input.pointer.PointerEventPass) r2
                        java.lang.Object r4 = r13.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                        k2.c.G(r14)
                        r11 = r0
                        r1 = r14
                    L3b:
                        r0 = r2
                        goto L63
                    L3d:
                        k2.c.G(r14)
                        java.lang.Object r0 = r13.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                        androidx.compose.ui.platform.ViewConfiguration r2 = r0.getViewConfiguration()
                        long r11 = r2.getLongPressTimeoutMillis()
                        androidx.compose.ui.input.pointer.PointerEventPass r2 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r13.L$0 = r0
                        r13.L$1 = r2
                        r13.J$0 = r11
                        r13.label = r1
                        r1 = 0
                        r4 = 1
                        r5 = 0
                        r3 = r13
                        java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r0, r1, r2, r3, r4, r5)
                        if (r1 != r10) goto L61
                        goto Laa
                    L61:
                        r4 = r0
                        goto L3b
                    L63:
                        androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
                        int r1 = r1.getType()
                        androidx.compose.ui.input.pointer.PointerType$Companion r2 = androidx.compose.ui.input.pointer.PointerType.INSTANCE
                        int r5 = r2.m4584getTouchT8wyACA()
                        boolean r5 = androidx.compose.ui.input.pointer.PointerType.m4577equalsimpl0(r1, r5)
                        if (r5 != 0) goto L7f
                        int r2 = r2.m4583getStylusT8wyACA()
                        boolean r1 = androidx.compose.ui.input.pointer.PointerType.m4577equalsimpl0(r1, r2)
                        if (r1 == 0) goto Lc3
                    L7f:
                        androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$1 r1 = new androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$1     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        r1.<init>(r0, r9)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        r13.L$0 = r4     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        r13.L$1 = r0     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        r13.label = r8     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        java.lang.Object r0 = r4.withTimeout(r11, r1, r13)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
                        if (r0 != r10) goto Lc3
                        goto Laa
                    L91:
                        r1 = r4
                    L92:
                        ka.k0 r2 = r13.$$this$coroutineScope
                        androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$2 r4 = new androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$1$1$1$2
                        androidx.compose.foundation.BasicTooltipState r5 = r13.$state
                        r4.<init>(r5, r9)
                        ka.m0.p(r2, r9, r6, r4, r7)
                        r13.L$0 = r9
                        r13.L$1 = r9
                        r13.label = r7
                        java.lang.Object r0 = r1.awaitPointerEvent(r0, r13)
                        if (r0 != r10) goto Lab
                    Laa:
                        return r10
                    Lab:
                        androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
                        java.util.List r0 = r0.getChanges()
                        int r1 = r0.size()
                    Lb5:
                        if (r6 >= r1) goto Lc3
                        java.lang.Object r2 = r0.get(r6)
                        androidx.compose.ui.input.pointer.PointerInputChange r2 = (androidx.compose.ui.input.pointer.PointerInputChange) r2
                        r2.consume()
                        int r6 = r6 + 1
                        goto Lb5
                    Lc3:
                        x6.t0 r0 = x6.t0.f22605a
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BasicTooltip_androidKt.C02881.C00051.C00061.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00051(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, d<? super C00051> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = basicTooltipState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                C00051 c00051 = new C00051(this.$$this$pointerInput, this.$state, dVar);
                c00051.L$0 = obj;
                return c00051;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    C00061 c00061 = new C00061(k0Var, this.$state, null);
                    this.label = 1;
                    Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c00061, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAwaitEachGesture == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00051) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02881(BasicTooltipState basicTooltipState, d<? super C02881> dVar) {
            super(2, dVar);
            this.$state = basicTooltipState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C02881 c02881 = new C02881(this.$state, dVar);
            c02881.L$0 = obj;
            return c02881;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C02881) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                C00051 c00051 = new C00051((PointerInputScope) this.L$0, this.$state, null);
                this.label = 1;
                Object objC = l0.c(c00051, this);
                e7.a aVar = e7.a.f15033i;
                if (objC == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2", f = "BasicTooltip.android.kt", l = {180}, m = "invokeSuspend")
    public static final class C02892 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ BasicTooltipState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1", f = "BasicTooltip.android.kt", l = {181}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ BasicTooltipState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1", f = "BasicTooltip.android.kt", l = {185}, m = "invokeSuspend")
            public static final class C00081 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ BasicTooltipState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @e(c = "androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1$1", f = "BasicTooltip.android.kt", l = {190}, m = "invokeSuspend")
                public static final class C00091 extends j implements p<k0, d<? super t0>, Object> {
                    final /* synthetic */ BasicTooltipState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00091(BasicTooltipState basicTooltipState, d<? super C00091> dVar) {
                        super(2, dVar);
                        this.$state = basicTooltipState;
                    }

                    @Override // f7.a
                    public final d<t0> create(Object obj, d<?> dVar) {
                        return new C00091(this.$state, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 == 0) {
                            k2.c.G(obj);
                            BasicTooltipState basicTooltipState = this.$state;
                            MutatePriority mutatePriority = MutatePriority.UserInput;
                            this.label = 1;
                            Object objShow = basicTooltipState.show(mutatePriority, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objShow == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                        }
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                        return ((C00091) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00081(k0 k0Var, BasicTooltipState basicTooltipState, d<? super C00081> dVar) {
                    super(2, dVar);
                    this.$$this$coroutineScope = k0Var;
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    C00081 c00081 = new C00081(this.$$this$coroutineScope, this.$state, dVar);
                    c00081.L$0 = obj;
                    return c00081;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                    return ((C00081) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0033 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0031 -> B:12:0x0034). Please report as a decompilation issue!!! */
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
                public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                    /*
                        r7 = this;
                        int r0 = r7.label
                        r1 = 1
                        if (r0 == 0) goto L1b
                        if (r0 != r1) goto L13
                        java.lang.Object r0 = r7.L$1
                        androidx.compose.ui.input.pointer.PointerEventPass r0 = (androidx.compose.ui.input.pointer.PointerEventPass) r0
                        java.lang.Object r2 = r7.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                        k2.c.G(r8)
                        goto L34
                    L13:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r0)
                        throw r8
                    L1b:
                        k2.c.G(r8)
                        java.lang.Object r8 = r7.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r8 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r8
                        androidx.compose.ui.input.pointer.PointerEventPass r0 = androidx.compose.ui.input.pointer.PointerEventPass.Main
                        r2 = r8
                    L25:
                        r7.L$0 = r2
                        r7.L$1 = r0
                        r7.label = r1
                        java.lang.Object r8 = r2.awaitPointerEvent(r0, r7)
                        e7.a r3 = e7.a.f15033i
                        if (r8 != r3) goto L34
                        return r3
                    L34:
                        androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
                        java.util.List r3 = r8.getChanges()
                        r4 = 0
                        java.lang.Object r3 = r3.get(r4)
                        androidx.compose.ui.input.pointer.PointerInputChange r3 = (androidx.compose.ui.input.pointer.PointerInputChange) r3
                        int r3 = r3.getType()
                        androidx.compose.ui.input.pointer.PointerType$Companion r5 = androidx.compose.ui.input.pointer.PointerType.INSTANCE
                        int r5 = r5.m4582getMouseT8wyACA()
                        boolean r3 = androidx.compose.ui.input.pointer.PointerType.m4577equalsimpl0(r3, r5)
                        if (r3 == 0) goto L25
                        int r8 = r8.getType()
                        androidx.compose.ui.input.pointer.PointerEventType$Companion r3 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
                        int r5 = r3.m4471getEnter7fucELk()
                        boolean r5 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r8, r5)
                        if (r5 == 0) goto L70
                        ka.k0 r8 = r7.$$this$coroutineScope
                        androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1$1 r3 = new androidx.compose.foundation.BasicTooltip_androidKt$handleGestures$2$1$1$1
                        androidx.compose.foundation.BasicTooltipState r5 = r7.$state
                        r6 = 0
                        r3.<init>(r5, r6)
                        r5 = 3
                        ka.m0.p(r8, r6, r4, r3, r5)
                        goto L25
                    L70:
                        int r3 = r3.m4472getExit7fucELk()
                        boolean r8 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r8, r3)
                        if (r8 == 0) goto L25
                        androidx.compose.foundation.BasicTooltipState r8 = r7.$state
                        r8.dismiss()
                        goto L25
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BasicTooltip_androidKt.C02892.AnonymousClass1.C00081.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = basicTooltipState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$pointerInput, this.$state, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    C00081 c00081 = new C00081(k0Var, this.$state, null);
                    this.label = 1;
                    Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c00081, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAwaitPointerEventScope == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02892(BasicTooltipState basicTooltipState, d<? super C02892> dVar) {
            super(2, dVar);
            this.$state = basicTooltipState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C02892 c02892 = new C02892(this.$state, dVar);
            c02892.L$0 = obj;
            return c02892;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((C02892) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1((PointerInputScope) this.L$0, this.$state, null);
                this.label = 1;
                Object objC = l0.c(anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objC == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTooltipBox(androidx.compose.ui.window.PopupPositionProvider r17, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r18, androidx.compose.foundation.BasicTooltipState r19, androidx.compose.ui.Modifier r20, boolean r21, boolean r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BasicTooltip_androidKt.BasicTooltipBox(androidx.compose.ui.window.PopupPositionProvider, r7.p, androidx.compose.foundation.BasicTooltipState, androidx.compose.ui.Modifier, boolean, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TooltipPopup(PopupPositionProvider popupPositionProvider, BasicTooltipState basicTooltipState, k0 k0Var, boolean z, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-73658727);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-73658727, i10, -1, "androidx.compose.foundation.TooltipPopup (BasicTooltip.android.kt:124)");
        }
        AndroidPopup_androidKt.Popup(popupPositionProvider, new AnonymousClass1(basicTooltipState, k0Var), new PopupProperties(z, false, false, null, false, false, 62, null), ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1147839433, true, new AnonymousClass2(StringResources_androidKt.stringResource(R.string.tooltip_description, composerStartRestartGroup, 0), pVar)), composerStartRestartGroup, (i10 & 14) | 3072, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C02853(popupPositionProvider, basicTooltipState, k0Var, z, pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WrappedAnchor(boolean z, BasicTooltipState basicTooltipState, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1712976033);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        int i13 = i11 & 4;
        if (i13 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & 896) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i11 & 8) != 0) {
            i12 |= 3072;
        } else if ((i10 & 7168) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i12 & 5851) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1712976033, i12, -1, "androidx.compose.foundation.WrappedAnchor (BasicTooltip.android.kt:107)");
            }
            Object objG = a0.c.g(773894976, composerStartRestartGroup, -492369756);
            if (objG == Composer.INSTANCE.getEmpty()) {
                objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierAnchorSemantics = anchorSemantics(handleGestures(modifier, z, basicTooltipState), StringResources_androidKt.stringResource(R.string.tooltip_label, composerStartRestartGroup, 0), z, basicTooltipState, coroutineScope);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierAnchorSemantics);
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
            pVar.invoke(composerStartRestartGroup, Integer.valueOf((i12 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C02862(z, basicTooltipState, modifier2, pVar, i10, i11));
        }
    }

    private static final Modifier anchorSemantics(Modifier modifier, String str, boolean z, BasicTooltipState basicTooltipState, k0 k0Var) {
        return z ? SemanticsModifierKt.semantics(modifier, true, new C02871(str, k0Var, basicTooltipState)) : modifier;
    }

    private static final Modifier handleGestures(Modifier modifier, boolean z, BasicTooltipState basicTooltipState) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput(modifier, basicTooltipState, new C02881(basicTooltipState, null)), basicTooltipState, new C02892(basicTooltipState, null)) : modifier;
    }
}
