package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.e1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Timeline {
    public static final Timeline EMPTY = new Timeline() { // from class: androidx.media3.common.Timeline.1
        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Period getPeriod(int i10, Period period, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 0;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public Window getWindow(int i10, Window window, long j10) {
            throw new IndexOutOfBoundsException();
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 0;
        }
    };
    private static final String FIELD_WINDOWS = Util.intToStringMaxRadix(0);
    private static final String FIELD_PERIODS = Util.intToStringMaxRadix(1);
    private static final String FIELD_SHUFFLED_WINDOW_INDICES = Util.intToStringMaxRadix(2);

    public static final class Period {
        public AdPlaybackState adPlaybackState = AdPlaybackState.NONE;
        public long durationUs;
        public Object id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        public Object uid;
        public int windowIndex;
        private static final String FIELD_WINDOW_INDEX = Util.intToStringMaxRadix(0);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(1);
        private static final String FIELD_POSITION_IN_WINDOW_US = Util.intToStringMaxRadix(2);
        private static final String FIELD_PLACEHOLDER = Util.intToStringMaxRadix(3);
        private static final String FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(4);

        public static Period fromBundle(Bundle bundle) {
            int i10 = bundle.getInt(FIELD_WINDOW_INDEX, 0);
            long j10 = bundle.getLong(FIELD_DURATION_US, C.TIME_UNSET);
            long j11 = bundle.getLong(FIELD_POSITION_IN_WINDOW_US, 0L);
            boolean z = bundle.getBoolean(FIELD_PLACEHOLDER, false);
            Bundle bundle2 = bundle.getBundle(FIELD_AD_PLAYBACK_STATE);
            AdPlaybackState adPlaybackStateFromBundle = bundle2 != null ? AdPlaybackState.fromBundle(bundle2) : AdPlaybackState.NONE;
            Period period = new Period();
            period.set(null, null, i10, j10, j11, adPlaybackStateFromBundle, z);
            return period;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Period.class.equals(obj.getClass())) {
                Period period = (Period) obj;
                if (Objects.equals(this.id, period.id) && Objects.equals(this.uid, period.uid) && this.windowIndex == period.windowIndex && this.durationUs == period.durationUs && this.positionInWindowUs == period.positionInWindowUs && this.isPlaceholder == period.isPlaceholder && Objects.equals(this.adPlaybackState, period.adPlaybackState)) {
                    return true;
                }
            }
            return false;
        }

        public int getAdCountInAdGroup(int i10) {
            return this.adPlaybackState.getAdGroup(i10).count;
        }

        public long getAdDurationUs(int i10, int i11) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i10);
            return adGroup.count != -1 ? adGroup.durationsUs[i11] : C.TIME_UNSET;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long j10) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j10, this.durationUs);
        }

        public int getAdGroupIndexForPositionUs(long j10) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j10, this.durationUs);
        }

        public long getAdGroupTimeUs(int i10) {
            return this.adPlaybackState.getAdGroup(i10).timeUs;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public int getAdState(int i10, int i11) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i10);
            if (adGroup.count != -1) {
                return adGroup.states[i11];
            }
            return 0;
        }

        public Object getAdsId() {
            return this.adPlaybackState.adsId;
        }

        public long getContentResumeOffsetUs(int i10) {
            return this.adPlaybackState.getAdGroup(i10).contentResumeOffsetUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int i10) {
            return this.adPlaybackState.getAdGroup(i10).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int i10, int i11) {
            return this.adPlaybackState.getAdGroup(i10).getNextAdIndexToPlay(i11);
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public boolean hasPlayedAdGroup(int i10) {
            return !this.adPlaybackState.getAdGroup(i10).hasUnplayedAds();
        }

        public int hashCode() {
            Object obj = this.id;
            int iHashCode = (217 + (obj == null ? 0 : obj.hashCode())) * 31;
            Object obj2 = this.uid;
            int iHashCode2 = (((iHashCode + (obj2 != null ? obj2.hashCode() : 0)) * 31) + this.windowIndex) * 31;
            long j10 = this.durationUs;
            int i10 = (iHashCode2 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.positionInWindowUs;
            return this.adPlaybackState.hashCode() + ((((i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31);
        }

        public boolean isLivePostrollPlaceholder(int i10) {
            return i10 == getAdGroupCount() - 1 && this.adPlaybackState.isLivePostrollPlaceholder(i10);
        }

        public boolean isServerSideInsertedAdGroup(int i10) {
            return this.adPlaybackState.getAdGroup(i10).isServerSideInserted;
        }

        public Period set(Object obj, Object obj2, int i10, long j10, long j11) {
            return set(obj, obj2, i10, j10, j11, AdPlaybackState.NONE, false);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            int i10 = this.windowIndex;
            if (i10 != 0) {
                bundle.putInt(FIELD_WINDOW_INDEX, i10);
            }
            long j10 = this.durationUs;
            if (j10 != C.TIME_UNSET) {
                bundle.putLong(FIELD_DURATION_US, j10);
            }
            long j11 = this.positionInWindowUs;
            if (j11 != 0) {
                bundle.putLong(FIELD_POSITION_IN_WINDOW_US, j11);
            }
            boolean z = this.isPlaceholder;
            if (z) {
                bundle.putBoolean(FIELD_PLACEHOLDER, z);
            }
            if (!this.adPlaybackState.equals(AdPlaybackState.NONE)) {
                bundle.putBundle(FIELD_AD_PLAYBACK_STATE, this.adPlaybackState.toBundle());
            }
            return bundle;
        }

        public Period set(Object obj, Object obj2, int i10, long j10, long j11, AdPlaybackState adPlaybackState, boolean z) {
            this.id = obj;
            this.uid = obj2;
            this.windowIndex = i10;
            this.durationUs = j10;
            this.positionInWindowUs = j11;
            this.adPlaybackState = adPlaybackState;
            this.isPlaceholder = z;
            return this;
        }
    }

    public static final class RemotableTimeline extends Timeline {
        private final h1 periods;
        private final int[] shuffledWindowIndices;
        private final int[] windowIndicesInShuffled;
        private final h1 windows;

        public RemotableTimeline(h1 h1Var, h1 h1Var2, int[] iArr) {
            ac.b.j(h1Var.size() == iArr.length);
            this.windows = h1Var;
            this.periods = h1Var2;
            this.shuffledWindowIndices = iArr;
            this.windowIndicesInShuffled = new int[iArr.length];
            for (int i10 = 0; i10 < iArr.length; i10++) {
                this.windowIndicesInShuffled[iArr[i10]] = i10;
            }
        }

        @Override // androidx.media3.common.Timeline
        public int getFirstWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            if (z) {
                return this.shuffledWindowIndices[0];
            }
            return 0;
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.common.Timeline
        public int getLastWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            return z ? this.shuffledWindowIndices[getWindowCount() - 1] : getWindowCount() - 1;
        }

        @Override // androidx.media3.common.Timeline
        public int getNextWindowIndex(int i10, int i11, boolean z) {
            if (i11 == 1) {
                return i10;
            }
            if (i10 != getLastWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i10] + 1] : i10 + 1;
            }
            if (i11 == 2) {
                return getFirstWindowIndex(z);
            }
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Period getPeriod(int i10, Period period, boolean z) {
            Period period2 = (Period) this.periods.get(i10);
            period.set(period2.id, period2.uid, period2.windowIndex, period2.durationUs, period2.positionInWindowUs, period2.adPlaybackState, period2.isPlaceholder);
            return period;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return this.periods.size();
        }

        @Override // androidx.media3.common.Timeline
        public int getPreviousWindowIndex(int i10, int i11, boolean z) {
            if (i11 == 1) {
                return i10;
            }
            if (i10 != getFirstWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i10] - 1] : i10 - 1;
            }
            if (i11 == 2) {
                return getLastWindowIndex(z);
            }
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int i10) {
            throw new UnsupportedOperationException();
        }

        @Override // androidx.media3.common.Timeline
        public Window getWindow(int i10, Window window, long j10) {
            Window window2 = (Window) this.windows.get(i10);
            window.set(window2.uid, window2.mediaItem, window2.manifest, window2.presentationStartTimeMs, window2.windowStartTimeMs, window2.elapsedRealtimeEpochOffsetMs, window2.isSeekable, window2.isDynamic, window2.liveConfiguration, window2.defaultPositionUs, window2.durationUs, window2.firstPeriodIndex, window2.lastPeriodIndex, window2.positionInFirstPeriodUs);
            window.isPlaceholder = window2.isPlaceholder;
            return window;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return this.windows.size();
        }
    }

    public static final class Window {
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public MediaItem.LiveConfiguration liveConfiguration;
        public Object manifest;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;

        @Deprecated
        public Object tag;
        public long windowStartTimeMs;
        public static final Object SINGLE_WINDOW_UID = new Object();
        private static final Object FAKE_WINDOW_UID = new Object();
        private static final MediaItem PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("androidx.media3.common.Timeline").setUri(Uri.EMPTY).build();
        private static final String FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
        private static final String FIELD_PRESENTATION_START_TIME_MS = Util.intToStringMaxRadix(2);
        private static final String FIELD_WINDOW_START_TIME_MS = Util.intToStringMaxRadix(3);
        private static final String FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = Util.intToStringMaxRadix(4);
        private static final String FIELD_IS_SEEKABLE = Util.intToStringMaxRadix(5);
        private static final String FIELD_IS_DYNAMIC = Util.intToStringMaxRadix(6);
        private static final String FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(7);
        private static final String FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(8);
        private static final String FIELD_DEFAULT_POSITION_US = Util.intToStringMaxRadix(9);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(10);
        private static final String FIELD_FIRST_PERIOD_INDEX = Util.intToStringMaxRadix(11);
        private static final String FIELD_LAST_PERIOD_INDEX = Util.intToStringMaxRadix(12);
        private static final String FIELD_POSITION_IN_FIRST_PERIOD_US = Util.intToStringMaxRadix(13);
        public Object uid = SINGLE_WINDOW_UID;
        public MediaItem mediaItem = PLACEHOLDER_MEDIA_ITEM;

        public static Window fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(FIELD_MEDIA_ITEM);
            MediaItem mediaItemFromBundle = bundle2 != null ? MediaItem.fromBundle(bundle2) : MediaItem.EMPTY;
            long j10 = bundle.getLong(FIELD_PRESENTATION_START_TIME_MS, C.TIME_UNSET);
            long j11 = bundle.getLong(FIELD_WINDOW_START_TIME_MS, C.TIME_UNSET);
            long j12 = bundle.getLong(FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS, C.TIME_UNSET);
            boolean z = bundle.getBoolean(FIELD_IS_SEEKABLE, false);
            boolean z5 = bundle.getBoolean(FIELD_IS_DYNAMIC, false);
            Bundle bundle3 = bundle.getBundle(FIELD_LIVE_CONFIGURATION);
            MediaItem.LiveConfiguration liveConfigurationFromBundle = bundle3 != null ? MediaItem.LiveConfiguration.fromBundle(bundle3) : null;
            boolean z10 = bundle.getBoolean(FIELD_IS_PLACEHOLDER, false);
            long j13 = bundle.getLong(FIELD_DEFAULT_POSITION_US, 0L);
            long j14 = bundle.getLong(FIELD_DURATION_US, C.TIME_UNSET);
            int i10 = bundle.getInt(FIELD_FIRST_PERIOD_INDEX, 0);
            int i11 = bundle.getInt(FIELD_LAST_PERIOD_INDEX, 0);
            long j15 = bundle.getLong(FIELD_POSITION_IN_FIRST_PERIOD_US, 0L);
            Window window = new Window();
            window.set(FAKE_WINDOW_UID, mediaItemFromBundle, null, j10, j11, j12, z, z5, liveConfigurationFromBundle, j13, j14, i10, i11, j15);
            window.isPlaceholder = z10;
            return window;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Window.class.equals(obj.getClass())) {
                Window window = (Window) obj;
                if (Objects.equals(this.uid, window.uid) && Objects.equals(this.mediaItem, window.mediaItem) && Objects.equals(this.manifest, window.manifest) && Objects.equals(this.liveConfiguration, window.liveConfiguration) && this.presentationStartTimeMs == window.presentationStartTimeMs && this.windowStartTimeMs == window.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == window.elapsedRealtimeEpochOffsetMs && this.isSeekable == window.isSeekable && this.isDynamic == window.isDynamic && this.isPlaceholder == window.isPlaceholder && this.defaultPositionUs == window.defaultPositionUs && this.durationUs == window.durationUs && this.firstPeriodIndex == window.firstPeriodIndex && this.lastPeriodIndex == window.lastPeriodIndex && this.positionInFirstPeriodUs == window.positionInFirstPeriodUs) {
                    return true;
                }
            }
            return false;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return Util.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public int hashCode() {
            int iHashCode = (this.mediaItem.hashCode() + ((this.uid.hashCode() + 217) * 31)) * 31;
            Object obj = this.manifest;
            int iHashCode2 = (iHashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            int iHashCode3 = (iHashCode2 + (liveConfiguration != null ? liveConfiguration.hashCode() : 0)) * 31;
            long j10 = this.presentationStartTimeMs;
            int i10 = (iHashCode3 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.windowStartTimeMs;
            int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
            long j12 = this.elapsedRealtimeEpochOffsetMs;
            int i12 = (((((((i11 + ((int) (j12 ^ (j12 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31;
            long j13 = this.defaultPositionUs;
            int i13 = (i12 + ((int) (j13 ^ (j13 >>> 32)))) * 31;
            long j14 = this.durationUs;
            int i14 = (((((i13 + ((int) (j14 ^ (j14 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31;
            long j15 = this.positionInFirstPeriodUs;
            return i14 + ((int) (j15 ^ (j15 >>> 32)));
        }

        public boolean isLive() {
            return this.liveConfiguration != null;
        }

        public Window set(Object obj, MediaItem mediaItem, Object obj2, long j10, long j11, long j12, boolean z, boolean z5, MediaItem.LiveConfiguration liveConfiguration, long j13, long j14, int i10, int i11, long j15) {
            MediaItem.LocalConfiguration localConfiguration;
            this.uid = obj;
            this.mediaItem = mediaItem != null ? mediaItem : PLACEHOLDER_MEDIA_ITEM;
            this.tag = (mediaItem == null || (localConfiguration = mediaItem.localConfiguration) == null) ? null : localConfiguration.tag;
            this.manifest = obj2;
            this.presentationStartTimeMs = j10;
            this.windowStartTimeMs = j11;
            this.elapsedRealtimeEpochOffsetMs = j12;
            this.isSeekable = z;
            this.isDynamic = z5;
            this.liveConfiguration = liveConfiguration;
            this.defaultPositionUs = j13;
            this.durationUs = j14;
            this.firstPeriodIndex = i10;
            this.lastPeriodIndex = i11;
            this.positionInFirstPeriodUs = j15;
            this.isPlaceholder = false;
            return this;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            if (!MediaItem.EMPTY.equals(this.mediaItem)) {
                bundle.putBundle(FIELD_MEDIA_ITEM, this.mediaItem.toBundle());
            }
            long j10 = this.presentationStartTimeMs;
            if (j10 != C.TIME_UNSET) {
                bundle.putLong(FIELD_PRESENTATION_START_TIME_MS, j10);
            }
            long j11 = this.windowStartTimeMs;
            if (j11 != C.TIME_UNSET) {
                bundle.putLong(FIELD_WINDOW_START_TIME_MS, j11);
            }
            long j12 = this.elapsedRealtimeEpochOffsetMs;
            if (j12 != C.TIME_UNSET) {
                bundle.putLong(FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS, j12);
            }
            boolean z = this.isSeekable;
            if (z) {
                bundle.putBoolean(FIELD_IS_SEEKABLE, z);
            }
            boolean z5 = this.isDynamic;
            if (z5) {
                bundle.putBoolean(FIELD_IS_DYNAMIC, z5);
            }
            MediaItem.LiveConfiguration liveConfiguration = this.liveConfiguration;
            if (liveConfiguration != null) {
                bundle.putBundle(FIELD_LIVE_CONFIGURATION, liveConfiguration.toBundle());
            }
            boolean z10 = this.isPlaceholder;
            if (z10) {
                bundle.putBoolean(FIELD_IS_PLACEHOLDER, z10);
            }
            long j13 = this.defaultPositionUs;
            if (j13 != 0) {
                bundle.putLong(FIELD_DEFAULT_POSITION_US, j13);
            }
            long j14 = this.durationUs;
            if (j14 != C.TIME_UNSET) {
                bundle.putLong(FIELD_DURATION_US, j14);
            }
            int i10 = this.firstPeriodIndex;
            if (i10 != 0) {
                bundle.putInt(FIELD_FIRST_PERIOD_INDEX, i10);
            }
            int i11 = this.lastPeriodIndex;
            if (i11 != 0) {
                bundle.putInt(FIELD_LAST_PERIOD_INDEX, i11);
            }
            long j15 = this.positionInFirstPeriodUs;
            if (j15 != 0) {
                bundle.putLong(FIELD_POSITION_IN_FIRST_PERIOD_US, j15);
            }
            return bundle;
        }
    }

    public static Timeline fromBundle(Bundle bundle) {
        h1 h1VarFromBundleListRetriever = fromBundleListRetriever(new e(7), bundle.getBinder(FIELD_WINDOWS));
        h1 h1VarFromBundleListRetriever2 = fromBundleListRetriever(new e(8), bundle.getBinder(FIELD_PERIODS));
        int[] intArray = bundle.getIntArray(FIELD_SHUFFLED_WINDOW_INDICES);
        if (intArray == null) {
            intArray = generateUnshuffledIndices(h1VarFromBundleListRetriever.size());
        }
        return new RemotableTimeline(h1VarFromBundleListRetriever, h1VarFromBundleListRetriever2, intArray);
    }

    private static <T> h1 fromBundleListRetriever(com.google.common.base.m<Bundle, T> mVar, IBinder iBinder) {
        if (iBinder != null) {
            return BundleCollectionUtil.fromBundleList(mVar, BundleListRetriever.getList(iBinder));
        }
        f1 f1Var = h1.f14020l;
        return o3.f14078o;
    }

    private static int[] generateUnshuffledIndices(int i10) {
        int[] iArr = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr[i11] = i11;
        }
        return iArr;
    }

    public final Timeline copyWithSingleWindow(int i10) {
        if (getWindowCount() == 1) {
            return this;
        }
        Window window = getWindow(i10, new Window(), 0L);
        e1 e1VarK = h1.k();
        int i11 = window.firstPeriodIndex;
        while (true) {
            int i12 = window.lastPeriodIndex;
            if (i11 > i12) {
                window.lastPeriodIndex = i12 - window.firstPeriodIndex;
                window.firstPeriodIndex = 0;
                return new RemotableTimeline(h1.s(window), e1VarK.f(), new int[]{0});
            }
            Period period = getPeriod(i11, new Period(), true);
            period.windowIndex = 0;
            e1VarK.c(period);
            i11++;
        }
    }

    public boolean equals(Object obj) {
        int lastWindowIndex;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timeline)) {
            return false;
        }
        Timeline timeline = (Timeline) obj;
        if (timeline.getWindowCount() != getWindowCount() || timeline.getPeriodCount() != getPeriodCount()) {
            return false;
        }
        Window window = new Window();
        Period period = new Period();
        Window window2 = new Window();
        Period period2 = new Period();
        for (int i10 = 0; i10 < getWindowCount(); i10++) {
            if (!getWindow(i10, window).equals(timeline.getWindow(i10, window2))) {
                return false;
            }
        }
        for (int i11 = 0; i11 < getPeriodCount(); i11++) {
            if (!getPeriod(i11, period, true).equals(timeline.getPeriod(i11, period2, true))) {
                return false;
            }
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        if (firstWindowIndex != timeline.getFirstWindowIndex(true) || (lastWindowIndex = getLastWindowIndex(true)) != timeline.getLastWindowIndex(true)) {
            return false;
        }
        while (firstWindowIndex != lastWindowIndex) {
            int nextWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
            if (nextWindowIndex != timeline.getNextWindowIndex(firstWindowIndex, 0, true)) {
                return false;
            }
            firstWindowIndex = nextWindowIndex;
        }
        return true;
    }

    public int getFirstWindowIndex(boolean z) {
        return isEmpty() ? -1 : 0;
    }

    public abstract int getIndexOfPeriod(Object obj);

    public int getLastWindowIndex(boolean z) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public final int getNextPeriodIndex(int i10, Period period, Window window, int i11, boolean z) {
        int i12 = getPeriod(i10, period).windowIndex;
        if (getWindow(i12, window).lastPeriodIndex != i10) {
            return i10 + 1;
        }
        int nextWindowIndex = getNextWindowIndex(i12, i11, z);
        if (nextWindowIndex == -1) {
            return -1;
        }
        return getWindow(nextWindowIndex, window).firstPeriodIndex;
    }

    public int getNextWindowIndex(int i10, int i11, boolean z) {
        if (i11 == 0) {
            if (i10 == getLastWindowIndex(z)) {
                return -1;
            }
            return i10 + 1;
        }
        if (i11 == 1) {
            return i10;
        }
        if (i11 == 2) {
            return i10 == getLastWindowIndex(z) ? getFirstWindowIndex(z) : i10 + 1;
        }
        throw new IllegalStateException();
    }

    public final Period getPeriod(int i10, Period period) {
        return getPeriod(i10, period, false);
    }

    public abstract Period getPeriod(int i10, Period period, boolean z);

    public Period getPeriodByUid(Object obj, Period period) {
        return getPeriod(getIndexOfPeriod(obj), period, true);
    }

    public abstract int getPeriodCount();

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i10, long j10) {
        return getPeriodPositionUs(window, period, i10, j10);
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i10, long j10) {
        Pair<Object, Long> periodPositionUs = getPeriodPositionUs(window, period, i10, j10, 0L);
        periodPositionUs.getClass();
        return periodPositionUs;
    }

    public int getPreviousWindowIndex(int i10, int i11, boolean z) {
        if (i11 == 0) {
            if (i10 == getFirstWindowIndex(z)) {
                return -1;
            }
            return i10 - 1;
        }
        if (i11 == 1) {
            return i10;
        }
        if (i11 == 2) {
            return i10 == getFirstWindowIndex(z) ? getLastWindowIndex(z) : i10 - 1;
        }
        throw new IllegalStateException();
    }

    public abstract Object getUidOfPeriod(int i10);

    public final Window getWindow(int i10, Window window) {
        return getWindow(i10, window, 0L);
    }

    public abstract Window getWindow(int i10, Window window, long j10);

    public abstract int getWindowCount();

    public int hashCode() {
        Window window = new Window();
        Period period = new Period();
        int windowCount = getWindowCount() + 217;
        for (int i10 = 0; i10 < getWindowCount(); i10++) {
            windowCount = (windowCount * 31) + getWindow(i10, window).hashCode();
        }
        int periodCount = getPeriodCount() + (windowCount * 31);
        for (int i11 = 0; i11 < getPeriodCount(); i11++) {
            periodCount = (periodCount * 31) + getPeriod(i11, period, true).hashCode();
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        while (firstWindowIndex != -1) {
            periodCount = (periodCount * 31) + firstWindowIndex;
            firstWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
        }
        return periodCount;
    }

    public final boolean isEmpty() {
        return getWindowCount() == 0;
    }

    public final boolean isLastPeriod(int i10, Period period, Window window, int i11, boolean z) {
        return getNextPeriodIndex(i10, period, window, i11, z) == -1;
    }

    public final Bundle toBundle() {
        ArrayList arrayList = new ArrayList();
        int windowCount = getWindowCount();
        Window window = new Window();
        for (int i10 = 0; i10 < windowCount; i10++) {
            arrayList.add(getWindow(i10, window, 0L).toBundle());
        }
        ArrayList arrayList2 = new ArrayList();
        int periodCount = getPeriodCount();
        Period period = new Period();
        for (int i11 = 0; i11 < periodCount; i11++) {
            arrayList2.add(getPeriod(i11, period, false).toBundle());
        }
        int[] iArr = new int[windowCount];
        if (windowCount > 0) {
            iArr[0] = getFirstWindowIndex(true);
        }
        for (int i12 = 1; i12 < windowCount; i12++) {
            iArr[i12] = getNextWindowIndex(iArr[i12 - 1], 0, true);
        }
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_WINDOWS, new BundleListRetriever(arrayList));
        bundle.putBinder(FIELD_PERIODS, new BundleListRetriever(arrayList2));
        bundle.putIntArray(FIELD_SHUFFLED_WINDOW_INDICES, iArr);
        return bundle;
    }

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i10, long j10, long j11) {
        return getPeriodPositionUs(window, period, i10, j10, j11);
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i10, long j10, long j11) {
        ac.b.n(i10, getWindowCount());
        getWindow(i10, window, j11);
        if (j10 == C.TIME_UNSET) {
            j10 = window.getDefaultPositionUs();
            if (j10 == C.TIME_UNSET) {
                return null;
            }
        }
        int i11 = window.firstPeriodIndex;
        getPeriod(i11, period);
        while (i11 < window.lastPeriodIndex && period.positionInWindowUs != j10) {
            int i12 = i11 + 1;
            if (getPeriod(i12, period).positionInWindowUs > j10) {
                break;
            }
            i11 = i12;
        }
        getPeriod(i11, period, true);
        long jMin = j10 - period.positionInWindowUs;
        long j12 = period.durationUs;
        if (j12 != C.TIME_UNSET) {
            jMin = Math.min(jMin, j12 - 1);
        }
        long jMax = Math.max(0L, jMin);
        Object obj = period.uid;
        obj.getClass();
        return Pair.create(obj, Long.valueOf(jMax));
    }
}
