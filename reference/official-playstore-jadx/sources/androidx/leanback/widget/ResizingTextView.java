package androidx.leanback.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
class ResizingTextView extends TextView {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2990i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2991l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f2992m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f2993n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f2994o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2995p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2996q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f2997r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2998s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f2999t;

    public ResizingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.textViewStyle);
        this.f2995p = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k1.a.f19457g, R.attr.textViewStyle, 0);
        try {
            this.f2990i = typedArrayObtainStyledAttributes.getInt(1, 1);
            this.f2991l = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, -1);
            this.f2992m = typedArrayObtainStyledAttributes.getBoolean(0, false);
            this.f2993n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(3, 0);
            this.f2994o = typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, 0);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final void a(int i10, int i11) {
        if (isPaddingRelative()) {
            setPaddingRelative(getPaddingStart(), i10, getPaddingEnd(), i11);
        } else {
            setPadding(getPaddingLeft(), i10, getPaddingRight(), i11);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cd A[PHI: r2
      0x00cd: PHI (r2v6 boolean) = (r2v2 boolean), (r2v8 boolean) binds: [B:40:0x00ca, B:27:0x0097] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasure(int r8, int r9) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.ResizingTextView.onMeasure(int, int):void");
    }

    @Override // android.widget.TextView
    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m2.f0.X(callback, this));
    }
}
