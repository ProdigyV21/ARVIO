package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class Tracks {
    public static final Tracks EMPTY;
    private static final String FIELD_TRACK_GROUPS;
    private final h1 groups;

    public static final class Group {
        private final boolean adaptiveSupported;
        public final int length;
        private final TrackGroup mediaTrackGroup;
        private final boolean[] trackSelected;
        private final int[] trackSupport;
        private static final String FIELD_TRACK_GROUP = Util.intToStringMaxRadix(0);
        private static final String FIELD_TRACK_SUPPORT = Util.intToStringMaxRadix(1);
        private static final String FIELD_TRACK_SELECTED = Util.intToStringMaxRadix(3);
        private static final String FIELD_ADAPTIVE_SUPPORTED = Util.intToStringMaxRadix(4);

        public Group(TrackGroup trackGroup, boolean z, int[] iArr, boolean[] zArr) {
            int i10 = trackGroup.length;
            this.length = i10;
            boolean z5 = false;
            ac.b.j(i10 == iArr.length && i10 == zArr.length);
            this.mediaTrackGroup = trackGroup;
            if (z && i10 > 1) {
                z5 = true;
            }
            this.adaptiveSupported = z5;
            this.trackSupport = (int[]) iArr.clone();
            this.trackSelected = (boolean[]) zArr.clone();
        }

        public static Group fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(FIELD_TRACK_GROUP);
            bundle2.getClass();
            TrackGroup trackGroupFromBundle = TrackGroup.fromBundle(bundle2);
            return new Group(trackGroupFromBundle, bundle.getBoolean(FIELD_ADAPTIVE_SUPPORTED, false), (int[]) xc.d.g0(bundle.getIntArray(FIELD_TRACK_SUPPORT), new int[trackGroupFromBundle.length]), (boolean[]) xc.d.g0(bundle.getBooleanArray(FIELD_TRACK_SELECTED), new boolean[trackGroupFromBundle.length]));
        }

        public Group copyWithId(String str) {
            return new Group(this.mediaTrackGroup.copyWithId(str), this.adaptiveSupported, this.trackSupport, this.trackSelected);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Group.class == obj.getClass()) {
                Group group = (Group) obj;
                if (this.adaptiveSupported == group.adaptiveSupported && this.mediaTrackGroup.equals(group.mediaTrackGroup) && Arrays.equals(this.trackSupport, group.trackSupport) && Arrays.equals(this.trackSelected, group.trackSelected)) {
                    return true;
                }
            }
            return false;
        }

        public TrackGroup getMediaTrackGroup() {
            return this.mediaTrackGroup;
        }

        public Format getTrackFormat(int i10) {
            return this.mediaTrackGroup.getFormat(i10);
        }

        public int getTrackSupport(int i10) {
            return this.trackSupport[i10];
        }

        public int getType() {
            return this.mediaTrackGroup.type;
        }

        public int hashCode() {
            return Arrays.hashCode(this.trackSelected) + ((Arrays.hashCode(this.trackSupport) + (((this.mediaTrackGroup.hashCode() * 31) + (this.adaptiveSupported ? 1 : 0)) * 31)) * 31);
        }

        public boolean isAdaptiveSupported() {
            return this.adaptiveSupported;
        }

        public boolean isSelected() {
            for (boolean z : this.trackSelected) {
                if (z) {
                    return true;
                }
            }
            return false;
        }

        public boolean isSupported() {
            return isSupported(false);
        }

        public boolean isTrackSelected(int i10) {
            return this.trackSelected[i10];
        }

        public boolean isTrackSupported(int i10) {
            return isTrackSupported(i10, false);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(FIELD_TRACK_GROUP, this.mediaTrackGroup.toBundle());
            bundle.putIntArray(FIELD_TRACK_SUPPORT, this.trackSupport);
            bundle.putBooleanArray(FIELD_TRACK_SELECTED, this.trackSelected);
            bundle.putBoolean(FIELD_ADAPTIVE_SUPPORTED, this.adaptiveSupported);
            return bundle;
        }

        public boolean isSupported(boolean z) {
            for (int i10 = 0; i10 < this.trackSupport.length; i10++) {
                if (isTrackSupported(i10, z)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isTrackSupported(int i10, boolean z) {
            int i11 = this.trackSupport[i10];
            if (i11 != 4) {
                return z && i11 == 3;
            }
            return true;
        }
    }

    static {
        f1 f1Var = h1.f14020l;
        EMPTY = new Tracks(o3.f14078o);
        FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    }

    public Tracks(List<Group> list) {
        this.groups = h1.n(list);
    }

    public static Tracks fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_TRACK_GROUPS);
        return new Tracks(parcelableArrayList == null ? o3.f14078o : BundleCollectionUtil.fromBundleList(new e(13), parcelableArrayList));
    }

    public boolean containsType(int i10) {
        for (int i11 = 0; i11 < this.groups.size(); i11++) {
            if (((Group) this.groups.get(i11)).getType() == i10) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Tracks.class != obj.getClass()) {
            return false;
        }
        return this.groups.equals(((Tracks) obj).groups);
    }

    public h1 getGroups() {
        return this.groups;
    }

    public int hashCode() {
        return this.groups.hashCode();
    }

    public boolean isEmpty() {
        return this.groups.isEmpty();
    }

    public boolean isTypeSelected(int i10) {
        for (int i11 = 0; i11 < this.groups.size(); i11++) {
            Group group = (Group) this.groups.get(i11);
            if (group.isSelected() && group.getType() == i10) {
                return true;
            }
        }
        return false;
    }

    public boolean isTypeSupported(int i10) {
        return isTypeSupported(i10, false);
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int i10) {
        return isTypeSupportedOrEmpty(i10, false);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FIELD_TRACK_GROUPS, BundleCollectionUtil.toBundleArrayList(this.groups, new e(12)));
        return bundle;
    }

    public boolean isTypeSupported(int i10, boolean z) {
        for (int i11 = 0; i11 < this.groups.size(); i11++) {
            if (((Group) this.groups.get(i11)).getType() == i10 && ((Group) this.groups.get(i11)).isSupported(z)) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isTypeSupportedOrEmpty(int i10, boolean z) {
        return !containsType(i10) || isTypeSupported(i10, z);
    }
}
