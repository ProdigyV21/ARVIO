package androidx.compose.runtime;

import d7.j;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ9\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00028\u00000\u0003H¦@¢\u0006\u0004\b\t\u0010\nR\u0018\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/MonotonicFrameClock;", "Ld7/j$a;", "R", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeNanos", "onFrame", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "Key", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MonotonicFrameClock extends j.a {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(MonotonicFrameClock monotonicFrameClock, R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) pVar.invoke(r4, monotonicFrameClock);
        }

        public static <E extends j.a> E get(MonotonicFrameClock monotonicFrameClock, j.b<E> bVar) {
            return (E) d7.i.a(monotonicFrameClock, bVar);
        }

        @Deprecated
        public static j.b<?> getKey(MonotonicFrameClock monotonicFrameClock) {
            return h.a(monotonicFrameClock);
        }

        public static d7.j minusKey(MonotonicFrameClock monotonicFrameClock, j.b<?> bVar) {
            return d7.i.b(monotonicFrameClock, bVar);
        }

        public static d7.j plus(MonotonicFrameClock monotonicFrameClock, d7.j jVar) {
            return d7.h.a(monotonicFrameClock, jVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.MonotonicFrameClock$Key, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/runtime/MonotonicFrameClock$Key;", "Ld7/j$b;", "Landroidx/compose/runtime/MonotonicFrameClock;", "<init>", "()V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements j.b<MonotonicFrameClock> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    @Override // d7.j
    /* synthetic */ Object fold(Object obj, p pVar);

    @Override // d7.j
    /* synthetic */ j.a get(j.b bVar);

    @Override // d7.j.a
    j.b<?> getKey();

    @Override // d7.j
    /* synthetic */ d7.j minusKey(j.b bVar);

    @Override // d7.j
    /* synthetic */ d7.j plus(d7.j jVar);

    <R> Object withFrameNanos(r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar);
}
