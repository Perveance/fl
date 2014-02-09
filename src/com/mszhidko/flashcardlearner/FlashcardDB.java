package com.mszhidko.flashcardlearner;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class FlashcardDB {
	private ArrayList<Flashcard> mFlashcards;
	
	private static FlashcardDB sFlashcardDB;
	private Context mAppContext;
	
	private FlashcardDB(Context appContext) {
		mAppContext = appContext;
		mFlashcards = new ArrayList<Flashcard>();
		/*
		for (int i = 0; i < 100; i++) {
			Flashcard f = new Flashcard();
			f.setFrontText("Flashcard #" + i);
			f.setBackText("Translation #" + i);
			f.setLearnt(i % 2 == 0); // Every other one
			mFlashcards.add(f);
		}*/
	}
	
	public static FlashcardDB get(Context c) {
		if (sFlashcardDB == null) {
			sFlashcardDB = new FlashcardDB(c.getApplicationContext()); 
		}
		return sFlashcardDB;
	}
	
	public void addFlashcard(Flashcard f) {
		mFlashcards.add(f);
	}
	
	public ArrayList<Flashcard> getFlashcards() {
		return mFlashcards;
	}
	
	public Flashcard getFlashcard(UUID id) {
		for (Flashcard f : mFlashcards) {
			if (f.getId().equals(id))
				return f;
		}
		return null;
	}
	
}
