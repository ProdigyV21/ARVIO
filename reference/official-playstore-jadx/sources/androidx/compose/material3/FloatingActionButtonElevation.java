package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B)\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0003¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0001¢\u0006\u0004\b\u000e\u0010\rJ\u0015\u0010\u0012\u001a\u00020\u0002H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0006\u0010\u001a\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/FloatingActionButtonElevation;", "", "Landroidx/compose/ui/unit/Dp;", "defaultElevation", "pressedElevation", "focusedElevation", "hoveredElevation", "<init>", "(FFFFLkotlin/jvm/internal/h;)V", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/runtime/State;", "animateElevation", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "shadowElevation$material3_release", "shadowElevation", "tonalElevation-D9Ej5fM$material3_release", "()F", "tonalElevation", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "F", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class FloatingActionButtonElevation {
    public static final int $stable = 0;
    private final float defaultElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ FloatingActionButtonElevation(float f10, float f11, float f12, float f13, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13);
    }

    private final State<Dp> animateElevation(InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(-1845106002);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1845106002, i10, -1, "androidx.compose.material3.FloatingActionButtonElevation.animateElevation (FloatingActionButton.kt:497)");
        }
        composer.startReplaceableGroup(1849274698);
        int i11 = i10 & 14;
        int i12 = i11 ^ 6;
        boolean z = (i12 > 4 && composer.changed(interactionSource)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object floatingActionButtonElevationAnimatable = new FloatingActionButtonElevationAnimatable(this.defaultElevation, this.pressedElevation, this.hoveredElevation, this.focusedElevation, null);
            composer.updateRememberedValue(floatingActionButtonElevationAnimatable);
            objRememberedValue = floatingActionButtonElevationAnimatable;
        }
        FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable2 = (FloatingActionButtonElevationAnimatable) objRememberedValue;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(1849275046);
        boolean zChangedInstance = composer.changedInstance(floatingActionButtonElevationAnimatable2) | ((((i10 & 112) ^ 48) > 32 && composer.changed(this)) || (i10 & 48) == 32);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new FloatingActionButtonElevation$animateElevation$1$1(floatingActionButtonElevationAnimatable2, this, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(this, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, (i10 >> 3) & 14);
        composer.startReplaceableGroup(1849275366);
        boolean zChangedInstance2 = composer.changedInstance(floatingActionButtonElevationAnimatable2) | ((i12 > 4 && composer.changed(interactionSource)) || (i10 & 6) == 4);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new FloatingActionButtonElevation$animateElevation$2$1(interactionSource, floatingActionButtonElevationAnimatable2, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(interactionSource, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue3, composer, i11);
        State<Dp> stateAsState = floatingActionButtonElevationAnimatable2.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAsState;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof FloatingActionButtonElevation)) {
            return false;
        }
        FloatingActionButtonElevation floatingActionButtonElevation = (FloatingActionButtonElevation) other;
        if (Dp.m5683equalsimpl0(this.defaultElevation, floatingActionButtonElevation.defaultElevation) && Dp.m5683equalsimpl0(this.pressedElevation, floatingActionButtonElevation.pressedElevation) && Dp.m5683equalsimpl0(this.focusedElevation, floatingActionButtonElevation.focusedElevation)) {
            return Dp.m5683equalsimpl0(this.hoveredElevation, floatingActionButtonElevation.hoveredElevation);
        }
        return false;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.hoveredElevation) + a0.c.C(this.focusedElevation, a0.c.C(this.pressedElevation, Dp.m5684hashCodeimpl(this.defaultElevation) * 31, 31), 31);
    }

    public final State<Dp> shadowElevation$material3_release(InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(-424810125);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-424810125, i10, -1, "androidx.compose.material3.FloatingActionButtonElevation.shadowElevation (FloatingActionButton.kt:488)");
        }
        State<Dp> stateAnimateElevation = animateElevation(interactionSource, composer, i10 & 126);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateElevation;
    }

    /* JADX INFO: renamed from: tonalElevation-D9Ej5fM$material3_release, reason: not valid java name and from getter */
    public final float getDefaultElevation() {
        return this.defaultElevation;
    }

    private FloatingActionButtonElevation(float f10, float f11, float f12, float f13) {
        this.defaultElevation = f10;
        this.pressedElevation = f11;
        this.focusedElevation = f12;
        this.hoveredElevation = f13;
    }
}
