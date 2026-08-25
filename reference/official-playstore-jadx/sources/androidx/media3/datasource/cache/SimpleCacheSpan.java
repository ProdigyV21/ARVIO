package androidx.media3.datasource.cache;

import a0.c;
import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
final class SimpleCacheSpan extends CacheSpan {
    private static final Pattern CACHE_FILE_PATTERN_V1 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);
    private static final Pattern CACHE_FILE_PATTERN_V2 = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);
    private static final Pattern CACHE_FILE_PATTERN_V3 = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);
    static final String COMMON_SUFFIX = ".exo";
    private static final String SUFFIX = ".v3.exo";

    private SimpleCacheSpan(String str, long j10, long j11, long j12, File file) {
        super(str, j10, j11, j12, file);
    }

    public static SimpleCacheSpan createCacheEntry(File file, long j10, CachedContentIndex cachedContentIndex) {
        return createCacheEntry(file, j10, C.TIME_UNSET, cachedContentIndex);
    }

    public static SimpleCacheSpan createHole(String str, long j10, long j11) {
        return new SimpleCacheSpan(str, j10, j11, C.TIME_UNSET, null);
    }

    public static SimpleCacheSpan createLookup(String str, long j10) {
        return new SimpleCacheSpan(str, j10, -1L, C.TIME_UNSET, null);
    }

    public static File getCacheFile(File file, int i10, long j10, long j11) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i10);
        sb2.append(".");
        sb2.append(j10);
        sb2.append(".");
        return new File(file, c.j(j11, SUFFIX, sb2));
    }

    private static File upgradeFile(File file, CachedContentIndex cachedContentIndex) {
        String strGroup;
        String name = file.getName();
        Matcher matcher = CACHE_FILE_PATTERN_V2.matcher(name);
        if (matcher.matches()) {
            String strGroup2 = matcher.group(1);
            strGroup2.getClass();
            strGroup = Util.unescapeFileName(strGroup2);
        } else {
            matcher = CACHE_FILE_PATTERN_V1.matcher(name);
            if (matcher.matches()) {
                strGroup = matcher.group(1);
                strGroup.getClass();
            } else {
                strGroup = null;
            }
        }
        if (strGroup == null) {
            return null;
        }
        File parentFile = file.getParentFile();
        parentFile.getClass();
        int iAssignIdForKey = cachedContentIndex.assignIdForKey(strGroup);
        String strGroup3 = matcher.group(2);
        strGroup3.getClass();
        long j10 = Long.parseLong(strGroup3);
        String strGroup4 = matcher.group(3);
        strGroup4.getClass();
        File cacheFile = getCacheFile(parentFile, iAssignIdForKey, j10, Long.parseLong(strGroup4));
        if (file.renameTo(cacheFile)) {
            return cacheFile;
        }
        return null;
    }

    public SimpleCacheSpan copyWithFileAndLastTouchTimestamp(File file, long j10) {
        ac.b.s(this.isCached);
        return new SimpleCacheSpan(this.key, this.position, this.length, j10, file);
    }

    public static SimpleCacheSpan createCacheEntry(File file, long j10, long j11, CachedContentIndex cachedContentIndex) {
        long j12;
        String name = file.getName();
        if (!name.endsWith(SUFFIX)) {
            file = upgradeFile(file, cachedContentIndex);
            if (file == null) {
                return null;
            }
            name = file.getName();
        }
        File file2 = file;
        Matcher matcher = CACHE_FILE_PATTERN_V3.matcher(name);
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        strGroup.getClass();
        String keyForId = cachedContentIndex.getKeyForId(Integer.parseInt(strGroup));
        if (keyForId == null) {
            return null;
        }
        if (j10 == -1) {
            j10 = file2.length();
        }
        long j13 = j10;
        if (j13 == 0) {
            return null;
        }
        String strGroup2 = matcher.group(2);
        strGroup2.getClass();
        long j14 = Long.parseLong(strGroup2);
        if (j11 == C.TIME_UNSET) {
            String strGroup3 = matcher.group(3);
            strGroup3.getClass();
            j12 = Long.parseLong(strGroup3);
        } else {
            j12 = j11;
        }
        return new SimpleCacheSpan(keyForId, j14, j13, j12, file2);
    }
}
