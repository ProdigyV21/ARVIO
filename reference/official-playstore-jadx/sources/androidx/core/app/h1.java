package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import com.arvio.tv.R;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Notification.Builder f1994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d0 f1995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Bundle f1996d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10, types: [android.os.Parcelable[]] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v9, types: [android.os.Bundle[]] */
    /* JADX WARN: Type inference failed for: r14v1, types: [android.os.BaseBundle, android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r2v21, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r2v22, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r2v40 */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v11, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.CharSequence[]] */
    /* JADX WARN: Type inference failed for: r7v14, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r8v29, types: [android.os.Bundle] */
    public h1(d0 d0Var) throws Throwable {
        Throwable th;
        ?? r52;
        int i10;
        ?? r10;
        ArrayList arrayList;
        x xVarC;
        new ArrayList();
        this.f1996d = new Bundle();
        this.f1995c = d0Var;
        Context context = d0Var.f1962a;
        ArrayList arrayList2 = d0Var.C;
        ArrayList<w1> arrayList3 = d0Var.f1964c;
        ArrayList arrayList4 = d0Var.f1965d;
        this.f1993a = context;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1994b = d1.a(context, d0Var.x);
        } else {
            this.f1994b = new Notification.Builder(context);
        }
        Notification notification = d0Var.B;
        Throwable th2 = null;
        int i11 = 2;
        this.f1994b.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(d0Var.f1966e).setContentText(d0Var.f1967f).setContentInfo(null).setContentIntent(d0Var.f1968g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(d0Var.f1974n, d0Var.f1975o, d0Var.f1976p);
        Notification.Builder builder = this.f1994b;
        IconCompat iconCompat = d0Var.f1969h;
        b1.b(builder, iconCompat == null ? null : j0.c.f(iconCompat, context));
        this.f1994b.setSubText(d0Var.f1973m).setUsesChronometer(d0Var.k).setPriority(d0Var.f1970i);
        y0 y0Var = d0Var.f1972l;
        if (y0Var instanceof j0) {
            j0 j0Var = (j0) y0Var;
            PendingIntent pendingIntent = j0Var.f2007d;
            x xVarC2 = pendingIntent == null ? j0Var.c(2131231060, R.string.call_notification_hang_up_action, j0Var.f2011h, R.color.call_notification_decline_color, j0Var.f2008e) : j0Var.c(2131231060, R.string.call_notification_decline_action, j0Var.f2011h, R.color.call_notification_decline_color, pendingIntent);
            PendingIntent pendingIntent2 = j0Var.f2006c;
            if (pendingIntent2 == null) {
                xVarC = null;
            } else {
                boolean z = j0Var.f2009f;
                xVarC = j0Var.c(z ? 2131231058 : 2131231056, z ? R.string.call_notification_answer_video_action : R.string.call_notification_answer_action, j0Var.f2010g, R.color.call_notification_answer_color, pendingIntent2);
            }
            ArrayList arrayList5 = new ArrayList(3);
            arrayList5.add(xVarC2);
            ArrayList<x> arrayList6 = j0Var.mBuilder.f1963b;
            if (arrayList6 != null) {
                for (x xVar : arrayList6) {
                    xVar.getClass();
                    if (!xVar.f2067a.getBoolean("key_action_priority") && i11 > 1) {
                        arrayList5.add(xVar);
                        i11--;
                    }
                    if (xVarC != null && i11 == 1) {
                        arrayList5.add(xVarC);
                        i11--;
                    }
                }
            }
            if (xVarC != null && i11 >= 1) {
                arrayList5.add(xVarC);
            }
            Iterator it = arrayList5.iterator();
            while (it.hasNext()) {
                b((x) it.next());
            }
        } else {
            Iterator it2 = d0Var.f1963b.iterator();
            while (it2.hasNext()) {
                b((x) it2.next());
            }
        }
        Bundle bundle = d0Var.f1981u;
        if (bundle != null) {
            this.f1996d.putAll(bundle);
        }
        int i12 = Build.VERSION.SDK_INT;
        this.f1994b.setShowWhen(d0Var.f1971j);
        z0.i(this.f1994b, d0Var.f1978r);
        z0.g(this.f1994b, d0Var.f1977q);
        z0.j(this.f1994b, null);
        z0.h(this.f1994b, false);
        a1.b(this.f1994b, null);
        a1.c(this.f1994b, d0Var.f1982v);
        a1.f(this.f1994b, d0Var.f1983w);
        a1.d(this.f1994b, null);
        a1.e(this.f1994b, notification.sound, notification.audioAttributes);
        if (i12 < 28) {
            if (arrayList3 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(arrayList3.size());
                for (w1 w1Var : arrayList3) {
                    CharSequence charSequence = w1Var.f2061a;
                    String str = w1Var.f2063c;
                    if (str == null) {
                        str = charSequence != null ? "name:" + ((Object) charSequence) : "";
                    }
                    arrayList.add(str);
                }
            }
            if (arrayList != null) {
                if (arrayList2 == null) {
                    arrayList2 = arrayList;
                } else {
                    t.f fVar = new t.f(arrayList2.size() + arrayList.size());
                    fVar.addAll(arrayList);
                    fVar.addAll(arrayList2);
                    arrayList2 = new ArrayList(fVar);
                }
            }
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                a1.a(this.f1994b, (String) it3.next());
            }
        }
        if (arrayList4.size() > 0) {
            if (d0Var.f1981u == null) {
                d0Var.f1981u = new Bundle();
            }
            Bundle bundle2 = d0Var.f1981u.getBundle("android.car.EXTENSIONS");
            ?? bundle3 = bundle2 == null ? new Bundle() : bundle2;
            ?? bundle4 = new Bundle((Bundle) bundle3);
            ?? bundle5 = new Bundle();
            int i13 = 0;
            while (i13 < arrayList4.size()) {
                String string = Integer.toString(i13);
                x xVar2 = (x) arrayList4.get(i13);
                ?? bundle6 = new Bundle();
                IconCompat iconCompatA = xVar2.a();
                Bundle bundle7 = xVar2.f2067a;
                Throwable th3 = th2;
                bundle6.putInt("icon", iconCompatA != null ? iconCompatA.f() : 0);
                bundle6.putCharSequence(LinkHeader.Parameters.Title, xVar2.f2073g);
                bundle6.putParcelable("actionIntent", xVar2.f2074h);
                Bundle bundle8 = bundle7 != null ? new Bundle(bundle7) : new Bundle();
                bundle8.putBoolean("android.support.allowGeneratedReplies", xVar2.f2070d);
                bundle6.putBundle("extras", bundle8);
                a2[] a2VarArr = xVar2.f2069c;
                if (a2VarArr == null) {
                    r10 = th3;
                } else {
                    r10 = new Bundle[a2VarArr.length];
                    if (a2VarArr.length > 0) {
                        a2 a2Var = a2VarArr[0];
                        new Bundle();
                        throw th3;
                    }
                }
                bundle6.putParcelableArray("remoteInputs", r10);
                bundle6.putBoolean("showsUserInterface", xVar2.f2071e);
                bundle6.putInt("semanticAction", 0);
                bundle5.putBundle(string, bundle6);
                i13++;
                th2 = th3;
            }
            th = th2;
            bundle3.putBundle("invisible_actions", bundle5);
            bundle4.putBundle("invisible_actions", bundle5);
            if (d0Var.f1981u == null) {
                d0Var.f1981u = new Bundle();
            }
            d0Var.f1981u.putBundle("android.car.EXTENSIONS", bundle3);
            this.f1996d.putBundle("android.car.EXTENSIONS", bundle4);
        } else {
            th = null;
        }
        int i14 = Build.VERSION.SDK_INT;
        if (i14 >= 24) {
            this.f1994b.setExtras(d0Var.f1981u);
            ?? r53 = th;
            c1.e(this.f1994b, r53);
            r52 = r53;
        } else {
            r52 = th;
        }
        if (i14 >= 26) {
            d1.b(this.f1994b, d0Var.f1984y);
            d1.e(this.f1994b, r52);
            d1.f(this.f1994b, r52);
            d1.g(this.f1994b, 0L);
            d1.d(this.f1994b, 0);
            if (d0Var.f1980t) {
                d1.c(this.f1994b, d0Var.f1979s);
            }
            if (!TextUtils.isEmpty(d0Var.x)) {
                this.f1994b.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i14 >= 28) {
            for (w1 w1Var2 : arrayList3) {
                Notification.Builder builder2 = this.f1994b;
                w1Var2.getClass();
                e1.a(builder2, v1.b(w1Var2));
            }
        }
        int i15 = Build.VERSION.SDK_INT;
        if (i15 >= 29) {
            f1.a(this.f1994b, d0Var.A);
            f1.b(this.f1994b, null);
        }
        if (i15 < 31 || (i10 = d0Var.z) == 0) {
            return;
        }
        g1.b(this.f1994b, i10);
    }

    @Override // androidx.core.app.t
    public final Notification.Builder a() {
        return this.f1994b;
    }

    public final void b(x xVar) {
        IconCompat iconCompatA = xVar.a();
        boolean z = xVar.f2070d;
        Bundle bundle = xVar.f2067a;
        Notification.Action.Builder builderA = b1.a(iconCompatA != null ? j0.c.f(iconCompatA, null) : null, xVar.f2073g, xVar.f2074h);
        a2[] a2VarArr = xVar.f2069c;
        if (a2VarArr != null) {
            RemoteInput[] remoteInputArr = new RemoteInput[a2VarArr.length];
            if (a2VarArr.length > 0) {
                a2 a2Var = a2VarArr[0];
                throw null;
            }
            for (RemoteInput remoteInput : remoteInputArr) {
                z0.c(builderA, remoteInput);
            }
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", z);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 24) {
            c1.a(builderA, z);
        }
        bundle2.putInt("android.support.action.semanticAction", 0);
        if (i10 >= 28) {
            e1.b(builderA, 0);
        }
        if (i10 >= 29) {
            f1.c(builderA, false);
        }
        if (i10 >= 31) {
            g1.a(builderA, false);
        }
        bundle2.putBoolean("android.support.action.showsUserInterface", xVar.f2071e);
        z0.b(builderA, bundle2);
        z0.a(this.f1994b, z0.d(builderA));
    }
}
