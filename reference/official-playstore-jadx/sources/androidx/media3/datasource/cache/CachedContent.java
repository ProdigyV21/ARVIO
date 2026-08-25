package androidx.media3.datasource.cache;

import androidx.compose.foundation.c;
import androidx.media3.common.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
final class CachedContent {
    private static final String TAG = "CachedContent";
    private final TreeSet<SimpleCacheSpan> cachedSpans;
    public final int id;
    public final String key;
    private final ArrayList<Range> lockedRanges;
    private DefaultContentMetadata metadata;

    public static final class Range {
        public final long length;
        public final long position;

        public Range(long j10, long j11) {
            this.position = j10;
            this.length = j11;
        }

        public boolean contains(long j10, long j11) {
            long j12 = this.length;
            if (j12 == -1) {
                return j10 >= this.position;
            }
            if (j11 == -1) {
                return false;
            }
            long j13 = this.position;
            return j13 <= j10 && j10 + j11 <= j13 + j12;
        }

        public boolean intersects(long j10, long j11) {
            long j12 = this.position;
            if (j12 > j10) {
                return j11 == -1 || j10 + j11 > j12;
            }
            long j13 = this.length;
            return j13 == -1 || j12 + j13 > j10;
        }
    }

    public CachedContent(int i10, String str) {
        this(i10, str, DefaultContentMetadata.EMPTY);
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public boolean applyMetadataMutations(ContentMetadataMutations contentMetadataMutations) {
        this.metadata = this.metadata.copyWithMutationsApplied(contentMetadataMutations);
        return !r2.equals(r0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CachedContent.class == obj.getClass()) {
            CachedContent cachedContent = (CachedContent) obj;
            if (this.id == cachedContent.id && this.key.equals(cachedContent.key) && this.cachedSpans.equals(cachedContent.cachedSpans) && this.metadata.equals(cachedContent.metadata)) {
                return true;
            }
        }
        return false;
    }

    public long getCachedBytesLength(long j10, long j11) {
        ac.b.j(j10 >= 0);
        ac.b.j(j11 >= 0);
        SimpleCacheSpan span = getSpan(j10, j11);
        if (span.isHoleSpan()) {
            return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length, j11);
        }
        long j12 = j10 + j11;
        long j13 = j12 >= 0 ? j12 : Long.MAX_VALUE;
        long jMax = span.position + span.length;
        if (jMax < j13) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                long j14 = simpleCacheSpan.position;
                if (j14 > jMax) {
                    break;
                }
                jMax = Math.max(jMax, j14 + simpleCacheSpan.length);
                if (jMax >= j13) {
                    break;
                }
            }
        }
        return Math.min(jMax - j10, j11);
    }

    public DefaultContentMetadata getMetadata() {
        return this.metadata;
    }

    public SimpleCacheSpan getSpan(long j10, long j11) {
        SimpleCacheSpan simpleCacheSpanCreateLookup = SimpleCacheSpan.createLookup(this.key, j10);
        SimpleCacheSpan simpleCacheSpanFloor = this.cachedSpans.floor(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanFloor != null && simpleCacheSpanFloor.position + simpleCacheSpanFloor.length > j10) {
            return simpleCacheSpanFloor;
        }
        SimpleCacheSpan simpleCacheSpanCeiling = this.cachedSpans.ceiling(simpleCacheSpanCreateLookup);
        if (simpleCacheSpanCeiling != null) {
            long j12 = simpleCacheSpanCeiling.position - j10;
            j11 = j11 == -1 ? j12 : Math.min(j12, j11);
        }
        return SimpleCacheSpan.createHole(this.key, j10, j11);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public int hashCode() {
        return this.metadata.hashCode() + c.c(this.id * 31, 31, this.key);
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean isFullyLocked(long j10, long j11) {
        for (int i10 = 0; i10 < this.lockedRanges.size(); i10++) {
            if (this.lockedRanges.get(i10).contains(j10, j11)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullyUnlocked() {
        return this.lockedRanges.isEmpty();
    }

    public boolean lockRange(long j10, long j11) {
        for (int i10 = 0; i10 < this.lockedRanges.size(); i10++) {
            if (this.lockedRanges.get(i10).intersects(j10, j11)) {
                return false;
            }
        }
        this.lockedRanges.add(new Range(j10, j11));
        return true;
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        File file = cacheSpan.file;
        if (file == null) {
            return true;
        }
        file.delete();
        return true;
    }

    public SimpleCacheSpan setLastTouchTimestamp(SimpleCacheSpan simpleCacheSpan, long j10, boolean z) {
        long j11;
        ac.b.s(this.cachedSpans.remove(simpleCacheSpan));
        File file = simpleCacheSpan.file;
        file.getClass();
        if (z) {
            File parentFile = file.getParentFile();
            parentFile.getClass();
            j11 = j10;
            File cacheFile = SimpleCacheSpan.getCacheFile(parentFile, this.id, simpleCacheSpan.position, j11);
            if (file.renameTo(cacheFile)) {
                file = cacheFile;
            } else {
                Log.w(TAG, "Failed to rename " + file + " to " + cacheFile);
            }
        } else {
            j11 = j10;
        }
        SimpleCacheSpan simpleCacheSpanCopyWithFileAndLastTouchTimestamp = simpleCacheSpan.copyWithFileAndLastTouchTimestamp(file, j11);
        this.cachedSpans.add(simpleCacheSpanCopyWithFileAndLastTouchTimestamp);
        return simpleCacheSpanCopyWithFileAndLastTouchTimestamp;
    }

    public void unlockRange(long j10) {
        for (int i10 = 0; i10 < this.lockedRanges.size(); i10++) {
            if (this.lockedRanges.get(i10).position == j10) {
                this.lockedRanges.remove(i10);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public CachedContent(int i10, String str, DefaultContentMetadata defaultContentMetadata) {
        this.id = i10;
        this.key = str;
        this.metadata = defaultContentMetadata;
        this.cachedSpans = new TreeSet<>();
        this.lockedRanges = new ArrayList<>();
    }
}
