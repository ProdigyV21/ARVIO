package androidx.compose.ui.platform;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.MotionDurationScale;
import d7.j;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/compose/ui/platform/MotionDurationScaleImpl;", "Landroidx/compose/ui/MotionDurationScale;", "()V", "<set-?>", "", "scaleFactor", "getScaleFactor", "()F", "setScaleFactor", "(F)V", "scaleFactor$delegate", "Landroidx/compose/runtime/MutableFloatState;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MotionDurationScaleImpl implements MotionDurationScale {

    /* JADX INFO: renamed from: scaleFactor$delegate, reason: from kotlin metadata */
    private final MutableFloatState scaleFactor = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public <R> R fold(R r4, r7.p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MotionDurationScale.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MotionDurationScale.DefaultImpls.get(this, bVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return androidx.compose.ui.c.a(this);
    }

    @Override // androidx.compose.ui.MotionDurationScale
    public float getScaleFactor() {
        return this.scaleFactor.getFloatValue();
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return MotionDurationScale.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.ui.MotionDurationScale, d7.j
    public d7.j plus(d7.j jVar) {
        return MotionDurationScale.DefaultImpls.plus(this, jVar);
    }

    public void setScaleFactor(float f10) {
        this.scaleFactor.setFloatValue(f10);
    }
}
