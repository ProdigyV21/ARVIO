package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends n0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f7468b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(ab.g gVar, int i10) {
        super(gVar);
        this.f7468b = i10;
    }

    @Override // ab.g
    public final String h() {
        switch (this.f7468b) {
            case 0:
                return "kotlin.Array";
            case 1:
                return "kotlin.collections.ArrayList";
            case 2:
                return "kotlin.collections.HashSet";
            default:
                return "kotlin.collections.LinkedHashSet";
        }
    }
}
