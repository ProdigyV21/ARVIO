package androidx.compose.material.ripple;

import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.util.Iterator;
import java.util.Map;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0012\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\t\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0014\u001a\u00020\u000f*\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010\u001fJ\u000f\u0010!\u001a\u00020\u000fH\u0016¢\u0006\u0004\b!\u0010\u001fR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u001a\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0006\u0010#R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010$R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010$R \u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020&0%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Landroidx/compose/material/ripple/CommonRippleIndicationInstance;", "Landroidx/compose/material/ripple/RippleIndicationInstance;", "Landroidx/compose/runtime/RememberObserver;", "", "bounded", "Landroidx/compose/ui/unit/Dp;", "radius", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/material/ripple/RippleAlpha;", "rippleAlpha", "<init>", "(ZFLandroidx/compose/runtime/State;Landroidx/compose/runtime/State;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "drawRipples-4WTKRHQ", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;J)V", "drawRipples", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawIndication", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "interaction", "Lka/k0;", "scope", "addRipple", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;Lka/k0;)V", "removeRipple", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)V", "onRemembered", "()V", "onForgotten", "onAbandoned", "Z", "F", "Landroidx/compose/runtime/State;", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "Landroidx/compose/material/ripple/RippleAnimation;", "ripples", "Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CommonRippleIndicationInstance extends RippleIndicationInstance implements RememberObserver {
    public static final int $stable = 0;
    private final boolean bounded;
    private final State<Color> color;
    private final float radius;
    private final State<RippleAlpha> rippleAlpha;
    private final SnapshotStateMap<PressInteraction.Press, RippleAnimation> ripples;

    /* JADX INFO: renamed from: androidx.compose.material.ripple.CommonRippleIndicationInstance$addRipple$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.material.ripple.CommonRippleIndicationInstance$addRipple$2", f = "CommonRipple.kt", l = {87}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ PressInteraction.Press $interaction;
        final /* synthetic */ RippleAnimation $rippleAnimation;
        int label;
        final /* synthetic */ CommonRippleIndicationInstance this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(RippleAnimation rippleAnimation, CommonRippleIndicationInstance commonRippleIndicationInstance, PressInteraction.Press press, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$rippleAnimation = rippleAnimation;
            this.this$0 = commonRippleIndicationInstance;
            this.$interaction = press;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$rippleAnimation, this.this$0, this.$interaction, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    RippleAnimation rippleAnimation = this.$rippleAnimation;
                    this.label = 1;
                    Object objAnimate = rippleAnimation.animate(this);
                    a aVar = a.f15033i;
                    if (objAnimate == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                this.this$0.ripples.remove(this.$interaction);
                return t0.f22605a;
            } catch (Throwable th) {
                this.this$0.ripples.remove(this.$interaction);
                throw th;
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public /* synthetic */ CommonRippleIndicationInstance(boolean z, float f10, State state, State state2, h hVar) {
        this(z, f10, state, state2);
    }

    /* JADX INFO: renamed from: drawRipples-4WTKRHQ, reason: not valid java name */
    private final void m1200drawRipples4WTKRHQ(DrawScope drawScope, long j10) {
        long j11;
        Iterator<Map.Entry<PressInteraction.Press, RippleAnimation>> it = this.ripples.entrySet().iterator();
        while (it.hasNext()) {
            RippleAnimation value = it.next().getValue();
            float pressedAlpha = this.rippleAlpha.getValue().getPressedAlpha();
            if (pressedAlpha == 0.0f) {
                j11 = j10;
            } else {
                j11 = j10;
                value.m1202draw4WTKRHQ(drawScope, Color.m3471copywmQWz5c$default(j11, pressedAlpha, 0.0f, 0.0f, 0.0f, 14, null));
            }
            j10 = j11;
        }
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public void addRipple(PressInteraction.Press interaction, k0 scope) {
        Iterator<Map.Entry<PressInteraction.Press, RippleAnimation>> it = this.ripples.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().finish();
        }
        RippleAnimation rippleAnimation = new RippleAnimation(this.bounded ? Offset.m3225boximpl(interaction.getPressPosition()) : null, this.radius, this.bounded, null);
        this.ripples.put(interaction, rippleAnimation);
        m0.p(scope, null, 0, new AnonymousClass2(rippleAnimation, this, interaction, null), 3);
    }

    @Override // androidx.compose.foundation.IndicationInstance
    public void drawIndication(ContentDrawScope contentDrawScope) {
        long jM3482unboximpl = this.color.getValue().m3482unboximpl();
        contentDrawScope.drawContent();
        m1207drawStateLayerH2RKhps(contentDrawScope, this.radius, jM3482unboximpl);
        m1200drawRipples4WTKRHQ(contentDrawScope, jM3482unboximpl);
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        this.ripples.clear();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        this.ripples.clear();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
    }

    @Override // androidx.compose.material.ripple.RippleIndicationInstance
    public void removeRipple(PressInteraction.Press interaction) {
        RippleAnimation rippleAnimation = this.ripples.get(interaction);
        if (rippleAnimation != null) {
            rippleAnimation.finish();
        }
    }

    private CommonRippleIndicationInstance(boolean z, float f10, State<Color> state, State<RippleAlpha> state2) {
        super(z, state2);
        this.bounded = z;
        this.radius = f10;
        this.color = state;
        this.rippleAlpha = state2;
        this.ripples = SnapshotStateKt.mutableStateMapOf();
    }
}
