package j$.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class a extends Clock implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f18131b;
    private static final long serialVersionUID = 6740630888130243051L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ZoneId f18132a;

    public a(ZoneId zoneId) {
        this.f18132a = zoneId;
    }

    static {
        System.currentTimeMillis();
        f18131b = new a(ZoneOffset.UTC);
    }

    @Override // j$.time.Clock
    public final long a() {
        return System.currentTimeMillis();
    }

    @Override // j$.time.Clock
    public final Instant instant() {
        return Instant.ofEpochMilli(System.currentTimeMillis());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return this.f18132a.equals(((a) obj).f18132a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f18132a.hashCode() + 1;
    }

    public final String toString() {
        return "SystemClock[" + this.f18132a + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
    }
}
