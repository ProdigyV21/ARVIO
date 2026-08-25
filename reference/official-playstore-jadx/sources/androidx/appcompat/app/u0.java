package androidx.appcompat.app;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class u0 extends androidx.activity.u implements u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public s0 f1194i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final t0 f1195l;

    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.appcompat.app.t0] */
    public u0(ContextThemeWrapper contextThemeWrapper, int i10) {
        int i11;
        if (i10 == 0) {
            TypedValue typedValue = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
            i11 = typedValue.resourceId;
        } else {
            i11 = i10;
        }
        super(contextThemeWrapper, i11);
        this.f1195l = new androidx.core.view.a0() { // from class: androidx.appcompat.app.t0
            @Override // androidx.core.view.a0
            public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return this.f1193i.c(keyEvent);
            }
        };
        z zVarB = b();
        if (i10 == 0) {
            TypedValue typedValue2 = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue2, true);
            i10 = typedValue2.resourceId;
        }
        ((s0) zVarB).f1174d0 = i10;
        zVarB.e();
    }

    @Override // androidx.activity.u, android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        s0 s0Var = (s0) b();
        s0Var.x();
        ((ViewGroup) s0Var.K.findViewById(android.R.id.content)).addView(view, layoutParams);
        s0Var.f1191w.a(s0Var.f1190v.getCallback());
    }

    public final z b() {
        if (this.f1194i == null) {
            z0 z0Var = z.f1211i;
            this.f1194i = new s0(getContext(), getWindow(), this, this);
        }
        return this.f1194i;
    }

    public final boolean c(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        b().g();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return androidx.work.impl.t.n(this.f1195l, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public final View findViewById(int i10) {
        s0 s0Var = (s0) b();
        s0Var.x();
        return s0Var.f1190v.findViewById(i10);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        b().c();
    }

    @Override // androidx.activity.u, android.app.Dialog
    public void onCreate(Bundle bundle) {
        b().a();
        super.onCreate(bundle);
        b().e();
    }

    @Override // androidx.activity.u, android.app.Dialog
    public final void onStop() {
        super.onStop();
        s0 s0Var = (s0) b();
        s0Var.C();
        f fVar = s0Var.f1192y;
        if (fVar != null) {
            fVar.s(false);
        }
    }

    @Override // androidx.appcompat.app.u
    public final n.b onWindowStartingSupportActionMode(n.a aVar) {
        return null;
    }

    @Override // androidx.activity.u, android.app.Dialog
    public final void setContentView(int i10) {
        b().j(i10);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        b().m(charSequence);
    }

    @Override // androidx.activity.u, android.app.Dialog
    public final void setContentView(View view) {
        b().k(view);
    }

    @Override // androidx.activity.u, android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        b().l(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i10) {
        super.setTitle(i10);
        b().m(getContext().getString(i10));
    }

    @Override // androidx.appcompat.app.u
    public final void onSupportActionModeFinished(n.b bVar) {
    }

    @Override // androidx.appcompat.app.u
    public final void onSupportActionModeStarted(n.b bVar) {
    }
}
