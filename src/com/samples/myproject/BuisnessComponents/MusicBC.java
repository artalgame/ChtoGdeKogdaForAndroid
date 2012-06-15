package com.samples.myproject.BuisnessComponents;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicBC {
	
	private static MediaPlayer mp = null;
	
	public static void play(Context context, int resource){
		//stop old song
		stop(context);
		//play new song
		mp = MediaPlayer.create(context, resource);
		mp.setLooping(true);
		mp.start();
	}
	
	public static boolean isPlaying(Context context){
		if (mp != null){
			if (mp.isLooping()){
				return true;
			}
			else 
				return false;
		}
		else
			return false;
	}
	
	public static void stop(Context context){
		if (mp != null){
			mp.stop();
			mp.release();
			mp = null;
		}
	}
}
