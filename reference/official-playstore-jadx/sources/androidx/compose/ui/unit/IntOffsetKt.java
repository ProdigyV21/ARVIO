package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a*\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001f\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001f\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001H\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0011\u001a\u001f\u0010\u0013\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0087\u0002ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0011\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u0019\u001a\u00020\u000e*\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0018\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"IntOffset", "Landroidx/compose/ui/unit/IntOffset;", "x", "", "y", "(II)J", "lerp", TtmlNode.START, "stop", "fraction", "", "lerp-81ZRxRo", "(JJF)J", "minus", "Landroidx/compose/ui/geometry/Offset;", "offset", "minus-Nv-tHpc", "(JJ)J", "minus-oCl6YwE", "plus", "plus-Nv-tHpc", "plus-oCl6YwE", "round", "round-k-4lQ0M", "(J)J", "toOffset", "toOffset--gyyYBs", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IntOffsetKt {
    public static final long IntOffset(int i10, int i11) {
        return IntOffset.m5796constructorimpl((((long) i11) & 4294967295L) | (((long) i10) << 32));
    }

    /* JADX INFO: renamed from: lerp-81ZRxRo, reason: not valid java name */
    public static final long m5813lerp81ZRxRo(long j10, long j11, float f10) {
        return IntOffset(MathHelpersKt.lerp(IntOffset.m5802getXimpl(j10), IntOffset.m5802getXimpl(j11), f10), MathHelpersKt.lerp(IntOffset.m5803getYimpl(j10), IntOffset.m5803getYimpl(j11), f10));
    }

    /* JADX INFO: renamed from: minus-Nv-tHpc, reason: not valid java name */
    public static final long m5814minusNvtHpc(long j10, long j11) {
        return OffsetKt.Offset(Offset.m3236getXimpl(j10) - IntOffset.m5802getXimpl(j11), Offset.m3237getYimpl(j10) - IntOffset.m5803getYimpl(j11));
    }

    /* JADX INFO: renamed from: minus-oCl6YwE, reason: not valid java name */
    public static final long m5815minusoCl6YwE(long j10, long j11) {
        return OffsetKt.Offset(IntOffset.m5802getXimpl(j10) - Offset.m3236getXimpl(j11), IntOffset.m5803getYimpl(j10) - Offset.m3237getYimpl(j11));
    }

    /* JADX INFO: renamed from: plus-Nv-tHpc, reason: not valid java name */
    public static final long m5816plusNvtHpc(long j10, long j11) {
        return OffsetKt.Offset(Offset.m3236getXimpl(j10) + IntOffset.m5802getXimpl(j11), Offset.m3237getYimpl(j10) + IntOffset.m5803getYimpl(j11));
    }

    /* JADX INFO: renamed from: plus-oCl6YwE, reason: not valid java name */
    public static final long m5817plusoCl6YwE(long j10, long j11) {
        return OffsetKt.Offset(Offset.m3236getXimpl(j11) + IntOffset.m5802getXimpl(j10), Offset.m3237getYimpl(j11) + IntOffset.m5803getYimpl(j10));
    }

    /* JADX INFO: renamed from: round-k-4lQ0M, reason: not valid java name */
    public static final long m5818roundk4lQ0M(long j10) {
        return IntOffset(t7.a.M(Offset.m3236getXimpl(j10)), t7.a.M(Offset.m3237getYimpl(j10)));
    }

    /* JADX INFO: renamed from: toOffset--gyyYBs, reason: not valid java name */
    public static final long m5819toOffsetgyyYBs(long j10) {
        return OffsetKt.Offset(IntOffset.m5802getXimpl(j10), IntOffset.m5803getYimpl(j10));
    }
}
