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

package com.example.mvstate.mosby;

import android.app.Application;
import android.content.Context;
import com.example.mvstate.mosby.dependencyinjection.DependencyInjection;
import timber.log.Timber;

/**
 * A custom Application class mainly used to provide dependency injection
 *
 * @author Hannes Dorfmann
 */
public class SampleApplication extends Application {

  protected DependencyInjection dependencyInjection = new DependencyInjection();

  {
    Timber.plant(new Timber.DebugTree());
  }

  public static DependencyInjection getDependencyInjection(Context context) {
    return ((SampleApplication) context.getApplicationContext()).dependencyInjection;
  }


  @Override public void onCreate() {
    super.onCreate();
    Timber.d("Starting Application");
  }
}
