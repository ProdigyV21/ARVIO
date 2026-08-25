package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.TextView;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends EditText implements androidx.core.view.y0, androidx.core.view.t0, h2, u0.u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1521i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k1 f1522l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j0 f1523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u0.r f1524n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final j0 f1525o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public b0 f1526p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.editTextStyle);
        f4.a(context);
        d4.a(getContext(), this);
        u uVar = new u(this);
        this.f1521i = uVar;
        uVar.l(attributeSet, R.attr.editTextStyle);
        k1 k1Var = new k1(this);
        this.f1522l = k1Var;
        k1Var.f(attributeSet, R.attr.editTextStyle);
        k1Var.b();
        j0 j0Var = new j0();
        j0Var.f1605b = this;
        this.f1523m = j0Var;
        this.f1524n = new u0.r();
        j0 j0Var2 = new j0(this);
        this.f1525o = j0Var2;
        j0Var2.b(attributeSet, R.attr.editTextStyle);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = isFocusable();
        boolean zIsClickable = isClickable();
        boolean zIsLongClickable = isLongClickable();
        int inputType = getInputType();
        KeyListener keyListenerA = j0Var2.a(keyListener);
        if (keyListenerA == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerA);
        setRawInputType(inputType);
        setFocusable(zIsFocusable);
        setClickable(zIsClickable);
        setLongClickable(zIsLongClickable);
    }

    private b0 getSuperCaller() {
        if (this.f1526p == null) {
            this.f1526p = new b0(this);
        }
        return this.f1526p;
    }

    @Override // androidx.core.view.t0
    public final androidx.core.view.n a(androidx.core.view.n nVar) {
        return this.f1524n.a(this, nVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1521i;
        if (uVar != null) {
            uVar.i();
        }
        k1 k1Var = this.f1522l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return m2.f0.W(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1521i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1521i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1522l.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1522l.e();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        j0 j0Var;
        if (Build.VERSION.SDK_INT >= 28 || (j0Var = this.f1523m) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) j0Var.f1606c;
        return textClassifier == null ? d1.a((TextView) j0Var.f1605b) : textClassifier;
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] strArrD;
        InputConnection eVar;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1522l.getClass();
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 30 && inputConnectionOnCreateInputConnection != null) {
            androidx.core.view.inputmethod.b.c(editorInfo, getText());
        }
        k2.c.x(inputConnectionOnCreateInputConnection, editorInfo, this);
        if (inputConnectionOnCreateInputConnection != null && i10 <= 30 && (strArrD = androidx.core.view.b2.d(this)) != null) {
            androidx.core.view.inputmethod.b.b(editorInfo, strArrD);
            androidx.core.view.inputmethod.c cVar = new androidx.core.view.inputmethod.c(this);
            if (i10 >= 25) {
                eVar = new androidx.core.view.inputmethod.d(inputConnectionOnCreateInputConnection, cVar);
            } else if (androidx.core.view.inputmethod.b.a(editorInfo).length != 0) {
                eVar = new androidx.core.view.inputmethod.e(inputConnectionOnCreateInputConnection, cVar);
            }
            inputConnectionOnCreateInputConnection = eVar;
        }
        return this.f1525o.c(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        int i10 = Build.VERSION.SDK_INT;
        boolean zA = false;
        if (i10 < 31 && i10 >= 24 && dragEvent.getLocalState() == null && androidx.core.view.b2.d(this) != null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                }
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (activity == null) {
                Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + this);
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                zA = m0.a(dragEvent, this, activity);
            }
        }
        if (zA) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i10) {
        if (Build.VERSION.SDK_INT >= 31 || androidx.core.view.b2.d(this) == null || !(i10 == 16908322 || i10 == 16908337)) {
            return super.onTextContextMenuItem(i10);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            androidx.core.view.f fVar = new androidx.core.view.f(primaryClip, 1);
            fVar.d(i10 == 16908322 ? 0 : 1);
            androidx.core.view.b2.f(this, fVar.a());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1521i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1521i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1522l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        k1 k1Var = this.f1522l;
        if (k1Var != null) {
            k1Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(m2.f0.X(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        this.f1525o.d(z);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f1525o.a(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1521i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1521i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        k1 k1Var = this.f1522l;
        k1Var.k(colorStateList);
        k1Var.b();
    }

    @Override // u0.u
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        k1 k1Var = this.f1522l;
        k1Var.l(mode);
        k1Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i10) {
        super.setTextAppearance(context, i10);
        k1 k1Var = this.f1522l;
        if (k1Var != null) {
            k1Var.g(context, i10);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        j0 j0Var;
        if (Build.VERSION.SDK_INT >= 28 || (j0Var = this.f1523m) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            j0Var.f1606c = textClassifier;
        }
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : getEditableText();
    }
}
