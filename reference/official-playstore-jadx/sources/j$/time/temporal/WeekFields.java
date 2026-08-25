package j$.time.temporal;

import j$.time.DayOfWeek;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class WeekFields implements Serializable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ConcurrentHashMap f18339g = new ConcurrentHashMap(4, 0.75f, 2);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final h f18340h;
    private static final long serialVersionUID = -1177360819670808121L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DayOfWeek f18341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient t f18343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient t f18344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final transient t f18345e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient t f18346f;

    static {
        new WeekFields(DayOfWeek.MONDAY, 4);
        a(DayOfWeek.SUNDAY, 1);
        f18340h = i.f18358d;
    }

    public static WeekFields of(Locale locale) {
        Objects.requireNonNull(locale, "locale");
        Calendar calendar = Calendar.getInstance(new Locale(locale.getLanguage(), locale.getCountry()));
        return a(DayOfWeek.f18093a[((((int) (((long) (calendar.getFirstDayOfWeek() - 1)) % 7)) + 7) + DayOfWeek.SUNDAY.ordinal()) % 7], calendar.getMinimalDaysInFirstWeek());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static WeekFields a(DayOfWeek dayOfWeek, int i10) {
        String str = dayOfWeek.toString() + i10;
        ConcurrentHashMap concurrentHashMap = f18339g;
        WeekFields weekFields = (WeekFields) concurrentHashMap.get(str);
        if (weekFields != null) {
            return weekFields;
        }
        concurrentHashMap.putIfAbsent(str, new WeekFields(dayOfWeek, i10));
        return (WeekFields) concurrentHashMap.get(str);
    }

    public WeekFields(DayOfWeek dayOfWeek, int i10) {
        ChronoUnit chronoUnit = ChronoUnit.DAYS;
        ChronoUnit chronoUnit2 = ChronoUnit.WEEKS;
        this.f18343c = new t("DayOfWeek", this, chronoUnit, chronoUnit2, t.f18377f);
        this.f18344d = new t("WeekOfMonth", this, chronoUnit2, ChronoUnit.MONTHS, t.f18378g);
        h hVar = i.f18358d;
        this.f18345e = new t("WeekOfWeekBasedYear", this, chronoUnit2, hVar, t.f18380i);
        this.f18346f = new t("WeekBasedYear", this, hVar, ChronoUnit.FOREVER, a.YEAR.f18349b);
        Objects.requireNonNull(dayOfWeek, "firstDayOfWeek");
        if (i10 < 1 || i10 > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.f18341a = dayOfWeek;
        this.f18342b = i10;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        if (this.f18341a == null) {
            throw new InvalidObjectException("firstDayOfWeek is null");
        }
        int i10 = this.f18342b;
        if (i10 < 1 || i10 > 7) {
            throw new InvalidObjectException("Minimal number of days is invalid");
        }
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return a(this.f18341a, this.f18342b);
        } catch (IllegalArgumentException e5) {
            throw new InvalidObjectException("Invalid serialized WeekFields: " + e5.getMessage());
        }
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.f18341a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WeekFields) && hashCode() == obj.hashCode();
    }

    public final int hashCode() {
        return (this.f18341a.ordinal() * 7) + this.f18342b;
    }

    public final String toString() {
        return "WeekFields[" + this.f18341a + "," + this.f18342b + "]";
    }
}
