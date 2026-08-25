package androidx.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.PathInterpolator;
import com.arvio.tv.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static WeakHashMap f2200a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f2201b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2202c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e1 f2203d = new e1();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g1 f2204e = new g1();

    public static n2 a(View view) {
        if (f2200a == null) {
            f2200a = new WeakHashMap();
        }
        n2 n2Var = (n2) f2200a.get(view);
        if (n2Var != null) {
            return n2Var;
        }
        n2 n2Var2 = new n2(view);
        f2200a.put(view, n2Var2);
        return n2Var2;
    }

    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = a2.f2193d;
        a2 a2Var = (a2) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (a2Var == null) {
            a2Var = new a2();
            a2Var.f2194a = null;
            a2Var.f2195b = null;
            a2Var.f2196c = null;
            view.setTag(R.id.tag_unhandled_key_event_manager, a2Var);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = a2Var.f2194a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = a2.f2193d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    try {
                        if (a2Var.f2194a == null) {
                            a2Var.f2194a = new WeakHashMap();
                        }
                        for (int size = arrayList2.size() - 1; size >= 0; size--) {
                            ArrayList arrayList3 = a2.f2193d;
                            View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                            if (view2 == null) {
                                arrayList3.remove(size);
                            } else {
                                a2Var.f2194a.put(view2, Boolean.TRUE);
                                for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                    a2Var.f2194a.put((View) parent, Boolean.TRUE);
                                }
                            }
                        }
                    } finally {
                    }
                }
            }
        }
        View viewA = a2Var.a(view, keyEvent);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                if (a2Var.f2195b == null) {
                    a2Var.f2195b = new SparseArray();
                }
                a2Var.f2195b.put(keyCode, new WeakReference(viewA));
            }
        }
        return viewA != null;
    }

    public static View.AccessibilityDelegate c(View view) {
        if (Build.VERSION.SDK_INT >= 29) {
            return p1.a(view);
        }
        if (f2202c) {
            return null;
        }
        if (f2201b == null) {
            try {
                Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                f2201b = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable unused) {
                f2202c = true;
                return null;
            }
        }
        try {
            Object obj = f2201b.get(view);
            if (obj instanceof View.AccessibilityDelegate) {
                return (View.AccessibilityDelegate) obj;
            }
            return null;
        } catch (Throwable unused2) {
            f2202c = true;
            return null;
        }
    }

    public static String[] d(androidx.appcompat.widget.c0 c0Var) {
        return Build.VERSION.SDK_INT >= 31 ? r1.a(c0Var) : (String[]) c0Var.getTag(R.id.tag_on_receive_content_mime_types);
    }

    public static void e(int i10, View view) {
        Object tag;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            int i11 = Build.VERSION.SDK_INT;
            Object objB = null;
            if (i11 >= 28) {
                tag = o1.b(view);
            } else {
                tag = view.getTag(R.id.tag_accessibility_pane_title);
                if (!CharSequence.class.isInstance(tag)) {
                    tag = null;
                }
            }
            boolean z = ((CharSequence) tag) != null && view.isShown() && view.getWindowVisibility() == 0;
            if (view.getAccessibilityLiveRegion() != 0 || z) {
                AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                accessibilityEventObtain.setEventType(z ? 32 : 2048);
                accessibilityEventObtain.setContentChangeTypes(i10);
                if (z) {
                    List<CharSequence> text = accessibilityEventObtain.getText();
                    if (i11 >= 28) {
                        objB = o1.b(view);
                    } else {
                        Object tag2 = view.getTag(R.id.tag_accessibility_pane_title);
                        if (CharSequence.class.isInstance(tag2)) {
                            objB = tag2;
                        }
                    }
                    text.add((CharSequence) objB);
                    if (view.getImportantForAccessibility() == 0) {
                        view.setImportantForAccessibility(1);
                    }
                }
                view.sendAccessibilityEventUnchecked(accessibilityEventObtain);
                return;
            }
            if (i10 != 32) {
                if (view.getParent() != null) {
                    try {
                        view.getParent().notifySubtreeAccessibilityStateChanged(view, view, i10);
                        return;
                    } catch (AbstractMethodError e5) {
                        Log.e("ViewCompat", view.getParent().getClass().getSimpleName().concat(" does not fully implement ViewParent"), e5);
                        return;
                    }
                }
                return;
            }
            AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain();
            view.onInitializeAccessibilityEvent(accessibilityEventObtain2);
            accessibilityEventObtain2.setEventType(32);
            accessibilityEventObtain2.setContentChangeTypes(i10);
            accessibilityEventObtain2.setSource(view);
            view.onPopulateAccessibilityEvent(accessibilityEventObtain2);
            List<CharSequence> text2 = accessibilityEventObtain2.getText();
            if (i11 >= 28) {
                objB = o1.b(view);
            } else {
                Object tag3 = view.getTag(R.id.tag_accessibility_pane_title);
                if (CharSequence.class.isInstance(tag3)) {
                    objB = tag3;
                }
            }
            text2.add((CharSequence) objB);
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static n f(View view, n nVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + nVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return r1.b(view, nVar);
        }
        s0 s0Var = (s0) view.getTag(R.id.tag_on_receive_content_listener);
        t0 t0Var = f2203d;
        if (s0Var == null) {
            if (view instanceof t0) {
                t0Var = (t0) view;
            }
            return t0Var.a(nVar);
        }
        n nVarA = s0Var.a(view, nVar);
        if (nVarA == null) {
            return null;
        }
        if (view instanceof t0) {
            t0Var = (t0) view;
        }
        return t0Var.a(nVarA);
    }

    public static void g(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i10) {
        if (Build.VERSION.SDK_INT >= 29) {
            p1.d(view, context, iArr, attributeSet, typedArray, i10, 0);
        }
    }

    public static void h(View view, b bVar) {
        if (bVar == null && (c(view) instanceof a)) {
            bVar = new b();
        }
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
        }
        view.setAccessibilityDelegate(bVar == null ? null : bVar.getBridge());
    }

    public static void i(View view, CharSequence charSequence) {
        new f1(R.id.tag_accessibility_pane_title, CharSequence.class, 8, 28, 1).f(view, charSequence);
        g1 g1Var = f2204e;
        if (charSequence == null) {
            g1Var.f2235i.remove(view);
            view.removeOnAttachStateChangeListener(g1Var);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(g1Var);
        } else {
            g1Var.f2235i.put(view, Boolean.valueOf(view.isShown() && view.getWindowVisibility() == 0));
            view.addOnAttachStateChangeListener(g1Var);
            if (view.isAttachedToWindow()) {
                view.getViewTreeObserver().addOnGlobalLayoutListener(g1Var);
            }
        }
    }

    public static void j(View view, t2 t2Var) {
        if (Build.VERSION.SDK_INT >= 30) {
            z2.f(view, t2Var);
            return;
        }
        PathInterpolator pathInterpolator = w2.f2329d;
        Object tag = view.getTag(R.id.tag_on_apply_window_listener);
        if (t2Var == null) {
            view.setTag(R.id.tag_window_insets_animation_callback, null);
            if (tag == null) {
                view.setOnApplyWindowInsetsListener(null);
                return;
            }
            return;
        }
        View.OnApplyWindowInsetsListener v2Var = new v2(view, t2Var);
        view.setTag(R.id.tag_window_insets_animation_callback, v2Var);
        if (tag == null) {
            view.setOnApplyWindowInsetsListener(v2Var);
        }
    }
}
