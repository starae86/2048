package com.ae86.game2048;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	private GameView gameView;
	private static final String SP_KEY_BEST_SCORE = "bestScore";
	private int score =0;
	private TextView tvScore,tvBestScore;
	private static MainActivity mainActivity = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore=(TextView) findViewById(R.id.tvScore);
        tvBestScore = (TextView) findViewById(R.id.tvBestScore);
		gameView = (GameView) findViewById(R.id.gameView);
        Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
		btnNewGame.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
			gameView.startGame();
		}});
    }
	public MainActivity() {
		mainActivity=this;
	}
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	
	public void clearScore(){
		score = 0;
		showScore();
	}
	public void showScore(){
		tvScore.setText(score+"");
	}
	public void addScore(int s) {
		score+=s;
		showScore();
		int maxScore = Math.max(score, getBestScore());
		saveBestScore(maxScore);
		showBestScore(maxScore);
	}
	public void saveBestScore(int s){
		Editor e = getPreferences(MODE_PRIVATE).edit();
		e.putInt(SP_KEY_BEST_SCORE, s);
		e.commit();
	}

	public int getBestScore(){
		return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE, 0);
	}

	public void showBestScore(int s){
		tvBestScore.setText(s+"");
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
