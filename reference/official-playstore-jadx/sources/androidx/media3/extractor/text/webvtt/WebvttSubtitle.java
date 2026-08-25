package androidx.media3.extractor.text.webvtt;

import ac.b;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.Subtitle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class WebvttSubtitle implements Subtitle {
    private final List<WebvttCueInfo> cueInfos;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCueInfo> list) {
        this.cueInfos = Collections.unmodifiableList(new ArrayList(list));
        this.cueTimesUs = new long[list.size() * 2];
        for (int i10 = 0; i10 < list.size(); i10++) {
            WebvttCueInfo webvttCueInfo = list.get(i10);
            int i11 = i10 * 2;
            long[] jArr = this.cueTimesUs;
            jArr[i11] = webvttCueInfo.startTimeUs;
            jArr[i11 + 1] = webvttCueInfo.endTimeUs;
        }
        long[] jArr2 = this.cueTimesUs;
        long[] jArrCopyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getCues$0(WebvttCueInfo webvttCueInfo, WebvttCueInfo webvttCueInfo2) {
        return Long.compare(webvttCueInfo.startTimeUs, webvttCueInfo2.startTimeUs);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long j10) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i10 = 0; i10 < this.cueInfos.size(); i10++) {
            long[] jArr = this.cueTimesUs;
            int i11 = i10 * 2;
            if (jArr[i11] <= j10 && j10 < jArr[i11 + 1]) {
                WebvttCueInfo webvttCueInfo = this.cueInfos.get(i10);
                Cue cue = webvttCueInfo.cue;
                if (cue.line == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(cue);
                }
            }
        }
        Collections.sort(arrayList2, new a(1));
        for (int i12 = 0; i12 < arrayList2.size(); i12++) {
            arrayList.add(((WebvttCueInfo) arrayList2.get(i12)).cue.buildUpon().setLine((-1) - i12, 1).build());
        }
        return arrayList;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i10) {
        b.j(i10 >= 0);
        b.j(i10 < this.sortedCueTimesUs.length);
        return this.sortedCueTimesUs[i10];
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j10) {
        int iBinarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j10, false, false);
        if (iBinarySearchCeil < this.sortedCueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }
}
