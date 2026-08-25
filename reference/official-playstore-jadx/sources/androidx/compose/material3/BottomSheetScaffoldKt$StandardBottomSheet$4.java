package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt$StandardBottomSheet$4 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ l<IntSize, DraggableAnchors<SheetValue>> $calculateAnchors;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ p<Composer, Integer, t0> $dragHandle;
    final /* synthetic */ float $peekHeight;
    final /* synthetic */ float $shadowElevation;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ float $sheetMaxWidth;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ SheetState $state;
    final /* synthetic */ float $tonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$StandardBottomSheet$4(SheetState sheetState, l<? super IntSize, ? extends DraggableAnchors<SheetValue>> lVar, float f10, float f11, boolean z, Shape shape, long j10, long j11, float f12, float f13, p<? super Composer, ? super Integer, t0> pVar, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
        super(2);
        this.$state = sheetState;
        this.$calculateAnchors = lVar;
        this.$peekHeight = f10;
        this.$sheetMaxWidth = f11;
        this.$sheetSwipeEnabled = z;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$tonalElevation = f12;
        this.$shadowElevation = f13;
        this.$dragHandle = pVar;
        this.$content = qVar;
        this.$$changed = i10;
        this.$$changed1 = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        BottomSheetScaffoldKt.m1251StandardBottomSheetXcniZvE(this.$state, this.$calculateAnchors, this.$peekHeight, this.$sheetMaxWidth, this.$sheetSwipeEnabled, this.$shape, this.$containerColor, this.$contentColor, this.$tonalElevation, this.$shadowElevation, this.$dragHandle, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
    }
}
