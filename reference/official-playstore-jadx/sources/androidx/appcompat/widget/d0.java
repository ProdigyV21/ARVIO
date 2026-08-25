package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a8.e f1545b;

    public d0(TextView textView) {
        this.f1544a = textView;
        this.f1545b = new a8.e(textView);
    }

    public final void a(AttributeSet attributeSet, int i10) {
        TypedArray typedArrayObtainStyledAttributes = this.f1544a.getContext().obtainStyledAttributes(attributeSet, j.a.f19025i, i10, 0);
        try {
            boolean z = typedArrayObtainStyledAttributes.hasValue(14) ? typedArrayObtainStyledAttributes.getBoolean(14, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            c(z);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void b(boolean z) {
        ((qb.d) this.f1545b.f183l).Q(z);
    }

    public final void c(boolean z) {
        ((qb.d) this.f1545b.f183l).R(z);
    }
}
