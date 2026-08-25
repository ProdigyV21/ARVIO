package androidx.compose.foundation.text2.input.internal;

import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.c;
import d7.j;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/FixedMotionDurationScale;", "Landroidx/compose/ui/MotionDurationScale;", "()V", "scaleFactor", "", "getScaleFactor", "()F", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FixedMotionDurationScale implements MotionDurationScale {
    public static final FixedMotionDurationScale INSTANCE = new FixedMotionDurationScale();

    private FixedMotionDurationScale() {
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MotionDurationScale.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MotionDurationScale.DefaultImpls.get(this, bVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return c.a(this);
    }

    @Override // androidx.compose.ui.MotionDurationScale
    public float getScaleFactor() {
        return 1.0f;
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public j minusKey(j.b<?> bVar) {
        return MotionDurationScale.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public j plus(j jVar) {
        return MotionDurationScale.DefaultImpls.plus(this, jVar);
    }
}
