package androidx.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.app.q1;
import androidx.core.app.r1;
import androidx.core.app.s1;
import androidx.core.app.t1;
import androidx.core.app.x1;
import androidx.lifecycle.e1;
import androidx.lifecycle.g1;
import androidx.lifecycle.i1;
import androidx.lifecycle.j1;
import androidx.lifecycle.t0;
import androidx.lifecycle.u0;
import androidx.lifecycle.z0;
import com.arvio.tv.R;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.q0;

/* JADX INFO: loaded from: classes.dex */
public abstract class t extends androidx.core.app.m implements f.a, j1, androidx.lifecycle.k, b2.g, n0, androidx.activity.result.l, androidx.activity.result.c, androidx.core.content.k, androidx.core.content.l, r1, q1, s1, t1, androidx.core.view.c0, x {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private static final k Companion = new k();
    private i1 _viewModelStore;
    private final androidx.activity.result.k activityResultRegistry;
    private int contentLayoutId;
    private final f.b contextAwareHelper;
    private final x6.s defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final x6.s fullyDrawnReporter$delegate;
    private final androidx.core.view.f0 menuHostHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final x6.s onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList<p0.a<Configuration>> onConfigurationChangedListeners;
    private final CopyOnWriteArrayList<p0.a<androidx.core.app.s>> onMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<p0.a<Intent>> onNewIntentListeners;
    private final CopyOnWriteArrayList<p0.a<x1>> onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<p0.a<Integer>> onTrimMemoryListeners;
    private final CopyOnWriteArrayList<Runnable> onUserLeaveHintListeners;
    private final m reportFullyDrawnExecutor;
    private final b2.f savedStateRegistryController;

    public t() {
        this.contextAwareHelper = new f.b();
        this.menuHostHelper = new androidx.core.view.f0(new e(this, 0));
        b2.f fVar = new b2.f(this);
        this.savedStateRegistryController = fVar;
        this.reportFullyDrawnExecutor = new o(this);
        this.fullyDrawnReporter$delegate = new x6.i0(new r(this, 2));
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityResultRegistry = new q(this);
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.onNewIntentListeners = new CopyOnWriteArrayList<>();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList<>();
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        final int i10 = 0;
        getLifecycle().addObserver(new androidx.lifecycle.w(this) { // from class: androidx.activity.f

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ t f939l;

            {
                this.f939l = this;
            }

            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
                Window window;
                View viewPeekDecorView;
                switch (i10) {
                    case 0:
                        if (pVar == androidx.lifecycle.p.ON_STOP && (window = this.f939l.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        t.b(this.f939l, pVar);
                        break;
                }
            }
        });
        final int i11 = 1;
        getLifecycle().addObserver(new androidx.lifecycle.w(this) { // from class: androidx.activity.f

            /* JADX INFO: renamed from: l, reason: collision with root package name */
            public final /* synthetic */ t f939l;

            {
                this.f939l = this;
            }

            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
                Window window;
                View viewPeekDecorView;
                switch (i11) {
                    case 0:
                        if (pVar == androidx.lifecycle.p.ON_STOP && (window = this.f939l.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        t.b(this.f939l, pVar);
                        break;
                }
            }
        });
        getLifecycle().addObserver(new b2.b(this, i11));
        fVar.a();
        z0.d(this);
        if (Build.VERSION.SDK_INT <= 23) {
            getLifecycle().addObserver(new c0(this));
        }
        getSavedStateRegistry().c(ACTIVITY_RESULT_TAG, new g(this, 0));
        addOnContextAvailableListener(new f.c() { // from class: androidx.activity.h
            @Override // f.c
            public final void onContextAvailable(Context context) {
                t.a(this.f946a);
            }
        });
        this.defaultViewModelProviderFactory$delegate = new x6.i0(new r(this, 0));
        this.onBackPressedDispatcher$delegate = new x6.i0(new r(this, 3));
    }

    public static void a(t tVar) {
        Bundle bundleA = tVar.getSavedStateRegistry().a(ACTIVITY_RESULT_TAG);
        if (bundleA != null) {
            androidx.activity.result.k kVar = tVar.activityResultRegistry;
            LinkedHashMap linkedHashMap = kVar.f1006b;
            LinkedHashMap linkedHashMap2 = kVar.f1005a;
            Bundle bundle = kVar.f1011g;
            ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            ArrayList<String> stringArrayList2 = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            if (stringArrayList2 != null) {
                kVar.f1008d.addAll(stringArrayList2);
            }
            Bundle bundle2 = bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            if (bundle2 != null) {
                bundle.putAll(bundle2);
            }
            int size = stringArrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                String str = stringArrayList.get(i10);
                if (linkedHashMap.containsKey(str)) {
                    Integer num = (Integer) linkedHashMap.remove(str);
                    if (!bundle.containsKey(str)) {
                        q0.c(linkedHashMap2).remove(num);
                    }
                }
                int iIntValue = integerArrayList.get(i10).intValue();
                String str2 = stringArrayList.get(i10);
                linkedHashMap2.put(Integer.valueOf(iIntValue), str2);
                kVar.f1006b.put(str2, Integer.valueOf(iIntValue));
            }
        }
    }

    public static final void access$ensureViewModelStore(t tVar) {
        if (tVar._viewModelStore == null) {
            l lVar = (l) tVar.getLastNonConfigurationInstance();
            if (lVar != null) {
                tVar._viewModelStore = lVar.f962b;
            }
            if (tVar._viewModelStore == null) {
                tVar._viewModelStore = new i1();
            }
        }
    }

    public static void b(t tVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_DESTROY) {
            tVar.contextAwareHelper.f15199b = null;
            if (!tVar.isChangingConfigurations()) {
                tVar.getViewModelStore().a();
            }
            tVar.reportFullyDrawnExecutor.j();
        }
    }

    public static Bundle c(t tVar) {
        Bundle bundle = new Bundle();
        androidx.activity.result.k kVar = tVar.activityResultRegistry;
        kVar.getClass();
        LinkedHashMap linkedHashMap = kVar.f1006b;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(linkedHashMap.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(linkedHashMap.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(kVar.f1008d));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(kVar.f1011g));
        return bundle;
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        this.reportFullyDrawnExecutor.O(getWindow().getDecorView());
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.core.view.c0
    public void addMenuProvider(androidx.core.view.i0 i0Var) {
        androidx.core.view.f0 f0Var = this.menuHostHelper;
        f0Var.f2230b.add(i0Var);
        f0Var.f2229a.run();
    }

    @Override // androidx.core.content.k
    public final void addOnConfigurationChangedListener(p0.a<Configuration> aVar) {
        this.onConfigurationChangedListeners.add(aVar);
    }

    public final void addOnContextAvailableListener(f.c cVar) {
        f.b bVar = this.contextAwareHelper;
        t tVar = bVar.f15199b;
        if (tVar != null) {
            cVar.onContextAvailable(tVar);
        }
        bVar.f15198a.add(cVar);
    }

    @Override // androidx.core.app.q1
    public final void addOnMultiWindowModeChangedListener(p0.a<androidx.core.app.s> aVar) {
        this.onMultiWindowModeChangedListeners.add(aVar);
    }

    public final void addOnNewIntentListener(p0.a<Intent> aVar) {
        this.onNewIntentListeners.add(aVar);
    }

    @Override // androidx.core.app.s1
    public final void addOnPictureInPictureModeChangedListener(p0.a<x1> aVar) {
        this.onPictureInPictureModeChangedListeners.add(aVar);
    }

    @Override // androidx.core.content.l
    public final void addOnTrimMemoryListener(p0.a<Integer> aVar) {
        this.onTrimMemoryListeners.add(aVar);
    }

    public final void addOnUserLeaveHintListener(Runnable runnable) {
        this.onUserLeaveHintListeners.add(runnable);
    }

    @Override // androidx.activity.result.l
    public final androidx.activity.result.k getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    @Override // androidx.lifecycle.k
    public o1.c getDefaultViewModelCreationExtras() {
        o1.d dVar = new o1.d(0);
        if (getApplication() != null) {
            dVar.b(e1.f3225a, getApplication());
        }
        dVar.b(z0.f3316a, this);
        dVar.b(z0.f3317b, this);
        Intent intent = getIntent();
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (extras != null) {
            dVar.b(z0.f3318c, extras);
        }
        return dVar;
    }

    @Override // androidx.lifecycle.k
    public g1 getDefaultViewModelProviderFactory() {
        return (g1) this.defaultViewModelProviderFactory$delegate.getValue();
    }

    @Override // androidx.activity.x
    public w getFullyDrawnReporter() {
        return (w) this.fullyDrawnReporter$delegate.getValue();
    }

    @x6.e
    public Object getLastCustomNonConfigurationInstance() {
        l lVar = (l) getLastNonConfigurationInstance();
        if (lVar != null) {
            return lVar.f961a;
        }
        return null;
    }

    @Override // androidx.core.app.m, androidx.lifecycle.y
    public androidx.lifecycle.r getLifecycle() {
        return super.getLifecycle();
    }

    @Override // androidx.activity.n0
    public final l0 getOnBackPressedDispatcher() {
        return (l0) this.onBackPressedDispatcher$delegate.getValue();
    }

    @Override // b2.g
    public final b2.e getSavedStateRegistry() {
        return this.savedStateRegistryController.f7121b;
    }

    @Override // androidx.lifecycle.j1
    public i1 getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this._viewModelStore == null) {
            l lVar = (l) getLastNonConfigurationInstance();
            if (lVar != null) {
                this._viewModelStore = lVar.f962b;
            }
            if (this._viewModelStore == null) {
                this._viewModelStore = new i1();
            }
        }
        return this._viewModelStore;
    }

    public void initializeViewTreeOwners() {
        z0.i(getWindow().getDecorView(), this);
        z0.j(getWindow().getDecorView(), this);
        f4.f.w(getWindow().getDecorView(), this);
        getWindow().getDecorView().setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        getWindow().getDecorView().setTag(R.id.report_drawn, this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    @x6.e
    public void onActivityResult(int i10, int i11, Intent intent) {
        if (this.activityResultRegistry.a(i10, i11, intent)) {
            return;
        }
        super.onActivityResult(i10, i11, intent);
    }

    @Override // android.app.Activity
    @x6.e
    public void onBackPressed() {
        getOnBackPressedDispatcher().c();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<p0.a<Configuration>> it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    @Override // androidx.core.app.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.savedStateRegistryController.b(bundle);
        f.b bVar = this.contextAwareHelper;
        bVar.f15199b = this;
        Iterator it = bVar.f15198a.iterator();
        while (it.hasNext()) {
            ((f.c) it.next()).onContextAvailable(this);
        }
        super.onCreate(bundle);
        int i10 = t0.f3298l;
        u0.b(this);
        int i11 = this.contentLayoutId;
        if (i11 != 0) {
            setContentView(i11);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i10, Menu menu) {
        if (i10 != 0) {
            return true;
        }
        super.onCreatePanelMenu(i10, menu);
        androidx.core.view.f0 f0Var = this.menuHostHelper;
        MenuInflater menuInflater = getMenuInflater();
        Iterator it = f0Var.f2230b.iterator();
        while (it.hasNext()) {
            ((androidx.core.view.i0) it.next()).d(menu, menuInflater);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i10, MenuItem menuItem) {
        if (super.onMenuItemSelected(i10, menuItem)) {
            return true;
        }
        if (i10 == 0) {
            Iterator it = this.menuHostHelper.f2230b.iterator();
            while (it.hasNext()) {
                if (((androidx.core.view.i0) it.next()).c(menuItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.app.Activity
    @x6.e
    public void onMultiWindowModeChanged(boolean z) {
        if (this.dispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator<p0.a<androidx.core.app.s>> it = this.onMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new androidx.core.app.s(z));
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator<p0.a<Intent>> it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i10, Menu menu) {
        Iterator it = this.menuHostHelper.f2230b.iterator();
        while (it.hasNext()) {
            ((androidx.core.view.i0) it.next()).a(menu);
        }
        super.onPanelClosed(i10, menu);
    }

    @Override // android.app.Activity
    @x6.e
    public void onPictureInPictureModeChanged(boolean z) {
        if (this.dispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator<p0.a<x1>> it = this.onPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new x1(z));
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i10, View view, Menu menu) {
        if (i10 != 0) {
            return true;
        }
        super.onPreparePanel(i10, view, menu);
        Iterator it = this.menuHostHelper.f2230b.iterator();
        while (it.hasNext()) {
            ((androidx.core.view.i0) it.next()).b(menu);
        }
        return true;
    }

    @Override // android.app.Activity
    @x6.e
    public void onRequestPermissionsResult(int i10, String[] strArr, int[] iArr) {
        if (this.activityResultRegistry.a(i10, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i10, strArr, iArr);
    }

    @x6.e
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        l lVar;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        i1 i1Var = this._viewModelStore;
        if (i1Var == null && (lVar = (l) getLastNonConfigurationInstance()) != null) {
            i1Var = lVar.f962b;
        }
        if (i1Var == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        l lVar2 = new l();
        lVar2.f961a = objOnRetainCustomNonConfigurationInstance;
        lVar2.f962b = i1Var;
        return lVar2;
    }

    @Override // androidx.core.app.m, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        if (getLifecycle() instanceof androidx.lifecycle.a0) {
            ((androidx.lifecycle.a0) getLifecycle()).e(androidx.lifecycle.q.f3268m);
        }
        super.onSaveInstanceState(bundle);
        this.savedStateRegistryController.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i10) {
        super.onTrimMemory(i10);
        Iterator<p0.a<Integer>> it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i10));
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator<Runnable> it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public Context peekAvailableContext() {
        return this.contextAwareHelper.f15199b;
    }

    public final <I, O> androidx.activity.result.d registerForActivityResult(g.a aVar, androidx.activity.result.k kVar, androidx.activity.result.b<O> bVar) {
        return kVar.c("activity_rq#" + this.nextLocalRequestCode.getAndIncrement(), this, aVar, bVar);
    }

    @Override // androidx.core.view.c0
    public void removeMenuProvider(androidx.core.view.i0 i0Var) {
        this.menuHostHelper.a(i0Var);
    }

    @Override // androidx.core.content.k
    public final void removeOnConfigurationChangedListener(p0.a<Configuration> aVar) {
        this.onConfigurationChangedListeners.remove(aVar);
    }

    public final void removeOnContextAvailableListener(f.c cVar) {
        this.contextAwareHelper.f15198a.remove(cVar);
    }

    @Override // androidx.core.app.q1
    public final void removeOnMultiWindowModeChangedListener(p0.a<androidx.core.app.s> aVar) {
        this.onMultiWindowModeChangedListeners.remove(aVar);
    }

    public final void removeOnNewIntentListener(p0.a<Intent> aVar) {
        this.onNewIntentListeners.remove(aVar);
    }

    @Override // androidx.core.app.s1
    public final void removeOnPictureInPictureModeChangedListener(p0.a<x1> aVar) {
        this.onPictureInPictureModeChangedListeners.remove(aVar);
    }

    @Override // androidx.core.content.l
    public final void removeOnTrimMemoryListener(p0.a<Integer> aVar) {
        this.onTrimMemoryListeners.remove(aVar);
    }

    public final void removeOnUserLeaveHintListener(Runnable runnable) {
        this.onUserLeaveHintListeners.remove(runnable);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (r0.r()) {
                r0.b("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            getFullyDrawnReporter().b();
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i10) {
        initializeViewTreeOwners();
        this.reportFullyDrawnExecutor.O(getWindow().getDecorView());
        super.setContentView(i10);
    }

    @Override // android.app.Activity
    @x6.e
    public void startActivityForResult(Intent intent, int i10) {
        super.startActivityForResult(intent, i10);
    }

    @Override // android.app.Activity
    @x6.e
    public void startIntentSenderForResult(IntentSender intentSender, int i10, Intent intent, int i11, int i12, int i13) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i10, intent, i11, i12, i13);
    }

    @Override // android.app.Activity
    @x6.e
    public void startActivityForResult(Intent intent, int i10, Bundle bundle) {
        super.startActivityForResult(intent, i10, bundle);
    }

    @Override // android.app.Activity
    @x6.e
    public void startIntentSenderForResult(IntentSender intentSender, int i10, Intent intent, int i11, int i12, int i13, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i10, intent, i11, i12, i13, bundle);
    }

    public final <I, O> androidx.activity.result.d registerForActivityResult(g.a aVar, androidx.activity.result.b<O> bVar) {
        return registerForActivityResult(aVar, this.activityResultRegistry, bVar);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator<p0.a<androidx.core.app.s>> it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new androidx.core.app.s(z));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator<p0.a<x1>> it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new x1(z));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        this.reportFullyDrawnExecutor.O(getWindow().getDecorView());
        super.setContentView(view);
    }

    public void addMenuProvider(androidx.core.view.i0 i0Var, androidx.lifecycle.y yVar) {
        androidx.core.view.f0 f0Var = this.menuHostHelper;
        f0Var.f2230b.add(i0Var);
        f0Var.f2229a.run();
        androidx.lifecycle.r lifecycle = yVar.getLifecycle();
        HashMap map = f0Var.f2231c;
        androidx.core.view.e0 e0Var = (androidx.core.view.e0) map.remove(i0Var);
        if (e0Var != null) {
            e0Var.f2220a.removeObserver(e0Var.f2221b);
            e0Var.f2221b = null;
        }
        map.put(i0Var, new androidx.core.view.e0(lifecycle, new i(f0Var, i0Var, 1)));
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        this.reportFullyDrawnExecutor.O(getWindow().getDecorView());
        super.setContentView(view, layoutParams);
    }

    public void addMenuProvider(androidx.core.view.i0 i0Var, androidx.lifecycle.y yVar, androidx.lifecycle.q qVar) {
        androidx.core.view.f0 f0Var = this.menuHostHelper;
        f0Var.getClass();
        androidx.lifecycle.r lifecycle = yVar.getLifecycle();
        HashMap map = f0Var.f2231c;
        androidx.core.view.e0 e0Var = (androidx.core.view.e0) map.remove(i0Var);
        if (e0Var != null) {
            e0Var.f2220a.removeObserver(e0Var.f2221b);
            e0Var.f2221b = null;
        }
        map.put(i0Var, new androidx.core.view.e0(lifecycle, new androidx.core.view.d0(f0Var, qVar, i0Var, 0)));
    }

    public t(int i10) {
        this();
        this.contentLayoutId = i10;
    }
}
