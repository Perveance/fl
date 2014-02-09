package com.mszhidko.flashcardlearner;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class FlashcardPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Flashcard> mFlashcards;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mFlashcards = FlashcardDB.get(this).getFlashcards();
		
		FragmentManager fm = getSupportFragmentManager();
		
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return mFlashcards.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				Flashcard f = mFlashcards.get(pos);
				
				return FlashcardFragment.newInstance(f.getId());
			}
		});
		
		UUID fcId = (UUID) getIntent().getSerializableExtra(FlashcardFragment.EXTRA_FLASHCARD_ID);
		for (int i = 0; i < mFlashcards.size(); i++) {
			if (mFlashcards.get(i).getId().equals(fcId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
		
	}

}
