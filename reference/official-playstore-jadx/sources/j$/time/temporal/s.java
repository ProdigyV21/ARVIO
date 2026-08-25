package j$.time.temporal;

import j$.time.DateTimeException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class s implements Serializable {
    private static final long serialVersionUID = -7317881728594519368L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18374b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18375c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f18376d;

    public static s f(long j10, long j11) {
        if (j10 > j11) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
        return new s(j10, j10, j11, j11);
    }

    public static s g(long j10, long j11, long j12) {
        if (j10 > 1) {
            throw new IllegalArgumentException("Smallest minimum value must be less than largest minimum value");
        }
        if (j11 > j12) {
            throw new IllegalArgumentException("Smallest maximum value must be less than largest maximum value");
        }
        if (1 > j12) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
        return new s(j10, 1L, j11, j12);
    }

    public s(long j10, long j11, long j12, long j13) {
        this.f18373a = j10;
        this.f18374b = j11;
        this.f18375c = j12;
        this.f18376d = j13;
    }

    public final boolean d() {
        return this.f18373a >= -2147483648L && this.f18376d <= 2147483647L;
    }

    public final boolean e(long j10) {
        return j10 >= this.f18373a && j10 <= this.f18376d;
    }

    public final int a(long j10, o oVar) {
        if (d() && e(j10)) {
            return (int) j10;
        }
        throw new DateTimeException(c(j10, oVar));
    }

    public final void b(long j10, o oVar) {
        if (!e(j10)) {
            throw new DateTimeException(c(j10, oVar));
        }
    }

    public final String c(long j10, o oVar) {
        if (oVar != null) {
            return "Invalid value for " + oVar + " (valid values " + this + "): " + j10;
        }
        return "Invalid value (valid values " + this + "): " + j10;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        long j10 = this.f18373a;
        long j11 = this.f18374b;
        if (j10 > j11) {
            throw new InvalidObjectException("Smallest minimum value must be less than largest minimum value");
        }
        long j12 = this.f18375c;
        long j13 = this.f18376d;
        if (j12 > j13) {
            throw new InvalidObjectException("Smallest maximum value must be less than largest maximum value");
        }
        if (j11 > j13) {
            throw new InvalidObjectException("Minimum value must be less than maximum value");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof s) {
            s sVar = (s) obj;
            if (this.f18373a == sVar.f18373a && this.f18374b == sVar.f18374b && this.f18375c == sVar.f18375c && this.f18376d == sVar.f18376d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f18373a;
        long j11 = this.f18374b;
        long j12 = j10 + (j11 << 16) + (j11 >> 48);
        long j13 = this.f18375c;
        long j14 = j12 + (j13 << 32) + (j13 >> 32);
        long j15 = this.f18376d;
        long j16 = j14 + (j15 << 48) + (j15 >> 16);
        return (int) (j16 ^ (j16 >>> 32));
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f18373a);
        if (this.f18373a != this.f18374b) {
            sb2.append('/');
            sb2.append(this.f18374b);
        }
        sb2.append(" - ");
        sb2.append(this.f18375c);
        if (this.f18375c != this.f18376d) {
            sb2.append('/');
            sb2.append(this.f18376d);
        }
        return sb2.toString();
    }
}
