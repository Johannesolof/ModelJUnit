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

import com.intellij.history.core.InMemoryLocalHistoryFacade;
import com.intellij.history.core.LocalHistoryFacade;
import com.intellij.history.core.LocalHistoryTestCase;
import com.intellij.history.core.changes.ChangeListTestCase;
import com.intellij.history.core.tree.Entry;
import com.intellij.history.core.tree.RootEntry;

/**
 * Created by johannes on 2016-05-16.
 */
public class ChangeAdapter extends ChangeListTestCase {

  private int changes = 0;

  public void createFile() {
    changes++;
    add(facade, createFile(r, "file" + nextId()));
  }

  public void deleteFile() {
    if (!r.getChildren().isEmpty()) {
      changes++;
      add(facade, delete(r, ((Entry)r.getChildren().toArray()[0]).getPath()));
    }
  }

  public void renameFile() {
    if (!r.getChildren().isEmpty()) {
      changes++;
      add(facade, rename(r, ((Entry)r.getChildren().toArray()[0]).getPath(), "FILE" + nextId()));
    }
  }

  public void createFolder() {
    changes++;
    add(facade, createDirectory(r, "dir" + nextId()));
  }

  public void moveFile() {
    if (!r.getChildren().isEmpty()) {
      changes++;
      addChangeSet(facade, "1", createDirectory(r, "dir1"), createDirectory(r, "dir2"));
      String filePath = "file" + nextId();
      add(facade, createFile(r, filePath, "one"));
      addChangeSet(facade, "3", move(r, filePath, "dir2"));
    }
  }

  public void changeContent() {
    if (!r.getChildren().isEmpty()) {
      changes++;
      String filePath = "file" + nextId();
      add(facade, createFile(r, filePath, "one"));
      add(facade, changeContent(r, filePath, "two"));
    }
  }


}

