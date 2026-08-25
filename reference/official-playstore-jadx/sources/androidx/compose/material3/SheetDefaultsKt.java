package androidx.compose.material3;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.Velocity;
import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aB\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004H\u0000¢\u0006\u0004\b\f\u0010\r\u001aC\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e0\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u0014\u0010\u0015\"\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/SheetState;", "sheetState", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "velocity", "Lx6/t0;", "onFling", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "(Landroidx/compose/material3/SheetState;Landroidx/compose/foundation/gestures/Orientation;Lr7/l;)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "", "skipPartiallyExpanded", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "initialValue", "skipHiddenState", "rememberSheetState", "(ZLr7/l;Landroidx/compose/material3/SheetValue;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "Landroidx/compose/ui/unit/Dp;", "DragHandleVerticalPadding", "F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SheetDefaultsKt {
    private static final float DragHandleVerticalPadding = Dp.m5678constructorimpl(22);

    /* JADX INFO: renamed from: androidx.compose.material3.SheetDefaultsKt$rememberSheetState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05371 extends r implements l<SheetValue, Boolean> {
        public static final C05371 INSTANCE = new C05371();

        public C05371() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(SheetValue sheetValue) {
            return Boolean.TRUE;
        }
    }

    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(final SheetState sheetState, final Orientation orientation, final l<? super Float, t0> lVar) {
        return new NestedScrollConnection() { // from class: androidx.compose.material3.SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection.1
            private final float offsetToFloat(long j10) {
                return orientation == Orientation.Horizontal ? Offset.m3236getXimpl(j10) : Offset.m3237getYimpl(j10);
            }

            private final long toOffset(float f10) {
                Orientation orientation2 = orientation;
                float f11 = orientation2 == Orientation.Horizontal ? f10 : 0.0f;
                if (orientation2 != Orientation.Vertical) {
                    f10 = 0.0f;
                }
                return OffsetKt.Offset(f11, f10);
            }

            private final float velocityToFloat(long j10) {
                return orientation == Orientation.Horizontal ? Velocity.m5909getXimpl(j10) : Velocity.m5910getYimpl(j10);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
            public Object mo368onPostFlingRZ2iAVY(long j10, long j11, d7.d<? super Velocity> dVar) {
                lVar.invoke(new Float(velocityToFloat(j11)));
                return Velocity.m5900boximpl(j11);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
            public long mo369onPostScrollDzOQY0M(long consumed, long available, int source) {
                return NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4425getDragWNlRxjI()) ? toOffset(sheetState.getAnchoredDraggableState$material3_release().dispatchRawDelta(offsetToFloat(available))) : Offset.INSTANCE.m3252getZeroF1C5BW0();
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreFling-QWom1Mo */
            public Object mo370onPreFlingQWom1Mo(long j10, d7.d<? super Velocity> dVar) {
                float fVelocityToFloat = velocityToFloat(j10);
                float fRequireOffset = sheetState.requireOffset();
                float fMinAnchor = sheetState.getAnchoredDraggableState$material3_release().getAnchors().minAnchor();
                if (fVelocityToFloat >= 0.0f || fRequireOffset <= fMinAnchor) {
                    j10 = Velocity.INSTANCE.m5920getZero9UxMQ8M();
                } else {
                    lVar.invoke(new Float(fVelocityToFloat));
                }
                return Velocity.m5900boximpl(j10);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
            public long mo371onPreScrollOzD1aCk(long available, int source) {
                float fOffsetToFloat = offsetToFloat(available);
                return (fOffsetToFloat >= 0.0f || !NestedScrollSource.m4420equalsimpl0(source, NestedScrollSource.INSTANCE.m4425getDragWNlRxjI())) ? Offset.INSTANCE.m3252getZeroF1C5BW0() : toOffset(sheetState.getAnchoredDraggableState$material3_release().dispatchRawDelta(fOffsetToFloat));
            }
        };
    }

    public static final SheetState rememberSheetState(boolean z, l<? super SheetValue, Boolean> lVar, SheetValue sheetValue, boolean z5, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1032784200);
        boolean z10 = (i11 & 1) != 0 ? false : z;
        if ((i11 & 2) != 0) {
            lVar = C05371.INSTANCE;
        }
        l<? super SheetValue, Boolean> lVar2 = lVar;
        SheetValue sheetValue2 = (i11 & 4) != 0 ? SheetValue.Hidden : sheetValue;
        boolean z11 = (i11 & 8) != 0 ? false : z5;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1032784200, i10, -1, "androidx.compose.material3.rememberSheetState (SheetDefaults.kt:480)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object[] objArr = {Boolean.valueOf(z10), lVar2};
        Saver<SheetState, SheetValue> Saver = SheetState.INSTANCE.Saver(z10, lVar2, density);
        composer.startReplaceableGroup(1097108455);
        boolean zChanged = ((((i10 & 14) ^ 6) > 4 && composer.changed(z10)) || (i10 & 6) == 4) | composer.changed(density) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(sheetValue2)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | ((((i10 & 112) ^ 48) > 32 && composer.changed(lVar2)) || (i10 & 48) == 32) | ((((i10 & 7168) ^ 3072) > 2048 && composer.changed(z11)) || (i10 & 3072) == 2048);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object sheetDefaultsKt$rememberSheetState$2$1 = new SheetDefaultsKt$rememberSheetState$2$1(z10, density, sheetValue2, lVar2, z11);
            composer.updateRememberedValue(sheetDefaultsKt$rememberSheetState$2$1);
            objRememberedValue = sheetDefaultsKt$rememberSheetState$2$1;
        }
        composer.endReplaceableGroup();
        SheetState sheetState = (SheetState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return sheetState;
    }
}
