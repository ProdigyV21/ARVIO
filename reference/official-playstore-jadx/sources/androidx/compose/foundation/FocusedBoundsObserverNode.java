package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalModifierNodeKt;
import kotlin.Metadata;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bR0\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\bR\"\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\tR\u001a\u0010\u000f\u001a\u00020\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000b¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/FocusedBoundsObserverNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Lx6/t0;", "onPositioned", "<init>", "(Lr7/l;)V", "Lr7/l;", "getOnPositioned", "()Lr7/l;", "setOnPositioned", "focusBoundsObserver", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "getParent", "parent", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusedBoundsObserverNode extends Modifier.Node implements ModifierLocalModifierNode {
    public static final int $stable = 8;
    private final l<LayoutCoordinates, t0> focusBoundsObserver;
    private l<? super LayoutCoordinates, t0> onPositioned;
    private final ModifierLocalMap providedValues;

    public FocusedBoundsObserverNode(l<? super LayoutCoordinates, t0> lVar) {
        this.onPositioned = lVar;
        FocusedBoundsObserverNode$focusBoundsObserver$1 focusedBoundsObserverNode$focusBoundsObserver$1 = new FocusedBoundsObserverNode$focusBoundsObserver$1(this);
        this.focusBoundsObserver = focusedBoundsObserverNode$focusBoundsObserver$1;
        this.providedValues = ModifierLocalModifierNodeKt.modifierLocalMapOf(new x(FocusedBoundsKt.getModifierLocalFocusedBoundsObserver(), focusedBoundsObserverNode$focusBoundsObserver$1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final l<LayoutCoordinates, t0> getParent() {
        if (getIsAttached()) {
            return (l) androidx.compose.ui.modifier.b.a(this, FocusedBoundsKt.getModifierLocalFocusedBoundsObserver());
        }
        return null;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.modifier.ModifierLocalReadScope
    public final /* synthetic */ Object getCurrent(ModifierLocal modifierLocal) {
        return androidx.compose.ui.modifier.b.a(this, modifierLocal);
    }

    public final l<LayoutCoordinates, t0> getOnPositioned() {
        return this.onPositioned;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public ModifierLocalMap getProvidedValues() {
        return this.providedValues;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final /* synthetic */ void provide(ModifierLocal modifierLocal, Object obj) {
        androidx.compose.ui.modifier.b.c(this, modifierLocal, obj);
    }

    public final void setOnPositioned(l<? super LayoutCoordinates, t0> lVar) {
        this.onPositioned = lVar;
    }
}
