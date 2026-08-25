package a;

import ab.g;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.database.Cursor;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.transition.TransitionValues;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.Window;
import androidx.compose.material3.TextFieldImplKt;
import androidx.compose.material3.d;
import androidx.core.os.j;
import androidx.leanback.transition.FadeAndShortSlide;
import bb.c;
import bb.e;
import cb.f1;
import cb.x0;
import com.arvio.tv.R;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.p;
import com.google.android.gms.tasks.t;
import com.google.common.util.concurrent.g1;
import com.google.common.util.concurrent.i1;
import com.google.common.util.concurrent.l1;
import d8.q;
import g8.b1;
import gb.u0;
import io.sentry.ILogger;
import io.sentry.android.replay.g0;
import io.sentry.w5;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import ka.l;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.text.o;
import kotlinx.serialization.SerializationException;
import p9.f;
import p9.k;
import p9.n;
import retrofit2.u;
import v9.w;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements e, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f79a;

    public /* synthetic */ a(int i10) {
        this.f79a = i10;
    }

    public static final Object G(h hVar, f7.c cVar) throws Exception {
        if (!hVar.g()) {
            l lVar = new l(1, t7.a.A(cVar));
            lVar.s();
            t tVar = (t) hVar;
            tVar.f13881b.a(new p(va.a.f22264i, new u(lVar, 2)));
            tVar.n();
            return lVar.r();
        }
        Exception excE = hVar.e();
        if (excE != null) {
            throw excE;
        }
        if (!((t) hVar).f13883d) {
            return hVar.f();
        }
        throw new CancellationException("Task " + hVar + " was cancelled normally.");
    }

    public static ObjectAnimator H(View view, TransitionValues transitionValues, int i10, int i11, float f10, float f11, float f12, float f13, TimeInterpolator timeInterpolator, FadeAndShortSlide fadeAndShortSlide) {
        float f14 = f11;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) transitionValues.view.getTag(R.id.transitionPosition)) != null) {
            f10 = (r2[0] - i10) + translationX;
            f14 = (r2[1] - i11) + translationY;
        }
        int iRound = Math.round(f10 - translationX) + i10;
        int iRound2 = Math.round(f14 - translationY) + i11;
        view.setTranslationX(f10);
        view.setTranslationY(f14);
        if (f10 == f12 && f14 == f13) {
            return null;
        }
        Path path = new Path();
        path.moveTo(f10, f14);
        path.lineTo(f12, f13);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, (Property<View, Float>) View.TRANSLATION_Y, path);
        androidx.leanback.transition.h hVar = new androidx.leanback.transition.h(view, transitionValues.view, iRound, iRound2, translationX, translationY);
        fadeAndShortSlide.addListener(hVar);
        objectAnimatorOfFloat.addListener(hVar);
        objectAnimatorOfFloat.addPauseListener(hVar);
        objectAnimatorOfFloat.setInterpolator(timeInterpolator);
        return objectAnimatorOfFloat;
    }

    public static Handler I(Looper looper) {
        if (Build.VERSION.SDK_INT >= 28) {
            return j.a(looper);
        }
        try {
            return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
        } catch (IllegalAccessException e5) {
            e = e5;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (InstantiationException e6) {
            e = e6;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (NoSuchMethodException e10) {
            e = e10;
            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
            return new Handler(looper);
        } catch (InvocationTargetException e11) {
            Throwable cause = e11.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static boolean L(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static u0 M(String str) {
        int iHashCode = str.hashCode();
        if (iHashCode != 79201641) {
            if (iHashCode != 79923350) {
                switch (iHashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return u0.TLS_1_1;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return u0.TLS_1_2;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return u0.TLS_1_3;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return u0.TLS_1_0;
            }
        } else if (str.equals("SSLv3")) {
            return u0.SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: ".concat(str));
    }

    public static final androidx.work.impl.model.j N(androidx.work.impl.model.p pVar) {
        return new androidx.work.impl.model.j(pVar.f6922a, pVar.f6940t);
    }

    public static final int O(Cursor cursor, String str) {
        String strF0;
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            columnIndex = cursor.getColumnIndex("`" + str + '`');
            if (columnIndex < 0) {
                if (Build.VERSION.SDK_INT <= 25 && str.length() != 0) {
                    String[] columnNames = cursor.getColumnNames();
                    String strConcat = ".".concat(str);
                    String strI = d.i('`', ".", str);
                    int length = columnNames.length;
                    int i10 = 0;
                    int i11 = 0;
                    while (i11 < length) {
                        String str2 = columnNames[i11];
                        int i12 = i10 + 1;
                        if (str2.length() >= str.length() + 2 && (kotlin.text.u.K(str2, strConcat, false) || (str2.charAt(0) == '`' && kotlin.text.u.K(str2, strI, false)))) {
                            columnIndex = i10;
                            break;
                        }
                        i11++;
                        i10 = i12;
                    }
                    columnIndex = -1;
                } else {
                    columnIndex = -1;
                }
            }
        }
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            strF0 = r.f0(cursor.getColumnNames(), null, null, null, null, 63);
        } catch (Exception e5) {
            Log.d("RoomCursorUtil", "Cannot collect column names for debug purposes", e5);
            strF0 = "unknown";
        }
        throw new IllegalArgumentException(d.n("column '", str, "' does not exist. Available columns: ", strF0));
    }

    public static /* synthetic */ Collection P(p9.p pVar, f fVar, int i10) {
        if ((i10 & 1) != 0) {
            fVar = f.f21212m;
        }
        n.f21236a.getClass();
        return pVar.f(fVar, k.f21229l);
    }

    public static final ya.h Q(ya.h hVar) {
        return hVar.getDescriptor().b() ? hVar : new x0(hVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, x6.s] */
    public static final Window R(View view) {
        Field field;
        Object obj = g0.f16962a;
        View rootView = view.getRootView();
        Class cls = (Class) g0.f16962a.getValue();
        if (cls == null || !cls.isInstance(rootView) || (field = (Field) g0.f16963b.getValue()) == null) {
            return null;
        }
        return (Window) field.get(rootView);
    }

    public static final int S(String str) {
        int iE0;
        char c10 = File.separatorChar;
        int iE02 = o.e0(str, c10, 0, false, 4);
        if (iE02 == 0) {
            if (str.length() <= 1 || str.charAt(1) != c10 || (iE0 = o.e0(str, c10, 2, false, 4)) < 0) {
                return 1;
            }
            int iE03 = o.e0(str, c10, iE0 + 1, false, 4);
            return iE03 >= 0 ? iE03 + 1 : str.length();
        }
        if (iE02 > 0 && str.charAt(iE02 - 1) == ':') {
            return iE02 + 1;
        }
        if (iE02 == -1 && o.X(str, ':')) {
            return str.length();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static y8.y T(androidx.appcompat.view.menu.e r3, boolean r4, boolean r5, java.lang.Boolean r6, boolean r7, l8.c r8, e9.f r9) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.T(androidx.appcompat.view.menu.e, boolean, boolean, java.lang.Boolean, boolean, l8.c, e9.f):y8.y");
    }

    public static g1 U(ExecutorService executorService) {
        return executorService instanceof g1 ? (g1) executorService : executorService instanceof ScheduledExecutorService ? new l1((ScheduledExecutorService) executorService) : new i1(executorService);
    }

    public static void V(Class cls, Object obj, ILogger iLogger) {
        iLogger.q(w5.DEBUG, "%s is not %s", obj != null ? obj.getClass().getCanonicalName() : TextFieldImplKt.PlaceholderId, cls.getCanonicalName());
    }

    public static final boolean W(w wVar) {
        g8.h hVarE = wVar.I0().e();
        if (hVarE != null && ((i9.h.b(hVarE) && i9.h.e(hVarE) && !m9.d.g((g8.f) hVarE).equals(q.f14763h)) || i9.h.f(wVar))) {
            return true;
        }
        g8.h hVarE2 = wVar.I0().e();
        b1 b1Var = hVarE2 instanceof b1 ? (b1) hVarE2 : null;
        return b1Var != null && W(xc.d.n0(b1Var));
    }

    public static final l7.a X(File file) {
        List list;
        String path = file.getPath();
        int iS = S(path);
        String strSubstring = path.substring(0, iS);
        String strSubstring2 = path.substring(iS);
        if (strSubstring2.length() == 0) {
            list = z.f19728i;
        } else {
            List listX0 = o.x0(strSubstring2, new char[]{File.separatorChar}, 6);
            ArrayList arrayList = new ArrayList(s.U(listX0, 10));
            Iterator it = listX0.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            list = arrayList;
        }
        return new l7.a(new File(strSubstring), list);
    }

    public static void Y(Parcel parcel, int i10, Boolean bool) {
        if (bool == null) {
            return;
        }
        l0(parcel, i10, 4);
        parcel.writeInt(bool.booleanValue() ? 1 : 0);
    }

    public static void Z(Parcel parcel, int i10, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeBundle(bundle);
            n0(iM0, parcel);
        }
    }

    public static void a0(Parcel parcel, int i10, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeByteArray(bArr);
            n0(iM0, parcel);
        }
    }

    public static void b0(Parcel parcel, int i10, IBinder iBinder) {
        if (iBinder == null) {
            return;
        }
        int iM0 = m0(i10, parcel);
        parcel.writeStrongBinder(iBinder);
        n0(iM0, parcel);
    }

    public static void c0(Parcel parcel, int i10, int[] iArr, boolean z) {
        if (iArr == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeIntArray(iArr);
            n0(iM0, parcel);
        }
    }

    public static void d0(Parcel parcel, int i10, Integer num) {
        if (num == null) {
            return;
        }
        l0(parcel, i10, 4);
        parcel.writeInt(num.intValue());
    }

    public static void e0(Parcel parcel, int i10, long[] jArr, boolean z) {
        if (jArr == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeLongArray(jArr);
            n0(iM0, parcel);
        }
    }

    public static void f0(Parcel parcel, int i10, Parcelable parcelable, int i11, boolean z) {
        if (parcelable == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcelable.writeToParcel(parcel, i11);
            n0(iM0, parcel);
        }
    }

    public static void g0(Parcel parcel, int i10, String str, boolean z) {
        if (str == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeString(str);
            n0(iM0, parcel);
        }
    }

    public static void h0(Parcel parcel, int i10, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                l0(parcel, i10, 0);
            }
        } else {
            int iM0 = m0(i10, parcel);
            parcel.writeStringArray(strArr);
            n0(iM0, parcel);
        }
    }

    public static void i0(Parcel parcel, List list, int i10) {
        if (list == null) {
            return;
        }
        int iM0 = m0(i10, parcel);
        parcel.writeStringList(list);
        n0(iM0, parcel);
    }

    public static void j0(Parcel parcel, int i10, Parcelable[] parcelableArr, int i11) {
        if (parcelableArr == null) {
            return;
        }
        int iM0 = m0(i10, parcel);
        parcel.writeInt(parcelableArr.length);
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                int iDataPosition = parcel.dataPosition();
                parcel.writeInt(1);
                int iDataPosition2 = parcel.dataPosition();
                parcelable.writeToParcel(parcel, i11);
                int iDataPosition3 = parcel.dataPosition();
                parcel.setDataPosition(iDataPosition);
                parcel.writeInt(iDataPosition3 - iDataPosition2);
                parcel.setDataPosition(iDataPosition3);
            }
        }
        n0(iM0, parcel);
    }

    public static void k0(Parcel parcel, int i10, List list, boolean z) {
        if (list == null) {
            if (z) {
                l0(parcel, i10, 0);
                return;
            }
            return;
        }
        int iM0 = m0(i10, parcel);
        int size = list.size();
        parcel.writeInt(size);
        for (int i11 = 0; i11 < size; i11++) {
            Parcelable parcelable = (Parcelable) list.get(i11);
            if (parcelable == null) {
                parcel.writeInt(0);
            } else {
                int iDataPosition = parcel.dataPosition();
                parcel.writeInt(1);
                int iDataPosition2 = parcel.dataPosition();
                parcelable.writeToParcel(parcel, 0);
                int iDataPosition3 = parcel.dataPosition();
                parcel.setDataPosition(iDataPosition);
                parcel.writeInt(iDataPosition3 - iDataPosition2);
                parcel.setDataPosition(iDataPosition3);
            }
        }
        n0(iM0, parcel);
    }

    public static void l0(Parcel parcel, int i10, int i11) {
        parcel.writeInt(i10 | (i11 << 16));
    }

    public static int m0(int i10, Parcel parcel) {
        parcel.writeInt(i10 | (-65536));
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    public static void n0(int i10, Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        parcel.setDataPosition(i10 - 4);
        parcel.writeInt(iDataPosition - i10);
        parcel.setDataPosition(iDataPosition);
    }

    @Override // bb.e
    public void A(int i10) {
        K(Integer.valueOf(i10));
    }

    @Override // bb.c
    public void B(g gVar, int i10, float f10) {
        J(gVar, i10);
        w(f10);
    }

    @Override // bb.c
    public boolean C() {
        return true;
    }

    @Override // bb.c
    public void D(f1 f1Var, int i10, short s10) {
        J(f1Var, i10);
        s(s10);
    }

    @Override // bb.e
    public void E(String str) {
        K(str);
    }

    public abstract String F();

    public void K(Object obj) {
        StringBuilder sb2 = new StringBuilder("Non-serializable ");
        Class<?> cls = obj.getClass();
        m0 m0Var = l0.f19747a;
        sb2.append(m0Var.b(cls));
        sb2.append(" is not supported by ");
        sb2.append(m0Var.b(getClass()));
        sb2.append(" encoder");
        throw new SerializationException(sb2.toString());
    }

    @Override // bb.c
    public e c(f1 f1Var, int i10) {
        J(f1Var, i10);
        return k(f1Var.g(i10));
    }

    @Override // bb.c
    public void d(g gVar, int i10, boolean z) {
        J(gVar, i10);
        t(z);
    }

    @Override // bb.e
    public void f(double d4) {
        K(Double.valueOf(d4));
    }

    @Override // bb.c
    public void g(g gVar, int i10, long j10) {
        J(gVar, i10);
        p(j10);
    }

    @Override // bb.e
    public void h(byte b10) {
        K(Byte.valueOf(b10));
    }

    public int hashCode() {
        switch (this.f79a) {
            case 2:
                return toString().hashCode();
            default:
                return super.hashCode();
        }
    }

    @Override // bb.e
    public void i(g gVar, int i10) {
        K(Integer.valueOf(i10));
    }

    @Override // bb.c
    public void j(g gVar, int i10, ya.s sVar, Object obj) {
        J(gVar, i10);
        z(sVar, obj);
    }

    @Override // bb.c
    public void m(g gVar, int i10, ya.s sVar, Object obj) {
        J(gVar, i10);
        if (sVar.getDescriptor().b()) {
            z(sVar, obj);
        } else if (obj == null) {
            q();
        } else {
            z(sVar, obj);
        }
    }

    @Override // bb.c
    public void n(f1 f1Var, int i10, byte b10) {
        J(f1Var, i10);
        h(b10);
    }

    @Override // bb.c
    public void o(f1 f1Var, int i10, char c10) {
        J(f1Var, i10);
        y(c10);
    }

    @Override // bb.e
    public void p(long j10) {
        K(Long.valueOf(j10));
    }

    @Override // bb.e
    public void q() {
        throw new SerializationException("'null' is not supported by default");
    }

    @Override // bb.c
    public void r(int i10, int i11, g gVar) {
        J(gVar, i10);
        A(i11);
    }

    @Override // bb.e
    public void s(short s10) {
        K(Short.valueOf(s10));
    }

    @Override // bb.e
    public void t(boolean z) {
        K(Boolean.valueOf(z));
    }

    public String toString() {
        switch (this.f79a) {
            case 2:
                return l0.f19747a.b(getClass()).r();
            case 9:
                return F();
            default:
                return super.toString();
        }
    }

    @Override // bb.c
    public void u(f1 f1Var, int i10, double d4) {
        J(f1Var, i10);
        f(d4);
    }

    @Override // bb.e
    public c v(g gVar) {
        return b(gVar);
    }

    @Override // bb.e
    public void w(float f10) {
        K(Float.valueOf(f10));
    }

    @Override // bb.c
    public void x(g gVar, int i10, String str) {
        J(gVar, i10);
        E(str);
    }

    @Override // bb.e
    public void y(char c10) {
        K(Character.valueOf(c10));
    }

    @Override // bb.e
    public void z(ya.s sVar, Object obj) {
        sVar.serialize(this, obj);
    }

    @Override // bb.c
    public void e() {
    }

    @Override // bb.e
    public c b(g gVar) {
        return this;
    }

    @Override // bb.e
    public e k(g gVar) {
        return this;
    }

    public void J(g gVar, int i10) {
    }
}
