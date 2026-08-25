package androidx.media3.extractor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class ChunkIndexMerger {
    private final Map<Long, ChunkIndex> chunkMap = new LinkedHashMap();

    public void add(ChunkIndex chunkIndex) {
        long[] jArr = chunkIndex.timesUs;
        if (jArr.length <= 0 || this.chunkMap.containsKey(Long.valueOf(jArr[0]))) {
            return;
        }
        this.chunkMap.put(Long.valueOf(chunkIndex.timesUs[0]), chunkIndex);
    }

    public void clear() {
        this.chunkMap.clear();
    }

    public ChunkIndex merge() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (ChunkIndex chunkIndex : this.chunkMap.values()) {
            arrayList.add(chunkIndex.sizes);
            arrayList2.add(chunkIndex.offsets);
            arrayList3.add(chunkIndex.durationsUs);
            arrayList4.add(chunkIndex.timesUs);
        }
        int[][] iArr = (int[][]) arrayList.toArray(new int[arrayList.size()][]);
        long length = 0;
        for (int[] iArr2 : iArr) {
            length += (long) iArr2.length;
        }
        int i10 = (int) length;
        ac.b.g(length, "the total number of elements (%s) in the arrays must fit in an int", length == ((long) i10));
        int[] iArr3 = new int[i10];
        int length2 = 0;
        for (int[] iArr4 : iArr) {
            System.arraycopy(iArr4, 0, iArr3, length2, iArr4.length);
            length2 += iArr4.length;
        }
        return new ChunkIndex(iArr3, qb.d.s((long[][]) arrayList2.toArray(new long[arrayList2.size()][])), qb.d.s((long[][]) arrayList3.toArray(new long[arrayList3.size()][])), qb.d.s((long[][]) arrayList4.toArray(new long[arrayList4.size()][])));
    }

    public int size() {
        return this.chunkMap.size();
    }
}
