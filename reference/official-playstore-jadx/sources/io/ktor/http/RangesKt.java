package io.ktor.http;

import com.google.common.util.concurrent.r0;
import io.ktor.http.ContentRange;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.text.o;
import kotlin.text.u;
import qb.d;
import t7.a;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a'\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0005*\b\u0012\u0004\u0012\u00020\t0\u0005H\u0000¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"", "rangeSpec", "Lio/ktor/http/RangesSpecifier;", "parseRangesSpecifier", "(Ljava/lang/String;)Lio/ktor/http/RangesSpecifier;", "", "Lio/ktor/http/ContentRange;", "", "contentLength", "Lx7/l;", "toLongRanges", "(Ljava/util/List;J)Ljava/util/List;", "mergeRangesKeepOrder", "(Ljava/util/List;)Ljava/util/List;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RangesKt {
    public static final List<l> mergeRangesKeepOrder(List<l> list) {
        List<l> listW0 = x.W0(list, new Comparator() { // from class: io.ktor.http.RangesKt$mergeRangesKeepOrder$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Long.valueOf(((l) t2).f22627i), Long.valueOf(((l) t10).f22627i));
            }
        });
        ArrayList<l> arrayList = new ArrayList(list.size());
        for (l lVar : listW0) {
            if (arrayList.isEmpty()) {
                arrayList.add(lVar);
            } else if (((l) x.w0(arrayList)).f22628l < lVar.f22627i - 1) {
                arrayList.add(lVar);
            } else {
                l lVar2 = (l) x.w0(arrayList);
                arrayList.set(a.w(arrayList), new l(lVar2.f22627i, Math.max(lVar2.f22628l, lVar.f22628l)));
            }
        }
        l[] lVarArr = new l[list.size()];
        for (l lVar3 : arrayList) {
            int size = list.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    break;
                }
                if (io.ktor.util.RangesKt.contains(lVar3, list.get(i10))) {
                    lVarArr[i10] = lVar3;
                    break;
                }
                i10++;
            }
        }
        return r.X(lVarArr);
    }

    public static final RangesSpecifier parseRangesSpecifier(String str) {
        ContentRange bounded;
        try {
            int iF0 = o.f0(str, "=", 0, false, 6);
            if (iF0 != -1) {
                String strSubstring = str.substring(0, iF0);
                List<String> listX0 = o.x0(str.substring(iF0 + 1), new char[]{','}, 6);
                ArrayList arrayList = new ArrayList(s.U(listX0, 10));
                for (String str2 : listX0) {
                    if (u.P(str2, "-", false)) {
                        bounded = new ContentRange.Suffix(Long.parseLong(o.r0(str2, "-")));
                    } else {
                        int iF02 = o.f0(str2, "-", 0, false, 6);
                        x6.x xVar = iF02 == -1 ? new x6.x("", "") : new x6.x(str2.substring(0, iF02), str2.substring(iF02 + 1));
                        String str3 = (String) xVar.f22608i;
                        String str4 = (String) xVar.f22609l;
                        bounded = str4.length() > 0 ? new ContentRange.Bounded(Long.parseLong(str3), Long.parseLong(str4)) : new ContentRange.TailFrom(Long.parseLong(str3));
                    }
                    arrayList.add(bounded);
                }
                if (!arrayList.isEmpty() && strSubstring.length() != 0) {
                    RangesSpecifier rangesSpecifier = new RangesSpecifier(strSubstring, arrayList);
                    if (RangesSpecifier.isValid$default(rangesSpecifier, null, 1, null)) {
                        return rangesSpecifier;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static final List<l> toLongRanges(List<? extends ContentRange> list, long j10) {
        l lVarY;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        for (ContentRange contentRange : list) {
            if (contentRange instanceof ContentRange.Bounded) {
                ContentRange.Bounded bounded = (ContentRange.Bounded) contentRange;
                long from = bounded.getFrom();
                long to = bounded.getTo();
                long j11 = j10 - 1;
                if (to > j11) {
                    to = j11;
                }
                lVarY = new l(from, to);
            } else if (contentRange instanceof ContentRange.TailFrom) {
                lVarY = d.Y(((ContentRange.TailFrom) contentRange).getFrom(), j10);
            } else {
                if (!(contentRange instanceof ContentRange.Suffix)) {
                    throw new NoWhenBranchMatchedException();
                }
                long lastCount = j10 - ((ContentRange.Suffix) contentRange).getLastCount();
                if (lastCount < 0) {
                    lastCount = 0;
                }
                lVarY = d.Y(lastCount, j10);
            }
            arrayList.add(lVarY);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!((l) obj).isEmpty()) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }
}
