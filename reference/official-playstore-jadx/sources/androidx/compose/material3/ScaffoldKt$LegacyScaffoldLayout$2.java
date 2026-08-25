package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScaffoldKt$LegacyScaffoldLayout$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ WindowInsets $contentWindowInsets;
    final /* synthetic */ p<Composer, Integer, t0> $fab;
    final /* synthetic */ int $fabPosition;
    final /* synthetic */ p<Composer, Integer, t0> $snackbar;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScaffoldKt$LegacyScaffoldLayout$2(int i10, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, int i11) {
        super(2);
        this.$fabPosition = i10;
        this.$topBar = pVar;
        this.$content = qVar;
        this.$snackbar = pVar2;
        this.$fab = pVar3;
        this.$contentWindowInsets = windowInsets;
        this.$bottomBar = pVar4;
        this.$$changed = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        ScaffoldKt.m1797LegacyScaffoldLayoutFMILGgc(this.$fabPosition, this.$topBar, this.$content, this.$snackbar, this.$fab, this.$contentWindowInsets, this.$bottomBar, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
