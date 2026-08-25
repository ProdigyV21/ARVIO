package androidx.compose.material3.carousel;

import a0.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.e0;
import kotlin.collections.x;
import qb.d;
import t.h;
import t.i;
import t.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aBM\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ)\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0017R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0018R\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0018R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\u0019R\u0014\u0010\f\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/carousel/Strategy;", "", "Landroidx/compose/material3/carousel/KeylineList;", "defaultKeylines", "", "startKeylineSteps", "endKeylineSteps", "", "startShiftDistance", "endShiftDistance", "Lt/h;", "startShiftPoints", "endShiftPoints", "<init>", "(Landroidx/compose/material3/carousel/KeylineList;Ljava/util/List;Ljava/util/List;FFLt/h;Lt/h;)V", "scrollOffset", "maxScrollOffset", "", "roundToNearestStep", "getKeylineListForScrollOffset$material3_release", "(FFZ)Landroidx/compose/material3/carousel/KeylineList;", "getKeylineListForScrollOffset", "Landroidx/compose/material3/carousel/KeylineList;", "Ljava/util/List;", "F", "Lt/h;", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Strategy {
    private final KeylineList defaultKeylines;
    private final List<KeylineList> endKeylineSteps;
    private final float endShiftDistance;
    private final h endShiftPoints;
    private final List<KeylineList> startKeylineSteps;
    private final float startShiftDistance;
    private final h startShiftPoints;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001(B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ/\u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J-\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ/\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002¢\u0006\u0004\b!\u0010\"J\u001f\u0010'\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0004H\u0000¢\u0006\u0004\b%\u0010&¨\u0006)"}, d2 = {"Landroidx/compose/material3/carousel/Strategy$Companion;", "", "<init>", "()V", "Landroidx/compose/material3/carousel/KeylineList;", "defaultKeylines", "", "carouselMainAxisSize", "", "getStartKeylineSteps", "(Landroidx/compose/material3/carousel/KeylineList;F)Ljava/util/List;", "getEndKeylineSteps", "from", "", "srcIndex", "dstIndex", "moveKeylineAndCreateShiftedKeylineList", "(Landroidx/compose/material3/carousel/KeylineList;IIF)Landroidx/compose/material3/carousel/KeylineList;", "totalShiftDistance", "steps", "", "isShiftingLeft", "Lt/h;", "getStepInterpolationPoints", "(FLjava/util/List;Z)Lt/h;", "stepsCount", "shiftPoint", "interpolation", "Landroidx/compose/material3/carousel/Strategy$Companion$ShiftPointRange;", "getShiftPointRange", "(ILt/h;F)Landroidx/compose/material3/carousel/Strategy$Companion$ShiftPointRange;", "", "Landroidx/compose/material3/carousel/Keyline;", "move", "(Ljava/util/List;II)Ljava/util/List;", "keylineList", "Landroidx/compose/material3/carousel/Strategy;", "create$material3_release", "(FLandroidx/compose/material3/carousel/KeylineList;)Landroidx/compose/material3/carousel/Strategy;", "create", "ShiftPointRange", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/carousel/Strategy$Companion$ShiftPointRange;", "", "fromStepIndex", "", "toStepIndex", "steppedInterpolation", "", "(IIF)V", "getFromStepIndex", "()I", "getSteppedInterpolation", "()F", "getToStepIndex", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final /* data */ class ShiftPointRange {
            private final int fromStepIndex;
            private final float steppedInterpolation;
            private final int toStepIndex;

            public ShiftPointRange(int i10, int i11, float f10) {
                this.fromStepIndex = i10;
                this.toStepIndex = i11;
                this.steppedInterpolation = f10;
            }

            public static /* synthetic */ ShiftPointRange copy$default(ShiftPointRange shiftPointRange, int i10, int i11, float f10, int i12, Object obj) {
                if ((i12 & 1) != 0) {
                    i10 = shiftPointRange.fromStepIndex;
                }
                if ((i12 & 2) != 0) {
                    i11 = shiftPointRange.toStepIndex;
                }
                if ((i12 & 4) != 0) {
                    f10 = shiftPointRange.steppedInterpolation;
                }
                return shiftPointRange.copy(i10, i11, f10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getFromStepIndex() {
                return this.fromStepIndex;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getToStepIndex() {
                return this.toStepIndex;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final float getSteppedInterpolation() {
                return this.steppedInterpolation;
            }

            public final ShiftPointRange copy(int fromStepIndex, int toStepIndex, float steppedInterpolation) {
                return new ShiftPointRange(fromStepIndex, toStepIndex, steppedInterpolation);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ShiftPointRange)) {
                    return false;
                }
                ShiftPointRange shiftPointRange = (ShiftPointRange) other;
                return this.fromStepIndex == shiftPointRange.fromStepIndex && this.toStepIndex == shiftPointRange.toStepIndex && Float.compare(this.steppedInterpolation, shiftPointRange.steppedInterpolation) == 0;
            }

            public final int getFromStepIndex() {
                return this.fromStepIndex;
            }

            public final float getSteppedInterpolation() {
                return this.steppedInterpolation;
            }

            public final int getToStepIndex() {
                return this.toStepIndex;
            }

            public int hashCode() {
                return Float.floatToIntBits(this.steppedInterpolation) + (((this.fromStepIndex * 31) + this.toStepIndex) * 31);
            }

            public String toString() {
                StringBuilder sb2 = new StringBuilder("ShiftPointRange(fromStepIndex=");
                sb2.append(this.fromStepIndex);
                sb2.append(", toStepIndex=");
                sb2.append(this.toStepIndex);
                sb2.append(", steppedInterpolation=");
                return c.n(sb2, this.steppedInterpolation, ')');
            }
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private final List<KeylineList> getEndKeylineSteps(KeylineList defaultKeylines, float carouselMainAxisSize) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(defaultKeylines);
            if (!defaultKeylines.isLastFocalItemAtEndOfContainer(carouselMainAxisSize)) {
                int lastFocalIndex = defaultKeylines.getLastFocalIndex();
                int lastNonAnchorIndex = defaultKeylines.getLastNonAnchorIndex();
                int i10 = lastNonAnchorIndex - lastFocalIndex;
                if (i10 <= 0 && defaultKeylines.getLastFocal().getCutoff() > 0.0f) {
                    arrayList.add(moveKeylineAndCreateShiftedKeylineList(defaultKeylines, 0, 0, carouselMainAxisSize));
                    return arrayList;
                }
                for (int i11 = 0; i11 < i10; i11++) {
                    KeylineList keylineList = (KeylineList) x.w0(arrayList);
                    int i12 = lastNonAnchorIndex - i11;
                    arrayList.add(moveKeylineAndCreateShiftedKeylineList(keylineList, defaultKeylines.getLastNonAnchorIndex(), i12 < t7.a.w(defaultKeylines) ? keylineList.lastIndexBeforeFocalRangeWithSize(defaultKeylines.get(i12 + 1).getSize()) + 1 : 0, carouselMainAxisSize));
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ShiftPointRange getShiftPointRange(int stepsCount, h shiftPoint, float interpolation) {
            float fA = shiftPoint.a(0);
            Iterator it = d.X(1, stepsCount).iterator();
            while (it.hasNext()) {
                int iNextInt = ((e0) it).nextInt();
                float fA2 = shiftPoint.a(iNextInt);
                if (interpolation <= fA2) {
                    return new ShiftPointRange(iNextInt - 1, iNextInt, StrategyKt.lerp(0.0f, 1.0f, fA, fA2, interpolation));
                }
                fA = fA2;
            }
            return new ShiftPointRange(0, 0, 0.0f);
        }

        private final List<KeylineList> getStartKeylineSteps(KeylineList defaultKeylines, float carouselMainAxisSize) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(defaultKeylines);
            if (!defaultKeylines.isFirstFocalItemAtStartOfContainer()) {
                int firstNonAnchorIndex = defaultKeylines.getFirstNonAnchorIndex();
                int firstFocalIndex = defaultKeylines.getFirstFocalIndex() - firstNonAnchorIndex;
                if (firstFocalIndex <= 0 && defaultKeylines.getFirstFocal().getCutoff() > 0.0f) {
                    arrayList.add(moveKeylineAndCreateShiftedKeylineList(defaultKeylines, 0, 0, carouselMainAxisSize));
                    return arrayList;
                }
                for (int i10 = 0; i10 < firstFocalIndex; i10++) {
                    KeylineList keylineList = (KeylineList) x.w0(arrayList);
                    int i11 = firstNonAnchorIndex + i10;
                    int iW = t7.a.w(defaultKeylines);
                    if (i11 > 0) {
                        iW = keylineList.firstIndexAfterFocalRangeWithSize(defaultKeylines.get(i11 - 1).getSize()) - 1;
                    }
                    arrayList.add(moveKeylineAndCreateShiftedKeylineList(keylineList, defaultKeylines.getFirstNonAnchorIndex(), iW, carouselMainAxisSize));
                }
            }
            return arrayList;
        }

        private final h getStepInterpolationPoints(float totalShiftDistance, List<KeylineList> steps, boolean isShiftingLeft) {
            int i10 = i.f21832a;
            s sVar = new s();
            sVar.f21828a = new float[1];
            sVar.b(0.0f);
            if (totalShiftDistance != 0.0f) {
                x7.i iVarX = d.X(1, steps.size());
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iVarX, 10));
                x7.h it = iVarX.iterator();
                while (it.f22624m) {
                    int iNextInt = it.nextInt();
                    int i11 = iNextInt - 1;
                    KeylineList keylineList = steps.get(i11);
                    KeylineList keylineList2 = steps.get(iNextInt);
                    sVar.b(iNextInt == t7.a.w(steps) ? 1.0f : sVar.a(i11) + ((isShiftingLeft ? ((Keyline) x.m0(keylineList2)).getUnadjustedOffset() - ((Keyline) x.m0(keylineList)).getUnadjustedOffset() : ((Keyline) x.w0(keylineList)).getUnadjustedOffset() - ((Keyline) x.w0(keylineList2)).getUnadjustedOffset()) / totalShiftDistance));
                    arrayList.add(Boolean.TRUE);
                }
            }
            return sVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<Keyline> move(List<Keyline> list, int i10, int i11) {
            Keyline keyline = list.get(i10);
            list.remove(i10);
            list.add(i11, keyline);
            return list;
        }

        private final KeylineList moveKeylineAndCreateShiftedKeylineList(KeylineList from, int srcIndex, int dstIndex, float carouselMainAxisSize) {
            int i10 = srcIndex > dstIndex ? 1 : -1;
            return KeylineKt.keylineListOf(carouselMainAxisSize, from.getPivotIndex() + i10, from.getPivot().getOffset() + (from.get(srcIndex).getSize() * i10), new Strategy$Companion$moveKeylineAndCreateShiftedKeylineList$1(from, srcIndex, dstIndex));
        }

        public final Strategy create$material3_release(float carouselMainAxisSize, KeylineList keylineList) {
            List<KeylineList> startKeylineSteps = getStartKeylineSteps(keylineList, carouselMainAxisSize);
            List<KeylineList> endKeylineSteps = getEndKeylineSteps(keylineList, carouselMainAxisSize);
            float unadjustedOffset = ((Keyline) x.m0((List) x.w0(startKeylineSteps))).getUnadjustedOffset() - ((Keyline) x.m0(keylineList)).getUnadjustedOffset();
            float unadjustedOffset2 = ((Keyline) x.w0(keylineList)).getUnadjustedOffset() - ((Keyline) x.w0((List) x.w0(endKeylineSteps))).getUnadjustedOffset();
            return new Strategy(keylineList, startKeylineSteps, endKeylineSteps, unadjustedOffset, unadjustedOffset2, getStepInterpolationPoints(unadjustedOffset, startKeylineSteps, true), getStepInterpolationPoints(unadjustedOffset2, endKeylineSteps, false), null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ Strategy(KeylineList keylineList, List list, List list2, float f10, float f11, h hVar, h hVar2, kotlin.jvm.internal.h hVar3) {
        this(keylineList, list, list2, f10, f11, hVar, hVar2);
    }

    public static /* synthetic */ KeylineList getKeylineListForScrollOffset$material3_release$default(Strategy strategy, float f10, float f11, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        return strategy.getKeylineListForScrollOffset$material3_release(f10, f11, z);
    }

    public final KeylineList getKeylineListForScrollOffset$material3_release(float scrollOffset, float maxScrollOffset, boolean roundToNearestStep) {
        float f10 = this.startShiftDistance;
        float f11 = maxScrollOffset - this.endShiftDistance;
        if (f10 <= scrollOffset && scrollOffset <= f11) {
            return this.defaultKeylines;
        }
        float fLerp = StrategyKt.lerp(1.0f, 0.0f, 0.0f, f10, scrollOffset);
        h hVar = this.startShiftPoints;
        List<KeylineList> list = this.startKeylineSteps;
        if (scrollOffset > f11) {
            fLerp = StrategyKt.lerp(0.0f, 1.0f, f11, maxScrollOffset, scrollOffset);
            hVar = this.endShiftPoints;
            list = this.endKeylineSteps;
        }
        Companion.ShiftPointRange shiftPointRange = INSTANCE.getShiftPointRange(list.size(), hVar, fLerp);
        if (roundToNearestStep) {
            return list.get(t7.a.M(shiftPointRange.getSteppedInterpolation()) == 0 ? shiftPointRange.getFromStepIndex() : shiftPointRange.getToStepIndex());
        }
        return StrategyKt.lerp(list.get(shiftPointRange.getFromStepIndex()), list.get(shiftPointRange.getToStepIndex()), shiftPointRange.getSteppedInterpolation());
    }

    private Strategy(KeylineList keylineList, List<KeylineList> list, List<KeylineList> list2, float f10, float f11, h hVar, h hVar2) {
        this.defaultKeylines = keylineList;
        this.startKeylineSteps = list;
        this.endKeylineSteps = list2;
        this.startShiftDistance = f10;
        this.endShiftDistance = f11;
        this.startShiftPoints = hVar;
        this.endShiftPoints = hVar2;
    }
}
