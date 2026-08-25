package ba;

import androidx.media3.exoplayer.upstream.CmcdData;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7336c = 1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7337d;

    /* JADX WARN: Illegal instructions before constructor call */
    public y(int i10) {
        StringBuilder sbS = a0.c.s(i10, "must have at least ", " value parameter");
        sbS.append(i10 > 1 ? CmcdData.STREAMING_FORMAT_SS : "");
        super(sbS.toString(), 1);
        this.f7337d = i10;
    }

    @Override // ba.e
    public final boolean c(r8.g gVar) {
        switch (this.f7336c) {
            case 0:
                if (gVar.e().size() >= this.f7337d) {
                }
                break;
            default:
                if (gVar.e().size() == this.f7337d) {
                }
                break;
        }
        return false;
    }

    public y() {
        super("must have exactly 2 value parameters", 1);
        this.f7337d = 2;
    }
}
