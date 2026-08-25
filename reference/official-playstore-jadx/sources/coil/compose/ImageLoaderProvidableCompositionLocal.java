package coil.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import coil.Coil;
import coil.ImageLoader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import q7.g;
import r7.a;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u001e\b\u0000\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0087\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0007\u001a\u00020\u00048GX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcoil/compose/ImageLoaderProvidableCompositionLocal;", "", "delegate", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcoil/ImageLoader;", "constructor-impl", "(Landroidx/compose/runtime/ProvidableCompositionLocal;)Landroidx/compose/runtime/ProvidableCompositionLocal;", "current", "getCurrent$annotations", "()V", "getCurrent", "(Landroidx/compose/runtime/ProvidableCompositionLocal;Landroidx/compose/runtime/Composer;I)Lcoil/ImageLoader;", "equals", "", "other", "equals-impl", "(Landroidx/compose/runtime/ProvidableCompositionLocal;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Landroidx/compose/runtime/ProvidableCompositionLocal;)I", "provides", "Landroidx/compose/runtime/ProvidedValue;", "value", "provides-impl", "(Landroidx/compose/runtime/ProvidableCompositionLocal;Lcoil/ImageLoader;)Landroidx/compose/runtime/ProvidedValue;", "toString", "", "toString-impl", "(Landroidx/compose/runtime/ProvidableCompositionLocal;)Ljava/lang/String;", "coil-compose-singleton_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@g
@e
public final class ImageLoaderProvidableCompositionLocal {
    private final ProvidableCompositionLocal<ImageLoader> delegate;

    /* JADX INFO: renamed from: coil.compose.ImageLoaderProvidableCompositionLocal$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/ImageLoader;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<ImageLoader> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final ImageLoader invoke() {
            return null;
        }
    }

    private /* synthetic */ ImageLoaderProvidableCompositionLocal(ProvidableCompositionLocal providableCompositionLocal) {
        this.delegate = providableCompositionLocal;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ImageLoaderProvidableCompositionLocal m6044boximpl(ProvidableCompositionLocal providableCompositionLocal) {
        return new ImageLoaderProvidableCompositionLocal(providableCompositionLocal);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static ProvidableCompositionLocal<ImageLoader> m6045constructorimpl(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal) {
        return providableCompositionLocal;
    }

    /* JADX INFO: renamed from: constructor-impl$default, reason: not valid java name */
    public static /* synthetic */ ProvidableCompositionLocal m6046constructorimpl$default(ProvidableCompositionLocal providableCompositionLocal, int i10, h hVar) {
        if ((i10 & 1) != 0) {
            providableCompositionLocal = CompositionLocalKt.staticCompositionLocalOf(AnonymousClass1.INSTANCE);
        }
        return m6045constructorimpl(providableCompositionLocal);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6047equalsimpl(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal, Object obj) {
        return (obj instanceof ImageLoaderProvidableCompositionLocal) && p.a(providableCompositionLocal, ((ImageLoaderProvidableCompositionLocal) obj).getDelegate());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6048equalsimpl0(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal, ProvidableCompositionLocal<ImageLoader> providableCompositionLocal2) {
        return p.a(providableCompositionLocal, providableCompositionLocal2);
    }

    public static final ImageLoader getCurrent(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-617597678, i10, -1, "coil.compose.ImageLoaderProvidableCompositionLocal.<get-current> (LocalImageLoader.kt:49)");
        }
        ImageLoader imageLoader = (ImageLoader) composer.consume(providableCompositionLocal);
        if (imageLoader == null) {
            imageLoader = Coil.imageLoader((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext()));
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imageLoader;
    }

    @e
    public static /* synthetic */ void getCurrent$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6049hashCodeimpl(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal) {
        return providableCompositionLocal.hashCode();
    }

    @e
    /* JADX INFO: renamed from: provides-impl, reason: not valid java name */
    public static final ProvidedValue<ImageLoader> m6050providesimpl(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal, ImageLoader imageLoader) {
        return providableCompositionLocal.provides(imageLoader);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6051toStringimpl(ProvidableCompositionLocal<ImageLoader> providableCompositionLocal) {
        return "ImageLoaderProvidableCompositionLocal(delegate=" + providableCompositionLocal + ')';
    }

    public boolean equals(Object obj) {
        return m6047equalsimpl(this.delegate, obj);
    }

    public int hashCode() {
        return m6049hashCodeimpl(this.delegate);
    }

    public String toString() {
        return m6051toStringimpl(this.delegate);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ ProvidableCompositionLocal getDelegate() {
        return this.delegate;
    }
}
