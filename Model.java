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

  //region Closing actions
  @Action
  public void closeView() throws Exception {
    myHistoryAdapter.closeView();
    if(prevView == null) throw new Exception("");
    state = prevView;
    prevView = null;
  }

  public boolean closeViewGuard() {
    return state == State.HistoryViewSourceTree || state == State.ClosingDialog;
  }

  @Action
  public void closingDialog() {
    myHistoryAdapter.closingDialog();
    state = prevView;
  }

  public boolean closingViewGuard() {
    return state == State.DifferenceViewReadOnly;
  }
  //endregion

  //region HistoryViewSourceTree
  @Action
  public void showHistoryOfFolder() {
    myHistoryAdapter.showHistoryFolder();
    state = State.HistoryViewSourceTree;
  }

  public boolean showHistoryOfFolderGuard() {
    return state == State.Idle;
  }

  @Action
  public void newSelectionSourceTree() {
    myHistoryAdapter.newSelectionSourceTree();
  }

  public boolean newSelectionSourceTreeGuard() {
    return state == State.HistoryViewSourceTree;
  }

  @Action
  public void showDifferenceReadOnlyFromSourceTree() {
    myHistoryAdapter.showDifferenceReadOnlyFromSourceTree();
    prevView = State.HistoryViewSourceTree;
    state = State.DifferenceViewReadOnly;
  }

  public boolean showDifferenceReadOnlyFromSourceTreeGuard() {
    return state == State.HistoryViewSourceTree;
  }
  //endregion

  //region History View - File Differences
  @Action
  public void showHistoryFile() {
    myHistoryAdapter.showHistoryFile();
    state = State.HistoryViewFileDifference;
  }

  public boolean showHistoryFileGuard() {
    return state == State.Idle;
  }

  @Action
  public void showHistoryClass() {
    myHistoryAdapter.showHistoryClass();
    state = State.HistoryViewFileDifference;
  }

  public boolean showHistoryClassGuard() {
    return state == State.Idle;
  }

  @Action
  public void showHistoryMethod() {
    myHistoryAdapter.showHistoryMethod();
    state = State.HistoryViewFileDifference;
  }

  public boolean showHistoryMethodGuard() {
    return state == State.Idle;
  }

  @Action
  public void showHistoryField() {
    myHistoryAdapter.showHistoryField();
    state = State.HistoryViewFileDifference;
  }

  public boolean showHistoryFieldGuard() {
    return state == State.Idle;
  }

  @Action
  public void showHistorySelection() {
    myHistoryAdapter.showHistorySelection();
    state = State.HistoryViewFileDifference;
  }

  public boolean showHistorySelectionGuard() {
    return state == State.Idle;
  }

  @Action
  public void newSelectionFileDifferences() {
    myHistoryAdapter.newSelectionFileDifferences();
  }

  public boolean newSelectionFileDifferencesGuard() {
    return state == State.HistoryViewFileDifference;
  }
  //endregion

  //region Recent Changes

  @Action
  public void recentChanges() {
    myHistoryAdapter.recentChanges();
    state = State.ListViewRecentChanges;
  }

  public boolean recentChangesGuard() {
    return state == State.Idle;
  }

  @Action
  public void newSelectionRecentChanges() {
    myHistoryAdapter.newSelectionRecentChanges();
  }

  public boolean newSelectionRecentChangesGuard() {
    return state == State.ListViewRecentChanges;
  }

  @Action
  public void showSelectedRecentChanges() {
    myHistoryAdapter.showSelectedRecentChanges();
    state = State.HistoryViewSingleFile;
  }

  public boolean showSelectedRecentChangesGuard() {
    return state == State.ListViewRecentChanges;
  }

  @Action
  public void showDifferenceReadOnlyFromSingleFile() {
    myHistoryAdapter.showDifferenceReadOnlyFromSingleFile();
    prevView = state;
    state = State.DifferenceViewReadOnly;
  }

  public boolean showDifferenceReadOnlyFromSingleFileGuard() {
    return state == State.HistoryViewSingleFile;
  }
  //endregion

  //region Revert

  @Action
  public void revertFromSourceTree() {
    myHistoryAdapter.revertFromSourceTree();
    prevView = state;
    state = State.Reverting;
  }

  public boolean revertFromSourceTreeGuard() {
    return state == State.HistoryViewSourceTree;
  }

  @Action
  public void revertFromFileDifference() {
    myHistoryAdapter.revertFromFileDifference();
    prevView = state;
    state = State.Reverting;
  }

  public boolean revertFromFileDifferenceGuard() {
    return state == State.HistoryViewFileDifference; 
  }

  @Action
  public void revertFromSingleFile() {
    myHistoryAdapter.revertFromSingleFile();
    prevView = state;
    state = State.Reverting;
  }

  public boolean revertFromSingleFileGuard() {
    return state == State.HistoryViewSingleFile;
  }

  @Action
  public void returnFromReverting() {
    myHistoryAdapter.returnFromReverting();
    state = prevView;
  }

  public boolean returnFromRevertingGuard() {
    return state == State.FinishReverting;
  }
  
  //endregion
  
}
