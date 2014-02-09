package com.mszhidko.flashcardlearner;

import java.util.Date;
import java.util.UUID;

public class Flashcard {

	private UUID mId;
	private String mFrontText;
	private String mBackText;
	private Date mDate;
	private boolean mLearnt;
	
	public Flashcard() {
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
	@Override
	public String toString() {
		return mFrontText;
	}

	public String getFrontText() {
		return mFrontText;
	}

	public void setFrontText(String front) {
		mFrontText = front;
	}

	public UUID getId() {
		return mId;
	}

	public String getBackText() {
		return mBackText;
	}

	public void setBackText(String backText) {
		mBackText = backText;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isLearnt() {
		return mLearnt;
	}

	public void setLearnt(boolean learnt) {
		mLearnt = learnt;
	}
}
