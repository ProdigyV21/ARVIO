package androidx.compose.material3;

import androidx.compose.material3.MenuPosition;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0081\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00128\b\u0002\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0004\b\u0010\u0010\u0011J2\u0010\u001a\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001d\u001a\u00020\u0002HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b \u0010!J@\u0010\"\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bHÆ\u0003¢\u0006\u0004\b\"\u0010#Jk\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u000628\b\u0002\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u0010\u0010(\u001a\u00020'HÖ\u0001¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b*\u0010!J\u001a\u0010.\u001a\u00020-2\b\u0010,\u001a\u0004\u0018\u00010+HÖ\u0003¢\u0006\u0004\b.\u0010/R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u00100\u001a\u0004\b1\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u0010\u001fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00104\u001a\u0004\b5\u0010!RG\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u000f\u00106\u001a\u0004\b7\u0010#R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010:R\u0014\u0010<\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010:R\u0014\u0010=\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010:R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010@R\u0014\u0010B\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010@R\u0014\u0010C\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010@R\u0014\u0010D\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010@\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006E"}, d2 = {"Landroidx/compose/material3/DropdownMenuPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "Landroidx/compose/ui/unit/DpOffset;", "contentOffset", "Landroidx/compose/ui/unit/Density;", "density", "", "verticalMargin", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntRect;", "Lx6/y;", ContentDisposition.Parameters.Name, "anchorBounds", "menuBounds", "Lx6/t0;", "onPositionCalculated", "<init>", "(JLandroidx/compose/ui/unit/Density;ILr7/p;Lkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/unit/IntSize;", "windowSize", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "popupContentSize", "Landroidx/compose/ui/unit/IntOffset;", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "calculatePosition", "component1-RKDOV3M", "()J", "component1", "component2", "()Landroidx/compose/ui/unit/Density;", "component3", "()I", "component4", "()Lr7/p;", "copy-uVxBXkw", "(JLandroidx/compose/ui/unit/Density;ILr7/p;)Landroidx/compose/material3/DropdownMenuPositionProvider;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getContentOffset-RKDOV3M", "Landroidx/compose/ui/unit/Density;", "getDensity", "I", "getVerticalMargin", "Lr7/p;", "getOnPositionCalculated", "Landroidx/compose/material3/MenuPosition$Horizontal;", "startToAnchorStart", "Landroidx/compose/material3/MenuPosition$Horizontal;", "endToAnchorEnd", "leftToWindowLeft", "rightToWindowRight", "Landroidx/compose/material3/MenuPosition$Vertical;", "topToAnchorBottom", "Landroidx/compose/material3/MenuPosition$Vertical;", "bottomToAnchorTop", "centerToAnchorTop", "topToWindowTop", "bottomToWindowBottom", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class DropdownMenuPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final MenuPosition.Vertical bottomToAnchorTop;
    private final MenuPosition.Vertical bottomToWindowBottom;
    private final MenuPosition.Vertical centerToAnchorTop;
    private final long contentOffset;
    private final Density density;
    private final MenuPosition.Horizontal endToAnchorEnd;
    private final MenuPosition.Horizontal leftToWindowLeft;
    private final p<IntRect, IntRect, t0> onPositionCalculated;
    private final MenuPosition.Horizontal rightToWindowRight;
    private final MenuPosition.Horizontal startToAnchorStart;
    private final MenuPosition.Vertical topToAnchorBottom;
    private final MenuPosition.Vertical topToWindowTop;
    private final int verticalMargin;

    /* JADX INFO: renamed from: androidx.compose.material3.DropdownMenuPositionProvider$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/unit/IntRect;", "<anonymous parameter 0>", "<anonymous parameter 1>", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<IntRect, IntRect, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(2);
        }

        public final void invoke(IntRect intRect, IntRect intRect2) {
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((IntRect) obj, (IntRect) obj2);
            return t0.f22605a;
        }
    }

    public /* synthetic */ DropdownMenuPositionProvider(long j10, Density density, int i10, p pVar, kotlin.jvm.internal.h hVar) {
        this(j10, density, i10, pVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-uVxBXkw$default, reason: not valid java name */
    public static /* synthetic */ DropdownMenuPositionProvider m1544copyuVxBXkw$default(DropdownMenuPositionProvider dropdownMenuPositionProvider, long j10, Density density, int i10, p pVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            j10 = dropdownMenuPositionProvider.contentOffset;
        }
        long j11 = j10;
        if ((i11 & 2) != 0) {
            density = dropdownMenuPositionProvider.density;
        }
        Density density2 = density;
        if ((i11 & 4) != 0) {
            i10 = dropdownMenuPositionProvider.verticalMargin;
        }
        int i12 = i10;
        if ((i11 & 8) != 0) {
            pVar = dropdownMenuPositionProvider.onPositionCalculated;
        }
        return dropdownMenuPositionProvider.m1546copyuVxBXkw(j11, density2, i12, pVar);
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo959calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        Object obj;
        Object obj2;
        int i10 = 0;
        List listE = t7.a.E(this.startToAnchorStart, this.endToAnchorEnd, IntOffset.m5802getXimpl(anchorBounds.m5825getCenternOccac()) < IntSize.m5844getWidthimpl(windowSize) / 2 ? this.leftToWindowLeft : this.rightToWindowRight);
        ArrayList arrayList = new ArrayList(listE.size());
        int size = listE.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(Integer.valueOf(((MenuPosition.Horizontal) listE.get(i11)).mo1217position95KtPRI(anchorBounds, windowSize, IntSize.m5844getWidthimpl(popupContentSize), layoutDirection)));
        }
        int size2 = arrayList.size();
        int i12 = 0;
        while (true) {
            obj = null;
            if (i12 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = arrayList.get(i12);
            int iIntValue = ((Number) obj2).intValue();
            if (iIntValue >= 0 && IntSize.m5844getWidthimpl(popupContentSize) + iIntValue <= IntSize.m5844getWidthimpl(windowSize)) {
                break;
            }
            i12++;
        }
        Integer num = (Integer) obj2;
        int iIntValue2 = num != null ? num.intValue() : ((Number) x.w0(arrayList)).intValue();
        List listE2 = t7.a.E(this.topToAnchorBottom, this.bottomToAnchorTop, this.centerToAnchorTop, IntOffset.m5803getYimpl(anchorBounds.m5825getCenternOccac()) < IntSize.m5843getHeightimpl(windowSize) / 2 ? this.topToWindowTop : this.bottomToWindowBottom);
        ArrayList arrayList2 = new ArrayList(listE2.size());
        int size3 = listE2.size();
        for (int i13 = 0; i13 < size3; i13++) {
            arrayList2.add(Integer.valueOf(((MenuPosition.Vertical) listE2.get(i13)).mo1218positionJVtK1S4(anchorBounds, windowSize, IntSize.m5843getHeightimpl(popupContentSize))));
        }
        int size4 = arrayList2.size();
        while (true) {
            if (i10 >= size4) {
                break;
            }
            Object obj3 = arrayList2.get(i10);
            int iIntValue3 = ((Number) obj3).intValue();
            if (iIntValue3 >= this.verticalMargin && IntSize.m5843getHeightimpl(popupContentSize) + iIntValue3 <= IntSize.m5843getHeightimpl(windowSize) - this.verticalMargin) {
                obj = obj3;
                break;
            }
            i10++;
        }
        Integer num2 = (Integer) obj;
        long jIntOffset = IntOffsetKt.IntOffset(iIntValue2, num2 != null ? num2.intValue() : ((Number) x.w0(arrayList2)).intValue());
        this.onPositionCalculated.invoke(anchorBounds, IntRectKt.m5834IntRectVbeCjmY(jIntOffset, popupContentSize));
        return jIntOffset;
    }

    /* JADX INFO: renamed from: component1-RKDOV3M, reason: not valid java name and from getter */
    public final long getContentOffset() {
        return this.contentOffset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Density getDensity() {
        return this.density;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public final p<IntRect, IntRect, t0> component4() {
        return this.onPositionCalculated;
    }

    /* JADX INFO: renamed from: copy-uVxBXkw, reason: not valid java name */
    public final DropdownMenuPositionProvider m1546copyuVxBXkw(long contentOffset, Density density, int verticalMargin, p<? super IntRect, ? super IntRect, t0> onPositionCalculated) {
        return new DropdownMenuPositionProvider(contentOffset, density, verticalMargin, onPositionCalculated, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DropdownMenuPositionProvider)) {
            return false;
        }
        DropdownMenuPositionProvider dropdownMenuPositionProvider = (DropdownMenuPositionProvider) other;
        return DpOffset.m5738equalsimpl0(this.contentOffset, dropdownMenuPositionProvider.contentOffset) && kotlin.jvm.internal.p.a(this.density, dropdownMenuPositionProvider.density) && this.verticalMargin == dropdownMenuPositionProvider.verticalMargin && kotlin.jvm.internal.p.a(this.onPositionCalculated, dropdownMenuPositionProvider.onPositionCalculated);
    }

    /* JADX INFO: renamed from: getContentOffset-RKDOV3M, reason: not valid java name */
    public final long m1547getContentOffsetRKDOV3M() {
        return this.contentOffset;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final p<IntRect, IntRect, t0> getOnPositionCalculated() {
        return this.onPositionCalculated;
    }

    public final int getVerticalMargin() {
        return this.verticalMargin;
    }

    public int hashCode() {
        return this.onPositionCalculated.hashCode() + ((((this.density.hashCode() + (DpOffset.m5743hashCodeimpl(this.contentOffset) * 31)) * 31) + this.verticalMargin) * 31);
    }

    public String toString() {
        return "DropdownMenuPositionProvider(contentOffset=" + ((Object) DpOffset.m5746toStringimpl(this.contentOffset)) + ", density=" + this.density + ", verticalMargin=" + this.verticalMargin + ", onPositionCalculated=" + this.onPositionCalculated + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DropdownMenuPositionProvider(long j10, Density density, int i10, p<? super IntRect, ? super IntRect, t0> pVar) {
        this.contentOffset = j10;
        this.density = density;
        this.verticalMargin = i10;
        this.onPositionCalculated = pVar;
        int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(DpOffset.m5739getXD9Ej5fM(j10));
        MenuPosition menuPosition = MenuPosition.INSTANCE;
        this.startToAnchorStart = menuPosition.startToAnchorStart(iMo279roundToPx0680j_4);
        this.endToAnchorEnd = menuPosition.endToAnchorEnd(iMo279roundToPx0680j_4);
        this.leftToWindowLeft = menuPosition.leftToWindowLeft(0);
        this.rightToWindowRight = menuPosition.rightToWindowRight(0);
        int iMo279roundToPx0680j_42 = density.mo279roundToPx0680j_4(DpOffset.m5741getYD9Ej5fM(j10));
        this.topToAnchorBottom = menuPosition.topToAnchorBottom(iMo279roundToPx0680j_42);
        this.bottomToAnchorTop = menuPosition.bottomToAnchorTop(iMo279roundToPx0680j_42);
        this.centerToAnchorTop = menuPosition.centerToAnchorTop(iMo279roundToPx0680j_42);
        this.topToWindowTop = menuPosition.topToWindowTop(i10);
        this.bottomToWindowBottom = menuPosition.bottomToWindowBottom(i10);
    }

    public /* synthetic */ DropdownMenuPositionProvider(long j10, Density density, int i10, p pVar, int i11, kotlin.jvm.internal.h hVar) {
        this(j10, density, (i11 & 4) != 0 ? density.mo279roundToPx0680j_4(MenuKt.getMenuVerticalMargin()) : i10, (i11 & 8) != 0 ? AnonymousClass2.INSTANCE : pVar, null);
    }
}
