package com.mszhidko.flashcardlearner;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class FlashcardListFragment extends ListFragment {
	
	private ArrayList<Flashcard> mFlashcards;
	
	private static final String TAG = "FlashcardListFragment";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.flashcards_title);
		mFlashcards = FlashcardDB.get(getActivity()).getFlashcards();
		
		Log.d(TAG, "FlashcardListFragment onCreate");
		
		FlashcardAdapter adapter = new FlashcardAdapter(mFlashcards);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Flashcard f = ((FlashcardAdapter)getListAdapter()).getItem(position);
		//Log.d(TAG, f.getFrontText() + " was clicked");
		
		Intent i = new Intent(getActivity(), FlashcardPagerActivity.class);
		i.putExtra(FlashcardFragment.EXTRA_FLASHCARD_ID, f.getId());
		startActivity(i);
	}
	
	private class FlashcardAdapter extends ArrayAdapter<Flashcard> {
		
		public FlashcardAdapter(ArrayList<Flashcard> flashcards) {
			super(getActivity(), 0, flashcards);
		}
		
		@Override 
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_flashcard, null);
			}
			
			Flashcard f = getItem(position);
			
			TextView frontTextView = (TextView)convertView.findViewById(R.id.flashcard_list_item_FrontTextView);
			frontTextView.setText(f.getFrontText());
			
			TextView dateTextView = (TextView)convertView.findViewById(R.id.flashcard_list_item_DateTextView);
			dateTextView.setText(f.getDate().toString());
			
			CheckBox learntCheckBox = (CheckBox)convertView.findViewById(R.id.flashcard_list_item_learntCheckBox);
			learntCheckBox.setChecked(f.isLearnt());
			
			return convertView;
		}
	}
	
	public void onResume() {
		super.onResume();
		((FlashcardAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_flashcard_list, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_new_flashcard:
			Flashcard f = new Flashcard();
			FlashcardDB.get(getActivity()).addFlashcard(f);
			Intent i = new Intent(getActivity(), FlashcardPagerActivity.class);
			i.putExtra(FlashcardFragment.EXTRA_FLASHCARD_ID, f.getId());
			startActivityForResult(i, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
