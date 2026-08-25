package androidx.compose.runtime;

import androidx.media3.container.MdtaMetadataEntry;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0000*\u00028\u0001\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "Landroidx/compose/runtime/ProduceStateScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/runtime/ProduceStateScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1", f = "SnapshotFlow.kt", l = {66, MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend")
public final class SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1<R> extends f7.j implements p<ProduceStateScope<R>, d7.d<? super t0>, Object> {
    final /* synthetic */ d7.j $context;
    final /* synthetic */ na.j<T> $this_collectAsState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0000*\u00028\u0001\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$2", f = "SnapshotFlow.kt", l = {68}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ ProduceStateScope<R> $$this$produceState;
        final /* synthetic */ na.j<T> $this_collectAsState;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(na.j<? extends T> jVar, ProduceStateScope<R> produceStateScope, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_collectAsState = jVar;
            this.$$this$produceState = produceStateScope;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$this_collectAsState, this.$$this$produceState, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<T> jVar = this.$this_collectAsState;
                final ProduceStateScope<R> produceStateScope = this.$$this$produceState;
                na.k kVar = new na.k() { // from class: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.collectAsState.1.1.2.1
                    @Override // na.k
                    public final Object emit(T t2, d7.d<? super t0> dVar) {
                        produceStateScope.setValue(t2);
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = jVar.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1(d7.j jVar, na.j<? extends T> jVar2, d7.d<? super SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1> dVar) {
        super(2, dVar);
        this.$context = jVar;
        this.$this_collectAsState = jVar2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1 snapshotStateKt__SnapshotFlowKt$collectAsState$1$1 = new SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1(this.$context, this.$this_collectAsState, dVar);
        snapshotStateKt__SnapshotFlowKt$collectAsState$1$1.L$0 = obj;
        return snapshotStateKt__SnapshotFlowKt$collectAsState$1$1;
    }

    @Override // r7.p
    public final Object invoke(ProduceStateScope<R> produceStateScope, d7.d<? super t0> dVar) {
        return ((SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1) create(produceStateScope, dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (r0.collect(r1, r6) == r3) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
    
        if (ka.m0.y(r0, r2, r6) == r3) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004c, code lost:
    
        return r3;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L17
            if (r0 == r2) goto L13
            if (r0 != r1) goto Lb
            goto L13
        Lb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L13:
            k2.c.G(r7)
            goto L4d
        L17:
            k2.c.G(r7)
            java.lang.Object r7 = r6.L$0
            androidx.compose.runtime.ProduceStateScope r7 = (androidx.compose.runtime.ProduceStateScope) r7
            d7.j r0 = r6.$context
            d7.k r3 = d7.k.f14688i
            boolean r0 = kotlin.jvm.internal.p.a(r0, r3)
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L3a
            na.j<T> r0 = r6.$this_collectAsState
            androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$1 r1 = new androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$1
            r1.<init>()
            r6.label = r2
            java.lang.Object r7 = r0.collect(r1, r6)
            if (r7 != r3) goto L4d
            goto L4c
        L3a:
            d7.j r0 = r6.$context
            androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$2 r2 = new androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1$2
            na.j<T> r4 = r6.$this_collectAsState
            r5 = 0
            r2.<init>(r4, r7, r5)
            r6.label = r1
            java.lang.Object r7 = ka.m0.y(r0, r2, r6)
            if (r7 != r3) goto L4d
        L4c:
            return r3
        L4d:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
