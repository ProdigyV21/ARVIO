package coil.request;

import android.content.Collections;
import android.content.Context;
import android.content.Requests;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Decoder;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Dimension;
import android.view.DisplaySizeResolver;
import android.view.Precision;
import android.view.Scale;
import android.view.Size;
import android.view.SizeResolver;
import android.view.SizeResolvers;
import android.view.Sizes;
import android.view.View;
import android.view.ViewSizeResolver;
import android.view.ViewSizeResolvers;
import android.widget.ImageView;
import androidx.compose.material3.d;
import androidx.lifecycle.r;
import androidx.lifecycle.y;
import coil.fetch.Fetcher;
import coil.memory.MemoryCache;
import coil.request.Parameters;
import coil.target.ImageViewTarget;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.transform.Transformation;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import gb.a0;
import gb.z;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ka.f0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.e;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bd\u0018\u00002\u00020\u0001:\u0004©\u0001ª\u0001B÷\u0002\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u001c\u0010\u0016\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0018\u00010\u0013\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010$\u001a\u00020\"\u0012\u0006\u0010%\u001a\u00020\"\u0012\u0006\u0010&\u001a\u00020\"\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010)\u001a\u00020'\u0012\u0006\u0010*\u001a\u00020'\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010-\u001a\u00020+\u0012\u0006\u0010.\u001a\u00020+\u0012\u0006\u0010/\u001a\u00020+\u0012\u0006\u00101\u001a\u000200\u0012\u0006\u00103\u001a\u000202\u0012\u0006\u00105\u001a\u000204\u0012\u0006\u00107\u001a\u000206\u0012\b\u00108\u001a\u0004\u0018\u00010\t\u0012\b\u0010:\u001a\u0004\u0018\u000109\u0012\b\u0010<\u001a\u0004\u0018\u00010;\u0012\b\u0010=\u001a\u0004\u0018\u000109\u0012\b\u0010>\u001a\u0004\u0018\u00010;\u0012\b\u0010?\u001a\u0004\u0018\u000109\u0012\b\u0010@\u001a\u0004\u0018\u00010;\u0012\u0006\u0010B\u001a\u00020A\u0012\u0006\u0010D\u001a\u00020C¢\u0006\u0004\bE\u0010FJ\u0019\u0010H\u001a\u00020G2\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\bH\u0010IJ\u001a\u0010K\u001a\u00020\"2\b\u0010J\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u000209H\u0016¢\u0006\u0004\bM\u0010NR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010O\u001a\u0004\bP\u0010QR\u0017\u0010\u0004\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010R\u001a\u0004\bS\u0010TR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010U\u001a\u0004\bV\u0010WR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010X\u001a\u0004\bY\u0010ZR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010[\u001a\u0004\b\\\u0010]R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010^\u001a\u0004\b_\u0010`R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010a\u001a\u0004\bb\u0010cR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010d\u001a\u0004\be\u0010fR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010g\u001a\u0004\bh\u0010iR-\u0010\u0016\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0016\u0010j\u001a\u0004\bk\u0010lR\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010m\u001a\u0004\bn\u0010oR\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0006¢\u0006\f\n\u0004\b\u001b\u0010p\u001a\u0004\bq\u0010rR\u0017\u0010\u001d\u001a\u00020\u001c8\u0006¢\u0006\f\n\u0004\b\u001d\u0010s\u001a\u0004\bt\u0010uR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010v\u001a\u0004\bw\u0010xR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010y\u001a\u0004\bz\u0010{R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010|\u001a\u0004\b}\u0010~R\u0017\u0010$\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b$\u0010|\u001a\u0004\b\u007f\u0010~R\u0018\u0010%\u001a\u00020\"8\u0006¢\u0006\r\n\u0004\b%\u0010|\u001a\u0005\b\u0080\u0001\u0010~R\u0018\u0010&\u001a\u00020\"8\u0006¢\u0006\r\n\u0004\b&\u0010|\u001a\u0005\b\u0081\u0001\u0010~R\u001a\u0010(\u001a\u00020'8\u0006¢\u0006\u000f\n\u0005\b(\u0010\u0082\u0001\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u001a\u0010)\u001a\u00020'8\u0006¢\u0006\u000f\n\u0005\b)\u0010\u0082\u0001\u001a\u0006\b\u0085\u0001\u0010\u0084\u0001R\u001a\u0010*\u001a\u00020'8\u0006¢\u0006\u000f\n\u0005\b*\u0010\u0082\u0001\u001a\u0006\b\u0086\u0001\u0010\u0084\u0001R\u001a\u0010,\u001a\u00020+8\u0006¢\u0006\u000f\n\u0005\b,\u0010\u0087\u0001\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001a\u0010-\u001a\u00020+8\u0006¢\u0006\u000f\n\u0005\b-\u0010\u0087\u0001\u001a\u0006\b\u008a\u0001\u0010\u0089\u0001R\u001a\u0010.\u001a\u00020+8\u0006¢\u0006\u000f\n\u0005\b.\u0010\u0087\u0001\u001a\u0006\b\u008b\u0001\u0010\u0089\u0001R\u001a\u0010/\u001a\u00020+8\u0006¢\u0006\u000f\n\u0005\b/\u0010\u0087\u0001\u001a\u0006\b\u008c\u0001\u0010\u0089\u0001R\u001a\u00101\u001a\u0002008\u0006¢\u0006\u000f\n\u0005\b1\u0010\u008d\u0001\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u001a\u00103\u001a\u0002028\u0006¢\u0006\u000f\n\u0005\b3\u0010\u0090\u0001\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R\u001a\u00105\u001a\u0002048\u0006¢\u0006\u000f\n\u0005\b5\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u001a\u00107\u001a\u0002068\u0006¢\u0006\u000f\n\u0005\b7\u0010\u0096\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001a\u00108\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\r\n\u0004\b8\u0010[\u001a\u0005\b\u0099\u0001\u0010]R\u0017\u0010:\u001a\u0004\u0018\u0001098\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b:\u0010\u009a\u0001R\u0017\u0010<\u001a\u0004\u0018\u00010;8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b<\u0010\u009b\u0001R\u0017\u0010=\u001a\u0004\u0018\u0001098\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b=\u0010\u009a\u0001R\u0017\u0010>\u001a\u0004\u0018\u00010;8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b>\u0010\u009b\u0001R\u0017\u0010?\u001a\u0004\u0018\u0001098\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b?\u0010\u009a\u0001R\u0017\u0010@\u001a\u0004\u0018\u00010;8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b@\u0010\u009b\u0001R\u001a\u0010B\u001a\u00020A8\u0006¢\u0006\u000f\n\u0005\bB\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u001a\u0010D\u001a\u00020C8\u0006¢\u0006\u000f\n\u0005\bD\u0010\u009f\u0001\u001a\u0006\b \u0001\u0010¡\u0001R\u0016\u0010¤\u0001\u001a\u0004\u0018\u00010;8F¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0016\u0010¦\u0001\u001a\u0004\u0018\u00010;8F¢\u0006\b\u001a\u0006\b¥\u0001\u0010£\u0001R\u0016\u0010¨\u0001\u001a\u0004\u0018\u00010;8F¢\u0006\b\u001a\u0006\b§\u0001\u0010£\u0001¨\u0006«\u0001"}, d2 = {"Lcoil/request/ImageRequest;", "", "Landroid/content/Context;", "context", "data", "Lcoil/target/Target;", "target", "Lcoil/request/ImageRequest$Listener;", "listener", "Lcoil/memory/MemoryCache$Key;", "memoryCacheKey", "", "diskCacheKey", "Landroid/graphics/Bitmap$Config;", "bitmapConfig", "Landroid/graphics/ColorSpace;", "colorSpace", "Lcoil/size/Precision;", "precision", "Lx6/x;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/lang/Class;", "fetcherFactory", "Lcoil/decode/Decoder$Factory;", "decoderFactory", "", "Lcoil/transform/Transformation;", "transformations", "Lcoil/transition/Transition$Factory;", "transitionFactory", "Lgb/a0;", "headers", "Lcoil/request/Tags;", "tags", "", "allowConversionToBitmap", "allowHardware", "allowRgb565", "premultipliedAlpha", "Lcoil/request/CachePolicy;", "memoryCachePolicy", "diskCachePolicy", "networkCachePolicy", "Lka/f0;", "interceptorDispatcher", "fetcherDispatcher", "decoderDispatcher", "transformationDispatcher", "Landroidx/lifecycle/r;", "lifecycle", "Lcoil/size/SizeResolver;", "sizeResolver", "Lcoil/size/Scale;", "scale", "Lcoil/request/Parameters;", "parameters", "placeholderMemoryCacheKey", "", "placeholderResId", "Landroid/graphics/drawable/Drawable;", "placeholderDrawable", "errorResId", "errorDrawable", "fallbackResId", "fallbackDrawable", "Lcoil/request/DefinedRequestOptions;", "defined", "Lcoil/request/DefaultRequestOptions;", "defaults", "<init>", "(Landroid/content/Context;Ljava/lang/Object;Lcoil/target/Target;Lcoil/request/ImageRequest$Listener;Lcoil/memory/MemoryCache$Key;Ljava/lang/String;Landroid/graphics/Bitmap$Config;Landroid/graphics/ColorSpace;Lcoil/size/Precision;Lx6/x;Lcoil/decode/Decoder$Factory;Ljava/util/List;Lcoil/transition/Transition$Factory;Lgb/a0;Lcoil/request/Tags;ZZZZLcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lka/f0;Lka/f0;Lka/f0;Lka/f0;Landroidx/lifecycle/r;Lcoil/size/SizeResolver;Lcoil/size/Scale;Lcoil/request/Parameters;Lcoil/memory/MemoryCache$Key;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;Lcoil/request/DefinedRequestOptions;Lcoil/request/DefaultRequestOptions;)V", "Lcoil/request/ImageRequest$Builder;", "newBuilder", "(Landroid/content/Context;)Lcoil/request/ImageRequest$Builder;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Ljava/lang/Object;", "getData", "()Ljava/lang/Object;", "Lcoil/target/Target;", "getTarget", "()Lcoil/target/Target;", "Lcoil/request/ImageRequest$Listener;", "getListener", "()Lcoil/request/ImageRequest$Listener;", "Lcoil/memory/MemoryCache$Key;", "getMemoryCacheKey", "()Lcoil/memory/MemoryCache$Key;", "Ljava/lang/String;", "getDiskCacheKey", "()Ljava/lang/String;", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "Landroid/graphics/ColorSpace;", "getColorSpace", "()Landroid/graphics/ColorSpace;", "Lcoil/size/Precision;", "getPrecision", "()Lcoil/size/Precision;", "Lx6/x;", "getFetcherFactory", "()Lx6/x;", "Lcoil/decode/Decoder$Factory;", "getDecoderFactory", "()Lcoil/decode/Decoder$Factory;", "Ljava/util/List;", "getTransformations", "()Ljava/util/List;", "Lcoil/transition/Transition$Factory;", "getTransitionFactory", "()Lcoil/transition/Transition$Factory;", "Lgb/a0;", "getHeaders", "()Lgb/a0;", "Lcoil/request/Tags;", "getTags", "()Lcoil/request/Tags;", "Z", "getAllowConversionToBitmap", "()Z", "getAllowHardware", "getAllowRgb565", "getPremultipliedAlpha", "Lcoil/request/CachePolicy;", "getMemoryCachePolicy", "()Lcoil/request/CachePolicy;", "getDiskCachePolicy", "getNetworkCachePolicy", "Lka/f0;", "getInterceptorDispatcher", "()Lka/f0;", "getFetcherDispatcher", "getDecoderDispatcher", "getTransformationDispatcher", "Landroidx/lifecycle/r;", "getLifecycle", "()Landroidx/lifecycle/r;", "Lcoil/size/SizeResolver;", "getSizeResolver", "()Lcoil/size/SizeResolver;", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "Lcoil/request/Parameters;", "getParameters", "()Lcoil/request/Parameters;", "getPlaceholderMemoryCacheKey", "Ljava/lang/Integer;", "Landroid/graphics/drawable/Drawable;", "Lcoil/request/DefinedRequestOptions;", "getDefined", "()Lcoil/request/DefinedRequestOptions;", "Lcoil/request/DefaultRequestOptions;", "getDefaults", "()Lcoil/request/DefaultRequestOptions;", "getPlaceholder", "()Landroid/graphics/drawable/Drawable;", "placeholder", "getError", "error", "getFallback", "fallback", "Builder", "Listener", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ImageRequest {
    private final boolean allowConversionToBitmap;
    private final boolean allowHardware;
    private final boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final ColorSpace colorSpace;
    private final Context context;
    private final Object data;
    private final f0 decoderDispatcher;
    private final Decoder.Factory decoderFactory;
    private final DefaultRequestOptions defaults;
    private final DefinedRequestOptions defined;
    private final String diskCacheKey;
    private final CachePolicy diskCachePolicy;
    private final Drawable errorDrawable;
    private final Integer errorResId;
    private final Drawable fallbackDrawable;
    private final Integer fallbackResId;
    private final f0 fetcherDispatcher;
    private final x fetcherFactory;
    private final a0 headers;
    private final f0 interceptorDispatcher;
    private final r lifecycle;
    private final Listener listener;
    private final MemoryCache.Key memoryCacheKey;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Parameters parameters;
    private final Drawable placeholderDrawable;
    private final MemoryCache.Key placeholderMemoryCacheKey;
    private final Integer placeholderResId;
    private final Precision precision;
    private final boolean premultipliedAlpha;
    private final Scale scale;
    private final SizeResolver sizeResolver;
    private final Tags tags;
    private final Target target;
    private final f0 transformationDispatcher;
    private final List<Transformation> transformations;
    private final Transition.Factory transitionFactory;

    @Metadata(d1 = {"\u0000Ø\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\bJ\u0017\u0010\t\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\r\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\r\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0011\u0010\u000eJÎ\u0001\u0010\u001e\u001a\u00020\u00002#\b\u0006\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00150\u00122#\b\u0006\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00150\u001228\b\u0006\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00150\u001828\b\u0006\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00150\u0018H\u0086\b¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\u001e\u0010!J\u0015\u0010#\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b%\u0010$J\u0015\u0010&\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b&\u0010$J\u0015\u0010'\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b'\u0010$J\u0015\u0010(\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b(\u0010$J!\u0010+\u001a\u00020\u00002\u0012\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020*0)\"\u00020*¢\u0006\u0004\b+\u0010,J\u001b\u0010+\u001a\u00020\u00002\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0-¢\u0006\u0004\b+\u0010.J\u0015\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020/¢\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\u00002\u0006\u00104\u001a\u000203H\u0007¢\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u00020\u00002\b\b\u0001\u00107\u001a\u000206¢\u0006\u0004\b7\u00108J!\u00107\u001a\u00020\u00002\b\b\u0001\u00109\u001a\u0002062\b\b\u0001\u0010:\u001a\u000206¢\u0006\u0004\b7\u0010;J\u001d\u00107\u001a\u00020\u00002\u0006\u00109\u001a\u00020<2\u0006\u0010:\u001a\u00020<¢\u0006\u0004\b7\u0010=J\u0015\u00107\u001a\u00020\u00002\u0006\u00107\u001a\u00020>¢\u0006\u0004\b7\u0010?J\u0015\u00107\u001a\u00020\u00002\u0006\u0010A\u001a\u00020@¢\u0006\u0004\b7\u0010BJ\u0015\u0010D\u001a\u00020\u00002\u0006\u0010D\u001a\u00020C¢\u0006\u0004\bD\u0010EJ\u0015\u0010G\u001a\u00020\u00002\u0006\u0010G\u001a\u00020F¢\u0006\u0004\bG\u0010HJ*\u0010L\u001a\u00020\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020\u00012\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000JH\u0086\b¢\u0006\u0004\bL\u0010MJ3\u0010L\u001a\u00020\u0000\"\b\b\u0000\u0010I*\u00020\u00012\f\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000J2\f\u0010O\u001a\b\u0012\u0004\u0012\u00028\u00000N¢\u0006\u0004\bL\u0010PJ\u0015\u0010R\u001a\u00020\u00002\u0006\u0010K\u001a\u00020Q¢\u0006\u0004\bR\u0010SJ\u0015\u0010V\u001a\u00020\u00002\u0006\u0010U\u001a\u00020T¢\u0006\u0004\bV\u0010WJ\u0015\u0010X\u001a\u00020\u00002\u0006\u0010U\u001a\u00020T¢\u0006\u0004\bX\u0010WJ\u0015\u0010Y\u001a\u00020\u00002\u0006\u0010U\u001a\u00020T¢\u0006\u0004\bY\u0010WJ\u0015\u0010Z\u001a\u00020\u00002\u0006\u0010U\u001a\u00020T¢\u0006\u0004\bZ\u0010WJ\u0015\u0010]\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\b]\u0010^J\u0015\u0010_\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\b_\u0010^J\u0015\u0010`\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020[¢\u0006\u0004\b`\u0010^J\u0015\u0010b\u001a\u00020\u00002\u0006\u0010b\u001a\u00020a¢\u0006\u0004\bb\u0010cJ\u001d\u0010e\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010d\u001a\u00020\u000b¢\u0006\u0004\be\u0010fJ\u001d\u0010g\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010d\u001a\u00020\u000b¢\u0006\u0004\bg\u0010fJ\u0015\u0010h\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000b¢\u0006\u0004\bh\u0010\u000eJ&\u0010i\u001a\u00020\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020\u00012\b\u0010i\u001a\u0004\u0018\u00018\u0000H\u0086\b¢\u0006\u0004\bi\u0010\nJ1\u0010i\u001a\u00020\u0000\"\b\b\u0000\u0010I*\u00020\u00012\u000e\u0010O\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000N2\b\u0010i\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\bi\u0010jJ\u0015\u0010l\u001a\u00020\u00002\u0006\u0010l\u001a\u00020k¢\u0006\u0004\bl\u0010mJ\u0017\u0010n\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\bn\u0010\u000eJ\u0017\u0010n\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\bn\u0010\u0010J\u0017\u0010p\u001a\u00020\u00002\b\b\u0001\u0010o\u001a\u000206¢\u0006\u0004\bp\u00108J\u0017\u0010p\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010q¢\u0006\u0004\bp\u0010sJ\u0017\u0010t\u001a\u00020\u00002\b\b\u0001\u0010o\u001a\u000206¢\u0006\u0004\bt\u00108J\u0017\u0010t\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010q¢\u0006\u0004\bt\u0010sJ\u0017\u0010u\u001a\u00020\u00002\b\b\u0001\u0010o\u001a\u000206¢\u0006\u0004\bu\u00108J\u0017\u0010u\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010q¢\u0006\u0004\bu\u0010sJ\u0015\u0010x\u001a\u00020\u00002\u0006\u0010w\u001a\u00020v¢\u0006\u0004\bx\u0010yJ\u0083\u0001\u0010x\u001a\u00020\u00002%\b\u0006\u0010\u0016\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010q¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020\u00150\u00122%\b\u0006\u0010\u001b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010q¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(t\u0012\u0004\u0012\u00020\u00150\u00122#\b\u0006\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110q¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00150\u0012H\u0086\b¢\u0006\u0004\bx\u0010zJ\u0017\u0010x\u001a\u00020\u00002\b\u0010x\u001a\u0004\u0018\u00010{¢\u0006\u0004\bx\u0010|J\u0015\u0010}\u001a\u00020\u00002\u0006\u0010U\u001a\u00020T¢\u0006\u0004\b}\u0010WJ\u0015\u0010}\u001a\u00020\u00002\u0006\u0010~\u001a\u000206¢\u0006\u0004\b}\u00108J\u0019\u0010\u0081\u0001\u001a\u00020\u00002\u0007\u0010\u0080\u0001\u001a\u00020\u007f¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001c\u0010\u0085\u0001\u001a\u00020\u00002\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0083\u0001¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u001c\u0010\u0085\u0001\u001a\u00020\u00002\n\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0087\u0001¢\u0006\u0006\b\u0085\u0001\u0010\u0088\u0001J0\u0010\u0089\u0001\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b2\b\u0010d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0017\u0010\u008b\u0001\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0005\b\u008b\u0001\u0010\u000eJ\u001a\u0010\u008d\u0001\u001a\u00020\u00002\b\u0010\u008d\u0001\u001a\u00030\u008c\u0001¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001a\u0010\u0090\u0001\u001a\u00020\u00002\b\u0010\u0090\u0001\u001a\u00030\u008f\u0001¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J\u0010\u0010\u0092\u0001\u001a\u00020\u0006¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u001c\u0010\u0095\u0001\u001a\u00020\u00002\b\u0010\u0095\u0001\u001a\u00030\u0094\u0001H\u0007¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u001c\u0010\u0098\u0001\u001a\u00020\u00002\b\u0010\u0098\u0001\u001a\u00030\u0097\u0001H\u0007¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001c\u0010\u0080\u0001\u001a\u00020\u00002\b\u0010\u0080\u0001\u001a\u00030\u009a\u0001H\u0007¢\u0006\u0006\b\u0080\u0001\u0010\u009b\u0001J\u0012\u0010\u009c\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u0012\u0010\u009e\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009d\u0001J\u0013\u0010\u009f\u0001\u001a\u00030\u0087\u0001H\u0002¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J\u0012\u0010¡\u0001\u001a\u00020@H\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u0012\u0010£\u0001\u001a\u00020CH\u0002¢\u0006\u0006\b£\u0001\u0010¤\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010¥\u0001R\u001a\u0010\u0090\u0001\u001a\u00030\u008f\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0090\u0001\u0010¦\u0001R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\t\u0010§\u0001R\u0019\u0010x\u001a\u0004\u0018\u00010{8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bx\u0010¨\u0001R\u0019\u0010\u001e\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u001e\u0010©\u0001R\u0019\u0010\r\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\r\u0010ª\u0001R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0011\u0010«\u0001R\u0019\u00101\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b1\u0010¬\u0001R\u0019\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b4\u0010\u00ad\u0001R\u0019\u0010G\u001a\u0004\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bG\u0010®\u0001R.\u0010L\u001a\u0019\u0012\b\u0012\u0006\u0012\u0002\b\u00030J\u0012\b\u0012\u0006\u0012\u0002\b\u00030N\u0018\u00010¯\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bL\u0010°\u0001R\u0019\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bR\u0010±\u0001R\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020*0-8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b+\u0010²\u0001R\u001b\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u007f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0081\u0001\u0010³\u0001R\u001a\u0010b\u001a\u0005\u0018\u00010´\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bb\u0010µ\u0001R*\u0010l\u001a\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030N\u0012\u0004\u0012\u00020\u0001\u0018\u00010¶\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bl\u0010·\u0001R\u0017\u0010V\u001a\u00020T8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bV\u0010¸\u0001R\u0019\u0010X\u001a\u0004\u0018\u00010T8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bX\u0010¹\u0001R\u0019\u0010Y\u001a\u0004\u0018\u00010T8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bY\u0010¹\u0001R\u0017\u0010Z\u001a\u00020T8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bZ\u0010¸\u0001R\u0019\u0010]\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b]\u0010º\u0001R\u0019\u0010_\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b_\u0010º\u0001R\u0019\u0010`\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b`\u0010º\u0001R\u0019\u0010%\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b%\u0010»\u0001R\u0019\u0010&\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b&\u0010»\u0001R\u0019\u0010'\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b'\u0010»\u0001R\u0019\u0010(\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b(\u0010»\u0001R\u001c\u0010\u008d\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008d\u0001\u0010½\u0001R\u0019\u0010n\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bn\u0010ª\u0001R\u001b\u0010¾\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\b¾\u0001\u0010¿\u0001R\u001b\u0010À\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010Á\u0001R\u001b\u0010Â\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\bÂ\u0001\u0010¿\u0001R\u001b\u0010Ã\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010Á\u0001R\u001b\u0010Ä\u0001\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\bÄ\u0001\u0010¿\u0001R\u001b\u0010Å\u0001\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0001\u0010Á\u0001R\u001c\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0087\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0001\u0010Æ\u0001R\u001b\u0010Ç\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÇ\u0001\u0010È\u0001R\u0019\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bD\u0010É\u0001R\u001c\u0010Ê\u0001\u001a\u0005\u0018\u00010\u0087\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÊ\u0001\u0010Æ\u0001R\u001b\u0010Ë\u0001\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bË\u0001\u0010È\u0001R\u001b\u0010Ì\u0001\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÌ\u0001\u0010É\u0001¨\u0006Í\u0001"}, d2 = {"Lcoil/request/ImageRequest$Builder;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcoil/request/ImageRequest;", "request", "(Lcoil/request/ImageRequest;Landroid/content/Context;)V", "data", "(Ljava/lang/Object;)Lcoil/request/ImageRequest$Builder;", "", "key", "memoryCacheKey", "(Ljava/lang/String;)Lcoil/request/ImageRequest$Builder;", "Lcoil/memory/MemoryCache$Key;", "(Lcoil/memory/MemoryCache$Key;)Lcoil/request/ImageRequest$Builder;", "diskCacheKey", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "Lx6/t0;", "onStart", "onCancel", "Lkotlin/Function2;", "Lcoil/request/ErrorResult;", "result", "onError", "Lcoil/request/SuccessResult;", "onSuccess", "listener", "(Lr7/l;Lr7/l;Lr7/p;Lr7/p;)Lcoil/request/ImageRequest$Builder;", "Lcoil/request/ImageRequest$Listener;", "(Lcoil/request/ImageRequest$Listener;)Lcoil/request/ImageRequest$Builder;", "Lka/f0;", "dispatcher", "(Lka/f0;)Lcoil/request/ImageRequest$Builder;", "interceptorDispatcher", "fetcherDispatcher", "decoderDispatcher", "transformationDispatcher", "", "Lcoil/transform/Transformation;", "transformations", "([Lcoil/transform/Transformation;)Lcoil/request/ImageRequest$Builder;", "", "(Ljava/util/List;)Lcoil/request/ImageRequest$Builder;", "Landroid/graphics/Bitmap$Config;", "config", "bitmapConfig", "(Landroid/graphics/Bitmap$Config;)Lcoil/request/ImageRequest$Builder;", "Landroid/graphics/ColorSpace;", "colorSpace", "(Landroid/graphics/ColorSpace;)Lcoil/request/ImageRequest$Builder;", "", ContentDisposition.Parameters.Size, "(I)Lcoil/request/ImageRequest$Builder;", "width", "height", "(II)Lcoil/request/ImageRequest$Builder;", "Lcoil/size/Dimension;", "(Lcoil/size/Dimension;Lcoil/size/Dimension;)Lcoil/request/ImageRequest$Builder;", "Lcoil/size/Size;", "(Lcoil/size/Size;)Lcoil/request/ImageRequest$Builder;", "Lcoil/size/SizeResolver;", "resolver", "(Lcoil/size/SizeResolver;)Lcoil/request/ImageRequest$Builder;", "Lcoil/size/Scale;", "scale", "(Lcoil/size/Scale;)Lcoil/request/ImageRequest$Builder;", "Lcoil/size/Precision;", "precision", "(Lcoil/size/Precision;)Lcoil/request/ImageRequest$Builder;", "T", "Lcoil/fetch/Fetcher$Factory;", "factory", "fetcherFactory", "(Lcoil/fetch/Fetcher$Factory;)Lcoil/request/ImageRequest$Builder;", "Ljava/lang/Class;", LinkHeader.Parameters.Type, "(Lcoil/fetch/Fetcher$Factory;Ljava/lang/Class;)Lcoil/request/ImageRequest$Builder;", "Lcoil/decode/Decoder$Factory;", "decoderFactory", "(Lcoil/decode/Decoder$Factory;)Lcoil/request/ImageRequest$Builder;", "", "enable", "allowConversionToBitmap", "(Z)Lcoil/request/ImageRequest$Builder;", "allowHardware", "allowRgb565", "premultipliedAlpha", "Lcoil/request/CachePolicy;", "policy", "memoryCachePolicy", "(Lcoil/request/CachePolicy;)Lcoil/request/ImageRequest$Builder;", "diskCachePolicy", "networkCachePolicy", "Lgb/a0;", "headers", "(Lgb/a0;)Lcoil/request/ImageRequest$Builder;", "value", "addHeader", "(Ljava/lang/String;Ljava/lang/String;)Lcoil/request/ImageRequest$Builder;", "setHeader", "removeHeader", "tag", "(Ljava/lang/Class;Ljava/lang/Object;)Lcoil/request/ImageRequest$Builder;", "Lcoil/request/Tags;", "tags", "(Lcoil/request/Tags;)Lcoil/request/ImageRequest$Builder;", "placeholderMemoryCacheKey", "drawableResId", "placeholder", "Landroid/graphics/drawable/Drawable;", "drawable", "(Landroid/graphics/drawable/Drawable;)Lcoil/request/ImageRequest$Builder;", "error", "fallback", "Landroid/widget/ImageView;", "imageView", "target", "(Landroid/widget/ImageView;)Lcoil/request/ImageRequest$Builder;", "(Lr7/l;Lr7/l;Lr7/l;)Lcoil/request/ImageRequest$Builder;", "Lcoil/target/Target;", "(Lcoil/target/Target;)Lcoil/request/ImageRequest$Builder;", "crossfade", "durationMillis", "Lcoil/transition/Transition$Factory;", "transition", "transitionFactory", "(Lcoil/transition/Transition$Factory;)Lcoil/request/ImageRequest$Builder;", "Landroidx/lifecycle/y;", "owner", "lifecycle", "(Landroidx/lifecycle/y;)Lcoil/request/ImageRequest$Builder;", "Landroidx/lifecycle/r;", "(Landroidx/lifecycle/r;)Lcoil/request/ImageRequest$Builder;", "setParameter", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Lcoil/request/ImageRequest$Builder;", "removeParameter", "Lcoil/request/Parameters;", "parameters", "(Lcoil/request/Parameters;)Lcoil/request/ImageRequest$Builder;", "Lcoil/request/DefaultRequestOptions;", "defaults", "(Lcoil/request/DefaultRequestOptions;)Lcoil/request/ImageRequest$Builder;", "build", "()Lcoil/request/ImageRequest;", "Lcoil/fetch/Fetcher;", "fetcher", "(Lcoil/fetch/Fetcher;)Lcoil/request/ImageRequest$Builder;", "Lcoil/decode/Decoder;", "decoder", "(Lcoil/decode/Decoder;)Lcoil/request/ImageRequest$Builder;", "Lcoil/transition/Transition;", "(Lcoil/transition/Transition;)Lcoil/request/ImageRequest$Builder;", "resetResolvedValues", "()V", "resetResolvedScale", "resolveLifecycle", "()Landroidx/lifecycle/r;", "resolveSizeResolver", "()Lcoil/size/SizeResolver;", "resolveScale", "()Lcoil/size/Scale;", "Landroid/content/Context;", "Lcoil/request/DefaultRequestOptions;", "Ljava/lang/Object;", "Lcoil/target/Target;", "Lcoil/request/ImageRequest$Listener;", "Lcoil/memory/MemoryCache$Key;", "Ljava/lang/String;", "Landroid/graphics/Bitmap$Config;", "Landroid/graphics/ColorSpace;", "Lcoil/size/Precision;", "Lx6/x;", "Lx6/x;", "Lcoil/decode/Decoder$Factory;", "Ljava/util/List;", "Lcoil/transition/Transition$Factory;", "Lgb/z;", "Lgb/z;", "", "Ljava/util/Map;", "Z", "Ljava/lang/Boolean;", "Lcoil/request/CachePolicy;", "Lka/f0;", "Lcoil/request/Parameters$Builder;", "Lcoil/request/Parameters$Builder;", "placeholderResId", "Ljava/lang/Integer;", "placeholderDrawable", "Landroid/graphics/drawable/Drawable;", "errorResId", "errorDrawable", "fallbackResId", "fallbackDrawable", "Landroidx/lifecycle/r;", "sizeResolver", "Lcoil/size/SizeResolver;", "Lcoil/size/Scale;", "resolvedLifecycle", "resolvedSizeResolver", "resolvedScale", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private boolean allowConversionToBitmap;
        private Boolean allowHardware;
        private Boolean allowRgb565;
        private Bitmap.Config bitmapConfig;
        private ColorSpace colorSpace;
        private final Context context;
        private Object data;
        private f0 decoderDispatcher;
        private Decoder.Factory decoderFactory;
        private DefaultRequestOptions defaults;
        private String diskCacheKey;
        private CachePolicy diskCachePolicy;
        private Drawable errorDrawable;
        private Integer errorResId;
        private Drawable fallbackDrawable;
        private Integer fallbackResId;
        private f0 fetcherDispatcher;
        private x fetcherFactory;
        private z headers;
        private f0 interceptorDispatcher;
        private r lifecycle;
        private Listener listener;
        private MemoryCache.Key memoryCacheKey;
        private CachePolicy memoryCachePolicy;
        private CachePolicy networkCachePolicy;
        private Parameters.Builder parameters;
        private Drawable placeholderDrawable;
        private MemoryCache.Key placeholderMemoryCacheKey;
        private Integer placeholderResId;
        private Precision precision;
        private boolean premultipliedAlpha;
        private r resolvedLifecycle;
        private Scale resolvedScale;
        private SizeResolver resolvedSizeResolver;
        private Scale scale;
        private SizeResolver sizeResolver;
        private Map<Class<?>, Object> tags;
        private Target target;
        private f0 transformationDispatcher;
        private List<? extends Transformation> transformations;
        private Transition.Factory transitionFactory;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(ImageRequest imageRequest) {
            this(imageRequest, null, 2, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Builder listener$default(Builder builder, l lVar, l lVar2, p pVar, p pVar2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                lVar = ImageRequest$Builder$listener$1.INSTANCE;
            }
            if ((i10 & 2) != 0) {
                lVar2 = ImageRequest$Builder$listener$2.INSTANCE;
            }
            if ((i10 & 4) != 0) {
                pVar = ImageRequest$Builder$listener$3.INSTANCE;
            }
            if ((i10 & 8) != 0) {
                pVar2 = ImageRequest$Builder$listener$4.INSTANCE;
            }
            return builder.listener(new ImageRequest$Builder$listener$5(lVar, lVar2, pVar, pVar2));
        }

        private final void resetResolvedScale() {
            this.resolvedScale = null;
        }

        private final void resetResolvedValues() {
            this.resolvedLifecycle = null;
            this.resolvedSizeResolver = null;
            this.resolvedScale = null;
        }

        private final r resolveLifecycle() {
            Target target = this.target;
            r lifecycle = android.content.Context.getLifecycle(target instanceof ViewTarget ? ((ViewTarget) target).getView().getContext() : this.context);
            return lifecycle == null ? GlobalLifecycle.INSTANCE : lifecycle;
        }

        private final Scale resolveScale() {
            View view;
            SizeResolver sizeResolver = this.sizeResolver;
            View view2 = null;
            ViewSizeResolver viewSizeResolver = sizeResolver instanceof ViewSizeResolver ? (ViewSizeResolver) sizeResolver : null;
            if (viewSizeResolver == null || (view = viewSizeResolver.getView()) == null) {
                Target target = this.target;
                ViewTarget viewTarget = target instanceof ViewTarget ? (ViewTarget) target : null;
                if (viewTarget != null) {
                    view2 = viewTarget.getView();
                }
            } else {
                view2 = view;
            }
            return view2 instanceof ImageView ? Utils.getScale((ImageView) view2) : Scale.FIT;
        }

        private final SizeResolver resolveSizeResolver() {
            ImageView.ScaleType scaleType;
            Target target = this.target;
            if (!(target instanceof ViewTarget)) {
                return new DisplaySizeResolver(this.context);
            }
            View view = ((ViewTarget) target).getView();
            return ((view instanceof ImageView) && ((scaleType = ((ImageView) view).getScaleType()) == ImageView.ScaleType.CENTER || scaleType == ImageView.ScaleType.MATRIX)) ? SizeResolvers.create(Size.ORIGINAL) : ViewSizeResolvers.create$default(view, false, 2, null);
        }

        public static /* synthetic */ Builder setParameter$default(Builder builder, String str, Object obj, String str2, int i10, Object obj2) {
            if ((i10 & 4) != 0) {
                str2 = obj != null ? obj.toString() : null;
            }
            return builder.setParameter(str, obj, str2);
        }

        public static /* synthetic */ Builder target$default(Builder builder, l lVar, l lVar2, l lVar3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                lVar = ImageRequest$Builder$target$1.INSTANCE;
            }
            if ((i10 & 2) != 0) {
                lVar2 = ImageRequest$Builder$target$2.INSTANCE;
            }
            if ((i10 & 4) != 0) {
                lVar3 = ImageRequest$Builder$target$3.INSTANCE;
            }
            return builder.target(new ImageRequest$Builder$target$4(lVar, lVar2, lVar3));
        }

        public final Builder addHeader(String name, String value) {
            z zVar = this.headers;
            if (zVar == null) {
                zVar = new z();
                this.headers = zVar;
            }
            zVar.a(name, value);
            return this;
        }

        public final Builder allowConversionToBitmap(boolean enable) {
            this.allowConversionToBitmap = enable;
            return this;
        }

        public final Builder allowHardware(boolean enable) {
            this.allowHardware = Boolean.valueOf(enable);
            return this;
        }

        public final Builder allowRgb565(boolean enable) {
            this.allowRgb565 = Boolean.valueOf(enable);
            return this;
        }

        public final Builder bitmapConfig(Bitmap.Config config) {
            this.bitmapConfig = config;
            return this;
        }

        public final ImageRequest build() {
            Context context = this.context;
            Object obj = this.data;
            if (obj == null) {
                obj = NullRequestData.INSTANCE;
            }
            Object obj2 = obj;
            Target target = this.target;
            Listener listener = this.listener;
            MemoryCache.Key key = this.memoryCacheKey;
            String str = this.diskCacheKey;
            Bitmap.Config bitmapConfig = this.bitmapConfig;
            if (bitmapConfig == null) {
                bitmapConfig = this.defaults.getBitmapConfig();
            }
            Bitmap.Config config = bitmapConfig;
            ColorSpace colorSpace = this.colorSpace;
            Precision precision = this.precision;
            if (precision == null) {
                precision = this.defaults.getPrecision();
            }
            Precision precision2 = precision;
            x xVar = this.fetcherFactory;
            Decoder.Factory factory = this.decoderFactory;
            List<? extends Transformation> list = this.transformations;
            Transition.Factory transitionFactory = this.transitionFactory;
            if (transitionFactory == null) {
                transitionFactory = this.defaults.getTransitionFactory();
            }
            Transition.Factory factory2 = transitionFactory;
            z zVar = this.headers;
            a0 a0VarOrEmpty = Utils.orEmpty(zVar != null ? zVar.e() : null);
            Map<Class<?>, ? extends Object> map = this.tags;
            Tags tagsOrEmpty = Utils.orEmpty(map != null ? Tags.INSTANCE.from(map) : null);
            boolean z = this.allowConversionToBitmap;
            Boolean bool = this.allowHardware;
            boolean zBooleanValue = bool != null ? bool.booleanValue() : this.defaults.getAllowHardware();
            Boolean bool2 = this.allowRgb565;
            boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : this.defaults.getAllowRgb565();
            boolean z5 = this.premultipliedAlpha;
            CachePolicy memoryCachePolicy = this.memoryCachePolicy;
            if (memoryCachePolicy == null) {
                memoryCachePolicy = this.defaults.getMemoryCachePolicy();
            }
            CachePolicy cachePolicy = memoryCachePolicy;
            CachePolicy diskCachePolicy = this.diskCachePolicy;
            if (diskCachePolicy == null) {
                diskCachePolicy = this.defaults.getDiskCachePolicy();
            }
            CachePolicy cachePolicy2 = diskCachePolicy;
            CachePolicy networkCachePolicy = this.networkCachePolicy;
            if (networkCachePolicy == null) {
                networkCachePolicy = this.defaults.getNetworkCachePolicy();
            }
            CachePolicy cachePolicy3 = networkCachePolicy;
            f0 interceptorDispatcher = this.interceptorDispatcher;
            if (interceptorDispatcher == null) {
                interceptorDispatcher = this.defaults.getInterceptorDispatcher();
            }
            f0 f0Var = interceptorDispatcher;
            f0 fetcherDispatcher = this.fetcherDispatcher;
            if (fetcherDispatcher == null) {
                fetcherDispatcher = this.defaults.getFetcherDispatcher();
            }
            f0 f0Var2 = fetcherDispatcher;
            f0 decoderDispatcher = this.decoderDispatcher;
            if (decoderDispatcher == null) {
                decoderDispatcher = this.defaults.getDecoderDispatcher();
            }
            f0 f0Var3 = decoderDispatcher;
            f0 transformationDispatcher = this.transformationDispatcher;
            if (transformationDispatcher == null) {
                transformationDispatcher = this.defaults.getTransformationDispatcher();
            }
            f0 f0Var4 = transformationDispatcher;
            r rVarResolveLifecycle = this.lifecycle;
            if (rVarResolveLifecycle == null && (rVarResolveLifecycle = this.resolvedLifecycle) == null) {
                rVarResolveLifecycle = resolveLifecycle();
            }
            r rVar = rVarResolveLifecycle;
            SizeResolver sizeResolverResolveSizeResolver = this.sizeResolver;
            if (sizeResolverResolveSizeResolver == null && (sizeResolverResolveSizeResolver = this.resolvedSizeResolver) == null) {
                sizeResolverResolveSizeResolver = resolveSizeResolver();
            }
            SizeResolver sizeResolver = sizeResolverResolveSizeResolver;
            Scale scaleResolveScale = this.scale;
            if (scaleResolveScale == null && (scaleResolveScale = this.resolvedScale) == null) {
                scaleResolveScale = resolveScale();
            }
            Scale scale = scaleResolveScale;
            Parameters.Builder builder = this.parameters;
            return new ImageRequest(context, obj2, target, listener, key, str, config, colorSpace, precision2, xVar, factory, list, factory2, a0VarOrEmpty, tagsOrEmpty, z, zBooleanValue, zBooleanValue2, z5, cachePolicy, cachePolicy2, cachePolicy3, f0Var, f0Var2, f0Var3, f0Var4, rVar, sizeResolver, scale, Utils.orEmpty(builder != null ? builder.build() : null), this.placeholderMemoryCacheKey, this.placeholderResId, this.placeholderDrawable, this.errorResId, this.errorDrawable, this.fallbackResId, this.fallbackDrawable, new DefinedRequestOptions(this.lifecycle, this.sizeResolver, this.scale, this.interceptorDispatcher, this.fetcherDispatcher, this.decoderDispatcher, this.transformationDispatcher, this.transitionFactory, this.precision, this.bitmapConfig, this.allowHardware, this.allowRgb565, this.memoryCachePolicy, this.diskCachePolicy, this.networkCachePolicy), this.defaults, null);
        }

        public final Builder colorSpace(ColorSpace colorSpace) {
            this.colorSpace = colorSpace;
            return this;
        }

        public final Builder crossfade(boolean enable) {
            return crossfade(enable ? 100 : 0);
        }

        public final Builder data(Object data) {
            this.data = data;
            return this;
        }

        @e
        public final Builder decoder(Decoder decoder) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder decoderDispatcher(f0 dispatcher) {
            this.decoderDispatcher = dispatcher;
            return this;
        }

        public final Builder decoderFactory(Decoder.Factory factory) {
            this.decoderFactory = factory;
            return this;
        }

        public final Builder defaults(DefaultRequestOptions defaults) {
            this.defaults = defaults;
            resetResolvedScale();
            return this;
        }

        public final Builder diskCacheKey(String key) {
            this.diskCacheKey = key;
            return this;
        }

        public final Builder diskCachePolicy(CachePolicy policy) {
            this.diskCachePolicy = policy;
            return this;
        }

        public final Builder dispatcher(f0 dispatcher) {
            this.fetcherDispatcher = dispatcher;
            this.decoderDispatcher = dispatcher;
            this.transformationDispatcher = dispatcher;
            return this;
        }

        public final Builder error(int drawableResId) {
            this.errorResId = Integer.valueOf(drawableResId);
            this.errorDrawable = null;
            return this;
        }

        public final Builder fallback(int drawableResId) {
            this.fallbackResId = Integer.valueOf(drawableResId);
            this.fallbackDrawable = null;
            return this;
        }

        @e
        public final Builder fetcher(Fetcher fetcher) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder fetcherDispatcher(f0 dispatcher) {
            this.fetcherDispatcher = dispatcher;
            return this;
        }

        public final <T> Builder fetcherFactory(Fetcher.Factory<T> factory, Class<T> type) {
            this.fetcherFactory = new x(factory, type);
            return this;
        }

        public final Builder headers(a0 headers) {
            this.headers = headers.c();
            return this;
        }

        public final Builder interceptorDispatcher(f0 dispatcher) {
            this.interceptorDispatcher = dispatcher;
            return this;
        }

        public final Builder lifecycle(y owner) {
            return lifecycle(owner != null ? owner.getLifecycle() : null);
        }

        public final Builder listener(l<? super ImageRequest, t0> onStart, l<? super ImageRequest, t0> onCancel, p<? super ImageRequest, ? super ErrorResult, t0> onError, p<? super ImageRequest, ? super SuccessResult, t0> onSuccess) {
            return listener(new ImageRequest$Builder$listener$5(onStart, onCancel, onError, onSuccess));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Builder memoryCacheKey(String key) {
            MemoryCache.Key key2 = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            if (key != null) {
                key2 = new MemoryCache.Key(key, objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0);
            }
            return memoryCacheKey(key2);
        }

        public final Builder memoryCachePolicy(CachePolicy policy) {
            this.memoryCachePolicy = policy;
            return this;
        }

        public final Builder networkCachePolicy(CachePolicy policy) {
            this.networkCachePolicy = policy;
            return this;
        }

        public final Builder parameters(Parameters parameters) {
            this.parameters = parameters.newBuilder();
            return this;
        }

        public final Builder placeholder(int drawableResId) {
            this.placeholderResId = Integer.valueOf(drawableResId);
            this.placeholderDrawable = null;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Builder placeholderMemoryCacheKey(String key) {
            MemoryCache.Key key2 = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            if (key != null) {
                key2 = new MemoryCache.Key(key, objArr2 == true ? 1 : 0, 2, objArr == true ? 1 : 0);
            }
            return placeholderMemoryCacheKey(key2);
        }

        public final Builder precision(Precision precision) {
            this.precision = precision;
            return this;
        }

        public final Builder premultipliedAlpha(boolean enable) {
            this.premultipliedAlpha = enable;
            return this;
        }

        public final Builder removeHeader(String name) {
            z zVar = this.headers;
            if (zVar != null) {
                zVar.g(name);
            }
            return this;
        }

        public final Builder removeParameter(String key) {
            Parameters.Builder builder = this.parameters;
            if (builder != null) {
                builder.remove(key);
            }
            return this;
        }

        public final Builder scale(Scale scale) {
            this.scale = scale;
            return this;
        }

        public final Builder setHeader(String name, String value) {
            z zVar = this.headers;
            if (zVar == null) {
                zVar = new z();
                this.headers = zVar;
            }
            zVar.h(name, value);
            return this;
        }

        public final Builder setParameter(String str, Object obj) {
            return setParameter$default(this, str, obj, null, 4, null);
        }

        public final Builder size(int size) {
            return size(size, size);
        }

        public final <T> Builder tag(T tag) {
            kotlin.jvm.internal.p.h();
            throw null;
        }

        public final Builder tags(Tags tags) {
            this.tags = new LinkedHashMap(tags.asMap());
            return this;
        }

        public final Builder target(ImageView imageView) {
            return target(new ImageViewTarget(imageView));
        }

        public final Builder transformationDispatcher(f0 dispatcher) {
            this.transformationDispatcher = dispatcher;
            return this;
        }

        public final Builder transformations(Transformation... transformations) {
            return transformations(kotlin.collections.r.o0(transformations));
        }

        @e
        public final Builder transition(Transition transition) {
            Utils.unsupported();
            throw new KotlinNothingValueException();
        }

        public final Builder transitionFactory(Transition.Factory transition) {
            this.transitionFactory = transition;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
            this.defaults = Requests.getDEFAULT_REQUEST_OPTIONS();
            this.data = null;
            this.target = null;
            this.listener = null;
            this.memoryCacheKey = null;
            this.diskCacheKey = null;
            this.bitmapConfig = null;
            if (Build.VERSION.SDK_INT >= 26) {
                this.colorSpace = null;
            }
            this.precision = null;
            this.fetcherFactory = null;
            this.decoderFactory = null;
            this.transformations = kotlin.collections.z.f19728i;
            this.transitionFactory = null;
            this.headers = null;
            this.tags = null;
            this.allowConversionToBitmap = true;
            this.allowHardware = null;
            this.allowRgb565 = null;
            this.premultipliedAlpha = true;
            this.memoryCachePolicy = null;
            this.diskCachePolicy = null;
            this.networkCachePolicy = null;
            this.interceptorDispatcher = null;
            this.fetcherDispatcher = null;
            this.decoderDispatcher = null;
            this.transformationDispatcher = null;
            this.parameters = null;
            this.placeholderMemoryCacheKey = null;
            this.placeholderResId = null;
            this.placeholderDrawable = null;
            this.errorResId = null;
            this.errorDrawable = null;
            this.fallbackResId = null;
            this.fallbackDrawable = null;
            this.lifecycle = null;
            this.sizeResolver = null;
            this.scale = null;
            this.resolvedLifecycle = null;
            this.resolvedSizeResolver = null;
            this.resolvedScale = null;
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

        public final Builder lifecycle(r lifecycle) {
            this.lifecycle = lifecycle;
            return this;
        }

        public final Builder listener(Listener listener) {
            this.listener = listener;
            return this;
        }

        public final Builder memoryCacheKey(MemoryCache.Key key) {
            this.memoryCacheKey = key;
            return this;
        }

        public final Builder placeholderMemoryCacheKey(MemoryCache.Key key) {
            this.placeholderMemoryCacheKey = key;
            return this;
        }

        public final Builder setParameter(String key, Object value, String memoryCacheKey) {
            Parameters.Builder builder = this.parameters;
            if (builder == null) {
                builder = new Parameters.Builder();
                this.parameters = builder;
            }
            builder.set(key, value, memoryCacheKey);
            return this;
        }

        public final Builder size(int width, int height) {
            return size(Sizes.Size(width, height));
        }

        public final <T> Builder tag(Class<? super T> type, T tag) {
            if (tag == null) {
                Map<Class<?>, Object> map = this.tags;
                if (map != null) {
                    map.remove(type);
                }
                return this;
            }
            Map linkedHashMap = this.tags;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.tags = linkedHashMap;
            }
            linkedHashMap.put(type, type.cast(tag));
            return this;
        }

        public final Builder target(l<? super Drawable, t0> onStart, l<? super Drawable, t0> onError, l<? super Drawable, t0> onSuccess) {
            return target(new ImageRequest$Builder$target$4(onStart, onError, onSuccess));
        }

        public final Builder transformations(List<? extends Transformation> transformations) {
            this.transformations = Collections.toImmutableList(transformations);
            return this;
        }

        public final Builder error(Drawable drawable) {
            this.errorDrawable = drawable;
            this.errorResId = 0;
            return this;
        }

        public final Builder fallback(Drawable drawable) {
            this.fallbackDrawable = drawable;
            this.fallbackResId = 0;
            return this;
        }

        public final <T> Builder fetcherFactory(Fetcher.Factory<T> factory) {
            kotlin.jvm.internal.p.h();
            throw null;
        }

        public final Builder placeholder(Drawable drawable) {
            this.placeholderDrawable = drawable;
            this.placeholderResId = 0;
            return this;
        }

        public final Builder size(Dimension width, Dimension height) {
            return size(new Size(width, height));
        }

        public final Builder target(Target target) {
            this.target = target;
            resetResolvedValues();
            return this;
        }

        public final Builder size(Size size) {
            return size(SizeResolvers.create(size));
        }

        public final Builder size(SizeResolver resolver) {
            this.sizeResolver = resolver;
            resetResolvedValues();
            return this;
        }

        public Builder(ImageRequest imageRequest, Context context) {
            this.context = context;
            this.defaults = imageRequest.getDefaults();
            this.data = imageRequest.getData();
            this.target = imageRequest.getTarget();
            this.listener = imageRequest.getListener();
            this.memoryCacheKey = imageRequest.getMemoryCacheKey();
            this.diskCacheKey = imageRequest.getDiskCacheKey();
            this.bitmapConfig = imageRequest.getDefined().getBitmapConfig();
            if (Build.VERSION.SDK_INT >= 26) {
                this.colorSpace = imageRequest.getColorSpace();
            }
            this.precision = imageRequest.getDefined().getPrecision();
            this.fetcherFactory = imageRequest.getFetcherFactory();
            this.decoderFactory = imageRequest.getDecoderFactory();
            this.transformations = imageRequest.getTransformations();
            this.transitionFactory = imageRequest.getDefined().getTransitionFactory();
            this.headers = imageRequest.getHeaders().c();
            this.tags = new LinkedHashMap(imageRequest.getTags().asMap());
            this.allowConversionToBitmap = imageRequest.getAllowConversionToBitmap();
            this.allowHardware = imageRequest.getDefined().getAllowHardware();
            this.allowRgb565 = imageRequest.getDefined().getAllowRgb565();
            this.premultipliedAlpha = imageRequest.getPremultipliedAlpha();
            this.memoryCachePolicy = imageRequest.getDefined().getMemoryCachePolicy();
            this.diskCachePolicy = imageRequest.getDefined().getDiskCachePolicy();
            this.networkCachePolicy = imageRequest.getDefined().getNetworkCachePolicy();
            this.interceptorDispatcher = imageRequest.getDefined().getInterceptorDispatcher();
            this.fetcherDispatcher = imageRequest.getDefined().getFetcherDispatcher();
            this.decoderDispatcher = imageRequest.getDefined().getDecoderDispatcher();
            this.transformationDispatcher = imageRequest.getDefined().getTransformationDispatcher();
            this.parameters = imageRequest.getParameters().newBuilder();
            this.placeholderMemoryCacheKey = imageRequest.getPlaceholderMemoryCacheKey();
            this.placeholderResId = imageRequest.placeholderResId;
            this.placeholderDrawable = imageRequest.placeholderDrawable;
            this.errorResId = imageRequest.errorResId;
            this.errorDrawable = imageRequest.errorDrawable;
            this.fallbackResId = imageRequest.fallbackResId;
            this.fallbackDrawable = imageRequest.fallbackDrawable;
            this.lifecycle = imageRequest.getDefined().getLifecycle();
            this.sizeResolver = imageRequest.getDefined().getSizeResolver();
            this.scale = imageRequest.getDefined().getScale();
            if (imageRequest.getContext() == context) {
                this.resolvedLifecycle = imageRequest.getLifecycle();
                this.resolvedSizeResolver = imageRequest.getSizeResolver();
                this.resolvedScale = imageRequest.getScale();
            } else {
                this.resolvedLifecycle = null;
                this.resolvedSizeResolver = null;
                this.resolvedScale = null;
            }
        }

        public /* synthetic */ Builder(ImageRequest imageRequest, Context context, int i10, h hVar) {
            this(imageRequest, (i10 & 2) != 0 ? imageRequest.getContext() : context);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0007\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\fH\u0017¢\u0006\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcoil/request/ImageRequest$Listener;", "", "Lcoil/request/ImageRequest;", "request", "Lx6/t0;", "onStart", "(Lcoil/request/ImageRequest;)V", "onCancel", "Lcoil/request/ErrorResult;", "result", "onError", "(Lcoil/request/ImageRequest;Lcoil/request/ErrorResult;)V", "Lcoil/request/SuccessResult;", "onSuccess", "(Lcoil/request/ImageRequest;Lcoil/request/SuccessResult;)V", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Listener {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onCancel(Listener listener, ImageRequest imageRequest) {
                a.a(listener, imageRequest);
            }

            @Deprecated
            public static void onError(Listener listener, ImageRequest imageRequest, ErrorResult errorResult) {
                a.b(listener, imageRequest, errorResult);
            }

            @Deprecated
            public static void onStart(Listener listener, ImageRequest imageRequest) {
                a.c(listener, imageRequest);
            }

            @Deprecated
            public static void onSuccess(Listener listener, ImageRequest imageRequest, SuccessResult successResult) {
                a.d(listener, imageRequest, successResult);
            }
        }

        void onCancel(ImageRequest request);

        void onError(ImageRequest request, ErrorResult result);

        void onStart(ImageRequest request);

        void onSuccess(ImageRequest request, SuccessResult result);
    }

    public /* synthetic */ ImageRequest(Context context, Object obj, Target target, Listener listener, MemoryCache.Key key, String str, Bitmap.Config config, ColorSpace colorSpace, Precision precision, x xVar, Decoder.Factory factory, List list, Transition.Factory factory2, a0 a0Var, Tags tags, boolean z, boolean z5, boolean z10, boolean z11, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, f0 f0Var, f0 f0Var2, f0 f0Var3, f0 f0Var4, r rVar, SizeResolver sizeResolver, Scale scale, Parameters parameters, MemoryCache.Key key2, Integer num, Drawable drawable, Integer num2, Drawable drawable2, Integer num3, Drawable drawable3, DefinedRequestOptions definedRequestOptions, DefaultRequestOptions defaultRequestOptions, h hVar) {
        this(context, obj, target, listener, key, str, config, colorSpace, precision, xVar, factory, list, factory2, a0Var, tags, z, z5, z10, z11, cachePolicy, cachePolicy2, cachePolicy3, f0Var, f0Var2, f0Var3, f0Var4, rVar, sizeResolver, scale, parameters, key2, num, drawable, num2, drawable2, num3, drawable3, definedRequestOptions, defaultRequestOptions);
    }

    public static /* synthetic */ Builder newBuilder$default(ImageRequest imageRequest, Context context, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            context = imageRequest.context;
        }
        return imageRequest.newBuilder(context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageRequest)) {
            return false;
        }
        ImageRequest imageRequest = (ImageRequest) other;
        if (kotlin.jvm.internal.p.a(this.context, imageRequest.context) && kotlin.jvm.internal.p.a(this.data, imageRequest.data) && kotlin.jvm.internal.p.a(this.target, imageRequest.target) && kotlin.jvm.internal.p.a(this.listener, imageRequest.listener) && kotlin.jvm.internal.p.a(this.memoryCacheKey, imageRequest.memoryCacheKey) && kotlin.jvm.internal.p.a(this.diskCacheKey, imageRequest.diskCacheKey) && this.bitmapConfig == imageRequest.bitmapConfig) {
            return (Build.VERSION.SDK_INT < 26 || kotlin.jvm.internal.p.a(this.colorSpace, imageRequest.colorSpace)) && this.precision == imageRequest.precision && kotlin.jvm.internal.p.a(this.fetcherFactory, imageRequest.fetcherFactory) && kotlin.jvm.internal.p.a(this.decoderFactory, imageRequest.decoderFactory) && kotlin.jvm.internal.p.a(this.transformations, imageRequest.transformations) && kotlin.jvm.internal.p.a(this.transitionFactory, imageRequest.transitionFactory) && kotlin.jvm.internal.p.a(this.headers, imageRequest.headers) && kotlin.jvm.internal.p.a(this.tags, imageRequest.tags) && this.allowConversionToBitmap == imageRequest.allowConversionToBitmap && this.allowHardware == imageRequest.allowHardware && this.allowRgb565 == imageRequest.allowRgb565 && this.premultipliedAlpha == imageRequest.premultipliedAlpha && this.memoryCachePolicy == imageRequest.memoryCachePolicy && this.diskCachePolicy == imageRequest.diskCachePolicy && this.networkCachePolicy == imageRequest.networkCachePolicy && kotlin.jvm.internal.p.a(this.interceptorDispatcher, imageRequest.interceptorDispatcher) && kotlin.jvm.internal.p.a(this.fetcherDispatcher, imageRequest.fetcherDispatcher) && kotlin.jvm.internal.p.a(this.decoderDispatcher, imageRequest.decoderDispatcher) && kotlin.jvm.internal.p.a(this.transformationDispatcher, imageRequest.transformationDispatcher) && kotlin.jvm.internal.p.a(this.placeholderMemoryCacheKey, imageRequest.placeholderMemoryCacheKey) && kotlin.jvm.internal.p.a(this.placeholderResId, imageRequest.placeholderResId) && kotlin.jvm.internal.p.a(this.placeholderDrawable, imageRequest.placeholderDrawable) && kotlin.jvm.internal.p.a(this.errorResId, imageRequest.errorResId) && kotlin.jvm.internal.p.a(this.errorDrawable, imageRequest.errorDrawable) && kotlin.jvm.internal.p.a(this.fallbackResId, imageRequest.fallbackResId) && kotlin.jvm.internal.p.a(this.fallbackDrawable, imageRequest.fallbackDrawable) && kotlin.jvm.internal.p.a(this.lifecycle, imageRequest.lifecycle) && kotlin.jvm.internal.p.a(this.sizeResolver, imageRequest.sizeResolver) && this.scale == imageRequest.scale && kotlin.jvm.internal.p.a(this.parameters, imageRequest.parameters) && kotlin.jvm.internal.p.a(this.defined, imageRequest.defined) && kotlin.jvm.internal.p.a(this.defaults, imageRequest.defaults);
        }
        return false;
    }

    public final boolean getAllowConversionToBitmap() {
        return this.allowConversionToBitmap;
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

    public final ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Object getData() {
        return this.data;
    }

    public final f0 getDecoderDispatcher() {
        return this.decoderDispatcher;
    }

    public final Decoder.Factory getDecoderFactory() {
        return this.decoderFactory;
    }

    public final DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    public final DefinedRequestOptions getDefined() {
        return this.defined;
    }

    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final Drawable getError() {
        return Requests.getDrawableCompat(this, this.errorDrawable, this.errorResId, this.defaults.getError());
    }

    public final Drawable getFallback() {
        return Requests.getDrawableCompat(this, this.fallbackDrawable, this.fallbackResId, this.defaults.getFallback());
    }

    public final f0 getFetcherDispatcher() {
        return this.fetcherDispatcher;
    }

    public final x getFetcherFactory() {
        return this.fetcherFactory;
    }

    public final a0 getHeaders() {
        return this.headers;
    }

    public final f0 getInterceptorDispatcher() {
        return this.interceptorDispatcher;
    }

    public final r getLifecycle() {
        return this.lifecycle;
    }

    public final Listener getListener() {
        return this.listener;
    }

    public final MemoryCache.Key getMemoryCacheKey() {
        return this.memoryCacheKey;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public final Parameters getParameters() {
        return this.parameters;
    }

    public final Drawable getPlaceholder() {
        return Requests.getDrawableCompat(this, this.placeholderDrawable, this.placeholderResId, this.defaults.getPlaceholder());
    }

    public final MemoryCache.Key getPlaceholderMemoryCacheKey() {
        return this.placeholderMemoryCacheKey;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final boolean getPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final SizeResolver getSizeResolver() {
        return this.sizeResolver;
    }

    public final Tags getTags() {
        return this.tags;
    }

    public final Target getTarget() {
        return this.target;
    }

    public final f0 getTransformationDispatcher() {
        return this.transformationDispatcher;
    }

    public final List<Transformation> getTransformations() {
        return this.transformations;
    }

    public final Transition.Factory getTransitionFactory() {
        return this.transitionFactory;
    }

    public int hashCode() {
        int iHashCode = (this.data.hashCode() + (this.context.hashCode() * 31)) * 31;
        Target target = this.target;
        int iHashCode2 = (iHashCode + (target != null ? target.hashCode() : 0)) * 31;
        Listener listener = this.listener;
        int iHashCode3 = (iHashCode2 + (listener != null ? listener.hashCode() : 0)) * 31;
        MemoryCache.Key key = this.memoryCacheKey;
        int iHashCode4 = (iHashCode3 + (key != null ? key.hashCode() : 0)) * 31;
        String str = this.diskCacheKey;
        int iHashCode5 = (this.bitmapConfig.hashCode() + ((iHashCode4 + (str != null ? str.hashCode() : 0)) * 31)) * 31;
        ColorSpace colorSpace = this.colorSpace;
        int iHashCode6 = (this.precision.hashCode() + ((iHashCode5 + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31)) * 31;
        x xVar = this.fetcherFactory;
        int iHashCode7 = (iHashCode6 + (xVar != null ? xVar.hashCode() : 0)) * 31;
        Decoder.Factory factory = this.decoderFactory;
        int iHashCode8 = (this.parameters.hashCode() + ((this.scale.hashCode() + ((this.sizeResolver.hashCode() + ((this.lifecycle.hashCode() + ((this.transformationDispatcher.hashCode() + ((this.decoderDispatcher.hashCode() + ((this.fetcherDispatcher.hashCode() + ((this.interceptorDispatcher.hashCode() + ((this.networkCachePolicy.hashCode() + ((this.diskCachePolicy.hashCode() + ((this.memoryCachePolicy.hashCode() + ((((((((((this.tags.hashCode() + ((((this.transitionFactory.hashCode() + d.e(this.transformations, (iHashCode7 + (factory != null ? factory.hashCode() : 0)) * 31, 31)) * 31) + Arrays.hashCode(this.headers.f15615i)) * 31)) * 31) + (this.allowConversionToBitmap ? 1231 : 1237)) * 31) + (this.allowHardware ? 1231 : 1237)) * 31) + (this.allowRgb565 ? 1231 : 1237)) * 31) + (this.premultipliedAlpha ? 1231 : 1237)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        MemoryCache.Key key2 = this.placeholderMemoryCacheKey;
        int iHashCode9 = (iHashCode8 + (key2 != null ? key2.hashCode() : 0)) * 31;
        Integer num = this.placeholderResId;
        int iHashCode10 = (iHashCode9 + (num != null ? num.hashCode() : 0)) * 31;
        Drawable drawable = this.placeholderDrawable;
        int iHashCode11 = (iHashCode10 + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Integer num2 = this.errorResId;
        int iHashCode12 = (iHashCode11 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Drawable drawable2 = this.errorDrawable;
        int iHashCode13 = (iHashCode12 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Integer num3 = this.fallbackResId;
        int iHashCode14 = (iHashCode13 + (num3 != null ? num3.hashCode() : 0)) * 31;
        Drawable drawable3 = this.fallbackDrawable;
        return this.defaults.hashCode() + ((this.defined.hashCode() + ((iHashCode14 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31)) * 31);
    }

    public final Builder newBuilder() {
        return newBuilder$default(this, null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ImageRequest(Context context, Object obj, Target target, Listener listener, MemoryCache.Key key, String str, Bitmap.Config config, ColorSpace colorSpace, Precision precision, x xVar, Decoder.Factory factory, List<? extends Transformation> list, Transition.Factory factory2, a0 a0Var, Tags tags, boolean z, boolean z5, boolean z10, boolean z11, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, f0 f0Var, f0 f0Var2, f0 f0Var3, f0 f0Var4, r rVar, SizeResolver sizeResolver, Scale scale, Parameters parameters, MemoryCache.Key key2, Integer num, Drawable drawable, Integer num2, Drawable drawable2, Integer num3, Drawable drawable3, DefinedRequestOptions definedRequestOptions, DefaultRequestOptions defaultRequestOptions) {
        this.context = context;
        this.data = obj;
        this.target = target;
        this.listener = listener;
        this.memoryCacheKey = key;
        this.diskCacheKey = str;
        this.bitmapConfig = config;
        this.colorSpace = colorSpace;
        this.precision = precision;
        this.fetcherFactory = xVar;
        this.decoderFactory = factory;
        this.transformations = list;
        this.transitionFactory = factory2;
        this.headers = a0Var;
        this.tags = tags;
        this.allowConversionToBitmap = z;
        this.allowHardware = z5;
        this.allowRgb565 = z10;
        this.premultipliedAlpha = z11;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
        this.interceptorDispatcher = f0Var;
        this.fetcherDispatcher = f0Var2;
        this.decoderDispatcher = f0Var3;
        this.transformationDispatcher = f0Var4;
        this.lifecycle = rVar;
        this.sizeResolver = sizeResolver;
        this.scale = scale;
        this.parameters = parameters;
        this.placeholderMemoryCacheKey = key2;
        this.placeholderResId = num;
        this.placeholderDrawable = drawable;
        this.errorResId = num2;
        this.errorDrawable = drawable2;
        this.fallbackResId = num3;
        this.fallbackDrawable = drawable3;
        this.defined = definedRequestOptions;
        this.defaults = defaultRequestOptions;
    }

    public final Builder newBuilder(Context context) {
        return new Builder(this, context);
    }
}
