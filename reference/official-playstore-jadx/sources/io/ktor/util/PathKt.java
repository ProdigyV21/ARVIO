package io.ktor.util;

import a.a;
import java.io.File;
import kotlin.Metadata;
import kotlin.text.o;
import l7.i;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0007\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002\u001a\f\u0010\u000b\u001a\u00020\t*\u00020\nH\u0002\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0001\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u000e"}, d2 = {"combineSafe", "Ljava/io/File;", "dir", "relativePath", "dropLeadingTopDirs", "", "path", "", "isPathSeparator", "", "", "isPathSeparatorOrDot", "normalizeAndRelativize", "notRooted", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PathKt {
    public static final File combineSafe(File file, String str) {
        return combineSafe(file, new File(str));
    }

    public static final int dropLeadingTopDirs(String str) {
        int length = str.length() - 1;
        int i10 = 0;
        while (i10 <= length) {
            char cCharAt = str.charAt(i10);
            if (!isPathSeparator(cCharAt)) {
                if (cCharAt != '.') {
                    break;
                }
                if (i10 != length) {
                    char cCharAt2 = str.charAt(i10 + 1);
                    if (!isPathSeparator(cCharAt2)) {
                        if (cCharAt2 == '.') {
                            int i11 = i10 + 2;
                            if (i11 != str.length()) {
                                if (!isPathSeparator(str.charAt(i11))) {
                                    break;
                                }
                                i10 += 3;
                            } else {
                                i10 = i11;
                            }
                        } else {
                            break;
                        }
                    } else {
                        i10 += 2;
                    }
                } else {
                    return i10 + 1;
                }
            } else {
                i10++;
            }
        }
        return i10;
    }

    private static final boolean isPathSeparator(char c10) {
        return c10 == '\\' || c10 == '/';
    }

    private static final boolean isPathSeparatorOrDot(char c10) {
        return c10 == '.' || isPathSeparator(c10);
    }

    public static final File normalizeAndRelativize(File file) {
        return dropLeadingTopDirs(notRooted(i.q0(file)));
    }

    private static final File notRooted(File file) {
        String strSubstring;
        if (a.S(file.getPath()) <= 0) {
            return file;
        }
        File file2 = file;
        while (true) {
            File parentFile = file2.getParentFile();
            if (parentFile == null) {
                break;
            }
            file2 = parentFile;
        }
        String strV = o.V(file2.getName().length(), file.getPath());
        int length = strV.length();
        int i10 = 0;
        while (true) {
            if (i10 < length) {
                char cCharAt = strV.charAt(i10);
                if (cCharAt != '\\' && cCharAt != '/') {
                    strSubstring = strV.substring(i10);
                    break;
                }
                i10++;
            } else {
                strSubstring = "";
                break;
            }
        }
        return new File(strSubstring);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, java.util.List] */
    private static final File combineSafe(File file, File file2) {
        File fileNormalizeAndRelativize = normalizeAndRelativize(file2);
        File file3 = new File("..");
        l7.a aVarX = a.X(fileNormalizeAndRelativize);
        ?? r32 = aVarX.f20044b;
        l7.a aVarX2 = a.X(file3);
        ?? r4 = aVarX2.f20044b;
        boolean zEquals = aVarX.f20043a.equals(aVarX2.f20043a);
        boolean zEquals2 = false;
        if (zEquals && r32.size() >= r4.size()) {
            zEquals2 = r32.subList(0, r4.size()).equals(r4);
        }
        if (zEquals2) {
            throw new IllegalArgumentException("Bad relative path " + file2);
        }
        if (!fileNormalizeAndRelativize.isAbsolute()) {
            return new File(file, fileNormalizeAndRelativize.getPath());
        }
        throw new IllegalStateException(("Bad relative path " + file2).toString());
    }

    private static final File dropLeadingTopDirs(File file) {
        String path = file.getPath();
        if (path == null) {
            path = "";
        }
        int iDropLeadingTopDirs = dropLeadingTopDirs(path);
        return iDropLeadingTopDirs == 0 ? file : iDropLeadingTopDirs >= file.getPath().length() ? new File(".") : new File(file.getPath().substring(iDropLeadingTopDirs));
    }
}
