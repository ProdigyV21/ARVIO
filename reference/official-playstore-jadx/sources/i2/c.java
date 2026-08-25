package i2;

import androidx.compose.animation.core.AnimationState;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends CancellationException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16024i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AnimationState f16025l;

    public c(int i10, AnimationState animationState) {
        this.f16024i = i10;
        this.f16025l = animationState;
    }
}
