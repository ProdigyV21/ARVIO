package j$.time.zone;

import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements Comparable, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f18389e = 0;
    private static final long serialVersionUID = -6946044323557704546L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18390a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LocalDateTime f18391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ZoneOffset f18392c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ZoneOffset f18393d;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f18390a, ((b) obj).f18390a);
    }

    public b(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        localDateTime.getClass();
        this.f18390a = j$.com.android.tools.r8.a.w(localDateTime, zoneOffset);
        this.f18391b = localDateTime;
        this.f18392c = zoneOffset;
        this.f18393d = zoneOffset2;
    }

    public b(long j10, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
        this.f18390a = j10;
        this.f18391b = LocalDateTime.Y(j10, 0, zoneOffset);
        this.f18392c = zoneOffset;
        this.f18393d = zoneOffset2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 2, this);
    }

    public final boolean l() {
        return this.f18393d.f18126b > this.f18392c.f18126b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this.f18390a == bVar.f18390a && this.f18392c.equals(bVar.f18392c) && this.f18393d.equals(bVar.f18393d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f18391b.hashCode() ^ this.f18392c.f18126b) ^ Integer.rotateLeft(this.f18393d.f18126b, 16);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Transition[");
        sb2.append(l() ? "Gap" : "Overlap");
        sb2.append(" at ");
        sb2.append(this.f18391b);
        sb2.append(this.f18392c);
        sb2.append(" to ");
        sb2.append(this.f18393d);
        sb2.append(']');
        return sb2.toString();
    }
}
