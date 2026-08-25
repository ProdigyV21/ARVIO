package io.ktor.server.http.content;

import androidx.compose.material3.d;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import ga.a;
import io.ktor.http.CodecsKt;
import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.URIFileContent;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.util.PathsKt;
import io.ktor.util.InternalAPI;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.t;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aG\u0010\n\u001a\u0004\u0018\u00010\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\n\u0010\u000b\u001aS\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e*\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\u0006H\u0000¢\u0006\u0004\b\n\u0010\u000f\u001a5\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0013\u0010\u0016\u001a\u00020\u0001*\u00020\u0001H\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a!\u0010\u0018\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "", "path", "resourcePackage", "Ljava/lang/ClassLoader;", "classLoader", "Lkotlin/Function1;", "Lio/ktor/http/ContentType;", "mimeResolve", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "resolveResource", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;Lr7/l;)Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/server/application/Application;", "Ljava/net/URL;", "Lx6/x;", "(Lio/ktor/server/application/Application;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;Lr7/l;)Lx6/x;", "url", "resourceClasspathResource", "(Ljava/net/URL;Ljava/lang/String;Lr7/l;)Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Ljava/io/File;", "findContainingJarFile", "(Ljava/lang/String;)Ljava/io/File;", "extension", "(Ljava/lang/String;)Ljava/lang/String;", "normalisedPath", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StaticContentResolutionKt {

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentResolutionKt$resolveResource$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<String, ContentType> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final ContentType invoke(String str) {
            return FileContentTypeKt.defaultForFileExtension(ContentType.INSTANCE, str);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentResolutionKt$resolveResource$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<URL, ContentType> {
        final /* synthetic */ l<String, ContentType> $mimeResolve;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super String, ContentType> lVar) {
            super(1);
            this.$mimeResolve = lVar;
        }

        @Override // r7.l
        public final ContentType invoke(URL url) {
            return (ContentType) this.$mimeResolve.invoke(StaticContentResolutionKt.extension(url.getPath()));
        }
    }

    public static final String extension(String str) {
        int iIntValue;
        int iK0 = o.k0(str, '/', 0, 6);
        Integer numValueOf = Integer.valueOf(iK0);
        if (iK0 == -1) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            iIntValue = numValueOf.intValue();
        } else {
            int iK02 = o.k0(str, '\\', 0, 6);
            Integer numValueOf2 = iK02 != -1 ? Integer.valueOf(iK02) : null;
            iIntValue = numValueOf2 != null ? numValueOf2.intValue() : 0;
        }
        int iE0 = o.e0(str, '.', iIntValue, false, 4);
        return iE0 >= 0 ? str.substring(iE0) : "";
    }

    public static final File findContainingJarFile(String str) {
        if (!u.P(str, "jar:file:", false)) {
            throw new IllegalArgumentException("Only local jars are supported (jar:file:)");
        }
        int iF0 = o.f0(str, "!", 9, false, 4);
        if (iF0 != -1) {
            return new File(CodecsKt.decodeURLPart$default(str.substring(9, iF0), 0, 0, null, 7, null));
        }
        throw new IllegalArgumentException("Jar path requires !/ separator but it is: ".concat(str).toString());
    }

    private static final String normalisedPath(String str, String str2) throws BadRequestException {
        List listX0 = o.x0(str2, new char[]{'/', '\\'}, 6);
        if (listX0.contains("..")) {
            throw new BadRequestException(d.C("Relative path should not contain path traversing characters: ", str2), null, 2, null);
        }
        if (str == null) {
            str = "";
        }
        return x.u0(PathsKt.normalizePathComponents(x.I0(o.x0(str, new char[]{'.', '/', '\\'}, 6), listX0)), DomExceptionUtils.SEPARATOR, null, null, null, 62);
    }

    public static final OutgoingContent.ReadChannelContent resolveResource(ApplicationCall applicationCall, String str, String str2, ClassLoader classLoader, l<? super String, ContentType> lVar) throws BadRequestException {
        if (u.K(str, DomExceptionUtils.SEPARATOR, false) || u.K(str, "\\", false)) {
            return null;
        }
        String strNormalisedPath = normalisedPath(str2, str);
        Iterator it = ((a) ga.r.E(new t(classLoader.getResources(strNormalisedPath)))).iterator();
        while (it.hasNext()) {
            OutgoingContent.ReadChannelContent readChannelContentResourceClasspathResource = resourceClasspathResource((URL) it.next(), strNormalisedPath, new AnonymousClass2(lVar));
            if (readChannelContentResourceClasspathResource != null) {
                return readChannelContentResourceClasspathResource;
            }
        }
        return null;
    }

    public static /* synthetic */ OutgoingContent.ReadChannelContent resolveResource$default(ApplicationCall applicationCall, String str, String str2, ClassLoader classLoader, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            classLoader = applicationCall.getApplication().getEnvironment().getClassLoader();
        }
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return resolveResource(applicationCall, str, str2, classLoader, (l<? super String, ContentType>) lVar);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @InternalAPI
    public static final OutgoingContent.ReadChannelContent resourceClasspathResource(URL url, String str, l<? super URL, ContentType> lVar) {
        String protocol = url.getProtocol();
        if (protocol == null) {
            return null;
        }
        switch (protocol.hashCode()) {
            case -341064690:
                if (!protocol.equals("resource")) {
                    return null;
                }
                break;
            case 104987:
                if (!protocol.equals("jar") || u.K(str, DomExceptionUtils.SEPARATOR, false)) {
                    return null;
                }
                JarFileContent jarFileContent = new JarFileContent(findContainingJarFile(url.toString()), str, (ContentType) lVar.invoke(url));
                if (jarFileContent.isFile()) {
                    return jarFileContent;
                }
                return null;
            case 105516:
                if (!protocol.equals("jrt")) {
                    return null;
                }
                break;
            case 3143036:
                if (!protocol.equals("file")) {
                    return null;
                }
                File file = new File(CodecsKt.decodeURLPart$default(url.getPath(), 0, 0, null, 7, null));
                if (file.isFile()) {
                    return new LocalFileContent(file, (ContentType) lVar.invoke(url));
                }
                return null;
            default:
                return null;
        }
        return new URIFileContent(url, (ContentType) lVar.invoke(url));
    }

    public static /* synthetic */ x6.x resolveResource$default(Application application, String str, String str2, ClassLoader classLoader, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            classLoader = application.getEnvironment().getClassLoader();
        }
        return resolveResource(application, str, str2, classLoader, (l<? super URL, ContentType>) lVar);
    }

    public static final x6.x resolveResource(Application application, String str, String str2, ClassLoader classLoader, l<? super URL, ContentType> lVar) throws BadRequestException {
        if (u.K(str, DomExceptionUtils.SEPARATOR, false) || u.K(str, "\\", false)) {
            return null;
        }
        String strNormalisedPath = normalisedPath(str2, str);
        for (URL url : (a) ga.r.E(new t(classLoader.getResources(strNormalisedPath)))) {
            OutgoingContent.ReadChannelContent readChannelContentResourceClasspathResource = resourceClasspathResource(url, strNormalisedPath, lVar);
            if (readChannelContentResourceClasspathResource != null) {
                return new x6.x(url, readChannelContentResourceClasspathResource);
            }
        }
        return null;
    }
}
