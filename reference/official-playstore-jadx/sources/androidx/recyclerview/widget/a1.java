package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public class a1 extends ViewGroup.MarginLayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p1 f4875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f4876b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4877c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4878d;

    public a1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4876b = new Rect();
        this.f4877c = true;
        this.f4878d = false;
    }

    public a1(int i10, int i11) {
        super(i10, i11);
        this.f4876b = new Rect();
        this.f4877c = true;
        this.f4878d = false;
    }

    public a1(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f4876b = new Rect();
        this.f4877c = true;
        this.f4878d = false;
    }

    public a1(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f4876b = new Rect();
        this.f4877c = true;
        this.f4878d = false;
    }

    public a1(a1 a1Var) {
        super((ViewGroup.LayoutParams) a1Var);
        this.f4876b = new Rect();
        this.f4877c = true;
        this.f4878d = false;
    }
}
