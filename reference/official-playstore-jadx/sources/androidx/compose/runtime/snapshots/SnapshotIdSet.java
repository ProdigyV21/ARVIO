package androidx.compose.runtime.snapshots;

import androidx.compose.foundation.c;
import d7.d;
import f4.f;
import f7.e;
import ga.o;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u0000 02\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00010B+\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0014J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010 \u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00002'\u0010\u001f\u001a#\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u001bH\u0086\b¢\u0006\u0004\b \u0010!J$\u0010%\u001a\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020#0\"H\u0086\b¢\u0006\u0004\b%\u0010&J\u0015\u0010(\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u0002¢\u0006\u0004\b(\u0010)J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010-R\u0014\u0010\u0005\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010-R\u0014\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010.R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010/¨\u00061"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "", "", "", "upperSet", "lowerSet", "lowerBound", "", "belowBound", "<init>", "(JJI[I)V", "bit", "", "get", "(I)Z", "set", "(I)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "clear", "bits", "andNot", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "and", "or", "", "iterator", "()Ljava/util/Iterator;", "initial", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "acc", "operation", "fastFold", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;Lr7/p;)Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "Lkotlin/Function1;", "Lx6/t0;", "block", "fastForEach", "(Lr7/l;)V", "default", "lowest", "(I)I", "", "toString", "()Ljava/lang/String;", "J", "I", "[I", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapshotIdSet implements Iterable<Integer>, s7.a {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    private final int[] belowBound;
    private final int lowerBound;
    private final long lowerSet;
    private final long upperSet;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotIdSet$Companion;", "", "()V", "EMPTY", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "getEMPTY", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final SnapshotIdSet getEMPTY() {
            return SnapshotIdSet.EMPTY;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lga/o;", "", "Lx6/t0;", "<anonymous>", "(Lga/o;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.runtime.snapshots.SnapshotIdSet$iterator$1", f = "SnapshotIdSet.kt", l = {295, 300, 307}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends f7.h implements p<o, d<? super t0>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = SnapshotIdSet.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(o oVar, d<? super t0> dVar) {
            return ((AnonymousClass1) create(oVar, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00a6  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00b4  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x008a -> B:26:0x00a4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00db -> B:37:0x00dc). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 226
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private SnapshotIdSet(long j10, long j11, int i10, int[] iArr) {
        this.upperSet = j10;
        this.lowerSet = j11;
        this.lowerBound = i10;
        this.belowBound = iArr;
    }

    public final SnapshotIdSet and(SnapshotIdSet bits) {
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (kotlin.jvm.internal.p.a(bits, snapshotIdSet) || equals(snapshotIdSet)) {
            return snapshotIdSet;
        }
        int i10 = bits.lowerBound;
        int i11 = this.lowerBound;
        if (i10 == i11) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                long j10 = this.upperSet;
                long j11 = bits.upperSet;
                long j12 = j10 & j11;
                long j13 = this.lowerSet;
                long j14 = bits.lowerSet;
                return (j12 == 0 && (j13 & j14) == 0 && iArr2 == null) ? snapshotIdSet : new SnapshotIdSet(j10 & j11, j14 & j13, i11, iArr2);
            }
        }
        SnapshotIdSet snapshotIdSet2 = snapshotIdSet;
        int i12 = 0;
        if (this.belowBound == null) {
            int[] iArr3 = this.belowBound;
            if (iArr3 != null) {
                for (int i13 : iArr3) {
                    if (bits.get(i13)) {
                        snapshotIdSet2 = snapshotIdSet2.set(i13);
                    }
                }
            }
            SnapshotIdSet snapshotIdSet3 = snapshotIdSet2;
            if (this.lowerSet != 0) {
                for (int i14 = 0; i14 < 64; i14++) {
                    if ((this.lowerSet & (1 << i14)) != 0) {
                        int i15 = this.lowerBound + i14;
                        if (bits.get(i15)) {
                            snapshotIdSet3 = snapshotIdSet3.set(i15);
                        }
                    }
                }
            }
            if (this.upperSet != 0) {
                while (i12 < 64) {
                    if ((this.upperSet & (1 << i12)) != 0) {
                        int i16 = this.lowerBound + i12 + 64;
                        if (bits.get(i16)) {
                            snapshotIdSet3 = snapshotIdSet3.set(i16);
                        }
                    }
                    i12++;
                }
            }
            return snapshotIdSet3;
        }
        int[] iArr4 = bits.belowBound;
        if (iArr4 != null) {
            for (int i17 : iArr4) {
                if (get(i17)) {
                    snapshotIdSet2 = snapshotIdSet2.set(i17);
                }
            }
        }
        SnapshotIdSet snapshotIdSet4 = snapshotIdSet2;
        if (bits.lowerSet != 0) {
            for (int i18 = 0; i18 < 64; i18++) {
                if ((bits.lowerSet & (1 << i18)) != 0) {
                    int i19 = bits.lowerBound + i18;
                    if (get(i19)) {
                        snapshotIdSet4 = snapshotIdSet4.set(i19);
                    }
                }
            }
        }
        if (bits.upperSet != 0) {
            while (i12 < 64) {
                if ((bits.upperSet & (1 << i12)) != 0) {
                    int i20 = bits.lowerBound + i12 + 64;
                    if (get(i20)) {
                        snapshotIdSet4 = snapshotIdSet4.set(i20);
                    }
                }
                i12++;
            }
        }
        return snapshotIdSet4;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet bits) {
        SnapshotIdSet snapshotIdSetClear;
        SnapshotIdSet snapshotIdSet = EMPTY;
        if (bits == snapshotIdSet) {
            return this;
        }
        if (this == snapshotIdSet) {
            return snapshotIdSet;
        }
        int i10 = bits.lowerBound;
        int i11 = this.lowerBound;
        if (i10 == i11) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet & (~bits.upperSet), this.lowerSet & (~bits.lowerSet), i11, iArr2);
            }
        }
        int[] iArr3 = bits.belowBound;
        if (iArr3 != null) {
            snapshotIdSetClear = this;
            for (int i12 : iArr3) {
                snapshotIdSetClear = snapshotIdSetClear.clear(i12);
            }
        } else {
            snapshotIdSetClear = this;
        }
        if (bits.lowerSet != 0) {
            for (int i13 = 0; i13 < 64; i13++) {
                if ((bits.lowerSet & (1 << i13)) != 0) {
                    snapshotIdSetClear = snapshotIdSetClear.clear(bits.lowerBound + i13);
                }
            }
        }
        if (bits.upperSet != 0) {
            for (int i14 = 0; i14 < 64; i14++) {
                if ((bits.upperSet & (1 << i14)) != 0) {
                    snapshotIdSetClear = snapshotIdSetClear.clear(bits.lowerBound + i14 + 64);
                }
            }
        }
        return snapshotIdSetClear;
    }

    public final SnapshotIdSet clear(int bit) {
        int[] iArr;
        int iBinarySearch;
        int i10 = this.lowerBound;
        int i11 = bit - i10;
        if (i11 >= 0 && i11 < 64) {
            long j10 = 1 << i11;
            long j11 = this.lowerSet;
            if ((j11 & j10) != 0) {
                return new SnapshotIdSet(this.upperSet, (~j10) & j11, i10, this.belowBound);
            }
        } else if (i11 >= 64 && i11 < 128) {
            long j12 = 1 << (i11 - 64);
            long j13 = this.upperSet;
            if ((j13 & j12) != 0) {
                return new SnapshotIdSet((~j12) & j13, this.lowerSet, i10, this.belowBound);
            }
        } else if (i11 < 0 && (iArr = this.belowBound) != null && (iBinarySearch = SnapshotIdSetKt.binarySearch(iArr, bit)) >= 0) {
            int length = iArr.length;
            int i12 = length - 1;
            if (i12 == 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, null);
            }
            int[] iArr2 = new int[i12];
            if (iBinarySearch > 0) {
                r.K(0, 0, iBinarySearch, iArr, iArr2);
            }
            if (iBinarySearch < i12) {
                r.K(iBinarySearch, iBinarySearch + 1, length, iArr, iArr2);
            }
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [androidx.compose.runtime.snapshots.SnapshotIdSet] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v9 */
    public final SnapshotIdSet fastFold(SnapshotIdSet initial, p<? super SnapshotIdSet, ? super Integer, SnapshotIdSet> operation) {
        int[] iArr = this.belowBound;
        int i10 = 0;
        if (iArr != null) {
            int length = iArr.length;
            int i11 = 0;
            initial = initial;
            while (i11 < length) {
                Object objInvoke = operation.invoke(initial, Integer.valueOf(iArr[i11]));
                i11++;
                initial = objInvoke;
            }
        }
        if (this.lowerSet != 0) {
            int i12 = 0;
            initial = initial;
            while (i12 < 64) {
                if ((this.lowerSet & (1 << i12)) != 0) {
                    initial = operation.invoke(initial, Integer.valueOf(this.lowerBound + i12));
                }
                i12++;
                initial = initial;
            }
        }
        initial = initial;
        if (this.upperSet != 0) {
            while (i10 < 64) {
                if ((this.upperSet & (1 << i10)) != 0) {
                    initial = operation.invoke(initial, Integer.valueOf(this.lowerBound + i10 + 64));
                }
                i10++;
                initial = initial;
            }
        }
        return (SnapshotIdSet) initial;
    }

    public final void fastForEach(l<? super Integer, t0> block) {
        int[] iArr = this.belowBound;
        if (iArr != null) {
            for (int i10 : iArr) {
                block.invoke(Integer.valueOf(i10));
            }
        }
        if (this.lowerSet != 0) {
            for (int i11 = 0; i11 < 64; i11++) {
                if ((this.lowerSet & (1 << i11)) != 0) {
                    block.invoke(Integer.valueOf(this.lowerBound + i11));
                }
            }
        }
        if (this.upperSet != 0) {
            for (int i12 = 0; i12 < 64; i12++) {
                if ((this.upperSet & (1 << i12)) != 0) {
                    block.invoke(Integer.valueOf(this.lowerBound + i12 + 64));
                }
            }
        }
    }

    public final boolean get(int bit) {
        int[] iArr;
        int i10 = bit - this.lowerBound;
        return (i10 < 0 || i10 >= 64) ? (i10 < 64 || i10 >= 128) ? i10 <= 0 && (iArr = this.belowBound) != null && SnapshotIdSetKt.binarySearch(iArr, bit) >= 0 : ((1 << (i10 - 64)) & this.upperSet) != 0 : ((1 << i10) & this.lowerSet) != 0;
    }

    @Override // java.lang.Iterable
    public Iterator<Integer> iterator() {
        return f.n(new AnonymousClass1(null));
    }

    public final int lowest(int i10) {
        int i11;
        int iNumberOfTrailingZeros;
        int[] iArr = this.belowBound;
        if (iArr != null) {
            return iArr[0];
        }
        long j10 = this.lowerSet;
        if (j10 != 0) {
            i11 = this.lowerBound;
            iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j10);
        } else {
            long j11 = this.upperSet;
            if (j11 == 0) {
                return i10;
            }
            i11 = this.lowerBound + 64;
            iNumberOfTrailingZeros = Long.numberOfTrailingZeros(j11);
        }
        return iNumberOfTrailingZeros + i11;
    }

    public final SnapshotIdSet or(SnapshotIdSet bits) {
        SnapshotIdSet snapshotIdSet;
        SnapshotIdSet snapshotIdSet2 = EMPTY;
        if (bits == snapshotIdSet2) {
            return this;
        }
        if (this == snapshotIdSet2) {
            return bits;
        }
        int i10 = bits.lowerBound;
        int i11 = this.lowerBound;
        if (i10 == i11) {
            int[] iArr = bits.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet | bits.upperSet, this.lowerSet | bits.lowerSet, i11, iArr2);
            }
        }
        int i12 = 0;
        if (this.belowBound == null) {
            int[] iArr3 = this.belowBound;
            if (iArr3 != null) {
                for (int i13 : iArr3) {
                    bits = bits.set(i13);
                }
            }
            if (this.lowerSet != 0) {
                for (int i14 = 0; i14 < 64; i14++) {
                    if ((this.lowerSet & (1 << i14)) != 0) {
                        bits = bits.set(this.lowerBound + i14);
                    }
                }
            }
            if (this.upperSet != 0) {
                while (i12 < 64) {
                    if ((this.upperSet & (1 << i12)) != 0) {
                        bits = bits.set(this.lowerBound + i12 + 64);
                    }
                    i12++;
                }
            }
            return bits;
        }
        int[] iArr4 = bits.belowBound;
        if (iArr4 != null) {
            snapshotIdSet = this;
            for (int i15 : iArr4) {
                snapshotIdSet = snapshotIdSet.set(i15);
            }
        } else {
            snapshotIdSet = this;
        }
        if (bits.lowerSet != 0) {
            for (int i16 = 0; i16 < 64; i16++) {
                if ((bits.lowerSet & (1 << i16)) != 0) {
                    snapshotIdSet = snapshotIdSet.set(bits.lowerBound + i16);
                }
            }
        }
        if (bits.upperSet != 0) {
            while (i12 < 64) {
                if ((bits.upperSet & (1 << i12)) != 0) {
                    snapshotIdSet = snapshotIdSet.set(bits.lowerBound + i12 + 64);
                }
                i12++;
            }
        }
        return snapshotIdSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0084 A[PHI: r9
      0x0084: PHI (r9v5 java.util.ArrayList) = (r9v2 java.util.ArrayList), (r9v7 java.util.ArrayList) binds: [B:24:0x0063, B:26:0x006c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.snapshots.SnapshotIdSet set(int r22) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotIdSet.set(int):androidx.compose.runtime.snapshots.SnapshotIdSet");
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(" [");
        ArrayList arrayList = new ArrayList(s.U(this, 10));
        Iterator<Integer> it = iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next().intValue()));
        }
        return c.u(sb2, ListUtilsKt.fastJoinToString$default(arrayList, null, null, null, 0, null, null, 63, null), ']');
    }
}
