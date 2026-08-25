package h9;

import java.util.ArrayList;
import java.util.Set;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public enum i {
    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true),
    CONST(true),
    LATEINIT(true),
    FUN(true),
    VALUE(true);


    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Set f15912l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Set f15913m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f15925i;

    static {
        i[] iVarArrValues = values();
        ArrayList arrayList = new ArrayList();
        for (i iVar : iVarArrValues) {
            if (iVar.f15925i) {
                arrayList.add(iVar);
            }
        }
        f15912l = x.g1(arrayList);
        f15913m = kotlin.collections.r.p0(values());
    }

    i(boolean z) {
        this.f15925i = z;
    }
}
