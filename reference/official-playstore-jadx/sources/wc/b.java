package wc;

import com.google.android.gms.cast.MediaError;

/* JADX INFO: loaded from: classes5.dex */
public enum b {
    /* JADX INFO: Fake field, exist only in values array */
    ERROR(MediaError.ERROR_TYPE_ERROR),
    /* JADX INFO: Fake field, exist only in values array */
    WARN("WARN"),
    INFO("INFO"),
    /* JADX INFO: Fake field, exist only in values array */
    DEBUG("DEBUG"),
    /* JADX INFO: Fake field, exist only in values array */
    TRACE("TRACE");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f22546i;

    b(String str) {
        this.f22546i = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f22546i;
    }
}
