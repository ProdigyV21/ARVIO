package androidx.media3.common;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Util;
import com.google.common.base.r;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class Metadata {
    private final Entry[] entries;
    public final long presentationTimeUs;

    public interface Entry {
        byte[] getWrappedMetadataBytes();

        Format getWrappedMetadataFormat();

        void populateMediaMetadata(MediaMetadata.Builder builder);
    }

    public Metadata(Entry... entryArr) {
        this(C.TIME_UNSET, entryArr);
    }

    private <T extends Entry> T entryIfMatches(Entry entry, Class<T> cls, r<T> rVar) {
        if (!cls.isAssignableFrom(entry.getClass())) {
            return null;
        }
        T tCast = cls.cast(entry);
        if (rVar.apply(tCast)) {
            return tCast;
        }
        return null;
    }

    public Metadata copyWithAppendedEntries(Entry... entryArr) {
        return entryArr.length == 0 ? this : new Metadata(this.presentationTimeUs, (Entry[]) Util.nullSafeArrayConcatenation(this.entries, entryArr));
    }

    public Metadata copyWithAppendedEntriesFrom(Metadata metadata) {
        return metadata == null ? this : copyWithAppendedEntries(metadata.entries);
    }

    public Metadata copyWithPresentationTimeUs(long j10) {
        return this.presentationTimeUs == j10 ? this : new Metadata(j10, this.entries);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Metadata.class == obj.getClass()) {
            Metadata metadata = (Metadata) obj;
            if (Arrays.equals(this.entries, metadata.entries) && this.presentationTimeUs == metadata.presentationTimeUs) {
                return true;
            }
        }
        return false;
    }

    public Entry get(int i10) {
        return this.entries[i10];
    }

    public <T extends Entry> h1 getEntriesOfType(Class<T> cls) {
        e1 e1VarK = h1.k();
        for (Entry entry : this.entries) {
            if (cls.isAssignableFrom(entry.getClass())) {
                e1VarK.c(cls.cast(entry));
            }
        }
        return e1VarK.f();
    }

    public <T extends Entry> T getFirstEntryOfType(Class<T> cls) {
        return (T) getFirstMatchingEntry(cls, com.google.common.base.x.f13913i);
    }

    public <T extends Entry> T getFirstMatchingEntry(Class<T> cls, r<T> rVar) {
        for (Entry entry : this.entries) {
            T t2 = (T) entryIfMatches(entry, cls, rVar);
            if (t2 != null) {
                return t2;
            }
        }
        return null;
    }

    public <T extends Entry> h1 getMatchingEntries(Class<T> cls, r<T> rVar) {
        e1 e1VarK = h1.k();
        for (Entry entry : this.entries) {
            Entry entryEntryIfMatches = entryIfMatches(entry, cls, rVar);
            if (entryEntryIfMatches != null) {
                e1VarK.c(entryEntryIfMatches);
            }
        }
        return e1VarK.f();
    }

    public int hashCode() {
        return qb.d.F(this.presentationTimeUs) + (Arrays.hashCode(this.entries) * 31);
    }

    public int length() {
        return this.entries.length;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("entries=");
        sb2.append(Arrays.toString(this.entries));
        if (this.presentationTimeUs == C.TIME_UNSET) {
            str = "";
        } else {
            str = ", presentationTimeUs=" + this.presentationTimeUs;
        }
        sb2.append(str);
        return sb2.toString();
    }

    public Metadata(long j10, Entry... entryArr) {
        this.presentationTimeUs = j10;
        this.entries = entryArr;
    }

    public Metadata(List<? extends Entry> list) {
        this((Entry[]) list.toArray(new Entry[0]));
    }

    public Metadata(long j10, List<? extends Entry> list) {
        this(j10, (Entry[]) list.toArray(new Entry[0]));
    }
}
