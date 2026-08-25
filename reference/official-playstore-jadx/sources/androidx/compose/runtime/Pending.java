package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import t.v;
import t.z;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005¢\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\"\u0010!J\u0015\u0010#\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b#\u0010!R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b(\u0010)R\"\u0010*\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010'\u001a\u0004\b+\u0010)\"\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010$R\u001a\u00101\u001a\b\u0012\u0004\u0012\u0002000/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R-\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0003038FX\u0086\u0084\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\u0003098F¢\u0006\u0006\u001a\u0004\b:\u0010&\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/runtime/Pending;", "", "", "Landroidx/compose/runtime/KeyInfo;", "keyInfos", "", "startIndex", "<init>", "(Ljava/util/List;I)V", "key", "dataKey", "getNext", "(ILjava/lang/Object;)Landroidx/compose/runtime/KeyInfo;", "keyInfo", "", "recordUsed", "(Landroidx/compose/runtime/KeyInfo;)Z", "from", "to", "Lx6/t0;", "registerMoveSlot", "(II)V", "count", "registerMoveNode", "(III)V", "insertIndex", "registerInsert", "(Landroidx/compose/runtime/KeyInfo;I)V", "group", "newCount", "updateNodeCount", "(II)Z", "slotPositionOf", "(Landroidx/compose/runtime/KeyInfo;)I", "nodePositionOf", "updatedNodeCountOf", "Ljava/util/List;", "getKeyInfos", "()Ljava/util/List;", "I", "getStartIndex", "()I", "groupIndex", "getGroupIndex", "setGroupIndex", "(I)V", "usedKeys", "Lt/v;", "Landroidx/compose/runtime/GroupInfo;", "groupInfos", "Lt/v;", "Landroidx/compose/runtime/MutableScatterMultiMap;", "keyMap$delegate", "Lx6/s;", "getKeyMap-SAeQiB4", "()Lt/z;", "keyMap", "", "getUsed", "used", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Pending {
    private int groupIndex;
    private final v groupInfos;
    private final List<KeyInfo> keyInfos;

    /* JADX INFO: renamed from: keyMap$delegate, reason: from kotlin metadata */
    private final s keyMap;
    private final int startIndex;
    private final List<KeyInfo> usedKeys;

    public Pending(List<KeyInfo> list, int i10) {
        this.keyInfos = list;
        this.startIndex = i10;
        if (i10 < 0) {
            PreconditionsKt.throwIllegalArgumentException("Invalid start index");
        }
        this.usedKeys = new ArrayList();
        v vVar = new v();
        int size = list.size();
        int nodes = 0;
        for (int i11 = 0; i11 < size; i11++) {
            KeyInfo keyInfo = this.keyInfos.get(i11);
            vVar.f(keyInfo.getLocation(), new GroupInfo(i11, nodes, keyInfo.getNodes()));
            nodes += keyInfo.getNodes();
        }
        this.groupInfos = vVar;
        this.keyMap = new i0(new Pending$keyMap$2(this));
    }

    public final int getGroupIndex() {
        return this.groupIndex;
    }

    public final List<KeyInfo> getKeyInfos() {
        return this.keyInfos;
    }

    /* JADX INFO: renamed from: getKeyMap-SAeQiB4, reason: not valid java name */
    public final z m2974getKeyMapSAeQiB4() {
        return ((MutableScatterMultiMap) this.keyMap.getValue()).m2973unboximpl();
    }

    public final KeyInfo getNext(int key, Object dataKey) {
        return (KeyInfo) MutableScatterMultiMap.m2970popimpl(m2974getKeyMapSAeQiB4(), dataKey != null ? new JoinedKey(Integer.valueOf(key), dataKey) : Integer.valueOf(key));
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final List<KeyInfo> getUsed() {
        return this.usedKeys;
    }

    public final int nodePositionOf(KeyInfo keyInfo) {
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.b(keyInfo.getLocation());
        if (groupInfo != null) {
            return groupInfo.getNodeIndex();
        }
        return -1;
    }

    public final boolean recordUsed(KeyInfo keyInfo) {
        return this.usedKeys.add(keyInfo);
    }

    public final void registerInsert(KeyInfo keyInfo, int insertIndex) {
        this.groupInfos.f(keyInfo.getLocation(), new GroupInfo(-1, insertIndex, 0));
    }

    public final void registerMoveNode(int from, int to, int count) {
        char c10;
        long j10;
        char c11;
        long j11;
        char c12 = 7;
        long j12 = -9187201950435737472L;
        if (from > to) {
            v vVar = this.groupInfos;
            Object[] objArr = vVar.f21844c;
            long[] jArr = vVar.f21842a;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i10 = 0;
            while (true) {
                long j13 = jArr[i10];
                if ((((~j13) << c12) & j13 & j12) != j12) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    int i12 = 0;
                    while (i12 < i11) {
                        if ((j13 & 255) < 128) {
                            c11 = c12;
                            GroupInfo groupInfo = (GroupInfo) objArr[(i10 << 3) + i12];
                            j11 = j12;
                            int nodeIndex = groupInfo.getNodeIndex();
                            if (from <= nodeIndex && nodeIndex < from + count) {
                                groupInfo.setNodeIndex((nodeIndex - from) + to);
                            } else if (to <= nodeIndex && nodeIndex < from) {
                                groupInfo.setNodeIndex(nodeIndex + count);
                            }
                        } else {
                            c11 = c12;
                            j11 = j12;
                        }
                        j13 >>= 8;
                        i12++;
                        c12 = c11;
                        j12 = j11;
                    }
                    c10 = c12;
                    j10 = j12;
                    if (i11 != 8) {
                        return;
                    }
                } else {
                    c10 = c12;
                    j10 = j12;
                }
                if (i10 == length) {
                    return;
                }
                i10++;
                c12 = c10;
                j12 = j10;
            }
        } else {
            if (to <= from) {
                return;
            }
            v vVar2 = this.groupInfos;
            Object[] objArr2 = vVar2.f21844c;
            long[] jArr2 = vVar2.f21842a;
            int length2 = jArr2.length - 2;
            if (length2 < 0) {
                return;
            }
            int i13 = 0;
            while (true) {
                long j14 = jArr2[i13];
                if ((((~j14) << 7) & j14 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i14 = 8 - ((~(i13 - length2)) >>> 31);
                    for (int i15 = 0; i15 < i14; i15++) {
                        if ((j14 & 255) < 128) {
                            GroupInfo groupInfo2 = (GroupInfo) objArr2[(i13 << 3) + i15];
                            int nodeIndex2 = groupInfo2.getNodeIndex();
                            if (from <= nodeIndex2 && nodeIndex2 < from + count) {
                                groupInfo2.setNodeIndex((nodeIndex2 - from) + to);
                            } else if (from + 1 <= nodeIndex2 && nodeIndex2 < to) {
                                groupInfo2.setNodeIndex(nodeIndex2 - count);
                            }
                        }
                        j14 >>= 8;
                    }
                    if (i14 != 8) {
                        return;
                    }
                }
                if (i13 == length2) {
                    return;
                } else {
                    i13++;
                }
            }
        }
    }

    public final void registerMoveSlot(int from, int to) {
        char c10;
        long j10;
        char c11;
        long j11;
        char c12 = 7;
        long j12 = -9187201950435737472L;
        if (from > to) {
            v vVar = this.groupInfos;
            Object[] objArr = vVar.f21844c;
            long[] jArr = vVar.f21842a;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i10 = 0;
            while (true) {
                long j13 = jArr[i10];
                if ((((~j13) << c12) & j13 & j12) != j12) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    int i12 = 0;
                    while (i12 < i11) {
                        if ((j13 & 255) < 128) {
                            c11 = c12;
                            GroupInfo groupInfo = (GroupInfo) objArr[(i10 << 3) + i12];
                            j11 = j12;
                            int slotIndex = groupInfo.getSlotIndex();
                            if (slotIndex == from) {
                                groupInfo.setSlotIndex(to);
                            } else if (to <= slotIndex && slotIndex < from) {
                                groupInfo.setSlotIndex(slotIndex + 1);
                            }
                        } else {
                            c11 = c12;
                            j11 = j12;
                        }
                        j13 >>= 8;
                        i12++;
                        c12 = c11;
                        j12 = j11;
                    }
                    c10 = c12;
                    j10 = j12;
                    if (i11 != 8) {
                        return;
                    }
                } else {
                    c10 = c12;
                    j10 = j12;
                }
                if (i10 == length) {
                    return;
                }
                i10++;
                c12 = c10;
                j12 = j10;
            }
        } else {
            if (to <= from) {
                return;
            }
            v vVar2 = this.groupInfos;
            Object[] objArr2 = vVar2.f21844c;
            long[] jArr2 = vVar2.f21842a;
            int length2 = jArr2.length - 2;
            if (length2 < 0) {
                return;
            }
            int i13 = 0;
            while (true) {
                long j14 = jArr2[i13];
                if ((((~j14) << 7) & j14 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i14 = 8 - ((~(i13 - length2)) >>> 31);
                    for (int i15 = 0; i15 < i14; i15++) {
                        if ((j14 & 255) < 128) {
                            GroupInfo groupInfo2 = (GroupInfo) objArr2[(i13 << 3) + i15];
                            int slotIndex2 = groupInfo2.getSlotIndex();
                            if (slotIndex2 == from) {
                                groupInfo2.setSlotIndex(to);
                            } else if (from + 1 <= slotIndex2 && slotIndex2 < to) {
                                groupInfo2.setSlotIndex(slotIndex2 - 1);
                            }
                        }
                        j14 >>= 8;
                    }
                    if (i14 != 8) {
                        return;
                    }
                }
                if (i13 == length2) {
                    return;
                } else {
                    i13++;
                }
            }
        }
    }

    public final void setGroupIndex(int i10) {
        this.groupIndex = i10;
    }

    public final int slotPositionOf(KeyInfo keyInfo) {
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.b(keyInfo.getLocation());
        if (groupInfo != null) {
            return groupInfo.getSlotIndex();
        }
        return -1;
    }

    public final boolean updateNodeCount(int group, int newCount) {
        int nodeIndex;
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.b(group);
        if (groupInfo == null) {
            return false;
        }
        int nodeIndex2 = groupInfo.getNodeIndex();
        int nodeCount = newCount - groupInfo.getNodeCount();
        groupInfo.setNodeCount(newCount);
        if (nodeCount == 0) {
            return true;
        }
        v vVar = this.groupInfos;
        Object[] objArr = vVar.f21844c;
        long[] jArr = vVar.f21842a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        GroupInfo groupInfo2 = (GroupInfo) objArr[(i10 << 3) + i12];
                        if (groupInfo2.getNodeIndex() >= nodeIndex2 && !groupInfo2.equals(groupInfo) && (nodeIndex = groupInfo2.getNodeIndex() + nodeCount) >= 0) {
                            groupInfo2.setNodeIndex(nodeIndex);
                        }
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return true;
                }
            }
            if (i10 == length) {
                return true;
            }
            i10++;
        }
    }

    public final int updatedNodeCountOf(KeyInfo keyInfo) {
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.b(keyInfo.getLocation());
        return groupInfo != null ? groupInfo.getNodeCount() : keyInfo.getNodes();
    }
}
