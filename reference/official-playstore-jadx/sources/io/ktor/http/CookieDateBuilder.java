package io.ktor.http;

import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import io.ktor.util.date.Month;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u001dR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR\u001e\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000e\u0010\u0006\"\u0004\b\u000f\u0010\bR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\b¨\u0006\u001e"}, d2 = {"Lio/ktor/http/CookieDateBuilder;", "", "()V", "dayOfMonth", "", "getDayOfMonth", "()Ljava/lang/Integer;", "setDayOfMonth", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hours", "getHours", "setHours", "minutes", "getMinutes", "setMinutes", "month", "Lio/ktor/util/date/Month;", "getMonth", "()Lio/ktor/util/date/Month;", "setMonth", "(Lio/ktor/util/date/Month;)V", "seconds", "getSeconds", "setSeconds", "year", "getYear", "setYear", "build", "Lio/ktor/util/date/GMTDate;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CookieDateBuilder {
    private Integer dayOfMonth;
    private Integer hours;
    private Integer minutes;
    private Month month;
    private Integer seconds;
    private Integer year;

    public final GMTDate build() {
        return DateJvmKt.GMTDate(this.seconds.intValue(), this.minutes.intValue(), this.hours.intValue(), this.dayOfMonth.intValue(), this.month, this.year.intValue());
    }

    public final Integer getDayOfMonth() {
        return this.dayOfMonth;
    }

    public final Integer getHours() {
        return this.hours;
    }

    public final Integer getMinutes() {
        return this.minutes;
    }

    public final Month getMonth() {
        return this.month;
    }

    public final Integer getSeconds() {
        return this.seconds;
    }

    public final Integer getYear() {
        return this.year;
    }

    public final void setDayOfMonth(Integer num) {
        this.dayOfMonth = num;
    }

    public final void setHours(Integer num) {
        this.hours = num;
    }

    public final void setMinutes(Integer num) {
        this.minutes = num;
    }

    public final void setMonth(Month month) {
        this.month = month;
    }

    public final void setSeconds(Integer num) {
        this.seconds = num;
    }

    public final void setYear(Integer num) {
        this.year = num;
    }
}
