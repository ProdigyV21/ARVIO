package androidx.compose.ui.platform;

import androidx.compose.runtime.MonotonicFrameClockKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a9\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00028\u00000\u0001H\u0080@¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"R", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeNanos", "onFrame", "withInfiniteAnimationFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InfiniteAnimationPolicyKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.ui.platform.InfiniteAnimationPolicyKt$withInfiniteAnimationFrameNanos$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "R"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.ui.platform.InfiniteAnimationPolicyKt$withInfiniteAnimationFrameNanos$2", f = "InfiniteAnimationPolicy.kt", l = {65}, m = "invokeSuspend")
    public static final class AnonymousClass2<R> extends f7.j implements r7.l<d7.d<? super R>, Object> {
        final /* synthetic */ r7.l<Long, R> $onFrame;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.l<? super Long, ? extends R> lVar, d7.d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$onFrame = lVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return new AnonymousClass2(this.$onFrame, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super R> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
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
            r7.l<Long, R> lVar = this.$onFrame;
            this.label = 1;
            Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(lVar, this);
            e7.a aVar = e7.a.f15033i;
            return objWithFrameNanos == aVar ? aVar : objWithFrameNanos;
        }
    }

    public static final <R> Object withInfiniteAnimationFrameNanos(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        InfiniteAnimationPolicy infiniteAnimationPolicy = (InfiniteAnimationPolicy) dVar.getContext().get(InfiniteAnimationPolicy.INSTANCE);
        return infiniteAnimationPolicy == null ? MonotonicFrameClockKt.withFrameNanos(lVar, dVar) : infiniteAnimationPolicy.onInfiniteOperation(new AnonymousClass2(lVar, null), dVar);
    }
}
