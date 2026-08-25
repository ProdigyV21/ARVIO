package m2;

import android.app.Notification;

/* JADX INFO: loaded from: classes3.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20208b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Notification f20209c;

    public j(int i10, int i11, Notification notification) {
        this.f20207a = i10;
        this.f20209c = notification;
        this.f20208b = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || j.class != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.f20207a == jVar.f20207a && this.f20208b == jVar.f20208b) {
            return this.f20209c.equals(jVar.f20209c);
        }
        return false;
    }

    public final int hashCode() {
        return this.f20209c.hashCode() + (((this.f20207a * 31) + this.f20208b) * 31);
    }

    public final String toString() {
        return "ForegroundInfo{mNotificationId=" + this.f20207a + ", mForegroundServiceType=" + this.f20208b + ", mNotification=" + this.f20209c + '}';
    }
}
