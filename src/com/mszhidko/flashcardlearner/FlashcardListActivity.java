package com.mszhidko.flashcardlearner;

import android.support.v4.app.Fragment;

public class FlashcardListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new FlashcardListFragment();
	}

}
