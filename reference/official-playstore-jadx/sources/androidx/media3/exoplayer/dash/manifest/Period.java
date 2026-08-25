package androidx.media3.exoplayer.dash.manifest;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final Descriptor assetIdentifier;
    public final List<EventStream> eventStreams;
    public final String id;
    public final long startMs;

    public Period(String str, long j10, List<AdaptationSet> list) {
        this(str, j10, list, Collections.EMPTY_LIST, null);
    }

    public int getAdaptationSetIndex(int i10) {
        int size = this.adaptationSets.size();
        for (int i11 = 0; i11 < size; i11++) {
            if (this.adaptationSets.get(i11).type == i10) {
                return i11;
            }
        }
        return -1;
    }

    public Period(String str, long j10, List<AdaptationSet> list, List<EventStream> list2) {
        this(str, j10, list, list2, null);
    }

    public Period(String str, long j10, List<AdaptationSet> list, List<EventStream> list2, Descriptor descriptor) {
        this.id = str;
        this.startMs = j10;
        this.adaptationSets = Collections.unmodifiableList(list);
        this.eventStreams = Collections.unmodifiableList(list2);
        this.assetIdentifier = descriptor;
    }
}
