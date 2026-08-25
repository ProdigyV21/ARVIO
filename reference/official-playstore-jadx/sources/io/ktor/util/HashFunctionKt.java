package io.ktor.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0015\u0010\u0007\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0082\u0004¨\u0006\t"}, d2 = {"digest", "", "Lio/ktor/util/HashFunction;", "input", "offset", "", "length", "leftRotate", "bitCount", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HashFunctionKt {
    public static final byte[] digest(HashFunction hashFunction, byte[] bArr, int i10, int i11) {
        hashFunction.update(bArr, i10, i11);
        return hashFunction.digest();
    }

    public static /* synthetic */ byte[] digest$default(HashFunction hashFunction, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length;
        }
        return digest(hashFunction, bArr, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int leftRotate(int i10, int i11) {
        return (i10 >>> (32 - i11)) | (i10 << i11);
    }
}
