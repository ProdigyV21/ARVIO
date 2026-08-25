package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b'\u0018\u00002\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J=\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0019\u0010\r\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n¢\u0006\u0002\b\fH$ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR$\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R0\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00198\u0004@DX\u0084\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R0\u0010\"\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020!8\u0004@DX\u0084\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\"\u0010\u001c\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R*\u0010%\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00068\u0004@BX\u0084\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u0014\u0010(\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0016R\u0014\u0010*\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006,"}, d2 = {"Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measured;", "<init>", "()V", "Lx6/t0;", "onMeasuredSizeChanged", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeAt-f8xVGno", "(JFLr7/l;)V", "placeAt", "", "<set-?>", "width", "I", "getWidth", "()I", "height", "getHeight", "Landroidx/compose/ui/unit/IntSize;", "value", "measuredSize", "J", "getMeasuredSize-YbymL2g", "()J", "setMeasuredSize-ozmzZPI", "(J)V", "Landroidx/compose/ui/unit/Constraints;", "measurementConstraints", "getMeasurementConstraints-msEJaDk", "setMeasurementConstraints-BRTryo0", "apparentToRealOffset", "getApparentToRealOffset-nOcc-ac", "getMeasuredWidth", "measuredWidth", "getMeasuredHeight", "measuredHeight", "PlacementScope", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Placeable implements Measured {
    public static final int $stable = 8;
    private int height;
    private int width;
    private long measuredSize = IntSizeKt.IntSize(0, 0);
    private long measurementConstraints = PlaceableKt.DefaultConstraints;
    private long apparentToRealOffset = IntOffset.INSTANCE.m5812getZeronOccac();

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ+\u0010\f\u001a\u00020\t*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u0010J+\u0010\u0011\u001a\u00020\t*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0011\u0010\u0010J&\u0010\u0011\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000bJA\u0010\u0019\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0015ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018JF\u0010\u0019\u001a\u00020\t*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u00072\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0015¢\u0006\u0004\b\u0019\u0010\u001aJF\u0010\u001b\u001a\u00020\t*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u00072\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0015¢\u0006\u0004\b\u001b\u0010\u001aJA\u0010\u001b\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013¢\u0006\u0002\b\u0015ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0018JD\u0010\u001e\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u001b\b\b\u0010\u0016\u001a\u0015\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013¢\u0006\u0002\b\u0015H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0018JD\u0010 \u001a\u00020\t*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u001b\b\b\u0010\u0016\u001a\u0015\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t\u0018\u00010\u0013¢\u0006\u0002\b\u0015H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0018R\u0014\u0010#\u001a\u00020\r8$X¤\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010'\u001a\u00020$8$X¤\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010+\u001a\u0004\u0018\u00010(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006,"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "<init>", "()V", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/unit/IntOffset;", "position", "", "zIndex", "Lx6/t0;", "placeRelative-70tqf50", "(Landroidx/compose/ui/layout/Placeable;JF)V", "placeRelative", "", "x", "y", "(Landroidx/compose/ui/layout/Placeable;IIF)V", "place", "place-70tqf50", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "placeRelativeWithLayer-aW-9-wM", "(Landroidx/compose/ui/layout/Placeable;JFLr7/l;)V", "placeRelativeWithLayer", "(Landroidx/compose/ui/layout/Placeable;IIFLr7/l;)V", "placeWithLayer", "placeWithLayer-aW-9-wM", "placeAutoMirrored-aW-9-wM$ui_release", "placeAutoMirrored", "placeApparentToRealOffset-aW-9-wM$ui_release", "placeApparentToRealOffset", "getParentWidth", "()I", "parentWidth", "Landroidx/compose/ui/unit/LayoutDirection;", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "parentLayoutDirection", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class PlacementScope {
        public static final int $stable = 0;

        public static /* synthetic */ void place$default(PlacementScope placementScope, Placeable placeable, int i10, int i11, float f10, int i12, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place");
            }
            if ((i12 & 4) != 0) {
                f10 = 0.0f;
            }
            placementScope.place(placeable, i10, i11, f10);
        }

        /* JADX INFO: renamed from: place-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m4669place70tqf50$default(PlacementScope placementScope, Placeable placeable, long j10, float f10, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place-70tqf50");
            }
            if ((i10 & 2) != 0) {
                f10 = 0.0f;
            }
            placementScope.m4673place70tqf50(placeable, j10, f10);
        }

        public static /* synthetic */ void placeRelative$default(PlacementScope placementScope, Placeable placeable, int i10, int i11, float f10, int i12, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative");
            }
            if ((i12 & 4) != 0) {
                f10 = 0.0f;
            }
            placementScope.placeRelative(placeable, i10, i11, f10);
        }

        /* JADX INFO: renamed from: placeRelative-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m4670placeRelative70tqf50$default(PlacementScope placementScope, Placeable placeable, long j10, float f10, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative-70tqf50");
            }
            if ((i10 & 2) != 0) {
                f10 = 0.0f;
            }
            placementScope.m4676placeRelative70tqf50(placeable, j10, f10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i10, int i11, float f10, r7.l lVar, int i12, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer");
            }
            if ((i12 & 4) != 0) {
                f10 = 0.0f;
            }
            float f11 = f10;
            if ((i12 & 8) != 0) {
                lVar = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeRelativeWithLayer(placeable, i10, i11, f11, lVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m4671placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j10, float f10, r7.l lVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer-aW-9-wM");
            }
            if ((i10 & 2) != 0) {
                f10 = 0.0f;
            }
            float f11 = f10;
            if ((i10 & 4) != 0) {
                lVar = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m4677placeRelativeWithLayeraW9wM(placeable, j10, f11, lVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i10, int i11, float f10, r7.l lVar, int i12, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer");
            }
            if ((i12 & 4) != 0) {
                f10 = 0.0f;
            }
            float f11 = f10;
            if ((i12 & 8) != 0) {
                lVar = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeWithLayer(placeable, i10, i11, f11, lVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m4672placeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j10, float f10, r7.l lVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer-aW-9-wM");
            }
            if ((i10 & 2) != 0) {
                f10 = 0.0f;
            }
            float f11 = f10;
            if ((i10 & 4) != 0) {
                lVar = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m4678placeWithLayeraW9wM(placeable, j10, f11, lVar);
        }

        public LayoutCoordinates getCoordinates() {
            return null;
        }

        public abstract LayoutDirection getParentLayoutDirection();

        public abstract int getParentWidth();

        public final void place(Placeable placeable, int i10, int i11, float f10) {
            long jIntOffset = IntOffsetKt.IntOffset(i10, i11);
            long j10 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jIntOffset), j10), f10, null);
        }

        /* JADX INFO: renamed from: place-70tqf50, reason: not valid java name */
        public final void m4673place70tqf50(Placeable placeable, long j10, float f10) {
            long j11 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, null);
        }

        /* JADX INFO: renamed from: placeApparentToRealOffset-aW-9-wM$ui_release, reason: not valid java name */
        public final void m4674placeApparentToRealOffsetaW9wM$ui_release(Placeable placeable, long j10, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            long j11 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, lVar);
        }

        /* JADX INFO: renamed from: placeAutoMirrored-aW-9-wM$ui_release, reason: not valid java name */
        public final void m4675placeAutoMirroredaW9wM$ui_release(Placeable placeable, long j10, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long j11 = placeable.apparentToRealOffset;
                placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, lVar);
                return;
            }
            long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m5802getXimpl(j10), IntOffset.m5803getYimpl(j10));
            long j12 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j12) + IntOffset.m5802getXimpl(jIntOffset), j12), f10, lVar);
        }

        public final void placeRelative(Placeable placeable, int i10, int i11, float f10) {
            long jIntOffset = IntOffsetKt.IntOffset(i10, i11);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long j10 = placeable.apparentToRealOffset;
                placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jIntOffset), j10), f10, null);
                return;
            }
            long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m5802getXimpl(jIntOffset), IntOffset.m5803getYimpl(jIntOffset));
            long j11 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset2), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(jIntOffset2), j11), f10, null);
        }

        /* JADX INFO: renamed from: placeRelative-70tqf50, reason: not valid java name */
        public final void m4676placeRelative70tqf50(Placeable placeable, long j10, float f10) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long j11 = placeable.apparentToRealOffset;
                placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, null);
                return;
            }
            long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m5802getXimpl(j10), IntOffset.m5803getYimpl(j10));
            long j12 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j12) + IntOffset.m5802getXimpl(jIntOffset), j12), f10, null);
        }

        public final void placeRelativeWithLayer(Placeable placeable, int i10, int i11, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            long jIntOffset = IntOffsetKt.IntOffset(i10, i11);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long j10 = placeable.apparentToRealOffset;
                placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jIntOffset), j10), f10, lVar);
                return;
            }
            long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m5802getXimpl(jIntOffset), IntOffset.m5803getYimpl(jIntOffset));
            long j11 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset2), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(jIntOffset2), j11), f10, lVar);
        }

        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m4677placeRelativeWithLayeraW9wM(Placeable placeable, long j10, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long j11 = placeable.apparentToRealOffset;
                placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, lVar);
                return;
            }
            long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m5802getXimpl(j10), IntOffset.m5803getYimpl(j10));
            long j12 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j12) + IntOffset.m5802getXimpl(jIntOffset), j12), f10, lVar);
        }

        public final void placeWithLayer(Placeable placeable, int i10, int i11, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            long jIntOffset = IntOffsetKt.IntOffset(i10, i11);
            long j10 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jIntOffset), j10), f10, lVar);
        }

        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m4678placeWithLayeraW9wM(Placeable placeable, long j10, float f10, r7.l<? super GraphicsLayerScope, t0> lVar) {
            long j11 = placeable.apparentToRealOffset;
            placeable.mo4632placeAtf8xVGno(a0.c.c(IntOffset.m5803getYimpl(j10), IntOffset.m5802getXimpl(j11) + IntOffset.m5802getXimpl(j10), j11), f10, lVar);
        }
    }

    private final void onMeasuredSizeChanged() {
        this.width = qb.d.n(IntSize.m5844getWidthimpl(this.measuredSize), Constraints.m5648getMinWidthimpl(this.measurementConstraints), Constraints.m5646getMaxWidthimpl(this.measurementConstraints));
        this.height = qb.d.n(IntSize.m5843getHeightimpl(this.measuredSize), Constraints.m5647getMinHeightimpl(this.measurementConstraints), Constraints.m5645getMaxHeightimpl(this.measurementConstraints));
        this.apparentToRealOffset = IntOffsetKt.IntOffset((this.width - IntSize.m5844getWidthimpl(this.measuredSize)) / 2, (this.height - IntSize.m5843getHeightimpl(this.measuredSize)) / 2);
    }

    /* JADX INFO: renamed from: getApparentToRealOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getApparentToRealOffset() {
        return this.apparentToRealOffset;
    }

    public final int getHeight() {
        return this.height;
    }

    @Override // androidx.compose.ui.layout.Measured
    public int getMeasuredHeight() {
        return IntSize.m5843getHeightimpl(this.measuredSize);
    }

    /* JADX INFO: renamed from: getMeasuredSize-YbymL2g, reason: not valid java name and from getter */
    public final long getMeasuredSize() {
        return this.measuredSize;
    }

    @Override // androidx.compose.ui.layout.Measured
    public int getMeasuredWidth() {
        return IntSize.m5844getWidthimpl(this.measuredSize);
    }

    /* JADX INFO: renamed from: getMeasurementConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getMeasurementConstraints() {
        return this.measurementConstraints;
    }

    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public /* synthetic */ Object getParentData() {
        return h.a(this);
    }

    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public abstract void mo4632placeAtf8xVGno(long position, float zIndex, r7.l<? super GraphicsLayerScope, t0> layerBlock);

    /* JADX INFO: renamed from: setMeasuredSize-ozmzZPI, reason: not valid java name */
    public final void m4667setMeasuredSizeozmzZPI(long j10) {
        if (IntSize.m5842equalsimpl0(this.measuredSize, j10)) {
            return;
        }
        this.measuredSize = j10;
        onMeasuredSizeChanged();
    }

    /* JADX INFO: renamed from: setMeasurementConstraints-BRTryo0, reason: not valid java name */
    public final void m4668setMeasurementConstraintsBRTryo0(long j10) {
        if (Constraints.m5639equalsimpl0(this.measurementConstraints, j10)) {
            return;
        }
        this.measurementConstraints = j10;
        onMeasuredSizeChanged();
    }
}
