package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.view.menu.e0;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.o1;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends o1 implements e0.a, View.OnClickListener, ActionMenuView.a {
    public int A;
    public final int B;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public t f1224r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f1225s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Drawable f1226t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public p f1227u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public b f1228v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public c f1229w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1230y;
    public final int z;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.x = p();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19019c, 0, 0);
        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.B = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.A = -1;
        setSaveEnabled(false);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public final boolean a() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // androidx.appcompat.widget.ActionMenuView.a
    public final boolean b() {
        return !TextUtils.isEmpty(getText()) && this.f1224r.getIcon() == null;
    }

    @Override // androidx.appcompat.view.menu.e0.a
    public final void c(t tVar) {
        this.f1224r = tVar;
        setIcon(tVar.getIcon());
        setTitle(tVar.getTitleCondensed());
        setId(tVar.f1367a);
        setVisibility(tVar.isVisible() ? 0 : 8);
        setEnabled(tVar.isEnabled());
        if (tVar.hasSubMenu() && this.f1228v == null) {
            this.f1228v = new b(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // androidx.appcompat.view.menu.e0.a
    public t getItemData() {
        return this.f1224r;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        p pVar = this.f1227u;
        if (pVar != null) {
            pVar.b(this.f1224r);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.x = p();
        q();
    }

    @Override // androidx.appcompat.widget.o1, android.widget.TextView, android.view.View
    public final void onMeasure(int i10, int i11) {
        int i12;
        boolean zIsEmpty = TextUtils.isEmpty(getText());
        if (!zIsEmpty && (i12 = this.A) >= 0) {
            super.setPadding(i12, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i10, i11);
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        int measuredWidth = getMeasuredWidth();
        int i13 = this.z;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i13) : i13;
        if (mode != 1073741824 && i13 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i11);
        }
        if (!zIsEmpty || this.f1226t == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f1226t.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        b bVar;
        if (this.f1224r.hasSubMenu() && (bVar = this.f1228v) != null && bVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final boolean p() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i10 = configuration.screenWidthDp;
        int i11 = configuration.screenHeightDp;
        if (i10 < 480) {
            return (i10 >= 640 && i11 >= 480) || configuration.orientation == 2;
        }
        return true;
    }

    public final void q() {
        boolean z = true;
        boolean z5 = !TextUtils.isEmpty(this.f1225s);
        if (this.f1226t != null && ((this.f1224r.f1389y & 4) != 4 || (!this.x && !this.f1230y))) {
            z = false;
        }
        boolean z10 = z5 & z;
        setText(z10 ? this.f1225s : null);
        CharSequence charSequence = this.f1224r.f1382q;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z10 ? null : this.f1224r.f1371e);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f1224r.f1383r;
        if (TextUtils.isEmpty(charSequence2)) {
            kotlin.reflect.b0.E(this, z10 ? null : this.f1224r.f1371e);
        } else {
            kotlin.reflect.b0.E(this, charSequence2);
        }
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.f1230y != z) {
            this.f1230y = z;
            t tVar = this.f1224r;
            if (tVar != null) {
                q qVar = tVar.f1379n;
                qVar.k = true;
                qVar.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f1226t = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i10 = this.B;
            if (intrinsicWidth > i10) {
                intrinsicHeight = (int) (intrinsicHeight * (i10 / intrinsicWidth));
                intrinsicWidth = i10;
            }
            if (intrinsicHeight > i10) {
                intrinsicWidth = (int) (intrinsicWidth * (i10 / intrinsicHeight));
            } else {
                i10 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i10);
        }
        setCompoundDrawables(drawable, null, null, null);
        q();
    }

    public void setItemInvoker(p pVar) {
        this.f1227u = pVar;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i10, int i11, int i12, int i13) {
        this.A = i10;
        super.setPadding(i10, i11, i12, i13);
    }

    public void setPopupCallback(c cVar) {
        this.f1229w = cVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f1225s = charSequence;
        q();
    }
}
