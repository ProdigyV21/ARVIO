package io.ktor.server.engine;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.common.util.concurrent.r0;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.collections.q0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import x7.h;
import x7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u0006H\u0002\u001a\u0014\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b*\u00020\u0003H\u0002\u001a\u0014\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\b*\u00020\u0003H\u0002¨\u0006\n"}, d2 = {"allURLs", "", "Ljava/net/URL;", "Ljava/lang/ClassLoader;", "findURLClassPathField", "Ljava/lang/reflect/Field;", "Ljava/lang/Class;", "urlClassPath", "", "urlClassPathByPackagesList", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClassLoadersKt {
    public static final Set<URL> allURLs(ClassLoader classLoader) {
        Set<URL> setAllURLs;
        ClassLoader parent = classLoader.getParent();
        if (parent == null || (setAllURLs = allURLs(parent)) == null) {
            setAllURLs = b0.f19686i;
        }
        if (classLoader instanceof URLClassLoader) {
            return q0.b0(x.g1(r.X(((URLClassLoader) classLoader).getURLs())), setAllURLs);
        }
        List<URL> listUrlClassPath = urlClassPath(classLoader);
        return listUrlClassPath == null ? setAllURLs : q0.b0(setAllURLs, listUrlClassPath);
    }

    private static final Field findURLClassPathField(Class<?> cls) {
        Field field;
        Field fieldFindURLClassPathField;
        Field[] declaredFields = cls.getDeclaredFields();
        int length = declaredFields.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                field = null;
                break;
            }
            field = declaredFields[i10];
            if (p.a(field.getName(), "ucp") && field.getType().getSimpleName().equals("URLClassPath")) {
                break;
            }
            i10++;
        }
        if (field != null) {
            return field;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || (fieldFindURLClassPathField = findURLClassPathField(superclass)) == null) {
            return null;
        }
        return fieldFindURLClassPathField;
    }

    private static final List<URL> urlClassPath(ClassLoader classLoader) {
        Method method;
        try {
            try {
                Field fieldFindURLClassPathField = findURLClassPathField(classLoader.getClass());
                if (fieldFindURLClassPathField == null) {
                    return null;
                }
                fieldFindURLClassPathField.setAccessible(true);
                Object obj = fieldFindURLClassPathField.get(classLoader);
                if (obj == null || (method = obj.getClass().getMethod("getURLs", null)) == null) {
                    return null;
                }
                method.setAccessible(true);
                URL[] urlArr = (URL[]) method.invoke(obj, null);
                if (urlArr != null) {
                    return r.o0(urlArr);
                }
                return null;
            } catch (Throwable unused) {
                return urlClassPathByPackagesList(classLoader);
            }
        } catch (Throwable unused2) {
            return null;
        }
        return null;
    }

    private static final List<URL> urlClassPathByPackagesList(ClassLoader classLoader) throws IOException {
        List<String> listPackagesList$ktor_server_host_common = new ClassLoaderDelegate(classLoader).packagesList$ktor_server_host_common();
        ArrayList<String> arrayList = new ArrayList(s.U(listPackagesList$ktor_server_host_common, 10));
        Iterator<T> it = listPackagesList$ktor_server_host_common.iterator();
        while (it.hasNext()) {
            arrayList.add(((String) it.next()).replace('.', '/'));
        }
        HashSet hashSet = new HashSet();
        for (String str : arrayList) {
            List listX0 = o.x0(str, new char[]{'/'}, 6);
            i iVar = new i(1, listX0.size(), 1);
            ArrayList arrayList2 = new ArrayList(s.U(iVar, 10));
            h it2 = iVar.iterator();
            while (it2.f22624m) {
                arrayList2.add(x.u0(listX0.subList(0, it2.nextInt()), DomExceptionUtils.SEPARATOR, null, null, null, 62));
            }
            x.b0(hashSet, x.H0(str, arrayList2));
        }
        ArrayList arrayListH0 = x.H0("", x.W0(hashSet, new Comparator() { // from class: io.ktor.server.engine.ClassLoadersKt$urlClassPathByPackagesList$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                String str2 = (String) t2;
                int i10 = 0;
                for (int i11 = 0; i11 < str2.length(); i11++) {
                    if (str2.charAt(i11) == '/') {
                        i10++;
                    }
                }
                Integer numValueOf = Integer.valueOf(i10);
                String str3 = (String) t10;
                int i12 = 0;
                for (int i13 = 0; i13 < str3.length(); i13++) {
                    if (str3.charAt(i13) == '/') {
                        i12++;
                    }
                }
                return r0.e(numValueOf, Integer.valueOf(i12));
            }
        }));
        ArrayList arrayList3 = new ArrayList();
        Iterator it3 = arrayListH0.iterator();
        while (it3.hasNext()) {
            Enumeration<URL> resources = classLoader.getResources((String) it3.next());
            x.b0(arrayList3, resources != null ? Collections.list(resources) : z.f19728i);
        }
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : arrayList3) {
            String path = ((URL) obj).getPath();
            if (hashSet2.add(o.G0('!', path, path))) {
                arrayList4.add(obj);
            }
        }
        return arrayList4;
    }
}
