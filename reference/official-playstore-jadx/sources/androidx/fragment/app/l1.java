package androidx.fragment.app;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.strictmode.WrongFragmentContainerViolation;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class l1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f2771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m1 f2772b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c0 f2773c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2774d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2775e = -1;

    public l1(g gVar, m1 m1Var, c0 c0Var) {
        this.f2771a = gVar;
        this.f2772b = m1Var;
        this.f2773c = c0Var;
    }

    public final void a() {
        View view;
        View view2;
        ArrayList arrayList = (ArrayList) this.f2772b.f2783i;
        c0 c0Var = this.f2773c;
        ViewGroup viewGroup = c0Var.mContainer;
        int iIndexOfChild = -1;
        if (viewGroup != null) {
            int iIndexOf = arrayList.indexOf(c0Var);
            int i10 = iIndexOf - 1;
            while (true) {
                if (i10 < 0) {
                    while (true) {
                        iIndexOf++;
                        if (iIndexOf >= arrayList.size()) {
                            break;
                        }
                        c0 c0Var2 = (c0) arrayList.get(iIndexOf);
                        if (c0Var2.mContainer == viewGroup && (view = c0Var2.mView) != null) {
                            iIndexOfChild = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    c0 c0Var3 = (c0) arrayList.get(i10);
                    if (c0Var3.mContainer == viewGroup && (view2 = c0Var3.mView) != null) {
                        iIndexOfChild = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i10--;
                }
            }
        }
        c0Var.mContainer.addView(c0Var.mView, iIndexOfChild);
    }

    public final void b() {
        boolean zG = b1.G(3);
        c0 c0Var = this.f2773c;
        if (zG) {
            Log.d("FragmentManager", "moveto ATTACHED: " + c0Var);
        }
        c0 c0Var2 = c0Var.mTarget;
        l1 l1Var = null;
        m1 m1Var = this.f2772b;
        if (c0Var2 != null) {
            l1 l1Var2 = (l1) ((HashMap) m1Var.f2784l).get(c0Var2.mWho);
            if (l1Var2 == null) {
                throw new IllegalStateException("Fragment " + c0Var + " declared target fragment " + c0Var.mTarget + " that does not belong to this FragmentManager!");
            }
            c0Var.mTargetWho = c0Var.mTarget.mWho;
            c0Var.mTarget = null;
            l1Var = l1Var2;
        } else {
            String str = c0Var.mTargetWho;
            if (str != null && (l1Var = (l1) ((HashMap) m1Var.f2784l).get(str)) == null) {
                StringBuilder sb2 = new StringBuilder("Fragment ");
                sb2.append(c0Var);
                sb2.append(" declared target fragment ");
                throw new IllegalStateException(a0.c.p(sb2, c0Var.mTargetWho, " that does not belong to this FragmentManager!"));
            }
        }
        if (l1Var != null) {
            l1Var.j();
        }
        b1 b1Var = c0Var.mFragmentManager;
        c0Var.mHost = b1Var.f2686u;
        c0Var.mParentFragment = b1Var.f2688w;
        g gVar = this.f2771a;
        gVar.g(false);
        c0Var.performAttach();
        gVar.b(false);
    }

    public final int c() {
        z1 z1Var;
        c0 c0Var = this.f2773c;
        if (c0Var.mFragmentManager == null) {
            return c0Var.mState;
        }
        int iMin = this.f2775e;
        int iOrdinal = c0Var.mMaxState.ordinal();
        if (iOrdinal == 1) {
            iMin = Math.min(iMin, 0);
        } else if (iOrdinal == 2) {
            iMin = Math.min(iMin, 1);
        } else if (iOrdinal == 3) {
            iMin = Math.min(iMin, 5);
        } else if (iOrdinal != 4) {
            iMin = Math.min(iMin, -1);
        }
        if (c0Var.mFromLayout) {
            if (c0Var.mInLayout) {
                iMin = Math.max(this.f2775e, 2);
                View view = c0Var.mView;
                if (view != null && view.getParent() == null) {
                    iMin = Math.min(iMin, 2);
                }
            } else {
                iMin = this.f2775e < 4 ? Math.min(iMin, c0Var.mState) : Math.min(iMin, 1);
            }
        }
        if (!c0Var.mAdded) {
            iMin = Math.min(iMin, 1);
        }
        ViewGroup viewGroup = c0Var.mContainer;
        if (viewGroup != null) {
            n nVarH = n.h(viewGroup, c0Var.getParentFragmentManager());
            z1 z1VarF = nVarH.f(c0Var);
            i = z1VarF != null ? z1VarF.f2891b : 0;
            Iterator it = nVarH.f2789c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z1Var = null;
                    break;
                }
                z1Var = (z1) it.next();
                if (z1Var.f2892c.equals(c0Var) && !z1Var.f2895f) {
                    break;
                }
            }
            if (z1Var != null && (i == 0 || i == 1)) {
                i = z1Var.f2891b;
            }
        }
        if (i == 2) {
            iMin = Math.min(iMin, 6);
        } else if (i == 3) {
            iMin = Math.max(iMin, 3);
        } else if (c0Var.mRemoving) {
            iMin = c0Var.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, -1);
        }
        if (c0Var.mDeferStart && c0Var.mState < 5) {
            iMin = Math.min(iMin, 4);
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + iMin + " for " + c0Var);
        }
        return iMin;
    }

    public final void d() {
        boolean zG = b1.G(3);
        c0 c0Var = this.f2773c;
        if (zG) {
            Log.d("FragmentManager", "moveto CREATED: " + c0Var);
        }
        if (c0Var.mIsCreated) {
            c0Var.restoreChildFragmentState(c0Var.mSavedFragmentState);
            c0Var.mState = 1;
        } else {
            g gVar = this.f2771a;
            gVar.h(false);
            c0Var.performCreate(c0Var.mSavedFragmentState);
            gVar.c(false);
        }
    }

    public final void e() {
        String resourceName;
        c0 c0Var = this.f2773c;
        if (c0Var.mFromLayout) {
            return;
        }
        if (b1.G(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + c0Var);
        }
        LayoutInflater layoutInflaterPerformGetLayoutInflater = c0Var.performGetLayoutInflater(c0Var.mSavedFragmentState);
        ViewGroup viewGroup = c0Var.mContainer;
        if (viewGroup == null) {
            int i10 = c0Var.mContainerId;
            if (i10 == 0) {
                viewGroup = null;
            } else {
                if (i10 == -1) {
                    throw new IllegalArgumentException(androidx.compose.material3.d.l("Cannot create fragment ", c0Var, " for a container view with no id"));
                }
                viewGroup = (ViewGroup) c0Var.mFragmentManager.f2687v.b(i10);
                if (viewGroup == null) {
                    if (!c0Var.mRestored) {
                        try {
                            resourceName = c0Var.getResources().getResourceName(c0Var.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(c0Var.mContainerId) + " (" + resourceName + ") for fragment " + c0Var);
                    }
                } else if (!(viewGroup instanceof k0)) {
                    h1.b bVar = h1.c.f15839a;
                    h1.c.b(new WrongFragmentContainerViolation(c0Var, "Attempting to add fragment " + c0Var + " to container " + viewGroup + " which is not a FragmentContainerView"));
                    h1.c.a(c0Var).getClass();
                }
            }
        }
        c0Var.mContainer = viewGroup;
        c0Var.performCreateView(layoutInflaterPerformGetLayoutInflater, viewGroup, c0Var.mSavedFragmentState);
        View view = c0Var.mView;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            c0Var.mView.setTag(R.id.fragment_container_view_tag, c0Var);
            if (viewGroup != null) {
                a();
            }
            if (c0Var.mHidden) {
                c0Var.mView.setVisibility(8);
            }
            View view2 = c0Var.mView;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            if (view2.isAttachedToWindow()) {
                androidx.core.view.i1.c(c0Var.mView);
            } else {
                View view3 = c0Var.mView;
                view3.addOnAttachStateChangeListener(new k1(view3));
            }
            c0Var.performViewCreated();
            this.f2771a.m(false);
            int visibility = c0Var.mView.getVisibility();
            c0Var.setPostOnViewCreatedAlpha(c0Var.mView.getAlpha());
            if (c0Var.mContainer != null && visibility == 0) {
                View viewFindFocus = c0Var.mView.findFocus();
                if (viewFindFocus != null) {
                    c0Var.setFocusedView(viewFindFocus);
                    if (b1.G(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + viewFindFocus + " for Fragment " + c0Var);
                    }
                }
                c0Var.mView.setAlpha(0.0f);
            }
        }
        c0Var.mState = 2;
    }

    public final void f() {
        c0 c0VarB;
        boolean zG = b1.G(3);
        c0 c0Var = this.f2773c;
        if (zG) {
            Log.d("FragmentManager", "movefrom CREATED: " + c0Var);
        }
        boolean zIsChangingConfigurations = true;
        boolean z = c0Var.mRemoving && !c0Var.isInBackStack();
        m1 m1Var = this.f2772b;
        if (z && !c0Var.mBeingSaved) {
        }
        if (!z && !((FragmentManagerViewModel) m1Var.f2786n).shouldDestroy(c0Var)) {
            String str = c0Var.mTargetWho;
            if (str != null && (c0VarB = m1Var.b(str)) != null && c0VarB.mRetainInstance) {
                c0Var.mTarget = c0VarB;
            }
            c0Var.mState = 0;
            return;
        }
        m0 m0Var = c0Var.mHost;
        if (m0Var instanceof androidx.lifecycle.j1) {
            zIsChangingConfigurations = ((FragmentManagerViewModel) m1Var.f2786n).isCleared();
        } else {
            h0 h0Var = m0Var.f2780l;
            if (androidx.compose.material3.d.B(h0Var)) {
                zIsChangingConfigurations = true ^ h0Var.isChangingConfigurations();
            }
        }
        if ((z && !c0Var.mBeingSaved) || zIsChangingConfigurations) {
            ((FragmentManagerViewModel) m1Var.f2786n).clearNonConfigState(c0Var);
        }
        c0Var.performDestroy();
        this.f2771a.d(false);
        for (l1 l1Var : m1Var.d()) {
            if (l1Var != null) {
                c0 c0Var2 = l1Var.f2773c;
                if (c0Var.mWho.equals(c0Var2.mTargetWho)) {
                    c0Var2.mTarget = c0Var;
                    c0Var2.mTargetWho = null;
                }
            }
        }
        String str2 = c0Var.mTargetWho;
        if (str2 != null) {
            c0Var.mTarget = m1Var.b(str2);
        }
        m1Var.h(this);
    }

    public final void g() {
        View view;
        boolean zG = b1.G(3);
        c0 c0Var = this.f2773c;
        if (zG) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + c0Var);
        }
        ViewGroup viewGroup = c0Var.mContainer;
        if (viewGroup != null && (view = c0Var.mView) != null) {
            viewGroup.removeView(view);
        }
        c0Var.performDestroyView();
        this.f2771a.n(false);
        c0Var.mContainer = null;
        c0Var.mView = null;
        c0Var.mViewLifecycleOwner = null;
        c0Var.mViewLifecycleOwnerLiveData.i(null);
        c0Var.mInLayout = false;
    }

    public final void h() {
        boolean zG = b1.G(3);
        c0 c0Var = this.f2773c;
        if (zG) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + c0Var);
        }
        c0Var.performDetach();
        this.f2771a.e(false);
        c0Var.mState = -1;
        c0Var.mHost = null;
        c0Var.mParentFragment = null;
        c0Var.mFragmentManager = null;
        if ((!c0Var.mRemoving || c0Var.isInBackStack()) && !((FragmentManagerViewModel) this.f2772b.f2786n).shouldDestroy(c0Var)) {
            return;
        }
        if (b1.G(3)) {
            Log.d("FragmentManager", "initState called for fragment: " + c0Var);
        }
        c0Var.initState();
    }

    public final void i() {
        c0 c0Var = this.f2773c;
        if (c0Var.mFromLayout && c0Var.mInLayout && !c0Var.mPerformedCreateView) {
            if (b1.G(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + c0Var);
            }
            c0Var.performCreateView(c0Var.performGetLayoutInflater(c0Var.mSavedFragmentState), null, c0Var.mSavedFragmentState);
            View view = c0Var.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                c0Var.mView.setTag(R.id.fragment_container_view_tag, c0Var);
                if (c0Var.mHidden) {
                    c0Var.mView.setVisibility(8);
                }
                c0Var.performViewCreated();
                this.f2771a.m(false);
                c0Var.mState = 2;
            }
        }
    }

    public final void j() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        boolean z = this.f2774d;
        c0 c0Var = this.f2773c;
        if (z) {
            if (b1.G(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + c0Var);
                return;
            }
            return;
        }
        try {
            this.f2774d = true;
            boolean z5 = false;
            while (true) {
                int iC = c();
                int i10 = c0Var.mState;
                m1 m1Var = this.f2772b;
                if (iC == i10) {
                    if (!z5 && i10 == -1 && c0Var.mRemoving && !c0Var.isInBackStack() && !c0Var.mBeingSaved) {
                        if (b1.G(3)) {
                            Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + c0Var);
                        }
                        ((FragmentManagerViewModel) m1Var.f2786n).clearNonConfigState(c0Var);
                        m1Var.h(this);
                        if (b1.G(3)) {
                            Log.d("FragmentManager", "initState called for fragment: " + c0Var);
                        }
                        c0Var.initState();
                    }
                    if (c0Var.mHiddenChanged) {
                        if (c0Var.mView != null && (viewGroup = c0Var.mContainer) != null) {
                            n nVarH = n.h(viewGroup, c0Var.getParentFragmentManager());
                            if (c0Var.mHidden) {
                                if (b1.G(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + c0Var);
                                }
                                nVarH.b(3, 1, this);
                            } else {
                                if (b1.G(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + c0Var);
                                }
                                nVarH.b(2, 1, this);
                            }
                        }
                        b1 b1Var = c0Var.mFragmentManager;
                        if (b1Var != null && c0Var.mAdded && b1.H(c0Var)) {
                            b1Var.E = true;
                        }
                        c0Var.mHiddenChanged = false;
                        c0Var.onHiddenChanged(c0Var.mHidden);
                        c0Var.mChildFragmentManager.n();
                    }
                    this.f2774d = false;
                    return;
                }
                g gVar = this.f2771a;
                if (iC <= i10) {
                    switch (i10 - 1) {
                        case -1:
                            h();
                            break;
                        case 0:
                            if (c0Var.mBeingSaved) {
                                if (((j1) ((HashMap) m1Var.f2785m).get(c0Var.mWho)) == null) {
                                    m();
                                }
                            }
                            f();
                            break;
                        case 1:
                            g();
                            c0Var.mState = 1;
                            break;
                        case 2:
                            c0Var.mInLayout = false;
                            c0Var.mState = 2;
                            break;
                        case 3:
                            if (b1.G(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + c0Var);
                            }
                            if (c0Var.mBeingSaved) {
                                m();
                            } else if (c0Var.mView != null && c0Var.mSavedViewState == null) {
                                n();
                            }
                            if (c0Var.mView != null && (viewGroup2 = c0Var.mContainer) != null) {
                                n nVarH2 = n.h(viewGroup2, c0Var.getParentFragmentManager());
                                if (b1.G(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + c0Var);
                                }
                                nVarH2.b(1, 3, this);
                            }
                            c0Var.mState = 3;
                            break;
                        case 4:
                            if (b1.G(3)) {
                                Log.d("FragmentManager", "movefrom STARTED: " + c0Var);
                            }
                            c0Var.performStop();
                            gVar.l(false);
                            break;
                        case 5:
                            c0Var.mState = 5;
                            break;
                        case 6:
                            if (b1.G(3)) {
                                Log.d("FragmentManager", "movefrom RESUMED: " + c0Var);
                            }
                            c0Var.performPause();
                            gVar.f(false);
                            break;
                    }
                } else {
                    switch (i10 + 1) {
                        case 0:
                            b();
                            break;
                        case 1:
                            d();
                            break;
                        case 2:
                            i();
                            e();
                            break;
                        case 3:
                            if (b1.G(3)) {
                                Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + c0Var);
                            }
                            c0Var.performActivityCreated(c0Var.mSavedFragmentState);
                            gVar.a(false);
                            break;
                        case 4:
                            if (c0Var.mView != null && (viewGroup3 = c0Var.mContainer) != null) {
                                n nVarH3 = n.h(viewGroup3, c0Var.getParentFragmentManager());
                                int iB = a2.b(c0Var.mView.getVisibility());
                                if (b1.G(2)) {
                                    Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + c0Var);
                                }
                                nVarH3.b(iB, 2, this);
                            }
                            c0Var.mState = 4;
                            break;
                        case 5:
                            if (b1.G(3)) {
                                Log.d("FragmentManager", "moveto STARTED: " + c0Var);
                            }
                            c0Var.performStart();
                            gVar.k(false);
                            break;
                        case 6:
                            c0Var.mState = 6;
                            break;
                        case 7:
                            l();
                            break;
                    }
                }
                z5 = true;
            }
        } catch (Throwable th) {
            this.f2774d = false;
            throw th;
        }
    }

    public final void k(ClassLoader classLoader) {
        c0 c0Var = this.f2773c;
        Bundle bundle = c0Var.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        c0Var.mSavedViewState = c0Var.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        c0Var.mSavedViewRegistryState = c0Var.mSavedFragmentState.getBundle("android:view_registry_state");
        c0Var.mTargetWho = c0Var.mSavedFragmentState.getString("android:target_state");
        if (c0Var.mTargetWho != null) {
            c0Var.mTargetRequestCode = c0Var.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        Boolean bool = c0Var.mSavedUserVisibleHint;
        if (bool != null) {
            c0Var.mUserVisibleHint = bool.booleanValue();
            c0Var.mSavedUserVisibleHint = null;
        } else {
            c0Var.mUserVisibleHint = c0Var.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        }
        if (c0Var.mUserVisibleHint) {
            return;
        }
        c0Var.mDeferStart = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l() {
        /*
            r6 = this;
            r0 = 3
            boolean r0 = androidx.fragment.app.b1.G(r0)
            java.lang.String r1 = "FragmentManager"
            androidx.fragment.app.c0 r2 = r6.f2773c
            if (r0 == 0) goto L1c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "moveto RESUMED: "
            r0.<init>(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L1c:
            android.view.View r0 = r2.getFocusedView()
            if (r0 == 0) goto L78
            android.view.View r3 = r2.mView
            if (r0 != r3) goto L27
            goto L31
        L27:
            android.view.ViewParent r3 = r0.getParent()
        L2b:
            if (r3 == 0) goto L78
            android.view.View r4 = r2.mView
            if (r3 != r4) goto L73
        L31:
            boolean r3 = r0.requestFocus()
            r4 = 2
            boolean r4 = androidx.fragment.app.b1.G(r4)
            if (r4 == 0) goto L78
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "requestFocus: Restoring focused view "
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r0 = " "
            r4.append(r0)
            if (r3 == 0) goto L50
            java.lang.String r0 = "succeeded"
            goto L52
        L50:
            java.lang.String r0 = "failed"
        L52:
            r4.append(r0)
            java.lang.String r0 = " on Fragment "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = " resulting in focused view "
            r4.append(r0)
            android.view.View r0 = r2.mView
            android.view.View r0 = r0.findFocus()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            android.util.Log.v(r1, r0)
            goto L78
        L73:
            android.view.ViewParent r3 = r3.getParent()
            goto L2b
        L78:
            r0 = 0
            r2.setFocusedView(r0)
            r2.performResume()
            androidx.fragment.app.g r1 = r6.f2771a
            r3 = 0
            r1.i(r3)
            r2.mSavedFragmentState = r0
            r2.mSavedViewState = r0
            r2.mSavedViewRegistryState = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.l1.l():void");
    }

    public final void m() {
        c0 c0Var = this.f2773c;
        j1 j1Var = new j1(c0Var);
        if (c0Var.mState <= -1 || j1Var.f2759w != null) {
            j1Var.f2759w = c0Var.mSavedFragmentState;
        } else {
            Bundle bundle = new Bundle();
            c0Var.performSaveInstanceState(bundle);
            this.f2771a.j(false);
            if (bundle.isEmpty()) {
                bundle = null;
            }
            if (c0Var.mView != null) {
                n();
            }
            if (c0Var.mSavedViewState != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putSparseParcelableArray("android:view_state", c0Var.mSavedViewState);
            }
            if (c0Var.mSavedViewRegistryState != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putBundle("android:view_registry_state", c0Var.mSavedViewRegistryState);
            }
            if (!c0Var.mUserVisibleHint) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putBoolean("android:user_visible_hint", c0Var.mUserVisibleHint);
            }
            j1Var.f2759w = bundle;
            if (c0Var.mTargetWho != null) {
                if (bundle == null) {
                    j1Var.f2759w = new Bundle();
                }
                j1Var.f2759w.putString("android:target_state", c0Var.mTargetWho);
                int i10 = c0Var.mTargetRequestCode;
                if (i10 != 0) {
                    j1Var.f2759w.putInt("android:target_req_state", i10);
                }
            }
        }
    }

    public final void n() {
        c0 c0Var = this.f2773c;
        if (c0Var.mView == null) {
            return;
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "Saving view state for fragment " + c0Var + " with view " + c0Var.mView);
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        c0Var.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            c0Var.mSavedViewState = sparseArray;
        }
        Bundle bundle = new Bundle();
        c0Var.mViewLifecycleOwner.f2859o.c(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        c0Var.mSavedViewRegistryState = bundle;
    }

    public l1(g gVar, m1 m1Var, ClassLoader classLoader, s0 s0Var, j1 j1Var) {
        this.f2771a = gVar;
        this.f2772b = m1Var;
        c0 c0VarInstantiate = c0.instantiate(s0Var.f2836a.f2686u.f2780l, j1Var.f2747i, null);
        Bundle bundle = j1Var.f2756t;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        c0VarInstantiate.setArguments(bundle);
        c0VarInstantiate.mWho = j1Var.f2748l;
        c0VarInstantiate.mFromLayout = j1Var.f2749m;
        c0VarInstantiate.mRestored = true;
        c0VarInstantiate.mFragmentId = j1Var.f2750n;
        c0VarInstantiate.mContainerId = j1Var.f2751o;
        c0VarInstantiate.mTag = j1Var.f2752p;
        c0VarInstantiate.mRetainInstance = j1Var.f2753q;
        c0VarInstantiate.mRemoving = j1Var.f2754r;
        c0VarInstantiate.mDetached = j1Var.f2755s;
        c0VarInstantiate.mHidden = j1Var.f2757u;
        c0VarInstantiate.mMaxState = androidx.lifecycle.q.values()[j1Var.f2758v];
        Bundle bundle2 = j1Var.f2759w;
        if (bundle2 != null) {
            c0VarInstantiate.mSavedFragmentState = bundle2;
        } else {
            c0VarInstantiate.mSavedFragmentState = new Bundle();
        }
        this.f2773c = c0VarInstantiate;
        if (b1.G(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + c0VarInstantiate);
        }
    }

    public l1(g gVar, m1 m1Var, c0 c0Var, j1 j1Var) {
        this.f2771a = gVar;
        this.f2772b = m1Var;
        this.f2773c = c0Var;
        c0Var.mSavedViewState = null;
        c0Var.mSavedViewRegistryState = null;
        c0Var.mBackStackNesting = 0;
        c0Var.mInLayout = false;
        c0Var.mAdded = false;
        c0 c0Var2 = c0Var.mTarget;
        c0Var.mTargetWho = c0Var2 != null ? c0Var2.mWho : null;
        c0Var.mTarget = null;
        Bundle bundle = j1Var.f2759w;
        if (bundle != null) {
            c0Var.mSavedFragmentState = bundle;
        } else {
            c0Var.mSavedFragmentState = new Bundle();
        }
    }
}
