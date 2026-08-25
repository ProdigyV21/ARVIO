package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.core.app.d2;
import androidx.fragment.app.strictmode.GetRetainInstanceUsageViolation;
import androidx.fragment.app.strictmode.GetTargetFragmentRequestCodeUsageViolation;
import androidx.fragment.app.strictmode.GetTargetFragmentUsageViolation;
import androidx.fragment.app.strictmode.SetRetainInstanceUsageViolation;
import androidx.fragment.app.strictmode.SetTargetFragmentUsageViolation;
import androidx.fragment.app.strictmode.SetUserVisibleHintViolation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c0 implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.y, androidx.lifecycle.j1, androidx.lifecycle.k, b2.g, androidx.activity.result.c {
    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    y mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mBeingSaved;
    private boolean mCalled;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    androidx.lifecycle.g1 mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    b1 mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    m0 mHost;
    boolean mInLayout;
    boolean mIsCreated;
    LayoutInflater mLayoutInflater;
    androidx.lifecycle.a0 mLifecycleRegistry;
    c0 mParentFragment;
    boolean mPerformedCreateView;
    Handler mPostponedHandler;
    public String mPreviousWho;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    b2.f mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray<Parcelable> mSavedViewState;
    String mTag;
    c0 mTarget;
    int mTargetRequestCode;
    View mView;
    w1 mViewLifecycleOwner;
    int mState = -1;
    String mWho = UUID.randomUUID().toString();
    String mTargetWho = null;
    private Boolean mIsPrimaryNavigationFragment = null;
    b1 mChildFragmentManager = new c1();
    boolean mMenuVisible = true;
    boolean mUserVisibleHint = true;
    Runnable mPostponedDurationRunnable = new s(this, 0);
    androidx.lifecycle.q mMaxState = androidx.lifecycle.q.f3270o;
    androidx.lifecycle.j0 mViewLifecycleOwnerLiveData = new androidx.lifecycle.j0();
    private final AtomicInteger mNextLocalRequestCode = new AtomicInteger();
    private final ArrayList<z> mOnPreAttachedListeners = new ArrayList<>();
    private final z mSavedStateAttachListener = new t(this);

    public c0() {
        g();
    }

    @Deprecated
    public static c0 instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    public Activity c() {
        return getActivity();
    }

    public void callStartTransitionListener(boolean z) {
        ViewGroup viewGroup;
        b1 b1Var;
        y yVar = this.mAnimationInfo;
        if (yVar != null) {
            yVar.f2886s = false;
        }
        if (this.mView == null || (viewGroup = this.mContainer) == null || (b1Var = this.mFragmentManager) == null) {
            return;
        }
        n nVarI = n.i(viewGroup, b1Var.E());
        nVarI.j();
        if (z) {
            this.mHost.f2781m.post(new h(nVarI, 3));
        } else {
            nVarI.d();
        }
        Handler handler = this.mPostponedHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mPostponedDurationRunnable);
            this.mPostponedHandler = null;
        }
    }

    public j0 createFragmentContainer() {
        return new u(this);
    }

    public final y d() {
        if (this.mAnimationInfo == null) {
            y yVar = new y();
            yVar.f2877i = null;
            Object obj = USE_DEFAULT_TRANSITION;
            yVar.f2878j = obj;
            yVar.k = null;
            yVar.f2879l = obj;
            yVar.f2880m = null;
            yVar.f2881n = obj;
            yVar.f2884q = 1.0f;
            yVar.f2885r = null;
            this.mAnimationInfo = yVar;
        }
        return this.mAnimationInfo;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mSavedViewRegistryState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.mSavedViewRegistryState);
        }
        c0 c0VarF = f(false);
        if (c0VarF != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(c0VarF);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(getPopDirection());
        if (getEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(getEnterAnim());
        }
        if (getExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(getExitAnim());
        }
        if (getPopEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(getPopEnterAnim());
        }
        if (getPopExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(getPopExitAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
        }
        if (getContext() != null) {
            androidx.loader.app.b.a(this).c(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.u(androidx.compose.material3.d.m(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public final int e() {
        androidx.lifecycle.q qVar = this.mMaxState;
        return (qVar == androidx.lifecycle.q.f3267l || this.mParentFragment == null) ? qVar.ordinal() : Math.min(qVar.ordinal(), this.mParentFragment.e());
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final c0 f(boolean z) {
        String str;
        if (z) {
            h1.b bVar = h1.c.f15839a;
            h1.c.b(new GetTargetFragmentUsageViolation(this, "Attempting to get target fragment from fragment " + this));
            h1.c.a(this).getClass();
        }
        c0 c0Var = this.mTarget;
        if (c0Var != null) {
            return c0Var;
        }
        b1 b1Var = this.mFragmentManager;
        if (b1Var == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return b1Var.f2669c.b(str);
    }

    public c0 findFragmentByWho(String str) {
        return str.equals(this.mWho) ? this : this.mChildFragmentManager.f2669c.c(str);
    }

    public final void g() {
        this.mLifecycleRegistry = new androidx.lifecycle.a0(this);
        this.mSavedStateRegistryController = new b2.f(this);
        this.mDefaultFactory = null;
        if (this.mOnPreAttachedListeners.contains(this.mSavedStateAttachListener)) {
            return;
        }
        z zVar = this.mSavedStateAttachListener;
        if (this.mState >= 0) {
            zVar.a();
        } else {
            this.mOnPreAttachedListeners.add(zVar);
        }
    }

    public String generateActivityResultKey() {
        return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
    }

    public final h0 getActivity() {
        m0 m0Var = this.mHost;
        if (m0Var == null) {
            return null;
        }
        return m0Var.f2779i;
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        y yVar = this.mAnimationInfo;
        if (yVar == null || (bool = yVar.f2883p) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        y yVar = this.mAnimationInfo;
        if (yVar == null || (bool = yVar.f2882o) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        yVar.getClass();
        return null;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final b1 getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " has not been attached yet."));
    }

    public Context getContext() {
        m0 m0Var = this.mHost;
        if (m0Var == null) {
            return null;
        }
        return m0Var.f2780l;
    }

    @Override // androidx.lifecycle.k
    public o1.c getDefaultViewModelCreationExtras() {
        Application application;
        Context applicationContext = requireContext().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        if (application == null && b1.G(3)) {
            Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        }
        o1.d dVar = new o1.d(0);
        if (application != null) {
            dVar.b(androidx.lifecycle.e1.f3225a, application);
        }
        dVar.b(androidx.lifecycle.z0.f3316a, this);
        dVar.b(androidx.lifecycle.z0.f3317b, this);
        if (getArguments() != null) {
            dVar.b(androidx.lifecycle.z0.f3318c, getArguments());
        }
        return dVar;
    }

    @Override // androidx.lifecycle.k
    public androidx.lifecycle.g1 getDefaultViewModelProviderFactory() {
        Application application;
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.mDefaultFactory == null) {
            Context applicationContext = requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && b1.G(3)) {
                Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will need CreationExtras to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.mDefaultFactory = new androidx.lifecycle.b1(application, this, getArguments());
        }
        return this.mDefaultFactory;
    }

    public int getEnterAnim() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 0;
        }
        return yVar.f2870b;
    }

    public Object getEnterTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        return yVar.f2877i;
    }

    public d2 getEnterTransitionCallback() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        yVar.getClass();
        return null;
    }

    public int getExitAnim() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 0;
        }
        return yVar.f2871c;
    }

    public Object getExitTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        return yVar.k;
    }

    public d2 getExitTransitionCallback() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        yVar.getClass();
        return null;
    }

    public View getFocusedView() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        return yVar.f2885r;
    }

    @Deprecated
    public final b1 getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        m0 m0Var = this.mHost;
        if (m0Var == null) {
            return null;
        }
        return ((g0) m0Var).f2731o;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Override // androidx.lifecycle.y
    public androidx.lifecycle.r getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public androidx.loader.app.b getLoaderManager() {
        return androidx.loader.app.b.a(this);
    }

    public int getNextTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 0;
        }
        return yVar.f2874f;
    }

    public final c0 getParentFragment() {
        return this.mParentFragment;
    }

    public final b1 getParentFragmentManager() {
        b1 b1Var = this.mFragmentManager;
        if (b1Var != null) {
            return b1Var;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not associated with a fragment manager."));
    }

    public boolean getPopDirection() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return false;
        }
        return yVar.f2869a;
    }

    public int getPopEnterAnim() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 0;
        }
        return yVar.f2872d;
    }

    public int getPopExitAnim() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 0;
        }
        return yVar.f2873e;
    }

    public float getPostOnViewCreatedAlpha() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return 1.0f;
        }
        return yVar.f2884q;
    }

    public Object getReenterTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        Object obj = yVar.f2879l;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        h1.b bVar = h1.c.f15839a;
        h1.c.b(new GetRetainInstanceUsageViolation(this, "Attempting to get retain instance for fragment " + this));
        h1.c.a(this).getClass();
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        Object obj = yVar.f2878j;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    @Override // b2.g
    public final b2.e getSavedStateRegistry() {
        return this.mSavedStateRegistryController.f7121b;
    }

    public Object getSharedElementEnterTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        return yVar.f2880m;
    }

    public Object getSharedElementReturnTransition() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return null;
        }
        Object obj = yVar.f2881n;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    public ArrayList<String> getSharedElementSourceNames() {
        ArrayList<String> arrayList;
        y yVar = this.mAnimationInfo;
        return (yVar == null || (arrayList = yVar.f2875g) == null) ? new ArrayList<>() : arrayList;
    }

    public ArrayList<String> getSharedElementTargetNames() {
        ArrayList<String> arrayList;
        y yVar = this.mAnimationInfo;
        return (yVar == null || (arrayList = yVar.f2876h) == null) ? new ArrayList<>() : arrayList;
    }

    public final String getString(int i10) {
        return getResources().getString(i10);
    }

    public final String getTag() {
        return this.mTag;
    }

    @Deprecated
    public final c0 getTargetFragment() {
        return f(true);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        h1.b bVar = h1.c.f15839a;
        h1.c.b(new GetTargetFragmentRequestCodeUsageViolation(this, "Attempting to get target request code from fragment " + this));
        h1.c.a(this).getClass();
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i10) {
        return getResources().getText(i10);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public androidx.lifecycle.y getViewLifecycleOwner() {
        w1 w1Var = this.mViewLifecycleOwner;
        if (w1Var != null) {
            return w1Var;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public androidx.lifecycle.h0 getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.j1
    public androidx.lifecycle.i1 getViewModelStore() {
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (e() != 1) {
            return this.mFragmentManager.M.getViewModelStore(this);
        }
        throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
    }

    public final androidx.activity.result.i h(g.a aVar, q.a aVar2, androidx.activity.result.b bVar) {
        if (this.mState > 1) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate())."));
        }
        x xVar = new x(this, aVar2, new AtomicReference(), aVar, bVar);
        if (this.mState >= 0) {
            xVar.a();
        } else {
            this.mOnPreAttachedListeners.add(xVar);
        }
        return new androidx.activity.result.i();
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        g();
        this.mPreviousWho = this.mWho;
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new c1();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        if (this.mHidden) {
            return true;
        }
        b1 b1Var = this.mFragmentManager;
        if (b1Var != null) {
            c0 c0Var = this.mParentFragment;
            b1Var.getClass();
            if (c0Var == null ? false : c0Var.isHidden()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        if (!this.mMenuVisible) {
            return false;
        }
        if (this.mFragmentManager != null) {
            c0 c0Var = this.mParentFragment;
            if (!(c0Var == null ? true : c0Var.isMenuVisible())) {
                return false;
            }
        }
        return true;
    }

    public boolean isPostponed() {
        y yVar = this.mAnimationInfo;
        if (yVar == null) {
            return false;
        }
        return yVar.f2886s;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    public final boolean isStateSaved() {
        b1 b1Var = this.mFragmentManager;
        if (b1Var == null) {
            return false;
        }
        return b1Var.F || b1Var.G;
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.L();
    }

    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int i10, int i11, Intent intent) {
        if (b1.G(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i10 + " resultCode: " + i11 + " data: " + intent);
        }
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        m0 m0Var = this.mHost;
        h0 h0Var = m0Var == null ? null : m0Var.f2779i;
        if (h0Var != null) {
            this.mCalled = false;
            onAttach((Activity) h0Var);
        }
    }

    @Deprecated
    public void onAttachFragment(c0 c0Var) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        b1 b1Var = this.mChildFragmentManager;
        if (b1Var.f2685t >= 1) {
            return;
        }
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(1);
    }

    public Animation onCreateAnimation(int i10, boolean z, int i11) {
        return null;
    }

    public Animator onCreateAnimator(int i10, boolean z, int i11) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Deprecated
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i10 = this.mContentLayoutId;
        if (i10 != 0) {
            return layoutInflater.inflate(i10, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    @Deprecated
    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z) {
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        m0 m0Var = this.mHost;
        h0 h0Var = m0Var == null ? null : m0Var.f2779i;
        if (h0Var != null) {
            this.mCalled = false;
            onInflate((Activity) h0Var, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    @Deprecated
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    @Deprecated
    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    @Deprecated
    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i10, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.L();
        this.mState = 3;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onActivityCreated()"));
        }
        if (b1.G(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.mView != null) {
            restoreViewState(this.mSavedFragmentState);
        }
        this.mSavedFragmentState = null;
        b1 b1Var = this.mChildFragmentManager;
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(4);
    }

    public void performAttach() {
        Iterator<z> it = this.mOnPreAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.b(this.mHost, createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach((Context) this.mHost.f2780l);
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onAttach()"));
        }
        Iterator it2 = this.mFragmentManager.f2679n.iterator();
        while (it2.hasNext()) {
            ((g1) it2.next()).a(this);
        }
        b1 b1Var = this.mChildFragmentManager;
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(0);
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.i(menuItem);
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.L();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.addObserver(new v(this));
        this.mSavedStateRegistryController.b(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onCreate()"));
        }
        this.mLifecycleRegistry.c(androidx.lifecycle.p.ON_CREATE);
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z = true;
        }
        return this.mChildFragmentManager.j(menu, menuInflater) | z;
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.L();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new w1(this, getViewModelStore());
        View viewOnCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = viewOnCreateView;
        if (viewOnCreateView == null) {
            if (this.mViewLifecycleOwner.f2858n != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
        } else {
            this.mViewLifecycleOwner.b();
            androidx.lifecycle.z0.i(this.mView, this.mViewLifecycleOwner);
            androidx.lifecycle.z0.j(this.mView, this.mViewLifecycleOwner);
            f4.f.w(this.mView, this.mViewLifecycleOwner);
            this.mViewLifecycleOwnerLiveData.i(this.mViewLifecycleOwner);
        }
    }

    public void performDestroy() {
        this.mChildFragmentManager.k();
        this.mLifecycleRegistry.c(androidx.lifecycle.p.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onDestroy()"));
        }
    }

    public void performDestroyView() {
        this.mChildFragmentManager.t(1);
        if (this.mView != null) {
            w1 w1Var = this.mViewLifecycleOwner;
            w1Var.b();
            if (w1Var.f2858n.f3198c.a(androidx.lifecycle.q.f3268m)) {
                this.mViewLifecycleOwner.a(androidx.lifecycle.p.ON_DESTROY);
            }
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onDestroyView()"));
        }
        androidx.loader.app.b.a(this).d();
        this.mPerformedCreateView = false;
    }

    public void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onDetach()"));
        }
        b1 b1Var = this.mChildFragmentManager;
        if (b1Var.H) {
            return;
        }
        b1Var.k();
        this.mChildFragmentManager = new c1();
    }

    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = layoutInflaterOnGetLayoutInflater;
        return layoutInflaterOnGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
    }

    public void performMultiWindowModeChanged(boolean z) {
        onMultiWindowModeChanged(z);
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.o(menuItem);
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        this.mChildFragmentManager.p(menu);
    }

    public void performPause() {
        this.mChildFragmentManager.t(5);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.p.ON_PAUSE);
        }
        this.mLifecycleRegistry.c(androidx.lifecycle.p.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onPause()"));
        }
    }

    public void performPictureInPictureModeChanged(boolean z) {
        onPictureInPictureModeChanged(z);
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z = true;
        }
        return this.mChildFragmentManager.s(menu) | z;
    }

    public void performPrimaryNavigationFragmentChanged() {
        this.mFragmentManager.getClass();
        boolean zJ = b1.J(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != zJ) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(zJ);
            onPrimaryNavigationFragmentChanged(zJ);
            b1 b1Var = this.mChildFragmentManager;
            b1Var.a0();
            b1Var.q(b1Var.x);
        }
    }

    public void performResume() {
        this.mChildFragmentManager.L();
        this.mChildFragmentManager.x(true);
        this.mState = 7;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onResume()"));
        }
        androidx.lifecycle.a0 a0Var = this.mLifecycleRegistry;
        androidx.lifecycle.p pVar = androidx.lifecycle.p.ON_RESUME;
        a0Var.c(pVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(pVar);
        }
        b1 b1Var = this.mChildFragmentManager;
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(7);
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.c(bundle);
        bundle.putParcelable("android:support:fragments", this.mChildFragmentManager.S());
    }

    public void performStart() {
        this.mChildFragmentManager.L();
        this.mChildFragmentManager.x(true);
        this.mState = 5;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onStart()"));
        }
        androidx.lifecycle.a0 a0Var = this.mLifecycleRegistry;
        androidx.lifecycle.p pVar = androidx.lifecycle.p.ON_START;
        a0Var.c(pVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(pVar);
        }
        b1 b1Var = this.mChildFragmentManager;
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(5);
    }

    public void performStop() {
        b1 b1Var = this.mChildFragmentManager;
        b1Var.G = true;
        b1Var.M.setIsStateSaved(true);
        b1Var.t(4);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.p.ON_STOP);
        }
        this.mLifecycleRegistry.c(androidx.lifecycle.p.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onStop()"));
        }
    }

    public void performViewCreated() {
        onViewCreated(this.mView, this.mSavedFragmentState);
        this.mChildFragmentManager.t(2);
    }

    public void postponeEnterTransition() {
        d().f2886s = true;
    }

    public final <I, O> androidx.activity.result.d registerForActivityResult(g.a aVar, androidx.activity.result.b<O> bVar) {
        return h(aVar, new w(this, 0), bVar);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(String[] strArr, int i10) {
        if (this.mHost == null) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to Activity"));
        }
        b1 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.C == null) {
            parentFragmentManager.f2686u.getClass();
            return;
        }
        parentFragmentManager.D.addLast(new x0(this.mWho, i10));
        parentFragmentManager.C.a(strArr);
    }

    public final h0 requireActivity() {
        h0 activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to an activity."));
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " does not have any arguments."));
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to a context."));
    }

    @Deprecated
    public final b1 requireFragmentManager() {
        return getParentFragmentManager();
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to a host."));
    }

    public final c0 requireParentFragment() {
        c0 parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " is not attached to any Fragment or host"));
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.mChildFragmentManager.R(parcelable);
        b1 b1Var = this.mChildFragmentManager;
        b1Var.F = false;
        b1Var.G = false;
        b1Var.M.setIsStateSaved(false);
        b1Var.t(1);
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        if (this.mView != null) {
            w1 w1Var = this.mViewLifecycleOwner;
            w1Var.f2859o.b(this.mSavedViewRegistryState);
            this.mSavedViewRegistryState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new c2(androidx.compose.material3.d.l("Fragment ", this, " did not call through to super.onViewStateRestored()"));
        }
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.p.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        d().f2883p = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        d().f2882o = Boolean.valueOf(z);
    }

    public void setAnimations(int i10, int i11, int i12, int i13) {
        if (this.mAnimationInfo == null && i10 == 0 && i11 == 0 && i12 == 0 && i13 == 0) {
            return;
        }
        d().f2870b = i10;
        d().f2871c = i11;
        d().f2872d = i12;
        d().f2873e = i13;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(d2 d2Var) {
        d().getClass();
    }

    public void setEnterTransition(Object obj) {
        d().f2877i = obj;
    }

    public void setExitSharedElementCallback(d2 d2Var) {
        d().getClass();
    }

    public void setExitTransition(Object obj) {
        d().k = obj;
    }

    public void setFocusedView(View view) {
        d().f2885r = view;
    }

    @Deprecated
    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (!isAdded() || isHidden()) {
                return;
            }
            ((g0) this.mHost).f2731o.invalidateMenu();
        }
    }

    public void setInitialSavedState(b0 b0Var) {
        Bundle bundle;
        if (this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (b0Var == null || (bundle = b0Var.f2666i) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                ((g0) this.mHost).f2731o.invalidateMenu();
            }
        }
    }

    public void setNextTransition(int i10) {
        if (this.mAnimationInfo == null && i10 == 0) {
            return;
        }
        d();
        this.mAnimationInfo.f2874f = i10;
    }

    public void setPopDirection(boolean z) {
        if (this.mAnimationInfo == null) {
            return;
        }
        d().f2869a = z;
    }

    public void setPostOnViewCreatedAlpha(float f10) {
        d().f2884q = f10;
    }

    public void setReenterTransition(Object obj) {
        d().f2879l = obj;
    }

    @Deprecated
    public void setRetainInstance(boolean z) {
        h1.b bVar = h1.c.f15839a;
        h1.c.b(new SetRetainInstanceUsageViolation(this, "Attempting to set retain instance for fragment " + this));
        h1.c.a(this).getClass();
        this.mRetainInstance = z;
        b1 b1Var = this.mFragmentManager;
        if (b1Var == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z) {
            b1Var.M.addRetainedFragment(this);
        } else {
            b1Var.M.removeRetainedFragment(this);
        }
    }

    public void setReturnTransition(Object obj) {
        d().f2878j = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        d().f2880m = obj;
    }

    public void setSharedElementNames(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        d();
        y yVar = this.mAnimationInfo;
        yVar.f2875g = arrayList;
        yVar.f2876h = arrayList2;
    }

    public void setSharedElementReturnTransition(Object obj) {
        d().f2881n = obj;
    }

    @Deprecated
    public void setTargetFragment(c0 c0Var, int i10) {
        if (c0Var != null) {
            h1.b bVar = h1.c.f15839a;
            h1.c.b(new SetTargetFragmentUsageViolation(this, "Attempting to set target fragment " + c0Var + " with request code " + i10 + " for fragment " + this));
            h1.c.a(this).getClass();
        }
        b1 b1Var = this.mFragmentManager;
        b1 b1Var2 = c0Var != null ? c0Var.mFragmentManager : null;
        if (b1Var != null && b1Var2 != null && b1Var != b1Var2) {
            throw new IllegalArgumentException(androidx.compose.material3.d.l("Fragment ", c0Var, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (c0 c0VarF = c0Var; c0VarF != null; c0VarF = c0VarF.f(false)) {
            if (c0VarF.equals(this)) {
                throw new IllegalArgumentException("Setting " + c0Var + " as the target of " + this + " would create a target cycle");
            }
        }
        if (c0Var == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager == null || c0Var.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = c0Var;
        } else {
            this.mTargetWho = c0Var.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = i10;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        h1.b bVar = h1.c.f15839a;
        h1.c.b(new SetUserVisibleHintViolation(this, "Attempting to set user visible hint to " + z + " for fragment " + this));
        h1.c.a(this).getClass();
        boolean z5 = false;
        if (!this.mUserVisibleHint && z && this.mState < 5 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            b1 b1Var = this.mFragmentManager;
            l1 l1VarF = b1Var.f(this);
            c0 c0Var = l1VarF.f2773c;
            if (c0Var.mDeferStart) {
                if (b1Var.f2668b) {
                    b1Var.I = true;
                } else {
                    c0Var.mDeferStart = false;
                    l1VarF.j();
                }
            }
        }
        this.mUserVisibleHint = z;
        if (this.mState < 5 && !z) {
            z5 = true;
        }
        this.mDeferStart = z5;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        m0 m0Var = this.mHost;
        if (m0Var == null) {
            return false;
        }
        h0 h0Var = ((g0) m0Var).f2731o;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return i10 >= 32 ? androidx.core.app.d.a(h0Var, str) : i10 == 31 ? androidx.core.app.c.b(h0Var, str) : androidx.core.app.b.c(h0Var, str);
        }
        return false;
    }

    public void startActivity(Intent intent) {
        startActivity(intent, null);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i10) {
        startActivityForResult(intent, i10, null);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i10, Intent intent, int i11, int i12, int i13, Bundle bundle) throws IntentSender.SendIntentException {
        if (this.mHost == null) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to Activity"));
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + i10 + " IntentSender: " + intentSender + " fillInIntent: " + intent + " options: " + bundle);
        }
        b1 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.B == null) {
            m0 m0Var = parentFragmentManager.f2686u;
            if (i10 == -1) {
                m0Var.f2779i.startIntentSenderForResult(intentSender, i10, intent, i11, i12, i13, bundle);
                return;
            } else {
                m0Var.getClass();
                throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
            }
        }
        if (bundle != null) {
            if (intent == null) {
                intent = new Intent();
                intent.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            }
            if (b1.G(2)) {
                Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent + " for fragment " + this);
            }
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        androidx.activity.result.n nVar = new androidx.activity.result.n(intentSender, intent, i11, i12);
        parentFragmentManager.D.addLast(new x0(this.mWho, i10));
        if (b1.G(2)) {
            Log.v("FragmentManager", "Fragment " + this + "is launching an IntentSender for result ");
        }
        parentFragmentManager.B.a(nVar);
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo == null || !d().f2886s) {
            return;
        }
        if (this.mHost == null) {
            d().f2886s = false;
        } else if (Looper.myLooper() != this.mHost.f2781m.getLooper()) {
            this.mHost.f2781m.postAtFrontOfQueue(new s(this, 1));
        } else {
            callStartTransitionListener(true);
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append(getClass().getSimpleName());
        sb2.append("{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("} (");
        sb2.append(this.mWho);
        if (this.mFragmentId != 0) {
            sb2.append(" id=0x");
            sb2.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb2.append(" tag=");
            sb2.append(this.mTag);
        }
        sb2.append(")");
        return sb2.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    @Deprecated
    public static c0 instantiate(Context context, String str, Bundle bundle) {
        try {
            c0 c0Var = (c0) s0.b(context.getClassLoader(), str).getConstructor(null).newInstance(null);
            if (bundle == null) {
                return c0Var;
            }
            bundle.setClassLoader(c0Var.getClass().getClassLoader());
            c0Var.setArguments(bundle);
            return c0Var;
        } catch (IllegalAccessException e5) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e5);
        } catch (InstantiationException e6) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e6);
        } catch (NoSuchMethodException e10) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e10);
        } catch (InvocationTargetException e11) {
            throw new Fragment$InstantiationException(a0.c.l("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e11);
        }
    }

    public final String getString(int i10, Object... objArr) {
        return getResources().getString(i10, objArr);
    }

    public final void postponeEnterTransition(long j10, TimeUnit timeUnit) {
        d().f2886s = true;
        Handler handler = this.mPostponedHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mPostponedDurationRunnable);
        }
        b1 b1Var = this.mFragmentManager;
        if (b1Var != null) {
            this.mPostponedHandler = b1Var.f2686u.f2781m;
        } else {
            this.mPostponedHandler = new Handler(Looper.getMainLooper());
        }
        this.mPostponedHandler.removeCallbacks(this.mPostponedDurationRunnable);
        this.mPostponedHandler.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j10));
    }

    public final <I, O> androidx.activity.result.d registerForActivityResult(g.a aVar, androidx.activity.result.k kVar, androidx.activity.result.b<O> bVar) {
        return h(aVar, new w(kVar, 2), bVar);
    }

    public void startActivity(Intent intent, Bundle bundle) {
        m0 m0Var = this.mHost;
        if (m0Var == null) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to Activity"));
        }
        m0Var.f2780l.startActivity(intent, bundle);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int i10, Bundle bundle) {
        if (this.mHost == null) {
            throw new IllegalStateException(androidx.compose.material3.d.l("Fragment ", this, " not attached to Activity"));
        }
        b1 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.A == null) {
            m0 m0Var = parentFragmentManager.f2686u;
            if (i10 == -1) {
                m0Var.f2780l.startActivity(intent, bundle);
                return;
            } else {
                m0Var.getClass();
                throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
            }
        }
        parentFragmentManager.D.addLast(new x0(this.mWho, i10));
        if (intent != null && bundle != null) {
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        parentFragmentManager.A.a(intent);
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        m0 m0Var = this.mHost;
        if (m0Var != null) {
            h0 h0Var = ((g0) m0Var).f2731o;
            LayoutInflater layoutInflaterCloneInContext = h0Var.getLayoutInflater().cloneInContext(h0Var);
            layoutInflaterCloneInContext.setFactory2(this.mChildFragmentManager.f2672f);
            return layoutInflaterCloneInContext;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }
}
