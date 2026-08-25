package androidx.compose.runtime.collection;

import d7.d;
import f4.f;
import f7.e;
import f7.h;
import ga.o;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import r7.p;
import s7.a;
import t.j0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/collection/ScatterSetWrapper;", "T", "", "Lt/j0;", "set", "<init>", "(Lt/j0;)V", "", "isEmpty", "()Z", "", "iterator", "()Ljava/util/Iterator;", "", "elements", "containsAll", "(Ljava/util/Collection;)Z", "element", "contains", "(Ljava/lang/Object;)Z", "Lt/j0;", "getSet$runtime_release", "()Lt/j0;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScatterSetWrapper<T> implements Set<T>, a {
    public static final int $stable = 8;
    private final j0 set;

    /* JADX INFO: renamed from: androidx.compose.runtime.collection.ScatterSetWrapper$iterator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lga/o;", "Lx6/t0;", "<anonymous>", "(Lga/o;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.runtime.collection.ScatterSetWrapper$iterator$1", f = "ScatterSetWrapper.kt", l = {34}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends h implements p<o, d<? super t0>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ ScatterSetWrapper<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ScatterSetWrapper<T> scatterSetWrapper, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = scatterSetWrapper;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(o oVar, d<? super t0> dVar) {
            return ((AnonymousClass1) create(oVar, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0099  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0053 -> B:22:0x0097). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0055 -> B:14:0x0066). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006f -> B:19:0x008e). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                r19 = this;
                r0 = r19
                int r1 = r0.label
                r2 = 0
                r3 = 8
                r4 = 1
                if (r1 == 0) goto L2e
                if (r1 != r4) goto L26
                int r1 = r0.I$3
                int r5 = r0.I$2
                long r6 = r0.J$0
                int r8 = r0.I$1
                int r9 = r0.I$0
                java.lang.Object r10 = r0.L$2
                long[] r10 = (long[]) r10
                java.lang.Object r11 = r0.L$1
                java.lang.Object[] r11 = (java.lang.Object[]) r11
                java.lang.Object r12 = r0.L$0
                ga.o r12 = (ga.o) r12
                k2.c.G(r20)
                goto L8e
            L26:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L2e:
                k2.c.G(r20)
                java.lang.Object r1 = r0.L$0
                ga.o r1 = (ga.o) r1
                androidx.compose.runtime.collection.ScatterSetWrapper<T> r5 = r0.this$0
                t.j0 r5 = r5.getSet()
                java.lang.Object[] r6 = r5.f21836b
                long[] r5 = r5.f21835a
                int r7 = r5.length
                int r7 = r7 + (-2)
                if (r7 < 0) goto L9c
                r8 = r2
            L45:
                r9 = r5[r8]
                long r11 = ~r9
                r13 = 7
                long r11 = r11 << r13
                long r11 = r11 & r9
                r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
                long r11 = r11 & r13
                int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
                if (r11 == 0) goto L97
                int r11 = r8 - r7
                int r11 = ~r11
                int r11 = r11 >>> 31
                int r11 = 8 - r11
                r12 = r1
                r1 = r2
                r17 = r9
                r10 = r5
                r9 = r7
                r5 = r11
                r11 = r6
                r6 = r17
            L66:
                if (r1 >= r5) goto L91
                r13 = 255(0xff, double:1.26E-321)
                long r13 = r13 & r6
                r15 = 128(0x80, double:6.3E-322)
                int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
                if (r13 >= 0) goto L8e
                int r2 = r8 << 3
                int r2 = r2 + r1
                r2 = r11[r2]
                r0.L$0 = r12
                r0.L$1 = r11
                r0.L$2 = r10
                r0.I$0 = r9
                r0.I$1 = r8
                r0.J$0 = r6
                r0.I$2 = r5
                r0.I$3 = r1
                r0.label = r4
                r12.b(r0, r2)
                e7.a r1 = e7.a.f15033i
                return r1
            L8e:
                long r6 = r6 >> r3
                int r1 = r1 + r4
                goto L66
            L91:
                if (r5 != r3) goto L9c
                r7 = r9
                r5 = r10
                r6 = r11
                r1 = r12
            L97:
                if (r8 == r7) goto L9c
                int r8 = r8 + 1
                goto L45
            L9c:
                x6.t0 r1 = x6.t0.f22605a
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.collection.ScatterSetWrapper.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public ScatterSetWrapper(j0 j0Var) {
        this.set = j0Var;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(T t2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object element) {
        return this.set.a(element);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<? extends Object> elements) {
        Collection<? extends Object> collection = elements;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!this.set.a(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: getSet$runtime_release, reason: from getter */
    public final j0 getSet() {
        return this.set;
    }

    public int getSize() {
        return this.set.f21838d;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.set.b();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return f.n(new AnonymousClass1(this, null));
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return kotlin.jvm.internal.o.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) kotlin.jvm.internal.o.b(this, tArr);
    }
}
