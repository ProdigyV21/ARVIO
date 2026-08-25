package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0001HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0001HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u001b\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00018\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\r¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/node/ForceUpdateElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/Modifier$Node;", "original", "<init>", "(Landroidx/compose/ui/node/ModifierNodeElement;)V", "create", "()Landroidx/compose/ui/Modifier$Node;", "node", "Lx6/t0;", "update", "(Landroidx/compose/ui/Modifier$Node;)V", "component1", "()Landroidx/compose/ui/node/ModifierNodeElement;", "copy", "(Landroidx/compose/ui/node/ModifierNodeElement;)Landroidx/compose/ui/node/ForceUpdateElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/node/ModifierNodeElement;", "getOriginal", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class ForceUpdateElement extends ModifierNodeElement<Modifier.Node> {
    private final ModifierNodeElement<?> original;

    public ForceUpdateElement(ModifierNodeElement<?> modifierNodeElement) {
        this.original = modifierNodeElement;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ForceUpdateElement copy$default(ForceUpdateElement forceUpdateElement, ModifierNodeElement modifierNodeElement, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            modifierNodeElement = forceUpdateElement.original;
        }
        return forceUpdateElement.copy(modifierNodeElement);
    }

    public final ModifierNodeElement<?> component1() {
        return this.original;
    }

    public final ForceUpdateElement copy(ModifierNodeElement<?> original) {
        return new ForceUpdateElement(original);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public Modifier.Node create() {
        throw new IllegalStateException("Shouldn't be called");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ForceUpdateElement) && p.a(this.original, ((ForceUpdateElement) other).original);
    }

    public final ModifierNodeElement<?> getOriginal() {
        return this.original;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.original.hashCode();
    }

    public String toString() {
        return "ForceUpdateElement(original=" + this.original + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(Modifier.Node node) {
        throw new IllegalStateException("Shouldn't be called");
    }
}
