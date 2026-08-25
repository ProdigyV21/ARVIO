package androidx.compose.material.ripple;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b!\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJF\u0010\u0015\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006H'ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0018\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006 "}, d2 = {"Landroidx/compose/material/ripple/Ripple;", "Landroidx/compose/foundation/Indication;", "", "bounded", "Landroidx/compose/ui/unit/Dp;", "radius", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "<init>", "(ZFLandroidx/compose/runtime/State;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/foundation/IndicationInstance;", "rememberUpdatedInstance", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/IndicationInstance;", "Landroidx/compose/material/ripple/RippleAlpha;", "rippleAlpha", "Landroidx/compose/material/ripple/RippleIndicationInstance;", "rememberUpdatedRippleInstance-942rkJo", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/runtime/State;Landroidx/compose/runtime/State;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/ripple/RippleIndicationInstance;", "rememberUpdatedRippleInstance", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Z", "F", "Landroidx/compose/runtime/State;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Ripple implements Indication {
    public static final int $stable = 0;
    private final boolean bounded;
    private final State<Color> color;
    private final float radius;

    /* JADX INFO: renamed from: androidx.compose.material.ripple.Ripple$rememberUpdatedInstance$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.material.ripple.Ripple$rememberUpdatedInstance$1", f = "Ripple.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ RippleIndicationInstance $instance;
        final /* synthetic */ InteractionSource $interactionSource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(InteractionSource interactionSource, RippleIndicationInstance rippleIndicationInstance, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$interactionSource = interactionSource;
            this.$instance = rippleIndicationInstance;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$interactionSource, this.$instance, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                final k0 k0Var = (k0) this.L$0;
                na.j<Interaction> interactions = this.$interactionSource.getInteractions();
                final RippleIndicationInstance rippleIndicationInstance = this.$instance;
                k kVar = new k() { // from class: androidx.compose.material.ripple.Ripple.rememberUpdatedInstance.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit((Interaction) obj2, (d<? super t0>) dVar);
                    }

                    public final Object emit(Interaction interaction, d<? super t0> dVar) {
                        if (interaction instanceof PressInteraction.Press) {
                            rippleIndicationInstance.addRipple((PressInteraction.Press) interaction, k0Var);
                        } else if (interaction instanceof PressInteraction.Release) {
                            rippleIndicationInstance.removeRipple(((PressInteraction.Release) interaction).getPress());
                        } else if (interaction instanceof PressInteraction.Cancel) {
                            rippleIndicationInstance.removeRipple(((PressInteraction.Cancel) interaction).getPress());
                        } else {
                            rippleIndicationInstance.updateStateLayer$material_ripple_release(interaction, k0Var);
                        }
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = interactions.collect(kVar, this);
                a aVar = a.f15033i;
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public /* synthetic */ Ripple(boolean z, float f10, State state, h hVar) {
        this(z, f10, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Ripple)) {
            return false;
        }
        Ripple ripple = (Ripple) other;
        return this.bounded == ripple.bounded && Dp.m5683equalsimpl0(this.radius, ripple.radius) && kotlin.jvm.internal.p.a(this.color, ripple.color);
    }

    public int hashCode() {
        return this.color.hashCode() + a0.c.C(this.radius, (this.bounded ? 1231 : 1237) * 31, 31);
    }

    @Override // androidx.compose.foundation.Indication
    public final IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(988743187);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(988743187, i10, -1, "androidx.compose.material.ripple.Ripple.rememberUpdatedInstance (Ripple.kt:115)");
        }
        RippleTheme rippleTheme = (RippleTheme) composer.consume(RippleThemeKt.getLocalRippleTheme());
        composer.startReplaceableGroup(-1524341038);
        long jM3482unboximpl = this.color.getValue().m3482unboximpl() != Color.INSTANCE.m3508getUnspecified0d7_KjU() ? this.color.getValue().m3482unboximpl() : rippleTheme.mo1201defaultColorWaAFU9c(composer, 0);
        composer.endReplaceableGroup();
        RippleIndicationInstance rippleIndicationInstanceMo1199rememberUpdatedRippleInstance942rkJo = mo1199rememberUpdatedRippleInstance942rkJo(interactionSource, this.bounded, this.radius, SnapshotStateKt.rememberUpdatedState(Color.m3462boximpl(jM3482unboximpl), composer, 0), SnapshotStateKt.rememberUpdatedState(rippleTheme.rippleAlpha(composer, 0), composer, 0), composer, (i10 & 14) | ((i10 << 12) & 458752));
        EffectsKt.LaunchedEffect(rippleIndicationInstanceMo1199rememberUpdatedRippleInstance942rkJo, interactionSource, new AnonymousClass1(interactionSource, rippleIndicationInstanceMo1199rememberUpdatedRippleInstance942rkJo, null), composer, ((i10 << 3) & 112) | 520);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return rippleIndicationInstanceMo1199rememberUpdatedRippleInstance942rkJo;
    }

    /* JADX INFO: renamed from: rememberUpdatedRippleInstance-942rkJo */
    public abstract RippleIndicationInstance mo1199rememberUpdatedRippleInstance942rkJo(InteractionSource interactionSource, boolean z, float f10, State<Color> state, State<RippleAlpha> state2, Composer composer, int i10);

    private Ripple(boolean z, float f10, State<Color> state) {
        this.bounded = z;
        this.radius = f10;
        this.color = state;
    }
}
