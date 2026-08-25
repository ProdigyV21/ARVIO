package androidx.compose.ui.node;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0083@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\b\u0010\tJ \u0010\u000f\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0086\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0016\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u001b\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/node/CenteredArray;", "", "", "data", "constructor-impl", "([I)[I", "", "index", "get-impl", "([II)I", "get", "value", "Lx6/t0;", "set-impl", "([III)V", "set", "", "toString-impl", "([I)Ljava/lang/String;", "toString", "hashCode-impl", "([I)I", "hashCode", "other", "", "equals-impl", "([ILjava/lang/Object;)Z", "equals", "[I", "getMid-impl", "mid", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
final class CenteredArray {
    private final int[] data;

    private /* synthetic */ CenteredArray(int[] iArr) {
        this.data = iArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CenteredArray m4712boximpl(int[] iArr) {
        return new CenteredArray(iArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int[] m4713constructorimpl(int[] iArr) {
        return iArr;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4714equalsimpl(int[] iArr, Object obj) {
        return (obj instanceof CenteredArray) && p.a(iArr, ((CenteredArray) obj).getData());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4715equalsimpl0(int[] iArr, int[] iArr2) {
        return p.a(iArr, iArr2);
    }

    /* JADX INFO: renamed from: get-impl, reason: not valid java name */
    public static final int m4716getimpl(int[] iArr, int i10) {
        return iArr[i10 + m4717getMidimpl(iArr)];
    }

    /* JADX INFO: renamed from: getMid-impl, reason: not valid java name */
    private static final int m4717getMidimpl(int[] iArr) {
        return iArr.length / 2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4718hashCodeimpl(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    /* JADX INFO: renamed from: set-impl, reason: not valid java name */
    public static final void m4719setimpl(int[] iArr, int i10, int i11) {
        iArr[i10 + m4717getMidimpl(iArr)] = i11;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4720toStringimpl(int[] iArr) {
        return "CenteredArray(data=" + Arrays.toString(iArr) + ')';
    }

    public boolean equals(Object obj) {
        return m4714equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m4718hashCodeimpl(this.data);
    }

    public String toString() {
        return m4720toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int[] getData() {
        return this.data;
    }
}
