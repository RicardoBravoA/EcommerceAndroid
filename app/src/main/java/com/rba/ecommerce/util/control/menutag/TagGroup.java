package com.rba.ecommerce.util.control.menutag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rba.ecommerce.R;
import com.rba.ecommerce.util.control.menutag.listener.TagListener;

import java.util.List;

/**
 * Created by Ricardo Bravo on 12/07/16.
 */

public class TagGroup<T> extends TagLayout implements TagListener {

    public enum Mode {
        SINGLE, MULTI, REQUIRED
    }

    private Context context;
    private int tagHeight;
    private int selectedColor = -1;
    private int selectedFontColor = -1;
    private int unselectedColor = -1;
    private int unselectedFontColor = -1;
    private int selectTransitionMS = 750;
    private int deselectTransitionMS = 500;
    private Mode mode = Mode.SINGLE;

    private TagListener tagListener;

    public TagGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TagGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TagGroup, 0, 0);
        try {
            selectedColor = a.getColor(R.styleable.TagGroup_selectedColor, -1);
            selectedFontColor = a.getColor(R.styleable.TagGroup_selectedFontColor, -1);
            unselectedColor = a.getColor(R.styleable.TagGroup_deselectedColor, -1);
            unselectedFontColor = a.getColor(R.styleable.TagGroup_deselectedFontColor, -1);
            selectTransitionMS = a.getInt(R.styleable.TagGroup_selectTransitionMS, 750);
            deselectTransitionMS = a.getInt(R.styleable.TagGroup_deselectTransitionMS, 500);
            int selectMode = a.getInt(R.styleable.TagGroup_selectMode, 1);
            switch(selectMode){
                case 0:
                    mode = Mode.SINGLE;
                    break;
                case 1:
                    mode = Mode.MULTI;
                    break;
                case 2:
                    mode = Mode.REQUIRED;
                    break;
                default:
                    mode = Mode.SINGLE;
            }

        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        tagHeight = (int) (28 * getResources().getDisplayMetrics().density + 0.5f);
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setSelectedFontColor(int selectedFontColor) {
        this.selectedFontColor = selectedFontColor;
    }

    public void setUnselectedColor(int unselectedColor) {
        this.unselectedColor = unselectedColor;
    }

    public void setUnselectedFontColor(int unselectedFontColor) {
        this.unselectedFontColor = unselectedFontColor;
    }

    public void setSelectTransitionMS(int selectTransitionMS) {
        this.selectTransitionMS = selectTransitionMS;
    }

    public void setDeselectTransitionMS(int deselectTransitionMS) {
        this.deselectTransitionMS = deselectTransitionMS;
    }

    public void setMode(Mode mode){
        this.mode = mode;
        for (int i = 0; i < getChildCount(); i++) {
            Tag tag = (Tag) getChildAt(i);
            tag.deselect();
            tag.setLocked(false);
        }
    }

    public void setTagListener(TagListener tagListener) {
        this.tagListener = tagListener;
    }

    public void addTags(List<T> labels){
        for(T label : labels){
            addTag(label.toString());
        }
    }

    public void addTag(String label) {
        Tag tag = new Tag.TagBuilder().index(getChildCount())
                .label(label)
                .selectedColor(selectedColor)
                .selectedFontColor(selectedFontColor)
                .unselectedColor(unselectedColor)
                .unselectedFontColor(unselectedFontColor)
                .selectTransitionMS(selectTransitionMS)
                .deselectTransitionMS(deselectTransitionMS)
                .tagHeight(tagHeight)
                .tagListener(this)
                .build(context);

        addView(tag);
    }

    public void setSelectedTag(int index) {
        View view = this;
        Log.i("x- tg", ""+view.getId());
        Tag tag = (Tag) getChildAt(index);
        tag.select(this);
        if(mode == Mode.REQUIRED){
            for (int i = 0; i < getChildCount(); i++) {
                Tag chipp = (Tag) getChildAt(i);
                if (i == index) {
                    chipp.setLocked(true);
                }else{
                    chipp.setLocked(false);
                }
            }
        }
    }

    @Override
    public void onTagSelected(View view, int index) {

        switch(mode){
            case SINGLE:
            case REQUIRED:
                for (int i = 0; i < getChildCount(); i++) {
                    Tag tag = (Tag) getChildAt(i);
                    if (i == index) {
                        if(mode == Mode.REQUIRED) tag.setLocked(true);
                    }else{
                        tag.deselect();
                        tag.setLocked(false);
                    }
                }
                break;
            default:
                break;
        }

        if (tagListener != null) {
            tagListener.onTagSelected(this, index);
        }
    }

    @Override
    public void onTagDeselected(View view, int index) {
        if (tagListener != null) {
            tagListener.onTagDeselected(this, index);
        }
    }

    public boolean isSelected(int index) {
        if (index > 0 && index < getChildCount()) {
            Tag tag = (Tag) getChildAt(index);
            return tag.isSelected();
        }
        return false;
    }

}
