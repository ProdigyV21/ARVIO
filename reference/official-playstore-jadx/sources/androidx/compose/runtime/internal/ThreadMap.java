package androidx.compose.runtime.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/internal/ThreadMap;", "", ContentDisposition.Parameters.Size, "", UserMetadata.KEYDATA_FILENAME, "", "values", "", "(I[J[Ljava/lang/Object;)V", "[Ljava/lang/Object;", "find", "key", "", "get", "newWith", "value", "trySet", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ThreadMap {
    public static final int $stable = 8;
    private final long[] keys;
    private final int size;
    private final Object[] values;

    public ThreadMap(int i10, long[] jArr, Object[] objArr) {
        this.size = i10;
        this.keys = jArr;
        this.values = objArr;
    }

    private final int find(long key) {
        int i10 = this.size - 1;
        if (i10 != -1) {
            int i11 = 0;
            if (i10 != 0) {
                while (i11 <= i10) {
                    int i12 = (i11 + i10) >>> 1;
                    long j10 = this.keys[i12] - key;
                    if (j10 < 0) {
                        i11 = i12 + 1;
                    } else {
                        if (j10 <= 0) {
                            return i12;
                        }
                        i10 = i12 - 1;
                    }
                }
                return -(i11 + 1);
            }
            long j11 = this.keys[0];
            if (j11 == key) {
                return 0;
            }
            if (j11 > key) {
                return -2;
            }
        }
        return -1;
    }

    public final Object get(long key) {
        int iFind = find(key);
        if (iFind >= 0) {
            return this.values[iFind];
        }
        return null;
    }

    public final ThreadMap newWith(long key, Object value) {
        int i10 = this.size;
        int i11 = 0;
        int i12 = 0;
        for (Object obj : this.values) {
            if (obj != null) {
                i12++;
            }
        }
        int i13 = i12 + 1;
        long[] jArr = new long[i13];
        Object[] objArr = new Object[i13];
        if (i13 > 1) {
            int i14 = 0;
            while (true) {
                if (i11 >= i13 || i14 >= i10) {
                    break;
                }
                long j10 = this.keys[i14];
                Object obj2 = this.values[i14];
                if (j10 > key) {
                    jArr[i11] = key;
                    objArr[i11] = value;
                    i11++;
                    break;
                }
                if (obj2 != null) {
                    jArr[i11] = j10;
                    objArr[i11] = obj2;
                    i11++;
                }
                i14++;
            }
            if (i14 == i10) {
                jArr[i12] = key;
                objArr[i12] = value;
            } else {
                while (i11 < i13) {
                    long j11 = this.keys[i14];
                    Object obj3 = this.values[i14];
                    if (obj3 != null) {
                        jArr[i11] = j11;
                        objArr[i11] = obj3;
                        i11++;
                    }
                    i14++;
                }
            }
        } else {
            jArr[0] = key;
            objArr[0] = value;
        }
        return new ThreadMap(i13, jArr, objArr);
    }

    public final boolean trySet(long key, Object value) {
        int iFind = find(key);
        if (iFind < 0) {
            return false;
        }
        this.values[iFind] = value;
        return true;
    }
}
