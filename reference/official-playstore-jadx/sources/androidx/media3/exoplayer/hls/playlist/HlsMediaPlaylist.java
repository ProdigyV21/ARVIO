package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import androidx.compose.ui.node.d;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.StreamKey;
import androidx.work.impl.t;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.l1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class HlsMediaPlaylist extends HlsPlaylist {
    public static final int PLAYLIST_TYPE_EVENT = 2;
    public static final int PLAYLIST_TYPE_UNKNOWN = 0;
    public static final int PLAYLIST_TYPE_VOD = 1;
    public final int discontinuitySequence;
    public final long durationUs;
    public final boolean hasDiscontinuitySequence;
    public final boolean hasEndTag;
    public final boolean hasPositiveStartOffset;
    public final boolean hasProgramDateTime;
    public final h1 interstitials;
    public final long mediaSequence;
    public final long partTargetDurationUs;
    public final int playlistType;
    public final boolean preciseStart;
    public final DrmInitData protectionSchemes;
    public final Map<Uri, RenditionReport> renditionReports;
    public final List<Segment> segments;
    public final ServerControl serverControl;
    public final long startOffsetUs;
    public final long startTimeUs;
    public final long targetDurationUs;
    public final List<Part> trailingParts;
    public final int version;

    public static final class Interstitial {
        public static final String CUE_TRIGGER_ONCE = "ONCE";
        public static final String CUE_TRIGGER_POST = "POST";
        public static final String CUE_TRIGGER_PRE = "PRE";
        public static final String NAVIGATION_RESTRICTION_JUMP = "JUMP";
        public static final String NAVIGATION_RESTRICTION_SKIP = "SKIP";
        public static final String SNAP_TYPE_IN = "IN";
        public static final String SNAP_TYPE_OUT = "OUT";
        public static final String TIMELINE_OCCUPIES_POINT = "POINT";
        public static final String TIMELINE_OCCUPIES_RANGE = "RANGE";
        public static final String TIMELINE_STYLE_HIGHLIGHT = "HIGHLIGHT";
        public static final String TIMELINE_STYLE_PRIMARY = "PRIMARY";
        public final Uri assetListUri;
        public final Uri assetUri;
        public final h1 clientDefinedAttributes;
        public final boolean contentMayVary;
        public final List<String> cue;
        public final long durationUs;
        public final long endDateUnixUs;
        public final boolean endOnNext;
        public final String id;
        public final long plannedDurationUs;
        public final long playoutLimitUs;
        public final h1 restrictions;
        public final long resumeOffsetUs;
        public final long skipControlDurationUs;
        public final String skipControlLabelId;
        public final long skipControlOffsetUs;
        public final h1 snapTypes;
        public final long startDateUnixUs;
        public final String timelineOccupies;
        public final String timelineStyle;

        public static final class Builder {
            private Uri assetListUri;
            private Uri assetUri;
            private Boolean contentMayVary;
            private boolean endOnNext;
            private final String id;
            private String skipControlLabelId;
            private String timelineOccupies;
            private String timelineStyle;
            private final Map<String, ClientDefinedAttribute> clientDefinedAttributes = new HashMap();
            private long startDateUnixUs = C.TIME_UNSET;
            private long endDateUnixUs = C.TIME_UNSET;
            private long durationUs = C.TIME_UNSET;
            private long plannedDurationUs = C.TIME_UNSET;
            private List<String> cue = new ArrayList();
            private long resumeOffsetUs = C.TIME_UNSET;
            private long playoutLimitUs = C.TIME_UNSET;
            private List<String> snapTypes = new ArrayList();
            private List<String> restrictions = new ArrayList();
            private long skipControlOffsetUs = C.TIME_UNSET;
            private long skipControlDurationUs = C.TIME_UNSET;

            public Builder(String str) {
                this.id = str;
            }

            public Interstitial build() {
                Uri uri = this.assetListUri;
                if ((uri != null || this.assetUri == null) && (uri == null || this.assetUri != null)) {
                    return null;
                }
                long j10 = this.startDateUnixUs;
                if (j10 == C.TIME_UNSET) {
                    return null;
                }
                String str = this.id;
                Uri uri2 = this.assetUri;
                long j11 = this.endDateUnixUs;
                long j12 = this.durationUs;
                long j13 = this.plannedDurationUs;
                List<String> list = this.cue;
                boolean z = this.endOnNext;
                long j14 = this.resumeOffsetUs;
                long j15 = this.playoutLimitUs;
                List<String> list2 = this.snapTypes;
                List<String> list3 = this.restrictions;
                ArrayList arrayList = new ArrayList(this.clientDefinedAttributes.values());
                Boolean bool = this.contentMayVary;
                boolean z5 = bool == null || bool.booleanValue();
                String str2 = this.timelineOccupies;
                if (str2 == null) {
                    str2 = Interstitial.TIMELINE_OCCUPIES_POINT;
                }
                String str3 = str2;
                String str4 = this.timelineStyle;
                if (str4 == null) {
                    str4 = Interstitial.TIMELINE_STYLE_HIGHLIGHT;
                }
                return new Interstitial(str, uri2, uri, j10, j11, j12, j13, list, z, j14, j15, list2, list3, arrayList, z5, str3, str4, this.skipControlOffsetUs, this.skipControlDurationUs, this.skipControlLabelId);
            }

            public Builder setAssetListUri(Uri uri) {
                if (uri == null) {
                    return this;
                }
                Uri uri2 = this.assetListUri;
                if (uri2 != null) {
                    ac.b.m(uri2.equals(uri), "Can't change assetListUri from %s to %s", this.assetListUri, uri);
                }
                this.assetListUri = uri;
                return this;
            }

            public Builder setAssetUri(Uri uri) {
                if (uri == null) {
                    return this;
                }
                Uri uri2 = this.assetUri;
                if (uri2 != null) {
                    ac.b.m(uri2.equals(uri), "Can't change assetUri from %s to %s", this.assetUri, uri);
                }
                this.assetUri = uri;
                return this;
            }

            public Builder setClientDefinedAttributes(List<ClientDefinedAttribute> list) {
                if (!list.isEmpty()) {
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ClientDefinedAttribute clientDefinedAttribute = list.get(i10);
                        String str = clientDefinedAttribute.name;
                        ClientDefinedAttribute clientDefinedAttribute2 = this.clientDefinedAttributes.get(str);
                        if (clientDefinedAttribute2 != null) {
                            boolean zEquals = clientDefinedAttribute2.equals(clientDefinedAttribute);
                            Object[] objArr = {str, clientDefinedAttribute2.textValue, Double.valueOf(clientDefinedAttribute2.doubleValue), clientDefinedAttribute.textValue, Double.valueOf(clientDefinedAttribute.doubleValue)};
                            if (!zEquals) {
                                throw new IllegalArgumentException(t.y("Can't change %s from %s %s to %s %s", objArr));
                            }
                        }
                        this.clientDefinedAttributes.put(str, clientDefinedAttribute);
                    }
                }
                return this;
            }

            public Builder setContentMayVary(Boolean bool) {
                if (bool == null) {
                    return this;
                }
                Boolean bool2 = this.contentMayVary;
                if (bool2 != null) {
                    ac.b.m(bool2.equals(bool), "Can't change contentMayVary from %s to %s", this.contentMayVary, bool);
                }
                this.contentMayVary = bool;
                return this;
            }

            public Builder setCue(List<String> list) {
                if (list.isEmpty()) {
                    return this;
                }
                if (!this.cue.isEmpty()) {
                    boolean zEquals = this.cue.equals(list);
                    StringBuilder sb2 = new StringBuilder("Can't change cue from ");
                    List<String> list2 = this.cue;
                    StringBuilder sb3 = new StringBuilder();
                    Iterator<T> it = list2.iterator();
                    if (it.hasNext()) {
                        while (true) {
                            sb3.append((CharSequence) it.next());
                            if (!it.hasNext()) {
                                break;
                            }
                            sb3.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb3.toString());
                    sb2.append(" to ");
                    StringBuilder sb4 = new StringBuilder();
                    Iterator<T> it2 = list.iterator();
                    if (it2.hasNext()) {
                        while (true) {
                            sb4.append((CharSequence) it2.next());
                            if (!it2.hasNext()) {
                                break;
                            }
                            sb4.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb4.toString());
                    ac.b.k(zEquals, sb2.toString());
                }
                this.cue = list;
                return this;
            }

            public Builder setDurationUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.durationUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change durationUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.durationUs = j11;
                return this;
            }

            public Builder setEndDateUnixUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.endDateUnixUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change endDateUnixUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.endDateUnixUs = j11;
                return this;
            }

            public Builder setEndOnNext(boolean z) {
                if (!z) {
                    return this;
                }
                this.endOnNext = true;
                return this;
            }

            public Builder setPlannedDurationUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.plannedDurationUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change plannedDurationUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.plannedDurationUs = j11;
                return this;
            }

            public Builder setPlayoutLimitUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.playoutLimitUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change playoutLimitUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.playoutLimitUs = j11;
                return this;
            }

            public Builder setRestrictions(List<String> list) {
                if (list.isEmpty()) {
                    return this;
                }
                if (!this.restrictions.isEmpty()) {
                    boolean zEquals = this.restrictions.equals(list);
                    StringBuilder sb2 = new StringBuilder("Can't change restrictions from ");
                    List<String> list2 = this.restrictions;
                    StringBuilder sb3 = new StringBuilder();
                    Iterator<T> it = list2.iterator();
                    if (it.hasNext()) {
                        while (true) {
                            sb3.append((CharSequence) it.next());
                            if (!it.hasNext()) {
                                break;
                            }
                            sb3.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb3.toString());
                    sb2.append(" to ");
                    StringBuilder sb4 = new StringBuilder();
                    Iterator<T> it2 = list.iterator();
                    if (it2.hasNext()) {
                        while (true) {
                            sb4.append((CharSequence) it2.next());
                            if (!it2.hasNext()) {
                                break;
                            }
                            sb4.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb4.toString());
                    ac.b.k(zEquals, sb2.toString());
                }
                this.restrictions = list;
                return this;
            }

            public Builder setResumeOffsetUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.resumeOffsetUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change resumeOffsetUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.resumeOffsetUs = j11;
                return this;
            }

            public Builder setSkipControlDurationUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.skipControlDurationUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change skipControlDurationUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.skipControlDurationUs = j11;
                return this;
            }

            public Builder setSkipControlLabelId(String str) {
                if (str == null) {
                    return this;
                }
                String str2 = this.skipControlLabelId;
                if (str2 != null) {
                    ac.b.m(str2.equals(str), "Can't change skipControlLabelId from %s to %s", this.skipControlLabelId, str);
                }
                this.skipControlLabelId = str;
                return this;
            }

            public Builder setSkipControlOffsetUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.skipControlOffsetUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change skipControlOffsetUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.skipControlOffsetUs = j11;
                return this;
            }

            public Builder setSnapTypes(List<String> list) {
                if (list.isEmpty()) {
                    return this;
                }
                if (!this.snapTypes.isEmpty()) {
                    boolean zEquals = this.snapTypes.equals(list);
                    StringBuilder sb2 = new StringBuilder("Can't change snapTypes from ");
                    List<String> list2 = this.snapTypes;
                    StringBuilder sb3 = new StringBuilder();
                    Iterator<T> it = list2.iterator();
                    if (it.hasNext()) {
                        while (true) {
                            sb3.append((CharSequence) it.next());
                            if (!it.hasNext()) {
                                break;
                            }
                            sb3.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb3.toString());
                    sb2.append(" to ");
                    StringBuilder sb4 = new StringBuilder();
                    Iterator<T> it2 = list.iterator();
                    if (it2.hasNext()) {
                        while (true) {
                            sb4.append((CharSequence) it2.next());
                            if (!it2.hasNext()) {
                                break;
                            }
                            sb4.append((CharSequence) ", ");
                        }
                    }
                    sb2.append(sb4.toString());
                    ac.b.k(zEquals, sb2.toString());
                }
                this.snapTypes = list;
                return this;
            }

            public Builder setStartDateUnixUs(long j10) {
                long j11;
                if (j10 == C.TIME_UNSET) {
                    return this;
                }
                long j12 = this.startDateUnixUs;
                if (j12 != C.TIME_UNSET) {
                    j11 = j10;
                    ac.b.i("Can't change startDateUnixUs from %s to %s", j12, j11, j12 == j10);
                } else {
                    j11 = j10;
                }
                this.startDateUnixUs = j11;
                return this;
            }

            public Builder setTimelineOccupies(String str) {
                if (str == null) {
                    return this;
                }
                String str2 = this.timelineOccupies;
                if (str2 != null) {
                    ac.b.m(str2.equals(str), "Can't change timelineOccupies from %s to %s", this.timelineOccupies, str);
                }
                this.timelineOccupies = str;
                return this;
            }

            public Builder setTimelineStyle(String str) {
                if (str == null) {
                    return this;
                }
                String str2 = this.timelineStyle;
                if (str2 != null) {
                    ac.b.m(str2.equals(str), "Can't change timelineStyle from %s to %s", this.timelineStyle, str);
                }
                this.timelineStyle = str;
                return this;
            }
        }

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface CueTriggerType {
        }

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface NavigationRestriction {
        }

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface SnapType {
        }

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface TimelineOccupiesType {
        }

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface TimelineStyleType {
        }

        public Interstitial(String str, Uri uri, Uri uri2, long j10, long j11, long j12, long j13, List<String> list, boolean z, long j14, long j15, List<String> list2, List<String> list3, List<ClientDefinedAttribute> list4, boolean z5, String str2, String str3, long j16, long j17, String str4) {
            ac.b.j((uri == null || uri2 == null) && !(uri == null && uri2 == null));
            this.id = str;
            this.assetUri = uri;
            this.assetListUri = uri2;
            this.startDateUnixUs = j10;
            this.endDateUnixUs = j11;
            this.durationUs = j12;
            this.plannedDurationUs = j13;
            this.cue = list;
            this.endOnNext = z;
            this.resumeOffsetUs = j14;
            this.playoutLimitUs = j15;
            this.snapTypes = h1.n(list2);
            this.restrictions = h1.n(list3);
            this.clientDefinedAttributes = h1.u(new d(4), list4);
            this.contentMayVary = z5;
            this.timelineOccupies = str2;
            this.timelineStyle = str3;
            this.skipControlOffsetUs = j16;
            this.skipControlDurationUs = j17;
            this.skipControlLabelId = str4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ int lambda$new$0(ClientDefinedAttribute clientDefinedAttribute, ClientDefinedAttribute clientDefinedAttribute2) {
            return clientDefinedAttribute.name.compareTo(clientDefinedAttribute2.name);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Interstitial)) {
                return false;
            }
            Interstitial interstitial = (Interstitial) obj;
            return this.startDateUnixUs == interstitial.startDateUnixUs && this.endDateUnixUs == interstitial.endDateUnixUs && this.durationUs == interstitial.durationUs && this.plannedDurationUs == interstitial.plannedDurationUs && this.endOnNext == interstitial.endOnNext && this.resumeOffsetUs == interstitial.resumeOffsetUs && this.playoutLimitUs == interstitial.playoutLimitUs && this.contentMayVary == interstitial.contentMayVary && this.skipControlOffsetUs == interstitial.skipControlOffsetUs && this.skipControlDurationUs == interstitial.skipControlDurationUs && Objects.equals(this.id, interstitial.id) && Objects.equals(this.assetUri, interstitial.assetUri) && Objects.equals(this.assetListUri, interstitial.assetListUri) && Objects.equals(this.cue, interstitial.cue) && Objects.equals(this.snapTypes, interstitial.snapTypes) && Objects.equals(this.restrictions, interstitial.restrictions) && Objects.equals(this.clientDefinedAttributes, interstitial.clientDefinedAttributes) && Objects.equals(this.timelineOccupies, interstitial.timelineOccupies) && Objects.equals(this.timelineStyle, interstitial.timelineStyle) && Objects.equals(this.skipControlLabelId, interstitial.skipControlLabelId);
        }

        public int hashCode() {
            return Objects.hash(this.id, this.assetUri, this.assetListUri, Long.valueOf(this.startDateUnixUs), Long.valueOf(this.endDateUnixUs), Long.valueOf(this.durationUs), Long.valueOf(this.plannedDurationUs), this.cue, Boolean.valueOf(this.endOnNext), Long.valueOf(this.resumeOffsetUs), Long.valueOf(this.playoutLimitUs), this.snapTypes, this.restrictions, this.clientDefinedAttributes, Boolean.valueOf(this.contentMayVary), this.timelineOccupies, this.timelineStyle, Long.valueOf(this.skipControlOffsetUs), Long.valueOf(this.skipControlDurationUs), this.skipControlLabelId);
        }
    }

    public static final class Part extends SegmentBase {
        public final boolean isIndependent;
        public final boolean isPreload;

        public Part(String str, Segment segment, long j10, int i10, long j11, DrmInitData drmInitData, String str2, String str3, long j12, long j13, boolean z, boolean z5, boolean z10) {
            super(str, segment, j10, i10, j11, drmInitData, str2, str3, j12, j13, z);
            this.isIndependent = z5;
            this.isPreload = z10;
        }

        public Part copyWith(long j10, int i10) {
            return new Part(this.url, this.initializationSegment, this.durationUs, i10, j10, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, this.isIndependent, this.isPreload);
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaylistType {
    }

    public static final class RenditionReport {
        public final long lastMediaSequence;
        public final int lastPartIndex;
        public final Uri playlistUri;

        public RenditionReport(Uri uri, long j10, int i10) {
            this.playlistUri = uri;
            this.lastMediaSequence = j10;
            this.lastPartIndex = i10;
        }
    }

    public static class SegmentBase implements Comparable<Long> {
        public final long byteRangeLength;
        public final long byteRangeOffset;
        public final DrmInitData drmInitData;
        public final long durationUs;
        public final String encryptionIV;
        public final String fullSegmentEncryptionKeyUri;
        public final boolean hasGapTag;
        public final Segment initializationSegment;
        public final int relativeDiscontinuitySequence;
        public final long relativeStartTimeUs;
        public final String url;

        private SegmentBase(String str, Segment segment, long j10, int i10, long j11, DrmInitData drmInitData, String str2, String str3, long j12, long j13, boolean z) {
            this.url = str;
            this.initializationSegment = segment;
            this.durationUs = j10;
            this.relativeDiscontinuitySequence = i10;
            this.relativeStartTimeUs = j11;
            this.drmInitData = drmInitData;
            this.fullSegmentEncryptionKeyUri = str2;
            this.encryptionIV = str3;
            this.byteRangeOffset = j12;
            this.byteRangeLength = j13;
            this.hasGapTag = z;
        }

        @Override // java.lang.Comparable
        public int compareTo(Long l10) {
            if (this.relativeStartTimeUs > l10.longValue()) {
                return 1;
            }
            return this.relativeStartTimeUs < l10.longValue() ? -1 : 0;
        }
    }

    public static final class ServerControl {
        public final boolean canBlockReload;
        public final boolean canSkipDateRanges;
        public final long holdBackUs;
        public final long partHoldBackUs;
        public final long skipUntilUs;

        public ServerControl(long j10, boolean z, long j11, long j12, boolean z5) {
            this.skipUntilUs = j10;
            this.canSkipDateRanges = z;
            this.holdBackUs = j11;
            this.partHoldBackUs = j12;
            this.canBlockReload = z5;
        }
    }

    public HlsMediaPlaylist(int i10, String str, List<String> list, long j10, boolean z, long j11, boolean z5, int i11, long j12, int i12, long j13, long j14, boolean z10, boolean z11, boolean z12, DrmInitData drmInitData, List<Segment> list2, List<Part> list3, ServerControl serverControl, Map<Uri, RenditionReport> map, List<Interstitial> list4) {
        super(str, list, z10);
        this.playlistType = i10;
        this.startTimeUs = j11;
        this.preciseStart = z;
        this.hasDiscontinuitySequence = z5;
        this.discontinuitySequence = i11;
        this.mediaSequence = j12;
        this.version = i12;
        this.targetDurationUs = j13;
        this.partTargetDurationUs = j14;
        this.hasEndTag = z11;
        this.hasProgramDateTime = z12;
        this.protectionSchemes = drmInitData;
        this.segments = h1.n(list2);
        this.trailingParts = h1.n(list3);
        this.renditionReports = l1.b(map);
        this.interstitials = h1.n(list4);
        if (!list3.isEmpty()) {
            Part part = (Part) a0.j(list3);
            this.durationUs = part.relativeStartTimeUs + part.durationUs;
        } else if (list2.isEmpty()) {
            this.durationUs = 0L;
        } else {
            Segment segment = (Segment) a0.j(list2);
            this.durationUs = segment.relativeStartTimeUs + segment.durationUs;
        }
        this.startOffsetUs = j10 != C.TIME_UNSET ? j10 >= 0 ? Math.min(this.durationUs, j10) : Math.max(0L, this.durationUs + j10) : C.TIME_UNSET;
        this.hasPositiveStartOffset = j10 >= 0;
        this.serverControl = serverControl;
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public HlsPlaylist copy(List<StreamKey> list) {
        return this;
    }

    public HlsMediaPlaylist copyWith(long j10, int i10) {
        return new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, j10, true, i10, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, this.hasEndTag, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports, this.interstitials);
    }

    public HlsMediaPlaylist copyWithEndTag() {
        return this.hasEndTag ? this : new HlsMediaPlaylist(this.playlistType, this.baseUri, this.tags, this.startOffsetUs, this.preciseStart, this.startTimeUs, this.hasDiscontinuitySequence, this.discontinuitySequence, this.mediaSequence, this.version, this.targetDurationUs, this.partTargetDurationUs, this.hasIndependentSegments, true, this.hasProgramDateTime, this.protectionSchemes, this.segments, this.trailingParts, this.serverControl, this.renditionReports, this.interstitials);
    }

    public long getEndTimeUs() {
        return this.startTimeUs + this.durationUs;
    }

    public boolean isNewerThan(HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsMediaPlaylist != null) {
            long j10 = this.mediaSequence;
            long j11 = hlsMediaPlaylist.mediaSequence;
            if (j10 <= j11) {
                if (j10 < j11) {
                    return false;
                }
                int size = this.segments.size() - hlsMediaPlaylist.segments.size();
                if (size != 0) {
                    return size > 0;
                }
                int size2 = this.trailingParts.size();
                int size3 = hlsMediaPlaylist.trailingParts.size();
                if (size2 <= size3 && (size2 != size3 || !this.hasEndTag || hlsMediaPlaylist.hasEndTag)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    /* JADX INFO: renamed from: copy, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ HlsPlaylist copy2(List list) {
        return copy((List<StreamKey>) list);
    }

    public static final class Segment extends SegmentBase {
        public final List<Part> parts;
        public final String title;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Segment(String str, long j10, long j11, String str2, String str3) {
            this(str, null, "", 0L, -1, C.TIME_UNSET, null, str2, str3, j10, j11, false, o3.f14078o);
            f1 f1Var = h1.f14020l;
        }

        public Segment copyWith(long j10, int i10) {
            ArrayList arrayList = new ArrayList();
            long j11 = j10;
            for (int i11 = 0; i11 < this.parts.size(); i11++) {
                Part part = this.parts.get(i11);
                arrayList.add(part.copyWith(j11, i10));
                j11 += part.durationUs;
            }
            return new Segment(this.url, this.initializationSegment, this.title, this.durationUs, i10, j10, this.drmInitData, this.fullSegmentEncryptionKeyUri, this.encryptionIV, this.byteRangeOffset, this.byteRangeLength, this.hasGapTag, arrayList);
        }

        public Segment(String str, Segment segment, String str2, long j10, int i10, long j11, DrmInitData drmInitData, String str3, String str4, long j12, long j13, boolean z, List<Part> list) {
            super(str, segment, j10, i10, j11, drmInitData, str3, str4, j12, j13, z);
            this.title = str2;
            this.parts = h1.n(list);
        }
    }

    public static class ClientDefinedAttribute {
        public static final int TYPE_DOUBLE = 2;
        public static final int TYPE_HEX_TEXT = 1;
        public static final int TYPE_TEXT = 0;
        private final double doubleValue;
        public final String name;
        private final String textValue;
        public final int type;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        public ClientDefinedAttribute(String str, double d4) {
            this.name = str;
            this.type = 2;
            this.doubleValue = d4;
            this.textValue = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClientDefinedAttribute)) {
                return false;
            }
            ClientDefinedAttribute clientDefinedAttribute = (ClientDefinedAttribute) obj;
            return this.type == clientDefinedAttribute.type && Double.compare(this.doubleValue, clientDefinedAttribute.doubleValue) == 0 && Objects.equals(this.name, clientDefinedAttribute.name) && Objects.equals(this.textValue, clientDefinedAttribute.textValue);
        }

        public double getDoubleValue() {
            ac.b.s(this.type == 2);
            return this.doubleValue;
        }

        public String getTextValue() {
            ac.b.s(this.type != 2);
            String str = this.textValue;
            str.getClass();
            return str;
        }

        public int hashCode() {
            return Objects.hash(this.name, Integer.valueOf(this.type), Double.valueOf(this.doubleValue), this.textValue);
        }

        public ClientDefinedAttribute(String str, String str2, int i10) {
            boolean z = true;
            if (i10 == 1 && !str2.startsWith("0x") && !str2.startsWith("0X")) {
                z = false;
            }
            ac.b.s(z);
            this.name = str;
            this.type = i10;
            this.textValue = str2;
            this.doubleValue = 0.0d;
        }
    }
}
