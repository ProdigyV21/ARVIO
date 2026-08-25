package androidx.compose.material3;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0005HÂ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0014\u001a\u00020\u0010*\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÖ\u0003¢\u0006\u0004\b \u0010!R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010#¨\u0006$"}, d2 = {"Landroidx/compose/material3/ClockDialModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/material3/ClockDialNode;", "Landroidx/compose/material3/TimePickerState;", "state", "", "autoSwitchToMinute", "<init>", "(Landroidx/compose/material3/TimePickerState;Z)V", "component1", "()Landroidx/compose/material3/TimePickerState;", "component2", "()Z", "create", "()Landroidx/compose/material3/ClockDialNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/material3/ClockDialNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "copy", "(Landroidx/compose/material3/TimePickerState;Z)Landroidx/compose/material3/ClockDialModifier;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/material3/TimePickerState;", "Z", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ClockDialModifier extends ModifierNodeElement<ClockDialNode> {
    public static final int $stable = 0;
    private final boolean autoSwitchToMinute;
    private final TimePickerState state;

    public ClockDialModifier(TimePickerState timePickerState, boolean z) {
        this.state = timePickerState;
        this.autoSwitchToMinute = z;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final TimePickerState getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getAutoSwitchToMinute() {
        return this.autoSwitchToMinute;
    }

    public static /* synthetic */ ClockDialModifier copy$default(ClockDialModifier clockDialModifier, TimePickerState timePickerState, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            timePickerState = clockDialModifier.state;
        }
        if ((i10 & 2) != 0) {
            z = clockDialModifier.autoSwitchToMinute;
        }
        return clockDialModifier.copy(timePickerState, z);
    }

    public final ClockDialModifier copy(TimePickerState state, boolean autoSwitchToMinute) {
        return new ClockDialModifier(state, autoSwitchToMinute);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClockDialModifier)) {
            return false;
        }
        ClockDialModifier clockDialModifier = (ClockDialModifier) other;
        return p.a(this.state, clockDialModifier.state) && this.autoSwitchToMinute == clockDialModifier.autoSwitchToMinute;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return (this.state.hashCode() * 31) + (this.autoSwitchToMinute ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ClockDialModifier(state=");
        sb2.append(this.state);
        sb2.append(", autoSwitchToMinute=");
        return a0.c.r(sb2, this.autoSwitchToMinute, ')');
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public ClockDialNode create() {
        return new ClockDialNode(this.state, this.autoSwitchToMinute);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(ClockDialNode node) {
        node.updateNode(this.state, this.autoSwitchToMinute);
    }
}
