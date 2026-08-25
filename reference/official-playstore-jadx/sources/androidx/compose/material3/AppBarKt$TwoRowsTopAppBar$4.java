package androidx.compose.material3;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class AppBarKt$TwoRowsTopAppBar$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
    final /* synthetic */ TopAppBarColors $colors;
    final /* synthetic */ float $maxHeight;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
    final /* synthetic */ float $pinnedHeight;
    final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
    final /* synthetic */ p<Composer, Integer, t0> $smallTitle;
    final /* synthetic */ TextStyle $smallTitleTextStyle;
    final /* synthetic */ p<Composer, Integer, t0> $title;
    final /* synthetic */ float $titleBottomPadding;
    final /* synthetic */ TextStyle $titleTextStyle;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AppBarKt$TwoRowsTopAppBar$4(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, float f10, p<? super Composer, ? super Integer, t0> pVar2, TextStyle textStyle2, p<? super Composer, ? super Integer, t0> pVar3, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, float f11, float f12, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11, int i12) {
        super(2);
        this.$modifier = modifier;
        this.$title = pVar;
        this.$titleTextStyle = textStyle;
        this.$titleBottomPadding = f10;
        this.$smallTitle = pVar2;
        this.$smallTitleTextStyle = textStyle2;
        this.$navigationIcon = pVar3;
        this.$actions = qVar;
        this.$windowInsets = windowInsets;
        this.$colors = topAppBarColors;
        this.$maxHeight = f11;
        this.$pinnedHeight = f12;
        this.$scrollBehavior = topAppBarScrollBehavior;
        this.$$changed = i10;
        this.$$changed1 = i11;
        this.$$default = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        AppBarKt.m1228TwoRowsTopAppBartjU4iQQ(this.$modifier, this.$title, this.$titleTextStyle, this.$titleBottomPadding, this.$smallTitle, this.$smallTitleTextStyle, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$maxHeight, this.$pinnedHeight, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
