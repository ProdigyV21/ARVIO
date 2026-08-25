package androidx.compose.animation;

import androidx.compose.animation.AnimatedContentTransitionScopeImpl;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"S", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class AnimatedContentKt$AnimatedContent$6$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ r7.r<AnimatedContentScope, S, Composer, Integer, t0> $content;
    final /* synthetic */ SnapshotStateList<S> $currentlyVisible;
    final /* synthetic */ AnimatedContentTransitionScopeImpl<S> $rootScope;
    final /* synthetic */ S $stateForContent;
    final /* synthetic */ Transition<S> $this_AnimatedContent;
    final /* synthetic */ l<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "S", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<MeasureScope, Measurable, Constraints, MeasureResult> {
        final /* synthetic */ ContentTransform $specOnEnter;

        /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"S", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00011 extends r implements l<Placeable.PlacementScope, t0> {
            final /* synthetic */ Placeable $placeable;
            final /* synthetic */ ContentTransform $specOnEnter;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00011(Placeable placeable, ContentTransform contentTransform) {
                super(1);
                this.$placeable = placeable;
                this.$specOnEnter = contentTransform;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                placementScope.place(this.$placeable, 0, 0, this.$specOnEnter.getTargetContentZIndex());
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ContentTransform contentTransform) {
            super(3);
            this.$specOnEnter = contentTransform;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m3invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).getValue());
        }

        /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
        public final MeasureResult m3invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
            Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
            return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new C00011(placeableMo4631measureBRTryo0, this.$specOnEnter), 4, null);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "S", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3<S> extends r implements l<S, Boolean> {
        final /* synthetic */ S $stateForContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(S s10) {
            super(1);
            this.$stateForContent = s10;
        }

        @Override // r7.l
        public final Boolean invoke(S s10) {
            return Boolean.valueOf(kotlin.jvm.internal.p.a(s10, this.$stateForContent));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"S", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements q<AnimatedVisibilityScope, Composer, Integer, t0> {
        final /* synthetic */ r7.r<AnimatedContentScope, S, Composer, Integer, t0> $content;
        final /* synthetic */ SnapshotStateList<S> $currentlyVisible;
        final /* synthetic */ AnimatedContentTransitionScopeImpl<S> $rootScope;
        final /* synthetic */ S $stateForContent;

        /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "S", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
            final /* synthetic */ SnapshotStateList<S> $currentlyVisible;
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> $rootScope;
            final /* synthetic */ S $stateForContent;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(SnapshotStateList<S> snapshotStateList, S s10, AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl) {
                super(1);
                this.$currentlyVisible = snapshotStateList;
                this.$stateForContent = s10;
                this.$rootScope = animatedContentTransitionScopeImpl;
            }

            @Override // r7.l
            public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
                final SnapshotStateList<S> snapshotStateList = this.$currentlyVisible;
                final S s10 = this.$stateForContent;
                final AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl = this.$rootScope;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.AnimatedContentKt$AnimatedContent$6$1$5$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        snapshotStateList.remove(s10);
                        animatedContentTransitionScopeImpl.getTargetSizeMap$animation_release().remove(s10);
                    }
                };
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl, S s10, SnapshotStateList<S> snapshotStateList, r7.r<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, t0> rVar) {
            super(3);
            this.$rootScope = animatedContentTransitionScopeImpl;
            this.$stateForContent = s10;
            this.$currentlyVisible = snapshotStateList;
            this.$content = rVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
            if ((i10 & 14) == 0) {
                i10 |= composer.changed(animatedVisibilityScope) ? 4 : 2;
            }
            if ((i10 & 91) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-616195562, i10, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous>.<anonymous> (AnimatedContent.kt:745)");
            }
            EffectsKt.DisposableEffect(animatedVisibilityScope, new AnonymousClass1(this.$currentlyVisible, this.$stateForContent, this.$rootScope), composer, i10 & 14);
            this.$rootScope.getTargetSizeMap$animation_release().put(this.$stateForContent, ((AnimatedVisibilityScopeImpl) animatedVisibilityScope).getTargetSize$animation_release());
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new AnimatedContentScopeImpl(animatedVisibilityScope);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            this.$content.invoke((AnimatedContentScopeImpl) objRememberedValue, this.$stateForContent, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AnimatedContentKt$AnimatedContent$6$1(Transition<S> transition, S s10, l<? super AnimatedContentTransitionScope<S>, ContentTransform> lVar, AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl, SnapshotStateList<S> snapshotStateList, r7.r<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, t0> rVar) {
        super(2);
        this.$this_AnimatedContent = transition;
        this.$stateForContent = s10;
        this.$transitionSpec = lVar;
        this.$rootScope = animatedContentTransitionScopeImpl;
        this.$currentlyVisible = snapshotStateList;
        this.$content = rVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void invoke(Composer composer, int i10) {
        if ((i10 & 11) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(885640742, i10, -1, "androidx.compose.animation.AnimatedContent.<anonymous>.<anonymous> (AnimatedContent.kt:709)");
        }
        l<AnimatedContentTransitionScope<S>, ContentTransform> lVar = this.$transitionSpec;
        Object obj = this.$rootScope;
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = (ContentTransform) lVar.invoke(obj);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        ContentTransform contentTransform = (ContentTransform) objRememberedValue;
        Object objValueOf = Boolean.valueOf(kotlin.jvm.internal.p.a(this.$this_AnimatedContent.getSegment().getTargetState(), this.$stateForContent));
        Transition<S> transition = this.$this_AnimatedContent;
        S s10 = this.$stateForContent;
        l<AnimatedContentTransitionScope<S>, ContentTransform> lVar2 = this.$transitionSpec;
        Object obj2 = this.$rootScope;
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(objValueOf);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = kotlin.jvm.internal.p.a(transition.getSegment().getTargetState(), s10) ? ExitTransition.INSTANCE.getNone() : ((ContentTransform) lVar2.invoke(obj2)).getInitialContentExit();
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        ExitTransition exitTransition = (ExitTransition) objRememberedValue2;
        S s11 = this.$stateForContent;
        Transition<S> transition2 = this.$this_AnimatedContent;
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new AnimatedContentTransitionScopeImpl.ChildData(kotlin.jvm.internal.p.a(s11, transition2.getTargetState()));
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        AnimatedContentTransitionScopeImpl.ChildData childData = (AnimatedContentTransitionScopeImpl.ChildData) objRememberedValue3;
        EnterTransition targetContentEnter = contentTransform.getTargetContentEnter();
        Modifier modifierLayout = LayoutModifierKt.layout(Modifier.INSTANCE, new AnonymousClass1(contentTransform));
        childData.setTarget(kotlin.jvm.internal.p.a(this.$stateForContent, this.$this_AnimatedContent.getTargetState()));
        Modifier modifierThen = modifierLayout.then(childData);
        Transition<S> transition3 = this.$this_AnimatedContent;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$stateForContent);
        composer.startReplaceableGroup(841088387);
        boolean zChanged2 = composer.changed(exitTransition);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new AnimatedContentKt$AnimatedContent$6$1$4$1(exitTransition);
            composer.updateRememberedValue(objRememberedValue4);
        }
        composer.endReplaceableGroup();
        AnimatedVisibilityKt.AnimatedEnterExitImpl(transition3, anonymousClass3, modifierThen, targetContentEnter, exitTransition, (p) objRememberedValue4, null, ComposableLambdaKt.composableLambda(composer, -616195562, true, new AnonymousClass5(this.$rootScope, this.$stateForContent, this.$currentlyVisible, this.$content)), composer, 12582912, 64);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
