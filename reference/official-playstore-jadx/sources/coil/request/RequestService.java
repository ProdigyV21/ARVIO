package coil.request;

import android.content.Bitmaps;
import android.content.HardwareBitmapService;
import android.content.HardwareBitmaps;
import android.content.Logger;
import android.content.Requests;
import android.content.SystemCallbacks;
import android.content.Utils;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Dimension;
import android.view.Scale;
import android.view.Size;
import android.view.View;
import coil.ImageLoader;
import coil.target.Target;
import coil.target.ViewTarget;
import io.ktor.http.ContentDisposition;
import ka.v1;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010#\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0017\u0010%\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0007¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcoil/request/RequestService;", "", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/util/SystemCallbacks;", "systemCallbacks", "Lcoil/util/Logger;", "logger", "<init>", "(Lcoil/ImageLoader;Lcoil/util/SystemCallbacks;Lcoil/util/Logger;)V", "Lcoil/request/ImageRequest;", "request", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "", "isConfigValidForHardwareAllocation", "(Lcoil/request/ImageRequest;Lcoil/size/Size;)Z", "isConfigValidForTransformations", "(Lcoil/request/ImageRequest;)Z", "initialRequest", "Lka/v1;", "job", "Lcoil/request/RequestDelegate;", "requestDelegate", "(Lcoil/request/ImageRequest;Lka/v1;)Lcoil/request/RequestDelegate;", "", "throwable", "Lcoil/request/ErrorResult;", "errorResult", "(Lcoil/request/ImageRequest;Ljava/lang/Throwable;)Lcoil/request/ErrorResult;", "Lcoil/request/Options;", "options", "(Lcoil/request/ImageRequest;Lcoil/size/Size;)Lcoil/request/Options;", "Landroid/graphics/Bitmap$Config;", "requestedConfig", "isConfigValidForHardware", "(Lcoil/request/ImageRequest;Landroid/graphics/Bitmap$Config;)Z", "allowHardwareWorkerThread", "(Lcoil/request/Options;)Z", "Lcoil/ImageLoader;", "Lcoil/util/SystemCallbacks;", "Lcoil/util/HardwareBitmapService;", "hardwareBitmapService", "Lcoil/util/HardwareBitmapService;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RequestService {
    private final HardwareBitmapService hardwareBitmapService;
    private final ImageLoader imageLoader;
    private final SystemCallbacks systemCallbacks;

    public RequestService(ImageLoader imageLoader, SystemCallbacks systemCallbacks, Logger logger) {
        this.imageLoader = imageLoader;
        this.systemCallbacks = systemCallbacks;
        this.hardwareBitmapService = HardwareBitmaps.HardwareBitmapService(logger);
    }

    private final boolean isConfigValidForHardwareAllocation(ImageRequest request, Size size) {
        if (Bitmaps.isHardware(request.getBitmapConfig())) {
            return isConfigValidForHardware(request, request.getBitmapConfig()) && this.hardwareBitmapService.allowHardwareMainThread(size);
        }
        return true;
    }

    private final boolean isConfigValidForTransformations(ImageRequest request) {
        return request.getTransformations().isEmpty() || r.J(Utils.getVALID_TRANSFORMATION_CONFIGS(), request.getBitmapConfig());
    }

    public final boolean allowHardwareWorkerThread(Options options) {
        return !Bitmaps.isHardware(options.getConfig()) || this.hardwareBitmapService.getAllowHardware();
    }

    public final ErrorResult errorResult(ImageRequest request, Throwable throwable) {
        Drawable error;
        if (!(throwable instanceof NullRequestDataException) || (error = request.getFallback()) == null) {
            error = request.getError();
        }
        return new ErrorResult(error, request, throwable);
    }

    public final boolean isConfigValidForHardware(ImageRequest request, Bitmap.Config requestedConfig) {
        if (!Bitmaps.isHardware(requestedConfig)) {
            return true;
        }
        if (!request.getAllowHardware()) {
            return false;
        }
        Target target = request.getTarget();
        if (target instanceof ViewTarget) {
            View view = ((ViewTarget) target).getView();
            if (view.isAttachedToWindow() && !view.isHardwareAccelerated()) {
                return false;
            }
        }
        return true;
    }

    public final Options options(ImageRequest request, Size size) {
        Bitmap.Config bitmapConfig = (isConfigValidForTransformations(request) && isConfigValidForHardwareAllocation(request, size)) ? request.getBitmapConfig() : Bitmap.Config.ARGB_8888;
        CachePolicy networkCachePolicy = this.systemCallbacks.get_isOnline() ? request.getNetworkCachePolicy() : CachePolicy.DISABLED;
        Dimension width = size.getWidth();
        Dimension.Undefined undefined = Dimension.Undefined.INSTANCE;
        return new Options(request.getContext(), bitmapConfig, request.getColorSpace(), size, (p.a(width, undefined) || p.a(size.getHeight(), undefined)) ? Scale.FIT : request.getScale(), Requests.getAllowInexactSize(request), request.getAllowRgb565() && request.getTransformations().isEmpty() && bitmapConfig != Bitmap.Config.ALPHA_8, request.getPremultipliedAlpha(), request.getDiskCacheKey(), request.getHeaders(), request.getTags(), request.getParameters(), request.getMemoryCachePolicy(), request.getDiskCachePolicy(), networkCachePolicy);
    }

    public final RequestDelegate requestDelegate(ImageRequest initialRequest, v1 job) {
        androidx.lifecycle.r lifecycle = initialRequest.getLifecycle();
        Target target = initialRequest.getTarget();
        return target instanceof ViewTarget ? new ViewTargetRequestDelegate(this.imageLoader, initialRequest, (ViewTarget) target, lifecycle, job) : new BaseRequestDelegate(lifecycle, job);
    }
}
