package l;

import android.graphics.drawable.Animatable;
import androidx.vectordrawable.graphics.drawable.h;

/* JADX INFO: loaded from: classes.dex */
public final class a extends qb.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19980a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Animatable f19981b;

    public /* synthetic */ a(Animatable animatable, int i10) {
        this.f19980a = i10;
        this.f19981b = animatable;
    }

    @Override // qb.d
    public final void T() {
        switch (this.f19980a) {
            case 0:
                this.f19981b.start();
                break;
            default:
                ((h) this.f19981b).start();
                break;
        }
    }

    @Override // qb.d
    public final void W() {
        switch (this.f19980a) {
            case 0:
                this.f19981b.stop();
                break;
            default:
                ((h) this.f19981b).stop();
                break;
        }
    }
}
