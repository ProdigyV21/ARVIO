package androidx.media3.datasource.cache;

import android.os.ConditionVariable;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.database.DatabaseIOException;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.datasource.cache.Cache;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public final class SimpleCache implements Cache {
    private static final int SUBDIRECTORY_COUNT = 10;
    private static final String TAG = "SimpleCache";
    private static final String UID_FILE_SUFFIX = ".uid";
    private static final HashSet<File> lockedCacheDirs = new HashSet<>();
    private final File cacheDir;
    private final CachedContentIndex contentIndex;
    private final CacheEvictor evictor;
    private final CacheFileMetadataIndex fileIndex;
    private Cache.CacheException initializationException;
    private final HashMap<String, ArrayList<Cache.Listener>> listeners;
    private final Random random;
    private boolean released;
    private long totalSpace;
    private final boolean touchCacheSpans;
    private long uid;

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, null, null, false, true);
    }

    private void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.contentIndex.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    private static void createCacheDirectories(File file) throws Cache.CacheException {
        if (file.mkdirs() || file.isDirectory()) {
            return;
        }
        String str = "Failed to create cache directory: " + file;
        Log.e(TAG, str);
        throw new Cache.CacheException(str);
    }

    private static long createUid(File file) throws IOException {
        long jNextLong = new SecureRandom().nextLong();
        long jAbs = jNextLong == Long.MIN_VALUE ? 0L : Math.abs(jNextLong);
        File file2 = new File(file, d.m(Long.toString(jAbs, 16), UID_FILE_SUFFIX));
        if (file2.createNewFile()) {
            return jAbs;
        }
        throw new IOException("Failed to create UID file: " + file2);
    }

    public static void delete(File file, DatabaseProvider databaseProvider) {
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                file.delete();
                return;
            }
            if (databaseProvider != null) {
                long jLoadUid = loadUid(fileArrListFiles);
                if (jLoadUid != -1) {
                    try {
                        CacheFileMetadataIndex.delete(databaseProvider, jLoadUid);
                    } catch (DatabaseIOException unused) {
                        Log.w(TAG, "Failed to delete file metadata: " + jLoadUid);
                    }
                    try {
                        CachedContentIndex.delete(databaseProvider, jLoadUid);
                    } catch (DatabaseIOException unused2) {
                        Log.w(TAG, "Failed to delete file metadata: " + jLoadUid);
                    }
                }
            }
            Util.recursiveDelete(file);
        }
    }

    private SimpleCacheSpan getSpan(String str, long j10, long j11) {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createHole(str, j10, j11);
        }
        while (true) {
            span = cachedContent.getSpan(j10, j11);
            if (!span.isCached) {
                break;
            }
            File file = span.file;
            file.getClass();
            if (file.length() == span.length) {
                break;
            }
            removeStaleSpans();
        }
        return span;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initialize() {
        if (!this.cacheDir.exists()) {
            try {
                createCacheDirectories(this.cacheDir);
            } catch (Cache.CacheException e5) {
                this.initializationException = e5;
                return;
            }
        }
        File[] fileArrListFiles = this.cacheDir.listFiles();
        if (fileArrListFiles == null) {
            String str = "Failed to list cache directory files: " + this.cacheDir;
            Log.e(TAG, str);
            this.initializationException = new Cache.CacheException(str);
            return;
        }
        long jLoadUid = loadUid(fileArrListFiles);
        this.uid = jLoadUid;
        if (jLoadUid == -1) {
            try {
                this.uid = createUid(this.cacheDir);
            } catch (IOException e6) {
                String str2 = "Failed to create cache UID: " + this.cacheDir;
                Log.e(TAG, str2, e6);
                this.initializationException = new Cache.CacheException(str2, e6);
                return;
            }
        }
        try {
            this.contentIndex.initialize(this.uid);
            CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
            if (cacheFileMetadataIndex != null) {
                cacheFileMetadataIndex.initialize(this.uid);
                Map<String, CacheFileMetadata> all = this.fileIndex.getAll();
                loadDirectory(this.cacheDir, true, fileArrListFiles, all);
                this.fileIndex.removeAll(all.keySet());
            } else {
                loadDirectory(this.cacheDir, true, fileArrListFiles, null);
            }
            this.contentIndex.removeEmpty();
            try {
                this.contentIndex.store();
            } catch (IOException e10) {
                Log.e(TAG, "Storing index file failed", e10);
            }
        } catch (IOException e11) {
            String str3 = "Failed to initialize cache indices: " + this.cacheDir;
            Log.e(TAG, str3, e11);
            this.initializationException = new Cache.CacheException(str3, e11);
        }
    }

    public static synchronized boolean isCacheFolderLocked(File file) {
        return lockedCacheDirs.contains(file.getAbsoluteFile());
    }

    private void loadDirectory(File file, boolean z, File[] fileArr, Map<String, CacheFileMetadata> map) {
        long j10;
        long j11;
        if (fileArr == null || fileArr.length == 0) {
            if (z) {
                return;
            }
            file.delete();
            return;
        }
        for (File file2 : fileArr) {
            String name = file2.getName();
            if (z && name.indexOf(46) == -1) {
                loadDirectory(file2, false, file2.listFiles(), map);
            } else if (!z || (!CachedContentIndex.isIndexFile(name) && !name.endsWith(UID_FILE_SUFFIX))) {
                CacheFileMetadata cacheFileMetadataRemove = map != null ? map.remove(name) : null;
                if (cacheFileMetadataRemove != null) {
                    j10 = cacheFileMetadataRemove.length;
                    j11 = cacheFileMetadataRemove.lastTouchTimestamp;
                } else {
                    j10 = -1;
                    j11 = C.TIME_UNSET;
                }
                SimpleCacheSpan simpleCacheSpanCreateCacheEntry = SimpleCacheSpan.createCacheEntry(file2, j10, j11, this.contentIndex);
                if (simpleCacheSpanCreateCacheEntry != null) {
                    addSpan(simpleCacheSpanCreateCacheEntry);
                } else {
                    file2.delete();
                }
            }
        }
    }

    private static long loadUid(File[] fileArr) {
        int length = fileArr.length;
        for (int i10 = 0; i10 < length; i10++) {
            File file = fileArr[i10];
            String name = file.getName();
            if (name.endsWith(UID_FILE_SUFFIX)) {
                try {
                    return parseUid(name);
                } catch (NumberFormatException unused) {
                    Log.e(TAG, "Malformed UID file: " + file);
                    file.delete();
                }
            }
        }
        return -1L;
    }

    private static synchronized boolean lockFolder(File file) {
        return lockedCacheDirs.add(file.getAbsoluteFile());
    }

    private void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList<Cache.Listener> arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    private static long parseUid(String str) {
        return Long.parseLong(str.substring(0, str.indexOf(46)), 16);
    }

    private void removeSpanInternal(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        if (cachedContent == null || !cachedContent.removeSpan(cacheSpan)) {
            return;
        }
        this.totalSpace -= cacheSpan.length;
        if (this.fileIndex != null) {
            File file = cacheSpan.file;
            file.getClass();
            String name = file.getName();
            try {
                this.fileIndex.remove(name);
            } catch (IOException unused) {
                a2.w("Failed to remove file index entry for: ", name, TAG);
            }
        }
        this.contentIndex.maybeRemove(cachedContent.key);
        notifySpanRemoved(cacheSpan);
    }

    private void removeStaleSpans() {
        ArrayList arrayList = new ArrayList();
        Iterator<CachedContent> it = this.contentIndex.getAll().iterator();
        while (it.hasNext()) {
            for (SimpleCacheSpan simpleCacheSpan : it.next().getSpans()) {
                File file = simpleCacheSpan.file;
                file.getClass();
                if (file.length() != simpleCacheSpan.length) {
                    arrayList.add(simpleCacheSpan);
                }
            }
        }
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            removeSpanInternal((CacheSpan) arrayList.get(i10));
        }
    }

    private SimpleCacheSpan touchSpan(String str, SimpleCacheSpan simpleCacheSpan) {
        boolean z;
        if (!this.touchCacheSpans) {
            return simpleCacheSpan;
        }
        File file = simpleCacheSpan.file;
        file.getClass();
        String name = file.getName();
        long j10 = simpleCacheSpan.length;
        long jCurrentTimeMillis = System.currentTimeMillis();
        CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
        if (cacheFileMetadataIndex != null) {
            try {
                cacheFileMetadataIndex.set(name, j10, jCurrentTimeMillis);
            } catch (IOException unused) {
                Log.w(TAG, "Failed to update index with new touch timestamp.");
            }
            z = false;
        } else {
            z = true;
        }
        CachedContent cachedContent = this.contentIndex.get(str);
        cachedContent.getClass();
        SimpleCacheSpan lastTouchTimestamp = cachedContent.setLastTouchTimestamp(simpleCacheSpan, jCurrentTimeMillis, z);
        notifySpanTouched(simpleCacheSpan, lastTouchTimestamp);
        return lastTouchTimestamp;
    }

    private static synchronized void unlockFolder(File file) {
        lockedCacheDirs.remove(file.getAbsoluteFile());
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        try {
            ac.b.s(!this.released);
            str.getClass();
            listener.getClass();
            ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.listeners.put(str, arrayList);
            }
            arrayList.add(listener);
        } catch (Throwable th) {
            throw th;
        }
        return getCachedSpans(str);
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws Cache.CacheException {
        ac.b.s(!this.released);
        checkInitialization();
        this.contentIndex.applyContentMetadataMutations(str, contentMetadataMutations);
        try {
            this.contentIndex.store();
        } catch (IOException e5) {
            throw new Cache.CacheException(e5);
        }
    }

    public synchronized void checkInitialization() throws Cache.CacheException {
        Cache.CacheException cacheException = this.initializationException;
        if (cacheException != null) {
            throw cacheException;
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void commitFile(File file, long j10) throws Cache.CacheException {
        boolean z = true;
        ac.b.s(!this.released);
        if (file.exists()) {
            if (j10 == 0) {
                file.delete();
                return;
            }
            SimpleCacheSpan simpleCacheSpanCreateCacheEntry = SimpleCacheSpan.createCacheEntry(file, j10, this.contentIndex);
            simpleCacheSpanCreateCacheEntry.getClass();
            CachedContent cachedContent = this.contentIndex.get(simpleCacheSpanCreateCacheEntry.key);
            cachedContent.getClass();
            ac.b.s(cachedContent.isFullyLocked(simpleCacheSpanCreateCacheEntry.position, simpleCacheSpanCreateCacheEntry.length));
            long jA = b.a(cachedContent.getMetadata());
            if (jA != -1) {
                if (simpleCacheSpanCreateCacheEntry.position + simpleCacheSpanCreateCacheEntry.length > jA) {
                    z = false;
                }
                ac.b.s(z);
            }
            if (this.fileIndex == null) {
                addSpan(simpleCacheSpanCreateCacheEntry);
                this.contentIndex.store();
                notifyAll();
                return;
            }
            try {
                this.fileIndex.set(file.getName(), simpleCacheSpanCreateCacheEntry.length, simpleCacheSpanCreateCacheEntry.lastTouchTimestamp);
                addSpan(simpleCacheSpanCreateCacheEntry);
                try {
                    this.contentIndex.store();
                    notifyAll();
                    return;
                } catch (IOException e5) {
                    throw new Cache.CacheException(e5);
                }
            } catch (IOException e6) {
                throw new Cache.CacheException(e6);
            }
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized long getCacheSpace() {
        ac.b.s(!this.released);
        return this.totalSpace;
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized long getCachedBytes(String str, long j10, long j11) {
        long j12;
        long j13 = j11 == -1 ? Long.MAX_VALUE : j10 + j11;
        long j14 = j13 >= 0 ? j13 : Long.MAX_VALUE;
        long j15 = j10;
        j12 = 0;
        while (j15 < j14) {
            long cachedLength = getCachedLength(str, j15, j14 - j15);
            if (cachedLength > 0) {
                j12 += cachedLength;
            } else {
                cachedLength = -cachedLength;
            }
            j15 += cachedLength;
        }
        return j12;
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized long getCachedLength(String str, long j10, long j11) {
        CachedContent cachedContent;
        ac.b.s(!this.released);
        if (j11 == -1) {
            j11 = Long.MAX_VALUE;
        }
        cachedContent = this.contentIndex.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j10, j11) : -j11;
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        CachedContent cachedContent;
        try {
            ac.b.s(!this.released);
            cachedContent = this.contentIndex.get(str);
        } catch (Throwable th) {
            throw th;
        }
        return (cachedContent == null || cachedContent.isEmpty()) ? new TreeSet() : new TreeSet((Collection) cachedContent.getSpans());
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized ContentMetadata getContentMetadata(String str) {
        ac.b.s(!this.released);
        return this.contentIndex.getContentMetadata(str);
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized Set<String> getKeys() {
        ac.b.s(!this.released);
        return new HashSet(this.contentIndex.getKeys());
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized long getUid() {
        return this.uid;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    @Override // androidx.media3.datasource.cache.Cache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isCached(java.lang.String r3, long r4, long r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch: java.lang.Throwable -> L19
            r1 = 1
            r0 = r0 ^ r1
            ac.b.s(r0)     // Catch: java.lang.Throwable -> L19
            androidx.media3.datasource.cache.CachedContentIndex r0 = r2.contentIndex     // Catch: java.lang.Throwable -> L19
            androidx.media3.datasource.cache.CachedContent r3 = r0.get(r3)     // Catch: java.lang.Throwable -> L19
            if (r3 == 0) goto L1b
            long r3 = r3.getCachedBytesLength(r4, r6)     // Catch: java.lang.Throwable -> L19
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 < 0) goto L1b
            goto L1c
        L19:
            r3 = move-exception
            goto L1e
        L1b:
            r1 = 0
        L1c:
            monitor-exit(r2)
            return r1
        L1e:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L19
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.cache.SimpleCache.isCached(java.lang.String, long, long):boolean");
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void release() {
        File file;
        if (this.released) {
            return;
        }
        this.listeners.clear();
        removeStaleSpans();
        try {
            try {
                this.contentIndex.store();
                file = this.cacheDir;
            } catch (IOException e5) {
                Log.e(TAG, "Storing index file failed", e5);
                file = this.cacheDir;
            }
            unlockFolder(file);
            this.released = true;
        } catch (Throwable th) {
            unlockFolder(this.cacheDir);
            this.released = true;
            throw th;
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        ac.b.s(!this.released);
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        cachedContent.getClass();
        cachedContent.unlockRange(cacheSpan.position);
        this.contentIndex.maybeRemove(cachedContent.key);
        notifyAll();
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void removeListener(String str, Cache.Listener listener) {
        if (this.released) {
            return;
        }
        ArrayList<Cache.Listener> arrayList = this.listeners.get(str);
        if (arrayList != null) {
            arrayList.remove(listener);
            if (arrayList.isEmpty()) {
                this.listeners.remove(str);
            }
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void removeResource(String str) {
        ac.b.s(!this.released);
        Iterator<CacheSpan> it = getCachedSpans(str).iterator();
        while (it.hasNext()) {
            removeSpanInternal(it.next());
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized void removeSpan(CacheSpan cacheSpan) {
        ac.b.s(!this.released);
        removeSpanInternal(cacheSpan);
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized File startFile(String str, long j10, long j11) throws Throwable {
        Throwable th;
        try {
            try {
                ac.b.s(!this.released);
                checkInitialization();
                CachedContent cachedContent = this.contentIndex.get(str);
                cachedContent.getClass();
                ac.b.s(cachedContent.isFullyLocked(j10, j11));
                if (!this.cacheDir.exists()) {
                    try {
                        createCacheDirectories(this.cacheDir);
                        removeStaleSpans();
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                this.evictor.onStartFile(this, str, j10, j11);
                File file = new File(this.cacheDir, Integer.toString(this.random.nextInt(10)));
                if (!file.exists()) {
                    createCacheDirectories(file);
                }
                return SimpleCacheSpan.getCacheFile(file, cachedContent.id, j10, System.currentTimeMillis());
            } catch (Throwable th3) {
                th = th3;
                th = th;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            th = th;
            throw th;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:? -> B:10:0x001e). Please report as a decompilation issue!!! */
    @Override // androidx.media3.datasource.cache.Cache
    public synchronized CacheSpan startReadWrite(String str, long j10, long j11) throws Throwable {
        try {
            ac.b.s(!this.released);
            checkInitialization();
            while (true) {
                CacheSpan cacheSpanStartReadWriteNonBlocking = startReadWriteNonBlocking(str, j10, j11);
                long j12 = j11;
                long j13 = j10;
                String str2 = str;
                if (cacheSpanStartReadWriteNonBlocking != null) {
                    return cacheSpanStartReadWriteNonBlocking;
                }
                try {
                    wait();
                    str = str2;
                    j10 = j13;
                    j11 = j12;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    @Override // androidx.media3.datasource.cache.Cache
    public synchronized CacheSpan startReadWriteNonBlocking(String str, long j10, long j11) throws Throwable {
        try {
            try {
                ac.b.s(!this.released);
                checkInitialization();
                SimpleCacheSpan span = getSpan(str, j10, j11);
                if (span.isCached) {
                    return touchSpan(str, span);
                }
                if (this.contentIndex.getOrAdd(str).lockRange(j10, span.length)) {
                    return span;
                }
                return null;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider) {
        this(file, cacheEvictor, databaseProvider, null, false, false);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider, byte[] bArr, boolean z, boolean z5) {
        this(file, cacheEvictor, new CachedContentIndex(databaseProvider, file, bArr, z, z5), (databaseProvider == null || z5) ? null : new CacheFileMetadataIndex(databaseProvider));
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex, CacheFileMetadataIndex cacheFileMetadataIndex) {
        if (lockFolder(file)) {
            this.cacheDir = file;
            this.evictor = cacheEvictor;
            this.contentIndex = cachedContentIndex;
            this.fileIndex = cacheFileMetadataIndex;
            this.listeners = new HashMap<>();
            this.random = new Random();
            this.touchCacheSpans = cacheEvictor.requiresCacheSpanTouches();
            this.uid = -1L;
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread("ExoPlayer:SimpleCacheInit") { // from class: androidx.media3.datasource.cache.SimpleCache.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    synchronized (SimpleCache.this) {
                        conditionVariable.open();
                        SimpleCache.this.initialize();
                        SimpleCache.this.evictor.onCacheInitialized();
                    }
                }
            }.start();
            conditionVariable.block();
            return;
        }
        throw new IllegalStateException("Another SimpleCache instance uses the folder: " + file);
    }
}
