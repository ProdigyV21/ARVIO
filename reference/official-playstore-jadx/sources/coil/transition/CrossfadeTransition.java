package coil.transition;

import android.graphics.DataSource;
import android.graphics.drawable.Drawable;
import android.view.Scale;
import coil.drawable.CrossfadeDrawable;
import coil.request.ErrorResult;
import coil.request.ImageResult;
import coil.request.SuccessResult;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001\u0017B-\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcoil/transition/CrossfadeTransition;", "Lcoil/transition/Transition;", "Lcoil/transition/TransitionTarget;", "target", "Lcoil/request/ImageResult;", "result", "", "durationMillis", "", "preferExactIntrinsicSize", "<init>", "(Lcoil/transition/TransitionTarget;Lcoil/request/ImageResult;IZ)V", "Lx6/t0;", "transition", "()V", "Lcoil/transition/TransitionTarget;", "Lcoil/request/ImageResult;", "I", "getDurationMillis", "()I", "Z", "getPreferExactIntrinsicSize", "()Z", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CrossfadeTransition implements Transition {
    private final int durationMillis;
    private final boolean preferExactIntrinsicSize;
    private final ImageResult result;
    private final TransitionTarget target;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcoil/transition/CrossfadeTransition$Factory;", "Lcoil/transition/Transition$Factory;", "durationMillis", "", "preferExactIntrinsicSize", "", "(IZ)V", "getDurationMillis", "()I", "getPreferExactIntrinsicSize", "()Z", "create", "Lcoil/transition/Transition;", "target", "Lcoil/transition/TransitionTarget;", "result", "Lcoil/request/ImageResult;", "equals", "other", "", "hashCode", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Transition.Factory {
        private final int durationMillis;
        private final boolean preferExactIntrinsicSize;

        /* JADX WARN: Multi-variable type inference failed */
        public Factory() {
            this(0, 0 == true ? 1 : 0, 3, null);
        }

        @Override // coil.transition.Transition.Factory
        public Transition create(TransitionTarget target, ImageResult result) {
            return !(result instanceof SuccessResult) ? Transition.Factory.NONE.create(target, result) : ((SuccessResult) result).getDataSource() == DataSource.MEMORY_CACHE ? Transition.Factory.NONE.create(target, result) : new CrossfadeTransition(target, result, this.durationMillis, this.preferExactIntrinsicSize);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Factory)) {
                return false;
            }
            Factory factory = (Factory) other;
            return this.durationMillis == factory.durationMillis && this.preferExactIntrinsicSize == factory.preferExactIntrinsicSize;
        }

        public final int getDurationMillis() {
            return this.durationMillis;
        }

        public final boolean getPreferExactIntrinsicSize() {
            return this.preferExactIntrinsicSize;
        }

        public int hashCode() {
            return (this.durationMillis * 31) + (this.preferExactIntrinsicSize ? 1231 : 1237);
        }

        public Factory(int i10) {
            this(i10, false, 2, null);
        }

        public Factory(int i10, boolean z) {
            this.durationMillis = i10;
            this.preferExactIntrinsicSize = z;
            if (i10 <= 0) {
                throw new IllegalArgumentException("durationMillis must be > 0.");
            }
        }

        public /* synthetic */ Factory(int i10, boolean z, int i11, h hVar) {
            this((i11 & 1) != 0 ? 100 : i10, (i11 & 2) != 0 ? false : z);
        }
    }

    public CrossfadeTransition(TransitionTarget transitionTarget, ImageResult imageResult) {
        this(transitionTarget, imageResult, 0, false, 12, null);
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public final boolean getPreferExactIntrinsicSize() {
        return this.preferExactIntrinsicSize;
    }

    @Override // coil.transition.Transition
    public void transition() {
        Drawable drawable = this.target.getDrawable();
        Drawable drawable2 = this.result.getDrawable();
        Scale scale = this.result.getRequest().getScale();
        int i10 = this.durationMillis;
        ImageResult imageResult = this.result;
        CrossfadeDrawable crossfadeDrawable = new CrossfadeDrawable(drawable, drawable2, scale, i10, ((imageResult instanceof SuccessResult) && ((SuccessResult) imageResult).getIsPlaceholderCached()) ? false : true, this.preferExactIntrinsicSize);
        ImageResult imageResult2 = this.result;
        if (imageResult2 instanceof SuccessResult) {
            this.target.onSuccess(crossfadeDrawable);
        } else if (imageResult2 instanceof ErrorResult) {
            this.target.onError(crossfadeDrawable);
        }
    }

    public CrossfadeTransition(TransitionTarget transitionTarget, ImageResult imageResult, int i10) {
        this(transitionTarget, imageResult, i10, false, 8, null);
    }

    public CrossfadeTransition(TransitionTarget transitionTarget, ImageResult imageResult, int i10, boolean z) {
        this.target = transitionTarget;
        this.result = imageResult;
        this.durationMillis = i10;
        this.preferExactIntrinsicSize = z;
        if (i10 <= 0) {
            throw new IllegalArgumentException("durationMillis must be > 0.");
        }
    }

    public /* synthetic */ CrossfadeTransition(TransitionTarget transitionTarget, ImageResult imageResult, int i10, boolean z, int i11, h hVar) {
        this(transitionTarget, imageResult, (i11 & 4) != 0 ? 100 : i10, (i11 & 8) != 0 ? false : z);
    }
}
