package androidx.compose.ui.node;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0083@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0018\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u001f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012R\u0011\u0010!\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b \u0010\u0012R\u0011\u0010#\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0012R\u0011\u0010&\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010(\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b'\u0010\u0012R\u0014\u0010*\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010%R\u0014\u0010,\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010%\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006-"}, d2 = {"Landroidx/compose/ui/node/Snake;", "", "", "data", "constructor-impl", "([I)[I", "Landroidx/compose/ui/node/IntStack;", "diagonals", "Lx6/t0;", "addDiagonalToStack-impl", "([ILandroidx/compose/ui/node/IntStack;)V", "addDiagonalToStack", "", "toString-impl", "([I)Ljava/lang/String;", "toString", "", "hashCode-impl", "([I)I", "hashCode", "other", "", "equals-impl", "([ILjava/lang/Object;)Z", "equals", "[I", "getData", "()[I", "getStartX-impl", "startX", "getStartY-impl", "startY", "getEndX-impl", "endX", "getEndY-impl", "endY", "getReverse-impl", "([I)Z", "reverse", "getDiagonalSize-impl", "diagonalSize", "getHasAdditionOrRemoval-impl", "hasAdditionOrRemoval", "isAddition-impl", "isAddition", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
final class Snake {
    private final int[] data;

    private /* synthetic */ Snake(int[] iArr) {
        this.data = iArr;
    }

    /* JADX INFO: renamed from: addDiagonalToStack-impl, reason: not valid java name */
    public static final void m4901addDiagonalToStackimpl(int[] iArr, IntStack intStack) {
        if (!m4909getHasAdditionOrRemovalimpl(iArr)) {
            intStack.pushDiagonal(m4911getStartXimpl(iArr), m4912getStartYimpl(iArr), m4907getEndXimpl(iArr) - m4911getStartXimpl(iArr));
            return;
        }
        if (m4910getReverseimpl(iArr)) {
            intStack.pushDiagonal(m4911getStartXimpl(iArr), m4912getStartYimpl(iArr), m4906getDiagonalSizeimpl(iArr));
        } else if (m4914isAdditionimpl(iArr)) {
            intStack.pushDiagonal(m4911getStartXimpl(iArr), m4912getStartYimpl(iArr) + 1, m4906getDiagonalSizeimpl(iArr));
        } else {
            intStack.pushDiagonal(m4911getStartXimpl(iArr) + 1, m4912getStartYimpl(iArr), m4906getDiagonalSizeimpl(iArr));
        }
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Snake m4902boximpl(int[] iArr) {
        return new Snake(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m4903constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4904equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof Snake) && p.a(iArr, ((Snake) obj).m4916unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4905equalsimpl0(int[] iArr, int[] iArr2) {
        return p.a(iArr, iArr2);
    }

    /* JADX INFO: renamed from: getDiagonalSize-impl, reason: not valid java name */
    public static final int m4906getDiagonalSizeimpl(int[] iArr) {
        return Math.min(m4907getEndXimpl(iArr) - m4911getStartXimpl(iArr), m4908getEndYimpl(iArr) - m4912getStartYimpl(iArr));
    }

    /* JADX INFO: renamed from: getEndX-impl, reason: not valid java name */
    public static final int m4907getEndXimpl(int[] iArr) {
        return iArr[2];
    }

    /* JADX INFO: renamed from: getEndY-impl, reason: not valid java name */
    public static final int m4908getEndYimpl(int[] iArr) {
        return iArr[3];
    }

    /* JADX INFO: renamed from: getHasAdditionOrRemoval-impl, reason: not valid java name */
    private static final boolean m4909getHasAdditionOrRemovalimpl(int[] iArr) {
        return m4908getEndYimpl(iArr) - m4912getStartYimpl(iArr) != m4907getEndXimpl(iArr) - m4911getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: getReverse-impl, reason: not valid java name */
    public static final boolean m4910getReverseimpl(int[] iArr) {
        return iArr[4] != 0;
    }

    /* JADX INFO: renamed from: getStartX-impl, reason: not valid java name */
    public static final int m4911getStartXimpl(int[] iArr) {
        return iArr[0];
    }

    /* JADX INFO: renamed from: getStartY-impl, reason: not valid java name */
    public static final int m4912getStartYimpl(int[] iArr) {
        return iArr[1];
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4913hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    /* JADX INFO: renamed from: isAddition-impl, reason: not valid java name */
    private static final boolean m4914isAdditionimpl(int[] iArr) {
        return m4908getEndYimpl(iArr) - m4912getStartYimpl(iArr) > m4907getEndXimpl(iArr) - m4911getStartXimpl(iArr);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4915toStringimpl(int[] iArr) {
        StringBuilder sb2 = new StringBuilder("Snake(");
        sb2.append(m4911getStartXimpl(iArr));
        sb2.append(',');
        sb2.append(m4912getStartYimpl(iArr));
        sb2.append(',');
        sb2.append(m4907getEndXimpl(iArr));
        sb2.append(',');
        sb2.append(m4908getEndYimpl(iArr));
        sb2.append(',');
        return a0.c.r(sb2, m4910getReverseimpl(iArr), ')');
    }

    public boolean equals(Object obj) {
        return m4904equalsimpl(this.data, obj);
    }

    public final int[] getData() {
        return this.data;
    }

    public int hashCode() {
        return m4913hashCodeimpl(this.data);
    }

    public String toString() {
        return m4915toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int[] m4916unboximpl() {
        return this.data;
    }
}
