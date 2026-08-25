package com.arflix.tv.ui.screens.details;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsTvRows$3$1", f = "DetailsScreen.kt", l = {2350, 2351, 2352, 2353, 2364, 2365, 2371, 2372, 2373, 2374}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsTvRows$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ int $castIdx;
    final /* synthetic */ int $collectionIdx;
    final /* synthetic */ boolean $contentHasFocus;
    final /* synthetic */ androidx.tv.foundation.lazy.list.m0 $contentScrollState;
    final /* synthetic */ Density $density;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $detailsStackOffsetPx;
    final /* synthetic */ int $episodesIdx;
    final /* synthetic */ FocusSection $focusSectionForUi;
    final /* synthetic */ FocusSection $focusedSection;
    final /* synthetic */ int $ratingsIdx;
    final /* synthetic */ int $reviewsIdx;
    final /* synthetic */ int $seasonsIdx;
    final /* synthetic */ int $similarIdx;
    float F$0;
    float F$1;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int label;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusSection.values().length];
            try {
                iArr[FocusSection.BUTTONS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusSection.EPISODES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusSection.SEASONS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusSection.RATINGS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FocusSection.CAST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FocusSection.REVIEWS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FocusSection.COLLECTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[FocusSection.SIMILAR.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailsScreenKt$DetailsTvRows$3$1(boolean z, FocusSection focusSection, int i10, int i11, int i12, int i13, int i14, int i15, int i16, androidx.tv.foundation.lazy.list.m0 m0Var, FocusSection focusSection2, Density density, Animatable<Float, AnimationVector1D> animatable, d7.d<? super DetailsScreenKt$DetailsTvRows$3$1> dVar) {
        super(2, dVar);
        this.$contentHasFocus = z;
        this.$focusedSection = focusSection;
        this.$ratingsIdx = i10;
        this.$castIdx = i11;
        this.$reviewsIdx = i12;
        this.$collectionIdx = i13;
        this.$similarIdx = i14;
        this.$seasonsIdx = i15;
        this.$episodesIdx = i16;
        this.$contentScrollState = m0Var;
        this.$focusSectionForUi = focusSection2;
        this.$density = density;
        this.$detailsStackOffsetPx = animatable;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsTvRows$3$1(this.$contentHasFocus, this.$focusedSection, this.$ratingsIdx, this.$castIdx, this.$reviewsIdx, this.$collectionIdx, this.$similarIdx, this.$seasonsIdx, this.$episodesIdx, this.$contentScrollState, this.$focusSectionForUi, this.$density, this.$detailsStackOffsetPx, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02ee A[PHI: r0 r1 r3 r12 r13 r14 r16
      0x02ee: PHI (r0v33 float) = (r0v31 float), (r0v37 float) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r1v13 int) = (r1v11 int), (r1v15 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r3v12 int) = (r3v11 int), (r3v14 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r12v11 int) = (r12v9 int), (r12v12 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r13v9 int) = (r13v7 int), (r13v10 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r14v7 int) = (r14v5 int), (r14v8 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]
      0x02ee: PHI (r16v5 int) = (r16v3 int), (r16v6 int) binds: [B:98:0x02eb, B:13:0x007b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x031f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a1 A[PHI: r0 r1 r12 r13 r14 r15 r16 r17
      0x00a1: PHI (r0v31 float) = (r0v29 float), (r0v32 float) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r1v11 int) = (r1v9 int), (r1v12 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r12v8 int) = (r12v6 int), (r12v10 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r13v6 int) = (r13v4 int), (r13v8 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r14v4 int) = (r14v2 int), (r14v6 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r15v5 int) = (r15v3 int), (r15v6 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r16v3 int) = (r16v1 int), (r16v4 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]
      0x00a1: PHI (r17v3 int) = (r17v1 int), (r17v4 int) binds: [B:95:0x02d2, B:14:0x008e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ea A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x022a A[PHI: r0 r1 r2 r3 r12 r13 r14 r15 r18
      0x022a: PHI (r0v43 float) = (r0v41 float), (r0v44 float) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r1v18 float) = (r1v16 float), (r1v19 float) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r2v17 int) = (r2v15 int), (r2v18 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r3v19 int) = (r3v17 int), (r3v20 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r12v16 int) = (r12v14 int), (r12v17 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r13v13 int) = (r13v11 int), (r13v14 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r14v11 int) = (r14v9 int), (r14v12 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r15v10 int) = (r15v8 int), (r15v11 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]
      0x022a: PHI (r18v3 int) = (r18v1 int), (r18v4 int) binds: [B:78:0x0226, B:8:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0248 A[PHI: r0 r1 r2 r3 r12 r13 r14 r15 r18
      0x0248: PHI (r0v45 float) = (r0v43 float), (r0v49 float) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r1v20 float) = (r1v18 float), (r1v22 float) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r2v19 int) = (r2v17 int), (r2v21 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r3v21 int) = (r3v19 int), (r3v23 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r12v18 int) = (r12v16 int), (r12v19 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r13v15 int) = (r13v13 int), (r13v16 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r14v13 int) = (r14v11 int), (r14v14 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r15v12 int) = (r15v10 int), (r15v13 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]
      0x0248: PHI (r18v5 int) = (r18v3 int), (r18v6 int) binds: [B:81:0x0244, B:7:0x001b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0286 A[RETURN] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instruction units count: 848
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsTvRows$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsTvRows$3$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
