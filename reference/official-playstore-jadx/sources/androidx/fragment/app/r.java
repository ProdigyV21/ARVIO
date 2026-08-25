package androidx.fragment.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* JADX INFO: loaded from: classes3.dex */
public class r extends c0 implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private boolean mCreatingDialog;
    private Dialog mDialog;
    private boolean mDismissed;
    private Handler mHandler;
    private boolean mShownByMe;
    private boolean mViewDestroyed;
    private Runnable mDismissRunnable = new h(this, 2);
    private DialogInterface.OnCancelListener mOnCancelListener = new o(this);
    private DialogInterface.OnDismissListener mOnDismissListener = new p(this);
    private int mStyle = 0;
    private int mTheme = 0;
    private boolean mCancelable = true;
    private boolean mShowsDialog = true;
    private int mBackStackId = -1;
    private androidx.lifecycle.k0<androidx.lifecycle.y> mObserver = new w(this, 1);
    private boolean mDialogCreated = false;

    @Override // androidx.fragment.app.c0
    public j0 createFragmentContainer() {
        return new q(this, super.createFragmentContainer());
    }

    public void dismiss() {
        i(false, false, false);
    }

    public void dismissAllowingStateLoss() {
        i(true, false, false);
    }

    public void dismissNow() {
        i(false, false, true);
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public final void i(boolean z, boolean z5, boolean z10) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!z5) {
                if (Looper.myLooper() == this.mHandler.getLooper()) {
                    onDismiss(this.mDialog);
                } else {
                    this.mHandler.post(this.mDismissRunnable);
                }
            }
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            if (z10) {
                b1 parentFragmentManager = getParentFragmentManager();
                int i10 = this.mBackStackId;
                if (i10 < 0) {
                    parentFragmentManager.getClass();
                    throw new IllegalArgumentException(a0.c.i(i10, "Bad id: "));
                }
                parentFragmentManager.N(i10, 1);
            } else {
                b1 parentFragmentManager2 = getParentFragmentManager();
                int i11 = this.mBackStackId;
                parentFragmentManager2.getClass();
                if (i11 < 0) {
                    throw new IllegalArgumentException(a0.c.i(i11, "Bad id: "));
                }
                parentFragmentManager2.v(new a1(parentFragmentManager2, i11), z);
            }
            this.mBackStackId = -1;
            return;
        }
        b1 parentFragmentManager3 = getParentFragmentManager();
        parentFragmentManager3.getClass();
        a aVar = new a(parentFragmentManager3);
        aVar.f2818o = true;
        aVar.g(this);
        if (z10) {
            if (aVar.f2811g) {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
            aVar.f2660p.y(aVar, false);
        } else if (z) {
            aVar.e(true);
        } else {
            aVar.e(false);
        }
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // androidx.fragment.app.c0
    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.c0
    public void onAttach(Context context) {
        Object obj;
        super.onAttach(context);
        androidx.lifecycle.h0 viewLifecycleOwnerLiveData = getViewLifecycleOwnerLiveData();
        androidx.lifecycle.k0<androidx.lifecycle.y> k0Var = this.mObserver;
        viewLifecycleOwnerLiveData.getClass();
        androidx.lifecycle.h0.b("observeForever");
        androidx.lifecycle.e0 e0Var = new androidx.lifecycle.e0(viewLifecycleOwnerLiveData, k0Var);
        p.f fVar = viewLifecycleOwnerLiveData.f3240b;
        p.c cVarA = fVar.a(k0Var);
        if (cVarA != null) {
            obj = cVarA.f21026l;
        } else {
            p.c cVar = new p.c(k0Var, e0Var);
            fVar.f21035n++;
            p.c cVar2 = fVar.f21033l;
            if (cVar2 == null) {
                fVar.f21032i = cVar;
                fVar.f21033l = cVar;
            } else {
                cVar2.f21027m = cVar;
                cVar.f21028n = cVar2;
                fVar.f21033l = cVar;
            }
            obj = null;
        }
        androidx.lifecycle.g0 g0Var = (androidx.lifecycle.g0) obj;
        if (g0Var instanceof androidx.lifecycle.f0) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (g0Var == null) {
            e0Var.a(true);
        }
        if (this.mShownByMe) {
            return;
        }
        this.mDismissed = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.c0
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        this.mShowsDialog = this.mContainerId == 0;
        if (bundle != null) {
            this.mStyle = bundle.getInt(SAVED_STYLE, 0);
            this.mTheme = bundle.getInt(SAVED_THEME, 0);
            this.mCancelable = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.mShowsDialog = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
            this.mBackStackId = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (b1.G(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new androidx.activity.u(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.c0
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!this.mDismissed) {
                onDismiss(this.mDialog);
            }
            this.mDialog = null;
            this.mDialogCreated = false;
        }
    }

    @Override // androidx.fragment.app.c0
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
        getViewLifecycleOwnerLiveData().h(this.mObserver);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mViewDestroyed) {
            return;
        }
        if (b1.G(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        i(true, true, false);
    }

    public View onFindViewById(int i10) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog.findViewById(i10);
        }
        return null;
    }

    @Override // androidx.fragment.app.c0
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        boolean z = this.mShowsDialog;
        if (z && !this.mCreatingDialog) {
            if (z && !this.mDialogCreated) {
                try {
                    this.mCreatingDialog = true;
                    Dialog dialogOnCreateDialog = onCreateDialog(bundle);
                    this.mDialog = dialogOnCreateDialog;
                    if (this.mShowsDialog) {
                        setupDialog(dialogOnCreateDialog, this.mStyle);
                        Context context = getContext();
                        if (context instanceof Activity) {
                            this.mDialog.setOwnerActivity((Activity) context);
                        }
                        this.mDialog.setCancelable(this.mCancelable);
                        this.mDialog.setOnCancelListener(this.mOnCancelListener);
                        this.mDialog.setOnDismissListener(this.mOnDismissListener);
                        this.mDialogCreated = true;
                    } else {
                        this.mDialog = null;
                    }
                    this.mCreatingDialog = false;
                } catch (Throwable th) {
                    this.mCreatingDialog = false;
                    throw th;
                }
            }
            if (b1.G(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.mDialog;
            if (dialog != null) {
                return layoutInflaterOnGetLayoutInflater.cloneInContext(dialog.getContext());
            }
        } else if (b1.G(2)) {
            String str = "getting layout inflater for DialogFragment " + this;
            if (!this.mShowsDialog) {
                Log.d("FragmentManager", "mShowsDialog = false: " + str);
                return layoutInflaterOnGetLayoutInflater;
            }
            Log.d("FragmentManager", "mCreatingDialog = true: " + str);
        }
        return layoutInflaterOnGetLayoutInflater;
    }

    public boolean onHasView() {
        return this.mDialogCreated;
    }

    @Override // androidx.fragment.app.c0
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean(SAVED_INTERNAL_DIALOG_SHOWING, false);
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, bundleOnSaveInstanceState);
        }
        int i10 = this.mStyle;
        if (i10 != 0) {
            bundle.putInt(SAVED_STYLE, i10);
        }
        int i11 = this.mTheme;
        if (i11 != 0) {
            bundle.putInt(SAVED_THEME, i11);
        }
        boolean z = this.mCancelable;
        if (!z) {
            bundle.putBoolean(SAVED_CANCELABLE, z);
        }
        boolean z5 = this.mShowsDialog;
        if (!z5) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, z5);
        }
        int i12 = this.mBackStackId;
        if (i12 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i12);
        }
    }

    @Override // androidx.fragment.app.c0
    public void onStart() {
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
            View decorView = this.mDialog.getWindow().getDecorView();
            androidx.lifecycle.z0.i(decorView, this);
            androidx.lifecycle.z0.j(decorView, this);
            f4.f.w(decorView, this);
        }
    }

    @Override // androidx.fragment.app.c0
    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.c0
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.c0
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null || this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    public final Dialog requireDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void setCancelable(boolean z) {
        this.mCancelable = z;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public void setShowsDialog(boolean z) {
        this.mShowsDialog = z;
    }

    public void setStyle(int i10, int i11) {
        if (b1.G(2)) {
            Log.d("FragmentManager", "Setting style and theme for DialogFragment " + this + " to " + i10 + ", " + i11);
        }
        this.mStyle = i10;
        if (i10 == 2 || i10 == 3) {
            this.mTheme = R.style.Theme.Panel;
        }
        if (i11 != 0) {
            this.mTheme = i11;
        }
    }

    public void setupDialog(Dialog dialog, int i10) {
        if (i10 != 1 && i10 != 2) {
            if (i10 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void show(b1 b1Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        b1Var.getClass();
        a aVar = new a(b1Var);
        aVar.f2818o = true;
        aVar.c(0, this, str);
        aVar.e(false);
    }

    public void showNow(b1 b1Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        b1Var.getClass();
        a aVar = new a(b1Var);
        aVar.f2818o = true;
        aVar.c(0, this, str);
        if (aVar.f2811g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        aVar.f2660p.y(aVar, false);
    }

    public int show(o1 o1Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        o1Var.c(0, this, str);
        this.mViewDestroyed = false;
        int iE = ((a) o1Var).e(false);
        this.mBackStackId = iE;
        return iE;
    }
}
