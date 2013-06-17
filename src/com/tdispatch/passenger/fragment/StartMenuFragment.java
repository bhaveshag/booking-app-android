package com.tdispatch.passenger.fragment;

import android.app.Activity;
import android.view.View;

import com.tdispatch.passenger.R;
import com.tdispatch.passenger.core.TDFragment;
import com.tdispatch.passenger.host.MainMenuHostInterface;

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
public class StartMenuFragment extends TDFragment
{
	@Override
	protected int getLayoutId() {
		return R.layout.start_menu_fragment;
	}

	@Override
	protected void onPostCreateView() {

		int ids[] = { R.id.button_login, R.id.button_register };

		for( int id : ids ) {
			View v = mFragmentView.findViewById( id );
			v.setOnClickListener(mOnClickListener);
		}
	}

	protected MainMenuHostInterface mHostActivity;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mHostActivity = (MainMenuHostInterface)activity;
		} catch( ClassCastException e ) {
			throw new ClassCastException("Host Activity needs to implement MainMenuHostInterface");
		}
	}


	protected View.OnClickListener mOnClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick( View v ) {

			switch( v.getId() ) {

				case R.id.button_login: {
					mHostActivity.showOAuth();
				}
				break;

				case R.id.button_register: {
					mHostActivity.showRegister();
				}
				break;
			}
		}
	};

// end of class
}
