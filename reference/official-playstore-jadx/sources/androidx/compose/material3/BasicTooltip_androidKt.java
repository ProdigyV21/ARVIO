package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
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
import androidx.media3.extractor.ts.PsExtractor;
import d7.k;
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
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\u001ac\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a<\u0010\u0010\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001aB\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a#\u0010\u0017\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a3\u0010\u001b\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/window/PopupPositionProvider;", "positionProvider", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "tooltip", "Landroidx/compose/material3/BasicTooltipState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "", "focusable", "enableUserInput", "content", "BasicTooltipBox", "(Landroidx/compose/ui/window/PopupPositionProvider;Lr7/p;Landroidx/compose/material3/BasicTooltipState;Landroidx/compose/ui/Modifier;ZZLr7/p;Landroidx/compose/runtime/Composer;II)V", "WrappedAnchor", "(ZLandroidx/compose/material3/BasicTooltipState;Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Lka/k0;", "scope", "TooltipPopup", "(Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/material3/BasicTooltipState;Lka/k0;ZLr7/p;Landroidx/compose/runtime/Composer;I)V", "enabled", "handleGestures", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/BasicTooltipState;)Landroidx/compose/ui/Modifier;", "", "label", "anchorSemantics", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;ZLandroidx/compose/material3/BasicTooltipState;Lka/k0;)Landroidx/compose/ui/Modifier;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BasicTooltip_androidKt {

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$BasicTooltipBox$3, reason: invalid class name */
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

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$TooltipPopup$2, reason: invalid class name */
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
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(282408040, i10, -1, "androidx.compose.material3.TooltipPopup.<anonymous> (BasicTooltip.android.kt:140)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(1706937961);
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

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$TooltipPopup$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04673 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $focusable;
        final /* synthetic */ PopupPositionProvider $positionProvider;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04673(PopupPositionProvider popupPositionProvider, BasicTooltipState basicTooltipState, k0 k0Var, boolean z, p<? super Composer, ? super Integer, t0> pVar, int i10) {
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

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$WrappedAnchor$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04682 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $enableUserInput;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04682(boolean z, BasicTooltipState basicTooltipState, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
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

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$anchorSemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ String $label;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$anchorSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00861 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ BasicTooltipState $state;

            /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$anchorSemantics$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$anchorSemantics$1$1$1", f = "BasicTooltip.android.kt", l = {219}, m = "invokeSuspend")
            public static final class C00871 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ BasicTooltipState $state;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00871(BasicTooltipState basicTooltipState, d7.d<? super C00871> dVar) {
                    super(2, dVar);
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00871(this.$state, dVar);
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
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((C00871) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00861(k0 k0Var, BasicTooltipState basicTooltipState) {
                super(0);
                this.$scope = k0Var;
                this.$state = basicTooltipState;
            }

            @Override // r7.a
            public final Boolean invoke() {
                m0.p(this.$scope, null, 0, new C00871(this.$state, null), 3);
                return Boolean.TRUE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, k0 k0Var, BasicTooltipState basicTooltipState) {
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
            SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, this.$label, new C00861(this.$scope, this.$state));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1", f = "BasicTooltip.android.kt", l = {156}, m = "invokeSuspend")
    public static final class C04691 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        final /* synthetic */ BasicTooltipState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1", f = "BasicTooltip.android.kt", l = {157}, m = "invokeSuspend")
        public static final class C00881 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ BasicTooltipState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1", f = "BasicTooltip.android.kt", l = {162, 168, 176}, m = "invokeSuspend")
            public static final class C00891 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ BasicTooltipState $state;
                long J$0;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
                @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$1", f = "BasicTooltip.android.kt", l = {169}, m = "invokeSuspend")
                public static final class C00901 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super PointerInputChange>, Object> {
                    final /* synthetic */ PointerEventPass $pass;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00901(PointerEventPass pointerEventPass, d7.d<? super C00901> dVar) {
                        super(2, dVar);
                        this.$pass = pointerEventPass;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        C00901 c00901 = new C00901(this.$pass, dVar);
                        c00901.L$0 = obj;
                        return c00901;
                    }

                    @Override // r7.p
                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super PointerInputChange> dVar) {
                        return ((C00901) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
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

                /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$2", f = "BasicTooltip.android.kt", l = {173}, m = "invokeSuspend")
                public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ BasicTooltipState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass2(BasicTooltipState basicTooltipState, d7.d<? super AnonymousClass2> dVar) {
                        super(2, dVar);
                        this.$state = basicTooltipState;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
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
                    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                        return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00891(k0 k0Var, BasicTooltipState basicTooltipState, d7.d<? super C00891> dVar) {
                    super(2, dVar);
                    this.$$this$coroutineScope = k0Var;
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C00891 c00891 = new C00891(this.$$this$coroutineScope, this.$state, dVar);
                    c00891.L$0 = obj;
                    return c00891;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                    return ((C00891) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
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
                        androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$1 r1 = new androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$1     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L91
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
                        androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$2 r4 = new androidx.compose.material3.BasicTooltip_androidKt$handleGestures$1$1$1$2
                        androidx.compose.material3.BasicTooltipState r5 = r13.$state
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
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BasicTooltip_androidKt.C04691.C00881.C00891.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00881(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, d7.d<? super C00881> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = basicTooltipState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C00881 c00881 = new C00881(this.$$this$pointerInput, this.$state, dVar);
                c00881.L$0 = obj;
                return c00881;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    C00891 c00891 = new C00891(k0Var, this.$state, null);
                    this.label = 1;
                    Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c00891, this);
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
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C00881) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04691(BasicTooltipState basicTooltipState, d7.d<? super C04691> dVar) {
            super(2, dVar);
            this.$state = basicTooltipState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C04691 c04691 = new C04691(this.$state, dVar);
            c04691.L$0 = obj;
            return c04691;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((C04691) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                C00881 c00881 = new C00881((PointerInputScope) this.L$0, this.$state, null);
                this.label = 1;
                Object objC = l0.c(c00881, this);
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

    /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2", f = "BasicTooltip.android.kt", l = {184}, m = "invokeSuspend")
    public static final class C04702 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        final /* synthetic */ BasicTooltipState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1", f = "BasicTooltip.android.kt", l = {185}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ BasicTooltipState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1", f = "BasicTooltip.android.kt", l = {PsExtractor.PRIVATE_STREAM_1}, m = "invokeSuspend")
            public static final class C00911 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ BasicTooltipState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                /* JADX INFO: renamed from: androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1$1", f = "BasicTooltip.android.kt", l = {194}, m = "invokeSuspend")
                public static final class C00921 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ BasicTooltipState $state;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00921(BasicTooltipState basicTooltipState, d7.d<? super C00921> dVar) {
                        super(2, dVar);
                        this.$state = basicTooltipState;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new C00921(this.$state, dVar);
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
                    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                        return ((C00921) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00911(k0 k0Var, BasicTooltipState basicTooltipState, d7.d<? super C00911> dVar) {
                    super(2, dVar);
                    this.$$this$coroutineScope = k0Var;
                    this.$state = basicTooltipState;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C00911 c00911 = new C00911(this.$$this$coroutineScope, this.$state, dVar);
                    c00911.L$0 = obj;
                    return c00911;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                    return ((C00911) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
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
                        androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1$1 r3 = new androidx.compose.material3.BasicTooltip_androidKt$handleGestures$2$1$1$1
                        androidx.compose.material3.BasicTooltipState r5 = r7.$state
                        r6 = 0
                        r3.<init>(r5, r6)
                        r5 = 3
                        ka.m0.p(r8, r6, r4, r3, r5)
                        goto L25
                    L70:
                        int r3 = r3.m4472getExit7fucELk()
                        boolean r8 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r8, r3)
                        if (r8 == 0) goto L25
                        androidx.compose.material3.BasicTooltipState r8 = r7.$state
                        r8.dismiss()
                        goto L25
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BasicTooltip_androidKt.C04702.AnonymousClass1.C00911.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = basicTooltipState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
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
                    C00911 c00911 = new C00911(k0Var, this.$state, null);
                    this.label = 1;
                    Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(c00911, this);
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
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04702(BasicTooltipState basicTooltipState, d7.d<? super C04702> dVar) {
            super(2, dVar);
            this.$state = basicTooltipState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C04702 c04702 = new C04702(this.$state, dVar);
            c04702.L$0 = obj;
            return c04702;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((C04702) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
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

    /* JADX WARN: Removed duplicated region for block: B:102:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTooltipBox(androidx.compose.ui.window.PopupPositionProvider r17, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r18, androidx.compose.material3.BasicTooltipState r19, androidx.compose.ui.Modifier r20, boolean r21, boolean r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BasicTooltip_androidKt.BasicTooltipBox(androidx.compose.ui.window.PopupPositionProvider, r7.p, androidx.compose.material3.BasicTooltipState, androidx.compose.ui.Modifier, boolean, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TooltipPopup(PopupPositionProvider popupPositionProvider, BasicTooltipState basicTooltipState, k0 k0Var, boolean z, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        PopupPositionProvider popupPositionProvider2;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-237130298);
        if ((i10 & 6) == 0) {
            popupPositionProvider2 = popupPositionProvider;
            i11 = (composerStartRestartGroup.changed(popupPositionProvider2) ? 4 : 2) | i10;
        } else {
            popupPositionProvider2 = popupPositionProvider;
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(k0Var) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-237130298, i11, -1, "androidx.compose.material3.TooltipPopup (BasicTooltip.android.kt:129)");
            }
            String strStringResource = StringResources_androidKt.stringResource(androidx.compose.foundation.R.string.tooltip_description, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(1291172190);
            boolean zChangedInstance = ((i11 & 112) == 32) | composerStartRestartGroup.changedInstance(k0Var);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new BasicTooltip_androidKt$TooltipPopup$1$1(basicTooltipState, k0Var);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AndroidPopup_androidKt.Popup(popupPositionProvider2, (r7.a) objRememberedValue, new PopupProperties(z, false, false, null, false, false, 62, null), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 282408040, true, new AnonymousClass2(strStringResource, pVar)), composerStartRestartGroup, (i11 & 14) | 3072, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04673(popupPositionProvider, basicTooltipState, k0Var, z, pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WrappedAnchor(boolean z, BasicTooltipState basicTooltipState, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(513239742);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        int i13 = i11 & 4;
        if (i13 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i11 & 8) != 0) {
            i12 |= 3072;
        } else if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i12 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(513239742, i12, -1, "androidx.compose.material3.WrappedAnchor (BasicTooltip.android.kt:113)");
            }
            Object objG = a0.c.g(773894976, composerStartRestartGroup, -492369756);
            if (objG == Composer.INSTANCE.getEmpty()) {
                objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierAnchorSemantics = anchorSemantics(handleGestures(modifier, z, basicTooltipState), StringResources_androidKt.stringResource(androidx.compose.foundation.R.string.tooltip_label, composerStartRestartGroup, 0), z, basicTooltipState, coroutineScope);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new C04682(z, basicTooltipState, modifier2, pVar, i10, i11));
        }
    }

    private static final Modifier anchorSemantics(Modifier modifier, String str, boolean z, BasicTooltipState basicTooltipState, k0 k0Var) {
        return z ? SemanticsModifierKt.semantics(modifier, true, new AnonymousClass1(str, k0Var, basicTooltipState)) : modifier;
    }

    private static final Modifier handleGestures(Modifier modifier, boolean z, BasicTooltipState basicTooltipState) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput(modifier, basicTooltipState, new C04691(basicTooltipState, null)), basicTooltipState, new C04702(basicTooltipState, null)) : modifier;
    }
}
