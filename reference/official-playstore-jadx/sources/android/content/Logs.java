package android.content;

import kotlin.Metadata;
import r7.a;

/* JADX INFO: renamed from: coil.util.-Logs, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u001a2\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0080\b¢\u0006\u0004\b\b\u0010\t\u001a#\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\b\u0010\f¨\u0006\r"}, d2 = {"Lcoil/util/Logger;", "", "tag", "", "priority", "Lkotlin/Function0;", "lazyMessage", "Lx6/t0;", "log", "(Lcoil/util/Logger;Ljava/lang/String;ILr7/a;)V", "", "throwable", "(Lcoil/util/Logger;Ljava/lang/String;Ljava/lang/Throwable;)V", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Logs {
    public static final void log(Logger logger, String str, int i10, a<String> aVar) {
        if (logger.getLevel() <= i10) {
            logger.log(str, i10, (String) aVar.invoke(), null);
        }
    }

    public static final void log(Logger logger, String str, Throwable th) {
        if (logger.getLevel() <= 6) {
            logger.log(str, 6, null, th);
        }
    }
}
