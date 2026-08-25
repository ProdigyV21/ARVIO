package coil.request;

import android.graphics.Bitmap;
import android.view.Precision;
import android.view.Scale;
import android.view.SizeResolver;
import androidx.lifecycle.r;
import coil.transition.Transition;
import ka.f0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b$\u0018\u00002\u00020\u0001B\u009d\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001a\u0010\u001bJÁ\u0001\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001f\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b(\u0010)R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010-\u001a\u0004\b.\u0010/R\u0019\u0010\n\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010-\u001a\u0004\b0\u0010/R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000b\u0010-\u001a\u0004\b1\u0010/R\u0019\u0010\f\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\f\u0010-\u001a\u0004\b2\u0010/R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u00103\u001a\u0004\b4\u00105R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u00109\u001a\u0004\b:\u0010;R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010<\u001a\u0004\b=\u0010>R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010<\u001a\u0004\b?\u0010>R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010@\u001a\u0004\bA\u0010BR\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010@\u001a\u0004\bC\u0010BR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0019\u0010@\u001a\u0004\bD\u0010B¨\u0006E"}, d2 = {"Lcoil/request/DefinedRequestOptions;", "", "Landroidx/lifecycle/r;", "lifecycle", "Lcoil/size/SizeResolver;", "sizeResolver", "Lcoil/size/Scale;", "scale", "Lka/f0;", "interceptorDispatcher", "fetcherDispatcher", "decoderDispatcher", "transformationDispatcher", "Lcoil/transition/Transition$Factory;", "transitionFactory", "Lcoil/size/Precision;", "precision", "Landroid/graphics/Bitmap$Config;", "bitmapConfig", "", "allowHardware", "allowRgb565", "Lcoil/request/CachePolicy;", "memoryCachePolicy", "diskCachePolicy", "networkCachePolicy", "<init>", "(Landroidx/lifecycle/r;Lcoil/size/SizeResolver;Lcoil/size/Scale;Lka/f0;Lka/f0;Lka/f0;Lka/f0;Lcoil/transition/Transition$Factory;Lcoil/size/Precision;Landroid/graphics/Bitmap$Config;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)V", "copy", "(Landroidx/lifecycle/r;Lcoil/size/SizeResolver;Lcoil/size/Scale;Lka/f0;Lka/f0;Lka/f0;Lka/f0;Lcoil/transition/Transition$Factory;Lcoil/size/Precision;Landroid/graphics/Bitmap$Config;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)Lcoil/request/DefinedRequestOptions;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/lifecycle/r;", "getLifecycle", "()Landroidx/lifecycle/r;", "Lcoil/size/SizeResolver;", "getSizeResolver", "()Lcoil/size/SizeResolver;", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "Lka/f0;", "getInterceptorDispatcher", "()Lka/f0;", "getFetcherDispatcher", "getDecoderDispatcher", "getTransformationDispatcher", "Lcoil/transition/Transition$Factory;", "getTransitionFactory", "()Lcoil/transition/Transition$Factory;", "Lcoil/size/Precision;", "getPrecision", "()Lcoil/size/Precision;", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "Ljava/lang/Boolean;", "getAllowHardware", "()Ljava/lang/Boolean;", "getAllowRgb565", "Lcoil/request/CachePolicy;", "getMemoryCachePolicy", "()Lcoil/request/CachePolicy;", "getDiskCachePolicy", "getNetworkCachePolicy", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DefinedRequestOptions {
    private final Boolean allowHardware;
    private final Boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final f0 decoderDispatcher;
    private final CachePolicy diskCachePolicy;
    private final f0 fetcherDispatcher;
    private final f0 interceptorDispatcher;
    private final r lifecycle;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Precision precision;
    private final Scale scale;
    private final SizeResolver sizeResolver;
    private final f0 transformationDispatcher;
    private final Transition.Factory transitionFactory;

    public DefinedRequestOptions(r rVar, SizeResolver sizeResolver, Scale scale, f0 f0Var, f0 f0Var2, f0 f0Var3, f0 f0Var4, Transition.Factory factory, Precision precision, Bitmap.Config config, Boolean bool, Boolean bool2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.lifecycle = rVar;
        this.sizeResolver = sizeResolver;
        this.scale = scale;
        this.interceptorDispatcher = f0Var;
        this.fetcherDispatcher = f0Var2;
        this.decoderDispatcher = f0Var3;
        this.transformationDispatcher = f0Var4;
        this.transitionFactory = factory;
        this.precision = precision;
        this.bitmapConfig = config;
        this.allowHardware = bool;
        this.allowRgb565 = bool2;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public final DefinedRequestOptions copy(r lifecycle, SizeResolver sizeResolver, Scale scale, f0 interceptorDispatcher, f0 fetcherDispatcher, f0 decoderDispatcher, f0 transformationDispatcher, Transition.Factory transitionFactory, Precision precision, Bitmap.Config bitmapConfig, Boolean allowHardware, Boolean allowRgb565, CachePolicy memoryCachePolicy, CachePolicy diskCachePolicy, CachePolicy networkCachePolicy) {
        return new DefinedRequestOptions(lifecycle, sizeResolver, scale, interceptorDispatcher, fetcherDispatcher, decoderDispatcher, transformationDispatcher, transitionFactory, precision, bitmapConfig, allowHardware, allowRgb565, memoryCachePolicy, diskCachePolicy, networkCachePolicy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefinedRequestOptions)) {
            return false;
        }
        DefinedRequestOptions definedRequestOptions = (DefinedRequestOptions) other;
        return p.a(this.lifecycle, definedRequestOptions.lifecycle) && p.a(this.sizeResolver, definedRequestOptions.sizeResolver) && this.scale == definedRequestOptions.scale && p.a(this.interceptorDispatcher, definedRequestOptions.interceptorDispatcher) && p.a(this.fetcherDispatcher, definedRequestOptions.fetcherDispatcher) && p.a(this.decoderDispatcher, definedRequestOptions.decoderDispatcher) && p.a(this.transformationDispatcher, definedRequestOptions.transformationDispatcher) && p.a(this.transitionFactory, definedRequestOptions.transitionFactory) && this.precision == definedRequestOptions.precision && this.bitmapConfig == definedRequestOptions.bitmapConfig && p.a(this.allowHardware, definedRequestOptions.allowHardware) && p.a(this.allowRgb565, definedRequestOptions.allowRgb565) && this.memoryCachePolicy == definedRequestOptions.memoryCachePolicy && this.diskCachePolicy == definedRequestOptions.diskCachePolicy && this.networkCachePolicy == definedRequestOptions.networkCachePolicy;
    }

    public final Boolean getAllowHardware() {
        return this.allowHardware;
    }

    public final Boolean getAllowRgb565() {
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

    public final f0 getFetcherDispatcher() {
        return this.fetcherDispatcher;
    }

    public final f0 getInterceptorDispatcher() {
        return this.interceptorDispatcher;
    }

    public final r getLifecycle() {
        return this.lifecycle;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final SizeResolver getSizeResolver() {
        return this.sizeResolver;
    }

    public final f0 getTransformationDispatcher() {
        return this.transformationDispatcher;
    }

    public final Transition.Factory getTransitionFactory() {
        return this.transitionFactory;
    }

    public int hashCode() {
        r rVar = this.lifecycle;
        int iHashCode = (rVar != null ? rVar.hashCode() : 0) * 31;
        SizeResolver sizeResolver = this.sizeResolver;
        int iHashCode2 = (iHashCode + (sizeResolver != null ? sizeResolver.hashCode() : 0)) * 31;
        Scale scale = this.scale;
        int iHashCode3 = (iHashCode2 + (scale != null ? scale.hashCode() : 0)) * 31;
        f0 f0Var = this.interceptorDispatcher;
        int iHashCode4 = (iHashCode3 + (f0Var != null ? f0Var.hashCode() : 0)) * 31;
        f0 f0Var2 = this.fetcherDispatcher;
        int iHashCode5 = (iHashCode4 + (f0Var2 != null ? f0Var2.hashCode() : 0)) * 31;
        f0 f0Var3 = this.decoderDispatcher;
        int iHashCode6 = (iHashCode5 + (f0Var3 != null ? f0Var3.hashCode() : 0)) * 31;
        f0 f0Var4 = this.transformationDispatcher;
        int iHashCode7 = (iHashCode6 + (f0Var4 != null ? f0Var4.hashCode() : 0)) * 31;
        Transition.Factory factory = this.transitionFactory;
        int iHashCode8 = (iHashCode7 + (factory != null ? factory.hashCode() : 0)) * 31;
        Precision precision = this.precision;
        int iHashCode9 = (iHashCode8 + (precision != null ? precision.hashCode() : 0)) * 31;
        Bitmap.Config config = this.bitmapConfig;
        int iHashCode10 = (iHashCode9 + (config != null ? config.hashCode() : 0)) * 31;
        Boolean bool = this.allowHardware;
        int iHashCode11 = (iHashCode10 + (bool != null ? bool.hashCode() : 0)) * 31;
        Boolean bool2 = this.allowRgb565;
        int iHashCode12 = (iHashCode11 + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy = this.memoryCachePolicy;
        int iHashCode13 = (iHashCode12 + (cachePolicy != null ? cachePolicy.hashCode() : 0)) * 31;
        CachePolicy cachePolicy2 = this.diskCachePolicy;
        int iHashCode14 = (iHashCode13 + (cachePolicy2 != null ? cachePolicy2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy3 = this.networkCachePolicy;
        return iHashCode14 + (cachePolicy3 != null ? cachePolicy3.hashCode() : 0);
    }
}
