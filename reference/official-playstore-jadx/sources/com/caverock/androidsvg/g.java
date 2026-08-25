package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12316a;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.caverock.androidsvg.e
    public final boolean a(b1 b1Var) {
        switch (this.f12316a) {
            case 0:
                if (!(b1Var instanceof z0) || ((z0) b1Var).getChildren().size() == 0) {
                }
                break;
            case 1:
                if (b1Var.f12293b == null) {
                }
                break;
        }
        return false;
    }

    public final String toString() {
        switch (this.f12316a) {
            case 0:
                return "empty";
            case 1:
                return "root";
            default:
                return "target";
        }
    }
}
