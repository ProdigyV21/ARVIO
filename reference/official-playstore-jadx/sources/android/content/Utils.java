package android.content;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.DataSource;
import android.graphics.Decoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.view.Dimension;
import android.view.Scale;
import android.view.Size;
import android.view.Sizes;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import androidx.compose.material3.d;
import androidx.core.content.b;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.vectordrawable.graphics.drawable.t;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.base.R;
import coil.disk.DiskCache;
import coil.intercept.Interceptor;
import coil.intercept.RealInterceptorChain;
import coil.memory.MemoryCache;
import coil.request.Parameters;
import coil.request.Tags;
import coil.request.ViewTargetRequestManager;
import gb.a0;
import gb.s0;
import gb.z;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import ka.p0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import r7.a;
import x6.x;

/* JADX INFO: renamed from: coil.util.-Utils, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¦\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\n\u001a\u00020\t*\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\n\u001a\u00020\f*\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\n\u0010\r\u001a\u0015\u0010\n\u001a\u00020\u000e*\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0004\b\n\u0010\u000f\u001a\u000f\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0013*\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a \u0010\u001b\u001a\u0004\u0018\u00010\u001a*\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0080\n¢\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\"\u001a\u00020\u001d*\u00020\u001d2\u001c\u0010!\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f\u0012\b\u0012\u0006\u0012\u0002\b\u00030 \u0018\u00010\u001eH\u0080\b¢\u0006\u0004\b\"\u0010#\u001a\u001e\u0010\"\u001a\u00020\u001d*\u00020\u001d2\b\u0010%\u001a\u0004\u0018\u00010$H\u0080\b¢\u0006\u0004\b\"\u0010&\u001a\u001b\u0010)\u001a\u00020'*\u00020\u00052\u0006\u0010(\u001a\u00020'H\u0000¢\u0006\u0004\b)\u0010*\u001a\u0013\u0010,\u001a\u00020\u0001*\u00020+H\u0000¢\u0006\u0004\b,\u0010-\u001a\u0013\u0010.\u001a\u00020\u0010*\u00020'H\u0000¢\u0006\u0004\b.\u0010/\u001a*\u00105\u001a\u00020'*\u0002002\u0006\u00102\u001a\u0002012\f\u00104\u001a\b\u0012\u0004\u0012\u00020'03H\u0080\b¢\u0006\u0004\b5\u00106\u001a*\u00107\u001a\u00020'*\u0002002\u0006\u00102\u001a\u0002012\f\u00104\u001a\b\u0012\u0004\u0012\u00020'03H\u0080\b¢\u0006\u0004\b7\u00106\u001a\u001b\u00109\u001a\u00020'*\u0002082\u0006\u00102\u001a\u000201H\u0000¢\u0006\u0004\b9\u0010:\u001a\u000f\u0010<\u001a\u00020;H\u0000¢\u0006\u0004\b<\u0010=\u001a\u0017\u0010@\u001a\u00020\u00102\u0006\u0010?\u001a\u00020>H\u0000¢\u0006\u0004\b@\u0010A\u001a\u001b\u0010D\u001a\u00020B*\u00020B2\u0006\u0010C\u001a\u00020\u0005H\u0000¢\u0006\u0004\bD\u0010E\u001a\u0013\u0010H\u001a\u00020G*\u00020FH\u0000¢\u0006\u0004\bH\u0010I\u001a\u0017\u0010M\u001a\u00020L2\u0006\u0010K\u001a\u00020JH\u0000¢\u0006\u0004\bM\u0010N\u001a\u001f\u0010P\u001a\u00020'2\u0006\u0010K\u001a\u00020J2\u0006\u0010O\u001a\u00020LH\u0000¢\u0006\u0004\bP\u0010Q\" \u0010T\u001a\b\u0012\u0004\u0012\u00020S0R8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010W\"\u001a\u0010X\u001a\u00020S8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u001c\u0010]\u001a\u0004\u0018\u00010\\8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b]\u0010^\u001a\u0004\b_\u0010`\"\u001a\u0010a\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\ba\u0010b\u001a\u0004\bc\u0010d\"\u0014\u0010e\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\be\u0010f\"\u0014\u0010g\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\bg\u0010f\"\u0014\u0010h\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\bh\u0010f\"\u0014\u0010i\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\bi\u0010f\"\u0014\u0010j\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\bj\u0010f\"\u0014\u0010k\u001a\u00020L8\u0002X\u0082T¢\u0006\u0006\n\u0004\bk\u0010l\"\u0014\u0010m\u001a\u00020L8\u0002X\u0082T¢\u0006\u0006\n\u0004\bm\u0010l\"\u0014\u0010n\u001a\u00020'8\u0002X\u0082T¢\u0006\u0006\n\u0004\bn\u0010o\"\u0018\u0010t\u001a\u00020q*\u00020p8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\br\u0010s\"\u0018\u0010x\u001a\u00020\u0005*\u00020u8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bv\u0010w\"\u0018\u0010|\u001a\u00020'*\u00020y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{\"\u0018\u0010~\u001a\u00020'*\u00020y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b}\u0010{\"\u0019\u0010\u007f\u001a\u00020\u0010*\u00020y8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u001b\u00102\u001a\u000201*\u00030\u0081\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u001d\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0005*\u00020>8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u001c\u0010\u008a\u0001\u001a\u00020'*\u00030\u0087\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u001d\u0010\u008e\u0001\u001a\u00020'*\u00030\u008b\u00018À\u0002X\u0080\u0004¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001\"\u001c\u0010\u0092\u0001\u001a\u00030\u008f\u0001*\u00020J8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u001c\u0010\u0094\u0001\u001a\u00020\u0010*\u00030\u0093\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u001d\u0010\u0099\u0001\u001a\u00030\u0096\u0001*\u00030\u0093\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001¨\u0006\u009a\u0001"}, d2 = {"Ljava/io/Closeable;", "Lx6/t0;", "closeQuietly", "(Ljava/io/Closeable;)V", "Landroid/webkit/MimeTypeMap;", "", "url", "getMimeTypeFromUrl", "(Landroid/webkit/MimeTypeMap;Ljava/lang/String;)Ljava/lang/String;", "Lgb/a0;", "orEmpty", "(Lgb/a0;)Lgb/a0;", "Lcoil/request/Tags;", "(Lcoil/request/Tags;)Lcoil/request/Tags;", "Lcoil/request/Parameters;", "(Lcoil/request/Parameters;)Lcoil/request/Parameters;", "", "isMainThread", "()Z", "T", "Lka/p0;", "getCompletedOrNull", "(Lka/p0;)Ljava/lang/Object;", "Lcoil/memory/MemoryCache;", "Lcoil/memory/MemoryCache$Key;", "key", "Lcoil/memory/MemoryCache$Value;", "get", "(Lcoil/memory/MemoryCache;Lcoil/memory/MemoryCache$Key;)Lcoil/memory/MemoryCache$Value;", "Lcoil/ComponentRegistry$Builder;", "Lx6/x;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/lang/Class;", "pair", "addFirst", "(Lcoil/ComponentRegistry$Builder;Lx6/x;)Lcoil/ComponentRegistry$Builder;", "Lcoil/decode/Decoder$Factory;", "factory", "(Lcoil/ComponentRegistry$Builder;Lcoil/decode/Decoder$Factory;)Lcoil/ComponentRegistry$Builder;", "", "defaultValue", "toNonNegativeInt", "(Ljava/lang/String;I)I", "Lcoil/disk/DiskCache$Editor;", "abortQuietly", "(Lcoil/disk/DiskCache$Editor;)V", "isMinOrMax", "(I)Z", "Lcoil/size/Size;", "Lcoil/size/Scale;", "scale", "Lkotlin/Function0;", "original", "widthPx", "(Lcoil/size/Size;Lcoil/size/Scale;Lr7/a;)I", "heightPx", "Lcoil/size/Dimension;", "toPx", "(Lcoil/size/Dimension;Lcoil/size/Scale;)I", "", "unsupported", "()Ljava/lang/Void;", "Landroid/net/Uri;", "uri", "isAssetUri", "(Landroid/net/Uri;)Z", "Lgb/z;", "line", "addUnsafeNonAscii", "(Lgb/z;Ljava/lang/String;)Lgb/z;", "Lgb/p0;", "Lgb/s0;", "requireBody", "(Lgb/p0;)Lgb/s0;", "Landroid/content/Context;", "context", "", "defaultMemoryCacheSizePercent", "(Landroid/content/Context;)D", "percent", "calculateMemoryCacheSize", "(Landroid/content/Context;D)I", "", "Landroid/graphics/Bitmap$Config;", "VALID_TRANSFORMATION_CONFIGS", "[Landroid/graphics/Bitmap$Config;", "getVALID_TRANSFORMATION_CONFIGS", "()[Landroid/graphics/Bitmap$Config;", "DEFAULT_BITMAP_CONFIG", "Landroid/graphics/Bitmap$Config;", "getDEFAULT_BITMAP_CONFIG", "()Landroid/graphics/Bitmap$Config;", "Landroid/graphics/ColorSpace;", "NULL_COLOR_SPACE", "Landroid/graphics/ColorSpace;", "getNULL_COLOR_SPACE", "()Landroid/graphics/ColorSpace;", "EMPTY_HEADERS", "Lgb/a0;", "getEMPTY_HEADERS", "()Lgb/a0;", "MIME_TYPE_JPEG", "Ljava/lang/String;", "MIME_TYPE_WEBP", "MIME_TYPE_HEIC", "MIME_TYPE_HEIF", "ASSET_FILE_PATH_ROOT", "STANDARD_MEMORY_MULTIPLIER", "D", "LOW_MEMORY_MULTIPLIER", "DEFAULT_MEMORY_CLASS_MEGABYTES", "I", "Landroid/view/View;", "Lcoil/request/ViewTargetRequestManager;", "getRequestManager", "(Landroid/view/View;)Lcoil/request/ViewTargetRequestManager;", "requestManager", "Lcoil/decode/DataSource;", "getEmoji", "(Lcoil/decode/DataSource;)Ljava/lang/String;", "emoji", "Landroid/graphics/drawable/Drawable;", "getWidth", "(Landroid/graphics/drawable/Drawable;)I", "width", "getHeight", "height", "isVector", "(Landroid/graphics/drawable/Drawable;)Z", "Landroid/widget/ImageView;", "getScale", "(Landroid/widget/ImageView;)Lcoil/size/Scale;", "getFirstPathSegment", "(Landroid/net/Uri;)Ljava/lang/String;", "firstPathSegment", "Landroid/content/res/Configuration;", "getNightMode", "(Landroid/content/res/Configuration;)I", "nightMode", "", "getIdentityHashCode", "(Ljava/lang/Object;)I", "identityHashCode", "Ljava/io/File;", "getSafeCacheDir", "(Landroid/content/Context;)Ljava/io/File;", "safeCacheDir", "Lcoil/intercept/Interceptor$Chain;", "isPlaceholderCached", "(Lcoil/intercept/Interceptor$Chain;)Z", "Lcoil/EventListener;", "getEventListener", "(Lcoil/intercept/Interceptor$Chain;)Lcoil/EventListener;", "eventListener", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Utils {
    public static final String ASSET_FILE_PATH_ROOT = "android_asset";
    private static final Bitmap.Config DEFAULT_BITMAP_CONFIG;
    private static final int DEFAULT_MEMORY_CLASS_MEGABYTES = 256;
    private static final a0 EMPTY_HEADERS;
    private static final double LOW_MEMORY_MULTIPLIER = 0.15d;
    public static final String MIME_TYPE_HEIC = "image/heic";
    public static final String MIME_TYPE_HEIF = "image/heif";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_WEBP = "image/webp";
    private static final ColorSpace NULL_COLOR_SPACE = null;
    private static final double STANDARD_MEMORY_MULTIPLIER = 0.2d;
    private static final Bitmap.Config[] VALID_TRANSFORMATION_CONFIGS;

    /* JADX INFO: renamed from: coil.util.-Utils$WhenMappings */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DataSource.values().length];
            try {
                iArr[DataSource.MEMORY_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DataSource.MEMORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DataSource.DISK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DataSource.NETWORK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            try {
                iArr2[ImageView.ScaleType.FIT_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[Scale.values().length];
            try {
                iArr3[Scale.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[Scale.FIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    static {
        int i10 = Build.VERSION.SDK_INT;
        VALID_TRANSFORMATION_CONFIGS = i10 >= 26 ? new Bitmap.Config[]{Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16} : new Bitmap.Config[]{Bitmap.Config.ARGB_8888};
        DEFAULT_BITMAP_CONFIG = i10 >= 26 ? Bitmap.Config.HARDWARE : Bitmap.Config.ARGB_8888;
        EMPTY_HEADERS = new a0((String[]) new ArrayList(20).toArray(new String[0]));
    }

    public static final void abortQuietly(DiskCache.Editor editor) {
        try {
            editor.abort();
        } catch (Exception unused) {
        }
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, x xVar) {
        if (xVar != null) {
            builder.getFetcherFactories$coil_base_release().add(0, xVar);
        }
        return builder;
    }

    public static final z addUnsafeNonAscii(z zVar, String str) {
        int iE0 = o.e0(str, ':', 0, false, 6);
        if (iE0 == -1) {
            throw new IllegalArgumentException(d.C("Unexpected header: ", str).toString());
        }
        zVar.d(o.L0(str.substring(0, iE0)).toString(), str.substring(iE0 + 1));
        return zVar;
    }

    public static final int calculateMemoryCacheSize(Context context, double d4) {
        int largeMemoryClass;
        try {
            ActivityManager activityManager = (ActivityManager) b.b(context, ActivityManager.class);
            largeMemoryClass = (context.getApplicationInfo().flags & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
        } catch (Exception unused) {
            largeMemoryClass = 256;
        }
        double d10 = 1024;
        return (int) (d4 * ((double) largeMemoryClass) * d10 * d10);
    }

    public static final void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception unused) {
        }
    }

    public static final double defaultMemoryCacheSizePercent(Context context) {
        try {
            if (((ActivityManager) b.b(context, ActivityManager.class)).isLowRamDevice()) {
                return LOW_MEMORY_MULTIPLIER;
            }
            return 0.2d;
        } catch (Exception unused) {
            return 0.2d;
        }
    }

    public static final MemoryCache.Value get(MemoryCache memoryCache, MemoryCache.Key key) {
        if (key != null) {
            return memoryCache.get(key);
        }
        return null;
    }

    public static final <T> T getCompletedOrNull(p0<? extends T> p0Var) {
        try {
            return (T) p0Var.k();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final Bitmap.Config getDEFAULT_BITMAP_CONFIG() {
        return DEFAULT_BITMAP_CONFIG;
    }

    public static final a0 getEMPTY_HEADERS() {
        return EMPTY_HEADERS;
    }

    public static final String getEmoji(DataSource dataSource) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[dataSource.ordinal()];
        if (i10 == 1 || i10 == 2) {
            return Emoji.BRAIN;
        }
        if (i10 == 3) {
            return Emoji.FLOPPY;
        }
        if (i10 == 4) {
            return Emoji.CLOUD;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final EventListener getEventListener(Interceptor.Chain chain) {
        return chain instanceof RealInterceptorChain ? ((RealInterceptorChain) chain).getEventListener() : EventListener.NONE;
    }

    public static final String getFirstPathSegment(Uri uri) {
        return (String) kotlin.collections.x.o0(uri.getPathSegments());
    }

    public static final int getHeight(Drawable drawable) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicHeight() : bitmap.getHeight();
    }

    public static final int getIdentityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }

    public static final String getMimeTypeFromUrl(MimeTypeMap mimeTypeMap, String str) {
        if (str == null || o.h0(str)) {
            return null;
        }
        int iK0 = o.k0(str, '#', 0, 6);
        if (iK0 != -1) {
            str = str.substring(0, iK0);
        }
        int iK02 = o.k0(str, '?', 0, 6);
        if (iK02 != -1) {
            str = str.substring(0, iK02);
        }
        return mimeTypeMap.getMimeTypeFromExtension(o.E0('.', o.E0('/', str, str), ""));
    }

    public static final ColorSpace getNULL_COLOR_SPACE() {
        return NULL_COLOR_SPACE;
    }

    public static final int getNightMode(Configuration configuration) {
        return configuration.uiMode & 48;
    }

    public static final ViewTargetRequestManager getRequestManager(View view) {
        ViewTargetRequestManager viewTargetRequestManager;
        Object tag = view.getTag(R.id.coil_request_manager);
        ViewTargetRequestManager viewTargetRequestManager2 = tag instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag : null;
        if (viewTargetRequestManager2 != null) {
            return viewTargetRequestManager2;
        }
        synchronized (view) {
            try {
                Object tag2 = view.getTag(R.id.coil_request_manager);
                viewTargetRequestManager = tag2 instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag2 : null;
                if (viewTargetRequestManager == null) {
                    viewTargetRequestManager = new ViewTargetRequestManager(view);
                    view.addOnAttachStateChangeListener(viewTargetRequestManager);
                    view.setTag(R.id.coil_request_manager, viewTargetRequestManager);
                }
            } finally {
            }
        }
        return viewTargetRequestManager;
    }

    public static final File getSafeCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            throw new IllegalStateException("cacheDir == null");
        }
        cacheDir.mkdirs();
        return cacheDir;
    }

    public static final Scale getScale(ImageView imageView) {
        ImageView.ScaleType scaleType = imageView.getScaleType();
        int i10 = scaleType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[scaleType.ordinal()];
        return (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) ? Scale.FIT : Scale.FILL;
    }

    public static final Bitmap.Config[] getVALID_TRANSFORMATION_CONFIGS() {
        return VALID_TRANSFORMATION_CONFIGS;
    }

    public static final int getWidth(Drawable drawable) {
        Bitmap bitmap;
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicWidth() : bitmap.getWidth();
    }

    public static final int heightPx(Size size, Scale scale, a<Integer> aVar) {
        return Sizes.isOriginal(size) ? ((Number) aVar.invoke()).intValue() : toPx(size.getHeight(), scale);
    }

    public static final boolean isAssetUri(Uri uri) {
        return p.a(uri.getScheme(), "file") && p.a(getFirstPathSegment(uri), ASSET_FILE_PATH_ROOT);
    }

    public static final boolean isMainThread() {
        return p.a(Looper.myLooper(), Looper.getMainLooper());
    }

    public static final boolean isMinOrMax(int i10) {
        return i10 == Integer.MIN_VALUE || i10 == Integer.MAX_VALUE;
    }

    public static final boolean isPlaceholderCached(Interceptor.Chain chain) {
        return (chain instanceof RealInterceptorChain) && ((RealInterceptorChain) chain).getIsPlaceholderCached();
    }

    public static final boolean isVector(Drawable drawable) {
        return (drawable instanceof VectorDrawable) || (drawable instanceof t);
    }

    public static final a0 orEmpty(a0 a0Var) {
        return a0Var == null ? EMPTY_HEADERS : a0Var;
    }

    public static final s0 requireBody(gb.p0 p0Var) {
        s0 s0Var = p0Var.f15786q;
        if (s0Var != null) {
            return s0Var;
        }
        throw new IllegalStateException("response body == null");
    }

    public static final int toNonNegativeInt(String str, int i10) {
        Long lS = u.S(str);
        if (lS == null) {
            return i10;
        }
        long jLongValue = lS.longValue();
        if (jLongValue > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (jLongValue < 0) {
            return 0;
        }
        return (int) jLongValue;
    }

    public static final int toPx(Dimension dimension, Scale scale) {
        if (dimension instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) dimension).px;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$2[scale.ordinal()];
        if (i10 == 1) {
            return Integer.MIN_VALUE;
        }
        if (i10 == 2) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final Void unsupported() {
        throw new IllegalStateException("Unsupported");
    }

    public static final int widthPx(Size size, Scale scale, a<Integer> aVar) {
        return Sizes.isOriginal(size) ? ((Number) aVar.invoke()).intValue() : toPx(size.getWidth(), scale);
    }

    public static final ComponentRegistry.Builder addFirst(ComponentRegistry.Builder builder, Decoder.Factory factory) {
        if (factory != null) {
            builder.getDecoderFactories$coil_base_release().add(0, factory);
        }
        return builder;
    }

    public static final Tags orEmpty(Tags tags) {
        return tags == null ? Tags.EMPTY : tags;
    }

    public static final Parameters orEmpty(Parameters parameters) {
        return parameters == null ? Parameters.EMPTY : parameters;
    }
}
