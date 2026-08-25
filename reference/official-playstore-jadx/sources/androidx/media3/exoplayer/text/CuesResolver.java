package androidx.media3.exoplayer.text;

import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.h1;

/* JADX INFO: loaded from: classes3.dex */
interface CuesResolver {
    boolean addCues(CuesWithTiming cuesWithTiming, long j10);

    void clear();

    void discardCuesBeforeTimeUs(long j10);

    h1 getCuesAtTimeUs(long j10);

    long getNextCueChangeTimeUs(long j10);

    long getPreviousCueChangeTimeUs(long j10);
}
