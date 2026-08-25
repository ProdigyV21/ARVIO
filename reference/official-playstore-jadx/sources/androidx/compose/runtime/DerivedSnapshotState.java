package androidx.compose.runtime;

import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Metadata;
import t.e0;
import t.f0;
import t.y;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001/B%\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJA\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001c\u0010\u0014R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0019\u0010'\u001a\u0004\u0018\u00018\u00008G¢\u0006\f\u0012\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0014\u0010*\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010\u0018\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010$R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u00060"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState;", "T", "Landroidx/compose/runtime/snapshots/StateObjectImpl;", "Landroidx/compose/runtime/DerivedState;", "Lkotlin/Function0;", "calculation", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "policy", "<init>", "(Lr7/a;Landroidx/compose/runtime/SnapshotMutationPolicy;)V", "Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "readable", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "", "forceDependencyReads", "currentRecord", "(Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;Landroidx/compose/runtime/snapshots/Snapshot;ZLr7/a;)Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "", "displayValue", "()Ljava/lang/String;", "Landroidx/compose/runtime/snapshots/StateRecord;", "current", "(Landroidx/compose/runtime/snapshots/Snapshot;)Landroidx/compose/runtime/snapshots/StateRecord;", "value", "Lx6/t0;", "prependStateRecord", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "toString", "Lr7/a;", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "getPolicy", "()Landroidx/compose/runtime/SnapshotMutationPolicy;", "first", "Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "getDebuggerDisplayValue", "()Ljava/lang/Object;", "getDebuggerDisplayValue$annotations", "()V", "debuggerDisplayValue", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "firstStateRecord", "getValue", "Landroidx/compose/runtime/DerivedState$Record;", "getCurrentRecord", "()Landroidx/compose/runtime/DerivedState$Record;", "ResultRecord", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DerivedSnapshotState<T> extends StateObjectImpl implements DerivedState<T> {
    private final r7.a<T> calculation;
    private ResultRecord<T> first = new ResultRecord<>();
    private final SnapshotMutationPolicy<T> policy;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\r\b\u0007\u0018\u0000 3*\u0004\b\u0001\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003:\u00013B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\u0011\u001a\u00020\u00102\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0014\u001a\u00020\u00132\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR(\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u001f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010(\u001a\u0004\u0018\u00010'8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\"\u0010.\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0017\u001a\u0004\b/\u0010\u0019\"\u0004\b0\u0010\u001bR\u0014\u00102\u001a\u00028\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010+¨\u00064"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord;", "T", "Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/DerivedState$Record;", "<init>", "()V", "value", "Lx6/t0;", "assign", "(Landroidx/compose/runtime/snapshots/StateRecord;)V", "create", "()Landroidx/compose/runtime/snapshots/StateRecord;", "Landroidx/compose/runtime/DerivedState;", "derivedState", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshot", "", "isValid", "(Landroidx/compose/runtime/DerivedState;Landroidx/compose/runtime/snapshots/Snapshot;)Z", "", "readableHash", "(Landroidx/compose/runtime/DerivedState;Landroidx/compose/runtime/snapshots/Snapshot;)I", "validSnapshotId", "I", "getValidSnapshotId", "()I", "setValidSnapshotId", "(I)V", "validSnapshotWriteCount", "getValidSnapshotWriteCount", "setValidSnapshotWriteCount", "Lt/e0;", "Landroidx/compose/runtime/snapshots/StateObject;", "dependencies", "Lt/e0;", "getDependencies", "()Lt/e0;", "setDependencies", "(Lt/e0;)V", "", "result", "Ljava/lang/Object;", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "resultHash", "getResultHash", "setResultHash", "getCurrentValue", "currentValue", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ResultRecord<T> extends StateRecord implements DerivedState.Record<T> {
        private e0 dependencies = f0.f21812a;
        private Object result = Unset;
        private int resultHash;
        private int validSnapshotId;
        private int validSnapshotWriteCount;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;
        private static final Object Unset = new Object();

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/DerivedSnapshotState$ResultRecord$Companion;", "", "()V", "Unset", "getUnset", "()Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
                this();
            }

            public final Object getUnset() {
                return ResultRecord.Unset;
            }

            private Companion() {
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            ResultRecord resultRecord = (ResultRecord) value;
            setDependencies(resultRecord.getDependencies());
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new ResultRecord();
        }

        @Override // androidx.compose.runtime.DerivedState.Record
        public T getCurrentValue() {
            return (T) this.result;
        }

        @Override // androidx.compose.runtime.DerivedState.Record
        public e0 getDependencies() {
            return this.dependencies;
        }

        public final Object getResult() {
            return this.result;
        }

        public final int getResultHash() {
            return this.resultHash;
        }

        public final int getValidSnapshotId() {
            return this.validSnapshotId;
        }

        public final int getValidSnapshotWriteCount() {
            return this.validSnapshotWriteCount;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean isValid(androidx.compose.runtime.DerivedState<?> r6, androidx.compose.runtime.snapshots.Snapshot r7) {
            /*
                r5 = this;
                java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r0)
                int r1 = r5.validSnapshotId     // Catch: java.lang.Throwable -> L1a
                int r2 = r7.getId()     // Catch: java.lang.Throwable -> L1a
                r3 = 1
                r4 = 0
                if (r1 != r2) goto L1c
                int r1 = r5.validSnapshotWriteCount     // Catch: java.lang.Throwable -> L1a
                int r2 = r7.getWriteCount$runtime_release()     // Catch: java.lang.Throwable -> L1a
                if (r1 == r2) goto L18
                goto L1c
            L18:
                r1 = r4
                goto L1d
            L1a:
                r6 = move-exception
                goto L4b
            L1c:
                r1 = r3
            L1d:
                monitor-exit(r0)
                java.lang.Object r0 = r5.result
                java.lang.Object r2 = androidx.compose.runtime.DerivedSnapshotState.ResultRecord.Unset
                if (r0 == r2) goto L2f
                if (r1 == 0) goto L30
                int r0 = r5.resultHash
                int r6 = r5.readableHash(r6, r7)
                if (r0 != r6) goto L2f
                goto L30
            L2f:
                r3 = r4
            L30:
                if (r3 == 0) goto L4a
                if (r1 == 0) goto L4a
                java.lang.Object r6 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()
                monitor-enter(r6)
                int r0 = r7.getId()     // Catch: java.lang.Throwable -> L47
                r5.validSnapshotId = r0     // Catch: java.lang.Throwable -> L47
                int r7 = r7.getWriteCount$runtime_release()     // Catch: java.lang.Throwable -> L47
                r5.validSnapshotWriteCount = r7     // Catch: java.lang.Throwable -> L47
                monitor-exit(r6)
                return r3
            L47:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            L4a:
                return r3
            L4b:
                monitor-exit(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.DerivedSnapshotState.ResultRecord.isValid(androidx.compose.runtime.DerivedState, androidx.compose.runtime.snapshots.Snapshot):boolean");
        }

        public final int readableHash(DerivedState<?> derivedState, Snapshot snapshot) {
            e0 dependencies;
            int iIdentityHashCode;
            int i10;
            int i11;
            synchronized (SnapshotKt.getLock()) {
                dependencies = getDependencies();
            }
            int i12 = 7;
            if (dependencies.f21808e == 0) {
                return 7;
            }
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            int size = mutableVectorDerivedStateObservers.getSize();
            if (size > 0) {
                DerivedStateObserver[] content = mutableVectorDerivedStateObservers.getContent();
                int i13 = 0;
                do {
                    content[i13].start(derivedState);
                    i13++;
                } while (i13 < size);
            }
            try {
                Object[] objArr = dependencies.f21805b;
                int[] iArr = dependencies.f21806c;
                long[] jArr = dependencies.f21804a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    iIdentityHashCode = 7;
                    int i14 = 0;
                    while (true) {
                        long j10 = jArr[i14];
                        if ((((~j10) << i12) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i15 = 8;
                            int i16 = 8 - ((~(i14 - length)) >>> 31);
                            i10 = i12;
                            int i17 = 0;
                            while (i17 < i16) {
                                if ((j10 & 255) < 128) {
                                    int i18 = (i14 << 3) + i17;
                                    Object obj = objArr[i18];
                                    int i19 = iArr[i18];
                                    i11 = i15;
                                    StateObject stateObject = (StateObject) obj;
                                    if (i19 == 1) {
                                        StateRecord stateRecordCurrent = stateObject instanceof DerivedSnapshotState ? ((DerivedSnapshotState) stateObject).current(snapshot) : SnapshotKt.current(stateObject.getFirstStateRecord(), snapshot);
                                        iIdentityHashCode = (((iIdentityHashCode * 31) + ActualJvm_jvmKt.identityHashCode(stateRecordCurrent)) * 31) + stateRecordCurrent.getSnapshotId();
                                    }
                                } else {
                                    i11 = i15;
                                }
                                j10 >>= i11;
                                i17++;
                                i15 = i11;
                            }
                            if (i16 != i15) {
                                break;
                            }
                        } else {
                            i10 = i12;
                        }
                        if (i14 == length) {
                            i12 = iIdentityHashCode;
                            break;
                        }
                        i14++;
                        i12 = i10;
                    }
                }
                iIdentityHashCode = i12;
                int size2 = mutableVectorDerivedStateObservers.getSize();
                if (size2 > 0) {
                    DerivedStateObserver[] content2 = mutableVectorDerivedStateObservers.getContent();
                    int i20 = 0;
                    do {
                        content2[i20].done(derivedState);
                        i20++;
                    } while (i20 < size2);
                }
                return iIdentityHashCode;
            } catch (Throwable th) {
                int size3 = mutableVectorDerivedStateObservers.getSize();
                if (size3 > 0) {
                    DerivedStateObserver[] content3 = mutableVectorDerivedStateObservers.getContent();
                    int i21 = 0;
                    do {
                        content3[i21].done(derivedState);
                        i21++;
                    } while (i21 < size3);
                }
                throw th;
            }
        }

        public void setDependencies(e0 e0Var) {
            this.dependencies = e0Var;
        }

        public final void setResult(Object obj) {
            this.result = obj;
        }

        public final void setResultHash(int i10) {
            this.resultHash = i10;
        }

        public final void setValidSnapshotId(int i10) {
            this.validSnapshotId = i10;
        }

        public final void setValidSnapshotWriteCount(int i10) {
            this.validSnapshotWriteCount = i10;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DerivedSnapshotState(r7.a<? extends T> aVar, SnapshotMutationPolicy<T> snapshotMutationPolicy) {
        this.calculation = aVar;
        this.policy = snapshotMutationPolicy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ResultRecord<T> currentRecord(ResultRecord<T> readable, Snapshot snapshot, boolean forceDependencyReads, r7.a<? extends T> calculation) throws Throwable {
        int i10;
        Snapshot.Companion companion;
        SnapshotMutationPolicy<T> policy;
        boolean z;
        int i11;
        ResultRecord<T> resultRecord = readable;
        boolean z5 = true;
        if (!resultRecord.isValid(this, snapshot)) {
            y yVar = new y();
            IntRef intRef = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
            if (intRef == null) {
                i10 = 0;
                intRef = new IntRef(0);
                SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(intRef);
            } else {
                i10 = 0;
            }
            int element = intRef.getElement();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            int size = mutableVectorDerivedStateObservers.getSize();
            if (size > 0) {
                DerivedStateObserver[] content = mutableVectorDerivedStateObservers.getContent();
                int i12 = i10;
                do {
                    content[i12].start(this);
                    i12++;
                } while (i12 < size);
            }
            try {
                intRef.setElement(element + 1);
                Object objObserve = Snapshot.INSTANCE.observe(new DerivedSnapshotState$currentRecord$result$1$1$result$1(this, intRef, yVar, element), null, calculation);
                intRef.setElement(element);
                int size2 = mutableVectorDerivedStateObservers.getSize();
                if (size2 > 0) {
                    DerivedStateObserver[] content2 = mutableVectorDerivedStateObservers.getContent();
                    do {
                        content2[i10].done(this);
                        i10++;
                    } while (i10 < size2);
                }
                synchronized (SnapshotKt.getLock()) {
                    try {
                        companion = Snapshot.INSTANCE;
                        Snapshot current = companion.getCurrent();
                        if (resultRecord.getResult() == ResultRecord.INSTANCE.getUnset() || (policy = getPolicy()) == 0 || !policy.equivalent(objObserve, resultRecord.getResult())) {
                            resultRecord = (ResultRecord) SnapshotKt.newWritableRecord(this.first, this, current);
                            resultRecord.setDependencies(yVar);
                            resultRecord.setResultHash(resultRecord.readableHash(this, current));
                            resultRecord.setResult(objObserve);
                        } else {
                            resultRecord.setDependencies(yVar);
                            resultRecord.setResultHash(resultRecord.readableHash(this, current));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                IntRef intRef2 = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                if (intRef2 == null || intRef2.getElement() != 0) {
                    return resultRecord;
                }
                companion.notifyObjectsInitialized();
                synchronized (SnapshotKt.getLock()) {
                    Snapshot current2 = companion.getCurrent();
                    resultRecord.setValidSnapshotId(current2.getId());
                    resultRecord.setValidSnapshotWriteCount(current2.getWriteCount$runtime_release());
                }
                return resultRecord;
            } catch (Throwable th2) {
                int size3 = mutableVectorDerivedStateObservers.getSize();
                if (size3 > 0) {
                    DerivedStateObserver[] content3 = mutableVectorDerivedStateObservers.getContent();
                    int i13 = i10;
                    do {
                        content3[i13].done(this);
                        i13++;
                    } while (i13 < size3);
                }
                throw th2;
            }
        }
        if (forceDependencyReads) {
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers2 = SnapshotStateKt.derivedStateObservers();
            int size4 = mutableVectorDerivedStateObservers2.getSize();
            if (size4 > 0) {
                DerivedStateObserver[] content4 = mutableVectorDerivedStateObservers2.getContent();
                int i14 = 0;
                do {
                    content4[i14].start(this);
                    i14++;
                } while (i14 < size4);
            }
            try {
                e0 dependencies = resultRecord.getDependencies();
                IntRef intRef3 = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                if (intRef3 == null) {
                    intRef3 = new IntRef(0);
                    SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(intRef3);
                }
                int element2 = intRef3.getElement();
                Object[] objArr = dependencies.f21805b;
                int[] iArr = dependencies.f21806c;
                long[] jArr = dependencies.f21804a;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i15 = 0;
                    while (true) {
                        long j10 = jArr[i15];
                        boolean z10 = z5;
                        if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i16 = 8;
                            int i17 = 8 - ((~(i15 - length)) >>> 31);
                            z = z10;
                            int i18 = 0;
                            while (i18 < i17) {
                                if ((j10 & 255) < 128) {
                                    int i19 = (i15 << 3) + i18;
                                    try {
                                        i11 = i16;
                                        StateObject stateObject = (StateObject) objArr[i19];
                                        intRef3.setElement(element2 + iArr[i19]);
                                        r7.l<Object, t0> readObserver = snapshot.getReadObserver();
                                        if (readObserver != null) {
                                            readObserver.invoke(stateObject);
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        int size5 = mutableVectorDerivedStateObservers2.getSize();
                                        if (size5 > 0) {
                                            DerivedStateObserver[] content5 = mutableVectorDerivedStateObservers2.getContent();
                                            int i20 = 0;
                                            do {
                                                content5[i20].done(this);
                                                i20++;
                                            } while (i20 < size5);
                                        }
                                        throw th;
                                    }
                                } else {
                                    i11 = i16;
                                }
                                j10 >>= i11;
                                i18++;
                                i16 = i11;
                            }
                            if (i17 != i16) {
                                break;
                            }
                        } else {
                            z = z10;
                        }
                        if (i15 == length) {
                            break;
                        }
                        i15++;
                        z5 = z;
                    }
                }
                intRef3.setElement(element2);
                int size6 = mutableVectorDerivedStateObservers2.getSize();
                if (size6 > 0) {
                    DerivedStateObserver[] content6 = mutableVectorDerivedStateObservers2.getContent();
                    int i21 = 0;
                    do {
                        content6[i21].done(this);
                        i21++;
                    } while (i21 < size6);
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        return resultRecord;
    }

    private final String displayValue() {
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        return resultRecord.isValid(this, Snapshot.INSTANCE.getCurrent()) ? String.valueOf(resultRecord.getResult()) : "<Not calculated>";
    }

    public static /* synthetic */ void getDebuggerDisplayValue$annotations() {
    }

    public final StateRecord current(Snapshot snapshot) {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, snapshot), snapshot, false, this.calculation);
    }

    @Override // androidx.compose.runtime.DerivedState
    public DerivedState.Record<T> getCurrentRecord() {
        Snapshot current = Snapshot.INSTANCE.getCurrent();
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, current), current, false, this.calculation);
    }

    public final T getDebuggerDisplayValue() {
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        if (resultRecord.isValid(this, Snapshot.INSTANCE.getCurrent())) {
            return (T) resultRecord.getResult();
        }
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.DerivedState
    public SnapshotMutationPolicy<T> getPolicy() {
        return this.policy;
    }

    @Override // androidx.compose.runtime.State
    public T getValue() {
        Snapshot.Companion companion = Snapshot.INSTANCE;
        r7.l<Object, t0> readObserver = companion.getCurrent().getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(this);
        }
        Snapshot current = companion.getCurrent();
        return (T) currentRecord((ResultRecord) SnapshotKt.current(this.first, current), current, true, this.calculation).getResult();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        this.first = (ResultRecord) value;
    }

    public String toString() {
        return "DerivedState(value=" + displayValue() + ")@" + hashCode();
    }
}
