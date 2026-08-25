package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import com.google.common.collect.h1;
import java.util.Collections;
import java.util.List;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackSelectionOverride {
    public final TrackGroup mediaTrackGroup;
    public final h1 trackIndices;
    private static final String FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
    private static final String FIELD_TRACKS = Util.intToStringMaxRadix(1);

    public TrackSelectionOverride(TrackGroup trackGroup, int i10) {
        this(trackGroup, h1.s(Integer.valueOf(i10)));
    }

    public static TrackSelectionOverride fromBundle(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(FIELD_TRACK_GROUP);
        bundle2.getClass();
        TrackGroup trackGroupFromBundle = TrackGroup.fromBundle(bundle2);
        int[] intArray = bundle.getIntArray(FIELD_TRACKS);
        intArray.getClass();
        return new TrackSelectionOverride(trackGroupFromBundle, (List<Integer>) f0.b(intArray));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackSelectionOverride.class == obj.getClass()) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) obj;
            if (this.mediaTrackGroup.equals(trackSelectionOverride.mediaTrackGroup) && this.trackIndices.equals(trackSelectionOverride.trackIndices)) {
                return true;
            }
        }
        return false;
    }

    public int getType() {
        return this.mediaTrackGroup.type;
    }

    public int hashCode() {
        return (this.trackIndices.hashCode() * 31) + this.mediaTrackGroup.hashCode();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
        bundle.putIntArray(FIELD_TRACKS, f0.O(this.trackIndices));
        return bundle;
    }

    public TrackSelectionOverride(TrackGroup trackGroup, List<Integer> list) {
        if (!list.isEmpty() && (((Integer) Collections.min(list)).intValue() < 0 || ((Integer) Collections.max(list)).intValue() >= trackGroup.length)) {
            throw new IndexOutOfBoundsException();
        }
        this.mediaTrackGroup = trackGroup;
        this.trackIndices = h1.n(list);
    }
}
