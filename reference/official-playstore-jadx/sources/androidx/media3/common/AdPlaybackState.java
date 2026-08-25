package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AdPlaybackState {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;
    public static final AdPlaybackState NONE = new AdPlaybackState(null, new AdGroup[0], 0, C.TIME_UNSET, 0);
    private static final AdGroup REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
    private static final String FIELD_AD_GROUPS = Util.intToStringMaxRadix(1);
    private static final String FIELD_AD_RESUME_POSITION_US = Util.intToStringMaxRadix(2);
    private static final String FIELD_CONTENT_DURATION_US = Util.intToStringMaxRadix(3);
    private static final String FIELD_REMOVED_AD_GROUP_COUNT = Util.intToStringMaxRadix(4);

    public static final class AdGroup {
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final String[] ids;
        public final boolean isPlaceholder;
        public final boolean isServerSideInserted;
        public final MediaItem[] mediaItems;
        public final int originalCount;
        public final SkipInfo[] skipInfos;
        public final int[] states;
        public final long timeUs;

        @Deprecated
        public final Uri[] uris;
        private static final String FIELD_TIME_US = Util.intToStringMaxRadix(0);
        private static final String FIELD_COUNT = Util.intToStringMaxRadix(1);
        private static final String FIELD_URIS = Util.intToStringMaxRadix(2);
        private static final String FIELD_STATES = Util.intToStringMaxRadix(3);
        private static final String FIELD_DURATIONS_US = Util.intToStringMaxRadix(4);
        private static final String FIELD_CONTENT_RESUME_OFFSET_US = Util.intToStringMaxRadix(5);
        private static final String FIELD_IS_SERVER_SIDE_INSERTED = Util.intToStringMaxRadix(6);
        private static final String FIELD_ORIGINAL_COUNT = Util.intToStringMaxRadix(7);
        static final String FIELD_MEDIA_ITEMS = Util.intToStringMaxRadix(8);
        static final String FIELD_IDS = Util.intToStringMaxRadix(9);
        static final String FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(10);
        private static final String FIELD_SKIP_INFOS = Util.intToStringMaxRadix(11);

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i10) {
            int length = jArr.length;
            int iMax = Math.max(i10, length);
            long[] jArrCopyOf = Arrays.copyOf(jArr, iMax);
            Arrays.fill(jArrCopyOf, length, iMax, C.TIME_UNSET);
            return jArrCopyOf;
        }

        private static SkipInfo[] copySkipInfosWithSpaceForAdCount(SkipInfo[] skipInfoArr, int i10) {
            return (SkipInfo[]) Arrays.copyOf(skipInfoArr, Math.max(i10, skipInfoArr.length));
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i10) {
            int length = iArr.length;
            int iMax = Math.max(i10, length);
            int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
            Arrays.fill(iArrCopyOf, length, iMax, 0);
            return iArrCopyOf;
        }

        public static AdGroup fromBundle(Bundle bundle) {
            long j10 = bundle.getLong(FIELD_TIME_US);
            int i10 = bundle.getInt(FIELD_COUNT);
            int i11 = bundle.getInt(FIELD_ORIGINAL_COUNT);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_URIS);
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(FIELD_MEDIA_ITEMS);
            int[] intArray = bundle.getIntArray(FIELD_STATES);
            long[] longArray = bundle.getLongArray(FIELD_DURATIONS_US);
            long j11 = bundle.getLong(FIELD_CONTENT_RESUME_OFFSET_US);
            boolean z = bundle.getBoolean(FIELD_IS_SERVER_SIDE_INSERTED);
            ArrayList<String> stringArrayList = bundle.getStringArrayList(FIELD_IDS);
            ArrayList parcelableArrayList3 = bundle.getParcelableArrayList(FIELD_SKIP_INFOS);
            boolean z5 = bundle.getBoolean(FIELD_IS_PLACEHOLDER);
            if (intArray == null) {
                intArray = new int[0];
            }
            MediaItem[] mediaItemsFromBundleArrays = getMediaItemsFromBundleArrays(parcelableArrayList2, parcelableArrayList);
            if (longArray == null) {
                longArray = new long[0];
            }
            String[] strArr = new String[0];
            if (stringArrayList != null) {
                strArr = (String[]) stringArrayList.toArray(strArr);
            }
            return new AdGroup(j10, i10, i11, intArray, mediaItemsFromBundleArrays, longArray, j11, z, strArr, parcelableArrayList3 == null ? new SkipInfo[0] : getSkipInfosFromBundleArrays(parcelableArrayList3), z5);
        }

        private ArrayList<Bundle> getMediaItemsArrayBundles() {
            ArrayList<Bundle> arrayList = new ArrayList<>();
            MediaItem[] mediaItemArr = this.mediaItems;
            int length = mediaItemArr.length;
            for (int i10 = 0; i10 < length; i10++) {
                MediaItem mediaItem = mediaItemArr[i10];
                arrayList.add(mediaItem == null ? null : mediaItem.toBundleIncludeLocalConfiguration());
            }
            return arrayList;
        }

        private static MediaItem[] getMediaItemsFromBundleArrays(ArrayList<Bundle> arrayList, ArrayList<Uri> arrayList2) {
            int i10 = 0;
            if (arrayList != null) {
                MediaItem[] mediaItemArr = new MediaItem[arrayList.size()];
                while (i10 < arrayList.size()) {
                    Bundle bundle = arrayList.get(i10);
                    mediaItemArr[i10] = bundle == null ? null : MediaItem.fromBundle(bundle);
                    i10++;
                }
                return mediaItemArr;
            }
            if (arrayList2 == null) {
                return new MediaItem[0];
            }
            MediaItem[] mediaItemArr2 = new MediaItem[arrayList2.size()];
            while (i10 < arrayList2.size()) {
                Uri uri = arrayList2.get(i10);
                mediaItemArr2[i10] = uri == null ? null : MediaItem.fromUri(uri);
                i10++;
            }
            return mediaItemArr2;
        }

        private ArrayList<Bundle> getSkipInfoArrayBundles() {
            ArrayList<Bundle> arrayList = new ArrayList<>();
            SkipInfo[] skipInfoArr = this.skipInfos;
            int length = skipInfoArr.length;
            for (int i10 = 0; i10 < length; i10++) {
                SkipInfo skipInfo = skipInfoArr[i10];
                arrayList.add(skipInfo == null ? null : skipInfo.toBundle());
            }
            return arrayList;
        }

        private static SkipInfo[] getSkipInfosFromBundleArrays(List<Bundle> list) {
            SkipInfo[] skipInfoArr = new SkipInfo[list.size()];
            for (int i10 = 0; i10 < list.size(); i10++) {
                Bundle bundle = list.get(i10);
                skipInfoArr[i10] = bundle == null ? null : SkipInfo.fromBundle(bundle);
            }
            return skipInfoArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AdGroup withIsPlaceholder(boolean z, boolean z5) {
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, z5, this.ids, this.skipInfos, z);
        }

        public AdGroup copy() {
            long j10 = this.timeUs;
            int i10 = this.count;
            int i11 = this.originalCount;
            int[] iArr = this.states;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            MediaItem[] mediaItemArr = this.mediaItems;
            MediaItem[] mediaItemArr2 = (MediaItem[]) Arrays.copyOf(mediaItemArr, mediaItemArr.length);
            long[] jArr = this.durationsUs;
            long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
            long j11 = this.contentResumeOffsetUs;
            boolean z = this.isServerSideInserted;
            String[] strArr = this.ids;
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
            SkipInfo[] skipInfoArr = this.skipInfos;
            return new AdGroup(j10, i10, i11, iArrCopyOf, mediaItemArr2, jArrCopyOf, j11, z, strArr2, (SkipInfo[]) Arrays.copyOf(skipInfoArr, skipInfoArr.length), this.isPlaceholder);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && AdGroup.class == obj.getClass()) {
                AdGroup adGroup = (AdGroup) obj;
                if (this.timeUs == adGroup.timeUs && this.count == adGroup.count && this.originalCount == adGroup.originalCount && Arrays.equals(this.mediaItems, adGroup.mediaItems) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs) && this.contentResumeOffsetUs == adGroup.contentResumeOffsetUs && this.isServerSideInserted == adGroup.isServerSideInserted && Arrays.equals(this.ids, adGroup.ids) && Arrays.equals(this.skipInfos, adGroup.skipInfos) && this.isPlaceholder == adGroup.isPlaceholder) {
                    return true;
                }
            }
            return false;
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getIndexOfAdId(String str) {
            int i10 = 0;
            while (true) {
                String[] strArr = this.ids;
                if (i10 >= strArr.length) {
                    return -1;
                }
                if (Objects.equals(strArr[i10], str)) {
                    return i10;
                }
                i10++;
            }
        }

        public int getNextAdIndexToPlay(int i10) {
            int i11;
            int i12 = i10 + 1;
            while (true) {
                int[] iArr = this.states;
                if (i12 >= iArr.length || this.isServerSideInserted || (i11 = iArr[i12]) == 0 || i11 == 1) {
                    break;
                }
                i12++;
            }
            return i12;
        }

        public boolean hasUnplayedAds() {
            if (this.count == -1) {
                return true;
            }
            for (int i10 = 0; i10 < this.count; i10++) {
                int i11 = this.states[i10];
                if (i11 == 0 || i11 == 1) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i10 = ((this.count * 31) + this.originalCount) * 31;
            long j10 = this.timeUs;
            int iHashCode = (Arrays.hashCode(this.durationsUs) + ((Arrays.hashCode(this.states) + ((Arrays.hashCode(this.mediaItems) + ((i10 + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31)) * 31)) * 31;
            long j11 = this.contentResumeOffsetUs;
            return ((Arrays.hashCode(this.skipInfos) + ((((((iHashCode + ((int) ((j11 >>> 32) ^ j11))) * 31) + (this.isServerSideInserted ? 1 : 0)) * 31) + Arrays.hashCode(this.ids)) * 31)) * 31) + (this.isPlaceholder ? 1 : 0);
        }

        public boolean isLivePostrollPlaceholder(boolean z) {
            return this.isServerSideInserted == z && isLivePostrollPlaceholder();
        }

        public boolean shouldPlayAdGroup() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong(FIELD_TIME_US, this.timeUs);
            bundle.putInt(FIELD_COUNT, this.count);
            bundle.putInt(FIELD_ORIGINAL_COUNT, this.originalCount);
            bundle.putParcelableArrayList(FIELD_URIS, new ArrayList<>(Arrays.asList(this.uris)));
            bundle.putParcelableArrayList(FIELD_MEDIA_ITEMS, getMediaItemsArrayBundles());
            bundle.putIntArray(FIELD_STATES, this.states);
            bundle.putLongArray(FIELD_DURATIONS_US, this.durationsUs);
            bundle.putLong(FIELD_CONTENT_RESUME_OFFSET_US, this.contentResumeOffsetUs);
            bundle.putBoolean(FIELD_IS_SERVER_SIDE_INSERTED, this.isServerSideInserted);
            bundle.putStringArrayList(FIELD_IDS, new ArrayList<>(Arrays.asList(this.ids)));
            bundle.putParcelableArrayList(FIELD_SKIP_INFOS, getSkipInfoArrayBundles());
            bundle.putBoolean(FIELD_IS_PLACEHOLDER, this.isPlaceholder);
            return bundle;
        }

        public AdGroup withAdCount(int i10) {
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i10);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, i10);
            return new AdGroup(this.timeUs, i10, this.originalCount, iArrCopyStatesWithSpaceForAdCount, (MediaItem[]) Arrays.copyOf(this.mediaItems, i10), jArrCopyDurationsUsWithSpaceForAdCount, this.contentResumeOffsetUs, this.isServerSideInserted, (String[]) Arrays.copyOf(this.ids, i10), copySkipInfosWithSpaceForAdCount(this.skipInfos, i10), this.isPlaceholder);
        }

        public AdGroup withAdDurationsUs(long[] jArr) {
            int length = jArr.length;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (length < mediaItemArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, mediaItemArr.length);
            } else if (this.count != -1 && jArr.length > mediaItemArr.length) {
                jArr = Arrays.copyOf(jArr, mediaItemArr.length);
            }
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, jArr, this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withAdId(String str, int i10) {
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i10 + 1);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            long[] jArr = jArrCopyDurationsUsWithSpaceForAdCount;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (mediaItemArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                mediaItemArr = (MediaItem[]) Arrays.copyOf(mediaItemArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            MediaItem[] mediaItemArr2 = mediaItemArr;
            String[] strArr = (String[]) Arrays.copyOf(this.ids, iArrCopyStatesWithSpaceForAdCount.length);
            strArr[i10] = str;
            return new AdGroup(this.timeUs, this.count, this.originalCount, iArrCopyStatesWithSpaceForAdCount, mediaItemArr2, jArr, this.contentResumeOffsetUs, this.isServerSideInserted, strArr, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withAdMediaItem(MediaItem mediaItem, int i10) {
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i10 + 1);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            long[] jArr = jArrCopyDurationsUsWithSpaceForAdCount;
            MediaItem[] mediaItemArr = (MediaItem[]) Arrays.copyOf(this.mediaItems, iArrCopyStatesWithSpaceForAdCount.length);
            mediaItemArr[i10] = mediaItem;
            iArrCopyStatesWithSpaceForAdCount[i10] = 1;
            String[] strArr = this.ids;
            if (strArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                strArr = (String[]) Arrays.copyOf(strArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            String[] strArr2 = strArr;
            SkipInfo[] skipInfoArrCopySkipInfosWithSpaceForAdCount = this.skipInfos;
            if (skipInfoArrCopySkipInfosWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                skipInfoArrCopySkipInfosWithSpaceForAdCount = copySkipInfosWithSpaceForAdCount(skipInfoArrCopySkipInfosWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            return new AdGroup(this.timeUs, this.count, this.originalCount, iArrCopyStatesWithSpaceForAdCount, mediaItemArr, jArr, this.contentResumeOffsetUs, this.isServerSideInserted, strArr2, skipInfoArrCopySkipInfosWithSpaceForAdCount, this.isPlaceholder);
        }

        public AdGroup withAdSkipInfo(SkipInfo skipInfo, int i10) {
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i10 + 1);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            long[] jArr = jArrCopyDurationsUsWithSpaceForAdCount;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (mediaItemArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                mediaItemArr = (MediaItem[]) Arrays.copyOf(mediaItemArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            MediaItem[] mediaItemArr2 = mediaItemArr;
            String[] strArr = this.ids;
            if (strArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                strArr = (String[]) Arrays.copyOf(strArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            String[] strArr2 = strArr;
            SkipInfo[] skipInfoArrCopySkipInfosWithSpaceForAdCount = copySkipInfosWithSpaceForAdCount(this.skipInfos, iArrCopyStatesWithSpaceForAdCount.length);
            skipInfoArrCopySkipInfosWithSpaceForAdCount[i10] = skipInfo;
            return new AdGroup(this.timeUs, this.count, this.originalCount, iArrCopyStatesWithSpaceForAdCount, mediaItemArr2, jArr, this.contentResumeOffsetUs, this.isServerSideInserted, strArr2, skipInfoArrCopySkipInfosWithSpaceForAdCount, this.isPlaceholder);
        }

        public AdGroup withAdState(int i10, int i11) {
            int i12 = this.count;
            ac.b.j(i12 == -1 || i11 < i12);
            int[] iArrCopyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i11 + 1);
            int i13 = iArrCopyStatesWithSpaceForAdCount[i11];
            ac.b.j(i13 == 0 || i13 == 1 || i13 == i10);
            long[] jArrCopyDurationsUsWithSpaceForAdCount = this.durationsUs;
            if (jArrCopyDurationsUsWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                jArrCopyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(jArrCopyDurationsUsWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            long[] jArr = jArrCopyDurationsUsWithSpaceForAdCount;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (mediaItemArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                mediaItemArr = (MediaItem[]) Arrays.copyOf(mediaItemArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            MediaItem[] mediaItemArr2 = mediaItemArr;
            String[] strArr = this.ids;
            if (strArr.length != iArrCopyStatesWithSpaceForAdCount.length) {
                strArr = (String[]) Arrays.copyOf(strArr, iArrCopyStatesWithSpaceForAdCount.length);
            }
            String[] strArr2 = strArr;
            iArrCopyStatesWithSpaceForAdCount[i11] = i10;
            SkipInfo[] skipInfoArrCopySkipInfosWithSpaceForAdCount = this.skipInfos;
            if (skipInfoArrCopySkipInfosWithSpaceForAdCount.length != iArrCopyStatesWithSpaceForAdCount.length) {
                skipInfoArrCopySkipInfosWithSpaceForAdCount = copySkipInfosWithSpaceForAdCount(skipInfoArrCopySkipInfosWithSpaceForAdCount, iArrCopyStatesWithSpaceForAdCount.length);
            }
            return new AdGroup(this.timeUs, this.count, this.originalCount, iArrCopyStatesWithSpaceForAdCount, mediaItemArr2, jArr, this.contentResumeOffsetUs, this.isServerSideInserted, strArr2, skipInfoArrCopySkipInfosWithSpaceForAdCount, this.isPlaceholder);
        }

        @Deprecated
        public AdGroup withAdUri(Uri uri, int i10) {
            return withAdMediaItem(MediaItem.fromUri(uri), i10);
        }

        public AdGroup withAllAdsReset() {
            if (this.count == -1) {
                return this;
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length);
            for (int i10 = 0; i10 < length; i10++) {
                int i11 = iArrCopyOf[i10];
                if (i11 == 3 || i11 == 2 || i11 == 4) {
                    iArrCopyOf[i10] = this.mediaItems[i10] == null ? 0 : 1;
                }
            }
            return new AdGroup(this.timeUs, length, this.originalCount, iArrCopyOf, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(this.timeUs, 0, this.originalCount, new int[0], new MediaItem[0], new long[0], this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length);
            for (int i10 = 0; i10 < length; i10++) {
                int i11 = iArrCopyOf[i10];
                if (i11 == 1 || i11 == 0) {
                    iArrCopyOf[i10] = 2;
                }
            }
            return new AdGroup(this.timeUs, length, this.originalCount, iArrCopyOf, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withContentResumeOffsetUs(long j10) {
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, j10, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withIsServerSideInserted(boolean z) {
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, z, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withLastAdRemoved() {
            int[] iArr = this.states;
            int length = iArr.length - 1;
            int[] iArrCopyOf = Arrays.copyOf(iArr, length);
            MediaItem[] mediaItemArr = (MediaItem[]) Arrays.copyOf(this.mediaItems, length);
            long[] jArrCopyOf = this.durationsUs;
            if (jArrCopyOf.length > length) {
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, length);
            }
            long[] jArr = jArrCopyOf;
            return new AdGroup(this.timeUs, length, this.originalCount, iArrCopyOf, mediaItemArr, jArr, Util.sum(jArr), this.isServerSideInserted, (String[]) Arrays.copyOf(this.ids, length), (SkipInfo[]) Arrays.copyOf(this.skipInfos, length), this.isPlaceholder);
        }

        public AdGroup withOriginalAdCount(int i10) {
            return new AdGroup(this.timeUs, this.count, i10, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup withTimeUs(long j10) {
            return new AdGroup(j10, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted, this.ids, this.skipInfos, this.isPlaceholder);
        }

        public AdGroup(long j10) {
            this(j10, -1, -1, new int[0], new MediaItem[0], new long[0], 0L, false, new String[0], new SkipInfo[0], false);
        }

        public boolean isLivePostrollPlaceholder() {
            return this.isPlaceholder && this.timeUs == Long.MIN_VALUE && this.count == -1;
        }

        private AdGroup(long j10, int i10, int i11, int[] iArr, MediaItem[] mediaItemArr, long[] jArr, long j11, boolean z, String[] strArr, SkipInfo[] skipInfoArr, boolean z5) {
            Uri uri;
            int i12 = 0;
            ac.b.j(iArr.length == mediaItemArr.length);
            ac.b.j(iArr.length == skipInfoArr.length);
            this.timeUs = j10;
            this.count = i10;
            this.originalCount = i11;
            this.states = iArr;
            this.mediaItems = mediaItemArr;
            this.durationsUs = jArr;
            this.contentResumeOffsetUs = j11;
            this.isServerSideInserted = z;
            this.uris = new Uri[mediaItemArr.length];
            while (true) {
                Uri[] uriArr = this.uris;
                if (i12 < uriArr.length) {
                    MediaItem mediaItem = mediaItemArr[i12];
                    if (mediaItem == null) {
                        uri = null;
                    } else {
                        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
                        localConfiguration.getClass();
                        uri = localConfiguration.uri;
                    }
                    uriArr[i12] = uri;
                    i12++;
                } else {
                    this.ids = strArr;
                    this.skipInfos = skipInfoArr;
                    this.isPlaceholder = z5;
                    return;
                }
            }
        }
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdState {
    }

    public static final class SkipInfo {
        public final String labelId;
        public final long skipDurationUs;
        public final long skipOffsetUs;
        private static final String FIELD_SKIP_OFFSET_US = Util.intToStringMaxRadix(0);
        private static final String FIELD_SKIP_DURATION_US = Util.intToStringMaxRadix(1);
        private static final String FIELD_LABEL_ID = Util.intToStringMaxRadix(2);

        public SkipInfo(long j10, long j11, String str) {
            ac.b.j((j10 == C.TIME_UNSET && j11 == C.TIME_UNSET && str == null) ? false : true);
            this.skipOffsetUs = j10 == C.TIME_UNSET ? 0L : j10;
            this.skipDurationUs = j11;
            this.labelId = str;
        }

        public static SkipInfo fromBundle(Bundle bundle) {
            return new SkipInfo(bundle.getLong(FIELD_SKIP_OFFSET_US), bundle.getLong(FIELD_SKIP_DURATION_US), bundle.getString(FIELD_LABEL_ID));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && SkipInfo.class == obj.getClass()) {
                SkipInfo skipInfo = (SkipInfo) obj;
                if (this.skipOffsetUs == skipInfo.skipOffsetUs && this.skipDurationUs == skipInfo.skipDurationUs && Objects.equals(this.labelId, skipInfo.labelId)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.skipOffsetUs), Long.valueOf(this.skipDurationUs), this.labelId);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong(FIELD_SKIP_OFFSET_US, this.skipOffsetUs);
            bundle.putLong(FIELD_SKIP_DURATION_US, this.skipDurationUs);
            bundle.putString(FIELD_LABEL_ID, this.labelId);
            return bundle;
        }
    }

    public AdPlaybackState(Object obj, long... jArr) {
        this(obj, createEmptyAdGroups(jArr), 0L, C.TIME_UNSET, 0);
    }

    private static AdGroup[] createEmptyAdGroups(long[] jArr) {
        int length = jArr.length;
        AdGroup[] adGroupArr = new AdGroup[length];
        for (int i10 = 0; i10 < length; i10++) {
            adGroupArr[i10] = new AdGroup(jArr[i10]);
        }
        return adGroupArr;
    }

    public static AdPlaybackState fromAdPlaybackState(Object obj, AdPlaybackState adPlaybackState) {
        int i10 = adPlaybackState.adGroupCount - adPlaybackState.removedAdGroupCount;
        AdGroup[] adGroupArr = new AdGroup[i10];
        int i11 = 0;
        while (i11 < i10) {
            AdGroup adGroup = adPlaybackState.adGroups[i11];
            long j10 = adGroup.timeUs;
            int i12 = adGroup.count;
            int i13 = adGroup.originalCount;
            int[] iArr = adGroup.states;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            MediaItem[] mediaItemArr = adGroup.mediaItems;
            MediaItem[] mediaItemArr2 = (MediaItem[]) Arrays.copyOf(mediaItemArr, mediaItemArr.length);
            long[] jArr = adGroup.durationsUs;
            adGroupArr[i11] = new AdGroup(j10, i12, i13, iArrCopyOf, mediaItemArr2, Arrays.copyOf(jArr, jArr.length), adGroup.contentResumeOffsetUs, adGroup.isServerSideInserted, adGroup.ids, adGroup.skipInfos, adGroup.isPlaceholder);
            i11++;
            i10 = i10;
        }
        return new AdPlaybackState(obj, adGroupArr, adPlaybackState.adResumePositionUs, adPlaybackState.contentDurationUs, adPlaybackState.removedAdGroupCount);
    }

    public static AdPlaybackState fromBundle(Bundle bundle) {
        AdGroup[] adGroupArr;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_AD_GROUPS);
        if (parcelableArrayList == null) {
            adGroupArr = new AdGroup[0];
        } else {
            AdGroup[] adGroupArr2 = new AdGroup[parcelableArrayList.size()];
            for (int i10 = 0; i10 < parcelableArrayList.size(); i10++) {
                adGroupArr2[i10] = AdGroup.fromBundle((Bundle) parcelableArrayList.get(i10));
            }
            adGroupArr = adGroupArr2;
        }
        String str = FIELD_AD_RESUME_POSITION_US;
        AdPlaybackState adPlaybackState = NONE;
        return new AdPlaybackState(null, adGroupArr, bundle.getLong(str, adPlaybackState.adResumePositionUs), bundle.getLong(FIELD_CONTENT_DURATION_US, adPlaybackState.contentDurationUs), bundle.getInt(FIELD_REMOVED_AD_GROUP_COUNT, adPlaybackState.removedAdGroupCount));
    }

    private boolean isPositionBeforeAdGroup(long j10, long j11, int i10) {
        if (j10 == Long.MIN_VALUE) {
            return false;
        }
        AdGroup adGroup = getAdGroup(i10);
        long j12 = adGroup.timeUs;
        return j12 == Long.MIN_VALUE ? j11 == C.TIME_UNSET || adGroup.isLivePostrollPlaceholder() || j10 < j11 : j10 < j12;
    }

    public AdPlaybackState copy() {
        int length = this.adGroups.length;
        AdGroup[] adGroupArr = new AdGroup[length];
        for (int i10 = 0; i10 < length; i10++) {
            adGroupArr[i10] = this.adGroups[i10].copy();
        }
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public boolean endsWithLivePostrollPlaceHolder() {
        int i10 = this.adGroupCount - 1;
        return i10 >= 0 && isLivePostrollPlaceholder(i10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AdPlaybackState.class == obj.getClass()) {
            AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
            if (Objects.equals(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && this.removedAdGroupCount == adPlaybackState.removedAdGroupCount && Arrays.equals(this.adGroups, adPlaybackState.adGroups)) {
                return true;
            }
        }
        return false;
    }

    public AdGroup getAdGroup(int i10) {
        int i11 = this.removedAdGroupCount;
        return i10 < i11 ? REMOVED_AD_GROUP : this.adGroups[i10 - i11];
    }

    public int getAdGroupIndexAfterPositionUs(long j10, long j11) {
        if (j10 != Long.MIN_VALUE && (j11 == C.TIME_UNSET || j10 < j11)) {
            int i10 = this.removedAdGroupCount;
            while (i10 < this.adGroupCount && ((getAdGroup(i10).timeUs != Long.MIN_VALUE && getAdGroup(i10).timeUs <= j10) || !getAdGroup(i10).shouldPlayAdGroup())) {
                i10++;
            }
            if (i10 < this.adGroupCount && (j11 == C.TIME_UNSET || getAdGroup(i10).timeUs <= j11)) {
                return i10;
            }
        }
        return -1;
    }

    public int getAdGroupIndexForPositionUs(long j10, long j11) {
        int i10 = this.adGroupCount - 1;
        int i11 = i10 - (isLivePostrollPlaceholder(i10) ? 1 : 0);
        while (i11 >= 0) {
            long j12 = j10;
            long j13 = j11;
            if (!isPositionBeforeAdGroup(j12, j13, i11)) {
                break;
            }
            i11--;
            j10 = j12;
            j11 = j13;
        }
        if (i11 < 0 || !getAdGroup(i11).hasUnplayedAds()) {
            return -1;
        }
        return i11;
    }

    public int getAdIndexOfAdId(int i10, String str) {
        return getAdGroup(i10).getIndexOfAdId(str);
    }

    public int hashCode() {
        int i10 = this.adGroupCount * 31;
        Object obj = this.adsId;
        return Arrays.hashCode(this.adGroups) + ((((((((i10 + (obj == null ? 0 : obj.hashCode())) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31);
    }

    public boolean isAdInErrorState(int i10, int i11) {
        AdGroup adGroup;
        int i12;
        return i10 < this.adGroupCount && (i12 = (adGroup = getAdGroup(i10)).count) != -1 && i11 < i12 && adGroup.states[i11] == 4;
    }

    public boolean isLivePostrollPlaceholder(int i10) {
        return i10 == this.adGroupCount - 1 && getAdGroup(i10).isLivePostrollPlaceholder();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (AdGroup adGroup : this.adGroups) {
            arrayList.add(adGroup.toBundle());
        }
        if (!arrayList.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_AD_GROUPS, arrayList);
        }
        long j10 = this.adResumePositionUs;
        AdPlaybackState adPlaybackState = NONE;
        if (j10 != adPlaybackState.adResumePositionUs) {
            bundle.putLong(FIELD_AD_RESUME_POSITION_US, j10);
        }
        long j11 = this.contentDurationUs;
        if (j11 != adPlaybackState.contentDurationUs) {
            bundle.putLong(FIELD_CONTENT_DURATION_US, j11);
        }
        int i10 = this.removedAdGroupCount;
        if (i10 != adPlaybackState.removedAdGroupCount) {
            bundle.putInt(FIELD_REMOVED_AD_GROUP_COUNT, i10);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AdPlaybackState(adsId=");
        sb2.append(this.adsId);
        sb2.append(", adResumePositionUs=");
        sb2.append(this.adResumePositionUs);
        sb2.append(", adGroups=[");
        for (int i10 = 0; i10 < this.adGroups.length; i10++) {
            sb2.append("adGroup(timeUs=");
            sb2.append(this.adGroups[i10].timeUs);
            sb2.append(", ads=[");
            for (int i11 = 0; i11 < this.adGroups[i10].states.length; i11++) {
                sb2.append("ad(state=");
                int i12 = this.adGroups[i10].states[i11];
                if (i12 == 0) {
                    sb2.append('_');
                } else if (i12 == 1) {
                    sb2.append('R');
                } else if (i12 == 2) {
                    sb2.append('S');
                } else if (i12 == 3) {
                    sb2.append('P');
                } else if (i12 != 4) {
                    sb2.append('?');
                } else {
                    sb2.append('!');
                }
                sb2.append(", durationUs=");
                sb2.append(this.adGroups[i10].durationsUs[i11]);
                sb2.append(')');
                if (i11 < this.adGroups[i10].states.length - 1) {
                    sb2.append(", ");
                }
            }
            sb2.append("])");
            if (i10 < this.adGroups.length - 1) {
                sb2.append(", ");
            }
        }
        sb2.append("])");
        return sb2.toString();
    }

    public AdPlaybackState withAdCount(int i10, int i11) {
        ac.b.j(i11 > 0);
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i12].count == i11) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = this.adGroups[i12].withAdCount(i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        int i10 = 0;
        ac.b.j(jArr.length == this.adGroupCount);
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        while (true) {
            int i11 = this.adGroupCount;
            int i12 = this.removedAdGroupCount;
            if (i10 >= i11 - i12) {
                return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, i12);
            }
            adGroupArr2[i10] = adGroupArr2[i10].withAdDurationsUs(jArr[i12 + i10]);
            i10++;
        }
    }

    public AdPlaybackState withAdGroupTimeUs(int i10, long j10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = this.adGroups[i11].withTimeUs(j10);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdId(int i10, int i11, String str) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withAdId(str, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdLoadError(int i10, int i11) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withAdState(4, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdResumePositionUs(long j10) {
        return this.adResumePositionUs == j10 ? this : new AdPlaybackState(this.adsId, this.adGroups, j10, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdSkipInfo(int i10, int i11, SkipInfo skipInfo) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withAdSkipInfo(skipInfo, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdsId(Object obj) {
        return new AdPlaybackState(obj, this.adGroups, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAvailableAd(int i10, int i11) {
        return withAvailableAdMediaItem(i10, i11, MediaItem.fromUri(Uri.EMPTY));
    }

    public AdPlaybackState withAvailableAdMediaItem(int i10, int i11, MediaItem mediaItem) {
        MediaItem.LocalConfiguration localConfiguration;
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        ac.b.s(adGroupArr2[i12].isServerSideInserted || !((localConfiguration = mediaItem.localConfiguration) == null || localConfiguration.uri.equals(Uri.EMPTY)));
        adGroupArr2[i12] = adGroupArr2[i12].withAdMediaItem(mediaItem, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @Deprecated
    public AdPlaybackState withAvailableAdUri(int i10, int i11, Uri uri) {
        return withAvailableAdMediaItem(i10, i11, MediaItem.fromUri(uri));
    }

    public AdPlaybackState withContentDurationUs(long j10) {
        return this.contentDurationUs == j10 ? this : new AdPlaybackState(this.adsId, this.adGroups, this.adResumePositionUs, j10, this.removedAdGroupCount);
    }

    public AdPlaybackState withContentResumeOffsetUs(int i10, long j10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i11].contentResumeOffsetUs == j10) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withContentResumeOffsetUs(j10);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withIsPlaceholder(int i10, boolean z, boolean z5) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup adGroup = adGroupArr[i11];
        if (adGroup.isPlaceholder == z && adGroup.isServerSideInserted == z5) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withIsPlaceholder(z, z5);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withIsServerSideInserted(int i10, boolean z) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i11].isServerSideInserted == z) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withIsServerSideInserted(z);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withLastAdRemoved(int i10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withLastAdRemoved();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @Deprecated
    public AdPlaybackState withLivePostrollPlaceholderAppended() {
        return withLivePostrollPlaceholderAppended(true);
    }

    public AdPlaybackState withNewAdGroup(int i10, long j10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup adGroup = new AdGroup(j10);
        AdGroup[] adGroupArr = (AdGroup[]) Util.nullSafeArrayAppend(this.adGroups, adGroup);
        System.arraycopy(adGroupArr, i11, adGroupArr, i11 + 1, this.adGroups.length - i11);
        adGroupArr[i11] = adGroup;
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withOriginalAdCount(int i10, int i11) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i12].originalCount == i11) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withOriginalAdCount(i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withPlayedAd(int i10, int i11) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withAdState(3, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withRemovedAdGroupCount(int i10) {
        int i11 = this.removedAdGroupCount;
        if (i11 == i10) {
            return this;
        }
        ac.b.j(i10 > i11);
        int i12 = this.adGroupCount - i10;
        AdGroup[] adGroupArr = new AdGroup[i12];
        System.arraycopy(this.adGroups, i10 - this.removedAdGroupCount, adGroupArr, 0, i12);
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, i10);
    }

    public AdPlaybackState withRemovedAdGroupCountBefore(long j10) {
        int i10 = this.removedAdGroupCount;
        while (i10 < this.adGroupCount) {
            long j11 = getAdGroup(i10).timeUs;
            if (j10 <= j11 || j11 == Long.MIN_VALUE) {
                break;
            }
            i10++;
        }
        return withRemovedAdGroupCount(i10);
    }

    public AdPlaybackState withResetAdGroup(int i10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withAllAdsReset();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAd(int i10, int i11) {
        int i12 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i12] = adGroupArr2[i12].withAdState(2, i11);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAdGroup(int i10) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withAllAdsSkipped();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public boolean isLivePostrollPlaceholder(int i10, boolean z) {
        return i10 == this.adGroupCount - 1 && getAdGroup(i10).isLivePostrollPlaceholder(z);
    }

    public AdPlaybackState withLivePostrollPlaceholderAppended(boolean z) {
        return withNewAdGroup(this.adGroupCount, Long.MIN_VALUE).withIsPlaceholder(this.adGroupCount, true, z);
    }

    private AdPlaybackState(Object obj, AdGroup[] adGroupArr, long j10, long j11, int i10) {
        this.adsId = obj;
        this.adResumePositionUs = j10;
        this.contentDurationUs = j11;
        this.adGroupCount = adGroupArr.length + i10;
        this.adGroups = adGroupArr;
        this.removedAdGroupCount = i10;
    }

    public boolean endsWithLivePostrollPlaceHolder(boolean z) {
        int i10 = this.adGroupCount - 1;
        return i10 >= 0 && isLivePostrollPlaceholder(i10, z);
    }

    public AdPlaybackState withAdDurationsUs(int i10, long... jArr) {
        int i11 = i10 - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i11] = adGroupArr2[i11].withAdDurationsUs(jArr);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }
}
