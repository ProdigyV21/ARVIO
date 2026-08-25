package coil;

import android.content.Context;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import d7.d;
import kotlin.Metadata;
import q7.n;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\n\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000f\u0010\u0003J\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\bJ\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0011H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lcoil/Coil;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lcoil/ImageLoader;", "imageLoader", "(Landroid/content/Context;)Lcoil/ImageLoader;", "Lx6/t0;", "setImageLoader", "(Lcoil/ImageLoader;)V", "Lcoil/ImageLoaderFactory;", "factory", "(Lcoil/ImageLoaderFactory;)V", "reset", "newImageLoader", "Lcoil/request/ImageRequest;", "request", "Lcoil/request/Disposable;", "enqueue", "(Lcoil/request/ImageRequest;)Lcoil/request/Disposable;", "Lcoil/request/ImageResult;", "execute", "(Lcoil/request/ImageRequest;Ld7/d;)Ljava/lang/Object;", "Lcoil/ImageLoader;", "imageLoaderFactory", "Lcoil/ImageLoaderFactory;", "coil-singleton_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Coil {
    public static final Coil INSTANCE = new Coil();
    private static ImageLoader imageLoader;
    private static ImageLoaderFactory imageLoaderFactory;

    private Coil() {
    }

    @n
    @e
    public static final Disposable enqueue(ImageRequest request) {
        throw new IllegalStateException("Unsupported");
    }

    @n
    @e
    public static final Object execute(ImageRequest imageRequest, d<? super ImageResult> dVar) {
        throw new IllegalStateException("Unsupported");
    }

    @n
    public static final ImageLoader imageLoader(Context context) {
        ImageLoader imageLoader2 = imageLoader;
        return imageLoader2 == null ? INSTANCE.newImageLoader(context) : imageLoader2;
    }

    private final synchronized ImageLoader newImageLoader(Context context) {
        ImageLoader imageLoaderNewImageLoader;
        try {
            ImageLoader imageLoader2 = imageLoader;
            if (imageLoader2 != null) {
                return imageLoader2;
            }
            ImageLoaderFactory imageLoaderFactory2 = imageLoaderFactory;
            if (imageLoaderFactory2 == null || (imageLoaderNewImageLoader = imageLoaderFactory2.newImageLoader()) == null) {
                Object applicationContext = context.getApplicationContext();
                ImageLoaderFactory imageLoaderFactory3 = applicationContext instanceof ImageLoaderFactory ? (ImageLoaderFactory) applicationContext : null;
                imageLoaderNewImageLoader = imageLoaderFactory3 != null ? imageLoaderFactory3.newImageLoader() : ImageLoaders.create(context);
            }
            imageLoaderFactory = null;
            imageLoader = imageLoaderNewImageLoader;
            return imageLoaderNewImageLoader;
        } catch (Throwable th) {
            throw th;
        }
    }

    @n
    public static final synchronized void reset() {
        imageLoader = null;
        imageLoaderFactory = null;
    }

    @n
    public static final synchronized void setImageLoader(ImageLoader imageLoader2) {
        imageLoaderFactory = null;
        imageLoader = imageLoader2;
    }

    @n
    public static final synchronized void setImageLoader(ImageLoaderFactory factory) {
        imageLoaderFactory = factory;
        imageLoader = null;
    }
}
