package androidx.core.app;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arvio.tv.R;
import com.google.android.gms.cast.CredentialsData;

/* JADX INFO: loaded from: classes3.dex */
public abstract class y0 {
    CharSequence mBigContentTitle;
    protected d0 mBuilder;
    CharSequence mSummaryText;
    boolean mSummaryTextSet = false;

    public static y0 constructCompatStyleByName(String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
            case "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle":
                return new l0();
            case "androidx.core.app.NotificationCompat$BigPictureStyle":
                return new b0();
            case "androidx.core.app.NotificationCompat$CallStyle":
                return new j0();
            case "androidx.core.app.NotificationCompat$InboxStyle":
                return new c0(1);
            case "androidx.core.app.NotificationCompat$BigTextStyle":
                return new c0(0);
            case "androidx.core.app.NotificationCompat$MessagingStyle":
                return new u0();
            default:
                return null;
        }
    }

    public static y0 constructCompatStyleForBundle(Bundle bundle) {
        y0 y0VarConstructCompatStyleByName = constructCompatStyleByName(bundle.getString("androidx.core.app.extra.COMPAT_TEMPLATE"));
        if (y0VarConstructCompatStyleByName != null) {
            return y0VarConstructCompatStyleByName;
        }
        if (bundle.containsKey("android.selfDisplayName") || bundle.containsKey("android.messagingStyleUser")) {
            return new u0();
        }
        if (bundle.containsKey("android.picture") || bundle.containsKey("android.pictureIcon")) {
            return new b0();
        }
        if (bundle.containsKey("android.bigText")) {
            return new c0(0);
        }
        if (bundle.containsKey("android.textLines")) {
            return new c0(1);
        }
        if (bundle.containsKey("android.callType")) {
            return new j0();
        }
        String string = bundle.getString("android.template");
        if (string == null) {
            return null;
        }
        if (string.equals(Notification.BigPictureStyle.class.getName())) {
            return new b0();
        }
        if (string.equals(Notification.BigTextStyle.class.getName())) {
            return new c0(0);
        }
        if (string.equals(Notification.InboxStyle.class.getName())) {
            return new c0(1);
        }
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        if (string.equals(Notification.MessagingStyle.class.getName())) {
            return new u0();
        }
        if (string.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
            return new l0();
        }
        return null;
    }

    public static y0 constructStyleForExtras(Bundle bundle) {
        y0 y0VarConstructCompatStyleForBundle = constructCompatStyleForBundle(bundle);
        if (y0VarConstructCompatStyleForBundle == null) {
            return null;
        }
        try {
            y0VarConstructCompatStyleForBundle.restoreFromCompatExtras(bundle);
            return y0VarConstructCompatStyleForBundle;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public static y0 extractStyleFromNotification(Notification notification) {
        Bundle bundle = notification.extras;
        if (bundle == null) {
            return null;
        }
        return constructStyleForExtras(bundle);
    }

    public final Bitmap a(IconCompat iconCompat, int i10, int i11) {
        Object obj;
        ApplicationInfo applicationInfo;
        Resources resourcesForApplication;
        Context context = this.mBuilder.f1962a;
        if (iconCompat.f2090a == 2 && (obj = iconCompat.f2091b) != null) {
            String str = (String) obj;
            if (str.contains(":")) {
                String str2 = str.split(":", -1)[1];
                String str3 = str2.split(DomExceptionUtils.SEPARATOR, -1)[0];
                String str4 = str2.split(DomExceptionUtils.SEPARATOR, -1)[1];
                String str5 = str.split(":", -1)[0];
                if ("0_resource_name_obfuscated".equals(str4)) {
                    Log.i("IconCompat", "Found obfuscated resource, not trying to update resource id for it");
                } else {
                    String strG = iconCompat.g();
                    if (CredentialsData.CREDENTIALS_TYPE_ANDROID.equals(strG)) {
                        resourcesForApplication = Resources.getSystem();
                    } else {
                        PackageManager packageManager = context.getPackageManager();
                        try {
                            applicationInfo = packageManager.getApplicationInfo(strG, 8192);
                        } catch (PackageManager.NameNotFoundException e5) {
                            Log.e("IconCompat", "Unable to find pkg=" + strG + " for icon", e5);
                        }
                        resourcesForApplication = applicationInfo != null ? packageManager.getResourcesForApplication(applicationInfo) : null;
                    }
                    int identifier = resourcesForApplication.getIdentifier(str4, str3, str5);
                    if (iconCompat.f2094e != identifier) {
                        Log.i("IconCompat", "Id has changed for " + strG + " " + str);
                        iconCompat.f2094e = identifier;
                    }
                }
            }
        }
        Drawable drawableE = j0.c.e(j0.c.f(iconCompat, context), context);
        int intrinsicWidth = i11 == 0 ? drawableE.getIntrinsicWidth() : i11;
        if (i11 == 0) {
            i11 = drawableE.getIntrinsicHeight();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, i11, Bitmap.Config.ARGB_8888);
        drawableE.setBounds(0, 0, intrinsicWidth, i11);
        if (i10 != 0) {
            drawableE.mutate().setColorFilter(new PorterDuffColorFilter(i10, PorterDuff.Mode.SRC_IN));
        }
        drawableE.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public void addCompatExtras(Bundle bundle) {
        if (this.mSummaryTextSet) {
            bundle.putCharSequence("android.summaryText", this.mSummaryText);
        }
        CharSequence charSequence = this.mBigContentTitle;
        if (charSequence != null) {
            bundle.putCharSequence("android.title.big", charSequence);
        }
        String className = getClassName();
        if (className != null) {
            bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", className);
        }
    }

    public abstract void apply(t tVar);

    public RemoteViews applyStandardTemplate(boolean z, int i10, boolean z5) {
        boolean z10;
        Resources resources = this.mBuilder.f1962a.getResources();
        RemoteViews remoteViews = new RemoteViews(this.mBuilder.f1962a.getPackageName(), i10);
        d0 d0Var = this.mBuilder;
        int i11 = d0Var.f1970i;
        if (d0Var.f1969h != null) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            remoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(this.mBuilder.f1969h, 0));
            if (z && this.mBuilder.B.icon != 0) {
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
                int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) * 2);
                d0 d0Var2 = this.mBuilder;
                remoteViews.setImageViewBitmap(R.id.right_icon, b(d0Var2.B.icon, dimensionPixelSize, dimensionPixelSize2, d0Var2.f1982v));
                remoteViews.setViewVisibility(R.id.right_icon, 0);
            }
        } else if (z && d0Var.B.icon != 0) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
            int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
            d0 d0Var3 = this.mBuilder;
            remoteViews.setImageViewBitmap(R.id.icon, b(d0Var3.B.icon, dimensionPixelSize3, dimensionPixelSize4, d0Var3.f1982v));
        }
        CharSequence charSequence = this.mBuilder.f1966e;
        if (charSequence != null) {
            remoteViews.setTextViewText(R.id.title, charSequence);
        }
        CharSequence charSequence2 = this.mBuilder.f1967f;
        boolean z11 = true;
        if (charSequence2 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence2);
            z10 = true;
        } else {
            z10 = false;
        }
        this.mBuilder.getClass();
        this.mBuilder.getClass();
        remoteViews.setViewVisibility(R.id.info, 8);
        CharSequence charSequence3 = this.mBuilder.f1973m;
        if (charSequence3 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence3);
            CharSequence charSequence4 = this.mBuilder.f1967f;
            if (charSequence4 != null) {
                remoteViews.setTextViewText(R.id.text2, charSequence4);
                remoteViews.setViewVisibility(R.id.text2, 0);
                if (z5) {
                    remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                }
                remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
            } else {
                remoteViews.setViewVisibility(R.id.text2, 8);
            }
        }
        d0 d0Var4 = this.mBuilder;
        if ((d0Var4.f1971j ? d0Var4.B.when : 0L) == 0) {
            z11 = false;
        } else if (d0Var4.k) {
            remoteViews.setViewVisibility(R.id.chronometer, 0);
            d0 d0Var5 = this.mBuilder;
            remoteViews.setLong(R.id.chronometer, "setBase", (SystemClock.elapsedRealtime() - System.currentTimeMillis()) + (d0Var5.f1971j ? d0Var5.B.when : 0L));
            remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
            this.mBuilder.getClass();
        } else {
            remoteViews.setViewVisibility(R.id.time, 0);
            d0 d0Var6 = this.mBuilder;
            remoteViews.setLong(R.id.time, "setTime", d0Var6.f1971j ? d0Var6.B.when : 0L);
        }
        remoteViews.setViewVisibility(R.id.right_side, z11 ? 0 : 8);
        remoteViews.setViewVisibility(R.id.line3, z10 ? 0 : 8);
        return remoteViews;
    }

    public final Bitmap b(int i10, int i11, int i12, int i13) {
        if (i13 == 0) {
            i13 = 0;
        }
        Bitmap bitmapA = a(IconCompat.d(this.mBuilder.f1962a, R.drawable.notification_icon_background), i13, i11);
        Canvas canvas = new Canvas(bitmapA);
        Drawable drawableMutate = this.mBuilder.f1962a.getResources().getDrawable(i10).mutate();
        drawableMutate.setFilterBitmap(true);
        int i14 = (i11 - i12) / 2;
        int i15 = i12 + i14;
        drawableMutate.setBounds(i14, i14, i15, i15);
        drawableMutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
        drawableMutate.draw(canvas);
        return bitmapA;
    }

    public Notification build() {
        d0 d0Var = this.mBuilder;
        if (d0Var != null) {
            return d0Var.a();
        }
        return null;
    }

    public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
        remoteViews.setViewVisibility(R.id.title, 8);
        remoteViews.setViewVisibility(R.id.text2, 8);
        remoteViews.setViewVisibility(R.id.text, 8);
        remoteViews.removeAllViews(R.id.notification_main_column);
        remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(R.id.notification_main_column, 0);
        Resources resources = this.mBuilder.f1962a.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
        float f10 = resources.getConfiguration().fontScale;
        if (f10 < 1.0f) {
            f10 = 1.0f;
        } else if (f10 > 1.3f) {
            f10 = 1.3f;
        }
        float f11 = (f10 - 1.0f) / 0.29999995f;
        remoteViews.setViewPadding(R.id.notification_main_column_container, 0, Math.round((f11 * dimensionPixelSize2) + ((1.0f - f11) * dimensionPixelSize)), 0, 0);
    }

    public void clearCompatExtraKeys(Bundle bundle) {
        bundle.remove("android.summaryText");
        bundle.remove("android.title.big");
        bundle.remove("androidx.core.app.extra.COMPAT_TEMPLATE");
    }

    public Bitmap createColoredBitmap(IconCompat iconCompat, int i10) {
        return a(iconCompat, i10, 0);
    }

    public boolean displayCustomViewInline() {
        return false;
    }

    public String getClassName() {
        return null;
    }

    public RemoteViews makeBigContentView(t tVar) {
        return null;
    }

    public RemoteViews makeContentView(t tVar) {
        return null;
    }

    public RemoteViews makeHeadsUpContentView(t tVar) {
        return null;
    }

    public void restoreFromCompatExtras(Bundle bundle) {
        if (bundle.containsKey("android.summaryText")) {
            this.mSummaryText = bundle.getCharSequence("android.summaryText");
            this.mSummaryTextSet = true;
        }
        this.mBigContentTitle = bundle.getCharSequence("android.title.big");
    }

    public void setBuilder(d0 d0Var) {
        if (this.mBuilder != d0Var) {
            this.mBuilder = d0Var;
            if (d0Var != null) {
                d0Var.e(this);
            }
        }
    }

    public Bitmap createColoredBitmap(int i10, int i11) {
        return a(IconCompat.d(this.mBuilder.f1962a, i10), i11, 0);
    }
}
