package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class ModalBottomSheet_androidKt$ModalBottomSheet$5 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<t0> $onDismissRequest;
    final /* synthetic */ ModalBottomSheetProperties $properties;
    final /* synthetic */ long $scrimColor;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ float $sheetMaxWidth;
    final /* synthetic */ SheetState $sheetState;
    final /* synthetic */ float $tonalElevation;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ModalBottomSheet_androidKt$ModalBottomSheet$5(r7.a<t0> aVar, Modifier modifier, SheetState sheetState, float f10, Shape shape, long j10, long j11, float f11, long j12, p<? super Composer, ? super Integer, t0> pVar, WindowInsets windowInsets, ModalBottomSheetProperties modalBottomSheetProperties, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11, int i12) {
        super(2);
        this.$onDismissRequest = aVar;
        this.$modifier = modifier;
        this.$sheetState = sheetState;
        this.$sheetMaxWidth = f10;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$tonalElevation = f11;
        this.$scrimColor = j12;
        this.$dragHandle = pVar;
        this.$windowInsets = windowInsets;
        this.$properties = modalBottomSheetProperties;
        this.$content = qVar;
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
        ModalBottomSheet_androidKt.m1684ModalBottomSheetdYc4hso(this.$onDismissRequest, this.$modifier, this.$sheetState, this.$sheetMaxWidth, this.$shape, this.$containerColor, this.$contentColor, this.$tonalElevation, this.$scrimColor, this.$dragHandle, this.$windowInsets, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
