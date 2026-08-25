package androidx.compose.foundation.text2.input;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text2/input/TextObfuscationMode;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class TextObfuscationMode {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Visible = m1072constructorimpl(0);
    private static final int RevealLastTyped = m1072constructorimpl(1);
    private static final int Hidden = m1072constructorimpl(2);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/text2/input/TextObfuscationMode$Companion;", "", "()V", "Hidden", "Landroidx/compose/foundation/text2/input/TextObfuscationMode;", "getHidden-pyid5Pk", "()I", "I", "RevealLastTyped", "getRevealLastTyped-pyid5Pk", "Visible", "getVisible-pyid5Pk", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getHidden-pyid5Pk, reason: not valid java name */
        public final int m1078getHiddenpyid5Pk() {
            return TextObfuscationMode.Hidden;
        }

        /* JADX INFO: renamed from: getRevealLastTyped-pyid5Pk, reason: not valid java name */
        public final int m1079getRevealLastTypedpyid5Pk() {
            return TextObfuscationMode.RevealLastTyped;
        }

        /* JADX INFO: renamed from: getVisible-pyid5Pk, reason: not valid java name */
        public final int m1080getVisiblepyid5Pk() {
            return TextObfuscationMode.Visible;
        }

        private Companion() {
        }
    }

    private /* synthetic */ TextObfuscationMode(int i10) {
        this.value = i10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextObfuscationMode m1071boximpl(int i10) {
        return new TextObfuscationMode(i10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m1072constructorimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1073equalsimpl(int i10, Object obj) {
        return (obj instanceof TextObfuscationMode) && i10 == ((TextObfuscationMode) obj).m1077unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1074equalsimpl0(int i10, int i11) {
        return i10 == i11;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1075hashCodeimpl(int i10) {
        return i10;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1076toStringimpl(int i10) {
        return c.k("TextObfuscationMode(value=", i10, ')');
    }

    public boolean equals(Object obj) {
        return m1073equalsimpl(this.value, obj);
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return m1075hashCodeimpl(this.value);
    }

    public String toString() {
        return m1076toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m1077unboximpl() {
        return this.value;
    }
}
