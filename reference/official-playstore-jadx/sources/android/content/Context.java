package android.content;

import a0.c;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.lifecycle.r;
import androidx.lifecycle.y;
import androidx.vectordrawable.graphics.drawable.h;
import androidx.vectordrawable.graphics.drawable.t;
import i0.k;
import i0.q;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import m2.f0;
import org.xmlpull.v1.XmlPullParserException;
import qb.d;

/* JADX INFO: renamed from: coil.util.-Contexts, reason: from Kotlin metadata */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u0004\u001a\u00020\u0003*\u00020\u00062\b\b\u0001\u0010\u0002\u001a\u00020\u00012\f\u0010\b\u001a\b\u0018\u00010\u0007R\u00020\u0006H\u0000¢\u0006\u0004\b\u0004\u0010\t\u001a%\u0010\u000b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\u000e\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a \u0010\u0012\u001a\u00028\u0000\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0010*\u00020\u0000H\u0080\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001b\u0010\u0017\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroid/content/Context;", "", "resId", "Landroid/graphics/drawable/Drawable;", "getDrawableCompat", "(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;", "Landroid/content/res/Resources;", "Landroid/content/res/Resources$Theme;", "theme", "(Landroid/content/res/Resources;ILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;", "resources", "getXmlDrawableCompat", "(Landroid/content/Context;Landroid/content/res/Resources;I)Landroid/graphics/drawable/Drawable;", "Landroidx/lifecycle/r;", "getLifecycle", "(Landroid/content/Context;)Landroidx/lifecycle/r;", "", "T", "requireSystemService", "(Landroid/content/Context;)Ljava/lang/Object;", "", "permission", "", "isPermissionGranted", "(Landroid/content/Context;Ljava/lang/String;)Z", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class Context {
    public static final Drawable getDrawableCompat(android.content.Context context, int i10) {
        Drawable drawableO = f0.o(context, i10);
        if (drawableO != null) {
            return drawableO;
        }
        throw new IllegalStateException(c.i(i10, "Invalid resource ID: ").toString());
    }

    public static final r getLifecycle(android.content.Context context) {
        Object baseContext = context;
        while (!(baseContext instanceof y)) {
            if (!(baseContext instanceof ContextWrapper)) {
                return null;
            }
            baseContext = ((ContextWrapper) baseContext).getBaseContext();
        }
        return ((y) baseContext).getLifecycle();
    }

    public static final Drawable getXmlDrawableCompat(android.content.Context context, Resources resources, int i10) throws Throwable {
        XmlResourceParser xml = resources.getXml(i10);
        int next = xml.next();
        while (next != 2 && next != 1) {
            next = xml.next();
        }
        if (next != 2) {
            throw new XmlPullParserException("No start tag found.");
        }
        if (Build.VERSION.SDK_INT < 24) {
            String name = xml.getName();
            if (p.a(name, "vector")) {
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                Resources.Theme theme = context.getTheme();
                t tVar = new t();
                tVar.inflate(resources, xml, attributeSetAsAttributeSet, theme);
                return tVar;
            }
            if (p.a(name, "animated-vector")) {
                AttributeSet attributeSetAsAttributeSet2 = Xml.asAttributeSet(xml);
                Resources.Theme theme2 = context.getTheme();
                h hVar = new h(context);
                hVar.inflate(resources, xml, attributeSetAsAttributeSet2, theme2);
                return hVar;
            }
        }
        return getDrawableCompat(resources, i10, context.getTheme());
    }

    public static final boolean isPermissionGranted(android.content.Context context, String str) {
        return d.f(context, str) == 0;
    }

    public static final <T> T requireSystemService(android.content.Context context) {
        p.h();
        throw null;
    }

    public static final Drawable getDrawableCompat(Resources resources, int i10, Resources.Theme theme) {
        ThreadLocal threadLocal = q.f16004a;
        Drawable drawableA = k.a(resources, i10, theme);
        if (drawableA != null) {
            return drawableA;
        }
        throw new IllegalStateException(c.i(i10, "Invalid resource ID: ").toString());
    }
}
