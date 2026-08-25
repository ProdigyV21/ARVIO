package androidx.datastore.preferences.protobuf;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public enum z3 {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(Boolean.FALSE),
    STRING(""),
    BYTE_STRING(m.f2470m),
    ENUM(null),
    MESSAGE(null);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f2584i;

    z3(Serializable serializable) {
        this.f2584i = serializable;
    }
}
