package io.ktor.http;

import ga.m;
import io.ktor.http.ContentType;
import io.ktor.util.CharsetKt;
import io.ktor.util.TextKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.text.a;
import kotlin.text.o;
import x6.i0;
import x6.s;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\r\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0004\b\f\u0010\n\u001a\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u0003¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u000f\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001aC\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0015\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0012*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0003*\u00020\u0001H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\"-\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"-\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b0\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001b\u001a\u0004\b \u0010\u001d¨\u0006\""}, d2 = {"Lio/ktor/http/ContentType$Companion;", "", "extension", "Lio/ktor/http/ContentType;", "defaultForFileExtension", "(Lio/ktor/http/ContentType$Companion;Ljava/lang/String;)Lio/ktor/http/ContentType;", "path", "defaultForFilePath", "", "fromFilePath", "(Lio/ktor/http/ContentType$Companion;Ljava/lang/String;)Ljava/util/List;", "ext", "fromFileExtension", "fileExtensions", "(Lio/ktor/http/ContentType;)Ljava/util/List;", "selectDefault", "(Ljava/util/List;)Lio/ktor/http/ContentType;", "A", "B", "Lga/m;", "Lx6/x;", "", "groupByPairs", "(Lga/m;)Ljava/util/Map;", "toContentType", "(Ljava/lang/String;)Lio/ktor/http/ContentType;", "contentTypesByExtensions$delegate", "Lx6/s;", "getContentTypesByExtensions", "()Ljava/util/Map;", "contentTypesByExtensions", "extensionsByContentType$delegate", "getExtensionsByContentType", "extensionsByContentType", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FileContentTypeKt {
    private static final s contentTypesByExtensions$delegate = new i0(FileContentTypeKt$contentTypesByExtensions$2.INSTANCE);
    private static final s extensionsByContentType$delegate = new i0(FileContentTypeKt$extensionsByContentType$2.INSTANCE);

    public static final ContentType defaultForFileExtension(ContentType.Companion companion, String str) {
        return selectDefault(fromFileExtension(ContentType.INSTANCE, str));
    }

    public static final ContentType defaultForFilePath(ContentType.Companion companion, String str) {
        return selectDefault(fromFilePath(ContentType.INSTANCE, str));
    }

    public static final List<String> fileExtensions(ContentType contentType) {
        List<String> list = getExtensionsByContentType().get(contentType);
        if (list != null) {
            return list;
        }
        List<String> list2 = getExtensionsByContentType().get(contentType.withoutParameters());
        return list2 == null ? z.f19728i : list2;
    }

    public static final List<ContentType> fromFileExtension(ContentType.Companion companion, String str) {
        for (String lowerCasePreservingASCIIRules = TextKt.toLowerCasePreservingASCIIRules(o.r0(str, ".")); lowerCasePreservingASCIIRules.length() > 0; lowerCasePreservingASCIIRules = o.D0(lowerCasePreservingASCIIRules, ".", "")) {
            List<ContentType> list = getContentTypesByExtensions().get(lowerCasePreservingASCIIRules);
            if (list != null) {
                return list;
            }
        }
        return z.f19728i;
    }

    public static final List<ContentType> fromFilePath(ContentType.Companion companion, String str) {
        int iE0 = o.e0(str, '.', o.l0(str, CharsetKt.toCharArray("/\\"), o.b0(str)) + 1, false, 4);
        return iE0 == -1 ? z.f19728i : fromFileExtension(companion, str.substring(iE0 + 1));
    }

    private static final Map<String, List<ContentType>> getContentTypesByExtensions() {
        return (Map) contentTypesByExtensions$delegate.getValue();
    }

    private static final Map<ContentType, List<String>> getExtensionsByContentType() {
        return (Map) extensionsByContentType$delegate.getValue();
    }

    public static final <A, B> Map<A, List<B>> groupByPairs(m<? extends x> mVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : mVar) {
            Object obj2 = ((x) obj).f22608i;
            Object arrayList = linkedHashMap.get(obj2);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(obj2, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            Iterable iterable = (Iterable) entry.getValue();
            ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList2.add(((x) it.next()).f22609l);
            }
            linkedHashMap2.put(key, arrayList2);
        }
        return linkedHashMap2;
    }

    public static final ContentType selectDefault(List<ContentType> list) {
        ContentType octetStream = (ContentType) kotlin.collections.x.o0(list);
        if (octetStream == null) {
            octetStream = ContentType.Application.INSTANCE.getOctetStream();
        }
        return (p.a(octetStream.getContentType(), "text") && ContentTypesKt.charset(octetStream) == null) ? ContentTypesKt.withCharset(octetStream, a.f19924a) : octetStream;
    }

    public static final ContentType toContentType(String str) {
        try {
            return ContentType.INSTANCE.parse(str);
        } catch (Throwable th) {
            throw new IllegalArgumentException("Failed to parse ".concat(str), th);
        }
    }
}
