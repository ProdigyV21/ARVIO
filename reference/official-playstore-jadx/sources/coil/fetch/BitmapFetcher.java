package coil.fetch;

import android.graphics.Bitmap;
import android.graphics.DataSource;
import android.graphics.drawable.BitmapDrawable;
import coil.ImageLoader;
import coil.fetch.Fetcher;
import coil.request.Options;
import d7.d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcoil/fetch/BitmapFetcher;", "Lcoil/fetch/Fetcher;", "Landroid/graphics/Bitmap;", "data", "Lcoil/request/Options;", "options", "<init>", "(Landroid/graphics/Bitmap;Lcoil/request/Options;)V", "Lcoil/fetch/FetchResult;", "fetch", "(Ld7/d;)Ljava/lang/Object;", "Landroid/graphics/Bitmap;", "Lcoil/request/Options;", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BitmapFetcher implements Fetcher {
    private final Bitmap data;
    private final Options options;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcoil/fetch/BitmapFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Landroid/graphics/Bitmap;", "()V", "create", "Lcoil/fetch/Fetcher;", "data", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<Bitmap> {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Bitmap data, Options options, ImageLoader imageLoader) {
            return new BitmapFetcher(data, options);
        }
    }

    public BitmapFetcher(Bitmap bitmap, Options options) {
        this.data = bitmap;
        this.options = options;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(d<? super FetchResult> dVar) {
        return new DrawableResult(new BitmapDrawable(this.options.getContext().getResources(), this.data), false, DataSource.MEMORY);
    }
}
