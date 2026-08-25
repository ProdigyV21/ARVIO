package w9;

import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class v extends kotlin.jvm.internal.l implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f22535i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(int i10, Object obj, int i11) {
        super(i10, obj);
        this.f22535i = i11;
    }

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        switch (this.f22535i) {
            case 0:
                return "isStrictSupertype";
            default:
                return "equalTypes";
        }
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        switch (this.f22535i) {
            case 0:
                return l0.f19747a.b(w.class);
            default:
                return l0.f19747a.b(l.class);
        }
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        switch (this.f22535i) {
            case 0:
                return "isStrictSupertype(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            default:
                return "equalTypes(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f22535i) {
            case 0:
                v9.w wVar = (v9.w) obj;
                v9.w wVar2 = (v9.w) obj2;
                ((w) this.receiver).getClass();
                k.f22525b.getClass();
                l lVar = j.f22524b;
                return Boolean.valueOf(lVar.d(wVar, wVar2) && !lVar.d(wVar2, wVar));
            default:
                return Boolean.valueOf(((l) this.receiver).b((v9.w) obj, (v9.w) obj2));
        }
    }
}
