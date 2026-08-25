package io.sentry.util;

import android.os.Parcel;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.auth.l4;
import g8.b1;
import gb.i0;
import gb.o0;
import gb.p0;
import h.f0;
import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import ob.a0;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import v9.s0;
import v9.t0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements l5.d, l6.n, m3.a, com.google.android.gms.common.api.internal.t, o9.a, a0, pb.a, q5.a, sa.b, v1.e {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static l f17964l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17965i;

    public /* synthetic */ l(int i10) {
        this.f17965i = i10;
    }

    public static final String c(byte[] bArr, byte[][] bArr2, int i10) {
        int i11;
        boolean z;
        int i12;
        int i13;
        byte[] bArr3 = PublicSuffixDatabase.f20990e;
        int length = bArr.length;
        int i14 = 0;
        while (i14 < length) {
            int i15 = (i14 + length) / 2;
            while (i15 > -1 && bArr[i15] != 10) {
                i15--;
            }
            int i16 = i15 + 1;
            int i17 = 1;
            while (true) {
                i11 = i16 + i17;
                if (bArr[i11] == 10) {
                    break;
                }
                i17++;
            }
            int i18 = i11 - i16;
            int i19 = i10;
            boolean z5 = false;
            int i20 = 0;
            int i21 = 0;
            while (true) {
                if (z5) {
                    i12 = 46;
                    z = false;
                } else {
                    byte b10 = bArr2[i19][i20];
                    byte[] bArr4 = ib.c.f16268a;
                    int i22 = b10 & 255;
                    z = z5;
                    i12 = i22;
                }
                byte b11 = bArr[i16 + i21];
                byte[] bArr5 = ib.c.f16268a;
                i13 = i12 - (b11 & 255);
                if (i13 != 0) {
                    break;
                }
                i21++;
                i20++;
                if (i21 == i18) {
                    break;
                }
                if (bArr2[i19].length != i20) {
                    z5 = z;
                } else {
                    if (i19 == bArr2.length - 1) {
                        break;
                    }
                    i19++;
                    i20 = -1;
                    z5 = true;
                }
            }
            if (i13 >= 0) {
                if (i13 <= 0) {
                    int i23 = i18 - i21;
                    int length2 = bArr2[i19].length - i20;
                    int length3 = bArr2.length;
                    for (int i24 = i19 + 1; i24 < length3; i24++) {
                        length2 += bArr2[i24].length;
                    }
                    if (length2 >= i23) {
                        if (length2 <= i23) {
                            return new String(bArr, i16, i18, StandardCharsets.UTF_8);
                        }
                    }
                }
                i14 = i11 + 1;
            }
            length = i15;
        }
        return null;
    }

    public static final p0 e(p0 p0Var) {
        if ((p0Var != null ? p0Var.f15786q : null) == null) {
            return p0Var;
        }
        o0 o0VarL = p0Var.l();
        o0VarL.f15768g = null;
        return o0VarL.a();
    }

    public static ArrayList f(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((i0) obj) != i0.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((i0) it.next()).f15721i);
        }
        return arrayList2;
    }

    public static s0 g(b1 b1Var, u8.a aVar, w wVar) {
        if (!aVar.f22059d) {
            aVar = aVar.b(1);
        }
        int iC = f0.c(aVar.f22058c);
        if (iC != 0 && iC != 1) {
            if (iC == 2) {
                return new t0(1, wVar);
            }
            throw new NoWhenBranchMatchedException();
        }
        int iY = b1Var.y();
        boolean z = true;
        if (iY != 1) {
            if (iY == 2) {
                z = false;
            } else if (iY != 3) {
                throw null;
            }
        }
        return !z ? new t0(1, m9.d.e(b1Var).n()) : !wVar.I0().getParameters().isEmpty() ? new t0(3, wVar) : v9.b1.k(b1Var, aVar);
    }

    public static byte[] h(List list) {
        xb.j jVar = new xb.j();
        for (String str : f(list)) {
            jVar.M0(str.length());
            jVar.R0(0, str.length(), str);
        }
        return jVar.T(jVar.f22759l);
    }

    public static boolean k() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    public static boolean l(y6 y6Var, String str) {
        return m(str, y6Var != null ? y6Var.getLogger() : null);
    }

    public static boolean m(String str, ILogger iLogger) {
        return o(str, iLogger) != null;
    }

    public static boolean n(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static Class o(String str, ILogger iLogger) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            if (iLogger == null) {
                return null;
            }
            iLogger.q(w5.INFO, "Class not available: ".concat(str), new Object[0]);
            return null;
        } catch (UnsatisfiedLinkError e5) {
            if (iLogger == null) {
                return null;
            }
            iLogger.l(w5.ERROR, "Failed to load (UnsatisfiedLinkError) ".concat(str), e5);
            return null;
        } catch (Throwable th) {
            if (iLogger == null) {
                return null;
            }
            iLogger.l(w5.ERROR, "Failed to initialize ".concat(str), th);
            return null;
        }
    }

    @Override // m3.a
    public long a() {
        switch (this.f17965i) {
            case 7:
                return SystemClock.elapsedRealtime();
            default:
                return System.currentTimeMillis();
        }
    }

    @Override // com.google.android.gms.common.api.internal.t
    public void accept(Object obj, Object obj2) {
        int i10 = 6;
        n4.n nVar = (n4.n) obj;
        com.google.android.gms.tasks.i iVar = (com.google.android.gms.tasks.i) obj2;
        switch (this.f17965i) {
            case 9:
                n4.j jVar = new n4.j(iVar);
                n4.h hVar = (n4.h) nVar.getService();
                Parcel parcelA = hVar.a();
                int i11 = n4.e.f20492a;
                parcelA.writeStrongBinder(jVar);
                hVar.c(4, parcelA);
                break;
            case 10:
                l4 l4Var = new l4(i10, iVar);
                n4.h hVar2 = (n4.h) nVar.getService();
                Parcel parcelA2 = hVar2.a();
                int i12 = n4.e.f20492a;
                parcelA2.writeStrongBinder(l4Var.asBinder());
                hVar2.c(3, parcelA2);
                break;
            case 11:
                l4 l4Var2 = new l4(7, iVar);
                n4.h hVar3 = (n4.h) nVar.getService();
                Parcel parcelA3 = hVar3.a();
                int i13 = n4.e.f20492a;
                parcelA3.writeStrongBinder(l4Var2.asBinder());
                hVar3.c(6, parcelA3);
                break;
            default:
                n4.h hVar4 = (n4.h) nVar.getService();
                n4.a aVar = new n4.a(1, iVar);
                Parcel parcelA4 = hVar4.a();
                int i14 = n4.e.f20492a;
                parcelA4.writeStrongBinder(aVar);
                hVar4.c(1, parcelA4);
                break;
        }
    }

    @Override // v1.e
    public void b() {
        switch (this.f17965i) {
            case 28:
                break;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                break;
        }
    }

    @Override // q5.a
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // v1.e
    public void d(int i10, Object obj) {
        String str;
        switch (this.f17965i) {
            case 28:
                break;
            default:
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
                break;
        }
    }

    public void i(File file) throws IOException {
        if (file.delete() || !file.exists()) {
            return;
        }
        throw new IOException("failed to delete " + file);
    }

    public void j(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                j(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete " + file2);
            }
        }
    }

    public void r(File file, File file2) throws IOException {
        i(file2);
        if (file.renameTo(file2)) {
            return;
        }
        throw new IOException("failed to rename " + file + " to " + file2);
    }

    public String toString() {
        switch (this.f17965i) {
            case 5:
                StringBuilder sb2 = new StringBuilder("ConfigRenderOptions(");
                sb2.append("json,");
                if (sb2.charAt(sb2.length() - 1) == ',') {
                    sb2.setLength(sb2.length() - 1);
                }
                sb2.append(")");
                return sb2.toString();
            case 15:
                return "FileSystem.SYSTEM";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ l(com.google.android.gms.common.api.n nVar, int i10) {
        this.f17965i = i10;
    }

    public l(u9.p pVar) {
        this.f17965i = 13;
        new ConcurrentHashMap(3, 1.0f, 2);
    }

    private final void p() {
    }

    private final void q(int i10, Object obj) {
    }
}
