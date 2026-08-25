package io.ktor.network.selector;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.k;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.v;
import kotlin.reflect.c;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ:\u0010\u0010\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u001d\u0010\u000f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\u00070\r¢\u0006\u0002\b\u000eH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0010\u001a\u00020\u00072#\u0010\u000f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0012¢\u0006\u0002\b\u000eH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0013J\u001d\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u000b¢\u0006\u0004\b\u0014\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0003R$\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b\u001e\u0010\u001c\u0012\u0004\b\u001f\u0010\u0003R$\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b \u0010\u001c\u0012\u0004\b!\u0010\u0003R$\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b\"\u0010\u001c\u0012\u0004\b#\u0010\u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"}, d2 = {"Lio/ktor/network/selector/InterestSuspensionsMap;", "", "<init>", "()V", "Lio/ktor/network/selector/SelectInterest;", "interest", "Lka/k;", "Lx6/t0;", "continuation", "addSuspension", "(Lio/ktor/network/selector/SelectInterest;Lka/k;)V", "", "readyOps", "Lkotlin/Function1;", "Lx6/n;", "block", "invokeForEachPresent", "(ILr7/l;)V", "Lkotlin/Function2;", "(Lr7/p;)V", "removeSuspension", "(Lio/ktor/network/selector/SelectInterest;)Lka/k;", "interestOrdinal", "(I)Lka/k;", "", "toString", "()Ljava/lang/String;", "readHandlerReference", "Lka/k;", "getReadHandlerReference$annotations", "writeHandlerReference", "getWriteHandlerReference$annotations", "connectHandlerReference", "getConnectHandlerReference$annotations", "acceptHandlerReference", "getAcceptHandlerReference$annotations", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InterestSuspensionsMap {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AtomicReferenceFieldUpdater<InterestSuspensionsMap, k<t0>>[] updaters;
    private volatile k<? super t0> acceptHandlerReference;
    private volatile k<? super t0> connectHandlerReference;
    private volatile k<? super t0> readHandlerReference;
    private volatile k<? super t0> writeHandlerReference;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bR4\u0010\r\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u00060\f8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u000f\u0010\u0003¨\u0006\u0010"}, d2 = {"Lio/ktor/network/selector/InterestSuspensionsMap$Companion;", "", "<init>", "()V", "Lio/ktor/network/selector/SelectInterest;", "interest", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lio/ktor/network/selector/InterestSuspensionsMap;", "Lka/k;", "Lx6/t0;", "updater", "(Lio/ktor/network/selector/SelectInterest;)Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "", "updaters", "[Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "getUpdaters$annotations", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private static /* synthetic */ void getUpdaters$annotations() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final AtomicReferenceFieldUpdater<InterestSuspensionsMap, k<t0>> updater(SelectInterest interest) {
            return InterestSuspensionsMap.updaters[interest.ordinal()];
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SelectInterest.values().length];
            try {
                iArr[SelectInterest.READ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SelectInterest.WRITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SelectInterest.ACCEPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SelectInterest.CONNECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        c cVar;
        SelectInterest[] allInterests = SelectInterest.INSTANCE.getAllInterests();
        ArrayList arrayList = new ArrayList(allInterests.length);
        for (SelectInterest selectInterest : allInterests) {
            int i10 = WhenMappings.$EnumSwitchMapping$0[selectInterest.ordinal()];
            if (i10 == 1) {
                cVar = new v() { // from class: io.ktor.network.selector.InterestSuspensionsMap$Companion$updaters$1$property$1
                    @Override // kotlin.jvm.internal.v, kotlin.reflect.o
                    public Object get(Object obj) {
                        return ((InterestSuspensionsMap) obj).readHandlerReference;
                    }

                    @Override // kotlin.jvm.internal.v, kotlin.reflect.j
                    public void set(Object obj, Object obj2) {
                        ((InterestSuspensionsMap) obj).readHandlerReference = (k) obj2;
                    }
                };
            } else if (i10 == 2) {
                cVar = new v() { // from class: io.ktor.network.selector.InterestSuspensionsMap$Companion$updaters$1$property$2
                    @Override // kotlin.jvm.internal.v, kotlin.reflect.o
                    public Object get(Object obj) {
                        return ((InterestSuspensionsMap) obj).writeHandlerReference;
                    }

                    @Override // kotlin.jvm.internal.v, kotlin.reflect.j
                    public void set(Object obj, Object obj2) {
                        ((InterestSuspensionsMap) obj).writeHandlerReference = (k) obj2;
                    }
                };
            } else if (i10 == 3) {
                cVar = new v() { // from class: io.ktor.network.selector.InterestSuspensionsMap$Companion$updaters$1$property$3
                    @Override // kotlin.jvm.internal.v, kotlin.reflect.o
                    public Object get(Object obj) {
                        return ((InterestSuspensionsMap) obj).acceptHandlerReference;
                    }

                    @Override // kotlin.jvm.internal.v, kotlin.reflect.j
                    public void set(Object obj, Object obj2) {
                        ((InterestSuspensionsMap) obj).acceptHandlerReference = (k) obj2;
                    }
                };
            } else {
                if (i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                cVar = new v() { // from class: io.ktor.network.selector.InterestSuspensionsMap$Companion$updaters$1$property$4
                    @Override // kotlin.jvm.internal.v, kotlin.reflect.o
                    public Object get(Object obj) {
                        return ((InterestSuspensionsMap) obj).connectHandlerReference;
                    }

                    @Override // kotlin.jvm.internal.v, kotlin.reflect.j
                    public void set(Object obj, Object obj2) {
                        ((InterestSuspensionsMap) obj).connectHandlerReference = (k) obj2;
                    }
                };
            }
            arrayList.add(AtomicReferenceFieldUpdater.newUpdater(InterestSuspensionsMap.class, k.class, cVar.getName()));
        }
        updaters = (AtomicReferenceFieldUpdater[]) arrayList.toArray(new AtomicReferenceFieldUpdater[0]);
    }

    private static /* synthetic */ void getAcceptHandlerReference$annotations() {
    }

    private static /* synthetic */ void getConnectHandlerReference$annotations() {
    }

    private static /* synthetic */ void getReadHandlerReference$annotations() {
    }

    private static /* synthetic */ void getWriteHandlerReference$annotations() {
    }

    public final void addSuspension(SelectInterest interest, k<? super t0> continuation) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdaterUpdater = INSTANCE.updater(interest);
        while (!atomicReferenceFieldUpdaterUpdater.compareAndSet(this, null, continuation)) {
            if (atomicReferenceFieldUpdaterUpdater.get(this) != null) {
                throw new IllegalStateException("Handler for " + interest.name() + " is already registered");
            }
        }
    }

    public final void invokeForEachPresent(int readyOps, l<? super k<? super t0>, t0> block) {
        k<t0> kVarRemoveSuspension;
        int[] flags = SelectInterest.INSTANCE.getFlags();
        int length = flags.length;
        for (int i10 = 0; i10 < length; i10++) {
            if ((flags[i10] & readyOps) != 0 && (kVarRemoveSuspension = removeSuspension(i10)) != null) {
                block.invoke(kVarRemoveSuspension);
            }
        }
    }

    public final k<t0> removeSuspension(SelectInterest interest) {
        return (k) INSTANCE.updater(interest).getAndSet(this, null);
    }

    public String toString() {
        return "R " + this.readHandlerReference + " W " + this.writeHandlerReference + " C " + this.connectHandlerReference + " A " + this.acceptHandlerReference;
    }

    public final k<t0> removeSuspension(int interestOrdinal) {
        return updaters[interestOrdinal].getAndSet(this, null);
    }

    public final void invokeForEachPresent(p<? super k<? super t0>, ? super SelectInterest, t0> block) {
        for (SelectInterest selectInterest : SelectInterest.INSTANCE.getAllInterests()) {
            k<t0> kVarRemoveSuspension = removeSuspension(selectInterest);
            if (kVarRemoveSuspension != null) {
                block.invoke(kVarRemoveSuspension, selectInterest);
            }
        }
    }
}
