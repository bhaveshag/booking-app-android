package com.tdispatch.passenger.core;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tdispatch.passenger.R;
import com.webnetmobile.tools.WebnetTools;

/*
 ******************************************************************************
 *
 * Copyright (C) 2013 T Dispatch Ltd
 *
 * Licensed under the GPL License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <marcin.orlowski@webnet.pl>
 *
 ******************************************************************************
*/
abstract public class TDDialogFragment extends DialogFragment
{
	protected View mFragmentView;

	protected int getLayoutId() {
		throw new InternalError("LayoutId have to be specified");
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		mFragmentView = getActivity().getLayoutInflater().inflate( getLayoutId(), null);

		mFragmentView.setMinimumWidth( getResources().getDimensionPixelSize(R.dimen.dialog_min_width));

		Window w = getDialog().getWindow();
		w.requestFeature(Window.FEATURE_NO_TITLE);
		w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getDialog().setCanceledOnTouchOutside(false);

		setCustomFonts((ViewGroup)mFragmentView);

		onPostCreateView();

		return mFragmentView;
	}

	protected void onPostCreateView() {
		// dummy
	}

	@Override
	public void onResume() {

		Window w = getDialog().getWindow();

		// Auto size the dialog based on it's contents
		w.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		//w.setBackgroundDrawableResource(resid)

		// Disable standard dialog styling/frame/theme: our custom view should create full UI
//		setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

		super.onResume();
	}


	protected void setCustomFonts() {
		setCustomFonts((ViewGroup)(((ViewGroup)mFragmentView.findViewById(android.R.id.content)).getChildAt(0)));
	}

	protected void setCustomFonts( ViewGroup viewGroup ) {
		WebnetTools.setCustomFonts(TDApplication.getAppContext(), viewGroup);
	}


}	// end of class
