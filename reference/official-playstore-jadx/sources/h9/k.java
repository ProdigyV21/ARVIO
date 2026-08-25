package h9;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends u7.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f15926a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Object obj, l lVar) {
        super(obj);
        this.f15926a = lVar;
    }

    @Override // u7.a
    public final boolean beforeChange(kotlin.reflect.m mVar, Object obj, Object obj2) {
        if (this.f15926a.f15927a) {
            throw new IllegalStateException("Cannot modify readonly DescriptorRendererOptions");
        }
        return true;
    }
}
