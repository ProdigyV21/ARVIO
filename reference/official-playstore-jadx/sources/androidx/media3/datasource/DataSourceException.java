package androidx.media3.datasource;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class DataSourceException extends IOException {

    @Deprecated
    public static final int POSITION_OUT_OF_RANGE = 2008;
    public final int reason;

    public DataSourceException(int i10) {
        this.reason = i10;
    }

    public static boolean isCausedByPositionOutOfRange(IOException iOException) {
        for (Throwable cause = iOException; cause != null; cause = cause.getCause()) {
            if ((cause instanceof DataSourceException) && ((DataSourceException) cause).reason == 2008) {
                return true;
            }
        }
        return false;
    }

    public DataSourceException(Throwable th, int i10) {
        super(th);
        this.reason = i10;
    }

    public DataSourceException(String str, int i10) {
        super(str);
        this.reason = i10;
    }

    public DataSourceException(String str, Throwable th, int i10) {
        super(str, th);
        this.reason = i10;
    }
}
