/**
 * Wire
 * Copyright (C) 2016 Wire Swiss GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.waz.zclient.controllers.singleimage;

import android.view.View;
import com.waz.api.Message;
import com.waz.api.User;

import java.util.ArrayList;
import java.util.List;

public class SingleImageController implements ISingleImageController {

    private List<SingleImageObserver> observerList;
    private View imageContainer;
    private Message message;

    public SingleImageController() {
        observerList = new ArrayList<>();
    }

    @Override
    public void addSingleImageObserver(SingleImageObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void removeSingleImageObserver(SingleImageObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void hideSingleImage() {
        for (SingleImageObserver observer : observerList) {
            observer.onHideSingleImage();
        }
    }

    @Override
    public Message getMessage() {
        return message;
    }

    @Override
    public void showSingleImage(Message message) {
        this.message = message;
        for (SingleImageObserver observer : observerList) {
            observer.onShowSingleImage(message);
        }
    }

    @Override
    public void showSingleImage(User user) {
        this.message = null;
        for (SingleImageObserver observer : observerList) {
            observer.onShowUserImage(user);
        }
    }

    @Override
    public void setViewReferences(View imageContainer) {
        this.imageContainer = imageContainer;
    }

    @Override
    public View getImageContainer() {
        return imageContainer;
    }

    @Override
    public void tearDown() {
        observerList.clear();
        observerList = null;
        clearReferences();
    }

    @Override
    public void clearReferences() {
        message = null;
        imageContainer = null;
    }
}
