package androidx.compose.runtime.snapshots;

import androidx.compose.material3.d;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import t.d0;
import t.y;
import t.z;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001IB0\u0012'\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u001b\u001a\u00020\u00042\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00040\u0002H\u0082\b¢\u0006\u0004\b\u001b\u0010\nJ$\u0010\u001c\u001a\u00020\u00042\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\u0002H\u0082\b¢\u0006\u0004\b\u001c\u0010\nJ-\u0010\u001f\u001a\u00020\u0019\"\b\b\u0000\u0010\u001d*\u00020\u00012\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0002H\u0002¢\u0006\u0004\b\u001f\u0010 JA\u0010#\u001a\u00020\u0004\"\b\b\u0000\u0010\u001d*\u00020\u00012\u0006\u0010!\u001a\u00028\u00002\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b#\u0010$J\u001d\u0010%\u001a\u00020\u00042\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0007¢\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0001¢\u0006\u0004\b'\u0010(J0\u0010*\u001a\u00020\u00042!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u000b0\u0002¢\u0006\u0004\b*\u0010\nJ\r\u0010+\u001a\u00020\u0004¢\u0006\u0004\b+\u0010\u000fJ\r\u0010,\u001a\u00020\u0004¢\u0006\u0004\b,\u0010\u000fJ%\u00100\u001a\u00020\u00042\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\u0006\u0010/\u001a\u00020.H\u0007¢\u0006\u0004\b0\u00101J\r\u0010'\u001a\u00020\u0004¢\u0006\u0004\b'\u0010\u000fR5\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00102R(\u00105\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000103j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`48\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R,\u0010:\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0004098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R \u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u00102R\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00190=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u00108R\u0018\u0010D\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010G\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010H¨\u0006J"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "Lkotlin/Function1;", "Lkotlin/Function0;", "Lx6/t0;", "Lx6/y;", ContentDisposition.Parameters.Name, "callback", "onChangedExecutor", "<init>", "(Lr7/l;)V", "", "drainChanges", "()Z", "sendNotifications", "()V", "", "set", "addChanges", "(Ljava/util/Set;)V", "removeChanges", "()Ljava/util/Set;", "", "report", "()Ljava/lang/Void;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "block", "forEachScopeMap", "removeScopeMapIf", "T", "onChanged", "ensureMap", "(Lr7/l;)Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "scope", "onValueChangedForScope", "observeReads", "(Ljava/lang/Object;Lr7/l;Lr7/a;)V", "withNoObservations", "(Lr7/a;)V", "clear", "(Ljava/lang/Object;)V", "predicate", "clearIf", TtmlNode.START, "stop", "changes", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "notifyChanges", "(Ljava/util/Set;Landroidx/compose/runtime/snapshots/Snapshot;)V", "Lr7/l;", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "sendingNotifications", "Z", "Lkotlin/Function2;", "applyObserver", "Lr7/p;", "readObserver", "Landroidx/compose/runtime/collection/MutableVector;", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "isPaused", "currentMap", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "currentMapThreadId", "J", "ObservedScopeMap", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final l<r7.a<t0>, t0> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final p<Set<? extends Object>, Snapshot, t0> applyObserver = new SnapshotStateObserver$applyObserver$1(this);
    private final l<Object, t0> readObserver = new SnapshotStateObserver$readObserver$1(this);
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private long currentMapThreadId = -1;

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotStateObserver$sendNotifications$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements r7.a<t0> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m3106invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m3106invoke() {
            do {
                MutableVector mutableVector = SnapshotStateObserver.this.observedScopeMaps;
                SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
                synchronized (mutableVector) {
                    try {
                        if (!snapshotStateObserver.sendingNotifications) {
                            snapshotStateObserver.sendingNotifications = true;
                            try {
                                MutableVector mutableVector2 = snapshotStateObserver.observedScopeMaps;
                                int size = mutableVector2.getSize();
                                if (size > 0) {
                                    Object[] content = mutableVector2.getContent();
                                    int i10 = 0;
                                    do {
                                        ((ObservedScopeMap) content[i10]).notifyInvalidatedScopes();
                                        i10++;
                                    } while (i10 < size);
                                }
                                snapshotStateObserver.sendingNotifications = false;
                            } finally {
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } while (SnapshotStateObserver.this.drainChanges());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(l<? super r7.a<t0>, t0> lVar) {
        this.onChangedExecutor = lVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void addChanges(Set<? extends Object> set) {
        Set<? extends Object> setI0;
        while (true) {
            Object obj = this.pendingChanges.get();
            if (obj == null) {
                setI0 = set;
            } else if (obj instanceof Set) {
                setI0 = t7.a.E(obj, set);
            } else {
                if (!(obj instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                setI0 = x.I0((Collection) obj, Collections.singletonList(set));
            }
            AtomicReference<Object> atomicReference = this.pendingChanges;
            while (!atomicReference.compareAndSet(obj, setI0)) {
                if (atomicReference.get() != obj) {
                    break;
                }
            }
            return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean drainChanges() {
        boolean z;
        synchronized (this.observedScopeMaps) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z5 = false;
        while (true) {
            Set<? extends Object> setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return z5;
            }
            synchronized (this.observedScopeMaps) {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                if (size > 0) {
                    ObservedScopeMap[] content = mutableVector.getContent();
                    int i10 = 0;
                    do {
                        z5 = content[i10].recordInvalidation(setRemoveChanges) || z5;
                        i10++;
                    } while (i10 < size);
                }
            }
        }
    }

    private final <T> ObservedScopeMap ensureMap(l<? super T, t0> onChanged) {
        ObservedScopeMap observedScopeMap;
        MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
        int size = mutableVector.getSize();
        if (size > 0) {
            ObservedScopeMap[] content = mutableVector.getContent();
            int i10 = 0;
            do {
                observedScopeMap = content[i10];
                if (observedScopeMap.getOnChanged() == onChanged) {
                    break;
                }
                i10++;
            } while (i10 < size);
            observedScopeMap = null;
        } else {
            observedScopeMap = null;
        }
        ObservedScopeMap observedScopeMap2 = observedScopeMap;
        if (observedScopeMap2 != null) {
            return observedScopeMap2;
        }
        q0.e(1, onChanged);
        ObservedScopeMap observedScopeMap3 = new ObservedScopeMap(onChanged);
        this.observedScopeMaps.add(observedScopeMap3);
        return observedScopeMap3;
    }

    private final void forEachScopeMap(l<? super ObservedScopeMap, t0> block) {
        synchronized (this.observedScopeMaps) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                ObservedScopeMap[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    block.invoke(content[i10]);
                    i10++;
                } while (i10 < size);
            }
        }
    }

    private final Set<Object> removeChanges() {
        Set<Object> set;
        while (true) {
            Object obj = this.pendingChanges.get();
            Object objSubList = null;
            if (obj == null) {
                return null;
            }
            if (obj instanceof Set) {
                set = (Set) obj;
            } else {
                if (!(obj instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                List list = (List) obj;
                Set<Object> set2 = (Set) list.get(0);
                if (list.size() == 2) {
                    objSubList = list.get(1);
                } else if (list.size() > 2) {
                    objSubList = list.subList(1, list.size());
                }
                set = set2;
            }
            AtomicReference<Object> atomicReference = this.pendingChanges;
            while (!atomicReference.compareAndSet(obj, objSubList)) {
                if (atomicReference.get() != obj) {
                    break;
                }
            }
            return set;
        }
    }

    private final void removeScopeMapIf(l<? super ObservedScopeMap, Boolean> block) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i10 = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    if (((Boolean) block.invoke(mutableVector.getContent()[i11])).booleanValue()) {
                        i10++;
                    } else if (i10 > 0) {
                        mutableVector.getContent()[i11 - i10] = mutableVector.getContent()[i11];
                    }
                }
                int i12 = size - i10;
                Arrays.fill(mutableVector.getContent(), i12, size, (Object) null);
                mutableVector.setSize(i12);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendNotifications() {
        this.onChangedExecutor.invoke(new AnonymousClass1());
    }

    public final void clear(Object scope) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i10 = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    ObservedScopeMap observedScopeMap = mutableVector.getContent()[i11];
                    observedScopeMap.clearScopeObservations(scope);
                    if (!observedScopeMap.hasScopeObservations()) {
                        i10++;
                    } else if (i10 > 0) {
                        mutableVector.getContent()[i11 - i10] = mutableVector.getContent()[i11];
                    }
                }
                int i12 = size - i10;
                Arrays.fill(mutableVector.getContent(), i12, size, (Object) null);
                mutableVector.setSize(i12);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void clearIf(l<Object, Boolean> predicate) {
        synchronized (this.observedScopeMaps) {
            try {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                int size = mutableVector.getSize();
                int i10 = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    ObservedScopeMap observedScopeMap = mutableVector.getContent()[i11];
                    observedScopeMap.removeScopeIf(predicate);
                    if (!observedScopeMap.hasScopeObservations()) {
                        i10++;
                    } else if (i10 > 0) {
                        mutableVector.getContent()[i11 - i10] = mutableVector.getContent()[i11];
                    }
                }
                int i12 = size - i10;
                Arrays.fill(mutableVector.getContent(), i12, size, (Object) null);
                mutableVector.setSize(i12);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    public final <T> void observeReads(T scope, l<? super T, t0> onValueChangedForScope, r7.a<t0> block) {
        ObservedScopeMap observedScopeMapEnsureMap;
        synchronized (this.observedScopeMaps) {
            observedScopeMapEnsureMap = ensureMap(onValueChangedForScope);
        }
        boolean z = this.isPaused;
        ObservedScopeMap observedScopeMap = this.currentMap;
        long j10 = this.currentMapThreadId;
        if (j10 != -1) {
            if (!(j10 == ActualJvm_jvmKt.currentThreadId())) {
                StringBuilder sbR = d.r(j10, "Detected multithreaded access to SnapshotStateObserver: previousThreadId=", "), currentThread={id=");
                sbR.append(ActualJvm_jvmKt.currentThreadId());
                sbR.append(", name=");
                sbR.append(ActualJvm_jvmKt.currentThreadName());
                sbR.append("}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
                PreconditionsKt.throwIllegalArgumentException(sbR.toString());
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = observedScopeMapEnsureMap;
            this.currentMapThreadId = Thread.currentThread().getId();
            observedScopeMapEnsureMap.observe(scope, this.readObserver, block);
        } finally {
            this.currentMap = observedScopeMap;
            this.isPaused = z;
            this.currentMapThreadId = j10;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    @e
    public final void withNoObservations(r7.a<t0> block) {
        boolean z = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = z;
        }
    }

    @Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J5\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0004\b\r\u0010\u0011J7\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0001¢\u0006\u0004\b\u0019\u0010\u0011J0\u0010\u001e\u001a\u00020\u00032!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u001c0\u0002¢\u0006\u0004\b\u001e\u0010\u0006J\r\u0010\u001f\u001a\u00020\u001c¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0003¢\u0006\u0004\b!\u0010\"J\u001b\u0010%\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010#¢\u0006\u0004\b%\u0010&J\u0019\u0010)\u001a\u00020\u00032\n\u0010(\u001a\u0006\u0012\u0002\b\u00030'¢\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0003¢\u0006\u0004\b+\u0010\"R#\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010,\u001a\u0004\b-\u0010.R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010/R\u001e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u00102R \u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R&\u00107\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0001098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u001e\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0017\u0010@\u001a\u00020?8\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u00102R$\u0010E\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030'038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u00105R<\u0010H\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0006\u0012\u0004\u0018\u00010\u00010Fj\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0006\u0012\u0004\u0018\u00010\u0001`G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010I¨\u0006J"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "Lkotlin/Function1;", "Lx6/t0;", "onChanged", "<init>", "(Lr7/l;)V", "value", "", "currentToken", "currentScope", "Lt/y;", "recordedValues", "recordRead", "(Ljava/lang/Object;ILjava/lang/Object;Lt/y;)V", "scope", "clearObsoleteStateReads", "(Ljava/lang/Object;)V", "removeObservation", "(Ljava/lang/Object;Ljava/lang/Object;)V", "readObserver", "Lkotlin/Function0;", "block", "observe", "(Ljava/lang/Object;Lr7/l;Lr7/a;)V", "clearScopeObservations", "Lx6/y;", ContentDisposition.Parameters.Name, "", "predicate", "removeScopeIf", "hasScopeObservations", "()Z", "clear", "()V", "", "changes", "recordInvalidation", "(Ljava/util/Set;)Z", "Landroidx/compose/runtime/DerivedState;", "derivedState", "rereadDerivedState", "(Landroidx/compose/runtime/DerivedState;)V", "notifyInvalidatedScopes", "Lr7/l;", "getOnChanged", "()Lr7/l;", "Ljava/lang/Object;", "currentScopeReads", "Lt/y;", "I", "Landroidx/compose/runtime/collection/ScopeMap;", "valueToScopes", "Landroidx/compose/runtime/collection/ScopeMap;", "Lt/z;", "scopeToValues", "Lt/z;", "Lt/d0;", "invalidated", "Lt/d0;", "Landroidx/compose/runtime/collection/MutableVector;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/DerivedStateObserver;", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "deriveStateScopeCount", "dependencyToDerivedStates", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recordedDerivedStateValues", "Ljava/util/HashMap;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ObservedScopeMap {
        private Object currentScope;
        private y currentScopeReads;
        private int deriveStateScopeCount;
        private final l<Object, t0> onChanged;
        private int currentToken = -1;
        private final ScopeMap<Object, Object> valueToScopes = new ScopeMap<>();
        private final z scopeToValues = new z();
        private final d0 invalidated = new d0();
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = this.this$0;
                observedScopeMap.deriveStateScopeCount--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount++;
            }
        };
        private final ScopeMap<Object, DerivedState<?>> dependencyToDerivedStates = new ScopeMap<>();
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(l<Object, t0> lVar) {
            this.onChanged = lVar;
        }

        private final void clearObsoleteStateReads(Object scope) {
            int i10 = this.currentToken;
            y yVar = this.currentScopeReads;
            if (yVar == null) {
                return;
            }
            long[] jArr = yVar.f21804a;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i11 = 0;
            while (true) {
                long j10 = jArr[i11];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i12 = 8 - ((~(i11 - length)) >>> 31);
                    for (int i13 = 0; i13 < i12; i13++) {
                        if ((255 & j10) < 128) {
                            int i14 = (i11 << 3) + i13;
                            Object obj = yVar.f21805b[i14];
                            boolean z = yVar.f21806c[i14] != i10;
                            if (z) {
                                removeObservation(scope, obj);
                            }
                            if (z) {
                                yVar.e(i14);
                            }
                        }
                        j10 >>= 8;
                    }
                    if (i12 != 8) {
                        return;
                    }
                }
                if (i11 == length) {
                    return;
                } else {
                    i11++;
                }
            }
        }

        private final void removeObservation(Object scope, Object value) {
            this.valueToScopes.remove(value, scope);
            if (!(value instanceof DerivedState) || this.valueToScopes.contains(value)) {
                return;
            }
            this.dependencyToDerivedStates.removeScope(value);
            this.recordedDerivedStateValues.remove(value);
        }

        public final void clear() {
            this.valueToScopes.clear();
            this.scopeToValues.a();
            this.dependencyToDerivedStates.clear();
            this.recordedDerivedStateValues.clear();
        }

        public final void clearScopeObservations(Object scope) {
            y yVar = (y) this.scopeToValues.h(scope);
            if (yVar == null) {
                return;
            }
            Object[] objArr = yVar.f21805b;
            int[] iArr = yVar.f21806c;
            long[] jArr = yVar.f21804a;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i10 = 0;
            while (true) {
                long j10 = jArr[i10];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j10) < 128) {
                            int i13 = (i10 << 3) + i12;
                            Object obj = objArr[i13];
                            int i14 = iArr[i13];
                            removeObservation(scope, obj);
                        }
                        j10 >>= 8;
                    }
                    if (i11 != 8) {
                        return;
                    }
                }
                if (i10 == length) {
                    return;
                } else {
                    i10++;
                }
            }
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final l<Object, t0> getOnChanged() {
            return this.onChanged;
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.f21885e != 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void notifyInvalidatedScopes() {
            /*
                r17 = this;
                r0 = r17
                t.d0 r1 = r0.invalidated
                r7.l<java.lang.Object, x6.t0> r2 = r0.onChanged
                java.lang.Object[] r3 = r1.f21836b
                long[] r4 = r1.f21835a
                int r5 = r4.length
                int r5 = r5 + (-2)
                if (r5 < 0) goto L49
                r6 = 0
                r7 = r6
            L11:
                r8 = r4[r7]
                long r10 = ~r8
                r12 = 7
                long r10 = r10 << r12
                long r10 = r10 & r8
                r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r10 = r10 & r12
                int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r10 == 0) goto L44
                int r10 = r7 - r5
                int r10 = ~r10
                int r10 = r10 >>> 31
                r11 = 8
                int r10 = 8 - r10
                r12 = r6
            L2b:
                if (r12 >= r10) goto L42
                r13 = 255(0xff, double:1.26E-321)
                long r13 = r13 & r8
                r15 = 128(0x80, double:6.3E-322)
                int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r13 >= 0) goto L3e
                int r13 = r7 << 3
                int r13 = r13 + r12
                r13 = r3[r13]
                r2.invoke(r13)
            L3e:
                long r8 = r8 >> r11
                int r12 = r12 + 1
                goto L2b
            L42:
                if (r10 != r11) goto L49
            L44:
                if (r7 == r5) goto L49
                int r7 = r7 + 1
                goto L11
            L49:
                r1.e()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.notifyInvalidatedScopes():void");
        }

        public final void observe(Object scope, l<Object, t0> readObserver, r7.a<t0> block) {
            Object obj = this.currentScope;
            y yVar = this.currentScopeReads;
            int i10 = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = (y) this.scopeToValues.e(scope);
            if (this.currentToken == -1) {
                this.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            DerivedStateObserver derivedStateObserver = this.derivedStateObserver;
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(derivedStateObserver);
                Snapshot.INSTANCE.observe(readObserver, null, block);
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                clearObsoleteStateReads(this.currentScope);
                this.currentScope = obj;
                this.currentScopeReads = yVar;
                this.currentToken = i10;
            } catch (Throwable th) {
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:104:0x027c  */
        /* JADX WARN: Removed duplicated region for block: B:119:0x02c0 A[PHI: r20
          0x02c0: PHI (r20v39 boolean) = (r20v38 boolean), (r20v40 boolean) binds: [B:110:0x0297, B:118:0x02be] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:180:0x0423 A[PHI: r20
          0x0423: PHI (r20v21 boolean) = (r20v20 boolean), (r20v22 boolean) binds: [B:169:0x03f1, B:179:0x0421] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:216:0x04eb A[PHI: r20
          0x04eb: PHI (r20v11 boolean) = (r20v10 boolean), (r20v12 boolean) binds: [B:207:0x04c2, B:215:0x04e9] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x04fc  */
        /* JADX WARN: Removed duplicated region for block: B:223:0x050a  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x024f A[PHI: r20
          0x024f: PHI (r20v45 boolean) = (r20v44 boolean), (r20v46 boolean) binds: [B:87:0x0226, B:95:0x024d] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean recordInvalidation(java.util.Set<? extends java.lang.Object> r44) {
            /*
                Method dump skipped, instruction units count: 1420
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordInvalidation(java.util.Set):boolean");
        }

        public final void recordRead(Object value) {
            Object obj = this.currentScope;
            int i10 = this.currentToken;
            y yVar = this.currentScopeReads;
            if (yVar == null) {
                yVar = new y();
                this.currentScopeReads = yVar;
                this.scopeToValues.k(obj, yVar);
            }
            recordRead(value, i10, obj, yVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00a8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void removeScopeIf(r7.l<java.lang.Object, java.lang.Boolean> r34) {
            /*
                Method dump skipped, instruction units count: 225
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.removeScopeIf(r7.l):void");
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            z zVar = this.scopeToValues;
            int id = SnapshotKt.currentSnapshot().getId();
            Object objE = this.valueToScopes.getMap().e(derivedState);
            if (objE == null) {
                return;
            }
            if (!(objE instanceof d0)) {
                y yVar = (y) zVar.e(objE);
                if (yVar == null) {
                    yVar = new y();
                    zVar.k(objE, yVar);
                }
                recordRead(derivedState, id, objE, yVar);
                return;
            }
            d0 d0Var = (d0) objE;
            Object[] objArr = d0Var.f21836b;
            long[] jArr = d0Var.f21835a;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i10 = 0;
            while (true) {
                long j10 = jArr[i10];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j10) < 128) {
                            Object obj = objArr[(i10 << 3) + i12];
                            y yVar2 = (y) zVar.e(obj);
                            if (yVar2 == null) {
                                yVar2 = new y();
                                zVar.k(obj, yVar2);
                            }
                            recordRead(derivedState, id, obj, yVar2);
                        }
                        j10 >>= 8;
                    }
                    if (i11 != 8) {
                        return;
                    }
                }
                if (i10 == length) {
                    return;
                } else {
                    i10++;
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00a8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void recordRead(java.lang.Object r21, int r22, java.lang.Object r23, t.y r24) {
            /*
                r20 = this;
                r0 = r20
                r1 = r21
                r2 = r22
                r3 = r24
                int r4 = r0.deriveStateScopeCount
                if (r4 <= 0) goto Le
                goto Lc2
            Le:
                int r4 = r3.c(r1)
                if (r4 >= 0) goto L17
                int r4 = ~r4
                r6 = -1
                goto L1b
            L17:
                int[] r6 = r3.f21806c
                r6 = r6[r4]
            L1b:
                java.lang.Object[] r7 = r3.f21805b
                r7[r4] = r1
                int[] r3 = r3.f21806c
                r3[r4] = r2
                boolean r3 = r1 instanceof androidx.compose.runtime.DerivedState
                r4 = 2
                if (r3 == 0) goto La8
                if (r6 == r2) goto La8
                r2 = r1
                androidx.compose.runtime.DerivedState r2 = (androidx.compose.runtime.DerivedState) r2
                androidx.compose.runtime.DerivedState$Record r2 = r2.getCurrentRecord()
                java.util.HashMap<androidx.compose.runtime.DerivedState<?>, java.lang.Object> r3 = r0.recordedDerivedStateValues
                java.lang.Object r7 = r2.getCurrentValue()
                r3.put(r1, r7)
                t.e0 r2 = r2.getDependencies()
                androidx.compose.runtime.collection.ScopeMap<java.lang.Object, androidx.compose.runtime.DerivedState<?>> r3 = r0.dependencyToDerivedStates
                r3.removeScope(r1)
                java.lang.Object[] r7 = r2.f21805b
                long[] r2 = r2.f21804a
                int r8 = r2.length
                int r8 = r8 - r4
                if (r8 < 0) goto La8
                r10 = 0
            L4c:
                r11 = r2[r10]
                long r13 = ~r11
                r15 = 7
                long r13 = r13 << r15
                long r13 = r13 & r11
                r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r13 = r13 & r15
                int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r13 == 0) goto L9d
                int r13 = r10 - r8
                int r13 = ~r13
                int r13 = r13 >>> 31
                r14 = 8
                int r13 = 8 - r13
                r15 = 0
            L66:
                if (r15 >= r13) goto L98
                r16 = 255(0xff, double:1.26E-321)
                long r16 = r11 & r16
                r18 = 128(0x80, double:6.3E-322)
                int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
                if (r16 >= 0) goto L90
                int r16 = r10 << 3
                int r16 = r16 + r15
                r16 = r7[r16]
                r24 = r4
                r4 = r16
                androidx.compose.runtime.snapshots.StateObject r4 = (androidx.compose.runtime.snapshots.StateObject) r4
                boolean r9 = r4 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
                if (r9 == 0) goto L8c
                r9 = r4
                androidx.compose.runtime.snapshots.StateObjectImpl r9 = (androidx.compose.runtime.snapshots.StateObjectImpl) r9
                int r5 = androidx.compose.runtime.snapshots.ReaderKind.m3091constructorimpl(r24)
                r9.m3108recordReadInh_f27i8$runtime_release(r5)
            L8c:
                r3.add(r4, r1)
                goto L92
            L90:
                r24 = r4
            L92:
                long r11 = r11 >> r14
                int r15 = r15 + 1
                r4 = r24
                goto L66
            L98:
                r24 = r4
                if (r13 != r14) goto La6
                goto L9f
            L9d:
                r24 = r4
            L9f:
                if (r10 == r8) goto La6
                int r10 = r10 + 1
                r4 = r24
                goto L4c
            La6:
                r2 = -1
                goto Lab
            La8:
                r24 = r4
                goto La6
            Lab:
                if (r6 != r2) goto Lc2
                boolean r2 = r1 instanceof androidx.compose.runtime.snapshots.StateObjectImpl
                if (r2 == 0) goto Lbb
                r2 = r1
                androidx.compose.runtime.snapshots.StateObjectImpl r2 = (androidx.compose.runtime.snapshots.StateObjectImpl) r2
                int r3 = androidx.compose.runtime.snapshots.ReaderKind.m3091constructorimpl(r24)
                r2.m3108recordReadInh_f27i8$runtime_release(r3)
            Lbb:
                androidx.compose.runtime.collection.ScopeMap<java.lang.Object, java.lang.Object> r2 = r0.valueToScopes
                r3 = r23
                r2.add(r1, r3)
            Lc2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordRead(java.lang.Object, int, java.lang.Object, t.y):void");
        }
    }

    public final void clear() {
        synchronized (this.observedScopeMaps) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int size = mutableVector.getSize();
            if (size > 0) {
                ObservedScopeMap[] content = mutableVector.getContent();
                int i10 = 0;
                do {
                    content[i10].clear();
                    i10++;
                } while (i10 < size);
            }
        }
    }
}
