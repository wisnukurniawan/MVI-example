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

package com.example.mvstate.mosby.view.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mvstate.R;
import com.example.mvstate.mosby.businesslogic.model.MainMenuItem;

/**
 * Simple ViewHolder to display a list of main menu items
 *
 * @author Hannes Dorfmann
 */
public class MainMenuViewHolder extends RecyclerView.ViewHolder {

  public interface MainMenuSelectionListener {
    public void onItemSelected(String categoryName);
  }

  public static MainMenuViewHolder create(LayoutInflater inflater,
      MainMenuSelectionListener listener) {
    return new MainMenuViewHolder(inflater.inflate(R.layout.item_main_menu, null, false), listener);
  }

  TextView name;

  private MainMenuViewHolder(View itemView, MainMenuSelectionListener listener) {
    super(itemView);
    name = itemView.findViewById(R.id.name);
    itemView.setOnClickListener(v -> listener.onItemSelected(name.getText().toString()));
  }

  public void bind(MainMenuItem item) {
    name.setText(item.getName());
  }
}
