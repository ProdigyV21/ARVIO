package io.ktor.server.http.content;

import a0.c;
import androidx.compose.material3.d;
import f4.f;
import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.VersionsKt;
import io.ktor.util.cio.ByteBufferPoolKt;
import io.ktor.util.cio.InputStreamAdaptersKt;
import io.ktor.utils.io.ByteReadChannel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import l7.i;
import r7.a;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0013R\u001d\u0010\u001f\u001a\u0004\u0018\u00010\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010$\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010#R\u001b\u0010'\u001a\u00020%8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u001c\u001a\u0004\b'\u0010(R\u0016\u0010,\u001a\u0004\u0018\u00010)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lio/ktor/server/http/content/JarFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Ljava/io/File;", "jarFile", "", "resourcePath", "Lio/ktor/http/ContentType;", "contentType", "<init>", "(Ljava/io/File;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "Ljava/nio/file/Path;", "zipFilePath", "(Ljava/nio/file/Path;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "Lio/ktor/utils/io/ByteReadChannel;", "readFrom", "()Lio/ktor/utils/io/ByteReadChannel;", "Ljava/io/File;", "getJarFile", "()Ljava/io/File;", "Ljava/lang/String;", "getResourcePath", "()Ljava/lang/String;", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "normalized", "Ljava/util/jar/JarEntry;", "jarEntry$delegate", "Lx6/s;", "getJarEntry", "()Ljava/util/jar/JarEntry;", "jarEntry", "Ljava/util/jar/JarFile;", "jar$delegate", "getJar", "()Ljava/util/jar/JarFile;", "jar", "", "isFile$delegate", "isFile", "()Z", "", "getContentLength", "()Ljava/lang/Long;", "contentLength", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class JarFileContent extends OutgoingContent.ReadChannelContent {
    private final ContentType contentType;

    /* JADX INFO: renamed from: isFile$delegate, reason: from kotlin metadata */
    private final s isFile;

    /* JADX INFO: renamed from: jar$delegate, reason: from kotlin metadata */
    private final s jar;

    /* JADX INFO: renamed from: jarEntry$delegate, reason: from kotlin metadata */
    private final s jarEntry;
    private final File jarFile;
    private final String normalized;
    private final String resourcePath;

    /* JADX INFO: renamed from: io.ktor.server.http.content.JarFileContent$isFile$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<Boolean> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            JarEntry jarEntry = JarFileContent.this.getJarEntry();
            boolean z = false;
            if (jarEntry != null && !jarEntry.isDirectory()) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    public JarFileContent(File file, String str, ContentType contentType) {
        this.jarFile = file;
        this.resourcePath = str;
        this.contentType = contentType;
        String strReplace = i.q0(new File(str)).toString().replace(File.separatorChar, '/');
        this.normalized = strReplace;
        this.jarEntry = f.o(3, new JarFileContent$jarEntry$2(this));
        this.jar = f.o(3, new JarFileContent$jar$2(this));
        this.isFile = f.o(3, new AnonymousClass2());
        if (u.P(strReplace, "..", false)) {
            throw new IllegalArgumentException(d.C("Bad resource relative path ", str).toString());
        }
        JarEntry jarEntry = getJarEntry();
        if (jarEntry != null) {
            VersionsKt.setVersions(this, x.H0(LastModifiedJavaTimeKt.LastModifiedVersion(jarEntry.getLastModifiedTime()), VersionsKt.getVersions(this)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JarFile getJar() {
        return (JarFile) this.jar.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JarEntry getJarEntry() {
        return (JarEntry) this.jarEntry.getValue();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        JarEntry jarEntry = getJarEntry();
        if (jarEntry != null) {
            return Long.valueOf(jarEntry.getSize());
        }
        return null;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    public final File getJarFile() {
        return this.jarFile;
    }

    public final String getResourcePath() {
        return this.resourcePath;
    }

    public final boolean isFile() {
        return ((Boolean) this.isFile.getValue()).booleanValue();
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    public ByteReadChannel readFrom() throws IOException {
        ByteReadChannel byteReadChannel$default;
        InputStream inputStream = getJar().getInputStream(getJarEntry());
        if (inputStream == null || (byteReadChannel$default = InputStreamAdaptersKt.toByteReadChannel$default(inputStream, ByteBufferPoolKt.getKtorDefaultPool(), null, null, 6, null)) == null) {
            throw new IOException(c.p(new StringBuilder("Resource "), this.normalized, " not found"));
        }
        return byteReadChannel$default;
    }

    public JarFileContent(Path path, String str, ContentType contentType) {
        this(path.toFile(), str, contentType);
    }
}
