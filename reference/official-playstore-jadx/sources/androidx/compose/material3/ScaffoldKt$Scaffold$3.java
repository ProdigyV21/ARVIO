package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
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
public final class ScaffoldKt$Scaffold$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ WindowInsets $contentWindowInsets;
    final /* synthetic */ p<Composer, Integer, t0> $floatingActionButton;
    final /* synthetic */ int $floatingActionButtonPosition;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ p<Composer, Integer, t0> $snackbarHost;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScaffoldKt$Scaffold$3(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, int i10, long j10, long j11, WindowInsets windowInsets, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, int i11, int i12) {
        super(2);
        this.$modifier = modifier;
        this.$topBar = pVar;
        this.$bottomBar = pVar2;
        this.$snackbarHost = pVar3;
        this.$floatingActionButton = pVar4;
        this.$floatingActionButtonPosition = i10;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$contentWindowInsets = windowInsets;
        this.$content = qVar;
        this.$$changed = i11;
        this.$$default = i12;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ScaffoldKt.m1798ScaffoldTvnljyQ(this.$modifier, this.$topBar, this.$bottomBar, this.$snackbarHost, this.$floatingActionButton, this.$floatingActionButtonPosition, this.$containerColor, this.$contentColor, this.$contentWindowInsets, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
