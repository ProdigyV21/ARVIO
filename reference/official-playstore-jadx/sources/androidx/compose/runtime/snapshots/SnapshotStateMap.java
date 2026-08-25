package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import s7.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010'\n\u0002\b\u0004\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0010\u001f\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004:\u0001XB\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u001a\u0010\u0014\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001b\u0010\u0006J!\u0010\u001c\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ%\u0010\u001f\u001a\u00020\t2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\fH\u0016¢\u0006\u0004\b\u001f\u0010 J\u0019\u0010!\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b!\u0010\u0015J\u0017\u0010#\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0001H\u0000¢\u0006\u0004\b\"\u0010\u0012J0\u0010)\u001a\u00020\u00102\u001e\u0010&\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010%\u0012\u0004\u0012\u00020\u00100$H\u0080\b¢\u0006\u0004\b'\u0010(J0\u0010,\u001a\u00020\u00102\u001e\u0010&\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*\u0012\u0004\u0012\u00020\u00100$H\u0080\b¢\u0006\u0004\b+\u0010(J0\u0010.\u001a\u00020\u00102\u001e\u0010&\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010*\u0012\u0004\u0012\u00020\u00100$H\u0080\b¢\u0006\u0004\b-\u0010(J;\u00103\u001a\u00028\u0002\"\u0004\b\u0002\u0010/2#\u00102\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100\u0012\u0004\u0012\u00028\u00020$¢\u0006\u0002\b1H\u0082\b¢\u0006\u0004\b3\u00104J;\u00105\u001a\u00028\u0002\"\u0004\b\u0002\u0010/2#\u00102\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100\u0012\u0004\u0012\u00028\u00020$¢\u0006\u0002\b1H\u0082\b¢\u0006\u0004\b5\u00104J6\u00106\u001a\u00028\u0002\"\u0004\b\u0002\u0010/2\u001e\u00102\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020$H\u0082\b¢\u0006\u0004\b6\u00104J<\u00108\u001a\u00020\t2*\u00102\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000107\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001070$H\u0082\b¢\u0006\u0004\b8\u00109R$\u0010;\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00078\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R,\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010%0?8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR \u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000?8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bD\u0010A\u001a\u0004\bE\u0010CR \u0010G\u001a\b\u0012\u0004\u0012\u00028\u00010F8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0014\u0010N\u001a\u00020K8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010P\u001a\u00020K8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bO\u0010MR&\u0010T\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001008@X\u0080\u0004¢\u0006\f\u0012\u0004\bS\u0010\u0006\u001a\u0004\bQ\u0010RR&\u0010W\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f8AX\u0080\u0004¢\u0006\f\u0012\u0004\bV\u0010\u0006\u001a\u0004\bU\u0010\u000e¨\u0006Y"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateMap;", "K", "V", "Landroidx/compose/runtime/snapshots/StateObject;", "", "<init>", "()V", "Landroidx/compose/runtime/snapshots/StateRecord;", "value", "Lx6/t0;", "prependStateRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "", "toMap", "()Ljava/util/Map;", "key", "", "containsKey", "(Ljava/lang/Object;)Z", "containsValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "()Z", "", "toString", "()Ljava/lang/String;", "clear", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "from", "putAll", "(Ljava/util/Map;)V", "remove", "removeValue$runtime_release", "removeValue", "Lkotlin/Function1;", "", "predicate", "removeIf$runtime_release", "(Lr7/l;)Z", "removeIf", "", "any$runtime_release", "any", "all$runtime_release", TtmlNode.COMBINE_ALL, "R", "Landroidx/compose/runtime/snapshots/SnapshotStateMap$StateMapStateRecord;", "Lx6/n;", "block", "withCurrent", "(Lr7/l;)Ljava/lang/Object;", "writable", "mutate", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;", "update", "(Lr7/l;)V", "<set-?>", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "", "entries", "Ljava/util/Set;", "getEntries", "()Ljava/util/Set;", UserMetadata.KEYDATA_FILENAME, "getKeys", "", "values", "Ljava/util/Collection;", "getValues", "()Ljava/util/Collection;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "getModification$runtime_release", "modification", "getReadable$runtime_release", "()Landroidx/compose/runtime/snapshots/SnapshotStateMap$StateMapStateRecord;", "getReadable$runtime_release$annotations", "readable", "getDebuggerDisplayValue", "getDebuggerDisplayValue$annotations", "debuggerDisplayValue", "StateMapStateRecord", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotStateMap<K, V> implements StateObject, Map<K, V>, g {
    public static final int $stable = 0;
    private final Set<Map.Entry<K, V>> entries;
    private StateRecord firstStateRecord;
    private final Set<K> keys;
    private final Collection<V> values;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u001d\b\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\f\u0010\rR.\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0007R\"\u0010\u0013\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateMap$StateMapStateRecord;", "K", "V", "Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;", "map", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;)V", "value", "Lx6/t0;", "assign", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "create", "()Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;", "getMap$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentMap;", "setMap$runtime_release", "", "modification", "I", "getModification$runtime_release", "()I", "setModification$runtime_release", "(I)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StateMapStateRecord<K, V> extends StateRecord {
        public static final int $stable = 8;
        private PersistentMap<K, ? extends V> map;
        private int modification;

        public StateMapStateRecord(PersistentMap<K, ? extends V> persistentMap) {
            this.map = persistentMap;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) value;
            synchronized (SnapshotStateMapKt.sync) {
                this.map = stateMapStateRecord.map;
                this.modification = stateMapStateRecord.modification;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new StateMapStateRecord(this.map);
        }

        public final PersistentMap<K, V> getMap$runtime_release() {
            return this.map;
        }

        /* JADX INFO: renamed from: getModification$runtime_release, reason: from getter */
        public final int getModification() {
            return this.modification;
        }

        public final void setMap$runtime_release(PersistentMap<K, ? extends V> persistentMap) {
            this.map = persistentMap;
        }

        public final void setModification$runtime_release(int i10) {
            this.modification = i10;
        }
    }

    public SnapshotStateMap() {
        PersistentMap persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
        StateMapStateRecord stateMapStateRecord = new StateMapStateRecord(persistentMapPersistentHashMapOf);
        if (Snapshot.INSTANCE.isInSnapshot()) {
            StateMapStateRecord stateMapStateRecord2 = new StateMapStateRecord(persistentMapPersistentHashMapOf);
            stateMapStateRecord2.setSnapshotId$runtime_release(1);
            stateMapStateRecord.setNext$runtime_release(stateMapStateRecord2);
        }
        this.firstStateRecord = stateMapStateRecord;
        this.entries = new SnapshotMapEntrySet(this);
        this.keys = new SnapshotMapKeySet(this);
        this.values = new SnapshotMapValueSet(this);
    }

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    public static /* synthetic */ void getReadable$runtime_release$annotations() {
    }

    private final <R> R mutate(l<? super Map<K, V>, ? extends R> block) {
        PersistentMap<K, V> map$runtime_release;
        int modification;
        R r4;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
                map$runtime_release = stateMapStateRecord.getMap$runtime_release();
                modification = stateMapStateRecord.getModification();
            }
            PersistentMap.Builder<K, V> builder = map$runtime_release.builder();
            r4 = (R) block.invoke(builder);
            PersistentMap<K, V> persistentMapBuild = builder.build();
            if (p.a(persistentMapBuild, map$runtime_release)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord3.getModification() == modification) {
                        stateMapStateRecord3.setMap$runtime_release(persistentMapBuild);
                        z = true;
                        stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return r4;
    }

    private final void update(l<? super PersistentMap<K, ? extends V>, ? extends PersistentMap<K, ? extends V>> block) {
        Snapshot current;
        StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
        PersistentMap<K, V> persistentMap = (PersistentMap) block.invoke(stateMapStateRecord.getMap$runtime_release());
        if (persistentMap != stateMapStateRecord.getMap$runtime_release()) {
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    stateMapStateRecord3.setMap$runtime_release(persistentMap);
                    stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                }
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    private final <R> R withCurrent(l<? super StateMapStateRecord<K, V>, ? extends R> block) {
        return (R) block.invoke(SnapshotKt.current((StateMapStateRecord) getFirstStateRecord()));
    }

    private final <R> R writable(l<? super StateMapStateRecord<K, V>, ? extends R> block) {
        Snapshot current;
        R r4;
        StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) getFirstStateRecord();
        SnapshotKt.getSnapshotInitializer();
        synchronized (SnapshotKt.getLock()) {
            current = Snapshot.INSTANCE.getCurrent();
            r4 = (R) block.invoke(SnapshotKt.writableRecord(stateMapStateRecord, this, current));
        }
        SnapshotKt.notifyWrite(current, this);
        return r4;
    }

    public final boolean all$runtime_release(l<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Iterator<E> it = ((ImmutableSet) getReadable$runtime_release().getMap$runtime_release().entrySet()).iterator();
        while (it.hasNext()) {
            if (!((Boolean) predicate.invoke((Map.Entry) it.next())).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public final boolean any$runtime_release(l<? super Map.Entry<? extends K, ? extends V>, Boolean> predicate) {
        Iterator<E> it = ((ImmutableSet) getReadable$runtime_release().getMap$runtime_release().entrySet()).iterator();
        while (it.hasNext()) {
            if (((Boolean) predicate.invoke((Map.Entry) it.next())).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public void clear() {
        Snapshot current;
        StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
        stateMapStateRecord.getMap$runtime_release();
        PersistentMap<K, V> persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
        if (persistentMapPersistentHashMapOf != stateMapStateRecord.getMap$runtime_release()) {
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    stateMapStateRecord3.setMap$runtime_release(persistentMapPersistentHashMapOf);
                    stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                }
            }
            SnapshotKt.notifyWrite(current, this);
        }
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return getReadable$runtime_release().getMap$runtime_release().containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return getReadable$runtime_release().getMap$runtime_release().containsValue(value);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public V get(Object key) {
        return getReadable$runtime_release().getMap$runtime_release().get(key);
    }

    public final Map<K, V> getDebuggerDisplayValue() {
        return ((StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord())).getMap$runtime_release();
    }

    public Set<Map.Entry<K, V>> getEntries() {
        return this.entries;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public Set<K> getKeys() {
        return this.keys;
    }

    public final int getModification$runtime_release() {
        return getReadable$runtime_release().getModification();
    }

    public final StateMapStateRecord<K, V> getReadable$runtime_release() {
        return (StateMapStateRecord) SnapshotKt.readable((StateMapStateRecord) getFirstStateRecord(), this);
    }

    public int getSize() {
        return getReadable$runtime_release().getMap$runtime_release().size();
    }

    public Collection<V> getValues() {
        return this.values;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return getReadable$runtime_release().getMap$runtime_release().isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final /* synthetic */ StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        return b.a(this, stateRecord, stateRecord2, stateRecord3);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        this.firstStateRecord = (StateMapStateRecord) value;
    }

    @Override // java.util.Map
    public V put(K key, V value) {
        PersistentMap<K, V> map$runtime_release;
        int modification;
        V vPut;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
                map$runtime_release = stateMapStateRecord.getMap$runtime_release();
                modification = stateMapStateRecord.getModification();
            }
            PersistentMap.Builder<K, V> builder = map$runtime_release.builder();
            vPut = builder.put(key, value);
            PersistentMap<K, V> persistentMapBuild = builder.build();
            if (p.a(persistentMapBuild, map$runtime_release)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord3.getModification() == modification) {
                        stateMapStateRecord3.setMap$runtime_release(persistentMapBuild);
                        z = true;
                        stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return vPut;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> from) {
        PersistentMap<K, V> map$runtime_release;
        int modification;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
                map$runtime_release = stateMapStateRecord.getMap$runtime_release();
                modification = stateMapStateRecord.getModification();
            }
            PersistentMap.Builder<K, V> builder = map$runtime_release.builder();
            builder.putAll(from);
            PersistentMap<K, V> persistentMapBuild = builder.build();
            if (p.a(persistentMapBuild, map$runtime_release)) {
                return;
            }
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord3.getModification() == modification) {
                        stateMapStateRecord3.setMap$runtime_release(persistentMapBuild);
                        z = true;
                        stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
    }

    @Override // java.util.Map
    public V remove(Object key) {
        PersistentMap<K, V> map$runtime_release;
        int modification;
        V vRemove;
        Snapshot current;
        boolean z;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
                map$runtime_release = stateMapStateRecord.getMap$runtime_release();
                modification = stateMapStateRecord.getModification();
            }
            PersistentMap.Builder<K, V> builder = map$runtime_release.builder();
            vRemove = builder.remove(key);
            PersistentMap<K, V> persistentMapBuild = builder.build();
            if (p.a(persistentMapBuild, map$runtime_release)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord3.getModification() == modification) {
                        stateMapStateRecord3.setMap$runtime_release(persistentMapBuild);
                        z = true;
                        stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return vRemove;
    }

    public final boolean removeIf$runtime_release(l<? super Map.Entry<K, V>, Boolean> predicate) {
        PersistentMap<K, V> map$runtime_release;
        int modification;
        boolean z;
        Snapshot current;
        boolean z5 = false;
        do {
            synchronized (SnapshotStateMapKt.sync) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord());
                map$runtime_release = stateMapStateRecord.getMap$runtime_release();
                modification = stateMapStateRecord.getModification();
            }
            PersistentMap.Builder<K, V> builder = map$runtime_release.builder();
            Iterator<Map.Entry<K, V>> it = entrySet().iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<K, V> next = it.next();
                if (((Boolean) predicate.invoke(next)).booleanValue()) {
                    builder.remove(next.getKey());
                    z5 = true;
                }
            }
            PersistentMap<K, V> persistentMapBuild = builder.build();
            if (p.a(persistentMapBuild, map$runtime_release)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = (StateMapStateRecord) getFirstStateRecord();
            SnapshotKt.getSnapshotInitializer();
            synchronized (SnapshotKt.getLock()) {
                current = Snapshot.INSTANCE.getCurrent();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, current);
                synchronized (SnapshotStateMapKt.sync) {
                    if (stateMapStateRecord3.getModification() == modification) {
                        stateMapStateRecord3.setMap$runtime_release(persistentMapBuild);
                        stateMapStateRecord3.setModification$runtime_release(stateMapStateRecord3.getModification() + 1);
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(current, this);
        } while (!z);
        return z5;
    }

    public final boolean removeValue$runtime_release(V value) {
        Object next;
        Iterator<T> it = entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((Map.Entry) next).getValue(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry == null) {
            return false;
        }
        remove(entry.getKey());
        return true;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public final Map<K, V> toMap() {
        return getReadable$runtime_release().getMap$runtime_release();
    }

    public String toString() {
        return "SnapshotStateMap(value=" + ((StateMapStateRecord) SnapshotKt.current((StateMapStateRecord) getFirstStateRecord())).getMap$runtime_release() + ")@" + hashCode();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }
}
