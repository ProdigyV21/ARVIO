package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ%\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0001¢\u0006\u0004\b\u0015\u0010\u0011J\u001a\u0010\u0018\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b \u0010\u001fR\u001d\u0010\u0005\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b!\u0010\u001fR\u001d\u0010\u0006\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\"\u0010\u001fR\u001d\u0010\u0007\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b#\u0010\u001fR\u001d\u0010\b\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\b\u0010\u001d\u001a\u0004\b$\u0010\u001f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006'²\u0006\u0010\u0010&\u001a\u0004\u0018\u00010%8\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/material3/SelectableChipElevation;", "", "Landroidx/compose/ui/unit/Dp;", "elevation", "pressedElevation", "focusedElevation", "hoveredElevation", "draggedElevation", "disabledElevation", "<init>", "(FFFFFFLkotlin/jvm/internal/h;)V", "", "enabled", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/runtime/State;", "animateElevation", "(ZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "tonalElevation-u2uoSUM$material3_release", "(Z)F", "tonalElevation", "shadowElevation$material3_release", "shadowElevation", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "F", "getElevation-D9Ej5fM", "()F", "getPressedElevation-D9Ej5fM", "getFocusedElevation-D9Ej5fM", "getHoveredElevation-D9Ej5fM", "getDraggedElevation-D9Ej5fM", "getDisabledElevation-D9Ej5fM", "Landroidx/compose/foundation/interaction/Interaction;", "lastInteraction", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectableChipElevation {
    public static final int $stable = 0;
    private final float disabledElevation;
    private final float draggedElevation;
    private final float elevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ SelectableChipElevation(float f10, float f11, float f12, float f13, float f14, float f15, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13, f14, f15);
    }

    private final State<Dp> animateElevation(boolean z, InteractionSource interactionSource, Composer composer, int i10) {
        Animatable animatable;
        composer.startReplaceableGroup(664514136);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(664514136, i10, -1, "androidx.compose.material3.SelectableChipElevation.animateElevation (Chip.kt:2259)");
        }
        composer.startReplaceableGroup(-699454716);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateListOf();
            composer.updateRememberedValue(objRememberedValue);
        }
        SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
        Object objL = androidx.compose.foundation.c.l(composer, -699454638);
        if (objL == companion.getEmpty()) {
            objL = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composer.updateRememberedValue(objL);
        }
        MutableState mutableState = (MutableState) objL;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-699454548);
        boolean z5 = true;
        boolean z10 = (((i10 & 112) ^ 48) > 32 && composer.changed(interactionSource)) || (i10 & 48) == 32;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z10 || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new SelectableChipElevation$animateElevation$1$1(interactionSource, snapshotStateList, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(interactionSource, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue2, composer, (i10 >> 3) & 14);
        Interaction interaction = (Interaction) x.y0(snapshotStateList);
        float f10 = !z ? this.disabledElevation : interaction instanceof PressInteraction.Press ? this.pressedElevation : interaction instanceof HoverInteraction.Enter ? this.hoveredElevation : interaction instanceof FocusInteraction.Focus ? this.focusedElevation : interaction instanceof DragInteraction.Start ? this.draggedElevation : this.elevation;
        composer.startReplaceableGroup(-699452563);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            Object animatable2 = new Animatable(Dp.m5676boximpl(f10), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), null, null, 12, null);
            composer.updateRememberedValue(animatable2);
            objRememberedValue3 = animatable2;
        }
        Animatable animatable3 = (Animatable) objRememberedValue3;
        composer.endReplaceableGroup();
        Dp dpM5676boximpl = Dp.m5676boximpl(f10);
        composer.startReplaceableGroup(-699452479);
        boolean zChangedInstance = composer.changedInstance(animatable3) | composer.changed(f10);
        if ((((i10 & 14) ^ 6) <= 4 || !composer.changed(z)) && (i10 & 6) != 4) {
            z5 = false;
        }
        boolean zChangedInstance2 = zChangedInstance | z5 | composer.changedInstance(interaction);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue4 == companion.getEmpty()) {
            animatable = animatable3;
            Object selectableChipElevation$animateElevation$2$1 = new SelectableChipElevation$animateElevation$2$1(animatable, f10, z, interaction, mutableState, null);
            composer.updateRememberedValue(selectableChipElevation$animateElevation$2$1);
            objRememberedValue4 = selectableChipElevation$animateElevation$2$1;
        } else {
            animatable = animatable3;
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(dpM5676boximpl, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue4, composer, 0);
        State<Dp> stateAsState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Interaction animateElevation$lambda$2(MutableState<Interaction> mutableState) {
        return mutableState.getValue();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof SelectableChipElevation)) {
            return false;
        }
        SelectableChipElevation selectableChipElevation = (SelectableChipElevation) other;
        return Dp.m5683equalsimpl0(this.elevation, selectableChipElevation.elevation) && Dp.m5683equalsimpl0(this.pressedElevation, selectableChipElevation.pressedElevation) && Dp.m5683equalsimpl0(this.focusedElevation, selectableChipElevation.focusedElevation) && Dp.m5683equalsimpl0(this.hoveredElevation, selectableChipElevation.hoveredElevation) && Dp.m5683equalsimpl0(this.disabledElevation, selectableChipElevation.disabledElevation);
    }

    /* JADX INFO: renamed from: getDisabledElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getDisabledElevation() {
        return this.disabledElevation;
    }

    /* JADX INFO: renamed from: getDraggedElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getDraggedElevation() {
        return this.draggedElevation;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getElevation() {
        return this.elevation;
    }

    /* JADX INFO: renamed from: getFocusedElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getFocusedElevation() {
        return this.focusedElevation;
    }

    /* JADX INFO: renamed from: getHoveredElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getHoveredElevation() {
        return this.hoveredElevation;
    }

    /* JADX INFO: renamed from: getPressedElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getPressedElevation() {
        return this.pressedElevation;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.disabledElevation) + a0.c.C(this.hoveredElevation, a0.c.C(this.focusedElevation, a0.c.C(this.pressedElevation, Dp.m5684hashCodeimpl(this.elevation) * 31, 31), 31), 31);
    }

    public final State<Dp> shadowElevation$material3_release(boolean z, InteractionSource interactionSource, Composer composer, int i10) {
        composer.startReplaceableGroup(-1888175651);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1888175651, i10, -1, "androidx.compose.material3.SelectableChipElevation.shadowElevation (Chip.kt:2251)");
        }
        State<Dp> stateAnimateElevation = animateElevation(z, interactionSource, composer, i10 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateElevation;
    }

    /* JADX INFO: renamed from: tonalElevation-u2uoSUM$material3_release, reason: not valid java name */
    public final float m1859tonalElevationu2uoSUM$material3_release(boolean enabled) {
        return enabled ? this.elevation : this.disabledElevation;
    }

    private SelectableChipElevation(float f10, float f11, float f12, float f13, float f14, float f15) {
        this.elevation = f10;
        this.pressedElevation = f11;
        this.focusedElevation = f12;
        this.hoveredElevation = f13;
        this.draggedElevation = f14;
        this.disabledElevation = f15;
    }
}
