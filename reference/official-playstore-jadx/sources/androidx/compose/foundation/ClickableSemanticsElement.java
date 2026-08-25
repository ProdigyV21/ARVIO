package androidx.compose.foundation;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BK\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\n*\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0006\u0010 R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010!R\u001c\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\"R\u0016\u0010\f\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010!R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\"\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006#"}, d2 = {"Landroidx/compose/foundation/ClickableSemanticsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/ClickableSemanticsNode;", "", "enabled", "Landroidx/compose/ui/semantics/Role;", "role", "", "onLongClickLabel", "Lkotlin/Function0;", "Lx6/t0;", "onLongClick", "onClickLabel", "onClick", "<init>", "(ZLandroidx/compose/ui/semantics/Role;Ljava/lang/String;Lr7/a;Ljava/lang/String;Lr7/a;Lkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/foundation/ClickableSemanticsNode;", "node", "update", "(Landroidx/compose/foundation/ClickableSemanticsNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "Landroidx/compose/ui/semantics/Role;", "Ljava/lang/String;", "Lr7/a;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ClickableSemanticsElement extends ModifierNodeElement<ClickableSemanticsNode> {
    private final boolean enabled;
    private final r7.a<t0> onClick;
    private final String onClickLabel;
    private final r7.a<t0> onLongClick;
    private final String onLongClickLabel;
    private final Role role;

    public /* synthetic */ ClickableSemanticsElement(boolean z, Role role, String str, r7.a aVar, String str2, r7.a aVar2, h hVar) {
        this(z, role, str, aVar, str2, aVar2);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClickableSemanticsElement)) {
            return false;
        }
        ClickableSemanticsElement clickableSemanticsElement = (ClickableSemanticsElement) other;
        return this.enabled == clickableSemanticsElement.enabled && p.a(this.role, clickableSemanticsElement.role) && p.a(this.onLongClickLabel, clickableSemanticsElement.onLongClickLabel) && p.a(this.onLongClick, clickableSemanticsElement.onLongClick) && p.a(this.onClickLabel, clickableSemanticsElement.onClickLabel) && p.a(this.onClick, clickableSemanticsElement.onClick);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int i10 = (this.enabled ? 1231 : 1237) * 31;
        Role role = this.role;
        int iHashCode = (i10 + (role != null ? role.hashCode() : 0)) * 31;
        String str = this.onLongClickLabel;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        r7.a<t0> aVar = this.onLongClick;
        int iHashCode3 = (iHashCode2 + (aVar != null ? aVar.hashCode() : 0)) * 31;
        String str2 = this.onClickLabel;
        return this.onClick.hashCode() + ((iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    private ClickableSemanticsElement(boolean z, Role role, String str, r7.a<t0> aVar, String str2, r7.a<t0> aVar2) {
        this.enabled = z;
        this.role = role;
        this.onLongClickLabel = str;
        this.onLongClick = aVar;
        this.onClickLabel = str2;
        this.onClick = aVar2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public ClickableSemanticsNode create() {
        return new ClickableSemanticsNode(this.enabled, this.onClickLabel, this.role, this.onClick, this.onLongClickLabel, this.onLongClick, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(ClickableSemanticsNode node) {
        node.m226updateUMe6uN4(this.enabled, this.onClickLabel, this.role, this.onClick, this.onLongClickLabel, this.onLongClick);
    }
}
