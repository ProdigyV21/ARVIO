package androidx.leanback.widget;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillValue;
import android.widget.EditText;
import android.widget.TextView;
import androidx.leanback.widget.e0;
import androidx.leanback.widget.g0;

/* JADX INFO: loaded from: classes3.dex */
public class GuidedActionEditText extends EditText implements g0, e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g0.a f2944i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e0.a f2945l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Drawable f2946m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f0 f2947n;

    public GuidedActionEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.editTextStyle);
        this.f2946m = getBackground();
        f0 f0Var = new f0();
        this.f2947n = f0Var;
        setBackground(f0Var);
    }

    @Override // android.widget.TextView, android.view.View
    public final void autofill(AutofillValue autofillValue) {
        super.autofill(autofillValue);
        e0.a aVar = this.f2945l;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int getAutofillType() {
        return 1;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z, int i10, Rect rect) {
        super.onFocusChanged(z, i10, rect);
        if (z) {
            setBackground(this.f2946m);
        } else {
            setBackground(this.f2947n);
        }
        if (z) {
            return;
        }
        setFocusable(false);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName((isFocused() ? EditText.class : TextView.class).getName());
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i10, KeyEvent keyEvent) {
        g0.a aVar = this.f2944i;
        boolean zA = aVar != null ? aVar.a() : false;
        return !zA ? super.onKeyPreIme(i10, keyEvent) : zA;
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInTouchMode() || isFocusableInTouchMode() || isTextSelectable()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m2.f0.X(callback, this));
    }

    public void setImeKeyListener(g0.a aVar) {
        this.f2944i = aVar;
    }

    public void setOnAutofillListener(e0.a aVar) {
        this.f2945l = aVar;
    }
}
