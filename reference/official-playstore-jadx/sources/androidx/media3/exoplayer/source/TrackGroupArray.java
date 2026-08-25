package androidx.media3.exoplayer.source;

import android.os.Bundle;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.a0;
import com.google.common.collect.h1;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackGroupArray {
    public static final TrackGroupArray EMPTY = new TrackGroupArray(new TrackGroup[0]);
    private static final String FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    private static final String TAG = "TrackGroupArray";
    private int hashCode;
    public final int length;
    private final h1 trackGroups;

    public TrackGroupArray(TrackGroup... trackGroupArr) {
        this.trackGroups = h1.p(trackGroupArr);
        this.length = trackGroupArr.length;
        verifyCorrectness();
    }

    public static TrackGroupArray fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_TRACK_GROUPS);
        return parcelableArrayList == null ? new TrackGroupArray(new TrackGroup[0]) : new TrackGroupArray((TrackGroup[]) BundleCollectionUtil.fromBundleList(new k(4), parcelableArrayList).toArray(new TrackGroup[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getTrackTypes$0(TrackGroup trackGroup) {
        return Integer.valueOf(trackGroup.type);
    }

    private void verifyCorrectness() {
        int i10 = 0;
        while (i10 < this.trackGroups.size()) {
            int i11 = i10 + 1;
            for (int i12 = i11; i12 < this.trackGroups.size(); i12++) {
                if (((TrackGroup) this.trackGroups.get(i10)).equals(this.trackGroups.get(i12))) {
                    Log.e(TAG, "", new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
                }
            }
            i10 = i11;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && TrackGroupArray.class == obj.getClass()) {
            TrackGroupArray trackGroupArray = (TrackGroupArray) obj;
            if (this.length == trackGroupArray.length && this.trackGroups.equals(trackGroupArray.trackGroups)) {
                return true;
            }
        }
        return false;
    }

    public TrackGroup get(int i10) {
        return (TrackGroup) this.trackGroups.get(i10);
    }

    public h1 getTrackTypes() {
        return h1.n(a0.x(this.trackGroups, new k(2)));
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.trackGroups.hashCode();
        }
        return this.hashCode;
    }

    public int indexOf(TrackGroup trackGroup) {
        int iIndexOf = this.trackGroups.indexOf(trackGroup);
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FIELD_TRACK_GROUPS, BundleCollectionUtil.toBundleArrayList(this.trackGroups, new k(3)));
        return bundle;
    }

    public String toString() {
        return this.trackGroups.toString();
    }
}
