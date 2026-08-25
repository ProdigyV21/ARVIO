package o5;

import androidx.compose.material3.d;
import e9.f;
import kb.e;
import ob.g;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f20774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f20775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f20776c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f20777d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f20778e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f20779f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f20780g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f20781h;

    public /* synthetic */ a() {
        this.f20774a = 0;
    }

    public b a() {
        String strM = this.f20776c == 0 ? " registrationStatus" : "";
        if (((Long) this.f20780g) == null) {
            strM = strM.concat(" expiresInSecs");
        }
        if (((Long) this.f20781h) == null) {
            strM = d.m(strM, " tokenCreationEpochInSecs");
        }
        if (strM.isEmpty()) {
            return new b(this.f20775b, this.f20776c, (String) this.f20777d, (String) this.f20778e, ((Long) this.f20780g).longValue(), ((Long) this.f20781h).longValue(), (String) this.f20779f);
        }
        throw new IllegalStateException("Missing required properties:".concat(strM));
    }

    public String toString() {
        switch (this.f20774a) {
            case 2:
                return ((z8.a) this.f20777d) + " version=" + ((f) this.f20778e);
            default:
                return super.toString();
        }
    }

    public a(z8.a aVar, f fVar, String[] strArr, String[] strArr2, String[] strArr3, String str, int i10) {
        this.f20774a = 2;
        this.f20777d = aVar;
        this.f20778e = fVar;
        this.f20779f = strArr;
        this.f20780g = strArr2;
        this.f20781h = strArr3;
        this.f20775b = str;
        this.f20776c = i10;
    }

    public a(e eVar) {
        this.f20774a = 1;
        this.f20777d = eVar;
        this.f20781h = g.f20898a;
    }
}
