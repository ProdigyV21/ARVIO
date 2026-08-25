package f8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.q0;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashSet f15348a = q0.c0(y8.q.f("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashSet f15349b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashSet f15350c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final LinkedHashSet f15351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final LinkedHashSet f15352e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final LinkedHashSet f15353f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final LinkedHashSet f15354g;

    static {
        List<n9.c> listE = t7.a.E(n9.c.BOOLEAN, n9.c.CHAR);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (n9.c cVar : listE) {
            String strD = cVar.j().f().d();
            String[] strArr = {cVar.f20510l + "Value()" + cVar.f()};
            String strConcat = "java/lang/".concat(strD);
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, 1);
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (String str : strArr2) {
                linkedHashSet2.add(strConcat + '.' + str);
            }
            x.b0(linkedHashSet, linkedHashSet2);
        }
        f15349b = q0.b0(q0.b0(q0.b0(q0.b0(q0.b0(q0.b0(linkedHashSet, y8.q.f("List", "sort(Ljava/util/Comparator;)V", "reversed()Ljava/util/List;")), y8.q.e("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;")), y8.q.e("Double", "isInfinite()Z", "isNaN()Z")), y8.q.e("Float", "isInfinite()Z", "isNaN()Z")), y8.q.e("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V")), y8.q.e("CharSequence", "isEmpty()Z"));
        f15350c = y8.q.f("List", "getFirst()Ljava/lang/Object;", "getLast()Ljava/lang/Object;");
        f15351d = q0.b0(q0.b0(q0.b0(q0.b0(q0.b0(q0.b0(y8.q.e("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), y8.q.f("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), y8.q.e("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;")), y8.q.e("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V")), y8.q.f("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z")), y8.q.f("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "addFirst(Ljava/lang/Object;)V", "addLast(Ljava/lang/Object;)V", "removeFirst()Ljava/lang/Object;", "removeLast()Ljava/lang/Object;")), y8.q.f("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        f15352e = q0.b0(q0.b0(y8.q.f("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), y8.q.f("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V")), y8.q.f("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        n9.c cVar2 = n9.c.BYTE;
        List listE2 = t7.a.E(n9.c.BOOLEAN, cVar2, n9.c.DOUBLE, n9.c.FLOAT, cVar2, n9.c.INT, n9.c.LONG, n9.c.SHORT);
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Iterator it = listE2.iterator();
        while (it.hasNext()) {
            String strD2 = ((n9.c) it.next()).j().f().d();
            String[] strArrA = y8.q.a("Ljava/lang/String;");
            x.b0(linkedHashSet3, y8.q.e(strD2, (String[]) Arrays.copyOf(strArrA, strArrA.length)));
        }
        String[] strArrA2 = y8.q.a("D");
        LinkedHashSet linkedHashSetB0 = q0.b0(linkedHashSet3, y8.q.e("Float", (String[]) Arrays.copyOf(strArrA2, strArrA2.length)));
        String[] strArrA3 = y8.q.a("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        f15353f = q0.b0(linkedHashSetB0, y8.q.e("String", (String[]) Arrays.copyOf(strArrA3, strArrA3.length)));
        String[] strArrA4 = y8.q.a("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        f15354g = y8.q.e("Throwable", (String[]) Arrays.copyOf(strArrA4, strArrA4.length));
    }
}
