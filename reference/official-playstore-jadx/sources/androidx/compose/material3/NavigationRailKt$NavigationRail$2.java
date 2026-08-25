package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class NavigationRailKt$NavigationRail$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $header;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public NavigationRailKt$NavigationRail$2(Modifier modifier, long j10, long j11, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar2, int i10, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$header = qVar;
        this.$windowInsets = windowInsets;
        this.$content = qVar2;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        NavigationRailKt.m1738NavigationRailqi6gXK8(this.$modifier, this.$containerColor, this.$contentColor, this.$header, this.$windowInsets, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
