/*
 * Copyright 2016 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.mvstate.mosby.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/** An ImageView that keeps asp
 * @author Hannes Dorfmann
 */
public class AspectRatioImageView extends AppCompatImageView {

  public AspectRatioImageView(Context context) {
    super(context);
  }

  public AspectRatioImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int measuredHeight = ((int) (((double) getMeasuredWidth()) / 0.68636363));
    setMeasuredDimension(getMeasuredWidth(), measuredHeight);
  }
}
