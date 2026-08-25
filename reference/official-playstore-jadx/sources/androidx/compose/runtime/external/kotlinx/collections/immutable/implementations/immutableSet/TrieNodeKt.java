package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a9\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\b\u0000\u0010\u0005*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\u000b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\f\u0010\r\u001aN\u0010\u0013\u001a\u00020\u0000*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00002\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u00110\u0010H\u0082\b¢\u0006\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0015\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016\"\u0014\u0010\u0017\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016\"\u0014\u0010\u0018\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016\"\u0014\u0010\u0019\u001a\u00020\u00008\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0016¨\u0006\u001a"}, d2 = {"", "index", "shift", "indexSegment", "(II)I", "E", "", "", "element", "addElementAtIndex", "([Ljava/lang/Object;ILjava/lang/Object;)[Ljava/lang/Object;", "cellIndex", "removeCellAtIndex", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "newArray", "newArrayOffset", "Lkotlin/Function1;", "", "predicate", "filterTo", "([Ljava/lang/Object;[Ljava/lang/Object;ILr7/l;)I", "MAX_BRANCHING_FACTOR", "I", "LOG_MAX_BRANCHING_FACTOR", "MAX_BRANCHING_FACTOR_MINUS_ONE", "MAX_SHIFT", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TrieNodeKt {
    public static final int LOG_MAX_BRANCHING_FACTOR = 5;
    public static final int MAX_BRANCHING_FACTOR = 32;
    public static final int MAX_BRANCHING_FACTOR_MINUS_ONE = 31;
    public static final int MAX_SHIFT = 30;

    /* JADX INFO: renamed from: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNodeKt$filterTo$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Object, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(Object obj) {
            return Boolean.valueOf(obj != TrieNode.INSTANCE.getEMPTY$runtime_release());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <E> Object[] addElementAtIndex(Object[] objArr, int i10, E e5) {
        Object[] objArr2 = new Object[objArr.length + 1];
        kotlin.collections.r.R(objArr, objArr2, 0, 0, i10, 6);
        kotlin.collections.r.N(objArr, objArr2, i10 + 1, i10, objArr.length);
        objArr2[i10] = e5;
        return objArr2;
    }

    private static final int filterTo(Object[] objArr, Object[] objArr2, int i10, l<Object, Boolean> lVar) {
        int i11 = 0;
        int i12 = 0;
        while (i11 < objArr.length) {
            CommonFunctionsKt.m3083assert(i12 <= i11);
            if (((Boolean) lVar.invoke(objArr[i11])).booleanValue()) {
                objArr2[i10 + i12] = objArr[i11];
                i12++;
                CommonFunctionsKt.m3083assert(i10 + i12 <= objArr2.length);
            }
            i11++;
        }
        return i12;
    }

    public static /* synthetic */ int filterTo$default(Object[] objArr, Object[] objArr2, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        int i12 = 0;
        int i13 = 0;
        while (i12 < objArr.length) {
            CommonFunctionsKt.m3083assert(i13 <= i12);
            if (((Boolean) lVar.invoke(objArr[i12])).booleanValue()) {
                objArr2[i10 + i13] = objArr[i12];
                i13++;
                CommonFunctionsKt.m3083assert(i10 + i13 <= objArr2.length);
            }
            i12++;
        }
        return i13;
    }

    public static final int indexSegment(int i10, int i11) {
        return (i10 >> i11) & 31;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] removeCellAtIndex(Object[] objArr, int i10) {
        Object[] objArr2 = new Object[objArr.length - 1];
        kotlin.collections.r.R(objArr, objArr2, 0, 0, i10, 6);
        kotlin.collections.r.N(objArr, objArr2, i10, i10 + 1, objArr.length);
        return objArr2;
    }
}
