package androidx.compose.material3;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/ui/unit/Density;", "invoke-Bjo55l4", "(Landroidx/compose/ui/unit/Density;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt$ModalNavigationDrawer$2$4$1 extends r implements l<Density, IntOffset> {
    final /* synthetic */ DrawerState $drawerState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationDrawerKt$ModalNavigationDrawer$2$4$1(DrawerState drawerState) {
        super(1);
        this.$drawerState = drawerState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return IntOffset.m5793boximpl(m1724invokeBjo55l4((Density) obj));
    }

    /* JADX INFO: renamed from: invoke-Bjo55l4, reason: not valid java name */
    public final long m1724invokeBjo55l4(Density density) {
        return IntOffsetKt.IntOffset(t7.a.M(this.$drawerState.requireOffset$material3_release()), 0);
    }
}
