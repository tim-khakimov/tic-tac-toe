package com.timkhakimov.tictactoe.adapter.dividers;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Timur Khakimov on 21.09.2019
 * Base ItemDecoration for drawing dividers and offsets in adapters using DividersOffsetsBehavior and DividersDrawablesBehavior
 */
public class BaseDividersDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = BaseDividersDecoration.class.toString();
    private DividersOffsetsBehavior dividersOffsetsBehavior;
    private DividersDrawablesBehavior dividersDrawablesBehavior;
    private final Rect mBounds = new Rect();

    public BaseDividersDecoration() {
    }

    public BaseDividersDecoration(DividersOffsetsBehavior dividersOffsetsBehavior, DividersDrawablesBehavior dividersDrawablesBehavior) {
        this.dividersOffsetsBehavior = dividersOffsetsBehavior;
        this.dividersDrawablesBehavior = dividersDrawablesBehavior;
    }

    public void setDividersOffsetsBehavior(@Nullable DividersOffsetsBehavior dividersOffsetsBehavior) {
        this.dividersOffsetsBehavior = dividersOffsetsBehavior;
    }

    public void setDividersDrawablesBehavior(@Nullable DividersDrawablesBehavior dividersDrawablesBehavior) {
        this.dividersDrawablesBehavior = dividersDrawablesBehavior;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        if (dividersOffsetsBehavior == null) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        int itemPosition = parent.getChildAdapterPosition(view);
        outRect.set(dividersOffsetsBehavior.getLeftOffset(itemPosition), dividersOffsetsBehavior.getTopOffset(itemPosition), dividersOffsetsBehavior.getRightOffset(itemPosition), dividersOffsetsBehavior.getBottomOffset(itemPosition));
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null || dividersOffsetsBehavior == null || dividersDrawablesBehavior == null) {
            super.onDraw(canvas, parent, state);
            return;
        }
        drawDividers(canvas, parent);
    }

    private void drawDividers(Canvas canvas, RecyclerView parent) {
        canvas.save();
        if (parent.getClipToPadding()) {
            canvas.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getWidth() - parent.getPaddingRight(), parent.getHeight() - parent.getPaddingBottom());
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int adapterPosition = parent.getChildAdapterPosition(child);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            drawLeftDivider(canvas, adapterPosition);
            drawTopDivider(canvas, adapterPosition);
            drawRightDivider(canvas, adapterPosition);
            drawBottomDivider(canvas, adapterPosition);
        }
        canvas.restore();
    }

    private void drawLeftDivider(Canvas canvas, int adapterPosition) {
        int leftOffset = dividersOffsetsBehavior.getLeftOffset(adapterPosition);
        if (leftOffset == 0) {
            return;
        }
        Drawable mDivider = dividersDrawablesBehavior.getLeftDivider(adapterPosition);
        if (mDivider == null) {
            return;
        }
        final int right = mBounds.left + leftOffset;
        int left = mBounds.left;
        if (mDivider.getIntrinsicWidth() >= 0 && mDivider.getIntrinsicWidth() < leftOffset) {
            left = right - leftOffset;
        }
        mDivider.setBounds(left, mBounds.top, right, mBounds.bottom);
        mDivider.draw(canvas);
    }

    private void drawTopDivider(Canvas canvas, int adapterPosition) {
        int topOffset = dividersOffsetsBehavior.getTopOffset(adapterPosition);
        if (topOffset == 0) {
            return;
        }
        Drawable divider = dividersDrawablesBehavior.getTopDivider(adapterPosition);
        if (divider == null) {
            return;
        }
        final int bottom = mBounds.top + topOffset;
        int top = mBounds.top;
        if (divider.getIntrinsicHeight() >= 0 && divider.getIntrinsicHeight() < topOffset) {
            top = bottom - divider.getIntrinsicHeight();
        }
        divider.setBounds(mBounds.left, top, mBounds.right, bottom);
        divider.draw(canvas);
    }

    private void drawRightDivider(Canvas canvas, int adapterPosition) {
        int rightOffset = dividersOffsetsBehavior.getRightOffset(adapterPosition);
        if (rightOffset == 0) {
            return;
        }
        Drawable divider = dividersDrawablesBehavior.getRightDivider(adapterPosition);
        if (divider == null) {
            return;
        }
        final int left = mBounds.right - rightOffset;
        divider.setBounds(left, mBounds.top, mBounds.right, mBounds.bottom);
        divider.draw(canvas);
    }

    private void drawBottomDivider(Canvas canvas, int adapterPosition) {
        int bottomOffset = dividersOffsetsBehavior.getBottomOffset(adapterPosition);
        if (bottomOffset == 0) {
            return;
        }
        Drawable divider = dividersDrawablesBehavior.getBottomDivider(adapterPosition);
        if (divider == null) {
            return;
        }
        final int top = mBounds.bottom - bottomOffset;
        divider.setBounds(mBounds.left, top, mBounds.right, mBounds.bottom);
        divider.draw(canvas);
    }
}
