package coil;

import android.content.Context;
import android.content.ImageLoaderOptions;
import android.content.Logger;
import android.content.Requests;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.ExifOrientationPolicy;
import android.graphics.drawable.Drawable;
import android.view.Precision;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.CachePolicy;
import coil.request.DefaultRequestOptions;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import d7.d;
import gb.h0;
import gb.i;
import ka.f0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.i0;
import x6.q;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001 J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001c8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eø\u0001\u0001\u0082\u0002\n\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006!À\u0006\u0001"}, d2 = {"Lcoil/ImageLoader;", "", "Lcoil/request/ImageRequest;", "request", "Lcoil/request/Disposable;", "enqueue", "(Lcoil/request/ImageRequest;)Lcoil/request/Disposable;", "Lcoil/request/ImageResult;", "execute", "(Lcoil/request/ImageRequest;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "shutdown", "()V", "Lcoil/ImageLoader$Builder;", "newBuilder", "()Lcoil/ImageLoader$Builder;", "Lcoil/request/DefaultRequestOptions;", "getDefaults", "()Lcoil/request/DefaultRequestOptions;", "defaults", "Lcoil/ComponentRegistry;", "getComponents", "()Lcoil/ComponentRegistry;", "components", "Lcoil/memory/MemoryCache;", "getMemoryCache", "()Lcoil/memory/MemoryCache;", "memoryCache", "Lcoil/disk/DiskCache;", "getDiskCache", "()Lcoil/disk/DiskCache;", "diskCache", "Builder", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface ImageLoader {

    @Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\n\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\f¢\u0006\u0004\b\n\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0010\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\f¢\u0006\u0004\b\u0010\u0010\u000eJ)\u0010\u0017\u001a\u00020\u00002\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00002\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001c\u001a\u00020\u00002\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\f¢\u0006\u0004\b\u001c\u0010\u000eJ\u0017\u0010\u001f\u001a\u00020\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010\u001f\u001a\u00020\u00002\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\f¢\u0006\u0004\b\u001f\u0010\u000eJ\u0015\u0010#\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b%\u0010$J\u0015\u0010&\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b&\u0010$J\u0015\u0010'\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b'\u0010$J\u0015\u0010(\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b(\u0010$J\u0015\u0010+\u001a\u00020\u00002\u0006\u0010*\u001a\u00020)¢\u0006\u0004\b+\u0010,J\u0015\u0010/\u001a\u00020\u00002\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100J\u0015\u00103\u001a\u00020\u00002\u0006\u00102\u001a\u000201¢\u0006\u0004\b3\u00104J\u0015\u00107\u001a\u00020\u00002\u0006\u00106\u001a\u000205¢\u0006\u0004\b7\u00108J\u0015\u00109\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b9\u0010$J\u0015\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020)¢\u0006\u0004\b9\u0010,J\u0015\u0010<\u001a\u00020\u00002\u0006\u00106\u001a\u00020;¢\u0006\u0004\b<\u0010=J\u0015\u0010?\u001a\u00020\u00002\u0006\u0010?\u001a\u00020>¢\u0006\u0004\b?\u0010@J\u0015\u0010B\u001a\u00020\u00002\u0006\u0010B\u001a\u00020A¢\u0006\u0004\bB\u0010CJ\u0015\u0010E\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bE\u0010FJ\u0015\u0010G\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bG\u0010FJ\u0015\u0010H\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bH\u0010FJ\u0015\u0010I\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bI\u0010FJ\u0015\u0010J\u001a\u00020\u00002\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bJ\u0010FJ\u0017\u0010L\u001a\u00020\u00002\b\b\u0001\u0010K\u001a\u00020)¢\u0006\u0004\bL\u0010,J\u0017\u0010L\u001a\u00020\u00002\b\u0010N\u001a\u0004\u0018\u00010M¢\u0006\u0004\bL\u0010OJ\u0017\u0010P\u001a\u00020\u00002\b\b\u0001\u0010K\u001a\u00020)¢\u0006\u0004\bP\u0010,J\u0017\u0010P\u001a\u00020\u00002\b\u0010N\u001a\u0004\u0018\u00010M¢\u0006\u0004\bP\u0010OJ\u0017\u0010Q\u001a\u00020\u00002\b\b\u0001\u0010K\u001a\u00020)¢\u0006\u0004\bQ\u0010,J\u0017\u0010Q\u001a\u00020\u00002\b\u0010N\u001a\u0004\u0018\u00010M¢\u0006\u0004\bQ\u0010OJ\u0015\u0010S\u001a\u00020\u00002\u0006\u0010.\u001a\u00020R¢\u0006\u0004\bS\u0010TJ\u0015\u0010U\u001a\u00020\u00002\u0006\u0010.\u001a\u00020R¢\u0006\u0004\bU\u0010TJ\u0015\u0010V\u001a\u00020\u00002\u0006\u0010.\u001a\u00020R¢\u0006\u0004\bV\u0010TJ\u0017\u0010X\u001a\u00020\u00002\b\u0010X\u001a\u0004\u0018\u00010W¢\u0006\u0004\bX\u0010YJ\r\u0010[\u001a\u00020Z¢\u0006\u0004\b[\u0010\\J\u0019\u0010_\u001a\u00020\u00002\b\b\u0001\u0010^\u001a\u00020]H\u0007¢\u0006\u0004\b_\u0010`J\u0017\u0010a\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!H\u0007¢\u0006\u0004\ba\u0010$J\u0017\u0010b\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!H\u0007¢\u0006\u0004\bb\u0010$J(\u0010c\u001a\u00020\u00002\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0004\bc\u0010\u0018J\u0017\u0010c\u001a\u00020\u00002\u0006\u0010d\u001a\u00020\u0019H\u0007¢\u0006\u0004\bc\u0010\u001aJ\u0017\u0010f\u001a\u00020\u00002\u0006\u0010f\u001a\u00020eH\u0007¢\u0006\u0004\bf\u0010gR\u0014\u0010h\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010k\u001a\u00020j8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bk\u0010lR \u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010m8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010nR \u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010m8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010nR\u001e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010m8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010nR\u0018\u00107\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u0010oR\u0018\u0010c\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010pR\u0016\u0010r\u001a\u00020q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR\u0018\u0010X\u001a\u0004\u0018\u00010W8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010t¨\u0006u"}, d2 = {"Lcoil/ImageLoader$Builder;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcoil/RealImageLoader;", "imageLoader", "(Lcoil/RealImageLoader;)V", "Lgb/h0;", "okHttpClient", "(Lgb/h0;)Lcoil/ImageLoader$Builder;", "Lkotlin/Function0;", "initializer", "(Lr7/a;)Lcoil/ImageLoader$Builder;", "Lgb/i$a;", "callFactory", "(Lgb/i$a;)Lcoil/ImageLoader$Builder;", "Lkotlin/Function1;", "Lcoil/ComponentRegistry$Builder;", "Lx6/t0;", "Lx6/n;", "builder", "components", "(Lr7/l;)Lcoil/ImageLoader$Builder;", "Lcoil/ComponentRegistry;", "(Lcoil/ComponentRegistry;)Lcoil/ImageLoader$Builder;", "Lcoil/memory/MemoryCache;", "memoryCache", "(Lcoil/memory/MemoryCache;)Lcoil/ImageLoader$Builder;", "Lcoil/disk/DiskCache;", "diskCache", "(Lcoil/disk/DiskCache;)Lcoil/ImageLoader$Builder;", "", "enable", "allowHardware", "(Z)Lcoil/ImageLoader$Builder;", "allowRgb565", "addLastModifiedToFileCacheKey", "networkObserverEnabled", "respectCacheHeaders", "", "maxParallelism", "bitmapFactoryMaxParallelism", "(I)Lcoil/ImageLoader$Builder;", "Lcoil/decode/ExifOrientationPolicy;", "policy", "bitmapFactoryExifOrientationPolicy", "(Lcoil/decode/ExifOrientationPolicy;)Lcoil/ImageLoader$Builder;", "Lcoil/EventListener;", "listener", "eventListener", "(Lcoil/EventListener;)Lcoil/ImageLoader$Builder;", "Lcoil/EventListener$Factory;", "factory", "eventListenerFactory", "(Lcoil/EventListener$Factory;)Lcoil/ImageLoader$Builder;", "crossfade", "durationMillis", "Lcoil/transition/Transition$Factory;", "transitionFactory", "(Lcoil/transition/Transition$Factory;)Lcoil/ImageLoader$Builder;", "Lcoil/size/Precision;", "precision", "(Lcoil/size/Precision;)Lcoil/ImageLoader$Builder;", "Landroid/graphics/Bitmap$Config;", "bitmapConfig", "(Landroid/graphics/Bitmap$Config;)Lcoil/ImageLoader$Builder;", "Lka/f0;", "dispatcher", "(Lka/f0;)Lcoil/ImageLoader$Builder;", "interceptorDispatcher", "fetcherDispatcher", "decoderDispatcher", "transformationDispatcher", "drawableResId", "placeholder", "Landroid/graphics/drawable/Drawable;", "drawable", "(Landroid/graphics/drawable/Drawable;)Lcoil/ImageLoader$Builder;", "error", "fallback", "Lcoil/request/CachePolicy;", "memoryCachePolicy", "(Lcoil/request/CachePolicy;)Lcoil/ImageLoader$Builder;", "diskCachePolicy", "networkCachePolicy", "Lcoil/util/Logger;", "logger", "(Lcoil/util/Logger;)Lcoil/ImageLoader$Builder;", "Lcoil/ImageLoader;", "build", "()Lcoil/ImageLoader;", "", "percent", "availableMemoryPercentage", "(D)Lcoil/ImageLoader$Builder;", "trackWeakReferences", "launchInterceptorChainOnMainThread", "componentRegistry", "registry", "Lcoil/transition/Transition;", "transition", "(Lcoil/transition/Transition;)Lcoil/ImageLoader$Builder;", "applicationContext", "Landroid/content/Context;", "Lcoil/request/DefaultRequestOptions;", "defaults", "Lcoil/request/DefaultRequestOptions;", "Lx6/s;", "Lx6/s;", "Lcoil/EventListener$Factory;", "Lcoil/ComponentRegistry;", "Lcoil/util/ImageLoaderOptions;", "options", "Lcoil/util/ImageLoaderOptions;", "Lcoil/util/Logger;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private final Context applicationContext;
        private s<? extends i.a> callFactory;
        private ComponentRegistry componentRegistry;
        private DefaultRequestOptions defaults;
        private s<? extends DiskCache> diskCache;
        private EventListener.Factory eventListenerFactory;
        private Logger logger;
        private s<? extends MemoryCache> memoryCache;
        private ImageLoaderOptions options;

        public Builder(Context context) {
            this.applicationContext = context.getApplicationContext();
            this.defaults = Requests.getDEFAULT_REQUEST_OPTIONS();
            this.memoryCache = null;
            this.diskCache = null;
            this.callFactory = null;
            this.eventListenerFactory = null;
            this.componentRegistry = null;
            this.options = new ImageLoaderOptions(false, false, false, 0, null, 31, null);
            this.logger = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final EventListener eventListener$lambda$15(EventListener eventListener, ImageRequest imageRequest) {
            return eventListener;
        }

        public final Builder addLastModifiedToFileCacheKey(boolean enable) {
            this.options = ImageLoaderOptions.copy$default(this.options, enable, false, false, 0, null, 30, null);
            return this;
        }

        public final Builder allowHardware(boolean enable) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : enable, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder allowRgb565(boolean enable) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : enable, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        @e
        public final Builder availableMemoryPercentage(double percent) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder bitmapConfig(Bitmap.Config bitmapConfig) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : bitmapConfig, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder bitmapFactoryExifOrientationPolicy(ExifOrientationPolicy policy) {
            this.options = ImageLoaderOptions.copy$default(this.options, false, false, false, 0, policy, 15, null);
            return this;
        }

        public final Builder bitmapFactoryMaxParallelism(int maxParallelism) {
            if (maxParallelism <= 0) {
                throw new IllegalArgumentException("maxParallelism must be > 0.");
            }
            this.options = ImageLoaderOptions.copy$default(this.options, false, false, false, maxParallelism, null, 23, null);
            return this;
        }

        public final ImageLoader build() {
            Context context = this.applicationContext;
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            s<? extends MemoryCache> i0Var = this.memoryCache;
            if (i0Var == null) {
                i0Var = new i0(new ImageLoader$Builder$build$1(this));
            }
            s<? extends DiskCache> i0Var2 = this.diskCache;
            if (i0Var2 == null) {
                i0Var2 = new i0(new ImageLoader$Builder$build$2(this));
            }
            s<? extends i.a> i0Var3 = this.callFactory;
            if (i0Var3 == null) {
                i0Var3 = new i0(ImageLoader$Builder$build$3.INSTANCE);
            }
            EventListener.Factory factory = this.eventListenerFactory;
            if (factory == null) {
                factory = EventListener.Factory.NONE;
            }
            ComponentRegistry componentRegistry = this.componentRegistry;
            if (componentRegistry == null) {
                componentRegistry = new ComponentRegistry();
            }
            return new RealImageLoader(context, defaultRequestOptions, i0Var, i0Var2, i0Var3, factory, componentRegistry, this.options, this.logger);
        }

        public final Builder callFactory(r7.a<? extends i.a> initializer) {
            this.callFactory = new i0(initializer);
            return this;
        }

        @e
        public final /* synthetic */ Builder componentRegistry(l builder) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final /* synthetic */ Builder components(l<? super ComponentRegistry.Builder, t0> builder) {
            ComponentRegistry.Builder builder2 = new ComponentRegistry.Builder();
            builder.invoke(builder2);
            return components(builder2.build());
        }

        public final Builder crossfade(boolean enable) {
            return crossfade(enable ? 100 : 0);
        }

        public final Builder decoderDispatcher(f0 dispatcher) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : dispatcher, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder diskCache(r7.a<? extends DiskCache> initializer) {
            this.diskCache = new i0(initializer);
            return this;
        }

        public final Builder diskCachePolicy(CachePolicy policy) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : policy, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder dispatcher(f0 dispatcher) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : dispatcher, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : dispatcher, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : dispatcher, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder error(int drawableResId) {
            return error(android.content.Context.getDrawableCompat(this.applicationContext, drawableResId));
        }

        public final Builder eventListener(EventListener listener) {
            return eventListenerFactory(new a3.b(listener, 12));
        }

        public final Builder eventListenerFactory(EventListener.Factory factory) {
            this.eventListenerFactory = factory;
            return this;
        }

        public final Builder fallback(int drawableResId) {
            return fallback(android.content.Context.getDrawableCompat(this.applicationContext, drawableResId));
        }

        public final Builder fetcherDispatcher(f0 dispatcher) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : dispatcher, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder interceptorDispatcher(f0 dispatcher) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : dispatcher, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        @e
        public final Builder launchInterceptorChainOnMainThread(boolean enable) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder logger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public final Builder memoryCache(r7.a<? extends MemoryCache> initializer) {
            this.memoryCache = new i0(initializer);
            return this;
        }

        public final Builder memoryCachePolicy(CachePolicy policy) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : policy, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder networkCachePolicy(CachePolicy policy) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : policy);
            return this;
        }

        public final Builder networkObserverEnabled(boolean enable) {
            this.options = ImageLoaderOptions.copy$default(this.options, false, enable, false, 0, null, 29, null);
            return this;
        }

        public final Builder okHttpClient(h0 okHttpClient) {
            return callFactory(okHttpClient);
        }

        public final Builder placeholder(int drawableResId) {
            return placeholder(android.content.Context.getDrawableCompat(this.applicationContext, drawableResId));
        }

        public final Builder precision(Precision precision) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : precision, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder respectCacheHeaders(boolean enable) {
            this.options = ImageLoaderOptions.copy$default(this.options, false, false, enable, 0, null, 27, null);
            return this;
        }

        @e
        public final Builder trackWeakReferences(boolean enable) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder transformationDispatcher(f0 dispatcher) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : dispatcher, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        @e
        public final Builder transition(Transition transition) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder transitionFactory(Transition.Factory factory) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : factory, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        @e
        public final Builder componentRegistry(ComponentRegistry registry) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder components(ComponentRegistry components) {
            this.componentRegistry = components;
            return this;
        }

        public final Builder crossfade(int durationMillis) {
            Transition.Factory factory;
            if (durationMillis > 0) {
                factory = new CrossfadeTransition.Factory(durationMillis, false, 2, null);
            } else {
                factory = Transition.Factory.NONE;
            }
            transitionFactory(factory);
            return this;
        }

        public final Builder error(Drawable drawable) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : drawable != null ? drawable.mutate() : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder fallback(Drawable drawable) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : drawable != null ? drawable.mutate() : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder okHttpClient(r7.a<? extends h0> initializer) {
            return callFactory(initializer);
        }

        public final Builder placeholder(Drawable drawable) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            this.defaults = defaultRequestOptions.copy((32639 & 1) != 0 ? defaultRequestOptions.interceptorDispatcher : null, (32639 & 2) != 0 ? defaultRequestOptions.fetcherDispatcher : null, (32639 & 4) != 0 ? defaultRequestOptions.decoderDispatcher : null, (32639 & 8) != 0 ? defaultRequestOptions.transformationDispatcher : null, (32639 & 16) != 0 ? defaultRequestOptions.transitionFactory : null, (32639 & 32) != 0 ? defaultRequestOptions.precision : null, (32639 & 64) != 0 ? defaultRequestOptions.bitmapConfig : null, (32639 & 128) != 0 ? defaultRequestOptions.allowHardware : false, (32639 & 256) != 0 ? defaultRequestOptions.allowRgb565 : false, (32639 & 512) != 0 ? defaultRequestOptions.placeholder : drawable != null ? drawable.mutate() : null, (32639 & 1024) != 0 ? defaultRequestOptions.error : null, (32639 & 2048) != 0 ? defaultRequestOptions.fallback : null, (32639 & 4096) != 0 ? defaultRequestOptions.memoryCachePolicy : null, (32639 & 8192) != 0 ? defaultRequestOptions.diskCachePolicy : null, (32639 & 16384) != 0 ? defaultRequestOptions.networkCachePolicy : null);
            return this;
        }

        public final Builder callFactory(i.a callFactory) {
            this.callFactory = new q(callFactory);
            return this;
        }

        public final Builder diskCache(DiskCache diskCache) {
            this.diskCache = new q(diskCache);
            return this;
        }

        public final Builder memoryCache(MemoryCache memoryCache) {
            this.memoryCache = new q(memoryCache);
            return this;
        }

        public Builder(RealImageLoader realImageLoader) {
            this.applicationContext = realImageLoader.getContext().getApplicationContext();
            this.defaults = realImageLoader.getDefaults();
            this.memoryCache = realImageLoader.getMemoryCacheLazy();
            this.diskCache = realImageLoader.getDiskCacheLazy();
            this.callFactory = realImageLoader.getCallFactoryLazy();
            this.eventListenerFactory = realImageLoader.getEventListenerFactory();
            this.componentRegistry = realImageLoader.getComponentRegistry();
            this.options = realImageLoader.getOptions();
            this.logger = realImageLoader.getLogger();
        }
    }

    Disposable enqueue(ImageRequest request);

    Object execute(ImageRequest imageRequest, d<? super ImageResult> dVar);

    ComponentRegistry getComponents();

    DefaultRequestOptions getDefaults();

    DiskCache getDiskCache();

    MemoryCache getMemoryCache();

    Builder newBuilder();

    void shutdown();
}
