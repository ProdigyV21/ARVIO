package androidx.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.z0;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public class u extends Dialog implements androidx.lifecycle.y, n0, b2.g {
    private androidx.lifecycle.a0 _lifecycleRegistry;
    private final l0 onBackPressedDispatcher;
    private final b2.f savedStateRegistryController;

    public u(Context context, int i10) {
        super(context, i10);
        this.savedStateRegistryController = new b2.f(this);
        this.onBackPressedDispatcher = new l0(new n(this, 1));
    }

    public static void a(u uVar) {
        super.onBackPressed();
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.lifecycle.y
    public androidx.lifecycle.r getLifecycle() {
        androidx.lifecycle.a0 a0Var = this._lifecycleRegistry;
        if (a0Var != null) {
            return a0Var;
        }
        androidx.lifecycle.a0 a0Var2 = new androidx.lifecycle.a0(this);
        this._lifecycleRegistry = a0Var2;
        return a0Var2;
    }

    @Override // androidx.activity.n0
    public final l0 getOnBackPressedDispatcher() {
        return this.onBackPressedDispatcher;
    }

    @Override // b2.g
    public b2.e getSavedStateRegistry() {
        return this.savedStateRegistryController.f7121b;
    }

    public void initializeViewTreeOwners() {
        z0.i(getWindow().getDecorView(), this);
        getWindow().getDecorView().setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        f4.f.w(getWindow().getDecorView(), this);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        this.onBackPressedDispatcher.c();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            l0 l0Var = this.onBackPressedDispatcher;
            l0Var.f967e = getOnBackInvokedDispatcher();
            l0Var.d(l0Var.f969g);
        }
        this.savedStateRegistryController.b(bundle);
        androidx.lifecycle.a0 a0Var = this._lifecycleRegistry;
        if (a0Var == null) {
            a0Var = new androidx.lifecycle.a0(this);
            this._lifecycleRegistry = a0Var;
        }
        a0Var.c(androidx.lifecycle.p.ON_CREATE);
    }

    @Override // android.app.Dialog
    public Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        this.savedStateRegistryController.c(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        androidx.lifecycle.a0 a0Var = this._lifecycleRegistry;
        if (a0Var == null) {
            a0Var = new androidx.lifecycle.a0(this);
            this._lifecycleRegistry = a0Var;
        }
        a0Var.c(androidx.lifecycle.p.ON_RESUME);
    }

    @Override // android.app.Dialog
    public void onStop() {
        androidx.lifecycle.a0 a0Var = this._lifecycleRegistry;
        if (a0Var == null) {
            a0Var = new androidx.lifecycle.a0(this);
            this._lifecycleRegistry = a0Var;
        }
        a0Var.c(androidx.lifecycle.p.ON_DESTROY);
        this._lifecycleRegistry = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public void setContentView(int i10) {
        initializeViewTreeOwners();
        super.setContentView(i10);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        initializeViewTreeOwners();
        super.setContentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        super.setContentView(view, layoutParams);
    }
}
