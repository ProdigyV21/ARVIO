package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* JADX INFO: loaded from: classes3.dex */
public interface TrackAwareSeekMap extends SeekMap {
    SeekMap.SeekPoints getSeekPoints(long j10, int i10);

    boolean isSeekable(int i10);
}
