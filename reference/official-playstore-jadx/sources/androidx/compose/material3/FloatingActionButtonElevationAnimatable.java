package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u0002*\u0004\u0018\u00010\tH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\rH\u0082@¢\u0006\u0004\b\u000e\u0010\u000fJ3\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0086@ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\tH\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0003\u0010\u0019R\u001c\u0010\u0004\u001a\u00020\u00028\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0004\u0010\u0019R\u001c\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u001c\u0010\u0006\u001a\u00020\u00028\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0006\u0010\u0019R \u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001b0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u001f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/material3/FloatingActionButtonElevationAnimatable;", "", "Landroidx/compose/ui/unit/Dp;", "defaultElevation", "pressedElevation", "hoveredElevation", "focusedElevation", "<init>", "(FFFFLkotlin/jvm/internal/h;)V", "Landroidx/compose/foundation/interaction/Interaction;", "calculateTarget-u2uoSUM", "(Landroidx/compose/foundation/interaction/Interaction;)F", "calculateTarget", "Lx6/t0;", "snapElevation", "(Ld7/d;)Ljava/lang/Object;", "updateElevation-lDy3nrA", "(FFFFLd7/d;)Ljava/lang/Object;", "updateElevation", "to", "animateElevation", "(Landroidx/compose/foundation/interaction/Interaction;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/runtime/State;", "asState", "()Landroidx/compose/runtime/State;", "F", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "animatable", "Landroidx/compose/animation/core/Animatable;", "lastTargetInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "targetInteraction", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FloatingActionButtonElevationAnimatable {
    private final Animatable<Dp, AnimationVector1D> animatable;
    private float defaultElevation;
    private float focusedElevation;
    private float hoveredElevation;
    private Interaction lastTargetInteraction;
    private float pressedElevation;
    private Interaction targetInteraction;

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", l = {623}, m = "animateElevation")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.animateElevation(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", l = {609}, m = "snapElevation")
    public static final class C05061 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C05061(d7.d<? super C05061> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.snapElevation(this);
        }
    }

    public /* synthetic */ FloatingActionButtonElevationAnimatable(float f10, float f11, float f12, float f13, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: calculateTarget-u2uoSUM, reason: not valid java name */
    private final float m1585calculateTargetu2uoSUM(Interaction interaction) {
        return interaction instanceof PressInteraction.Press ? this.pressedElevation : interaction instanceof HoverInteraction.Enter ? this.hoveredElevation : interaction instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object snapElevation(d7.d<? super x6.t0> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof androidx.compose.material3.FloatingActionButtonElevationAnimatable.C05061
            if (r0 == 0) goto L13
            r0 = r5
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable.C05061) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1 r0 = new androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r0 = r0.L$0
            androidx.compose.material3.FloatingActionButtonElevationAnimatable r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable) r0
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L29
            goto L62
        L29:
            r5 = move-exception
            goto L69
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L33:
            k2.c.G(r5)
            androidx.compose.foundation.interaction.Interaction r5 = r4.targetInteraction
            float r5 = r4.m1585calculateTargetu2uoSUM(r5)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r4.animatable
            java.lang.Object r1 = r1.getTargetValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.m5692unboximpl()
            boolean r1 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r1, r5)
            if (r1 != 0) goto L6e
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r4.animatable     // Catch: java.lang.Throwable -> L67
            androidx.compose.ui.unit.Dp r5 = androidx.compose.ui.unit.Dp.m5676boximpl(r5)     // Catch: java.lang.Throwable -> L67
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L67
            r0.label = r2     // Catch: java.lang.Throwable -> L67
            java.lang.Object r5 = r1.snapTo(r5, r0)     // Catch: java.lang.Throwable -> L67
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L61
            return r0
        L61:
            r0 = r4
        L62:
            androidx.compose.foundation.interaction.Interaction r5 = r0.targetInteraction
            r0.lastTargetInteraction = r5
            goto L6e
        L67:
            r5 = move-exception
            r0 = r4
        L69:
            androidx.compose.foundation.interaction.Interaction r1 = r0.targetInteraction
            r0.lastTargetInteraction = r1
            throw r5
        L6e:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonElevationAnimatable.snapElevation(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateElevation(androidx.compose.foundation.interaction.Interaction r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.material3.FloatingActionButtonElevationAnimatable.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1 r0 = new androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r5 = r0.L$1
            androidx.compose.foundation.interaction.Interaction r5 = (androidx.compose.foundation.interaction.Interaction) r5
            java.lang.Object r0 = r0.L$0
            androidx.compose.material3.FloatingActionButtonElevationAnimatable r0 = (androidx.compose.material3.FloatingActionButtonElevationAnimatable) r0
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L2d
            goto L69
        L2d:
            r6 = move-exception
            goto L6e
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            k2.c.G(r6)
            float r6 = r4.m1585calculateTargetu2uoSUM(r5)
            r4.targetInteraction = r5
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r4.animatable     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = r1.getTargetValue()     // Catch: java.lang.Throwable -> L65
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1     // Catch: java.lang.Throwable -> L65
            float r1 = r1.m5692unboximpl()     // Catch: java.lang.Throwable -> L65
            boolean r1 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r1, r6)     // Catch: java.lang.Throwable -> L65
            if (r1 != 0) goto L68
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r4.animatable     // Catch: java.lang.Throwable -> L65
            androidx.compose.foundation.interaction.Interaction r3 = r4.lastTargetInteraction     // Catch: java.lang.Throwable -> L65
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L65
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L65
            r0.label = r2     // Catch: java.lang.Throwable -> L65
            java.lang.Object r6 = androidx.compose.material3.ElevationKt.m1549animateElevationrAjV9yQ(r1, r6, r3, r5, r0)     // Catch: java.lang.Throwable -> L65
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L68
            return r0
        L65:
            r6 = move-exception
            r0 = r4
            goto L6e
        L68:
            r0 = r4
        L69:
            r0.lastTargetInteraction = r5
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L6e:
            r0.lastTargetInteraction = r5
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonElevationAnimatable.animateElevation(androidx.compose.foundation.interaction.Interaction, d7.d):java.lang.Object");
    }

    public final State<Dp> asState() {
        return this.animatable.asState();
    }

    /* JADX INFO: renamed from: updateElevation-lDy3nrA, reason: not valid java name */
    public final Object m1586updateElevationlDy3nrA(float f10, float f11, float f12, float f13, d7.d<? super t0> dVar) throws Throwable {
        this.defaultElevation = f10;
        this.pressedElevation = f11;
        this.hoveredElevation = f12;
        this.focusedElevation = f13;
        Object objSnapElevation = snapElevation(dVar);
        return objSnapElevation == e7.a.f15033i ? objSnapElevation : t0.f22605a;
    }

    private FloatingActionButtonElevationAnimatable(float f10, float f11, float f12, float f13) {
        this.defaultElevation = f10;
        this.pressedElevation = f11;
        this.hoveredElevation = f12;
        this.focusedElevation = f13;
        this.animatable = new Animatable<>(Dp.m5676boximpl(f10), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
    }
}
