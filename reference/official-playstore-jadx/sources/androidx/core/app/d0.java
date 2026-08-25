package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import com.arvio.tv.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class d0 {
    public final boolean A;
    public final Notification B;
    public final ArrayList C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1962a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f1966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1967f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PendingIntent f1968g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public IconCompat f1969h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1970i;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public y0 f1972l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f1973m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1974n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1975o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1976p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f1977q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1979s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1980t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Bundle f1981u;
    public String x;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f1963b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1964c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f1965d = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1971j = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1978r = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1982v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1983w = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1984y = 0;
    public int z = 0;

    public d0(Context context, String str) {
        Notification notification = new Notification();
        this.B = notification;
        this.f1962a = context;
        this.x = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.f1970i = 0;
        this.C = new ArrayList();
        this.A = true;
    }

    public static CharSequence b(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public final Notification a() {
        Notification notificationBuild;
        Bundle bundle;
        RemoteViews remoteViewsMakeHeadsUpContentView;
        RemoteViews remoteViewsMakeBigContentView;
        h1 h1Var = new h1(this);
        d0 d0Var = h1Var.f1995c;
        y0 y0Var = d0Var.f1972l;
        if (y0Var != null) {
            y0Var.apply(h1Var);
        }
        RemoteViews remoteViewsMakeContentView = y0Var != null ? y0Var.makeContentView(h1Var) : null;
        int i10 = Build.VERSION.SDK_INT;
        Notification.Builder builder = h1Var.f1994b;
        if (i10 < 26 && i10 < 24) {
            builder.setExtras(h1Var.f1996d);
            notificationBuild = builder.build();
        } else {
            notificationBuild = builder.build();
        }
        if (remoteViewsMakeContentView != null) {
            notificationBuild.contentView = remoteViewsMakeContentView;
        }
        if (y0Var != null && (remoteViewsMakeBigContentView = y0Var.makeBigContentView(h1Var)) != null) {
            notificationBuild.bigContentView = remoteViewsMakeBigContentView;
        }
        if (y0Var != null && (remoteViewsMakeHeadsUpContentView = d0Var.f1972l.makeHeadsUpContentView(h1Var)) != null) {
            notificationBuild.headsUpContentView = remoteViewsMakeHeadsUpContentView;
        }
        if (y0Var != null && (bundle = notificationBuild.extras) != null) {
            y0Var.addCompatExtras(bundle);
        }
        return notificationBuild;
    }

    public final void c(int i10, boolean z) {
        Notification notification = this.B;
        if (z) {
            notification.flags = i10 | notification.flags;
        } else {
            notification.flags = (~i10) & notification.flags;
        }
    }

    public final void d(Bitmap bitmap) {
        IconCompat iconCompat;
        if (bitmap == null) {
            iconCompat = null;
        } else {
            if (Build.VERSION.SDK_INT < 27) {
                Resources resources = this.f1962a.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
                int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
                if (bitmap.getWidth() > dimensionPixelSize || bitmap.getHeight() > dimensionPixelSize2) {
                    double dMin = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, bitmap.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, bitmap.getHeight())));
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) Math.ceil(((double) bitmap.getWidth()) * dMin), (int) Math.ceil(((double) bitmap.getHeight()) * dMin), true);
                }
            }
            PorterDuff.Mode mode = IconCompat.k;
            bitmap.getClass();
            IconCompat iconCompat2 = new IconCompat(1);
            iconCompat2.f2091b = bitmap;
            iconCompat = iconCompat2;
        }
        this.f1969h = iconCompat;
    }

    public final void e(y0 y0Var) {
        if (this.f1972l != y0Var) {
            this.f1972l = y0Var;
            if (y0Var != null) {
                y0Var.setBuilder(this);
            }
        }
    }
}
