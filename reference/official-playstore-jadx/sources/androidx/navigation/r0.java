package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 extends v0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Class f4759m;

    public r0(Class cls) {
        super(cls, 0);
        if (cls.isEnum()) {
            this.f4759m = cls;
            return;
        }
        throw new IllegalArgumentException((cls + " is not an Enum type.").toString());
    }

    @Override // androidx.navigation.v0, androidx.navigation.w0
    public final String b() {
        return this.f4759m.getName();
    }

    @Override // androidx.navigation.v0
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final Enum c(String str) {
        Object obj;
        Class cls = this.f4759m;
        Object[] enumConstants = cls.getEnumConstants();
        int length = enumConstants.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                obj = null;
                break;
            }
            obj = enumConstants[i10];
            if (kotlin.text.u.L(((Enum) obj).name(), str, true)) {
                break;
            }
            i10++;
        }
        Enum r4 = (Enum) obj;
        if (r4 != null) {
            return r4;
        }
        StringBuilder sbT = a0.c.t("Enum value ", str, " not found for type ");
        sbT.append(cls.getName());
        sbT.append('.');
        throw new IllegalArgumentException(sbT.toString());
    }
}
