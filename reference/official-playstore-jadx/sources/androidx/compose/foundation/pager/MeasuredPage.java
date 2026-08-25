package androidx.compose.foundation.pager;

import a0.c;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0015\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J+\u0010\u001f\u001a\u00020\b*\u00020\b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001bH\u0082\bø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ%\u0010$\u001a\u00020#2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002¢\u0006\u0004\b$\u0010%J\u0015\u0010(\u001a\u00020#2\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b(\u0010)J\u0015\u0010+\u001a\u00020#2\u0006\u0010*\u001a\u00020\u0002¢\u0006\u0004\b+\u0010,R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010-\u001a\u0004\b.\u0010/R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010-\u001a\u0004\b0\u0010/R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00101R\u001a\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\t\u00102R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00103\u001a\u0004\b4\u00105R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u00106R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u00107R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u00108R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u00109R\u0014\u0010:\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00109R\u0017\u0010;\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b;\u0010-\u001a\u0004\b<\u0010/R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R$\u0010 \u001a\u00020\u00022\u0006\u0010@\u001a\u00020\u00028\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b \u0010-\u001a\u0004\b\u001a\u0010/R\u0016\u0010A\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010-R\u0018\u0010D\u001a\u00020\u0002*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"Landroidx/compose/foundation/pager/MeasuredPage;", "Landroidx/compose/foundation/pager/PageInfo;", "", "index", ContentDisposition.Parameters.Size, "", "Landroidx/compose/ui/layout/Placeable;", "placeables", "Landroidx/compose/ui/unit/IntOffset;", "visualOffset", "", "key", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "", "reverseLayout", "<init>", "(IILjava/util/List;JLjava/lang/Object;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZLkotlin/jvm/internal/h;)V", "getOffset-Bjo55l4", "(I)J", "getOffset", "Lkotlin/Function1;", "mainAxisMap", "copy-4Tuh3kE", "(JLr7/l;)J", "copy", "offset", "layoutWidth", "layoutHeight", "Lx6/t0;", "position", "(III)V", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "scope", "place", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "delta", "applyScrollDelta", "(I)V", "I", "getIndex", "()I", "getSize", "Ljava/util/List;", "J", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "Landroidx/compose/ui/Alignment$Horizontal;", "Landroidx/compose/ui/Alignment$Vertical;", "Landroidx/compose/ui/unit/LayoutDirection;", "Z", "isVertical", "crossAxisSize", "getCrossAxisSize", "", "placeableOffsets", "[I", "<set-?>", "mainAxisLayoutSize", "getMainAxisSize", "(Landroidx/compose/ui/layout/Placeable;)I", "mainAxisSize", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MeasuredPage implements PageInfo {
    public static final int $stable = 8;
    private final int crossAxisSize;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private int offset;
    private final int[] placeableOffsets;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private final int size;
    private final Alignment.Vertical verticalAlignment;
    private final long visualOffset;

    public /* synthetic */ MeasuredPage(int i10, int i11, List list, long j10, Object obj, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z, h hVar) {
        this(i10, i11, list, j10, obj, orientation, horizontal, vertical, layoutDirection, z);
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m751copy4Tuh3kE(long j10, l<? super Integer, Integer> lVar) {
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(j10) : ((Number) lVar.invoke(Integer.valueOf(IntOffset.m5802getXimpl(j10)))).intValue();
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(j10);
        if (z) {
            iM5803getYimpl = ((Number) lVar.invoke(Integer.valueOf(iM5803getYimpl))).intValue();
        }
        return IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
    }

    private final int getMainAxisSize(Placeable placeable) {
        return this.isVertical ? placeable.getHeight() : placeable.getWidth();
    }

    /* JADX INFO: renamed from: getOffset-Bjo55l4, reason: not valid java name */
    private final long m752getOffsetBjo55l4(int index) {
        int[] iArr = this.placeableOffsets;
        int i10 = index * 2;
        return IntOffsetKt.IntOffset(iArr[i10], iArr[i10 + 1]);
    }

    public final void applyScrollDelta(int delta) {
        this.offset = getOffset() + delta;
        int length = this.placeableOffsets.length;
        for (int i10 = 0; i10 < length; i10++) {
            boolean z = this.isVertical;
            if ((z && i10 % 2 == 1) || (!z && i10 % 2 == 0)) {
                int[] iArr = this.placeableOffsets;
                iArr[i10] = iArr[i10] + delta;
            }
        }
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.pager.PageInfo
    public int getIndex() {
        return this.index;
    }

    public final Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.pager.PageInfo
    public int getOffset() {
        return this.offset;
    }

    public final int getSize() {
        return this.size;
    }

    public final void place(Placeable.PlacementScope scope) {
        Placeable.PlacementScope placementScope;
        int iM5802getXimpl;
        int iM5803getYimpl;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        int size = this.placeables.size();
        int i10 = 0;
        while (i10 < size) {
            Placeable placeable = this.placeables.get(i10);
            long jM752getOffsetBjo55l4 = m752getOffsetBjo55l4(i10);
            if (this.reverseLayout) {
                if (this.isVertical) {
                    iM5802getXimpl = IntOffset.m5802getXimpl(jM752getOffsetBjo55l4);
                } else {
                    iM5802getXimpl = (this.mainAxisLayoutSize - IntOffset.m5802getXimpl(jM752getOffsetBjo55l4)) - getMainAxisSize(placeable);
                }
                if (this.isVertical) {
                    iM5803getYimpl = (this.mainAxisLayoutSize - IntOffset.m5803getYimpl(jM752getOffsetBjo55l4)) - getMainAxisSize(placeable);
                } else {
                    iM5803getYimpl = IntOffset.m5803getYimpl(jM752getOffsetBjo55l4);
                }
                jM752getOffsetBjo55l4 = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
            }
            long j10 = this.visualOffset;
            long jC = c.c(IntOffset.m5803getYimpl(jM752getOffsetBjo55l4), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jM752getOffsetBjo55l4), j10);
            if (this.isVertical) {
                placementScope = scope;
                Placeable.PlacementScope.m4672placeWithLayeraW9wM$default(placementScope, placeable, jC, 0.0f, null, 6, null);
            } else {
                placementScope = scope;
                Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(placementScope, placeable, jC, 0.0f, null, 6, null);
            }
            i10++;
            scope = placementScope;
        }
    }

    public final void position(int offset, int layoutWidth, int layoutHeight) {
        int width;
        this.offset = offset;
        this.mainAxisLayoutSize = this.isVertical ? layoutHeight : layoutWidth;
        List<Placeable> list = this.placeables;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Placeable placeable = list.get(i10);
            int i11 = i10 * 2;
            if (this.isVertical) {
                int[] iArr = this.placeableOffsets;
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalAlignment");
                }
                iArr[i11] = horizontal.align(placeable.getWidth(), layoutWidth, this.layoutDirection);
                this.placeableOffsets[i11 + 1] = offset;
                width = placeable.getHeight();
            } else {
                int[] iArr2 = this.placeableOffsets;
                iArr2[i11] = offset;
                int i12 = i11 + 1;
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalAlignment");
                }
                iArr2[i12] = vertical.align(placeable.getHeight(), layoutHeight);
                width = placeable.getWidth();
            }
            offset = width + offset;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MeasuredPage(int i10, int i11, List<? extends Placeable> list, long j10, Object obj, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z) {
        this.index = i10;
        this.size = i11;
        this.placeables = list;
        this.visualOffset = j10;
        this.key = obj;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z;
        this.isVertical = orientation == Orientation.Vertical;
        int size = list.size();
        int iMax = 0;
        for (int i12 = 0; i12 < size; i12++) {
            Placeable placeable = (Placeable) list.get(i12);
            iMax = Math.max(iMax, !this.isVertical ? placeable.getHeight() : placeable.getWidth());
        }
        this.crossAxisSize = iMax;
        this.placeableOffsets = new int[this.placeables.size() * 2];
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
    }
}
