package com.arflix.tv.util;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.h0;
import kotlin.text.i;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001:\u00017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\u0007J\u0017\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0012\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0010J)\u0010\u0015\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0017\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0017\u0010\u0016J'\u0010\u0019\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u0010J\u001d\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001e¢\u0006\u0004\b\u001d\u0010\u001fJ\u001d\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020 ¢\u0006\u0004\b\u001d\u0010!J\u0017\u0010#\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b#\u0010$J+\u0010'\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0014\b\u0002\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040%¢\u0006\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u001e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00101\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010/R\u0014\u00102\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010/R\u0014\u00103\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010/R\u0014\u00104\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010/R\u0014\u00105\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u0010/R\u0014\u00106\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u0010/¨\u00068"}, d2 = {"Lcom/arflix/tv/util/AppLogger;", "", "<init>", "()V", "", "message", "sanitize", "(Ljava/lang/String;)Ljava/lang/String;", "tag", "safeTag", "Lcom/arflix/tv/util/AppLogger$CrashContextProvider;", "provider", "Lx6/t0;", "init", "(Lcom/arflix/tv/util/AppLogger$CrashContextProvider;)V", "v", "(Ljava/lang/String;Ljava/lang/String;)V", "d", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "", "throwable", "w", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V", "e", "severity", "breadcrumb", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "key", "value", "setCrashContext", "", "(Ljava/lang/String;I)V", "", "(Ljava/lang/String;Z)V", "userId", "setUserId", "(Ljava/lang/String;)V", "", "context", "recordException", "(Ljava/lang/Throwable;Ljava/util/Map;)V", "crashContextProvider", "Lcom/arflix/tv/util/AppLogger$CrashContextProvider;", "MAX_DIAGNOSTIC_LENGTH", "I", "Lkotlin/text/m;", "EMAIL_PATTERN", "Lkotlin/text/m;", "URL_PATTERN", "MAGNET_PATTERN", "JWT_PATTERN", "IPV4_PATTERN", "TOKEN_PATTERN", "LONG_HEX_PATTERN", "SAFE_TAG_PATTERN", "CrashContextProvider", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppLogger {
    private static final int MAX_DIAGNOSTIC_LENGTH = 500;
    private static CrashContextProvider crashContextProvider;
    public static final AppLogger INSTANCE = new AppLogger();
    private static final m EMAIL_PATTERN = new m("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    private static final m URL_PATTERN = new m("\\b(?:https?|wss?|ftp|file|content)://[^\\s\"'<>]+", 0);
    private static final m MAGNET_PATTERN = new m("\\bmagnet:\\?[^\\s\"'<>]+", 0);
    private static final m JWT_PATTERN = new m("\\b[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\.[A-Za-z0-9_-]{16,}\\b");
    private static final m IPV4_PATTERN = new m("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    private static final m TOKEN_PATTERN = new m("(token|jwt|bearer|api[_-]?key|secret)[\"':\\s=]+([a-zA-Z0-9._-]{20,})", 0);
    private static final m LONG_HEX_PATTERN = new m("[a-fA-F0-9]{32,}");
    private static final m SAFE_TAG_PATTERN = new m("[^A-Za-z0-9_.-]");
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\bH&¢\u0006\u0004\b\u0006\u0010\tJ\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\nH&¢\u0006\u0004\b\u0006\u0010\u000bJ\u0017\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0002H&¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000fH&¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\u0014\u0010\u000e¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/util/AppLogger$CrashContextProvider;", "", "", "key", "value", "Lx6/t0;", "setCustomKey", "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;I)V", "", "(Ljava/lang/String;Z)V", "message", "log", "(Ljava/lang/String;)V", "", "throwable", "recordException", "(Ljava/lang/Throwable;)V", "userId", "setUserId", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface CrashContextProvider {
        void log(String message);

        void recordException(Throwable throwable);

        void setCustomKey(String key, int value);

        void setCustomKey(String key, String value);

        void setCustomKey(String key, boolean value);

        void setUserId(String userId);
    }

    private AppLogger() {
    }

    public static /* synthetic */ void breadcrumb$default(AppLogger appLogger, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str3 = "info";
        }
        appLogger.breadcrumb(str, str2, str3);
    }

    public static /* synthetic */ void e$default(AppLogger appLogger, String str, String str2, Throwable th, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            th = null;
        }
        appLogger.e(str, str2, th);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void recordException$default(AppLogger appLogger, Throwable th, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = a0.f19683i;
        }
        appLogger.recordException(th, map);
    }

    private final String safeTag(String tag) {
        String strI0 = o.I0(40, SAFE_TAG_PATTERN.f("_", tag));
        return o.h0(strI0) ? "app" : strI0;
    }

    private final String sanitize(String message) {
        String string;
        String string2;
        String string3;
        String strF = IPV4_PATTERN.f("[IP]", JWT_PATTERN.f("[TOKEN]", MAGNET_PATTERN.f("[MAGNET]", URL_PATTERN.f("[URL]", message))));
        m mVar = EMAIL_PATTERN;
        mVar.getClass();
        k kVarB = m.b(mVar, strF);
        int i10 = 0;
        if (kVarB == null) {
            string = strF.toString();
        } else {
            int length = strF.length();
            StringBuilder sb2 = new StringBuilder(length);
            int i11 = 0;
            do {
                sb2.append((CharSequence) strF, i11, kVarB.d().f22619i);
                sb2.append(sanitize$lambda$0(kVarB));
                i11 = kVarB.d().f22620l + 1;
                kVarB = kVarB.next();
                if (i11 >= length) {
                    break;
                }
            } while (kVarB != null);
            if (i11 < length) {
                sb2.append((CharSequence) strF, i11, length);
            }
            string = sb2.toString();
        }
        m mVar2 = TOKEN_PATTERN;
        mVar2.getClass();
        k kVarB2 = m.b(mVar2, string);
        if (kVarB2 == null) {
            string2 = string.toString();
        } else {
            int length2 = string.length();
            StringBuilder sb3 = new StringBuilder(length2);
            int i12 = 0;
            do {
                sb3.append((CharSequence) string, i12, kVarB2.d().f22619i);
                sb3.append(sanitize$lambda$1(kVarB2));
                i12 = kVarB2.d().f22620l + 1;
                kVarB2 = kVarB2.next();
                if (i12 >= length2) {
                    break;
                }
            } while (kVarB2 != null);
            if (i12 < length2) {
                sb3.append((CharSequence) string, i12, length2);
            }
            string2 = sb3.toString();
        }
        m mVar3 = LONG_HEX_PATTERN;
        mVar3.getClass();
        k kVarB3 = m.b(mVar3, string2);
        if (kVarB3 == null) {
            string3 = string2.toString();
        } else {
            int length3 = string2.length();
            StringBuilder sb4 = new StringBuilder(length3);
            do {
                sb4.append((CharSequence) string2, i10, kVarB3.d().f22619i);
                sb4.append(sanitize$lambda$2(kVarB3));
                i10 = kVarB3.d().f22620l + 1;
                kVarB3 = kVarB3.next();
                if (i10 >= length3) {
                    break;
                }
            } while (kVarB3 != null);
            if (i10 < length3) {
                sb4.append((CharSequence) string2, i10, length3);
            }
            string3 = sb4.toString();
        }
        return o.I0(500, string3);
    }

    private static final CharSequence sanitize$lambda$0(i iVar) {
        String value = iVar.getValue();
        int iE0 = o.e0(value, '@', 0, false, 6);
        int iK0 = o.k0(value, '.', 0, 6);
        if (iE0 <= 0 || iK0 <= iE0) {
            return "[EMAIL]";
        }
        return value.charAt(0) + "***@***." + value.substring(iK0 + 1);
    }

    private static final CharSequence sanitize$lambda$1(i iVar) {
        String str = (String) iVar.b().get(1);
        String str2 = (String) iVar.b().get(2);
        return c.t(str, ":", str2.length() > 4 ? o.I0(4, str2).concat("***") : "***");
    }

    private static final CharSequence sanitize$lambda$2(i iVar) {
        String value = iVar.getValue();
        return value.length() > 8 ? o.I0(8, value).concat("...") : value;
    }

    public static /* synthetic */ void w$default(AppLogger appLogger, String str, String str2, Throwable th, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            th = null;
        }
        appLogger.w(str, str2, th);
    }

    public final void breadcrumb(String tag, String message, String severity) {
        String strI0 = o.I0(500, d.o(safeTag(tag), "[", severity, "]: ", sanitize(message)));
        CrashContextProvider crashContextProvider2 = crashContextProvider;
        if (crashContextProvider2 != null) {
            crashContextProvider2.log(strI0);
        }
    }

    public final void d(String tag, String message) {
    }

    public final void e(String tag, String message, Throwable throwable) {
        breadcrumb(tag, message, "error");
        if (throwable != null) {
            AppLogger appLogger = INSTANCE;
            appLogger.recordException(throwable, h0.t0(new x("error_area", appLogger.safeTag(tag)), new x("error_severity", "error")));
        }
    }

    public final void i(String tag, String message) {
    }

    public final void init(CrashContextProvider provider) {
        crashContextProvider = provider;
    }

    public final void recordException(Throwable throwable, Map<String, String> context) {
        String strDropReasonForHandledException = CrashReportFilter.INSTANCE.dropReasonForHandledException(throwable);
        if (strDropReasonForHandledException != null) {
            breadcrumb("exception", d.n("filtered ", throwable.getClass().getSimpleName(), " reason=", strDropReasonForHandledException), "info");
            return;
        }
        breadcrumb("exception", throwable.getClass().getSimpleName(), "error");
        for (Map.Entry<String, String> entry : context.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            CrashContextProvider crashContextProvider2 = crashContextProvider;
            if (crashContextProvider2 != null) {
                AppLogger appLogger = INSTANCE;
                crashContextProvider2.setCustomKey(appLogger.safeTag(key), appLogger.sanitize(value));
            }
        }
        if (!CrashReportFilter.INSTANCE.shouldSampleHandledException(throwable, context)) {
            breadcrumb("exception", "sampled ".concat(throwable.getClass().getSimpleName()), "info");
            return;
        }
        CrashContextProvider crashContextProvider3 = crashContextProvider;
        if (crashContextProvider3 != null) {
            crashContextProvider3.recordException(throwable);
        }
    }

    public final void setCrashContext(String key, String value) {
        CrashContextProvider crashContextProvider2 = crashContextProvider;
        if (crashContextProvider2 != null) {
            crashContextProvider2.setCustomKey(key, sanitize(value));
        }
    }

    public final void setUserId(String userId) {
        CrashContextProvider crashContextProvider2 = crashContextProvider;
        if (crashContextProvider2 != null) {
            crashContextProvider2.setUserId(userId != null ? AppLoggerKt.hash(userId) : null);
        }
    }

    public final void v(String tag, String message) {
    }

    public final void w(String tag, String message, Throwable throwable) {
        breadcrumb(tag, message, "warning");
        if (throwable != null) {
            INSTANCE.breadcrumb(tag, throwable.getClass().getSimpleName(), "warning_type");
        }
    }

    public final void setCrashContext(String key, int value) {
        CrashContextProvider crashContextProvider2 = crashContextProvider;
        if (crashContextProvider2 != null) {
            crashContextProvider2.setCustomKey(key, value);
        }
    }

    public final void setCrashContext(String key, boolean value) {
        CrashContextProvider crashContextProvider2 = crashContextProvider;
        if (crashContextProvider2 != null) {
            crashContextProvider2.setCustomKey(key, value);
        }
    }
}
