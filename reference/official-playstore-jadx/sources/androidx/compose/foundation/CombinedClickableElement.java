package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Be\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0019\u001a\u00020\f*\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010#R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010$R\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\n\u0010%R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010&R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010$R\u001c\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010&R\u001c\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010&\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006'"}, d2 = {"Landroidx/compose/foundation/CombinedClickableElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/CombinedClickableNodeImpl;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "", "onClickLabel", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "onLongClickLabel", "onLongClick", "onDoubleClick", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;Ljava/lang/String;Lr7/a;Lr7/a;Lkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/foundation/CombinedClickableNodeImpl;", "node", "update", "(Landroidx/compose/foundation/CombinedClickableNodeImpl;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Z", "Ljava/lang/String;", "Landroidx/compose/ui/semantics/Role;", "Lr7/a;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CombinedClickableElement extends ModifierNodeElement<CombinedClickableNodeImpl> {
    private final boolean enabled;
    private final MutableInteractionSource interactionSource;
    private final r7.a<t0> onClick;
    private final String onClickLabel;
    private final r7.a<t0> onDoubleClick;
    private final r7.a<t0> onLongClick;
    private final String onLongClickLabel;
    private final Role role;

    public /* synthetic */ CombinedClickableElement(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a aVar, String str2, r7.a aVar2, r7.a aVar3, h hVar) {
        this(mutableInteractionSource, z, str, role, aVar, str2, aVar2, aVar3);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || CombinedClickableElement.class != other.getClass()) {
            return false;
        }
        CombinedClickableElement combinedClickableElement = (CombinedClickableElement) other;
        return p.a(this.interactionSource, combinedClickableElement.interactionSource) && this.enabled == combinedClickableElement.enabled && p.a(this.onClickLabel, combinedClickableElement.onClickLabel) && p.a(this.role, combinedClickableElement.role) && p.a(this.onClick, combinedClickableElement.onClick) && p.a(this.onLongClickLabel, combinedClickableElement.onLongClickLabel) && p.a(this.onLongClick, combinedClickableElement.onLongClick) && p.a(this.onDoubleClick, combinedClickableElement.onDoubleClick);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iHashCode = ((this.interactionSource.hashCode() * 31) + (this.enabled ? 1231 : 1237)) * 31;
        String str = this.onClickLabel;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Role role = this.role;
        int iHashCode3 = (this.onClick.hashCode() + ((iHashCode2 + (role != null ? Role.m5020hashCodeimpl(role.getValue()) : 0)) * 31)) * 31;
        String str2 = this.onLongClickLabel;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        r7.a<t0> aVar = this.onLongClick;
        int iHashCode5 = (iHashCode4 + (aVar != null ? aVar.hashCode() : 0)) * 31;
        r7.a<t0> aVar2 = this.onDoubleClick;
        return iHashCode5 + (aVar2 != null ? aVar2.hashCode() : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
    }

    public /* synthetic */ CombinedClickableElement(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a aVar, String str2, r7.a aVar2, r7.a aVar3, int i10, h hVar) {
        this(mutableInteractionSource, z, str, (i10 & 8) != 0 ? null : role, aVar, str2, aVar2, aVar3, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public CombinedClickableNodeImpl create() {
        return new CombinedClickableNodeImpl(this.onClick, this.onLongClickLabel, this.onLongClick, this.onDoubleClick, this.interactionSource, this.enabled, this.onClickLabel, this.role, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(CombinedClickableNodeImpl node) {
        node.mo231updatexpl5gLE(this.onClick, this.onLongClickLabel, this.onLongClick, this.onDoubleClick, this.interactionSource, this.enabled, this.onClickLabel, this.role);
    }

    private CombinedClickableElement(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a<t0> aVar, String str2, r7.a<t0> aVar2, r7.a<t0> aVar3) {
        this.interactionSource = mutableInteractionSource;
        this.enabled = z;
        this.onClickLabel = str;
        this.role = role;
        this.onClick = aVar;
        this.onLongClickLabel = str2;
        this.onLongClick = aVar2;
        this.onDoubleClick = aVar3;
    }
}
