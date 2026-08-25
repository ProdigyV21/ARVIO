package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt$ModalNavigationDrawer$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ p<Composer, Integer, t0> $drawerContent;
    final /* synthetic */ DrawerState $drawerState;
    final /* synthetic */ boolean $gesturesEnabled;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ long $scrimColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NavigationDrawerKt$ModalNavigationDrawer$3(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, DrawerState drawerState, boolean z, long j10, p<? super Composer, ? super Integer, t0> pVar2, int i10, int i11) {
        super(2);
        this.$drawerContent = pVar;
        this.$modifier = modifier;
        this.$drawerState = drawerState;
        this.$gesturesEnabled = z;
        this.$scrimColor = j10;
        this.$content = pVar2;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        NavigationDrawerKt.m1716ModalNavigationDrawerFHprtrg(this.$drawerContent, this.$modifier, this.$drawerState, this.$gesturesEnabled, this.$scrimColor, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
