package androidx.fragment.app;

import android.util.Log;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends o1 implements v0, z0 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b1 f2660p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2661q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2662r;

    public a(b1 b1Var) {
        b1Var.D();
        m0 m0Var = b1Var.f2686u;
        if (m0Var != null) {
            m0Var.f2780l.getClassLoader();
        }
        this.f2805a = new ArrayList();
        this.f2818o = false;
        this.f2662r = -1;
        this.f2660p = b1Var;
    }

    @Override // androidx.fragment.app.z0
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (b1.G(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f2811g) {
            return true;
        }
        b1 b1Var = this.f2660p;
        if (b1Var.f2670d == null) {
            b1Var.f2670d = new ArrayList();
        }
        b1Var.f2670d.add(this);
        return true;
    }

    @Override // androidx.fragment.app.o1
    public final void c(int i10, c0 c0Var, String str) {
        String str2 = c0Var.mPreviousWho;
        if (str2 != null) {
            h1.c.c(c0Var, str2);
        }
        Class<?> cls = c0Var.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = c0Var.mTag;
            if (str3 != null && !str.equals(str3)) {
                StringBuilder sb2 = new StringBuilder("Can't change tag of fragment ");
                sb2.append(c0Var);
                sb2.append(": was ");
                throw new IllegalStateException(androidx.compose.material3.d.q(sb2, c0Var.mTag, " now ", str));
            }
            c0Var.mTag = str;
        }
        if (i10 != 0) {
            if (i10 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + c0Var + " with tag " + str + " to container view with no id");
            }
            int i11 = c0Var.mFragmentId;
            if (i11 != 0 && i11 != i10) {
                throw new IllegalStateException("Can't change container ID of fragment " + c0Var + ": was " + c0Var.mFragmentId + " now " + i10);
            }
            c0Var.mFragmentId = i10;
            c0Var.mContainerId = i10;
        }
        b(new n1(c0Var, 1));
        c0Var.mFragmentManager = this.f2660p;
    }

    public final void d(int i10) {
        ArrayList arrayList = this.f2805a;
        if (this.f2811g) {
            if (b1.G(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i10);
            }
            int size = arrayList.size();
            for (int i11 = 0; i11 < size; i11++) {
                n1 n1Var = (n1) arrayList.get(i11);
                c0 c0Var = n1Var.f2795b;
                if (c0Var != null) {
                    c0Var.mBackStackNesting += i10;
                    if (b1.G(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + n1Var.f2795b + " to " + n1Var.f2795b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public final int e(boolean z) {
        if (this.f2661q) {
            throw new IllegalStateException("commit already called");
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new x1());
            f("  ", printWriter, true);
            printWriter.close();
        }
        this.f2661q = true;
        boolean z5 = this.f2811g;
        b1 b1Var = this.f2660p;
        if (z5) {
            this.f2662r = b1Var.f2675i.getAndIncrement();
        } else {
            this.f2662r = -1;
        }
        b1Var.v(this, z);
        return this.f2662r;
    }

    public final void f(String str, PrintWriter printWriter, boolean z) {
        String str2;
        ArrayList arrayList = this.f2805a;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f2812h);
            printWriter.print(" mIndex=");
            printWriter.print(this.f2662r);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f2661q);
            if (this.f2810f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f2810f));
            }
            if (this.f2806b != 0 || this.f2807c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2806b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2807c));
            }
            if (this.f2808d != 0 || this.f2809e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2808d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2809e));
            }
            if (this.f2813i != 0 || this.f2814j != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f2813i));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f2814j);
            }
            if (this.k != 0 || this.f2815l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f2815l);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            n1 n1Var = (n1) arrayList.get(i10);
            switch (n1Var.f2794a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + n1Var.f2794a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i10);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(n1Var.f2795b);
            if (z) {
                if (n1Var.f2797d != 0 || n1Var.f2798e != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(n1Var.f2797d));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(n1Var.f2798e));
                }
                if (n1Var.f2799f != 0 || n1Var.f2800g != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(n1Var.f2799f));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(n1Var.f2800g));
                }
            }
        }
    }

    public final a g(c0 c0Var) {
        b1 b1Var = c0Var.mFragmentManager;
        if (b1Var == null || b1Var == this.f2660p) {
            b(new n1(c0Var, 3));
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + c0Var.toString() + " is already attached to a FragmentManager.");
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(128);
        sb2.append("BackStackEntry{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f2662r >= 0) {
            sb2.append(" #");
            sb2.append(this.f2662r);
        }
        if (this.f2812h != null) {
            sb2.append(" ");
            sb2.append(this.f2812h);
        }
        sb2.append("}");
        return sb2.toString();
    }
}
