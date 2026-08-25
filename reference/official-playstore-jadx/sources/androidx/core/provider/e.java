package androidx.core.provider;

import android.text.TextUtils;
import android.util.Base64;
import androidx.appcompat.app.g1;
import androidx.lifecycle.d1;
import androidx.lifecycle.h1;
import androidx.lifecycle.i1;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.MainActivity$special$$inlined$viewModels$default$1;
import com.arflix.tv.MainActivity$special$$inlined$viewModels$default$2;
import com.arflix.tv.MainActivity$special$$inlined$viewModels$default$3;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.b6;
import com.google.android.gms.internal.cast.c0;
import com.google.android.gms.internal.cast.c6;
import com.google.android.gms.internal.cast.d6;
import com.google.android.gms.internal.cast.e4;
import com.google.android.gms.internal.cast.e6;
import com.google.android.gms.internal.cast.f6;
import com.google.android.gms.internal.cast.g6;
import com.google.android.gms.internal.cast.h6;
import com.google.android.gms.internal.cast.i6;
import com.google.android.gms.internal.cast.j1;
import com.google.android.gms.internal.cast.j6;
import com.google.android.gms.internal.cast.j8;
import com.google.android.gms.internal.cast.k1;
import com.google.android.gms.internal.cast.k6;
import com.google.android.gms.internal.cast.k8;
import com.google.android.gms.internal.cast.m6;
import com.google.android.gms.internal.cast.n6;
import com.google.android.gms.internal.cast.o6;
import com.google.android.gms.internal.cast.o8;
import com.google.android.gms.internal.cast.p6;
import com.google.android.gms.internal.cast.r;
import com.google.android.gms.internal.cast.s5;
import com.google.android.gms.internal.cast.t5;
import com.google.android.gms.internal.cast.v6;
import com.google.android.gms.internal.cast.w6;
import com.google.android.gms.internal.cast.z4;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import x6.s;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2139i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f2140l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f2141m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f2142n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f2143o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f2144p;

    public e(z4 z4Var, c0 c0Var, String str) {
        this.f2139i = 2;
        this.f2141m = z4Var;
        this.f2142n = c0Var;
        this.f2140l = str;
        this.f2144p = new o8(this);
    }

    public void a(k1 k1Var) {
        int i10 = k1Var.f13370e;
        if (i10 == 2 && ((com.google.android.gms.internal.cast.b) this.f2143o) != null) {
            c();
        }
        if (i10 == 2) {
            this.f2143o = new com.google.android.gms.internal.cast.b((z4) this.f2141m, (String) this.f2140l);
        } else {
            this.f2143o = b();
        }
        com.google.android.gms.internal.cast.b bVar = (com.google.android.gms.internal.cast.b) this.f2143o;
        t.i(bVar);
        k1Var.f13369d = bVar.f13179h;
        bVar.f13173b.add(k1Var);
    }

    public com.google.android.gms.internal.cast.b b() {
        if (((com.google.android.gms.internal.cast.b) this.f2143o) == null) {
            com.google.android.gms.internal.cast.b bVar = new com.google.android.gms.internal.cast.b((z4) this.f2141m, (String) this.f2140l);
            this.f2143o = bVar;
            bVar.b(1);
        }
        return (com.google.android.gms.internal.cast.b) this.f2143o;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void c() {
        int i10;
        int i11;
        int i12;
        long jLongValue;
        com.google.android.gms.internal.cast.b bVar = (com.google.android.gms.internal.cast.b) this.f2143o;
        if (bVar != null) {
            CastSession castSession = bVar.f13181j;
            if (castSession != null) {
                castSession.zzb(null);
                bVar.f13181j = null;
            }
            long j10 = bVar.f13180i;
            b6 b6VarO = c6.o();
            b6VarO.c();
            ((c6) b6VarO.f13530l).q(j10);
            String str = bVar.f13182l;
            if (str != null) {
                b6VarO.c();
                ((c6) b6VarO.f13530l).v(str);
            }
            j8 j8VarN = k8.n();
            if (!TextUtils.isEmpty(bVar.f13184n)) {
                String str2 = bVar.f13184n;
                b6VarO.c();
                ((c6) b6VarO.f13530l).r(str2);
                String str3 = bVar.f13184n;
                j8VarN.c();
                ((k8) j8VarN.f13530l).o(str3);
            }
            if (!TextUtils.isEmpty(bVar.f13185o)) {
                String str4 = bVar.f13185o;
                j8VarN.c();
                ((k8) j8VarN.f13530l).p(str4);
            }
            if (!TextUtils.isEmpty(bVar.f13186p)) {
                String str5 = bVar.f13186p;
                j8VarN.c();
                ((k8) j8VarN.f13530l).q(str5);
            }
            if (!TextUtils.isEmpty(bVar.f13187q)) {
                String str6 = bVar.f13187q;
                j8VarN.c();
                ((k8) j8VarN.f13530l).r(str6);
            }
            if (!TextUtils.isEmpty(bVar.f13188r)) {
                String str7 = bVar.f13188r;
                j8VarN.c();
                ((k8) j8VarN.f13530l).s(str7);
            }
            if (!TextUtils.isEmpty(bVar.f13189s)) {
                String str8 = bVar.f13189s;
                j8VarN.c();
                ((k8) j8VarN.f13530l).t(str8);
            }
            int i13 = bVar.f13190t;
            int i14 = i13 != 1 ? i13 != 2 ? i13 != 3 ? i13 != 4 ? i13 != 5 ? 1 : 6 : 5 : 4 : 3 : 2;
            j8VarN.c();
            ((k8) j8VarN.f13530l).u(i14);
            k8 k8Var = (k8) j8VarN.e();
            b6VarO.c();
            ((c6) b6VarO.f13530l).B(k8Var);
            s5 s5VarN = t5.n();
            String str9 = com.google.android.gms.internal.cast.b.f13171w;
            s5VarN.c();
            ((t5) s5VarN.f13530l).p(str9);
            String str10 = bVar.f13178g;
            s5VarN.c();
            ((t5) s5VarN.f13530l).o(str10);
            t5 t5Var = (t5) s5VarN.e();
            b6VarO.c();
            ((c6) b6VarO.f13530l).z(t5Var);
            e4 e4Var = bVar.f13172a;
            j6 j6VarN = k6.n();
            String str11 = (String) e4Var.zza();
            if (str11 != null) {
                v6 v6VarN = w6.n();
                v6VarN.c();
                ((w6) v6VarN.f13530l).o(str11);
                w6 w6Var = (w6) v6VarN.e();
                j6VarN.c();
                ((k6) j6VarN.f13530l).o(w6Var);
            }
            String str12 = bVar.k;
            if (str12 != null) {
                try {
                    String strReplace = str12.replace("-", "");
                    jLongValue = new BigInteger(strReplace.substring(0, Math.min(16, strReplace.length())), 16).longValue();
                } catch (NumberFormatException e5) {
                    com.google.android.gms.internal.cast.b.f13170v.w(e5, "receiverSessionId %s is not valid for hash", str12);
                    jLongValue = 0;
                }
                j6VarN.c();
                ((k6) j6VarN.f13530l).p(jLongValue);
            }
            List<k1> list = bVar.f13173b;
            if (!list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (k1 k1Var : list) {
                    k1Var.getClass();
                    h6 h6VarN = i6.n();
                    int i15 = k1Var.f13370e;
                    h6VarN.c();
                    ((i6) h6VarN.f13530l).s(i15);
                    int i16 = (int) (k1Var.f13367b - k1Var.f13369d);
                    h6VarN.c();
                    ((i6) h6VarN.f13530l).r(i16);
                    h6VarN.c();
                    ((i6) h6VarN.f13530l).o(i16);
                    Integer num = k1Var.f13366a;
                    if (num != null) {
                        int iIntValue = num.intValue();
                        h6VarN.c();
                        ((i6) h6VarN.f13530l).p(iIntValue);
                    }
                    Boolean bool = k1Var.f13368c;
                    if (bool != null) {
                        boolean zBooleanValue = bool.booleanValue();
                        h6VarN.c();
                        ((i6) h6VarN.f13530l).q(zBooleanValue);
                    }
                    arrayList.add((i6) h6VarN.e());
                }
                j6VarN.c();
                ((k6) j6VarN.f13530l).q(arrayList);
            }
            List<r> list2 = bVar.f13174c;
            if (!list2.isEmpty()) {
                ArrayList arrayList2 = new ArrayList();
                for (r rVar : list2) {
                    rVar.getClass();
                    o6 o6VarN = p6.n();
                    int i17 = (int) (rVar.f13571b - rVar.f13572c);
                    o6VarN.c();
                    ((p6) o6VarN.f13530l).o(i17);
                    int i18 = rVar.f13570a;
                    int i19 = i18 != 1 ? i18 != 2 ? i18 != 3 ? 1 : 4 : 3 : 2;
                    o6VarN.c();
                    ((p6) o6VarN.f13530l).p(i19);
                    arrayList2.add((p6) o6VarN.e());
                }
                j6VarN.c();
                ((k6) j6VarN.f13530l).s(arrayList2);
            }
            List<j1> list3 = bVar.f13175d;
            if (!list3.isEmpty()) {
                ArrayList arrayList3 = new ArrayList();
                for (j1 j1Var : list3) {
                    String str13 = j1Var.f13331a;
                    d6 d6VarN = e6.n();
                    switch (str13.hashCode()) {
                        case -1189611734:
                            if (str13.equals("queueInsert")) {
                                i12 = 13;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -1109843021:
                            if (str13.equals("launch")) {
                                i12 = 22;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -940430091:
                            if (str13.equals("queueRemove")) {
                                i12 = 15;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -936597225:
                            if (str13.equals("queueFetchItems")) {
                                i12 = 19;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -930425472:
                            if (str13.equals("setPlaybackDevices")) {
                                i12 = 23;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -921113364:
                            if (str13.equals("volume-mute")) {
                                i12 = 9;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -900560382:
                            if (str13.equals("skipAd")) {
                                i12 = 21;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -892481550:
                            if (str13.equals("status")) {
                                i12 = 10;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -844665542:
                            if (str13.equals("queueUpdate")) {
                                i12 = 14;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -810883302:
                            if (str13.equals("volume")) {
                                i12 = 7;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case -402284771:
                            if (str13.equals("setPlaybackRate")) {
                                i12 = 20;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 3327206:
                            if (str13.equals("load")) {
                                i12 = 2;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 3363353:
                            if (str13.equals("mute")) {
                                i12 = 8;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 3443508:
                            if (str13.equals(BuildConfig.FLAVOR)) {
                                i12 = 3;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 3526264:
                            if (str13.equals("seek")) {
                                i12 = 6;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 3540994:
                            if (str13.equals("stop")) {
                                i12 = 5;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 106440182:
                            if (str13.equals("pause")) {
                                i12 = 4;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 525402049:
                            if (str13.equals("queueFetchItemRange")) {
                                i12 = 18;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 913357482:
                            if (str13.equals("queueReorder")) {
                                i12 = 16;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 1148867366:
                            if (str13.equals("trackStyle")) {
                                i12 = 12;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 1451542318:
                            if (str13.equals("activeTracks")) {
                                i12 = 11;
                            } else {
                                i12 = 1;
                            }
                            break;
                        case 1873161788:
                            if (str13.equals("queueFetchItemIds")) {
                                i12 = 17;
                            } else {
                                i12 = 1;
                            }
                            break;
                        default:
                            i12 = 1;
                            break;
                    }
                    d6VarN.c();
                    ((e6) d6VarN.f13530l).s(i12);
                    int i20 = (int) j1Var.f13332b;
                    d6VarN.c();
                    ((e6) d6VarN.f13530l).o(i20);
                    int i21 = j1Var.f13333c;
                    d6VarN.c();
                    ((e6) d6VarN.f13530l).p(i21);
                    int i22 = (int) (j1Var.f13334d - j1Var.f13336f);
                    d6VarN.c();
                    ((e6) d6VarN.f13530l).q(i22);
                    int i23 = (int) (j1Var.f13335e - j1Var.f13336f);
                    d6VarN.c();
                    ((e6) d6VarN.f13530l).r(i23);
                    arrayList3.add((e6) d6VarN.e());
                }
                j6VarN.c();
                ((k6) j6VarN.f13530l).r(arrayList3);
            }
            if (bVar.f13183m != null) {
                ArrayList arrayList4 = new ArrayList();
                g1 g1Var = bVar.f13183m;
                int i24 = g1Var.f1051a;
                f6 f6VarN = g6.n();
                if (i24 == 1) {
                    i10 = 2;
                } else if (i24 != 2) {
                    i11 = i24 != 3 ? i24 != 4 ? 1 : 5 : 4;
                    f6VarN.c();
                    ((g6) f6VarN.f13530l).p(i11);
                    int i25 = (int) (g1Var.f1052b - g1Var.f1053c);
                    f6VarN.c();
                    ((g6) f6VarN.f13530l).o(i25);
                    arrayList4.add((g6) f6VarN.e());
                    j6VarN.c();
                    ((k6) j6VarN.f13530l).u(arrayList4);
                } else {
                    i10 = 3;
                }
                i11 = i10;
                f6VarN.c();
                ((g6) f6VarN.f13530l).p(i11);
                int i252 = (int) (g1Var.f1052b - g1Var.f1053c);
                f6VarN.c();
                ((g6) f6VarN.f13530l).o(i252);
                arrayList4.add((g6) f6VarN.e());
                j6VarN.c();
                ((k6) j6VarN.f13530l).u(arrayList4);
            }
            Map map = bVar.f13176e;
            if (!map.isEmpty()) {
                ArrayList arrayList5 = new ArrayList();
                for (com.google.android.gms.internal.cast.s sVar : map.values()) {
                    sVar.getClass();
                    m6 m6VarN = n6.n();
                    int i26 = sVar.f13596e;
                    m6VarN.c();
                    ((n6) m6VarN.f13530l).r(i26);
                    int i27 = sVar.f13595d.get();
                    m6VarN.c();
                    ((n6) m6VarN.f13530l).o(i27);
                    int i28 = (int) (sVar.f13592a - sVar.f13594c);
                    m6VarN.c();
                    ((n6) m6VarN.f13530l).p(i28);
                    int i29 = (int) (sVar.f13593b - sVar.f13594c);
                    m6VarN.c();
                    ((n6) m6VarN.f13530l).q(i29);
                    arrayList5.add((n6) m6VarN.e());
                }
                j6VarN.c();
                ((k6) j6VarN.f13530l).t(arrayList5);
            }
            int i30 = bVar.f13191u;
            j6VarN.c();
            ((k6) j6VarN.f13530l).v(i30);
            k6 k6Var = (k6) j6VarN.e();
            b6VarO.c();
            ((c6) b6VarO.f13530l).y(k6Var);
            bVar.f13177f.a((c6) b6VarO.e(), 233);
            this.f2143o = null;
        }
    }

    @Override // x6.s
    public Object getValue() {
        d1 d1Var = (d1) this.f2144p;
        if (d1Var != null) {
            return d1Var;
        }
        d1 d1VarA = new h1((i1) ((MainActivity$special$$inlined$viewModels$default$2) this.f2141m).invoke(), (androidx.lifecycle.g1) ((MainActivity$special$$inlined$viewModels$default$1) this.f2142n).invoke(), (o1.c) ((MainActivity$special$$inlined$viewModels$default$3) this.f2143o).invoke()).a(((kotlin.jvm.internal.f) ((kotlin.reflect.d) this.f2140l)).c());
        this.f2144p = d1VarA;
        return d1VarA;
    }

    @Override // x6.s
    public boolean isInitialized() {
        return ((d1) this.f2144p) != null;
    }

    public String toString() {
        switch (this.f2139i) {
            case 0:
                List list = (List) this.f2144p;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FontRequest {mProviderAuthority: " + ((String) this.f2140l) + ", mProviderPackage: " + ((String) this.f2141m) + ", mQuery: " + ((String) this.f2142n) + ", mCertificates:");
                for (int i10 = 0; i10 < list.size(); i10++) {
                    sb2.append(" [");
                    List list2 = (List) list.get(i10);
                    for (int i11 = 0; i11 < list2.size(); i11++) {
                        sb2.append(" \"");
                        sb2.append(Base64.encodeToString((byte[]) list2.get(i11), 0));
                        sb2.append("\"");
                    }
                    sb2.append(" ]");
                }
                sb2.append("}mCertificatesArray: 0");
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public e(Boolean bool, Double d4) {
        this(bool, d4, null, Boolean.FALSE, null);
        this.f2139i = 3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public e(Boolean bool, Double d4, Double d10) {
        this(bool, d4, d10, Boolean.FALSE, null);
        this.f2139i = 3;
    }

    public e(kotlin.reflect.d dVar, MainActivity$special$$inlined$viewModels$default$2 mainActivity$special$$inlined$viewModels$default$2, MainActivity$special$$inlined$viewModels$default$1 mainActivity$special$$inlined$viewModels$default$1, MainActivity$special$$inlined$viewModels$default$3 mainActivity$special$$inlined$viewModels$default$3) {
        this.f2139i = 1;
        this.f2140l = dVar;
        this.f2141m = mainActivity$special$$inlined$viewModels$default$2;
        this.f2142n = mainActivity$special$$inlined$viewModels$default$1;
        this.f2143o = mainActivity$special$$inlined$viewModels$default$3;
    }

    public e(Boolean bool, Double d4, Double d10, Boolean bool2, Double d11) {
        this.f2139i = 3;
        this.f2140l = bool;
        this.f2141m = d4;
        this.f2142n = d10;
        this.f2143o = Boolean.valueOf(bool.booleanValue() && bool2.booleanValue());
        this.f2144p = d11;
    }

    public e(String str, String str2, String str3, List list) {
        this.f2139i = 0;
        str.getClass();
        this.f2140l = str;
        str2.getClass();
        this.f2141m = str2;
        this.f2142n = str3;
        list.getClass();
        this.f2144p = list;
        this.f2143o = androidx.compose.material3.d.o(str, "-", str2, "-", str3);
    }

    public e(s8.a aVar, s8.h hVar, s sVar) {
        this.f2139i = 4;
        this.f2140l = aVar;
        this.f2141m = hVar;
        this.f2142n = sVar;
        this.f2143o = sVar;
        this.f2144p = new androidx.appcompat.app.i1(this, hVar);
    }
}
