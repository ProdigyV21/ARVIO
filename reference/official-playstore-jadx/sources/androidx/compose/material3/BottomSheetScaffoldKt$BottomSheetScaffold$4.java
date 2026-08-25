package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
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
public final class BottomSheetScaffoldKt$BottomSheetScaffold$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
    final /* synthetic */ long $sheetContainerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $sheetContent;
    final /* synthetic */ long $sheetContentColor;
    final /* synthetic */ p<Composer, Integer, t0> $sheetDragHandle;
    final /* synthetic */ float $sheetMaxWidth;
    final /* synthetic */ float $sheetPeekHeight;
    final /* synthetic */ float $sheetShadowElevation;
    final /* synthetic */ Shape $sheetShape;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ float $sheetTonalElevation;
    final /* synthetic */ q<SnackbarHostState, Composer, Integer, t0> $snackbarHost;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$BottomSheetScaffold$4(q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, float f10, float f11, Shape shape, long j10, long j11, float f12, float f13, p<? super Composer, ? super Integer, t0> pVar, boolean z, p<? super Composer, ? super Integer, t0> pVar2, q<? super SnackbarHostState, ? super Composer, ? super Integer, t0> qVar2, long j12, long j13, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar3, int i10, int i11, int i12) {
        super(2);
        this.$sheetContent = qVar;
        this.$modifier = modifier;
        this.$scaffoldState = bottomSheetScaffoldState;
        this.$sheetPeekHeight = f10;
        this.$sheetMaxWidth = f11;
        this.$sheetShape = shape;
        this.$sheetContainerColor = j10;
        this.$sheetContentColor = j11;
        this.$sheetTonalElevation = f12;
        this.$sheetShadowElevation = f13;
        this.$sheetDragHandle = pVar;
        this.$sheetSwipeEnabled = z;
        this.$topBar = pVar2;
        this.$snackbarHost = qVar2;
        this.$containerColor = j12;
        this.$contentColor = j13;
        this.$content = qVar3;
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
        BottomSheetScaffoldKt.m1249BottomSheetScaffoldsdMYb0k(this.$sheetContent, this.$modifier, this.$scaffoldState, this.$sheetPeekHeight, this.$sheetMaxWidth, this.$sheetShape, this.$sheetContainerColor, this.$sheetContentColor, this.$sheetTonalElevation, this.$sheetShadowElevation, this.$sheetDragHandle, this.$sheetSwipeEnabled, this.$topBar, this.$snackbarHost, this.$containerColor, this.$contentColor, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
