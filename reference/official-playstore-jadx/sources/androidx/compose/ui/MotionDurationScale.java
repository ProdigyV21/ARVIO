package androidx.compose.ui;

import d7.h;
import d7.i;
import d7.j;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 \n2\u00020\u0001:\u0001\nR\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0018\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/MotionDurationScale;", "Ld7/j$a;", "", "getScaleFactor", "()F", "scaleFactor", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "Key", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MotionDurationScale extends j.a {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(MotionDurationScale motionDurationScale, R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) pVar.invoke(r4, motionDurationScale);
        }

        public static <E extends j.a> E get(MotionDurationScale motionDurationScale, j.b<E> bVar) {
            return (E) i.a(motionDurationScale, bVar);
        }

        public static j minusKey(MotionDurationScale motionDurationScale, j.b<?> bVar) {
            return i.b(motionDurationScale, bVar);
        }

        public static j plus(MotionDurationScale motionDurationScale, j jVar) {
            return h.a(motionDurationScale, jVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.MotionDurationScale$Key, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/MotionDurationScale$Key;", "Ld7/j$b;", "Landroidx/compose/ui/MotionDurationScale;", "<init>", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements j.b<MotionDurationScale> {
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

    float getScaleFactor();

    @Override // d7.j
    /* synthetic */ j minusKey(j.b bVar);

    @Override // d7.j
    /* synthetic */ j plus(j jVar);
}
