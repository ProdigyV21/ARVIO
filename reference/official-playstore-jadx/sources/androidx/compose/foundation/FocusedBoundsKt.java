package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a)\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\"0\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00010\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Lx6/t0;", "onPositioned", "onFocusedBoundsChanged", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "ModifierLocalFocusedBoundsObserver", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getModifierLocalFocusedBoundsObserver", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FocusedBoundsKt {
    private static final ProvidableModifierLocal<l<LayoutCoordinates, t0>> ModifierLocalFocusedBoundsObserver = ModifierLocalKt.modifierLocalOf(FocusedBoundsKt$ModifierLocalFocusedBoundsObserver$1.INSTANCE);

    public static final ProvidableModifierLocal<l<LayoutCoordinates, t0>> getModifierLocalFocusedBoundsObserver() {
        return ModifierLocalFocusedBoundsObserver;
    }

    public static final Modifier onFocusedBoundsChanged(Modifier modifier, l<? super LayoutCoordinates, t0> lVar) {
        return modifier.then(new FocusedBoundsObserverElement(lVar));
    }
}
