package androidx.compose.ui.unit;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J;\u0010#\u001a\u00020\u00002\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b+\u0010\tJ\u000f\u0010,\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\rR\u0011\u0010\u001a\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\tR\u0011\u0010\u001c\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\tR\u0011\u0010\u001e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\tR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u0012\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Landroidx/compose/ui/unit/Constraints;", "", "value", "", "constructor-impl", "(J)J", "focusIndex", "", "getFocusIndex-impl", "(J)I", "hasBoundedHeight", "", "getHasBoundedHeight-impl", "(J)Z", "hasBoundedWidth", "getHasBoundedWidth-impl", "hasFixedHeight", "getHasFixedHeight$annotations", "()V", "getHasFixedHeight-impl", "hasFixedWidth", "getHasFixedWidth$annotations", "getHasFixedWidth-impl", "isZero", "isZero$annotations", "isZero-impl", "maxHeight", "getMaxHeight-impl", "maxWidth", "getMaxWidth-impl", "minHeight", "getMinHeight-impl", "minWidth", "getMinWidth-impl", "getValue$annotations", "copy", "copy-Zbe2FdA", "(JIIII)J", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class Constraints {
    private static final long FocusMask = 3;
    public static final int Infinity = Integer.MAX_VALUE;
    private static final int MaxFocusBits = 18;
    private static final long MaxFocusHeight = 3;
    private static final long MaxFocusWidth = 1;
    private static final int MaxNonFocusBits = 13;
    private static final int MinFocusBits = 16;
    private static final long MinFocusHeight = 2;
    private static final int MinFocusMask = 65535;
    private static final long MinFocusWidth = 0;
    private static final int MinNonFocusBits = 15;
    private static final int MinNonFocusMask = 32767;
    private final long value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[] MinHeightOffsets = {18, 20, 17, 15};
    private static final int MaxFocusMask = 262143;
    private static final int MaxNonFocusMask = 8191;
    private static final int[] WidthMask = {65535, MaxFocusMask, 32767, MaxNonFocusMask};
    private static final int[] HeightMask = {32767, MaxNonFocusMask, 65535, MaxFocusMask};

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002J5\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 J%\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u001d\u0010&\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010(R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/ui/unit/Constraints$Companion;", "", "()V", "FocusMask", "", "HeightMask", "", "Infinity", "", "MaxFocusBits", "MaxFocusHeight", "MaxFocusMask", "MaxFocusWidth", "MaxNonFocusBits", "MaxNonFocusMask", "MinFocusBits", "MinFocusHeight", "MinFocusMask", "MinFocusWidth", "MinHeightOffsets", "MinNonFocusBits", "MinNonFocusMask", "WidthMask", "bitsNeedForSize", ContentDisposition.Parameters.Size, "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "minWidth", "maxWidth", "minHeight", "maxHeight", "createConstraints-Zbe2FdA$ui_unit_release", "(IIII)J", "fixed", "width", "height", "fixed-JhjzzOo", "(II)J", "fixedHeight", "fixedHeight-OenEA2s", "(I)J", "fixedWidth", "fixedWidth-OenEA2s", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final int bitsNeedForSize(int size) {
            if (size < Constraints.MaxNonFocusMask) {
                return 13;
            }
            if (size < 32767) {
                return 15;
            }
            if (size < 65535) {
                return 16;
            }
            if (size < Constraints.MaxFocusMask) {
                return 18;
            }
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(size, "Can't represent a size of ", " in Constraints"));
        }

        /* JADX INFO: renamed from: createConstraints-Zbe2FdA$ui_unit_release, reason: not valid java name */
        public final long m5653createConstraintsZbe2FdA$ui_unit_release(int minWidth, int maxWidth, int minHeight, int maxHeight) {
            long j10;
            int i10 = maxHeight == Integer.MAX_VALUE ? minHeight : maxHeight;
            int iBitsNeedForSize = bitsNeedForSize(i10);
            int i11 = maxWidth == Integer.MAX_VALUE ? minWidth : maxWidth;
            int iBitsNeedForSize2 = bitsNeedForSize(i11);
            if (iBitsNeedForSize + iBitsNeedForSize2 > 31) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.s("Can't represent a width of ", i11, " and height of ", i10, " in Constraints"));
            }
            if (iBitsNeedForSize2 == 13) {
                j10 = 3;
            } else if (iBitsNeedForSize2 == 18) {
                j10 = 1;
            } else if (iBitsNeedForSize2 == 15) {
                j10 = 2;
            } else {
                if (iBitsNeedForSize2 != 16) {
                    throw new IllegalStateException("Should only have the provided constants.");
                }
                j10 = 0;
            }
            int i12 = maxWidth == Integer.MAX_VALUE ? 0 : maxWidth + 1;
            int i13 = maxHeight != Integer.MAX_VALUE ? maxHeight + 1 : 0;
            int i14 = Constraints.MinHeightOffsets[(int) j10];
            return Constraints.m5635constructorimpl((((long) i12) << 33) | j10 | (((long) minWidth) << 2) | (((long) minHeight) << i14) | (((long) i13) << (i14 + 31)));
        }

        /* JADX INFO: renamed from: fixed-JhjzzOo, reason: not valid java name */
        public final long m5654fixedJhjzzOo(int width, int height) {
            if (width < 0 || height < 0) {
                throw new IllegalArgumentException(androidx.compose.foundation.c.s("width(", width, ") and height(", height, ") must be >= 0").toString());
            }
            return m5653createConstraintsZbe2FdA$ui_unit_release(width, width, height, height);
        }

        /* JADX INFO: renamed from: fixedHeight-OenEA2s, reason: not valid java name */
        public final long m5655fixedHeightOenEA2s(int height) {
            if (height >= 0) {
                return m5653createConstraintsZbe2FdA$ui_unit_release(0, Integer.MAX_VALUE, height, height);
            }
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(height, "height(", ") must be >= 0").toString());
        }

        /* JADX INFO: renamed from: fixedWidth-OenEA2s, reason: not valid java name */
        public final long m5656fixedWidthOenEA2s(int width) {
            if (width >= 0) {
                return m5653createConstraintsZbe2FdA$ui_unit_release(width, width, 0, Integer.MAX_VALUE);
            }
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(width, "width(", ") must be >= 0").toString());
        }

        private Companion() {
        }
    }

    private /* synthetic */ Constraints(long j10) {
        this.value = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Constraints m5634boximpl(long j10) {
        return new Constraints(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m5635constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA, reason: not valid java name */
    public static final long m5636copyZbe2FdA(long j10, int i10, int i11, int i12, int i13) {
        if (i12 < 0 || i10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.s("minHeight(", i12, ") and minWidth(", i10, ") must be >= 0").toString());
        }
        if (i11 < i10 && i11 != Integer.MAX_VALUE) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.r("maxWidth(", i11, ") must be >= minWidth(", i10, ')').toString());
        }
        if (i13 >= i12 || i13 == Integer.MAX_VALUE) {
            return INSTANCE.m5653createConstraintsZbe2FdA$ui_unit_release(i10, i11, i12, i13);
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.r("maxHeight(", i13, ") must be >= minHeight(", i12, ')').toString());
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA$default, reason: not valid java name */
    public static /* synthetic */ long m5637copyZbe2FdA$default(long j10, int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = m5648getMinWidthimpl(j10);
        }
        int i15 = i10;
        if ((i14 & 2) != 0) {
            i11 = m5646getMaxWidthimpl(j10);
        }
        int i16 = i11;
        if ((i14 & 4) != 0) {
            i12 = m5647getMinHeightimpl(j10);
        }
        int i17 = i12;
        if ((i14 & 8) != 0) {
            i13 = m5645getMaxHeightimpl(j10);
        }
        return m5636copyZbe2FdA(j10, i15, i16, i17, i13);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5638equalsimpl(long j10, Object obj) {
        return (obj instanceof Constraints) && j10 == ((Constraints) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5639equalsimpl0(long j10, long j11) {
        return j10 == j11;
    }

    /* JADX INFO: renamed from: getFocusIndex-impl, reason: not valid java name */
    private static final int m5640getFocusIndeximpl(long j10) {
        return (int) (j10 & 3);
    }

    /* JADX INFO: renamed from: getHasBoundedHeight-impl, reason: not valid java name */
    public static final boolean m5641getHasBoundedHeightimpl(long j10) {
        int iM5640getFocusIndeximpl = m5640getFocusIndeximpl(j10);
        return (((int) (j10 >> (MinHeightOffsets[iM5640getFocusIndeximpl] + 31))) & HeightMask[iM5640getFocusIndeximpl]) != 0;
    }

    /* JADX INFO: renamed from: getHasBoundedWidth-impl, reason: not valid java name */
    public static final boolean m5642getHasBoundedWidthimpl(long j10) {
        return (((int) (j10 >> 33)) & WidthMask[m5640getFocusIndeximpl(j10)]) != 0;
    }

    public static /* synthetic */ void getHasFixedHeight$annotations() {
    }

    /* JADX INFO: renamed from: getHasFixedHeight-impl, reason: not valid java name */
    public static final boolean m5643getHasFixedHeightimpl(long j10) {
        return m5645getMaxHeightimpl(j10) == m5647getMinHeightimpl(j10);
    }

    public static /* synthetic */ void getHasFixedWidth$annotations() {
    }

    /* JADX INFO: renamed from: getHasFixedWidth-impl, reason: not valid java name */
    public static final boolean m5644getHasFixedWidthimpl(long j10) {
        return m5646getMaxWidthimpl(j10) == m5648getMinWidthimpl(j10);
    }

    /* JADX INFO: renamed from: getMaxHeight-impl, reason: not valid java name */
    public static final int m5645getMaxHeightimpl(long j10) {
        int iM5640getFocusIndeximpl = m5640getFocusIndeximpl(j10);
        int i10 = ((int) (j10 >> (MinHeightOffsets[iM5640getFocusIndeximpl] + 31))) & HeightMask[iM5640getFocusIndeximpl];
        if (i10 == 0) {
            return Integer.MAX_VALUE;
        }
        return i10 - 1;
    }

    /* JADX INFO: renamed from: getMaxWidth-impl, reason: not valid java name */
    public static final int m5646getMaxWidthimpl(long j10) {
        int i10 = ((int) (j10 >> 33)) & WidthMask[m5640getFocusIndeximpl(j10)];
        if (i10 == 0) {
            return Integer.MAX_VALUE;
        }
        return i10 - 1;
    }

    /* JADX INFO: renamed from: getMinHeight-impl, reason: not valid java name */
    public static final int m5647getMinHeightimpl(long j10) {
        int iM5640getFocusIndeximpl = m5640getFocusIndeximpl(j10);
        return ((int) (j10 >> MinHeightOffsets[iM5640getFocusIndeximpl])) & HeightMask[iM5640getFocusIndeximpl];
    }

    /* JADX INFO: renamed from: getMinWidth-impl, reason: not valid java name */
    public static final int m5648getMinWidthimpl(long j10) {
        return ((int) (j10 >> 2)) & WidthMask[m5640getFocusIndeximpl(j10)];
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5649hashCodeimpl(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    public static /* synthetic */ void isZero$annotations() {
    }

    /* JADX INFO: renamed from: isZero-impl, reason: not valid java name */
    public static final boolean m5650isZeroimpl(long j10) {
        return m5646getMaxWidthimpl(j10) == 0 || m5645getMaxHeightimpl(j10) == 0;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5651toStringimpl(long j10) {
        int iM5646getMaxWidthimpl = m5646getMaxWidthimpl(j10);
        String strValueOf = iM5646getMaxWidthimpl == Integer.MAX_VALUE ? "Infinity" : String.valueOf(iM5646getMaxWidthimpl);
        int iM5645getMaxHeightimpl = m5645getMaxHeightimpl(j10);
        String strValueOf2 = iM5645getMaxHeightimpl != Integer.MAX_VALUE ? String.valueOf(iM5645getMaxHeightimpl) : "Infinity";
        StringBuilder sb2 = new StringBuilder("Constraints(minWidth = ");
        sb2.append(m5648getMinWidthimpl(j10));
        sb2.append(", maxWidth = ");
        sb2.append(strValueOf);
        sb2.append(", minHeight = ");
        sb2.append(m5647getMinHeightimpl(j10));
        sb2.append(", maxHeight = ");
        return androidx.compose.foundation.c.u(sb2, strValueOf2, ')');
    }

    public boolean equals(Object obj) {
        return m5638equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m5649hashCodeimpl(this.value);
    }

    public String toString() {
        return m5651toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }
}
