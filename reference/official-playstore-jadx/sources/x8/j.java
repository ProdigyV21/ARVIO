package x8;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f22657a = new d(g.f22649l, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f22658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f22659c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LinkedHashMap f22660d;

    static {
        g gVar = g.f22650m;
        f22658b = new d(gVar, false);
        f22659c = new d(gVar, true);
        String strConcat = "java/lang/".concat("Object");
        String strConcat2 = "java/util/function/".concat("Predicate");
        String strConcat3 = "java/util/function/".concat("Function");
        String strConcat4 = "java/util/function/".concat("Consumer");
        String strConcat5 = "java/util/function/".concat("BiFunction");
        String strConcat6 = "java/util/function/".concat("BiConsumer");
        String strConcat7 = "java/util/function/".concat("UnaryOperator");
        String strConcat8 = "java/util/".concat("stream/Stream");
        String strConcat9 = "java/util/".concat("Optional");
        o oVar = new o(0);
        new vb.f(oVar, "java/util/".concat("Iterator")).a("forEachRemaining", new androidx.navigation.r(strConcat4, 2));
        new vb.f(oVar, "java/lang/".concat("Iterable")).a("spliterator", new l(1, 3));
        vb.f fVar = new vb.f(oVar, "java/util/".concat("Collection"));
        fVar.a("removeIf", new androidx.navigation.r(strConcat2, 8));
        fVar.a("stream", new androidx.navigation.r(strConcat8, 9));
        fVar.a("parallelStream", new androidx.navigation.r(strConcat8, 10));
        new vb.f(oVar, "java/util/".concat("List")).a("replaceAll", new androidx.navigation.r(strConcat7, 11));
        vb.f fVar2 = new vb.f(oVar, "java/util/".concat("Map"));
        fVar2.a("forEach", new androidx.navigation.r(strConcat6, 12));
        fVar2.a("putIfAbsent", new androidx.navigation.r(strConcat, 13));
        fVar2.a("replace", new androidx.navigation.r(strConcat, 14));
        fVar2.a("replace", new androidx.navigation.r(strConcat, 15));
        fVar2.a("replaceAll", new androidx.navigation.r(strConcat5, 16));
        fVar2.a("compute", new i(strConcat, strConcat5, 0));
        fVar2.a("computeIfAbsent", new i(strConcat, strConcat3, 1));
        fVar2.a("computeIfPresent", new i(strConcat, strConcat5, 2));
        fVar2.a("merge", new i(strConcat, strConcat5, 3));
        vb.f fVar3 = new vb.f(oVar, strConcat9);
        fVar3.a("empty", new androidx.navigation.r(strConcat9, 17));
        fVar3.a("of", new i(strConcat, strConcat9, 4));
        fVar3.a("ofNullable", new i(strConcat, strConcat9, 5));
        fVar3.a("get", new androidx.navigation.r(strConcat, 18));
        fVar3.a("ifPresent", new androidx.navigation.r(strConcat4, 19));
        new vb.f(oVar, "java/lang/".concat("ref/Reference")).a("get", new androidx.navigation.r(strConcat, 20));
        new vb.f(oVar, strConcat2).a("test", new androidx.navigation.r(strConcat, 21));
        new vb.f(oVar, "java/util/function/".concat("BiPredicate")).a("test", new androidx.navigation.r(strConcat, 22));
        new vb.f(oVar, strConcat4).a("accept", new androidx.navigation.r(strConcat, 3));
        new vb.f(oVar, strConcat6).a("accept", new androidx.navigation.r(strConcat, 4));
        new vb.f(oVar, strConcat3).a("apply", new androidx.navigation.r(strConcat, 5));
        new vb.f(oVar, strConcat5).a("apply", new androidx.navigation.r(strConcat, 6));
        new vb.f(oVar, "java/util/function/".concat("Supplier")).a("get", new androidx.navigation.r(strConcat, 7));
        f22660d = oVar.f22669a;
    }
}
