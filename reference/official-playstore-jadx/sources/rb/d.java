package rb;

import android.util.Log;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.text.o;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f21450a = new d();

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int iMin;
        CopyOnWriteArraySet copyOnWriteArraySet = c.f21448a;
        String loggerName = logRecord.getLoggerName();
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        int i10 = iIntValue > level.intValue() ? 5 : logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        String message = logRecord.getMessage();
        Throwable thrown = logRecord.getThrown();
        String strI0 = (String) c.f21449b.get(loggerName);
        if (strI0 == null) {
            strI0 = o.I0(23, loggerName);
        }
        if (Log.isLoggable(strI0, i10)) {
            if (thrown != null) {
                message = message + '\n' + Log.getStackTraceString(thrown);
            }
            int length = message.length();
            int i11 = 0;
            while (i11 < length) {
                int iE0 = o.e0(message, '\n', i11, false, 4);
                if (iE0 == -1) {
                    iE0 = length;
                }
                while (true) {
                    iMin = Math.min(iE0, i11 + 4000);
                    Log.println(i10, strI0, message.substring(i11, iMin));
                    if (iMin >= iE0) {
                        break;
                    } else {
                        i11 = iMin;
                    }
                }
                i11 = iMin + 1;
            }
        }
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }
}
