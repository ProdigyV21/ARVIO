package androidx.core.content;

import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements FileProvider.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f2083b = new HashMap();

    public j(String str) {
        this.f2082a = str;
    }

    public static boolean c(String str, String str2) {
        String strA = FileProvider.a(str);
        String strA2 = FileProvider.a(str2);
        if (strA.equals(strA2)) {
            return true;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(strA2);
        sb2.append('/');
        return strA.startsWith(sb2.toString());
    }

    @Override // androidx.core.content.FileProvider.a
    public final Uri a(File file) {
        try {
            String canonicalPath = file.getCanonicalPath();
            Map.Entry entry = null;
            for (Map.Entry entry2 : this.f2083b.entrySet()) {
                String path = ((File) entry2.getValue()).getPath();
                if (c(canonicalPath, path) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                    entry = entry2;
                }
            }
            if (entry == null) {
                throw new IllegalArgumentException(androidx.compose.material3.d.C("Failed to find configured root that contains ", canonicalPath));
            }
            String path2 = ((File) entry.getValue()).getPath();
            return new Uri.Builder().scheme("content").authority(this.f2082a).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(path2.endsWith(DomExceptionUtils.SEPARATOR) ? canonicalPath.substring(path2.length()) : canonicalPath.substring(path2.length() + 1), DomExceptionUtils.SEPARATOR)).build();
        } catch (IOException unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
        }
    }

    @Override // androidx.core.content.FileProvider.a
    public final File b(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int iIndexOf = encodedPath.indexOf(47, 1);
        String strDecode = Uri.decode(encodedPath.substring(1, iIndexOf));
        String strDecode2 = Uri.decode(encodedPath.substring(iIndexOf + 1));
        File file = (File) this.f2083b.get(strDecode);
        if (file == null) {
            throw new IllegalArgumentException("Unable to find configured root for " + uri);
        }
        File file2 = new File(file, strDecode2);
        try {
            File canonicalFile = file2.getCanonicalFile();
            if (c(canonicalFile.getPath(), file.getPath())) {
                return canonicalFile;
            }
            throw new SecurityException("Resolved path jumped beyond configured root");
        } catch (IOException unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
        }
    }
}
