package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import androidx.recyclerview.widget.RecyclerView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a2 {
    public static void A(StringBuilder sb2, List list, String str, List list2, String str2) {
        sb2.append(list);
        sb2.append(str);
        sb2.append(list2);
        sb2.append(str2);
    }

    public static void B(StringBuilder sb2, boolean z, String str, String str2, String str3) {
        sb2.append(z);
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
    }

    public static /* synthetic */ boolean C(List list) {
        return list != null;
    }

    public static StringBuilder D(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        sb2.append(str5);
        return sb2;
    }

    public static void E(int i10, HashMap map, String str, int i11, String str2) {
        map.put(str, Integer.valueOf(i10));
        map.put(str2, Integer.valueOf(i11));
    }

    public static /* synthetic */ String F(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "INVISIBLE" : "GONE" : "VISIBLE" : "REMOVED";
    }

    public static final void a(int i10, View view) {
        int iC = h.f0.c(i10);
        if (iC == 0) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                if (b1.G(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                }
                viewGroup.removeView(view);
                return;
            }
            return;
        }
        if (iC == 1) {
            if (b1.G(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
            }
            view.setVisibility(0);
            return;
        }
        if (iC == 2) {
            if (b1.G(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
            }
            view.setVisibility(8);
            return;
        }
        if (iC != 3) {
            return;
        }
        if (b1.G(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
        }
        view.setVisibility(4);
    }

    public static int b(int i10) {
        if (i10 == 0) {
            return 2;
        }
        if (i10 == 4) {
            return 4;
        }
        if (i10 == 8) {
            return 3;
        }
        throw new IllegalArgumentException(a0.c.i(i10, "Unknown visibility "));
    }

    public static int c(View view) {
        if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
            return 4;
        }
        return b(view.getVisibility());
    }

    public static /* synthetic */ void d(androidx.tv.foundation.lazy.list.g0 g0Var, Object obj, r7.q qVar, int i10) {
        if ((i10 & 1) != 0) {
            obj = null;
        }
        g0Var.item(obj, null, qVar);
    }

    public static int e(androidx.tv.material3.d dVar, int i10, int i11) {
        return (dVar.hashCode() + i10) * i11;
    }

    public static int f(androidx.tv.material3.s1 s1Var, int i10, int i11) {
        return (s1Var.hashCode() + i10) * i11;
    }

    public static int g(Map map, int i10, int i11) {
        return (map.hashCode() + i10) * i11;
    }

    public static Modifier h(float f10, Modifier modifier, float f11, Composer composer, int i10) {
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(modifier, f11, Dp.m5678constructorimpl(f10));
        composer.startReplaceableGroup(i10);
        return modifierM530paddingVpY3zN4;
    }

    public static String i(int i10, String str) {
        return i10 + str;
    }

    public static String j(int i10, String str, String str2) {
        return str + str2 + i10;
    }

    public static String k(RecyclerView recyclerView, StringBuilder sb2) {
        sb2.append(recyclerView.w());
        return sb2.toString();
    }

    public static String l(Object obj, String str) {
        k2.c.G(obj);
        return kotlin.text.o.L0(str).toString();
    }

    public static String m(String str, String str2, String str3, String str4, String str5) {
        return str + str2 + str3 + str4 + str5;
    }

    public static String n(StringBuilder sb2, String str, String str2, String str3, String str4) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        return sb2.toString();
    }

    public static StringBuilder o(String str, int i10, String str2, String str3, String str4) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(i10);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        return sb2;
    }

    public static StringBuilder p(String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder q(String str, String str2, String str3, int i10, String str4) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(i10);
        sb2.append(str4);
        return sb2;
    }

    public static StringBuilder r(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        sb2.append(str4);
        sb2.append(str5);
        return sb2;
    }

    public static void s(int i10, String str, String str2) {
        androidx.media3.common.util.Log.w(str2, str + i10);
    }

    public static void t(int i10, HashMap map, String str, int i11, String str2) {
        map.put(str, Integer.valueOf(i10));
        map.put(str2, Integer.valueOf(i11));
    }

    public static void u(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.verticalLineToRelative(f10);
        pathBuilder.lineTo(f11, f12);
        pathBuilder.horizontalLineTo(f13);
    }

    public static void v(String str, String str2, PrintStream printStream) {
        printStream.println(str + str2);
    }

    public static void w(String str, String str2, String str3) {
        androidx.media3.common.util.Log.w(str3, str + str2);
    }

    public static void x(String str, ArrayList arrayList) {
        arrayList.add(kotlin.text.o.L0(str).toString());
    }

    public static void y(StringBuilder sb2, int i10, String str, int i11, String str2) {
        sb2.append(i10);
        sb2.append(str);
        sb2.append(i11);
        sb2.append(str2);
    }

    public static void z(StringBuilder sb2, Integer num, String str, Integer num2, String str2) {
        sb2.append(num);
        sb2.append(str);
        sb2.append(num2);
        sb2.append(str2);
    }
}
