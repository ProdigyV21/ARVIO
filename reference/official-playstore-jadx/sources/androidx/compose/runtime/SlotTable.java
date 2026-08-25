package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import t.v;
import t.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b/\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J9\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ9\u0010\u0011\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00000\u0007H\u0086\b¢\u0006\u0004\b\u0011\u0010\u000eJ\r\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b!\u0010\"J?\u0010*\u001a\u00020'2\u0006\u0010\u000b\u001a\u00020\b2&\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u0001`%H\u0000¢\u0006\u0004\b(\u0010)J\u008f\u0001\u0010*\u001a\u00020'2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00162\u000e\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0.2\u0006\u00101\u001a\u00020\u00162\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u001802j\b\u0012\u0004\u0012\u00020\u0018`32&\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u0001`%2\u000e\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105H\u0000¢\u0006\u0004\b(\u00108J\u0087\u0001\u0010;\u001a\u00020'2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00162\u000e\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0.2\u0006\u00101\u001a\u00020\u00162\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u001802j\b\u0012\u0004\u0012\u00020\u0018`32&\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u0001`%2\u000e\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105H\u0000¢\u0006\u0004\b9\u0010:J\u001f\u0010A\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=2\u0006\u0010<\u001a\u00020\u0016H\u0000¢\u0006\u0004\b?\u0010@J\r\u0010B\u001a\u00020\u001d¢\u0006\u0004\bB\u0010CJ\u0017\u0010E\u001a\u0004\u0018\u00010$2\u0006\u0010D\u001a\u00020\u0016¢\u0006\u0004\bE\u0010FJ\r\u0010G\u001a\u00020'¢\u0006\u0004\bG\u0010\u0005J\r\u0010H\u001a\u00020'¢\u0006\u0004\bH\u0010\u0005J\r\u0010I\u001a\u00020'¢\u0006\u0004\bI\u0010\u0005J\r\u0010K\u001a\u00020J¢\u0006\u0004\bK\u0010LJ\u001f\u0010N\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0=2\u0006\u0010D\u001a\u00020\u0016H\u0000¢\u0006\u0004\bM\u0010@J!\u0010R\u001a\u0004\u0018\u00010/2\u0006\u0010D\u001a\u00020\u00162\u0006\u0010O\u001a\u00020\u0016H\u0000¢\u0006\u0004\bP\u0010QJ\u0016\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00030SH\u0096\u0002¢\u0006\u0004\bT\u0010UJ\u0019\u0010W\u001a\u0004\u0018\u00010\u00032\u0006\u0010V\u001a\u00020/H\u0016¢\u0006\u0004\bW\u0010XJ\u0019\u0010Y\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\bY\u0010\u001aJ\u0019\u0010Z\u001a\u0004\u0018\u00010>2\u0006\u0010D\u001a\u00020\u0016H\u0002¢\u0006\u0004\bZ\u0010[J'\u0010_\u001a\u00020\u0016*\u00060\\j\u0002`]2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010^\u001a\u00020\u0016H\u0002¢\u0006\u0004\b_\u0010`J\u0015\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00160=H\u0002¢\u0006\u0004\ba\u0010bJ\u0015\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00160=H\u0002¢\u0006\u0004\bc\u0010bJ\u0015\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00160=H\u0002¢\u0006\u0004\bd\u0010bJ\u0015\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00160=H\u0002¢\u0006\u0004\be\u0010bJ\u0015\u0010f\u001a\b\u0012\u0004\u0012\u00020\u00160=H\u0002¢\u0006\u0004\bf\u0010bR$\u0010,\u001a\u00020+2\u0006\u0010g\u001a\u00020+8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b,\u0010h\u001a\u0004\bi\u0010jR$\u0010-\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00168\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b-\u0010k\u001a\u0004\bl\u0010mR4\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0.2\u000e\u0010g\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0.8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b0\u0010n\u001a\u0004\bo\u0010pR$\u00101\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00168\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b1\u0010k\u001a\u0004\bq\u0010mR\u0016\u0010r\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010kR$\u0010\u0010\u001a\u00020\u001d2\u0006\u0010g\u001a\u00020\u001d8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b\u0010\u0010s\u001a\u0004\bt\u0010CR\"\u0010u\u001a\u00020\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bu\u0010k\u001a\u0004\bv\u0010m\"\u0004\bw\u0010xR2\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u001802j\b\u0012\u0004\u0012\u00020\u0018`38\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b4\u0010y\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}RE\u0010&\u001a\"\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u00010#j\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020$\u0018\u0001`%8\u0000@\u0000X\u0080\u000e¢\u0006\u0015\n\u0004\b&\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R/\u00107\u001a\n\u0012\u0004\u0012\u000206\u0018\u0001058\u0000@\u0000X\u0080\u000e¢\u0006\u0017\n\u0005\b7\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010CR\u001d\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001¨\u0006\u008c\u0001"}, d2 = {"Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "<init>", "()V", "T", "Lkotlin/Function1;", "Landroidx/compose/runtime/SlotReader;", "Lx6/y;", ContentDisposition.Parameters.Name, "reader", "block", "read", "(Lr7/l;)Ljava/lang/Object;", "Landroidx/compose/runtime/SlotWriter;", "writer", "write", "openReader", "()Landroidx/compose/runtime/SlotReader;", "openWriter", "()Landroidx/compose/runtime/SlotWriter;", "", "index", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "(I)Landroidx/compose/runtime/Anchor;", "anchorIndex", "(Landroidx/compose/runtime/Anchor;)I", "", "ownsAnchor", "(Landroidx/compose/runtime/Anchor;)Z", "groupIndex", "groupContainsAnchor", "(ILandroidx/compose/runtime/Anchor;)Z", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "sourceInformationMap", "Lx6/t0;", "close$runtime_release", "(Landroidx/compose/runtime/SlotReader;Ljava/util/HashMap;)V", "close", "", "groups", "groupsSize", "", "", "slots", "slotsSize", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "anchors", "Lt/v;", "Lt/w;", "calledByMap", "(Landroidx/compose/runtime/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Lt/v;)V", "setTo$runtime_release", "([II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Lt/v;)V", "setTo", "target", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", "invalidateGroupsWithKey$runtime_release", "(I)Ljava/util/List;", "invalidateGroupsWithKey", "containsMark", "()Z", "group", "sourceInformationOf", "(I)Landroidx/compose/runtime/GroupSourceInformation;", "verifyWellFormed", "collectCalledByInformation", "collectSourceInformation", "", "toDebugString", "()Ljava/lang/String;", "slotsOf$runtime_release", "slotsOf", "slotIndex", "slot$runtime_release", "(II)Ljava/lang/Object;", "slot", "", "iterator", "()Ljava/util/Iterator;", "identityToFind", "find", "(Ljava/lang/Object;)Landroidx/compose/runtime/tooling/CompositionGroup;", "tryAnchor", "findEffectiveRecomposeScope", "(I)Landroidx/compose/runtime/RecomposeScopeImpl;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "level", "emitGroup", "(Ljava/lang/StringBuilder;II)I", UserMetadata.KEYDATA_FILENAME, "()Ljava/util/List;", "nodes", "parentIndexes", "dataIndexes", "groupSizes", "<set-?>", "[I", "getGroups", "()[I", "I", "getGroupsSize", "()I", "[Ljava/lang/Object;", "getSlots", "()[Ljava/lang/Object;", "getSlotsSize", "readers", "Z", "getWriter$runtime_release", "version", "getVersion$runtime_release", "setVersion$runtime_release", "(I)V", "Ljava/util/ArrayList;", "getAnchors$runtime_release", "()Ljava/util/ArrayList;", "setAnchors$runtime_release", "(Ljava/util/ArrayList;)V", "Ljava/util/HashMap;", "getSourceInformationMap$runtime_release", "()Ljava/util/HashMap;", "setSourceInformationMap$runtime_release", "(Ljava/util/HashMap;)V", "Lt/v;", "getCalledByMap$runtime_release", "()Lt/v;", "setCalledByMap$runtime_release", "(Lt/v;)V", "isEmpty", "getCompositionGroups", "()Ljava/lang/Iterable;", "compositionGroups", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SlotTable implements CompositionData, Iterable<CompositionGroup>, s7.a {
    public static final int $stable = 8;
    private v calledByMap;
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private ArrayList<Anchor> anchors = new ArrayList<>();

    private final List<Integer> dataIndexes() {
        return SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
    }

    private final int emitGroup(StringBuilder sb2, int i10, int i11) {
        String sourceInformation;
        for (int i12 = 0; i12 < i11; i12++) {
            sb2.append(' ');
        }
        sb2.append("Group(");
        sb2.append(i10);
        sb2.append(")");
        GroupSourceInformation groupSourceInformationSourceInformationOf = sourceInformationOf(i10);
        if (groupSourceInformationSourceInformationOf != null && (sourceInformation = groupSourceInformationSourceInformationOf.getSourceInformation()) != null && (u.P(sourceInformation, "C(", false) || u.P(sourceInformation, "CC(", false))) {
            int iF0 = o.f0(sourceInformation, "(", 0, false, 6) + 1;
            int iE0 = o.e0(sourceInformation, ')', 0, false, 6);
            sb2.append(" ");
            sb2.append(sourceInformation.substring(iF0, iE0));
            sb2.append("()");
        }
        sb2.append(" key=");
        sb2.append(SlotTableKt.key(this.groups, i10));
        int iGroupSize = SlotTableKt.groupSize(this.groups, i10);
        sb2.append(", nodes=");
        sb2.append(SlotTableKt.nodeCount(this.groups, i10));
        sb2.append(", size=");
        sb2.append(iGroupSize);
        if (SlotTableKt.hasMark(this.groups, i10)) {
            sb2.append(", mark");
        }
        if (SlotTableKt.containsMark(this.groups, i10)) {
            sb2.append(", contains mark");
        }
        int iEmitGroup$dataIndex = emitGroup$dataIndex(this, i10);
        int iEmitGroup = i10 + 1;
        int iEmitGroup$dataIndex2 = emitGroup$dataIndex(this, iEmitGroup);
        if (iEmitGroup$dataIndex < 0 || iEmitGroup$dataIndex > iEmitGroup$dataIndex2 || iEmitGroup$dataIndex2 > this.slotsSize) {
            sb2.append(", *invalid data offsets " + iEmitGroup$dataIndex + '-' + iEmitGroup$dataIndex2 + GMTDateParser.ANY);
        } else {
            if (SlotTableKt.hasObjectKey(this.groups, i10)) {
                sb2.append(" objectKey=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.objectKeyIndex(this.groups, i10)]), 10));
            }
            if (SlotTableKt.isNode(this.groups, i10)) {
                sb2.append(" node=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.nodeIndex(this.groups, i10)]), 10));
            }
            if (SlotTableKt.hasAux(this.groups, i10)) {
                sb2.append(" aux=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.auxIndex(this.groups, i10)]), 10));
            }
            int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i10);
            if (iSlotAnchor < iEmitGroup$dataIndex2) {
                sb2.append(", slots=[");
                sb2.append(iSlotAnchor);
                sb2.append(": ");
                for (int i13 = iSlotAnchor; i13 < iEmitGroup$dataIndex2; i13++) {
                    if (i13 != iSlotAnchor) {
                        sb2.append(", ");
                    }
                    sb2.append(SlotTableKt.summarize(String.valueOf(this.slots[i13]), 10));
                }
                sb2.append("]");
            }
        }
        sb2.append('\n');
        int i14 = i10 + iGroupSize;
        while (iEmitGroup < i14) {
            iEmitGroup += emitGroup(sb2, iEmitGroup, i11 + 1);
        }
        return iGroupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable slotTable, int i10) {
        return i10 >= slotTable.groupsSize ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i10);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        int iParentAnchor = group;
        while (iParentAnchor > 0) {
            for (Object obj : new DataIterator(this, iParentAnchor)) {
                if (obj instanceof RecomposeScopeImpl) {
                    RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
                    if (recomposeScopeImpl.getUsed() && iParentAnchor != group) {
                        return recomposeScopeImpl;
                    }
                    recomposeScopeImpl.setForcedRecompose(true);
                }
            }
            iParentAnchor = SlotTableKt.parentAnchor(this.groups, iParentAnchor);
        }
        return null;
    }

    private final List<Integer> groupSizes() {
        return SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
    }

    private static final void invalidateGroupsWithKey$lambda$20$scanGroup(SlotReader slotReader, w wVar, List<Anchor> list, f0 f0Var, SlotTable slotTable, List<RecomposeScopeImpl> list2) {
        RecomposeScopeImpl recomposeScopeImplFindEffectiveRecomposeScope;
        int groupKey = slotReader.getGroupKey();
        if (!wVar.b(groupKey)) {
            slotReader.startGroup();
            while (!slotReader.isGroupEnd()) {
                invalidateGroupsWithKey$lambda$20$scanGroup(slotReader, wVar, list, f0Var, slotTable, list2);
            }
            slotReader.endGroup();
            return;
        }
        if (groupKey != -3) {
            list.add(SlotReader.anchor$default(slotReader, 0, 1, null));
        }
        if (f0Var.f19738i) {
            RecomposeScopeImpl recomposeScopeImplFindEffectiveRecomposeScope2 = slotTable.findEffectiveRecomposeScope(slotReader.getCurrentGroup());
            if (recomposeScopeImplFindEffectiveRecomposeScope2 != null) {
                list2.add(recomposeScopeImplFindEffectiveRecomposeScope2);
                Anchor anchor = recomposeScopeImplFindEffectiveRecomposeScope2.getAnchor();
                if (anchor != null && anchor.getLocation() == slotReader.getCurrentGroup() && (recomposeScopeImplFindEffectiveRecomposeScope = slotTable.findEffectiveRecomposeScope(slotReader.getParent())) != null) {
                    list2.add(recomposeScopeImplFindEffectiveRecomposeScope);
                }
            } else {
                f0Var.f19738i = false;
                list2.clear();
            }
        }
        slotReader.skipGroup();
    }

    private final List<Integer> keys() {
        return SlotTableKt.keys(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> nodes() {
        return SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> parentIndexes() {
        return SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
    }

    private final Anchor tryAnchor(int index) {
        int i10;
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to crate an anchor for location instead");
        }
        if (index < 0 || index >= (i10 = this.groupsSize)) {
            return null;
        }
        return SlotTableKt.find(this.anchors, index, i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v42 */
    /* JADX WARN: Type inference failed for: r5v45 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v6 */
    private static final int verifyWellFormed$validateGroup(i0 i0Var, SlotTable slotTable, int i10, int i11) {
        int i12 = i0Var.f19744i;
        int i13 = i12 + 1;
        i0Var.f19744i = i13;
        int iParentAnchor = SlotTableKt.parentAnchor(slotTable.groups, i12);
        if ((iParentAnchor == i10) == false) {
            StringBuilder sbV = androidx.compose.foundation.c.v("Invalid parent index detected at ", i12, ", expected parent index to be ", i10, " found ");
            sbV.append(iParentAnchor);
            PreconditionsKt.throwIllegalStateException(sbV.toString());
        }
        int iGroupSize = SlotTableKt.groupSize(slotTable.groups, i12) + i12;
        if ((iGroupSize <= slotTable.groupsSize) == false) {
            PreconditionsKt.throwIllegalStateException("A group extends past the end of the table at " + i12);
        }
        if ((iGroupSize <= i11) == false) {
            PreconditionsKt.throwIllegalStateException("A group extends past its parent group at " + i12);
        }
        int iDataAnchor = SlotTableKt.dataAnchor(slotTable.groups, i12);
        int iDataAnchor2 = i12 >= slotTable.groupsSize - 1 ? slotTable.slotsSize : SlotTableKt.dataAnchor(slotTable.groups, i13);
        if ((iDataAnchor2 <= slotTable.slots.length) == false) {
            PreconditionsKt.throwIllegalStateException("Slots for " + i12 + " extend past the end of the slot table");
        }
        if ((iDataAnchor <= iDataAnchor2) == false) {
            PreconditionsKt.throwIllegalStateException("Invalid data anchor at " + i12);
        }
        if ((SlotTableKt.slotAnchor(slotTable.groups, i12) <= iDataAnchor2) == false) {
            PreconditionsKt.throwIllegalStateException("Slots start out of range at " + i12);
        }
        if ((iDataAnchor2 - iDataAnchor >= (SlotTableKt.hasAux(slotTable.groups, i12) ? 1 : 0) + ((SlotTableKt.hasObjectKey(slotTable.groups, i12) ? 1 : 0) + (SlotTableKt.isNode(slotTable.groups, i12) ? 1 : 0))) == false) {
            PreconditionsKt.throwIllegalStateException("Not enough slots added for group " + i12);
        }
        boolean zIsNode = SlotTableKt.isNode(slotTable.groups, i12);
        if (((zIsNode && slotTable.slots[SlotTableKt.nodeIndex(slotTable.groups, i12)] == null) ? false : true) == false) {
            PreconditionsKt.throwIllegalStateException("No node recorded for a node group at " + i12);
        }
        int iVerifyWellFormed$validateGroup = 0;
        while (i0Var.f19744i < iGroupSize) {
            iVerifyWellFormed$validateGroup += verifyWellFormed$validateGroup(i0Var, slotTable, i12, iGroupSize);
        }
        int iNodeCount = SlotTableKt.nodeCount(slotTable.groups, i12);
        int iGroupSize2 = SlotTableKt.groupSize(slotTable.groups, i12);
        if ((iNodeCount == iVerifyWellFormed$validateGroup) == false) {
            StringBuilder sbV2 = androidx.compose.foundation.c.v("Incorrect node count detected at ", i12, ", expected ", iNodeCount, ", received ");
            sbV2.append(iVerifyWellFormed$validateGroup);
            PreconditionsKt.throwIllegalStateException(sbV2.toString());
        }
        int i14 = i0Var.f19744i - i12;
        if ((iGroupSize2 == i14) == false) {
            StringBuilder sbV3 = androidx.compose.foundation.c.v("Incorrect slot count detected at ", i12, ", expected ", iGroupSize2, ", received ");
            sbV3.append(i14);
            PreconditionsKt.throwIllegalStateException(sbV3.toString());
        }
        if (SlotTableKt.containsAnyMark(slotTable.groups, i12)) {
            if (!(i12 <= 0 || SlotTableKt.containsMark(slotTable.groups, i10))) {
                PreconditionsKt.throwIllegalStateException("Expected group " + i10 + " to record it contains a mark because " + i12 + " does");
            }
        }
        if (zIsNode) {
            return 1;
        }
        return iVerifyWellFormed$validateGroup;
    }

    private static final void verifyWellFormed$verifySourceGroup(SlotTable slotTable, GroupSourceInformation groupSourceInformation) {
        ArrayList<Object> groups = groupSourceInformation.getGroups();
        if (groups != null) {
            int size = groups.size();
            for (int i10 = 0; i10 < size; i10++) {
                Object obj = groups.get(i10);
                if (obj instanceof Anchor) {
                    Anchor anchor = (Anchor) obj;
                    if (!anchor.getValid()) {
                        PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                    }
                    if (!slotTable.ownsAnchor(anchor)) {
                        PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                    }
                } else if (obj instanceof GroupSourceInformation) {
                    verifyWellFormed$verifySourceGroup(slotTable, (GroupSourceInformation) obj);
                }
            }
        }
    }

    public final Anchor anchor(int index) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to create an anchor location instead");
        }
        boolean z = false;
        if (index >= 0 && index < this.groupsSize) {
            z = true;
        }
        if (!z) {
            PreconditionsKt.throwIllegalArgumentException("Parameter index is out of range");
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, index, this.groupsSize);
        if (iSearch >= 0) {
            return arrayList.get(iSearch);
        }
        Anchor anchor = new Anchor(index);
        arrayList.add(-(iSearch + 1), anchor);
        return anchor;
    }

    public final int anchorIndex(Anchor anchor) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Use active SlotWriter to determine anchor location instead");
        }
        if (!anchor.getValid()) {
            PreconditionsKt.throwIllegalArgumentException("Anchor refers to a group that was removed");
        }
        return anchor.getLocation();
    }

    public final void close$runtime_release(SlotReader reader, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        if (!(reader.getTable() == this && this.readers > 0)) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        this.readers--;
        if (sourceInformationMap != null) {
            synchronized (this) {
                try {
                    HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
                    if (map != null) {
                        map.putAll(sourceInformationMap);
                    } else {
                        this.sourceInformationMap = sourceInformationMap;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void collectCalledByInformation() {
        this.calledByMap = new v();
    }

    public final void collectSourceInformation() {
        this.sourceInformationMap = new HashMap<>();
    }

    public final boolean containsMark() {
        return this.groupsSize > 0 && SlotTableKt.containsMark(this.groups, 0);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        return new SlotTableGroup(this, 0, 0, 4, null).find(identityToFind);
    }

    public final ArrayList<Anchor> getAnchors$runtime_release() {
        return this.anchors;
    }

    /* JADX INFO: renamed from: getCalledByMap$runtime_release, reason: from getter */
    public final v getCalledByMap() {
        return this.calledByMap;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    public final HashMap<Anchor, GroupSourceInformation> getSourceInformationMap$runtime_release() {
        return this.sourceInformationMap;
    }

    /* JADX INFO: renamed from: getVersion$runtime_release, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: getWriter$runtime_release, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    public final boolean groupContainsAnchor(int groupIndex, Anchor anchor) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Writer is active");
        }
        if (!(groupIndex >= 0 && groupIndex < this.groupsSize)) {
            ComposerKt.composeImmediateRuntimeError("Invalid group index");
        }
        if (ownsAnchor(anchor)) {
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndex) + groupIndex;
            int location$runtime_release = anchor.getLocation();
            if (groupIndex <= location$runtime_release && location$runtime_release < iGroupSize) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<androidx.compose.runtime.RecomposeScopeImpl> invalidateGroupsWithKey$runtime_release(int r21) {
        /*
            r20 = this;
            r0 = r21
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            kotlin.jvm.internal.f0 r4 = new kotlin.jvm.internal.f0
            r4.<init>()
            r7 = 1
            r4.f19738i = r7
            t.w r2 = new t.w
            r2.<init>()
            r2.a(r0)
            r1 = -3
            r2.a(r1)
            r5 = r20
            t.v r1 = r5.calledByMap
            if (r1 == 0) goto L79
            java.lang.Object r0 = r1.b(r0)
            t.w r0 = (t.w) r0
            if (r0 == 0) goto L79
            int[] r1 = r0.f21871b
            long[] r0 = r0.f21870a
            int r9 = r0.length
            int r9 = r9 + (-2)
            if (r9 < 0) goto L79
            r10 = 0
        L38:
            r11 = r0[r10]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L73
            int r13 = r10 - r9
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L52:
            if (r15 >= r13) goto L71
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto L6c
            int r16 = r10 << 3
            int r16 = r16 + r15
            r8 = r1[r16]
            int r16 = r2.c(r8)
            int[] r7 = r2.f21871b
            r7[r16] = r8
        L6c:
            long r11 = r11 >> r14
            int r15 = r15 + 1
            r7 = 1
            goto L52
        L71:
            if (r13 != r14) goto L79
        L73:
            if (r10 == r9) goto L79
            int r10 = r10 + 1
            r7 = 1
            goto L38
        L79:
            androidx.compose.runtime.SlotReader r1 = r5.openReader()
            invalidateGroupsWithKey$lambda$20$scanGroup(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lc3
            r1.close()
            androidx.compose.runtime.SlotWriter r1 = r20.openWriter()
            r1.startGroup()     // Catch: java.lang.Throwable -> La8
            int r0 = r3.size()     // Catch: java.lang.Throwable -> La8
            r2 = 0
        L8f:
            if (r2 >= r0) goto Lae
            java.lang.Object r5 = r3.get(r2)     // Catch: java.lang.Throwable -> La8
            androidx.compose.runtime.Anchor r5 = (androidx.compose.runtime.Anchor) r5     // Catch: java.lang.Throwable -> La8
            int r7 = r5.toIndexFor(r1)     // Catch: java.lang.Throwable -> La8
            int r8 = r1.getCurrentGroup()     // Catch: java.lang.Throwable -> La8
            if (r7 < r8) goto Lab
            r1.seek(r5)     // Catch: java.lang.Throwable -> La8
            r1.bashCurrentGroup()     // Catch: java.lang.Throwable -> La8
            goto Lab
        La8:
            r0 = move-exception
            r2 = 0
            goto Lbf
        Lab:
            int r2 = r2 + 1
            goto L8f
        Lae:
            r1.skipToGroupEnd()     // Catch: java.lang.Throwable -> La8
            r1.endGroup()     // Catch: java.lang.Throwable -> La8
            r0 = 1
            r1.close(r0)
            boolean r0 = r4.f19738i
            if (r0 == 0) goto Lbd
            return r6
        Lbd:
            r0 = 0
            return r0
        Lbf:
            r1.close(r2)
            throw r0
        Lc3:
            r0 = move-exception
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotTable.invalidateGroupsWithKey$runtime_release(int):java.util.List");
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending");
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when another writer is pending");
        }
        if (!(this.readers <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when a reader is pending");
        }
        this.writer = true;
        this.version++;
        return new SlotWriter(this);
    }

    public final boolean ownsAnchor(Anchor anchor) {
        int iSearch;
        return anchor.getValid() && (iSearch = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize)) >= 0 && p.a(this.anchors.get(iSearch), anchor);
    }

    public final <T> T read(r7.l<? super SlotReader, ? extends T> block) {
        SlotReader slotReaderOpenReader = openReader();
        try {
            return (T) block.invoke(slotReaderOpenReader);
        } finally {
            slotReaderOpenReader.close();
        }
    }

    public final void setAnchors$runtime_release(ArrayList<Anchor> arrayList) {
        this.anchors = arrayList;
    }

    public final void setCalledByMap$runtime_release(v vVar) {
        this.calledByMap = vVar;
    }

    public final void setSourceInformationMap$runtime_release(HashMap<Anchor, GroupSourceInformation> map) {
        this.sourceInformationMap = map;
    }

    public final void setTo$runtime_release(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, v calledByMap) {
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
        this.sourceInformationMap = sourceInformationMap;
        this.calledByMap = calledByMap;
    }

    public final void setVersion$runtime_release(int i10) {
        this.version = i10;
    }

    public final Object slot$runtime_release(int group, int slotIndex) {
        int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, group);
        int i10 = group + 1;
        return (slotIndex < 0 || slotIndex >= (i10 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i10) : this.slots.length) - iSlotAnchor) ? Composer.INSTANCE.getEmpty() : this.slots[iSlotAnchor + slotIndex];
    }

    public final List<Object> slotsOf$runtime_release(int group) {
        int iDataAnchor = SlotTableKt.dataAnchor(this.groups, group);
        int i10 = group + 1;
        return r.o0(this.slots).subList(iDataAnchor, i10 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i10) : this.slots.length);
    }

    public final GroupSourceInformation sourceInformationOf(int group) {
        Anchor anchorTryAnchor;
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchorTryAnchor = tryAnchor(group)) == null) {
            return null;
        }
        return map.get(anchorTryAnchor);
    }

    public final String toDebugString() {
        if (this.writer) {
            return toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(toString());
        sb2.append('\n');
        int i10 = this.groupsSize;
        if (i10 > 0) {
            int iEmitGroup = 0;
            while (iEmitGroup < i10) {
                iEmitGroup += emitGroup(sb2, iEmitGroup, 0);
            }
        } else {
            sb2.append("<EMPTY>");
        }
        return sb2.toString();
    }

    public final void verifyWellFormed() {
        int i10;
        int i11;
        i0 i0Var = new i0();
        int i12 = -1;
        if (this.groupsSize > 0) {
            while (true) {
                i10 = i0Var.f19744i;
                i11 = this.groupsSize;
                if (i10 >= i11) {
                    break;
                } else {
                    verifyWellFormed$validateGroup(i0Var, this, -1, SlotTableKt.groupSize(this.groups, i10) + i10);
                }
            }
            if (!(i10 == i11)) {
                PreconditionsKt.throwIllegalStateException("Incomplete group at root " + i0Var.f19744i + " expected to be " + this.groupsSize);
            }
        }
        int length = this.slots.length;
        for (int i13 = this.slotsSize; i13 < length; i13++) {
            if (!(this.slots[i13] == null)) {
                PreconditionsKt.throwIllegalStateException("Non null value in the slot gap at index " + i13);
            }
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int size = arrayList.size();
        int i14 = 0;
        while (i14 < size) {
            int indexFor = arrayList.get(i14).toIndexFor(this);
            if (!(indexFor >= 0 && indexFor <= this.groupsSize)) {
                PreconditionsKt.throwIllegalArgumentException("Invalid anchor, location out of bound");
            }
            if (!(i12 < indexFor)) {
                PreconditionsKt.throwIllegalArgumentException("Anchor is out of order");
            }
            i14++;
            i12 = indexFor;
        }
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map != null) {
            for (Map.Entry<Anchor, GroupSourceInformation> entry : map.entrySet()) {
                Anchor key = entry.getKey();
                GroupSourceInformation value = entry.getValue();
                if (!key.getValid()) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                if (!ownsAnchor(key)) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
                verifyWellFormed$verifySourceGroup(this, value);
            }
        }
    }

    public final <T> T write(r7.l<? super SlotWriter, ? extends T> block) {
        SlotWriter slotWriterOpenWriter = openWriter();
        try {
            T t2 = (T) block.invoke(slotWriterOpenWriter);
            slotWriterOpenWriter.close(true);
            return t2;
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    public final void close$runtime_release(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, v calledByMap) {
        if (!(writer.getTable() == this && this.writer)) {
            PreconditionsKt.throwIllegalArgumentException("Unexpected writer close()");
        }
        this.writer = false;
        setTo$runtime_release(groups, groupsSize, slots, slotsSize, anchors, sourceInformationMap, calledByMap);
    }
}
