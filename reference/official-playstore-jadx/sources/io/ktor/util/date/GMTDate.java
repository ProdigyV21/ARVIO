package io.ktor.util.date;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 /2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001/BO\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003Jc\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010\u001e\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u00060"}, d2 = {"Lio/ktor/util/date/GMTDate;", "", "seconds", "", "minutes", "hours", "dayOfWeek", "Lio/ktor/util/date/WeekDay;", "dayOfMonth", "dayOfYear", "month", "Lio/ktor/util/date/Month;", "year", "timestamp", "", "(IIILio/ktor/util/date/WeekDay;IILio/ktor/util/date/Month;IJ)V", "getDayOfMonth", "()I", "getDayOfWeek", "()Lio/ktor/util/date/WeekDay;", "getDayOfYear", "getHours", "getMinutes", "getMonth", "()Lio/ktor/util/date/Month;", "getSeconds", "getTimestamp", "()J", "getYear", "compareTo", "other", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "", "hashCode", "toString", "", "Companion", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class GMTDate implements Comparable<GMTDate> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final GMTDate START = DateJvmKt.GMTDate(0L);
    private final int dayOfMonth;
    private final WeekDay dayOfWeek;
    private final int dayOfYear;
    private final int hours;
    private final int minutes;
    private final Month month;
    private final int seconds;
    private final long timestamp;
    private final int year;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/util/date/GMTDate$Companion;", "", "()V", "START", "Lio/ktor/util/date/GMTDate;", "getSTART", "()Lio/ktor/util/date/GMTDate;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final GMTDate getSTART() {
            return GMTDate.START;
        }

        private Companion() {
        }
    }

    public GMTDate(int i10, int i11, int i12, WeekDay weekDay, int i13, int i14, Month month, int i15, long j10) {
        this.seconds = i10;
        this.minutes = i11;
        this.hours = i12;
        this.dayOfWeek = weekDay;
        this.dayOfMonth = i13;
        this.dayOfYear = i14;
        this.month = month;
        this.year = i15;
        this.timestamp = j10;
    }

    public static /* synthetic */ GMTDate copy$default(GMTDate gMTDate, int i10, int i11, int i12, WeekDay weekDay, int i13, int i14, Month month, int i15, long j10, int i16, Object obj) {
        if ((i16 & 1) != 0) {
            i10 = gMTDate.seconds;
        }
        if ((i16 & 2) != 0) {
            i11 = gMTDate.minutes;
        }
        if ((i16 & 4) != 0) {
            i12 = gMTDate.hours;
        }
        if ((i16 & 8) != 0) {
            weekDay = gMTDate.dayOfWeek;
        }
        if ((i16 & 16) != 0) {
            i13 = gMTDate.dayOfMonth;
        }
        if ((i16 & 32) != 0) {
            i14 = gMTDate.dayOfYear;
        }
        if ((i16 & 64) != 0) {
            month = gMTDate.month;
        }
        if ((i16 & 128) != 0) {
            i15 = gMTDate.year;
        }
        if ((i16 & 256) != 0) {
            j10 = gMTDate.timestamp;
        }
        long j11 = j10;
        Month month2 = month;
        int i17 = i15;
        int i18 = i13;
        int i19 = i14;
        return gMTDate.copy(i10, i11, i12, weekDay, i18, i19, month2, i17, j11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeconds() {
        return this.seconds;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMinutes() {
        return this.minutes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getHours() {
        return this.hours;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final WeekDay getDayOfWeek() {
        return this.dayOfWeek;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getDayOfMonth() {
        return this.dayOfMonth;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getDayOfYear() {
        return this.dayOfYear;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Month getMonth() {
        return this.month;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final GMTDate copy(int seconds, int minutes, int hours, WeekDay dayOfWeek, int dayOfMonth, int dayOfYear, Month month, int year, long timestamp) {
        return new GMTDate(seconds, minutes, hours, dayOfWeek, dayOfMonth, dayOfYear, month, year, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GMTDate)) {
            return false;
        }
        GMTDate gMTDate = (GMTDate) other;
        return this.seconds == gMTDate.seconds && this.minutes == gMTDate.minutes && this.hours == gMTDate.hours && this.dayOfWeek == gMTDate.dayOfWeek && this.dayOfMonth == gMTDate.dayOfMonth && this.dayOfYear == gMTDate.dayOfYear && this.month == gMTDate.month && this.year == gMTDate.year && this.timestamp == gMTDate.timestamp;
    }

    public final int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public final WeekDay getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final int getDayOfYear() {
        return this.dayOfYear;
    }

    public final int getHours() {
        return this.hours;
    }

    public final int getMinutes() {
        return this.minutes;
    }

    public final Month getMonth() {
        return this.month;
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getYear() {
        return this.year;
    }

    public int hashCode() {
        int iHashCode = (((this.month.hashCode() + ((((((this.dayOfWeek.hashCode() + (((((this.seconds * 31) + this.minutes) * 31) + this.hours) * 31)) * 31) + this.dayOfMonth) * 31) + this.dayOfYear) * 31)) * 31) + this.year) * 31;
        long j10 = this.timestamp;
        return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return "GMTDate(seconds=" + this.seconds + ", minutes=" + this.minutes + ", hours=" + this.hours + ", dayOfWeek=" + this.dayOfWeek + ", dayOfMonth=" + this.dayOfMonth + ", dayOfYear=" + this.dayOfYear + ", month=" + this.month + ", year=" + this.year + ", timestamp=" + this.timestamp + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(GMTDate other) {
        return p.d(this.timestamp, other.timestamp);
    }
}
