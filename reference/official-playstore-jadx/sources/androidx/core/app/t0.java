package androidx.core.app;

import android.app.Notification;
import android.app.Person;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f2043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f2044b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w1 f2045c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Bundle f2046d = new Bundle();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Uri f2048f;

    public t0(CharSequence charSequence, long j10, w1 w1Var) {
        this.f2043a = charSequence;
        this.f2044b = j10;
        this.f2045c = w1Var;
    }

    public static Bundle[] a(ArrayList arrayList) {
        Bundle[] bundleArr = new Bundle[arrayList.size()];
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            t0 t0Var = (t0) arrayList.get(i10);
            w1 w1Var = t0Var.f2045c;
            Bundle bundle = new Bundle();
            CharSequence charSequence = t0Var.f2043a;
            if (charSequence != null) {
                bundle.putCharSequence("text", charSequence);
            }
            bundle.putLong("time", t0Var.f2044b);
            if (w1Var != null) {
                bundle.putCharSequence("sender", w1Var.f2061a);
                if (Build.VERSION.SDK_INT >= 28) {
                    bundle.putParcelable("sender_person", s0.a(v1.b(w1Var)));
                } else {
                    bundle.putBundle("person", w1Var.b());
                }
            }
            String str = t0Var.f2047e;
            if (str != null) {
                bundle.putString(LinkHeader.Parameters.Type, str);
            }
            Uri uri = t0Var.f2048f;
            if (uri != null) {
                bundle.putParcelable("uri", uri);
            }
            Bundle bundle2 = t0Var.f2046d;
            if (bundle2 != null) {
                bundle.putBundle("extras", bundle2);
            }
            bundleArr[i10] = bundle;
        }
        return bundleArr;
    }

    public static ArrayList b(Parcelable[] parcelableArr) {
        w1 w1VarA;
        ArrayList arrayList = new ArrayList(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable instanceof Bundle) {
                Bundle bundle = (Bundle) parcelable;
                t0 t0Var = null;
                try {
                    if (bundle.containsKey("text") && bundle.containsKey("time")) {
                        if (bundle.containsKey("person")) {
                            w1VarA = w1.a(bundle.getBundle("person"));
                        } else if (bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            w1VarA = v1.a((Person) bundle.getParcelable("sender_person"));
                        } else if (bundle.containsKey("sender")) {
                            CharSequence charSequence = bundle.getCharSequence("sender");
                            w1 w1Var = new w1();
                            w1Var.f2061a = charSequence;
                            w1Var.f2062b = null;
                            w1Var.f2063c = null;
                            w1Var.f2064d = null;
                            w1Var.f2065e = false;
                            w1Var.f2066f = false;
                            w1VarA = w1Var;
                        } else {
                            w1VarA = null;
                        }
                        t0 t0Var2 = new t0(bundle.getCharSequence("text"), bundle.getLong("time"), w1VarA);
                        if (bundle.containsKey(LinkHeader.Parameters.Type) && bundle.containsKey("uri")) {
                            String string = bundle.getString(LinkHeader.Parameters.Type);
                            Uri uri = (Uri) bundle.getParcelable("uri");
                            t0Var2.f2047e = string;
                            t0Var2.f2048f = uri;
                        }
                        if (bundle.containsKey("extras")) {
                            t0Var2.f2046d.putAll(bundle.getBundle("extras"));
                        }
                        t0Var = t0Var2;
                    }
                } catch (ClassCastException unused) {
                }
                if (t0Var != null) {
                    arrayList.add(t0Var);
                }
            }
        }
        return arrayList;
    }

    public final Notification.MessagingStyle.Message c() {
        Notification.MessagingStyle.Message messageA;
        int i10 = Build.VERSION.SDK_INT;
        long j10 = this.f2044b;
        CharSequence charSequence = this.f2043a;
        w1 w1Var = this.f2045c;
        if (i10 >= 28) {
            messageA = s0.b(charSequence, j10, w1Var != null ? v1.b(w1Var) : null);
        } else {
            messageA = r0.a(charSequence, j10, w1Var != null ? w1Var.f2061a : null);
        }
        String str = this.f2047e;
        if (str != null) {
            r0.b(messageA, str, this.f2048f);
        }
        return messageA;
    }
}
