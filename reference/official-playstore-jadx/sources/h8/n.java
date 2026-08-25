package h8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.collections.h0;
import kotlin.collections.r;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public enum n {
    CLASS(true),
    ANNOTATION_CLASS(true),
    TYPE_PARAMETER(false),
    PROPERTY(true),
    FIELD(true),
    LOCAL_VARIABLE(true),
    VALUE_PARAMETER(true),
    CONSTRUCTOR(true),
    FUNCTION(true),
    PROPERTY_GETTER(true),
    PROPERTY_SETTER(true),
    TYPE(false),
    /* JADX INFO: Fake field, exist only in values array */
    EXPRESSION(false),
    FILE(false),
    /* JADX INFO: Fake field, exist only in values array */
    PROPERTY_PARAMETER(false),
    /* JADX INFO: Fake field, exist only in values array */
    TYPE_PROJECTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    STAR_PROJECTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    PROPERTY_PARAMETER(false),
    CLASS_ONLY(false),
    OBJECT(false),
    STANDALONE_OBJECT(false),
    COMPANION_OBJECT(false),
    INTERFACE(false),
    ENUM_CLASS(false),
    ENUM_ENTRY(false),
    LOCAL_CLASS(false),
    /* JADX INFO: Fake field, exist only in values array */
    LOCAL_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITH_BACKING_FIELD(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITH_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITH_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE(false),
    /* JADX INFO: Fake field, exist only in values array */
    BACKING_FIELD(true),
    /* JADX INFO: Fake field, exist only in values array */
    INITIALIZER(false),
    /* JADX INFO: Fake field, exist only in values array */
    DESTRUCTURING_DECLARATION(false),
    /* JADX INFO: Fake field, exist only in values array */
    LAMBDA_EXPRESSION(false),
    /* JADX INFO: Fake field, exist only in values array */
    ANONYMOUS_FUNCTION(false),
    /* JADX INFO: Fake field, exist only in values array */
    OBJECT_LITERAL(false);

    public static final Object A;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final HashMap f15877l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Set f15878m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Set f15879n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final List f15880o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final List f15881p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final List f15882q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final List f15883r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final List f15884s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final List f15885t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final List f15886u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final List f15887v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final List f15888w;
    public static final List x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final List f15889y;
    public static final List z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f15890i;

    static {
        for (n nVar : values()) {
            f15877l.put(nVar.name(), nVar);
        }
        n[] nVarArrValues = values();
        ArrayList arrayList = new ArrayList();
        for (n nVar2 : nVarArrValues) {
            if (nVar2.f15890i) {
                arrayList.add(nVar2);
            }
        }
        f15878m = x.g1(arrayList);
        f15879n = r.p0(values());
        n nVar3 = CLASS;
        f15880o = t7.a.E(ANNOTATION_CLASS, nVar3);
        f15881p = t7.a.E(LOCAL_CLASS, nVar3);
        f15882q = t7.a.E(CLASS_ONLY, nVar3);
        n nVar4 = OBJECT;
        f15883r = t7.a.E(COMPANION_OBJECT, nVar4, nVar3);
        f15884s = t7.a.E(STANDALONE_OBJECT, nVar4, nVar3);
        f15885t = t7.a.E(INTERFACE, nVar3);
        f15886u = t7.a.E(ENUM_CLASS, nVar3);
        n nVar5 = PROPERTY;
        n nVar6 = FIELD;
        f15887v = t7.a.E(ENUM_ENTRY, nVar5, nVar6);
        n nVar7 = PROPERTY_SETTER;
        f15888w = Collections.singletonList(nVar7);
        n nVar8 = PROPERTY_GETTER;
        x = Collections.singletonList(nVar8);
        f15889y = Collections.singletonList(FUNCTION);
        n nVar9 = FILE;
        z = Collections.singletonList(nVar9);
        d dVar = d.CONSTRUCTOR_PARAMETER;
        n nVar10 = VALUE_PARAMETER;
        A = h0.t0(new x6.x(dVar, nVar10), new x6.x(d.FIELD, nVar6), new x6.x(d.PROPERTY, nVar5), new x6.x(d.FILE, nVar9), new x6.x(d.PROPERTY_GETTER, nVar8), new x6.x(d.PROPERTY_SETTER, nVar7), new x6.x(d.RECEIVER, nVar10), new x6.x(d.SETTER_PARAMETER, nVar10), new x6.x(d.PROPERTY_DELEGATE_FIELD, nVar6));
    }

    n(boolean z5) {
        this.f15890i = z5;
    }
}
