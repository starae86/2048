package com.ae86.game2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		initGameView();
	}

	private void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
		setOnTouchListener(new OnTouchListener() {
			private float startX, startY, offSetX, offSetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offSetX = event.getX() - startX;
					offSetY = event.getY() - startY;
					if (Math.abs(offSetX)>Math.abs(offSetY)) {
						if (offSetX<-5) {
							swipeLeft();
						}else if (offSetX>5) {
							swipeRight();
						}
					}else{
						if (offSetY<-5) {
							swipeUp();
						}else if (offSetY>5) {
							swipeDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}
	private void swipeUp() {
		// TODO Auto-generated method stub
		
	}

	private void swipeDown() {
		// TODO Auto-generated method stub
		
	}

	private void swipeLeft() {
		// TODO Auto-generated method stub
		
	}

	private void swipeRight() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//��Ƭ���
		int cardWidth = (Math.min(w,h)-10)/4;
		addCards(cardWidth, cardWidth);

	}

	private void addCards(int cardWidth,int cardHeight) {
		Card c;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <4; j++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth,cardHeight);
				cardsMap[i][j]= c;
			}
		}
	}
	private Card[][] cardsMap= new Card[4][4];


}
