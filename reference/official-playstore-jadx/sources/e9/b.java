package e9;

import androidx.fragment.app.a2;
import i7.n;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f15066a = x.u0(t7.a.E('k', 'o', 't', 'l', 'i', 'n'), "", null, null, null, 62);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f15067b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listE = t7.a.E("Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D");
        int iA = n.a(0, listE.size() - 1, 2);
        if (iA >= 0) {
            int i10 = 0;
            while (true) {
                StringBuilder sb2 = new StringBuilder();
                String str = f15066a;
                sb2.append(str);
                sb2.append('/');
                sb2.append((String) listE.get(i10));
                int i11 = i10 + 1;
                linkedHashMap.put(sb2.toString(), listE.get(i11));
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append('/');
                linkedHashMap.put(a0.c.p(sb3, (String) listE.get(i10), "Array"), "[" + ((String) listE.get(i11)));
                if (i10 == iA) {
                    break;
                } else {
                    i10 += 2;
                }
            }
        }
        linkedHashMap.put(f15066a + "/Unit", "V");
        a(linkedHashMap, "Any", "java/lang/Object");
        a(linkedHashMap, "Nothing", "java/lang/Void");
        a(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : t7.a.E("String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum")) {
            a(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : t7.a.E("Iterator", "Collection", "List", "Set", "Map", "ListIterator")) {
            a(linkedHashMap, androidx.compose.material3.d.C("collections/", str3), "java/util/" + str3);
            a(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        a(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        a(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i12 = 0; i12 < 23; i12++) {
            String strI = a0.c.i(i12, "Function");
            StringBuilder sb4 = new StringBuilder();
            String str4 = f15066a;
            sb4.append(str4);
            sb4.append("/jvm/functions/Function");
            sb4.append(i12);
            a(linkedHashMap, strI, sb4.toString());
            a(linkedHashMap, "reflect/KFunction" + i12, str4 + "/reflect/KFunction");
        }
        for (String str5 : t7.a.E("Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum")) {
            a(linkedHashMap, androidx.compose.material3.d.m(str5, ".Companion"), a2.n(new StringBuilder(), f15066a, "/jvm/internal/", str5, "CompanionObject"));
        }
        f15067b = linkedHashMap;
    }

    public static final void a(LinkedHashMap linkedHashMap, String str, String str2) {
        linkedHashMap.put(f15066a + '/' + str, "L" + str2 + ';');
    }

    public static final String b(String str) {
        String str2 = (String) f15067b.get(str);
        if (str2 != null) {
            return str2;
        }
        return "L" + str.replace('.', '$') + ';';
    }
}
