package com.vinhsang.vivmall.presentation.view.recyclerviewhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yuvaraj on 3/4/16.
 */
public class DynamicHeightImageView extends ImageView {

	private float whRatio = 1;

	public DynamicHeightImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DynamicHeightImageView(Context context) {
		super(context);
	}

	public void setRatio(float ratio) {
		whRatio = ratio;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (whRatio != 0) {
			int width = getMeasuredWidth();
			int height = (int)(whRatio * width);
			setMeasuredDimension(width, height);
		}else{
			int width = getMeasuredWidth();
			int height = getMeasuredHeight();
			if(height>0&&width>0){
				whRatio = getMeasuredHeight()/getMeasuredWidth();
				setMeasuredDimension(width, height);
			}

		}
;
	}
}
