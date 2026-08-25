package androidx.compose.runtime;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;
import t.v;
import t.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u001c\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a@\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0001j\b\u0012\u0004\u0012\u00028\u0000`\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a>\u0010\t\u001a\u00020\b\"\u0004\b\u0000\u0010\u0000*\u0012\u0012\u0004\u0012\u00028\u00000\u0001j\b\u0012\u0004\u0012\u00028\u0000`\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0082\b¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\r\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\u0011\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0015\u0010\u0012\u001a\u001b\u0010\u0016\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001b\u0010\u0017\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0017\u0010\u0012\u001a\u001b\u0010\u0018\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0018\u0010\u0014\u001a\u001b\u0010\u001a\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001b\u0010\u001c\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001c\u0010\u0014\u001a#\u0010\u001e\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001b\u0010 \u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b \u0010\u0014\u001a#\u0010!\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0004H\u0002¢\u0006\u0004\b!\u0010\u001f\u001a\u001b\u0010\"\u001a\u00020\u0004*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b\"\u0010\u0014\u001a\u001b\u0010#\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b#\u0010\u0012\u001a\u001b\u0010$\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b$\u0010\u0012\u001a\u0017\u0010%\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b%\u0010&\u001a\u001b\u0010'\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b'\u0010\u0012\u001a#\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\bH\u0002¢\u0006\u0004\b*\u0010+\u001a\u001b\u0010,\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b,\u0010\u0012\u001a#\u0010-\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b-\u0010.\u001a#\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\bH\u0002¢\u0006\u0004\b/\u0010+\u001a\u001b\u00100\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b0\u0010\u0012\u001a#\u00101\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b1\u0010.\u001a#\u00102\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\bH\u0002¢\u0006\u0004\b2\u0010+\u001a\u001b\u00103\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b3\u0010\u0012\u001a#\u00104\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b4\u0010.\u001a'\u00107\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b05H\u0002¢\u0006\u0004\b7\u00108\u001a#\u00109\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\bH\u0002¢\u0006\u0004\b9\u0010+\u001a\u001b\u0010:\u001a\u00020\b*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0002¢\u0006\u0004\b:\u0010\u0012\u001a#\u0010<\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010;\u001a\u00020\bH\u0002¢\u0006\u0004\b<\u0010.\u001a#\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0)*\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\bH\u0002¢\u0006\u0004\b=\u0010+\u001aK\u0010@\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u00100\u001a\u00020\b2\u0006\u0010:\u001a\u00020\bH\u0002¢\u0006\u0004\b@\u0010A\u001a#\u0010B\u001a\u00020\u0019*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0002¢\u0006\u0004\bB\u0010.\u001aB\u0010H\u001a\u00020C*\u0012\u0012\u0004\u0012\u00020C0\u0001j\b\u0012\u0004\u0012\u00020C`\u00022\u0006\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\b2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020C0FH\u0082\b¢\u0006\u0004\bH\u0010I\u001a5\u0010J\u001a\u0004\u0018\u00010C*\u0012\u0012\u0004\u0012\u00020C0\u0001j\b\u0012\u0004\u0012\u00020C`\u00022\u0006\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\bH\u0002¢\u0006\u0004\bJ\u0010K\u001a3\u0010M\u001a\u00020\b*\u0012\u0012\u0004\u0012\u00020C0\u0001j\b\u0012\u0004\u0012\u00020C`\u00022\u0006\u0010L\u001a\u00020\b2\u0006\u0010E\u001a\u00020\bH\u0002¢\u0006\u0004\bM\u0010N\u001a3\u0010O\u001a\u00020\b*\u0012\u0012\u0004\u0012\u00020C0\u0001j\b\u0012\u0004\u0012\u00020C`\u00022\u0006\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u00020\bH\u0002¢\u0006\u0004\bO\u0010N\u001a)\u0010R\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020Q0P2\u0006\u0010'\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\bR\u0010S\"\u0014\u0010T\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bT\u0010U\"\u0014\u0010V\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bV\u0010U\"\u0014\u0010W\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bW\u0010U\"\u0014\u0010X\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bX\u0010U\"\u0014\u0010Y\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bY\u0010U\"\u0014\u0010Z\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bZ\u0010U\"\u0014\u0010[\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b[\u0010U\"\u0014\u0010\\\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\\\u0010U\"\u0014\u0010]\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b]\u0010U\"\u0014\u0010^\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b^\u0010U\"\u0014\u0010_\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b_\u0010U\"\u0014\u0010`\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b`\u0010U\"\u0014\u0010a\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\ba\u0010U\"\u0014\u0010b\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bb\u0010U\"\u0014\u0010c\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bc\u0010U\"\u0014\u0010d\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bd\u0010U\"\u0014\u0010e\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\be\u0010U\"\u0014\u0010f\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bf\u0010U\"\u0014\u0010g\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\bg\u0010U¨\u0006h"}, d2 = {"T", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "Lkotlin/Function1;", "", "predicate", "fastLastOrNull", "(Ljava/util/ArrayList;Lr7/l;)Ljava/lang/Object;", "", "fastIndexOf", "(Ljava/util/ArrayList;Lr7/l;)I", "", ContentDisposition.Parameters.Size, "summarize", "(Ljava/lang/String;I)Ljava/lang/String;", "", "address", "groupInfo", "([II)I", "isNode", "([II)Z", "nodeIndex", "hasObjectKey", "objectKeyIndex", "hasAux", "Lx6/t0;", "addAux", "([II)V", "hasMark", "value", "updateMark", "([IIZ)V", "containsMark", "updateContainsMark", "containsAnyMark", "auxIndex", "slotAnchor", "countOneBits", "(I)I", "key", "len", "", UserMetadata.KEYDATA_FILENAME, "([II)Ljava/util/List;", "nodeCount", "updateNodeCount", "([III)V", "nodeCounts", "parentAnchor", "updateParentAnchor", "parentAnchors", "groupSize", "updateGroupSize", "", "indices", "slice", "([ILjava/lang/Iterable;)Ljava/util/List;", "groupSizes", "dataAnchor", LinkHeader.Parameters.Anchor, "updateDataAnchor", "dataAnchors", "hasDataKey", "hasData", "initGroup", "([IIIZZZII)V", "updateGroupKey", "Landroidx/compose/runtime/Anchor;", "index", "effectiveSize", "Lkotlin/Function0;", "block", "getOrAdd", "(Ljava/util/ArrayList;IILr7/a;)Landroidx/compose/runtime/Anchor;", "find", "(Ljava/util/ArrayList;II)Landroidx/compose/runtime/Anchor;", "location", "search", "(Ljava/util/ArrayList;II)I", "locationOf", "Lt/v;", "Lt/w;", "add", "(Lt/v;II)V", "parentAnchorPivot", "I", "Key_Offset", "GroupInfo_Offset", "ParentAnchor_Offset", "Size_Offset", "DataAnchor_Offset", "Group_Fields_Size", "NodeBit_Mask", "ObjectKey_Mask", "ObjectKey_Shift", "Aux_Mask", "Aux_Shift", "Mark_Mask", "ContainsMark_Mask", "Slots_Shift", "NodeCount_Mask", "MinGroupGrowthSize", "MinSlotsGrowthSize", "LIVE_EDIT_INVALID_KEY", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SlotTableKt {
    private static final int Aux_Mask = 268435456;
    private static final int Aux_Shift = 28;
    private static final int ContainsMark_Mask = 67108864;
    private static final int DataAnchor_Offset = 4;
    private static final int GroupInfo_Offset = 1;
    private static final int Group_Fields_Size = 5;
    private static final int Key_Offset = 0;
    private static final int LIVE_EDIT_INVALID_KEY = -3;
    private static final int Mark_Mask = 134217728;
    private static final int MinGroupGrowthSize = 32;
    private static final int MinSlotsGrowthSize = 32;
    private static final int NodeBit_Mask = 1073741824;
    private static final int NodeCount_Mask = 67108863;
    private static final int ObjectKey_Mask = 536870912;
    private static final int ObjectKey_Shift = 29;
    private static final int ParentAnchor_Offset = 2;
    private static final int Size_Offset = 3;
    private static final int Slots_Shift = 28;
    private static final int parentAnchorPivot = -2;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void add(v vVar, int i10, int i11) {
        w wVar = (w) vVar.b(i10);
        if (wVar == null) {
            wVar = new w();
            vVar.f(i10, wVar);
        }
        wVar.a(i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addAux(int[] iArr, int i10) {
        int i11 = (i10 * 5) + 1;
        iArr[i11] = iArr[i11] | 268435456;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int auxIndex(int[] iArr, int i10) {
        int i11 = i10 * 5;
        return i11 >= iArr.length ? iArr.length : iArr[i11 + 4] + countOneBits(iArr[i11 + 1] >> 29);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsAnyMark(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 201326592) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsMark(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int countOneBits(int i10) {
        switch (i10) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 1;
            case 5:
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int dataAnchor(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> dataAnchors(int[] iArr, int i10) {
        return slice(iArr, qb.d.V(qb.d.X(4, i10), 5));
    }

    public static /* synthetic */ List dataAnchors$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = iArr.length;
        }
        return dataAnchors(iArr, i10);
    }

    private static final <T> int fastIndexOf(ArrayList<T> arrayList, r7.l<? super T, Boolean> lVar) {
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((Boolean) lVar.invoke(arrayList.get(i10))).booleanValue()) {
                return i10;
            }
        }
        return -1;
    }

    private static final <T> T fastLastOrNull(ArrayList<T> arrayList, r7.l<? super T, Boolean> lVar) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            T t2 = arrayList.get(size);
            if (((Boolean) lVar.invoke(t2)).booleanValue()) {
                return t2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Anchor find(ArrayList<Anchor> arrayList, int i10, int i11) {
        int iSearch = search(arrayList, i10, i11);
        if (iSearch >= 0) {
            return arrayList.get(iSearch);
        }
        return null;
    }

    private static final Anchor getOrAdd(ArrayList<Anchor> arrayList, int i10, int i11, r7.a<Anchor> aVar) {
        int iSearch = search(arrayList, i10, i11);
        if (iSearch >= 0) {
            return arrayList.get(iSearch);
        }
        Anchor anchor = (Anchor) aVar.invoke();
        arrayList.add(-(iSearch + 1), anchor);
        return anchor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupInfo(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupSize(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> groupSizes(int[] iArr, int i10) {
        return slice(iArr, qb.d.V(qb.d.X(3, i10), 5));
    }

    public static /* synthetic */ List groupSizes$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = iArr.length;
        }
        return groupSizes(iArr, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasAux(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 268435456) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasMark(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 134217728) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasObjectKey(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 536870912) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGroup(int[] iArr, int i10, int i11, boolean z, boolean z5, boolean z10, int i12, int i13) {
        int i14 = z ? 1073741824 : 0;
        int i15 = z5 ? 536870912 : 0;
        int i16 = z10 ? 268435456 : 0;
        int i17 = i10 * 5;
        iArr[i17] = i11;
        iArr[i17 + 1] = i14 | i15 | i16;
        iArr[i17 + 2] = i12;
        iArr[i17 + 3] = 0;
        iArr[i17 + 4] = i13;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNode(int[] iArr, int i10) {
        return (iArr[(i10 * 5) + 1] & 1073741824) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int key(int[] iArr, int i10) {
        return iArr[i10 * 5];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> keys(int[] iArr, int i10) {
        return slice(iArr, qb.d.V(qb.d.X(0, i10), 5));
    }

    public static /* synthetic */ List keys$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = iArr.length;
        }
        return keys(iArr, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int locationOf(ArrayList<Anchor> arrayList, int i10, int i11) {
        int iSearch = search(arrayList, i10, i11);
        return iSearch >= 0 ? iSearch : -(iSearch + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeCount(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 1] & NodeCount_Mask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> nodeCounts(int[] iArr, int i10) {
        List<Integer> listSlice = slice(iArr, qb.d.V(qb.d.X(1, i10), 5));
        ArrayList arrayList = new ArrayList(listSlice.size());
        int size = listSlice.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(Integer.valueOf(listSlice.get(i11).intValue() & NodeCount_Mask));
        }
        return arrayList;
    }

    public static /* synthetic */ List nodeCounts$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = iArr.length;
        }
        return nodeCounts(iArr, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeIndex(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int objectKeyIndex(int[] iArr, int i10) {
        int i11 = i10 * 5;
        return iArr[i11 + 4] + countOneBits(iArr[i11 + 1] >> 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int parentAnchor(int[] iArr, int i10) {
        return iArr[(i10 * 5) + 2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> parentAnchors(int[] iArr, int i10) {
        return slice(iArr, qb.d.V(qb.d.X(2, i10), 5));
    }

    public static /* synthetic */ List parentAnchors$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = iArr.length;
        }
        return parentAnchors(iArr, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int search(ArrayList<Anchor> arrayList, int i10, int i11) {
        int size = arrayList.size() - 1;
        int i12 = 0;
        while (i12 <= size) {
            int i13 = (i12 + size) >>> 1;
            int location$runtime_release = arrayList.get(i13).getLocation();
            if (location$runtime_release < 0) {
                location$runtime_release += i11;
            }
            int iC = p.c(location$runtime_release, i10);
            if (iC < 0) {
                i12 = i13 + 1;
            } else {
                if (iC <= 0) {
                    return i13;
                }
                size = i13 - 1;
            }
        }
        return -(i12 + 1);
    }

    private static final List<Integer> slice(int[] iArr, Iterable<Integer> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(iArr[it.next().intValue()]));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int slotAnchor(int[] iArr, int i10) {
        int i11 = i10 * 5;
        return iArr[i11 + 4] + countOneBits(iArr[i11 + 1] >> 28);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String summarize(String str, int i10) {
        String strO = u.O(u.O(u.O(u.O(u.O(u.O(u.O(u.O(u.O(u.O(str, "androidx.", "a.", false), "compose.", "c.", false), "runtime.", "r.", false), "internal.", "ι.", false), "ui.", "u.", false), "Modifier", "μ", false), "material.", "m.", false), "Function", "λ", false), "OpaqueKey", "κ", false), "MutableState", "σ", false);
        return strO.substring(0, Math.min(i10, strO.length()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateContainsMark(int[] iArr, int i10, boolean z) {
        int i11 = (i10 * 5) + 1;
        if (z) {
            iArr[i11] = iArr[i11] | 67108864;
        } else {
            iArr[i11] = iArr[i11] & (-67108865);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDataAnchor(int[] iArr, int i10, int i11) {
        iArr[(i10 * 5) + 4] = i11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupKey(int[] iArr, int i10, int i11) {
        iArr[i10 * 5] = i11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupSize(int[] iArr, int i10, int i11) {
        ComposerKt.runtimeCheck(i11 >= 0);
        iArr[(i10 * 5) + 3] = i11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMark(int[] iArr, int i10, boolean z) {
        int i11 = (i10 * 5) + 1;
        if (z) {
            iArr[i11] = iArr[i11] | 134217728;
        } else {
            iArr[i11] = iArr[i11] & (-134217729);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateNodeCount(int[] iArr, int i10, int i11) {
        ComposerKt.runtimeCheck(i11 >= 0 && i11 < NodeCount_Mask);
        int i12 = (i10 * 5) + 1;
        iArr[i12] = i11 | (iArr[i12] & (-67108864));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateParentAnchor(int[] iArr, int i10, int i11) {
        iArr[(i10 * 5) + 2] = i11;
    }
}
