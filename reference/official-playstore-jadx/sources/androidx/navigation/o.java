package androidx.navigation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import androidx.compose.runtime.State;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4735i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4736l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4737m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4738n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4739o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4740p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(io.sentry.android.replay.util.e eVar, Bitmap bitmap, Matrix matrix, ArrayList arrayList, Canvas canvas) {
        super(1);
        this.f4735i = 2;
        this.f4736l = eVar;
        this.f4738n = bitmap;
        this.f4739o = matrix;
        this.f4737m = arrayList;
        this.f4740p = canvas;
    }

    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object, x6.s] */
    @Override // r7.l
    public final Object invoke(Object obj) {
        List listSubList;
        x6.x xVar;
        Integer numI;
        List listSingletonList;
        switch (this.f4735i) {
            case 0:
                i iVar = (i) obj;
                kotlin.jvm.internal.i0 i0Var = (kotlin.jvm.internal.i0) this.f4738n;
                ((kotlin.jvm.internal.f0) this.f4736l).f19738i = true;
                ArrayList arrayList = (ArrayList) this.f4737m;
                int iIndexOf = arrayList.indexOf(iVar);
                if (iIndexOf != -1) {
                    int i10 = iIndexOf + 1;
                    listSubList = arrayList.subList(i0Var.f19744i, i10);
                    i0Var.f19744i = i10;
                } else {
                    listSubList = kotlin.collections.z.f19728i;
                }
                ((s) this.f4739o).a(iVar.f4681l, (Bundle) this.f4740p, iVar, listSubList);
                return x6.t0.f22605a;
            case 1:
                e.a aVar = (e.a) this.f4736l;
                aVar.f14932a = ((androidx.activity.result.k) this.f4737m).d((String) this.f4738n, (g.a) this.f4739o, new a3.b((State) this.f4740p, 22));
                return new e.b(aVar, 0);
            default:
                io.sentry.android.replay.viewhierarchy.g gVar = (io.sentry.android.replay.viewhierarchy.g) obj;
                io.sentry.android.replay.util.e eVar = (io.sentry.android.replay.util.e) this.f4736l;
                ?? r1 = eVar.f17049n;
                ?? r22 = eVar.f17047l;
                Rect rect = gVar.f17082e;
                if (gVar.f17081d && gVar.f17078a > 0 && gVar.f17079b > 0) {
                    if (rect == null) {
                        return Boolean.FALSE;
                    }
                    if (gVar instanceof io.sentry.android.replay.viewhierarchy.e) {
                        List listSingletonList2 = Collections.singletonList(rect);
                        Bitmap bitmap = (Bitmap) this.f4738n;
                        Matrix matrix = (Matrix) this.f4739o;
                        if (!bitmap.isRecycled() && !((Bitmap) r22.getValue()).isRecycled()) {
                            Rect rect2 = new Rect(rect);
                            RectF rectF = new RectF(rect2);
                            if (matrix != null) {
                                matrix.mapRect(rectF);
                            }
                            rectF.round(rect2);
                            ((Canvas) eVar.f17048m.getValue()).drawBitmap(bitmap, rect2, new Rect(0, 0, 1, 1), (Paint) null);
                            iIntValue = ((Bitmap) r22.getValue()).getPixel(0, 0);
                        }
                        xVar = new x6.x(listSingletonList2, Integer.valueOf(iIntValue));
                    } else if (gVar instanceof io.sentry.android.replay.viewhierarchy.f) {
                        io.sentry.android.replay.viewhierarchy.f fVar = (io.sentry.android.replay.viewhierarchy.f) gVar;
                        io.sentry.android.replay.util.k kVar = fVar.f17074g;
                        iIntValue = ((kVar == null || (numI = kVar.i()) == null) && (numI = fVar.f17075h) == null) ? -16777216 : numI.intValue();
                        int i11 = fVar.f17076i;
                        int i12 = fVar.f17077j;
                        if (kVar == null) {
                            listSingletonList = Collections.singletonList(rect);
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            int lineCount = kVar.getLineCount();
                            for (int i13 = 0; i13 < lineCount; i13++) {
                                int lineLeft = (int) kVar.getLineLeft(i13);
                                int lineRight = (int) kVar.getLineRight(i13);
                                int lineTop = kVar.getLineTop(i13);
                                int lineBottom = kVar.getLineBottom(i13);
                                Rect rect3 = new Rect();
                                rect3.left = rect.left + i11 + lineLeft;
                                rect3.right = rect.left + i11 + lineRight;
                                int i14 = rect.top + i12 + lineTop;
                                rect3.top = i14;
                                rect3.bottom = (lineBottom - lineTop) + i14;
                                arrayList2.add(rect3);
                            }
                            listSingletonList = arrayList2;
                        }
                        xVar = new x6.x(listSingletonList, Integer.valueOf(iIntValue));
                    } else {
                        xVar = new x6.x(Collections.singletonList(rect), -16777216);
                    }
                    List list = (List) xVar.f22608i;
                    ((Paint) r1.getValue()).setColor(((Number) xVar.f22609l).intValue());
                    Canvas canvas = (Canvas) this.f4740p;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        canvas.drawRoundRect(new RectF((Rect) it.next()), 10.0f, 10.0f, (Paint) r1.getValue());
                    }
                    ((ArrayList) this.f4737m).addAll(list);
                }
                return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o(Object obj, Object obj2, Serializable serializable, Object obj3, Object obj4, int i10) {
        super(1);
        this.f4735i = i10;
        this.f4736l = obj;
        this.f4737m = obj2;
        this.f4738n = serializable;
        this.f4739o = obj3;
        this.f4740p = obj4;
    }
}
