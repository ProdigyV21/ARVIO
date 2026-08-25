package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001:\u0001=BQ\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012:\b\u0002\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0015\u0010\u0014J&\u0010\u001d\u001a\u00020\u001a*\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010 \u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0011R(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%RT\u0010\u000b\u001a4\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u00020\u00038\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b+\u0010,R*\u0010.\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e8\u0002@BX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b.\u0010,\"\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R/\u0010<\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u0001048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006>"}, d2 = {"Landroidx/compose/animation/SizeAnimationModifierNode;", "Landroidx/compose/animation/LayoutModifierNodeWithPassThroughIntrinsics;", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/ui/unit/IntSize;", "animationSpec", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "startSize", "endSize", "Lx6/t0;", "listener", "<init>", "(Landroidx/compose/animation/core/AnimationSpec;Lr7/p;)V", "Landroidx/compose/ui/unit/Constraints;", "default", "targetConstraints-ZezNO4M", "(J)J", "targetConstraints", "onReset", "()V", "onAttach", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "targetSize", "animateTo-mzRDjE0", "animateTo", "Landroidx/compose/animation/core/AnimationSpec;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/AnimationSpec;)V", "Lr7/p;", "getListener", "()Lr7/p;", "setListener", "(Lr7/p;)V", "lookaheadSize", "J", "value", "lookaheadConstraints", "setLookaheadConstraints-BRTryo0", "(J)V", "", "lookaheadConstraintsAvailable", "Z", "Landroidx/compose/animation/SizeAnimationModifierNode$AnimData;", "<set-?>", "animData$delegate", "Landroidx/compose/runtime/MutableState;", "getAnimData", "()Landroidx/compose/animation/SizeAnimationModifierNode$AnimData;", "setAnimData", "(Landroidx/compose/animation/SizeAnimationModifierNode$AnimData;)V", "animData", "AnimData", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SizeAnimationModifierNode extends LayoutModifierNodeWithPassThroughIntrinsics {

    /* JADX INFO: renamed from: animData$delegate, reason: from kotlin metadata */
    private final MutableState animData;
    private AnimationSpec<IntSize> animationSpec;
    private p<? super IntSize, ? super IntSize, t0> listener;
    private long lookaheadConstraints;
    private boolean lookaheadConstraintsAvailable;
    private long lookaheadSize;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\r\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\u0010\u001a\u00020\u00002\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\nR(\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0012\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/animation/SizeAnimationModifierNode$AnimData;", "", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "anim", "startSize", "<init>", "(Landroidx/compose/animation/core/Animatable;JLkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/animation/core/Animatable;", "component2-YbymL2g", "()J", "component2", "copy-O0kMr_c", "(Landroidx/compose/animation/core/Animatable;J)Landroidx/compose/animation/SizeAnimationModifierNode$AnimData;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/animation/core/Animatable;", "getAnim", "J", "getStartSize-YbymL2g", "setStartSize-ozmzZPI", "(J)V", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class AnimData {
        public static final int $stable = 8;
        private final Animatable<IntSize, AnimationVector2D> anim;
        private long startSize;

        public /* synthetic */ AnimData(Animatable animatable, long j10, h hVar) {
            this(animatable, j10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: copy-O0kMr_c$default, reason: not valid java name */
        public static /* synthetic */ AnimData m73copyO0kMr_c$default(AnimData animData, Animatable animatable, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                animatable = animData.anim;
            }
            if ((i10 & 2) != 0) {
                j10 = animData.startSize;
            }
            return animData.m75copyO0kMr_c(animatable, j10);
        }

        public final Animatable<IntSize, AnimationVector2D> component1() {
            return this.anim;
        }

        /* JADX INFO: renamed from: component2-YbymL2g, reason: not valid java name and from getter */
        public final long getStartSize() {
            return this.startSize;
        }

        /* JADX INFO: renamed from: copy-O0kMr_c, reason: not valid java name */
        public final AnimData m75copyO0kMr_c(Animatable<IntSize, AnimationVector2D> anim, long startSize) {
            return new AnimData(anim, startSize, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AnimData)) {
                return false;
            }
            AnimData animData = (AnimData) other;
            return kotlin.jvm.internal.p.a(this.anim, animData.anim) && IntSize.m5842equalsimpl0(this.startSize, animData.startSize);
        }

        public final Animatable<IntSize, AnimationVector2D> getAnim() {
            return this.anim;
        }

        /* JADX INFO: renamed from: getStartSize-YbymL2g, reason: not valid java name */
        public final long m76getStartSizeYbymL2g() {
            return this.startSize;
        }

        public int hashCode() {
            return IntSize.m5845hashCodeimpl(this.startSize) + (this.anim.hashCode() * 31);
        }

        /* JADX INFO: renamed from: setStartSize-ozmzZPI, reason: not valid java name */
        public final void m77setStartSizeozmzZPI(long j10) {
            this.startSize = j10;
        }

        public String toString() {
            return "AnimData(anim=" + this.anim + ", startSize=" + ((Object) IntSize.m5847toStringimpl(this.startSize)) + ')';
        }

        private AnimData(Animatable<IntSize, AnimationVector2D> animatable, long j10) {
            this.anim = animatable;
            this.startSize = j10;
        }
    }

    public /* synthetic */ SizeAnimationModifierNode(AnimationSpec animationSpec, p pVar, int i10, h hVar) {
        this(animationSpec, (i10 & 2) != 0 ? null : pVar);
    }

    /* JADX INFO: renamed from: setLookaheadConstraints-BRTryo0, reason: not valid java name */
    private final void m70setLookaheadConstraintsBRTryo0(long j10) {
        this.lookaheadConstraints = j10;
        this.lookaheadConstraintsAvailable = true;
    }

    /* JADX INFO: renamed from: targetConstraints-ZezNO4M, reason: not valid java name */
    private final long m71targetConstraintsZezNO4M(long j10) {
        return this.lookaheadConstraintsAvailable ? this.lookaheadConstraints : j10;
    }

    /* JADX INFO: renamed from: animateTo-mzRDjE0, reason: not valid java name */
    public final long m72animateTomzRDjE0(long targetSize) {
        AnimData animData = getAnimData();
        h hVar = null;
        if (animData == null) {
            animData = new AnimData(new Animatable(IntSize.m5836boximpl(targetSize), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), IntSize.m5836boximpl(IntSizeKt.IntSize(1, 1)), null, 8, null), targetSize, hVar);
        } else if (!IntSize.m5842equalsimpl0(targetSize, animData.getAnim().getTargetValue().getPackedValue())) {
            animData.m77setStartSizeozmzZPI(animData.getAnim().getValue().getPackedValue());
            m0.p(getCoroutineScope(), null, 0, new SizeAnimationModifierNode$animateTo$data$1$1(animData, targetSize, this, null), 3);
        }
        setAnimData(animData);
        return animData.getAnim().getValue().getPackedValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AnimData getAnimData() {
        return (AnimData) this.animData.getValue();
    }

    public final AnimationSpec<IntSize> getAnimationSpec() {
        return this.animationSpec;
    }

    public final p<IntSize, IntSize, t0> getListener() {
        return this.listener;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0;
        if (measureScope.isLookingAhead()) {
            m70setLookaheadConstraintsBRTryo0(j10);
            placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        } else {
            placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(m71targetConstraintsZezNO4M(j10));
        }
        long jIntSize = IntSizeKt.IntSize(placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight());
        if (measureScope.isLookingAhead()) {
            this.lookaheadSize = jIntSize;
        } else {
            if (AnimationModifierKt.m33isValidozmzZPI(this.lookaheadSize)) {
                jIntSize = this.lookaheadSize;
            }
            jIntSize = ConstraintsKt.m5657constrain4WqzIAM(j10, m72animateTomzRDjE0(jIntSize));
        }
        return MeasureScope.CC.q(measureScope, IntSize.m5844getWidthimpl(jIntSize), IntSize.m5843getHeightimpl(jIntSize), null, new SizeAnimationModifierNode$measure$2(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        this.lookaheadSize = AnimationModifierKt.getInvalidSize();
        this.lookaheadConstraintsAvailable = false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        super.onReset();
        setAnimData(null);
    }

    public final void setAnimData(AnimData animData) {
        this.animData.setValue(animData);
    }

    public final void setAnimationSpec(AnimationSpec<IntSize> animationSpec) {
        this.animationSpec = animationSpec;
    }

    public final void setListener(p<? super IntSize, ? super IntSize, t0> pVar) {
        this.listener = pVar;
    }

    public SizeAnimationModifierNode(AnimationSpec<IntSize> animationSpec, p<? super IntSize, ? super IntSize, t0> pVar) {
        this.animationSpec = animationSpec;
        this.listener = pVar;
        this.lookaheadSize = AnimationModifierKt.getInvalidSize();
        this.lookaheadConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
        this.animData = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }
}
