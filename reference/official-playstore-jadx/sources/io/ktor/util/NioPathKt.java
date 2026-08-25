package io.ktor.util;

import com.google.firebase.installations.d;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"extension", "", "Ljava/nio/file/Path;", "getExtension", "(Ljava/nio/file/Path;)Ljava/lang/String;", "combineSafe", "Ljava/io/File;", "relativePath", "dropLeadingTopDirs", "normalizeAndRelativize", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NioPathKt {
    public static final File combineSafe(Path path, Path path2) {
        Path pathNormalizeAndRelativize = normalizeAndRelativize(path2);
        if (pathNormalizeAndRelativize.startsWith("..")) {
            throw new InvalidPathException(path2.toString(), "Relative path " + path2 + " beginning with .. is invalid");
        }
        if (!pathNormalizeAndRelativize.isAbsolute()) {
            return path.resolve(pathNormalizeAndRelativize).toFile();
        }
        throw new IllegalStateException(("Bad relative path " + path2).toString());
    }

    private static final Path dropLeadingTopDirs(Path path) {
        Iterator it = path.iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            Object next = it.next();
            if (i10 < 0) {
                a.Q();
                throw null;
            }
            if (!p.a(d.e(next).toString(), "..")) {
                break;
            }
            i10++;
        }
        return i10 == 0 ? path : path.subpath(i10, path.getNameCount());
    }

    public static final String getExtension(Path path) {
        return o.F0(path.getFileName().toString(), ".");
    }

    public static final Path normalizeAndRelativize(Path path) {
        Path pathRelativize;
        Path pathNormalize;
        Path pathDropLeadingTopDirs;
        Path root = path.getRoot();
        return (root == null || (pathRelativize = root.relativize(path)) == null || (pathNormalize = pathRelativize.normalize()) == null || (pathDropLeadingTopDirs = dropLeadingTopDirs(pathNormalize)) == null) ? dropLeadingTopDirs(path.normalize()) : pathDropLeadingTopDirs;
    }

    public static final File combineSafe(File file, Path path) {
        Path pathNormalizeAndRelativize = normalizeAndRelativize(path);
        if (pathNormalizeAndRelativize.startsWith("..")) {
            throw new InvalidPathException(path.toString(), "Relative path " + path + " beginning with .. is invalid");
        }
        if (!pathNormalizeAndRelativize.isAbsolute()) {
            return new File(file, pathNormalizeAndRelativize.toString());
        }
        throw new IllegalStateException(("Bad relative path " + path).toString());
    }
}
