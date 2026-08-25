package android.graphics;

import android.content.Context;
import android.graphics.ImageSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Closeable;
import java.io.File;
import kotlin.Metadata;
import xb.f0;
import xb.l;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a9\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\u001aE\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b\t\u0010\u000e\u001a\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\t\u0010\u0013\u001a+\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b\t\u0010\u0014\u001a\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\t\u0010\u0017\u001a+\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b\t\u0010\u0018¨\u0006\u0019"}, d2 = {"Lxb/f0;", "file", "Lxb/s;", "fileSystem", "", "diskCacheKey", "Ljava/io/Closeable;", "closeable", "Lcoil/decode/ImageSource;", "create", "(Lxb/f0;Lxb/s;Ljava/lang/String;Ljava/io/Closeable;)Lcoil/decode/ImageSource;", "ImageSource", "Lcoil/decode/ImageSource$Metadata;", TtmlNode.TAG_METADATA, "(Lxb/f0;Lxb/s;Ljava/lang/String;Ljava/io/Closeable;Lcoil/decode/ImageSource$Metadata;)Lcoil/decode/ImageSource;", "Lxb/l;", "source", "Landroid/content/Context;", "context", "(Lxb/l;Landroid/content/Context;)Lcoil/decode/ImageSource;", "(Lxb/l;Landroid/content/Context;Lcoil/decode/ImageSource$Metadata;)Lcoil/decode/ImageSource;", "Ljava/io/File;", "cacheDirectory", "(Lxb/l;Ljava/io/File;)Lcoil/decode/ImageSource;", "(Lxb/l;Ljava/io/File;Lcoil/decode/ImageSource$Metadata;)Lcoil/decode/ImageSource;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ImageSources {
    public static final ImageSource create(f0 f0Var, s sVar, String str, Closeable closeable) {
        return new FileImageSource(f0Var, sVar, str, closeable, null);
    }

    public static /* synthetic */ ImageSource create$default(f0 f0Var, s sVar, String str, Closeable closeable, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            sVar = s.SYSTEM;
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            closeable = null;
        }
        return create(f0Var, sVar, str, closeable);
    }

    public static final ImageSource create(f0 f0Var, s sVar, String str, Closeable closeable, ImageSource.Metadata metadata) {
        return new FileImageSource(f0Var, sVar, str, closeable, metadata);
    }

    public static final ImageSource create(l lVar, Context context) {
        return new SourceImageSource(lVar, new ImageSources$ImageSource$1(context), null);
    }

    public static /* synthetic */ ImageSource create$default(f0 f0Var, s sVar, String str, Closeable closeable, ImageSource.Metadata metadata, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            sVar = s.SYSTEM;
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            closeable = null;
        }
        if ((i10 & 16) != 0) {
            metadata = null;
        }
        return create(f0Var, sVar, str, closeable, metadata);
    }

    public static final ImageSource create(l lVar, Context context, ImageSource.Metadata metadata) {
        return new SourceImageSource(lVar, new ImageSources$ImageSource$2(context), metadata);
    }

    public static final ImageSource create(l lVar, File file) {
        return new SourceImageSource(lVar, new ImageSources$ImageSource$3(file), null);
    }

    public static /* synthetic */ ImageSource create$default(l lVar, Context context, ImageSource.Metadata metadata, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            metadata = null;
        }
        return create(lVar, context, metadata);
    }

    public static final ImageSource create(l lVar, File file, ImageSource.Metadata metadata) {
        return new SourceImageSource(lVar, new ImageSources$ImageSource$4(file), metadata);
    }

    public static /* synthetic */ ImageSource create$default(l lVar, File file, ImageSource.Metadata metadata, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            metadata = null;
        }
        return create(lVar, file, metadata);
    }
}
