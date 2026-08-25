package androidx.media3.exoplayer.dash.manifest;

import ac.b;
import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.dash.DashSegmentIndex;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Representation {
    public static final long REVISION_ID_DEFAULT = -1;
    public final h1 baseUrls;
    public final List<Descriptor> essentialProperties;
    public final Format format;
    public final List<Descriptor> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;
    public final List<Descriptor> supplementalProperties;

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        final SegmentBase.MultiSegmentBase segmentBase;

        public MultiSegmentRepresentation(long j10, Format format, List<BaseUrl> list, SegmentBase.MultiSegmentBase multiSegmentBase, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
            super(j10, format, list, multiSegmentBase, list2, list3, list4);
            this.segmentBase = multiSegmentBase;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getAvailableSegmentCount(long j10, long j11) {
            return this.segmentBase.getAvailableSegmentCount(j10, j11);
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public String getCacheKey() {
            return null;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getDurationUs(long j10, long j11) {
            return this.segmentBase.getSegmentDurationUs(j10, j11);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getFirstAvailableSegmentNum(long j10, long j11) {
            return this.segmentBase.getFirstAvailableSegmentNum(j10, j11);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return null;
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getNextSegmentAvailableTimeUs(long j10, long j11) {
            return this.segmentBase.getNextSegmentAvailableTimeUs(j10, j11);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getSegmentCount(long j10) {
            return this.segmentBase.getSegmentCount(j10);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getSegmentNum(long j10, long j11) {
            return this.segmentBase.getSegmentNum(j10, j11);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(long j10) {
            return this.segmentBase.getSegmentUrl(this, j10);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public long getTimeUs(long j10) {
            return this.segmentBase.getSegmentTimeUs(j10);
        }

        @Override // androidx.media3.exoplayer.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }

    public static class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public SingleSegmentRepresentation(long j10, Format format, List<BaseUrl> list, SegmentBase.SingleSegmentBase singleSegmentBase, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4, String str, long j11) {
            super(j10, format, list, singleSegmentBase, list2, list3, list4);
            this.uri = Uri.parse(list.get(0).url);
            RangedUri index = singleSegmentBase.getIndex();
            this.indexUri = index;
            this.cacheKey = str;
            this.contentLength = j11;
            this.segmentIndex = index != null ? null : new SingleSegmentIndex(new RangedUri(null, 0L, j11));
        }

        public static SingleSegmentRepresentation newInstance(long j10, Format format, String str, long j11, long j12, long j13, long j14, List<Descriptor> list, String str2, long j15) {
            SegmentBase.SingleSegmentBase singleSegmentBase = new SegmentBase.SingleSegmentBase(new RangedUri(null, j11, (j12 - j11) + 1), 1L, 0L, j13, (j14 - j13) + 1);
            o3 o3VarS = h1.s(new BaseUrl(str));
            o3 o3Var = o3.f14078o;
            return new SingleSegmentRepresentation(j10, format, o3VarS, singleSegmentBase, list, o3Var, o3Var, str2, j15);
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public String getCacheKey() {
            return this.cacheKey;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        @Override // androidx.media3.exoplayer.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return this.indexUri;
        }
    }

    public static Representation newInstance(long j10, Format format, List<BaseUrl> list, SegmentBase segmentBase) {
        f1 f1Var = h1.f14020l;
        o3 o3Var = o3.f14078o;
        return newInstance(j10, format, list, segmentBase, null, o3Var, o3Var, null);
    }

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    private Representation(long j10, Format format, List<BaseUrl> list, SegmentBase segmentBase, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
        b.j(!list.isEmpty());
        this.revisionId = j10;
        this.format = format;
        this.baseUrls = h1.n(list);
        this.inbandEventStreams = list2 == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(list2);
        this.essentialProperties = list3;
        this.supplementalProperties = list4;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public static Representation newInstance(long j10, Format format, List<BaseUrl> list, SegmentBase segmentBase, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4, String str) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(j10, format, list, (SegmentBase.SingleSegmentBase) segmentBase, list2, list3, list4, str, -1L);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(j10, format, list, (SegmentBase.MultiSegmentBase) segmentBase, list2, list3, list4);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }
}
