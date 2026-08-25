package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\n\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0014\u001a\u00020\u0010*\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ:\u0010\u001c\u001a\u00020\u00002\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u001eHÖ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b!\u0010\u0019J\u001a\u0010$\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\"HÖ\u0003¢\u0006\u0004\b$\u0010%R#\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00038\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b'\u0010\u0017R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010(\u001a\u0004\b)\u0010\u0019R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010*\u001a\u0004\b+\u0010\u001b¨\u0006,"}, d2 = {"Landroidx/compose/material3/TabIndicatorModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/material3/TabIndicatorOffsetNode;", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/material3/TabPosition;", "tabPositionsState", "", "selectedTabIndex", "", "followContentSize", "<init>", "(Landroidx/compose/runtime/State;IZ)V", "create", "()Landroidx/compose/material3/TabIndicatorOffsetNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/material3/TabIndicatorOffsetNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Landroidx/compose/runtime/State;", "component2", "()I", "component3", "()Z", "copy", "(Landroidx/compose/runtime/State;IZ)Landroidx/compose/material3/TabIndicatorModifier;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/runtime/State;", "getTabPositionsState", "I", "getSelectedTabIndex", "Z", "getFollowContentSize", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class TabIndicatorModifier extends ModifierNodeElement<TabIndicatorOffsetNode> {
    public static final int $stable = 0;
    private final boolean followContentSize;
    private final int selectedTabIndex;
    private final State<List<TabPosition>> tabPositionsState;

    /* JADX WARN: Multi-variable type inference failed */
    public TabIndicatorModifier(State<? extends List<TabPosition>> state, int i10, boolean z) {
        this.tabPositionsState = state;
        this.selectedTabIndex = i10;
        this.followContentSize = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TabIndicatorModifier copy$default(TabIndicatorModifier tabIndicatorModifier, State state, int i10, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            state = tabIndicatorModifier.tabPositionsState;
        }
        if ((i11 & 2) != 0) {
            i10 = tabIndicatorModifier.selectedTabIndex;
        }
        if ((i11 & 4) != 0) {
            z = tabIndicatorModifier.followContentSize;
        }
        return tabIndicatorModifier.copy(state, i10, z);
    }

    public final State<List<TabPosition>> component1() {
        return this.tabPositionsState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getFollowContentSize() {
        return this.followContentSize;
    }

    public final TabIndicatorModifier copy(State<? extends List<TabPosition>> tabPositionsState, int selectedTabIndex, boolean followContentSize) {
        return new TabIndicatorModifier(tabPositionsState, selectedTabIndex, followContentSize);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TabIndicatorModifier)) {
            return false;
        }
        TabIndicatorModifier tabIndicatorModifier = (TabIndicatorModifier) other;
        return p.a(this.tabPositionsState, tabIndicatorModifier.tabPositionsState) && this.selectedTabIndex == tabIndicatorModifier.selectedTabIndex && this.followContentSize == tabIndicatorModifier.followContentSize;
    }

    public final boolean getFollowContentSize() {
        return this.followContentSize;
    }

    public final int getSelectedTabIndex() {
        return this.selectedTabIndex;
    }

    public final State<List<TabPosition>> getTabPositionsState() {
        return this.tabPositionsState;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return (((this.tabPositionsState.hashCode() * 31) + this.selectedTabIndex) * 31) + (this.followContentSize ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TabIndicatorModifier(tabPositionsState=");
        sb2.append(this.tabPositionsState);
        sb2.append(", selectedTabIndex=");
        sb2.append(this.selectedTabIndex);
        sb2.append(", followContentSize=");
        return a0.c.r(sb2, this.followContentSize, ')');
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public TabIndicatorOffsetNode create() {
        return new TabIndicatorOffsetNode(this.tabPositionsState, this.selectedTabIndex, this.followContentSize);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(TabIndicatorOffsetNode node) {
        node.setTabPositionsState(this.tabPositionsState);
        node.setSelectedTabIndex(this.selectedTabIndex);
        node.setFollowContentSize(this.followContentSize);
    }
}
