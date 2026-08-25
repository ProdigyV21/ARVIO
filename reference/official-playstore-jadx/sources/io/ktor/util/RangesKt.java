package io.ktor.util;

import kotlin.Metadata;
import x6.e;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u001a\u001c\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0086\u0002¢\u0006\u0004\b\u0003\u0010\u0004\"\u001e\u0010\n\u001a\u00020\u0005*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lx7/l;", "other", "", "contains", "(Lx7/l;Lx7/l;)Z", "", "getLength", "(Lx7/l;)J", "getLength$annotations", "(Lx7/l;)V", "length", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RangesKt {
    public static final boolean contains(l lVar, l lVar2) {
        return lVar2.f22627i >= lVar.f22627i && lVar2.f22628l <= lVar.f22628l;
    }

    public static final long getLength(l lVar) {
        long j10 = (lVar.f22628l - lVar.f22627i) + 1;
        if (j10 < 0) {
            return 0L;
        }
        return j10;
    }

    @e
    public static /* synthetic */ void getLength$annotations(l lVar) {
    }
}
