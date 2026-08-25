package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.i1;
import androidx.appcompat.view.menu.e0;
import androidx.core.view.b2;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements e0.a, AbsListView.SelectionBoundsAdjuster {
    public boolean A;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public t f1233i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ImageView f1234l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RadioButton f1235m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public TextView f1236n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CheckBox f1237o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f1238p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ImageView f1239q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ImageView f1240r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LinearLayout f1241s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Drawable f1242t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f1243u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Context f1244v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1245w;
    public final Drawable x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f1246y;
    public LayoutInflater z;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        i1 i1VarZ = i1.Z(getContext(), attributeSet, j.a.f19033r, R.attr.listMenuViewStyle);
        this.f1242t = i1VarZ.T(5);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        this.f1243u = typedArray.getResourceId(1, -1);
        this.f1245w = typedArray.getBoolean(7, false);
        this.f1244v = context;
        this.x = i1VarZ.T(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.f1246y = typedArrayObtainStyledAttributes.hasValue(0);
        i1VarZ.b0();
        typedArrayObtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.z == null) {
            this.z = LayoutInflater.from(getContext());
        }
        return this.z;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.f1239q;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f1240r;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f1240r.getLayoutParams();
        rect.top = this.f1240r.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0058  */
    @Override // androidx.appcompat.view.menu.e0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(androidx.appcompat.view.menu.t r11) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.c(androidx.appcompat.view.menu.t):void");
    }

    @Override // androidx.appcompat.view.menu.e0.a
    public t getItemData() {
        return this.f1233i;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        WeakHashMap weakHashMap = b2.f2200a;
        setBackground(this.f1242t);
        TextView textView = (TextView) findViewById(R.id.title);
        this.f1236n = textView;
        int i10 = this.f1243u;
        if (i10 != -1) {
            textView.setTextAppearance(this.f1244v, i10);
        }
        this.f1238p = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f1239q = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.x);
        }
        this.f1240r = (ImageView) findViewById(R.id.group_divider);
        this.f1241s = (LinearLayout) findViewById(R.id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        if (this.f1234l != null && this.f1245w) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1234l.getLayoutParams();
            int i12 = layoutParams.height;
            if (i12 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i12;
            }
        }
        super.onMeasure(i10, i11);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        View view;
        if (!z && this.f1235m == null && this.f1237o == null) {
            return;
        }
        if ((this.f1233i.x & 4) != 0) {
            if (this.f1235m == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f1235m = radioButton;
                LinearLayout linearLayout = this.f1241s;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f1235m;
            view = this.f1237o;
        } else {
            if (this.f1237o == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f1237o = checkBox;
                LinearLayout linearLayout2 = this.f1241s;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f1237o;
            view = this.f1235m;
        }
        if (z) {
            compoundButton.setChecked(this.f1233i.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.f1237o;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f1235m;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if ((this.f1233i.x & 4) != 0) {
            if (this.f1235m == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f1235m = radioButton;
                LinearLayout linearLayout = this.f1241s;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f1235m;
        } else {
            if (this.f1237o == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f1237o = checkBox;
                LinearLayout linearLayout2 = this.f1241s;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f1237o;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.A = z;
        this.f1245w = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.f1240r;
        if (imageView != null) {
            imageView.setVisibility((this.f1246y || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        q qVar = this.f1233i.f1379n;
        boolean z = this.A;
        if (z || this.f1245w) {
            ImageView imageView = this.f1234l;
            if (imageView == null && drawable == null && !this.f1245w) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f1234l = imageView2;
                LinearLayout linearLayout = this.f1241s;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.f1245w) {
                this.f1234l.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f1234l;
            if (!z) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f1234l.getVisibility() != 0) {
                this.f1234l.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f1236n.getVisibility() != 8) {
                this.f1236n.setVisibility(8);
            }
        } else {
            this.f1236n.setText(charSequence);
            if (this.f1236n.getVisibility() != 0) {
                this.f1236n.setVisibility(0);
            }
        }
    }
}
