package com.dafruits.android.library.widgets;

import com.dafruits.android.library.R;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.ListView;


public class ExtendedListView extends ListView {
	
	private OnScrollListener mOnScrollListener = null;
	
	private View mScrollBarPanel = null;
	private int mScrollBarPanelPosition = 0;
	private int mScrollBarPanelFadeDuration = 0;
	
	private Animation mInAnimation = null;
	private Animation mOutAnimation = null;
	
	private Handler mHandler = new Handler();
	private Runnable mScrollBarPanelFadeRunnable = new Runnable() {
		
		@Override
		public void run() {
			mScrollBarPanel.startAnimation(mOutAnimation);
		}
	};
	
	/*
	 * keep track of Measure Spec
	 */
	private int mWidthMeasureSpec;
	private int mHeightMeasureSpec;
	
	
	private OnScrollListener mScrollListener = new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			if (mOnScrollListener != null) {
				mOnScrollListener.onScrollStateChanged(view, scrollState);
			}
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			updateScrollerView();
			if (mOnScrollListener != null) {
				mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
			}
		}
	};
	
	
	public ExtendedListView(Context context) {
        super(context);
        init();
    }

	
    public ExtendedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
		init();
    }

    
    public ExtendedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
		init();
    }

    
    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
    	mOnScrollListener = onScrollListener;
    }
   
    
    public void setScrollBarPanel(View scrollBarPanel) {
    	mScrollBarPanel = scrollBarPanel;
    	mScrollBarPanel.setVisibility(View.GONE);
    	requestLayout();
    }
    
    
    public void setScrollBarPanel(int resId) {
    	setScrollBarPanel(LayoutInflater.from(getContext()).inflate(resId, this, false));
    }
    
    
    public View getScrollBarPanel() {
    	return mScrollBarPanel;
    }
    
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        if (mScrollBarPanel != null && getAdapter() != null) {
        	mWidthMeasureSpec = widthMeasureSpec;
        	mHeightMeasureSpec = heightMeasureSpec;
        	measureChild(mScrollBarPanel, widthMeasureSpec, heightMeasureSpec);
        }
    }
    
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        
        if (mScrollBarPanel != null) {
        	final int x = getMeasuredWidth() - mScrollBarPanel.getMeasuredWidth() - getVerticalScrollbarWidth();
        	mScrollBarPanel.layout(x, mScrollBarPanelPosition, x + mScrollBarPanel.getMeasuredWidth(), mScrollBarPanelPosition + mScrollBarPanel.getMeasuredHeight());
        }
    }
    
    
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    
        if (mScrollBarPanel != null && mScrollBarPanel.getVisibility() == View.VISIBLE) {
            drawChild(canvas, mScrollBarPanel, getDrawingTime());
        }
    }
    
    
    @Override
    public void onDetachedFromWindow() {
    	super.onDetachedFromWindow();
    	
    	mHandler.removeCallbacks(mScrollBarPanelFadeRunnable);
    }
    
    
    private void updateScrollerView() {
    	if (mScrollBarPanel == null) {
    		return;
    	}
    	
    	mHandler.removeCallbacks(mScrollBarPanelFadeRunnable);
    	
    	if (mScrollBarPanel.getVisibility() == View.GONE) {
    		mScrollBarPanel.setVisibility(View.VISIBLE);
    		mScrollBarPanel.startAnimation(mInAnimation);
    	}
    	
    	/*
    	 * from android source code (ScrollBarDrawable.java)
    	 */
    	final int thickness = getVerticalScrollbarWidth();
    	int height = Math.round((float) getMeasuredHeight() * computeVerticalScrollExtent() / computeVerticalScrollRange());
    	final int offset = Math.round((float) (getMeasuredHeight() - height) * computeVerticalScrollOffset() / (computeVerticalScrollRange() - computeVerticalScrollExtent()));
    	final int minLength = thickness * 2;
        if (height < minLength) {
            height = minLength;
        }

        /*
         * quick hack to handle TextView has ScrollBarPanel (to wrap text in case TextView's content has changed)
         */
        measureChild(mScrollBarPanel, mWidthMeasureSpec, mHeightMeasureSpec);
        
    	mScrollBarPanelPosition = offset + height / 2 - mScrollBarPanel.getMeasuredHeight() / 2;

    	final int x = getMeasuredWidth() - mScrollBarPanel.getMeasuredWidth() - getVerticalScrollbarWidth();
    	mScrollBarPanel.layout(x, mScrollBarPanelPosition, x + mScrollBarPanel.getMeasuredWidth(), mScrollBarPanelPosition + mScrollBarPanel.getMeasuredHeight());
    	
    	mHandler.postDelayed(mScrollBarPanelFadeRunnable, mScrollBarPanelFadeDuration);
    }
    
    
    private void init() {
    	super.setOnScrollListener(mScrollListener);
    	mScrollBarPanelFadeDuration = ViewConfiguration.getScrollBarFadeDuration() * 2;
    	
    	mInAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.in_animation);
    	mOutAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.out_animation);
    	
    	mOutAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {

			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if (mScrollBarPanel != null) {
					mScrollBarPanel.setVisibility(View.GONE);
				}
			}
		});
    }
}
