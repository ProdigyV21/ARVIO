package hb;

import androidx.emoji2.text.e0;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.common.util.concurrent.r0;
import gb.b0;
import gb.d0;
import gb.h0;
import gb.i;
import gb.i0;
import gb.j0;
import gb.l0;
import gb.p0;
import gb.s0;
import gb.t;
import java.io.EOFException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.o;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import qb.n;
import xb.j;
import xb.m;
import xc.d;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d0 f15967d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f15968b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b0 f15969c;

    static {
        Pattern pattern = d0.f15633d;
        f15967d = d.i0("application/dns-message");
    }

    public b(h0 h0Var, b0 b0Var) {
        this.f15968b = h0Var;
        this.f15969c = b0Var;
    }

    public static void b(p0 p0Var, String str, ArrayList arrayList, ArrayList arrayList2) {
        try {
            ArrayList arrayListC = c(p0Var, str);
            synchronized (arrayList) {
                arrayList.addAll(arrayListC);
            }
        } catch (Exception e5) {
            synchronized (arrayList2) {
                arrayList2.add(e5);
            }
        }
    }

    public static ArrayList c(p0 p0Var, String str) {
        if (p0Var.f15788s == null && p0Var.f15781l != i0.HTTP_2) {
            n nVar = n.f21385a;
            n.j(n.f21385a, "Incorrect protocol: " + p0Var.f15781l, 4);
        }
        try {
            if (!p0Var.k()) {
                throw new IOException("response: " + p0Var.f15783n + ' ' + p0Var.f15782m);
            }
            s0 s0Var = p0Var.f15786q;
            if (s0Var.k() <= 65536) {
                ArrayList arrayListA = c.a(str, s0Var.o().x0());
                p0Var.close();
                return arrayListA;
            }
            throw new IOException("response size exceeds limit (65536 bytes): " + s0Var.k() + " bytes");
        } finally {
        }
    }

    public final void a(String str, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, int i10) throws EOFException {
        List<String> listX0;
        j0 j0Var = new j0();
        d0 d0Var = f15967d;
        j0Var.f15724c.h("Accept", d0Var.f15635a);
        int i11 = c.f15970a;
        j jVar = new j();
        jVar.Q0(0);
        jVar.Q0(256);
        int i12 = 1;
        jVar.Q0(1);
        jVar.Q0(0);
        jVar.Q0(0);
        jVar.Q0(0);
        j jVar2 = new j();
        List listX02 = o.x0(str, new char[]{'.'}, 6);
        if (listX02.isEmpty()) {
            listX0 = z.f19728i;
        } else {
            ListIterator listIterator = listX02.listIterator(listX02.size());
            while (listIterator.hasPrevious()) {
                if (((String) listIterator.previous()).length() != 0) {
                    listX0 = x.X0(listX02, listIterator.nextIndex() + 1);
                    break;
                }
            }
            listX0 = z.f19728i;
        }
        for (String str2 : listX0) {
            long jF = xb.b.f(str2);
            if (jF != str2.length()) {
                throw new IllegalArgumentException("non-ascii hostname: ".concat(str).toString());
            }
            jVar2.M0((int) jF);
            jVar2.R0(0, str2.length(), str2);
        }
        jVar2.M0(0);
        jVar2.l(jVar, 0L, jVar2.f22759l);
        jVar.Q0(i10);
        jVar.Q0(1);
        m mVarC0 = jVar.c0(jVar.f22759l);
        j0Var.f15722a = this.f15969c;
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new l0(d0Var, mVarC0, i12));
        arrayList.add(this.f15968b.a(j0Var.b()));
    }

    @Override // gb.t
    public final List lookup(String str) throws Exception {
        if (PublicSuffixDatabase.f20992g.a(str) == null) {
            throw new UnknownHostException("private hosts not resolved");
        }
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(2);
        ArrayList arrayList3 = new ArrayList(5);
        a(str, arrayList, arrayList3, arrayList2, 1);
        a(str, arrayList, arrayList3, arrayList2, 28);
        CountDownLatch countDownLatch = new CountDownLatch(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ArrayList arrayList4 = arrayList3;
            arrayList3 = arrayList4;
            ((i) it.next()).K0(new e0(arrayList2, countDownLatch, this, str, arrayList4));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e5) {
            arrayList2.add(e5);
        }
        if (!arrayList3.isEmpty()) {
            return arrayList3;
        }
        if (arrayList2.isEmpty()) {
            throw new UnknownHostException(str);
        }
        Exception exc = (Exception) arrayList2.get(0);
        if (exc instanceof UnknownHostException) {
            throw exc;
        }
        UnknownHostException unknownHostException = new UnknownHostException(str);
        unknownHostException.initCause(exc);
        int size = arrayList2.size();
        for (int i10 = 1; i10 < size; i10++) {
            r0.a(unknownHostException, (Throwable) arrayList2.get(i10));
        }
        throw unknownHostException;
    }
}
