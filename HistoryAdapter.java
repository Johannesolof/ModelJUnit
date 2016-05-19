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

import com.intellij.history.core.revisions.RecentChange;
import com.intellij.history.integration.revertion.Reverter;
import com.intellij.history.integration.ui.LocalHistoryUITestCase;
import com.intellij.history.integration.ui.models.EntireFileHistoryDialogModel;
import com.intellij.history.integration.ui.models.FileHistoryDialogModel;
import com.intellij.history.integration.ui.models.RecentChangeDialogModel;
import com.intellij.history.integration.ui.views.DirectoryHistoryDialog;
import com.intellij.history.integration.ui.views.RecentChangeDialog;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * Created by hui on 16/05/16.
 */
public class HistoryAdapter extends LocalHistoryUITestCase {

  private long myCurrentId = 0;

  DirectoryHistoryDialog myDirectoryHistoryDialog;
  FileHistoryDialogModel myFileHistoryDialogModel;

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
    myDirectoryHistoryDialog = new DirectoryHistoryDialog(myProject, myGateway, myRoot);
    //TODO create somewhere else?
    createChildData(myRoot, getNewFilename());
    createChildData(myRoot, getNewFilename());
  }

  public void newSelectionSourceTree(){
    //TODO
  }

  public void closeView(){
    //TODO - Implement for all views

    // Dispose of history dialog if instance exists
    if (myDirectoryHistoryDialog != null) {
      Disposer.dispose(myDirectoryHistoryDialog);
      myDirectoryHistoryDialog = null;
    }
    myFileHistoryDialogModel = null;
  }

  public void closingDialog() {
    //TODO
  }

  public void showDifferenceReadOnlyFromSourceTree() {

    createChildData(myRoot, getNewFilename());
    createChildData(myRoot, getNewFilename());

    myFileHistoryDialogModel.selectRevisions(1, 1);

    //TODO
  }


  public void showHistoryFile() {
    VirtualFile f = createChildData(myRoot, getNewFilename());
    setBinaryContent(f, "old".getBytes());
    setBinaryContent(f, "new".getBytes());
    setBinaryContent(f, "current".getBytes());
    myFileHistoryDialogModel = createFileModel(f);
    myFileHistoryDialogModel.selectRevisions(0, 1);
    //TODO
  }

  public void showHistoryClass() {

    VirtualFile f = createChildData(myRoot, getNewFilename());
    setBinaryContent(f, "old".getBytes());
    setBinaryContent(f, "new".getBytes());
    setBinaryContent(f, "current".getBytes());
    myFileHistoryDialogModel = createFileModel(f);
    myFileHistoryDialogModel.selectRevisions(0, 1);
    //TODO
  }

  public void showHistoryMethod() {

    VirtualFile f = createChildData(myRoot, getNewFilename());
    setBinaryContent(f, "old".getBytes());
    setBinaryContent(f, "new".getBytes());
    setBinaryContent(f, "current".getBytes());
    myFileHistoryDialogModel = createFileModel(f);
    myFileHistoryDialogModel.selectRevisions(0, 1);
    //TODO
  }

  public void showHistoryField() {
    VirtualFile f = createChildData(myRoot, getNewFilename());
    setBinaryContent(f, "old".getBytes());
    setBinaryContent(f, "new".getBytes());
    setBinaryContent(f, "current".getBytes());
    myFileHistoryDialogModel = createFileModel(f);
    myFileHistoryDialogModel.selectRevisions(0, 1);
    //TODO
  }

  public void showHistorySelection() {
    VirtualFile f = createChildData(myRoot, getNewFilename());
    setBinaryContent(f, "old".getBytes());
    setBinaryContent(f, "new".getBytes());
    setBinaryContent(f, "current".getBytes());
    myFileHistoryDialogModel = createFileModel(f);
    myFileHistoryDialogModel.selectRevisions(0, 1);
    //TODO
  }

  public void recentChanges() {
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(0);
    RecentChangeDialog d = null;

    try {
      d = new RecentChangeDialog(myProject, myGateway, c);
    }
    finally {
      if (d != null) {
        Disposer.dispose(d);
      }
    }
    //TODO
  }

  public void showSelectedRecentChanges() {
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(0);
    RecentChangeDialog d = null;

    try {
      d = new RecentChangeDialog(myProject, myGateway, c);
    }
    finally {
      if (d != null) {
        Disposer.dispose(d);
      }
    }
    //TODO
  }

  public void showDifferenceReadOnlyFromSingleFile() {
    //TODO
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(0);
    RecentChangeDialog d = null;

    try {
      d = new RecentChangeDialog(myProject, myGateway, c);
    }
    finally {
      if (d != null) {
        Disposer.dispose(d);
      }
    }
  }

  public void newSelectionFileDifferences() {
    //TODO
  }

  public void newSelectionRecentChanges() {

  }

  public void revertFromSourceTree() throws IOException {
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("another change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(1);
    RecentChangeDialogModel m = new RecentChangeDialogModel(myProject, myGateway, getVcs(), c);

    Reverter r = m.createReverter();
    r.revert();
    //TODO
  }

  public void revertFromFileDifference() throws IOException {
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("another change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(1);
    RecentChangeDialogModel m = new RecentChangeDialogModel(myProject, myGateway, getVcs(), c);

    Reverter r = m.createReverter();
    r.revert();
    //TODO
  }

  public void revertFromSingleFile() throws Exception {
    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("change");

    getVcs().beginChangeSet();
    createChildData(myRoot, getNewFilename());
    getVcs().endChangeSet("another change");

    RecentChange c = getVcs().getRecentChanges(getRootEntry()).get(1);
    RecentChangeDialogModel m = new RecentChangeDialogModel(myProject, myGateway, getVcs(), c);

    Reverter r = m.createReverter();
    r.revert();
    //TODO
  }

  public void returnFromReverting() {
    //TODO
    nextId();
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

  private FileHistoryDialogModel createFileModel(VirtualFile f) {
    return new EntireFileHistoryDialogModel(myProject, myGateway, getVcs(), f);
  }

  public void reset() {
    try {
      super.tearDown();
      super.setUp();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
