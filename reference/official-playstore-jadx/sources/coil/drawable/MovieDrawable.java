package coil.drawable;

import android.content.GifUtils;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DecodeUtils;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.Scale;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.vectordrawable.graphics.drawable.c;
import androidx.vectordrawable.graphics.drawable.d;
import coil.transform.AnimatedTransformation;
import coil.transform.PixelOpacity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 d2\u00020\u00012\u00020\u0002:\u0001dB%\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0012J\u0015\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\u0018¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0018H\u0016¢\u0006\u0004\b%\u0010\u001bJ\u000f\u0010&\u001a\u00020\u0018H\u0017¢\u0006\u0004\b&\u0010\u001dJ\u0019\u0010)\u001a\u00020\u00102\b\u0010(\u001a\u0004\u0018\u00010'H\u0016¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0018H\u0016¢\u0006\u0004\b+\u0010\u001dJ\u000f\u0010,\u001a\u00020\u0018H\u0016¢\u0006\u0004\b,\u0010\u001dJ\u000f\u0010-\u001a\u00020\u000bH\u0016¢\u0006\u0004\b-\u0010\rJ\u000f\u0010.\u001a\u00020\u0010H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0010H\u0016¢\u0006\u0004\b0\u0010/J\u0017\u00103\u001a\u00020\u00102\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u000b2\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020\u0010H\u0016¢\u0006\u0004\b7\u0010/R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u00108R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u00109\u001a\u0004\b:\u0010;R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010<\u001a\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u001a\u0010C\u001a\b\u0012\u0004\u0012\u0002010B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010FR\u0018\u0010H\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010N\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010OR\u0016\u0010Q\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010OR\u0016\u0010R\u001a\u00020M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010OR\u0016\u0010-\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010SR\u0016\u0010U\u001a\u00020T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010VR\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010XR\u0016\u0010Y\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010XR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010ZR\u0018\u0010\\\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010_\u001a\u00020^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010SR\u0018\u0010\u0014\u001a\u00020\u0013*\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bb\u0010c¨\u0006e"}, d2 = {"Lcoil/drawable/MovieDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroidx/vectordrawable/graphics/drawable/d;", "Landroid/graphics/Movie;", "movie", "Landroid/graphics/Bitmap$Config;", "config", "Lcoil/size/Scale;", "scale", "<init>", "(Landroid/graphics/Movie;Landroid/graphics/Bitmap$Config;Lcoil/size/Scale;)V", "", "updateFrameTime", "()Z", "Landroid/graphics/Canvas;", "canvas", "Lx6/t0;", "drawFrame", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Rect;", "bounds", "updateBounds", "(Landroid/graphics/Rect;)V", "draw", "", "repeatCount", "setRepeatCount", "(I)V", "getRepeatCount", "()I", "Lcoil/transform/AnimatedTransformation;", "animatedTransformation", "setAnimatedTransformation", "(Lcoil/transform/AnimatedTransformation;)V", "getAnimatedTransformation", "()Lcoil/transform/AnimatedTransformation;", "alpha", "setAlpha", "getOpacity", "Landroid/graphics/ColorFilter;", "colorFilter", "setColorFilter", "(Landroid/graphics/ColorFilter;)V", "getIntrinsicWidth", "getIntrinsicHeight", "isRunning", TtmlNode.START, "()V", "stop", "Landroidx/vectordrawable/graphics/drawable/c;", "callback", "registerAnimationCallback", "(Landroidx/vectordrawable/graphics/drawable/c;)V", "unregisterAnimationCallback", "(Landroidx/vectordrawable/graphics/drawable/c;)Z", "clearAnimationCallbacks", "Landroid/graphics/Movie;", "Landroid/graphics/Bitmap$Config;", "getConfig", "()Landroid/graphics/Bitmap$Config;", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "Landroid/graphics/Paint;", "paint", "Landroid/graphics/Paint;", "", "callbacks", "Ljava/util/List;", "currentBounds", "Landroid/graphics/Rect;", "tempCanvasBounds", "softwareCanvas", "Landroid/graphics/Canvas;", "Landroid/graphics/Bitmap;", "softwareBitmap", "Landroid/graphics/Bitmap;", "", "softwareScale", "F", "hardwareScale", "hardwareDx", "hardwareDy", "Z", "", "startTimeMillis", "J", "frameTimeMillis", "I", "loopIteration", "Lcoil/transform/AnimatedTransformation;", "Landroid/graphics/Picture;", "animatedTransformationPicture", "Landroid/graphics/Picture;", "Lcoil/transform/PixelOpacity;", "pixelOpacity", "Lcoil/transform/PixelOpacity;", "isSoftwareScalingEnabled", "getBounds", "(Landroid/graphics/Canvas;)Landroid/graphics/Rect;", "Companion", "coil-gif_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MovieDrawable extends Drawable implements d {
    public static final int REPEAT_INFINITE = -1;
    private AnimatedTransformation animatedTransformation;
    private Picture animatedTransformationPicture;
    private final List<c> callbacks;
    private final Bitmap.Config config;
    private final Rect currentBounds;
    private long frameTimeMillis;
    private float hardwareDx;
    private float hardwareDy;
    private float hardwareScale;
    private boolean isRunning;
    private boolean isSoftwareScalingEnabled;
    private int loopIteration;
    private final Movie movie;
    private final Paint paint;
    private PixelOpacity pixelOpacity;
    private int repeatCount;
    private final Scale scale;
    private Bitmap softwareBitmap;
    private Canvas softwareCanvas;
    private float softwareScale;
    private long startTimeMillis;
    private final Rect tempCanvasBounds;

    public MovieDrawable(Movie movie) {
        this(movie, null, null, 6, null);
    }

    private final void drawFrame(Canvas canvas) {
        Canvas canvas2 = this.softwareCanvas;
        Bitmap bitmap = this.softwareBitmap;
        if (canvas2 == null || bitmap == null) {
            return;
        }
        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        int iSave = canvas2.save();
        try {
            float f10 = this.softwareScale;
            canvas2.scale(f10, f10);
            this.movie.draw(canvas2, 0.0f, 0.0f, this.paint);
            Picture picture = this.animatedTransformationPicture;
            if (picture != null) {
                picture.draw(canvas2);
            }
            canvas2.restoreToCount(iSave);
            int iSave2 = canvas.save();
            try {
                canvas.translate(this.hardwareDx, this.hardwareDy);
                float f11 = this.hardwareScale;
                canvas.scale(f11, f11);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.paint);
            } finally {
                canvas.restoreToCount(iSave2);
            }
        } catch (Throwable th) {
            canvas2.restoreToCount(iSave);
            throw th;
        }
    }

    private final Rect getBounds(Canvas canvas) {
        Rect rect = this.tempCanvasBounds;
        rect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        return rect;
    }

    private final void updateBounds(Rect bounds) {
        if (p.a(this.currentBounds, bounds)) {
            return;
        }
        this.currentBounds.set(bounds);
        int iWidth = bounds.width();
        int iHeight = bounds.height();
        int iWidth2 = this.movie.width();
        int iHeight2 = this.movie.height();
        if (iWidth2 <= 0 || iHeight2 <= 0) {
            return;
        }
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(iWidth2, iHeight2, iWidth, iHeight, this.scale);
        if (!this.isSoftwareScalingEnabled && dComputeSizeMultiplier > 1.0d) {
            dComputeSizeMultiplier = 1.0d;
        }
        float f10 = (float) dComputeSizeMultiplier;
        this.softwareScale = f10;
        int i10 = (int) (iWidth2 * f10);
        int i11 = (int) (f10 * iHeight2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i10, i11, this.config);
        Bitmap bitmap = this.softwareBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.softwareBitmap = bitmapCreateBitmap;
        this.softwareCanvas = new Canvas(bitmapCreateBitmap);
        if (this.isSoftwareScalingEnabled) {
            this.hardwareScale = 1.0f;
            this.hardwareDx = 0.0f;
            this.hardwareDy = 0.0f;
            return;
        }
        float fComputeSizeMultiplier = (float) DecodeUtils.computeSizeMultiplier(i10, i11, iWidth, iHeight, this.scale);
        this.hardwareScale = fComputeSizeMultiplier;
        float f11 = iWidth - (i10 * fComputeSizeMultiplier);
        float f12 = 2;
        this.hardwareDx = (f11 / f12) + bounds.left;
        this.hardwareDy = ((iHeight - (fComputeSizeMultiplier * i11)) / f12) + bounds.top;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean updateFrameTime() {
        boolean z;
        int iDuration = this.movie.duration();
        if (iDuration == 0) {
            z = 0;
        } else {
            if (this.isRunning) {
                this.frameTimeMillis = SystemClock.uptimeMillis();
            }
            int i10 = (int) (this.frameTimeMillis - this.startTimeMillis);
            int i11 = i10 / iDuration;
            this.loopIteration = i11;
            int i12 = this.repeatCount;
            i = (i12 == -1 || i11 <= i12) ? 1 : 0;
            if (i != 0) {
                iDuration = i10 - (i11 * iDuration);
            }
            int i13 = i;
            i = iDuration;
            z = i13;
        }
        this.movie.setTime(i);
        return z;
    }

    public void clearAnimationCallbacks() {
        this.callbacks.clear();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean zUpdateFrameTime = updateFrameTime();
        if (this.isSoftwareScalingEnabled) {
            updateBounds(getBounds(canvas));
            int iSave = canvas.save();
            try {
                float f10 = 1 / this.softwareScale;
                canvas.scale(f10, f10);
                drawFrame(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        } else {
            updateBounds(getBounds());
            drawFrame(canvas);
        }
        if (this.isRunning && zUpdateFrameTime) {
            invalidateSelf();
        } else {
            stop();
        }
    }

    public final AnimatedTransformation getAnimatedTransformation() {
        return this.animatedTransformation;
    }

    public final Bitmap.Config getConfig() {
        return this.config;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.movie.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.movie.width();
    }

    @Override // android.graphics.drawable.Drawable
    @e
    public int getOpacity() {
        if (this.paint.getAlpha() != 255) {
            return -3;
        }
        PixelOpacity pixelOpacity = this.pixelOpacity;
        if (pixelOpacity != PixelOpacity.OPAQUE) {
            return (pixelOpacity == PixelOpacity.UNCHANGED && this.movie.isOpaque()) ? -1 : -3;
        }
        return -1;
    }

    public final int getRepeatCount() {
        return this.repeatCount;
    }

    public final Scale getScale() {
        return this.scale;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.isRunning;
    }

    public void registerAnimationCallback(c callback) {
        this.callbacks.add(callback);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (alpha < 0 || alpha >= 256) {
            throw new IllegalArgumentException(a0.c.i(alpha, "Invalid alpha: ").toString());
        }
        this.paint.setAlpha(alpha);
    }

    public final void setAnimatedTransformation(AnimatedTransformation animatedTransformation) {
        this.animatedTransformation = animatedTransformation;
        if (animatedTransformation == null || this.movie.width() <= 0 || this.movie.height() <= 0) {
            this.animatedTransformationPicture = null;
            this.pixelOpacity = PixelOpacity.UNCHANGED;
            this.isSoftwareScalingEnabled = false;
        } else {
            Picture picture = new Picture();
            this.pixelOpacity = animatedTransformation.transform(picture.beginRecording(this.movie.width(), this.movie.height()));
            picture.endRecording();
            this.animatedTransformationPicture = picture;
            this.isSoftwareScalingEnabled = true;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public final void setRepeatCount(int repeatCount) {
        if (repeatCount < -1) {
            throw new IllegalArgumentException(a0.c.i(repeatCount, "Invalid repeatCount: ").toString());
        }
        this.repeatCount = repeatCount;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        this.loopIteration = 0;
        this.startTimeMillis = SystemClock.uptimeMillis();
        List<c> list = this.callbacks;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).onAnimationStart(this);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            List<c> list = this.callbacks;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                list.get(i10).onAnimationEnd(this);
            }
        }
    }

    public boolean unregisterAnimationCallback(c callback) {
        return this.callbacks.remove(callback);
    }

    public MovieDrawable(Movie movie, Bitmap.Config config) {
        this(movie, config, null, 4, null);
    }

    public /* synthetic */ MovieDrawable(Movie movie, Bitmap.Config config, Scale scale, int i10, h hVar) {
        this(movie, (i10 & 2) != 0 ? Bitmap.Config.ARGB_8888 : config, (i10 & 4) != 0 ? Scale.FIT : scale);
    }

    public MovieDrawable(Movie movie, Bitmap.Config config, Scale scale) {
        this.movie = movie;
        this.config = config;
        this.scale = scale;
        this.paint = new Paint(3);
        this.callbacks = new ArrayList();
        this.currentBounds = new Rect();
        this.tempCanvasBounds = new Rect();
        this.softwareScale = 1.0f;
        this.hardwareScale = 1.0f;
        this.repeatCount = -1;
        this.pixelOpacity = PixelOpacity.UNCHANGED;
        if (GifUtils.isHardware(config)) {
            throw new IllegalArgumentException("Bitmap config must not be hardware.");
        }
    }
}
