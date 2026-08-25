package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001aE\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\r*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u0002H\f2\u0006\u0010\u0010\u001a\u0002H\rH\u0002¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0013\u001a)\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u0015\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0013\u001aA\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u000e\u0010\u0017\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0018H\u0002¢\u0006\u0002\u0010\u0019\u001aM\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\r*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u0002H\f2\u0006\u0010\u0010\u001a\u0002H\rH\u0002¢\u0006\u0002\u0010\u001b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"ENTRY_SIZE", "", "LOG_MAX_BRANCHING_FACTOR", "MAX_BRANCHING_FACTOR", "MAX_BRANCHING_FACTOR_MINUS_ONE", "MAX_SHIFT", "indexSegment", "index", "shift", "insertEntryAtIndex", "", "", "K", "V", "keyIndex", "key", "value", "([Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "removeEntryAtIndex", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "removeNodeAtIndex", "nodeIndex", "replaceEntryWithNode", "newNode", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "([Ljava/lang/Object;IILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;)[Ljava/lang/Object;", "replaceNodeWithEntry", "([Ljava/lang/Object;IILjava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TrieNodeKt {
    public static final int ENTRY_SIZE = 2;
    public static final int LOG_MAX_BRANCHING_FACTOR = 5;
    public static final int MAX_BRANCHING_FACTOR = 32;
    public static final int MAX_BRANCHING_FACTOR_MINUS_ONE = 31;
    public static final int MAX_SHIFT = 30;

    public static final int indexSegment(int i10, int i11) {
        return (i10 >> i11) & 31;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> Object[] insertEntryAtIndex(Object[] objArr, int i10, K k, V v2) {
        Object[] objArr2 = new Object[objArr.length + 2];
        r.R(objArr, objArr2, 0, 0, i10, 6);
        r.N(objArr, objArr2, i10 + 2, i10, objArr.length);
        objArr2[i10] = k;
        objArr2[i10 + 1] = v2;
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] removeEntryAtIndex(Object[] objArr, int i10) {
        Object[] objArr2 = new Object[objArr.length - 2];
        r.R(objArr, objArr2, 0, 0, i10, 6);
        r.N(objArr, objArr2, i10, i10 + 2, objArr.length);
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] removeNodeAtIndex(Object[] objArr, int i10) {
        Object[] objArr2 = new Object[objArr.length - 1];
        r.R(objArr, objArr2, 0, 0, i10, 6);
        r.N(objArr, objArr2, i10, i10 + 1, objArr.length);
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object[] replaceEntryWithNode(Object[] objArr, int i10, int i11, TrieNode<?, ?> trieNode) {
        Object[] objArr2 = new Object[objArr.length - 1];
        r.R(objArr, objArr2, 0, 0, i10, 6);
        r.N(objArr, objArr2, i10, i10 + 2, i11);
        objArr2[i11 - 2] = trieNode;
        r.N(objArr, objArr2, i11 - 1, i11, objArr.length);
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> Object[] replaceNodeWithEntry(Object[] objArr, int i10, int i11, K k, V v2) {
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + 1);
        r.N(objArrCopyOf, objArrCopyOf, i10 + 2, i10 + 1, objArr.length);
        r.N(objArrCopyOf, objArrCopyOf, i11 + 2, i11, i10);
        objArrCopyOf[i11] = k;
        objArrCopyOf[i11 + 1] = v2;
        return objArrCopyOf;
    }
}
