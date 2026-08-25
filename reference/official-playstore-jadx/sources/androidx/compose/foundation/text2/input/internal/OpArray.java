package androidx.compose.foundation.text2.input.internal;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.g;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0083@\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ-\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013Jo\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00162K\u0010\u001b\u001aG\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0018H\u0086\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\"\u001a\u00020\u001fHÖ\u0001¢\u0006\u0004\b \u0010!J\u0010\u0010%\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b#\u0010$J\u001a\u0010)\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010*R\u0011\u0010\u0003\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010$\u0088\u0001\u0007\u0092\u0001\u00020\u0006\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/OpArray;", "", "", ContentDisposition.Parameters.Size, "constructor-impl", "(I)[I", "", "values", "([I)[I", "index", "offset", "srcLen", "destLen", "Lx6/t0;", "set-impl", "([IIIII)V", "set", "newSize", "copyOf-S4-kM8k", "([II)[I", "copyOf", "max", "", "reversed", "Lkotlin/Function3;", "Lx6/y;", ContentDisposition.Parameters.Name, "block", "forEach-impl", "([IIZLr7/q;)V", "forEach", "", "toString-impl", "([I)Ljava/lang/String;", "toString", "hashCode-impl", "([I)I", "hashCode", "other", "equals-impl", "([ILjava/lang/Object;)Z", "equals", "[I", "getSize-impl", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
final class OpArray {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int ElementSize = 3;
    private final int[] values;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/OpArray$Companion;", "", "()V", "ElementSize", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    private /* synthetic */ OpArray(int[] iArr) {
        this.values = iArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OpArray m1090boximpl(int[] iArr) {
        return new OpArray(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int[] m1092constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: copyOf-S4-kM8k, reason: not valid java name */
    public static final int[] m1093copyOfS4kM8k(int[] iArr, int i10) {
        return m1092constructorimpl(Arrays.copyOf(iArr, i10 * 3));
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1094equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof OpArray) && p.a(iArr, ((OpArray) obj).getValues());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1095equalsimpl0(int[] iArr, int[] iArr2) {
        return p.a(iArr, iArr2);
    }

    /* JADX INFO: renamed from: forEach-impl, reason: not valid java name */
    public static final void m1096forEachimpl(int[] iArr, int i10, boolean z, q<? super Integer, ? super Integer, ? super Integer, t0> qVar) {
        if (i10 < 0) {
            return;
        }
        if (!z) {
            for (int i11 = 0; i11 < i10; i11++) {
                int i12 = i11 * 3;
                qVar.invoke(Integer.valueOf(iArr[i12]), Integer.valueOf(iArr[i12 + 1]), Integer.valueOf(iArr[i12 + 2]));
            }
            return;
        }
        while (true) {
            i10--;
            if (-1 >= i10) {
                return;
            }
            int i13 = i10 * 3;
            qVar.invoke(Integer.valueOf(iArr[i13]), Integer.valueOf(iArr[i13 + 1]), Integer.valueOf(iArr[i13 + 2]));
        }
    }

    /* JADX INFO: renamed from: forEach-impl$default, reason: not valid java name */
    public static /* synthetic */ void m1097forEachimpl$default(int[] iArr, int i10, boolean z, q qVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            z = false;
        }
        if (i10 < 0) {
            return;
        }
        if (!z) {
            for (int i12 = 0; i12 < i10; i12++) {
                int i13 = i12 * 3;
                qVar.invoke(Integer.valueOf(iArr[i13]), Integer.valueOf(iArr[i13 + 1]), Integer.valueOf(iArr[i13 + 2]));
            }
            return;
        }
        while (true) {
            i10--;
            if (-1 >= i10) {
                return;
            }
            int i14 = i10 * 3;
            qVar.invoke(Integer.valueOf(iArr[i14]), Integer.valueOf(iArr[i14 + 1]), Integer.valueOf(iArr[i14 + 2]));
        }
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static final int m1098getSizeimpl(int[] iArr) {
        return iArr.length / 3;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1099hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m1100setimpl(int[] iArr, int i10, int i11, int i12, int i13) {
        int i14 = i10 * 3;
        iArr[i14] = i11;
        iArr[i14 + 1] = i12;
        iArr[i14 + 2] = i13;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1101toStringimpl(int[] iArr) {
        return "OpArray(values=" + Arrays.toString(iArr) + ')';
    }

    public boolean equals(Object obj) {
        return m1094equalsimpl(this.values, obj);
    }

    public int hashCode() {
        return m1099hashCodeimpl(this.values);
    }

    public String toString() {
        return m1101toStringimpl(this.values);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int[] getValues() {
        return this.values;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m1091constructorimpl(int i10) {
        return m1092constructorimpl(new int[i10 * 3]);
    }
}
