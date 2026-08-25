package p0;

import io.ktor.util.date.GMTDateParser;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f21038a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static char[] f21039b = new char[24];

    public static void a(StringBuilder sb2, Object obj) {
        int iLastIndexOf;
        if (obj == null) {
            sb2.append("null");
            return;
        }
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName.length() <= 0 && (iLastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(iLastIndexOf + 1);
        }
        sb2.append(simpleName);
        sb2.append('{');
        sb2.append(Integer.toHexString(System.identityHashCode(obj)));
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void c(long j10, PrintWriter printWriter) {
        synchronized (f21038a) {
            printWriter.print(new String(f21039b, 0, d(j10)));
        }
    }

    public static int d(long j10) {
        char c10;
        int i10;
        int i11;
        int i12;
        if (f21039b.length < 0) {
            f21039b = new char[0];
        }
        char[] cArr = f21039b;
        if (j10 == 0) {
            cArr[0] = '0';
            return 1;
        }
        if (j10 > 0) {
            c10 = '+';
        } else {
            j10 = -j10;
            c10 = '-';
        }
        int i13 = (int) (j10 % 1000);
        int iFloor = (int) Math.floor(j10 / 1000);
        if (iFloor > 86400) {
            i10 = iFloor / 86400;
            iFloor -= 86400 * i10;
        } else {
            i10 = 0;
        }
        if (iFloor > 3600) {
            i11 = iFloor / 3600;
            iFloor -= i11 * 3600;
        } else {
            i11 = 0;
        }
        if (iFloor > 60) {
            int i14 = iFloor / 60;
            iFloor -= i14 * 60;
            i12 = i14;
        } else {
            i12 = 0;
        }
        cArr[0] = c10;
        int iE = e(cArr, i10, GMTDateParser.DAY_OF_MONTH, 1, false, 0);
        int iE2 = e(cArr, i11, GMTDateParser.HOURS, iE, iE != 1, 0);
        int iE3 = e(cArr, i12, GMTDateParser.MINUTES, iE2, iE2 != 1, 0);
        int iE4 = e(cArr, i13, GMTDateParser.MINUTES, e(cArr, iFloor, GMTDateParser.SECONDS, iE3, iE3 != 1, 0), true, 0);
        cArr[iE4] = GMTDateParser.SECONDS;
        return iE4 + 1;
    }

    public static int e(char[] cArr, int i10, char c10, int i11, boolean z, int i12) {
        int i13;
        if (!z && i10 <= 0) {
            return i11;
        }
        if ((!z || i12 < 3) && i10 <= 99) {
            i13 = i11;
        } else {
            int i14 = i10 / 100;
            cArr[i11] = (char) (i14 + 48);
            i13 = i11 + 1;
            i10 -= i14 * 100;
        }
        if ((z && i12 >= 2) || i10 > 9 || i11 != i13) {
            int i15 = i10 / 10;
            cArr[i13] = (char) (i15 + 48);
            i13++;
            i10 -= i15 * 10;
        }
        cArr[i13] = (char) (i10 + 48);
        cArr[i13 + 1] = c10;
        return i13 + 2;
    }
}
