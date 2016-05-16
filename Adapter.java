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

import com.intellij.history.core.changes.ChangeListTestCase;
import com.intellij.history.core.changes.ChangeSet;
import com.intellij.history.core.tree.Entry;

import java.util.List;

/**
 * Created by johannes on 2016-05-16.
 */
public class Adapter extends ChangeListTestCase {

  int changes = 0;

  public void CreateFile(){
    changes++;
    add(facade, createFile(r, "file" + nextId()));
  }

  public void DeleteFile(){
    if (!r.getChildren().isEmpty())
    {
      changes++;
      add(facade, delete(r, ((Entry)r.getChildren().toArray()[0]).getPath()));
    }
  }

  public void RenameFile(){
    if (!r.getChildren().isEmpty())
    {
      changes++;
      add(facade, rename(r, ((Entry)r.getChildren().toArray()[0]).getPath(), "FILE" + nextId()));
    }
  }

  public void CreateFolder(){
    changes++;
    add(facade, createDirectory(r,"dir"+nextId()));

  }

  public void MoveFile(){
    if(!r.getChildren().isEmpty()){
      changes++;
      add(facade, move(r, ((Entry)r.getChildren().toArray()[0]).getPath(), "root2"));
    }

  }
  public void ChangeContent(){
    if(!r.getChildren().isEmpty()){
      changes++;
      add(facade, changeContent(r, ((Entry)r.getChildren().toArray()[0]).getPath(), "This is a content"));
    }
  }
}

