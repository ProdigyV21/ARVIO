package kb;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.view.s;
import androidx.core.widget.NestedScrollView;
import androidx.profileinstaller.ProfileInstallReceiver;
import com.arflix.tv.MainActivity;
import com.arvio.tv.R;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.common.api.internal.t;
import com.google.android.gms.common.api.n;
import com.google.android.gms.common.internal.a0;
import com.google.android.gms.internal.auth.l4;
import com.google.android.gms.tasks.i;
import g8.f;
import g8.h;
import g8.h0;
import g8.l0;
import g8.v0;
import g8.y;
import gb.s0;
import gb.t0;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.collections.x;
import kotlin.reflect.m;
import m4.b0;
import m4.c0;
import m4.r;
import m4.w;
import m8.o;
import retrofit2.l;
import s8.g;
import t8.p;
import u9.u;
import w5.k;
import w5.z;
import y5.j;

/* JADX INFO: loaded from: classes5.dex */
public class d implements c, t, l, g, s9.e, s, u, v1.e, k, j, v0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19672i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f19673l;

    public /* synthetic */ d(n nVar, t3.a aVar, int i10) {
        this.f19672i = i10;
        this.f19673l = aVar;
    }

    @Override // s8.g
    public f a(w8.g gVar) {
        switch (this.f19672i) {
            case 12:
                f9.c cVarC = gVar.c();
                o oVarN = gVar.n();
                if (oVarN != null) {
                    f fVarA = a(oVarN);
                    p9.n nVarR = fVarA != null ? fVarA.R() : null;
                    h hVarE = nVarR != null ? nVarR.e(gVar.getName(), o8.e.f20800r) : null;
                    if (hVarE instanceof f) {
                        return (f) hVarE;
                    }
                    return null;
                }
                p pVar = (p) x.o0(((s8.f) this.f19673l).c(cVarC.e()));
                if (pVar == null) {
                    return null;
                }
                t8.u uVar = pVar.f21964t.f21912d;
                uVar.getClass();
                return uVar.v(gVar.getName(), gVar);
            default:
                d dVar = (d) this.f19673l;
                if (dVar != null) {
                    return dVar.a(gVar);
                }
                kotlin.jvm.internal.p.i("resolver");
                throw null;
        }
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        switch (this.f19672i) {
            case 5:
                m4.c cVar = new m4.c((i) obj2);
                w wVar = (w) ((m4.s) obj).getService();
                SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest = (SaveAccountLinkingTokenRequest) this.f19673l;
                com.google.android.gms.common.internal.t.i(saveAccountLinkingTokenRequest);
                Parcel parcelZba = wVar.zba();
                int i10 = m4.n.f20244a;
                parcelZba.writeStrongBinder(cVar.asBinder());
                m4.n.c(parcelZba, saveAccountLinkingTokenRequest);
                wVar.zbb(1, parcelZba);
                return;
            case 6:
                m4.d dVar = new m4.d((i) obj2);
                w wVar2 = (w) ((m4.s) obj).getService();
                SavePasswordRequest savePasswordRequest = (SavePasswordRequest) this.f19673l;
                com.google.android.gms.common.internal.t.i(savePasswordRequest);
                Parcel parcelZba2 = wVar2.zba();
                int i11 = m4.n.f20244a;
                parcelZba2.writeStrongBinder(dVar.asBinder());
                m4.n.c(parcelZba2, savePasswordRequest);
                wVar2.zbb(2, parcelZba2);
                return;
            case 7:
                m4.i iVar = (m4.i) this.f19673l;
                l4 l4Var = new l4(5, (i) obj2);
                b0 b0Var = (b0) ((m4.j) obj).getService();
                String str = iVar.f20234a;
                Parcel parcelZba3 = b0Var.zba();
                int i12 = m4.n.f20244a;
                parcelZba3.writeStrongBinder(l4Var.asBinder());
                parcelZba3.writeString(str);
                b0Var.zbb(2, parcelZba3);
                return;
            case 8:
                m4.f fVar = new m4.f((i) obj2);
                b0 b0Var2 = (b0) ((m4.j) obj).getService();
                BeginSignInRequest beginSignInRequest = (BeginSignInRequest) this.f19673l;
                com.google.android.gms.common.internal.t.i(beginSignInRequest);
                Parcel parcelZba4 = b0Var2.zba();
                int i13 = m4.n.f20244a;
                parcelZba4.writeStrongBinder(fVar.asBinder());
                m4.n.c(parcelZba4, beginSignInRequest);
                b0Var2.zbb(1, parcelZba4);
                return;
            case 9:
                m4.g gVar = new m4.g((i) obj2);
                b0 b0Var3 = (b0) ((m4.j) obj).getService();
                GetSignInIntentRequest getSignInIntentRequest = (GetSignInIntentRequest) this.f19673l;
                com.google.android.gms.common.internal.t.i(getSignInIntentRequest);
                Parcel parcelZba5 = b0Var3.zba();
                int i14 = m4.n.f20244a;
                parcelZba5.writeStrongBinder(gVar.asBinder());
                m4.n.c(parcelZba5, getSignInIntentRequest);
                b0Var3.zbb(3, parcelZba5);
                return;
            case 10:
                c0 c0Var = new c0((i) obj2);
                m4.u uVar = (m4.u) ((r) obj).getService();
                AuthorizationRequest authorizationRequest = (AuthorizationRequest) this.f19673l;
                com.google.android.gms.common.internal.t.i(authorizationRequest);
                Parcel parcelZba6 = uVar.zba();
                int i15 = m4.n.f20244a;
                parcelZba6.writeStrongBinder(c0Var.asBinder());
                m4.n.c(parcelZba6, authorizationRequest);
                uVar.zbb(1, parcelZba6);
                return;
            default:
                i iVar2 = (i) obj2;
                com.google.android.gms.common.api.i iVar3 = u3.c.f22042a;
                u3.a aVar = (u3.a) ((u3.d) obj).getService();
                a0 a0Var = (a0) this.f19673l;
                aVar.getClass();
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.writeInterfaceToken(aVar.f20769e);
                int i16 = o4.c.f20770a;
                if (a0Var == null) {
                    parcelObtain.writeInt(0);
                } else {
                    parcelObtain.writeInt(1);
                    a0Var.writeToParcel(parcelObtain, 0);
                }
                try {
                    aVar.f20768d.transact(1, parcelObtain, null, 1);
                    parcelObtain.recycle();
                    iVar2.b(null);
                    return;
                } catch (Throwable th) {
                    parcelObtain.recycle();
                    throw th;
                }
        }
    }

    @Override // v1.e
    public void b() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // s9.e
    public s9.d c(f9.b bVar) {
        s9.d dVarC;
        l0 l0Var = (l0) this.f19673l;
        f9.c cVarG = bVar.g();
        ArrayList<h0> arrayList = new ArrayList();
        y.b(l0Var, cVarG, arrayList);
        for (h0 h0Var : arrayList) {
            if ((h0Var instanceof t9.c) && (dVarC = ((t9.c) h0Var).f22003s.c(bVar)) != null) {
                return dVarC;
            }
        }
        return null;
    }

    @Override // v1.e
    public void d(int i10, Object obj) {
        String str;
        switch (i10) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i10 == 6 || i10 == 7 || i10 == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.f19673l).setResultCode(i10);
    }

    public m2.h g() throws Throwable {
        m2.h hVar = new m2.h((HashMap) this.f19673l);
        m2.h.b(hVar);
        return hVar;
    }

    public synchronized void h(t0 t0Var) {
        ((LinkedHashSet) this.f19673l).remove(t0Var);
    }

    public void i(kotlin.reflect.jvm.internal.impl.protobuf.g gVar) {
        if (!gVar.f()) {
            if (!(gVar instanceof kotlin.reflect.jvm.internal.impl.protobuf.h0)) {
                String strValueOf = String.valueOf(gVar.getClass());
                throw new IllegalArgumentException(a0.c.p(new StringBuilder(strValueOf.length() + 49), "Has a new type of ByteString been created? Found ", strValueOf));
            }
            kotlin.reflect.jvm.internal.impl.protobuf.h0 h0Var = (kotlin.reflect.jvm.internal.impl.protobuf.h0) gVar;
            i(h0Var.f19778m);
            i(h0Var.f19779n);
            return;
        }
        int size = gVar.size();
        int[] iArr = kotlin.reflect.jvm.internal.impl.protobuf.h0.f19776r;
        int iBinarySearch = Arrays.binarySearch(iArr, size);
        if (iBinarySearch < 0) {
            iBinarySearch = (-(iBinarySearch + 1)) - 1;
        }
        int i10 = iArr[iBinarySearch + 1];
        Stack stack = (Stack) this.f19673l;
        if (stack.isEmpty() || ((kotlin.reflect.jvm.internal.impl.protobuf.g) stack.peek()).size() >= i10) {
            stack.push(gVar);
            return;
        }
        int i11 = iArr[iBinarySearch];
        kotlin.reflect.jvm.internal.impl.protobuf.g h0Var2 = (kotlin.reflect.jvm.internal.impl.protobuf.g) stack.pop();
        while (!stack.isEmpty() && ((kotlin.reflect.jvm.internal.impl.protobuf.g) stack.peek()).size() < i11) {
            h0Var2 = new kotlin.reflect.jvm.internal.impl.protobuf.h0((kotlin.reflect.jvm.internal.impl.protobuf.g) stack.pop(), h0Var2);
        }
        kotlin.reflect.jvm.internal.impl.protobuf.h0 h0Var3 = new kotlin.reflect.jvm.internal.impl.protobuf.h0(h0Var2, gVar);
        while (!stack.isEmpty()) {
            int[] iArr2 = kotlin.reflect.jvm.internal.impl.protobuf.h0.f19776r;
            int iBinarySearch2 = Arrays.binarySearch(iArr2, h0Var3.f19777l);
            if (iBinarySearch2 < 0) {
                iBinarySearch2 = (-(iBinarySearch2 + 1)) - 1;
            }
            if (((kotlin.reflect.jvm.internal.impl.protobuf.g) stack.peek()).size() >= iArr2[iBinarySearch2 + 1]) {
                break;
            } else {
                h0Var3 = new kotlin.reflect.jvm.internal.impl.protobuf.h0((kotlin.reflect.jvm.internal.impl.protobuf.g) stack.pop(), h0Var3);
            }
        }
        stack.push(h0Var3);
    }

    public void j(androidx.leanback.widget.u uVar) {
        ((ThreadPoolExecutor) this.f19673l).execute(uVar);
    }

    public Boolean k() {
        Bundle bundle = (Bundle) this.f19673l;
        if (bundle.containsKey("firebase_sessions_enabled")) {
            return Boolean.valueOf(bundle.getBoolean("firebase_sessions_enabled"));
        }
        return null;
    }

    public void l() {
        int i10;
        TypedValue typedValue = new TypedValue();
        MainActivity mainActivity = (MainActivity) this.f19673l;
        Resources.Theme theme = mainActivity.getTheme();
        theme.resolveAttribute(R.attr.windowSplashScreenBackground, typedValue, true);
        if (theme.resolveAttribute(R.attr.windowSplashScreenAnimatedIcon, typedValue, true)) {
            theme.getDrawable(typedValue.resourceId);
        }
        theme.resolveAttribute(R.attr.splashScreenIconSize, typedValue, true);
        if (!theme.resolveAttribute(R.attr.postSplashScreenTheme, typedValue, true) || (i10 = typedValue.resourceId) == 0) {
            return;
        }
        mainActivity.setTheme(i10);
    }

    @Override // u9.u
    public void lock() {
        ((ReentrantLock) this.f19673l).lock();
    }

    public void m(z zVar) {
        ((z2.f) ((m5.b) this.f19673l).get()).a("FIREBASE_APPQUALITY_SESSION", new z2.b("json"), new retrofit2.l0(this)).b(new z2.a(null, zVar, z2.c.f23164i), new androidx.media3.extractor.mp4.b(23));
    }

    public void n(Object obj, String str) {
        HashMap map = (HashMap) this.f19673l;
        if (obj == null) {
            map.put(str, null);
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls == Boolean.class || cls == Byte.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Byte[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
            map.put(str, obj);
            return;
        }
        int i10 = 0;
        if (cls == boolean[].class) {
            boolean[] zArr = (boolean[]) obj;
            String str2 = m2.h.f20201b;
            Boolean[] boolArr = new Boolean[zArr.length];
            while (i10 < zArr.length) {
                boolArr[i10] = Boolean.valueOf(zArr[i10]);
                i10++;
            }
            map.put(str, boolArr);
            return;
        }
        if (cls == byte[].class) {
            byte[] bArr = (byte[]) obj;
            String str3 = m2.h.f20201b;
            Byte[] bArr2 = new Byte[bArr.length];
            while (i10 < bArr.length) {
                bArr2[i10] = Byte.valueOf(bArr[i10]);
                i10++;
            }
            map.put(str, bArr2);
            return;
        }
        if (cls == int[].class) {
            int[] iArr = (int[]) obj;
            String str4 = m2.h.f20201b;
            Integer[] numArr = new Integer[iArr.length];
            while (i10 < iArr.length) {
                numArr[i10] = Integer.valueOf(iArr[i10]);
                i10++;
            }
            map.put(str, numArr);
            return;
        }
        if (cls == long[].class) {
            long[] jArr = (long[]) obj;
            String str5 = m2.h.f20201b;
            Long[] lArr = new Long[jArr.length];
            while (i10 < jArr.length) {
                lArr[i10] = Long.valueOf(jArr[i10]);
                i10++;
            }
            map.put(str, lArr);
            return;
        }
        if (cls == float[].class) {
            float[] fArr = (float[]) obj;
            String str6 = m2.h.f20201b;
            Float[] fArr2 = new Float[fArr.length];
            while (i10 < fArr.length) {
                fArr2[i10] = Float.valueOf(fArr[i10]);
                i10++;
            }
            map.put(str, fArr2);
            return;
        }
        if (cls != double[].class) {
            throw new IllegalArgumentException("Key " + str + " has invalid type " + cls);
        }
        double[] dArr = (double[]) obj;
        String str7 = m2.h.f20201b;
        Double[] dArr2 = new Double[dArr.length];
        while (i10 < dArr.length) {
            dArr2[i10] = Double.valueOf(dArr[i10]);
            i10++;
        }
        map.put(str, dArr2);
    }

    public void o(HashMap map) {
        for (Map.Entry entry : map.entrySet()) {
            n(entry.getValue(), (String) entry.getKey());
        }
    }

    public void p() {
        ((NestedScrollView) this.f19673l).f2345n.abortAnimation();
    }

    public String toString() {
        switch (this.f19672i) {
            case 23:
                StringBuilder sb2 = new StringBuilder();
                p pVar = (p) this.f19673l;
                sb2.append(pVar);
                sb2.append(": ");
                u9.s sVar = pVar.f21963s;
                m mVar = p.f21960w[0];
                sb2.append(((Map) sVar.invoke()).keySet());
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    @Override // u9.u
    public void unlock() {
        ((ReentrantLock) this.f19673l).unlock();
    }

    @Override // retrofit2.l
    public Object w(Object obj) {
        return Optional.ofNullable(((l) this.f19673l).w((s0) obj));
    }

    public /* synthetic */ d(Object obj, int i10) {
        this.f19672i = i10;
        this.f19673l = obj;
    }

    public d(Context context) {
        this.f19672i = 21;
        Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        this.f19673l = bundle == null ? Bundle.EMPTY : bundle;
    }

    public d(ib.b bVar) {
        this.f19672i = 0;
        this.f19673l = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), bVar);
    }

    public d(int i10) {
        this.f19672i = i10;
        switch (i10) {
            case 3:
                this.f19673l = new LinkedHashSet();
                break;
            case 4:
                this.f19673l = new HashMap();
                break;
            case 14:
                break;
            default:
                this.f19673l = new Stack();
                break;
        }
    }
}
