package androidx.compose.material3.carousel;

import a0.c;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001:\u0001+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006JS\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ%\u0010 \u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b \u0010!J\u001d\u0010$\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b$\u0010%R\u0016\u0010\n\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010&R\u0016\u0010'\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010\u0007\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010&R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010(R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010*¨\u0006,"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl;", "Landroidx/compose/material3/carousel/KeylineListScope;", "<init>", "()V", "", "findLastFocalIndex", "()I", "pivotIndex", "", "pivotOffset", "firstFocalIndex", "lastFocalIndex", "itemMainAxisSize", "carouselMainAxisSize", "", "Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "tmpKeylines", "Landroidx/compose/material3/carousel/Keyline;", "createKeylinesWithPivot", "(IFIIFFLjava/util/List;)Ljava/util/List;", ContentDisposition.Parameters.Size, "offset", "", "isCutoffLeft", "(FF)Z", "isCutoffRight", "(FFF)Z", "isAnchor", "Lx6/t0;", "add", "(FZ)V", "Landroidx/compose/material3/carousel/KeylineList;", "createWithPivot", "(FIF)Landroidx/compose/material3/carousel/KeylineList;", "Landroidx/compose/material3/carousel/CarouselAlignment;", "carouselAlignment", "createWithAlignment", "(FLandroidx/compose/material3/carousel/CarouselAlignment;)Landroidx/compose/material3/carousel/KeylineList;", "I", "focalItemSize", "F", "", "Ljava/util/List;", "TmpKeyline", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class KeylineListScopeImpl implements KeylineListScope {
    private float focalItemSize;
    private float pivotOffset;
    private int firstFocalIndex = -1;
    private int pivotIndex = -1;
    private final List<TmpKeyline> tmpKeylines = new ArrayList();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/carousel/KeylineListScopeImpl$TmpKeyline;", "", ContentDisposition.Parameters.Size, "", "isAnchor", "", "(FZ)V", "()Z", "getSize", "()F", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class TmpKeyline {
        private final boolean isAnchor;
        private final float size;

        public TmpKeyline(float f10, boolean z) {
            this.size = f10;
            this.isAnchor = z;
        }

        public static /* synthetic */ TmpKeyline copy$default(TmpKeyline tmpKeyline, float f10, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                f10 = tmpKeyline.size;
            }
            if ((i10 & 2) != 0) {
                z = tmpKeyline.isAnchor;
            }
            return tmpKeyline.copy(f10, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final float getSize() {
            return this.size;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsAnchor() {
            return this.isAnchor;
        }

        public final TmpKeyline copy(float size, boolean isAnchor) {
            return new TmpKeyline(size, isAnchor);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TmpKeyline)) {
                return false;
            }
            TmpKeyline tmpKeyline = (TmpKeyline) other;
            return Float.compare(this.size, tmpKeyline.size) == 0 && this.isAnchor == tmpKeyline.isAnchor;
        }

        public final float getSize() {
            return this.size;
        }

        public int hashCode() {
            return (Float.floatToIntBits(this.size) * 31) + (this.isAnchor ? 1231 : 1237);
        }

        public final boolean isAnchor() {
            return this.isAnchor;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("TmpKeyline(size=");
            sb2.append(this.size);
            sb2.append(", isAnchor=");
            return c.r(sb2, this.isAnchor, ')');
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CarouselAlignment.values().length];
            try {
                iArr[CarouselAlignment.Start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CarouselAlignment.Center.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CarouselAlignment.End.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<androidx.compose.material3.carousel.Keyline> createKeylinesWithPivot(int r28, float r29, int r30, int r31, float r32, float r33, java.util.List<androidx.compose.material3.carousel.KeylineListScopeImpl.TmpKeyline> r34) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.carousel.KeylineListScopeImpl.createKeylinesWithPivot(int, float, int, int, float, float, java.util.List):java.util.List");
    }

    private final int findLastFocalIndex() {
        int i10 = this.firstFocalIndex;
        while (i10 < t7.a.w(this.tmpKeylines)) {
            int i11 = i10 + 1;
            if (this.tmpKeylines.get(i11).getSize() != this.focalItemSize) {
                break;
            }
            i10 = i11;
        }
        return i10;
    }

    private final boolean isCutoffLeft(float size, float offset) {
        float f10 = size / 2;
        return offset - f10 < 0.0f && offset + f10 > 0.0f;
    }

    private final boolean isCutoffRight(float size, float offset, float carouselMainAxisSize) {
        float f10 = size / 2;
        return offset - f10 < carouselMainAxisSize && offset + f10 > carouselMainAxisSize;
    }

    @Override // androidx.compose.material3.carousel.KeylineListScope
    public void add(float size, boolean isAnchor) {
        this.tmpKeylines.add(new TmpKeyline(size, isAnchor));
        if (size > this.focalItemSize) {
            this.firstFocalIndex = t7.a.w(this.tmpKeylines);
            this.focalItemSize = size;
        }
    }

    public final KeylineList createWithAlignment(float carouselMainAxisSize, CarouselAlignment carouselAlignment) {
        float f10;
        int iFindLastFocalIndex = findLastFocalIndex();
        int i10 = this.firstFocalIndex;
        int i11 = iFindLastFocalIndex - i10;
        this.pivotIndex = i10;
        int i12 = WhenMappings.$EnumSwitchMapping$0[carouselAlignment.ordinal()];
        if (i12 == 1) {
            f10 = this.focalItemSize / 2;
        } else if (i12 == 2) {
            float f11 = 2;
            f10 = (carouselMainAxisSize / f11) - ((this.focalItemSize / f11) * i11);
        } else {
            if (i12 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            f10 = carouselMainAxisSize - (this.focalItemSize / 2);
        }
        float f12 = f10;
        this.pivotOffset = f12;
        return new KeylineList(createKeylinesWithPivot(this.pivotIndex, f12, this.firstFocalIndex, iFindLastFocalIndex, this.focalItemSize, carouselMainAxisSize, this.tmpKeylines));
    }

    public final KeylineList createWithPivot(float carouselMainAxisSize, int pivotIndex, float pivotOffset) {
        return new KeylineList(createKeylinesWithPivot(pivotIndex, pivotOffset, this.firstFocalIndex, findLastFocalIndex(), this.focalItemSize, carouselMainAxisSize, this.tmpKeylines));
    }
}
