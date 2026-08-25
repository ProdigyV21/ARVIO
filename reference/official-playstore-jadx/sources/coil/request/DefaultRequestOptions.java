package coil.request;

import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Precision;
import androidx.compose.ui.layout.LayoutKt;
import coil.transition.Transition;
import ka.f0;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import la.d;
import pa.q;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b \u0018\u00002\u00020\u0001B£\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0014¢\u0006\u0004\b\u0018\u0010\u0019J©\u0001\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0014¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001d\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b%\u0010$R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b&\u0010$R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b'\u0010$R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00101\u001a\u0004\b2\u00103R\u0017\u0010\u000f\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000f\u00101\u001a\u0004\b4\u00103R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00105\u001a\u0004\b6\u00107R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u00105\u001a\u0004\b8\u00107R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0013\u00105\u001a\u0004\b9\u00107R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010\u0016\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0016\u0010:\u001a\u0004\b=\u0010<R\u0017\u0010\u0017\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0017\u0010:\u001a\u0004\b>\u0010<¨\u0006?"}, d2 = {"Lcoil/request/DefaultRequestOptions;", "", "Lka/f0;", "interceptorDispatcher", "fetcherDispatcher", "decoderDispatcher", "transformationDispatcher", "Lcoil/transition/Transition$Factory;", "transitionFactory", "Lcoil/size/Precision;", "precision", "Landroid/graphics/Bitmap$Config;", "bitmapConfig", "", "allowHardware", "allowRgb565", "Landroid/graphics/drawable/Drawable;", "placeholder", "error", "fallback", "Lcoil/request/CachePolicy;", "memoryCachePolicy", "diskCachePolicy", "networkCachePolicy", "<init>", "(Lka/f0;Lka/f0;Lka/f0;Lka/f0;Lcoil/transition/Transition$Factory;Lcoil/size/Precision;Landroid/graphics/Bitmap$Config;ZZLandroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)V", "copy", "(Lka/f0;Lka/f0;Lka/f0;Lka/f0;Lcoil/transition/Transition$Factory;Lcoil/size/Precision;Landroid/graphics/Bitmap$Config;ZZLandroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)Lcoil/request/DefaultRequestOptions;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lka/f0;", "getInterceptorDispatcher", "()Lka/f0;", "getFetcherDispatcher", "getDecoderDispatcher", "getTransformationDispatcher", "Lcoil/transition/Transition$Factory;", "getTransitionFactory", "()Lcoil/transition/Transition$Factory;", "Lcoil/size/Precision;", "getPrecision", "()Lcoil/size/Precision;", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "Z", "getAllowHardware", "()Z", "getAllowRgb565", "Landroid/graphics/drawable/Drawable;", "getPlaceholder", "()Landroid/graphics/drawable/Drawable;", "getError", "getFallback", "Lcoil/request/CachePolicy;", "getMemoryCachePolicy", "()Lcoil/request/CachePolicy;", "getDiskCachePolicy", "getNetworkCachePolicy", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DefaultRequestOptions {
    private final boolean allowHardware;
    private final boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final f0 decoderDispatcher;
    private final CachePolicy diskCachePolicy;
    private final Drawable error;
    private final Drawable fallback;
    private final f0 fetcherDispatcher;
    private final f0 interceptorDispatcher;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Drawable placeholder;
    private final Precision precision;
    private final f0 transformationDispatcher;
    private final Transition.Factory transitionFactory;

    public DefaultRequestOptions() {
        this(null, null, null, null, null, null, null, false, false, null, null, null, null, null, null, LayoutKt.LargeDimension, null);
    }

    public final DefaultRequestOptions copy(f0 interceptorDispatcher, f0 fetcherDispatcher, f0 decoderDispatcher, f0 transformationDispatcher, Transition.Factory transitionFactory, Precision precision, Bitmap.Config bitmapConfig, boolean allowHardware, boolean allowRgb565, Drawable placeholder, Drawable error, Drawable fallback, CachePolicy memoryCachePolicy, CachePolicy diskCachePolicy, CachePolicy networkCachePolicy) {
        return new DefaultRequestOptions(interceptorDispatcher, fetcherDispatcher, decoderDispatcher, transformationDispatcher, transitionFactory, precision, bitmapConfig, allowHardware, allowRgb565, placeholder, error, fallback, memoryCachePolicy, diskCachePolicy, networkCachePolicy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultRequestOptions)) {
            return false;
        }
        DefaultRequestOptions defaultRequestOptions = (DefaultRequestOptions) other;
        return p.a(this.interceptorDispatcher, defaultRequestOptions.interceptorDispatcher) && p.a(this.fetcherDispatcher, defaultRequestOptions.fetcherDispatcher) && p.a(this.decoderDispatcher, defaultRequestOptions.decoderDispatcher) && p.a(this.transformationDispatcher, defaultRequestOptions.transformationDispatcher) && p.a(this.transitionFactory, defaultRequestOptions.transitionFactory) && this.precision == defaultRequestOptions.precision && this.bitmapConfig == defaultRequestOptions.bitmapConfig && this.allowHardware == defaultRequestOptions.allowHardware && this.allowRgb565 == defaultRequestOptions.allowRgb565 && p.a(this.placeholder, defaultRequestOptions.placeholder) && p.a(this.error, defaultRequestOptions.error) && p.a(this.fallback, defaultRequestOptions.fallback) && this.memoryCachePolicy == defaultRequestOptions.memoryCachePolicy && this.diskCachePolicy == defaultRequestOptions.diskCachePolicy && this.networkCachePolicy == defaultRequestOptions.networkCachePolicy;
    }

    public final boolean getAllowHardware() {
        return this.allowHardware;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public final f0 getDecoderDispatcher() {
        return this.decoderDispatcher;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final Drawable getError() {
        return this.error;
    }

    public final Drawable getFallback() {
        return this.fallback;
    }

    public final f0 getFetcherDispatcher() {
        return this.fetcherDispatcher;
    }

    public final f0 getInterceptorDispatcher() {
        return this.interceptorDispatcher;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final Drawable getPlaceholder() {
        return this.placeholder;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final f0 getTransformationDispatcher() {
        return this.transformationDispatcher;
    }

    public final Transition.Factory getTransitionFactory() {
        return this.transitionFactory;
    }

    public int hashCode() {
        int iHashCode = (((((this.bitmapConfig.hashCode() + ((this.precision.hashCode() + ((this.transitionFactory.hashCode() + ((this.transformationDispatcher.hashCode() + ((this.decoderDispatcher.hashCode() + ((this.fetcherDispatcher.hashCode() + (this.interceptorDispatcher.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + (this.allowHardware ? 1231 : 1237)) * 31) + (this.allowRgb565 ? 1231 : 1237)) * 31;
        Drawable drawable = this.placeholder;
        int iHashCode2 = (iHashCode + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Drawable drawable2 = this.error;
        int iHashCode3 = (iHashCode2 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Drawable drawable3 = this.fallback;
        return this.networkCachePolicy.hashCode() + ((this.diskCachePolicy.hashCode() + ((this.memoryCachePolicy.hashCode() + ((iHashCode3 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31)) * 31)) * 31);
    }

    public DefaultRequestOptions(f0 f0Var, f0 f0Var2, f0 f0Var3, f0 f0Var4, Transition.Factory factory, Precision precision, Bitmap.Config config, boolean z, boolean z5, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.interceptorDispatcher = f0Var;
        this.fetcherDispatcher = f0Var2;
        this.decoderDispatcher = f0Var3;
        this.transformationDispatcher = f0Var4;
        this.transitionFactory = factory;
        this.precision = precision;
        this.bitmapConfig = config;
        this.allowHardware = z;
        this.allowRgb565 = z5;
        this.placeholder = drawable;
        this.error = drawable2;
        this.fallback = drawable3;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DefaultRequestOptions(f0 f0Var, f0 f0Var2, f0 f0Var3, f0 f0Var4, Transition.Factory factory, Precision precision, Bitmap.Config config, boolean z, boolean z5, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, int i10, h hVar) {
        f0 f0Var5;
        if ((i10 & 1) != 0) {
            x0 x0Var = x0.f19652a;
            f0Var5 = ((d) q.f21294a).f20077n;
        } else {
            f0Var5 = f0Var;
        }
        this(f0Var5, (i10 & 2) != 0 ? x0.f19655d : f0Var2, (i10 & 4) != 0 ? x0.f19655d : f0Var3, (i10 & 8) != 0 ? x0.f19655d : f0Var4, (i10 & 16) != 0 ? Transition.Factory.NONE : factory, (i10 & 32) != 0 ? Precision.AUTOMATIC : precision, (i10 & 64) != 0 ? Utils.getDEFAULT_BITMAP_CONFIG() : config, (i10 & 128) != 0 ? true : z, (i10 & 256) != 0 ? false : z5, (i10 & 512) != 0 ? null : drawable, (i10 & 1024) != 0 ? null : drawable2, (i10 & 2048) == 0 ? drawable3 : null, (i10 & 4096) != 0 ? CachePolicy.ENABLED : cachePolicy, (i10 & 8192) != 0 ? CachePolicy.ENABLED : cachePolicy2, (i10 & 16384) != 0 ? CachePolicy.ENABLED : cachePolicy3);
    }
}
