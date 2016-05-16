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

import com.intellij.history.integration.ui.DirectoryHistoryDialogModelTest;
import com.intellij.history.integration.ui.FileHistoryDialogTest;
import com.intellij.history.integration.ui.LocalHistoryUITestCase;
import com.intellij.history.integration.ui.views.FileHistoryDialog;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;

import javax.xml.ws.Action;

/**
 * Created by hui on 16/05/16.
 */
public class HistoryDialogAdapter extends FileHistoryDialogTest {


  int changes = 0;

  @Action
  public void showHistoryFolder(){
    VirtualFile file = createChildData(myRoot, "f.txt");
    FileHistoryDialog d = new FileHistoryDialog(myProject, myGateway, file);
    Disposer.dispose(d);
  }

  @Action
  public void newSelectionSourceTree(){
    //TODO


  }



  @Action
  public void closeView(){
    //TODO

  }

  @Action
  public void showDifferenceReadOnly(){
    //TODO

  }

  @Action
  public void closingView() {
    //TODO
  }
}
