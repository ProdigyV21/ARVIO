package androidx.compose.animation.core;

import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.platform.InfiniteAnimationPolicy;
import f7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\b\u001a9\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00028\u00000\u0001H\u0086@¢\u0006\u0004\b\u0007\u0010\b\u001a;\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002#\b\u0004\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00000\u0001H\u0086H¢\u0006\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"R", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeNanos", "onFrame", "withInfiniteAnimationFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "frameTimeMillis", "withInfiniteAnimationFrameMillis", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InfiniteAnimationPolicyKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteAnimationPolicyKt$withInfiniteAnimationFrameMillis$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "R", "it", "", "invoke", "(J)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass2<R> extends r implements l<Long, R> {
        final /* synthetic */ l<Long, R> $onFrame;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super Long, ? extends R> lVar) {
            super(1);
            this.$onFrame = lVar;
        }

        public final R invoke(long j10) {
            return (R) this.$onFrame.invoke(Long.valueOf(j10 / 1000000));
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).longValue());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteAnimationPolicyKt$withInfiniteAnimationFrameNanos$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "R"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.animation.core.InfiniteAnimationPolicyKt$withInfiniteAnimationFrameNanos$2", f = "InfiniteAnimationPolicy.kt", l = {31}, m = "invokeSuspend")
    public static final class C02682<R> extends j implements l<d7.d<? super R>, Object> {
        final /* synthetic */ l<Long, R> $onFrame;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02682(l<? super Long, ? extends R> lVar, d7.d<? super C02682> dVar) {
            super(1, dVar);
            this.$onFrame = lVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new C02682(this.$onFrame, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super R> dVar) {
            return ((C02682) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            l<Long, R> lVar = this.$onFrame;
            this.label = 1;
            Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(lVar, this);
            e7.a aVar = e7.a.f15033i;
            return objWithFrameNanos == aVar ? aVar : objWithFrameNanos;
        }
    }

    public static final <R> Object withInfiniteAnimationFrameMillis(l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        return withInfiniteAnimationFrameNanos(new AnonymousClass2(lVar), dVar);
    }

    public static final <R> Object withInfiniteAnimationFrameNanos(l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        InfiniteAnimationPolicy infiniteAnimationPolicy = (InfiniteAnimationPolicy) dVar.getContext().get(InfiniteAnimationPolicy.INSTANCE);
        return infiniteAnimationPolicy == null ? MonotonicFrameClockKt.withFrameNanos(lVar, dVar) : infiniteAnimationPolicy.onInfiniteOperation(new C02682(lVar, null), dVar);
    }
}
