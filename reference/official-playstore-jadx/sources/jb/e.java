package jb;

import io.sentry.p7;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;
import xb.d0;
import xb.o0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f19420b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f19421c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f19422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f19423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f19424f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public p7 f19425g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f19426h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f19427i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ h f19428j;

    public e(h hVar, String str) {
        this.f19428j = hVar;
        this.f19419a = str;
        hVar.getClass();
        this.f19420b = new long[2];
        this.f19421c = new ArrayList();
        this.f19422d = new ArrayList();
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append('.');
        int length = sb2.length();
        for (int i10 = 0; i10 < 2; i10++) {
            sb2.append(i10);
            this.f19421c.add(new File(this.f19428j.f19435i, sb2.toString()));
            sb2.append(".tmp");
            this.f19422d.add(new File(this.f19428j.f19435i, sb2.toString()));
            sb2.setLength(length);
        }
    }

    public final f a() {
        byte[] bArr = ib.c.f16268a;
        if (!this.f19423e) {
            return null;
        }
        h hVar = this.f19428j;
        if (!hVar.f19444t && (this.f19425g != null || this.f19424f)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < 2; i10++) {
            try {
                File file = (File) this.f19421c.get(i10);
                Logger logger = d0.f22723a;
                o0 dVar = new xb.d(new FileInputStream(file), q0.f22791d, 1);
                if (!hVar.f19444t) {
                    this.f19426h++;
                    dVar = new d(dVar, hVar, this);
                }
                arrayList.add(dVar);
            } catch (FileNotFoundException unused) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ib.c.d((o0) it.next());
                }
                try {
                    hVar.T(this);
                    return null;
                } catch (IOException unused2) {
                    return null;
                }
            }
        }
        return new f(this.f19428j, this.f19419a, this.f19427i, arrayList);
    }
}
