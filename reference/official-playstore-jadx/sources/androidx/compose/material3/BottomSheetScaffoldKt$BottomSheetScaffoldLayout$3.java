package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
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
public final class BottomSheetScaffoldKt$BottomSheetScaffoldLayout$3 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $body;
    final /* synthetic */ q<Integer, Composer, Integer, t0> $bottomSheet;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<Float> $sheetOffset;
    final /* synthetic */ float $sheetPeekHeight;
    final /* synthetic */ SheetState $sheetState;
    final /* synthetic */ p<Composer, Integer, t0> $snackbarHost;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$BottomSheetScaffoldLayout$3(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, q<? super Integer, ? super Composer, ? super Integer, t0> qVar2, p<? super Composer, ? super Integer, t0> pVar2, float f10, r7.a<Float> aVar, SheetState sheetState, long j10, long j11, int i10) {
        super(2);
        this.$modifier = modifier;
        this.$topBar = pVar;
        this.$body = qVar;
        this.$bottomSheet = qVar2;
        this.$snackbarHost = pVar2;
        this.$sheetPeekHeight = f10;
        this.$sheetOffset = aVar;
        this.$sheetState = sheetState;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$$changed = i10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        BottomSheetScaffoldKt.m1250BottomSheetScaffoldLayoutPxNyym8(this.$modifier, this.$topBar, this.$body, this.$bottomSheet, this.$snackbarHost, this.$sheetPeekHeight, this.$sheetOffset, this.$sheetState, this.$containerColor, this.$contentColor, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
    }
}
