package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ScaffoldKt$Scaffold$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ p<Composer, Integer, t0> $floatingActionButton;
    final /* synthetic */ int $floatingActionButtonPosition;
    final /* synthetic */ MutableWindowInsets $safeInsets;
    final /* synthetic */ p<Composer, Integer, t0> $snackbarHost;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScaffoldKt$Scaffold$2(int i10, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, MutableWindowInsets mutableWindowInsets, p<? super Composer, ? super Integer, t0> pVar4) {
        super(2);
        this.$floatingActionButtonPosition = i10;
        this.$topBar = pVar;
        this.$content = qVar;
        this.$snackbarHost = pVar2;
        this.$floatingActionButton = pVar3;
        this.$safeInsets = mutableWindowInsets;
        this.$bottomBar = pVar4;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1979205334, i10, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:107)");
        }
        ScaffoldKt.m1799ScaffoldLayoutFMILGgc(this.$floatingActionButtonPosition, this.$topBar, this.$content, this.$snackbarHost, this.$floatingActionButton, this.$safeInsets, this.$bottomBar, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
