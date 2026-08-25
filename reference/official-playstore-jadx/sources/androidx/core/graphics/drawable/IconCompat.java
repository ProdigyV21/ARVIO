package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import io.ktor.http.LinkHeader;
import j0.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class IconCompat extends CustomVersionedParcelable {
    public static final PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2090a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f2091b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f2092c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f2093d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2094e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2095f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f2096g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f2097h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2098i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2099j;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    public IconCompat() {
        this.f2090a = -1;
        this.f2092c = null;
        this.f2093d = null;
        this.f2094e = 0;
        this.f2095f = 0;
        this.f2096g = null;
        this.f2097h = k;
        this.f2098i = null;
    }

    public static IconCompat a(Bundle bundle) {
        int i10 = bundle.getInt(LinkHeader.Parameters.Type);
        IconCompat iconCompat = new IconCompat(i10);
        iconCompat.f2094e = bundle.getInt("int1");
        iconCompat.f2095f = bundle.getInt("int2");
        iconCompat.f2099j = bundle.getString("string1");
        if (bundle.containsKey("tint_list")) {
            iconCompat.f2096g = (ColorStateList) bundle.getParcelable("tint_list");
        }
        if (bundle.containsKey("tint_mode")) {
            iconCompat.f2097h = PorterDuff.Mode.valueOf(bundle.getString("tint_mode"));
        }
        switch (i10) {
            case -1:
            case 1:
            case 5:
                iconCompat.f2091b = bundle.getParcelable("obj");
                return iconCompat;
            case 0:
            default:
                Log.w("IconCompat", "Unknown type " + i10);
                return null;
            case 2:
            case 4:
            case 6:
                iconCompat.f2091b = bundle.getString("obj");
                return iconCompat;
            case 3:
                iconCompat.f2091b = bundle.getByteArray("obj");
                return iconCompat;
        }
    }

    public static IconCompat b(Icon icon) {
        icon.getClass();
        int iC = c.c(icon);
        if (iC == 2) {
            return e(null, c.b(icon), c.a(icon));
        }
        if (iC == 4) {
            Uri uriD = c.d(icon);
            uriD.getClass();
            String string = uriD.toString();
            string.getClass();
            IconCompat iconCompat = new IconCompat(4);
            iconCompat.f2091b = string;
            return iconCompat;
        }
        if (iC != 6) {
            IconCompat iconCompat2 = new IconCompat(-1);
            iconCompat2.f2091b = icon;
            return iconCompat2;
        }
        Uri uriD2 = c.d(icon);
        uriD2.getClass();
        String string2 = uriD2.toString();
        string2.getClass();
        IconCompat iconCompat3 = new IconCompat(6);
        iconCompat3.f2091b = string2;
        return iconCompat3;
    }

    public static Bitmap c(boolean z, Bitmap bitmap) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f10 = iMin;
        float f11 = 0.5f * f10;
        float f12 = 0.9166667f * f11;
        if (z) {
            float f13 = 0.010416667f * f10;
            paint.setColor(0);
            paint.setShadowLayer(f13, 0.0f, f10 * 0.020833334f, 1023410176);
            canvas.drawCircle(f11, f11, f12, paint);
            paint.setShadowLayer(f13, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f11, f11, f12, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2.0f, (-(bitmap.getHeight() - iMin)) / 2.0f);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f11, f11, f12, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat d(Context context, int i10) {
        context.getClass();
        return e(context.getResources(), context.getPackageName(), i10);
    }

    public static IconCompat e(Resources resources, String str, int i10) {
        str.getClass();
        if (i10 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f2094e = i10;
        if (resources != null) {
            try {
                iconCompat.f2091b = resources.getResourceName(i10);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f2091b = str;
        }
        iconCompat.f2099j = str;
        return iconCompat;
    }

    public final int f() {
        int i10 = this.f2090a;
        if (i10 == -1) {
            return c.a(this.f2091b);
        }
        if (i10 == 2) {
            return this.f2094e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public final String g() {
        int i10 = this.f2090a;
        if (i10 == -1) {
            return c.b(this.f2091b);
        }
        if (i10 == 2) {
            String str = this.f2099j;
            return (str == null || TextUtils.isEmpty(str)) ? ((String) this.f2091b).split(":", -1)[0] : this.f2099j;
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public final Uri h() {
        int i10 = this.f2090a;
        if (i10 == -1) {
            return c.d(this.f2091b);
        }
        if (i10 == 4 || i10 == 6) {
            return Uri.parse((String) this.f2091b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public final String toString() {
        String str;
        if (this.f2090a == -1) {
            return String.valueOf(this.f2091b);
        }
        StringBuilder sb2 = new StringBuilder("Icon(typ=");
        switch (this.f2090a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb2.append(str);
        switch (this.f2090a) {
            case 1:
            case 5:
                sb2.append(" size=");
                sb2.append(((Bitmap) this.f2091b).getWidth());
                sb2.append("x");
                sb2.append(((Bitmap) this.f2091b).getHeight());
                break;
            case 2:
                sb2.append(" pkg=");
                sb2.append(this.f2099j);
                sb2.append(" id=");
                sb2.append(String.format("0x%08x", Integer.valueOf(f())));
                break;
            case 3:
                sb2.append(" len=");
                sb2.append(this.f2094e);
                if (this.f2095f != 0) {
                    sb2.append(" off=");
                    sb2.append(this.f2095f);
                }
                break;
            case 4:
            case 6:
                sb2.append(" uri=");
                sb2.append(this.f2091b);
                break;
        }
        if (this.f2096g != null) {
            sb2.append(" tint=");
            sb2.append(this.f2096g);
        }
        if (this.f2097h != k) {
            sb2.append(" mode=");
            sb2.append(this.f2097h);
        }
        sb2.append(")");
        return sb2.toString();
    }

    public IconCompat(int i10) {
        this.f2092c = null;
        this.f2093d = null;
        this.f2094e = 0;
        this.f2095f = 0;
        this.f2096g = null;
        this.f2097h = k;
        this.f2098i = null;
        this.f2090a = i10;
    }
}
