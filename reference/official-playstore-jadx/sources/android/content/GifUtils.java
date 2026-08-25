package android.content;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PostProcessor;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Dimension;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import androidx.vectordrawable.graphics.drawable.c;
import coil.transform.AnimatedTransformation;
import coil.transform.PixelOpacity;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: coil.util.-GifUtils, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a/\u0010\t\u001a\u00020\b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0001¢\u0006\u0004\b\t\u0010\n\u001a/\u0010\f\u001a\u00020\u000b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\f\u0010\r\u001a4\u0010\u0012\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0010H\u0080\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a*\u0010\u0019\u001a\u00020\u0017*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004H\u0080\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u0017*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004H\u0080\b¢\u0006\u0004\b\u001b\u0010\u001a\u001a\u001b\u0010\u001d\u001a\u00020\u0017*\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u001d\u0010\u001e\"\u0018\u0010\"\u001a\u00020\u0017*\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\"\u0018\u0010%\u001a\u00020$*\u00020#8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcoil/transform/AnimatedTransformation;", "Landroid/graphics/PostProcessor;", "asPostProcessor", "(Lcoil/transform/AnimatedTransformation;)Landroid/graphics/PostProcessor;", "Lkotlin/Function0;", "Lx6/t0;", "onStart", "onEnd", "Landroid/graphics/drawable/Animatable2$AnimationCallback;", "animatable2CallbackOf", "(Lr7/a;Lr7/a;)Landroid/graphics/drawable/Animatable2$AnimationCallback;", "Landroidx/vectordrawable/graphics/drawable/c;", "animatable2CompatCallbackOf", "(Lr7/a;Lr7/a;)Landroidx/vectordrawable/graphics/drawable/c;", "T", "", "Lkotlin/Function1;", "action", "forEachIndices", "(Ljava/util/List;Lr7/l;)V", "Lcoil/size/Size;", "Lcoil/size/Scale;", "scale", "", "original", "widthPx", "(Lcoil/size/Size;Lcoil/size/Scale;Lr7/a;)I", "heightPx", "Lcoil/size/Dimension;", "toPx", "(Lcoil/size/Dimension;Lcoil/size/Scale;)I", "Lcoil/transform/PixelOpacity;", "getFlag", "(Lcoil/transform/PixelOpacity;)I", "flag", "Landroid/graphics/Bitmap$Config;", "", "isHardware", "(Landroid/graphics/Bitmap$Config;)Z", "coil-gif_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class GifUtils {

    /* JADX INFO: renamed from: coil.util.-GifUtils$WhenMappings */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PixelOpacity.values().length];
            try {
                iArr[PixelOpacity.UNCHANGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PixelOpacity.TRANSLUCENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PixelOpacity.OPAQUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Scale.values().length];
            try {
                iArr2[Scale.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[Scale.FIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final Animatable2.AnimationCallback animatable2CallbackOf(final a<t0> aVar, final a<t0> aVar2) {
        return new Animatable2.AnimationCallback() { // from class: coil.util.-GifUtils.animatable2CallbackOf.1
            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public void onAnimationEnd(Drawable drawable) {
                a<t0> aVar3 = aVar2;
                if (aVar3 != null) {
                    aVar3.invoke();
                }
            }

            @Override // android.graphics.drawable.Animatable2.AnimationCallback
            public void onAnimationStart(Drawable drawable) {
                a<t0> aVar3 = aVar;
                if (aVar3 != null) {
                    aVar3.invoke();
                }
            }
        };
    }

    public static final c animatable2CompatCallbackOf(final a<t0> aVar, final a<t0> aVar2) {
        return new c() { // from class: coil.util.-GifUtils.animatable2CompatCallbackOf.1
            @Override // androidx.vectordrawable.graphics.drawable.c
            public void onAnimationEnd(Drawable drawable) {
                a<t0> aVar3 = aVar2;
                if (aVar3 != null) {
                    aVar3.invoke();
                }
            }

            @Override // androidx.vectordrawable.graphics.drawable.c
            public void onAnimationStart(Drawable drawable) {
                a<t0> aVar3 = aVar;
                if (aVar3 != null) {
                    aVar3.invoke();
                }
            }
        };
    }

    public static final PostProcessor asPostProcessor(final AnimatedTransformation animatedTransformation) {
        return new PostProcessor() { // from class: coil.util.a
            @Override // android.graphics.PostProcessor
            public final int onPostProcess(Canvas canvas) {
                return GifUtils.asPostProcessor$lambda$0(animatedTransformation, canvas);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asPostProcessor$lambda$0(AnimatedTransformation animatedTransformation, Canvas canvas) {
        return getFlag(animatedTransformation.transform(canvas));
    }

    public static final <T> void forEachIndices(List<? extends T> list, l<? super T, t0> lVar) {
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            lVar.invoke(list.get(i10));
        }
    }

    public static final int getFlag(PixelOpacity pixelOpacity) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[pixelOpacity.ordinal()];
        if (i10 == 1) {
            return 0;
        }
        if (i10 == 2) {
            return -3;
        }
        if (i10 == 3) {
            return -1;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int heightPx(Size size, Scale scale, a<Integer> aVar) {
        return Sizes.isOriginal(size) ? ((Number) aVar.invoke()).intValue() : toPx(size.getHeight(), scale);
    }

    public static final boolean isHardware(Bitmap.Config config) {
        return Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE;
    }

    public static final int toPx(Dimension dimension, Scale scale) {
        if (dimension instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) dimension).px;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$1[scale.ordinal()];
        if (i10 == 1) {
            return Integer.MIN_VALUE;
        }
        if (i10 == 2) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int widthPx(Size size, Scale scale, a<Integer> aVar) {
        return Sizes.isOriginal(size) ? ((Number) aVar.invoke()).intValue() : toPx(size.getWidth(), scale);
    }
}
