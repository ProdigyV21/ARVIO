package androidx.compose.ui.unit;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\b\u001a5\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u0002\u001a\u001c\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001e\u0010\u000b\u001a\u00020\u000f*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000e\u001a\u001e\u0010\u0012\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001e\u0010\u0016\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0015\u001a\u001e\u0010\u0019\u001a\u00020\u001a*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a*\u0010\u001d\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Constraints", "Landroidx/compose/ui/unit/Constraints;", "minWidth", "", "maxWidth", "minHeight", "maxHeight", "(IIII)J", "addMaxWithMinimum", "max", "value", "constrain", "otherConstraints", "constrain-N9IONVI", "(JJ)J", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "constrain-4WqzIAM", "constrainHeight", "height", "constrainHeight-K40F9xA", "(JI)I", "constrainWidth", "width", "constrainWidth-K40F9xA", "isSatisfiedBy", "", "isSatisfiedBy-4WqzIAM", "(JJ)Z", "offset", "horizontal", "vertical", "offset-NN6Ew-U", "(JII)J", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConstraintsKt {
    public static final long Constraints(int i10, int i11, int i12, int i13) {
        if (i11 < i10) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.r("maxWidth(", i11, ") must be >= than minWidth(", i10, ')').toString());
        }
        if (i13 < i12) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.r("maxHeight(", i13, ") must be >= than minHeight(", i12, ')').toString());
        }
        if (i10 < 0 || i12 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.s("minWidth(", i10, ") and minHeight(", i12, ") must be >= 0").toString());
        }
        return Constraints.INSTANCE.m5653createConstraintsZbe2FdA$ui_unit_release(i10, i11, i12, i13);
    }

    public static /* synthetic */ long Constraints$default(int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = 0;
        }
        if ((i14 & 2) != 0) {
            i11 = Integer.MAX_VALUE;
        }
        if ((i14 & 4) != 0) {
            i12 = 0;
        }
        if ((i14 & 8) != 0) {
            i13 = Integer.MAX_VALUE;
        }
        return Constraints(i10, i11, i12, i13);
    }

    private static final int addMaxWithMinimum(int i10, int i11) {
        if (i10 == Integer.MAX_VALUE) {
            return i10;
        }
        int i12 = i10 + i11;
        if (i12 < 0) {
            return 0;
        }
        return i12;
    }

    /* JADX INFO: renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m5657constrain4WqzIAM(long j10, long j11) {
        return IntSizeKt.IntSize(d.n(IntSize.m5844getWidthimpl(j11), Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10)), d.n(IntSize.m5843getHeightimpl(j11), Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10)));
    }

    /* JADX INFO: renamed from: constrain-N9IONVI, reason: not valid java name */
    public static final long m5658constrainN9IONVI(long j10, long j11) {
        return Constraints(d.n(Constraints.m5648getMinWidthimpl(j11), Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10)), d.n(Constraints.m5646getMaxWidthimpl(j11), Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10)), d.n(Constraints.m5647getMinHeightimpl(j11), Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10)), d.n(Constraints.m5645getMaxHeightimpl(j11), Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10)));
    }

    /* JADX INFO: renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final int m5659constrainHeightK40F9xA(long j10, int i10) {
        return d.n(i10, Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10));
    }

    /* JADX INFO: renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final int m5660constrainWidthK40F9xA(long j10, int i10) {
        return d.n(i10, Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10));
    }

    /* JADX INFO: renamed from: isSatisfiedBy-4WqzIAM, reason: not valid java name */
    public static final boolean m5661isSatisfiedBy4WqzIAM(long j10, long j11) {
        int iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j10);
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5844getWidthimpl = IntSize.m5844getWidthimpl(j11);
        if (iM5648getMinWidthimpl > iM5844getWidthimpl || iM5844getWidthimpl > iM5646getMaxWidthimpl) {
            return false;
        }
        int iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10);
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        int iM5843getHeightimpl = IntSize.m5843getHeightimpl(j11);
        return iM5647getMinHeightimpl <= iM5843getHeightimpl && iM5843getHeightimpl <= iM5645getMaxHeightimpl;
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U, reason: not valid java name */
    public static final long m5662offsetNN6EwU(long j10, int i10, int i11) {
        int iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j10) + i10;
        if (iM5648getMinWidthimpl < 0) {
            iM5648getMinWidthimpl = 0;
        }
        int iAddMaxWithMinimum = addMaxWithMinimum(Constraints.m5646getMaxWidthimpl(j10), i10);
        int iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j10) + i11;
        return Constraints(iM5648getMinWidthimpl, iAddMaxWithMinimum, iM5647getMinHeightimpl >= 0 ? iM5647getMinHeightimpl : 0, addMaxWithMinimum(Constraints.m5645getMaxHeightimpl(j10), i11));
    }

    /* JADX INFO: renamed from: offset-NN6Ew-U$default, reason: not valid java name */
    public static /* synthetic */ long m5663offsetNN6EwU$default(long j10, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        return m5662offsetNN6EwU(j10, i10, i11);
    }
}
