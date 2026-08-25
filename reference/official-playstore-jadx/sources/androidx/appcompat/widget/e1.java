package androidx.appcompat.widget;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e1 extends i0.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1561b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ WeakReference f1562c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ k1 f1563d;

    public e1(k1 k1Var, int i10, int i11, WeakReference weakReference) {
        this.f1563d = k1Var;
        this.f1560a = i10;
        this.f1561b = i11;
        this.f1562c = weakReference;
    }

    @Override // i0.o
    public final void onFontRetrievalFailed(int i10) {
    }

    @Override // i0.o
    public final void onFontRetrieved(Typeface typeface) {
        int i10;
        if (Build.VERSION.SDK_INT >= 28 && (i10 = this.f1560a) != -1) {
            typeface = j1.a(typeface, i10, (this.f1561b & 2) != 0);
        }
        k1 k1Var = this.f1563d;
        if (k1Var.f1636m) {
            k1Var.f1635l = typeface;
            TextView textView = (TextView) this.f1562c.get();
            if (textView != null) {
                WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                if (textView.isAttachedToWindow()) {
                    textView.post(new android.support.v4.os.e(k1Var.f1634j, 1, textView, typeface));
                } else {
                    textView.setTypeface(typeface, k1Var.f1634j);
                }
            }
        }
    }
}
