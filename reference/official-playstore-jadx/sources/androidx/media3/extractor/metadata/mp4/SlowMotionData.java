package androidx.media3.extractor.metadata.mp4;

import ac.b;
import androidx.compose.ui.node.d;
import androidx.media3.common.Format;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.g;
import androidx.media3.common.util.Util;
import com.google.common.collect.l0;
import j$.util.Objects;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class SlowMotionData implements Metadata.Entry {
    public final List<Segment> segments;

    public static final class Segment {
        public static final Comparator<Segment> BY_START_THEN_END_THEN_DIVISOR = new d(5);
        public final long endTimeMs;
        public final int speedDivisor;
        public final long startTimeMs;

        public Segment(long j10, long j11, int i10) {
            b.j(j10 < j11);
            this.startTimeMs = j10;
            this.endTimeMs = j11;
            this.speedDivisor = i10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ int lambda$static$0(Segment segment, Segment segment2) {
            return l0.f14044a.b(segment.startTimeMs, segment2.startTimeMs).b(segment.endTimeMs, segment2.endTimeMs).a(segment.speedDivisor, segment2.speedDivisor).f();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Segment.class == obj.getClass()) {
                Segment segment = (Segment) obj;
                if (this.startTimeMs == segment.startTimeMs && this.endTimeMs == segment.endTimeMs && this.speedDivisor == segment.speedDivisor) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor));
        }

        public String toString() {
            return Util.formatInvariant("Segment: startTimeMs=%d, endTimeMs=%d, speedDivisor=%d", Long.valueOf(this.startTimeMs), Long.valueOf(this.endTimeMs), Integer.valueOf(this.speedDivisor));
        }
    }

    public SlowMotionData(List<Segment> list) {
        this.segments = list;
        b.j(!doSegmentsOverlap(list));
    }

    private static boolean doSegmentsOverlap(List<Segment> list) {
        if (list.isEmpty()) {
            return false;
        }
        long j10 = list.get(0).endTimeMs;
        for (int i10 = 1; i10 < list.size(); i10++) {
            if (list.get(i10).startTimeMs < j10) {
                return true;
            }
            j10 = list.get(i10).endTimeMs;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SlowMotionData.class != obj.getClass()) {
            return false;
        }
        return this.segments.equals(((SlowMotionData) obj).segments);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ byte[] getWrappedMetadataBytes() {
        return g.a(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ Format getWrappedMetadataFormat() {
        return g.b(this);
    }

    public int hashCode() {
        return this.segments.hashCode();
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ void populateMediaMetadata(MediaMetadata.Builder builder) {
        g.c(this, builder);
    }

    public String toString() {
        return "SlowMotion: segments=" + this.segments;
    }
}
