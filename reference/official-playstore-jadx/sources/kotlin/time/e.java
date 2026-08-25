package kotlin.time;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public enum e {
    NANOSECONDS(TimeUnit.NANOSECONDS),
    /* JADX INFO: Fake field, exist only in values array */
    MICROSECONDS(TimeUnit.MICROSECONDS),
    MILLISECONDS(TimeUnit.MILLISECONDS),
    SECONDS(TimeUnit.SECONDS),
    MINUTES(TimeUnit.MINUTES),
    HOURS(TimeUnit.HOURS),
    DAYS(TimeUnit.DAYS);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TimeUnit f19969i;

    e(TimeUnit timeUnit) {
        this.f19969i = timeUnit;
    }
}
