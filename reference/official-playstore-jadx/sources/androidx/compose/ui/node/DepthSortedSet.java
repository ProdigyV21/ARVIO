package androidx.compose.ui.node;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\tJ\r\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0012\u001a\u00020\n2\u0014\b\u0004\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0010H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002H\u0086\b¢\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR'\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001c0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R$\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00060\"j\b\u0012\u0004\u0012\u00020\u0006`#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/compose/ui/node/DepthSortedSet;", "", "", "extraAssertions", "<init>", "(Z)V", "Landroidx/compose/ui/node/LayoutNode;", "node", "contains", "(Landroidx/compose/ui/node/LayoutNode;)Z", "Lx6/t0;", "add", "(Landroidx/compose/ui/node/LayoutNode;)V", "remove", "pop", "()Landroidx/compose/ui/node/LayoutNode;", "Lkotlin/Function1;", "block", "popEach", "(Lr7/l;)V", "isEmpty", "()Z", "isNotEmpty", "", "toString", "()Ljava/lang/String;", "Z", "", "", "mapOfOriginalDepth$delegate", "Lx6/s;", "getMapOfOriginalDepth", "()Ljava/util/Map;", "mapOfOriginalDepth", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "DepthComparator", "Ljava/util/Comparator;", "Landroidx/compose/ui/node/TreeSet;", "set", "Landroidx/compose/ui/node/TreeSet;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DepthSortedSet {
    public static final int $stable = 8;
    private final Comparator<LayoutNode> DepthComparator;
    private final boolean extraAssertions;

    /* JADX INFO: renamed from: mapOfOriginalDepth$delegate, reason: from kotlin metadata */
    private final s mapOfOriginalDepth = f4.f.o(3, DepthSortedSet$mapOfOriginalDepth$2.INSTANCE);
    private final TreeSet<LayoutNode> set;

    public DepthSortedSet(boolean z) {
        this.extraAssertions = z;
        Comparator<LayoutNode> comparator = new Comparator<LayoutNode>() { // from class: androidx.compose.ui.node.DepthSortedSet$DepthComparator$1
            @Override // java.util.Comparator
            public int compare(LayoutNode l12, LayoutNode l22) {
                int iC = p.c(l12.getDepth(), l22.getDepth());
                return iC != 0 ? iC : p.c(l12.hashCode(), l22.hashCode());
            }
        };
        this.DepthComparator = comparator;
        this.set = new TreeSet<>(comparator);
    }

    private final Map<LayoutNode, Integer> getMapOfOriginalDepth() {
        return (Map) this.mapOfOriginalDepth.getValue();
    }

    public final void add(LayoutNode node) {
        if (!node.isAttached()) {
            throw new IllegalStateException("DepthSortedSet.add called on an unattached node");
        }
        if (this.extraAssertions) {
            Integer num = getMapOfOriginalDepth().get(node);
            if (num == null) {
                getMapOfOriginalDepth().put(node, Integer.valueOf(node.getDepth()));
            } else {
                if (num.intValue() != node.getDepth()) {
                    throw new IllegalStateException("invalid node depth");
                }
            }
        }
        this.set.add(node);
    }

    public final boolean contains(LayoutNode node) {
        boolean zContains = this.set.contains(node);
        if (!this.extraAssertions || zContains == getMapOfOriginalDepth().containsKey(node)) {
            return zContains;
        }
        throw new IllegalStateException("inconsistency in TreeSet");
    }

    public final boolean isEmpty() {
        return this.set.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final LayoutNode pop() {
        LayoutNode layoutNodeFirst = this.set.first();
        remove(layoutNodeFirst);
        return layoutNodeFirst;
    }

    public final void popEach(l<? super LayoutNode, t0> block) {
        while (!isEmpty()) {
            block.invoke(pop());
        }
    }

    public final boolean remove(LayoutNode node) {
        if (!node.isAttached()) {
            throw new IllegalStateException("DepthSortedSet.remove called on an unattached node");
        }
        boolean zRemove = this.set.remove(node);
        if (this.extraAssertions) {
            if (!p.a(getMapOfOriginalDepth().remove(node), zRemove ? Integer.valueOf(node.getDepth()) : null)) {
                throw new IllegalStateException("invalid node depth");
            }
        }
        return zRemove;
    }

    public String toString() {
        return this.set.toString();
    }
}
