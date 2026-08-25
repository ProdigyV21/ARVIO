package androidx.compose.ui.platform;

import d7.j;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ4\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H¦@¢\u0006\u0004\b\u0007\u0010\bR\u0018\u0010\f\u001a\u0006\u0012\u0002\b\u00030\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/platform/InfiniteAnimationPolicy;", "Ld7/j$a;", "R", "Lkotlin/Function1;", "Ld7/d;", "", "block", "onInfiniteOperation", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "Key", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface InfiniteAnimationPolicy extends j.a {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(InfiniteAnimationPolicy infiniteAnimationPolicy, R r4, r7.p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) pVar.invoke(r4, infiniteAnimationPolicy);
        }

        public static <E extends j.a> E get(InfiniteAnimationPolicy infiniteAnimationPolicy, j.b<E> bVar) {
            return (E) d7.i.a(infiniteAnimationPolicy, bVar);
        }

        @Deprecated
        public static j.b<?> getKey(InfiniteAnimationPolicy infiniteAnimationPolicy) {
            return l.a(infiniteAnimationPolicy);
        }

        public static d7.j minusKey(InfiniteAnimationPolicy infiniteAnimationPolicy, j.b<?> bVar) {
            return d7.i.b(infiniteAnimationPolicy, bVar);
        }

        public static d7.j plus(InfiniteAnimationPolicy infiniteAnimationPolicy, d7.j jVar) {
            return d7.h.a(infiniteAnimationPolicy, jVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.InfiniteAnimationPolicy$Key, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/platform/InfiniteAnimationPolicy$Key;", "Ld7/j$b;", "Landroidx/compose/ui/platform/InfiniteAnimationPolicy;", "<init>", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements j.b<InfiniteAnimationPolicy> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    @Override // d7.j
    /* synthetic */ Object fold(Object obj, r7.p pVar);

    @Override // d7.j
    /* synthetic */ j.a get(j.b bVar);

    @Override // d7.j.a
    j.b<?> getKey();

    @Override // d7.j
    /* synthetic */ d7.j minusKey(j.b bVar);

    <R> Object onInfiniteOperation(r7.l<? super d7.d<? super R>, ? extends Object> lVar, d7.d<? super R> dVar);

    @Override // d7.j
    /* synthetic */ d7.j plus(d7.j jVar);
}
