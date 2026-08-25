package androidx.core.app;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.graphics.drawable.IconCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f2061a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public IconCompat f2062b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2063c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2064d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2065e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2066f;

    public static w1 a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("icon");
        CharSequence charSequence = bundle.getCharSequence(ContentDisposition.Parameters.Name);
        IconCompat iconCompatA = bundle2 != null ? IconCompat.a(bundle2) : null;
        String string = bundle.getString("uri");
        String string2 = bundle.getString("key");
        boolean z = bundle.getBoolean("isBot");
        boolean z5 = bundle.getBoolean("isImportant");
        w1 w1Var = new w1();
        w1Var.f2061a = charSequence;
        w1Var.f2062b = iconCompatA;
        w1Var.f2063c = string;
        w1Var.f2064d = string2;
        w1Var.f2065e = z;
        w1Var.f2066f = z5;
        return w1Var;
    }

    public final Bundle b() {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putCharSequence(ContentDisposition.Parameters.Name, this.f2061a);
        IconCompat iconCompat = this.f2062b;
        if (iconCompat != null) {
            bundle = new Bundle();
            switch (iconCompat.f2090a) {
                case -1:
                    bundle.putParcelable("obj", (Parcelable) iconCompat.f2091b);
                    break;
                case 0:
                default:
                    throw new IllegalArgumentException("Invalid icon");
                case 1:
                case 5:
                    bundle.putParcelable("obj", (Bitmap) iconCompat.f2091b);
                    break;
                case 2:
                case 4:
                case 6:
                    bundle.putString("obj", (String) iconCompat.f2091b);
                    break;
                case 3:
                    bundle.putByteArray("obj", (byte[]) iconCompat.f2091b);
                    break;
            }
            bundle.putInt(LinkHeader.Parameters.Type, iconCompat.f2090a);
            bundle.putInt("int1", iconCompat.f2094e);
            bundle.putInt("int2", iconCompat.f2095f);
            bundle.putString("string1", iconCompat.f2099j);
            ColorStateList colorStateList = iconCompat.f2096g;
            if (colorStateList != null) {
                bundle.putParcelable("tint_list", colorStateList);
            }
            PorterDuff.Mode mode = iconCompat.f2097h;
            if (mode != IconCompat.k) {
                bundle.putString("tint_mode", mode.name());
            }
        } else {
            bundle = null;
        }
        bundle2.putBundle("icon", bundle);
        bundle2.putString("uri", this.f2063c);
        bundle2.putString("key", this.f2064d);
        bundle2.putBoolean("isBot", this.f2065e);
        bundle2.putBoolean("isImportant", this.f2066f);
        return bundle2;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof w1)) {
            return false;
        }
        w1 w1Var = (w1) obj;
        String str = this.f2064d;
        String str2 = w1Var.f2064d;
        return (str == null && str2 == null) ? Objects.equals(Objects.toString(this.f2061a), Objects.toString(w1Var.f2061a)) && Objects.equals(this.f2063c, w1Var.f2063c) && Boolean.valueOf(this.f2065e).equals(Boolean.valueOf(w1Var.f2065e)) && Boolean.valueOf(this.f2066f).equals(Boolean.valueOf(w1Var.f2066f)) : Objects.equals(str, str2);
    }

    public final int hashCode() {
        String str = this.f2064d;
        return str != null ? str.hashCode() : Objects.hash(this.f2061a, this.f2063c, Boolean.valueOf(this.f2065e), Boolean.valueOf(this.f2066f));
    }
}
