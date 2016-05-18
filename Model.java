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

  private ChangeAdapter myChangeAdapter = new ChangeAdapter();
  private HistoryAdapter myHistoryAdapter = new HistoryAdapter();

  private enum State {
    Idle,
    NewChangeSet, NewChange,
    HistoryViewSourceTree, HistoryViewFileDifference, HistoryViewSingleFile,
    ListViewRecentChanges,
    DifferenceViewReadOnly, ClosingDialog,
    Reverting, FinishReverting,
  }

  private State state = State.Idle;

  private State prevView = State.Idle;

  @Override
  public Object getState() {
    return state;
  }

  @Override
  public void reset(boolean b) {
    myChangeAdapter = new ChangeAdapter();
    state = State.Idle;
  }

  //region Changes
  @Action
  public void createFile() {
    myChangeAdapter.createFile();
    state = State.NewChange;
  }

  public boolean createFileGuard() {
    return state == State.Idle;
  }

  @Action
  public void renameFile() {
    myChangeAdapter.renameFile();
    state = State.NewChange;
  }

  public boolean renameFileGuard() {
    return state == State.Idle;
  }


  @Action
  public void moveFile() {
    myChangeAdapter.moveFile();
    state = State.NewChange;
  }

  public boolean moveFileGuard() {
    return state == State.Idle;
  }


  @Action
  public void deleteFile() {
    myChangeAdapter.deleteFile();
    state = State.NewChange;
  }

  public boolean deleteFileGuard() {
    return state == State.Idle;
  }

  @Action
  public void createFolder() {
    myChangeAdapter.createFolder();
    state = State.NewChange;
  }

  public boolean createFolderGuard() {
    return state == State.Idle;
  }

  @Action
  public void changeContent() {
    myChangeAdapter.changeContent();
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

  @Action
  public void showHistoryOfFolder() {
    myHistoryAdapter.showHistoryFolder();
    state = State.HistoryViewSourceTree;
  }

  public boolean showHistoryOfFolderGuard() {
    return state == State.Idle;
  }

  @Action
  public void closeView() {
    myHistoryAdapter.closeView();
    switch (prevView)
    {
      case Idle:
        state = State.Idle;
        break;
      case HistoryViewSourceTree:
        state = State.HistoryViewSourceTree;
        break;
    }
    prevView = State.Idle;
  }

  public boolean closeViewGuard() {
    return state == State.HistoryViewSourceTree || state == State.ClosingDialog;
  }

  @Action
  public void newSelectionSourceTree() {
    myHistoryAdapter.newSelectionSourceTree();
    state = State.HistoryViewSourceTree;
  }

  public boolean newSelectionSourceTreeGuard() {
    return state == State.HistoryViewSourceTree;
  }

  @Action
  public void showDifferenceReadOnly() {
    myHistoryAdapter.showDifferenceReadOnly();
    prevView = State.HistoryViewSourceTree;
    state = State.DifferenceViewReadOnly;
  }

  public boolean showDifferenceReadOnlyGuard() {
    return state == State.HistoryViewSourceTree;
  }

  @Action
  public void closingView() {
    myHistoryAdapter.closingView();
    state = State.ClosingDialog;
  }

  public boolean closingViewGuard() {
    return state == State.DifferenceViewReadOnly;
  }
  
  //region Revert
/*
  @Action
  public void revertFromSourceTree() {
    myChangeAdapter.revertFromSourceTree();
    prevView = State.HistoryViewSourceTree;
    state = State.Reverting;
  }

  public boolean revertFromSourceTreeGuard() {
    return state == State.HistoryViewSourceTree;
  }

  @Action
  public void revertFromFileDifference() {
    myChangeAdapter.revertFromFileDifference();
    prevView = State.HistoryViewFileDifference;
    state = State.Reverting;
  }

  public boolean revertFromFileDifferenceGuard() {
    return state == State.HistoryViewFileDifference; 
  }

  @Action
  public void revertFromSingleFile() {
    myChangeAdapter.revertFromSingleFile();
    prevView = State.HistoryViewSingleFile;
    state = State.Reverting;
  }

  public boolean revertFromSingleFileGuard() {
    return state == State.HistoryViewSingleFile;
  }
  
  //
  */
  
}
