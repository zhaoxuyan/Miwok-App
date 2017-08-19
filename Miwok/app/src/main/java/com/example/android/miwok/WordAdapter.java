package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ArrayAdapter负责处理数据
 * 因为ArrayAdapter默认的getView方法只能包含一个TextView，所以需要重写getView
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        /**
         *  list_item
         */
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        //得到当前Word对象
        Word currentWord = getItem(position);
        //装入两个TextView
        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTranslation.setText(currentWord.getmDefaultTranslation());
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTranslation.setText(currentWord.getmMiwokTranslation());
        //装入ImageView
        ImageView imageReourceId = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()){
            imageReourceId.setImageResource(currentWord.getmImageResourveId());
            imageReourceId.setVisibility(View.VISIBLE);
        }
        else{
            imageReourceId.setVisibility(View.GONE);
        }
        //装入color
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
