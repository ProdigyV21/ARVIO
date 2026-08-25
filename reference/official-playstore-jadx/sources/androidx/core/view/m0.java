package androidx.core.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes3.dex */
public final class m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f2283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f2284b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f2285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2286d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f2287e;

    public m0(View view) {
        this.f2285c = view;
    }

    public final boolean a(float f10, float f11, boolean z) {
        ViewParent viewParentE;
        if (this.f2286d && (viewParentE = e(0)) != null) {
            try {
                return k2.a(viewParentE, this.f2285c, f10, f11, z);
            } catch (AbstractMethodError e5) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedFling", e5);
            }
        }
        return false;
    }

    public final boolean b(float f10, float f11) {
        ViewParent viewParentE;
        if (this.f2286d && (viewParentE = e(0)) != null) {
            try {
                return k2.b(viewParentE, this.f2285c, f10, f11);
            } catch (AbstractMethodError e5) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreFling", e5);
            }
        }
        return false;
    }

    public final boolean c(int i10, int i11, int i12, int[] iArr, int[] iArr2) {
        ViewParent viewParentE;
        int i13;
        int i14;
        int[] iArr3;
        if (!this.f2286d || (viewParentE = e(i12)) == null) {
            return false;
        }
        if (i10 == 0 && i11 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        View view = this.f2285c;
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            i13 = iArr2[0];
            i14 = iArr2[1];
        } else {
            i13 = 0;
            i14 = 0;
        }
        if (iArr == null) {
            if (this.f2287e == null) {
                this.f2287e = new int[2];
            }
            iArr3 = this.f2287e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        boolean z = viewParentE instanceof n0;
        View view2 = this.f2285c;
        if (z) {
            ((n0) viewParentE).onNestedPreScroll(view2, i10, i11, iArr3, i12);
        } else if (i12 == 0) {
            try {
                k2.c(viewParentE, view2, i10, i11, iArr3);
            } catch (AbstractMethodError e5) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreScroll", e5);
            }
        }
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i13;
            iArr2[1] = iArr2[1] - i14;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    public final boolean d(int i10, int i11, int i12, int i13, int[] iArr, int i14, int[] iArr2) {
        ViewParent viewParentE;
        int i15;
        int i16;
        int[] iArr3;
        if (this.f2286d && (viewParentE = e(i14)) != null) {
            if (i10 != 0 || i11 != 0 || i12 != 0 || i13 != 0) {
                View view = this.f2285c;
                if (iArr != null) {
                    view.getLocationInWindow(iArr);
                    i15 = iArr[0];
                    i16 = iArr[1];
                } else {
                    i15 = 0;
                    i16 = 0;
                }
                if (iArr2 == null) {
                    if (this.f2287e == null) {
                        this.f2287e = new int[2];
                    }
                    int[] iArr4 = this.f2287e;
                    iArr4[0] = 0;
                    iArr4[1] = 0;
                    iArr3 = iArr4;
                } else {
                    iArr3 = iArr2;
                }
                boolean z = viewParentE instanceof o0;
                View view2 = this.f2285c;
                if (z) {
                    ((o0) viewParentE).onNestedScroll(view2, i10, i11, i12, i13, i14, iArr3);
                } else {
                    iArr3[0] = iArr3[0] + i12;
                    iArr3[1] = iArr3[1] + i13;
                    if (viewParentE instanceof n0) {
                        ((n0) viewParentE).onNestedScroll(view2, i10, i11, i12, i13, i14);
                    } else if (i14 == 0) {
                        try {
                            k2.d(viewParentE, view2, i10, i11, i12, i13);
                        } catch (AbstractMethodError e5) {
                            Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedScroll", e5);
                        }
                    }
                }
                if (iArr != null) {
                    view.getLocationInWindow(iArr);
                    iArr[0] = iArr[0] - i15;
                    iArr[1] = iArr[1] - i16;
                }
                return true;
            }
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
                return false;
            }
        }
        return false;
    }

    public final ViewParent e(int i10) {
        if (i10 == 0) {
            return this.f2283a;
        }
        if (i10 != 1) {
            return null;
        }
        return this.f2284b;
    }

    public final boolean f(int i10) {
        return e(i10) != null;
    }

    public final boolean g(int i10, int i11) {
        boolean zF;
        if (!f(i11)) {
            if (this.f2286d) {
                View view = this.f2285c;
                View view2 = view;
                for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                    boolean z = parent instanceof n0;
                    if (z) {
                        zF = ((n0) parent).onStartNestedScroll(view2, view, i10, i11);
                    } else if (i11 == 0) {
                        try {
                            zF = k2.f(parent, view2, view, i10);
                        } catch (AbstractMethodError e5) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e5);
                            zF = false;
                        }
                    } else {
                        zF = false;
                    }
                    if (zF) {
                        if (i11 == 0) {
                            this.f2283a = parent;
                        } else if (i11 == 1) {
                            this.f2284b = parent;
                        }
                        if (z) {
                            ((n0) parent).onNestedScrollAccepted(view2, view, i10, i11);
                        } else if (i11 == 0) {
                            try {
                                k2.e(parent, view2, view, i10);
                            } catch (AbstractMethodError e6) {
                                Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e6);
                            }
                        }
                    } else {
                        if (parent instanceof View) {
                            view2 = parent;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void h(int i10) {
        ViewParent viewParentE = e(i10);
        if (viewParentE != null) {
            boolean z = viewParentE instanceof n0;
            View view = this.f2285c;
            if (z) {
                ((n0) viewParentE).onStopNestedScroll(view, i10);
            } else if (i10 == 0) {
                try {
                    k2.g(viewParentE, view);
                } catch (AbstractMethodError e5) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onStopNestedScroll", e5);
                }
            }
            if (i10 == 0) {
                this.f2283a = null;
            } else {
                if (i10 != 1) {
                    return;
                }
                this.f2284b = null;
            }
        }
    }
}
