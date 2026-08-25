package com.arflix.tv.ui.motion;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r8F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R+\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0016\u0010\b\"\u0004\b\u0017\u0010\nR\u0011\u0010\u0019\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/ui/motion/ArvioBackMotion;", "", "<init>", "()V", "<set-?>", "", "progress", "getProgress", "()F", "setProgress$app_playRelease", "(F)V", "progress$delegate", "Landroidx/compose/runtime/MutableFloatState;", "", "swipeEdge", "getSwipeEdge", "()I", "setSwipeEdge$app_playRelease", "(I)V", "swipeEdge$delegate", "Landroidx/compose/runtime/MutableIntState;", "touchY", "getTouchY", "setTouchY$app_playRelease", "touchY$delegate", "eased", "getEased", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArvioBackMotion {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final MutableFloatState progress = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: swipeEdge$delegate, reason: from kotlin metadata */
    private final MutableIntState swipeEdge = SnapshotIntStateKt.mutableIntStateOf(0);

    /* JADX INFO: renamed from: touchY$delegate, reason: from kotlin metadata */
    private final MutableFloatState touchY = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);

    public final float getEased() {
        return ArvioPredictiveBackKt.getArvioStandardDecelerate().transform(getProgress());
    }

    public final float getProgress() {
        return this.progress.getFloatValue();
    }

    public final int getSwipeEdge() {
        return this.swipeEdge.getIntValue();
    }

    public final float getTouchY() {
        return this.touchY.getFloatValue();
    }

    public final void setProgress$app_playRelease(float f10) {
        this.progress.setFloatValue(f10);
    }

    public final void setSwipeEdge$app_playRelease(int i10) {
        this.swipeEdge.setIntValue(i10);
    }

    public final void setTouchY$app_playRelease(float f10) {
        this.touchY.setFloatValue(f10);
    }
}
