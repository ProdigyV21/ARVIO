package fi.iki.elonen;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class NanoHTTPD$ResponseException extends Exception {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f15401i;

    public NanoHTTPD$ResponseException(m mVar, String str) {
        super(str);
        this.f15401i = mVar;
    }

    public final m a() {
        return this.f15401i;
    }

    public NanoHTTPD$ResponseException(String str, IOException iOException) {
        super(str, iOException);
        this.f15401i = m.INTERNAL_ERROR;
    }
}
