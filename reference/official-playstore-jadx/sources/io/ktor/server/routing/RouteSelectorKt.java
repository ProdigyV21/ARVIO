package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001aF\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\f"}, d2 = {"evaluatePathSegmentParameter", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "segments", "", "", "segmentIndex", "", ContentDisposition.Parameters.Name, "prefix", "suffix", "isOptional", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RouteSelectorKt {
    public static final RouteSelectorEvaluation evaluatePathSegmentParameter(List<String> list, int i10, String str, String str2, String str3, boolean z) {
        String strV;
        if (i10 >= list.size()) {
            return evaluatePathSegmentParameter$failedEvaluation(z, null);
        }
        String str4 = list.get(i10);
        if (str4.length() == 0) {
            return evaluatePathSegmentParameter$failedEvaluation(z, str4);
        }
        if (str2 == null) {
            strV = str4;
        } else {
            if (!u.P(str4, str2, false)) {
                return evaluatePathSegmentParameter$failedEvaluation(z, str4);
            }
            strV = o.V(str2.length(), str4);
        }
        if (str3 != null) {
            if (!u.K(strV, str3, false)) {
                return evaluatePathSegmentParameter$failedEvaluation(z, str4);
            }
            strV = o.W(str3.length(), strV);
        }
        return new RouteSelectorEvaluation.Success(((str2 == null || str2.length() == 0) && (str3 == null || str3.length() == 0)) ? 0.8d : 0.9d, ParametersKt.parametersOf(str, strV), 1);
    }

    public static /* synthetic */ RouteSelectorEvaluation evaluatePathSegmentParameter$default(List list, int i10, String str, String str2, String str3, boolean z, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            str2 = null;
        }
        if ((i11 & 16) != 0) {
            str3 = null;
        }
        return evaluatePathSegmentParameter(list, i10, str, str2, str3, z);
    }

    private static final RouteSelectorEvaluation evaluatePathSegmentParameter$failedEvaluation(boolean z, String str) {
        return !z ? RouteSelectorEvaluation.INSTANCE.getFailedPath() : str == null ? RouteSelectorEvaluation.INSTANCE.getMissing() : str.length() == 0 ? new RouteSelectorEvaluation.Success(0.2d, null, 1, 2, null) : RouteSelectorEvaluation.INSTANCE.getMissing();
    }
}
