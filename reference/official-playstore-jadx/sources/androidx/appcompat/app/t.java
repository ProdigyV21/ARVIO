package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.v4;
import androidx.core.app.e2;
import androidx.core.app.f2;

/* JADX INFO: loaded from: classes.dex */
public abstract class t extends androidx.fragment.app.h0 implements u, e2, h {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private z mDelegate;
    private Resources mResources;

    @Override // androidx.activity.t, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e();
        s0 s0Var = (s0) getDelegate();
        s0Var.x();
        ((ViewGroup) s0Var.K.findViewById(R.id.content)).addView(view, layoutParams);
        s0Var.f1191w.a(s0Var.f1190v.getCallback());
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a7  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void attachBaseContext(android.content.Context r10) {
        /*
            Method dump skipped, instruction units count: 483
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.t.attachBaseContext(android.content.Context):void");
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        f supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.e()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.m, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        f supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.n(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void e() {
        androidx.lifecycle.z0.i(getWindow().getDecorView(), this);
        androidx.lifecycle.z0.j(getWindow().getDecorView(), this);
        f4.f.w(getWindow().getDecorView(), this);
        getWindow().getDecorView().setTag(com.arvio.tv.R.id.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i10) {
        s0 s0Var = (s0) getDelegate();
        s0Var.x();
        return (T) s0Var.f1190v.findViewById(i10);
    }

    public z getDelegate() {
        if (this.mDelegate == null) {
            z0 z0Var = z.f1211i;
            this.mDelegate = new s0(this, null, this, this);
        }
        return this.mDelegate;
    }

    public g getDrawerToggleDelegate() {
        ((s0) getDelegate()).getClass();
        return new a1.a();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        s0 s0Var = (s0) getDelegate();
        if (s0Var.z == null) {
            s0Var.C();
            f fVar = s0Var.f1192y;
            s0Var.z = new n.j(fVar != null ? fVar.i() : s0Var.f1189u);
        }
        return s0Var.z;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.mResources;
        if (resources == null) {
            int i10 = v4.f1801a;
        }
        return resources == null ? super.getResources() : resources;
    }

    public f getSupportActionBar() {
        s0 s0Var = (s0) getDelegate();
        s0Var.C();
        return s0Var.f1192y;
    }

    @Override // androidx.core.app.e2
    public Intent getSupportParentActivityIntent() {
        return m2.f0.v(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().c();
    }

    @Override // androidx.activity.t, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) throws IllegalAccessException {
        super.onConfigurationChanged(configuration);
        s0 s0Var = (s0) getDelegate();
        if (s0Var.P && s0Var.J) {
            s0Var.C();
            f fVar = s0Var.f1192y;
            if (fVar != null) {
                fVar.k();
            }
        }
        androidx.appcompat.widget.a0 a0VarA = androidx.appcompat.widget.a0.a();
        Context context = s0Var.f1189u;
        synchronized (a0VarA) {
            a0VarA.f1504a.k(context);
        }
        s0Var.b0 = new Configuration(s0Var.f1189u.getResources().getConfiguration());
        s0Var.o(false, false);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(f2 f2Var) {
        f2Var.getClass();
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = m2.f0.v(this);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(f2Var.f1986l.getPackageManager());
            }
            f2Var.a(component);
            f2Var.f1985i.add(supportParentActivityIntent);
        }
    }

    @Override // androidx.fragment.app.h0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().g();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i10, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return super.onKeyDown(i10, keyEvent);
        }
        return true;
    }

    public void onLocalesChanged(androidx.core.os.m mVar) {
    }

    @Override // androidx.fragment.app.h0, androidx.activity.t, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i10, MenuItem menuItem) {
        if (super.onMenuItemSelected(i10, menuItem)) {
            return true;
        }
        f supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.h() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i10, Menu menu) {
        return super.onMenuOpened(i10, menu);
    }

    public void onNightModeChanged(int i10) {
    }

    @Override // androidx.activity.t, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i10, Menu menu) {
        super.onPanelClosed(i10, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((s0) getDelegate()).x();
    }

    @Override // androidx.fragment.app.h0, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        s0 s0Var = (s0) getDelegate();
        s0Var.C();
        f fVar = s0Var.f1192y;
        if (fVar != null) {
            fVar.s(true);
        }
    }

    public void onPrepareSupportNavigateUpTaskStack(f2 f2Var) {
    }

    @Override // androidx.fragment.app.h0, android.app.Activity
    public void onStart() throws IllegalAccessException {
        super.onStart();
        ((s0) getDelegate()).o(true, false);
    }

    @Override // androidx.fragment.app.h0, android.app.Activity
    public void onStop() {
        super.onStop();
        s0 s0Var = (s0) getDelegate();
        s0Var.C();
        f fVar = s0Var.f1192y;
        if (fVar != null) {
            fVar.s(false);
        }
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        f2 f2Var = new f2(this);
        onCreateSupportNavigateUpTaskStack(f2Var);
        onPrepareSupportNavigateUpTaskStack(f2Var);
        f2Var.b();
        try {
            finishAffinity();
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i10) {
        super.onTitleChanged(charSequence, i10);
        getDelegate().m(charSequence);
    }

    @Override // androidx.appcompat.app.u
    public n.b onWindowStartingSupportActionMode(n.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        f supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.o()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.t, android.app.Activity
    public void setContentView(int i10) {
        e();
        getDelegate().j(i10);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        s0 s0Var = (s0) getDelegate();
        if (s0Var.f1188t instanceof Activity) {
            s0Var.C();
            f fVar = s0Var.f1192y;
            if (fVar instanceof l1) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            s0Var.z = null;
            if (fVar != null) {
                fVar.l();
            }
            s0Var.f1192y = null;
            if (toolbar != null) {
                Object obj = s0Var.f1188t;
                f1 f1Var = new f1(toolbar, obj instanceof Activity ? ((Activity) obj).getTitle() : s0Var.A, s0Var.f1191w);
                s0Var.f1192y = f1Var;
                s0Var.f1191w.f1087l = f1Var.f1044c;
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                s0Var.f1191w.f1087l = null;
            }
            s0Var.c();
        }
    }

    @Deprecated
    public void setSupportProgress(int i10) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z) {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i10) {
        super.setTheme(i10);
        ((s0) getDelegate()).f1174d0 = i10;
    }

    public n.b startSupportActionMode(n.a aVar) {
        return getDelegate().n(aVar);
    }

    @Override // androidx.fragment.app.h0
    public void supportInvalidateOptionsMenu() {
        getDelegate().c();
    }

    public void supportNavigateUpTo(Intent intent) {
        navigateUpTo(intent);
    }

    public boolean supportRequestWindowFeature(int i10) {
        return getDelegate().i(i10);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return shouldUpRecreateTask(intent);
    }

    @Override // androidx.activity.t, android.app.Activity
    public void setContentView(View view) {
        e();
        getDelegate().k(view);
    }

    @Override // androidx.activity.t, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        e();
        getDelegate().l(view, layoutParams);
    }

    @Override // androidx.appcompat.app.u
    public void onSupportActionModeFinished(n.b bVar) {
    }

    @Override // androidx.appcompat.app.u
    public void onSupportActionModeStarted(n.b bVar) {
    }
}
