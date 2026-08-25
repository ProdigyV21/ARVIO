package coil.disk;

import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.disk.DiskCache;
import coil.disk.DiskLruCache;
import io.ktor.http.ContentDisposition;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import xb.f0;
import xb.m;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0003,-.B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\f*\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\fH\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010 ¨\u0006/"}, d2 = {"Lcoil/disk/RealDiskCache;", "Lcoil/disk/DiskCache;", "", "maxSize", "Lxb/f0;", "directory", "Lxb/s;", "fileSystem", "Lka/f0;", "cleanupDispatcher", "<init>", "(JLxb/f0;Lxb/s;Lka/f0;)V", "", "hash", "(Ljava/lang/String;)Ljava/lang/String;", "key", "Lcoil/disk/DiskCache$Snapshot;", "openSnapshot", "(Ljava/lang/String;)Lcoil/disk/DiskCache$Snapshot;", "get", "Lcoil/disk/DiskCache$Editor;", "openEditor", "(Ljava/lang/String;)Lcoil/disk/DiskCache$Editor;", "edit", "", "remove", "(Ljava/lang/String;)Z", "Lx6/t0;", "clear", "()V", "J", "getMaxSize", "()J", "Lxb/f0;", "getDirectory", "()Lxb/f0;", "Lxb/s;", "getFileSystem", "()Lxb/s;", "Lcoil/disk/DiskLruCache;", "cache", "Lcoil/disk/DiskLruCache;", "getSize", ContentDisposition.Parameters.Size, "Companion", "RealEditor", "RealSnapshot", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealDiskCache implements DiskCache {
    private static final int ENTRY_DATA = 1;
    private static final int ENTRY_METADATA = 0;
    private final DiskLruCache cache;
    private final f0 directory;
    private final s fileSystem;
    private final long maxSize;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002R\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\tR\u0018\u0010\u0004\u001a\u00060\u0002R\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcoil/disk/RealDiskCache$RealEditor;", "Lcoil/disk/DiskCache$Editor;", "Lcoil/disk/DiskLruCache$Editor;", "Lcoil/disk/DiskLruCache;", "editor", "<init>", "(Lcoil/disk/DiskLruCache$Editor;)V", "Lx6/t0;", "commit", "()V", "Lcoil/disk/RealDiskCache$RealSnapshot;", "commitAndOpenSnapshot", "()Lcoil/disk/RealDiskCache$RealSnapshot;", "commitAndGet", "abort", "Lcoil/disk/DiskLruCache$Editor;", "Lxb/f0;", "getMetadata", "()Lxb/f0;", TtmlNode.TAG_METADATA, "getData", "data", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RealEditor implements DiskCache.Editor {
        private final DiskLruCache.Editor editor;

        public RealEditor(DiskLruCache.Editor editor) {
            this.editor = editor;
        }

        @Override // coil.disk.DiskCache.Editor
        public void abort() {
            this.editor.abort();
        }

        @Override // coil.disk.DiskCache.Editor
        public void commit() {
            this.editor.commit();
        }

        @Override // coil.disk.DiskCache.Editor
        public f0 getData() {
            return this.editor.file(1);
        }

        @Override // coil.disk.DiskCache.Editor
        public f0 getMetadata() {
            return this.editor.file(0);
        }

        @Override // coil.disk.DiskCache.Editor
        public RealSnapshot commitAndGet() {
            return commitAndOpenSnapshot();
        }

        @Override // coil.disk.DiskCache.Editor
        public RealSnapshot commitAndOpenSnapshot() {
            DiskLruCache.Snapshot snapshotCommitAndGet = this.editor.commitAndGet();
            if (snapshotCommitAndGet != null) {
                return new RealSnapshot(snapshotCommitAndGet);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002R\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\r\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\r\u0010\fR\u0018\u0010\u0004\u001a\u00060\u0002R\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcoil/disk/RealDiskCache$RealSnapshot;", "Lcoil/disk/DiskCache$Snapshot;", "Lcoil/disk/DiskLruCache$Snapshot;", "Lcoil/disk/DiskLruCache;", "snapshot", "<init>", "(Lcoil/disk/DiskLruCache$Snapshot;)V", "Lx6/t0;", "close", "()V", "Lcoil/disk/RealDiskCache$RealEditor;", "closeAndOpenEditor", "()Lcoil/disk/RealDiskCache$RealEditor;", "closeAndEdit", "Lcoil/disk/DiskLruCache$Snapshot;", "Lxb/f0;", "getMetadata", "()Lxb/f0;", TtmlNode.TAG_METADATA, "getData", "data", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RealSnapshot implements DiskCache.Snapshot {
        private final DiskLruCache.Snapshot snapshot;

        public RealSnapshot(DiskLruCache.Snapshot snapshot) {
            this.snapshot = snapshot;
        }

        @Override // coil.disk.DiskCache.Snapshot, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.snapshot.close();
        }

        @Override // coil.disk.DiskCache.Snapshot
        public f0 getData() {
            return this.snapshot.file(1);
        }

        @Override // coil.disk.DiskCache.Snapshot
        public f0 getMetadata() {
            return this.snapshot.file(0);
        }

        @Override // coil.disk.DiskCache.Snapshot
        public RealEditor closeAndEdit() {
            return closeAndOpenEditor();
        }

        @Override // coil.disk.DiskCache.Snapshot
        public RealEditor closeAndOpenEditor() {
            DiskLruCache.Editor editorCloseAndEdit = this.snapshot.closeAndEdit();
            if (editorCloseAndEdit != null) {
                return new RealEditor(editorCloseAndEdit);
            }
            return null;
        }
    }

    public RealDiskCache(long j10, f0 f0Var, s sVar, ka.f0 f0Var2) {
        this.maxSize = j10;
        this.directory = f0Var;
        this.fileSystem = sVar;
        this.cache = new DiskLruCache(getFileSystem(), getDirectory(), f0Var2, getMaxSize(), 1, 2);
    }

    private final String hash(String str) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes(kotlin.text.a.f19924a);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        return new m(messageDigest.digest()).i();
    }

    @Override // coil.disk.DiskCache
    public void clear() {
        this.cache.evictAll();
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Editor edit(String key) {
        return openEditor(key);
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Snapshot get(String key) {
        return openSnapshot(key);
    }

    @Override // coil.disk.DiskCache
    public f0 getDirectory() {
        return this.directory;
    }

    @Override // coil.disk.DiskCache
    public s getFileSystem() {
        return this.fileSystem;
    }

    @Override // coil.disk.DiskCache
    public long getMaxSize() {
        return this.maxSize;
    }

    @Override // coil.disk.DiskCache
    public long getSize() {
        return this.cache.size();
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Editor openEditor(String key) {
        DiskLruCache.Editor editorEdit = this.cache.edit(hash(key));
        if (editorEdit != null) {
            return new RealEditor(editorEdit);
        }
        return null;
    }

    @Override // coil.disk.DiskCache
    public DiskCache.Snapshot openSnapshot(String key) {
        DiskLruCache.Snapshot snapshot = this.cache.get(hash(key));
        if (snapshot != null) {
            return new RealSnapshot(snapshot);
        }
        return null;
    }

    @Override // coil.disk.DiskCache
    public boolean remove(String key) {
        return this.cache.remove(hash(key));
    }
}
