package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001:\u0001\u000fJ\u0015\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\nJ\u0015\u0010\r\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u0005ø\u0001\u0002\u0082\u0002\u0011\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValues;", "", "calculateBottomPadding", "Landroidx/compose/ui/unit/Dp;", "calculateBottomPadding-D9Ej5fM", "()F", "calculateLeftPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "calculateLeftPadding-u2uoSUM", "(Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateRightPadding", "calculateRightPadding-u2uoSUM", "calculateTopPadding", "calculateTopPadding-D9Ej5fM", "Absolute", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PaddingValues {

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u0002H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\fJ\u0015\u0010\u0014\u001a\u00020\u0002H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u000fJ\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR \u0010\u0003\u001a\u00020\u00028\u0002X\u0083\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010 \u0012\u0004\b!\u0010\"R \u0010\u0004\u001a\u00020\u00028\u0002X\u0083\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0004\u0010 \u0012\u0004\b#\u0010\"R \u0010\u0005\u001a\u00020\u00028\u0002X\u0083\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010 \u0012\u0004\b$\u0010\"R \u0010\u0006\u001a\u00020\u00028\u0002X\u0083\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0006\u0010 \u0012\u0004\b%\u0010\"\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006&"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValues$Absolute;", "Landroidx/compose/foundation/layout/PaddingValues;", "Landroidx/compose/ui/unit/Dp;", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "<init>", "(FFFFLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "calculateLeftPadding-u2uoSUM", "(Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateLeftPadding", "calculateTopPadding-D9Ej5fM", "()F", "calculateTopPadding", "calculateRightPadding-u2uoSUM", "calculateRightPadding", "calculateBottomPadding-D9Ej5fM", "calculateBottomPadding", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getLeft-D9Ej5fM$annotations", "()V", "getTop-D9Ej5fM$annotations", "getRight-D9Ej5fM$annotations", "getBottom-D9Ej5fM$annotations", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Absolute implements PaddingValues {
        public static final int $stable = 0;
        private final float bottom;
        private final float left;
        private final float right;
        private final float top;

        public /* synthetic */ Absolute(float f10, float f11, float f12, float f13, h hVar) {
            this(f10, f11, f12, f13);
        }

        /* JADX INFO: renamed from: getBottom-D9Ej5fM$annotations, reason: not valid java name */
        private static /* synthetic */ void m542getBottomD9Ej5fM$annotations() {
        }

        /* JADX INFO: renamed from: getLeft-D9Ej5fM$annotations, reason: not valid java name */
        private static /* synthetic */ void m543getLeftD9Ej5fM$annotations() {
        }

        /* JADX INFO: renamed from: getRight-D9Ej5fM$annotations, reason: not valid java name */
        private static /* synthetic */ void m544getRightD9Ej5fM$annotations() {
        }

        /* JADX INFO: renamed from: getTop-D9Ej5fM$annotations, reason: not valid java name */
        private static /* synthetic */ void m545getTopD9Ej5fM$annotations() {
        }

        @Override // androidx.compose.foundation.layout.PaddingValues
        /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM, reason: from getter */
        public float getBottom() {
            return this.bottom;
        }

        @Override // androidx.compose.foundation.layout.PaddingValues
        /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM */
        public float mo480calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection) {
            return this.left;
        }

        @Override // androidx.compose.foundation.layout.PaddingValues
        /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM */
        public float mo481calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection) {
            return this.right;
        }

        @Override // androidx.compose.foundation.layout.PaddingValues
        /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM, reason: from getter */
        public float getTop() {
            return this.top;
        }

        public boolean equals(Object other) {
            if (!(other instanceof Absolute)) {
                return false;
            }
            Absolute absolute = (Absolute) other;
            return Dp.m5683equalsimpl0(this.left, absolute.left) && Dp.m5683equalsimpl0(this.top, absolute.top) && Dp.m5683equalsimpl0(this.right, absolute.right) && Dp.m5683equalsimpl0(this.bottom, absolute.bottom);
        }

        public int hashCode() {
            return Dp.m5684hashCodeimpl(this.bottom) + a0.c.C(this.right, a0.c.C(this.top, Dp.m5684hashCodeimpl(this.left) * 31, 31), 31);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("PaddingValues.Absolute(left=");
            a0.c.v(this.left, sb2, ", top=");
            a0.c.v(this.top, sb2, ", right=");
            a0.c.v(this.right, sb2, ", bottom=");
            sb2.append((Object) Dp.m5689toStringimpl(this.bottom));
            sb2.append(')');
            return sb2.toString();
        }

        private Absolute(float f10, float f11, float f12, float f13) {
            this.left = f10;
            this.top = f11;
            this.right = f12;
            this.bottom = f13;
        }

        public /* synthetic */ Absolute(float f10, float f11, float f12, float f13, int i10, h hVar) {
            this((i10 & 1) != 0 ? Dp.m5678constructorimpl(0) : f10, (i10 & 2) != 0 ? Dp.m5678constructorimpl(0) : f11, (i10 & 4) != 0 ? Dp.m5678constructorimpl(0) : f12, (i10 & 8) != 0 ? Dp.m5678constructorimpl(0) : f13, null);
        }
    }

    /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM */
    float getBottom();

    /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM */
    float mo480calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection);

    /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM */
    float mo481calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection);

    /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM */
    float getTop();
}
