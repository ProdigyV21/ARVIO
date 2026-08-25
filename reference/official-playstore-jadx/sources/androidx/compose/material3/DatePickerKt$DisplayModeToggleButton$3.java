package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt$DisplayModeToggleButton$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $displayMode;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ l<DisplayMode, t0> $onDisplayModeChange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DatePickerKt$DisplayModeToggleButton$3(Modifier modifier, int i10, l<? super DisplayMode, t0> lVar, int i11) {
        super(2);
        this.$modifier = modifier;
        this.$displayMode = i10;
        this.$onDisplayModeChange = lVar;
        this.$$changed = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        DatePickerKt.m1484DisplayModeToggleButtontER2X8s(this.$modifier, this.$displayMode, this.$onDisplayModeChange, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
