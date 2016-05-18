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

import com.intellij.history.core.LocalHistoryFacade;
import com.intellij.history.core.LocalHistoryTestCase;
import com.intellij.history.core.revisions.Revision;
import com.intellij.history.integration.ui.LocalHistoryUITestCase;
import com.intellij.history.integration.ui.models.HistoryDialogModel;
import com.intellij.history.integration.ui.views.DirectoryHistoryDialog;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.io.PersistentHashMapValueStorage;

import javax.xml.ws.Action;
import java.io.IOException;
import java.util.List;

/**
 * Created by hui on 16/05/16.
 */
public class HistoryAdapter extends LocalHistoryUITestCase {

  private long myCurrentId = 0;
  private int changes = 0;

  DirectoryHistoryDialog myDirectoryHistoryDialog;
  HistoryDialogModel myHistoryDialogModel;

  public HistoryAdapter() {
    try {
      System.out.println("Running setUp!");
      setUp();
      System.out.println("Completed setUp!");
    }


    catch (Exception e) {
      System.out.println(e);
      System.exit(-1);
    }
  }

  public void tearDown() throws Exception {
    super.tearDown();
  }

  public void showHistoryFolder() {
    DirectoryHistoryDialog d = new DirectoryHistoryDialog(myProject, myGateway, myRoot);
    Disposer.dispose(d);
  }
  /*
  public void showHistoryFolder(){

    System.out.println("In showHistoryFolder!");
    //LocalHistoryFacade facade = getVcs()
    //TODO
    myDirectoryHistoryDialog = new DirectoryHistoryDialog(myProject, myGateway, myRoot);
    Disposer.dispose(myDirectoryHistoryDialog);

    /*
    System.out.println("Passed it.");

    //TODO create somewhere else?
    createChildData(myRoot, getNewFilename());
    createChildData(myRoot, getNewFilename());

    System.out.println(getVcs().getChangeListInTests().getChangesInTests().size());
    System.out.println(getVcs().getChangeListInTests().getChangesInTests().toString());

  }
    */

  public void newSelectionSourceTree(){
    //TODO
  }

  public void closeView(){
    //TODO - Implement for all views

    // Dispose of history dialog if instance exists
    if (myDirectoryHistoryDialog != null) {
      Disposer.dispose(myDirectoryHistoryDialog);
      myDirectoryHistoryDialog = null;
      myHistoryDialogModel = null;
    }
  }

  public void showDifferenceReadOnly(){
    //TODO

  }

  public void closingView() {
    //TODO
  }

  public void closingDialog() {
    //TODO
  }

  public void showDifferenceReadOnlyFromSourceTree() {
    //TODO
  }

  public void showHistoryFile() {
    //TODO
  }

  public void showHistoryClass() {
    //TODO
  }

  public void showHistoryMethod() {
    //TODO
  }

  public void showHistoryField() {
    //TODO
  }

  public void showHistorySelection() {
    //TODO
  }

  public void recentChanges() {
    //TODO
  }

  public void showSelectedRecentChanges() {
    //TODO
  }

  public void showDifferenceReadOnlyFromSingleFile() {
    //TODO
  }

  public void newSelectionFileDifferences() {
    //TODO
  }

  public void newSelectionRecentChanges() {
    //TODO
  }

  public void revertFromSourceTree() {
    //TODO
  }

  public void revertFromFileDifference() {
    //TODO
  }

  public void revertFromSingleFile() {
    //TODO
  }

  public void returnFromReverting() {
    //TODO
  }

  /* *** HELPERS *** */
  private long nextId() {
    return myCurrentId++;
  }

  private String getNewFilename() {
    StringBuilder s = new StringBuilder();
    s.append("file");
    s.append(nextId());
    s.append(".txt");

    return  s.toString();
  }
}
