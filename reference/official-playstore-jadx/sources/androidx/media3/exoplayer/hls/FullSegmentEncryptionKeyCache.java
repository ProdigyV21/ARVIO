package androidx.media3.exoplayer.hls;

import android.net.Uri;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class FullSegmentEncryptionKeyCache {
    private final LinkedHashMap<Uri, byte[]> backingMap;

    public FullSegmentEncryptionKeyCache(final int i10) {
        this.backingMap = new LinkedHashMap<Uri, byte[]>(i10 + 1, 1.0f, false) { // from class: androidx.media3.exoplayer.hls.FullSegmentEncryptionKeyCache.1
            @Override // java.util.LinkedHashMap
            public boolean removeEldestEntry(Map.Entry<Uri, byte[]> entry) {
                return size() > i10;
            }
        };
    }

    public boolean containsUri(Uri uri) {
        LinkedHashMap<Uri, byte[]> linkedHashMap = this.backingMap;
        uri.getClass();
        return linkedHashMap.containsKey(uri);
    }

    public byte[] get(Uri uri) {
        if (uri == null) {
            return null;
        }
        return this.backingMap.get(uri);
    }

    public byte[] put(Uri uri, byte[] bArr) {
        LinkedHashMap<Uri, byte[]> linkedHashMap = this.backingMap;
        uri.getClass();
        bArr.getClass();
        return linkedHashMap.put(uri, bArr);
    }

    public byte[] remove(Uri uri) {
        LinkedHashMap<Uri, byte[]> linkedHashMap = this.backingMap;
        uri.getClass();
        return linkedHashMap.remove(uri);
    }
}
