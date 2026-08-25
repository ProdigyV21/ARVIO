package io.sentry.android.core.internal.tombstone;

import androidx.leanback.widget.r1;
import h.f0;
import io.ktor.http.ContentDisposition;
import io.sentry.f2;
import io.sentry.j5;
import io.sentry.protocol.DebugImage;
import io.sentry.protocol.a0;
import io.sentry.protocol.b0;
import io.sentry.protocol.d;
import io.sentry.protocol.d0;
import io.sentry.protocol.m;
import io.sentry.protocol.n;
import io.sentry.protocol.u;
import io.sentry.protocol.z;
import io.sentry.util.l;
import io.sentry.w5;
import j$.util.Objects;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.protobuf.j;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InputStream f16577i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f16578l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f16579m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f16580n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final HashMap f16581o;

    public c(InputStream inputStream, List list, List list2, String str) {
        HashMap map = new HashMap();
        this.f16581o = map;
        this.f16577i = inputStream;
        this.f16578l = list;
        this.f16579m = list2;
        this.f16580n = str;
        map.put("SIGILL", "IllegalInstruction");
        map.put("SIGTRAP", "Trap");
        map.put("SIGABRT", "Abort");
        map.put("SIGBUS", "BusError");
        map.put("SIGFPE", "FloatingPointException");
        map.put("SIGSEGV", "Segfault");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        InputStream inputStream = this.f16577i;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public final j5 i() throws IOException {
        DebugImage debugImageA;
        DebugImage debugImageA2;
        r1 r1Var;
        ArrayList arrayList;
        ArrayList arrayList2;
        HashMap map;
        int i10;
        ArrayList arrayList3;
        r1 r1Var2;
        ArrayList arrayList4;
        HashMap map2;
        ArrayList arrayList5;
        HashMap map3;
        HashMap map4;
        r1 r1Var3;
        r1 r1Var4;
        r1 r1Var5;
        InputStream inputStream = this.f16577i;
        if (inputStream == null) {
            throw new IOException("No InputStream provided; use parse(Tombstone) instead.");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i11 = inputStream.read(bArr);
            if (i11 == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i11);
        }
        r1 r1Var6 = new r1(byteArrayOutputStream.toByteArray());
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        HashMap map5 = new HashMap();
        HashMap map6 = new HashMap();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        ArrayList arrayList11 = new ArrayList();
        String str = "";
        int iJ = 0;
        int iJ2 = 0;
        String strH = "";
        j jVar = null;
        while (true) {
            int i12 = r1Var6.i();
            if (i12 == 0) {
                String str2 = str;
                int i13 = iJ2;
                List listUnmodifiableList = Collections.unmodifiableList(arrayList6);
                Collections.unmodifiableList(arrayList7);
                Collections.unmodifiableList(arrayList8);
                Map mapUnmodifiableMap = Collections.unmodifiableMap(map5);
                Collections.unmodifiableMap(map6);
                List<s2.b> listUnmodifiableList2 = Collections.unmodifiableList(arrayList9);
                Collections.unmodifiableList(arrayList10);
                Collections.unmodifiableList(arrayList11);
                j5 j5Var = new j5();
                j5Var.E = w5.FATAL;
                j5Var.f17991r = "native";
                n nVar = new n();
                StringBuilder sb2 = new StringBuilder();
                Iterator it = listUnmodifiableList.iterator();
                if (it.hasNext()) {
                    while (true) {
                        sb2.append((CharSequence) it.next());
                        if (it.hasNext()) {
                            sb2.append((CharSequence) " ");
                        }
                    }
                }
                String string = sb2.toString();
                if (jVar != null) {
                    Locale locale = Locale.ROOT;
                    String strConcat = !strH.isEmpty() ? strH.concat(": ") : str2;
                    nVar.f17649i = strConcat + "Fatal signal " + ((String) jVar.f19800c) + " (" + jVar.f19798a + "), " + ((String) jVar.f19801d) + " (" + jVar.f19799b + "), pid = " + iJ + " (" + string + ")";
                } else {
                    Locale locale2 = Locale.ROOT;
                    nVar.f17649i = "Fatal exit pid = " + iJ + " (" + string + ")";
                }
                j5Var.A = nVar;
                ArrayList arrayList12 = new ArrayList();
                b bVar = null;
                for (s2.b bVar2 : listUnmodifiableList2) {
                    boolean z = bVar2.f21628d;
                    String str3 = bVar2.f21630f;
                    String str4 = bVar2.f21629e;
                    long j10 = bVar2.f21626b;
                    if (z && !str4.isEmpty() && !str4.startsWith("/dev/")) {
                        boolean zIsEmpty = str3.isEmpty();
                        boolean z5 = bVar2.f21627c == 0;
                        if (zIsEmpty || !z5) {
                            if (bVar != null && str4.equals((String) bVar.f16575c)) {
                                bVar.f16574b = j10;
                            }
                        } else if (bVar == null || !str4.equals((String) bVar.f16575c)) {
                            if (bVar != null && (debugImageA2 = bVar.a()) != null) {
                                arrayList12.add(debugImageA2);
                            }
                            b bVar3 = new b();
                            bVar3.f16575c = str4;
                            bVar3.f16576d = str3;
                            bVar3.f16573a = bVar2.f21625a;
                            bVar3.f16574b = j10;
                            bVar = bVar3;
                        } else {
                            bVar.f16574b = j10;
                        }
                    }
                }
                if (bVar != null && (debugImageA = bVar.a()) != null) {
                    arrayList12.add(debugImageA);
                }
                d dVar = new d();
                dVar.f17554l = new ArrayList(arrayList12);
                j5Var.x = dVar;
                u uVar = new u();
                if (jVar != null) {
                    String str5 = (String) jVar.f19800c;
                    uVar.f17700i = str5;
                    uVar.f17701l = (String) this.f16581o.get(str5);
                    m mVar = new m();
                    mVar.f17638i = a.TOMBSTONE.getValue();
                    mVar.f17641n = Boolean.FALSE;
                    mVar.f17644q = Boolean.TRUE;
                    HashMap map7 = new HashMap();
                    map7.put("number", Integer.valueOf(jVar.f19798a));
                    map7.put(ContentDisposition.Parameters.Name, (String) jVar.f19800c);
                    map7.put("code", Integer.valueOf(jVar.f19799b));
                    map7.put("code_name", (String) jVar.f19801d);
                    mVar.f17642o = new HashMap(map7);
                    uVar.f17705p = mVar;
                }
                uVar.f17703n = Long.valueOf(i13);
                ArrayList arrayList13 = new ArrayList(1);
                arrayList13.add(uVar);
                j5Var.D = new f2(arrayList13);
                ArrayList arrayListD = j5Var.d();
                Objects.requireNonNull(arrayListD);
                u uVar2 = (u) arrayListD.get(0);
                ArrayList arrayList14 = new ArrayList();
                Iterator it2 = mapUnmodifiableMap.entrySet().iterator();
                while (it2.hasNext()) {
                    s2.d dVar2 = (s2.d) ((Map.Entry) it2.next()).getValue();
                    d0 d0Var = new d0();
                    d0Var.f17556i = Long.valueOf(((Integer) r5.getKey()).intValue());
                    d0Var.f17558m = dVar2.f21634b;
                    ArrayList arrayList15 = new ArrayList();
                    Iterator it3 = dVar2.f21638f.iterator();
                    while (it3.hasNext()) {
                        s2.a aVar = (s2.a) it3.next();
                        String str6 = aVar.f21624c;
                        String str7 = aVar.f21623b;
                        if (!str6.endsWith("libart.so") && (!str6.startsWith("<anonymous") || !str7.isEmpty())) {
                            z zVar = new z();
                            zVar.f17740v = str6;
                            zVar.f17733o = str7;
                            Iterator it4 = it3;
                            Iterator it5 = it2;
                            zVar.A = String.format("0x%x", Long.valueOf(aVar.f21622a));
                            Boolean boolC = str7.isEmpty() ? Boolean.FALSE : io.sentry.u.c(str7, this.f16578l, this.f16579m);
                            String str8 = this.f16580n;
                            zVar.f17739u = Boolean.valueOf((boolC != null && boolC.booleanValue()) || (str8 != null && str6.startsWith(str8)));
                            arrayList15.add(0, zVar);
                            it3 = it4;
                            it2 = it5;
                        }
                    }
                    Iterator it6 = it2;
                    b0 b0Var = new b0();
                    b0Var.f17545i = arrayList15;
                    b0Var.f17548n = a0.NONE;
                    HashMap map8 = new HashMap();
                    for (s2.c cVar : dVar2.f21635c) {
                        map8.put(cVar.f21631a, String.format("0x%x", Long.valueOf(cVar.f21632b)));
                    }
                    b0Var.f17546l = map8;
                    d0Var.f17564s = b0Var;
                    if (i13 == dVar2.f21633a) {
                        d0Var.f17560o = Boolean.TRUE;
                        uVar2.f17704o = b0Var;
                    }
                    arrayList14.add(d0Var);
                    it2 = it6;
                }
                j5Var.C = new f2(arrayList14);
                return j5Var;
            }
            int i14 = i12 >>> 3;
            int i15 = i12 & 7;
            String str9 = str;
            switch (i14) {
                case 1:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    int iJ3 = (int) r1Var.j();
                    int[] iArrD = f0.d(6);
                    int length = iArrD.length;
                    for (int i16 = 0; i16 < length && f0.c(iArrD[i16]) != iJ3; i16++) {
                    }
                    iJ2 = i10;
                    break;
                case 2:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    r1.b(i14, 2, i15);
                    r1Var.h();
                    break;
                case 3:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    r1.b(i14, 2, i15);
                    r1Var.h();
                    break;
                case 4:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    r1.b(i14, 2, i15);
                    r1Var.h();
                    break;
                case 5:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    iJ = (int) r1Var.j();
                    iJ2 = i10;
                    break;
                case 6:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    r1.b(i14, 0, i15);
                    iJ2 = (int) r1Var.j();
                    break;
                case 7:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    r1Var.j();
                    iJ2 = i10;
                    break;
                case 8:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    r1.b(i14, 2, i15);
                    r1Var.h();
                    break;
                case 9:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    arrayList6.add(r1Var.h());
                    iJ2 = i10;
                    break;
                case 10:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    map = map5;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG = r1Var.g();
                    String strH2 = str9;
                    String strH3 = strH2;
                    int iJ4 = 0;
                    int iJ5 = 0;
                    while (true) {
                        int i17 = r1VarG.i();
                        if (i17 == 0) {
                            arrayList2 = arrayList8;
                            jVar = new j(iJ4, iJ5, strH2, strH3);
                            iJ2 = i10;
                        } else {
                            int i18 = i17 >>> 3;
                            int i19 = i17 & 7;
                            switch (i18) {
                                case 1:
                                    arrayList3 = arrayList8;
                                    r1.b(i18, 0, i19);
                                    iJ4 = (int) r1VarG.j();
                                    break;
                                case 2:
                                    arrayList3 = arrayList8;
                                    r1.b(i18, 2, i19);
                                    strH2 = r1VarG.h();
                                    break;
                                case 3:
                                    r1.b(i18, 0, i19);
                                    arrayList3 = arrayList8;
                                    iJ5 = (int) r1VarG.j();
                                    break;
                                case 4:
                                    r1.b(i18, 2, i19);
                                    arrayList3 = arrayList8;
                                    strH3 = r1VarG.h();
                                    break;
                                case 5:
                                    r1.b(i18, 0, i19);
                                    r1VarG.e();
                                    arrayList3 = arrayList8;
                                    break;
                                case 6:
                                    r1.b(i18, 0, i19);
                                    r1VarG.j();
                                    arrayList3 = arrayList8;
                                    break;
                                case 7:
                                    r1.b(i18, 0, i19);
                                    r1VarG.j();
                                    arrayList3 = arrayList8;
                                    break;
                                case 8:
                                    r1.b(i18, 0, i19);
                                    r1VarG.e();
                                    arrayList3 = arrayList8;
                                    break;
                                case 9:
                                    r1.b(i18, 0, i19);
                                    r1VarG.j();
                                    arrayList3 = arrayList8;
                                    break;
                                case 10:
                                    r1.b(i18, 2, i19);
                                    qb.d.w(r1VarG.g());
                                    arrayList3 = arrayList8;
                                    break;
                                default:
                                    r1VarG.l(i19);
                                    arrayList3 = arrayList8;
                                    break;
                            }
                            arrayList8 = arrayList3;
                        }
                        break;
                    }
                    break;
                case 11:
                case 12:
                case 13:
                default:
                    r1Var6.l(i15);
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    i10 = iJ2;
                    iJ2 = i10;
                    break;
                case 14:
                    r1Var = r1Var6;
                    arrayList = arrayList7;
                    map = map5;
                    r1.b(i14, 2, i15);
                    strH = r1Var.h();
                    arrayList2 = arrayList8;
                    break;
                case 15:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    int i20 = 2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG2 = r1Var.g();
                    while (true) {
                        int i21 = r1VarG2.i();
                        if (i21 == 0) {
                            arrayList = arrayList7;
                            map = map5;
                            arrayList8.add(new l(19));
                            arrayList2 = arrayList8;
                            iJ2 = i10;
                        } else {
                            int i22 = i21 >>> 3;
                            int i23 = i21 & 7;
                            if (i22 != 1) {
                                if (i22 != i20) {
                                    r1VarG2.l(i23);
                                } else {
                                    r1.b(i22, i20, i23);
                                    r1 r1VarG3 = r1VarG2.g();
                                    while (true) {
                                        int i24 = r1VarG3.i();
                                        if (i24 != 0) {
                                            int i25 = i24 >>> 3;
                                            int i26 = i24 & 7;
                                            r1 r1Var7 = r1VarG2;
                                            if (i25 == 1) {
                                                arrayList5 = arrayList7;
                                                map3 = map5;
                                                r1.b(i25, 0, i26);
                                                int iJ6 = (int) r1VarG3.j();
                                                int[] iArrD2 = f0.d(2);
                                                int length2 = iArrD2.length;
                                                for (int i27 = 0; i27 < length2 && f0.c(iArrD2[i27]) != iJ6; i27++) {
                                                }
                                            } else if (i25 == i20) {
                                                arrayList5 = arrayList7;
                                                map3 = map5;
                                                r1.b(i25, 0, i26);
                                                int iJ7 = (int) r1VarG3.j();
                                                int[] iArrD3 = f0.d(6);
                                                int length3 = iArrD3.length;
                                                for (int i28 = 0; i28 < length3 && f0.c(iArrD3[i28]) != iJ7; i28++) {
                                                }
                                            } else if (i25 != 3) {
                                                r1VarG3.l(i26);
                                                arrayList5 = arrayList7;
                                                map3 = map5;
                                            } else {
                                                r1.b(i25, i20, i26);
                                                r1 r1VarG4 = r1VarG3.g();
                                                ArrayList arrayList16 = new ArrayList();
                                                ArrayList arrayList17 = new ArrayList();
                                                while (true) {
                                                    int i29 = r1VarG4.i();
                                                    if (i29 != 0) {
                                                        int i30 = i29 >>> 3;
                                                        ArrayList arrayList18 = arrayList7;
                                                        int i31 = i29 & 7;
                                                        switch (i30) {
                                                            case 1:
                                                                map4 = map5;
                                                                r1.b(i30, 0, i31);
                                                                r1VarG4.j();
                                                                break;
                                                            case 2:
                                                                map4 = map5;
                                                                r1.b(i30, 0, i31);
                                                                r1VarG4.j();
                                                                break;
                                                            case 3:
                                                                map4 = map5;
                                                                r1.b(i30, 0, i31);
                                                                r1VarG4.j();
                                                                break;
                                                            case 4:
                                                                map4 = map5;
                                                                r1.b(i30, 2, i31);
                                                                arrayList16.add(qb.d.v(r1VarG4.g()));
                                                                break;
                                                            case 5:
                                                                map4 = map5;
                                                                r1.b(i30, 0, i31);
                                                                r1VarG4.j();
                                                                break;
                                                            case 6:
                                                                map4 = map5;
                                                                r1.b(i30, 2, i31);
                                                                arrayList17.add(qb.d.v(r1VarG4.g()));
                                                                break;
                                                            default:
                                                                r1VarG4.l(i31);
                                                                map4 = map5;
                                                                break;
                                                        }
                                                        arrayList7 = arrayList18;
                                                        map5 = map4;
                                                    } else {
                                                        arrayList5 = arrayList7;
                                                        map3 = map5;
                                                        Collections.unmodifiableList(arrayList16);
                                                        Collections.unmodifiableList(arrayList17);
                                                    }
                                                }
                                            }
                                            r1VarG2 = r1Var7;
                                            arrayList7 = arrayList5;
                                            map5 = map3;
                                            i20 = 2;
                                        }
                                    }
                                }
                                r1Var2 = r1VarG2;
                                arrayList4 = arrayList7;
                                map2 = map5;
                            } else {
                                r1Var2 = r1VarG2;
                                arrayList4 = arrayList7;
                                map2 = map5;
                                r1.b(i22, i20, i23);
                                r1Var2.h();
                            }
                            r1VarG2 = r1Var2;
                            arrayList7 = arrayList4;
                            map5 = map2;
                        }
                        break;
                    }
                    break;
                case 16:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    qb.d.x(r1Var.g(), map5);
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 17:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG5 = r1Var.g();
                    String strH4 = str9;
                    String strH5 = strH4;
                    long j11 = 0;
                    long j12 = 0;
                    long j13 = 0;
                    boolean zE = false;
                    while (true) {
                        int i32 = r1VarG5.i();
                        if (i32 == 0) {
                            arrayList9.add(new s2.b(j11, j12, j13, zE, strH4, strH5));
                            arrayList = arrayList7;
                            arrayList2 = arrayList8;
                            map = map5;
                            iJ2 = i10;
                            break;
                        } else {
                            int i33 = i32 >>> 3;
                            int i34 = i32 & 7;
                            switch (i33) {
                                case 1:
                                    r1.b(i33, 0, i34);
                                    j11 = r1VarG5.j();
                                    break;
                                case 2:
                                    r1.b(i33, 0, i34);
                                    j12 = r1VarG5.j();
                                    break;
                                case 3:
                                    r1.b(i33, 0, i34);
                                    j13 = r1VarG5.j();
                                    break;
                                case 4:
                                    r1.b(i33, 0, i34);
                                    zE = r1VarG5.e();
                                    break;
                                case 5:
                                    r1.b(i33, 0, i34);
                                    r1VarG5.e();
                                    break;
                                case 6:
                                    r1.b(i33, 0, i34);
                                    r1VarG5.e();
                                    break;
                                case 7:
                                    r1.b(i33, 2, i34);
                                    strH4 = r1VarG5.h();
                                    break;
                                case 8:
                                    r1.b(i33, 2, i34);
                                    strH5 = r1VarG5.h();
                                    break;
                                case 9:
                                    r1.b(i33, 0, i34);
                                    r1VarG5.j();
                                    break;
                                default:
                                    r1VarG5.l(i34);
                                    break;
                            }
                        }
                    }
                    break;
                case 18:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG6 = r1Var.g();
                    ArrayList arrayList19 = new ArrayList();
                    while (true) {
                        int i35 = r1VarG6.i();
                        if (i35 == 0) {
                            arrayList10.add(new androidx.work.impl.constraints.j(arrayList19));
                            arrayList = arrayList7;
                            arrayList2 = arrayList8;
                            map = map5;
                            iJ2 = i10;
                        } else {
                            int i36 = i35 >>> 3;
                            int i37 = i35 & 7;
                            if (i36 == 1) {
                                r1Var3 = r1VarG6;
                                r1.b(i36, 2, i37);
                                r1Var3.h();
                            } else if (i36 != 2) {
                                r1VarG6.l(i37);
                                r1Var3 = r1VarG6;
                            } else {
                                r1.b(i36, 2, i37);
                                r1 r1VarG7 = r1VarG6.g();
                                while (true) {
                                    int i38 = r1VarG7.i();
                                    if (i38 != 0) {
                                        int i39 = i38 >>> 3;
                                        int i40 = i38 & 7;
                                        switch (i39) {
                                            case 1:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 2, i40);
                                                r1VarG7.h();
                                                break;
                                            case 2:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 0, i40);
                                                r1VarG7.j();
                                                break;
                                            case 3:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 0, i40);
                                                r1VarG7.j();
                                                break;
                                            case 4:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 0, i40);
                                                r1VarG7.j();
                                                break;
                                            case 5:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 2, i40);
                                                r1VarG7.h();
                                                break;
                                            case 6:
                                                r1Var4 = r1VarG6;
                                                r1.b(i39, 2, i40);
                                                r1VarG7.h();
                                                break;
                                            default:
                                                r1VarG7.l(i40);
                                                r1Var4 = r1VarG6;
                                                break;
                                        }
                                        r1VarG6 = r1Var4;
                                    } else {
                                        r1Var3 = r1VarG6;
                                        arrayList19.add(new l(22));
                                    }
                                }
                            }
                            r1VarG6 = r1Var3;
                        }
                        break;
                    }
                    break;
                case 19:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    int i41 = 2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG8 = r1Var.g();
                    while (true) {
                        int i42 = r1VarG8.i();
                        if (i42 == 0) {
                            arrayList11.add(new l(21));
                            arrayList = arrayList7;
                            arrayList2 = arrayList8;
                            map = map5;
                            iJ2 = i10;
                        } else {
                            int i43 = i42 >>> 3;
                            int i44 = i42 & 7;
                            if (i43 == 1) {
                                r1.b(i43, 0, i44);
                                r1VarG8.j();
                            } else if (i43 == i41) {
                                r1.b(i43, i41, i44);
                                r1VarG8.h();
                            } else if (i43 == 3) {
                                r1.b(i43, i41, i44);
                                r1VarG8.h();
                            } else if (i43 != 4) {
                                r1VarG8.l(i44);
                            } else {
                                r1.b(i43, 0, i44);
                                r1VarG8.j();
                            }
                            i41 = 2;
                        }
                        break;
                    }
                    break;
                case 20:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    r1Var.j();
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 21:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    int i45 = 2;
                    r1.b(i14, 2, i15);
                    r1 r1VarG9 = r1Var.g();
                    while (true) {
                        int i46 = r1VarG9.i();
                        if (i46 == 0) {
                            arrayList7.add(new l(20));
                            arrayList = arrayList7;
                            arrayList2 = arrayList8;
                            map = map5;
                            iJ2 = i10;
                        } else {
                            int i47 = i46 >>> 3;
                            int i48 = i46 & 7;
                            if (i47 == 1) {
                                r1.b(i47, i45, i48);
                                r1VarG9.f();
                            } else if (i47 != i45) {
                                r1VarG9.l(i48);
                            } else {
                                r1.b(i47, i45, i48);
                                r1VarG9.f();
                            }
                            i45 = 2;
                        }
                        break;
                    }
                    break;
                case 22:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    r1Var.j();
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 23:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    r1Var.e();
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 24:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 0, i15);
                    int iJ8 = (int) r1Var.j();
                    int[] iArrD4 = f0.d(6);
                    int length4 = iArrD4.length;
                    for (int i49 = 0; i49 < length4 && f0.c(iArrD4[i49]) != iJ8; i49++) {
                    }
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 25:
                    r1Var = r1Var6;
                    i10 = iJ2;
                    r1.b(i14, 2, i15);
                    qb.d.x(r1Var.g(), map6);
                    arrayList = arrayList7;
                    arrayList2 = arrayList8;
                    map = map5;
                    iJ2 = i10;
                    break;
                case 26:
                    r1.b(i14, 2, i15);
                    r1 r1VarG10 = r1Var6.g();
                    ArrayList arrayList20 = new ArrayList();
                    while (true) {
                        int i50 = r1VarG10.i();
                        if (i50 == 0) {
                            r1Var = r1Var6;
                            i10 = iJ2;
                            Collections.unmodifiableList(arrayList20);
                            arrayList = arrayList7;
                            arrayList2 = arrayList8;
                            map = map5;
                            iJ2 = i10;
                        } else {
                            r1 r1Var8 = r1Var6;
                            int i51 = i50 >>> 3;
                            int i52 = i50 & 7;
                            int i53 = iJ2;
                            if (i51 == 1) {
                                r1Var5 = r1VarG10;
                                r1.b(i51, 0, i52);
                                r1Var5.j();
                            } else if (i51 != 2) {
                                r1VarG10.l(i52);
                                r1Var5 = r1VarG10;
                            } else {
                                r1.b(i51, 2, i52);
                                r1 r1VarG11 = r1VarG10.g();
                                while (true) {
                                    int i54 = r1VarG11.i();
                                    if (i54 != 0) {
                                        int i55 = i54 >>> 3;
                                        int i56 = i54 & 7;
                                        r1 r1Var9 = r1VarG10;
                                        if (i55 == 1) {
                                            r1.b(i55, 2, i56);
                                            qb.d.v(r1VarG11.g());
                                        } else if (i55 == 2) {
                                            r1.b(i55, 0, i56);
                                            r1VarG11.j();
                                        } else if (i55 != 3) {
                                            r1VarG11.l(i56);
                                        } else {
                                            r1.b(i55, 0, i56);
                                            r1VarG11.j();
                                        }
                                        r1VarG10 = r1Var9;
                                    } else {
                                        r1Var5 = r1VarG10;
                                        arrayList20.add(new l(24));
                                    }
                                }
                            }
                            r1VarG10 = r1Var5;
                            r1Var6 = r1Var8;
                            iJ2 = i53;
                        }
                        break;
                    }
                    break;
            }
            str = str9;
            arrayList8 = arrayList2;
            r1Var6 = r1Var;
            arrayList7 = arrayList;
            map5 = map;
        }
    }
}
