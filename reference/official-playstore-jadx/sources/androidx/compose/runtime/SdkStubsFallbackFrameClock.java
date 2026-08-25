package androidx.compose.runtime;

import androidx.compose.runtime.MonotonicFrameClock;
import d7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import ka.m0;
import ka.s0;
import ka.x0;
import kotlin.Metadata;
import pa.q;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/SdkStubsFallbackFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "<init>", "()V", "R", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeNanos", "onFrame", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "DefaultFrameDelay", "J", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SdkStubsFallbackFrameClock implements MonotonicFrameClock {
    private static final long DefaultFrameDelay = 16;
    public static final SdkStubsFallbackFrameClock INSTANCE = new SdkStubsFallbackFrameClock();

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.SdkStubsFallbackFrameClock$withFrameNanos$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"R", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.runtime.SdkStubsFallbackFrameClock$withFrameNanos$2", f = "ActualAndroid.android.kt", l = {52}, m = "invokeSuspend")
    public static final class AnonymousClass2<R> extends f7.j implements p<k0, d7.d<? super R>, Object> {
        final /* synthetic */ r7.l<Long, R> $onFrame;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.l<? super Long, ? extends R> lVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$onFrame = lVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$onFrame, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(16L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return this.$onFrame.invoke(new Long(System.nanoTime()));
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super R> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    private SdkStubsFallbackFrameClock() {
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, bVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return h.a(this);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j plus(d7.j jVar) {
        return MonotonicFrameClock.DefaultImpls.plus(this, jVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public <R> Object withFrameNanos(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        x0 x0Var = x0.f19652a;
        return m0.y(q.f21294a, new AnonymousClass2(lVar, null), dVar);
    }
}
