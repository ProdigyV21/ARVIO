package fi.iki.elonen;

import a9.b0;
import a9.k1;
import a9.r1;
import a9.t0;
import a9.x1;
import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.datastore.preferences.protobuf.w2;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import g8.b1;
import g8.e1;
import g8.v0;
import gb.d0;
import gb.p0;
import gb.s0;
import h.f0;
import io.sentry.ILogger;
import io.sentry.b5;
import io.sentry.d6;
import io.sentry.f2;
import io.sentry.h5;
import io.sentry.l3;
import io.sentry.v5;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z5;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import k9.v;
import ka.m0;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.a0;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;
import kotlin.reflect.jvm.internal.impl.protobuf.u0;
import org.json.JSONException;
import org.json.JSONObject;
import p8.e0;
import retrofit2.r0;
import s9.y;
import v9.d1;
import v9.g0;
import v9.k0;
import v9.q0;
import v9.w;
import v9.y0;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements r, p, i6.c, ILogger, l3, io.sentry.internal.debugmeta.a, io.sentry.clientreport.f, com.google.android.gms.common.api.internal.t, e0, retrofit2.e, retrofit2.f, gb.j, retrofit2.l, s9.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f15416i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f15417l;

    public /* synthetic */ f(Object obj, Object obj2) {
        this.f15416i = obj;
        this.f15417l = obj2;
    }

    public static io.sentry.m G(v5 v5Var) {
        return v5.Event.equals(v5Var) ? io.sentry.m.Error : v5.Session.equals(v5Var) ? io.sentry.m.Session : v5.Transaction.equals(v5Var) ? io.sentry.m.Transaction : v5.UserFeedback.equals(v5Var) ? io.sentry.m.UserReport : v5.Feedback.equals(v5Var) ? io.sentry.m.Feedback : v5.Profile.equals(v5Var) ? io.sentry.m.Profile : v5.ProfileChunk.equals(v5Var) ? io.sentry.m.ProfileChunkUi : v5.Attachment.equals(v5Var) ? io.sentry.m.Attachment : v5.CheckIn.equals(v5Var) ? io.sentry.m.Monitor : v5.ReplayVideo.equals(v5Var) ? io.sentry.m.Replay : v5.Log.equals(v5Var) ? io.sentry.m.LogItem : v5.Span.equals(v5Var) ? io.sentry.m.Span : v5.TraceMetric.equals(v5Var) ? io.sentry.m.TraceMetric : io.sentry.m.Default;
    }

    public static k0 H(List list) {
        return list.isEmpty() ? k0.f22221m : new k0(list);
    }

    @Override // s9.c
    public List A(androidx.appcompat.view.menu.e eVar, t0 t0Var) {
        ((r9.a) this.f15416i).getClass();
        z zVar = z.f19728i;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(zVar, 10));
        Iterator<E> it = zVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    @Override // s9.c
    public List B(androidx.appcompat.view.menu.e eVar, t0 t0Var) {
        ((r9.a) this.f15416i).getClass();
        z zVar = z.f19728i;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(zVar, 10));
        Iterator<E> it = zVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    @Override // retrofit2.f
    public void C(retrofit2.c cVar, r0 r0Var) {
        ((retrofit2.m) this.f15417l).f21521i.execute(new androidx.emoji2.text.q(this, (retrofit2.f) this.f15416i, r0Var, 14));
    }

    @Override // s9.c
    public ArrayList D(y yVar) {
        Iterable iterable = (List) yVar.f21766e.j(((r9.a) this.f15416i).f21395c);
        if (iterable == null) {
            iterable = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) yVar.f1284b));
        }
        return arrayList;
    }

    @Override // s9.c
    public List E(androidx.appcompat.view.menu.e eVar, b0 b0Var) {
        Iterable iterable = (List) b0Var.j(((r9.a) this.f15416i).f21400h);
        if (iterable == null) {
            iterable = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    @Override // s9.c
    public List F(androidx.appcompat.view.menu.e eVar, c0 c0Var, int i10, int i11, x1 x1Var) {
        Iterable iterable = (List) x1Var.j(((r9.a) this.f15416i).f21402j);
        if (iterable == null) {
            iterable = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    public h8.c I(a9.j jVar, c9.f fVar) {
        Map mapA0;
        g8.e eVar;
        g8.f fVarF = g8.y.f((g8.b0) this.f15416i, t7.a.r(fVar, jVar.f502m), (androidx.emoji2.text.e0) this.f15417l);
        if (jVar.f503n.size() == 0 || x9.k.e(fVarF) || !i9.f.n(fVarF, 5) || (eVar = (g8.e) x.S0(fVarF.h())) == null) {
            mapA0 = a0.f19683i;
        } else {
            List listE = eVar.e();
            int iQ0 = i0.q0(kotlin.collections.s.U(listE, 10));
            if (iQ0 < 16) {
                iQ0 = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
            for (Object obj : listE) {
                linkedHashMap.put(((e1) obj).getName(), obj);
            }
            List<a9.g> list = jVar.f503n;
            ArrayList arrayList = new ArrayList();
            for (a9.g gVar : list) {
                e1 e1Var = (e1) linkedHashMap.get(f9.f.i(fVar.getString(gVar.f450m)));
                if (e1Var != null) {
                    f9.f fVarI = f9.f.i(fVar.getString(gVar.f450m));
                    w type = e1Var.getType();
                    a9.e eVar2 = gVar.f451n;
                    k9.g gVarW = W(type, eVar2, fVar);
                    xVar = J(gVarW, type, eVar2) ? gVarW : null;
                    if (xVar == null) {
                        xVar = new k9.j("Unexpected argument value: actual type " + eVar2.f404m + " != expected type " + type);
                    }
                    xVar = new x6.x(fVarI, xVar);
                }
                if (xVar != null) {
                    arrayList.add(xVar);
                }
            }
            mapA0 = h0.A0(arrayList);
        }
        return new h8.c(fVarF.m(), mapA0, v0.f15554b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean J(k9.g gVar, w wVar, a9.e eVar) {
        g8.b0 b0Var = (g8.b0) this.f15416i;
        a9.d dVar = eVar.f404m;
        int i10 = dVar == null ? -1 : s9.b.f21679a[dVar.ordinal()];
        if (i10 == 10) {
            g8.h hVarE = wVar.I0().e();
            g8.f fVar = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            if (fVar == null) {
                return true;
            }
            f9.f fVar2 = d8.k.f14705e;
            return d8.k.b(fVar, d8.p.P);
        }
        if (i10 != 13) {
            return kotlin.jvm.internal.p.a(gVar.a(b0Var), wVar);
        }
        if (gVar instanceof k9.b) {
            Object obj = ((k9.b) gVar).f19530a;
            if (((List) obj).size() == eVar.f412u.size()) {
                w wVarF = b0Var.j().f(wVar);
                x7.i iVarT = t7.a.t((Collection) obj);
                if ((iVarT instanceof Collection) && ((Collection) iVarT).isEmpty()) {
                    return true;
                }
                x7.h hVarC = iVarT.iterator();
                while (hVarC.f22624m) {
                    int iNextInt = hVarC.nextInt();
                    if (!J((k9.g) ((List) obj).get(iNextInt), wVarF, (a9.e) eVar.f412u.get(iNextInt))) {
                        return false;
                    }
                }
                return true;
            }
        }
        throw new IllegalStateException(("Deserialized ArrayValue should have the same number of elements as the original array value: " + gVar).toString());
    }

    public f K() {
        ((io.sentry.vendor.gson.stream.c) this.f15416i).j(3, 5, '}');
        return this;
    }

    public void L(io.sentry.clientreport.d dVar, io.sentry.m mVar, Long l10) {
        y6 y6Var = (y6) this.f15417l;
        if (y6Var.getOnDiscard() != null) {
            try {
                y6Var.getOnDiscard().execute();
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "The onDiscard callback threw an exception.", th);
            }
        }
    }

    public byte[] M() {
        Callable callable;
        if (((byte[]) this.f15416i) == null && (callable = (Callable) this.f15417l) != null) {
            this.f15416i = (byte[]) callable.call();
        }
        byte[] bArr = (byte[]) this.f15416i;
        return bArr != null ? bArr : new byte[0];
    }

    public File N() {
        if (((File) this.f15416i) == null) {
            synchronized (this) {
                try {
                    if (((File) this.f15416i) == null) {
                        z4.g gVar = (z4.g) this.f15417l;
                        gVar.a();
                        this.f15416i = new File(gVar.f23184a.getFilesDir(), "PersistedInstallation." + ((z4.g) this.f15417l).d() + ".json");
                    }
                } finally {
                }
            }
        }
        return (File) this.f15416i;
    }

    public d1 O(u8.a aVar) {
        d1 d1VarG0;
        v9.b0 b0Var = aVar.f22062g;
        return (b0Var == null || (d1VarG0 = xc.d.G0(b0Var)) == null) ? (x9.h) ((x6.i0) this.f15416i).getValue() : d1VarG0;
    }

    public w P(b1 b1Var, u8.a aVar) {
        return (w) ((u9.n) this.f15417l).invoke(new q0(b1Var, aVar));
    }

    public int Q(String str) {
        int iIntValue;
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f15416i;
        ab.h hVar = new ab.h(this, 8);
        Integer num = (Integer) concurrentHashMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        synchronized (concurrentHashMap) {
            try {
                Integer num2 = (Integer) concurrentHashMap.get(str);
                if (num2 == null) {
                    Object objInvoke = hVar.invoke(str);
                    concurrentHashMap.putIfAbsent(str, Integer.valueOf(((Number) objInvoke).intValue()));
                    num2 = (Integer) objInvoke;
                }
                iIntValue = num2.intValue();
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    public void R(o5.b bVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Fid", bVar.f20782a);
            jSONObject.put("Status", f0.c(bVar.f20783b));
            jSONObject.put("AuthToken", bVar.f20784c);
            jSONObject.put("RefreshToken", bVar.f20785d);
            jSONObject.put("TokenCreationEpochInSecs", bVar.f20787f);
            jSONObject.put("ExpiresInSecs", bVar.f20786e);
            jSONObject.put("FisError", bVar.f20788g);
            z4.g gVar = (z4.g) this.f15417l;
            gVar.a();
            File fileCreateTempFile = File.createTempFile("PersistedInstallation", "tmp", gVar.f23184a.getFilesDir());
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            fileOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
            fileOutputStream.close();
            if (fileCreateTempFile.renameTo(N())) {
            } else {
                throw new IOException("unable to rename the tmpfile to PersistedInstallation");
            }
        } catch (IOException | JSONException unused) {
        }
    }

    public f S(String str) {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        if (str == null) {
            cVar.getClass();
            throw new NullPointerException("name == null");
        }
        if (cVar.f18025q != null) {
            throw new IllegalStateException();
        }
        if (cVar.f18021m == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        cVar.f18025q = str;
        return this;
    }

    public f T() throws IOException {
        ((io.sentry.vendor.gson.stream.c) this.f15416i).l();
        return this;
    }

    public o5.b U() {
        JSONObject jSONObject;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        try {
            FileInputStream fileInputStream = new FileInputStream(N());
            while (true) {
                try {
                    int i10 = fileInputStream.read(bArr, 0, 16384);
                    if (i10 < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i10);
                } finally {
                }
            }
            jSONObject = new JSONObject(byteArrayOutputStream.toString());
            fileInputStream.close();
        } catch (IOException | JSONException unused) {
            jSONObject = new JSONObject();
        }
        String strOptString = jSONObject.optString("Fid", null);
        int iOptInt = jSONObject.optInt("Status", 0);
        String strOptString2 = jSONObject.optString("AuthToken", null);
        String strOptString3 = jSONObject.optString("RefreshToken", null);
        long jOptLong = jSONObject.optLong("TokenCreationEpochInSecs", 0L);
        long jOptLong2 = jSONObject.optLong("ExpiresInSecs", 0L);
        String strOptString4 = jSONObject.optString("FisError", null);
        int i11 = f0.d(5)[iOptInt];
        if (i11 == 0) {
            throw new NullPointerException("Null registrationStatus");
        }
        String str = i11 == 0 ? " registrationStatus" : "";
        if (str.isEmpty()) {
            return new o5.b(strOptString, i11, strOptString2, strOptString3, jOptLong2, jOptLong, strOptString4);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public void V(String str, String str2, Long l10) {
        AtomicLong atomicLong = (AtomicLong) ((Map) ((io.sentry.util.k) ((a8.e) this.f15416i).f183l).a()).get(new io.sentry.clientreport.c(str, str2));
        if (atomicLong != null) {
            atomicLong.addAndGet(l10.longValue());
        }
    }

    public k9.g W(w wVar, a9.e eVar, c9.f fVar) {
        boolean zBooleanValue = c9.e.N.c(eVar.f414w).booleanValue();
        a9.d dVar = eVar.f404m;
        switch (dVar == null ? -1 : s9.b.f21679a[dVar.ordinal()]) {
            case 1:
                byte b10 = (byte) eVar.f405n;
                return zBooleanValue ? new k9.y(b10) : new k9.d(b10);
            case 2:
                return new k9.e(Character.valueOf((char) eVar.f405n));
            case 3:
                short s10 = (short) eVar.f405n;
                return zBooleanValue ? new k9.y(s10) : new v(s10);
            case 4:
                int i10 = (int) eVar.f405n;
                return zBooleanValue ? new k9.y(i10) : new k9.k(i10);
            case 5:
                long j10 = eVar.f405n;
                return zBooleanValue ? new k9.y(j10) : new k9.t(j10);
            case 6:
                return new k9.c(eVar.f406o);
            case 7:
                return new k9.c(eVar.f407p);
            case 8:
                return new k9.c(Boolean.valueOf(eVar.f405n != 0));
            case 9:
                return new k9.w(fVar.getString(eVar.f408q));
            case 10:
                return new k9.s(t7.a.r(fVar, eVar.f409r), eVar.f413v);
            case 11:
                return new k9.i(t7.a.r(fVar, eVar.f409r), f9.f.i(fVar.getString(eVar.f410s)));
            case 12:
                return new k9.a(I(eVar.f411t, fVar));
            case 13:
                List list = eVar.f412u;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(W(((g8.b0) this.f15416i).j().e(), (a9.e) it.next(), fVar));
                }
                return new k9.x(arrayList, wVar);
            default:
                throw new IllegalStateException(("Unsupported annotation argument type: " + eVar.f404m + " (expected " + wVar + ')').toString());
        }
    }

    public void X(io.sentry.clientreport.b bVar) {
        if (bVar == null) {
            return;
        }
        for (io.sentry.clientreport.e eVar : bVar.f17189l) {
            V(eVar.f17193i, eVar.f17194l, eVar.f17195m);
        }
    }

    public void Y(String str) {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        if (str != null) {
            cVar.getClass();
            if (str.length() != 0) {
                cVar.f18022n = str;
                cVar.f18023o = ": ";
                return;
            }
        }
        cVar.f18022n = null;
        cVar.f18023o = ":";
    }

    public z6.i Z(y0 y0Var, List list, u8.a aVar) {
        d1 d1VarM;
        z6.i iVar = new z6.i();
        Iterator it = list.iterator();
        if (it.hasNext()) {
            w wVar = (w) it.next();
            g8.h hVarE = wVar.I0().e();
            if (hVarE instanceof g8.f) {
                Set set = aVar.f22061f;
                d1 d1VarL0 = wVar.L0();
                if (d1VarL0 instanceof v9.q) {
                    v9.q qVar = (v9.q) d1VarL0;
                    v9.b0 b0VarM = qVar.f22241l;
                    if (!b0VarM.I0().getParameters().isEmpty() && b0VarM.I0().e() != null) {
                        List<b1> parameters = b0VarM.I0().getParameters();
                        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(parameters, 10));
                        for (b1 b1Var : parameters) {
                            v9.r0 g0Var = (v9.r0) x.p0(b1Var.getIndex(), wVar.G0());
                            boolean z = set != null && set.contains(b1Var);
                            if (g0Var == null || z || y0Var.f().d(g0Var.getType()) == null) {
                                g0Var = new g0(b1Var);
                            }
                            arrayList.add(g0Var);
                        }
                        b0VarM = v9.c.m(b0VarM, arrayList, null, 2);
                    }
                    v9.b0 b0VarM2 = qVar.f22242m;
                    if (!b0VarM2.I0().getParameters().isEmpty() && b0VarM2.I0().e() != null) {
                        List<b1> parameters2 = b0VarM2.I0().getParameters();
                        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(parameters2, 10));
                        for (b1 b1Var2 : parameters2) {
                            v9.r0 g0Var2 = (v9.r0) x.p0(b1Var2.getIndex(), wVar.G0());
                            boolean z5 = set != null && set.contains(b1Var2);
                            if (g0Var2 == null || z5 || y0Var.f().d(g0Var2.getType()) == null) {
                                g0Var2 = new g0(b1Var2);
                            }
                            arrayList2.add(g0Var2);
                        }
                        b0VarM2 = v9.c.m(b0VarM2, arrayList2, null, 2);
                    }
                    d1VarM = v9.e.j(b0VarM, b0VarM2);
                } else {
                    if (!(d1VarL0 instanceof v9.b0)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    v9.b0 b0Var = (v9.b0) d1VarL0;
                    if (b0Var.I0().getParameters().isEmpty() || b0Var.I0().e() == null) {
                        d1VarM = b0Var;
                    } else {
                        List<b1> parameters3 = b0Var.I0().getParameters();
                        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(parameters3, 10));
                        for (b1 b1Var3 : parameters3) {
                            v9.r0 g0Var3 = (v9.r0) x.p0(b1Var3.getIndex(), wVar.G0());
                            boolean z10 = set != null && set.contains(b1Var3);
                            if (g0Var3 == null || z10 || y0Var.f().d(g0Var3.getType()) == null) {
                                g0Var3 = new g0(b1Var3);
                            }
                            arrayList3.add(g0Var3);
                        }
                        d1VarM = v9.c.m(b0Var, arrayList3, null, 2);
                    }
                }
                iVar.add(y0Var.g(3, v9.c.x(d1VarM, v9.c.d(d1VarL0))));
            } else if (hVarE instanceof b1) {
                Set set2 = aVar.f22061f;
                if (set2 == null || !set2.contains(hVarE)) {
                    iVar.addAll(Z(y0Var, ((b1) hVarE).getUpperBounds(), aVar));
                } else {
                    iVar.add(O(aVar));
                }
            }
        }
        return ac.b.c(iVar);
    }

    @Override // fi.iki.elonen.p
    /* JADX INFO: renamed from: a */
    public ServerSocket mo0a() {
        SSLServerSocket sSLServerSocket = (SSLServerSocket) ((SSLServerSocketFactory) this.f15416i).createServerSocket();
        String[] strArr = (String[]) this.f15417l;
        if (strArr != null) {
            sSLServerSocket.setEnabledProtocols(strArr);
        } else {
            sSLServerSocket.setEnabledProtocols(sSLServerSocket.getSupportedProtocols());
        }
        sSLServerSocket.setUseClientMode(false);
        sSLServerSocket.setWantClientAuth(false);
        sSLServerSocket.setNeedClientAuth(false);
        return sSLServerSocket;
    }

    public f a0(double d4) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        cVar.y();
        if (cVar.f18024p || !(Double.isNaN(d4) || Double.isInfinite(d4))) {
            cVar.i();
            cVar.f18019i.append((CharSequence) Double.toString(d4));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d4);
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) throws RemoteException {
        m4.i iVar = (m4.i) this.f15416i;
        GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest = (GetPhoneNumberHintIntentRequest) this.f15417l;
        m4.h hVar = new m4.h((com.google.android.gms.tasks.i) obj2);
        m4.b0 b0Var = (m4.b0) ((m4.j) obj).getService();
        String str = iVar.f20234a;
        Parcel parcelZba = b0Var.zba();
        int i10 = m4.n.f20244a;
        parcelZba.writeStrongBinder(hVar.asBinder());
        m4.n.c(parcelZba, getPhoneNumberHintIntentRequest);
        parcelZba.writeString(str);
        b0Var.zbb(4, parcelZba);
    }

    @Override // s9.c
    public List b(androidx.appcompat.view.menu.e eVar, c0 c0Var, int i10) {
        r9.a aVar = (r9.a) this.f15416i;
        if (c0Var instanceof a9.i0) {
            aVar.getClass();
        } else {
            if (!(c0Var instanceof t0)) {
                throw new IllegalStateException(("Unknown message: " + c0Var).toString());
            }
            int iC = f0.c(i10);
            if (iC != 1 && iC != 2 && iC != 3) {
                throw new IllegalStateException("Unsupported callable kind with property proto for receiver annotations: ".concat(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "PROPERTY_SETTER" : "PROPERTY_GETTER" : "PROPERTY" : "FUNCTION").toString());
            }
            aVar.getClass();
        }
        z zVar = z.f19728i;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(zVar, 10));
        Iterator<E> it = zVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    public f b0(ILogger iLogger, Object obj) {
        ((f2) this.f15417l).f(this, iLogger, obj);
        return this;
    }

    @Override // io.sentry.clientreport.f
    public void c(io.sentry.clientreport.d dVar, io.sentry.m mVar) {
        o(dVar, mVar, 1L);
    }

    public f c0(Boolean bool) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        if (bool == null) {
            cVar.l();
            return this;
        }
        cVar.y();
        cVar.i();
        cVar.f18019i.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    @Override // fi.iki.elonen.r
    public void clear() {
        ArrayList arrayList = (ArrayList) this.f15417l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                ((q) it.next()).delete();
            } catch (Exception e5) {
                t.LOG.log(Level.WARNING, "could not delete file ", (Throwable) e5);
            }
        }
        arrayList.clear();
    }

    @Override // io.sentry.l3
    public f d(long j10) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        cVar.y();
        cVar.i();
        cVar.f18019i.write(Long.toString(j10));
        return this;
    }

    public f d0(Number number) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        if (number == null) {
            cVar.l();
            return this;
        }
        cVar.y();
        String string = number.toString();
        if (cVar.f18024p || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            cVar.i();
            cVar.f18019i.append((CharSequence) string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    @Override // s9.a
    public /* bridge */ /* synthetic */ Object e(androidx.appcompat.view.menu.e eVar, t0 t0Var, w wVar) {
        return null;
    }

    public f e0(boolean z) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        cVar.y();
        cVar.i();
        cVar.f18019i.write(z ? "true" : "false");
        return this;
    }

    @Override // i6.c
    public Object f(String str, String str2, d7.d dVar) throws Throwable {
        Object objY = m0.y((ka.f0) this.f15417l, new androidx.navigation.compose.i(this, str, str2, null, 1), dVar);
        return objY == e7.a.f15033i ? objY : x6.t0.f22605a;
    }

    public void f0(int i10, kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        Iterator it = (Iterator) this.f15416i;
        while (true) {
            Map.Entry entry = (Map.Entry) this.f15417l;
            if (entry == null || ((kotlin.reflect.jvm.internal.impl.protobuf.s) entry.getKey()).f19821i >= i10) {
                return;
            }
            kotlin.reflect.jvm.internal.impl.protobuf.s sVar = (kotlin.reflect.jvm.internal.impl.protobuf.s) ((Map.Entry) this.f15417l).getKey();
            Object value = ((Map.Entry) this.f15417l).getValue();
            kotlin.reflect.jvm.internal.impl.protobuf.n nVar = kotlin.reflect.jvm.internal.impl.protobuf.n.f19811c;
            u0 u0Var = sVar.f19822l;
            int i11 = sVar.f19821i;
            if (sVar.f19823m) {
                for (Object obj : (List) value) {
                    if (u0Var == u0.f19831o) {
                        jVar.x(i11, 3);
                        ((c0) obj).e(jVar);
                        jVar.x(i11, 4);
                    } else {
                        jVar.x(i11, u0Var.f19836l);
                        kotlin.reflect.jvm.internal.impl.protobuf.n.k(jVar, u0Var, obj);
                    }
                }
            } else if (u0Var == u0.f19831o) {
                jVar.x(i11, 3);
                ((c0) value).e(jVar);
                jVar.x(i11, 4);
            } else {
                jVar.x(i11, u0Var.f19836l);
                kotlin.reflect.jvm.internal.impl.protobuf.n.k(jVar, u0Var, value);
            }
            if (it.hasNext()) {
                this.f15417l = (Map.Entry) it.next();
            } else {
                this.f15417l = null;
            }
        }
    }

    @Override // s9.c
    public ArrayList g(k1 k1Var, c9.f fVar) {
        Iterable iterable = (List) k1Var.j(((r9.a) this.f15416i).k);
        if (iterable == null) {
            iterable = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), fVar));
        }
        return arrayList;
    }

    @Override // retrofit2.e
    public Type h() {
        return (Type) this.f15416i;
    }

    @Override // s9.c
    public List i(androidx.appcompat.view.menu.e eVar, c0 c0Var, int i10) {
        List list;
        r9.a aVar = (r9.a) this.f15416i;
        if (c0Var instanceof a9.q) {
            list = (List) ((a9.q) c0Var).j(aVar.f21394b);
        } else if (c0Var instanceof a9.i0) {
            list = (List) ((a9.i0) c0Var).j(aVar.f21396d);
        } else {
            if (!(c0Var instanceof t0)) {
                throw new IllegalStateException(("Unknown message: " + c0Var).toString());
            }
            int iC = f0.c(i10);
            if (iC == 1) {
                list = (List) ((t0) c0Var).j(aVar.f21397e);
            } else if (iC == 2) {
                list = (List) ((t0) c0Var).j(aVar.f21398f);
            } else {
                if (iC != 3) {
                    throw new IllegalStateException("Unsupported callable kind with property proto");
                }
                list = (List) ((t0) c0Var).j(aVar.f21399g);
            }
        }
        if (list == null) {
            list = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), (c9.f) eVar.f1284b));
        }
        return arrayList;
    }

    @Override // io.sentry.l3
    public f j() {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        cVar.y();
        cVar.i();
        int i10 = cVar.f18021m;
        int[] iArr = cVar.f18020l;
        if (i10 == iArr.length) {
            cVar.f18020l = Arrays.copyOf(iArr, i10 * 2);
        }
        int[] iArr2 = cVar.f18020l;
        int i11 = cVar.f18021m;
        cVar.f18021m = i11 + 1;
        iArr2[i11] = 3;
        cVar.f18019i.write(123);
        return this;
    }

    @Override // io.sentry.ILogger
    public void k(w5 w5Var, Throwable th, String str, Object... objArr) {
        ILogger iLogger = (ILogger) this.f15417l;
        if (iLogger == null || !t(w5Var)) {
            return;
        }
        iLogger.k(w5Var, th, str, objArr);
    }

    @Override // io.sentry.ILogger
    public void l(w5 w5Var, String str, Throwable th) {
        ILogger iLogger = (ILogger) this.f15417l;
        if (iLogger == null || !t(w5Var)) {
            return;
        }
        iLogger.l(w5Var, str, th);
    }

    @Override // s9.a
    public Object m(androidx.appcompat.view.menu.e eVar, t0 t0Var, w wVar) {
        a9.e eVar2 = (a9.e) com.google.common.util.concurrent.r0.l(t0Var, ((r9.a) this.f15416i).f21401i);
        if (eVar2 == null) {
            return null;
        }
        return ((f) this.f15417l).W(wVar, eVar2, (c9.f) eVar.f1284b);
    }

    @Override // i6.c
    public Object n(String str, d7.d dVar) {
        return m0.y((ka.f0) this.f15417l, new i6.d(this, str, null, 0), dVar);
    }

    @Override // io.sentry.clientreport.f
    public void o(io.sentry.clientreport.d dVar, io.sentry.m mVar, long j10) {
        try {
            V(dVar.getReason(), mVar.getCategory(), Long.valueOf(j10));
            L(dVar, mVar, Long.valueOf(j10));
        } catch (Throwable th) {
            ((y6) this.f15417l).getLogger().k(w5.ERROR, th, "Unable to record lost event.", new Object[0]);
        }
    }

    @Override // retrofit2.f
    public void onFailure(Throwable th) {
        ((retrofit2.m) this.f15417l).f21521i.execute(new io.sentry.android.ndk.b(this, (retrofit2.f) this.f15416i, th));
    }

    @Override // gb.j
    public void onResponse(gb.i iVar, p0 p0Var) {
        retrofit2.f fVar = (retrofit2.f) this.f15416i;
        retrofit2.y yVar = (retrofit2.y) this.f15417l;
        try {
            try {
                fVar.C(yVar, yVar.c(p0Var));
            } catch (Throwable th) {
                retrofit2.b1.o(th);
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            retrofit2.b1.o(th2);
            try {
                fVar.onFailure(th2);
            } catch (Throwable th3) {
                retrofit2.b1.o(th3);
                th3.printStackTrace();
            }
        }
    }

    @Override // io.sentry.internal.debugmeta.a
    public List p() {
        ILogger iLogger = (ILogger) this.f15417l;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(((Context) this.f15416i).getAssets().open("sentry-debug-meta.properties"));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                List listSingletonList = Collections.singletonList(properties);
                bufferedInputStream.close();
                return listSingletonList;
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            iLogger.q(w5.INFO, "%s file was not found.", "sentry-debug-meta.properties");
            return null;
        } catch (IOException e5) {
            iLogger.l(w5.ERROR, "Error getting Proguard UUIDs.", e5);
            return null;
        } catch (RuntimeException e6) {
            iLogger.k(w5.ERROR, e6, "%s file is malformed.", "sentry-debug-meta.properties");
            return null;
        }
    }

    @Override // io.sentry.ILogger
    public void q(w5 w5Var, String str, Object... objArr) {
        ILogger iLogger = (ILogger) this.f15417l;
        if (iLogger == null || !t(w5Var)) {
            return;
        }
        iLogger.q(w5Var, str, objArr);
    }

    @Override // s9.c
    public ArrayList r(r1 r1Var, c9.f fVar) {
        Iterable iterable = (List) r1Var.j(((r9.a) this.f15416i).f21403l);
        if (iterable == null) {
            iterable = z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((f) this.f15417l).I((a9.j) it.next(), fVar));
        }
        return arrayList;
    }

    @Override // io.sentry.clientreport.f
    public void s(io.sentry.clientreport.d dVar, h5 h5Var) {
        y6 y6Var = (y6) this.f15417l;
        if (h5Var == null) {
            return;
        }
        try {
            v5 v5Var = h5Var.f17320a.f17356o;
            if (v5.ClientReport.equals(v5Var)) {
                try {
                    X(h5Var.e(y6Var.getSerializer()));
                    return;
                } catch (Exception unused) {
                    y6Var.getLogger().q(w5.ERROR, "Unable to restore counts from previous client report.", new Object[0]);
                    return;
                }
            }
            io.sentry.m mVarG = G(v5Var);
            if (mVarG.equals(io.sentry.m.Transaction)) {
                io.sentry.protocol.e0 e0VarI = h5Var.i(y6Var.getSerializer());
                if (e0VarI != null) {
                    ArrayList arrayList = e0VarI.C;
                    String reason = dVar.getReason();
                    io.sentry.m mVar = io.sentry.m.Span;
                    V(reason, mVar.getCategory(), Long.valueOf(((long) arrayList.size()) + 1));
                    L(dVar, mVar, Long.valueOf(((long) arrayList.size()) + 1));
                }
                V(dVar.getReason(), mVarG.getCategory(), 1L);
                L(dVar, mVarG, 1L);
                return;
            }
            if (mVarG.equals(io.sentry.m.LogItem)) {
                z5 z5VarG = h5Var.g(y6Var.getSerializer());
                if (z5VarG == null) {
                    y6Var.getLogger().q(w5.ERROR, "Unable to parse lost logs envelope item.", new Object[0]);
                    return;
                }
                long size = z5VarG.f18079i.size();
                V(dVar.getReason(), mVarG.getCategory(), Long.valueOf(size));
                V(dVar.getReason(), io.sentry.m.LogByte.getCategory(), Long.valueOf(h5Var.f().length));
                L(dVar, mVarG, Long.valueOf(size));
                return;
            }
            if (!mVarG.equals(io.sentry.m.TraceMetric)) {
                V(dVar.getReason(), mVarG.getCategory(), 1L);
                L(dVar, mVarG, 1L);
                return;
            }
            d6 d6VarH = h5Var.h(y6Var.getSerializer());
            if (d6VarH == null) {
                y6Var.getLogger().q(w5.ERROR, "Unable to parse lost metrics envelope item.", new Object[0]);
                return;
            }
            long size2 = d6VarH.f17214i.size();
            V(dVar.getReason(), mVarG.getCategory(), Long.valueOf(size2));
            L(dVar, mVarG, Long.valueOf(size2));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Unable to record lost envelope item.", new Object[0]);
        }
    }

    @Override // io.sentry.ILogger
    public boolean t(w5 w5Var) {
        y6 y6Var = (y6) this.f15416i;
        return w5Var != null && y6Var.isDebug() && w5Var.ordinal() >= y6Var.getDiagnosticLevel().ordinal();
    }

    @Override // retrofit2.e
    public Object u(retrofit2.y yVar) {
        Executor executor = (Executor) this.f15417l;
        return executor == null ? yVar : new retrofit2.m(executor, yVar);
    }

    @Override // io.sentry.clientreport.f
    public void v(io.sentry.clientreport.d dVar, f fVar) {
        if (fVar == null) {
            return;
        }
        try {
            Iterator it = ((Iterable) fVar.f15417l).iterator();
            while (it.hasNext()) {
                s(dVar, (h5) it.next());
            }
        } catch (Throwable th) {
            ((y6) this.f15417l).getLogger().k(w5.ERROR, th, "Unable to record lost envelope.", new Object[0]);
        }
    }

    @Override // io.sentry.l3
    public f value(String str) {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f15416i;
        if (str == null) {
            cVar.l();
            return this;
        }
        cVar.y();
        cVar.i();
        cVar.x(str);
        return this;
    }

    @Override // retrofit2.l
    public Object w(Object obj) {
        Charset charsetA;
        s0 s0Var = (s0) obj;
        Gson gson = (Gson) this.f15416i;
        gb.q0 q0Var = s0Var.f15814i;
        if (q0Var == null) {
            xb.l lVarO = s0Var.o();
            d0 d0VarL = s0Var.l();
            if (d0VarL == null || (charsetA = d0VarL.a(kotlin.text.a.f19924a)) == null) {
                charsetA = kotlin.text.a.f19924a;
            }
            q0Var = new gb.q0(lVarO, charsetA);
            s0Var.f15814i = q0Var;
        }
        JsonReader jsonReaderNewJsonReader = gson.newJsonReader(q0Var);
        try {
            Object obj2 = ((TypeAdapter) this.f15417l).read(jsonReaderNewJsonReader);
            if (jsonReaderNewJsonReader.peek() == JsonToken.END_DOCUMENT) {
                return obj2;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            s0Var.close();
        }
    }

    @Override // i6.c
    public Object x(String str, d7.d dVar) throws Throwable {
        Object objY = m0.y((ka.f0) this.f15417l, new i6.d(this, str, null, 1), dVar);
        return objY == e7.a.f15033i ? objY : x6.t0.f22605a;
    }

    @Override // fi.iki.elonen.r
    public e y() {
        e eVar = new e((File) this.f15416i);
        ((ArrayList) this.f15417l).add(eVar);
        return eVar;
    }

    @Override // io.sentry.clientreport.f
    public f z(f fVar) {
        y6 y6Var = (y6) this.f15417l;
        Date dateM = k2.c.m();
        a8.e eVar = (a8.e) this.f15416i;
        eVar.getClass();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((Map) ((io.sentry.util.k) eVar.f183l).a()).entrySet()) {
            long andSet = ((AtomicLong) entry.getValue()).getAndSet(0L);
            Long lValueOf = Long.valueOf(andSet);
            if (andSet > 0) {
                arrayList.add(new io.sentry.clientreport.e(((io.sentry.clientreport.c) entry.getKey()).f17191a, ((io.sentry.clientreport.c) entry.getKey()).f17192b, lValueOf));
            }
        }
        io.sentry.clientreport.b bVar = arrayList.isEmpty() ? null : new io.sentry.clientreport.b(dateM, arrayList);
        if (bVar == null) {
            return fVar;
        }
        try {
            y6Var.getLogger().q(w5.DEBUG, "Attaching client report to envelope.", new Object[0]);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = ((Iterable) fVar.f15417l).iterator();
            while (it.hasNext()) {
                arrayList2.add((h5) it.next());
            }
            arrayList2.add(h5.b(y6Var.getSerializer(), bVar));
            return new f((b5) fVar.f15416i, (List) arrayList2);
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Unable to attach client report to envelope.", new Object[0]);
            return fVar;
        }
    }

    public /* synthetic */ f(Object obj, Object obj2, boolean z) {
        this.f15417l = obj;
        this.f15416i = obj2;
    }

    @Override // gb.j
    public void onFailure(gb.i iVar, IOException iOException) {
        try {
            ((retrofit2.f) this.f15416i).onFailure(iOException);
        } catch (Throwable th) {
            retrofit2.b1.o(th);
            th.printStackTrace();
        }
    }

    public /* synthetic */ f(Object obj, boolean z) {
        this.f15417l = obj;
    }

    public f(Writer writer, int i10) {
        this.f15416i = new io.sentry.vendor.gson.stream.c(writer);
        this.f15417l = new f2(i10);
    }

    public f(io.sentry.util.l lVar) {
        u9.p pVar = new u9.p("Type parameter upper bound erasure results");
        this.f15416i = new x6.i0(new j8.t0(this, 10));
        this.f15417l = pVar.g(new s8.d(this, 12));
    }

    public f(y6 y6Var) {
        this.f15417l = y6Var;
        this.f15416i = new a8.e(27);
    }

    public f(g8.b0 b0Var, androidx.emoji2.text.e0 e0Var, r9.a aVar) {
        this.f15416i = aVar;
        this.f15417l = new f(b0Var, e0Var);
    }

    public f(b5 b5Var, List list) {
        ac.b.V(b5Var, "SentryEnvelopeHeader is required.");
        this.f15416i = b5Var;
        ac.b.V(list, "SentryEnvelope items are required.");
        this.f15417l = list;
    }

    public f(io.sentry.protocol.v vVar, io.sentry.protocol.t tVar, h5 h5Var) {
        this.f15416i = new b5(vVar, tVar, null);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(h5Var);
        this.f15417l = arrayList;
    }

    public f(Map map) {
        this.f15416i = map;
        this.f15417l = new u9.p("Java nullability annotation states").e(new ab.h(this, 29));
    }

    public f(kotlin.reflect.jvm.internal.impl.protobuf.q qVar) {
        Iterator it = ((w2) qVar.f19820i.f19812a.entrySet()).iterator();
        this.f15416i = it;
        if (it.hasNext()) {
            this.f15417l = (Map.Entry) it.next();
        }
    }

    public f(int i10) {
        switch (i10) {
            case 2:
                this.f15417l = new ArrayList();
                this.f15416i = new ArrayList();
                break;
            case 28:
                this.f15416i = new ConcurrentHashMap();
                this.f15417l = new AtomicInteger(0);
                break;
            default:
                File file = new File(System.getProperty("java.io.tmpdir"));
                this.f15416i = file;
                if (!file.exists()) {
                    file.mkdirs();
                }
                this.f15417l = new ArrayList();
                break;
        }
    }
}
