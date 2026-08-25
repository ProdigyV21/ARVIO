package androidx.compose.ui.node;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\u000bJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\nJ\u0015\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u000bJ\r\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011JJ\u0010\u0016\u001a\u00020\f28\b\u0004\u0010\u0015\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\f0\u0012H\u0086\b¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u0018\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001e¨\u0006 "}, d2 = {"Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "", "", "extraAssertions", "<init>", "(Z)V", "Landroidx/compose/ui/node/LayoutNode;", "node", "affectsLookahead", "contains", "(Landroidx/compose/ui/node/LayoutNode;Z)Z", "(Landroidx/compose/ui/node/LayoutNode;)Z", "Lx6/t0;", "add", "(Landroidx/compose/ui/node/LayoutNode;Z)V", "remove", "pop", "()Landroidx/compose/ui/node/LayoutNode;", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "block", "popEach", "(Lr7/p;)V", "isEmpty", "()Z", "(Z)Z", "isNotEmpty", "Landroidx/compose/ui/node/DepthSortedSet;", "lookaheadSet", "Landroidx/compose/ui/node/DepthSortedSet;", "set", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DepthSortedSetsForDifferentPasses {
    public static final int $stable = 8;
    private final DepthSortedSet lookaheadSet;
    private final DepthSortedSet set;

    public DepthSortedSetsForDifferentPasses(boolean z) {
        this.lookaheadSet = new DepthSortedSet(z);
        this.set = new DepthSortedSet(z);
    }

    public final void add(LayoutNode node, boolean affectsLookahead) {
        if (affectsLookahead) {
            this.lookaheadSet.add(node);
        } else {
            if (this.lookaheadSet.contains(node)) {
                return;
            }
            this.set.add(node);
        }
    }

    public final boolean contains(LayoutNode node, boolean affectsLookahead) {
        boolean zContains = this.lookaheadSet.contains(node);
        return affectsLookahead ? zContains : zContains || this.set.contains(node);
    }

    public final boolean isEmpty() {
        return this.set.isEmpty() && this.lookaheadSet.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final LayoutNode pop() {
        return !this.lookaheadSet.isEmpty() ? this.lookaheadSet.pop() : this.set.pop();
    }

    public final void popEach(p<? super LayoutNode, ? super Boolean, t0> block) {
        while (isNotEmpty()) {
            boolean zIsEmpty = this.lookaheadSet.isEmpty();
            block.invoke((!zIsEmpty ? this.lookaheadSet : this.set).pop(), Boolean.valueOf(!zIsEmpty));
        }
    }

    public final boolean remove(LayoutNode node, boolean affectsLookahead) {
        return affectsLookahead ? this.lookaheadSet.remove(node) : this.set.remove(node);
    }

    public final boolean isEmpty(boolean affectsLookahead) {
        return (affectsLookahead ? this.lookaheadSet : this.set).isEmpty();
    }

    public final boolean contains(LayoutNode node) {
        return this.lookaheadSet.contains(node) || this.set.contains(node);
    }

    public final boolean remove(LayoutNode node) {
        return this.set.remove(node) || this.lookaheadSet.remove(node);
    }
}
