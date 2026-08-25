package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import com.google.common.collect.h1;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CuesWithTiming {
    public final h1 cues;
    public final long durationUs;
    public final long endTimeUs;
    public final long startTimeUs;

    public CuesWithTiming(List<Cue> list, long j10, long j11) {
        this.cues = h1.n(list);
        this.startTimeUs = j10;
        this.durationUs = j11;
        long j12 = C.TIME_UNSET;
        if (j10 != C.TIME_UNSET && j11 != C.TIME_UNSET) {
            j12 = j10 + j11;
        }
        this.endTimeUs = j12;
    }
}
