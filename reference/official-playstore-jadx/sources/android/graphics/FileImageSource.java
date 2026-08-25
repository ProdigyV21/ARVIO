package android.graphics;

import android.content.Utils;
import android.graphics.ImageSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Closeable;
import kotlin.Metadata;
import xb.f0;
import xb.i0;
import xb.l;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0017\u0010\u0010R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\t\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010 R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b\"\u0010#R\u0016\u0010%\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010'¨\u0006("}, d2 = {"Lcoil/decode/FileImageSource;", "Lcoil/decode/ImageSource;", "Lxb/f0;", "file", "Lxb/s;", "fileSystem", "", "diskCacheKey", "Ljava/io/Closeable;", "closeable", "Lcoil/decode/ImageSource$Metadata;", TtmlNode.TAG_METADATA, "<init>", "(Lxb/f0;Lxb/s;Ljava/lang/String;Ljava/io/Closeable;Lcoil/decode/ImageSource$Metadata;)V", "Lx6/t0;", "assertNotClosed", "()V", "Lxb/l;", "source", "()Lxb/l;", "sourceOrNull", "()Lxb/f0;", "fileOrNull", "close", "Lxb/f0;", "getFile$coil_base_release", "Lxb/s;", "getFileSystem", "()Lxb/s;", "Ljava/lang/String;", "getDiskCacheKey$coil_base_release", "()Ljava/lang/String;", "Ljava/io/Closeable;", "Lcoil/decode/ImageSource$Metadata;", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "", "isClosed", "Z", "Lxb/l;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FileImageSource extends ImageSource {
    private final Closeable closeable;
    private final String diskCacheKey;
    private final f0 file;
    private final s fileSystem;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private l source;

    public FileImageSource(f0 f0Var, s sVar, String str, Closeable closeable, ImageSource.Metadata metadata) {
        super(null);
        this.file = f0Var;
        this.fileSystem = sVar;
        this.diskCacheKey = str;
        this.closeable = closeable;
        this.metadata = metadata;
    }

    private final void assertNotClosed() {
        if (this.isClosed) {
            throw new IllegalStateException("closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            this.isClosed = true;
            l lVar = this.source;
            if (lVar != null) {
                Utils.closeQuietly(lVar);
            }
            Closeable closeable = this.closeable;
            if (closeable != null) {
                Utils.closeQuietly(closeable);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.graphics.ImageSource
    public synchronized f0 file() {
        assertNotClosed();
        return this.file;
    }

    @Override // android.graphics.ImageSource
    public f0 fileOrNull() {
        return file();
    }

    /* JADX INFO: renamed from: getDiskCacheKey$coil_base_release, reason: from getter */
    public final String getDiskCacheKey() {
        return this.diskCacheKey;
    }

    /* JADX INFO: renamed from: getFile$coil_base_release, reason: from getter */
    public final f0 getFile() {
        return this.file;
    }

    @Override // android.graphics.ImageSource
    public s getFileSystem() {
        return this.fileSystem;
    }

    @Override // android.graphics.ImageSource
    public ImageSource.Metadata getMetadata() {
        return this.metadata;
    }

    @Override // android.graphics.ImageSource
    public synchronized l source() {
        assertNotClosed();
        l lVar = this.source;
        if (lVar != null) {
            return lVar;
        }
        i0 i0Var = new i0(getFileSystem().source(this.file));
        this.source = i0Var;
        return i0Var;
    }

    @Override // android.graphics.ImageSource
    public synchronized l sourceOrNull() {
        assertNotClosed();
        return this.source;
    }
}
