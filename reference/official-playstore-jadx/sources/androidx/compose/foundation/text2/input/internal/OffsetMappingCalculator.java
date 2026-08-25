package androidx.compose.foundation.text2.input.internal;

import a0.c;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ=\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "", "<init>", "()V", "", "offset", "", "fromSource", "Landroidx/compose/ui/text/TextRange;", "map-fzxv0v0", "(IZ)J", "map", "opOffset", "untransformedLen", "transformedLen", "mapStep-C6u-MEY", "(IIIIZ)J", "mapStep", "sourceStart", "sourceEnd", "newLength", "Lx6/t0;", "recordEditOperation", "(III)V", "mapFromSource--jx7JFs", "(I)J", "mapFromSource", "mapFromDest--jx7JFs", "mapFromDest", "Landroidx/compose/foundation/text2/input/internal/OpArray;", "ops", "[I", "opsSize", "I", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OffsetMappingCalculator {
    public static final int $stable = 8;
    private int[] ops = OpArray.m1091constructorimpl(10);
    private int opsSize;

    /* JADX INFO: renamed from: map-fzxv0v0, reason: not valid java name */
    private final long m1086mapfzxv0v0(int offset, boolean fromSource) {
        int i10;
        int iMin;
        int[] iArr = this.ops;
        int i11 = this.opsSize;
        if (i11 >= 0) {
            if (fromSource) {
                int i12 = 0;
                iMin = offset;
                while (i12 < i11) {
                    int i13 = i12 * 3;
                    int i14 = iArr[i13];
                    int i15 = iArr[i13 + 1];
                    int i16 = iArr[i13 + 2];
                    long jM1087mapStepC6uMEY = m1087mapStepC6uMEY(iMin, i14, i15, i16, fromSource);
                    long jM1087mapStepC6uMEY2 = m1087mapStepC6uMEY(offset, i14, i15, i16, fromSource);
                    int iMin2 = Math.min(TextRange.m5192getStartimpl(jM1087mapStepC6uMEY), TextRange.m5192getStartimpl(jM1087mapStepC6uMEY2));
                    int iMax = Math.max(TextRange.m5187getEndimpl(jM1087mapStepC6uMEY), TextRange.m5187getEndimpl(jM1087mapStepC6uMEY2));
                    i12++;
                    iMin = iMin2;
                    offset = iMax;
                }
            } else {
                int i17 = i11 - 1;
                iMin = offset;
                while (-1 < i17) {
                    int i18 = i17 * 3;
                    int i19 = iArr[i18];
                    int i20 = iArr[i18 + 1];
                    int i21 = iArr[i18 + 2];
                    boolean z = fromSource;
                    long jM1087mapStepC6uMEY3 = m1087mapStepC6uMEY(iMin, i19, i20, i21, z);
                    long jM1087mapStepC6uMEY4 = m1087mapStepC6uMEY(offset, i19, i20, i21, z);
                    iMin = Math.min(TextRange.m5192getStartimpl(jM1087mapStepC6uMEY3), TextRange.m5192getStartimpl(jM1087mapStepC6uMEY4));
                    offset = Math.max(TextRange.m5187getEndimpl(jM1087mapStepC6uMEY3), TextRange.m5187getEndimpl(jM1087mapStepC6uMEY4));
                    i17--;
                    fromSource = z;
                }
            }
            i10 = offset;
            offset = iMin;
        } else {
            i10 = offset;
        }
        return TextRangeKt.TextRange(offset, i10);
    }

    /* JADX INFO: renamed from: mapStep-C6u-MEY, reason: not valid java name */
    private final long m1087mapStepC6uMEY(int offset, int opOffset, int untransformedLen, int transformedLen, boolean fromSource) {
        int i10 = fromSource ? untransformedLen : transformedLen;
        if (fromSource) {
            untransformedLen = transformedLen;
        }
        return offset < opOffset ? TextRangeKt.TextRange(offset) : offset == opOffset ? i10 == 0 ? TextRangeKt.TextRange(opOffset, untransformedLen + opOffset) : TextRangeKt.TextRange(opOffset) : offset < opOffset + i10 ? untransformedLen == 0 ? TextRangeKt.TextRange(opOffset) : TextRangeKt.TextRange(opOffset, untransformedLen + opOffset) : TextRangeKt.TextRange((offset - i10) + untransformedLen);
    }

    /* JADX INFO: renamed from: mapFromDest--jx7JFs, reason: not valid java name */
    public final long m1088mapFromDestjx7JFs(int offset) {
        return m1086mapfzxv0v0(offset, false);
    }

    /* JADX INFO: renamed from: mapFromSource--jx7JFs, reason: not valid java name */
    public final long m1089mapFromSourcejx7JFs(int offset) {
        return m1086mapfzxv0v0(offset, true);
    }

    public final void recordEditOperation(int sourceStart, int sourceEnd, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException(c.i(newLength, "Expected newLen to be ≥ 0, was ").toString());
        }
        int iMin = Math.min(sourceStart, sourceEnd);
        int iMax = Math.max(iMin, sourceEnd) - iMin;
        if (iMax >= 2 || iMax != newLength) {
            int i10 = this.opsSize + 1;
            if (i10 > OpArray.m1098getSizeimpl(this.ops)) {
                this.ops = OpArray.m1093copyOfS4kM8k(this.ops, Math.max(i10 * 2, OpArray.m1098getSizeimpl(this.ops) * 2));
            }
            OpArray.m1100setimpl(this.ops, this.opsSize, iMin, iMax, newLength);
            this.opsSize = i10;
        }
    }
}
