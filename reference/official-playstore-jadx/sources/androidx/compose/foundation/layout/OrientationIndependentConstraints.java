package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Metadata;
import q7.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0007\u0010\rB\u0011\b\u0002\u0012\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000fJ;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0011J\u0015\u0010 \u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b$\u0010\"J\u0013\u0010%\u001a\u00020\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u000fJ\u001b\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\rJ\u0010\u0010)\u001a\u00020*HÖ\u0001¢\u0006\u0004\b+\u0010,R\u0012\u0010\u0006\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0005\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R\u0012\u0010\u0004\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0012\u0010\u0002\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0011R\u0016\u0010\u000e\u001a\u00020\nX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0015\u0088\u0001\u000e\u0092\u0001\u00020\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006-"}, d2 = {"Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "", "mainAxisMin", "", "mainAxisMax", "crossAxisMin", "crossAxisMax", "constructor-impl", "(IIII)J", "c", "Landroidx/compose/ui/unit/Constraints;", "orientation", "Landroidx/compose/foundation/layout/LayoutOrientation;", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)J", "value", "(J)J", "getCrossAxisMax-impl", "(J)I", "getCrossAxisMin-impl", "getMainAxisMax-impl", "getMainAxisMin-impl", "J", "copy", "copy-yUG9Ft0", "(JIIII)J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "maxHeight", "maxHeight-impl", "(JLandroidx/compose/foundation/layout/LayoutOrientation;)I", "maxWidth", "maxWidth-impl", "stretchCrossAxis", "stretchCrossAxis-q4ezo7Y", "toBoxConstraints", "toBoxConstraints-OenEA2s", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class OrientationIndependentConstraints {
    private final long value;

    private /* synthetic */ OrientationIndependentConstraints(long j10) {
        this.value = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OrientationIndependentConstraints m495boximpl(long j10) {
        return new OrientationIndependentConstraints(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static long m497constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0, reason: not valid java name */
    public static final long m499copyyUG9Ft0(long j10, int i10, int i11, int i12, int i13) {
        return m496constructorimpl(i10, i11, i12, i13);
    }

    /* JADX INFO: renamed from: copy-yUG9Ft0$default, reason: not valid java name */
    public static /* synthetic */ long m500copyyUG9Ft0$default(long j10, int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = Constraints.m5648getMinWidthimpl(j10);
        }
        int i15 = i10;
        if ((i14 & 2) != 0) {
            i11 = Constraints.m5646getMaxWidthimpl(j10);
        }
        int i16 = i11;
        if ((i14 & 4) != 0) {
            i12 = Constraints.m5647getMinHeightimpl(j10);
        }
        int i17 = i12;
        if ((i14 & 8) != 0) {
            i13 = Constraints.m5645getMaxHeightimpl(j10);
        }
        return m499copyyUG9Ft0(j10, i15, i16, i17, i13);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m501equalsimpl(long j10, Object obj) {
        return (obj instanceof OrientationIndependentConstraints) && Constraints.m5639equalsimpl0(j10, ((OrientationIndependentConstraints) obj).getValue());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m502equalsimpl0(long j10, long j11) {
        return Constraints.m5639equalsimpl0(j10, j11);
    }

    /* JADX INFO: renamed from: getCrossAxisMax-impl, reason: not valid java name */
    public static final int m503getCrossAxisMaximpl(long j10) {
        return Constraints.m5645getMaxHeightimpl(j10);
    }

    /* JADX INFO: renamed from: getCrossAxisMin-impl, reason: not valid java name */
    public static final int m504getCrossAxisMinimpl(long j10) {
        return Constraints.m5647getMinHeightimpl(j10);
    }

    /* JADX INFO: renamed from: getMainAxisMax-impl, reason: not valid java name */
    public static final int m505getMainAxisMaximpl(long j10) {
        return Constraints.m5646getMaxWidthimpl(j10);
    }

    /* JADX INFO: renamed from: getMainAxisMin-impl, reason: not valid java name */
    public static final int m506getMainAxisMinimpl(long j10) {
        return Constraints.m5648getMinWidthimpl(j10);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m507hashCodeimpl(long j10) {
        return Constraints.m5649hashCodeimpl(j10);
    }

    /* JADX INFO: renamed from: maxHeight-impl, reason: not valid java name */
    public static final int m508maxHeightimpl(long j10, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? Constraints.m5645getMaxHeightimpl(j10) : Constraints.m5646getMaxWidthimpl(j10);
    }

    /* JADX INFO: renamed from: maxWidth-impl, reason: not valid java name */
    public static final int m509maxWidthimpl(long j10, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? Constraints.m5646getMaxWidthimpl(j10) : Constraints.m5645getMaxHeightimpl(j10);
    }

    /* JADX INFO: renamed from: stretchCrossAxis-q4ezo7Y, reason: not valid java name */
    public static final long m510stretchCrossAxisq4ezo7Y(long j10) {
        return m496constructorimpl(Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10), Constraints.m5645getMaxHeightimpl(j10) != Integer.MAX_VALUE ? Constraints.m5645getMaxHeightimpl(j10) : Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10));
    }

    /* JADX INFO: renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m511toBoxConstraintsOenEA2s(long j10, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? ConstraintsKt.Constraints(Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10), Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10)) : ConstraintsKt.Constraints(Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10), Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m512toStringimpl(long j10) {
        return "OrientationIndependentConstraints(value=" + ((Object) Constraints.m5651toStringimpl(j10)) + ')';
    }

    public boolean equals(Object obj) {
        return m501equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m507hashCodeimpl(this.value);
    }

    public String toString() {
        return m512toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m496constructorimpl(int i10, int i11, int i12, int i13) {
        return m497constructorimpl(ConstraintsKt.Constraints(i10, i11, i12, i13));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m498constructorimpl(long j10, LayoutOrientation layoutOrientation) {
        LayoutOrientation layoutOrientation2 = LayoutOrientation.Horizontal;
        return m496constructorimpl(layoutOrientation == layoutOrientation2 ? Constraints.m5648getMinWidthimpl(j10) : Constraints.m5647getMinHeightimpl(j10), layoutOrientation == layoutOrientation2 ? Constraints.m5646getMaxWidthimpl(j10) : Constraints.m5645getMaxHeightimpl(j10), layoutOrientation == layoutOrientation2 ? Constraints.m5647getMinHeightimpl(j10) : Constraints.m5648getMinWidthimpl(j10), layoutOrientation == layoutOrientation2 ? Constraints.m5645getMaxHeightimpl(j10) : Constraints.m5646getMaxWidthimpl(j10));
    }
}
