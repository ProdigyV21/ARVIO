package coil.request;

import a0.c;
import android.graphics.GifDecoder;
import coil.request.ImageRequest;
import coil.transform.AnimatedTransformation;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0019\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\t\u001a!\u0010\r\u001a\u00020\u0000*\u00020\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n*\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010\u001a!\u0010\u0011\u001a\u00020\u0000*\u00020\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0004\b\u0011\u0010\u000e\u001a\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n*\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcoil/request/ImageRequest$Builder;", "", "repeatCount", "(Lcoil/request/ImageRequest$Builder;I)Lcoil/request/ImageRequest$Builder;", "Lcoil/request/Parameters;", "(Lcoil/request/Parameters;)Ljava/lang/Integer;", "Lcoil/transform/AnimatedTransformation;", "animatedTransformation", "(Lcoil/request/ImageRequest$Builder;Lcoil/transform/AnimatedTransformation;)Lcoil/request/ImageRequest$Builder;", "(Lcoil/request/Parameters;)Lcoil/transform/AnimatedTransformation;", "Lkotlin/Function0;", "Lx6/t0;", "callback", "onAnimationStart", "(Lcoil/request/ImageRequest$Builder;Lr7/a;)Lcoil/request/ImageRequest$Builder;", "animationStartCallback", "(Lcoil/request/Parameters;)Lr7/a;", "onAnimationEnd", "animationEndCallback", "coil-gif_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Gifs {
    public static final ImageRequest.Builder animatedTransformation(ImageRequest.Builder builder, AnimatedTransformation animatedTransformation) {
        return ImageRequest.Builder.setParameter$default(builder, GifDecoder.ANIMATED_TRANSFORMATION_KEY, animatedTransformation, null, 4, null);
    }

    public static final r7.a<t0> animationEndCallback(Parameters parameters) {
        return (r7.a) parameters.value(GifDecoder.ANIMATION_END_CALLBACK_KEY);
    }

    public static final r7.a<t0> animationStartCallback(Parameters parameters) {
        return (r7.a) parameters.value(GifDecoder.ANIMATION_START_CALLBACK_KEY);
    }

    public static final ImageRequest.Builder onAnimationEnd(ImageRequest.Builder builder, r7.a<t0> aVar) {
        return ImageRequest.Builder.setParameter$default(builder, GifDecoder.ANIMATION_END_CALLBACK_KEY, aVar, null, 4, null);
    }

    public static final ImageRequest.Builder onAnimationStart(ImageRequest.Builder builder, r7.a<t0> aVar) {
        return ImageRequest.Builder.setParameter$default(builder, GifDecoder.ANIMATION_START_CALLBACK_KEY, aVar, null, 4, null);
    }

    public static final ImageRequest.Builder repeatCount(ImageRequest.Builder builder, int i10) {
        if (i10 >= -1) {
            return ImageRequest.Builder.setParameter$default(builder, GifDecoder.REPEAT_COUNT_KEY, Integer.valueOf(i10), null, 4, null);
        }
        throw new IllegalArgumentException(c.i(i10, "Invalid repeatCount: ").toString());
    }

    public static final AnimatedTransformation animatedTransformation(Parameters parameters) {
        return (AnimatedTransformation) parameters.value(GifDecoder.ANIMATED_TRANSFORMATION_KEY);
    }

    public static final Integer repeatCount(Parameters parameters) {
        return (Integer) parameters.value(GifDecoder.REPEAT_COUNT_KEY);
    }
}
