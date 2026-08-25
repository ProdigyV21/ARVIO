package xb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;

/* JADX INFO: loaded from: classes5.dex */
public class c0 extends a0 {
    public static q b(Path path) {
        f0 f0VarJ;
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            Path symbolicLink = attributes.isSymbolicLink() ? Files.readSymbolicLink(path) : null;
            boolean zIsRegularFile = attributes.isRegularFile();
            boolean zIsDirectory = attributes.isDirectory();
            if (symbolicLink != null) {
                String str = f0.f22733l;
                f0VarJ = v1.i.j(symbolicLink);
            } else {
                f0VarJ = null;
            }
            Long lValueOf = Long.valueOf(attributes.size());
            FileTime fileTimeCreationTime = attributes.creationTime();
            Long lC = fileTimeCreationTime != null ? c(fileTimeCreationTime) : null;
            FileTime fileTimeLastModifiedTime = attributes.lastModifiedTime();
            Long lC2 = fileTimeLastModifiedTime != null ? c(fileTimeLastModifiedTime) : null;
            FileTime fileTimeLastAccessTime = attributes.lastAccessTime();
            return new q(zIsRegularFile, zIsDirectory, f0VarJ, lValueOf, lC, lC2, fileTimeLastAccessTime != null ? c(fileTimeLastAccessTime) : null);
        } catch (NoSuchFileException | FileSystemException unused) {
            return null;
        }
    }

    public static Long c(FileTime fileTime) {
        long millis = fileTime.toMillis();
        Long lValueOf = Long.valueOf(millis);
        if (millis != 0) {
            return lValueOf;
        }
        return null;
    }

    @Override // xb.a0, xb.s
    public void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        try {
            Files.move(f0Var.j(), f0Var2.j(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        } catch (NoSuchFileException e5) {
            throw new FileNotFoundException(e5.getMessage());
        }
    }

    @Override // xb.a0, xb.s
    public void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        Files.createSymbolicLink(f0Var.j(), f0Var2.j(), new FileAttribute[0]);
    }

    @Override // xb.a0, xb.s
    public q metadataOrNull(f0 f0Var) {
        return b(f0Var.j());
    }

    @Override // xb.a0
    public String toString() {
        return "NioSystemFileSystem";
    }
}
