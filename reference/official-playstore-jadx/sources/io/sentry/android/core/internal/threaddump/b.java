package io.sentry.android.core.internal.threaddump;

import io.sentry.protocol.d0;
import io.sentry.u;
import io.sentry.x5;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import k2.c;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Pattern f16557f = Pattern.compile("\"(.*)\" (.*) ?prio=(\\d+)\\s+tid=(\\d+)\\s*(.*)");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Pattern f16558g = Pattern.compile("\"(.*)\" (.*) ?sysTid=(\\d+)");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Pattern f16559h = Pattern.compile(" *(?:native: )?#(\\d+) \\S+ ([0-9a-fA-F]+)\\s+((.*?)(?:\\s+\\(deleted\\))?(?:\\s+\\(offset (.*?)\\))?)(?:\\s+\\((?:\\?\\?\\?|(.*?)(?:\\+(\\d+))?)\\))?(?:\\s+\\(BuildId: (.*?)\\))?");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Pattern f16560i = Pattern.compile(" *at (?:(.+)\\.)?([^.]+)\\.([^.]+)\\((.*):([\\d-]+)\\)");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f16561j = Pattern.compile(" *at (?:(.+)\\.)?([^.]+)\\.([^.]+)\\(Native method\\)");
    public static final Pattern k = Pattern.compile(" *- locked \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Pattern f16562l = Pattern.compile(" *- sleeping on \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f16563m = Pattern.compile(" *- waiting on \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f16564n = Pattern.compile(" *- waiting to lock \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Pattern f16565o = Pattern.compile(" *- waiting to lock \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)(?: held by thread (\\d+))");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f16566p = Pattern.compile(" *- waiting to lock an unknown object");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Pattern f16567q = Pattern.compile("\\s+");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f16568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f16569b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u f16570c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f16571d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f16572e = new ArrayList();

    public b(y6 y6Var, boolean z) {
        this.f16568a = y6Var;
        this.f16569b = z;
        this.f16570c = new u(y6Var, 2);
    }

    public static void a(d0 d0Var, x5 x5Var) {
        Map map = d0Var.f17565t;
        if (map == null) {
            map = new HashMap();
        }
        x5 x5Var2 = (x5) map.get(x5Var.f18055l);
        if (x5Var2 != null) {
            x5Var2.f18054i = Math.max(x5Var2.f18054i, x5Var.f18054i);
        } else {
            String str = x5Var.f18055l;
            x5 x5Var3 = new x5();
            x5Var3.f18054i = x5Var.f18054i;
            x5Var3.f18055l = str;
            x5Var3.f18056m = x5Var.f18056m;
            x5Var3.f18057n = x5Var.f18057n;
            x5Var3.f18058o = x5Var.f18058o;
            x5Var3.f18059p = c.w(x5Var.f18059p);
            map.put(str, x5Var3);
        }
        d0Var.f17565t = map;
    }

    public static Long b(Matcher matcher, int i10) {
        String strGroup = matcher.group(i10);
        if (strGroup == null || strGroup.length() == 0) {
            return null;
        }
        return Long.valueOf(Long.parseLong(strGroup));
    }

    public static boolean c(Matcher matcher, String str) {
        matcher.reset(str);
        return matcher.matches();
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0184, code lost:
    
        r30 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0184 A[EDGE_INSN: B:137:0x0184->B:53:0x0184 BREAK  A[LOOP:1: B:48:0x016e->B:125:0x03d8], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(androidx.leanback.widget.r1 r32) {
        /*
            Method dump skipped, instruction units count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.threaddump.b.d(androidx.leanback.widget.r1):void");
    }
}
