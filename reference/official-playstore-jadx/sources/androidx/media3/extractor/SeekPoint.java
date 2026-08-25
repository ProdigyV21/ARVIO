package androidx.media3.extractor;

/* JADX INFO: loaded from: classes3.dex */
public final class SeekPoint {
    public static final SeekPoint START = new SeekPoint(0, 0);
    public final long position;
    public final long timeUs;

    public SeekPoint(long j10, long j11) {
        this.timeUs = j10;
        this.position = j11;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && SeekPoint.class == obj.getClass()) {
            SeekPoint seekPoint = (SeekPoint) obj;
            if (this.timeUs == seekPoint.timeUs && this.position == seekPoint.position) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((int) this.timeUs) * 31) + ((int) this.position);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("[timeUs=");
        sb2.append(this.timeUs);
        sb2.append(", position=");
        return a0.c.j(this.position, "]", sb2);
    }
}
