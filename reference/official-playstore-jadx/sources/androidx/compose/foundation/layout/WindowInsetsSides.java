package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087@\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0000H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0005J\u001b\u0010\u0011\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsSides;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hasAny", "sides", "hasAny-bkgdKaI$foundation_layout_release", "(II)Z", "hashCode", "hashCode-impl", "plus", "plus-gK_yJZ4", "(II)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "valueToString", "valueToString-impl", "Companion", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class WindowInsetsSides {
    private static final int AllowLeftInLtr;
    private static final int AllowLeftInRtl;
    private static final int AllowRightInLtr;
    private static final int AllowRightInRtl;
    private static final int Bottom;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int End;
    private static final int Horizontal;
    private static final int Left;
    private static final int Right;
    private static final int Start;
    private static final int Top;
    private static final int Vertical;
    private final int value;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u001c\u0010\f\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006R\u0019\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0013\u0010\u0006R\u0019\u0010\u0014\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0015\u0010\u0006R\u0019\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0017\u0010\u0006R\u0019\u0010\u0018\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0019\u0010\u0006R\u0019\u0010\u001a\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001b\u0010\u0006R\u0019\u0010\u001c\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001d\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsSides$Companion;", "", "()V", "AllowLeftInLtr", "Landroidx/compose/foundation/layout/WindowInsetsSides;", "getAllowLeftInLtr-JoeWqyM$foundation_layout_release", "()I", "I", "AllowLeftInRtl", "getAllowLeftInRtl-JoeWqyM$foundation_layout_release", "AllowRightInLtr", "getAllowRightInLtr-JoeWqyM$foundation_layout_release", "AllowRightInRtl", "getAllowRightInRtl-JoeWqyM$foundation_layout_release", "Bottom", "getBottom-JoeWqyM", "End", "getEnd-JoeWqyM", "Horizontal", "getHorizontal-JoeWqyM", "Left", "getLeft-JoeWqyM", "Right", "getRight-JoeWqyM", "Start", "getStart-JoeWqyM", "Top", "getTop-JoeWqyM", "Vertical", "getVertical-JoeWqyM", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getAllowLeftInLtr-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m618getAllowLeftInLtrJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowLeftInLtr;
        }

        /* JADX INFO: renamed from: getAllowLeftInRtl-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m619getAllowLeftInRtlJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowLeftInRtl;
        }

        /* JADX INFO: renamed from: getAllowRightInLtr-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m620getAllowRightInLtrJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowRightInLtr;
        }

        /* JADX INFO: renamed from: getAllowRightInRtl-JoeWqyM$foundation_layout_release, reason: not valid java name */
        public final int m621getAllowRightInRtlJoeWqyM$foundation_layout_release() {
            return WindowInsetsSides.AllowRightInRtl;
        }

        /* JADX INFO: renamed from: getBottom-JoeWqyM, reason: not valid java name */
        public final int m622getBottomJoeWqyM() {
            return WindowInsetsSides.Bottom;
        }

        /* JADX INFO: renamed from: getEnd-JoeWqyM, reason: not valid java name */
        public final int m623getEndJoeWqyM() {
            return WindowInsetsSides.End;
        }

        /* JADX INFO: renamed from: getHorizontal-JoeWqyM, reason: not valid java name */
        public final int m624getHorizontalJoeWqyM() {
            return WindowInsetsSides.Horizontal;
        }

        /* JADX INFO: renamed from: getLeft-JoeWqyM, reason: not valid java name */
        public final int m625getLeftJoeWqyM() {
            return WindowInsetsSides.Left;
        }

        /* JADX INFO: renamed from: getRight-JoeWqyM, reason: not valid java name */
        public final int m626getRightJoeWqyM() {
            return WindowInsetsSides.Right;
        }

        /* JADX INFO: renamed from: getStart-JoeWqyM, reason: not valid java name */
        public final int m627getStartJoeWqyM() {
            return WindowInsetsSides.Start;
        }

        /* JADX INFO: renamed from: getTop-JoeWqyM, reason: not valid java name */
        public final int m628getTopJoeWqyM() {
            return WindowInsetsSides.Top;
        }

        /* JADX INFO: renamed from: getVertical-JoeWqyM, reason: not valid java name */
        public final int m629getVerticalJoeWqyM() {
            return WindowInsetsSides.Vertical;
        }

        private Companion() {
        }
    }

    static {
        int iM609constructorimpl = m609constructorimpl(8);
        AllowLeftInLtr = iM609constructorimpl;
        int iM609constructorimpl2 = m609constructorimpl(4);
        AllowRightInLtr = iM609constructorimpl2;
        int iM609constructorimpl3 = m609constructorimpl(2);
        AllowLeftInRtl = iM609constructorimpl3;
        int iM609constructorimpl4 = m609constructorimpl(1);
        AllowRightInRtl = iM609constructorimpl4;
        Start = m614plusgK_yJZ4(iM609constructorimpl, iM609constructorimpl4);
        End = m614plusgK_yJZ4(iM609constructorimpl2, iM609constructorimpl3);
        int iM609constructorimpl5 = m609constructorimpl(16);
        Top = iM609constructorimpl5;
        int iM609constructorimpl6 = m609constructorimpl(32);
        Bottom = iM609constructorimpl6;
        int iM614plusgK_yJZ4 = m614plusgK_yJZ4(iM609constructorimpl, iM609constructorimpl3);
        Left = iM614plusgK_yJZ4;
        int iM614plusgK_yJZ42 = m614plusgK_yJZ4(iM609constructorimpl2, iM609constructorimpl4);
        Right = iM614plusgK_yJZ42;
        Horizontal = m614plusgK_yJZ4(iM614plusgK_yJZ4, iM614plusgK_yJZ42);
        Vertical = m614plusgK_yJZ4(iM609constructorimpl5, iM609constructorimpl6);
    }

    private /* synthetic */ WindowInsetsSides(int i10) {
        this.value = i10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ WindowInsetsSides m608boximpl(int i10) {
        return new WindowInsetsSides(i10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m609constructorimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m610equalsimpl(int i10, Object obj) {
        return (obj instanceof WindowInsetsSides) && i10 == ((WindowInsetsSides) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m611equalsimpl0(int i10, int i11) {
        return i10 == i11;
    }

    /* JADX INFO: renamed from: hasAny-bkgdKaI$foundation_layout_release, reason: not valid java name */
    public static final boolean m612hasAnybkgdKaI$foundation_layout_release(int i10, int i11) {
        return (i10 & i11) != 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m613hashCodeimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: plus-gK_yJZ4, reason: not valid java name */
    public static final int m614plusgK_yJZ4(int i10, int i11) {
        return m609constructorimpl(i10 | i11);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m615toStringimpl(int i10) {
        return androidx.compose.foundation.c.u(new StringBuilder("WindowInsetsSides("), m616valueToStringimpl(i10), ')');
    }

    /* JADX INFO: renamed from: valueToString-impl, reason: not valid java name */
    private static final String m616valueToStringimpl(int i10) {
        StringBuilder sb2 = new StringBuilder();
        int i11 = Start;
        if ((i10 & i11) == i11) {
            valueToString_impl$lambda$0$appendPlus(sb2, "Start");
        }
        int i12 = Left;
        if ((i10 & i12) == i12) {
            valueToString_impl$lambda$0$appendPlus(sb2, "Left");
        }
        int i13 = Top;
        if ((i10 & i13) == i13) {
            valueToString_impl$lambda$0$appendPlus(sb2, "Top");
        }
        int i14 = End;
        if ((i10 & i14) == i14) {
            valueToString_impl$lambda$0$appendPlus(sb2, "End");
        }
        int i15 = Right;
        if ((i10 & i15) == i15) {
            valueToString_impl$lambda$0$appendPlus(sb2, "Right");
        }
        int i16 = Bottom;
        if ((i10 & i16) == i16) {
            valueToString_impl$lambda$0$appendPlus(sb2, "Bottom");
        }
        return sb2.toString();
    }

    private static final void valueToString_impl$lambda$0$appendPlus(StringBuilder sb2, String str) {
        if (sb2.length() > 0) {
            sb2.append('+');
        }
        sb2.append(str);
    }

    public boolean equals(Object obj) {
        return m610equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m613hashCodeimpl(this.value);
    }

    public String toString() {
        return m615toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }
}
