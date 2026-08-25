package androidx.compose.material3;

import androidx.compose.material3.MenuPosition;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Landroidx/compose/material3/WindowAlignmentMarginPosition;", "", "()V", "Horizontal", "Vertical", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WindowAlignmentMarginPosition {
    public static final int $stable = 0;
    public static final WindowAlignmentMarginPosition INSTANCE = new WindowAlignmentMarginPosition();

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÖ\u0001J2\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/material3/WindowAlignmentMarginPosition$Horizontal;", "Landroidx/compose/material3/MenuPosition$Horizontal;", "alignment", "Landroidx/compose/ui/Alignment$Horizontal;", "margin", "", "(Landroidx/compose/ui/Alignment$Horizontal;I)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "position", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuWidth", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "position-95KtPRI", "(Landroidx/compose/ui/unit/IntRect;JILandroidx/compose/ui/unit/LayoutDirection;)I", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Horizontal implements MenuPosition.Horizontal {
        public static final int $stable = 0;
        private final Alignment.Horizontal alignment;
        private final int margin;

        public Horizontal(Alignment.Horizontal horizontal, int i10) {
            this.alignment = horizontal;
            this.margin = i10;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Alignment.Horizontal getAlignment() {
            return this.alignment;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getMargin() {
            return this.margin;
        }

        public static /* synthetic */ Horizontal copy$default(Horizontal horizontal, Alignment.Horizontal horizontal2, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                horizontal2 = horizontal.alignment;
            }
            if ((i11 & 2) != 0) {
                i10 = horizontal.margin;
            }
            return horizontal.copy(horizontal2, i10);
        }

        public final Horizontal copy(Alignment.Horizontal alignment, int margin) {
            return new Horizontal(alignment, margin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Horizontal)) {
                return false;
            }
            Horizontal horizontal = (Horizontal) other;
            return p.a(this.alignment, horizontal.alignment) && this.margin == horizontal.margin;
        }

        public int hashCode() {
            return (this.alignment.hashCode() * 31) + this.margin;
        }

        @Override // androidx.compose.material3.MenuPosition.Horizontal
        /* JADX INFO: renamed from: position-95KtPRI */
        public int mo1217position95KtPRI(IntRect anchorBounds, long windowSize, int menuWidth, LayoutDirection layoutDirection) {
            return menuWidth >= IntSize.m5844getWidthimpl(windowSize) - (this.margin * 2) ? Alignment.INSTANCE.getCenterHorizontally().align(menuWidth, IntSize.m5844getWidthimpl(windowSize), layoutDirection) : qb.d.n(this.alignment.align(menuWidth, IntSize.m5844getWidthimpl(windowSize), layoutDirection), this.margin, (IntSize.m5844getWidthimpl(windowSize) - this.margin) - menuWidth);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Horizontal(alignment=");
            sb2.append(this.alignment);
            sb2.append(", margin=");
            return a0.c.o(sb2, this.margin, ')');
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÖ\u0001J*\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/WindowAlignmentMarginPosition$Vertical;", "Landroidx/compose/material3/MenuPosition$Vertical;", "alignment", "Landroidx/compose/ui/Alignment$Vertical;", "margin", "", "(Landroidx/compose/ui/Alignment$Vertical;I)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "position", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "menuHeight", "position-JVtK1S4", "(Landroidx/compose/ui/unit/IntRect;JI)I", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Vertical implements MenuPosition.Vertical {
        public static final int $stable = 0;
        private final Alignment.Vertical alignment;
        private final int margin;

        public Vertical(Alignment.Vertical vertical, int i10) {
            this.alignment = vertical;
            this.margin = i10;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Alignment.Vertical getAlignment() {
            return this.alignment;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getMargin() {
            return this.margin;
        }

        public static /* synthetic */ Vertical copy$default(Vertical vertical, Alignment.Vertical vertical2, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                vertical2 = vertical.alignment;
            }
            if ((i11 & 2) != 0) {
                i10 = vertical.margin;
            }
            return vertical.copy(vertical2, i10);
        }

        public final Vertical copy(Alignment.Vertical alignment, int margin) {
            return new Vertical(alignment, margin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Vertical)) {
                return false;
            }
            Vertical vertical = (Vertical) other;
            return p.a(this.alignment, vertical.alignment) && this.margin == vertical.margin;
        }

        public int hashCode() {
            return (this.alignment.hashCode() * 31) + this.margin;
        }

        @Override // androidx.compose.material3.MenuPosition.Vertical
        /* JADX INFO: renamed from: position-JVtK1S4 */
        public int mo1218positionJVtK1S4(IntRect anchorBounds, long windowSize, int menuHeight) {
            return menuHeight >= IntSize.m5843getHeightimpl(windowSize) - (this.margin * 2) ? Alignment.INSTANCE.getCenterVertically().align(menuHeight, IntSize.m5843getHeightimpl(windowSize)) : qb.d.n(this.alignment.align(menuHeight, IntSize.m5843getHeightimpl(windowSize)), this.margin, (IntSize.m5843getHeightimpl(windowSize) - this.margin) - menuHeight);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Vertical(alignment=");
            sb2.append(this.alignment);
            sb2.append(", margin=");
            return a0.c.o(sb2, this.margin, ')');
        }
    }

    private WindowAlignmentMarginPosition() {
    }
}
