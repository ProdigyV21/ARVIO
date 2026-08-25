package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class SnackbarKt$Snackbar$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ p<Composer, Integer, t0> $action;
    final /* synthetic */ long $actionContentColor;
    final /* synthetic */ boolean $actionOnNewLine;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ p<Composer, Integer, t0> $dismissAction;
    final /* synthetic */ long $dismissActionContentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Shape $shape;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnackbarKt$Snackbar$2(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, boolean z, Shape shape, long j10, long j11, long j12, long j13, p<? super Composer, ? super Integer, t0> pVar3, int i10, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$action = pVar;
        this.$dismissAction = pVar2;
        this.$actionOnNewLine = z;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$actionContentColor = j12;
        this.$dismissActionContentColor = j13;
        this.$content = pVar3;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        SnackbarKt.m1918SnackbareQBnUkQ(this.$modifier, this.$action, this.$dismissAction, this.$actionOnNewLine, this.$shape, this.$containerColor, this.$contentColor, this.$actionContentColor, this.$dismissActionContentColor, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
