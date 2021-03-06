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

package com.example.mvstate.mosby.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.mvstate.R;
import com.example.mvstate.mosby.SampleApplication;
import com.example.mvstate.mosby.businesslogic.interactor.details.ProductDetailsViewState;
import com.example.mvstate.mosby.businesslogic.model.Product;
import com.example.mvstate.mosby.dependencyinjection.DependencyInjection;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.Observable;
import timber.log.Timber;

import java.util.Locale;

public class ProductDetailsActivity extends MviActivity<ProductDetailsView, ProductDetailsPresenter>
    implements ProductDetailsView {

  public static final String KEY_PRODUCT_ID = "productId";
  private Product product;
  private boolean isProductInshoppingCart = false;
  private Observable<Boolean> fabClickObservable;

  View errorView;
 View loadingView;
  View detailsView;
  TextView price;
  TextView description;
  FloatingActionButton fab;
  ImageView backdrop;
  Toolbar toolbar;
   ViewGroup rootView;
  CollapsingToolbarLayout collapsingToolbarLayout;

  public static void start(Activity activity, Product product) {
    Intent i = new Intent(activity, ProductDetailsActivity.class);
    i.putExtra(KEY_PRODUCT_ID, product.getId());
    activity.startActivity(i);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    detailsView = findViewById(R.id.detailsView);
    price = findViewById(R.id.price);
    loadingView = findViewById(R.id.loadingView);
    errorView = findViewById(R.id.errorView);
    description = findViewById(R.id.description);
    fab = findViewById(R.id.fab);
    backdrop = findViewById(R.id.backdrop);
    toolbar = findViewById(R.id.toolbar);
    rootView = findViewById(R.id.root);
    collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    fabClickObservable = RxView.clicks(fab).share().map(ignored -> true);
  }

  @NonNull
  @Override public ProductDetailsPresenter createPresenter() {
    Timber.d("Create presenter");
    return SampleApplication.getDependencyInjection(this).newProductDetailsPresenter();
  }

  @Override public Observable<Integer> loadDetailsIntent() {
    return Observable.just(getIntent().getIntExtra(KEY_PRODUCT_ID, 0));
  }

  @Override public Observable<Product> addToShoppingCartIntent() {
    return fabClickObservable.filter(item -> product != null)
        .filter(item -> !isProductInshoppingCart)
        .map(item -> product);
  }

  @Override public Observable<Product> removeFromShoppingCartIntent() {
    return fabClickObservable.filter(item -> product != null)
        .filter(item -> isProductInshoppingCart)
        .map(item -> product);
  }

  @Override public void render(ProductDetailsViewState state) {
    Timber.d("render " + state);

    if (state instanceof ProductDetailsViewState.LoadingState) {
      renderLoading();
    } else if (state instanceof ProductDetailsViewState.DataState) {
      renderData((ProductDetailsViewState.DataState) state);
    } else if (state instanceof ProductDetailsViewState.ErrorState) {
      renderError();
    } else {
      throw new IllegalStateException("Unknown state " + state);
    }
  }

  private void renderError() {
    TransitionManager.beginDelayedTransition(rootView);
    errorView.setVisibility(View.VISIBLE);
    loadingView.setVisibility(View.GONE);
    detailsView.setVisibility(View.GONE);
  }

  private void renderData(ProductDetailsViewState.DataState state) {
    TransitionManager.beginDelayedTransition(rootView);
    errorView.setVisibility(View.GONE);
    loadingView.setVisibility(View.GONE);
    detailsView.setVisibility(View.VISIBLE);

    isProductInshoppingCart = state.getDetail().isInShoppingCart();
    product = state.getDetail().getProduct();
    price.setText("Price: $" + String.format(Locale.US, "%.2f", product.getPrice()));
    description.setText(product.getDescription());
    toolbar.setTitle(product.getName());
    collapsingToolbarLayout.setTitle(product.getName());

    if (isProductInshoppingCart) {
      fab.setImageResource(R.drawable.ic_in_shopping_cart);
    } else {
      fab.setImageResource(R.drawable.ic_add_shopping_cart);
    }

    Glide.with(this)
        .load(DependencyInjection.BASE_IMAGE_URL + product.getImage())
        .centerCrop()
        .into(backdrop);
  }

  private void renderLoading() {
    TransitionManager.beginDelayedTransition(rootView);
    errorView.setVisibility(View.GONE);
    loadingView.setVisibility(View.VISIBLE);
    detailsView.setVisibility(View.GONE);
  }
}
