package coil.fetch;

import android.graphics.DataSource;
import android.graphics.ImageSources;
import android.webkit.MimeTypeMap;
import coil.ImageLoader;
import coil.fetch.Fetcher;
import coil.request.Options;
import d7.d;
import java.io.File;
import kotlin.Metadata;
import kotlin.text.o;
import v1.i;
import xb.f0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\nB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lcoil/fetch/FileFetcher;", "Lcoil/fetch/Fetcher;", "Ljava/io/File;", "data", "<init>", "(Ljava/io/File;)V", "Lcoil/fetch/FetchResult;", "fetch", "(Ld7/d;)Ljava/lang/Object;", "Ljava/io/File;", "Factory", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FileFetcher implements Fetcher {
    private final File data;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcoil/fetch/FileFetcher$Factory;", "Lcoil/fetch/Fetcher$Factory;", "Ljava/io/File;", "()V", "create", "Lcoil/fetch/Fetcher;", "data", "options", "Lcoil/request/Options;", "imageLoader", "Lcoil/ImageLoader;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Factory implements Fetcher.Factory<File> {
        @Override // coil.fetch.Fetcher.Factory
        public Fetcher create(File data, Options options, ImageLoader imageLoader) {
            return new FileFetcher(data);
        }
    }

    public FileFetcher(File file) {
        this.data = file;
    }

    @Override // coil.fetch.Fetcher
    public Object fetch(d<? super FetchResult> dVar) {
        String str = f0.f22733l;
        return new SourceResult(ImageSources.create$default(i.h(this.data), null, null, null, 14, null), MimeTypeMap.getSingleton().getMimeTypeFromExtension(o.E0('.', this.data.getName(), "")), DataSource.DISK);
    }
}
