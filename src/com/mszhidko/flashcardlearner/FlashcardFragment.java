package com.mszhidko.flashcardlearner;

import java.text.DateFormat;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class FlashcardFragment extends Fragment {
	public static final String EXTRA_FLASHCARD_ID = 
			"com.mszhidko.android.flashcardlearner.flashcard_id";
	
	private Flashcard mFlashcard;
	private EditText mFronttextField;
	private EditText mBacktextField;
	private Button mDateButton;
	private CheckBox mLearntCheckBox;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//UUID flashcardId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_FLASHCARD_ID);
		UUID flashcardId = (UUID)getArguments().getSerializable(EXTRA_FLASHCARD_ID);
		mFlashcard = FlashcardDB.get(getActivity()).getFlashcard(flashcardId);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstance) {
		View v = inflater.inflate(R.layout.fragment_flashcard, parent, false);
		
		mFronttextField = (EditText)v.findViewById(R.id.flashcard_fronttext);
		mFronttextField.setText(mFlashcard.getFrontText());
		mFronttextField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mFlashcard.setFrontText(c.toString());
			}
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				
			}
			
			public void afterTextChanged(Editable c) {
				
			}
		});
		
		mBacktextField = (EditText)v.findViewById(R.id.flashcard_backtext);
		mBacktextField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mFlashcard.setBackText(c.toString());
			}
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				
			}
			
			public void afterTextChanged(Editable c) {
				
			}
		});
		
		mDateButton = (Button)v.findViewById(R.id.flashcard_date);
		
		DateFormat df = DateFormat.getDateInstance();
		
		mDateButton.setText( df.format(mFlashcard.getDate() ) );
		mDateButton.setEnabled(false);
		
		mLearntCheckBox = (CheckBox)v.findViewById(R.id.flashcard_learnt);
		mLearntCheckBox.setChecked(mFlashcard.isLearnt());
		mLearntCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mFlashcard.setLearnt(isChecked);
			}
		});
		
		Button mSendButton = (Button) v.findViewById(R.id.flashcard_send_button);
		mSendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_TEXT, "intent_text");
				i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.flashcard_send_subject));
				startActivity(i);
			}
		});
		
		return v;
	}
	
	public static FlashcardFragment newInstance(UUID flashcardId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_FLASHCARD_ID, flashcardId);
		
		FlashcardFragment fragment = new FlashcardFragment();
		fragment.setArguments(args);
		
		return fragment;
	}

}
