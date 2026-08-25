package androidx.compose.runtime;

import androidx.media3.container.MdtaMetadataEntry;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u001aT\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002<\u0010\n\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0001¢\u0006\u0002\b\tH\u0086@¢\u0006\u0004\b\u000b\u0010\f\u001aA\u0010\u0014\u001a\u00020\u0013\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u0001H\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a;\u0010\u0016\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00100\u000f2\u0006\u0010\u0011\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001b\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c\"\u0014\u0010\u001d\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001c¨\u0006\u001e"}, d2 = {"R", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/runtime/Recomposer;", "Lx6/y;", ContentDisposition.Parameters.Name, "recomposer", "Ld7/d;", "", "Lx6/n;", "block", "withRunningRecomposer", "(Lr7/q;Ld7/d;)Ljava/lang/Object;", "K", "V", "", "", "key", "value", "", "addMultiValue", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Z", "removeLastMultiValue", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "", "RecomposerCompoundHashKey", "I", "ProduceAnotherFrame", "Ljava/lang/Object;", "FramePending", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RecomposerKt {
    private static final int RecomposerCompoundHashKey = 1000;
    private static final Object ProduceAnotherFrame = new Object();
    private static final Object FramePending = new Object();

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.RecomposerKt$withRunningRecomposer$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"R", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.RecomposerKt$withRunningRecomposer$2", f = "Recomposer.kt", l = {76, MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64}, m = "invokeSuspend")
    public static final class AnonymousClass2<R> extends f7.j implements p<k0, d7.d<? super R>, Object> {
        final /* synthetic */ q<k0, Recomposer, d7.d<? super R>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.runtime.RecomposerKt$withRunningRecomposer$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"R", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.runtime.RecomposerKt$withRunningRecomposer$2$1", f = "Recomposer.kt", l = {75}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ Recomposer $recomposer;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(Recomposer recomposer, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$recomposer = recomposer;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.$recomposer, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    Recomposer recomposer = this.$recomposer;
                    this.label = 1;
                    Object objRunRecomposeAndApplyChanges = recomposer.runRecomposeAndApplyChanges(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objRunRecomposeAndApplyChanges == aVar) {
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
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(q<? super k0, ? super Recomposer, ? super d7.d<? super R>, ? extends Object> qVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$block = qVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Recomposer recomposer;
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                recomposer = new Recomposer(k0Var.getCoroutineContext());
                m0.p(k0Var, null, 0, new AnonymousClass1(recomposer, null), 3);
                q<k0, Recomposer, d7.d<? super R>, Object> qVar = this.$block;
                this.L$0 = recomposer;
                this.label = 1;
                obj = qVar.invoke(k0Var, recomposer, this);
                if (obj != aVar) {
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj2 = this.L$0;
                k2.c.G(obj);
                return obj2;
            }
            recomposer = (Recomposer) this.L$0;
            k2.c.G(obj);
            recomposer.close();
            this.L$0 = obj;
            this.label = 2;
            return recomposer.join(this) == aVar ? aVar : obj;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super R> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static final <K, V> boolean addMultiValue(Map<K, List<V>> map, K k, V v2) {
        List<V> arrayList = map.get(k);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(k, arrayList);
        }
        return arrayList.add(v2);
    }

    public static final <K, V> V removeLastMultiValue(Map<K, List<V>> map, K k) {
        List<V> list = map.get(k);
        if (list == null) {
            return null;
        }
        V v2 = (V) x.N0(list);
        if (list.isEmpty()) {
            map.remove(k);
        }
        return v2;
    }

    public static final <R> Object withRunningRecomposer(q<? super k0, ? super Recomposer, ? super d7.d<? super R>, ? extends Object> qVar, d7.d<? super R> dVar) {
        return l0.c(new AnonymousClass2(qVar, null), dVar);
    }
}
