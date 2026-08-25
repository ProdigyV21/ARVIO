package androidx.media3.session;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class QueueTimeline extends Timeline {
    public static final QueueTimeline DEFAULT;
    private static final Object FAKE_WINDOW_UID;
    private final QueuedMediaItem fakeQueuedMediaItem;
    private final com.google.common.collect.h1 queuedMediaItems;

    public static final class QueuedMediaItem {
        public final long durationMs;
        public final MediaItem mediaItem;
        public final long queueId;

        public QueuedMediaItem(MediaItem mediaItem, long j10, long j11) {
            this.mediaItem = mediaItem;
            this.queueId = j10;
            this.durationMs = j11;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof QueuedMediaItem)) {
                return false;
            }
            QueuedMediaItem queuedMediaItem = (QueuedMediaItem) obj;
            return this.queueId == queuedMediaItem.queueId && this.mediaItem.equals(queuedMediaItem.mediaItem) && this.durationMs == queuedMediaItem.durationMs;
        }

        public int hashCode() {
            long j10 = this.queueId;
            int iHashCode = (this.mediaItem.hashCode() + ((217 + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31;
            long j11 = this.durationMs;
            return iHashCode + ((int) ((j11 >>> 32) ^ j11));
        }
    }

    static {
        com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
        DEFAULT = new QueueTimeline(o3.f14078o, null);
        FAKE_WINDOW_UID = new Object();
    }

    private QueueTimeline(com.google.common.collect.h1 h1Var, QueuedMediaItem queuedMediaItem) {
        this.queuedMediaItems = h1Var;
        this.fakeQueuedMediaItem = queuedMediaItem;
    }

    public static QueueTimeline create(List<MediaSessionCompat.QueueItem> list) {
        com.google.common.collect.a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        int i11 = 0;
        while (i10 < list.size()) {
            MediaSessionCompat.QueueItem queueItem = list.get(i10);
            QueuedMediaItem queuedMediaItem = new QueuedMediaItem(LegacyConversions.convertToMediaItem(queueItem), queueItem.getQueueId(), C.TIME_UNSET);
            int i12 = i11 + 1;
            int iB = com.google.common.collect.b1.b(objArrCopyOf.length, i12);
            if (iB > objArrCopyOf.length) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
            }
            objArrCopyOf[i11] = queuedMediaItem;
            i10++;
            i11 = i12;
        }
        return new QueueTimeline(com.google.common.collect.h1.h(i11, objArrCopyOf), null);
    }

    private QueuedMediaItem getQueuedMediaItem(int i10) {
        QueuedMediaItem queuedMediaItem;
        return (i10 != this.queuedMediaItems.size() || (queuedMediaItem = this.fakeQueuedMediaItem) == null) ? (QueuedMediaItem) this.queuedMediaItems.get(i10) : queuedMediaItem;
    }

    public boolean contains(MediaItem mediaItem) {
        QueuedMediaItem queuedMediaItem = this.fakeQueuedMediaItem;
        if (queuedMediaItem != null && mediaItem.equals(queuedMediaItem.mediaItem)) {
            return true;
        }
        for (int i10 = 0; i10 < this.queuedMediaItems.size(); i10++) {
            if (mediaItem.equals(((QueuedMediaItem) this.queuedMediaItems.get(i10)).mediaItem)) {
                return true;
            }
        }
        return false;
    }

    public QueueTimeline copy() {
        return new QueueTimeline(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithClearedFakeMediaItem() {
        return new QueueTimeline(this.queuedMediaItems, null);
    }

    public QueueTimeline copyWithFakeMediaItem(MediaItem mediaItem, long j10) {
        return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1L, j10));
    }

    public QueueTimeline copyWithMovedMediaItems(int i10, int i11, int i12) {
        ArrayList arrayList = new ArrayList(this.queuedMediaItems);
        Util.moveItems(arrayList, i10, i11, i12);
        return new QueueTimeline(com.google.common.collect.h1.n(arrayList), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithNewMediaItem(int i10, MediaItem mediaItem, long j10) {
        ac.b.j(i10 < this.queuedMediaItems.size() || (i10 == this.queuedMediaItems.size() && this.fakeQueuedMediaItem != null));
        if (i10 == this.queuedMediaItems.size()) {
            return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1L, j10));
        }
        long j11 = ((QueuedMediaItem) this.queuedMediaItems.get(i10)).queueId;
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        e1Var.d(this.queuedMediaItems.subList(0, i10));
        e1Var.c(new QueuedMediaItem(mediaItem, j11, j10));
        com.google.common.collect.h1 h1Var = this.queuedMediaItems;
        e1Var.d(h1Var.subList(i10 + 1, h1Var.size()));
        return new QueueTimeline(e1Var.f(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithNewMediaItems(int i10, List<MediaItem> list) {
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        e1Var.d(this.queuedMediaItems.subList(0, i10));
        for (int i11 = 0; i11 < list.size(); i11++) {
            e1Var.c(new QueuedMediaItem(list.get(i11), -1L, C.TIME_UNSET));
        }
        com.google.common.collect.h1 h1Var = this.queuedMediaItems;
        e1Var.d(h1Var.subList(i10, h1Var.size()));
        return new QueueTimeline(e1Var.f(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithRemovedMediaItems(int i10, int i11) {
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        e1Var.d(this.queuedMediaItems.subList(0, i10));
        com.google.common.collect.h1 h1Var = this.queuedMediaItems;
        e1Var.d(h1Var.subList(i11, h1Var.size()));
        return new QueueTimeline(e1Var.f(), this.fakeQueuedMediaItem);
    }

    @Override // androidx.media3.common.Timeline
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QueueTimeline)) {
            return false;
        }
        QueueTimeline queueTimeline = (QueueTimeline) obj;
        return Objects.equals(this.queuedMediaItems, queueTimeline.queuedMediaItems) && Objects.equals(this.fakeQueuedMediaItem, queueTimeline.fakeQueuedMediaItem);
    }

    @Override // androidx.media3.common.Timeline
    public int getIndexOfPeriod(Object obj) {
        throw new UnsupportedOperationException();
    }

    public MediaItem getMediaItemAt(int i10) {
        if (i10 >= getWindowCount()) {
            return null;
        }
        return getQueuedMediaItem(i10).mediaItem;
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Period getPeriod(int i10, Timeline.Period period, boolean z) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i10);
        period.set(Long.valueOf(queuedMediaItem.queueId), null, i10, Util.msToUs(queuedMediaItem.durationMs), 0L);
        return period;
    }

    @Override // androidx.media3.common.Timeline
    public int getPeriodCount() {
        return getWindowCount();
    }

    public long getQueueId(int i10) {
        if (i10 < 0 || i10 >= this.queuedMediaItems.size()) {
            return -1L;
        }
        return ((QueuedMediaItem) this.queuedMediaItems.get(i10)).queueId;
    }

    @Override // androidx.media3.common.Timeline
    public Object getUidOfPeriod(int i10) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.media3.common.Timeline
    public Timeline.Window getWindow(int i10, Timeline.Window window, long j10) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i10);
        window.set(FAKE_WINDOW_UID, queuedMediaItem.mediaItem, null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, true, false, null, 0L, Util.msToUs(queuedMediaItem.durationMs), i10, i10, 0L);
        return window;
    }

    @Override // androidx.media3.common.Timeline
    public int getWindowCount() {
        return this.queuedMediaItems.size() + (this.fakeQueuedMediaItem == null ? 0 : 1);
    }

    @Override // androidx.media3.common.Timeline
    public int hashCode() {
        return Objects.hash(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }
}
