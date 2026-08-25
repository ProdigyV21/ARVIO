package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Log;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheSpan;
import androidx.media3.extractor.ChunkIndex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public final class CachedRegionTracker implements Cache.Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final TreeSet<Region> regions = new TreeSet<>();
    private final Region lookupRegion = new Region(0, 0);

    public static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j10, long j11) {
            this.startOffset = j10;
            this.endOffset = j11;
        }

        @Override // java.lang.Comparable
        public int compareTo(Region region) {
            return Long.compare(this.startOffset, region.startOffset);
        }
    }

    public CachedRegionTracker(Cache cache, String str, ChunkIndex chunkIndex) {
        this.cache = cache;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex;
        synchronized (this) {
            try {
                Iterator<CacheSpan> itDescendingIterator = cache.addListener(str, this).descendingIterator();
                while (itDescendingIterator.hasNext()) {
                    mergeSpan(itDescendingIterator.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        long j10 = cacheSpan.position;
        Region region = new Region(j10, cacheSpan.length + j10);
        Region regionFloor = this.regions.floor(region);
        Region regionCeiling = this.regions.ceiling(region);
        boolean zRegionsConnect = regionsConnect(regionFloor, region);
        if (regionsConnect(region, regionCeiling)) {
            if (zRegionsConnect) {
                regionFloor.endOffset = regionCeiling.endOffset;
                regionFloor.endOffsetIndex = regionCeiling.endOffsetIndex;
            } else {
                region.endOffset = regionCeiling.endOffset;
                region.endOffsetIndex = regionCeiling.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(regionCeiling);
            return;
        }
        if (!zRegionsConnect) {
            int iBinarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (iBinarySearch < 0) {
                iBinarySearch = (-iBinarySearch) - 2;
            }
            region.endOffsetIndex = iBinarySearch;
            this.regions.add(region);
            return;
        }
        regionFloor.endOffset = region.endOffset;
        int i10 = regionFloor.endOffsetIndex;
        while (true) {
            ChunkIndex chunkIndex = this.chunkIndex;
            if (i10 >= chunkIndex.length - 1) {
                break;
            }
            int i11 = i10 + 1;
            if (chunkIndex.offsets[i11] > regionFloor.endOffset) {
                break;
            } else {
                i10 = i11;
            }
        }
        regionFloor.endOffsetIndex = i10;
    }

    private boolean regionsConnect(Region region, Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }

    public synchronized int getRegionEndTimeMs(long j10) {
        int i10;
        Region region = this.lookupRegion;
        region.startOffset = j10;
        Region regionFloor = this.regions.floor(region);
        if (regionFloor != null) {
            long j11 = regionFloor.endOffset;
            if (j10 <= j11 && (i10 = regionFloor.endOffsetIndex) != -1) {
                ChunkIndex chunkIndex = this.chunkIndex;
                if (i10 == chunkIndex.length - 1) {
                    if (j11 == chunkIndex.offsets[i10] + ((long) chunkIndex.sizes[i10])) {
                        return -2;
                    }
                }
                return (int) ((chunkIndex.timesUs[i10] + (((j11 - chunkIndex.offsets[i10]) * chunkIndex.durationsUs[i10]) / ((long) chunkIndex.sizes[i10]))) / 1000);
            }
        }
        return -1;
    }

    @Override // androidx.media3.datasource.cache.Cache.Listener
    public synchronized void onSpanAdded(Cache cache, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    @Override // androidx.media3.datasource.cache.Cache.Listener
    public synchronized void onSpanRemoved(Cache cache, CacheSpan cacheSpan) {
        long j10 = cacheSpan.position;
        Region region = new Region(j10, cacheSpan.length + j10);
        Region regionFloor = this.regions.floor(region);
        if (regionFloor == null) {
            Log.e(TAG, "Removed a span we were not aware of");
            return;
        }
        this.regions.remove(regionFloor);
        long j11 = regionFloor.startOffset;
        long j12 = region.startOffset;
        if (j11 < j12) {
            Region region2 = new Region(j11, j12);
            int iBinarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region2.endOffset);
            if (iBinarySearch < 0) {
                iBinarySearch = (-iBinarySearch) - 2;
            }
            region2.endOffsetIndex = iBinarySearch;
            this.regions.add(region2);
        }
        long j13 = regionFloor.endOffset;
        long j14 = region.endOffset;
        if (j13 > j14) {
            Region region3 = new Region(j14 + 1, j13);
            region3.endOffsetIndex = regionFloor.endOffsetIndex;
            this.regions.add(region3);
        }
    }

    @Override // androidx.media3.datasource.cache.Cache.Listener
    public void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }
}
