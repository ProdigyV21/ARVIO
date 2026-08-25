package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class j0 extends y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2004a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public w1 f2005b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PendingIntent f2006c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PendingIntent f2007d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PendingIntent f2008e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2009f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Integer f2010g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Integer f2011h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public IconCompat f2012i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f2013j;

    @Override // androidx.core.app.y0
    public final void addCompatExtras(Bundle bundle) {
        super.addCompatExtras(bundle);
        bundle.putInt("android.callType", this.f2004a);
        bundle.putBoolean("android.callIsVideo", this.f2009f);
        w1 w1Var = this.f2005b;
        if (w1Var != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                w1Var.getClass();
                bundle.putParcelable("android.callPerson", g0.b(v1.b(w1Var)));
            } else {
                bundle.putParcelable("android.callPersonCompat", w1Var.b());
            }
        }
        IconCompat iconCompat = this.f2012i;
        if (iconCompat != null) {
            Context context = this.mBuilder.f1962a;
            iconCompat.getClass();
            bundle.putParcelable("android.verificationIcon", f0.a(j0.c.f(iconCompat, context)));
        }
        bundle.putCharSequence("android.verificationText", this.f2013j);
        bundle.putParcelable("android.answerIntent", this.f2006c);
        bundle.putParcelable("android.declineIntent", this.f2007d);
        bundle.putParcelable("android.hangUpIntent", this.f2008e);
        Integer num = this.f2010g;
        if (num != null) {
            bundle.putInt("android.answerColor", num.intValue());
        }
        Integer num2 = this.f2011h;
        if (num2 != null) {
            bundle.putInt("android.declineColor", num2.intValue());
        }
    }

    @Override // androidx.core.app.y0
    public final void apply(t tVar) {
        int i10 = Build.VERSION.SDK_INT;
        String string = null;
        callStyleA = null;
        Notification.CallStyle callStyleA = null;
        if (i10 < 31) {
            Notification.Builder builder = ((h1) tVar).f1994b;
            w1 w1Var = this.f2005b;
            builder.setContentTitle(w1Var != null ? w1Var.f2061a : null);
            Bundle bundle = this.mBuilder.f1981u;
            CharSequence charSequence = (bundle == null || !bundle.containsKey("android.text")) ? null : this.mBuilder.f1981u.getCharSequence("android.text");
            if (charSequence == null) {
                int i11 = this.f2004a;
                if (i11 == 1) {
                    string = this.mBuilder.f1962a.getResources().getString(R.string.call_notification_incoming_text);
                } else if (i11 == 2) {
                    string = this.mBuilder.f1962a.getResources().getString(R.string.call_notification_ongoing_text);
                } else if (i11 == 3) {
                    string = this.mBuilder.f1962a.getResources().getString(R.string.call_notification_screening_text);
                }
                charSequence = string;
            }
            builder.setContentText(charSequence);
            w1 w1Var2 = this.f2005b;
            if (w1Var2 != null) {
                IconCompat iconCompat = w1Var2.f2062b;
                if (iconCompat != null) {
                    f0.c(builder, j0.c.f(iconCompat, this.mBuilder.f1962a));
                }
                if (i10 >= 28) {
                    w1 w1Var3 = this.f2005b;
                    w1Var3.getClass();
                    g0.a(builder, v1.b(w1Var3));
                } else {
                    e0.a(builder, this.f2005b.f2063c);
                }
            }
            e0.b(builder, "call");
            return;
        }
        int i12 = this.f2004a;
        if (i12 == 1) {
            w1 w1Var4 = this.f2005b;
            w1Var4.getClass();
            callStyleA = h0.a(v1.b(w1Var4), this.f2007d, this.f2006c);
        } else if (i12 == 2) {
            w1 w1Var5 = this.f2005b;
            w1Var5.getClass();
            callStyleA = h0.b(v1.b(w1Var5), this.f2008e);
        } else if (i12 == 3) {
            w1 w1Var6 = this.f2005b;
            w1Var6.getClass();
            callStyleA = h0.c(v1.b(w1Var6), this.f2008e, this.f2006c);
        } else if (Log.isLoggable("NotifCompat", 3)) {
            Log.d("NotifCompat", "Unrecognized call type in CallStyle: " + String.valueOf(this.f2004a));
        }
        if (callStyleA != null) {
            callStyleA.setBuilder(((h1) tVar).f1994b);
            Integer num = this.f2010g;
            if (num != null) {
                h0.d(callStyleA, num.intValue());
            }
            Integer num2 = this.f2011h;
            if (num2 != null) {
                h0.f(callStyleA, num2.intValue());
            }
            h0.i(callStyleA, this.f2013j);
            IconCompat iconCompat2 = this.f2012i;
            if (iconCompat2 != null) {
                h0.h(callStyleA, j0.c.f(iconCompat2, this.mBuilder.f1962a));
            }
            h0.g(callStyleA, this.f2009f);
        }
    }

    public final x c(int i10, int i11, Integer num, int i12, PendingIntent pendingIntent) {
        if (num == null) {
            num = Integer.valueOf(androidx.core.content.b.a(this.mBuilder.f1962a, i12));
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) this.mBuilder.f1962a.getResources().getString(i11));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(num.intValue()), 0, spannableStringBuilder.length(), 18);
        x xVarA = new u(IconCompat.d(this.mBuilder.f1962a, i10), spannableStringBuilder, pendingIntent, new Bundle()).a();
        xVarA.f2067a.putBoolean("key_action_priority", true);
        return xVarA;
    }

    @Override // androidx.core.app.y0
    public final boolean displayCustomViewInline() {
        return true;
    }

    @Override // androidx.core.app.y0
    public final String getClassName() {
        return "androidx.core.app.NotificationCompat$CallStyle";
    }

    @Override // androidx.core.app.y0
    public final void restoreFromCompatExtras(Bundle bundle) {
        super.restoreFromCompatExtras(bundle);
        this.f2004a = bundle.getInt("android.callType");
        this.f2009f = bundle.getBoolean("android.callIsVideo");
        if (Build.VERSION.SDK_INT >= 28 && bundle.containsKey("android.callPerson")) {
            this.f2005b = v1.a((Person) bundle.getParcelable("android.callPerson"));
        } else if (bundle.containsKey("android.callPersonCompat")) {
            this.f2005b = w1.a(bundle.getBundle("android.callPersonCompat"));
        }
        if (bundle.containsKey("android.verificationIcon")) {
            this.f2012i = IconCompat.b((Icon) bundle.getParcelable("android.verificationIcon"));
        } else if (bundle.containsKey("android.verificationIconCompat")) {
            this.f2012i = IconCompat.a(bundle.getBundle("android.verificationIconCompat"));
        }
        this.f2013j = bundle.getCharSequence("android.verificationText");
        this.f2006c = (PendingIntent) bundle.getParcelable("android.answerIntent");
        this.f2007d = (PendingIntent) bundle.getParcelable("android.declineIntent");
        this.f2008e = (PendingIntent) bundle.getParcelable("android.hangUpIntent");
        this.f2010g = bundle.containsKey("android.answerColor") ? Integer.valueOf(bundle.getInt("android.answerColor")) : null;
        this.f2011h = bundle.containsKey("android.declineColor") ? Integer.valueOf(bundle.getInt("android.declineColor")) : null;
    }
}
