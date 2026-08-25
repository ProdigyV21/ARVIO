package coil.memory;

import a0.c;
import android.content.Bitmaps;
import android.content.Logger;
import android.content.Requests;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.DataSource;
import android.graphics.DecodeUtils;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Dimension;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import coil.EventListener;
import coil.ImageLoader;
import coil.intercept.EngineInterceptor;
import coil.intercept.Interceptor;
import coil.memory.MemoryCache;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.request.RequestService;
import coil.request.SuccessResult;
import coil.transform.Transformation;
import io.ktor.http.ContentDisposition;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ(\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J5\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001cJ0\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010\u001e\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J&\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u000bJ \u0010(\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006,"}, d2 = {"Lcoil/memory/MemoryCacheService;", "", "imageLoader", "Lcoil/ImageLoader;", "requestService", "Lcoil/request/RequestService;", "logger", "Lcoil/util/Logger;", "(Lcoil/ImageLoader;Lcoil/request/RequestService;Lcoil/util/Logger;)V", "diskCacheKey", "", "Lcoil/memory/MemoryCache$Value;", "getDiskCacheKey", "(Lcoil/memory/MemoryCache$Value;)Ljava/lang/String;", "isSampled", "", "(Lcoil/memory/MemoryCache$Value;)Z", "getCacheValue", "request", "Lcoil/request/ImageRequest;", "cacheKey", "Lcoil/memory/MemoryCache$Key;", ContentDisposition.Parameters.Size, "Lcoil/size/Size;", "scale", "Lcoil/size/Scale;", "isCacheValueValid", "cacheValue", "isCacheValueValid$coil_base_release", "isSizeValid", "newCacheKey", "mappedData", "options", "Lcoil/request/Options;", "eventListener", "Lcoil/EventListener;", "newResult", "Lcoil/request/SuccessResult;", "chain", "Lcoil/intercept/Interceptor$Chain;", "setCacheValue", "result", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MemoryCacheService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String EXTRA_DISK_CACHE_KEY = "coil#disk_cache_key";
    public static final String EXTRA_IS_SAMPLED = "coil#is_sampled";
    public static final String EXTRA_TRANSFORMATION_INDEX = "coil#transformation_";
    public static final String EXTRA_TRANSFORMATION_SIZE = "coil#transformation_size";
    private static final String TAG = "MemoryCacheService";
    private final ImageLoader imageLoader;
    private final Logger logger;
    private final RequestService requestService;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcoil/memory/MemoryCacheService$Companion;", "", "()V", "EXTRA_DISK_CACHE_KEY", "", "getEXTRA_DISK_CACHE_KEY$coil_base_release$annotations", "EXTRA_IS_SAMPLED", "getEXTRA_IS_SAMPLED$coil_base_release$annotations", "EXTRA_TRANSFORMATION_INDEX", "getEXTRA_TRANSFORMATION_INDEX$coil_base_release$annotations", "EXTRA_TRANSFORMATION_SIZE", "getEXTRA_TRANSFORMATION_SIZE$coil_base_release$annotations", "TAG", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getEXTRA_DISK_CACHE_KEY$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getEXTRA_IS_SAMPLED$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getEXTRA_TRANSFORMATION_INDEX$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getEXTRA_TRANSFORMATION_SIZE$coil_base_release$annotations() {
        }

        private Companion() {
        }
    }

    public MemoryCacheService(ImageLoader imageLoader, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.requestService = requestService;
        this.logger = logger;
    }

    private final String getDiskCacheKey(MemoryCache.Value value) {
        Object obj = value.getExtras().get(EXTRA_DISK_CACHE_KEY);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    private final boolean isSampled(MemoryCache.Value value) {
        Object obj = value.getExtras().get(EXTRA_IS_SAMPLED);
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final boolean isSizeValid(ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue, Size size, Scale scale) {
        boolean z;
        String str;
        boolean zIsSampled = isSampled(cacheValue);
        if (Sizes.isOriginal(size)) {
            if (!zIsSampled) {
                return true;
            }
            Logger logger = this.logger;
            if (logger != null && logger.getLevel() <= 3) {
                logger.log(TAG, 3, request.getData() + ": Requested original size, but cached image is sampled.", null);
            }
            return false;
        }
        String str2 = cacheKey.getExtras().get(EXTRA_TRANSFORMATION_SIZE);
        if (str2 != null) {
            return str2.equals(size.toString());
        }
        int width = cacheValue.getBitmap().getWidth();
        int height = cacheValue.getBitmap().getHeight();
        Dimension width2 = size.getWidth();
        int i10 = width2 instanceof Dimension.Pixels ? ((Dimension.Pixels) width2).px : Integer.MAX_VALUE;
        Dimension height2 = size.getHeight();
        int i11 = height2 instanceof Dimension.Pixels ? ((Dimension.Pixels) height2).px : Integer.MAX_VALUE;
        double dComputeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, height, i10, i11, scale);
        boolean allowInexactSize = Requests.getAllowInexactSize(request);
        if (!allowInexactSize) {
            z = false;
            str = TAG;
            if ((!Utils.isMinOrMax(i10) && Math.abs(i10 - width) > 1) || (!Utils.isMinOrMax(i11) && Math.abs(i11 - height) > 1)) {
            }
            return true;
        }
        double d4 = dComputeSizeMultiplier > 1.0d ? 1.0d : dComputeSizeMultiplier;
        z = false;
        str = TAG;
        if (Math.abs(((double) i10) - (((double) width) * d4)) <= 1.0d || Math.abs(((double) i11) - (d4 * ((double) height))) <= 1.0d) {
            return true;
        }
        if (dComputeSizeMultiplier != 1.0d && !allowInexactSize) {
            Logger logger2 = this.logger;
            if (logger2 != null && logger2.getLevel() <= 3) {
                logger2.log(str, 3, request.getData() + ": Cached image's request size (" + width + ", " + height + ") does not exactly match the requested size (" + size.getWidth() + ", " + size.getHeight() + ", " + scale + ").", null);
            }
            return z;
        }
        String str3 = str;
        if (dComputeSizeMultiplier > 1.0d && zIsSampled) {
            Logger logger3 = this.logger;
            if (logger3 != null && logger3.getLevel() <= 3) {
                logger3.log(str3, 3, request.getData() + ": Cached image's request size (" + width + ", " + height + ") is smaller than the requested size (" + size.getWidth() + ", " + size.getHeight() + ", " + scale + ").", null);
            }
            return z;
        }
        return true;
    }

    public final MemoryCache.Value getCacheValue(ImageRequest request, MemoryCache.Key cacheKey, Size size, Scale scale) {
        if (!request.getMemoryCachePolicy().getReadEnabled()) {
            return null;
        }
        MemoryCache memoryCache = this.imageLoader.getMemoryCache();
        MemoryCache.Value value = memoryCache != null ? memoryCache.get(cacheKey) : null;
        if (value == null || !isCacheValueValid$coil_base_release(request, cacheKey, value, size, scale)) {
            return null;
        }
        return value;
    }

    public final boolean isCacheValueValid$coil_base_release(ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue, Size size, Scale scale) {
        if (this.requestService.isConfigValidForHardware(request, Bitmaps.getSafeConfig(cacheValue.getBitmap()))) {
            return isSizeValid(request, cacheKey, cacheValue, size, scale);
        }
        Logger logger = this.logger;
        if (logger == null || logger.getLevel() > 3) {
            return false;
        }
        logger.log(TAG, 3, request.getData() + ": Cached bitmap is hardware-backed, which is incompatible with the request.", null);
        return false;
    }

    public final MemoryCache.Key newCacheKey(ImageRequest request, Object mappedData, Options options, EventListener eventListener) {
        MemoryCache.Key memoryCacheKey = request.getMemoryCacheKey();
        if (memoryCacheKey != null) {
            return memoryCacheKey;
        }
        eventListener.keyStart(request, mappedData);
        String strKey = this.imageLoader.getComponents().key(mappedData, options);
        eventListener.keyEnd(request, strKey);
        if (strKey == null) {
            return null;
        }
        List<Transformation> transformations = request.getTransformations();
        Map<String, String> mapMemoryCacheKeys = request.getParameters().memoryCacheKeys();
        if (transformations.isEmpty() && mapMemoryCacheKeys.isEmpty()) {
            return new MemoryCache.Key(strKey, null, 2, null);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapMemoryCacheKeys);
        if (!transformations.isEmpty()) {
            List<Transformation> transformations2 = request.getTransformations();
            int size = transformations2.size();
            for (int i10 = 0; i10 < size; i10++) {
                linkedHashMap.put(c.i(i10, EXTRA_TRANSFORMATION_INDEX), transformations2.get(i10).getCacheKey());
            }
            linkedHashMap.put(EXTRA_TRANSFORMATION_SIZE, options.getSize().toString());
        }
        return new MemoryCache.Key(strKey, linkedHashMap);
    }

    public final SuccessResult newResult(Interceptor.Chain chain, ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue) {
        return new SuccessResult(new BitmapDrawable(request.getContext().getResources(), cacheValue.getBitmap()), request, DataSource.MEMORY_CACHE, cacheKey, getDiskCacheKey(cacheValue), isSampled(cacheValue), Utils.isPlaceholderCached(chain));
    }

    public final boolean setCacheValue(MemoryCache.Key cacheKey, ImageRequest request, EngineInterceptor.ExecuteResult result) {
        MemoryCache memoryCache;
        Bitmap bitmap;
        if (request.getMemoryCachePolicy().getWriteEnabled() && (memoryCache = this.imageLoader.getMemoryCache()) != null && cacheKey != null) {
            Drawable drawable = result.getDrawable();
            BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
            if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(EXTRA_IS_SAMPLED, Boolean.valueOf(result.getIsSampled()));
                String diskCacheKey = result.getDiskCacheKey();
                if (diskCacheKey != null) {
                    linkedHashMap.put(EXTRA_DISK_CACHE_KEY, diskCacheKey);
                }
                memoryCache.set(cacheKey, new MemoryCache.Value(bitmap, linkedHashMap));
                return true;
            }
        }
        return false;
    }
}
