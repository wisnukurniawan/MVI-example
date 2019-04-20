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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.mvstate.R;
import com.example.mvstate.mosby.businesslogic.model.Product;
import com.example.mvstate.mosby.dependencyinjection.DependencyInjection;

/**
 * View Holder just to display
 *
 * @author Hannes Dorfmann
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {

  public interface ProductClickedListener {
    void onProductClicked(Product product);
  }

  public static ProductViewHolder create(LayoutInflater inflater, ProductClickedListener listener) {
    return new ProductViewHolder(inflater.inflate(R.layout.item_product, null, false), listener);
  }

  @BindView(R.id.productImage) ImageView image;
  @BindView(R.id.productName) TextView name;

  private Product product;

  private ProductViewHolder(View itemView, ProductClickedListener clickedListener) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    itemView.setOnClickListener(v -> clickedListener.onProductClicked(product));
  }

  public void bind(Product product) {
    this.product = product;
    Glide.with(itemView.getContext())
        .load(DependencyInjection.BASE_IMAGE_URL + product.getImage())
        .centerCrop()
        .into(image);
    name.setText(product.getName());
  }
}
