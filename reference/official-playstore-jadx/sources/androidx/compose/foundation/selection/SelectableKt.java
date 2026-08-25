package androidx.compose.foundation.selection;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import kotlin.Metadata;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a@\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001aR\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/Modifier;", "", "selected", "enabled", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "selectable-XHw0xAI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "selectable", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/Indication;", "indication", "selectable-O2vRcR0", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLandroidx/compose/ui/semantics/Role;Lr7/a;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectableKt {
    /* JADX INFO: renamed from: selectable-O2vRcR0, reason: not valid java name */
    public static final Modifier m768selectableO2vRcR0(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z5, Role role, a<t0> aVar) {
        return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new SelectableKt$selectableO2vRcR0$$inlined$debugInspectorInfo$1(z, mutableInteractionSource, indication, z5, role, aVar) : InspectableValueKt.getNoInspectorInfo(), SemanticsModifierKt.semantics$default(ClickableKt.m212clickableO2vRcR0$default(Modifier.INSTANCE, mutableInteractionSource, indication, z5, null, role, aVar, 8, null), false, new SelectableKt$selectable$4$1(z), 1, null));
    }

    /* JADX INFO: renamed from: selectable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m769selectableO2vRcR0$default(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z5, Role role, a aVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z5 = true;
        }
        boolean z10 = z5;
        if ((i10 & 16) != 0) {
            role = null;
        }
        return m768selectableO2vRcR0(modifier, z, mutableInteractionSource, indication, z10, role, aVar);
    }

    /* JADX INFO: renamed from: selectable-XHw0xAI, reason: not valid java name */
    public static final Modifier m770selectableXHw0xAI(Modifier modifier, boolean z, boolean z5, Role role, a<t0> aVar) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new SelectableKt$selectableXHw0xAI$$inlined$debugInspectorInfo$1(z, z5, role, aVar) : InspectableValueKt.getNoInspectorInfo(), new SelectableKt$selectable$2(z, z5, role, aVar));
    }

    /* JADX INFO: renamed from: selectable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m771selectableXHw0xAI$default(Modifier modifier, boolean z, boolean z5, Role role, a aVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        if ((i10 & 4) != 0) {
            role = null;
        }
        return m770selectableXHw0xAI(modifier, z, z5, role, aVar);
    }
}
