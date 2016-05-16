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

import com.intellij.icons.AllIcons;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

/**
 * Created by johannes on 2016-05-16.
 */
public class Model implements FsmModel {
  private Adapter adapter = new Adapter();

  private enum State {
    Idle,
    NewChangeSet, NewChange,
  }

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

  //region Changes
  @Action
  public void createFile() {
    adapter.createFile();
    state = State.NewChange;
  }

  public boolean createFileGuard() {
    return state == State.Idle;
  }

  @Action
  public void renameFile() {
    adapter.renameFile();
    state = State.NewChange;
  }

  public boolean renameFileGuard() {
    return state == State.Idle;
  }


  @Action
  public void moveFile() {
    adapter.moveFile();
    state = State.NewChange;
  }

  public boolean moveFileGuard() {
    return state == State.Idle;
  }


  @Action
  public void deleteFile() {
    adapter.deleteFile();
    state = State.NewChange;
  }

  public boolean deleteFileGuard() {
    return state == State.Idle;
  }

  @Action
  public void createFolder() {
    adapter.createFolder();
    state = State.Idle;
  }

  public boolean createFolderGuard() {
    return state == State.NewChangeSet;
  }

  @Action
  public void changeContent() {
    adapter.changeContent();
    state = State.NewChange;
  }

  public boolean changeContentGuard() {
    return state == State.Idle;
  }

  @Action
  public void change() {
    state = State.NewChangeSet;
  }

  public boolean changeGuard() {
    return state == State.NewChange;
  }

  @Action
  public void changeSet() {
    state = State.Idle;
  }

  public boolean changeSetGuard() {
    return state == State.NewChangeSet;
  }

  //endregion
}
