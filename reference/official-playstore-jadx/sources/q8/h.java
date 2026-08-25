package q8;

import androidx.emoji2.text.e0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import v9.w;
import w8.q;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements i, j, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h f21348c = new h();

    public static /* synthetic */ void c(int i10) {
        Object[] objArr = new Object[3];
        switch (i10) {
            case 1:
                objArr[0] = "owner";
                break;
            case 2:
                objArr[0] = "returnType";
                break;
            case 3:
                objArr[0] = "valueParameters";
                break;
            case 4:
                objArr[0] = "typeParameters";
                break;
            case 5:
                objArr[0] = "descriptor";
                break;
            case 6:
                objArr[0] = "signatureErrors";
                break;
            default:
                objArr[0] = "method";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$1";
        if (i10 == 5 || i10 == 6) {
            objArr[2] = "reportSignatureErrors";
        } else {
            objArr[2] = "resolvePropagatedSignature";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // q8.m
    public e0 a(q qVar, g8.f fVar, w wVar, List list, ArrayList arrayList) {
        if (qVar == null) {
            c(0);
            throw null;
        }
        if (fVar != null) {
            List list2 = Collections.EMPTY_LIST;
            return new e0(wVar, list, arrayList);
        }
        c(1);
        throw null;
    }

    @Override // q8.m
    public void b(r8.g gVar, List list) {
        throw new UnsupportedOperationException("Should not be called");
    }
}
