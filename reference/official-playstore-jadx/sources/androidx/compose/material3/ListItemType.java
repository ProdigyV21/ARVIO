package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0083@\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/material3/ListItemType;", "", "lines", "", "constructor-impl", "(I)I", "compareTo", "other", "compareTo-yh95HIg", "(II)I", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
final class ListItemType implements Comparable<ListItemType> {
    private final int lines;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int OneLine = m1661constructorimpl(1);
    private static final int TwoLine = m1661constructorimpl(2);
    private static final int ThreeLine = m1661constructorimpl(3);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/ListItemType$Companion;", "", "()V", "OneLine", "Landroidx/compose/material3/ListItemType;", "getOneLine-AlXitO8", "()I", "I", "ThreeLine", "getThreeLine-AlXitO8", "TwoLine", "getTwoLine-AlXitO8", "getListItemType", "hasOverline", "", "hasSupporting", "isSupportingMultiline", "getListItemType-Z-LSjz4$material3_release", "(ZZZ)I", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getListItemType-Z-LSjz4$material3_release, reason: not valid java name */
        public final int m1668getListItemTypeZLSjz4$material3_release(boolean hasOverline, boolean hasSupporting, boolean isSupportingMultiline) {
            return ((hasOverline && hasSupporting) || isSupportingMultiline) ? m1670getThreeLineAlXitO8() : (hasOverline || hasSupporting) ? m1671getTwoLineAlXitO8() : m1669getOneLineAlXitO8();
        }

        /* JADX INFO: renamed from: getOneLine-AlXitO8, reason: not valid java name */
        public final int m1669getOneLineAlXitO8() {
            return ListItemType.OneLine;
        }

        /* JADX INFO: renamed from: getThreeLine-AlXitO8, reason: not valid java name */
        public final int m1670getThreeLineAlXitO8() {
            return ListItemType.ThreeLine;
        }

        /* JADX INFO: renamed from: getTwoLine-AlXitO8, reason: not valid java name */
        public final int m1671getTwoLineAlXitO8() {
            return ListItemType.TwoLine;
        }

        private Companion() {
        }
    }

    private /* synthetic */ ListItemType(int i10) {
        this.lines = i10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ListItemType m1659boximpl(int i10) {
        return new ListItemType(i10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1661constructorimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1662equalsimpl(int i10, Object obj) {
        return (obj instanceof ListItemType) && i10 == ((ListItemType) obj).getLines();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1663equalsimpl0(int i10, int i11) {
        return i10 == i11;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1664hashCodeimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1665toStringimpl(int i10) {
        return a0.c.k("ListItemType(lines=", i10, ')');
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ListItemType listItemType) {
        return m1666compareToyh95HIg(listItemType.getLines());
    }

    /* JADX INFO: renamed from: compareTo-yh95HIg, reason: not valid java name */
    public int m1666compareToyh95HIg(int i10) {
        return m1660compareToyh95HIg(this.lines, i10);
    }

    public boolean equals(Object obj) {
        return m1662equalsimpl(this.lines, obj);
    }

    public int hashCode() {
        return m1664hashCodeimpl(this.lines);
    }

    public String toString() {
        return m1665toStringimpl(this.lines);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getLines() {
        return this.lines;
    }

    /* JADX INFO: renamed from: compareTo-yh95HIg, reason: not valid java name */
    public static int m1660compareToyh95HIg(int i10, int i11) {
        return p.c(i10, i11);
    }
}
