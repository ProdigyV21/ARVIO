package android.graphics;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Closeable;
import kotlin.jvm.internal.h;
import xb.f0;
import xb.l;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@kotlin.Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0004H&¢\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\bH&¢\u0006\u0004\b\u000b\u0010\nR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\f8&X§\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0001\u0002\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcoil/decode/ImageSource;", "Ljava/io/Closeable;", "<init>", "()V", "Lxb/l;", "source", "()Lxb/l;", "sourceOrNull", "Lxb/f0;", "file", "()Lxb/f0;", "fileOrNull", "Lcoil/decode/ImageSource$Metadata;", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "getMetadata$annotations", TtmlNode.TAG_METADATA, "Lxb/s;", "getFileSystem", "()Lxb/s;", "fileSystem", "Metadata", "Lcoil/decode/FileImageSource;", "Lcoil/decode/SourceImageSource;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class ImageSource implements Closeable {

    @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcoil/decode/ImageSource$Metadata;", "", "()V", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Metadata {
    }

    public /* synthetic */ ImageSource(h hVar) {
        this();
    }

    public static /* synthetic */ void getMetadata$annotations() {
    }

    public abstract f0 file();

    public abstract f0 fileOrNull();

    public abstract s getFileSystem();

    public abstract Metadata getMetadata();

    public abstract l source();

    public abstract l sourceOrNull();

    private ImageSource() {
    }
}
