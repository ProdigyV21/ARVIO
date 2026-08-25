package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.util.ArrayList;
import java.util.List;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ$\u0010\u0017\u001a\u00020\r*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0019R \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\"\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/material/ripple/StateLayer;", "", "", "bounded", "Landroidx/compose/runtime/State;", "Landroidx/compose/material/ripple/RippleAlpha;", "rippleAlpha", "<init>", "(ZLandroidx/compose/runtime/State;)V", "Landroidx/compose/foundation/interaction/Interaction;", "interaction", "Lka/k0;", "scope", "Lx6/t0;", "handleInteraction", "(Landroidx/compose/foundation/interaction/Interaction;Lka/k0;)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Dp;", "radius", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawStateLayer-H2RKhps", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJ)V", "drawStateLayer", "Z", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "animatedAlpha", "Landroidx/compose/animation/core/Animatable;", "", "interactions", "Ljava/util/List;", "currentInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StateLayer {
    private final boolean bounded;
    private Interaction currentInteraction;
    private final State<RippleAlpha> rippleAlpha;
    private final Animatable<Float, AnimationVector1D> animatedAlpha = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private final List<Interaction> interactions = new ArrayList();

    /* JADX INFO: renamed from: androidx.compose.material.ripple.StateLayer$handleInteraction$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.material.ripple.StateLayer$handleInteraction$1", f = "Ripple.kt", l = {299}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ AnimationSpec<Float> $incomingAnimationSpec;
        final /* synthetic */ float $targetAlpha;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(float f10, AnimationSpec<Float> animationSpec, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$targetAlpha = f10;
            this.$incomingAnimationSpec = animationSpec;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return StateLayer.this.new AnonymousClass1(this.$targetAlpha, this.$incomingAnimationSpec, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                Animatable animatable = StateLayer.this.animatedAlpha;
                Float f10 = new Float(this.$targetAlpha);
                AnimationSpec<Float> animationSpec = this.$incomingAnimationSpec;
                this.label = 1;
                Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, animationSpec, null, null, this, 12, null);
                a aVar = a.f15033i;
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material.ripple.StateLayer$handleInteraction$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.material.ripple.StateLayer$handleInteraction$2", f = "Ripple.kt", l = {305}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ AnimationSpec<Float> $outgoingAnimationSpec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(AnimationSpec<Float> animationSpec, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$outgoingAnimationSpec = animationSpec;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return StateLayer.this.new AnonymousClass2(this.$outgoingAnimationSpec, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                Animatable animatable = StateLayer.this.animatedAlpha;
                Float f10 = new Float(0.0f);
                AnimationSpec<Float> animationSpec = this.$outgoingAnimationSpec;
                this.label = 1;
                Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, animationSpec, null, null, this, 12, null);
                a aVar = a.f15033i;
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
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public StateLayer(boolean z, State<RippleAlpha> state) {
        this.bounded = z;
        this.rippleAlpha = state;
    }

    /* JADX INFO: renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m1211drawStateLayerH2RKhps(DrawScope drawScope, float f10, long j10) {
        DrawScope drawScope2;
        float fMo285toPx0680j_4;
        if (Float.isNaN(f10)) {
            drawScope2 = drawScope;
            fMo285toPx0680j_4 = RippleAnimationKt.m1203getRippleEndRadiuscSwnlzA(drawScope2, this.bounded, drawScope.mo3916getSizeNHjbRc());
        } else {
            drawScope2 = drawScope;
            fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(f10);
        }
        float f11 = fMo285toPx0680j_4;
        float fFloatValue = this.animatedAlpha.getValue().floatValue();
        if (fFloatValue > 0.0f) {
            long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(j10, fFloatValue, 0.0f, 0.0f, 0.0f, 14, null);
            if (!this.bounded) {
                androidx.compose.ui.graphics.drawscope.c.x(drawScope, jM3471copywmQWz5c$default, f11, 0L, 0.0f, null, null, 0, 124, null);
                return;
            }
            float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope2.mo3916getSizeNHjbRc());
            float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope2.mo3916getSizeNHjbRc());
            int iM3461getIntersectrtfAjoo = ClipOp.INSTANCE.m3461getIntersectrtfAjoo();
            DrawContext drawContext = drawScope2.getDrawContext();
            long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
            drawContext.getCanvas().save();
            drawContext.getTransform().mo3925clipRectN_I0leg(0.0f, 0.0f, fM3305getWidthimpl, fM3302getHeightimpl, iM3461getIntersectrtfAjoo);
            androidx.compose.ui.graphics.drawscope.c.x(drawScope2, jM3471copywmQWz5c$default, f11, 0L, 0.0f, null, null, 0, 124, null);
            drawContext.getCanvas().restore();
            drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
        }
    }

    public final void handleInteraction(Interaction interaction, k0 scope) {
        boolean z = interaction instanceof HoverInteraction.Enter;
        if (z) {
            this.interactions.add(interaction);
        } else if (interaction instanceof HoverInteraction.Exit) {
            this.interactions.remove(((HoverInteraction.Exit) interaction).getEnter());
        } else if (interaction instanceof FocusInteraction.Focus) {
            this.interactions.add(interaction);
        } else if (interaction instanceof FocusInteraction.Unfocus) {
            this.interactions.remove(((FocusInteraction.Unfocus) interaction).getFocus());
        } else if (interaction instanceof DragInteraction.Start) {
            this.interactions.add(interaction);
        } else if (interaction instanceof DragInteraction.Stop) {
            this.interactions.remove(((DragInteraction.Stop) interaction).getStart());
        } else if (!(interaction instanceof DragInteraction.Cancel)) {
            return;
        } else {
            this.interactions.remove(((DragInteraction.Cancel) interaction).getStart());
        }
        Interaction interaction2 = (Interaction) x.y0(this.interactions);
        if (kotlin.jvm.internal.p.a(this.currentInteraction, interaction2)) {
            return;
        }
        if (interaction2 != null) {
            m0.p(scope, null, 0, new AnonymousClass1(z ? this.rippleAlpha.getValue().getHoveredAlpha() : interaction instanceof FocusInteraction.Focus ? this.rippleAlpha.getValue().getFocusedAlpha() : interaction instanceof DragInteraction.Start ? this.rippleAlpha.getValue().getDraggedAlpha() : 0.0f, RippleKt.incomingStateLayerAnimationSpecFor(interaction2), null), 3);
        } else {
            m0.p(scope, null, 0, new AnonymousClass2(RippleKt.outgoingStateLayerAnimationSpecFor(this.currentInteraction), null), 3);
        }
        this.currentInteraction = interaction2;
    }
}
