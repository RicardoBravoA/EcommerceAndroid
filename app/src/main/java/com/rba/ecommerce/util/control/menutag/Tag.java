package com.rba.ecommerce.util.control.menutag;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rba.ecommerce.R;
import com.rba.ecommerce.util.control.menutag.listener.TagListener;

/**
 * Created by Ricardo Bravo on 12/07/16.
 */

public class Tag extends TextView implements View.OnClickListener {

    private int index = -1;
    private boolean selected = false;
    private TagListener listener = null;
    private int selectedFontColor = -1;
    private int unselectedFontColor = -1;
    private TransitionDrawable crossfader;
    private int selectTransitionMS = 750;
    private int deselectTransitionMS = 500;
    private boolean isLocked = false;

    public void setTagListener(TagListener listener) {
        this.listener = listener;
    }

    public Tag(Context context) {
        super(context);
        init();
    }

    public Tag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Tag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void initTag(Context context, int index, String label, int selectedColor,
                        int selectedFontColor, int unselectedColor, int unselectedFontColor) {

        this.index = index;
        this.selectedFontColor = selectedFontColor;
        this.unselectedFontColor = unselectedFontColor;

        Drawable selectedDrawable = ContextCompat.getDrawable(context, R.drawable.tag_selected);

        if (selectedColor == -1) {
            selectedDrawable.setColorFilter(new PorterDuffColorFilter(
                    ContextCompat.getColor(context, R.color.dark_grey), PorterDuff.Mode.MULTIPLY));
        } else {
            selectedDrawable.setColorFilter(
                    new PorterDuffColorFilter(selectedColor, PorterDuff.Mode.MULTIPLY));
        }

        if (selectedFontColor == -1) {
            this.selectedFontColor = ContextCompat.getColor(context, R.color.white);
        }

        Drawable unselectedDrawable = ContextCompat.getDrawable(context, R.drawable.tag_selected);
        if (unselectedColor == -1) {
            unselectedDrawable.setColorFilter(
                    new PorterDuffColorFilter(ContextCompat.getColor(context, R.color.light_grey),
                            PorterDuff.Mode.MULTIPLY));
        } else {
            unselectedDrawable.setColorFilter(
                    new PorterDuffColorFilter(unselectedColor, PorterDuff.Mode.MULTIPLY));
        }

        if (unselectedFontColor == -1) {
            this.unselectedFontColor = ContextCompat.getColor(context, R.color.white);
        }

        Drawable backgrounds[] = new Drawable[2];
        backgrounds[0] = unselectedDrawable;
        backgrounds[1] = selectedDrawable;

        crossfader = new TransitionDrawable(backgrounds);

        int leftPad = getPaddingLeft();
        int topPad = getPaddingTop();
        int rightPad = getPaddingRight();
        int bottomPad = getPaddingBottom();

        setBackgroundCompat(crossfader);

        setPadding(leftPad, topPad, rightPad, bottomPad);

        setText(label);
        unselect();
    }

    public void setLocked(boolean isLocked){
        this.isLocked = isLocked;
    }

    public void setSelectTransitionMS(int selectTransitionMS) {
        this.selectTransitionMS = selectTransitionMS;
    }

    public void setDeselectTransitionMS(int deselectTransitionMS) {
        this.deselectTransitionMS = deselectTransitionMS;
    }

    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (selected && !isLocked) {
            unselect();
            if (listener != null) {
                listener.onTagDeselected(index);
            }
        } else if (!selected) {
            crossfader.startTransition(selectTransitionMS);

            setTextColor(selectedFontColor);
            if (listener != null) {
                listener.onTagSelected(index);
            }
        }

        selected = !selected;
    }

    public void select() {
        selected = true;
        crossfader.startTransition(selectTransitionMS);
        setTextColor(selectedFontColor);
        if (listener != null) {
            listener.onTagSelected(index);
        }
    }

    private void unselect() {
        if (selected) {
            crossfader.reverseTransition(deselectTransitionMS);
        } else {
            crossfader.resetTransition();
        }

        setTextColor(unselectedFontColor);
    }

    @SuppressWarnings("deprecation")
    private void setBackgroundCompat(Drawable background) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(background);
        } else {
            setBackground(background);
        }
    }

    public void deselect() {
        unselect();
        selected = false;
    }

    public static class TagBuilder {
        private int index;
        private String label;
        private int selectedColor;
        private int selectedFontColor;
        private int unselectedColor;
        private int unselectedFontColor;
        private int tagHeight;
        private int selectTransitionMS = 750;
        private int deselectTransitionMS = 500;

        private TagListener tagListener;

        public TagBuilder index(int index) {
            this.index = index;
            return this;
        }

        public TagBuilder selectedColor(int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        public TagBuilder selectedFontColor(int selectedFontColor) {
            this.selectedFontColor = selectedFontColor;
            return this;
        }

        public TagBuilder unselectedColor(int unselectedColor) {
            this.unselectedColor = unselectedColor;
            return this;
        }

        public TagBuilder unselectedFontColor(int unselectedFontColor) {
            this.unselectedFontColor = unselectedFontColor;
            return this;
        }

        public TagBuilder label(String label) {
            this.label = label;
            return this;
        }

        public TagBuilder tagHeight(int tagHeight) {
            this.tagHeight = tagHeight;
            return this;
        }

        public TagBuilder tagListener(TagListener tagListener) {
            this.tagListener = tagListener;
            return this;
        }

        public TagBuilder selectTransitionMS(int selectTransitionMS) {
            this.selectTransitionMS = selectTransitionMS;
            return this;
        }

        public TagBuilder deselectTransitionMS(int deselectTransitionMS) {
            this.deselectTransitionMS = deselectTransitionMS;
            return this;
        }

        public Tag build(Context context) {
            Tag tag = (Tag) LayoutInflater.from(context).inflate(R.layout.tag_item, null);
            tag.initTag(context, index, label, selectedColor, selectedFontColor,
                    unselectedColor, unselectedFontColor);
            tag.setSelectTransitionMS(selectTransitionMS);
            tag.setDeselectTransitionMS(deselectTransitionMS);
            tag.setTagListener(tagListener);
            tag.setHeight(tagHeight);
            return tag;
        }
    }
}