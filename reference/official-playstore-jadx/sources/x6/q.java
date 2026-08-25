package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements s, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f22596i;

    public q(Object obj) {
        this.f22596i = obj;
    }

    @Override // x6.s
    public final Object getValue() {
        return this.f22596i;
    }

    @Override // x6.s
    public final boolean isInitialized() {
        return true;
    }

    public final String toString() {
        return String.valueOf(this.f22596i);
    }
}
