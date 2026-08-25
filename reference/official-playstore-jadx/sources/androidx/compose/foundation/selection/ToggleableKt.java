package androidx.compose.foundation.selection;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.state.ToggleableStateKt;
import kotlin.Metadata;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aF\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001aX\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a@\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001aR\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/Modifier;", "", "value", "enabled", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "toggleable-XHw0xAI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/semantics/Role;Lr7/l;)Landroidx/compose/ui/Modifier;", "toggleable", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/Indication;", "indication", "toggleable-O2vRcR0", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLandroidx/compose/ui/semantics/Role;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/state/ToggleableState;", "state", "Lkotlin/Function0;", "onClick", "triStateToggleable-XHw0xAI", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/state/ToggleableState;ZLandroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "triStateToggleable", "triStateToggleable-O2vRcR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/state/ToggleableState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLandroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ToggleableKt {
    /* JADX INFO: renamed from: toggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m772toggleableO2vRcR0(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z5, Role role, l<? super Boolean, t0> lVar) {
        MutableInteractionSource mutableInteractionSource2;
        Indication indication2;
        boolean z10;
        Role role2;
        l<? super Boolean, t0> lVar2;
        l noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            z10 = z5;
            role2 = role;
            lVar2 = lVar;
            noInspectorInfo = new ToggleableKt$toggleableO2vRcR0$$inlined$debugInspectorInfo$1(z, mutableInteractionSource2, indication2, z10, role2, lVar2);
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
            indication2 = indication;
            z10 = z5;
            role2 = role;
            lVar2 = lVar;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        l<? super Boolean, t0> lVar3 = lVar2;
        Role role3 = role2;
        boolean z11 = z10;
        Indication indication3 = indication2;
        return InspectableValueKt.inspectableWrapper(modifier, noInspectorInfo, m776triStateToggleableO2vRcR0(Modifier.INSTANCE, ToggleableStateKt.ToggleableState(z), mutableInteractionSource2, indication3, z11, role3, new ToggleableKt$toggleable$4$1(lVar3, z)));
    }

    /* JADX INFO: renamed from: toggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m773toggleableO2vRcR0$default(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z5, Role role, l lVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z5 = true;
        }
        boolean z10 = z5;
        if ((i10 & 16) != 0) {
            role = null;
        }
        return m772toggleableO2vRcR0(modifier, z, mutableInteractionSource, indication, z10, role, lVar);
    }

    /* JADX INFO: renamed from: toggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m774toggleableXHw0xAI(Modifier modifier, boolean z, boolean z5, Role role, l<? super Boolean, t0> lVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ToggleableKt$toggleableXHw0xAI$$inlined$debugInspectorInfo$1(z, z5, role, lVar) : InspectableValueKt.getNoInspectorInfo(), new ToggleableKt$toggleable$2(z, z5, role, lVar));
    }

    /* JADX INFO: renamed from: toggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m775toggleableXHw0xAI$default(Modifier modifier, boolean z, boolean z5, Role role, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        if ((i10 & 4) != 0) {
            role = null;
        }
        return m774toggleableXHw0xAI(modifier, z, z5, role, lVar);
    }

    /* JADX INFO: renamed from: triStateToggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m776triStateToggleableO2vRcR0(Modifier modifier, ToggleableState toggleableState, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, Role role, a<t0> aVar) {
        return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ToggleableKt$triStateToggleableO2vRcR0$$inlined$debugInspectorInfo$1(toggleableState, z, role, mutableInteractionSource, indication, aVar) : InspectableValueKt.getNoInspectorInfo(), SemanticsModifierKt.semantics$default(ClickableKt.m212clickableO2vRcR0$default(Modifier.INSTANCE, mutableInteractionSource, indication, z, null, role, aVar, 8, null), false, new ToggleableKt$triStateToggleable$4$1(toggleableState), 1, null));
    }

    /* JADX INFO: renamed from: triStateToggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m777triStateToggleableO2vRcR0$default(Modifier modifier, ToggleableState toggleableState, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, Role role, a aVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z = true;
        }
        boolean z5 = z;
        if ((i10 & 16) != 0) {
            role = null;
        }
        return m776triStateToggleableO2vRcR0(modifier, toggleableState, mutableInteractionSource, indication, z5, role, aVar);
    }

    /* JADX INFO: renamed from: triStateToggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m778triStateToggleableXHw0xAI(Modifier modifier, ToggleableState toggleableState, boolean z, Role role, a<t0> aVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ToggleableKt$triStateToggleableXHw0xAI$$inlined$debugInspectorInfo$1(toggleableState, z, role, aVar) : InspectableValueKt.getNoInspectorInfo(), new ToggleableKt$triStateToggleable$2(toggleableState, z, role, aVar));
    }

    /* JADX INFO: renamed from: triStateToggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m779triStateToggleableXHw0xAI$default(Modifier modifier, ToggleableState toggleableState, boolean z, Role role, a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        if ((i10 & 4) != 0) {
            role = null;
        }
        return m778triStateToggleableXHw0xAI(modifier, toggleableState, z, role, aVar);
    }
}
