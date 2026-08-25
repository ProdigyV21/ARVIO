package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.R;

/* JADX INFO: loaded from: classes4.dex */
final class zzi {
    private final Rect zza = new Rect();
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final zzh zzf;

    public zzi(zzh zzhVar) {
        this.zzf = zzhVar;
        Resources resources = zzhVar.getResources();
        this.zzb = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzc = resources.getDimensionPixelOffset(R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzd = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zze = resources.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    private final int zzb(View view, int i10, int i11, int i12, int i13) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i14 = i13 - i10;
        int i15 = i11 - i13;
        int i16 = i13 - (i12 / 2);
        int i17 = i14 <= i15 ? i16 + this.zze : i16 - this.zze;
        int i18 = marginLayoutParams.leftMargin;
        if (i17 - i18 < i10) {
            return i10 + i18;
        }
        int i19 = marginLayoutParams.rightMargin;
        return (i17 + i12) + i19 > i11 ? (i11 - i12) - i19 : i17;
    }

    private final void zzc(View view, int i10, int i11) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min((i10 - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, this.zzd), 1073741824), View.MeasureSpec.makeMeasureSpec(i11, Integer.MIN_VALUE));
    }

    public final void zza(Rect rect, Rect rect2) {
        zzi zziVar;
        zzh zzhVar = this.zzf;
        View viewZzg = zzhVar.zzg();
        if (rect.isEmpty() || rect2.isEmpty()) {
            zziVar = this;
            viewZzg.layout(0, 0, 0, 0);
        } else {
            int iCenterY = rect.centerY();
            int iCenterX = rect.centerX();
            int iCenterY2 = rect2.centerY();
            int iHeight = rect.height();
            int i10 = this.zzb;
            int iMax = Math.max(i10 + i10, iHeight) / 2;
            int i11 = iCenterY + iMax;
            int i12 = this.zzc;
            if (iCenterY < iCenterY2) {
                int i13 = i12 + i11;
                zzc(viewZzg, rect2.width(), rect2.bottom - i13);
                int iZzb = zzb(viewZzg, rect2.left, rect2.right, viewZzg.getMeasuredWidth(), iCenterX);
                viewZzg.layout(iZzb, i13, viewZzg.getMeasuredWidth() + iZzb, viewZzg.getMeasuredHeight() + i13);
                zziVar = this;
                viewZzg = viewZzg;
            } else {
                int i14 = (iCenterY - iMax) - i12;
                zzc(viewZzg, rect2.width(), i14 - rect2.top);
                viewZzg = viewZzg;
                zziVar = this;
                int iZzb2 = zziVar.zzb(viewZzg, rect2.left, rect2.right, viewZzg.getMeasuredWidth(), iCenterX);
                viewZzg.layout(iZzb2, i14 - viewZzg.getMeasuredHeight(), viewZzg.getMeasuredWidth() + iZzb2, i14);
            }
        }
        Rect rect3 = zziVar.zza;
        rect3.set(viewZzg.getLeft(), viewZzg.getTop(), viewZzg.getRight(), viewZzg.getBottom());
        zzhVar.zzh().zzc(rect, rect3);
        zzhVar.zzi().zza(rect);
    }
}
