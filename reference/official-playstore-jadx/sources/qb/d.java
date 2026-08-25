package qb;

import a8.l0;
import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.text.InputFilter;
import android.text.TextUtils;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.i1;
import androidx.core.app.p1;
import androidx.leanback.widget.r1;
import androidx.navigation.b1;
import androidx.navigation.i0;
import com.google.android.gms.common.api.Status;
import io.sentry.util.q;
import io.sentry.util.t;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import ka.m0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlin.text.o;
import kotlin.text.u;
import m2.f0;
import ma.w;
import w8.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {
    public static final String A(long j10) {
        return String.format("%6s", Arrays.copyOf(new Object[]{j10 <= -999500000 ? a0.c.j((j10 - ((long) 500000000)) / ((long) 1000000000), " s ", new StringBuilder()) : j10 <= -999500 ? a0.c.j((j10 - ((long) 500000)) / ((long) 1000000), " ms", new StringBuilder()) : j10 <= 0 ? a0.c.j((j10 - ((long) 500)) / ((long) 1000), " µs", new StringBuilder()) : j10 < 999500 ? a0.c.j((j10 + ((long) 500)) / ((long) 1000), " µs", new StringBuilder()) : j10 < 999500000 ? a0.c.j((j10 + ((long) 500000)) / ((long) 1000000), " ms", new StringBuilder()) : a0.c.j((j10 + ((long) 500000000)) / ((long) 1000000000), " s ", new StringBuilder())}, 1));
    }

    public static String B() {
        byte[] bArr = new byte[16];
        q.a().b(bArr);
        byte b10 = (byte) (bArr[6] & 15);
        bArr[6] = b10;
        bArr[6] = (byte) (b10 | 64);
        byte b11 = (byte) (bArr[8] & 63);
        bArr[8] = b11;
        bArr[8] = (byte) (b11 | 128);
        long j10 = 0;
        long j11 = 0;
        for (int i10 = 0; i10 < 8; i10++) {
            j11 = (j11 << 8) | ((long) (bArr[i10] & 255));
        }
        for (int i11 = 8; i11 < 16; i11++) {
            j10 = (j10 << 8) | ((long) (bArr[i11] & 255));
        }
        UUID uuid = new UUID(j11, j10);
        char[] cArr = t.f17976a;
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        char[] cArr2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, cArr[(int) (((-1152921504606846976L) & leastSignificantBits) >>> 60)], cArr[(int) ((1080863910568919040L & leastSignificantBits) >>> 56)], cArr[(int) ((67553994410557440L & leastSignificantBits) >>> 52)], cArr[(int) ((4222124650659840L & leastSignificantBits) >>> 48)], cArr[(int) ((263882790666240L & leastSignificantBits) >>> 44)], cArr[(int) ((16492674416640L & leastSignificantBits) >>> 40)], cArr[(int) ((1030792151040L & leastSignificantBits) >>> 36)], cArr[(int) ((64424509440L & leastSignificantBits) >>> 32)], cArr[(int) ((4026531840L & leastSignificantBits) >>> 28)], cArr[(int) ((251658240 & leastSignificantBits) >>> 24)], cArr[(int) ((15728640 & leastSignificantBits) >>> 20)], cArr[(int) ((983040 & leastSignificantBits) >>> 16)], cArr[(int) ((61440 & leastSignificantBits) >>> 12)], cArr[(int) ((3840 & leastSignificantBits) >>> 8)], cArr[(int) ((240 & leastSignificantBits) >>> 4)], cArr[(int) (15 & leastSignificantBits)]};
        t.a(cArr2, mostSignificantBits);
        char[] cArr3 = t.f17976a;
        return new String(cArr2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
    
        if (r5.f16001c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.content.res.ColorStateList C(android.content.Context r8, int r9) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            i0.n r1 = new i0.n
            r1.<init>(r0, r8)
            java.lang.Object r2 = i0.q.f16006c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = i0.q.f16005b     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3c
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3c
            r4 = 0
            if (r3 == 0) goto L4f
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3c
            if (r5 <= 0) goto L4f
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3c
            i0.m r5 = (i0.m) r5     // Catch: java.lang.Throwable -> L3c
            if (r5 == 0) goto L4f
            android.content.res.Configuration r6 = r5.f16000b     // Catch: java.lang.Throwable -> L3c
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3c
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L4c
            if (r8 != 0) goto L3e
            int r6 = r5.f16001c     // Catch: java.lang.Throwable -> L3c
            if (r6 == 0) goto L48
            goto L3e
        L3c:
            r8 = move-exception
            goto L91
        L3e:
            if (r8 == 0) goto L4c
            int r6 = r5.f16001c     // Catch: java.lang.Throwable -> L3c
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3c
            if (r6 != r7) goto L4c
        L48:
            android.content.res.ColorStateList r3 = r5.f15999a     // Catch: java.lang.Throwable -> L3c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            goto L51
        L4c:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3c
        L4f:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            r3 = r4
        L51:
            if (r3 == 0) goto L54
            return r3
        L54:
            java.lang.ThreadLocal r2 = i0.q.f16004a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L66
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L66:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L75
            r3 = 31
            if (r2 > r3) goto L75
            goto L86
        L75:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = i0.c.a(r0, r2, r8)     // Catch: java.lang.Exception -> L7e
            goto L86
        L7e:
            r2 = move-exception
            java.lang.String r3 = "ResourcesCompat"
            java.lang.String r5 = "Failed to inflate ColorStateList, leaving it to the framework"
            android.util.Log.w(r3, r5, r2)
        L86:
            if (r4 == 0) goto L8c
            i0.q.a(r1, r9, r4, r8)
            goto L90
        L8c:
            android.content.res.ColorStateList r4 = i0.l.b(r0, r9, r8)
        L90:
            return r4
        L91:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3c
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.d.C(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static Set D() {
        try {
            Object objInvoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", null).invoke(null, null);
            if (objInvoke == null) {
                return Collections.EMPTY_SET;
            }
            Set set = (Set) objInvoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.EMPTY_SET;
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.EMPTY_SET;
        }
    }

    public static int F(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    public static String G(Context context) {
        String str = context.getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (l.e(context, str) == 0) {
            return str;
        }
        throw new RuntimeException(a0.c.l("Permission ", str, " is required by your application to receive broadcasts, please add it to your manifest"));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long H(int r13, java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.d.H(int, java.lang.String):long");
    }

    public static f9.f I(f9.f fVar, String str, String str2, int i10) {
        char cCharAt;
        char cCharAt2;
        Object next;
        boolean z = (i10 & 4) != 0;
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if (!fVar.f15369l) {
            String strF = fVar.f();
            if (u.P(strF, str, false) && strF.length() != str.length() && ('a' > (cCharAt = strF.charAt(str.length())) || cCharAt >= '{')) {
                if (str2 != null) {
                    return f9.f.j(str2.concat(o.r0(strF, str)));
                }
                if (!z) {
                    return fVar;
                }
                String strR0 = o.r0(strF, str);
                if (strR0.length() != 0 && b0.w(0, strR0)) {
                    if (strR0.length() != 1 && b0.w(1, strR0)) {
                        x7.h hVarC = new x7.i(0, strR0.length() - 1, 1).iterator();
                        while (true) {
                            if (!hVarC.f22624m) {
                                next = null;
                                break;
                            }
                            next = hVarC.next();
                            if (!b0.w(((Number) next).intValue(), strR0)) {
                                break;
                            }
                        }
                        Integer num = (Integer) next;
                        if (num != null) {
                            int iIntValue = num.intValue() - 1;
                            strR0 = b0.J(strR0.substring(0, iIntValue)).concat(strR0.substring(iIntValue));
                        } else {
                            strR0 = b0.J(strR0);
                        }
                    } else if (strR0.length() != 0 && 'A' <= (cCharAt2 = strR0.charAt(0)) && cCharAt2 < '[') {
                        strR0 = Character.toLowerCase(cCharAt2) + strR0.substring(1);
                    }
                }
                if (f9.f.k(strR0)) {
                    return f9.f.j(strR0);
                }
            }
        }
        return null;
    }

    public static int J(x7.i iVar) {
        v7.c cVar = v7.d.f22177i;
        try {
            int i10 = iVar.f22619i;
            if (iVar.isEmpty()) {
                throw new IllegalArgumentException("Cannot get random in empty range: " + iVar);
            }
            int i11 = iVar.f22620l;
            if (i11 < Integer.MAX_VALUE) {
                return v7.d.f22178l.e(i10, i11 + 1);
            }
            if (i10 <= Integer.MIN_VALUE) {
                return v7.d.f22178l.d();
            }
            return v7.d.f22178l.e(i10 - 1, i11) + 1;
        } catch (IllegalArgumentException e5) {
            throw new NoSuchElementException(e5.getMessage());
        }
    }

    public static byte[] K(long j10, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        File file = new File(str);
        if (!file.exists()) {
            throw new IOException(a0.c.l("File '", file.getName(), "' doesn't exists"));
        }
        if (!file.isFile()) {
            throw new IOException(a0.c.l("Reading path ", str, " failed, because it's not a file."));
        }
        if (!file.canRead()) {
            throw new IOException(a0.c.l("Reading the item ", str, " failed, because can't read the file."));
        }
        if (file.length() > j10) {
            throw new IOException(String.format("Reading file failed, because size located at '%s' with %d bytes is bigger than the maximum allowed size of %d bytes.", str, Long.valueOf(file.length()), Long.valueOf(j10)));
        }
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bArr = new byte[1024];
                } finally {
                }
            } finally {
            }
            while (true) {
                int i10 = bufferedInputStream.read(bArr);
                if (i10 == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.write(bArr, 0, i10);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static b9.a L(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        x7.i iVar = new x7.i(1, dataInputStream.readInt(), 1);
        ArrayList arrayList = new ArrayList(s.U(iVar, 10));
        x7.h hVarC = iVar.iterator();
        while (hVarC.f22624m) {
            hVarC.nextInt();
            arrayList.add(Integer.valueOf(dataInputStream.readInt()));
        }
        int[] iArrB1 = x.b1(arrayList);
        return new b9.a(Arrays.copyOf(iArrB1, iArrB1.length));
    }

    public static String M(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb2.append(line);
            }
            while (true) {
                String line2 = bufferedReader.readLine();
                if (line2 == null) {
                    bufferedReader.close();
                    return sb2.toString();
                }
                sb2.append("\n");
                sb2.append(line2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void N(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i10) {
        int i11 = i10 & 2;
        if (i11 == 0 && (i10 & 4) == 0) {
            throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
        }
        if (i11 != 0 && (i10 & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
        }
        int i12 = Build.VERSION.SDK_INT;
        if (i12 >= 33) {
            androidx.core.content.g.a(context, broadcastReceiver, intentFilter, null, null, i10);
            return;
        }
        if (i12 >= 26) {
            androidx.core.content.d.a(context, broadcastReceiver, intentFilter, null, null, i10);
        } else if ((i10 & 4) != 0) {
            context.registerReceiver(broadcastReceiver, intentFilter, G(context), null);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, null, null);
        }
    }

    public static x7.g P(x7.i iVar) {
        return new x7.g(iVar.f22620l, iVar.f22619i, -iVar.f22621m);
    }

    public static void S(Status status, Object obj, com.google.android.gms.tasks.i iVar) {
        if (status.d()) {
            iVar.b(obj);
        } else {
            iVar.a(com.google.android.gms.common.internal.t.m(status));
        }
    }

    public static void U(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            androidx.core.content.d.b(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public static x7.g V(x7.i iVar, int i10) {
        boolean z = i10 > 0;
        Integer numValueOf = Integer.valueOf(i10);
        if (!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
        }
        int i11 = iVar.f22619i;
        int i12 = iVar.f22620l;
        if (iVar.f22621m <= 0) {
            i10 = -i10;
        }
        return new x7.g(i11, i12, i10);
    }

    public static x7.i X(int i10, int i11) {
        if (i11 > Integer.MIN_VALUE) {
            return new x7.i(i10, i11 - 1, 1);
        }
        x7.i iVar = x7.i.f22626n;
        return x7.i.f22626n;
    }

    public static x7.l Y(long j10, long j11) {
        if (j11 > Long.MIN_VALUE) {
            return new x7.l(j10, j11 - 1);
        }
        x7.l lVar = x7.l.f22634n;
        return x7.l.f22634n;
    }

    public static final String a(Method method) {
        return method.getName() + r.f0(method.getParameterTypes(), "", "(", ")", a8.b.f160w, 24) + m8.c.b(method.getReturnType());
    }

    public static final void b(kb.a aVar, kb.b bVar, String str) {
        kb.e.f19675i.fine(bVar.f19667b + ' ' + String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1)) + ": " + aVar.f19662a);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x007e -> B:25:0x0061). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0081 -> B:25:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(java.util.List r6, z0.i r7, f7.c r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof z0.d
            if (r0 == 0) goto L13
            r0 = r8
            z0.d r0 = (z0.d) r0
            int r1 = r0.f23076n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23076n = r1
            goto L18
        L13:
            z0.d r0 = new z0.d
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f23075m
            int r1 = r0.f23076n
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.util.Iterator r6 = r0.f23074l
            java.io.Serializable r7 = r0.f23073i
            kotlin.jvm.internal.k0 r7 = (kotlin.jvm.internal.k0) r7
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L30
            goto L61
        L30:
            r8 = move-exception
            goto L7a
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.io.Serializable r6 = r0.f23073i
            java.util.List r6 = (java.util.List) r6
            k2.c.G(r8)
            goto L58
        L42:
            java.util.ArrayList r8 = androidx.compose.foundation.c.w(r8)
            z0.f r1 = new z0.f
            r5 = 0
            r1.<init>(r6, r8, r5)
            r0.f23073i = r8
            r0.f23076n = r3
            java.lang.Object r6 = r7.a(r1, r0)
            if (r6 != r4) goto L57
            goto L8f
        L57:
            r6 = r8
        L58:
            kotlin.jvm.internal.k0 r7 = new kotlin.jvm.internal.k0
            r7.<init>()
            java.util.Iterator r6 = r6.iterator()
        L61:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L87
            java.lang.Object r8 = r6.next()
            r7.l r8 = (r7.l) r8
            r0.f23073i = r7     // Catch: java.lang.Throwable -> L30
            r0.f23074l = r6     // Catch: java.lang.Throwable -> L30
            r0.f23076n = r2     // Catch: java.lang.Throwable -> L30
            java.lang.Object r8 = r8.invoke(r0)     // Catch: java.lang.Throwable -> L30
            if (r8 != r4) goto L61
            goto L8f
        L7a:
            java.lang.Object r1 = r7.f19746i
            if (r1 != 0) goto L81
            r7.f19746i = r8
            goto L61
        L81:
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            com.google.common.util.concurrent.r0.a(r1, r8)
            goto L61
        L87:
            java.lang.Object r6 = r7.f19746i
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            if (r6 != 0) goto L90
            x6.t0 r4 = x6.t0.f22605a
        L8f:
            return r4
        L90:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.d.c(java.util.List, z0.i, f7.c):java.lang.Object");
    }

    public static final void e(w wVar, Throwable th) {
        CancellationException cancellationExceptionA = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationExceptionA == null) {
            cancellationExceptionA = m0.a("Channel was consumed, consumer had failed", th);
        }
        wVar.cancel(cancellationExceptionA);
    }

    public static int f(Context context, String str) {
        boolean zA;
        Method method;
        Integer num;
        if (str == null) {
            throw new NullPointerException("permission must be non-null");
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        p1 p1Var = new p1(context);
        if (i10 >= 24) {
            zA = i1.a(p1Var.f2041b);
        } else {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i11 = applicationInfo.uid;
            try {
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                Class<?> cls2 = Integer.TYPE;
                method = cls.getMethod("checkOpNoThrow", cls2, cls2, String.class);
                num = (Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class);
                num.getClass();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            }
            zA = ((Integer) method.invoke(appOpsManager, num, Integer.valueOf(i11), packageName)).intValue() == 0;
        }
        return zA ? 0 : -1;
    }

    public static final androidx.core.provider.e g(androidx.core.provider.e eVar, g8.l lVar, y yVar, int i10, x6.s sVar) {
        s8.h hVar;
        s8.a aVar = (s8.a) eVar.f2140l;
        if (yVar != null) {
            com.google.android.gms.common.api.internal.r rVar = new com.google.android.gms.common.api.internal.r();
            rVar.f12787b = eVar;
            rVar.f12788c = lVar;
            rVar.f12786a = i10;
            ArrayList typeParameters = yVar.getTypeParameters();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it = typeParameters.iterator();
            int i11 = 0;
            while (it.hasNext()) {
                linkedHashMap.put(it.next(), Integer.valueOf(i11));
                i11++;
            }
            rVar.f12789d = linkedHashMap;
            rVar.f12790e = ((s8.a) ((androidx.core.provider.e) rVar.f12787b).f2140l).f21645a.e(new s8.d(rVar, 1));
            hVar = rVar;
        } else {
            hVar = (s8.h) eVar.f2141m;
        }
        return new androidx.core.provider.e(aVar, hVar, sVar);
    }

    public static androidx.core.provider.e h(androidx.core.provider.e eVar, g8.g gVar, w8.g gVar2, int i10) {
        if ((i10 & 2) != 0) {
            gVar2 = null;
        }
        return g(eVar, gVar, gVar2, 0, f4.f.o(3, new l0(eVar, gVar, 19)));
    }

    public static int i(int i10, int i11) {
        return i10 < i11 ? i11 : i10;
    }

    public static long j(long j10, long j11) {
        return j10 < j11 ? j11 : j10;
    }

    public static Comparable k(Dp dp, Dp dp2) {
        return dp.compareTo(dp2) < 0 ? dp2 : dp;
    }

    public static double l(double d4, double d10, double d11) {
        if (d10 <= d11) {
            return d4 < d10 ? d10 : d4 > d11 ? d11 : d4;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d11 + " is less than minimum " + d10 + '.');
    }

    public static float m(float f10, float f11, float f12) {
        if (f11 <= f12) {
            return f10 < f11 ? f11 : f10 > f12 ? f12 : f10;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f12 + " is less than minimum " + f11 + '.');
    }

    public static int n(int i10, int i11, int i12) {
        if (i11 <= i12) {
            return i10 < i11 ? i11 : i10 > i12 ? i12 : i10;
        }
        throw new IllegalArgumentException(androidx.compose.foundation.c.r("Cannot coerce value to an empty range: maximum ", i12, " is less than minimum ", i11, '.'));
    }

    public static long o(long j10, long j11, long j12) {
        if (j11 <= j12) {
            return j10 < j11 ? j11 : j10 > j12 ? j12 : j10;
        }
        StringBuilder sbR = androidx.compose.material3.d.r(j12, "Cannot coerce value to an empty range: maximum ", " is less than minimum ");
        sbR.append(j11);
        sbR.append('.');
        throw new IllegalArgumentException(sbR.toString());
    }

    public static Comparable p(Dp dp, Dp dp2, Dp dp3) {
        if (dp2 == null || dp3 == null) {
            if (dp2 != null && dp.compareTo(dp2) < 0) {
                return dp2;
            }
            if (dp3 != null && dp.compareTo(dp3) > 0) {
                return dp3;
            }
        } else {
            if (dp2.compareTo(dp3) > 0) {
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + dp3 + " is less than minimum " + dp2 + '.');
            }
            if (dp.compareTo(dp2) < 0) {
                return dp2;
            }
            if (dp.compareTo(dp3) > 0) {
                return dp3;
            }
        }
        return dp;
    }

    public static Comparable q(Comparable comparable, x7.e eVar) {
        if (!eVar.isEmpty()) {
            return (!eVar.a(comparable, eVar.getStart()) || eVar.a(eVar.getStart(), comparable)) ? (!eVar.a(eVar.b(), comparable) || eVar.a(comparable, eVar.b())) ? comparable : eVar.b() : eVar.getStart();
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: " + eVar + '.');
    }

    public static void r(i0 i0Var, String str, List list, ComposableLambda composableLambda, int i10) {
        if ((i10 & 2) != 0) {
            list = z.f19728i;
        }
        b1 b1Var = i0Var.f4698g;
        b1Var.getClass();
        androidx.navigation.compose.e eVar = new androidx.navigation.compose.e((androidx.navigation.compose.f) b1Var.b(f0.t(androidx.navigation.compose.f.class)), composableLambda);
        eVar.e(str);
        for (androidx.navigation.e eVar2 : list) {
            eVar.f4661o.put(eVar2.f4664a, eVar2.f4665b);
        }
        i0Var.f4700i.add(eVar);
    }

    public static long[] s(long[]... jArr) {
        long length = 0;
        for (long[] jArr2 : jArr) {
            length += (long) jArr2.length;
        }
        int i10 = (int) length;
        ac.b.g(length, "the total number of elements (%s) in the arrays must fit in an int", length == ((long) i10));
        long[] jArr3 = new long[i10];
        int length2 = 0;
        for (long[] jArr4 : jArr) {
            System.arraycopy(jArr4, 0, jArr3, length2, jArr4.length);
            length2 += jArr4.length;
        }
        return jArr3;
    }

    public static final androidx.core.provider.e t(androidx.core.provider.e eVar, h8.h hVar) {
        return hVar.isEmpty() ? eVar : new androidx.core.provider.e((s8.a) eVar.f2140l, (s8.h) eVar.f2141m, f4.f.o(3, new l0(eVar, hVar, 20)));
    }

    public static int u(String str, int i10, int i11, boolean z) {
        while (i10 < i11) {
            char cCharAt = str.charAt(i10);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && cCharAt < ':') || (('a' <= cCharAt && cCharAt < '{') || (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':'))) == (!z)) {
                return i10;
            }
            i10++;
        }
        return i11;
    }

    public static s2.a v(r1 r1Var) {
        long j10 = 0;
        String strH = "";
        String strH2 = "";
        while (true) {
            int i10 = r1Var.i();
            if (i10 == 0) {
                return new s2.a(j10, strH, strH2);
            }
            int i11 = i10 >>> 3;
            int i12 = i10 & 7;
            switch (i11) {
                case 1:
                    r1.b(i11, 0, i12);
                    r1Var.j();
                    break;
                case 2:
                    r1.b(i11, 0, i12);
                    j10 = r1Var.j();
                    break;
                case 3:
                    r1.b(i11, 0, i12);
                    r1Var.j();
                    break;
                case 4:
                    r1.b(i11, 2, i12);
                    strH = r1Var.h();
                    break;
                case 5:
                    r1.b(i11, 0, i12);
                    r1Var.j();
                    break;
                case 6:
                    r1.b(i11, 2, i12);
                    strH2 = r1Var.h();
                    break;
                case 7:
                    r1.b(i11, 0, i12);
                    r1Var.j();
                    break;
                case 8:
                    r1.b(i11, 2, i12);
                    r1Var.h();
                    break;
                default:
                    r1Var.l(i12);
                    break;
            }
        }
    }

    public static io.sentry.util.l w(r1 r1Var) {
        while (true) {
            int i10 = r1Var.i();
            if (i10 == 0) {
                return new io.sentry.util.l(23);
            }
            int i11 = i10 >>> 3;
            int i12 = i10 & 7;
            if (i11 == 1) {
                r1.b(i11, 2, i12);
                r1Var.h();
            } else if (i11 == 2) {
                r1.b(i11, 2, i12);
                r1Var.h();
            } else if (i11 == 3) {
                r1.b(i11, 0, i12);
                r1Var.j();
            } else if (i11 == 4) {
                r1.b(i11, 2, i12);
                r1Var.f();
            } else if (i11 != 6) {
                r1Var.l(i12);
            } else {
                r1.b(i11, 2, i12);
                r1 r1VarG = r1Var.g();
                while (true) {
                    int i13 = r1VarG.i();
                    if (i13 != 0) {
                        int i14 = i13 >>> 3;
                        int i15 = i13 & 7;
                        if (i14 != 1) {
                            r1VarG.l(i15);
                        } else {
                            r1.b(i14, 2, i15);
                            r1VarG.f();
                        }
                    }
                }
            }
        }
    }

    public static void x(r1 r1Var, HashMap map) {
        int i10;
        int i11;
        r1 r1Var2;
        int i12 = 0;
        s2.d dVar = null;
        int iJ = 0;
        while (true) {
            int i13 = r1Var.i();
            if (i13 == 0) {
                if (dVar != null) {
                    map.put(Integer.valueOf(iJ), dVar);
                    return;
                }
                return;
            }
            int i14 = i13 >>> 3;
            int i15 = i13 & 7;
            int i16 = 1;
            if (i14 == 1) {
                i10 = i12;
                r1.b(i14, i10, i15);
                iJ = (int) r1Var.j();
            } else if (i14 != 2) {
                r1Var.l(i15);
                i10 = i12;
            } else {
                r1.b(i14, 2, i15);
                r1 r1VarG = r1Var.g();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                int iJ2 = i12;
                String strH = "";
                while (true) {
                    int i17 = r1VarG.i();
                    if (i17 != 0) {
                        int i18 = i17 >>> 3;
                        int i19 = i17 & 7;
                        switch (i18) {
                            case 1:
                                i11 = i12;
                                r1Var2 = r1VarG;
                                r1.b(i18, i11, i19);
                                iJ2 = (int) r1Var2.j();
                                break;
                            case 2:
                                r1Var2 = r1VarG;
                                r1.b(i18, 2, i19);
                                strH = r1Var2.h();
                                i11 = 0;
                                break;
                            case 3:
                                r1.b(i18, 2, i19);
                                r1 r1VarG2 = r1VarG.g();
                                String strH2 = "";
                                long j10 = 0;
                                while (true) {
                                    int i20 = r1VarG2.i();
                                    if (i20 == 0) {
                                        r1Var2 = r1VarG;
                                        arrayList.add(new s2.c(strH2, j10));
                                        i11 = 0;
                                    } else {
                                        int i21 = i20 >>> 3;
                                        r1 r1Var3 = r1VarG;
                                        int i22 = i20 & 7;
                                        if (i21 == i16) {
                                            r1.b(i21, 2, i22);
                                            strH2 = r1VarG2.h();
                                        } else if (i21 != 2) {
                                            r1VarG2.l(i22);
                                        } else {
                                            r1.b(i21, 0, i22);
                                            j10 = r1VarG2.j();
                                        }
                                        r1VarG = r1Var3;
                                        i16 = 1;
                                    }
                                    break;
                                }
                                break;
                            case 4:
                                r1.b(i18, 2, i19);
                                arrayList4.add(v(r1VarG.g()));
                                r1Var2 = r1VarG;
                                i11 = 0;
                                break;
                            case 5:
                                r1.b(i18, 2, i19);
                                arrayList5.add(w(r1VarG.g()));
                                r1Var2 = r1VarG;
                                i11 = 0;
                                break;
                            case 6:
                                r1.b(i18, i12, i19);
                                r1VarG.j();
                                i11 = i12;
                                r1Var2 = r1VarG;
                                break;
                            case 7:
                                r1.b(i18, 2, i19);
                                arrayList2.add(r1VarG.h());
                                r1Var2 = r1VarG;
                                i11 = 0;
                                break;
                            case 8:
                                r1.b(i18, i12, i19);
                                r1VarG.j();
                                i11 = i12;
                                r1Var2 = r1VarG;
                                break;
                            case 9:
                                r1.b(i18, 2, i19);
                                arrayList3.add(r1VarG.h());
                                r1Var2 = r1VarG;
                                i11 = 0;
                                break;
                            default:
                                r1VarG.l(i19);
                                r1Var2 = r1VarG;
                                i11 = 0;
                                break;
                        }
                        i12 = i11;
                        r1VarG = r1Var2;
                        i16 = 1;
                    } else {
                        i10 = i12;
                        dVar = new s2.d(iJ2, strH, arrayList, arrayList2, arrayList3, arrayList4, arrayList5);
                    }
                }
            }
            i12 = i10;
        }
    }

    public static boolean y(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return true;
        }
        for (File file2 : fileArrListFiles) {
            if (!y(file2)) {
                return false;
            }
        }
        return file.delete();
    }

    public static boolean z(String str, String str2) {
        return p.a(str, str2) || (u.K(str, str2, false) && str.charAt((str.length() - str2.length()) - 1) == '.' && !ib.c.f16274g.e(str));
    }

    public abstract InputFilter[] E(InputFilter[] inputFilterArr);

    public abstract void Q(boolean z);

    public abstract void R(boolean z);

    public abstract void T();

    public abstract void W();

    public boolean d() {
        return false;
    }

    public void O() {
    }
}
