package androidx.compose.runtime;

import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\bJ\u0015\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u001a\u0010\u001eJ%\u0010 \u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0002¢\u0006\u0004\b\"\u0010\u0017J\u0015\u0010#\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b#\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b,\u0010&R6\u0010/\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010-j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`.8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\"\u00105\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010;\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010$\u001a\u0004\b<\u0010&\"\u0004\b=\u0010\u0017¨\u0006>"}, d2 = {"Landroidx/compose/runtime/GroupSourceInformation;", "", "", "key", "", "sourceInformation", "dataStartOffset", "<init>", "(ILjava/lang/String;I)V", "openInformation", "()Landroidx/compose/runtime/GroupSourceInformation;", "group", "Lx6/t0;", "add", "(Ljava/lang/Object;)V", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "", "hasAnchor", "(Landroidx/compose/runtime/Anchor;)Z", "dataOffset", "startGrouplessCall", "endGrouplessCall", "(I)V", "Landroidx/compose/runtime/SlotWriter;", "writer", "reportGroup", "(Landroidx/compose/runtime/SlotWriter;I)V", "Landroidx/compose/runtime/SlotTable;", "table", "(Landroidx/compose/runtime/SlotTable;I)V", "predecessor", "addGroupAfter", "(Landroidx/compose/runtime/SlotWriter;II)V", "close", "removeAnchor", "I", "getKey", "()I", "Ljava/lang/String;", "getSourceInformation", "()Ljava/lang/String;", "setSourceInformation", "(Ljava/lang/String;)V", "getDataStartOffset", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "groups", "Ljava/util/ArrayList;", "getGroups", "()Ljava/util/ArrayList;", "setGroups", "(Ljava/util/ArrayList;)V", "closed", "Z", "getClosed", "()Z", "setClosed", "(Z)V", "dataEndOffset", "getDataEndOffset", "setDataEndOffset", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GroupSourceInformation {
    public static final int $stable = 8;
    private boolean closed;
    private int dataEndOffset;
    private final int dataStartOffset;
    private ArrayList<Object> groups;
    private final int key;
    private String sourceInformation;

    public GroupSourceInformation(int i10, String str, int i11) {
        this.key = i10;
        this.sourceInformation = str;
        this.dataStartOffset = i11;
    }

    private final void add(Object group) {
        ArrayList<Object> arrayList = this.groups;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        this.groups = arrayList;
        arrayList.add(group);
    }

    private final boolean hasAnchor(Anchor anchor) {
        ArrayList<Object> arrayList = this.groups;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                Object obj = arrayList.get(i10);
                if (p.a(obj, anchor)) {
                    return true;
                }
                if ((obj instanceof GroupSourceInformation) && ((GroupSourceInformation) obj).hasAnchor(anchor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final GroupSourceInformation openInformation() {
        Object obj;
        GroupSourceInformation groupSourceInformationOpenInformation;
        ArrayList<Object> arrayList = this.groups;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                obj = arrayList.get(size);
                if ((obj instanceof GroupSourceInformation) && !((GroupSourceInformation) obj).closed) {
                    break;
                }
            }
            obj = null;
        } else {
            obj = null;
        }
        GroupSourceInformation groupSourceInformation = obj instanceof GroupSourceInformation ? (GroupSourceInformation) obj : null;
        return (groupSourceInformation == null || (groupSourceInformationOpenInformation = groupSourceInformation.openInformation()) == null) ? this : groupSourceInformationOpenInformation;
    }

    public final void addGroupAfter(SlotWriter writer, int predecessor, int group) {
        Anchor anchorTryAnchor$runtime_release;
        ArrayList<Object> arrayList = this.groups;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.groups = arrayList;
        }
        int i10 = 0;
        if (predecessor >= 0 && (anchorTryAnchor$runtime_release = writer.tryAnchor$runtime_release(predecessor)) != null) {
            int size = arrayList.size();
            while (true) {
                if (i10 >= size) {
                    i10 = -1;
                    break;
                }
                Object obj = arrayList.get(i10);
                if (p.a(obj, anchorTryAnchor$runtime_release) || ((obj instanceof GroupSourceInformation) && ((GroupSourceInformation) obj).hasAnchor(anchorTryAnchor$runtime_release))) {
                    break;
                } else {
                    i10++;
                }
            }
        }
        arrayList.add(i10, writer.anchor(group));
    }

    public final void close(int dataOffset) {
        this.closed = true;
        this.dataEndOffset = dataOffset;
    }

    public final void endGrouplessCall(int dataOffset) {
        openInformation().close(dataOffset);
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final int getDataEndOffset() {
        return this.dataEndOffset;
    }

    public final int getDataStartOffset() {
        return this.dataStartOffset;
    }

    public final ArrayList<Object> getGroups() {
        return this.groups;
    }

    public final int getKey() {
        return this.key;
    }

    public final String getSourceInformation() {
        return this.sourceInformation;
    }

    public final boolean removeAnchor(Anchor anchor) {
        ArrayList<Object> arrayList = this.groups;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Object obj = arrayList.get(size);
                if (obj instanceof Anchor) {
                    if (obj.equals(anchor)) {
                        arrayList.remove(size);
                    }
                } else if ((obj instanceof GroupSourceInformation) && !((GroupSourceInformation) obj).removeAnchor(anchor)) {
                    arrayList.remove(size);
                }
            }
            if (arrayList.isEmpty()) {
                this.groups = null;
                return false;
            }
        }
        return true;
    }

    public final void reportGroup(SlotWriter writer, int group) {
        openInformation().add(writer.anchor(group));
    }

    public final void setClosed(boolean z) {
        this.closed = z;
    }

    public final void setDataEndOffset(int i10) {
        this.dataEndOffset = i10;
    }

    public final void setGroups(ArrayList<Object> arrayList) {
        this.groups = arrayList;
    }

    public final void setSourceInformation(String str) {
        this.sourceInformation = str;
    }

    public final void startGrouplessCall(int key, String sourceInformation, int dataOffset) {
        openInformation().add(new GroupSourceInformation(key, sourceInformation, dataOffset));
    }

    public final void reportGroup(SlotTable table, int group) {
        openInformation().add(table.anchor(group));
    }
}
