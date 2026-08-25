package coil.fetch;

import android.content.Context;
import android.content.DrawableUtils;
import android.content.Utils;
import android.content.res.Resources;
import android.graphics.DataSource;
import android.graphics.ImageSources;
import android.graphics.ResourceMetadata;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.fetch.Fetcher;
import coil.request.Options;
import d7.d;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import xb.b;
import xb.i0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0002\u0010\u0011B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcoil/fetch/ResourceUriFetcher;", "Lcoil/fetch/Fetcher;", "Landroid/net/Uri;", "data", "Lcoil/request/Options;", "options", "<init>", "(Landroid/net/Uri;Lcoil/request/Options;)V", "", "throwInvalidUriException", "(Landroid/net/Uri;)Ljava/lang/Void;", "Lcoil/fetch/FetchResult;", "fetch", "(Ld7/d;)Ljava/lang/Object;", "Landroid/net/Uri;", "Lcoil/request/Options;", "Companion", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ResourceUriFetcher implements Fetcher {
    private static final String MIME_TYPE_XML = "text/xml";
    private final Uri data;
    private final Options options;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0002H\u0002¨\u0006\r"}, d2 = {"Lcoil/fetch/ResourceUriFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Landroid/net/Uri;", "()V", "create", "Lcoil/fetch/Fetcher;", "data", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "isApplicable", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<Uri> {
        private final boolean isApplicable(Uri data) {
            return p.a(data.getScheme(), "android.resource");
        }

        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(Uri data, Options options, ImageLoader imageLoader) {
            if (isApplicable(data)) {
                return new ResourceUriFetcher(data, options);
            }
            return null;
        }
    }

    public ResourceUriFetcher(Uri uri, Options options) {
        this.data = uri;
        this.options = options;
    }

    private final Void throwInvalidUriException(Uri data) {
        throw new IllegalStateException("Invalid android.resource URI: " + data);
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(d<? super FetchResult> dVar) {
        Integer numR;
        String authority = this.data.getAuthority();
        if (authority != null) {
            if (o.h0(authority)) {
                authority = null;
            }
            if (authority != null) {
                String str = (String) x.y0(this.data.getPathSegments());
                if (str == null || (numR = u.R(str)) == null) {
                    throwInvalidUriException(this.data);
                    throw new KotlinNothingValueException();
                }
                int iIntValue = numR.intValue();
                Context context = this.options.getContext();
                Resources resources = authority.equals(context.getPackageName()) ? context.getResources() : context.getPackageManager().getResourcesForApplication(authority);
                TypedValue typedValue = new TypedValue();
                resources.getValue(iIntValue, typedValue, true);
                CharSequence charSequence = typedValue.string;
                String mimeTypeFromUrl = Utils.getMimeTypeFromUrl(MimeTypeMap.getSingleton(), charSequence.subSequence(o.k0(charSequence, '/', 0, 6), charSequence.length()).toString());
                if (!p.a(mimeTypeFromUrl, MIME_TYPE_XML)) {
                    TypedValue typedValue2 = new TypedValue();
                    return new SourceResult(ImageSources.create(new i0(b.g(resources.openRawResource(iIntValue, typedValue2))), context, new ResourceMetadata(authority, iIntValue, typedValue2.density)), mimeTypeFromUrl, DataSource.DISK);
                }
                Drawable drawableCompat = authority.equals(context.getPackageName()) ? android.content.Context.getDrawableCompat(context, iIntValue) : android.content.Context.getXmlDrawableCompat(context, resources, iIntValue);
                boolean zIsVector = Utils.isVector(drawableCompat);
                if (zIsVector) {
                    drawableCompat = new BitmapDrawable(context.getResources(), DrawableUtils.INSTANCE.convertToBitmap(drawableCompat, this.options.getConfig(), this.options.getSize(), this.options.getScale(), this.options.getAllowInexactSize()));
                }
                return new DrawableResult(drawableCompat, zIsVector, DataSource.DISK);
            }
        }
        throwInvalidUriException(this.data);
        throw new KotlinNothingValueException();
    }
}
