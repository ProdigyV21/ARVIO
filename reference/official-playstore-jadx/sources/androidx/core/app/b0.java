package androidx.core.app;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class b0 extends y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IconCompat f1956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IconCompat f1957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1959d;

    public static IconCompat c(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        if (parcelable instanceof Icon) {
            return IconCompat.b((Icon) parcelable);
        }
        if (!(parcelable instanceof Bitmap)) {
            return null;
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.f2091b = (Bitmap) parcelable;
        return iconCompat;
    }

    @Override // androidx.core.app.y0
    public final void apply(t tVar) {
        Bitmap bitmapC;
        Notification.BigPictureStyle bigContentTitle = new Notification.BigPictureStyle(((h1) tVar).f1994b).setBigContentTitle(this.mBigContentTitle);
        IconCompat iconCompat = this.f1956a;
        if (iconCompat != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                a0.a(bigContentTitle, j0.c.f(iconCompat, ((h1) tVar).f1993a));
            } else {
                int iC = iconCompat.f2090a;
                if (iC == -1) {
                    iC = j0.c.c(iconCompat.f2091b);
                }
                if (iC == 1) {
                    IconCompat iconCompat2 = this.f1956a;
                    int i10 = iconCompat2.f2090a;
                    if (i10 == -1) {
                        Object obj = iconCompat2.f2091b;
                        bitmapC = obj instanceof Bitmap ? (Bitmap) obj : null;
                    } else if (i10 == 1) {
                        bitmapC = (Bitmap) iconCompat2.f2091b;
                    } else {
                        if (i10 != 5) {
                            throw new IllegalStateException("called getBitmap() on " + iconCompat2);
                        }
                        bitmapC = IconCompat.c(true, (Bitmap) iconCompat2.f2091b);
                    }
                    bigContentTitle = bigContentTitle.bigPicture(bitmapC);
                }
            }
        }
        if (this.f1958c) {
            IconCompat iconCompat3 = this.f1957b;
            if (iconCompat3 == null) {
                bigContentTitle.bigLargeIcon((Bitmap) null);
            } else {
                z.a(bigContentTitle, j0.c.f(iconCompat3, ((h1) tVar).f1993a));
            }
        }
        if (this.mSummaryTextSet) {
            bigContentTitle.setSummaryText(this.mSummaryText);
        }
        if (Build.VERSION.SDK_INT >= 31) {
            a0.c(bigContentTitle, this.f1959d);
            a0.b(bigContentTitle, null);
        }
    }

    @Override // androidx.core.app.y0
    public final void clearCompatExtraKeys(Bundle bundle) {
        super.clearCompatExtraKeys(bundle);
        bundle.remove("android.largeIcon.big");
        bundle.remove("android.picture");
        bundle.remove("android.pictureIcon");
        bundle.remove("android.showBigPictureWhenCollapsed");
    }

    @Override // androidx.core.app.y0
    public final String getClassName() {
        return "androidx.core.app.NotificationCompat$BigPictureStyle";
    }

    @Override // androidx.core.app.y0
    public final void restoreFromCompatExtras(Bundle bundle) {
        super.restoreFromCompatExtras(bundle);
        if (bundle.containsKey("android.largeIcon.big")) {
            this.f1957b = c(bundle.getParcelable("android.largeIcon.big"));
            this.f1958c = true;
        }
        Parcelable parcelable = bundle.getParcelable("android.picture");
        this.f1956a = parcelable != null ? c(parcelable) : c(bundle.getParcelable("android.pictureIcon"));
        this.f1959d = bundle.getBoolean("android.showBigPictureWhenCollapsed");
    }
}
