package io.ktor.http;

import androidx.compose.material3.TextFieldImplKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lio/ktor/http/ContentRange;", "", "()V", "Bounded", TextFieldImplKt.SuffixId, "TailFrom", "Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange$Suffix;", "Lio/ktor/http/ContentRange$TailFrom;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ContentRange {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lio/ktor/http/ContentRange$Bounded;", "Lio/ktor/http/ContentRange;", "from", "", "to", "(JJ)V", "getFrom", "()J", "getTo", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Bounded extends ContentRange {
        private final long from;
        private final long to;

        public Bounded(long j10, long j11) {
            super(null);
            this.from = j10;
            this.to = j11;
        }

        public static /* synthetic */ Bounded copy$default(Bounded bounded, long j10, long j11, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = bounded.from;
            }
            if ((i10 & 2) != 0) {
                j11 = bounded.to;
            }
            return bounded.copy(j10, j11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrom() {
            return this.from;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTo() {
            return this.to;
        }

        public final Bounded copy(long from, long to) {
            return new Bounded(from, to);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Bounded)) {
                return false;
            }
            Bounded bounded = (Bounded) other;
            return this.from == bounded.from && this.to == bounded.to;
        }

        public final long getFrom() {
            return this.from;
        }

        public final long getTo() {
            return this.to;
        }

        public int hashCode() {
            long j10 = this.from;
            int i10 = ((int) (j10 ^ (j10 >>> 32))) * 31;
            long j11 = this.to;
            return i10 + ((int) ((j11 >>> 32) ^ j11));
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.from);
            sb2.append('-');
            sb2.append(this.to);
            return sb2.toString();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/ContentRange$Suffix;", "Lio/ktor/http/ContentRange;", "lastCount", "", "(J)V", "getLastCount", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Suffix extends ContentRange {
        private final long lastCount;

        public Suffix(long j10) {
            super(null);
            this.lastCount = j10;
        }

        public static /* synthetic */ Suffix copy$default(Suffix suffix, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = suffix.lastCount;
            }
            return suffix.copy(j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getLastCount() {
            return this.lastCount;
        }

        public final Suffix copy(long lastCount) {
            return new Suffix(lastCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Suffix) && this.lastCount == ((Suffix) other).lastCount;
        }

        public final long getLastCount() {
            return this.lastCount;
        }

        public int hashCode() {
            long j10 = this.lastCount;
            return (int) (j10 ^ (j10 >>> 32));
        }

        public String toString() {
            return "-" + this.lastCount;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/ContentRange$TailFrom;", "Lio/ktor/http/ContentRange;", "from", "", "(J)V", "getFrom", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class TailFrom extends ContentRange {
        private final long from;

        public TailFrom(long j10) {
            super(null);
            this.from = j10;
        }

        public static /* synthetic */ TailFrom copy$default(TailFrom tailFrom, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = tailFrom.from;
            }
            return tailFrom.copy(j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrom() {
            return this.from;
        }

        public final TailFrom copy(long from) {
            return new TailFrom(from);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TailFrom) && this.from == ((TailFrom) other).from;
        }

        public final long getFrom() {
            return this.from;
        }

        public int hashCode() {
            long j10 = this.from;
            return (int) (j10 ^ (j10 >>> 32));
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.from);
            sb2.append('-');
            return sb2.toString();
        }
    }

    public /* synthetic */ ContentRange(h hVar) {
        this();
    }

    private ContentRange() {
    }
}
