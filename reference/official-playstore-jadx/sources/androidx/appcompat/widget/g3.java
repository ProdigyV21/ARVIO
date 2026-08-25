package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class g3 implements h3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1588a;

    public /* synthetic */ g3(int i10) {
        this.f1588a = i10;
    }

    @Override // androidx.appcompat.widget.h3
    public final Drawable a(Context context, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Resources.Theme theme) throws Throwable {
        switch (this.f1588a) {
            case 0:
                String classAttribute = attributeSet.getClassAttribute();
                if (classAttribute != null) {
                    try {
                        Drawable drawable = (Drawable) g3.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(null).newInstance(null);
                        m.b.c(drawable, context.getResources(), xmlResourceParser, attributeSet, theme);
                    } catch (Exception e5) {
                        Log.e("DrawableDelegate", "Exception while inflating <drawable>", e5);
                        return null;
                    }
                }
                break;
            case 1:
                try {
                } catch (Exception e6) {
                    Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e6);
                    return null;
                }
                break;
            case 2:
                try {
                    Resources resources = context.getResources();
                    androidx.vectordrawable.graphics.drawable.h hVar = new androidx.vectordrawable.graphics.drawable.h(context);
                    hVar.inflate(resources, xmlResourceParser, attributeSet, theme);
                } catch (Exception e10) {
                    Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e10);
                    return null;
                }
                break;
            default:
                try {
                    Resources resources2 = context.getResources();
                    androidx.vectordrawable.graphics.drawable.t tVar = new androidx.vectordrawable.graphics.drawable.t();
                    tVar.inflate(resources2, xmlResourceParser, attributeSet, theme);
                } catch (Exception e11) {
                    Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e11);
                    return null;
                }
                break;
        }
        return null;
    }
}
