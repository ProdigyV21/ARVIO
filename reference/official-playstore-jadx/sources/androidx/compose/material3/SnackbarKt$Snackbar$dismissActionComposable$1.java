package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnackbarKt$Snackbar$dismissActionComposable$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ SnackbarData $snackbarData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnackbarKt$Snackbar$dismissActionComposable$1(SnackbarData snackbarData) {
        super(2);
        this.$snackbarData = snackbarData;
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
            ComposerKt.traceEventStart(-1812633777, i10, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:225)");
        }
        composer.startReplaceableGroup(-2057496502);
        boolean zChanged = composer.changed(this.$snackbarData);
        SnackbarData snackbarData = this.$snackbarData;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new SnackbarKt$Snackbar$dismissActionComposable$1$1$1(snackbarData);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        IconButtonKt.IconButton((r7.a) objRememberedValue, null, false, null, null, ComposableSingletons$SnackbarKt.INSTANCE.m1436getLambda1$material3_release(), composer, 196608, 30);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
