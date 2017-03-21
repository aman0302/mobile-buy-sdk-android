/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2015 Shopify Inc.
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package com.shopify.sample.presenter.cart;

import com.shopify.sample.model.cart.Cart;
import com.shopify.sample.model.cart.CartItem;
import com.shopify.sample.model.cart.CartManager;
import com.shopify.sample.mvp.BasePageListViewPresenter;
import com.shopify.sample.mvp.PageListViewPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;

public final class CartLisViewPresenter extends BasePageListViewPresenter<CartItem, PageListViewPresenter.View<CartItem>> {

  public Observable<Cart> cartObservable() {
    return CartManager.instance().cartObservable();
  }

  @Override protected ObservableTransformer<String, List<CartItem>> nextPageRequestComposer() {
    return upstream -> upstream.flatMapSingle(cursor -> Single.fromCallable(() -> {
      return CartManager.instance().cart().cartItems();
    }));
  }

  public void addCartItem(final CartItem cartItem) {
    CartManager.instance().addCartItem(cartItem);
  }

  public void removeCartItem(final CartItem cartItem) {
    CartManager.instance().removeCartItem(cartItem);
  }
}