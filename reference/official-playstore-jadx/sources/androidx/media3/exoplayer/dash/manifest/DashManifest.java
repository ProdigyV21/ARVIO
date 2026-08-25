package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.offline.FilterableManifest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DashManifest implements FilterableManifest<DashManifest> {
    public final long availabilityStartTimeMs;
    public final long durationMs;
    public final boolean dynamic;
    public final Uri location;
    public final long minBufferTimeMs;
    public final long minUpdatePeriodMs;
    private final List<Period> periods;
    public final ProgramInformation programInformation;
    public final long publishTimeMs;
    public final ServiceDescriptionElement serviceDescription;
    public final long suggestedPresentationDelayMs;
    public final long timeShiftBufferDepthMs;
    public final UtcTimingElement utcTiming;

    public DashManifest(long j10, long j11, long j12, boolean z, long j13, long j14, long j15, long j16, ProgramInformation programInformation, UtcTimingElement utcTimingElement, ServiceDescriptionElement serviceDescriptionElement, Uri uri, List<Period> list) {
        this.availabilityStartTimeMs = j10;
        this.durationMs = j11;
        this.minBufferTimeMs = j12;
        this.dynamic = z;
        this.minUpdatePeriodMs = j13;
        this.timeShiftBufferDepthMs = j14;
        this.suggestedPresentationDelayMs = j15;
        this.publishTimeMs = j16;
        this.programInformation = programInformation;
        this.utcTiming = utcTimingElement;
        this.location = uri;
        this.serviceDescription = serviceDescriptionElement;
        this.periods = list == null ? Collections.EMPTY_LIST : list;
    }

    private static ArrayList<AdaptationSet> copyAdaptationSets(List<AdaptationSet> list, LinkedList<StreamKey> linkedList) {
        StreamKey streamKeyPoll = linkedList.poll();
        int i10 = streamKeyPoll.periodIndex;
        ArrayList<AdaptationSet> arrayList = new ArrayList<>();
        do {
            int i11 = streamKeyPoll.groupIndex;
            AdaptationSet adaptationSet = list.get(i11);
            List<Representation> list2 = adaptationSet.representations;
            ArrayList arrayList2 = new ArrayList();
            do {
                arrayList2.add(list2.get(streamKeyPoll.streamIndex));
                streamKeyPoll = linkedList.poll();
                if (streamKeyPoll.periodIndex != i10) {
                    break;
                }
            } while (streamKeyPoll.groupIndex == i11);
            arrayList.add(new AdaptationSet(adaptationSet.id, adaptationSet.type, arrayList2, adaptationSet.accessibilityDescriptors, adaptationSet.essentialProperties, adaptationSet.supplementalProperties));
        } while (streamKeyPoll.periodIndex == i10);
        linkedList.addFirst(streamKeyPoll);
        return arrayList;
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public /* bridge */ /* synthetic */ DashManifest copy(List list) {
        return copy((List<StreamKey>) list);
    }

    public final Period getPeriod(int i10) {
        return this.periods.get(i10);
    }

    public final int getPeriodCount() {
        return this.periods.size();
    }

    public final long getPeriodDurationMs(int i10) {
        long j10;
        long j11;
        if (i10 == this.periods.size() - 1) {
            j10 = this.durationMs;
            if (j10 == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            j11 = this.periods.get(i10).startMs;
        } else {
            j10 = this.periods.get(i10 + 1).startMs;
            j11 = this.periods.get(i10).startMs;
        }
        return j10 - j11;
    }

    public final long getPeriodDurationUs(int i10) {
        return Util.msToUs(getPeriodDurationMs(i10));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public final DashManifest copy(List<StreamKey> list) {
        long j10;
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList);
        linkedList.add(new StreamKey(-1, -1, -1));
        ArrayList arrayList = new ArrayList();
        long j11 = 0;
        int i10 = 0;
        while (true) {
            int periodCount = getPeriodCount();
            j10 = C.TIME_UNSET;
            if (i10 >= periodCount) {
                break;
            }
            if (((StreamKey) linkedList.peek()).periodIndex != i10) {
                long periodDurationMs = getPeriodDurationMs(i10);
                if (periodDurationMs != C.TIME_UNSET) {
                    j11 += periodDurationMs;
                }
            } else {
                Period period = getPeriod(i10);
                arrayList.add(new Period(period.id, period.startMs - j11, copyAdaptationSets(period.adaptationSets, linkedList), period.eventStreams));
            }
            i10++;
        }
        long j12 = this.durationMs;
        if (j12 != C.TIME_UNSET) {
            j10 = j12 - j11;
        }
        return new DashManifest(this.availabilityStartTimeMs, j10, this.minBufferTimeMs, this.dynamic, this.minUpdatePeriodMs, this.timeShiftBufferDepthMs, this.suggestedPresentationDelayMs, this.publishTimeMs, this.programInformation, this.utcTiming, this.serviceDescription, this.location, arrayList);
    }
}
