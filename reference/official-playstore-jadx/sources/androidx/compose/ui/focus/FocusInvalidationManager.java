package androidx.compose.ui.focus;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\n\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\r¢\u0006\u0004\b\u000b\u0010\u000eJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0011¢\u0006\u0004\b\u000b\u0010\u0012R&\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/focus/FocusInvalidationManager;", "", "Lkotlin/Function1;", "Lkotlin/Function0;", "Lx6/t0;", "onRequestApplyChangesListener", "<init>", "(Lr7/l;)V", "T", "", "node", "scheduleInvalidation", "(Ljava/util/Set;Ljava/lang/Object;)V", "Landroidx/compose/ui/focus/FocusTargetNode;", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "(Landroidx/compose/ui/focus/FocusEventModifierNode;)V", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "(Landroidx/compose/ui/focus/FocusPropertiesModifierNode;)V", "Lr7/l;", "focusTargetNodes", "Ljava/util/Set;", "focusEventNodes", "focusPropertiesNodes", "invalidateNodes", "Lr7/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusInvalidationManager {
    public static final int $stable = 8;
    private final l<r7.a<t0>, t0> onRequestApplyChangesListener;
    private Set<FocusTargetNode> focusTargetNodes = new LinkedHashSet();
    private Set<FocusEventModifierNode> focusEventNodes = new LinkedHashSet();
    private Set<FocusPropertiesModifierNode> focusPropertiesNodes = new LinkedHashSet();
    private final r7.a<t0> invalidateNodes = new FocusInvalidationManager$invalidateNodes$1(this);

    /* JADX WARN: Multi-variable type inference failed */
    public FocusInvalidationManager(l<? super r7.a<t0>, t0> lVar) {
        this.onRequestApplyChangesListener = lVar;
    }

    public final void scheduleInvalidation(FocusTargetNode node) {
        scheduleInvalidation(this.focusTargetNodes, node);
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        scheduleInvalidation(this.focusEventNodes, node);
    }

    public final void scheduleInvalidation(FocusPropertiesModifierNode node) {
        scheduleInvalidation(this.focusPropertiesNodes, node);
    }

    private final <T> void scheduleInvalidation(Set<T> set, T t2) {
        if (set.add(t2)) {
            if (this.focusPropertiesNodes.size() + this.focusEventNodes.size() + this.focusTargetNodes.size() == 1) {
                this.onRequestApplyChangesListener.invoke(this.invalidateNodes);
            }
        }
    }
}
