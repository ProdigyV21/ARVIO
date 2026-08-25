package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a?\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012#\b\u0004\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00000\u0002H\u0086H¢\u0006\u0004\b\b\u0010\t\u001a9\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@¢\u0006\u0004\b\u000b\u0010\f\u001a9\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00028\u00000\u0002H\u0086@¢\u0006\u0004\b\b\u0010\f\"\u001e\u0010\u0012\u001a\u00020\u0001*\u00020\r8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"R", "Landroidx/compose/runtime/MonotonicFrameClock;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeMillis", "onFrame", "withFrameMillis", "(Landroidx/compose/runtime/MonotonicFrameClock;Lr7/l;Ld7/d;)Ljava/lang/Object;", "frameTimeNanos", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/j;", "getMonotonicFrameClock", "(Ld7/j;)Landroidx/compose/runtime/MonotonicFrameClock;", "getMonotonicFrameClock$annotations", "(Ld7/j;)V", "monotonicFrameClock", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MonotonicFrameClockKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.MonotonicFrameClockKt$withFrameMillis$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "R", "it", "", "invoke", "(J)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass2<R> extends r implements r7.l<Long, R> {
        final /* synthetic */ r7.l<Long, R> $onFrame;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.l<? super Long, ? extends R> lVar) {
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

    public static final MonotonicFrameClock getMonotonicFrameClock(d7.j jVar) {
        MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) jVar.get(MonotonicFrameClock.INSTANCE);
        if (monotonicFrameClock != null) {
            return monotonicFrameClock;
        }
        throw new IllegalStateException("A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.");
    }

    public static final <R> Object withFrameMillis(MonotonicFrameClock monotonicFrameClock, r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        return monotonicFrameClock.withFrameNanos(new AnonymousClass2(lVar), dVar);
    }

    public static final <R> Object withFrameNanos(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        return getMonotonicFrameClock(dVar.getContext()).withFrameNanos(lVar, dVar);
    }

    public static final <R> Object withFrameMillis(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        return getMonotonicFrameClock(dVar.getContext()).withFrameNanos(new AnonymousClass2(lVar), dVar);
    }

    public static /* synthetic */ void getMonotonicFrameClock$annotations(d7.j jVar) {
    }
}
