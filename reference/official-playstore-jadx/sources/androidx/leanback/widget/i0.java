package androidx.leanback.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Rect f3093a = new Rect();

    public static int a(View view, h0 h0Var, int i10) {
        View viewFindViewById;
        int height;
        int width;
        int width2;
        int width3;
        x xVar = (x) view.getLayoutParams();
        int i11 = h0Var.f3087a;
        if (i11 == 0 || (viewFindViewById = view.findViewById(i11)) == null) {
            viewFindViewById = view;
        }
        int paddingBottom = h0Var.f3088b;
        Rect rect = f3093a;
        if (i10 != 0) {
            if (h0Var.f3090d) {
                float f10 = h0Var.f3089c;
                if (f10 == 0.0f) {
                    paddingBottom += viewFindViewById.getPaddingTop();
                } else if (f10 == 100.0f) {
                    paddingBottom -= viewFindViewById.getPaddingBottom();
                }
            }
            if (h0Var.f3089c != -1.0f) {
                if (viewFindViewById == view) {
                    xVar.getClass();
                    height = (viewFindViewById.getHeight() - xVar.f3171f) - xVar.f3173h;
                } else {
                    height = viewFindViewById.getHeight();
                }
                paddingBottom += (int) ((height * h0Var.f3089c) / 100.0f);
            }
            if (view == viewFindViewById) {
                return paddingBottom;
            }
            rect.top = paddingBottom;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, rect);
            return rect.top - xVar.f3171f;
        }
        if (view.getLayoutDirection() != 1) {
            if (h0Var.f3090d) {
                float f11 = h0Var.f3089c;
                if (f11 == 0.0f) {
                    paddingBottom += viewFindViewById.getPaddingLeft();
                } else if (f11 == 100.0f) {
                    paddingBottom -= viewFindViewById.getPaddingRight();
                }
            }
            if (h0Var.f3089c != -1.0f) {
                if (viewFindViewById == view) {
                    xVar.getClass();
                    width = (viewFindViewById.getWidth() - xVar.f3170e) - xVar.f3172g;
                } else {
                    width = viewFindViewById.getWidth();
                }
                paddingBottom += (int) ((width * h0Var.f3089c) / 100.0f);
            }
            if (view == viewFindViewById) {
                return paddingBottom;
            }
            rect.left = paddingBottom;
            ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, rect);
            return rect.left - xVar.f3170e;
        }
        if (viewFindViewById == view) {
            xVar.getClass();
            width2 = (viewFindViewById.getWidth() - xVar.f3170e) - xVar.f3172g;
        } else {
            width2 = viewFindViewById.getWidth();
        }
        int paddingLeft = width2 - paddingBottom;
        if (h0Var.f3090d) {
            float f12 = h0Var.f3089c;
            if (f12 == 0.0f) {
                paddingLeft -= viewFindViewById.getPaddingRight();
            } else if (f12 == 100.0f) {
                paddingLeft += viewFindViewById.getPaddingLeft();
            }
        }
        if (h0Var.f3089c != -1.0f) {
            if (viewFindViewById == view) {
                xVar.getClass();
                width3 = (viewFindViewById.getWidth() - xVar.f3170e) - xVar.f3172g;
            } else {
                width3 = viewFindViewById.getWidth();
            }
            paddingLeft -= (int) ((width3 * h0Var.f3089c) / 100.0f);
        }
        if (view == viewFindViewById) {
            return paddingLeft;
        }
        rect.right = paddingLeft;
        ((ViewGroup) view).offsetDescendantRectToMyCoords(viewFindViewById, rect);
        return rect.right + xVar.f3172g;
    }
}
