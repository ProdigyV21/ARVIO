package androidx.media3.exoplayer.analytics;

import android.media.metrics.LogSessionId;
import android.os.Build;
import io.ktor.http.LinkHeader;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayerId {
    private final LogSessionIdApi31 logSessionIdApi31;
    public final String name;
    public static final PlayerId UNSET = new PlayerId("");
    public static final PlayerId PRELOAD = new PlayerId(LinkHeader.Rel.PreLoad);

    public static final class LogSessionIdApi31 {
        public LogSessionId logSessionId = LogSessionId.LOG_SESSION_ID_NONE;

        public void setLogSessionId(LogSessionId logSessionId) {
            ac.b.s(this.logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE));
            this.logSessionId = logSessionId;
        }
    }

    public PlayerId(String str) {
        this.name = str;
        this.logSessionIdApi31 = Build.VERSION.SDK_INT >= 31 ? new LogSessionIdApi31() : null;
    }

    public synchronized LogSessionId getLogSessionId() {
        LogSessionIdApi31 logSessionIdApi31;
        logSessionIdApi31 = this.logSessionIdApi31;
        logSessionIdApi31.getClass();
        return logSessionIdApi31.logSessionId;
    }

    public synchronized void setLogSessionId(LogSessionId logSessionId) {
        LogSessionIdApi31 logSessionIdApi31 = this.logSessionIdApi31;
        logSessionIdApi31.getClass();
        logSessionIdApi31.setLogSessionId(logSessionId);
    }
}
