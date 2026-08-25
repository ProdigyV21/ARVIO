package coil.request;

import android.content.Context;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.os.Build;
import android.view.Scale;
import android.view.Size;
import gb.a0;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b(\u0018\u00002\u00020\u0001B\u009f\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0018¢\u0006\u0004\b\u001c\u0010\u001dJ§\u0001\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u0018¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010!\u001a\u00020\f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b*\u0010+R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010/\u001a\u0004\b0\u00101R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00102\u001a\u0004\b3\u00104R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u00105\u001a\u0004\b6\u00107R\u0017\u0010\u000e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000e\u00105\u001a\u0004\b8\u00107R\u0017\u0010\u000f\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000f\u00105\u001a\u0004\b9\u00107R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010=\u001a\u0004\b>\u0010?R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010@\u001a\u0004\bA\u0010BR\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010C\u001a\u0004\bD\u0010ER\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010F\u001a\u0004\bG\u0010HR\u0017\u0010\u001a\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u001a\u0010F\u001a\u0004\bI\u0010HR\u0017\u0010\u001b\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u001b\u0010F\u001a\u0004\bJ\u0010H¨\u0006K"}, d2 = {"Lcoil/request/Options;", "", "Landroid/content/Context;", "context", "Landroid/graphics/Bitmap$Config;", "config", "Landroid/graphics/ColorSpace;", "colorSpace", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "Lcoil/size/Scale;", "scale", "", "allowInexactSize", "allowRgb565", "premultipliedAlpha", "", "diskCacheKey", "Lgb/a0;", "headers", "Lcoil/request/Tags;", "tags", "Lcoil/request/Parameters;", "parameters", "Lcoil/request/CachePolicy;", "memoryCachePolicy", "diskCachePolicy", "networkCachePolicy", "<init>", "(Landroid/content/Context;Landroid/graphics/Bitmap$Config;Landroid/graphics/ColorSpace;Lcoil/size/Size;Lcoil/size/Scale;ZZZLjava/lang/String;Lgb/a0;Lcoil/request/Tags;Lcoil/request/Parameters;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)V", "copy", "(Landroid/content/Context;Landroid/graphics/Bitmap$Config;Landroid/graphics/ColorSpace;Lcoil/size/Size;Lcoil/size/Scale;ZZZLjava/lang/String;Lgb/a0;Lcoil/request/Tags;Lcoil/request/Parameters;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)Lcoil/request/Options;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroid/graphics/Bitmap$Config;", "getConfig", "()Landroid/graphics/Bitmap$Config;", "Landroid/graphics/ColorSpace;", "getColorSpace", "()Landroid/graphics/ColorSpace;", "Lcoil/size/Size;", "getSize", "()Lcoil/size/Size;", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "Z", "getAllowInexactSize", "()Z", "getAllowRgb565", "getPremultipliedAlpha", "Ljava/lang/String;", "getDiskCacheKey", "()Ljava/lang/String;", "Lgb/a0;", "getHeaders", "()Lgb/a0;", "Lcoil/request/Tags;", "getTags", "()Lcoil/request/Tags;", "Lcoil/request/Parameters;", "getParameters", "()Lcoil/request/Parameters;", "Lcoil/request/CachePolicy;", "getMemoryCachePolicy", "()Lcoil/request/CachePolicy;", "getDiskCachePolicy", "getNetworkCachePolicy", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Options {
    private final boolean allowInexactSize;
    private final boolean allowRgb565;
    private final ColorSpace colorSpace;
    private final Bitmap.Config config;
    private final Context context;
    private final String diskCacheKey;
    private final CachePolicy diskCachePolicy;
    private final a0 headers;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Parameters parameters;
    private final boolean premultipliedAlpha;
    private final Scale scale;
    private final Size size;
    private final Tags tags;

    public Options(Context context, Bitmap.Config config, ColorSpace colorSpace, Size size, Scale scale, boolean z, boolean z5, boolean z10, String str, a0 a0Var, Tags tags, Parameters parameters, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        this.context = context;
        this.config = config;
        this.colorSpace = colorSpace;
        this.size = size;
        this.scale = scale;
        this.allowInexactSize = z;
        this.allowRgb565 = z5;
        this.premultipliedAlpha = z10;
        this.diskCacheKey = str;
        this.headers = a0Var;
        this.tags = tags;
        this.parameters = parameters;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public final Options copy(Context context, Bitmap.Config config, ColorSpace colorSpace, Size size, Scale scale, boolean allowInexactSize, boolean allowRgb565, boolean premultipliedAlpha, String diskCacheKey, a0 headers, Tags tags, Parameters parameters, CachePolicy memoryCachePolicy, CachePolicy diskCachePolicy, CachePolicy networkCachePolicy) {
        return new Options(context, config, colorSpace, size, scale, allowInexactSize, allowRgb565, premultipliedAlpha, diskCacheKey, headers, tags, parameters, memoryCachePolicy, diskCachePolicy, networkCachePolicy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Options)) {
            return false;
        }
        Options options = (Options) other;
        if (p.a(this.context, options.context) && this.config == options.config) {
            return (Build.VERSION.SDK_INT < 26 || p.a(this.colorSpace, options.colorSpace)) && p.a(this.size, options.size) && this.scale == options.scale && this.allowInexactSize == options.allowInexactSize && this.allowRgb565 == options.allowRgb565 && this.premultipliedAlpha == options.premultipliedAlpha && p.a(this.diskCacheKey, options.diskCacheKey) && p.a(this.headers, options.headers) && p.a(this.tags, options.tags) && p.a(this.parameters, options.parameters) && this.memoryCachePolicy == options.memoryCachePolicy && this.diskCachePolicy == options.diskCachePolicy && this.networkCachePolicy == options.networkCachePolicy;
        }
        return false;
    }

    public final boolean getAllowInexactSize() {
        return this.allowInexactSize;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public final Bitmap.Config getConfig() {
        return this.config;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final a0 getHeaders() {
        return this.headers;
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

    public final boolean getPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final Size getSize() {
        return this.size;
    }

    public final Tags getTags() {
        return this.tags;
    }

    public int hashCode() {
        int iHashCode = (this.config.hashCode() + (this.context.hashCode() * 31)) * 31;
        ColorSpace colorSpace = this.colorSpace;
        int iHashCode2 = (((((((this.scale.hashCode() + ((this.size.hashCode() + ((iHashCode + (colorSpace != null ? colorSpace.hashCode() : 0)) * 31)) * 31)) * 31) + (this.allowInexactSize ? 1231 : 1237)) * 31) + (this.allowRgb565 ? 1231 : 1237)) * 31) + (this.premultipliedAlpha ? 1231 : 1237)) * 31;
        String str = this.diskCacheKey;
        return this.networkCachePolicy.hashCode() + ((this.diskCachePolicy.hashCode() + ((this.memoryCachePolicy.hashCode() + ((this.parameters.hashCode() + ((this.tags.hashCode() + ((((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Arrays.hashCode(this.headers.f15615i)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public /* synthetic */ Options(Context context, Bitmap.Config config, ColorSpace colorSpace, Size size, Scale scale, boolean z, boolean z5, boolean z10, String str, a0 a0Var, Tags tags, Parameters parameters, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, int i10, h hVar) {
        this(context, (i10 & 2) != 0 ? Bitmap.Config.ARGB_8888 : config, (i10 & 4) != 0 ? Utils.getNULL_COLOR_SPACE() : colorSpace, (i10 & 8) != 0 ? Size.ORIGINAL : size, (i10 & 16) != 0 ? Scale.FIT : scale, (i10 & 32) != 0 ? false : z, (i10 & 64) == 0 ? z5 : false, (i10 & 128) != 0 ? true : z10, (i10 & 256) != 0 ? null : str, (i10 & 512) != 0 ? Utils.getEMPTY_HEADERS() : a0Var, (i10 & 1024) != 0 ? Tags.EMPTY : tags, (i10 & 2048) != 0 ? Parameters.EMPTY : parameters, (i10 & 4096) != 0 ? CachePolicy.ENABLED : cachePolicy, (i10 & 8192) != 0 ? CachePolicy.ENABLED : cachePolicy2, (i10 & 16384) != 0 ? CachePolicy.ENABLED : cachePolicy3);
    }
}
