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
					if (Math.abs(offSetX) > Math.abs(offSetY)) {
						if (offSetX < -5) {
							swipeLeft();
							System.out.println("left");
						} else if (offSetX > 5) {
							swipeRight();
							System.out.println("right");
						}
					} else {
						if (offSetY < -5) {
							swipeUp();
							System.out.println("up");
						} else if (offSetY > 5) {
							swipeDown();
							System.out.println("down");
						}
					}
					break;
				}
				return true;
			}
		});
	}

	private void swipeLeft() {
		boolean merge = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {

				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y--;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
		}

	}

	private void swipeRight() {
		boolean merge = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {

				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardsMap[x][y1].getNum() > 0) {
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
		}

	}

	private void swipeUp() {
		boolean merge = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cardsMap[x1][y].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x--;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
		}
	}

	private void swipeDown() {
		boolean merge = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {

				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardsMap[x1][y].getNum() > 0) {

						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x++;
							merge = true;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardsMap[x][y].getNum());
							merge = true;
						}

						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// ¿¨Æ¬¿í¶È
		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCards(cardWidth, cardWidth);
		startGame();

	}

	private void addCards(int cardWidth, int cardHeight) {
		Card c;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				c = new Card(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				cardsMap[i][j] = c;
			}
		}
	}

	private void startGame() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cardsMap[i][j].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	private Card[][] cardsMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();

	private void addRandomNum() {
		emptyPoints.clear();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cardsMap[i][j].getNum() <= 0) {
					emptyPoints.add(new Point(i, j));
				}
			}
		}
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

}
