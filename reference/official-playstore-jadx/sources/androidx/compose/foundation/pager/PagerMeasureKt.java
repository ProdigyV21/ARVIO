package androidx.compose.foundation.pager;

import a0.c;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayoutKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.p;
import kotlin.collections.r;
import kotlin.collections.z;
import qb.d;
import r7.l;
import r7.q;
import x6.t0;
import x7.g;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\u001aé\u0001\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2/\u0010%\u001a+\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 ¢\u0006\u0002\b#\u0012\u0004\u0012\u00020$0\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001aO\u0010.\u001a\b\u0012\u0004\u0012\u00020,0\u00192\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00192\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020,0 H\u0002¢\u0006\u0004\b.\u0010/\u001aG\u00101\u001a\b\u0012\u0004\u0012\u00020,0\u00192\u0006\u00100\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00192\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020,0 H\u0002¢\u0006\u0004\b1\u00102\u001aG\u00106\u001a\u0004\u0018\u00010,2\u0006\u00103\u001a\u00020\u00012\f\u00104\u001a\b\u0012\u0004\u0012\u00020,0\u00192\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u00105\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b6\u00107\u001aj\u0010-\u001a\u00020,*\u00020\u00002\u0006\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u0093\u0001\u0010I\u001a\b\u0012\u0004\u0012\u00020,0H*\u00020\u00002\f\u0010>\u001a\b\u0012\u0004\u0012\u00020,0\u00192\f\u0010?\u001a\b\u0012\u0004\u0012\u00020,0\u00192\f\u0010@\u001a\b\u0012\u0004\u0012\u00020,0\u00192\u0006\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00012\u0006\u0010D\u001a\u00020\u00012\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010G\u001a\u00020F2\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0002¢\u0006\u0004\bI\u0010J\u001a\u001e\u0010N\u001a\u00020\"2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020L0KH\u0082\b¢\u0006\u0004\bN\u0010O\"\u0014\u0010Q\u001a\u00020P8\u0000X\u0080T¢\u0006\u0006\n\u0004\bQ\u0010R\"\u0014\u0010S\u001a\u00020P8\u0000X\u0080T¢\u0006\u0006\n\u0004\bS\u0010R\"\u0014\u0010T\u001a\u00020\u00138\u0002X\u0082T¢\u0006\u0006\n\u0004\bT\u0010U\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006V"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "", "pageCount", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pagerItemProvider", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenPages", "currentPage", "currentPageOffset", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "", "reverseLayout", "Landroidx/compose/ui/unit/IntOffset;", "visualPageOffset", "pageAvailableSize", "beyondBoundsPageCount", "", "pinnedPages", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "snapPositionInLayout", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "placementScopeInvalidator", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "Lx6/n;", "Landroidx/compose/ui/layout/MeasureResult;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/foundation/pager/PagerMeasureResult;", "measurePager-_JDW0YA", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;IIIIIIJLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;ZJIILjava/util/List;Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;Landroidx/compose/runtime/MutableState;Lr7/q;)Landroidx/compose/foundation/pager/PagerMeasureResult;", "measurePager", "currentLastPage", "pagesCount", "Landroidx/compose/foundation/pager/MeasuredPage;", "getAndMeasure", "createPagesAfterList", "(IIILjava/util/List;Lr7/l;)Ljava/util/List;", "currentFirstPage", "createPagesBeforeList", "(IILjava/util/List;Lr7/l;)Ljava/util/List;", "viewportSize", "visiblePagesInfo", "itemSize", "calculateNewCurrentPage", "(ILjava/util/List;IIILandroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;)Landroidx/compose/foundation/pager/MeasuredPage;", "index", "childConstraints", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getAndMeasure-SGf7dI0", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJLandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZI)Landroidx/compose/foundation/pager/MeasuredPage;", "pages", "extraPagesBefore", "extraPagesAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "pagesScrollOffset", "Landroidx/compose/ui/unit/Density;", "density", "", "calculatePagesOffsets", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Ljava/util/List;Ljava/util/List;Ljava/util/List;IIIIILandroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/Density;II)Ljava/util/List;", "Lkotlin/Function0;", "", "generateMsg", "debugLog", "(Lr7/a;)V", "", "MinPageOffset", "F", "MaxPageOffset", "DEBUG", "Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasureKt {
    private static final boolean DEBUG = false;
    public static final float MaxPageOffset = 0.5f;
    public static final float MinPageOffset = -0.5f;

    private static final MeasuredPage calculateNewCurrentPage(int i10, List<MeasuredPage> list, int i11, int i12, int i13, SnapPositionInLayout snapPositionInLayout) {
        MeasuredPage measuredPage;
        if (list.isEmpty()) {
            measuredPage = null;
        } else {
            MeasuredPage measuredPage2 = list.get(0);
            MeasuredPage measuredPage3 = measuredPage2;
            float f10 = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(i10, i11, i12, i13, measuredPage3.getOffset(), measuredPage3.getIndex(), snapPositionInLayout));
            int iW = t7.a.w(list);
            int i14 = 1;
            if (1 <= iW) {
                while (true) {
                    MeasuredPage measuredPage4 = list.get(i14);
                    MeasuredPage measuredPage5 = measuredPage4;
                    float f11 = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(i10, i11, i12, i13, measuredPage5.getOffset(), measuredPage5.getIndex(), snapPositionInLayout));
                    if (Float.compare(f10, f11) < 0) {
                        measuredPage2 = measuredPage4;
                        f10 = f11;
                    }
                    if (i14 == iW) {
                        break;
                    }
                    i14++;
                }
            }
            measuredPage = measuredPage2;
        }
        return measuredPage;
    }

    private static final List<MeasuredPage> calculatePagesOffsets(LazyLayoutMeasureScope lazyLayoutMeasureScope, List<MeasuredPage> list, List<MeasuredPage> list2, List<MeasuredPage> list3, int i10, int i11, int i12, int i13, int i14, Orientation orientation, boolean z, Density density, int i15, int i16) {
        int i17 = i14;
        int i18 = i16 + i15;
        int i19 = orientation == Orientation.Vertical ? i11 : i10;
        boolean z5 = i12 < Math.min(i19, i13);
        if (z5 && i17 != 0) {
            throw new IllegalStateException(c.i(i17, "non-zero pagesScrollOffset=").toString());
        }
        ArrayList arrayList = new ArrayList(list3.size() + list2.size() + list.size());
        if (!z5) {
            int size = list2.size();
            int i20 = i17;
            for (int i21 = 0; i21 < size; i21++) {
                MeasuredPage measuredPage = list2.get(i21);
                i20 -= i18;
                measuredPage.position(i20, i10, i11);
                arrayList.add(measuredPage);
            }
            int size2 = list.size();
            for (int i22 = 0; i22 < size2; i22++) {
                MeasuredPage measuredPage2 = list.get(i22);
                measuredPage2.position(i17, i10, i11);
                arrayList.add(measuredPage2);
                i17 += i18;
            }
            int size3 = list3.size();
            for (int i23 = 0; i23 < size3; i23++) {
                MeasuredPage measuredPage3 = list3.get(i23);
                measuredPage3.position(i17, i10, i11);
                arrayList.add(measuredPage3);
                i17 += i18;
            }
        } else {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("No extra pages");
            }
            int size4 = list.size();
            int[] iArr = new int[size4];
            for (int i24 = 0; i24 < size4; i24++) {
                iArr[i24] = i16;
            }
            int[] iArr2 = new int[size4];
            for (int i25 = 0; i25 < size4; i25++) {
                iArr2[i25] = 0;
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM444spacedBy0680j_4 = Arrangement.Absolute.INSTANCE.m444spacedBy0680j_4(lazyLayoutMeasureScope.mo282toDpu2uoSUM(i15));
            if (orientation == Orientation.Vertical) {
                horizontalOrVerticalM444spacedBy0680j_4.arrange(density, i19, iArr, iArr2);
            } else {
                horizontalOrVerticalM444spacedBy0680j_4.arrange(density, i19, iArr, LayoutDirection.Ltr, iArr2);
            }
            i iVarA0 = r.a0(iArr2);
            g gVarP = iVarA0;
            if (z) {
                gVarP = d.P(iVarA0);
            }
            int i26 = gVarP.f22619i;
            int i27 = gVarP.f22620l;
            int i28 = gVarP.f22621m;
            if ((i28 > 0 && i26 <= i27) || (i28 < 0 && i27 <= i26)) {
                while (true) {
                    int size5 = iArr2[i26];
                    MeasuredPage measuredPage4 = list.get(calculatePagesOffsets$reverseAware(i26, z, size4));
                    if (z) {
                        size5 = (i19 - size5) - measuredPage4.getSize();
                    }
                    measuredPage4.position(size5, i10, i11);
                    arrayList.add(measuredPage4);
                    if (i26 == i27) {
                        break;
                    }
                    i26 += i28;
                }
            }
        }
        return arrayList;
    }

    private static final int calculatePagesOffsets$reverseAware(int i10, boolean z, int i11) {
        return !z ? i10 : (i11 - i10) - 1;
    }

    private static final List<MeasuredPage> createPagesAfterList(int i10, int i11, int i12, List<Integer> list, l<? super Integer, MeasuredPage> lVar) {
        int iMin = Math.min(i12 + i10, i11 - 1);
        int i13 = i10 + 1;
        ArrayList arrayList = null;
        if (i13 <= iMin) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVar.invoke(Integer.valueOf(i13)));
                if (i13 == iMin) {
                    break;
                }
                i13++;
            }
        }
        int size = list.size();
        for (int i14 = 0; i14 < size; i14++) {
            int iIntValue = list.get(i14).intValue();
            if (iMin + 1 <= iIntValue && iIntValue < i11) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVar.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    private static final List<MeasuredPage> createPagesBeforeList(int i10, int i11, List<Integer> list, l<? super Integer, MeasuredPage> lVar) {
        int iMax = Math.max(0, i10 - i11);
        int i12 = i10 - 1;
        ArrayList arrayList = null;
        if (iMax <= i12) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVar.invoke(Integer.valueOf(i12)));
                if (i12 == iMax) {
                    break;
                }
                i12--;
            }
        }
        int size = list.size();
        for (int i13 = 0; i13 < size; i13++) {
            int iIntValue = list.get(i13).intValue();
            if (iIntValue < iMax) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lVar.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAndMeasure-SGf7dI0, reason: not valid java name */
    public static final MeasuredPage m759getAndMeasureSGf7dI0(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i10, long j10, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, long j11, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z, int i11) {
        return new MeasuredPage(i10, i11, lazyLayoutMeasureScope.mo691measure0kLqBqw(i10, j10), j11, pagerLazyLayoutItemProvider.getKey(i10), orientation, horizontal, vertical, layoutDirection, z, null);
    }

    /* JADX INFO: renamed from: measurePager-_JDW0YA, reason: not valid java name */
    public static final PagerMeasureResult m760measurePager_JDW0YA(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i10, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, int i11, int i12, int i13, int i14, int i15, int i16, long j10, Orientation orientation, Alignment.Vertical vertical, Alignment.Horizontal horizontal, boolean z, long j11, int i17, int i18, List<Integer> list, SnapPositionInLayout snapPositionInLayout, MutableState<t0> mutableState, q<? super Integer, ? super Integer, ? super l<? super Placeable.PlacementScope, t0>, ? extends MeasureResult> qVar) {
        int i19;
        int iMax;
        int i20;
        int i21;
        int i22;
        List<MeasuredPage> arrayList;
        List<MeasuredPage> list2;
        int i23;
        int i24;
        if (i12 < 0) {
            throw new IllegalArgumentException("negative beforeContentPadding");
        }
        if (i13 < 0) {
            throw new IllegalArgumentException("negative afterContentPadding");
        }
        int i25 = i17 + i14;
        if (i25 < 0) {
            i25 = 0;
        }
        if (i10 <= 0) {
            return new PagerMeasureResult(z.f19728i, i17, i14, i13, orientation, -i12, i11 + i13, false, i18, null, null, 0.0f, 0, false, (MeasureResult) qVar.invoke(Integer.valueOf(Constraints.m5648getMinWidthimpl(j10)), Integer.valueOf(Constraints.m5647getMinHeightimpl(j10)), PagerMeasureKt$measurePager$4.INSTANCE), false);
        }
        Orientation orientation2 = orientation;
        Orientation orientation3 = Orientation.Vertical;
        long jConstraints$default = ConstraintsKt.Constraints$default(0, orientation2 == orientation3 ? Constraints.m5646getMaxWidthimpl(j10) : i17, 0, orientation2 != orientation3 ? Constraints.m5645getMaxHeightimpl(j10) : i17, 5, null);
        int i26 = i15;
        int i27 = i16;
        while (i26 > 0 && i27 > 0) {
            i26--;
            i27 -= i25;
        }
        int i28 = i27 * (-1);
        if (i26 >= i10) {
            i26 = i10 - 1;
            i28 = 0;
        }
        p pVar = new p();
        int i29 = -i12;
        int i30 = (i14 < 0 ? i14 : 0) + i29;
        int i31 = i28 + i30;
        int iMax2 = 0;
        while (i31 < 0 && i26 > 0) {
            int i32 = i26 - 1;
            MeasuredPage measuredPageM759getAndMeasureSGf7dI0 = m759getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i32, jConstraints$default, pagerLazyLayoutItemProvider, j11, orientation2, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i17);
            pVar.add(0, measuredPageM759getAndMeasureSGf7dI0);
            iMax2 = Math.max(iMax2, measuredPageM759getAndMeasureSGf7dI0.getCrossAxisSize());
            i31 += i25;
            orientation2 = orientation;
            i26 = i32;
        }
        int i33 = iMax2;
        if (i31 < i30) {
            i31 = i30;
        }
        int i34 = i31 - i30;
        int i35 = i11 + i13;
        int i36 = i35 >= 0 ? i35 : 0;
        int i37 = -i34;
        int i38 = i26;
        int i39 = i38;
        int i40 = 0;
        boolean z5 = false;
        while (i40 < pVar.size()) {
            if (i37 >= i36) {
                pVar.remove(i40);
                z5 = true;
            } else {
                i39++;
                i37 += i25;
                i40++;
            }
        }
        int i41 = i39;
        boolean z10 = z5;
        int iMax3 = i33;
        while (i41 < i10 && (i37 < i36 || i37 <= 0 || pVar.isEmpty())) {
            int i42 = i41;
            MeasuredPage measuredPageM759getAndMeasureSGf7dI02 = m759getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i42, jConstraints$default, pagerLazyLayoutItemProvider, j11, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i17);
            int i43 = i36;
            int i44 = i34;
            int i45 = i10 - 1;
            i37 += i42 == i45 ? i17 : i25;
            if (i37 > i30 || i42 == i45) {
                iMax3 = Math.max(iMax3, measuredPageM759getAndMeasureSGf7dI02.getCrossAxisSize());
                pVar.addLast(measuredPageM759getAndMeasureSGf7dI02);
                i24 = i38;
                i34 = i44;
            } else {
                i24 = i42 + 1;
                i34 = i44 - i25;
                z10 = true;
            }
            i38 = i24;
            i41 = i42 + 1;
            i36 = i43;
        }
        int i46 = i41;
        int i47 = i34;
        if (i37 < i11) {
            int i48 = i11 - i37;
            i19 = i47 - i48;
            i37 += i48;
            iMax = iMax3;
            i20 = i38;
            while (i19 < i12 && i20 > 0) {
                int i49 = i20 - 1;
                MeasuredPage measuredPageM759getAndMeasureSGf7dI03 = m759getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i49, jConstraints$default, pagerLazyLayoutItemProvider, j11, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i17);
                pVar.add(0, measuredPageM759getAndMeasureSGf7dI03);
                iMax = Math.max(iMax, measuredPageM759getAndMeasureSGf7dI03.getCrossAxisSize());
                i19 += i25;
                i20 = i49;
            }
            if (i19 < 0) {
                i37 += i19;
                i19 = 0;
            }
        } else {
            i19 = i47;
            iMax = iMax3;
            i20 = i38;
        }
        if (i19 < 0) {
            throw new IllegalArgumentException("invalid currentFirstPageScrollOffset");
        }
        int i50 = -i19;
        MeasuredPage measuredPage = (MeasuredPage) pVar.first();
        if (i12 > 0 || i14 < 0) {
            i21 = i37;
            int size = pVar.size();
            i22 = i46;
            int i51 = 0;
            while (i51 < size && i19 != 0 && i25 <= i19) {
                int i52 = size;
                if (i51 == t7.a.w(pVar)) {
                    break;
                }
                i19 -= i25;
                i51++;
                measuredPage = (MeasuredPage) pVar.get(i51);
                size = i52;
            }
        } else {
            i21 = i37;
            i22 = i46;
        }
        int i53 = i19;
        MeasuredPage measuredPage2 = measuredPage;
        List<MeasuredPage> listCreatePagesBeforeList = createPagesBeforeList(i20, i18, list, new PagerMeasureKt$measurePager$extraPagesBefore$1(lazyLayoutMeasureScope, jConstraints$default, pagerLazyLayoutItemProvider, j11, orientation, horizontal, vertical, z, i17));
        int size2 = listCreatePagesBeforeList.size();
        int i54 = 0;
        while (i54 < size2) {
            iMax = Math.max(iMax, listCreatePagesBeforeList.get(i54).getCrossAxisSize());
            i54++;
            i25 = i25;
        }
        int i55 = i25;
        int iMax4 = iMax;
        List<MeasuredPage> listCreatePagesAfterList = createPagesAfterList(((MeasuredPage) pVar.last()).getIndex(), i10, i18, list, new PagerMeasureKt$measurePager$extraPagesAfter$1(lazyLayoutMeasureScope, jConstraints$default, pagerLazyLayoutItemProvider, j11, orientation, horizontal, vertical, z, i17));
        int size3 = listCreatePagesAfterList.size();
        int i56 = 0;
        while (i56 < size3) {
            iMax4 = Math.max(iMax4, listCreatePagesAfterList.get(i56).getCrossAxisSize());
            i56++;
            listCreatePagesAfterList = listCreatePagesAfterList;
        }
        List<MeasuredPage> list3 = listCreatePagesAfterList;
        boolean z11 = kotlin.jvm.internal.p.a(measuredPage2, pVar.first()) && listCreatePagesBeforeList.isEmpty() && list3.isEmpty();
        Orientation orientation4 = Orientation.Vertical;
        int iM5660constrainWidthK40F9xA = ConstraintsKt.m5660constrainWidthK40F9xA(j10, orientation == orientation4 ? iMax4 : i21);
        if (orientation == orientation4) {
            iMax4 = i21;
        }
        int iM5659constrainHeightK40F9xA = ConstraintsKt.m5659constrainHeightK40F9xA(j10, iMax4);
        int i57 = i21;
        int i58 = i22;
        List<MeasuredPage> listCalculatePagesOffsets = calculatePagesOffsets(lazyLayoutMeasureScope, pVar, listCreatePagesBeforeList, list3, iM5660constrainWidthK40F9xA, iM5659constrainHeightK40F9xA, i57, i11, i50, orientation, z, lazyLayoutMeasureScope, i14, i17);
        if (z11) {
            arrayList = listCalculatePagesOffsets;
        } else {
            arrayList = new ArrayList<>(listCalculatePagesOffsets.size());
            int size4 = listCalculatePagesOffsets.size();
            for (int i59 = 0; i59 < size4; i59++) {
                MeasuredPage measuredPage3 = listCalculatePagesOffsets.get(i59);
                MeasuredPage measuredPage4 = measuredPage3;
                if (measuredPage4.getIndex() >= ((MeasuredPage) pVar.first()).getIndex() && measuredPage4.getIndex() <= ((MeasuredPage) pVar.last()).getIndex()) {
                    arrayList.add(measuredPage3);
                }
            }
        }
        if (orientation == Orientation.Vertical) {
            list2 = arrayList;
            i23 = iM5659constrainHeightK40F9xA;
        } else {
            list2 = arrayList;
            i23 = iM5660constrainWidthK40F9xA;
        }
        MeasuredPage measuredPageCalculateNewCurrentPage = calculateNewCurrentPage(i23, list2, i12, i13, i55, snapPositionInLayout);
        return new PagerMeasureResult(list2, i17, i14, i13, orientation, i29, i35, z, i18, measuredPage2, measuredPageCalculateNewCurrentPage, i55 == 0 ? 0.0f : d.m((-(measuredPageCalculateNewCurrentPage != null ? measuredPageCalculateNewCurrentPage.getOffset() : 0)) / i55, -0.5f, 0.5f), i53, i58 < i10 || i57 > i11, (MeasureResult) qVar.invoke(Integer.valueOf(iM5660constrainWidthK40F9xA), Integer.valueOf(iM5659constrainHeightK40F9xA), new PagerMeasureKt$measurePager$9(listCalculatePagesOffsets, mutableState)), z10);
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}
