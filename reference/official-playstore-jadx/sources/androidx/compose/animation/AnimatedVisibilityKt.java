package androidx.compose.animation;

import a0.c;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a]\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001aa\u0010\u0010\u001a\u00020\f*\u00020\u00122\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0013\u001aa\u0010\u0010\u001a\u00020\f*\u00020\u00142\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0015\u001ac\u0010\u0010\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0018\u001ag\u0010\u0010\u001a\u00020\f*\u00020\u00122\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0019\u001ag\u0010\u0010\u001a\u00020\f*\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u00162\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u001a\u001ao\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0012\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00000\n2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u001d\u001aL\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00002\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\f0\u001f¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u0010\u0010 \u001am\u0010\"\u001a\u00020\f\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0012\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00000\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0004\b\"\u0010#\u001a\u0093\u0001\u0010)\u001a\u00020\f\"\u0004\b\u0000\u0010\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0012\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00000\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0018\u0010&\u001a\u0014\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00000$2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010'2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0004\b)\u0010*\u001a;\u0010,\u001a\u00020%\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0012\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00000\n2\u0006\u0010+\u001a\u00028\u0000H\u0003¢\u0006\u0004\b,\u0010-\"\u001e\u00100\u001a\u00020\u0000*\b\u0012\u0004\u0012\u00020%0\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00063²\u0006$\u00101\u001a\u0014\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00000$\"\u0004\b\u0000\u0010\u001b8\nX\u008a\u0084\u0002²\u0006\u0012\u00102\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001b8\nX\u008a\u0084\u0002"}, d2 = {"", "visible", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/animation/EnterTransition;", "enter", "Landroidx/compose/animation/ExitTransition;", "exit", "", "label", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "AnimatedVisibility", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/RowScope;", "(Landroidx/compose/foundation/layout/RowScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/MutableTransitionState;", "visibleState", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "T", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "initiallyVisible", "Lkotlin/Function0;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;ZLr7/p;Landroidx/compose/runtime/Composer;II)V", "transition", "AnimatedVisibilityImpl", "(Landroidx/compose/animation/core/Transition;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function2;", "Landroidx/compose/animation/EnterExitState;", "shouldDisposeBlock", "Landroidx/compose/animation/OnLookaheadMeasured;", "onLookaheadMeasured", "AnimatedEnterExitImpl", "(Landroidx/compose/animation/core/Transition;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lr7/p;Landroidx/compose/animation/OnLookaheadMeasured;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "targetState", "targetEnterExit", "(Landroidx/compose/animation/core/Transition;Lr7/l;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterExitState;", "getExitFinished", "(Landroidx/compose/animation/core/Transition;)Z", "exitFinished", "shouldDisposeBlockUpdated", "shouldDisposeAfterExit", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimatedVisibilityKt {

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "T", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<MeasureScope, Measurable, Constraints, MeasureResult> {
        final /* synthetic */ OnLookaheadMeasured $onLookaheadMeasured;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(OnLookaheadMeasured onLookaheadMeasured) {
            super(3);
            this.$onLookaheadMeasured = onLookaheadMeasured;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return m31invoke3p2s80s((MeasureScope) obj, (Measurable) obj2, ((Constraints) obj3).getValue());
        }

        /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
        public final MeasureResult m31invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
            Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
            OnLookaheadMeasured onLookaheadMeasured = this.$onLookaheadMeasured;
            if (measureScope.isLookingAhead()) {
                onLookaheadMeasured.m62invokeozmzZPI(IntSizeKt.IntSize(placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight()));
            }
            return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1(placeableMo4631measureBRTryo0), 4, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ OnLookaheadMeasured $onLookaheadMeasured;
        final /* synthetic */ p<EnterExitState, EnterExitState, Boolean> $shouldDisposeBlock;
        final /* synthetic */ Transition<T> $transition;
        final /* synthetic */ l<T, Boolean> $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Transition<T> transition, l<? super T, Boolean> lVar, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, p<? super EnterExitState, ? super EnterExitState, Boolean> pVar, OnLookaheadMeasured onLookaheadMeasured, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$transition = transition;
            this.$visible = lVar;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$shouldDisposeBlock = pVar;
            this.$onLookaheadMeasured = onLookaheadMeasured;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedEnterExitImpl(this.$transition, this.$visible, this.$modifier, this.$enter, this.$exit, this.$shouldDisposeBlock, this.$onLookaheadMeasured, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$10, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ RowScope $this_AnimatedVisibility;
        final /* synthetic */ MutableTransitionState<Boolean> $visibleState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(RowScope rowScope, MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedVisibility = rowScope;
            this.$visibleState = mutableTransitionState;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$this_AnimatedVisibility, this.$visibleState, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$11, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass11 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass11 INSTANCE = new AnonymousClass11();

        public AnonymousClass11() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$12, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass12 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ ColumnScope $this_AnimatedVisibility;
        final /* synthetic */ MutableTransitionState<Boolean> $visibleState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass12(ColumnScope columnScope, MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedVisibility = columnScope;
            this.$visibleState = mutableTransitionState;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$this_AnimatedVisibility, this.$visibleState, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$13, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass13 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Transition<T> $this_AnimatedVisibility;
        final /* synthetic */ l<T, Boolean> $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass13(Transition<T> transition, l<? super T, Boolean> lVar, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedVisibility = transition;
            this.$visible = lVar;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$this_AnimatedVisibility, this.$visible, this.$modifier, this.$enter, this.$exit, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$16, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass16 extends r implements q<AnimatedVisibilityScope, Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass16(p<? super Composer, ? super Integer, t0> pVar) {
            super(3);
            this.$content = pVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
            if ((i10 & 81) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1996320812, i10, -1, "androidx.compose.animation.AnimatedVisibility.<anonymous> (AnimatedVisibility.kt:721)");
            }
            if (c.A(composer, 0, this.$content)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$17, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass17 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ boolean $initiallyVisible;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass17(boolean z, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, boolean z5, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$visible = z;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$initiallyVisible = z5;
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
            AnimatedVisibilityKt.AnimatedVisibility(this.$visible, this.$modifier, this.$enter, this.$exit, this.$initiallyVisible, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02462 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02462(boolean z, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$visible = z;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$visible, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02474 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ RowScope $this_AnimatedVisibility;
        final /* synthetic */ boolean $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02474(RowScope rowScope, boolean z, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedVisibility = rowScope;
            this.$visible = z;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$this_AnimatedVisibility, this.$visible, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ ColumnScope $this_AnimatedVisibility;
        final /* synthetic */ boolean $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(ColumnScope columnScope, boolean z, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedVisibility = columnScope;
            this.$visible = z;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$this_AnimatedVisibility, this.$visible, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass7 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass7 INSTANCE = new AnonymousClass7();

        public AnonymousClass7() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$8, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ MutableTransitionState<Boolean> $visibleState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, String str, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$visibleState = mutableTransitionState;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$label = str;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibility(this.$visibleState, this.$modifier, this.$enter, this.$exit, this.$label, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$9, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Z)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements l<Boolean, Boolean> {
        public static final AnonymousClass9 INSTANCE = new AnonymousClass9();

        public AnonymousClass9() {
            super(1);
        }

        public final Boolean invoke(boolean z) {
            return Boolean.valueOf(z);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Boolean) obj).booleanValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "current", "Landroidx/compose/animation/EnterExitState;", "target", "invoke", "(Landroidx/compose/animation/EnterExitState;Landroidx/compose/animation/EnterExitState;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02482 extends r implements p<EnterExitState, EnterExitState, Boolean> {
        public static final C02482 INSTANCE = new C02482();

        public C02482() {
            super(2);
        }

        @Override // r7.p
        public final Boolean invoke(EnterExitState enterExitState, EnterExitState enterExitState2) {
            return Boolean.valueOf(enterExitState == enterExitState2 && enterExitState2 == EnterExitState.PostExit);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02493 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ q<AnimatedVisibilityScope, Composer, Integer, t0> $content;
        final /* synthetic */ EnterTransition $enter;
        final /* synthetic */ ExitTransition $exit;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Transition<T> $transition;
        final /* synthetic */ l<T, Boolean> $visible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02493(Transition<T> transition, l<? super T, Boolean> lVar, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, int i10) {
            super(2);
            this.$transition = transition;
            this.$visible = lVar;
            this.$modifier = modifier;
            this.$enter = enterTransition;
            this.$exit = exitTransition;
            this.$content = qVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedVisibilityKt.AnimatedVisibilityImpl(this.$transition, this.$visible, this.$modifier, this.$enter, this.$exit, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> void AnimatedEnterExitImpl(androidx.compose.animation.core.Transition<T> r20, r7.l<? super T, java.lang.Boolean> r21, androidx.compose.ui.Modifier r22, androidx.compose.animation.EnterTransition r23, androidx.compose.animation.ExitTransition r24, r7.p<? super androidx.compose.animation.EnterExitState, ? super androidx.compose.animation.EnterExitState, java.lang.Boolean> r25, androidx.compose.animation.OnLookaheadMeasured r26, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 810
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl(androidx.compose.animation.core.Transition, r7.l, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, r7.p, androidx.compose.animation.OnLookaheadMeasured, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p<EnterExitState, EnterExitState, Boolean> AnimatedEnterExitImpl$lambda$4(State<? extends p<? super EnterExitState, ? super EnterExitState, Boolean>> state) {
        return (p) state.getValue();
    }

    private static final boolean AnimatedEnterExitImpl$lambda$6(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(boolean r23, androidx.compose.ui.Modifier r24, androidx.compose.animation.EnterTransition r25, androidx.compose.animation.ExitTransition r26, java.lang.String r27, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final <T> void AnimatedVisibilityImpl(Transition<T> transition, l<? super T, Boolean> lVar, Modifier modifier, EnterTransition enterTransition, ExitTransition exitTransition, q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i10) {
        int i11;
        EnterTransition enterTransition2;
        q<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, t0> qVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(429978603);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(transition) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar) ? 32 : 16;
        }
        if ((i10 & 896) == 0) {
            i11 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i10 & 7168) == 0) {
            enterTransition2 = enterTransition;
            i11 |= composerStartRestartGroup.changed(enterTransition2) ? 2048 : 1024;
        } else {
            enterTransition2 = enterTransition;
        }
        if ((i10 & 57344) == 0) {
            i11 |= composerStartRestartGroup.changed(exitTransition) ? 16384 : 8192;
        }
        if ((458752 & i10) == 0) {
            qVar2 = qVar;
            i11 |= composerStartRestartGroup.changedInstance(qVar2) ? 131072 : 65536;
        } else {
            qVar2 = qVar;
        }
        if ((374491 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(429978603, i11, -1, "androidx.compose.animation.AnimatedVisibilityImpl (AnimatedVisibility.kt:739)");
            }
            composerStartRestartGroup.startReplaceableGroup(-311853878);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(lVar) | composerStartRestartGroup.changed(transition);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1(lVar, transition);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            AnimatedEnterExitImpl(transition, lVar, LayoutModifierKt.layout(modifier, (q) objRememberedValue), enterTransition2, exitTransition, C02482.INSTANCE, null, qVar2, composerStartRestartGroup, (57344 & i11) | 196608 | (i11 & 14) | (i11 & 112) | (i11 & 7168) | ((i11 << 6) & 29360128), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C02493(transition, lVar, modifier, enterTransition, exitTransition, qVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getExitFinished(Transition<EnterExitState> transition) {
        EnterExitState currentState = transition.getCurrentState();
        EnterExitState enterExitState = EnterExitState.PostExit;
        return currentState == enterExitState && transition.getTargetState() == enterExitState;
    }

    private static final <T> EnterExitState targetEnterExit(Transition<T> transition, l<? super T, Boolean> lVar, T t2, Composer composer, int i10) {
        EnterExitState enterExitState;
        composer.startReplaceableGroup(361571134);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, i10, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:889)");
        }
        composer.startMovableGroup(-721835388, transition);
        if (transition.isSeeking()) {
            enterExitState = ((Boolean) lVar.invoke(t2)).booleanValue() ? EnterExitState.Visible : ((Boolean) lVar.invoke(transition.getCurrentState())).booleanValue() ? EnterExitState.PostExit : EnterExitState.PreEnter;
        } else {
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            MutableState mutableState = (MutableState) objRememberedValue;
            if (((Boolean) lVar.invoke(transition.getCurrentState())).booleanValue()) {
                mutableState.setValue(Boolean.TRUE);
            }
            enterExitState = ((Boolean) lVar.invoke(t2)).booleanValue() ? EnterExitState.Visible : ((Boolean) mutableState.getValue()).booleanValue() ? EnterExitState.PostExit : EnterExitState.PreEnter;
        }
        composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return enterExitState;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(androidx.compose.foundation.layout.RowScope r23, boolean r24, androidx.compose.ui.Modifier r25, androidx.compose.animation.EnterTransition r26, androidx.compose.animation.ExitTransition r27, java.lang.String r28, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.RowScope, boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope r23, boolean r24, androidx.compose.ui.Modifier r25, androidx.compose.animation.EnterTransition r26, androidx.compose.animation.ExitTransition r27, java.lang.String r28, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope, boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(androidx.compose.animation.core.MutableTransitionState<java.lang.Boolean> r23, androidx.compose.ui.Modifier r24, androidx.compose.animation.EnterTransition r25, androidx.compose.animation.ExitTransition r26, java.lang.String r27, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.animation.core.MutableTransitionState, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(androidx.compose.foundation.layout.RowScope r23, androidx.compose.animation.core.MutableTransitionState<java.lang.Boolean> r24, androidx.compose.ui.Modifier r25, androidx.compose.animation.EnterTransition r26, androidx.compose.animation.ExitTransition r27, java.lang.String r28, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.RowScope, androidx.compose.animation.core.MutableTransitionState, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope r23, androidx.compose.animation.core.MutableTransitionState<java.lang.Boolean> r24, androidx.compose.ui.Modifier r25, androidx.compose.animation.EnterTransition r26, androidx.compose.animation.ExitTransition r27, java.lang.String r28, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope, androidx.compose.animation.core.MutableTransitionState, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> void AnimatedVisibility(androidx.compose.animation.core.Transition<T> r21, r7.l<? super T, java.lang.Boolean> r22, androidx.compose.ui.Modifier r23, androidx.compose.animation.EnterTransition r24, androidx.compose.animation.ExitTransition r25, r7.q<? super androidx.compose.animation.AnimatedVisibilityScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, androidx.compose.runtime.Composer r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.animation.core.Transition, r7.l, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedVisibility(boolean r17, androidx.compose.ui.Modifier r18, androidx.compose.animation.EnterTransition r19, androidx.compose.animation.ExitTransition r20, boolean r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }
}
