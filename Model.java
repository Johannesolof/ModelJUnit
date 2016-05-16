/*
 * Copyright 2000-2016 JetBrains s.r.o.
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
 */
package com.intellij.history.ModelJUnit;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

/**
 * Created by johannes on 2016-05-16.
 */
public class Model implements FsmModel {
  private Adapter adapter = new Adapter();

  private enum State{ Idle, Change}


  private State state = State.Idle;


  @Override
  public Object getState() {
    return state;
  }

  @Override
  public void reset(boolean b) {
    adapter = new Adapter();
    state = State.Idle;
  }

  @Action
  public void createFile() {
    if (state == State.Idle) {
      adapter.CreateFile();
      state = State.Idle;
    }
  }

  @Action
  public void deleteFile() {
    if (state == State.Idle) {
      adapter.DeleteFile();
      state = State.Idle;
    }
  }

  @Action
  public void renameFile() {
    if (state == State.Idle) {
      adapter.RenameFile();
      state = State.Idle;
    }
  }

}