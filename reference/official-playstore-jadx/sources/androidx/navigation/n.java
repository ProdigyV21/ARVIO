package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class n extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4725i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ s f4726l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(s sVar, int i10) {
        super(1);
        this.f4725i = i10;
        this.f4726l = sVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4725i) {
        }
        return Boolean.valueOf(!this.f4726l.f4771m.containsKey(Integer.valueOf(((d0) obj).f4662p)));
    }
}
