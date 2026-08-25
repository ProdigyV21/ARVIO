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
public final class BottomSheetDefaults$DragHandle$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $color;
    final /* synthetic */ float $height;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ BottomSheetDefaults $tmp1_rcvr;
    final /* synthetic */ float $width;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomSheetDefaults$DragHandle$3(BottomSheetDefaults bottomSheetDefaults, Modifier modifier, float f10, float f11, Shape shape, long j10, int i10, int i11) {
        super(2);
        this.$tmp1_rcvr = bottomSheetDefaults;
        this.$modifier = modifier;
        this.$width = f10;
        this.$height = f11;
        this.$shape = shape;
        this.$color = j10;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        this.$tmp1_rcvr.m1245DragHandlelgZ2HuY(this.$modifier, this.$width, this.$height, this.$shape, this.$color, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
