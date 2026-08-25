package j$.time;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Clock {
    public abstract Instant instant();

    public static Clock systemUTC() {
        return a.f18131b;
    }

    public static a b() {
        return new a(ZoneId.systemDefault());
    }

    public long a() {
        return instant().toEpochMilli();
    }
}
