package android.view;

import android.view.Dimension;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewSizeResolver;
import android.view.ViewTreeObserver;
import d7.d;
import io.ktor.http.ContentDisposition;
import ka.l;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003J\u0013\u0010\u0005\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J)\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u000fJ\u001b\u0010\u0017\u001a\u00020\u0016*\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001b\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eø\u0001\u0001\u0082\u0002\n\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006 À\u0006\u0003"}, d2 = {"Lcoil/size/ViewSizeResolver;", "Landroid/view/View;", "T", "Lcoil/size/SizeResolver;", "Lcoil/size/Size;", ContentDisposition.Parameters.Size, "(Ld7/d;)Ljava/lang/Object;", "", "paramSize", "viewSize", "paddingSize", "Lcoil/size/Dimension;", "getDimension", "(III)Lcoil/size/Dimension;", "getHeight", "()Lcoil/size/Dimension;", "getSize", "()Lcoil/size/Size;", "getWidth", "Landroid/view/ViewTreeObserver;", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "victim", "Lx6/t0;", "removePreDrawListenerSafe", "(Landroid/view/ViewTreeObserver;Landroid/view/ViewTreeObserver$OnPreDrawListener;)V", "getView", "()Landroid/view/View;", "view", "", "getSubtractPadding", "()Z", "subtractPadding", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface ViewSizeResolver<T extends View> extends SizeResolver {

    /* JADX INFO: renamed from: coil.size.ViewSizeResolver$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static boolean a(ViewSizeResolver viewSizeResolver) {
            return true;
        }

        public static Object b(ViewSizeResolver viewSizeResolver, d dVar) {
            return l(viewSizeResolver, dVar);
        }

        public static Dimension c(ViewSizeResolver viewSizeResolver, int i10, int i11, int i12) {
            if (i10 == -2) {
                return Dimension.Undefined.INSTANCE;
            }
            int i13 = i10 - i12;
            if (i13 > 0) {
                return Dimensions.Dimension(i13);
            }
            int i14 = i11 - i12;
            if (i14 > 0) {
                return Dimensions.Dimension(i14);
            }
            return null;
        }

        public static Dimension d(ViewSizeResolver viewSizeResolver) {
            ViewGroup.LayoutParams layoutParams = viewSizeResolver.getView().getLayoutParams();
            return c(viewSizeResolver, layoutParams != null ? layoutParams.height : -1, viewSizeResolver.getView().getHeight(), viewSizeResolver.getSubtractPadding() ? viewSizeResolver.getView().getPaddingBottom() + viewSizeResolver.getView().getPaddingTop() : 0);
        }

        public static Size e(ViewSizeResolver viewSizeResolver) {
            Dimension dimensionD;
            Dimension dimensionF = f(viewSizeResolver);
            if (dimensionF == null || (dimensionD = d(viewSizeResolver)) == null) {
                return null;
            }
            return new Size(dimensionF, dimensionD);
        }

        public static Dimension f(ViewSizeResolver viewSizeResolver) {
            ViewGroup.LayoutParams layoutParams = viewSizeResolver.getView().getLayoutParams();
            return c(viewSizeResolver, layoutParams != null ? layoutParams.width : -1, viewSizeResolver.getView().getWidth(), viewSizeResolver.getSubtractPadding() ? viewSizeResolver.getView().getPaddingRight() + viewSizeResolver.getView().getPaddingLeft() : 0);
        }

        public static void g(ViewSizeResolver viewSizeResolver, ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnPreDrawListener onPreDrawListener) {
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            } else {
                viewSizeResolver.getView().getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.view.ViewTreeObserver$OnPreDrawListener, coil.size.ViewSizeResolver$size$3$preDrawListener$1] */
        public static /* synthetic */ Object l(final ViewSizeResolver viewSizeResolver, d dVar) {
            Size sizeE = e(viewSizeResolver);
            if (sizeE != null) {
                return sizeE;
            }
            final l lVar = new l(1, a.A(dVar));
            lVar.s();
            final ViewTreeObserver viewTreeObserver = viewSizeResolver.getView().getViewTreeObserver();
            ?? r1 = new ViewTreeObserver.OnPreDrawListener() { // from class: coil.size.ViewSizeResolver$size$3$preDrawListener$1
                private boolean isResumed;

                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    Size sizeE2 = ViewSizeResolver.CC.e(viewSizeResolver);
                    if (sizeE2 != null) {
                        ViewSizeResolver.CC.g(viewSizeResolver, viewTreeObserver, this);
                        if (!this.isResumed) {
                            this.isResumed = true;
                            lVar.resumeWith(sizeE2);
                        }
                    }
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(r1);
            lVar.c(new View(viewSizeResolver, viewTreeObserver, r1));
            return lVar.r();
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T extends View> boolean getSubtractPadding(ViewSizeResolver<T> viewSizeResolver) {
            return CC.a(viewSizeResolver);
        }

        @Deprecated
        public static <T extends View> Object size(ViewSizeResolver<T> viewSizeResolver, d<? super Size> dVar) {
            return CC.b(viewSizeResolver, dVar);
        }
    }

    boolean getSubtractPadding();

    T getView();

    @Override // android.view.SizeResolver
    Object size(d<? super Size> dVar);
}
