package androidx.media3.common.util;

import android.text.TextUtils;
import androidx.fragment.app.a2;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.UnknownHostException;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: loaded from: classes3.dex */
public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;
    private static final Object lock = new Object();
    private static Logger logger = Logger.DEFAULT;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevel {
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: androidx.media3.common.util.Log.Logger.1
            @Override // androidx.media3.common.util.Log.Logger
            public void d(String str, String str2, Throwable th) {
                android.util.Log.d(str, Log.appendThrowableString(str2, th));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void e(String str, String str2, Throwable th) {
                android.util.Log.e(str, Log.appendThrowableString(str2, th));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void i(String str, String str2, Throwable th) {
                android.util.Log.i(str, Log.appendThrowableString(str2, th));
            }

            @Override // androidx.media3.common.util.Log.Logger
            public void w(String str, String str2, Throwable th) {
                android.util.Log.w(str, Log.appendThrowableString(str2, th));
            }
        };

        void d(String str, String str2, Throwable th);

        void e(String str, String str2, Throwable th);

        void i(String str, String str2, Throwable th);

        void w(String str, String str2, Throwable th);
    }

    private Log() {
    }

    @Pure
    public static String appendThrowableString(String str, Throwable th) {
        String throwableString = getThrowableString(th);
        if (TextUtils.isEmpty(throwableString)) {
            return str;
        }
        StringBuilder sbP = a2.p(str, "\n  ");
        sbP.append(throwableString.replace("\n", "\n  "));
        sbP.append('\n');
        return sbP.toString();
    }

    @Pure
    public static void d(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel == 0) {
                    logger.d(str, str2, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static void e(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 3) {
                    logger.e(str, str2, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static int getLogLevel() {
        int i10;
        synchronized (lock) {
            i10 = logLevel;
        }
        return i10;
    }

    @Pure
    public static String getThrowableString(Throwable th) {
        if (th == null) {
            return null;
        }
        synchronized (lock) {
            try {
                if (isCausedByUnknownHostException(th)) {
                    return "UnknownHostException (no network)";
                }
                if (logStackTraces) {
                    return android.util.Log.getStackTraceString(th).trim().replace("\t", "    ");
                }
                return th.getMessage();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Pure
    public static void i(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 1) {
                    logger.i(str, str2, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    private static boolean isCausedByUnknownHostException(Throwable th) {
        while (th != null) {
            if (th instanceof UnknownHostException) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static void setLogLevel(int i10) {
        synchronized (lock) {
            logLevel = i10;
        }
    }

    public static void setLogStackTraces(boolean z) {
        synchronized (lock) {
            logStackTraces = z;
        }
    }

    public static void setLogger(Logger logger2) {
        synchronized (lock) {
            logger = logger2;
        }
    }

    @Pure
    public static void w(String str, String str2) {
        synchronized (lock) {
            try {
                if (logLevel <= 2) {
                    logger.w(str, str2, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Pure
    public static void d(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel == 0) {
                    logger.d(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Pure
    public static void e(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel <= 3) {
                    logger.e(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Pure
    public static void i(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel <= 1) {
                    logger.i(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Pure
    public static void w(String str, String str2, Throwable th) {
        synchronized (lock) {
            try {
                if (logLevel <= 2) {
                    logger.w(str, str2, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
