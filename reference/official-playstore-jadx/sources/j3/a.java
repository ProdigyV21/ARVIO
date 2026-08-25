package j3;

import c3.e;
import com.google.android.datatransport.runtime.h;
import com.google.android.datatransport.runtime.i;
import com.google.android.datatransport.runtime.scheduling.persistence.d;
import com.google.android.datatransport.runtime.w;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import k3.j;
import z2.g;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Logger f19199f = Logger.getLogger(w.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f19200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f19201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f19202c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f19203d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l3.a f19204e;

    public a(Executor executor, e eVar, j jVar, d dVar, l3.a aVar) {
        this.f19201b = executor;
        this.f19202c = eVar;
        this.f19200a = jVar;
        this.f19203d = dVar;
        this.f19204e = aVar;
    }

    @Override // j3.c
    public final void a(i iVar, h hVar, g gVar) {
        this.f19201b.execute(new androidx.media3.exoplayer.source.preload.b(this, iVar, gVar, hVar, 6));
    }
}
