package android.graphics;

import android.content.Utils;
import android.graphics.ImageSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.util.concurrent.r0;
import java.io.File;
import kotlin.Metadata;
import r7.a;
import v1.i;
import xb.f0;
import xb.h0;
import xb.i0;
import xb.l;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\rJ\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0004\b\u0014\u0010\rJ\u000f\u0010\u0015\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0015\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u001dR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u001eR\u0014\u0010\"\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcoil/decode/SourceImageSource;", "Lcoil/decode/ImageSource;", "Lxb/l;", "source", "Lkotlin/Function0;", "Ljava/io/File;", "cacheDirectoryFactory", "Lcoil/decode/ImageSource$Metadata;", TtmlNode.TAG_METADATA, "<init>", "(Lxb/l;Lr7/a;Lcoil/decode/ImageSource$Metadata;)V", "Lxb/f0;", "createTempFile", "()Lxb/f0;", "Lx6/t0;", "assertNotClosed", "()V", "()Lxb/l;", "sourceOrNull", "file", "fileOrNull", "close", "Lcoil/decode/ImageSource$Metadata;", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "", "isClosed", "Z", "Lxb/l;", "Lr7/a;", "Lxb/f0;", "Lxb/s;", "getFileSystem", "()Lxb/s;", "fileSystem", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SourceImageSource extends ImageSource {
    private a<? extends File> cacheDirectoryFactory;
    private f0 file;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private l source;

    public SourceImageSource(l lVar, a<? extends File> aVar, ImageSource.Metadata metadata) {
        super(null);
        this.metadata = metadata;
        this.source = lVar;
        this.cacheDirectoryFactory = aVar;
    }

    private final void assertNotClosed() {
        if (this.isClosed) {
            throw new IllegalStateException("closed");
        }
    }

    private final f0 createTempFile() {
        File file = (File) this.cacheDirectoryFactory.invoke();
        if (!file.isDirectory()) {
            throw new IllegalStateException("cacheDirectory must be a directory.");
        }
        String str = f0.f22733l;
        return i.h(File.createTempFile("tmp", null, file));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            this.isClosed = true;
            l lVar = this.source;
            if (lVar != null) {
                Utils.closeQuietly(lVar);
            }
            f0 f0Var = this.file;
            if (f0Var != null) {
                getFileSystem().delete(f0Var);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.graphics.ImageSource
    public synchronized f0 file() {
        Throwable th;
        assertNotClosed();
        f0 f0Var = this.file;
        if (f0Var != null) {
            return f0Var;
        }
        f0 f0VarCreateTempFile = createTempFile();
        h0 h0Var = new h0(getFileSystem().sink(f0VarCreateTempFile, false));
        try {
            h0Var.z0(this.source);
            try {
                h0Var.close();
                th = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            try {
                h0Var.close();
            } catch (Throwable th4) {
                r0.a(th3, th4);
            }
            th = th3;
        }
        if (th != null) {
            throw th;
        }
        this.source = null;
        this.file = f0VarCreateTempFile;
        this.cacheDirectoryFactory = null;
        return f0VarCreateTempFile;
    }

    @Override // android.graphics.ImageSource
    public synchronized f0 fileOrNull() {
        assertNotClosed();
        return this.file;
    }

    @Override // android.graphics.ImageSource
    public s getFileSystem() {
        return s.SYSTEM;
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
    public l sourceOrNull() {
        return source();
    }
}
