package androidx.appcompat.app;

import android.accounts.Account;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.a2;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.p1;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import com.google.android.gms.internal.auth.e4;
import com.google.android.gms.internal.auth.i4;
import com.google.android.gms.internal.auth.k4;
import com.google.firebase.encoders.EncodingException;
import com.typesafe.config.impl.m1;
import com.typesafe.config.impl.n1;
import com.typesafe.config.impl.o1;
import io.sentry.a4;
import io.sentry.b3;
import io.sentry.b4;
import io.sentry.c4;
import io.sentry.g4;
import io.sentry.j5;
import io.sentry.j7;
import io.sentry.o7;
import io.sentry.u7;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.Adler32;

/* JADX INFO: loaded from: classes.dex */
public class i1 implements com.google.android.gms.common.api.internal.t, com.typesafe.config.impl.d, io.sentry.a1, k3.j {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static i1 f1057o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1058i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f1059l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f1060m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f1061n;

    public /* synthetic */ i1(com.google.android.gms.internal.auth.c cVar, Account account, String str, Bundle bundle) {
        this.f1058i = 10;
        this.f1059l = account;
        this.f1060m = str;
        this.f1061n = bundle;
    }

    public static i1 Z(Context context, AttributeSet attributeSet, int[] iArr, int i10) {
        return new i1(context, context.obtainStyledAttributes(attributeSet, iArr, i10, 0));
    }

    @Override // io.sentry.a1
    public io.sentry.protocol.i0 A() {
        io.sentry.protocol.i0 i0VarA = ((io.sentry.a1) this.f1061n).A();
        if (i0VarA != null) {
            return i0VarA;
        }
        io.sentry.protocol.i0 i0VarA2 = ((io.sentry.a1) this.f1060m).A();
        return i0VarA2 != null ? i0VarA2 : ((io.sentry.a1) this.f1059l).A();
    }

    @Override // io.sentry.a1
    public String B() {
        String strB = ((io.sentry.a1) this.f1061n).B();
        if (strB != null) {
            return strB;
        }
        String strB2 = ((io.sentry.a1) this.f1060m).B();
        return strB2 != null ? strB2 : ((io.sentry.a1) this.f1059l).B();
    }

    @Override // io.sentry.a1
    public void C() {
        X(null).C();
    }

    @Override // io.sentry.a1
    public io.sentry.featureflags.b D() {
        y6 options = ((io.sentry.a1) this.f1059l).getOptions();
        io.sentry.featureflags.b bVarD = ((io.sentry.a1) this.f1059l).D();
        io.sentry.featureflags.b bVarD2 = ((io.sentry.a1) this.f1060m).D();
        io.sentry.featureflags.b bVarD3 = ((io.sentry.a1) this.f1061n).D();
        io.sentry.featureflags.c cVar = io.sentry.featureflags.c.f17287i;
        int maxFeatureFlags = options.getMaxFeatureFlags();
        if (maxFeatureFlags > 0) {
            io.sentry.featureflags.a aVar = bVarD instanceof io.sentry.featureflags.a ? (io.sentry.featureflags.a) bVarD : null;
            io.sentry.featureflags.a aVar2 = bVarD2 instanceof io.sentry.featureflags.a ? (io.sentry.featureflags.a) bVarD2 : null;
            io.sentry.featureflags.a aVar3 = bVarD3 instanceof io.sentry.featureflags.a ? (io.sentry.featureflags.a) bVarD3 : null;
            CopyOnWriteArrayList copyOnWriteArrayList = aVar == null ? null : aVar.f17286i;
            CopyOnWriteArrayList copyOnWriteArrayList2 = aVar2 == null ? null : aVar2.f17286i;
            CopyOnWriteArrayList copyOnWriteArrayList3 = aVar3 != null ? aVar3.f17286i : null;
            int size = copyOnWriteArrayList == null ? 0 : copyOnWriteArrayList.size();
            int size2 = copyOnWriteArrayList2 == null ? 0 : copyOnWriteArrayList2.size();
            int size3 = copyOnWriteArrayList3 != null ? copyOnWriteArrayList3.size() : 0;
            if (size != 0 || size2 != 0 || size3 != 0) {
                int i10 = size - 1;
                int i11 = size2 - 1;
                int i12 = size3 - 1;
                if (copyOnWriteArrayList != null && i10 >= 0 && copyOnWriteArrayList.get(i10) != null) {
                    throw new ClassCastException();
                }
                if (copyOnWriteArrayList2 != null && i11 >= 0 && copyOnWriteArrayList2.get(i11) != null) {
                    throw new ClassCastException();
                }
                if (copyOnWriteArrayList3 != null && i12 >= 0 && copyOnWriteArrayList3.get(i12) != null) {
                    throw new ClassCastException();
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(maxFeatureFlags);
                linkedHashMap.size();
                ArrayList arrayList = new ArrayList(linkedHashMap.values());
                Collections.reverse(arrayList);
                CopyOnWriteArrayList copyOnWriteArrayList4 = new CopyOnWriteArrayList(arrayList);
                io.sentry.featureflags.a aVar4 = new io.sentry.featureflags.a();
                new io.sentry.util.b();
                aVar4.f17286i = copyOnWriteArrayList4;
                return aVar4;
            }
        }
        return cVar;
    }

    @Override // io.sentry.a1
    public j7 E() {
        j7 j7VarE = ((io.sentry.a1) this.f1061n).E();
        if (j7VarE != null) {
            return j7VarE;
        }
        j7 j7VarE2 = ((io.sentry.a1) this.f1060m).E();
        return j7VarE2 != null ? j7VarE2 : ((io.sentry.a1) this.f1059l).E();
    }

    @Override // io.sentry.a1
    public i1 F() {
        return X(null).F();
    }

    @Override // io.sentry.a1
    public void G(String str) {
        X(null).G(str);
    }

    @Override // io.sentry.a1
    public void H(i1 i1Var) {
        X(null).H(i1Var);
    }

    @Override // io.sentry.a1
    public io.sentry.f1 I() {
        io.sentry.f1 f1VarI = ((io.sentry.a1) this.f1061n).I();
        if (!(f1VarI instanceof b3)) {
            return f1VarI;
        }
        io.sentry.f1 f1VarI2 = ((io.sentry.a1) this.f1060m).I();
        return !(f1VarI2 instanceof b3) ? f1VarI2 : ((io.sentry.a1) this.f1059l).I();
    }

    @Override // io.sentry.a1
    public List J() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1059l).J());
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1060m).J());
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1061n).J());
        return copyOnWriteArrayList;
    }

    @Override // io.sentry.a1
    public void K(j5 j5Var) {
        ((io.sentry.a1) this.f1059l).K(j5Var);
    }

    @Override // com.typesafe.config.impl.d
    public com.typesafe.config.impl.g L(com.typesafe.config.impl.g gVar, String str) {
        com.typesafe.config.impl.g1 g1Var = (com.typesafe.config.impl.g1) this.f1059l;
        o1 o1Var = (o1) this.f1061n;
        m1 m1Var = (m1) this.f1060m;
        com.typesafe.config.impl.g1 g1Var2 = m1Var.f14578c;
        if (g1Var2 == null) {
            n1 n1VarC = m1Var.d(null).c(gVar, o1Var);
            this.f1060m = n1VarC.f14593a.d(null).d(g1Var);
            return n1VarC.f14594b;
        }
        if (!str.equals(g1Var2.f14541a)) {
            return gVar;
        }
        m1 m1Var2 = (m1) this.f1060m;
        com.typesafe.config.impl.g1 g1Var3 = m1Var2.f14578c.f14542b;
        if (g1Var3 == null) {
            return gVar;
        }
        n1 n1VarC2 = m1Var2.d(g1Var3).c(gVar, o1Var);
        this.f1060m = n1VarC2.f14593a.d(null).d(g1Var);
        return n1VarC2.f14594b;
    }

    @Override // io.sentry.a1
    public i1 M(z3 z3Var) {
        return X(null).M(z3Var);
    }

    @Override // io.sentry.a1
    public void N(b4 b4Var) {
        X(null).N(b4Var);
    }

    @Override // io.sentry.a1
    public void O(io.sentry.protocol.v vVar) {
        ((io.sentry.a1) this.f1059l).O(vVar);
        ((io.sentry.a1) this.f1060m).O(vVar);
        ((io.sentry.a1) this.f1061n).O(vVar);
    }

    @Override // io.sentry.a1
    public List P() {
        return kotlin.reflect.b0.K((CopyOnWriteArrayList) v());
    }

    public View Q(int i10) {
        return ((androidx.recyclerview.widget.j0) this.f1059l).f4952a.getChildAt(W(i10));
    }

    public int R() {
        return ((androidx.recyclerview.widget.j0) this.f1059l).f4952a.getChildCount() - ((ArrayList) this.f1061n).size();
    }

    public ColorStateList S(int i10) {
        int resourceId;
        ColorStateList colorStateListC;
        TypedArray typedArray = (TypedArray) this.f1060m;
        return (!typedArray.hasValue(i10) || (resourceId = typedArray.getResourceId(i10, 0)) == 0 || (colorStateListC = qb.d.C((Context) this.f1059l, resourceId)) == null) ? typedArray.getColorStateList(i10) : colorStateListC;
    }

    public Drawable T(int i10) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.f1060m;
        return (!typedArray.hasValue(i10) || (resourceId = typedArray.getResourceId(i10, 0)) == 0) ? typedArray.getDrawable(i10) : m2.f0.o((Context) this.f1059l, resourceId);
    }

    public Drawable U(int i10) {
        int resourceId;
        Drawable drawableF;
        if (!((TypedArray) this.f1060m).hasValue(i10) || (resourceId = ((TypedArray) this.f1060m).getResourceId(i10, 0)) == 0) {
            return null;
        }
        androidx.appcompat.widget.a0 a0VarA = androidx.appcompat.widget.a0.a();
        Context context = (Context) this.f1059l;
        synchronized (a0VarA) {
            drawableF = a0VarA.f1504a.f(context, resourceId, true);
        }
        return drawableF;
    }

    public Typeface V(int i10, int i11, androidx.appcompat.widget.e1 e1Var) {
        int resourceId = ((TypedArray) this.f1060m).getResourceId(i10, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.f1061n) == null) {
            this.f1061n = new TypedValue();
        }
        Context context = (Context) this.f1059l;
        TypedValue typedValue = (TypedValue) this.f1061n;
        ThreadLocal threadLocal = i0.q.f16004a;
        if (context.isRestricted()) {
            return null;
        }
        return i0.q.c(context, resourceId, typedValue, i11, e1Var, true);
    }

    public int W(int i10) {
        androidx.recyclerview.widget.e eVar = (androidx.recyclerview.widget.e) this.f1060m;
        if (i10 < 0) {
            return -1;
        }
        int childCount = ((androidx.recyclerview.widget.j0) this.f1059l).f4952a.getChildCount();
        int i11 = i10;
        while (i11 < childCount) {
            int iE = i10 - (i11 - eVar.e(i11));
            if (iE == 0) {
                while (eVar.g(i11)) {
                    i11++;
                }
                return i11;
            }
            i11 += iE;
        }
        return -1;
    }

    public io.sentry.a1 X(g4 g4Var) {
        io.sentry.a1 a1Var = (io.sentry.a1) this.f1060m;
        io.sentry.a1 a1Var2 = (io.sentry.a1) this.f1061n;
        io.sentry.a1 a1Var3 = (io.sentry.a1) this.f1059l;
        if (g4Var != null) {
            int i10 = io.sentry.k.f17416a[g4Var.ordinal()];
            if (i10 == 1) {
                return a1Var2;
            }
            if (i10 == 2) {
                return a1Var;
            }
            if (i10 == 3) {
                return a1Var3;
            }
            if (i10 == 4) {
                return this;
            }
        }
        int i11 = io.sentry.k.f17416a[a1Var3.getOptions().getDefaultScopeType().ordinal()];
        return i11 != 1 ? i11 != 2 ? i11 != 3 ? a1Var2 : a1Var3 : a1Var : a1Var2;
    }

    public int Y(View view) {
        androidx.recyclerview.widget.e eVar = (androidx.recyclerview.widget.e) this.f1060m;
        int iIndexOfChild = ((androidx.recyclerview.widget.j0) this.f1059l).f4952a.indexOfChild(view);
        if (iIndexOfChild == -1 || eVar.g(iIndexOfChild)) {
            return -1;
        }
        return iIndexOfChild - eVar.e(iIndexOfChild);
    }

    @Override // k3.j
    public void a(com.google.android.datatransport.runtime.i iVar, int i10) {
        b(iVar, i10, false);
    }

    public void a0(androidx.lifecycle.p pVar) {
        androidx.fragment.app.j jVar = (androidx.fragment.app.j) this.f1061n;
        if (jVar != null) {
            jVar.run();
        }
        androidx.fragment.app.j jVar2 = new androidx.fragment.app.j((androidx.lifecycle.a0) this.f1059l, pVar);
        this.f1061n = jVar2;
        ((Handler) this.f1060m).postAtFrontOfQueue(jVar2);
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        Account account = (Account) this.f1059l;
        String str = (String) this.f1060m;
        Bundle bundle = (Bundle) this.f1061n;
        i4 i4Var = (i4) ((e4) obj).getService();
        k4 k4Var = new k4((com.google.android.gms.tasks.i) obj2);
        Parcel parcelZza = i4Var.zza();
        com.google.android.gms.internal.auth.o0.d(parcelZza, k4Var);
        com.google.android.gms.internal.auth.o0.c(parcelZza, account);
        parcelZza.writeString(str);
        com.google.android.gms.internal.auth.o0.c(parcelZza, bundle);
        i4Var.zzc(1, parcelZza);
    }

    @Override // k3.j
    public void b(com.google.android.datatransport.runtime.i iVar, int i10, boolean z) {
        k3.a aVar = (k3.a) this.f1061n;
        Context context = (Context) this.f1059l;
        ComponentName componentName = new ComponentName(context, (Class<?>) JobInfoSchedulerService.class);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        Adler32 adler32 = new Adler32();
        adler32.update(context.getPackageName().getBytes(Charset.forName("UTF-8")));
        adler32.update(iVar.f12589a.getBytes(Charset.forName("UTF-8")));
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        z2.c cVar = iVar.f12591c;
        adler32.update(byteBufferAllocate.putInt(n3.a.a(cVar)).array());
        byte[] bArr = iVar.f12590b;
        if (bArr != null) {
            adler32.update(bArr);
        }
        int value = (int) adler32.getValue();
        if (!z) {
            Iterator<JobInfo> it = jobScheduler.getAllPendingJobs().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                JobInfo next = it.next();
                int i11 = next.getExtras().getInt("attemptNumber");
                if (next.getId() == value) {
                    if (i11 >= i10) {
                        ac.b.x("JobInfoScheduler", iVar, "Upload for context %s is already scheduled. Returning...");
                        return;
                    }
                }
            }
        }
        long J = ((com.google.android.datatransport.runtime.scheduling.persistence.d) this.f1060m).J(iVar);
        JobInfo.Builder builder = new JobInfo.Builder(value, componentName);
        builder.setMinimumLatency(aVar.a(cVar, J, i10));
        Set set = ((k3.b) aVar.f19473b.get(cVar)).f19476c;
        if (set.contains(k3.c.f19477i)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(k3.c.f19479m)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(k3.c.f19478l)) {
            builder.setRequiresDeviceIdle(true);
        }
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putInt("attemptNumber", i10);
        persistableBundle.putString("backendName", iVar.f12589a);
        persistableBundle.putInt("priority", n3.a.a(cVar));
        if (bArr != null) {
            persistableBundle.putString("extras", Base64.encodeToString(bArr, 0));
        }
        builder.setExtras(persistableBundle);
        Object[] objArr = {iVar, Integer.valueOf(value), Long.valueOf(aVar.a(cVar, J, i10)), Long.valueOf(J), Integer.valueOf(i10)};
        String strI = ac.b.I("JobInfoScheduler");
        if (Log.isLoggable(strI, 3)) {
            Log.d(strI, String.format("Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", objArr));
        }
        jobScheduler.schedule(builder.build());
    }

    public void b0() {
        ((TypedArray) this.f1060m).recycle();
    }

    public void c(View view, int i10, ViewGroup.LayoutParams layoutParams, boolean z) {
        androidx.recyclerview.widget.j0 j0Var = (androidx.recyclerview.widget.j0) this.f1059l;
        RecyclerView recyclerView = j0Var.f4952a;
        int childCount = i10 < 0 ? recyclerView.getChildCount() : W(i10);
        ((androidx.recyclerview.widget.e) this.f1060m).h(childCount, z);
        if (z) {
            ((ArrayList) this.f1061n).add(view);
            p1 p1VarG = RecyclerView.G(view);
            if (p1VarG != null) {
                p1VarG.onEnteredHiddenState(j0Var.f4952a);
            }
        }
        p1 p1VarG2 = RecyclerView.G(view);
        if (p1VarG2 != null) {
            if (!p1VarG2.isTmpDetached() && !p1VarG2.shouldIgnore()) {
                StringBuilder sb2 = new StringBuilder("Called attach on a child which is not detached: ");
                sb2.append(p1VarG2);
                throw new IllegalArgumentException(a2.k(recyclerView, sb2));
            }
            p1VarG2.clearTmpDetachFlag();
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public v9.d1 c0(w8.f fVar, u8.a aVar, boolean z) throws Throwable {
        androidx.core.provider.e eVar = (androidx.core.provider.e) this.f1059l;
        s8.a aVar2 = (s8.a) eVar.f2140l;
        boolean z5 = aVar.f22060e;
        w8.w wVarV = fVar.v();
        w8.u uVar = wVarV instanceof w8.u ? (w8.u) wVarV : null;
        d8.m type = uVar != null ? uVar.getType() : null;
        s8.e eVar2 = new s8.e(eVar, fVar, true);
        if (type != null) {
            v9.b0 b0VarQ = aVar2.f21658o.j().q(type);
            v9.b0 b0Var = (v9.b0) xc.d.F0(b0VarQ, new h8.i(new h8.h[]{b0VarQ.getAnnotations(), eVar2}));
            return z5 ? b0Var : v9.e.j(b0Var, b0Var.M0(true));
        }
        v9.w wVarD0 = d0(wVarV, qb.l.C(2, z5, null, 6));
        if (z5) {
            return aVar2.f21658o.j().g(z ? 3 : 1, wVarD0, eVar2);
        }
        return v9.e.j(aVar2.f21658o.j().g(1, wVarD0, eVar2), aVar2.f21658o.j().g(3, wVarD0, eVar2).M0(true));
    }

    @Override // io.sentry.a1
    public void clear() {
        X(null).clear();
    }

    /* JADX INFO: renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m2clone() {
        switch (this.f1058i) {
            case 17:
                return clone();
            default:
                return super.clone();
        }
    }

    @Override // io.sentry.a1
    public void d(io.sentry.protocol.i0 i0Var) {
        X(null).d(i0Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public v9.w d0(w8.w r6, u8.a r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.i1.d0(w8.w, u8.a):v9.w");
    }

    @Override // io.sentry.a1
    public void e(String str, String str2) {
        X(null).e(str, str2);
    }

    public void e0(View view) {
        if (((ArrayList) this.f1061n).remove(view)) {
            ((androidx.recyclerview.widget.j0) this.f1059l).g(view);
        }
    }

    public void f() {
        android.support.v4.media.session.v vVar = (android.support.v4.media.session.v) this.f1059l;
        if (vVar != null) {
            int i10 = ((androidx.mediarouter.media.f) this.f1061n).f4339o.f4459d;
            android.support.v4.media.session.o oVar = vVar.f897a;
            oVar.getClass();
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(i10);
            oVar.f886a.setPlaybackToLocal(builder.build());
            this.f1060m = null;
        }
    }

    public void f0(Object obj, String str) {
        i1 i1Var = new i1(11);
        ((i1) this.f1061n).f1061n = i1Var;
        this.f1061n = i1Var;
        i1Var.f1060m = obj;
        i1Var.f1059l = str;
    }

    @Override // io.sentry.a1
    public void g(String str, String str2) {
        X(null).g(str, str2);
    }

    @Override // io.sentry.a1
    public Map getExtras() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.putAll(((io.sentry.a1) this.f1059l).getExtras());
        concurrentHashMap.putAll(((io.sentry.a1) this.f1060m).getExtras());
        concurrentHashMap.putAll(((io.sentry.a1) this.f1061n).getExtras());
        return concurrentHashMap;
    }

    @Override // io.sentry.a1
    public w5 getLevel() {
        w5 level = ((io.sentry.a1) this.f1061n).getLevel();
        if (level != null) {
            return level;
        }
        w5 level2 = ((io.sentry.a1) this.f1060m).getLevel();
        return level2 != null ? level2 : ((io.sentry.a1) this.f1059l).getLevel();
    }

    @Override // io.sentry.a1
    public y6 getOptions() {
        return ((io.sentry.a1) this.f1059l).getOptions();
    }

    @Override // io.sentry.a1
    public io.sentry.protocol.p getRequest() {
        io.sentry.protocol.p request = ((io.sentry.a1) this.f1061n).getRequest();
        if (request != null) {
            return request;
        }
        io.sentry.protocol.p request2 = ((io.sentry.a1) this.f1060m).getRequest();
        return request2 != null ? request2 : ((io.sentry.a1) this.f1059l).getRequest();
    }

    @Override // io.sentry.a1
    public void h(io.sentry.f fVar, io.sentry.j0 j0Var) {
        X(null).h(fVar, j0Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x016e, code lost:
    
        if (r5 != null) goto L75;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x016a  */
    /* JADX WARN: Type inference failed for: r11v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v11 */
    /* JADX WARN: Type inference failed for: r17v2, types: [v9.b0] */
    /* JADX WARN: Type inference failed for: r17v21 */
    /* JADX WARN: Type inference failed for: r17v22 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v12, types: [v9.o0] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public v9.b0 i(w8.j r22, u8.a r23, v9.b0 r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 949
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.i1.i(w8.j, u8.a, v9.b0):v9.b0");
    }

    @Override // io.sentry.a1
    public io.sentry.m1 j() {
        io.sentry.m1 m1VarJ = ((io.sentry.a1) this.f1061n).j();
        if (m1VarJ != null) {
            return m1VarJ;
        }
        io.sentry.m1 m1VarJ2 = ((io.sentry.a1) this.f1060m).j();
        return m1VarJ2 != null ? m1VarJ2 : ((io.sentry.a1) this.f1059l).j();
    }

    public v9.o0 k(w8.j jVar) {
        jVar.B();
        throw null;
    }

    public void l(g3.a aVar, ByteArrayOutputStream byteArrayOutputStream) {
        HashMap map = (HashMap) this.f1059l;
        j5.f fVar = new j5.f(byteArrayOutputStream, map, (HashMap) this.f1060m, (com.google.firebase.encoders.d) this.f1061n);
        com.google.firebase.encoders.d dVar = (com.google.firebase.encoders.d) map.get(g3.a.class);
        if (dVar != null) {
            dVar.encode(aVar, fVar);
        } else {
            throw new EncodingException("No encoder for " + g3.a.class);
        }
    }

    @Override // io.sentry.a1
    public j7 m() {
        return X(null).m();
    }

    @Override // io.sentry.a1
    public io.sentry.protocol.h n() {
        return D().n();
    }

    @Override // io.sentry.a1
    public io.sentry.protocol.v o() {
        io.sentry.protocol.v vVarO = ((io.sentry.a1) this.f1061n).o();
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!vVar.equals(vVarO)) {
            return vVarO;
        }
        io.sentry.protocol.v vVarO2 = ((io.sentry.a1) this.f1060m).o();
        return !vVar.equals(vVarO2) ? vVarO2 : ((io.sentry.a1) this.f1059l).o();
    }

    @Override // io.sentry.a1
    public void p(io.sentry.protocol.v vVar) {
        X(null).p(vVar);
    }

    @Override // io.sentry.a1
    public fi.iki.elonen.f q() {
        return X(null).q();
    }

    @Override // io.sentry.a1
    public io.sentry.k1 r() {
        io.sentry.k1 k1VarR = ((io.sentry.a1) this.f1061n).r();
        if (k1VarR != null) {
            return k1VarR;
        }
        io.sentry.k1 k1VarR2 = ((io.sentry.a1) this.f1060m).r();
        return k1VarR2 != null ? k1VarR2 : ((io.sentry.a1) this.f1059l).r();
    }

    @Override // io.sentry.a1
    public Queue s() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(((io.sentry.a1) this.f1059l).s());
        arrayList.addAll(((io.sentry.a1) this.f1060m).s());
        io.sentry.a1 a1Var = (io.sentry.a1) this.f1061n;
        arrayList.addAll(a1Var.s());
        Collections.sort(arrayList);
        Queue queueA = c4.a(a1Var.getOptions().getMaxBreadcrumbs());
        queueA.addAll(arrayList);
        return queueA;
    }

    @Override // io.sentry.a1
    public j7 t(a4 a4Var) {
        return X(null).t(a4Var);
    }

    public String toString() {
        String str = "";
        switch (this.f1058i) {
            case 6:
                String str2 = (String) this.f1061n;
                String str3 = (String) this.f1060m;
                StringBuilder sb2 = new StringBuilder("NavDeepLinkRequest{");
                Uri uri = (Uri) this.f1059l;
                if (uri != null) {
                    sb2.append(" uri=");
                    sb2.append(String.valueOf(uri));
                }
                if (str3 != null) {
                    sb2.append(" action=");
                    sb2.append(str3);
                }
                if (str2 != null) {
                    sb2.append(" mimetype=");
                    sb2.append(str2);
                }
                sb2.append(" }");
                return sb2.toString();
            case 7:
                return ((androidx.recyclerview.widget.e) this.f1060m).toString() + ", hidden list:" + ((ArrayList) this.f1061n).size();
            case 12:
                StringBuilder sb3 = new StringBuilder(32);
                sb3.append((String) this.f1059l);
                sb3.append('{');
                i1 i1Var = (i1) ((i1) this.f1060m).f1061n;
                while (i1Var != null) {
                    Object obj = i1Var.f1060m;
                    sb3.append(str);
                    String str4 = (String) i1Var.f1059l;
                    if (str4 != null) {
                        sb3.append(str4);
                        sb3.append('=');
                    }
                    if (obj == null || !obj.getClass().isArray()) {
                        sb3.append(obj);
                    } else {
                        sb3.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r5.length() - 1);
                    }
                    i1Var = (i1) i1Var.f1061n;
                    str = ", ";
                }
                sb3.append('}');
                return sb3.toString();
            case 13:
                StringBuilder sb4 = new StringBuilder(32);
                sb4.append((String) this.f1059l);
                sb4.append('{');
                io.sentry.internal.debugmeta.c cVar = (io.sentry.internal.debugmeta.c) ((io.sentry.internal.debugmeta.c) this.f1060m).f17368m;
                while (cVar != null) {
                    Object obj2 = cVar.f17367l;
                    sb4.append(str);
                    if (obj2 == null || !obj2.getClass().isArray()) {
                        sb4.append(obj2);
                    } else {
                        String strDeepToString = Arrays.deepToString(new Object[]{obj2});
                        sb4.append((CharSequence) strDeepToString, 1, strDeepToString.length() - 1);
                    }
                    cVar = (io.sentry.internal.debugmeta.c) cVar.f17368m;
                    str = ", ";
                }
                sb4.append('}');
                return sb4.toString();
            default:
                return super.toString();
        }
    }

    @Override // io.sentry.a1
    public Map u() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.putAll(((io.sentry.a1) this.f1059l).u());
        concurrentHashMap.putAll(((io.sentry.a1) this.f1060m).u());
        concurrentHashMap.putAll(((io.sentry.a1) this.f1061n).u());
        return concurrentHashMap;
    }

    @Override // io.sentry.a1
    public List v() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1059l).v());
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1060m).v());
        copyOnWriteArrayList.addAll(((io.sentry.a1) this.f1061n).v());
        Collections.sort(copyOnWriteArrayList);
        return copyOnWriteArrayList;
    }

    @Override // io.sentry.a1
    public io.sentry.protocol.c w() {
        io.sentry.a1 a1Var = (io.sentry.a1) this.f1059l;
        return new io.sentry.j(a1Var.w(), ((io.sentry.a1) this.f1060m).w(), ((io.sentry.a1) this.f1061n).w(), a1Var.getOptions().getDefaultScopeType());
    }

    @Override // io.sentry.a1
    public String x() {
        String strX = ((io.sentry.a1) this.f1061n).x();
        if (strX != null) {
            return strX;
        }
        String strX2 = ((io.sentry.a1) this.f1060m).x();
        return strX2 != null ? strX2 : ((io.sentry.a1) this.f1059l).x();
    }

    @Override // io.sentry.a1
    public void y(io.sentry.m1 m1Var) {
        X(null).y(m1Var);
    }

    @Override // io.sentry.a1
    public List z() {
        List listZ = ((io.sentry.a1) this.f1061n).z();
        if (!listZ.isEmpty()) {
            return listZ;
        }
        List listZ2 = ((io.sentry.a1) this.f1060m).z();
        return !listZ2.isEmpty() ? listZ2 : ((io.sentry.a1) this.f1059l).z();
    }

    public /* synthetic */ i1(Object obj, Object obj2, Object obj3, int i10) {
        this.f1058i = i10;
        this.f1059l = obj;
        this.f1060m = obj2;
        this.f1061n = obj3;
    }

    @Override // io.sentry.a1
    public io.sentry.a1 clone() {
        return new i1((io.sentry.a1) this.f1059l, ((io.sentry.a1) this.f1060m).clone(), ((io.sentry.a1) this.f1061n).clone(), 17);
    }

    public i1(String str, int i10) {
        this.f1058i = i10;
        switch (i10) {
            case 13:
                io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(21);
                this.f1060m = cVar;
                this.f1061n = cVar;
                this.f1059l = str;
                break;
            default:
                i1 i1Var = new i1(11);
                this.f1060m = i1Var;
                this.f1061n = i1Var;
                this.f1059l = str;
                break;
        }
    }

    public i1(y8.p pVar, l8.c cVar) {
        this.f1058i = 22;
        this.f1059l = pVar;
        this.f1060m = cVar;
        this.f1061n = new ConcurrentHashMap();
    }

    public i1(androidx.lifecycle.c0 c0Var) {
        this.f1058i = 4;
        this.f1059l = new androidx.lifecycle.a0(c0Var);
        this.f1060m = new Handler();
    }

    public i1(int i10) {
        this.f1058i = i10;
        switch (i10) {
            case 11:
                break;
            case 16:
                String string = UUID.randomUUID().toString();
                xb.m mVar = new xb.m(string.getBytes(kotlin.text.a.f19924a));
                mVar.f22775m = string;
                this.f1059l = mVar;
                this.f1060m = gb.f0.f15653e;
                this.f1061n = new ArrayList();
                break;
            case 18:
                this(new io.sentry.protocol.v(), new o7(), null);
                break;
            case 23:
                List list = Collections.EMPTY_LIST;
                this.f1059l = list;
                this.f1060m = list;
                break;
            default:
                this.f1059l = new androidx.leanback.widget.h0(1);
                androidx.leanback.widget.h0 h0Var = new androidx.leanback.widget.h0(0);
                this.f1060m = h0Var;
                this.f1061n = h0Var;
                break;
        }
    }

    public i1(u7 u7Var, Double d4) {
        this.f1058i = 19;
        this.f1059l = u7Var;
        this.f1060m = d4;
        this.f1061n = Collections.EMPTY_MAP;
    }

    public i1(androidx.core.provider.e eVar, s8.h hVar) {
        this.f1058i = 24;
        this.f1059l = eVar;
        this.f1060m = hVar;
        this.f1061n = new fi.iki.elonen.f(new io.sentry.util.l(27));
    }

    public i1(androidx.recyclerview.widget.j0 j0Var) {
        this.f1058i = 7;
        this.f1059l = j0Var;
        this.f1060m = new androidx.recyclerview.widget.e(0);
        this.f1061n = new ArrayList();
    }

    public i1(Context context, TypedArray typedArray) {
        this.f1058i = 1;
        this.f1059l = context;
        this.f1060m = typedArray;
    }

    public i1(Context context, LocationManager locationManager) {
        this.f1058i = 0;
        this.f1061n = new h1();
        this.f1059l = context;
        this.f1060m = locationManager;
    }

    public i1(io.sentry.protocol.v vVar, o7 o7Var, io.sentry.d dVar) {
        this.f1058i = 18;
        this.f1059l = vVar;
        this.f1060m = o7Var;
        this.f1061n = com.google.common.util.concurrent.p0.i(dVar, null, null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public i1(i1 i1Var) {
        this((io.sentry.protocol.v) i1Var.f1059l, (o7) i1Var.f1060m, (io.sentry.d) i1Var.f1061n);
        this.f1058i = 18;
    }

    public i1(m1 m1Var, o1 o1Var) {
        this.f1058i = 14;
        this.f1060m = m1Var;
        this.f1061n = o1Var;
        this.f1059l = m1Var.f14578c;
    }

    public i1(androidx.mediarouter.media.f fVar, android.support.v4.media.session.v vVar) {
        this.f1058i = 5;
        this.f1061n = fVar;
        this.f1059l = vVar;
    }
}
