package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentTagUsageViolation;

/* JADX INFO: loaded from: classes3.dex */
public final class o0 implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b1 f2804i;

    public o0(b1 b1Var) {
        this.f2804i = b1Var;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zIsAssignableFrom;
        l1 l1VarF;
        boolean zEquals = k0.class.getName().equals(str);
        b1 b1Var = this.f2804i;
        if (zEquals) {
            return new k0(context, attributeSet, b1Var);
        }
        if ("fragment".equals(str)) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g1.a.f15458a);
            if (attributeValue == null) {
                attributeValue = typedArrayObtainStyledAttributes.getString(0);
            }
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
            String string = typedArrayObtainStyledAttributes.getString(2);
            typedArrayObtainStyledAttributes.recycle();
            if (attributeValue != null) {
                try {
                    zIsAssignableFrom = c0.class.isAssignableFrom(s0.a(context.getClassLoader(), attributeValue));
                } catch (ClassNotFoundException unused) {
                    zIsAssignableFrom = false;
                }
                if (zIsAssignableFrom) {
                    int id = view != null ? view.getId() : 0;
                    if (id == -1 && resourceId == -1 && string == null) {
                        throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                    }
                    c0 c0VarA = resourceId != -1 ? b1Var.A(resourceId) : null;
                    if (c0VarA == null && string != null) {
                        c0VarA = b1Var.B(string);
                    }
                    if (c0VarA == null && id != -1) {
                        c0VarA = b1Var.A(id);
                    }
                    if (c0VarA == null) {
                        s0 s0VarD = b1Var.D();
                        context.getClassLoader();
                        c0VarA = c0.instantiate(s0VarD.f2836a.f2686u.f2780l, attributeValue, null);
                        c0VarA.mFromLayout = true;
                        c0VarA.mFragmentId = resourceId != 0 ? resourceId : id;
                        c0VarA.mContainerId = id;
                        c0VarA.mTag = string;
                        c0VarA.mInLayout = true;
                        c0VarA.mFragmentManager = b1Var;
                        m0 m0Var = b1Var.f2686u;
                        c0VarA.mHost = m0Var;
                        c0VarA.onInflate((Context) m0Var.f2780l, attributeSet, c0VarA.mSavedFragmentState);
                        l1VarF = b1Var.a(c0VarA);
                        if (b1.G(2)) {
                            Log.v("FragmentManager", "Fragment " + c0VarA + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    } else {
                        if (c0VarA.mInLayout) {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
                        }
                        c0VarA.mInLayout = true;
                        c0VarA.mFragmentManager = b1Var;
                        m0 m0Var2 = b1Var.f2686u;
                        c0VarA.mHost = m0Var2;
                        c0VarA.onInflate((Context) m0Var2.f2780l, attributeSet, c0VarA.mSavedFragmentState);
                        l1VarF = b1Var.f(c0VarA);
                        if (b1.G(2)) {
                            Log.v("FragmentManager", "Retained Fragment " + c0VarA + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(resourceId));
                        }
                    }
                    ViewGroup viewGroup = (ViewGroup) view;
                    h1.b bVar = h1.c.f15839a;
                    h1.c.b(new FragmentTagUsageViolation(c0VarA, "Attempting to use <fragment> tag to add fragment " + c0VarA + " to container " + viewGroup));
                    h1.c.a(c0VarA).getClass();
                    c0VarA.mContainer = viewGroup;
                    l1VarF.j();
                    l1VarF.i();
                    View view2 = c0VarA.mView;
                    if (view2 == null) {
                        throw new IllegalStateException(a0.c.l("Fragment ", attributeValue, " did not create a view."));
                    }
                    if (resourceId != 0) {
                        view2.setId(resourceId);
                    }
                    if (c0VarA.mView.getTag() == null) {
                        c0VarA.mView.setTag(string);
                    }
                    c0VarA.mView.addOnAttachStateChangeListener(new n0(this, l1VarF));
                    return c0VarA.mView;
                }
            }
        }
        return null;
    }
}
