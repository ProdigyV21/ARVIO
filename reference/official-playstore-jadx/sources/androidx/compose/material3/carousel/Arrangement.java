package androidx.compose.material3.carousel;

import h.x;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/carousel/Arrangement;", "", "priority", "", "smallSize", "", "smallCount", "mediumSize", "mediumCount", "largeSize", "largeCount", "(IFIFIFI)V", "getLargeSize", "()F", "getMediumSize", "getSmallSize", "cost", "targetLargeSize", "isValid", "", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Arrangement {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float MediumItemFlexPercentage = 0.1f;
    private final int largeCount;
    private final float largeSize;
    private final int mediumCount;
    private final float mediumSize;
    private final int priority;
    private final int smallCount;
    private final float smallSize;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JW\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014JO\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/compose/material3/carousel/Arrangement$Companion;", "", "<init>", "()V", "", "priority", "", "availableSpace", "smallCount", "smallSize", "Lh/x;", "smallSizeRange", "mediumCount", "mediumSize", "largeCount", "largeSize", "Landroidx/compose/material3/carousel/Arrangement;", "fit", "(IFIFLh/x;IFIF)Landroidx/compose/material3/carousel/Arrangement;", "calculateLargeSize", "(FIFII)F", "targetSmallSize", "", "smallCounts", "targetMediumSize", "mediumCounts", "targetLargeSize", "largeCounts", "findLowestCostArrangement", "(FFLh/x;[IF[IF[I)Landroidx/compose/material3/carousel/Arrangement;", "MediumItemFlexPercentage", "F", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final float calculateLargeSize(float availableSpace, int smallCount, float smallSize, int mediumCount, int largeCount) {
            float f10 = mediumCount / 2.0f;
            return (availableSpace - ((smallCount + f10) * smallSize)) / (largeCount + f10);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0081  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final androidx.compose.material3.carousel.Arrangement fit(int r13, float r14, int r15, float r16, h.x r17, int r18, float r19, int r20, float r21) {
            /*
                r12 = this;
                r4 = r18
                double r0 = r17.from()
                float r0 = (float) r0
                double r1 = r17.to()
                float r1 = (float) r1
                r2 = r16
                float r0 = qb.d.m(r2, r0, r1)
                r7 = r20
                float r6 = (float) r7
                float r1 = r21 * r6
                float r8 = (float) r4
                float r2 = r19 * r8
                float r2 = r2 + r1
                float r1 = (float) r15
                float r3 = r0 * r1
                float r3 = r3 + r2
                float r2 = r14 - r3
                r9 = 0
                if (r15 <= 0) goto L35
                int r3 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
                if (r3 <= 0) goto L35
                float r2 = r2 / r1
                double r10 = r17.to()
                float r1 = (float) r10
                float r1 = r1 - r0
                float r1 = java.lang.Math.min(r2, r1)
            L33:
                float r0 = r0 + r1
                goto L47
            L35:
                if (r15 <= 0) goto L47
                int r3 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
                if (r3 >= 0) goto L47
                float r2 = r2 / r1
                double r10 = r17.from()
                float r1 = (float) r10
                float r1 = r1 - r0
                float r1 = java.lang.Math.max(r2, r1)
                goto L33
            L47:
                if (r15 <= 0) goto L4f
                r3 = r0
                r1 = r14
                r2 = r15
                r5 = r7
                r0 = r12
                goto L54
            L4f:
                r3 = r9
                r0 = r12
                r1 = r14
                r2 = r15
                r5 = r7
            L54:
                float r14 = r0.calculateLargeSize(r1, r2, r3, r4, r5)
                r2 = r3
                float r3 = r14 + r2
                r0 = 1073741824(0x40000000, float:2.0)
                float r3 = r3 / r0
                if (r18 <= 0) goto L7e
                int r0 = (r14 > r21 ? 1 : (r14 == r21 ? 0 : -1))
                if (r0 != 0) goto L65
                goto L7e
            L65:
                float r0 = r21 - r14
                float r0 = r0 * r6
                r1 = 1036831949(0x3dcccccd, float:0.1)
                float r1 = r1 * r3
                float r1 = r1 * r8
                float r4 = java.lang.Math.abs(r0)
                float r1 = java.lang.Math.min(r4, r1)
                int r0 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
                if (r0 <= 0) goto L81
                float r0 = r1 / r8
                float r3 = r3 - r0
                float r1 = r1 / r6
                float r14 = r14 + r1
            L7e:
                r6 = r14
                r4 = r3
                goto L87
            L81:
                float r0 = r1 / r8
                float r3 = r3 + r0
                float r1 = r1 / r6
                float r14 = r14 - r1
                goto L7e
            L87:
                androidx.compose.material3.carousel.Arrangement r0 = new androidx.compose.material3.carousel.Arrangement
                r1 = r13
                r3 = r15
                r5 = r18
                r7 = r20
                r0.<init>(r1, r2, r3, r4, r5, r6, r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.carousel.Arrangement.Companion.fit(int, float, int, float, h.x, int, float, int, float):androidx.compose.material3.carousel.Arrangement");
        }

        public final Arrangement findLowestCostArrangement(float availableSpace, float targetSmallSize, x smallSizeRange, int[] smallCounts, float targetMediumSize, int[] mediumCounts, float targetLargeSize, int[] largeCounts) {
            Arrangement arrangement = null;
            int i10 = 1;
            for (int i11 : largeCounts) {
                int length = mediumCounts.length;
                int i12 = 0;
                while (i12 < length) {
                    int i13 = mediumCounts[i12];
                    int length2 = smallCounts.length;
                    int i14 = 0;
                    while (i14 < length2) {
                        int i15 = i12;
                        Arrangement arrangement2 = arrangement;
                        int i16 = length;
                        int i17 = length2;
                        int i18 = i14;
                        Arrangement arrangementFit = fit(i10, availableSpace, smallCounts[i14], targetSmallSize, smallSizeRange, i13, targetMediumSize, i11, targetLargeSize);
                        if (arrangement2 != null && arrangementFit.cost(targetLargeSize) >= arrangement2.cost(targetLargeSize)) {
                            arrangement = arrangement2;
                        } else {
                            if (arrangementFit.cost(targetLargeSize) == 0.0f) {
                                return arrangementFit;
                            }
                            arrangement = arrangementFit;
                        }
                        i10++;
                        i14 = i18 + 1;
                        length = i16;
                        i12 = i15;
                        length2 = i17;
                    }
                    i12++;
                }
            }
            return arrangement;
        }

        private Companion() {
        }
    }

    public Arrangement(int i10, float f10, int i11, float f11, int i12, float f12, int i13) {
        this.priority = i10;
        this.smallSize = f10;
        this.smallCount = i11;
        this.mediumSize = f11;
        this.mediumCount = i12;
        this.largeSize = f12;
        this.largeCount = i13;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float cost(float targetLargeSize) {
        if (isValid()) {
            return Math.abs(targetLargeSize - this.largeSize) * this.priority;
        }
        return Float.MAX_VALUE;
    }

    private final boolean isValid() {
        int i10 = this.largeCount;
        if (i10 <= 0 || this.smallCount <= 0 || this.mediumCount <= 0) {
            return i10 <= 0 || this.smallCount <= 0 || this.largeSize > this.smallSize;
        }
        float f10 = this.largeSize;
        float f11 = this.mediumSize;
        return f10 > f11 && f11 > this.smallSize;
    }

    public final float getLargeSize() {
        return this.largeSize;
    }

    public final float getMediumSize() {
        return this.mediumSize;
    }

    public final float getSmallSize() {
        return this.smallSize;
    }
}
