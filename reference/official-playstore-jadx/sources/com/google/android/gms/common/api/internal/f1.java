package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.cast.y3;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public final class f1 extends ThreadLocal {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12732a;

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.f12732a) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return new y3();
            case 2:
                return new LinkedList();
            case 3:
                return 0L;
            case 4:
                return new DecimalFormat("#.################", DecimalFormatSymbols.getInstance(Locale.ROOT));
            case 5:
                return new io.sentry.util.p();
            case 6:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(ib.c.f16273f);
                return simpleDateFormat;
            default:
                return new Random();
        }
    }
}
