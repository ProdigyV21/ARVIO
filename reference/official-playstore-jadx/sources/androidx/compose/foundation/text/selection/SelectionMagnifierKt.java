package androidx.compose.foundation.text.selection;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import na.k;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aJ\u0010\t\u001a\u00020\u0000*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012'\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00000\u0004H\u0000¢\u0006\u0004\b\t\u0010\n\u001a#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0003¢\u0006\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"&\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u00128\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u001a\u0010\u0017\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\" \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006!²\u0006\f\u0010\u0007\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\f\u0010 \u001a\u00020\u00028\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Offset;", "magnifierCenter", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "animatedCenter", "platformMagnifier", "animatedSelectionMagnifier", "(Landroidx/compose/ui/Modifier;Lr7/a;Lr7/l;)Landroidx/compose/ui/Modifier;", "targetCalculation", "Landroidx/compose/runtime/State;", "rememberAnimatedMagnifierPosition", "(Lr7/a;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/AnimationVector2D;", "UnspecifiedAnimationVector2D", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/TwoWayConverter;", "UnspecifiedSafeOffsetVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "getUnspecifiedSafeOffsetVectorConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "OffsetDisplacementThreshold", "J", "getOffsetDisplacementThreshold", "()J", "Landroidx/compose/animation/core/SpringSpec;", "MagnifierSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "getMagnifierSpringSpec", "()Landroidx/compose/animation/core/SpringSpec;", "targetValue", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionMagnifierKt {
    private static final SpringSpec<Offset> MagnifierSpringSpec;
    private static final long OffsetDisplacementThreshold;
    private static final AnimationVector2D UnspecifiedAnimationVector2D = new AnimationVector2D(Float.NaN, Float.NaN);
    private static final TwoWayConverter<Offset, AnimationVector2D> UnspecifiedSafeOffsetVectorConverter = VectorConvertersKt.TwoWayConverter(SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$1.INSTANCE, SelectionMagnifierKt$UnspecifiedSafeOffsetVectorConverter$2.INSTANCE);

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$animatedSelectionMagnifier$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ r7.a<Offset> $magnifierCenter;
        final /* synthetic */ l<r7.a<Offset>, Modifier> $platformMagnifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(r7.a<Offset> aVar, l<? super r7.a<Offset>, ? extends Modifier> lVar) {
            super(3);
            this.$magnifierCenter = aVar;
            this.$platformMagnifier = lVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$0(State<Offset> state) {
            return state.getValue().getPackedValue();
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(759876635);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759876635, i10, -1, "androidx.compose.foundation.text.selection.animatedSelectionMagnifier.<anonymous> (SelectionMagnifier.kt:65)");
            }
            State stateRememberAnimatedMagnifierPosition = SelectionMagnifierKt.rememberAnimatedMagnifierPosition(this.$magnifierCenter, composer, 0);
            l<r7.a<Offset>, Modifier> lVar = this.$platformMagnifier;
            composer.startReplaceableGroup(1714568984);
            boolean zChanged = composer.changed(stateRememberAnimatedMagnifierPosition);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SelectionMagnifierKt$animatedSelectionMagnifier$1$1$1(stateRememberAnimatedMagnifierPosition);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifier2 = (Modifier) lVar.invoke((r7.a) objRememberedValue);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifier2;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1", f = "SelectionMagnifier.kt", l = {86}, m = "invokeSuspend")
    public static final class C03961 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Animatable<Offset, AnimationVector2D> $animatable;
        final /* synthetic */ State<Offset> $targetValue$delegate;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "invoke-F1C5BW0", "()J"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00581 extends r implements r7.a<Offset> {
            final /* synthetic */ State<Offset> $targetValue$delegate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00581(State<Offset> state) {
                super(0);
                this.$targetValue$delegate = state;
            }

            @Override // r7.a
            public /* bridge */ /* synthetic */ Object invoke() {
                return Offset.m3225boximpl(m981invokeF1C5BW0());
            }

            /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
            public final long m981invokeF1C5BW0() {
                return SelectionMagnifierKt.rememberAnimatedMagnifierPosition$lambda$1(this.$targetValue$delegate);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03961(State<Offset> state, Animatable<Offset, AnimationVector2D> animatable, d<? super C03961> dVar) {
            super(2, dVar);
            this.$targetValue$delegate = state;
            this.$animatable = animatable;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C03961 c03961 = new C03961(this.$targetValue$delegate, this.$animatable, dVar);
            c03961.L$0 = obj;
            return c03961;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                final k0 k0Var = (k0) this.L$0;
                na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new C00581(this.$targetValue$delegate));
                final Animatable<Offset, AnimationVector2D> animatable = this.$animatable;
                k kVar = new k() { // from class: androidx.compose.foundation.text.selection.SelectionMagnifierKt.rememberAnimatedMagnifierPosition.1.2

                    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$2$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                    @e(c = "androidx.compose.foundation.text.selection.SelectionMagnifierKt$rememberAnimatedMagnifierPosition$1$2$1", f = "SelectionMagnifier.kt", l = {100}, m = "invokeSuspend")
                    public static final class C00591 extends j implements p<k0, d<? super t0>, Object> {
                        final /* synthetic */ Animatable<Offset, AnimationVector2D> $animatable;
                        final /* synthetic */ long $targetValue;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00591(Animatable<Offset, AnimationVector2D> animatable, long j10, d<? super C00591> dVar) {
                            super(2, dVar);
                            this.$animatable = animatable;
                            this.$targetValue = j10;
                        }

                        @Override // f7.a
                        public final d<t0> create(Object obj, d<?> dVar) {
                            return new C00591(this.$animatable, this.$targetValue, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                c.G(obj);
                                Animatable<Offset, AnimationVector2D> animatable = this.$animatable;
                                Offset offsetM3225boximpl = Offset.m3225boximpl(this.$targetValue);
                                SpringSpec<Offset> magnifierSpringSpec = SelectionMagnifierKt.getMagnifierSpringSpec();
                                this.label = 1;
                                Object objAnimateTo$default = Animatable.animateTo$default(animatable, offsetM3225boximpl, magnifierSpringSpec, null, null, this, 12, null);
                                e7.a aVar = e7.a.f15033i;
                                if (objAnimateTo$default == aVar) {
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
                            return ((C00591) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                        }
                    }

                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return m982emit3MmeM6k(((Offset) obj2).getPackedValue(), dVar);
                    }

                    /* JADX INFO: renamed from: emit-3MmeM6k, reason: not valid java name */
                    public final Object m982emit3MmeM6k(long j10, d<? super t0> dVar) {
                        boolean zM3255isSpecifiedk4lQ0M = OffsetKt.m3255isSpecifiedk4lQ0M(animatable.getValue().getPackedValue());
                        t0 t0Var = t0.f22605a;
                        if (zM3255isSpecifiedk4lQ0M && OffsetKt.m3255isSpecifiedk4lQ0M(j10) && Offset.m3237getYimpl(animatable.getValue().getPackedValue()) != Offset.m3237getYimpl(j10)) {
                            m0.p(k0Var, null, 0, new C00591(animatable, j10, null), 3);
                            return t0Var;
                        }
                        Object objSnapTo = animatable.snapTo(Offset.m3225boximpl(j10), dVar);
                        return objSnapTo == e7.a.f15033i ? objSnapTo : t0Var;
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
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C03961) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    static {
        long jOffset = OffsetKt.Offset(0.01f, 0.01f);
        OffsetDisplacementThreshold = jOffset;
        MagnifierSpringSpec = new SpringSpec<>(0.0f, 0.0f, Offset.m3225boximpl(jOffset), 3, null);
    }

    public static final Modifier animatedSelectionMagnifier(Modifier modifier, r7.a<Offset> aVar, l<? super r7.a<Offset>, ? extends Modifier> lVar) {
        return ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(aVar, lVar), 1, null);
    }

    public static final SpringSpec<Offset> getMagnifierSpringSpec() {
        return MagnifierSpringSpec;
    }

    public static final long getOffsetDisplacementThreshold() {
        return OffsetDisplacementThreshold;
    }

    public static final TwoWayConverter<Offset, AnimationVector2D> getUnspecifiedSafeOffsetVectorConverter() {
        return UnspecifiedSafeOffsetVectorConverter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final State<Offset> rememberAnimatedMagnifierPosition(r7.a<Offset> aVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1589795249);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1589795249, i10, -1, "androidx.compose.foundation.text.selection.rememberAnimatedMagnifierPosition (SelectionMagnifier.kt:76)");
        }
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(aVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        State state = (State) objRememberedValue;
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            Animatable animatable = new Animatable(Offset.m3225boximpl(rememberAnimatedMagnifierPosition$lambda$1(state)), getUnspecifiedSafeOffsetVectorConverter(), Offset.m3225boximpl(getOffsetDisplacementThreshold()), null, 8, null);
            composer.updateRememberedValue(animatable);
            objRememberedValue2 = animatable;
        }
        composer.endReplaceableGroup();
        Animatable animatable2 = (Animatable) objRememberedValue2;
        EffectsKt.LaunchedEffect(t0.f22605a, new C03961(state, animatable2, null), composer, 70);
        State<Offset> stateAsState = animatable2.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long rememberAnimatedMagnifierPosition$lambda$1(State<Offset> state) {
        return state.getValue().getPackedValue();
    }
}
