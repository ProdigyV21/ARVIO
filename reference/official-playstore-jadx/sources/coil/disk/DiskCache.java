package coil.disk;

import android.os.StatFs;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.Closeable;
import java.io.File;
import ka.x0;
import kotlin.Metadata;
import qb.d;
import v1.i;
import x6.e;
import xb.f0;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0003$%&J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H§\u0002¢\u0006\u0004\b\u0007\u0010\u0006J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH'¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0016\u001a\u00020\u00128&X§\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0019\u001a\u00020\u00128&X§\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0017\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u001a8&X§\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010#\u001a\u00020\u001f8&X§\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u0011\u001a\u0004\b \u0010!ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006'À\u0006\u0001"}, d2 = {"Lcoil/disk/DiskCache;", "", "", "key", "Lcoil/disk/DiskCache$Snapshot;", "openSnapshot", "(Ljava/lang/String;)Lcoil/disk/DiskCache$Snapshot;", "get", "Lcoil/disk/DiskCache$Editor;", "openEditor", "(Ljava/lang/String;)Lcoil/disk/DiskCache$Editor;", "edit", "", "remove", "(Ljava/lang/String;)Z", "Lx6/t0;", "clear", "()V", "", "getSize", "()J", "getSize$annotations", ContentDisposition.Parameters.Size, "getMaxSize", "getMaxSize$annotations", "maxSize", "Lxb/f0;", "getDirectory", "()Lxb/f0;", "getDirectory$annotations", "directory", "Lxb/s;", "getFileSystem", "()Lxb/s;", "getFileSystem$annotations", "fileSystem", "Builder", "Editor", "Snapshot", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface DiskCache {

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\bJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0014\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0013J\u0015\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u001eR\u0016\u0010\u000e\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u001fR\u0016\u0010\u0012\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010 R\u0016\u0010\u0014\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010 R\u0016\u0010\u0015\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010 R\u0016\u0010\u0018\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010!¨\u0006\""}, d2 = {"Lcoil/disk/DiskCache$Builder;", "", "<init>", "()V", "Ljava/io/File;", "directory", "(Ljava/io/File;)Lcoil/disk/DiskCache$Builder;", "Lxb/f0;", "(Lxb/f0;)Lcoil/disk/DiskCache$Builder;", "Lxb/s;", "fileSystem", "(Lxb/s;)Lcoil/disk/DiskCache$Builder;", "", "percent", "maxSizePercent", "(D)Lcoil/disk/DiskCache$Builder;", "", ContentDisposition.Parameters.Size, "minimumMaxSizeBytes", "(J)Lcoil/disk/DiskCache$Builder;", "maximumMaxSizeBytes", "maxSizeBytes", "Lka/f0;", "dispatcher", "cleanupDispatcher", "(Lka/f0;)Lcoil/disk/DiskCache$Builder;", "Lcoil/disk/DiskCache;", "build", "()Lcoil/disk/DiskCache;", "Lxb/f0;", "Lxb/s;", "D", "J", "Lka/f0;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Builder {
        private f0 directory;
        private long maxSizeBytes;
        private s fileSystem = s.SYSTEM;
        private double maxSizePercent = 0.02d;
        private long minimumMaxSizeBytes = 10485760;
        private long maximumMaxSizeBytes = 262144000;
        private ka.f0 cleanupDispatcher = x0.f19655d;

        public final DiskCache build() {
            long jO;
            f0 f0Var = this.directory;
            if (f0Var == null) {
                throw new IllegalStateException("directory == null");
            }
            if (this.maxSizePercent > 0.0d) {
                try {
                    File file = f0Var.toFile();
                    file.mkdir();
                    StatFs statFs = new StatFs(file.getAbsolutePath());
                    jO = d.o((long) (this.maxSizePercent * statFs.getBlockCountLong() * statFs.getBlockSizeLong()), this.minimumMaxSizeBytes, this.maximumMaxSizeBytes);
                } catch (Exception unused) {
                    jO = this.minimumMaxSizeBytes;
                }
            } else {
                jO = this.maxSizeBytes;
            }
            return new RealDiskCache(jO, f0Var, this.fileSystem, this.cleanupDispatcher);
        }

        public final Builder cleanupDispatcher(ka.f0 dispatcher) {
            this.cleanupDispatcher = dispatcher;
            return this;
        }

        public final Builder directory(File directory) {
            String str = f0.f22733l;
            return directory(i.h(directory));
        }

        public final Builder fileSystem(s fileSystem) {
            this.fileSystem = fileSystem;
            return this;
        }

        public final Builder maxSizeBytes(long size) {
            if (size <= 0) {
                throw new IllegalArgumentException("size must be > 0.");
            }
            this.maxSizePercent = 0.0d;
            this.maxSizeBytes = size;
            return this;
        }

        public final Builder maxSizePercent(double percent) {
            if (0.0d > percent || percent > 1.0d) {
                throw new IllegalArgumentException("size must be in the range [0.0, 1.0].");
            }
            this.maxSizeBytes = 0L;
            this.maxSizePercent = percent;
            return this;
        }

        public final Builder maximumMaxSizeBytes(long size) {
            if (size <= 0) {
                throw new IllegalArgumentException("size must be > 0.");
            }
            this.maximumMaxSizeBytes = size;
            return this;
        }

        public final Builder minimumMaxSizeBytes(long size) {
            if (size <= 0) {
                throw new IllegalArgumentException("size must be > 0.");
            }
            this.minimumMaxSizeBytes = size;
            return this;
        }

        public final Builder directory(f0 directory) {
            this.directory = directory;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0005H'¢\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\t\u001a\u00020\u0002H&¢\u0006\u0004\b\t\u0010\u0004R\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lcoil/disk/DiskCache$Editor;", "", "Lx6/t0;", "commit", "()V", "Lcoil/disk/DiskCache$Snapshot;", "commitAndOpenSnapshot", "()Lcoil/disk/DiskCache$Snapshot;", "commitAndGet", "abort", "Lxb/f0;", "getMetadata", "()Lxb/f0;", TtmlNode.TAG_METADATA, "getData", "data", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Editor {
        void abort();

        void commit();

        @e
        Snapshot commitAndGet();

        Snapshot commitAndOpenSnapshot();

        f0 getData();

        f0 getMetadata();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00060\u0001j\u0002`\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\t\u001a\u0004\u0018\u00010\u0006H'¢\u0006\u0004\b\t\u0010\bR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lcoil/disk/DiskCache$Snapshot;", "Ljava/io/Closeable;", "Lokio/Closeable;", "Lx6/t0;", "close", "()V", "Lcoil/disk/DiskCache$Editor;", "closeAndOpenEditor", "()Lcoil/disk/DiskCache$Editor;", "closeAndEdit", "Lxb/f0;", "getMetadata", "()Lxb/f0;", TtmlNode.TAG_METADATA, "getData", "data", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Snapshot extends Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();

        @e
        Editor closeAndEdit();

        Editor closeAndOpenEditor();

        f0 getData();

        f0 getMetadata();
    }

    void clear();

    @e
    Editor edit(String key);

    @e
    Snapshot get(String key);

    f0 getDirectory();

    s getFileSystem();

    long getMaxSize();

    long getSize();

    Editor openEditor(String key);

    Snapshot openSnapshot(String key);

    boolean remove(String key);
}
