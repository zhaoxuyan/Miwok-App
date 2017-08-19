package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call

                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.

                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);//从音频的最开头开始 如果我们重新获得audio focus 最好重新播放整个单词
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.

                        mMediaPlayer.start();//API中没有恢复的方法, 所有调用start():从上次离开的地方继续
                    }
                }
            };

    /**
     * 声明mCompletionListener全局变量节约内存资源
     * 匿名类用法
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

//        String[] numbers = new String[10];
//        numbers[0]="one";
//        numbers[1]="two";
//        numbers[2]="three";
//        numbers[3]="four";
//        numbers[4]="five";
//        numbers[5]="six";
//        numbers[6]="seven";
//        numbers[7]="eight";
//        numbers[8]="nine";
//        numbers[9]="ten";/^
        /**
         * 匿名类的参数必须为final类型
         * 这样onItemClick才可以使用words
         */
        final ArrayList<Word> words = new ArrayList<>();
//        Word w = new Word("one", "lutti");
//        words.add(w);
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

//        /**
//         * 向【LinearLayout】中手动添加 【TextView】
//         */
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.activity_numbers);
//        for(int i = 0; i < numbers.size(); i++) {
//            TextView numberView = new TextView(NumbersActivity.this);
//            numberView.setText(numbers.get(i));
//            rootView.addView(numberView);
//        }
        /**
         * ArrayAdapter负责处理数据（将数据从ArrayList装入ArrayAdapter）
         * ListView负责用户界面
         */
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list_numbers);
        listView.setAdapter(adapter);
        /**
         * 设置OnItemClickListener监听器称为异步回调(Asyn callback)
         * OnItemClickListener在行内定义时是个匿名类(new AdapterView.OnItemClickListener)
         * OnItemClickListener是一个接口,用匿名类的方式实例化它，并重写onItemClick方法
         * AdapterView.OnItemClickListener的意思是将监听时间将附到AdapterView(ListView)的视图上
         * ListView是实体类,AdaperView是抽象类
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this, "list item clicked", Toast.LENGTH_SHORT).show();
                Word word = words.get(position);
                Log.v("NumbersActivity", "Current word:" + word);//自动调用word.toString();

                /**
                 * 如果正在播放音频时用户又点击了另一个音频需要先释放正在播放的音频,再create新的音频
                 */
                releaseMediaPlayer();

                /**
                 * Request audio focus for playback
                 */

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request Transient focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // 意味着已经成功捕获到了Audio Focus
                    // 可以开始create和start了
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmRawResourceId());
                    mMediaPlayer.start();
//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        releaseMediaPlayer();
//                    }
//                });
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // 释放AudioFocus
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

